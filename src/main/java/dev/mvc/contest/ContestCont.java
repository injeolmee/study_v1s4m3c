package dev.mvc.contest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import dev.mvc.conlike.ConlikeProcInter;


@Controller
public class ContestCont {
  @Autowired
  @Qualifier("dev.mvc.contest.ContestProc")
  private ContestProcInter contestProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.conlike.ConlikeProc")
  private ConlikeProcInter conlikeProc = null;

  public ContestCont() {
    //System.out.println("--> ContestCont() Created.");
  }
  
  // http://localhost:9090/study/contest/create.do
  @RequestMapping(value = "/admin/contest/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    //System.out.println("--> create() GET Executed.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/contest/create");
    
    return mav;
  }
  
  @RequestMapping(value="/admin/contest/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ContestVO contestVO) {
    //System.out.println("--> create() POST Executed.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/contest/message");
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    // -------------------- 파일전송 코드 시작 --------------------
    String upDir = Tool.getRealPath(request, "/admin/contest/storage");
    MultipartFile file1MF = contestVO.getFile1MF();
    MultipartFile file2MF = contestVO.getFile2MF();
    MultipartFile file3MF = contestVO.getFile3MF();
    String conFile1 = file1MF.getOriginalFilename();
    long conSize1 = file1MF.getSize();
    String conThumb = "";
    
    String conFile2 = file2MF.getOriginalFilename();
    long conSize2 = file2MF.getSize();
    String conFstro2 = "";
    
    String conFile3 = file3MF.getOriginalFilename();
    long conSize3 = file3MF.getSize();
    String conFstro3 = "";
    
    if (conSize1 > 0) {
      conFile1 = Upload.saveFileSpring(file1MF, upDir);
      if (Tool.isImage(conFile1)) {
        conThumb = Tool.preview(upDir, conFile1, 120, 80);
      }
    }
    
    if (conSize2 > 0) {
      conFile2 = Upload.saveFileSpring(file2MF, upDir);
    }
    
    if (conSize3 > 0) {
      conFile3 = Upload.saveFileSpring(file3MF, upDir);
    }
    
    contestVO.setConFile1(conFile1);
    contestVO.setConSize1(conSize1);
    contestVO.setConThumb(conThumb);
    
    contestVO.setConFile2(conFile2);
    contestVO.setConSize2(conSize2);
    contestVO.setConFstor2(conFstro2);
    
    contestVO.setConFile3(conFile3);
    contestVO.setConSize3(conSize3);
    contestVO.setConFstor3(conFstro3);
    
    // -------------------- 파일전송 코드 종료 --------------------
    
    if (contestProc.create(contestVO) == 1) {
      // msgs.add("공모전 정보 등록 성공<br>");
      mav.setViewName("redirect:/nonuser/contest/list_all_contest.do");
      // categoryProc.increaseCnt(contestVO.getCateno());
    } /*else {
      msgs.add("공모전 정보 등록 실패<br>");
      msgs.add("다시 시도 --> 운영팀<br>");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }
    
    links.add("<button type= 'button' onclick=\"location.href='/study/nonuser/contest/list_all_contest.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);*/
    
    return mav;
  }
  
  @RequestMapping(value="/nonuser/contest/list_all_contest.do", method = RequestMethod.GET)
  public ModelAndView list_all_contest(@RequestParam(value="conWord", defaultValue="") String conWord,
                                       @RequestParam(value="nowPage", defaultValue="1") int nowPage) throws Exception {
    // System.out.println("list 출력");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/contest/list_all_contest");

    // 숫자와 문자열 타입을 저장해야함으로 Object 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();  
    hashMap.put("conWord", conWord);
    hashMap.put("nowPage", nowPage);
    
    List<ContestVO> list = contestProc.list_all_contest(hashMap);
    mav.addObject("list", list);
    
    List<ContestVO> list_by_count = contestProc.list_by_count();
    mav.addObject("list_by_count", list_by_count);
    
    List<ContestVO> list_by_good = contestProc.list_by_good();
    mav.addObject("list_by_good", list_by_good);
    
    int search_count = contestProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    int total_count = contestProc.total_count();
    mav.addObject("total_count", total_count);
    
    int day_count = contestProc.day_count();
    mav.addObject("day_count", day_count);
    
    String paging = contestProc.paging(search_count, nowPage, conWord);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    
    // System.out.println("day_count ---> " + day_count);
    
    return mav;    
  }
  
  @RequestMapping(value="/nonuser/contest/read.do", method = RequestMethod.GET)
  public ModelAndView read(int conNo, HttpSession session) throws Exception {
    contestProc.increaseCnt(conNo);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/contest/read");
    
    ContestVO contestVO = contestProc.read(conNo);
    
    if (session.getAttribute("memberno") != null) {
      HashMap hashMap = new HashMap();
      
      // 세션을 통해 memberno 값 가져옴
      int memberno = (Integer)session.getAttribute("memberno");
      // int memberno = contestVO.getMemberno();
      
      hashMap.put("memberno", memberno);
      hashMap.put("conNo", conNo);
      
      // conlike 테이블에 이미 입력된 회원인지 확인
      if (conlikeProc.good_chk(hashMap) == 0) {
        conlikeProc.create(hashMap);
      }
    }
    mav.setViewName("/nonuser/contest/read");
    
    contestVO.setConCont(Tool.convertChar(contestVO.getConCont()));
    
    mav.addObject("contestVO", contestVO);
    mav.addObject("max", contestProc.max());
    mav.addObject("min", contestProc.min());
    
    return mav;
  }
  
  
//http://localhost:9090/study/contest/update.do
 @ResponseBody
 @RequestMapping(value = "/admin/contest/update.do", method = RequestMethod.GET)
 public ModelAndView update(int conNo) throws Exception {
   // System.out.println("--> update() GET executed");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/contest/update"); // webapp/contest/update.jsp

   ContestVO contestVO = contestProc.read(conNo);
   mav.addObject("contestVO", contestVO);
   
   return mav;
 } 
 
 // http://localhost:9090/blog/blog/update.do
 @RequestMapping(value = "/admin/contest/update.do", method = RequestMethod.POST)
 public ModelAndView update(HttpServletRequest request, ContestVO contestVO) throws Exception {
   // System.out.println("--> update() GET executed");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/contest/message"); // webapp/contest/update.jsp

   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();
   
   // ********************* 날짜 계산 시작 *********************
   String conEnd = contestVO.getConEnd();
   String conStart = contestVO.getConStart();
   
   long time = System.currentTimeMillis();
   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
   String today = df.format(new Date(time));
   
   Date date_e = df.parse(conEnd);
   Date date_s = df.parse(conStart);
   
   String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date_e);
   String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date_s);
   
   long now = (df.parse(today)).getTime();
   long end = (df.parse(endDate)).getTime();
   long start = (df.parse(startDate)).getTime();
   
   long diff_e = end - now;
   long diff_s = start - now;
   long conRemain_e = diff_e / (24 * 60 * 60 * 1000);
   long conRemain_s = diff_s / (24 * 60 * 60 * 1000);
   
   //System.out.println("conRemain_e ---> " + conRemain_e);
   //System.out.println("conRemain_s ---> " + conRemain_s);
   
   contestVO.setConRemain_e(conRemain_e);
   contestVO.setConRemain_s(conRemain_s);
   // ********************* 날짜 계산 종료 *********************

   // 회원 개발 후 session 으로변경
   /*contestVO.setMno(1);*/

   String root = request.getContextPath();

   if (contestProc.update(contestVO) == 1) {
     /*msgs.add("게시글 변경 성공<br>");
     links.add("<button type='button' onclick=\"location.href='/study/nonuser/contest/read.do?conNo=" + contestVO.getConNo() + "'\">변경 확인</button>");
     */
     mav.setViewName("redirect:/nonuser/contest/read.do?conNo=" + contestVO.getConNo());
   } 

   return mav;
 }
 
 @ResponseBody
 @RequestMapping(value = "/admin/contest/update_thumb.do", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
 public String update_thumb(int conNo) throws Exception {
   // System.out.println("--> update() GET executed");
   /*ModelAndView mav = new ModelAndView();
   mav.setViewName("/contest/update_thumb");*/ // webapp/contest/update.jsp

   JSONObject obj = new JSONObject();
   
   ContestVO contestVO = contestProc.read(conNo);
   /*mav.addObject("contestVO", contestVO);*/
   
   obj.put("conNo", contestVO.getConNo());
   obj.put("conFile1", contestVO.getConFile1());
   obj.put("conSize1", contestVO.getConSize1());
   obj.put("conThumb", contestVO.getConThumb());
   
   return obj.toJSONString();
 } 
 
 @RequestMapping(value = "/admin/contest/update_thumb.do", method = RequestMethod.POST)
 public ModelAndView update_thumb(HttpServletRequest request, ContestVO contestVO) throws Exception {
   ModelAndView mav = new ModelAndView();
   // ============================== 파일 전송 코드 시작 ====================================
   String upDir = Tool.getRealPath(request, "/study/admin/contest/storage");
   MultipartFile file1MF = contestVO.getFile1MF();
   
   
   //System.out.println("conNo ---> " + contestVO.getConNo());
   //System.out.println("cateno ---> " + contestVO.getCateno());
   //System.out.println("file1MF ---> " + file1MF);
   
   String conFile1 = "";
   long conSize1 = file1MF.getSize();
   String conThumb = "";
   
   ContestVO contestVO_old = contestProc.read(contestVO.getConNo());
   
   if (conSize1 > 0) {
     Tool.deleteFile(upDir, contestVO_old.getConFile1());
     Tool.deleteFile(upDir, contestVO_old.getConThumb());
     
     conFile1 = Upload.saveFileSpring(file1MF, upDir);
     
     if (Tool.isImage(conFile1)) {
       conThumb = Tool.preview(upDir, conFile1, 120, 80);
     }
   } else {
     conFile1 = contestVO.getConFile1();
     conSize1 = contestVO.getConSize1();
     conThumb = contestVO.getConThumb();
   }
   
   contestVO.setConFile1(conFile1);
   contestVO.setConSize1(conSize1);
   contestVO.setConThumb(conThumb);
   
   // ============================== 파일 전송 코드 종료 ====================================
   
   if (contestProc.update_thumb(contestVO) == 1) {
     mav.setViewName("redirect:/study/admin/contest/update.do?conNo=" + contestVO.getConNo());
     //System.out.println("파일이 바뀌었습니다.");
     /*links.add("<button type='button' onclick=\"location.href='./update.do?conNo=" + contestVO.getConNo() + "&cateno=" + contestVO.getCateno() +"'\">업데이트 계속</button>");*/
   } 
   
   return mav;
 }
 
 @ResponseBody
 @RequestMapping(value = "/admin/contest/update_img.do", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
 public String update_img(int conNo) throws Exception {
// System.out.println("--> update() GET executed");
   /*ModelAndView mav = new ModelAndView();
   mav.setViewName("/contest/update_thumb");*/ // webapp/contest/update.jsp

   JSONObject obj = new JSONObject();
   
   ContestVO contestVO = contestProc.read(conNo);
   /*mav.addObject("contestVO", contestVO);*/
     
   obj.put("conNo", contestVO.getConNo());
   obj.put("conFile2", contestVO.getConFile2());
   obj.put("conSize2", contestVO.getConSize2());
   obj.put("conFstor2", contestVO.getConFstor2());
   
   return obj.toJSONString();
 } 
 
 @RequestMapping(value = "/admin/contest/update_img.do", method = RequestMethod.POST)
 public ModelAndView update_img(HttpServletRequest request, ContestVO contestVO) throws Exception {
   ModelAndView mav = new ModelAndView();
   // ============================== 파일 전송 코드 시작 ====================================
   String upDir = Tool.getRealPath(request, "/study/admin/contest/storage");
   MultipartFile file2MF = contestVO.getFile2MF();
   
   String conFile2 = "";
   long conSize2 = file2MF.getSize();
   String conFstor2 = "";
   
   ContestVO contestVO_old = contestProc.read(contestVO.getConNo());
   
   if (conSize2 > 0) {
     Tool.deleteFile(upDir, contestVO_old.getConFile2());
     Tool.deleteFile(upDir, contestVO_old.getConFstor2());
     
     conFile2 = Upload.saveFileSpring(file2MF, upDir);
     
     if (Tool.isImage(conFile2)) {
       conFstor2 = Tool.preview(upDir, conFile2, 120, 80);
     }
   } else {
     conFile2 = contestVO.getConFile2();
     conSize2 = contestVO.getConSize2();
     conFstor2 = contestVO.getConFstor2();
   }
   
   contestVO.setConFile2(conFile2);
   contestVO.setConSize2(conSize2);
   contestVO.setConFstor2(conFstor2);
   
   // ============================== 파일 전송 코드 종료 ====================================
   
   if (contestProc.update_img(contestVO) == 1) {
     mav.setViewName("redirect:/study/admin/contest/update.do?conNo=" + contestVO.getConNo());
     //System.out.println("파일이 바뀌었습니다.");
   }

   return mav;
   
 }
 
 @ResponseBody
 @RequestMapping(value = "/admin/contest/update_file.do", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
 public String update_file(int conNo) throws Exception {
   // System.out.println("--> update() GET executed");
   /*ModelAndView mav = new ModelAndView();
   mav.setViewName("/contest/update_thumb");*/ // webapp/contest/update.jsp

   JSONObject obj = new JSONObject();
   
   ContestVO contestVO = contestProc.read(conNo);
   /*mav.addObject("contestVO", contestVO);*/
     
   obj.put("conNo", contestVO.getConNo());
   obj.put("conFile3", contestVO.getConFile3());
   obj.put("conSize3", contestVO.getConSize3());
   obj.put("conFstor3", contestVO.getConFstor3());
   
   return obj.toJSONString();
 } 
 
 @RequestMapping(value = "/admin/contest/update_file.do", method = RequestMethod.POST)
 public ModelAndView update_file(HttpServletRequest request, ContestVO contestVO) throws Exception {
   ModelAndView mav = new ModelAndView();
   
   // ============================== 파일 전송 코드 시작 ====================================
   String upDir = Tool.getRealPath(request, "/study/admin/contest/storage");
   MultipartFile file3MF = contestVO.getFile3MF();
   
   String conFile3 = "";
   long conSize3 = file3MF.getSize();
   String conFstor3 = "";
   
   ContestVO contestVO_old = contestProc.read(contestVO.getConNo());
   
   if (conSize3 > 0) {
     Tool.deleteFile(upDir, contestVO_old.getConFile3());
     Tool.deleteFile(upDir, contestVO_old.getConFstor3());
     
     conFile3 = Upload.saveFileSpring(file3MF, upDir);
     
     if (Tool.isImage(conFile3)) {
       conFstor3 = Tool.preview(upDir, conFile3, 120, 80);
     }
   } else {
     conFile3 = contestVO.getConFile3();
     conSize3 = contestVO.getConSize3();
     conFstor3 = contestVO.getConFstor3();
   }
   
   contestVO.setConFile3(conFile3);
   contestVO.setConSize3(conSize3);
   contestVO.setConFstor3(conFstor3);
   
   // ============================== 파일 전송 코드 종료 ====================================
   
   if (contestProc.update_file(contestVO) == 1) {
     mav.setViewName("redirect:/study/admin/contest/update.do?conNo=" + contestVO.getConNo());
     //System.out.println("파일이 바뀌었습니다.");
   }

   return mav;
   
 }
 
 @ResponseBody
 @RequestMapping(value = "/admin/contest/delete.do", method = RequestMethod.GET, produces="text/plain;charset=utf-8")
 public String delete(int conNo) throws Exception {
   JSONObject obj = new JSONObject();
   
   ContestVO contestVO = contestProc.read(conNo);
   
   obj.put("conNo", contestVO.getConNo());
   
   return obj.toJSONString();
 }
 
 @RequestMapping(value = "/admin/contest/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(HttpServletRequest request, ContestVO contestVO) throws Exception {
   // System.out.println("--> delete() POST executed");
   ModelAndView mav = new ModelAndView();
   // mav.setViewName("/contest/message"); // webapp/blog/message.jsp
   
   ArrayList<String> msgs = new ArrayList<String>();
   ArrayList<String> links = new ArrayList<String>();
   
   // -----------------------------------파일전송 코드 시작-------------------------------------------------
   String upDir = Tool.getRealPath(request, "/study/admin/contest/storage");

   // 기존에 등록된 글 정보 로딩
   ContestVO contestVO_old = contestProc.read(contestVO.getConNo());

     // 신규 파일 등록시 기존 파일 삭제
     Tool.deleteFile(upDir, contestVO_old.getConFile1());
     Tool.deleteFile(upDir, contestVO_old.getConFile2());
     Tool.deleteFile(upDir, contestVO_old.getConFile3());
     Tool.deleteFile(upDir, contestVO_old.getConThumb());

   // -----------------------------------파일전송 코드 종료-------------------------------------------------

   
   String root = request.getContextPath();
   
   if (contestProc.delete(contestVO.getConNo()) == 1) {
     // msgs.add("공모전 글 삭제 성공<br>");
     mav.setViewName("redirect:/nonuser/contest/list_all_contest.do");
   } 
   
   return mav;
 }
 
}
