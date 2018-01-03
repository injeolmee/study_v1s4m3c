package dev.mvc.qna;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nation.web.tool.Tool;
import nation.web.tool.Upload;

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

import dev.mvc.qnareply.QnareplyProcInter;
import dev.mvc.qnareply.QnareplyVO;

@Controller
public class QnaCont {
  @Autowired
  @Qualifier("dev.mvc.qna.QnaProc")
  private QnaProcInter qnaProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.qnareply.QnareplyProc")
  private QnareplyProcInter qnareplyProc;
  
  public QnaCont() {
    System.out.println("--> QnaCont() Created.");
  }
  
  @RequestMapping(value="/user/qnaboard/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/qnaboard/create");
    
    return mav;
  }
  
  @RequestMapping(value="/user/qnaboard/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, QnaVO qnaVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/qnaboard/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
 // -------------------- 파일전송 코드 시작 --------------------
    String upDir = Tool.getRealPath(request, "/study/user/qnaboard/storage");
    MultipartFile file1MF = qnaVO.getFile1MF();
    String qnafile1 = file1MF.getOriginalFilename();
    long qnasize1 = file1MF.getSize();
    String qnafstor1 = "";
    
    if (qnasize1 > 0) {
      qnafile1 = Upload.saveFileSpring(file1MF, upDir);
      if (Tool.isImage(qnafile1)) {
        qnafstor1 = Tool.preview(upDir, qnafile1, 120, 80);
      }
    }
    
    qnaVO.setQnafile1(qnafile1);
    qnaVO.setQnasize1(qnasize1);
    qnaVO.setQnafstor1(qnafstor1);
    
    // -------------------- 파일전송 코드 종료 --------------------
    
    if (qnaProc.create(qnaVO) == 1) {
      msgs.add("QnA 등록 성공<br>");
      // categoryProc.increaseCnt(contestVO.getCateno());
    } else {
      msgs.add("QnA 등록 실패<br>");
      msgs.add("다시 시도 --> 운영팀<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }
    
    links.add("<button type= 'button' onclick=\"location.href='/study/user/qnaboard/list_all_qna.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  @RequestMapping(value="/nonuser/qnaboard/list_all_qna.do", method=RequestMethod.GET)
  public ModelAndView list_all_qna(@RequestParam(value="qnatitle", defaultValue="") String qnatitle,
                                   @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/qnaboard/list_all_qna");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("qnatitle", qnatitle);
    hashMap.put("nowPage", nowPage);
    
    List<QnaVO> list = qnaProc.list_all_qna(hashMap);
    mav.addObject("list", list);
    
    int total_count = qnaProc.total_count();
    mav.addObject("total_count", total_count);
    
    int search_count = qnaProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    String paging = qnaProc.paging(search_count, nowPage, qnatitle);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  @RequestMapping(value="/user/qnaboard/update.do", method=RequestMethod.GET)
  public ModelAndView update(int qnano) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/qnaboard/update");
    
    QnaVO qnaVO = qnaProc.read(qnano);
    mav.addObject("qnaVO", qnaVO);
    
    return mav;
  }
  
  @RequestMapping(value="/user/qnaboard/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, QnaVO qnaVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qnaboard/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    // ============================= 파일 전송 코드 시작 ========================
    String upDir = Tool.getRealPath(request, "/study/user/qnaboard/storage");
    MultipartFile file1MF = qnaVO.getFile1MF();
    String qnafile1 = "";
    long qnasize1 = file1MF.getSize();
    String qnafstor1 = "";
    
    QnaVO qnaVO_old = qnaProc.read(qnaVO.getQnano());
    
    if (qnasize1 > 0) {
      Tool.deleteFile(upDir, qnaVO_old.getQnafile1());
      Tool.deleteFile(upDir, qnaVO_old.getQnafstor1());
      
      qnafile1 = Upload.saveFileSpring(file1MF, upDir);
      
    } else {
      qnafile1 = qnaVO.getQnafile1();
      qnasize1 = qnaVO.getQnasize1();
      qnafstor1 = qnaVO.getQnafstor1();
    }
    
    qnaVO.setQnafile1(qnafile1);
    qnaVO.setQnasize1(qnasize1);
    qnaVO.setQnafstor1(qnafstor1);
    // ============================= 파일 전송 코드 종료 ========================
    
    String root = request.getContextPath();

    if (qnaProc.update(qnaVO) == 1) {
      msgs.add("게시글 변경 성공<br>");
      links.add("<button type='button' onclick=\"location.href='/study/user/qnaboard/read.do?qnano=" + qnaVO.getQnano() + "'\">변경 확인</button>");
    } else {
      msgs.add("블로그 변경 실패<br>");
      msgs.add("다시 시도 -> 운영팀<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='/study/user/qnaboard/list_all_qna.do?qnano=" + qnaVO.getQnano() +"'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  /**
   * 회원 패스워드 검사
   * @param request 
   * @param memid
   * @return
   * @throws UnsupportedEncodingException 
   * @throws BadPaddingException 
   * @throws IllegalBlockSizeException 
   * @throws InvalidAlgorithmParameterException 
   * @throws NoSuchPaddingException 
   * @throws NoSuchAlgorithmException 
   * @throws InvalidKeyException 
   */
  /*@ResponseBody
  @RequestMapping(value = "/qnaboard/pwdchk.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String pwdchk(String modal_pwd, HttpServletRequest request) {
    JSONObject obj = new JSONObject();
    HttpSession session = request.getSession(false);
    
    // 패스워드 암호화
    String qnapwd = (String)session.getAttribute("qnapwd");
    System.out.println("패스워드검사 qnapwd: " + qnapwd);
    int cnt_qnapwd = 0;
    if(modal_pwd.equals(qnapwd) == true) { 
      cnt_qnapwd = 1;
    }
    obj.put("cnt_qnapwd", cnt_qnapwd);
    
    return obj.toJSONString();    
  }*/
  
  @RequestMapping(value="/nonuser/qnaboard/read.do", method=RequestMethod.GET)
  public ModelAndView read(HttpSession session,
                          @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
                          @RequestParam(value = "qnano") int qnano) {
    qnaProc.increaseCnt(qnano);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/qnaboard/read");
    
    QnaVO qnaVO = qnaProc.read(qnano);
    qnaVO.setQnacont(Tool.convertChar(qnaVO.getQnacont()));
    mav.addObject("qnaVO", qnaVO);
    
    //***************************************************************** 
    // 댓글 목록 처리 시작 (From "Sharedreply" Package)
    //***************************************************************** 
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("qnano", qnano);
    hashMap.put("nowPage", nowPage);
    
    List<QnareplyVO> list_reply = qnareplyProc.list_all_qnareply(hashMap);
    mav.addObject("list_reply", list_reply);
    
    int search_count = qnareplyProc.search_count(qnano);
    mav.addObject("search_count", search_count); // 검색된 레코드 갯수

    String paging = qnareplyProc.paging(nowPage, search_count, qnano);  // 페이징 처리
    mav.addObject("paging", paging);              
    mav.addObject("nowPage", nowPage);         // 현재 페이지 변수
    //***************************************************************** 
    // 댓글 목록 처리 종료
    //*****************************************************************
    
    return mav;
  }
  
  @RequestMapping(value = "/user/qnaboard/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, QnaVO qnaVO) throws Exception {
    ModelAndView mav = new ModelAndView();
    
    // -----------------------------------파일전송 코드 시작-------------------------------------------------
    String upDir = Tool.getRealPath(request, "/study/user/qnaboard/storage");
    
    // 기존 글 정보 로딩
    QnaVO qnaVO_old = qnaProc.read(qnaVO.getQnano());
    
    Tool.deleteFile(upDir, qnaVO_old.getQnafile1());
    Tool.deleteFile(upDir, qnaVO_old.getQnafstor1());
    // -----------------------------------파일전송 코드 종료-------------------------------------------------    
    
    int check = 0; // 댓글 전체 삭제
    int count = 0; // 게시글 삭제 
    
    check = qnareplyProc.delete_all(qnaVO.getQnano());
    
    System.out.println("check ---> " + check);
    
    if (check == 1) {
      System.out.println("댓글 전체 삭제 성공");
      count = qnaProc.delete(qnaVO.getQnano());
      mav.addObject("count", count);
      if (count  == 1) {
        System.out.println("글 삭제 성공");
      } else {
        System.out.println("글 삭제 실패");
      }
      
    }
    mav.setViewName("redirect:/study/user/qnaboard/list_all_qna.do");
    
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
  @RequestMapping(value = "/user/qnaboard/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(HttpServletRequest request, QnareplyVO qnareplyVO) {
    // System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int qnano = qnareplyVO.getQnano();
    String qrcont = qnareplyVO.getQrcont();
    String qrname = qnareplyVO.getQrname();
    int qrno = qnareplyVO.getQrno();
    int memberno = qnareplyVO.getMemberno();
    
    int count = qnareplyProc.create(qnareplyVO);
    
    System.out.println("qnano ---> " + qnano);
    System.out.println("qrcont ---> " + qrcont);
    System.out.println("qrname ---> " + qrname);
    System.out.println("qrno ---> " + qrno);
    System.out.println("memberno ---> " + memberno);
    System.out.println("count ---> " + count);
    
    if (count == 1) {
      System.out.println("댓글등록 성공");
      mav.setViewName("redirect:/user/qnaboard/read.do?qnano=" + qnano);
    } else {
      System.out.println("댓글등록 실패");
    }
    
    return mav;
  }
  
  /**
   * <XMP>
   * 수정을 위한 해당 댓글의 댓글 내용 read
   * </XMP>
   * @param shreplyno 댓글 번호
   * @return shreplyno, shreplycontent
   */
  @ResponseBody
  @RequestMapping(value = "/user/qnaboard/reply_update.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String reply_update(int qrno) {
    // System.out.println(" => Update() GET executed");
    
    JSONObject obj = new JSONObject(); 
    
    QnareplyVO qnareplyVO = qnareplyProc.read(qrno);
    
    obj.put("qrno", qrno);
    obj.put("qrcont", qnareplyVO.getQrcont());

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
  @RequestMapping(value = "/user/qnaboard/reply_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_update(QnareplyVO qnareplyVO) {
    // System.out.println(" => Update() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int qrno = qnareplyVO.getQrno();
    String qrcont = qnareplyVO.getQrcont();
    int nowPage = qnareplyVO.getNowPage();
    int qnano = qnareplyVO.getQnano();
    
    int count = qnareplyProc.update(qnareplyVO);
    
    obj.put("count", count);
    obj.put("qrcont", qrcont);
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
  @RequestMapping(value = "/user/qnaboard/reply_delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_delete(QnareplyVO qnareplyVO) {
    // System.out.println(" => delete() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int qrno = qnareplyVO.getQrno();
    int nowPage = qnareplyVO.getNowPage();
    
    int count = qnareplyProc.delete(qrno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
}
