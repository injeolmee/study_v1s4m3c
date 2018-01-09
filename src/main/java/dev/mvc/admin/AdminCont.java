package dev.mvc.admin;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberVO;
import nation.web.tool.AES256Util;
import nation.web.tool.Tool;

@Controller
public class AdminCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  @Autowired
  private JavaMailSender mailSender;

  public AdminCont() {
  }

  /**
   * 관리자 등록
   * 
   * @return http://localhost:9090/study/nonuser/login/admin_create.do
   */
  @RequestMapping(value = "/nonuser/login/admin_create.do", method = RequestMethod.GET)
  public ModelAndView admin_create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/login/admin_create"); // webapp/admin/admin/membadmin_createerjoin.jsp

    return mav;
  }

  /**
   * 관리자 등록 처리
   * 
   * @param request
   * @param adminVO
   * @return
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   * @throws InvalidAlgorithmParameterException
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   * @throws UnsupportedEncodingException
   * @throws InvalidKeyException
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/admin_create.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String admin_create(HttpServletRequest request, AdminVO adminVO)
      throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    System.out.println("admin_create!");
    JSONObject obj = new JSONObject();

    // 관리자 마스터 권한 검사 (관리자 처음가입시 마스터 권한 부여)
    int cnt_master = adminProc.check_master(adminVO.getAdmauth());
    String admauth = adminVO.getAdmauth();
    if (cnt_master == 0) {
      admauth = "M";
      adminVO.setAdmauth(admauth);
    }

    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();
    String admpasswd = adminVO.getAdmpasswd();
    admpasswd = aes256.aesEncode(admpasswd);
    adminVO.setAdmpasswd(admpasswd);
    System.out.println("암호화 패스워드: " + admpasswd);

    // 중복 아이디, 이메일 검사
    int cnt_id = adminProc.check_admid(adminVO.getAdmid());
    int cnt_email = adminProc.check_admemail(adminVO.getAdmemail());
    int join_cnt = 0;

    // 회원 가입 처리
    if (adminProc.admin_create(adminVO) == 1) {
      join_cnt = 1;
    } else {
      join_cnt = 2;
    }

  obj.put("join_cnt", join_cnt);
  obj.put("cnt_id", cnt_id);
  obj.put("cnt_email", cnt_email);

  return obj.toString();
  }

  /**
   * 관리자 아이디 중복 검사
   * 
   * @param admid
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/check_admid.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String check_admid(String admid) {
    JSONObject obj = new JSONObject();

    int cnt_admid = adminProc.check_admid(admid);
    obj.put("cnt_admid", cnt_admid);

    return obj.toJSONString();
  }

  /**
   * 관리자 이메일 중복 검사
   * 
   * @param admemail
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login//check_admemail.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String check_admemail(String admemail) {
    JSONObject obj = new JSONObject();

    int cnt_admemail = adminProc.check_admemail(admemail);
    obj.put("cnt_admemail", cnt_admemail);

    return obj.toJSONString();
  }

  /**
   * 인증 메일 보내기
   * 
   * @param tomail
   * @param title
   * @param content
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/admin_mailSending.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String mailSending(String tomail, String title, String content) { // HttpServletRequest
                                                                           // request
    JSONObject obj = new JSONObject();
    // System.out.println("Cont tomail: " + tomail);
    // System.out.println("Cont title: " + title);
    String code = adminProc.key();

    String codesend = "";

    String setfrom = "jukseolhwa@gmail.com";
    // String tomail = request.getParameter("tomail"); // 받는 사람 이메일
    // String title = request.getParameter("title"); // 제목
    // String content = request.getParameter("content"); // 내용
    content = content.replace("<br>", "\r\n");
    content += code;
    System.out.println("Cont content: " + content);

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

      messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
      messageHelper.setTo(tomail); // 받는사람 이메일
      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
      messageHelper.setText(content); // 메일 내용

      mailSender.send(message);

      codesend = "성공";
      obj.put("codesend", codesend);
      obj.put("code", code);
    } catch (Exception e) {
      System.out.println(e);
    }

    return obj.toJSONString();
  }

  /**
   * 관리자 목록
   * 
   * @return
   */
  @RequestMapping(value = "/master/admin_list.do", method = RequestMethod.GET)
  public ModelAndView admin_list(AdminVO adminVO) {
    // System.out.println("--> admin_list() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/master/admin_list"); //

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    String word = adminVO.getWord();
    String search = adminVO.getSearch();
    int nowPage = adminVO.getNowPage();

    word = Tool.checkNull(word);

/*    System.out.println("word:" + word);
    System.out.println("search:" + search);
    System.out.println("nowPage:" + nowPage);*/

    hashMap.put("word", word);
    hashMap.put("search", search);
    hashMap.put("nowPage", nowPage);

    List<AdminVO> admin_list = adminProc.admin_list(hashMap);
    mav.addObject("admin_list", admin_list);

    int search_count = adminProc.search_count(hashMap); // 검색 레코드 갯수
    mav.addObject("search_count", search_count);
    //System.out.println("search_count: " + search_count);

    String paging = adminProc.paging(search_count, nowPage, word, search);
    mav.addObject("paging", paging);

    return mav;
  }

  /**
   * 관리자 정보 조회
   * 
   * @param adminno
   * @return
   */
  @RequestMapping(value = "/admin/admin/admin_read.do", method = RequestMethod.GET)
  public ModelAndView admin_read(int adminno) {
    // System.out.println("--> admin_read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/admin/admin_read"); // webapp/member/mem_read.jsp

    AdminVO adminVO = adminProc.admin_read(adminno);
    mav.addObject("adminVO", adminVO);

    return mav;
  }

  /**
   * 관리자 패스워드 검사
   * 
   * @param request
   * @param admid
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
  @RequestMapping(value = "/admin/admin/admin_read_pwdcheck.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String admin_read_pwdcheck(String modal_pwd, AdminVO adminVO, HttpSession session)
      throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    JSONObject obj = new JSONObject();
    int adminno = (Integer) session.getAttribute("adminno");

    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();
    modal_pwd = aes256.aesEncode(modal_pwd); // 리드에서 수정넘어갈 때 패스워드 검사 모달창에 입력된 패스워드 암호화 변환
    
    adminVO = adminProc.admin_read(adminno);
    String admpasswd = adminVO.getAdmpasswd();
    System.out.println("패스워드검사 admpasswd: " + admpasswd);
    int cnt_admpasswd = 0;
    if (modal_pwd.equals(admpasswd) == true) {
      cnt_admpasswd = 1;
    } else {
      cnt_admpasswd = 2;
    }
    obj.put("cnt_admpasswd", cnt_admpasswd);

    return obj.toJSONString();
  }

  /**
   * 관리자 정보 조회[마스터용]
   * 
   * @param adminno
   * @return
   */
  @RequestMapping(value = "/master/admin_read_master.do", method = RequestMethod.GET)
  public ModelAndView admin_read_master(int adminno) {
    // System.out.println("--> admin_read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/master/admin_read_master"); // webapp/member/mem_read.jsp

    AdminVO adminVO = adminProc.admin_read(adminno);
    mav.addObject("adminVO", adminVO);

    return mav;
  }

  /**
   * 관리자 정보 수정
   * 
   * @param adminno
   * @return
   */
  @RequestMapping(value = "/admin/admin/admin_update.do", method = RequestMethod.GET)
  public ModelAndView admin_update(int adminno) {
    // System.out.println("--> admin_update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/admin/admin_update");

    AdminVO adminVO = adminProc.admin_read(adminno);

    mav.addObject("adminVO", adminVO);
    return mav;
  }

  /**
   * 관리자 정보 수정 처리
   * 
   * @param request
   * @param adminVO
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
  @RequestMapping(value = "/admin/admin/admin_update.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String admin_update(HttpServletRequest request, HttpSession session, AdminVO adminVO)
      throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    // System.out.println("--> admin_update() POST called.");
    JSONObject obj = new JSONObject();
    String root = request.getContextPath();
    AdminVO adminVO_old = adminProc.admin_read(adminVO.getAdminno());

    String admpasswd = adminVO.getAdmpasswd();
    System.out.println("admpasswd: " + admpasswd);
    
    String admauth = (String) session.getAttribute("admauth");
    System.out.println("admauth: " + admauth);
    
    int adminno = (Integer) session.getAttribute("adminno");
    
    // 마스터가 관리자 수정시에
    if(admauth == "M" && adminno != adminVO.getAdminno()) {
      admpasswd = adminVO_old.getAdmpasswd();
      adminVO.setAdmpasswd(admpasswd);
      System.out.println("마스터가 관리자 수정시에 암호: " + admpasswd);
    } else { // 관리자 본인이 수정할때
      admauth = adminVO_old.getAdmauth();
      adminVO.setAdmauth(admauth);
      
      // 패스워드 암호화
      AES256Util aes256 = new AES256Util();
      admpasswd = aes256.aesEncode(admpasswd);
      adminVO.setAdmpasswd(admpasswd);
      System.out.println("업데이트 암호화 패스워드: " + admpasswd);
      
    }

    int update_cnt = 0;
    if (adminProc.admin_update(adminVO) == 1) {
      update_cnt = 1;
    } else {
      update_cnt = 2;
    }
    
    obj.put("update_cnt", update_cnt);

    return obj.toString();

  }

  /**
   * 관리자 로그인 폼
   * 
   * @return
   */
  // http://localhost:9090/study/nonuser/login/admin_login.do
  /*
   * @RequestMapping(value = "/nonuser/login/admin_login.do ", method =
   * RequestMethod.GET) public ModelAndView admin_login(HttpServletRequest
   * request, HttpSession session) {
   * 
   * ModelAndView mav = new ModelAndView();
   * mav.setViewName("/nonuser/login/login_form");
   * 
   * Cookie[] cookies = request.getCookies(); Cookie cookie = null;
   * 
   * String ck_admid = ""; // id 저장 변수 String ck_admid_save = ""; // id 저장 여부를
   * 체크하는 변수 String ck_admpasswd = ""; // passwd 저장 변수 String ck_admpasswd_save
   * = ""; // passwd 저장 여부를 체크하는 변수
   * 
   * if (cookies != null) { for (int i=0; i < cookies.length; i++){ cookie =
   * cookies[i]; // 쿠키 객체 추출
   * 
   * if (cookie.getName().equals("ck_admid")){ ck_admid = cookie.getValue();
   * }else if(cookie.getName().equals("ck_admid_save")){ ck_admid_save =
   * cookie.getValue(); // Y, N }else if
   * (cookie.getName().equals("ck_admpasswd")){ ck_admpasswd =
   * cookie.getValue(); // 1234 }else
   * if(cookie.getName().equals("ck_admpasswd_save")){ ck_admpasswd_save =
   * cookie.getValue(); // Y, N } } }
   * 
   * System.out.println("ck_admid" + ck_admid);
   * System.out.println("ck_admid_save" + ck_admid_save);
   * System.out.println("ck_admpasswd" + ck_admpasswd);
   * System.out.println("ck_admpasswd_save" + ck_admpasswd_save);
   * 
   * mav.addObject("ck_admid", ck_admid); mav.addObject("ck_admid_save",
   * ck_admid_save); mav.addObject("ck_admpasswd", ck_admpasswd);
   * mav.addObject("ck_admpasswd_save", ck_admpasswd_save);
   * 
   * return mav; }
   */

  /**
   * 로그인 처리
   * 
   * @param request
   * @param response
   * @param session
   * @param adminVO
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
  @RequestMapping(value = "/nonuser/login/admin_login.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String admin_login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
      AdminVO adminVO) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException,
      NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    System.out.println("--> admin_login() POST called.");
    System.out.println("adminVO.getAdmid(): " + adminVO.getAdmid());
    System.out.println("adminVO.getAdmpasswd(): " + adminVO.getAdmpasswd());

    JSONObject obj = new JSONObject();
    String root = request.getContextPath();

    String admid = adminVO.getAdmid();
    String input_admpasswd = adminVO.getAdmpasswd();

    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();
    String admpasswd = aes256.aesEncode(input_admpasswd);
    System.out.println("암호화 패스워드: " + admpasswd);

    int adminno = (adminProc.admin_read_id(admid)).getAdminno(); // ID에 해당하는
    String admauth = (adminProc.admin_read_id(admid)).getAdmauth();
    String admname = (adminProc.admin_read_id(admid)).getAdmname();
    
    int login_cnt = 0;
    if(admauth.equals("M") == true || admauth.equals("A") == true) {
      if (adminProc.admin_login(admid, admpasswd) != 1) {
        login_cnt = 2;
  
      } else { // 패스워드 일치하는 경우
        login_cnt = 1;
        String login_time = Tool.getDate2();
  
        System.out.println("admid :" + admid);
        System.out.println("admpasswd :" + admpasswd);
        System.out.println("admauth :" + admauth);
        System.out.println("admname :" + admname);
  
        session.setAttribute("adminno", adminno); // session 내부 객체
        session.setAttribute("admid", admid);
        session.setAttribute("admauth", admauth);
        session.setAttribute("admname", admname);
        session.setAttribute("login_time", login_time);
        // -------------------------------------------------------------------
        // id 관련 쿠기 저장
        // -------------------------------------------------------------------
        String admid_save = Tool.checkNull(adminVO.getAdmid_save());
        if (admid_save.equals("Y")) { // id를 저장할 경우
          Cookie ck_admid = new Cookie("ck_admid", admid);
          ck_admid.setMaxAge(60 * 60 * 72 * 10); // 30 day, 초단위
          response.addCookie(ck_admid);
        } else { // N, id를 저장하지 않는 경우
          Cookie ck_admid = new Cookie("ck_admid", "");
          ck_admid.setMaxAge(0);
          response.addCookie(ck_admid);
        }
        // id를 저장할지 선택하는 CheckBox 체크 여부
        Cookie ck_admid_save = new Cookie("ck_admid_save", admid_save);
        ck_admid_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_admid_save);
        // -------------------------------------------------------------------
  
        // -------------------------------------------------------------------
        // Password 관련 쿠기 저장
        // -------------------------------------------------------------------
        String admpasswd_save = Tool.checkNull(adminVO.getAdmpasswd_save());
        if (admpasswd_save.equals("Y")) { // 패스워드 저장할 경우
          Cookie ck_admpasswd = new Cookie("ck_admpasswd", input_admpasswd);
          ck_admpasswd.setMaxAge(60 * 60 * 72 * 10); // 30 day
          response.addCookie(ck_admpasswd);
        } else { // N, 패스워드를 저장하지 않을 경우
          Cookie ck_admpasswd = new Cookie("ck_admpasswd", "");
          ck_admpasswd.setMaxAge(0);
          response.addCookie(ck_admpasswd);
        }
        // passwd를 저장할지 선택하는 CheckBox 체크 여부
        Cookie ck_admpasswd_save = new Cookie("ck_admpasswd_save", admpasswd_save);
        ck_admpasswd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_admpasswd_save);
        // -------------------------------------------------------------------
  
      }
    } else { // 관리자 권한 M, A가 아닌경우
      login_cnt = 3;
    }

    obj.put("login_cnt", login_cnt);

    return obj.toString();
  }

  /**
   * 로그아웃 처리
   * 
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value = "/nonuser/login/admin_logout.do", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, HttpSession session) {
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    String root = request.getContextPath();

    mav.setViewName("redirect:/main/index.do"); // 확장자 명시

    session.removeAttribute("adminno");
    session.removeAttribute("admid");
    session.removeAttribute("admauth");
    session.removeAttribute("admname");
    session.removeAttribute("login_time");

    // session.invalidate(); // session 내부 객체의 등록된 모든 session 변수 삭제

    return mav;
  }

  /**
   * 아이디/비번 찾기 폼
   * 
   * @return
   */
  @RequestMapping(value = "/nonuser/login/admin_id_pwd_find.do", method = RequestMethod.GET)
  public ModelAndView admin_id_pwd_find() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/login/admin_id_pwd_find");

    return mav;
  }

  /**
   * 아이디 찾기
   * 
   * @param memname
   * @param mememail
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/find_admid.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String find_admid(String admname, String admemail) {
    JSONObject obj = new JSONObject();
    // System.out.println("find_admid created");

    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    if (admname == null || admname == "" || admemail == null || admemail == "") {
      obj.put("admid", "none");
    } else {
      hashMap.put("admname", admname);
      hashMap.put("admemail", admemail);

      String admid = adminProc.find_admid(hashMap);
      
      if(admid == null || admid == "") {
        obj.put("admid", "none");
      } else {
        obj.put("admid", admid);
        // System.out.println("memid: " + memid);
      }
      
    }

    return obj.toJSONString();
  }

  /**
   * 비밀번호 찾기
   * 
   * @param admname
   * @param admemail
   * @param admid
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/login/find_admpasswd.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String find_admpasswd(String admname, String admemail, String admid) {
    JSONObject obj = new JSONObject();
    System.out.println("find_admpasswd created");

    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    if (admid == null || admid == "" || admname == null || admname == "" || admemail == null || admemail == "") {
      obj.put("adminno", "none");
    } else {
      hashMap.put("admid", admid);
      hashMap.put("admname", admname);
      hashMap.put("admemail", admemail);

      String adminno = adminProc.find_admpasswd(hashMap);

      if (adminno == null || adminno == "") {
        obj.put("adminno", "none");
      } else {
        obj.put("adminno", adminno);
      }
    }

    return obj.toJSONString();
  }

  /**
   * 비밀번호 변경
   * 
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
  @RequestMapping(value = "/nonuser/login/admpasswd_change.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String admpasswd_change(HttpServletRequest request, AdminVO adminVO, String admpasswd, int adminno)
      throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    String root = request.getContextPath();
    JSONObject obj = new JSONObject();

    adminVO = adminProc.admin_read(adminno);
    adminVO.setAdmpasswd(admpasswd);

    // 패스워드 암호화
    AES256Util aes256 = new AES256Util();
    admpasswd = adminVO.getAdmpasswd();
    System.out.println("암호화전 패스워드: " + admpasswd);
    admpasswd = aes256.aesEncode(admpasswd);
    adminVO.setAdmpasswd(admpasswd);
    System.out.println("업데이트 암호화 패스워드: " + admpasswd);

    if (adminProc.admpasswd_change(adminVO) == 1) {
      obj.put("success", "success");

    } else {
      obj.put("fail", "fail");
    }

    return obj.toJSONString();

  }
  
  
  /**
   * 마스터용 관리자 삭제
   * @param adminno
   * @param adminVO
   * @param session
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
  @RequestMapping(value = "/master/admin/admin_delete.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String admin_delete(int adminno, AdminVO adminVO, HttpSession session)
      throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    // System.out.println("admin_delete get!");
    JSONObject obj = new JSONObject();
    
    String delete_email = adminProc.key(); // 관리자 삭제시 이메일 랜덤키로 변환
    
    //System.out.println("관리자번호: " + adminno);
    adminVO = adminProc.admin_read(adminno);
    
    String admauth = (String) session.getAttribute("admauth");
    //System.out.println("admauth: " + admauth);
    
    int cnt_withdraw = 0; // 탈퇴 성공 숫자 1, 실패 2
    
    // 마스터가 관리자 삭제시에
    if(admauth.equals("M") == true) {
      adminVO.setAdmemail(delete_email);
      adminVO.setAdmauth("N");
      if(adminProc.admin_delete(adminVO) == 1) { 
        cnt_withdraw = 1;
      } else {
        cnt_withdraw = 2;
      }
      
    } else { // 마스터가 아닐시
      cnt_withdraw = 2;
    }
    
    obj.put("cnt_withdraw", cnt_withdraw);
    
    return obj.toJSONString();
  }
  

}
