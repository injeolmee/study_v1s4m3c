package dev.mvc.shared;

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

import dev.mvc.sharedreply.SharedreplyProcInter;
import dev.mvc.sharedreply.SharedreplyVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class SharedCont {
  
  @Autowired
  @Qualifier("dev.mvc.shared.SharedProc")
  private SharedProcInter sharedProc;
  
  @Autowired
  @Qualifier("dev.mvc.sharedreply.SharedreplyProc")
  private SharedreplyProcInter sharedreplyProc;
  
  public SharedCont() {
    // System.out.println(" => SharedCont created");
  }
  
  /**
   * <XMP>
   * 등록 폼 출력
   * </XMP>
   * @return 
   */
  @RequestMapping(value="/user/shared/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/create");
    
    return mav;
  }
  
  /**
   * <XMP>
   * 등록 결과 출력
   * </XMP>
   * @param request
   * @param sharedVO
   * @return 
   */
  @ResponseBody
  @RequestMapping(value="/user/shared/create.do", method=RequestMethod.POST, produces = "application/text; charset=utf8")
  public String create(HttpSession session, HttpServletRequest request, SharedVO sharedVO) {
    
    JSONObject obj = new JSONObject();
    
    String root = request.getContextPath(); // 절대경로 산출
    
    //*********************************************************************************
    // 파일전송 코드 시작
    //*********************************************************************************
    String upDir = Tool.getRealPath(request, "/user/shared/storage"); // 파일 upload 저장 위치
    MultipartFile file1MF = sharedVO.getFile1MF(); // 파일 실제 사이즈
    String sharedfile="";
    long sharedsize = 0;
    String sharedfstor = ""; //  실제 업로드 파일명을 저장할 변수
    String sharedtum = ""; // 썸네일 출력
    
    if (file1MF != null) { // 파일을 업로드 할 경우
      sharedsize=  file1MF.getSize(); // 파일 사이즈
    }
    
    if (sharedsize > 0) { // 파일사이즈가 0 이상일 경우 (업로드할 경우)
      sharedfstor = Upload.saveFileSpring(file1MF, upDir);
      sharedfile = file1MF.getOriginalFilename(); // 원본 파일명
     
      if (Tool.isImage(sharedfstor)) {
        sharedtum = Tool.preview(upDir, sharedfstor, 120, 80); // Thumb 이미지 생성
      }
    }
    
    sharedVO.setSharedfile(sharedfile);
    sharedVO.setSharedfstor(sharedfstor);
    sharedVO.setSharedsize(sharedsize);
    sharedVO.setSharedtum(sharedtum);
    
    //*********************************************************************************
    // 파일전송 코드 종료
    //*********************************************************************************
    
    int count = 0; // 게시글 등록을 처리할 변수
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      count = sharedProc.create(sharedVO);
    } else if (session.getAttribute("adminno") != null) { // 관리자일 경우
      count = sharedProc.create_admin(sharedVO);
    }
   
    if (count == 1) { // 글 등록에 성공했을 경우
      // System.out.println("글 등록 성공");
    } else { // 글 등록에 실패했을 경우
      // System.out.println("글 등록 실패");
    }
    
    obj.put("count", count);
    
    return obj.toString();
  }

  /**
   * <XMP>
   * 검색 + 페이징 목록 출력
   * </XMP>
   * @param word 검색어
   * @param search 검색분류값
   * @param nowPage 현재페이지
   * @return
   */
  @RequestMapping(value = "/user/shared/list.do", method = RequestMethod.GET )
  public ModelAndView list( @RequestParam(value="word", defaultValue="") String word, // 검색어
                                    @RequestParam(value="search", defaultValue="") String search, // 검색분류값
                                    @RequestParam(value="nowPage", defaultValue = "1") int nowPage) { // 현재페이지
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/list");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);           // 검색어
    hashMap.put("search", search);        // 검색 Option Value
    hashMap.put("nowPage", nowPage); // 현재 페이지
    
    List <SharedVO> list = sharedProc.list_search(hashMap);
    mav.addObject("list", list); // 검색 포함한 리스트 출력
    
    int search_count = sharedProc.search_count(hashMap);
    mav.addObject("search_count", search_count); // 검색된 레코드 갯수
    
    String paging = sharedProc.paging(nowPage, search_count, word, search);
    mav.addObject("paging", paging); // 페이징 처리
    
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  /**
   * <XMP>
   * 게시글 조회 및 댓글 목록 출력
   * </XMP>
   * @param sharedno 게시글 번호
   * @param nowPage 현재 페이지
   * @return sharedVO, pre_num(이전글 번호), post_num(다음글 번호)
   */
  @RequestMapping(value = "/user/shared/read.do", method = RequestMethod.GET )
  public ModelAndView read( @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
                                      @RequestParam(value = "sharedno") int sharedno) {    
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/read");
    
    //***************************************************************** 
    // 게시글 조회 처리 시작(From "Shared" Package)
    //*****************************************************************     
    SharedVO sharedVO = sharedProc.read(sharedno);
    
    int pre_num = sharedProc.read_pre(sharedno);   // 이전글 번호
    int post_num = sharedProc.read_post(sharedno); // 다음글 번호
      
    sharedVO.setPre_num(pre_num); 
    sharedVO.setPost_num(post_num);
    mav.addObject("sharedVO", sharedVO);    
    //***************************************************************** 
    // 게시글 조회 처리 종료
    //***************************************************************** 
    
    //***************************************************************** 
    // 댓글 목록 처리 시작 (From "Sharedreply" Package)
    //***************************************************************** 
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("sharedno", sharedno);
    hashMap.put("nowPage", nowPage);
    
    List <SharedreplyVO> list_reply = sharedreplyProc.total_list_reply(hashMap);
    mav.addObject("list_reply", list_reply);
    
    int search_count = sharedreplyProc.search_count(sharedno);
    mav.addObject("search_count", search_count); // 검색된 레코드 갯수

    String paging = sharedreplyProc.paging(nowPage, search_count, sharedno);  // 페이징 처리
    mav.addObject("paging", paging);              

    mav.addObject("nowPage", nowPage);         // 현재 페이지 변수
    //***************************************************************** 
    // 댓글 목록 처리 종료
    //*****************************************************************
    
    sharedProc.increaseCnt(sharedno); // 조회시 조회수 상승 처리
    
    return mav;  
  }
   
  /**
   * <XMP>
   * 게시글 수정폼 출력
   * </XMP>
   * @param sharedVO
   * @return
   */
  @RequestMapping(value="/user/shared/update.do", method=RequestMethod.GET)
  public ModelAndView update(int sharedno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/update");
    
    SharedVO sharedVO = sharedProc.read(sharedno);
    
    //********************내용의 특수문자 처리를 위한 구문 **************************
    String sharedcontent = sharedVO.getSharedcontent(); // 기존 내용을 가져와서
    String new_sharedcontent = Tool.toJS(sharedcontent); // 자바스크립트가 인식할 수 있는 코드로 변환한다.

    sharedVO.setSharedcontent(new_sharedcontent);
    // System.out.println("원래 내용: " + sharedcontent);
    // System.out.println("변환한 후의 내용: " + new_sharedcontent);
    //**********************************************************************************
    
    mav.addObject("sharedVO", sharedVO);
    
    return mav;
  }
  
  /**
   * <XMP>
   * 게시글 수정결과 출력
   * </XMP>
   * @param request
   * @param sharedVO
   * @return 변경된 레코드 갯수
   */
  @ResponseBody
  @RequestMapping(value="/user/shared/update.do", method=RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update(HttpSession session, HttpServletRequest request, SharedVO sharedVO) {
    
    JSONObject obj = new JSONObject();
    String root = request.getContextPath(); // 절대경로 산출
    
    //*********************************************************************************
    // 파일전송 코드 시작
    //*********************************************************************************
    String upDir = Tool.getRealPath(request, "/user/shared/storage"); // Upload 경로 지정
    MultipartFile file1MF = sharedVO.getFile1MF(); // 실제 파일
    
    String sharedfile = ""; // 원본 파일명
    String sharedfstor = ""; //  실제 업로드 파일명을 저장할 변수
    long sharedsize = 0; // 파일사이즈
    String sharedtum = ""; // 썸네일

    if (file1MF != null) {// 파일까지 받아온다면의 경우
      sharedsize = file1MF.getSize();
    }
    SharedVO sharedVO_od= sharedVO;
    
    String sharedyoutube = sharedVO_od.getSharedyoutube(); // Null 값 처리 위한 변수 선언
    
    if (sharedsize > 0) { // 파일 사이즈가 0 이상인 경우 (=파일을 변경할경우)
    
      // 신규 파일 등록시 기존 파일 삭제
      Tool.deleteFile(upDir, sharedVO_od.getSharedfstor());
      Tool.deleteFile(upDir, sharedVO_od.getSharedfile());
      Tool.deleteFile(upDir, sharedVO_od.getSharedtum());
      
      sharedfstor = Upload.saveFileSpring(file1MF, upDir);
      sharedfile = file1MF.getOriginalFilename();

      if (Tool.isImage(sharedfstor)) { // 업로드 파일이 이미지일 경우
        sharedtum = Tool.preview(upDir, sharedfstor, 120, 80); // Thumb 이미지 생성
      }
      
    } else { // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      sharedfile = sharedVO_od.getSharedfile();
      sharedfstor = sharedVO_od.getSharedfstor();
      sharedsize = sharedVO_od.getSharedsize();
      sharedtum = sharedVO_od.getSharedtum();
    }
    
    //***** Null 값 (등록하지 않았을경우) 처리 *****
    sharedfile = Tool.checkNull(sharedfile);
    sharedfstor = Tool.checkNull(sharedfstor);
    sharedtum = Tool.checkNull(sharedtum);
    sharedyoutube = Tool.checkNull(sharedyoutube);
    //************************************************

    sharedVO.setSharedyoutube(sharedyoutube);
    sharedVO.setSharedfile(sharedfile);
    sharedVO.setSharedfstor(sharedfstor);
    sharedVO.setSharedsize(sharedsize);
    sharedVO.setSharedtum(sharedtum);
    
    //*********************************************************************************
    // 파일전송 코드 종료
    //*********************************************************************************
    
    int sharedno = sharedVO.getSharedno();
    
    int check = 0; // 아이디 검사를 처리할 변수 (회원일 경우)
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      check = sharedProc.member_check(sharedVO); // 아이디 검사
    }
    
    int count = 0;  // 아이디 검사가 성공할 경우 처리할 변수

    if (check == 1 || session.getAttribute("adminno") != null) { // 등록된 아이디와 일치하거나 관리자일 경우
      count = sharedProc.update(sharedVO);
    
      if(count == 1) { 
        // System.out.println("수정 처리 성공");
      } else { // 수정 처리가 실패했을 경우
        // System.out.println("수정 처리 실패");
      }
    } else { // 등록된 아이디와 일치하지 않는 경우
      // System.out.println("아이디 일치 X");
    }
      
    obj.put("count", count);
    obj.put("sharedno", sharedno);
     
    return obj.toString();
  }

  /**
   * <XMP>
   * 게시글 삭제결과 출력
   * </XMP>
   * @param sharedno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String delete(HttpSession session, HttpServletRequest request, int sharedno) {

    JSONObject obj = new JSONObject();
    String root = request.getContextPath(); // 절대경로 산출
    
    SharedVO sharedVO = sharedProc.read(sharedno);
    
    // *************************************************************************
    // 파일 삭제 코드 시작
    // *************************************************************************
    String upDir = Tool.getRealPath(request, "/user/shared/storage"); // 업로드 경로
    
    Tool.deleteFile(upDir, sharedVO.getSharedfstor()); // 실제 파일명 삭제
    Tool.deleteFile(upDir, sharedVO.getSharedfile()); // 업로드 파일명 삭제
    Tool.deleteFile(upDir, sharedVO.getSharedtum()); // 파일 썸네일 삭제
    // *************************************************************************
    // 파일 삭제 코드 종료
    // *************************************************************************
          
    int check = 0; // 아이디 검사를 처리할 변수 (회원일 경우)
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      check = sharedProc.member_check(sharedVO); // 아이디 검사
    }
    
    int count_check; // 아이디 검사를 성공할 경우 처리할 변수 ①
    int count = 0;  // 아이디 검사를 성공할 경우 처리할 변수 ②
    
    if (check == 1 || session.getAttribute("adminno") != null) { // 등록된 아이디와 일치하거나 관리자일 경우
      
      count_check = sharedreplyProc.delete_all(sharedno); // ⓐ 해당 게시글의 댓글 전체 삭제
      count = sharedProc.delete(sharedno);                   // ⓑ 게시글 삭제
      
      if (count == 1) { // 삭제 처리가 성공했을 경우
        // System.out.println("삭제 처리 성공");
      } else { // 삭제 처리가 실패했을 경우
        // System.out.println("삭제 처리 실패");
      }
      
    } else { // 등록된 아이디와 일치하지 않는 경우
      // System.out.println("아이디 일치 X");
    }
    
    obj.put("count", count);
    
    return obj.toString();
  }
  
  /**
   * <XMP>
   * Grid 목록 출력
   * </XMP>
   * @param word 검색어
   * @param search 검색분류값
   * @param nowPage 현재페이지
   * @return
   */
  @RequestMapping(value = "/user/shared/list_grid.do", method = RequestMethod.GET )
  public ModelAndView list_gird( @RequestParam(value="word", defaultValue="") String word, // 검색어
                                          @RequestParam(value="search", defaultValue="") String search, // 검색분류값
                                          @RequestParam(value="nowPage", defaultValue = "1") int nowPage) { // 현재페이지
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/list_grid");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);           // 검색어
    hashMap.put("search", search);        // 검색 Option Value
    hashMap.put("nowPage", nowPage); // 현재 페이지
    
    List <SharedVO> list_grid = sharedProc.list_grid(hashMap);
    mav.addObject("list_grid", list_grid); // 검색 포함한 리스트 출력
    
    int search_count = sharedProc.search_count(hashMap);
    mav.addObject("search_count", search_count); // 검색된 레코드 갯수
    
    String paging_grid = sharedProc.paging_grid(nowPage, search_count, word, search);
    mav.addObject("paging_grid", paging_grid); // 페이징 처리
    
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  /**
   * <XMP>
   * 댓글 등록
   * <insert id="create" parameterType="SharedreplyVO">
   * </XMP>
   * @param request
   * @param sharedreplyVO
   * @return
   */
  @RequestMapping(value = "/user/shared/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(HttpSession session, HttpServletRequest request, SharedreplyVO sharedreplyVO) {
    // System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int sharedno = sharedreplyVO.getSharedno();
    int count = 0; // 댓글 등록을 처리할 변수
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      count = sharedreplyProc.create(sharedreplyVO);
    } else if (session.getAttribute("adminno") != null) { // 관리자일 경우
      count = sharedreplyProc.create_admin(sharedreplyVO);
    }
    
    if (count == 1) {
      // System.out.println("댓글등록 성공");
      mav.setViewName("redirect:/user/shared/read.do?sharedno=" + sharedno);
    } else {
      // System.out.println("댓글등록 실패");
    }
    
    return mav;
  }

  /**
   * <XMP> 
   * 대댓글 등록 
   * </XMP>
   * @param request
   * @param salereplyVO
   * @return
   */
  @RequestMapping(value = "/user/shared/rereply.do", method = RequestMethod.POST)
  public ModelAndView rereply(HttpSession session, HttpServletRequest request, SharedreplyVO sharedreplyVO) {
    // System.out.println("--> rereply() POST called.");
    ModelAndView mav = new ModelAndView();

    // *************************************************************************
    // 대댓글(답변) 등록 처리 시작
    // *************************************************************************
 
    SharedreplyVO parentVO = sharedreplyProc.read(sharedreplyVO.getShreplyno()); // 대댓글에 대한 부모글

    sharedreplyProc.update_seqno(sharedreplyVO.getShreplyno());
    
    int seqno_ch = 1; // 자식값에 주는 seqno를 1로 처리하기 위한 변수

    sharedreplyVO.setSharedno(parentVO.getSharedno());                  // 게시글 번호
    
    //**************** session에 따른 받아오는 값 구분 시작 ***********************************
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      sharedreplyVO.setMemberno(sharedreplyVO.getMemberno());      // 회원번호 => 부모에서 받으면 X
    } else if (session.getAttribute("adminno") != null) { // 관리자일 경우
      sharedreplyVO.setAdminno(sharedreplyVO.getAdminno());          // 관리자번호 => 부모에서 받으면 X
    }
    //**************** session에 따른 받아오는 값 구분 종료 ***********************************
    
    sharedreplyVO.setShreplyname(sharedreplyVO.getShreplyname());  // 작성자 => 부모에서 받으면 X
    sharedreplyVO.setShreplygrpno(parentVO.getShreplygrpno());      // 그룹 번호
    sharedreplyVO.setShreplyansnum(parentVO.getShreplyansnum());  // 댓글 순서

    sharedreplyProc.updateAnsnum(sharedreplyVO); // 현재 등록된 답변 뒤로 +1 처리함.

    sharedreplyVO.setShreplyindent(parentVO.getShreplyindent() + 1);   // 답변 차수 증가 (답변글의 답변글에 대한 차수 증가)             
    sharedreplyVO.setShreplyansnum(parentVO.getShreplyansnum()+ 1); // 최신 글의 답변 순서 조절
    sharedreplyVO.setSeqno(seqno_ch);
    
    // *************************************************************************
    // 대댓글(답변) 등록 처리 종료
    // *************************************************************************

    int count = 0; // 댓글 등록을 처리하기 위한 변수
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      count = sharedreplyProc.reply(sharedreplyVO);
    } else if (session.getAttribute("adminno") != null) { // 관리자일 경우
      count = sharedreplyProc.reply_admin(sharedreplyVO);
    }
    
    if (count == 1) {
      // System.out.println("대댓글 등록 성공");     
      mav.setViewName("redirect:/user/shared/read.do?sharedno=" + sharedreplyVO.getSharedno());
    } else {
      // System.out.println("대댓글 등록 실패");
    }

    return mav;
  }
  
  /**
   * <XMP>
   * 수정을 위한 해당 댓글내용 조회
   * </XMP>
   * @param shreplyno 댓글 번호
   * @return shreplyno, shreplycontent
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/reply_update.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String reply_update(int shreplyno) {
    // System.out.println(" => Update() GET executed");
    
    JSONObject obj = new JSONObject(); 
    
    SharedreplyVO sharedreplyVO = sharedreplyProc.read(shreplyno);
    
    obj.put("shreplyno", shreplyno);
    obj.put("shreplycontent", sharedreplyVO.getShreplycontent());

    return obj.toString();
  }
  
  /**
   * <XMP>
   * 댓글 수정 결과 출력
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/reply_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_update(SharedreplyVO sharedreplyVO) {
    // System.out.println(" => Update() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int shreplyno = sharedreplyVO.getShreplyno(); // 댓글번호
    String shreplycontent = sharedreplyVO.getShreplycontent(); // 댓글 내용
    int nowPage = sharedreplyVO.getNowPage(); // 현재 페이지
    int sharedno = sharedreplyVO.getSharedno(); // 게시글번호
    
    int count = sharedreplyProc.update(sharedreplyVO);
    
    obj.put("count", count);
    obj.put("shreplycontent", shreplycontent);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * 댓글 삭제 결과 출력
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/reply_delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_delete(SharedreplyVO sharedreplyVO) {
    // System.out.println(" => delete() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int shreplyno = sharedreplyVO.getShreplyno();
    int nowPage = sharedreplyVO.getNowPage();
    
    int count = sharedreplyProc.delete(shreplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * 부모 댓글의 delete에 대하여 update 처리
   * (= "삭제된 내용입니다."를 출력하기 위한 내용 변경)
   * </XMP>
   * @param salereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/update_parent.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update_parent(SharedreplyVO sharedreplyVO) {
    // System.out.println(" => Update_parent() POST executed");
    
    JSONObject obj = new JSONObject(); 
    
    int parent_check = 0; // ⓐ 부모 댓글에 대한 검사를 처리하기 위한 변수
    int reply_check = 0;   // ⓑ 맨 마지막 댓글 검사를 처리하기 위한 변수
    int count = 0;          // ⓒ 검사 후 댓글에 대해 처리하기 위한 변수
      
    //*****************************************************************************************************
    // 댓글과 관련된 내용을 조회하는 부분 시작
    //*****************************************************************************************************
    int shreplyno = sharedreplyVO.getShreplyno();                             // 댓글 번호
    sharedreplyVO = sharedreplyProc.read(shreplyno);                         // 댓글 조회
    int shreplygrpno = sharedreplyVO.getShreplygrpno();                    // 댓글그룹 번호
    int nowPage = sharedreplyVO.getNowPage();                              // 현재페이지
    int shreplyindent = sharedreplyVO.getShreplyindent();                   // 대댓글 차수
    //*****************************************************************************************************
    // 댓글과 관련된 내용을 조회하는 부분 종료
    //*****************************************************************************************************
    
    //*****************************************************************************************************
    // 맨 마지막 댓글인지 검사하기 위한 부분 시작
    //*****************************************************************************************************
    HashMap hashMap = new HashMap();
    hashMap.put("sharedno", sharedreplyVO.getSharedno()); 
    hashMap.put("shreplygrpno", shreplygrpno);
    
    reply_check = sharedreplyProc.reply_check(hashMap); // 댓글의 차수 최대값 검사
    int max_value =  reply_check;                              // 대댓글 차수 최대값
    //*****************************************************************************************************
    // 맨 마지막 댓글인지 검사하기 위한 부분 종료
    //*****************************************************************************************************
    
    parent_check = sharedreplyProc.parent_check(shreplygrpno); // 부모 댓글에 대하여 하위 댓글이 존재하는지 검사
    
    if (parent_check == 1 || max_value == shreplyindent) { // ⓐ 하위 댓글이 존재하지 않는 경우 또는 대댓글 차수가 최대값일 경우
      count = sharedreplyProc.delete(shreplyno);
    } else {                     // ⓑ 하위 댓글이 존재할 경우
      count = sharedreplyProc.update_parent(shreplyno);
    }

    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  
  

}
