package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import dev.mvc.room.RoomProcInter;
import dev.mvc.room.RoomVO;
import dev.mvc.rvlike.RvlikeProcInter;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class ReviewCont {
  @Autowired
  @Qualifier("dev.mvc.room.RoomProc")
  private RoomProcInter roomProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc = null;
  
  
  @Autowired
  @Qualifier("dev.mvc.rvlike.RvlikeProc")
  private RvlikeProcInter rvlikeProc = null;
  
  public ReviewCont() {
    System.out.println("--> ReviewCont created.");
  }
  
  
  //http://localhost:9090/study/review/create.jsp →
  // http://localhost:9090/study/review/create.do
  @RequestMapping(value= "/user/review/create.do", method= RequestMethod.GET)
  public ModelAndView create(@RequestParam(value="rono", defaultValue="") int rono) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/review/create"); // webapp/review/create.jsp
    
    RoomVO roomVO=roomProc.read(rono);
    mav.addObject("roomVO", roomVO);
   
    return mav;
  }
  
  
  @ResponseBody
  @RequestMapping(value= "/user/review/create.do", method= RequestMethod.POST, produces = "application/text; charset=utf8")
  public String create(HttpServletRequest request, ReviewVO reviewVO) {
   
    // System.out.println("review create()");
    JSONObject obj = new JSONObject();
    
    int rono = reviewVO.getRono();
    String rvcont = reviewVO.getRvcont();
    System.out.println("rvcont : " + rvcont);
    
    //***************************************************************** 
    // 파일 전송 코드 시작
    //***************************************************************** 
    String upDir = Tool.getRealPath(request, "/user/review/storage"); // 파일 upload 저장 위치
    
    MultipartFile file1MF = reviewVO.getFile1MF(); // 업로드 될 파일
    
    // System.out.println("file 유무: " + file1MF);
    long rvsize1 = 0;
    String rvfile1="";
    
    String rvthumb = ""; // 썸네일 출력
    
    if (file1MF != null) { // 파일까지 받아온다면의 경우
      rvsize1 = file1MF.getSize();
    }
    
    System.out.println("rvsize1 : " + rvsize1);
    
    if (rvsize1 > 0) { // 파일사이즈가 0 이상일 경우 (업로드할 경우)
      rvfile1 = Upload.saveFileSpring(file1MF, upDir);
      //System.out.println("rvfile1 ---> " + rvfile1);
      //System.out.println("rvsize1 ---> " + rvsize1);
      //System.out.println("rvthumb ---> " + rvthumb);
      //System.out.println("upDir ---> " + upDir);
      if (Tool.isImage(rvfile1)) { // 이미지 일 경우
        rvthumb = Tool.preview(upDir, rvfile1, 200, 250); // Thumb 이미지 생성
      }
    }
    
    reviewVO.setRvfile1(rvfile1);
    reviewVO.setRvsize1(rvsize1);
    reviewVO.setRvthumb(rvthumb);
    
    int count = 0;
    count = reviewProc.create(reviewVO);
    
    if ( count == 1) {
        
      HashMap hashmap = new HashMap();
      int memberno = reviewVO.getMemberno();
      int rvno = reviewProc.rvno();
      hashmap.put("memberno", memberno);
      hashmap.put("rvno", rvno);
      
      int count1 = rvlikeProc.like_chk(hashmap);
        
        if(count1 == 0){
          rvlikeProc.create(hashmap);
        }
      

      System.out.println("등록햇어");
    } else {
      System.out.println("등록 못햇어");
    }
    
    obj.put("rono", rono);
    obj.put("count", count);
    
    return obj.toJSONString();
    
  }
  

  /*@RequestMapping(value= "/review/create.do", method= RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ReviewVO reviewVO) { 
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/message"); // webapp/review/message.jsp
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
 // ---------------------------------------------------------------
    // 파일 전송 코드 시작
    // ---------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    MultipartFile file1MF = reviewVO.getFile1MF();
    String rvfile1 = ""; // 컬럼에 저장할 파일명
    Long rvsize1 = file1MF.getSize();
    String rvthumb = "";
    
    if(rvsize1 > 0) {
      rvfile1 = Upload.saveFileSpring(file1MF, upDir);
      
      if(Tool.isImage(rvfile1)) {
        rvthumb = Tool.preview(upDir, rvfile1, 120, 80);
      }
    }
    reviewVO.setRvfile1(rvfile1);
    reviewVO.setRvsize1(rvsize1);
    reviewVO.setRvthumb(rvthumb);
    // ---------------------------------------------------------------
    // 파일 전송 종료
    // ---------------------------------------------------------------
    
    if (reviewProc.create(reviewVO) == 1) {
      msgs.add("리뷰 등록 성공");
    } else {
      msgs.add("리뷰 등록 실패");
      msgs.add("다시 시도 -> 운영팀");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }
    
    links.add("<button type= 'button' onclick=\"location.href='./list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
      
    return mav;
  }*/
  
  
  @RequestMapping(value= "/review/list.do", method= RequestMethod.GET)
  public ModelAndView list(ReviewVO reviewVO) { 
    System.out.println("--> list() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/list"); // webapp/review/list.jsp
    
    /*if (session.getAttribute("memberno") != null) {
      HashMap hashMap = new HashMap();
      
      // 세션을 통해 memberno 값 가져옴
      int memberno = (Integer)session.getAttribute("memberno");
      // int memberno = reviewVO.getMemberno();
      
      hashMap.put("memberno", memberno);
      hashMap.put("rvno", reviewVO.getRvno());
      
      // rvlike 테이블에 이미 입력된 회원인지 확인
      if (rvlikeProc.good_chk(hashMap) == 0) {
        rvlikeProc.create(hashMap); 
      }
    }*/
    
    // 숫자와 문자열 타입을 저장해야함으로 Object 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("rono", reviewVO.getRono()); 
    hashMap.put("nowPage", reviewVO.getNowPage());
     
    List<ReviewVO> list = reviewProc.list(hashMap);
    mav.addObject("list", list);
    
    int search_count = reviewProc.search_count(reviewVO.getRono());
    mav.addObject("search_count", search_count);
   
    
    String paging = reviewProc.paging(reviewVO.getRono(), search_count, reviewVO.getNowPage());
    mav.addObject("paging", paging);
    mav.addObject("nowPage", reviewVO.getNowPage()); 
    
    return mav;
  } 
  

  @RequestMapping(value = "/user/review/update.do", method = RequestMethod.GET)
  public ModelAndView update(int rono, int rvno) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/review/update"); // webapp/room/update.jsp

    RoomVO roomVO = roomProc.read(rono);
    mav.addObject("roomVO", roomVO);
    
    ReviewVO reviewVO = reviewProc.read(rvno);
    mav.addObject("reviewVO", reviewVO);

    //System.out.println("reviewVO의 memberno:" + reviewVO.getMemberno());
    return mav;
  }

  
  
  @ResponseBody
  @RequestMapping(value= "/user/review/update.do", method= RequestMethod.POST,  produces = "application/text; charset=utf8")
  public String update(HttpServletRequest request, ReviewVO reviewVO) {
    
    System.out.println(" Update Post called");
    
    JSONObject obj = new JSONObject();
    ReviewVO reviewVO_old = reviewVO; 
    
    System.out.println("cont의 memberno: " + reviewVO_old.getMemberno());
    
    //***************************************************************** 
    // 파일 전송 코드 시작
    //***************************************************************** 
    String upDir = Tool.getRealPath(request, "/user/review/storage"); // 파일 upload 저장 위치
    MultipartFile file1MF = reviewVO.getFile1MF(); // 업로드 될 파일
    
    System.out.println("file1MF ---> " + file1MF);
    
    long rvsize1 = 0;
    String rvfile1 = "";
    String rvthumb = ""; // 썸네일 출력

    if (file1MF != null) { // 파일까지 받아온다면의 경우
      rvsize1 = file1MF.getSize();
    }
    
    System.out.println("rvsize1 : " + rvsize1);
    
    if (rvsize1 > 0) { // 파일사이즈가 0 이상일 경우 (업로드할 경우)
      Tool.deleteFile(upDir, reviewVO_old.getRvfile1()); // 기존 파일 삭제
      Tool.deleteFile(upDir, reviewVO_old.getRvthumb());
      
      rvfile1 = Upload.saveFileSpring(file1MF, upDir); // 신규 파일 업로드
      /*System.out.println("rvfile1 ---> " + rvfile1);
      System.out.println("rvsize1 ---> " + rvsize1);
      System.out.println("rvthumb ---> " + rvthumb);
      System.out.println("upDir ---> " + upDir);*/
      System.out.println("file1MF ---> " + file1MF);
      if (Tool.isImage(rvfile1)) { // 이미지 일 경우
        rvthumb = Tool.preview(upDir, rvfile1, 200, 250); // Thumb 이미지 생성
      }
    }  else {
      // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      rvfile1 = reviewVO_old.getRvfile1();
      rvsize1 = reviewVO_old.getRvsize1();
      rvthumb = reviewVO_old.getRvthumb();
    }
    
    reviewVO.setRvfile1(rvfile1);
    reviewVO.setRvsize1(rvsize1);
    reviewVO.setRvthumb(rvthumb);
    
    System.out.println("rvno --->"+reviewVO.getRvno());
    System.out.println("rvfile1 ---> " + rvfile1);
    System.out.println("rvsize1 ---> " + rvsize1);
    System.out.println("rvthumb ---> " + rvthumb);  
    
    System.out.println("reviewVO의 memno: " + reviewVO.getMemberno());
    
    int count = 0;
    count = reviewProc.update(reviewVO);
    
    System.out.println("count: " + count);
    if (count == 1) {
      System.out.println("수정햇어"); 
      System.out.println("==========================================");
    } else {
      System.out.println("수정 못햇어");
      System.out.println("==========================================");
    }
    
    obj.put("count", count);
    
    return obj.toJSONString();
    
    
  }
  
  
  /*@RequestMapping(value = "/review/update.do", method = RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, ReviewVO reviewVO) {
    System.out.println("--> update() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/message"); // webapp/blog/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();


    // ---------------------------------------------------------------
    // 파일 전송 코드 시작
    // ---------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    MultipartFile file1MF = reviewVO.getFile1MF();
    String rvfile1 = ""; // 컬럼에 저장할 파일명
    Long rvsize1 = file1MF.getSize();
    String rvthumb = "";

    ReviewVO reviewVO_old = reviewProc.read(reviewVO.getRvno());

    if (rvsize1 > 0) {
      Tool.deleteFile(upDir, reviewVO_old.getRvfile1()); // 기존 파일 삭제
      Tool.deleteFile(upDir, reviewVO_old.getRvthumb()); // 기존 Thumb 파일 삭제

      rvfile1 = Upload.saveFileSpring(file1MF, upDir); // 신규 파일 업로드

      if (Tool.isImage(rvfile1)) { // 새로운 파일 이미지인지 검사
        rvthumb = Tool.preview(upDir, rvfile1, 320, 240); // Thumb 이미지 생성
      }
    } else {
      // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      rvfile1 = reviewVO_old.getRvfile1();
      rvsize1 = reviewVO_old.getRvsize1();
      rvthumb = reviewVO_old.getRvthumb();
    }

    reviewVO.setRvfile1(rvfile1);
    reviewVO.setRvsize1(rvsize1);
    reviewVO.setRvthumb(rvthumb);
    // ---------------------------------------------------------------
    // 파일 전송 종료
    // ---------------------------------------------------------------

    
     * //회원 개발 후 session으로 변경 roomVO.setMno(1);
     

    String root = request.getContextPath();

    if (reviewProc.update(reviewVO) == 1) {
      msgs.add("수정 성공");
      msgs.add("글을 수정했습니다.");
    } else {
      msgs.add("수정 실패");
      msgs.add("글 수정에 실패했습니다.");
      msgs.add("다시 시도 -> 운영팀");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='../room/read.do?rono="+reviewVO.getRono()+ "&rvno=" +reviewVO.getRvno() + "'\">목록</button>");
    links.add("<button type='button' onclick=\"location.href='" + root + "/home.do'\">홈페이지</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }*/
  
  
  @RequestMapping(value = "/user/review/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int rvno) {
    System.out.println("--> review delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("review/delete"); // webapp/room/delete.jsp
    
    mav.addObject("rvno", rvno);
    /*ReviewVO reviewVO = reviewProc.read(rvno);
    mav.addObject("reviewVO", reviewVO);*/

    return mav;
  }
  
  /**
   * <XMP> 
   * 리뷰글 삭제결과 출력
   *  </XMP>
   * @param rvno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/review/delete.do", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String delete(HttpServletRequest request, int rvno) {
    
    JSONObject obj = new JSONObject();
    
    ReviewVO reviewVO = reviewProc.read(rvno);

    String root = request.getContextPath(); // 절대경로 산출

    // *************************************************************************
    // 파일 삭제 코드 시작
    // *************************************************************************

    String upDir = Tool.getRealPath(request, "/user/review/storage"); // 업로드 경로

    Tool.deleteFile(upDir, reviewVO.getRvfile1()); // 실제 파일명 삭제
    Tool.deleteFile(upDir, reviewVO.getRvthumb()); // 업로드 파일명 삭제

    // *************************************************************************
    // 파일 삭제 코드 종료
    // *************************************************************************

    
   /* int check = saleProc.member_check(saleVO);
    int count_check;
    int count = 0;
    
    if (check == 1) { // 등록된 아이디와 일치하는 경우
      count_check = salereplyProc.delete_all(saleno);
      count = saleProc.delete(saleno);

      if (count == 1) { // 삭제 처리가 성공했을 경우
        // System.out.println("글 삭제 성공");
      } else { // 삭제 처리가 실패했을 경우
        // System.out.println("글 삭제 실패");
      }
      
    } else { // 등록된 아이디와 일치하지 않는 경우
      // System.out.println("아이디 일치 X");
    }
    */
    int count = reviewProc.delete(rvno);
    if (count == 1) { // 삭제 처리가 성공했을 경우
      // System.out.println("글 삭제 성공");
    } else { // 삭제 처리가 실패했을 경우
      // System.out.println("글 삭제 실패");
    }
    
    
    
    obj.put("count", count);
    
    return obj.toString();
    
  }
  
  
  
  /*@RequestMapping(value = "/review/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int rvno) {
    System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("review/delete"); // webapp/room/delete.jsp


    
    ReviewVO reviewVO = reviewProc.read(rvno);
    mav.addObject("reviewVO", reviewVO);

    return mav;
  }

  @RequestMapping(value = "/reveiw/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, ReviewVO reviewVO) {
    System.out.println("--> delete() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/message"); // webapp/room/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    ReviewVO reviewVO_old = reviewProc.read(reviewVO.getRvno());

    // ---------------------------------------------------------------
    // 파일삭제
    // ---------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");

    Tool.deleteFile(upDir, reviewVO_old.getRvfile1()); // 기존 파일 삭제
    Tool.deleteFile(upDir, reviewVO_old.getRvthumb()); // 기존 Thumb 파일 삭제

    // ---------------------------------------------------------------

    
     * //회원 개발 후 session으로 변경 roomVO.setMno(1);
     

    String root = request.getContextPath();

    if (reviewProc.delete(reviewVO.getRvno()) == 1) {

      msgs.add("삭제 성공");
      msgs.add("글을 삭제했습니다.");
    } else {
      msgs.add("삭제 실패");
      msgs.add("글 삭제에 실패했습니다.");
      msgs.add("다시 시도 -> 운영팀");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='./list.do?nowPage=" + reviewVO.getNowPage() + "&roname="
        + roomVO.getRoname() + "'\">목록</button>");
    links.add("<button type='button' onclick=\"location.href='" + root + "/home.do'\">홈페이지</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }*/

}
