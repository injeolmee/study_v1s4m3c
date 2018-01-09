package dev.mvc.free;

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
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.freelike.FreelikeProcInter;
import dev.mvc.freereply.FreereplyProcInter;
import dev.mvc.freereply.FreereplyVO;
import nation.web.tool.Tool;

@Controller
public class FreeCont {
  
  @Autowired
  @Qualifier("dev.mvc.free.FreeProc")
  private FreeProcInter freeProc;
  
  @Autowired
  @Qualifier("dev.mvc.freereply.FreereplyProc")
  private FreereplyProcInter freereplyProc;
  
  @Autowired
  @Qualifier("dev.mvc.freelike.FreelikeProc")
  private FreelikeProcInter freelikeProc;
  
  public FreeCont() {
    // System.out.println(" => FreeCont create");
  }
  
  /**
   * <XMP>
   * 등록 폼 출력
   * </XMP>
   * @return 
   */
  @RequestMapping(value="/user/free/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
   // System.out.println("--> create() GET executed");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/user/free/create");
   
   return mav;
  }
    
  /**
   * <XMP>
   * 등록 결과 출력
   * </XMP>
   * @param request
   * @param freeVO
   * @return count
   */
  @ResponseBody
  @RequestMapping(value="/user/free/create.do", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String create(HttpSession session, HttpServletRequest request, FreeVO freeVO) {

    JSONObject obj = new JSONObject();

    int count = 0; // 게시글 등록을 처리할 변수
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      count = freeProc.create(freeVO);
    } else if (session.getAttribute("adminno") != null) { // 관리자일 경우
      count = freeProc.create_admin(freeVO);
    }
    obj.put("count", count);
 
    return obj.toString();
  }
  
  /**
   * <XMP>
   * 게시판 목록 + 검색 +페이징
   * </XMP>
   * @param word 검색어
   * @param search 검색 Value Option
   * @param nowPage 현재 페이지
   * @return
   */
  @RequestMapping(value="/nonuser/free/list.do", method=RequestMethod.GET)
  public ModelAndView list( @RequestParam(value="word", defaultValue="") String word, // 검색어
                                    @RequestParam(value="search", defaultValue="") String search, // 검색분류값
                                    @RequestParam(value="nowPage", defaultValue = "1") int nowPage) { // 현재페이지
    
    // System.out.println("--> list_search() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/free/list");

    //*******************************************************************************
    // 검색과 관련된 기본 옵션 처리 시작                                               
    //*******************************************************************************
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);           // 검색어
    hashMap.put("search", search);        // 검색 Option Value
    hashMap.put("nowPage", nowPage); // 현재 페이지
    //*******************************************************************************
    // 검색과 관련된 기본 옵션 처리 종료                                               
    //*******************************************************************************
      
    //*******************************************************************************
    // ① 검색 + 페이징 기반의 목록 출력 시작                                   
    //*******************************************************************************
    List<FreeVO> list = freeProc.list_search(hashMap); // 게시판 기본 목록 출력 (검색+페이징)
    mav.addObject("list", list);
    //*******************************************************************************
    // 검색 + 페이징 기반의 목록 출력 종료                                        
    //*******************************************************************************
    
    //*******************************************************************************
    // ② 추천수 상위 3개 목록 출력 시작                             
    //*******************************************************************************
    HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
    List<FreeVO> list_like = freeProc.list_like(hashMap2); // 게시판 추천수(Like) 최상위 목록 출력
    mav.addObject("list_like", list_like);
    //*******************************************************************************
    // 추천수 상위 3개 목록 출력 종료                        
    //*******************************************************************************
    
    //*******************************************************************************
    // ③ 조회수 상위 3개 목록 출력 시작                             
    //*******************************************************************************
    HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
    List<FreeVO> list_cnt = freeProc.list_cnt(hashMap3); // 게시판 조회수(Cnt) 최상위 목록 출력
    mav.addObject("list_cnt", list_cnt); 
    //*******************************************************************************
    // 조회수 상위 3개 목록 출력 종료                         
    //*******************************************************************************
    
    int search_count = freeProc.search_count(hashMap); // 검색된 레코드 갯수
    mav.addObject("search_count", search_count); 
   
    String paging = freeProc.paging(nowPage, search_count, word, search); // 페이징 출력
    mav.addObject("paging", paging);
    
    mav.addObject("nowPage", nowPage); // 현재페이지
       
    return mav;
  }
  
  /**
   * <XMP>
   * 게시글 조회 + 댓글 목록
   * </XMP>
   * @param nowPage 현재 페이지
   * @param freeno 게시글 번호
   * @return freeVO, pre_num(이전글 번호), post_num(다음글 번호)
   */
  @RequestMapping(value = "/nonuser/free/read.do", method = RequestMethod.GET)
  public ModelAndView read( HttpSession session,
                                      @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
                                      @RequestParam(value = "freeno") int freeno) {
    
    // System.out.println("--> read() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/free/read");

    //***************************************************************** 
    // 게시글 조회 처리 시작(From "Free" Package)
    //*****************************************************************  
    FreeVO freeVO = freeProc.read(freeno);
    
    int pre_num = freeProc.read_pre(freeno); // 이전글 번호
    int post_num = freeProc.read_post(freeno); // 다음글 번호
    
    freeVO.setPre_num(pre_num); 
    freeVO.setPost_num(post_num); 
    mav.addObject("freeVO", freeVO);
    //***************************************************************** 
    // 게시글 조회 처리 종료
    //***************************************************************** 
    
    //***************************************************************** 
    // 댓글 목록 처리 시작 (From "Freereply" Package)
    //*****************************************************************    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("freeno", freeno);
    hashMap.put("nowPage", nowPage);
    
    List <FreereplyVO> list_reply = freereplyProc.total_list_reply(hashMap);
    mav.addObject("list_reply", list_reply);
    
    int search_count = freereplyProc.search_count(freeno);
    mav.addObject("search_count", search_count); // 검색된 레코드 갯수

    String paging = freereplyProc.paging(nowPage, search_count, freeno);  // 페이징 처리
    mav.addObject("paging", paging);              

    freeVO.setNowPage(nowPage);
    mav.addObject("nowPage", nowPage);         // 현재 페이지 변수
    //***************************************************************** 
    // 댓글 목록 처리 종료
    //*****************************************************************
    
    //***************************************************************** 
    // 좋아요와 관련된 처리 시작 (= 입력된 회원과 관련되어서 Check)
    //*****************************************************************
    if (session.getAttribute("memberno") != null) {
      HashMap hashMap2 = new HashMap();
      
      int memberno = (Integer)session.getAttribute("memberno"); // 세션을 통해 회원번호를 가져옴!
      
      hashMap2.put("memberno", memberno);
      hashMap2.put("freeno", freeno);
      
      // Freelike 테이블에 이미 입력된 회원인지 Check
      if (freelikeProc.good_chk(hashMap2) == 0) { 
        freelikeProc.create(hashMap2);
      }
    }
    //***************************************************************** 
    // 좋아요와 관련된 처리 종료
    //*****************************************************************
    
    freeProc.increaseCnt(freeno); // 조회 시 조회수 상승 처리
    
    return mav;
  }
  
  /**
   * <XMP>
   * 수정 폼 출력
   * </XMP>
   * @param freeno
   * @return http://localhost:9090/user/study/free/update.do
   */
  @RequestMapping(value="/user/free/update.do", method=RequestMethod.GET)
  public ModelAndView update(int freeno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/free/update");
    
    FreeVO freeVO = freeProc.read(freeno);
    
    //**************************************************************************
    // 내용의 특수문자 처리를 위한 구문 시작 
    //**************************************************************************
    String freecontent = freeVO.getFreecontent(); // 기존 내용을 가져와서
    String new_freecontent = Tool.toJS(freecontent); // 자바스크립트가 인식할 수 있는 코드로 변환한다.
    
    freeVO.setFreecontent(new_freecontent);
    //**************************************************************************
    // 내용의 특수문자 처리를 위한 구문 종료
    //**************************************************************************

    mav.addObject("freeVO", freeVO);
    
    return mav;
  }
    
  /**
   * <XMP>
   * 수정 결과 처리
   * </XMP>
   * @param request
   * @param freeVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/user/free/update.do", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String update(HttpSession session, FreeVO freeVO) {
    
    JSONObject obj = new JSONObject(); 
 
    int freeno = freeVO.getFreeno();

    int check = 0; // 아이디 검사를 처리할 변수 (★ 회원만 해당)
    int count= 0; // 아디디 검사를 성공할 경우 처리할 변수
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      check = freeProc.member_check(freeVO); // 아이디 검사
    }
    
    if (check == 1 || session.getAttribute("adminno") != null) { // 등록된 멤버 아이디와 일치하거나 관리자일 경우
       count = freeProc.update(freeVO);
       
       if (count == 1) { // 수정 처리가 성공한 경우
         // System.out.println("수정 성공");
       } else { // 수정 처리가 실패한 경우
         // System.out.println("수정 실패");
       }
       
    } else { // 등록된 멤버 아이디와 일치하지 않는 경우
      // System.out.println("아이디 일치 X");
    }

    obj.put("count", count);
    obj.put("freeno", freeno);
    
    return obj.toString();
  }
  
  /**
   * <XMP>
   * 게시글 삭제결과 출력
   * </XMP>
   * @param request
   * @param freeVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/delete.do", method = RequestMethod.POST,  produces = "text/html; charset=UTF-8")
  public String delete(HttpSession session, HttpServletRequest request, int freeno) {

    JSONObject obj = new JSONObject(); 
    String root = request.getContextPath(); // 절대 경로 산출
    
    FreeVO freeVO = freeProc.read(freeno);
    
    int check = 0; // 아이디 검사 처리할 변수 (★ 회원만 해당)
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
     check = freeProc.member_check(freeVO); // 아이디 검사
    }
    
    int like_all = 0; // 아디디 검사 성공시 처리할 변수 ①
    int count_all = 0; // 아이디 검사 성공시 처리할 변수 ②
    int count = 0; // 아이디 검사 성공시 처리할 변수 ③
  
    if (check == 1 || session.getAttribute("adminno") != null) { // 등록된 멤버 아이디와 일치하거나 관리자일 경우
      
      like_all = freelikeProc.like_delete(freeno); // ⓐ 좋아요 삭제
      count_all = freereplyProc.delete_all(freeno); // ⓑ 댓글 전체 삭제
      count = freeProc.delete(freeno);               //  ⓒ 게시글 삭제
      
      if (count == 1) { // 삭제 처리가 성공한 경우
        // System.out.println("삭제 처리 성공");
      } else { // 삭제 처리가 실패한 경우
        // System.out.println("삭제 처리 실패");
      }
      
    } else { // 등록된 멤버 아이디와 일치하지 않는 경우
      // System.out.println("아이디 일치 X");
    }

    obj.put("count", count);

    return obj.toString();
  }
  
  /**
   * <XMP>
   * 댓글 등록
   * <insert id="create" parameterType="FreereplyVO">
   * </XMP>
   * @param request
   * @param freereplyVO
   * @return
   */
  @RequestMapping(value = "/user/free/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(HttpSession session, HttpServletRequest request, FreereplyVO freereplyVO, int nowPage) {
    // System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int freeno = freereplyVO.getFreeno();
    freereplyVO.setNowPage(nowPage);
    int count = 0; // 댓글 등록을 처리하기위한 변수
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      count = freereplyProc.create(freereplyVO);
    } else if (session.getAttribute("adminno") != null) { // 관리자일 경우
      count = freereplyProc.create_admin(freereplyVO);
    }
    
    if (count == 1) {
      // System.out.println("댓글등록 성공");
      mav.setViewName("redirect:/nonuser/free/read.do?freeno=" + freeno + "&nowPage=" + nowPage);
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
   * @param freereplyVO
   * @return
   */
  @RequestMapping(value = "/user/free/rereply.do", method = RequestMethod.POST)
  public ModelAndView rereply(HttpSession session, HttpServletRequest request, FreereplyVO freereplyVO, int nowPage) {
    // System.out.println("--> rereply() POST called.");
    ModelAndView mav = new ModelAndView();

    // *************************************************************************
    // 대댓글(답변) 등록 처리 시작
    // *************************************************************************
 
    FreereplyVO parentVO = freereplyProc.read(freereplyVO.getFreplyno()); // 대댓글에 대한 부모글
    
    freereplyProc.update_seqno(freereplyVO.getFreplyno()); // 부모글에 대한 seqno를 0으로 처리

    int seqno_ch = 1; // 자식값에 주는 seqno를 1로 처리하기 위한 변수

    freereplyVO.setFreeno(parentVO.getFreeno());                       // 게시글 번호
    
    //**************** session에 따른 받아오는 값 구분 시작 ***********************************
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      freereplyVO.setMemberno(freereplyVO.getMemberno());      // 회원번호 => 부모에서 받으면 X
    } else if (session.getAttribute("adminno") != null) { // 관리자일 경우
      freereplyVO.setAdminno(freereplyVO.getAdminno());          // 관리자번호 => 부모에서 받으면 X
    }
    //**************** session에 따른 받아오는 값 구분 종료 ***********************************

    freereplyVO.setFreplyname(freereplyVO.getFreplyname());          // 작성자   => 부모에서 받으면 X
    freereplyVO.setFreplygrpno(parentVO.getFreplygrpno());          // 그룹 번호
    freereplyVO.setFreplyansnum(parentVO.getFreplyansnum());       // 댓글 순서
    freereplyVO.setNowPage(nowPage);

    freereplyProc.updateAnsnum(freereplyVO); // 현재 등록된 답변 뒤로 +1 처리함.

    freereplyVO.setFreplyindent(parentVO.getFreplyindent() + 1);  // 답변 차수 증가 (답변글의 답변글에 대한 차수 증가)             
    freereplyVO.setFreplyansnum(parentVO.getFreplyansnum() + 1);// 최신 글의 답변 순서 조절
    freereplyVO.setSeqno(seqno_ch); // 새로 등록되는 댓글에 대하여 seqno의 값을 1로 처리
    
    // *************************************************************************
    // 대댓글(답변) 등록 처리 종료
    // *************************************************************************

    int count = 0; // 댓글 등록을 처리하기 위한 변수
    
    if (session.getAttribute("memberno") != null) { // 회원일 경우
      count = freereplyProc.reply(freereplyVO);
    } else if (session.getAttribute("adminno") != null) { // 관리자일 경우
      count = freereplyProc.reply_admin(freereplyVO);
    }

    if (count == 1) {
      // System.out.println("대댓글 등록 성공");     
      mav.setViewName("redirect:/nonuser/free/read.do?freeno=" + freereplyVO.getFreeno() + "&nowPage=" + freereplyVO.getNowPage());
    } else {
      // System.out.println("대댓글 등록 실패");
    }

    return mav;
  }
  
  /**
   * <XMP>
   * 수정을 위한 해당 댓글내용 조회
   * </XMP>
   * @param freplyno 댓글 번호
   * @return freplyno, freplycontent
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/reply_update.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String reply_update(int freplyno) {
    // System.out.println(" => Update() GET executed");
    
    JSONObject obj = new JSONObject(); 
    
    FreereplyVO freereplyVO = freereplyProc.read(freplyno);
    
    obj.put("freplyno", freplyno);
    obj.put("freplycontent", freereplyVO.getFreplycontent());

    return obj.toString();
  }
  
  /**
   * <XMP>
   * 댓글 수정 결과 출력
   * </XMP>
   * @param freereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/reply_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_update(FreereplyVO freereplyVO) {
    // System.out.println(" => Update() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int freplyno = freereplyVO.getFreplyno(); // 댓글번호
    String freplycontent = freereplyVO.getFreplycontent(); // 댓글 내용
    int nowPage = freereplyVO.getNowPage(); // 현재 페이지
    int freeno = freereplyVO.getFreeno(); // 게시글 번호
    
    int count = freereplyProc.update(freereplyVO);
    
    obj.put("count", count);
    obj.put("freplycontent", freplycontent);
    obj.put("nowPage", nowPage);

    return obj.toString();
  }
  
  /**
   * <XMP>
   * 댓글 삭제 결과 출력 (일반 삭제)
   * </XMP>
   * @param freereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/reply_delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_delete(FreereplyVO freereplyVO) {
    // System.out.println(" => delete() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int freplyno = freereplyVO.getFreplyno();
    int nowPage = freereplyVO.getNowPage();
    
    int count = freereplyProc.delete(freplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * 댓글 삭제 결과 출력
   * (+) 부모일 경우 delete 대신 content 내용만 update 수행
   * </XMP>
   * @param freereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/update_parent.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update_parent(FreereplyVO freereplyVO) {
    // System.out.println(" => Update_parent() POST executed");
    
    JSONObject obj = new JSONObject(); 
    
    int parent_check = 0; // ⓐ 부모 댓글에 대한 검사를 처리하기 위한 변수
    int reply_check = 0;   // ⓑ 맨 마지막 댓글 검사를 처리하기 위한 변수
    int count = 0;          // ⓒ 검사 후 댓글에 대해 처리하기 위한 변수
    
    //*****************************************************************************************************
    // 댓글과 관련된 내용을 조회하는 부분 시작
    //*****************************************************************************************************
    int freplyno = freereplyVO.getFreplyno();                                   // 댓글 번호
    freereplyVO = freereplyProc.read(freplyno);                                // 댓글 조회
    int freplygrpno = freereplyVO.getFreplygrpno();                          // 댓글 그룹번호
    int nowPage = freereplyVO.getNowPage();                                // 현재 페이지
    int freplyindent = freereplyVO.getFreplyindent();                         // 대댓글 차수
    //*****************************************************************************************************
    // 댓글과 관련된 내용을 조회하는 부분 종료
    //*****************************************************************************************************
   
    //*****************************************************************************************************
    // 맨 마지막 댓글인지 검사하기 위한 부분 시작
    //*****************************************************************************************************
    HashMap hashMap = new HashMap();
    hashMap.put("freeno", freereplyVO.getFreeno());
    hashMap.put("freplygrpno", freplygrpno);
    
    reply_check = freereplyProc.reply_check(hashMap); // 댓글의 차수 최대값 검사
    int max_value = reply_check;                           // 대댓글 차수 최대값
    //*****************************************************************************************************
    // 맨 마지막 댓글인지 검사하기 위한 부분 종료
    //*****************************************************************************************************
    
    parent_check = freereplyProc.parent_check(freplygrpno);  // 부모 댓글에 대하여 하위 댓글이 존재하는지 검사
    
    if (parent_check == 1 || max_value == freplyindent) { // ⓐ 하위 댓글이 존재하지 않는 경우 또는 대댓글 차수가 최대값일 경우
      count = freereplyProc.delete(freplyno);
    } else {             // ⓑ 하위 댓글이 존재하는 경우
      count = freereplyProc.update_parent(freplyno);
    }
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();  
  }
  
}