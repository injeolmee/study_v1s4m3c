/**
 * My_pds Controller - 내 스터디 컨트롤러
 * <pre>
 * 프로젝트명     : (주)솔데스크 IT 교육센터 JAVA CBD Project 1조
 * PMO, PM        : 지도 훈련교사
 * 패키지명       : ${dev.mvc.my_pds}
 * 파일명         : ${My_pdsCont.java} ${date}
 * 작성자         : 이동석
 * 작성자 email   : lee33398@naver.com
 * 수정내용
 * ------------------------------------------------------------------
 * 수정 이력
 * ------------------------------------------------------------------ 
 * 수정일        수정자  연락처               수정 내용
 * ------------------------------------------------------------------ 
 * 2017-11-22    이동석 lee33398@naver.com    Controller 최초 작성
 * 2017-11-23    이동석 lee33398@naver.com
 * 2017-11-23    이동석 lee33398@naver.com
 * 2017-11-23    이동석 lee33398@naver.com
 * 2017-11-23    이동석 lee33398@naver.com
 * 2017-11-23    이동석 lee33398@naver.com
 * 2017-11-23    이동석 lee33398@naver.com
 * 2017-11-23    이동석 lee33398@naver.com
 * 2017-11-23    이동석 lee33398@naver.com
 * ------------------------------------------------------------------
 * 
 *</pre>
 */
package dev.mvc.my_pds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.memsearch.MemsearchProcInter;
import dev.mvc.memsearch.MemsearchVO;
import dev.mvc.message.MessageProcInter; 
import dev.mvc.my_std_catelist.My_std_catelistVO;
import dev.mvc.recruit.RecruitProcInter;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

import net.sf.json.JSONArray;

@Controller
public class My_pdsCont {
  
  @Autowired
  @Qualifier("dev.mvc.my_pds.My_pdsProc")
  private My_pdsProcInter my_pdsProc;
  
  @Autowired
  @Qualifier("dev.mvc.memsearch.MemsearchProc") 
  private MemsearchProcInter memsearchProc;
  
  @Autowired
  @Qualifier("dev.mvc.message.MessageProc")
  private MessageProcInter messageProc; 
  
  @Autowired 
  @Qualifier("dev.mvc.recruit.RecruitProc")
  private RecruitProcInter recruitProc;
  
  @RequestMapping(value="/user/mystudy/mystudy_space.do", method=RequestMethod.GET)
  public ModelAndView myStudy_space(
      HttpServletRequest request,
      @RequestParam(value="stdlist_no") int stdlist_no
      ){
    
    // stdlist_no, memberno -> std_auth 검사
    HttpSession session=request.getSession(false);
    int memberno=(Integer)session.getAttribute("memberno");
    
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    hashMap.put("stdlist_no", stdlist_no);
    hashMap.put("memberno", memberno);
     
    String std_auth=recruitProc.check_leader(hashMap);
    
    System.out.println("현재 접속한 회원의 std_auth:"+std_auth);
    
    session.setAttribute("std_auth", std_auth);
    
    System.out.println(" --> myStudy_space 호출");
    ModelAndView mav=new ModelAndView();
    
    mav.addObject("std_auth", std_auth); 
    mav.setViewName("/user/my_pds/mystudy_space");
    
    return mav;
  }
  
  /**
   * 게시글 전송 AJAX + 검색 기능 추가 + 검색 결과 수 
   * @param cateno
   * @param stdlist_no
   * @param pdsword
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/user/mystudy/pds_notice.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String pds_notice(int cateno, int stdlist_no, String pdsword, int nowpage){
    System.out.println(" --> pds_notice 호출 (글 목록 출력 AJAX)");
    // mylistno를 먼저 조회해야함. -> stdlist_no, cateno 필요함.
    HashMap<String, Integer> hm_mylistno =new HashMap<String, Integer>();
    hm_mylistno.put("stdlist_no", stdlist_no);
    hm_mylistno.put("cateno", cateno);
    
    // 여기서 mylistno를 가져옴. 
    My_std_catelistVO catelistVO=my_pdsProc.search_mylistno(hm_mylistno);
    
    HashMap<String, Object> hashMap =new HashMap<String, Object>();
    hashMap.put("mylistno", catelistVO.getMylistno());
    hashMap.put("pdsword", pdsword);
    
    // ===============페이징을 위한 코드 시작==================================
    int beginOfPage = (nowpage - 1) * 3;
    
    int startNum = beginOfPage + 1; // 시작 rownum, 1
    int endNum = beginOfPage + 3; // 종료 rownum, 3
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    //==========================================================================
    
    // 조회된 mylistno를 바탕으로 my_pds 조회 -> mylistno가 1인 글만 조회.
    List<My_pdsVO> list=my_pdsProc.list(hashMap);
    int search_count=my_pdsProc.search_count(hashMap);  // 검색 결과 수 조회.
    
    System.out.println("search_count :"+search_count);
    
    String paging=my_pdsProc.paging(search_count, nowpage, cateno, stdlist_no); // 페이징 결과 값.
    
    // list는 mylistno가 1인 글의 정보를 담고 있음.
    // memberno를 가지고 있는데 이를 [회원이름]으로 바꿔야 함. --> ok!
    for(int i=0; i<list.size(); i++){
      MemsearchVO memsearchVO=memsearchProc.search(list.get(i).getMemberno()); // 회원 번호로 아이디, 이름을 가지는 VO 객체 정의
      
      list.get(i).setMemid(memsearchVO.getMemid());     // 아이디 조회 후 저장
      list.get(i).setMemname(memsearchVO.getMemname()); // 이름 조회 후 저장
      list.get(i).setCateno(cateno);
      list.get(i).setStdlist_no(stdlist_no); 
      list.get(i).setSearch_count(search_count);
    }
    
    // paging 메소드를 통해 산출된 결과를 JSON으로 변경하기 위해 저장.
    JSONObject page=new JSONObject();
    page.put("paging", paging);
    
    JSONArray reply = JSONArray.fromObject(list);
    reply.add(page);   // JSONArray에 추가.
    
    return reply.toString();
  }
  
  /**
   * 글 등록 폼
   * @param stdlist_no
   * @param cateno
   * @return
   */ 
  @RequestMapping(value="/user/mystudy/create.do", method=RequestMethod.GET)
  public ModelAndView mypds_create(HttpServletRequest request,int stdlist_no){
    System.out.println(" --> mypds_create [GET]호출");
    ModelAndView mav=new ModelAndView();
    
    HttpSession session=request.getSession(false);
    int memberno=(Integer)session.getAttribute("memberno");
    
    mav.setViewName("/user/my_pds/my_pds_create");
    
    // mylistno를 넘겨줘야함.
    // mylistno를 먼저 조회해야함. -> stdlist_no, cateno 필요함.
    /*HashMap<String, Integer> hm_mylistno =new HashMap<String, Integer>();
    hm_mylistno.put("stdlist_no", stdlist_no);
    hm_mylistno.put("cateno", cateno);
    
    My_std_catelistVO catelistVO=my_pdsProc.search_mylistno(hm_mylistno);
    
    mav.addObject("mylistno", catelistVO.getMylistno());*/
    
    return mav;
  }
  
  /**
   * 글 등록 처리 컨트롤러
   * jsp - hidden : memberno, stdlist_no, cateno, mylistno
   * 
   * jsp에서 보낸 mylistno는 my_pdsVO에 소속되어 컨트롤러에 전달된다.
   *              memberno도 동일하다.
   * 
   * @param my_pdsVO - 글 정보를 전달
   * @param request  - 해당 jsp의 request 객체
   * @param stdlist_no - 돌아갈 위치
   * @param cateno   - 돌아갈 위치
   * @return 
   */
  @RequestMapping(value="/user/mystudy/create.do", method=RequestMethod.POST)
  public ModelAndView mypds_create(My_pdsVO my_pdsVO, HttpServletRequest request, int stdlist_no, int cateno){
    System.out.println(" --> mypds_create [POST]호출");
    ModelAndView mav=new ModelAndView();
    
    // mylistno 구하기
    HashMap<String, Integer> hm_mylistno =new HashMap<String, Integer>();
    hm_mylistno.put("stdlist_no", stdlist_no);
    hm_mylistno.put("cateno", cateno);
    My_std_catelistVO catelistVO=my_pdsProc.search_mylistno(hm_mylistno);
    my_pdsVO.setMylistno(catelistVO.getMylistno());
    
    System.out.println("getSpring_file() : "+my_pdsVO.getFile1MF());
    //==파일 전송 코드 시작===============================================================================
    String upDir=Tool.getRealPath(request, "/my_pds/storage");
    MultipartFile file1MF=my_pdsVO.getFile1MF();    // 입려되는 파일 명
    long size1=file1MF.getSize();
    
    String file1="";
    String thumb="";
    
    if(size1>0){ 
      file1=Upload.saveFileSpring(file1MF, upDir);
      
      if(Tool.isImage(file1)){
        thumb=Tool.preview(upDir, file1, 120, 80);  // Thumb 이미지 생성.
      }
    }
    
    my_pdsVO.setPdsfile1(file1);
    my_pdsVO.setPdsfilesize((int)size1);
    my_pdsVO.setPdsthumb(thumb);
    
    // ===================================================================================================
    my_pdsVO.setPdsword(my_pdsVO.getPdsword());  // 검색어 일단 보류
    
    ArrayList<String> result_msg=new ArrayList<String>();
    ArrayList<String> result_link=new ArrayList<String>();
    
    int count=my_pdsProc.insert(my_pdsVO);  // 등록 수행
    
    int lastest_pdsno=my_pdsProc.lastest_pdsno(); // 가장 최근에 등록된 pdsno
    
    if(count==1){ // 등록 성공
      //result_msg.add("글 등록에 성공하였습니다."); 
      //result_link.add("<button type='button' class='btn btn-info' onclick=\"location.href='./read.do?stdlist_no="+stdlist_no+"&pdsno="+lastest_pdsno+"'\">등록 확인</button>");
      mav.setViewName("redirect:/user/mystudy/mystudy_space.do?stdlist_no="+stdlist_no);  
    }else{ // 등록 실패  
      mav.setViewName("/user/my_pds/message");  
      result_msg.add("글 등록에 실패하였습니다.");
      result_msg.add("관리자에게 문의해주세요.");
      result_link.add("<button type='button' class='btn btn-warning' onclick=\"history.back()\">다시 시도</button>");
      mav.addObject("result_msg", result_msg);
      mav.addObject("result_link", result_link);
    }
    return mav;
  }
  
  /**
   * 글 내용 조회 GET 컨트롤러
   * @param pdsno
   * @return
   */
  @RequestMapping(value="/user/mystudy/read.do", method=RequestMethod.GET)
  public ModelAndView read(int pdsno){
    ModelAndView mav=new ModelAndView(); 
    mav.setViewName("/user/my_pds/my_pds_read");
    
    My_pdsVO read=my_pdsProc.read(pdsno);
    
    String memname=my_pdsProc.search_memname(read.getMemberno());
    read.setMemname(memname);
    
    System.out.println(pdsno);
    System.out.println(memname);
    
    // 글 조회수 증가
    my_pdsProc.inc_cnt(pdsno);
    
    mav.addObject("My_pdsVO", read);
    
    return mav;
  }
  
  /**
   * 글 수정 GET 컨트롤러
   * @param pdsno
   * @return
   */
  @RequestMapping(value="/user/mystudy/update.do", method=RequestMethod.GET)
  public ModelAndView update(int pdsno){
    ModelAndView mav=new ModelAndView(); 
    mav.setViewName("/user/my_pds/my_pds_update");
    
    My_pdsVO read=my_pdsProc.read(pdsno);
    
    String memname=my_pdsProc.search_memname(read.getMemberno());
    read.setMemname(memname);
    
    mav.addObject("My_pdsVO", read);
    
    return mav;
  }
  
  //글 수정 처리 POST 컨트롤러
  /**
   * 글 수정 처리 POST 컨트롤러
   * @param my_pdsVO
   * @param request
   * @param mylistno
   * @return
   */
  @RequestMapping(value="/user/mystudy/update.do", method=RequestMethod.POST)
  public ModelAndView update(My_pdsVO my_pdsVO, HttpServletRequest request, int mylistno){
    System.out.println(" --> update() POST 호출 ");
    ModelAndView mav=new ModelAndView();
    // ---------------------------------------------------------------------------
    // 파일 전송
    // ---------------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/my_pds/storage");
    
    System.out.println(upDir);
    /*
    <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
    ↓
     name='file1MF'에 해당하는 필드를 찾아서 File 객체를 자동으로 할당
    ↓
    BlogVO.java: private MultipartFile file1MF;
    ↓
     파일 객체 사용: MultipartFile file1MF = blogVO.getFile1MF();          
     */
    
    MultipartFile file1MF = my_pdsVO.getFile1MF();
    String file1 = "";                // DBMS 컬럼명
    long size1 = file1MF.getSize();  // 파일 크기
    String thumb = "";
    
    // 기존에 등록된 글 정보 로딩
    My_pdsVO my_pdsVO_old = my_pdsProc.read(my_pdsVO.getPdsno());
    
    if (size1 > 0) { // 파일이 새로 등록되었다면.
      Tool.deleteFile(upDir, my_pdsVO_old.getPdsfile1());  // 기존 이미지 파일 삭제.
      Tool.deleteFile(upDir, my_pdsVO_old.getPdsthumb());  // 기존 thumb 파일 삭제.
      
      // 신규 파일 업로드
      file1 = Upload.saveFileSpring(file1MF, upDir);
      
      // 이미지 파일이라면 thumb 이미지 생성. thumb는 파일 명을 리턴.
      if (Tool.isImage(file1)) {
        thumb = Tool.preview(upDir, file1, 120, 80); // Thumb 이미지 생성
      }
    } else {
      // 파일을 변경하지 않는 경우 기존 ;파일 정보 사용
      // 기존의 파일의 내용이 없는 경우 null이기에 Tool을 사용하여 ""으로 변환한다.
      file1 = Tool.checkNull(my_pdsVO_old.getPdsfile1());
      size1 = my_pdsVO_old.getPdsfilesize();
      thumb = Tool.checkNull(my_pdsVO_old.getPdsthumb());
    }
    
    my_pdsVO.setPdsfile1(file1);
    my_pdsVO.setPdsfilesize((int)size1);
    my_pdsVO.setPdsthumb(thumb); 
    // ---------------------------------------------------------------------------
    
    // ==== 글 수정 시작 ==== //
    
    ArrayList<String> result_msg=new ArrayList<String>();
    ArrayList<String> result_link=new ArrayList<String>();
    
    My_std_catelistVO catelistVO=my_pdsProc.search_cateno_stdlist_no(mylistno);
    
    int stdlist_no=catelistVO.getStdlist_no();
    int cateno=catelistVO.getCateno();
    
    int count=my_pdsProc.update(my_pdsVO);
    
    if(count==1){ // 수정 성공
      //result_msg.add("글 수정에 성공하였습니다.");
      //result_link.add("<button type='button' onclick=\"location.href='./read.do?stdlist_no="+stdlist_no+"&pdsno="+my_pdsVO.getPdsno()+"'\">변경 확인</button>");
      mav.setViewName("redirect:/user/mystudy/read.do?stdlist_no="+stdlist_no+"&pdsno="+my_pdsVO.getPdsno());
    }else{ // 수정 실패
      mav.setViewName("/user/my_pds/message");
      result_msg.add("글 수정에 실패하였습니다.");
      result_msg.add("관리자에게 문의해주세요.");
      result_link.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      result_link.add("<button type='button' onclick=\"location.href='./mystudy_space.do?stdlist_no="+stdlist_no+"'\">글 목록 가기</button>");
      mav.addObject("result_msg", result_msg);
      mav.addObject("result_link", result_link);
    }
    mav.addObject("count", count); 
    
    return mav;
  }
  
  /**
   * 패스워드 검사 - AJAX
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/user/mystudy/check_pdspasswd.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String check_pdspasswd(int pdsno, String pdspasswd){
    System.out.println(" --> check_pdspasswd 호출 ");
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    hashMap.put("pdsno", pdsno);
    hashMap.put("pdspasswd", pdspasswd);
    
    int passwd_check=my_pdsProc.check_passwd(hashMap);
    
    System.out.println("passwd_check : "+passwd_check);
    
    JSONObject obj=new JSONObject();
    
    obj.put("passwd_check", passwd_check); 
    
    return obj.toJSONString();
  }
  
  /**
   * 글 삭제 처리 POST 컨트롤러
   * @param my_pdsVO
   * @param pdsno
   * @return
   */
  @RequestMapping(value="/user/mystudy/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(My_pdsVO my_pdsVO, int pdsno, int stdlist_no, int cateno){
    System.out.println(" --> delete() POST 호출 ");
    ModelAndView mav=new ModelAndView();
    mav.setViewName("/user/my_pds/message"); 
    
    ArrayList<String> result_msg=new ArrayList<String>();
    ArrayList<String> result_link=new ArrayList<String>();
    
    int count=my_pdsProc.delete(pdsno);
    
    if(count==1){ // 삭제 성공
      mav.setViewName("redirect:/user/mystudy/mystudy_space.do?stdlist_no="+stdlist_no);
    }else{ // 수정 실패
      result_msg.add("글 삭제에 실패하였습니다.");
      result_msg.add("관리자에게 문의해주세요.");
      result_link.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      result_link.add("<button type='button' onclick=\"location.href='./mystudy_space.do?stdlist_no="+stdlist_no+"'\">글 목록 가기</button>");
      mav.addObject("result_msg", result_msg);
      mav.addObject("result_link", result_link);
    } 
    mav.addObject("count", count);
    
    return mav;
  }
  
 //좋아요 AJAX
  /**
   * 글 좋아요 증가 컨트롤러
   * @param pdsno
   * @return 성공여부
   */
 @ResponseBody
 @RequestMapping(value="/user/mystudy/like.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
 public String like(int pdsno){
   JSONObject obj=new JSONObject();
   int like_count=my_pdsProc.inc_like(pdsno);
   obj.put("like_count", like_count);
   return obj.toJSONString();
 }
 
 // 파일 삭제 AJAX - [수정]에서 파일을 삭제하는 경우에 사용.
 // 새로운 mybatis를 생성하여 구성함.
 @ResponseBody
 @RequestMapping(value="/user/mystudy/del_file.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
 public String del_file(int pdsno, String filename, String thumb, HttpServletRequest request){
   String upDir = Tool.getRealPath(request, "/my_pds/storage");
   
   System.out.println("upDir :"+upDir);
   System.out.println("filename:"+filename);
   
   JSONObject obj=new JSONObject();
   int del_file_count=my_pdsProc.del_file(pdsno);
   boolean del_file_idx=false;  // 파일 삭제 성공 유무 인덱스
   boolean del_thumb_idx=false; // 썸 파일 삭제 성공 유무 인덱스
   int result=0;                  // 총 결과.
   
   if(del_file_count==1){
     // 이미지 관련 파일이면 썸 파일이 존재하므로 모두 같이 제거.
     if(filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".gif")){
       del_file_idx=Tool.deleteFile(upDir, filename);  // 등록된 이미지 파일 삭제.
       del_thumb_idx=Tool.deleteFile(upDir, thumb);     // 등록된 thumb 파일 삭제.
     }else{  // 이미지 파일이 아닌경우 썸 파일도 없으므로 파일만 제거.
       del_file_idx=Tool.deleteFile(upDir, filename);  // 등록된 이미지 파일 삭제.
     }
   }
   
   if(del_file_count==1 && (del_file_idx==true || del_thumb_idx==true)){
     result=1;
   }else{
     result=0;
   }
   
   obj.put("result", result);
   return obj.toJSONString();
 }
 
 /**
  * 회원 정보를 눌러서 쪽지를 바로 보낼때.
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/mystudy/msn.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
 public String direct_msn(int memberno_send, int memberno_recv, String msg_title, String msg_content){
   
   int msg_count=0; // 쪽지 DB 처리 결과 변수
   String result=""; // 쪽지 전송 결과 변수
   
   JSONObject msg_obj=new JSONObject();
   
   /*System.out.println("memberno_send:"+memberno_send);
   System.out.println("memberno_recv:"+memberno_recv);
   System.out.println("msg_title:"+msg_title);
   System.out.println("msg:"+msg_content);*/
   
   // 전달한 memberno_send, memberno_recv가 유효한지 검사.
   int confirm_send=messageProc.member_count(memberno_send);  // 발송자 회원 번호 유효 검사
   int confirm_recv=messageProc.member_count(memberno_recv);  // 수신자 회원 번호 유효 검사
   
   if(confirm_send==1 && confirm_recv==1){
     // 전달받은 parameter로 쪽지를 DB에 저장. 
     msg_count=messageProc.msg_create(memberno_send, memberno_recv, msg_title, msg_content);
     
     int msg_no=messageProc.serach_last_msg_no();  // 방금 저장된 쪽지 번호 
     
     // 저장이 성공적으로 되었으면
     if(msg_count==1){ // 쪽지 저장 성공
       messageProc.msgsend_insert(memberno_send, msg_no);
       messageProc.msgrecv_insert(memberno_recv, msg_no);
       result="OK";
     }else{            // 쪽지 저장 실패
       result="FAIL";
     }
   
   }else if(confirm_send!=1){
     result="no_sender";
   }else if(confirm_recv!=1){ 
     result="no_receiver";
   }
   
   msg_obj.put("result", result);
   
   return msg_obj.toJSONString();
 }
}
