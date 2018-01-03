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
   * ������ ���
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
   * ������ ��� ó��
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
  @RequestMapping(value = "/nonuser/login/admin_create.do", method = RequestMethod.POST)
  public ModelAndView admin_create(HttpServletRequest request, AdminVO adminVO)
      throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    String root = request.getContextPath();

    ModelAndView mav = new ModelAndView();
    mav.setViewName("nonuser/login/message"); // webapp/admin/admin/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    // ������ ������ ���� �˻� (������ ó�����Խ� ������ ���� �ο�)
    int cnt_master = adminProc.check_master(adminVO.getAdmauth());
    String admauth = adminVO.getAdmauth();
    if (cnt_master == 0) {
      admauth = "M";
      adminVO.setAdmauth(admauth);
    }

    // �н����� ��ȣȭ
    AES256Util aes256 = new AES256Util();
    String admpasswd = adminVO.getAdmpasswd();
    admpasswd = aes256.aesEncode(admpasswd);
    adminVO.setAdmpasswd(admpasswd);
    mav.addObject("admpasswd", admpasswd);
    System.out.println("��ȣȭ �н�����: " + admpasswd);

    // �ߺ� ���̵�, �̸��� �˻�
    int cnt_admid = adminProc.check_admid(adminVO.getAdmid());
    int cnt_admemail = adminProc.check_admemail(adminVO.getAdmemail());
    if (cnt_admid > 0 || cnt_admemail > 0) {
      msgs.add("ID�� �̸����� �ߺ��˴ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    } else {
      // ȸ�� ���� ó��
      if (adminProc.admin_create(adminVO) == 1) {
        msgs.add("ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
        links.add("<button type='button'>�α���</button>");

      } else {
        msgs.add("ȸ�� ���Կ� �����߽��ϴ�.");
        links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      }
    }
    links.add("<button type='button' onclick=\"location.href='" + root + "/main/index.do'\">Ȩ������</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * ������ ���̵� �ߺ� �˻�
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
   * ������ �̸��� �ߺ� �˻�
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
   * ���� ���� ������
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
    // String tomail = request.getParameter("tomail"); // �޴� ��� �̸���
    // String title = request.getParameter("title"); // ����
    // String content = request.getParameter("content"); // ����
    content = content.replace("<br>", "\r\n");
    content += code;
    System.out.println("Cont content: " + content);

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

      messageHelper.setFrom(setfrom); // �����»�� �����ϰų� �ϸ� �����۵��� ����
      messageHelper.setTo(tomail); // �޴»�� �̸���
      messageHelper.setSubject(title); // ���������� ������ �����ϴ�
      messageHelper.setText(content); // ���� ����

      mailSender.send(message);

      codesend = "����";
      obj.put("codesend", codesend);
      obj.put("code", code);
    } catch (Exception e) {
      System.out.println(e);
    }

    return obj.toJSONString();
  }

  /**
   * ������ ���
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

    System.out.println("word:" + word);
    System.out.println("search:" + search);
    System.out.println("nowPage:" + nowPage);

    hashMap.put("word", word);
    hashMap.put("search", search);
    hashMap.put("nowPage", nowPage);

    List<AdminVO> admin_list = adminProc.admin_list(hashMap);
    mav.addObject("admin_list", admin_list);

    int search_count = adminProc.search_count(hashMap); // �˻� ���ڵ� ����
    mav.addObject("search_count", search_count);
    System.out.println("search_count: " + search_count);

    String paging = adminProc.paging(search_count, nowPage, word, search);
    mav.addObject("paging", paging);

    return mav;
  }

  /**
   * ������ ���� ��ȸ
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
   * ������ �н����� �˻�
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
  public String admin_read_pwdcheck(String modal_pwd, HttpServletRequest request)
      throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    JSONObject obj = new JSONObject();
    HttpSession session = request.getSession(false);

    // �н����� ��ȣȭ
    AES256Util aes256 = new AES256Util();
    modal_pwd = aes256.aesEncode(modal_pwd); // ���忡�� �����Ѿ �� �н����� �˻� ���â�� �Էµ�
                                             // �н����� ��ȣȭ ��ȯ
    String admpasswd = (String) session.getAttribute("admpasswd"); // ���ǿ� �����
                                                                   // ��ȣȭ�� �н�����
    System.out.println("�н�����˻� admpasswd: " + admpasswd);
    int cnt_admpasswd = 0;
    if (modal_pwd.equals(admpasswd) == true) {
      cnt_admpasswd = 1;
    }
    obj.put("cnt_admpasswd", cnt_admpasswd);

    return obj.toJSONString();
  }

  /**
   * ������ ���� ��ȸ[�����Ϳ�]
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
   * ������ ���� ����
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
   * ������ ���� ���� ó��
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
  @RequestMapping(value = "/admin/admin/admin_update.do", method = RequestMethod.POST)
  public ModelAndView admin_update(HttpServletRequest request, AdminVO adminVO)
      throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
      InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    // System.out.println("--> admin_update() POST called.");
    String root = request.getContextPath();

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/admin/message");

    // �н����� ��ȣȭ
    AES256Util aes256 = new AES256Util();
    String admpasswd = adminVO.getAdmpasswd();
    admpasswd = aes256.aesEncode(admpasswd);
    adminVO.setAdmpasswd(admpasswd);
    mav.addObject("admpasswd", admpasswd);
    System.out.println("������Ʈ ��ȣȭ �н�����: " + admpasswd);

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    System.out.println(adminVO.getAdmauth());

    if (adminProc.admin_update(adminVO) == 1) {
      mav.setViewName("redirect:/master/admin_read_master.do?adminno=" + adminVO.getAdminno());
    } else {
      msgs.add("ȸ�� ������ �����߽��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");

    }

    links.add("<button type='button' onclick=\"location.href='" + root + "/main/index.do'\">Ȩ������</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * ������ �α��� ��
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
   * String ck_admid = ""; // id ���� ���� String ck_admid_save = ""; // id ���� ���θ�
   * üũ�ϴ� ���� String ck_admpasswd = ""; // passwd ���� ���� String ck_admpasswd_save
   * = ""; // passwd ���� ���θ� üũ�ϴ� ����
   * 
   * if (cookies != null) { for (int i=0; i < cookies.length; i++){ cookie =
   * cookies[i]; // ��Ű ��ü ����
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
   * �α��� ó��
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
  @RequestMapping(value = "/nonuser/login/admin_login.do", method = RequestMethod.POST)
  public ModelAndView admin_login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
      AdminVO adminVO) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException,
      NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    System.out.println("--> admin_login() POST called.");
    System.out.println("adminVO.getAdmid(): " + adminVO.getAdmid());
    System.out.println("adminVO.getAdmpasswd(): " + adminVO.getAdmpasswd());

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/login/message");

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    String root = request.getContextPath();

    String admid = adminVO.getAdmid();
    String input_admpasswd = adminVO.getAdmpasswd();

    // �н����� ��ȣȭ
    AES256Util aes256 = new AES256Util();
    String admpasswd = aes256.aesEncode(input_admpasswd);
    System.out.println("��ȣȭ �н�����: " + admpasswd);

    if (adminProc.admin_login(admid, admpasswd) != 1) {
      msgs.add("���� �н����尡 ��ġ���� �ʽ��ϴ�.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");

    } else { // �н����� ��ġ�ϴ� ���
      int adminno = (adminProc.admin_read_id(admid)).getAdminno(); // ID�� �ش��ϴ�
                                                                   // ��ȣ ����
      String admauth = (adminProc.admin_read_id(admid)).getAdmauth();
      String admname = (adminProc.admin_read_id(admid)).getAdmname();
      System.out.println("admid :" + admid);
      System.out.println("admpasswd :" + admpasswd);
      System.out.println("admauth :" + admauth);
      System.out.println("admname :" + admname);

      session.setAttribute("adminno", adminno); // session ���� ��ü
      session.setAttribute("admid", admid);
      session.setAttribute("admauth", admauth);
      session.setAttribute("admname", admname);
      // -------------------------------------------------------------------
      // id ���� ��� ����
      // -------------------------------------------------------------------
      String admid_save = Tool.checkNull(adminVO.getAdmid_save());
      if (admid_save.equals("Y")) { // id�� ������ ���
        Cookie ck_admid = new Cookie("ck_admid", admid);
        ck_admid.setMaxAge(60 * 60 * 72 * 10); // 30 day, �ʴ���
        response.addCookie(ck_admid);
      } else { // N, id�� �������� �ʴ� ���
        Cookie ck_admid = new Cookie("ck_admid", "");
        ck_admid.setMaxAge(0);
        response.addCookie(ck_admid);
      }
      // id�� �������� �����ϴ� CheckBox üũ ����
      Cookie ck_admid_save = new Cookie("ck_admid_save", admid_save);
      ck_admid_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_admid_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password ���� ��� ����
      // -------------------------------------------------------------------
      String admpasswd_save = Tool.checkNull(adminVO.getAdmpasswd_save());
      if (admpasswd_save.equals("Y")) { // �н����� ������ ���
        Cookie ck_admpasswd = new Cookie("ck_admpasswd", input_admpasswd);
        ck_admpasswd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_admpasswd);
      } else { // N, �н����带 �������� ���� ���
        Cookie ck_admpasswd = new Cookie("ck_admpasswd", "");
        ck_admpasswd.setMaxAge(0);
        response.addCookie(ck_admpasswd);
      }
      // passwd�� �������� �����ϴ� CheckBox üũ ����
      Cookie ck_admpasswd_save = new Cookie("ck_admpasswd_save", admpasswd_save);
      ck_admpasswd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_admpasswd_save);
      // -------------------------------------------------------------------

      mav.setViewName("redirect:/main/index.do"); // Ȯ���� ���

    }

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * �α׾ƿ� ó��
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

    mav.setViewName("redirect:/main/index.do"); // Ȯ���� ���

    session.removeAttribute("adminno");
    session.removeAttribute("admid");
    session.removeAttribute("admauth");
    session.removeAttribute("admname");

    // session.invalidate(); // session ���� ��ü�� ��ϵ� ��� session ���� ����

    return mav;
  }

  /**
   * ���̵�/��� ã�� ��
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
   * ���̵� ã��
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
   * ��й�ȣ ã��
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
   * ��й�ȣ ����
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

    // �н����� ��ȣȭ
    AES256Util aes256 = new AES256Util();
    admpasswd = adminVO.getAdmpasswd();
    System.out.println("��ȣȭ�� �н�����: " + admpasswd);
    admpasswd = aes256.aesEncode(admpasswd);
    adminVO.setAdmpasswd(admpasswd);
    System.out.println("������Ʈ ��ȣȭ �н�����: " + admpasswd);

    if (adminProc.admpasswd_change(adminVO) == 1) {
      obj.put("success", "success");

    } else {
      obj.put("fail", "fail");
    }

    return obj.toJSONString();

  }

}
