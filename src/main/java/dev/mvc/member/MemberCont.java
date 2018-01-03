package dev.mvc.member;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import nation.web.tool.AES256Util;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  private JavaMailSender mailSender;
    

  public MemberCont() {
    
  }
  
  /**
   * 회원 정보 등록
   * @return
   */
  // http://localhost:9090/study/nonuser/login/memberjoin.do
  @RequestMapping(value = "/nonuser/login/memberjoin.do", method= RequestMethod.GET)
  public ModelAndView memberjoin() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/login/memberjoin"); 
    
    return mav;
  }
  
  /**
   * 회원 정보 등록
   * @param memberVO
   * @return
   * @throws BadPaddingException 
   * @throws IllegalBlockSizeException 
   * @throws InvalidAlgorithmParameterException 
   * @throws NoSuchPaddingException 
   * @throws NoSuchAlgorithmException 
   * @throws UnsupportedEncodingException 
   * @throws InvalidKeyException 
   */
  @RequestMapping(value = "/nonuser/login/memberjoin.do", method = RequestMethod.POST)
  public ModelAndView memberjoin(HttpServletRequest request, MemberVO memberVO) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    
/*    System.out.println(memberVO.getMemname());
    System.out.println(memberVO.getMememail());
    System.out.println(memberVO.getMemgender());
    System.out.println("getMemphoto:" + memberVO.getMemphoto());
    System.out.println("getMemaddress:" + memberVO.getMemaddress());
    System.out.println("getMbirthvb:" + memberVO.getMbirthvb());
    System.out.println("getMgendervb:" + memberVO.getMgendervb());
    System.out.println("getMaddressvb:" + memberVO.getMaddressvb());
    System.out.println("getMphonevb:" + memberVO.getMphonevb());
    System.out.println("getMsnsvb:" + memberVO.getMsnsvb());
    System.out.println("getMintrovb:" + memberVO.getMintrovb());
    System.out.println("getMphotovb:" + memberVO.getMphotovb());
    System.out.println(memberVO.getMemauth());
    System.out.println(memberVO.getMemconfirm());*/
    
    String root = request.getContextPath();  
    
    if (memberVO.getMbirthvb() == null || memberVO.getMbirthvb() == "") {
      memberVO.setMbirthvb("Y");
    }
    if (memberVO.getMgendervb() == null || memberVO.getMgendervb() == "") {
      memberVO.setMgendervb("Y");
    }
    if (memberVO.getMaddressvb() == null || memberVO.getMaddressvb() == "") {
      memberVO.setMaddressvb("Y");
    }
    if (memberVO.getMphonevb() == null || memberVO.getMphonevb() == "") {
      memberVO.setMphonevb("Y");
    }
    if (memberVO.getMsnsvb() == null || memberVO.getMsnsvb() == "") {
      memberVO.setMsnsvb("Y");
    }
    if (memberVO.getMintrovb() == null || memberVO.getMintrovb() == "") {
      memberVO.setMintrovb("Y");
    }
    if (memberVO.getMphotovb() == null || memberVO.getMphotovb() == "") {
      memberVO.setMphotovb("Y");
    }
    
    // 주소 select 옵션 회원주소 = 시 + 구
    String area = memberVO.getArea();
    String selected_area = memberVO.getSelected_area();
    String memaddress =  area + " " + selected_area;
    memberVO.setArea(area);
    memberVO.setSelected_area(selected_area);
    memberVO.setMemaddress(memaddress);
    
    System.out.println("getArea:" + memberVO.getArea());
    System.out.println("getSelected_area:" + memberVO.getSelected_area());
    System.out.println("getMemaddress:" + memberVO.getMemaddress());
    
    System.out.println("getMaddressvb:" + memberVO.getMaddressvb());
    System.out.println("--> memberjoin() POST executed");
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/login/message"); // webapp/member/message.jsp
    
    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();    
    String mempasswd = memberVO.getMempasswd();
    mempasswd = AES256Util.aesEncode(mempasswd);
    mav.addObject("mempasswd", mempasswd); 
    memberVO.setMempasswd(mempasswd);
    System.out.println("암호화 패스워드: " + mempasswd);
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    // --------------------------------파일전송 코드 시작-------------------------------------------
    String upDir = Tool.getRealPath(request,  "/nonuser/login/storage");
    MultipartFile file1MF = memberVO.getFile1MF();
    String memphoto = ""; // 컬럼에 저장할 파일명
    long memsize = file1MF.getSize();
    String memphoto_t = "";

    if (memsize > 0) {
      memphoto = Upload.saveFileSpring(file1MF, upDir);

      if (Tool.isImage(memphoto)) {
        memphoto_t = Tool.preview(upDir, memphoto, 110, 120); // Thumb 이미지 생성
      }
    }
    memberVO.setMemphoto(memphoto);
    memberVO.setMemsize(memsize);
    memberVO.setMemphoto_t(memphoto_t);
    // --------------------------------파일전송 코드 종료-------------------------------------------      

 
    // 중복 아이디 검사
    int cnt_id = memberProc.check_id(memberVO.getMemid());       
    int cnt_email = memberProc.check_email(memberVO.getMememail());       
    if (cnt_id > 0 || cnt_email > 0) {
      msgs.add("ID나 이메일이 중복됩니다.");
      msgs.add("폼에서 회원가입을해주세요. ☏ 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
    } else {
      // 회원 가입 처리
      if (memberProc.memberjoin(memberVO) == 1) {
        msgs.add("회원 가입이 완료되었습니다.");
        msgs.add("가입해 주셔서 감사합니다.");
        links.add("<button type='button' onclick=\"location.href='" + root + "/nonuser/login/login.do'\">로그인</button>");
        
      } else {
        msgs.add("회원 가입에 실패했습니다.");
        msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 000-0000-0000");
        links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");        
      }
      
    }
    
    links.add("<button type='button' onclick=\"location.href='" + root + "/main/index.do'\">홈페이지</button>");
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }

  
  /**
   * 중복 아이디 검사
   * @param memid
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/check_id.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String check_id(String memid) {
    JSONObject obj = new JSONObject();
    // System.out.println("check_id created");
    // System.out.println("memid: " + memid);
    
    int cnt_id = memberProc.check_id(memid);
    obj.put("cnt_id", cnt_id);
    // System.out.println("cnt_id: " + cnt_id);
    
    return obj.toJSONString();
  }
  
  
  /**
   * 중복 이메일 검사
   * @param mememail
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/check_email.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String check_email(String mememail) {
    JSONObject obj = new JSONObject();
    // System.out.println("check_email created");
    // System.out.println("mememail: " + mememail);

    int cnt_email = memberProc.check_email(mememail);
    obj.put("cnt_email", cnt_email);
    // System.out.println("cnt: " + cnt);

    return obj.toJSONString();
  }
  
  
  // http://localhost:9090/study/member/mailForm.do
/*  @RequestMapping(value = "/member/mailForm.do")
  public String mailForm() {
   
    return "/member/mailForm";
  }*/
/*  
  @RequestMapping(value = "/member/mailForm.do")
  public ModelAndView mailForm(String mememail) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/mailForm");
    String toemail = mememail;
    mav.addObject("toemail", toemail);
   
    return mav;
  }
  */
  
  
  /**
   * 인증 메일 보내기
   * @param tomail
   * @param title
   * @param content
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/mailSending.do", method = RequestMethod.POST, produces= "text/plain;charset=UTF-8")
  public String mailSending(String tomail, String title, String content) {    // HttpServletRequest request
    JSONObject obj = new JSONObject();
    // System.out.println("Cont tomail: " + tomail);
    // System.out.println("Cont title: " + title);
    String code = memberProc.key(); // 인증코드 생성
    
    String codesend = "";
   
    String setfrom = "jukseolhwa@gmail.com";        /*각자 gmail 주소 쓰세요*/         
    // String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
    // String title   = request.getParameter("title");      // 제목
    // String content = request.getParameter("content");    // 내용
    content = content.replace("<br>","\r\n");
    content += code;
    System.out.println("Cont content: " + content);
   
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper 
                        = new MimeMessageHelper(message, true, "UTF-8");
 
      messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
      messageHelper.setTo(tomail);     // 받는사람 이메일
      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
      messageHelper.setText(content);  // 메일 내용
     
      mailSender.send(message);
      
      codesend = "성공";
      obj.put("codesend", codesend);
      obj.put("code", code);
    } catch(Exception e){
      System.out.println(e);
    }
   
    return obj.toJSONString();
  }
  
  
  /**
   * 회원 목록
   * @return
   */
  @RequestMapping(value="/admin/member/mem_list.do", method=RequestMethod.GET)
  public ModelAndView mem_list(MemberVO memberVO){
    // System.out.println("--> mem_list() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/member/mem_list"); // 
        
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    String word = memberVO.getWord();
    String search = memberVO.getSearch();
    int nowPage = memberVO.getNowPage();
    
    word = Tool.checkNull(word);
    
    System.out.println("word:" + word);
    System.out.println("search:" + search);
    System.out.println("nowPage:" + nowPage);
    
    hashMap.put("word", word);
    hashMap.put("search", search);
    hashMap.put("nowPage", nowPage);
    
    List<MemberVO> mem_list = memberProc.mem_list(hashMap);
    mav.addObject("mem_list", mem_list);
/*    System.out.println(mem_list.get(0).getMemname());
    System.out.println(mem_list.get(1).getMemname());*/
    
    int search_count = memberProc.search_count(hashMap); // 검색 레코드 갯수
    mav.addObject("search_count", search_count);
    System.out.println("search_count: " + search_count);
    
    String paging = memberProc.paging(search_count, nowPage, word, search);
    mav.addObject("paging", paging);
    
    return mav;
  }
  
  
  /**
   * 회원 정보 조회
   * @param memberno
   * @return
   */
  @RequestMapping(value="/user/member/mem_read.do", method=RequestMethod.GET)
  public ModelAndView mem_read(int memberno){
    // System.out.println("--> mem_read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/member/mem_read"); // webapp/member/mem_read.jsp
    
    MemberVO memberVO = memberProc.mem_read(memberno);    
    mav.addObject("memberVO", memberVO);

    return mav;
  } 
  
  
  /**
   * 회원 정보 조회 타인
   * @param memberno
   * @return
   */
  @RequestMapping(value="/user/member/mem_read_info.do", method=RequestMethod.GET)
  public ModelAndView mem_read_info(int memberno){
    // System.out.println("--> mem_read_info(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/member/mem_read_info"); 
    
    MemberVO memberVO = memberProc.mem_read(memberno);    
    mav.addObject("memberVO", memberVO);
    
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
  @ResponseBody
  @RequestMapping(value = "/user/member/mem_read_pwdcheck.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String admin_read_pwdcheck(String modal_pwd, HttpServletRequest request) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    JSONObject obj = new JSONObject();
    HttpSession session = request.getSession(false);
    
    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();    
    modal_pwd = aes256.aesEncode(modal_pwd);  // 리드에서 수정넘어갈 때 패스워드 검사 모달창에 입력된 패스워드 암호화 변환
    String mempasswd = (String) session.getAttribute("mempasswd"); // 세션에 저장된 암호화된 패스워드
    System.out.println("패스워드검사 mempasswd: " + mempasswd);
    int cnt_mempasswd = 0;
    if(modal_pwd.equals(mempasswd) == true) { 
      cnt_mempasswd = 1;
    }
    obj.put("cnt_mempasswd", cnt_mempasswd);
    
    return obj.toJSONString();    
  }
  
  
  /**
   * 회원 정보 수정
   * @param memberno
   * @return
   */
  @RequestMapping(value = "/user/member/mem_update.do", method = RequestMethod.GET)
  public ModelAndView mem_update(int memberno) {
    // System.out.println("--> mem_update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/member/mem_update"); // webapp/member/mem_update.jsp

  
    MemberVO memberVO = memberProc.mem_read(memberno);
    String memaddress = memberVO.getMemaddress();
    String[] address = memaddress.split(" ");
    String area = address[0];
    String selected_area = address[1];
    
    // System.out.println(address[0]);
    // System.out.println(address[1]);
    // System.out.println(memberVO.getMemphoto_t());
    memberVO.setArea(area);
    memberVO.setSelected_area(selected_area);
    mav.addObject("memberVO", memberVO);
    return mav;
  }
  
  
  /**
   * 회원 정보 수정 처리
   * @param request
   * @param memberVO
   * @return
   * @throws UnsupportedEncodingException 
   * @throws BadPaddingException 
   * @throws IllegalBlockSizeException 
   * @throws InvalidAlgorithmParameterException 
   * @throws NoSuchPaddingException 
   * @throws NoSuchAlgorithmException 
   * @throws InvalidKeyException 
   */
  @RequestMapping(value="/user/member/mem_update.do", method=RequestMethod.POST)
  public ModelAndView mem_update(HttpServletRequest request, HttpSession session,
                                           MemberVO memberVO) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
     System.out.println("--> mem_update() POST called.");
    // session의 memberno값 가져오기
    int memberno = (Integer) session.getAttribute("memberno");
    memberVO.setMemberno(memberno);
    
    String root = request.getContextPath();

    if (memberVO.getMbirthvb() == null || memberVO.getMbirthvb() == "") {
      memberVO.setMbirthvb("Y");
    }
    if (memberVO.getMgendervb() == null || memberVO.getMgendervb() == "") {
      memberVO.setMgendervb("Y");
    }
    if (memberVO.getMaddressvb() == null || memberVO.getMaddressvb() == "") {
      memberVO.setMaddressvb("Y");
    }
    if (memberVO.getMphonevb() == null || memberVO.getMphonevb() == "") {
      memberVO.setMphonevb("Y");
    }
    if (memberVO.getMsnsvb() == null || memberVO.getMsnsvb() == "") {
      memberVO.setMsnsvb("Y");
    }
    if (memberVO.getMintrovb() == null || memberVO.getMintrovb() == "") {
      memberVO.setMintrovb("Y");
    }
    if (memberVO.getMphotovb() == null || memberVO.getMphotovb() == "") {
      memberVO.setMphotovb("Y");
    }
    
    // 주소 select 옵션 회원주소 = 시 + 구
    String area = memberVO.getArea();
    String selected_area = memberVO.getSelected_area();
    String memaddress =  area + " " + selected_area;
    memberVO.setMemaddress(memaddress);    
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/member/message"); // webapp/member/message.jsp
    
    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();    
    String mempasswd = memberVO.getMempasswd();
    mempasswd = aes256.aesEncode(mempasswd);
    memberVO.setMempasswd(mempasswd);
    mav.addObject("mempasswd", mempasswd); 
    System.out.println("업데이트 암호화 패스워드: " + mempasswd);
    
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    // --------------------------------파일전송 코드 시작-------------------------------------------
    
    String upDir = Tool.getRealPath(request, "/nonuser/login/storage");
    MultipartFile file1MF = memberVO.getFile1MF();
    String memphoto = ""; // 컬럼에 저장할 파일명
    long memsize = file1MF.getSize();
    String memphoto_t = "";
    
    MemberVO memberVO_old = memberProc.mem_read(memberVO.getMemberno());
    
    if (memsize > 0) {
      // 신규 파일 등록시 기존 파일 삭제
      Tool.deleteFile(upDir, memberVO_old.getMemphoto());
      Tool.deleteFile(upDir, memberVO_old.getMemphoto_t());
      
      memphoto = Upload.saveFileSpring(file1MF, upDir);

      if (Tool.isImage(memphoto)) {
        memphoto_t = Tool.preview(upDir, memphoto, 110, 120); // Thumb 이미지 생성
      }
    } else { // 파일을 변경하지 않는 경우 기존 파일 정보 사용
      memphoto = memberVO_old.getMemphoto();
      memsize = memberVO_old.getMemsize();
      memphoto_t = memberVO_old.getMemphoto_t();
     
    }
    
    memberVO.setMemphoto(memphoto);
    memberVO.setMemsize(memsize);
    memberVO.setMemphoto_t(memphoto_t);
    // --------------------------------파일전송 코드 시작-------------------------------------------    
    System.out.println("memphoto: " + memberVO.getMemphoto());
    if (memberProc.mem_update(memberVO) == 1) {
      msgs.add("회원 수정이 완료되었습니다.");
      links.add("<button type='button' onclick=\"location.href='./mem_read.do?memberno="+memberVO.getMemberno()+"'\">변경 사항 확인</button>");
      
    } else {
      msgs.add("회원 수정에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      
    }
    
    links.add("<button type='button' onclick=\"location.href='" + root + "/main/index.do'\">홈페이지</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
  /**
   * 로그인 폼
   * @return
   */
  // http://localhost:9090/study/nonuser/login/login.do 
  @RequestMapping(value = "/nonuser/login/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request, HttpSession session) {
            
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/login/login_form"); // /webapp/member/login.jsp    
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;
 
    String ck_id = ""; // id 저장 변수
    String ck_id_save = ""; // id 저장 여부를 체크하는 변수
    String ck_passwd = ""; // passwd 저장 변수
    String ck_passwd_save = ""; // passwd 저장 여부를 체크하는 변수
    
    String ck_admid = ""; // id 저장 변수
    String ck_admid_save = ""; // id 저장 여부를 체크하는 변수
    String ck_admpasswd = ""; // passwd 저장 변수
    String ck_admpasswd_save = ""; // passwd 저장 여부를 체크하는 변수
 
 
    if (cookies != null) {
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
        
        if (cookie.getName().equals("ck_id")){
          ck_id = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_id_save")){
          ck_id_save = cookie.getValue();  // Y, N
        }else if (cookie.getName().equals("ck_passwd")){
          ck_passwd = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_passwd_save")){
          ck_passwd_save = cookie.getValue();  // Y, N
        }
      }
    }
    
    
    if (cookies != null) {
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
        
        if (cookie.getName().equals("ck_admid")){
          ck_admid = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_admid_save")){
          ck_admid_save = cookie.getValue();  // Y, N
        }else if (cookie.getName().equals("ck_admpasswd")){
          ck_admpasswd = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_admpasswd_save")){
          ck_admpasswd_save = cookie.getValue();  // Y, N
        }
      }
    }
    
    mav.addObject("ck_id", ck_id);
    mav.addObject("ck_id_save", ck_id_save);
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);
    
    mav.addObject("ck_admid", ck_admid);
    mav.addObject("ck_admid_save", ck_admid_save);
    mav.addObject("ck_admpasswd", ck_admpasswd);
    mav.addObject("ck_admpasswd_save", ck_admpasswd_save);
    
    return mav;
  }
  

   /**
    * 로그인 처리
    * @param request
    * @param response
    * @param session
    * @param memberVO
    * @return
   * @throws UnsupportedEncodingException 
   * @throws BadPaddingException 
   * @throws IllegalBlockSizeException 
   * @throws InvalidAlgorithmParameterException 
   * @throws NoSuchPaddingException 
   * @throws NoSuchAlgorithmException 
   * @throws InvalidKeyException 
    */
  @RequestMapping(value="/nonuser/login/login.do", method=RequestMethod.POST)
  public ModelAndView login(HttpServletRequest request, 
                                       HttpServletResponse response,
                                       HttpSession session,
                                       MemberVO memberVO, String memid, String mempasswd) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
    System.out.println("--> login() POST called.");
    // System.out.println("memberVO.getMemid(): " + memberVO.getMemid());
    // System.out.println("memberVO.getMempasswd(): " + memberVO.getMempasswd());
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/login/message"); // webapp/member/message.jsp
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String root = request.getContextPath();
    
    String input_mempasswd = mempasswd;
    
    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();    
    mempasswd = aes256.aesEncode(input_mempasswd);
    System.out.println("암호화 패스워드: " + mempasswd);
    
    if (memberProc.login(memid, mempasswd) != 1) {
      msgs.add("현재 패스워드가 일치하지 않습니다.");
      links.add("<button type='button' onclick=\"history.back()\">다시 시도</button>");
      
    } else { // 패스워드 일치하는 경우
      Date mem_login_time = new Date();
      
      int memberno = (memberProc.mem_read_id(memid)).getMemberno(); // ID에 해당하는 번호 산출
      String memauth = (memberProc.mem_read_id(memid)).getMemauth();
      String memname = (memberProc.mem_read_id(memid)).getMemname();
      System.out.println("memid :" + memid);
      System.out.println("mempasswd :" + mempasswd);
      System.out.println("memauth :" + memauth);
      System.out.println("memname :" + memname);
      
      session.setAttribute("memberno",  memberno); // session 내부 객체
      session.setAttribute("memid", memid);
      session.setAttribute("memauth", memauth);
      session.setAttribute("memname", memname);
      session.setAttribute("mem_login_time", mem_login_time);
      
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      String id_save = Tool.checkNull(memberVO.getId_save());
      if (id_save.equals("Y")) { // id를 저장할 경우
        Cookie ck_id = new Cookie("ck_id", memid);
        ck_id.setMaxAge(60 * 60 * 72 * 10); // 30 day, 초단위
        response.addCookie(ck_id);
      } else { // N, id를 저장하지 않는 경우
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id);
      }
      // id를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------
 
      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      String passwd_save = Tool.checkNull(memberVO.getPasswd_save());
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_passwd = new Cookie("ck_passwd", input_mempasswd);
        ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // passwd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------
      
      mav.setViewName("redirect:/main/index.do"); // 확장자 명시 
      
    }    
    
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);
    
    return mav;
  }
  
  
 
  /**
   * 로그아웃 처리
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value="/nonuser/login/logout.do", method=RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, 
                                         HttpSession session){
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String root = request.getContextPath();
    
    mav.setViewName("redirect:/main/index.do"); // 확장자 명시 

    session.removeAttribute("memberno");
    session.removeAttribute("memid");
    session.removeAttribute("memauth");
    session.removeAttribute("memname");
    
    // session.invalidate(); // session 내부 객체의 등록된 모든 session 변수 삭제

    return mav;
  }
  
  
  /**
   * 아이디/비번 찾기 폼
   * @return
   */
  @RequestMapping(value = "/nonuser/login/member_id_pwd_find.do", method= RequestMethod.GET)
  public ModelAndView member_id_pwd_find() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/login/member_id_pwd_find"); 
    
    return mav;
  }
  
  
  /**
   * 아이디 찾기
   * @param memname
   * @param mememail
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/find_memid.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String find_memid(String memname, String mememail) {
    JSONObject obj = new JSONObject();
    System.out.println("find_memid created");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    if(memname == null || memname == "" || mememail == null || mememail == "") {
      obj.put("memid", "none");
    } else {
      hashMap.put("memname", memname);
      hashMap.put("mememail", mememail);
      
      String memid = memberProc.find_memid(hashMap);
      
      if(memid == null || memid == "") {
        obj.put("memid", "none");
      } else {
        obj.put("memid", memid);
        // System.out.println("memid: " + memid);
      }
      
    }
    
    return obj.toJSONString();
  }
  

  /**
   * 비밀번호 찾기
   * @param memname
   * @param mememail
   * @param memid
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/find_mempasswd.do", method = RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String find_mempasswd(String memname, String mememail, String memid) {
    JSONObject obj = new JSONObject();
    System.out.println("find_mempasswd created");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    if(memname == null || memname == "" || mememail == null || mememail == "" || memid == null || memid == "") {
      obj.put("memberno", "none");
    } else {
      hashMap.put("memid", memid);
      hashMap.put("memname", memname);
      hashMap.put("mememail", mememail);
      
      String memberno = memberProc.find_mempasswd(hashMap);
      
      if(memberno == null || memberno == "") {
        obj.put("memberno", "none");
      } else {
        obj.put("memberno", memberno);
      }
      
    }
    
    return obj.toJSONString();
  }
  
  
  /**
   * 비밀번호 변경
   * @param request
   * @param memberVO
   * @param mempasswd
   * @param memberno
   * @return
   * @throws UnsupportedEncodingException
   * @throws InvalidKeyException
   * @throws NoSuchAlgorithmException
   * @throws NoSuchPaddingException
   * @throws InvalidAlgorithmParameterException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   */
  @ResponseBody
  @RequestMapping(value="/nonuser/login/mempasswd_change.do", method=RequestMethod.GET, produces= "text/plain;charset=UTF-8")
  public String mempasswd_change(HttpServletRequest request, MemberVO memberVO, String mempasswd, int memberno) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
    String root = request.getContextPath();
    JSONObject obj = new JSONObject();

    memberVO = memberProc.mem_read(memberno);
    memberVO.setMempasswd(mempasswd);
    
    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();    
    mempasswd = memberVO.getMempasswd();
    System.out.println("암호화전 패스워드: " + mempasswd);
    mempasswd = aes256.aesEncode(mempasswd);
    memberVO.setMempasswd(mempasswd);
    System.out.println("업데이트 암호화 패스워드: " + mempasswd);
    System.out.println("업데이트 암호화 패스워드VO: " + memberVO.getMempasswd());
    
    if(memberProc.mempasswd_change(memberVO) == 1) {
      obj.put("success", "success");
    } else {
      obj.put("fail", "fail");
    }
    
    return obj.toJSONString();
    
  }

  

}
