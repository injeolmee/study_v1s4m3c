package dev.mvc.sale;

import java.util.ArrayList;
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

import dev.mvc.freereply.FreereplyVO;
import dev.mvc.salereply.SalereplyProcInter;
import dev.mvc.salereply.SalereplyVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class SaleCont {

  @Autowired
  @Qualifier("dev.mvc.sale.SaleProc")
  private SaleProcInter saleProc;

  @Autowired
  @Qualifier("dev.mvc.salereply.SalereplyProc")
  private SalereplyProcInter salereplyProc;

  public SaleCont() {
    // System.out.println(" => SaleCont created ");
  }

  /**
   * 등록 폼 출력 
   * @return
   */
  @RequestMapping(value = "/user/sale/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    // System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/create");

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
  @RequestMapping(value = "/user/sale/create.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String create(HttpServletRequest request, SaleVO saleVO) {
    // System.out.println("--> create() POST executed");
    JSONObject obj = new JSONObject();
    
    String root = request.getContextPath(); // 절대경로 산출
    
    ///// NULL 값 처리 위한 변수 선언 ////////
    String saletel = saleVO.getSaletel(); 
    String saleaddress = saleVO.getSaleaddress();
    String saleemail = saleVO.getSaleemail();
    //////////////////////////////////////////////////
    
    //***************************************************************** 
    // 파일 전송 코드 시작
    //***************************************************************** 
    String upDir = Tool.getRealPath(request, "/user/sale/storage"); // 파일 upload 저장 위치
    MultipartFile file1MF = saleVO.getFile1MF(); // 업로드 될 파일
    long salesize = 0;
    String salefile="";
    String salefstor = ""; // 실제 업로드 파일명을 저장할 변수
    String saletum = ""; // 썸네일 출력
    
    if (file1MF != null) { // 파일까지 받아온다면의 경우
      salesize = file1MF.getSize();
    }
    
    if (salesize > 0) { // 파일사이즈가 0 이상일 경우 (= 업로드할 경우)
      salefstor = Upload.saveFileSpring(file1MF, upDir);
      salefile = file1MF.getOriginalFilename(); // 원본 파일명 (사용자에게 보일 파일명)

      if (Tool.isImage(salefstor)) { // 이미지 일 경우
        saletum = Tool.preview(upDir, salefstor, 200, 250); // Thumb 이미지 생성
      }
    }
    
    //**** Null 값 (=값 등록하지 않았을경우) 처리 ***
    salefile = Tool.checkNull(salefile);
    salefstor = Tool.checkNull(salefstor);
    saletum = Tool.checkNull(saletum);
    saletel = Tool.checkNull(saletel);
    saleaddress = Tool.checkNull(saleaddress);
    saleemail = Tool.checkNull(saleemail);
    //**************************************************
    
    saleVO.setSalefile(salefile);
    saleVO.setSalefstor(salefstor);
    saleVO.setSalesize(salesize);
    saleVO.setSaletum(saletum);

    //***************************************************************** 
    // 파일 전송 코드 종료
    //***************************************************************** 

    int count = saleProc.create(saleVO);
    
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
   * 검색+ 페이징 목록 출력 
   * </XMP>
   * @param word 검색어
   * @param search 검색 분류값
   * @param nowPage 현재 페이지
   * @return
   */
  @RequestMapping(value = "/user/sale/list.do", method = RequestMethod.GET)
  public ModelAndView list(@RequestParam(value = "word", defaultValue = "") String word,  // 검색어
                                   @RequestParam(value = "search", defaultValue = "") String search,  // 검색분류값
                                   @RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {  // 현재페이지

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/list");

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);           // 검색어
    hashMap.put("search", search);        // 검색 Option Value
    hashMap.put("nowPage", nowPage); // 현재 페이지

    List<SaleVO> list = saleProc.list_search(hashMap);
    mav.addObject("list", list);              // 검색 포함한 리스트 출력

    int search_count = saleProc.search_count(hashMap);
    mav.addObject("search_count", search_count); // 검색된 레코드 갯수

    String paging = saleProc.paging(nowPage, search_count, word, search);
    mav.addObject("paging", paging);  // 페이징 처리

    mav.addObject("nowPage", nowPage);

    return mav;
  }

  /**
   * <XMP> 
   * Grid형 목록 출력 
   * </XMP>
   * @param word 검색어
   * @param search 검색분류값
   * @param nowPage 현재페이지
   * @return
   */
  @RequestMapping(value = "/user/sale/list_grid.do", method = RequestMethod.GET)
  public ModelAndView list_grid(@RequestParam(value = "word", defaultValue = "") String word, // 검색어
                                         @RequestParam(value = "search", defaultValue = "") String search, // 검색분류값
                                         @RequestParam(value = "nowPage", defaultValue = "1") int nowPage) { // 현재페이지

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/list_grid");

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word); // 검색어
    hashMap.put("search", search); // 검색 Option Value
    hashMap.put("nowPage", nowPage); // 현재 페이지

    List<SaleVO> list_grid = saleProc.list_grid(hashMap);
    mav.addObject("list_grid", list_grid); // 검색 포함한 리스트 출력

    int search_count = saleProc.search_count(hashMap);
    mav.addObject("search_count", search_count); // 검색된 레코드 갯수

    String paging_grid = saleProc.paging_grid(nowPage, search_count, word, search);
    mav.addObject("paging_grid", paging_grid); // 페이징 처리

    mav.addObject("nowPage", nowPage);

    return mav;
  }

  /**
   * 게시글 조회 및 댓글 목록 출력
   * @param saleno 게시글 번호
   * @param nowPage 현재 페이지
   * @return saleVO, pre_num(이전글 번호), post_num(다음글 번호)
   */
  @RequestMapping(value = "/user/sale/read.do", method = RequestMethod.GET)
  public ModelAndView read( @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
                                      @RequestParam(value = "saleno") int saleno) {
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/read");

    //***************************************************************** 
    // 게시글 조회 처리 시작(From "Sale" Package)
    //***************************************************************** 
    SaleVO saleVO = saleProc.read(saleno);
    int pre_num = saleProc.read_pre(saleno); // 이전글 번호
    int post_num = saleProc.read_post(saleno); // 다음글 번호

    saleVO.setPre_num(pre_num);
    saleVO.setPost_num(post_num);
    mav.addObject("saleVO", saleVO);
    //***************************************************************** 
    // 게시글 조회 처리 종료
    //***************************************************************** 

    //***************************************************************** 
    // 댓글 목록 처리 시작 (From "Salereply" Package)
    //***************************************************************** 
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("saleno", saleno);
    hashMap.put("nowPage", nowPage);
    
    List<SalereplyVO> list_reply = salereplyProc.total_list_reply(hashMap);
    mav.addObject("list_reply", list_reply);
    
    int search_count = salereplyProc.search_count(saleno);
    mav.addObject("search_count", search_count); // 검색된 레코드 갯수

    String paging = salereplyProc.paging(nowPage, search_count, saleno);
    mav.addObject("paging", paging);               // 페이징 처리

    saleVO.setNowPage(nowPage);
    mav.addObject("nowPage", nowPage);        // 현재 페이지 변수
    //***************************************************************** 
    // 댓글 목록 처리 종료
    //*****************************************************************

    saleProc.increaseCnt(saleno); // 조회 시 조회수 상승 처리

    return mav;
  }

  /**
   * <XMP> 
   * 게시글 수정폼 출력
   *  <XMP>
   * @param saleVO
   * @return
   */
  @RequestMapping(value = "/user/sale/update.do", method = RequestMethod.GET)
  public ModelAndView update(int saleno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/update");

    SaleVO saleVO = saleProc.read(saleno);
   
    //**************************************************************************
    // 내용의 특수문자 처리를 위한 구문 시작 
    //**************************************************************************
    String salecontent = saleVO.getSalecontent(); // 기존 내용을 가져와서
    String new_salecontent = Tool.toJS(salecontent); // 자바스크립트가 인식할 수 있는 코드로 변환한다.
    
    saleVO.setSalecontent(new_salecontent);
    // System.out.println("원래 내용: " + salecontent);
    // System.out.println("변환한 후의 내용: " + new_salecontent);
    //**************************************************************************
    // 내용의 특수문자 처리를 위한 구문 종료
    //**************************************************************************

    mav.addObject("saleVO", saleVO);

    return mav;
  }

  /**
   * <XMP> 
   * 게시글 수정결과 출력 
   * </XMP>
   * @param request
   * @param saleVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update(HttpServletRequest request, SaleVO saleVO) {
    
    // System.out.println("=> update post called");
    // System.out.println("Cont에서 saleno: " + saleVO.getSaleno() + " 그리고 memberno: " + saleVO.getMemberno());
    // System.out.println("salefstor: " + saleVO.getSalefstor());

    JSONObject obj = new JSONObject();

    //***************************************************************** 
    // 파일 전송 코드 시작
    //*****************************************************************
    String upDir = Tool.getRealPath(request, "/user/sale/storage"); // 파일 upload 저장 위치
    MultipartFile file1MF = saleVO.getFile1MF(); // 업로드 될 파일
    String salefile = ""; // 원본 파일명
    String salefstor = ""; // 실제 업로드 파일명을 저장할 변수
    long salesize = 0; // 파일 사이즈
    String saletum = ""; // 썸네일 출력
    
    if (file1MF != null) { // 파일까지 받아온다면의 경우
      salesize = file1MF.getSize();
    }

    // System.out.println("salesize: " + salesize);
    
    SaleVO saleVO_od = saleVO; // 받아온 saleVO를 saleVO_od로 저장 (= 업로드할 경우 삭제하기 위해 변수명을 분리해놓음)
    
    ////////////// Null 값 처리를 위해 변수 선언 //////////////////
    String saletel = saleVO_od.getSaletel();
    String saleaddress = saleVO_od.getSaleaddress();
    String saleemail = saleVO_od.getSaleemail();
    ///////////////////////////////////////////////////////////////////////

    if (salesize > 0) { // ① 파일사이즈가 0 이상일 경우 (변경할 경우)

      //////////// 기존 파일 삭제 처리 //////////////////
      Tool.deleteFile(upDir, saleVO_od.getSalefstor());
      Tool.deleteFile(upDir, saleVO_od.getSalefile());
      Tool.deleteFile(upDir, saleVO_od.getSaletum());
      /////////////////////////////////////////////////////////

      salefstor = Upload.saveFileSpring(file1MF, upDir);
      salefile = file1MF.getOriginalFilename();

      if (Tool.isImage(salefstor)) { // 이미지 일 경우
        saletum = Tool.preview(upDir, salefstor, 200, 250); // Thumb 이미지 생성
      }

    } else { // ② 파일을 변경하지 않는 경우 기존 파일 정보 사용
      salefile = saleVO_od.getSalefile();
      salefstor = saleVO_od.getSalefstor();
      saletum = saleVO_od.getSaletum();
      salesize = saleVO_od.getSalesize();
    }

    //***** Null 값 (등록하지 않았을경우) 처리 *****
    salefile = Tool.checkNull(salefile);
    salefstor = Tool.checkNull(salefstor);
    saletum = Tool.checkNull(saletum);
    saletel = Tool.checkNull(saletel);
    saleaddress = Tool.checkNull(saleaddress);
    saleemail = Tool.checkNull(saleemail);
    //************************************************
    
    saleVO.setSalefile(salefile);
    saleVO.setSalefstor(salefstor);
    saleVO.setSalesize(salesize);
    saleVO.setSaletum(saletum);
    
    //***************************************************************** 
    // 파일 전송 코드 종료
    //*****************************************************************

    int check = saleProc.member_check(saleVO_od); // 아이디 검사
    int count=0; // 아이디 검사가 성공할 경우 처리할 변수
    
    if (check == 1) { // 등록된 아이디와 일치하는 경우
      count = saleProc.update(saleVO);
      
      if (count == 1) { // 수정 처리가 성공했을 경우
        // System.out.println("수정 처리 성공");
      } else { // 수정 처리가 실패했을 경우
        // System.out.println("수정 처리 실패");
      }
      
    } else { // 등록된 아이디와 일치하지 않는 경우
      // System.out.println("아이디 일치 X");
    }
    
    obj.put("count", count);

    return obj.toString();
  }

  /**
   * <XMP> 
   * 게시글 삭제폼 출력 (지금은 쓰지 않음)
   * </XMP>
   * @param saleno
   * @return
   */
/*  @RequestMapping(value = "/user/sale/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int saleno) {
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/delete");

    SaleVO saleVO = saleProc.read(saleno);
    mav.addObject("saleVO", saleVO);

    return mav;
  }*/

  /**
   * <XMP> 
   * 게시글 삭제결과 출력
   *  </XMP>
   * @param sharedno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/delete.do", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String delete(HttpServletRequest request, int saleno) {
    
    JSONObject obj = new JSONObject();
    String root = request.getContextPath(); // 절대경로 산출
    
    SaleVO saleVO = saleProc.read(saleno);

    // *************************************************************************
    // 파일 삭제 코드 시작
    // *************************************************************************
    String upDir = Tool.getRealPath(request, "/user/sale/storage"); // 업로드 경로

    Tool.deleteFile(upDir, saleVO.getSalefstor()); // 실제 파일명 삭제
    Tool.deleteFile(upDir, saleVO.getSalefile()); // 업로드 파일명 삭제
    Tool.deleteFile(upDir, saleVO.getSaletum()); // 파일 썸네일 삭제
    // *************************************************************************
    // 파일 삭제 코드 종료
    // *************************************************************************

    int check = saleProc.member_check(saleVO);
    int count_check;
    int count = 0;
    
    if (check == 1) { // 등록된 아이디와 일치하는 경우
      
      count_check = salereplyProc.delete_all(saleno); // 글에 해당하는 모든 댓글 삭제
      count = saleProc.delete(saleno);                   // 게시글 삭제

      if (count == 1) { // 삭제 처리가 성공했을 경우
        // System.out.println("글 삭제 성공");
      } else { // 삭제 처리가 실패했을 경우
        // System.out.println("글 삭제 실패");
      }
      
    } else { // 등록된 아이디와 일치하지 않는 경우
      // System.out.println("아이디 일치 X");
    }
    
    obj.put("count", count);
    
    return obj.toString();
    
  }

  /**
   * <XMP> 
   * 댓글 등록 
   * <insert id="create" parameterType="SalereplyVO" > 
   * </XMP>
   * @param request
   * @param salereplyVO
   * @return
   */
  @RequestMapping(value = "/user/sale/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(HttpServletRequest request, SalereplyVO salereplyVO) {
    
    ModelAndView mav = new ModelAndView();

    int saleno = salereplyVO.getSaleno();
    int count = salereplyProc.create(salereplyVO);

    if (count == 1) {
      // System.out.println("댓글등록 성공");
      mav.setViewName("redirect:/user/sale/read.do?saleno=" + saleno);
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
  @RequestMapping(value = "/user/sale/rereply.do", method = RequestMethod.POST)
  public ModelAndView rereply(HttpServletRequest request, SalereplyVO salereplyVO) {
    
    // System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();

    // *************************************************************************
    // 대댓글(답변) 등록 처리 시작
    // *************************************************************************
    
    SalereplyVO parentVO = salereplyProc.read(salereplyVO.getSreplyno()); // 대댓글에 대한 부모글

    salereplyProc.update_seqno(salereplyVO.getSreplyno()); // 부모글에 대한 seqno를 0으로 처리
    
    int seqno_ch = 1; // 자식값에 주는 seqno를 1로 처리하기 위한 변수
    
    salereplyVO.setSaleno(parentVO.getSaleno());                  // 게시글 번호
    salereplyVO.setMemberno(salereplyVO.getMemberno());    // 회원번호 => 부모에서 받으면 안된다! (주의)
    salereplyVO.setSreplyname(salereplyVO.getSreplyname());   // 작성자   => 부모에서 받으면 안된다! (주의)
    salereplyVO.setSreplygrpno(parentVO.getSreplygrpno());    // 그룹 번호
    salereplyVO.setSreplyansnum(parentVO.getSreplyansnum()); // 댓글 순서
    
    salereplyProc.updateAnsnum(salereplyVO); // 현재 등록된 답변 뒤로 +1 처리함.

    salereplyVO.setSreplyindent(parentVO.getSreplyindent() + 1);    // 답변 차수 증가 (답변글의 답변글에 대한 차수 증가)             
    salereplyVO.setSreplyansnum(parentVO.getSreplyansnum() + 1); // 최신 글의 답변 순서 조절
    salereplyVO.setSeqno(seqno_ch); // 새로 등록되는 댓글에 대하여 seqno의 값을 1로 처리
    
    // *************************************************************************
    // 대댓글(답변) 등록 처리 종료
    // *************************************************************************

    int count = salereplyProc.reply(salereplyVO);
    
    if (count == 1) {
      // System.out.println("대댓글 등록 성공");     
      mav.setViewName("redirect:/user/sale/read.do?saleno=" + salereplyVO.getSaleno());
    } else {
      // System.out.println("대댓글 등록 실패");
    }

    return mav;
  }
  
  /**
   * <XMP>
   * 수정을 위한 해당 댓글의 댓글 내용 read
   * </XMP>
   * @param sreplyno 댓글 번호
   * @return sreplyno, sreplycontent
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/reply_update.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String reply_update(int sreplyno) {
    
    // System.out.println(" => Update() GET executed");
    JSONObject obj = new JSONObject(); 
    
    SalereplyVO salereplyVO = salereplyProc.read(sreplyno);
    
    obj.put("sreplyno", sreplyno);
    obj.put("sreplycontent", salereplyVO.getSreplycontent());

    return obj.toString();
  }
  
  /**
   * <XMP>
   * 댓글 수정 결과 출력
   * </XMP>
   * @param salereplyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/reply_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_update(SalereplyVO salereplyVO) {
    
    // System.out.println(" => Update() POST executed");
    JSONObject obj = new JSONObject(); 
      
    int sreplyno = salereplyVO.getSreplyno(); // 댓글 번호
    String sreplycontent = salereplyVO.getSreplycontent(); // 댓글 내용
    int nowPage = salereplyVO.getNowPage(); // 현재 페이지
    int saleno = salereplyVO.getSaleno(); // 게시글 번호
    
    int count = salereplyProc.update(salereplyVO);
    
    obj.put("count", count);
    obj.put("sreplycontent", sreplycontent);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * 댓글 삭제 결과 출력
   * </XMP>
   * @param salereplyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/reply_delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_delete(SalereplyVO salereplyVO) {
    
    // System.out.println(" => delete() POST executed");
    JSONObject obj = new JSONObject(); 
      
    int sreplyno = salereplyVO.getSreplyno();
    int nowPage = salereplyVO.getNowPage();
    
    int count = salereplyProc.delete(sreplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * 부모 댓글의 delete에 대하여 update 처리
   * (= "삭제 된 댓글입니다."라는 내용으로 출력하려는 것)
   * </XMP>
   * @param salereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/update_parent.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update_parent(SalereplyVO salereplyVO) {
    
    // System.out.println(" => Update_parent() POST executed");
    JSONObject obj = new JSONObject(); 
      
    int sreplyno = salereplyVO.getSreplyno();
    int nowPage = salereplyVO.getNowPage();
    
    int count = salereplyProc.update_parent(sreplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  

}
