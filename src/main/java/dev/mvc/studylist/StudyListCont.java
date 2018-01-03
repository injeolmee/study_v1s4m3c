package dev.mvc.studylist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.recruit.RecruitProcInter;
import dev.mvc.std_recom.Std_RecomProcInter;
import dev.mvc.studylist_reply.ReplyProcInter;
import dev.mvc.studylist_reply.ReplyVO;
import nation.web.tool.Tool;

@Controller
public class StudyListCont {

  @Autowired
  @Qualifier("dev.mvc.studylist.StudyListProc")
  private StudyListProcInter studylistProc = null;

  @Autowired
  @Qualifier("dev.mvc.recruit.RecruitProc")
  private RecruitProcInter recruitProc = null;

  @Autowired
  @Qualifier("dev.mvc.studylist_reply.ReplyProc")
  private ReplyProcInter ReplyProc = null;

  @Autowired
  @Qualifier("dev.mvc.std_recom.Std_RecomProc")
  private Std_RecomProcInter Std_RecomProc = null;

  public StudyListCont() {
    // System.out.println("-->StudyListCont created");
  }

  /**
   * create form
   * 
   * @return
   */
  // http://localhost:9090/study/studylist/create.do
  @RequestMapping(value = "/user/studylist/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    // System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/studylist/studycreate"); // webapp/studylist/create.jsp

    return mav;
  }

  /**
   * create form �����ͺ��̽��� �����ؼ� ����
   * 
   * @param studyListVO
   * @return
   */
  @RequestMapping(value = "/user/studylist/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, StudyListVO studyListVO) {
    // System.out.println("--> create() POST executed");

    ModelAndView mav = new ModelAndView();

    // ���ǿ� ���ؼ� id���� ���� ���� memberno ���� ������
    int memberno = (Integer) session.getAttribute("memberno");

    /* System.out.println("memberno :"+memberno); */

    // create ������ memberno�� VO�� ����
    studyListVO.setMemberno(memberno);

    mav.setViewName("/user/studylist/message"); // webapp/category/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    /*
     * System.out.println("stdlist_topic :"+studyListVO.getStdlist_topic());
     * System.out.println("AREA :"+studyListVO.getArea());
     * System.out.println("selected_area :"+studyListVO.getSelected_area());
     * System.out.println("EMAIL1 :"+studyListVO.getEmail1());
     * System.out.println("EMAIL2 :"+studyListVO.getEmail2());
     */

    // email + area ����
    String stdlist_email = studyListVO.getEmail1() + "@" + studyListVO.getEmail2();
    String stdlist_area = studyListVO.getArea() + " " + studyListVO.getSelected_area();

    // ���� ����
    String stdlist_dow = "";
    String dow1 = studyListVO.getDow1();
    String dow2 = studyListVO.getDow2();
    String dow3 = studyListVO.getDow3();
    String dow4 = studyListVO.getDow4();
    String dow5 = studyListVO.getDow5();
    String dow6 = studyListVO.getDow6();
    String dow7 = studyListVO.getDow7();

    if (dow1 != null) {
      stdlist_dow += dow1;
    }
    if (dow2 != null) {
      stdlist_dow += dow2;
    }
    if (dow3 != null) {
      stdlist_dow += dow3;
    }
    if (dow4 != null) {
      stdlist_dow += dow4;
    }
    if (dow5 != null) {
      stdlist_dow += dow5;
    }
    if (dow6 != null) {
      stdlist_dow += dow6;
    }
    if (dow7 != null) {
      stdlist_dow += dow7;
    }

    // �� ����
    studyListVO.setStdlist_email(stdlist_email);
    studyListVO.setStdlist_area(stdlist_area);
    studyListVO.setStdlist_dow(stdlist_dow);

    if (studylistProc.create(studyListVO) == 1) {

      // ����� ���͵𸮽�Ʈ�� ���� ū ���͵� ��ȣ ������.
      studyListVO.setStdlist_no(studylistProc.stdlist_no());

      // ���͵𸮽�Ʈ ��ϰ� ���ÿ� ��û���̺� ����
      if (recruitProc.create(studyListVO) == 1) {
        // ���͵𸮽�Ʈ ���� ���� ����

        HashMap hashmap = new HashMap();
        hashmap.put("memberno", studyListVO.getMemberno());
        hashmap.put("stdlist_no", studylistProc.stdlist_no());

        recruitProc.leader_auth(hashmap);
        Std_RecomProc.create(hashmap);
        msgs.add("��� ����");
      }
    } else {
      msgs.add("��� ����");
      msgs.add("�ٽ� �õ� -> ���");
      links.add("<button type= 'button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='/study/nonuser/studylist/list.do'\">���</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /*
   * ���͵𸮽�Ʈ ��� ���
   * 
   * @return
   */
  // http://localhost:9090/blog/studylist/list.do
  @RequestMapping(value = "/nonuser/studylist/list.do", method = RequestMethod.GET)
  public ModelAndView list() {

    // System.out.println("--> list() GET executed");

    ModelAndView mav = new ModelAndView();

    mav.setViewName("/nonuser/studylist/study_list4"); // studylist/study_list.jsp

    /*
     * List<StudyListVO> list = studylistProc.list();
     * 
     * mav.addObject("list",list);
     */

    return mav;
  }

  /**
   * üũ�ڽ� �˻��� ���� ���͵𸮽�Ʈ ��� ���
   * 
   * @return String
   */
  // http://localhost:9090/blog/studylist/list.do
  @ResponseBody
  @RequestMapping(value = "/nonuser/studylist/listajax.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String search_list(StudyListVO studyListVO) {
    // System.out.println("--> search_list() GET executed");

    JSONArray searchlist = new JSONArray();

    HashMap<String, Object> hashmap = new HashMap<String, Object>();
    hashmap.put("stdlist_topic", studyListVO.getStdlist_topic());
    hashmap.put("stdlist_area", studyListVO.getStdlist_area());
    hashmap.put("stdlist_dow", studyListVO.getStdlist_dow());

    List<StudyListVO> list = studylistProc.search_list1(hashmap);

    searchlist = JSONArray.fromObject(list);

    return searchlist.toString();
  }

  /**
   * select option�� �̿��� �˻� ajax ���͵𸮽�Ʈ ��� + paging ���
   * 
   * @return String
   */
  // http://localhost:9090/blog/studylist/search.do
  @ResponseBody
  @RequestMapping(value = "/nonuser/studylist/search.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String search_list2(StudyListVO studyListVO) {
    // System.out.println("--> search_list() GET executed");

    JSONArray searchlist = new JSONArray();

    HashMap<String, Object> hashmap = new HashMap<String, Object>();

    // �о�
    String gettopic = studyListVO.getTopic();
    // �оߺ� ���õ� ���� ī�װ�
    String getselected_topic = studyListVO.getSelected_topic();
    // �˻���
    String word = studyListVO.getWord();
    // �˻��� select option ��
    String search = studyListVO.getSearch();
    // �����ͺ��̽��� ������ ���� ��
    // gettopic + getselected_topic
    String topic;
    // �������� ���� ��
    int nowPage = studyListVO.getNowPage();
    // �˻��� list�� ����
    int search_count;

    // null üũ �˻�
    gettopic = Tool.checkNull(gettopic);
    getselected_topic = Tool.checkNull(getselected_topic);
    word = Tool.checkNull(word);

/*    System.out.println("memberno:" + studyListVO.getMemberno());
    System.out.println("topic:" + gettopic);
    System.out.println("selected_topic:" + getselected_topic);
    System.out.println("word:" + word);
    System.out.println("search:" + search);
    System.out.println("nowPage:" + nowPage);*/

    // �о߰� ������ ������ topic�� ��������
    if (gettopic == "") {
      topic = "";
    } else {
      topic = gettopic + "/" + getselected_topic;
    }

    // System.out.println(topic);

    hashmap.put("topic", topic);
    hashmap.put("word", word);
    hashmap.put("search", search);
    hashmap.put("nowPage", nowPage);

    // �˻� + paging
    List<StudyListVO> list = studylistProc.search_list3(hashmap);

    // arraylist �� JSONArray �� ��ȯ�ؼ� ����
    searchlist = JSONArray.fromObject(list);

    JSONObject obj = new JSONObject();

    // �˻��� list�� ����
    search_count = studylistProc.search_count(hashmap);

    // paging append �� ��������
    String paging = studylistProc.paging(search_count, nowPage);

    obj.put("paging", paging);
    obj.put("search_count", search_count);
    obj.put("searchlist", searchlist);

    // System.out.println(obj);

    return obj.toString();
  }

  /**
   * update form
   * 
   * @return
   */
  // http://localhost:9090/study/studylist/update.do
  @RequestMapping(value = "/user/studylist/update.do", method = RequestMethod.GET)
  public ModelAndView update(int stdlist_no) {
    // System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/studylist/studyupdate"); // webapp/studylist/update.jsp

    StudyListVO studyListVO = studylistProc.read_std(stdlist_no);

    // ������ ��������
    String Stdlist_area = studyListVO.getStdlist_area();
    // �̸��� �� ��������
    String Stdlist_email = studyListVO.getStdlist_email();
    // ���� ��������
    String Stdlist_dow = studyListVO.getStdlist_dow();

    // ���� ������
    String[] split = Stdlist_area.split(" ");
    String area = split[0];
    String selected_area = split[1];

    // �̸��� ������
    String[] split1 = Stdlist_email.split("@");
    String email1 = split1[0];
    String email2 = split1[1];

    // ���� ������
    String[] split2 = Stdlist_dow.split("");

    // System.out.println(split2.length);

    for (int i = 0; i < split2.length; i++) {

      if (split2[i].equals("��")) {
        String dow1 = split2[i];
        studyListVO.setDow1(dow1);
      } else if (split2[i].equals("ȭ")) {
        String dow2 = split2[i];
        studyListVO.setDow2(dow2);
      } else if (split2[i].equals("��")) {
        String dow3 = split2[i];
        studyListVO.setDow3(dow3);
      } else if (split2[i].equals("��")) {
        String dow4 = split2[i];
        studyListVO.setDow4(dow4);
      } else if (split2[i].equals("��")) {
        String dow5 = split2[i];
        studyListVO.setDow5(dow5);
      } else if (split2[i].equals("��")) {
        String dow6 = split2[i];
        studyListVO.setDow6(dow6);
      } else if (split2[i].equals("��")) {
        String dow7 = split2[i];
        studyListVO.setDow7(dow7);
      }

    }
    ;

/*    System.out.println(studyListVO.getDow1());
    System.out.println(studyListVO.getDow2());
    System.out.println(studyListVO.getDow3());
    System.out.println(studyListVO.getDow4());
    System.out.println(studyListVO.getDow5());
    System.out.println(studyListVO.getDow6());
    System.out.println(studyListVO.getDow7());*/

    // ����� ����
    studyListVO.setArea(area); // ���� ����
    studyListVO.setSelected_area(selected_area);

    studyListVO.setEmail1(email1); // �̸��� ����
    studyListVO.setEmail2(email2);

    mav.addObject("studyListVO", studyListVO);

    return mav;
  }

  /**
   * ���͵𸮽�Ʈ ����
   * 
   * @param studyListVO
   * @return
   */
  @RequestMapping(value = "/user/studylist/update.do", method = RequestMethod.POST)
  public ModelAndView update(StudyListVO studyListVO) {
    // System.out.println("--> update() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/studylist/message"); // webapp/categrp/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    // email + area ����
    String stdlist_email = studyListVO.getEmail1() + "@" + studyListVO.getEmail2();
    String stdlist_area = studyListVO.getArea() + " " + studyListVO.getSelected_area();

    // ���� ����
    String stdlist_dow = "";
    String dow1 = studyListVO.getDow1();
    String dow2 = studyListVO.getDow2();
    String dow3 = studyListVO.getDow3();
    String dow4 = studyListVO.getDow4();
    String dow5 = studyListVO.getDow5();
    String dow6 = studyListVO.getDow6();
    String dow7 = studyListVO.getDow7();

    if (dow1 != null) {
      stdlist_dow += dow1;
    }
    if (dow2 != null) {
      stdlist_dow += dow2;
    }
    if (dow3 != null) {
      stdlist_dow += dow3;
    }
    if (dow4 != null) {
      stdlist_dow += dow4;
    }
    if (dow5 != null) {
      stdlist_dow += dow5;
    }
    if (dow6 != null) {
      stdlist_dow += dow6;
    }
    if (dow7 != null) {
      stdlist_dow += dow7;
    }

    // �� ����
    studyListVO.setStdlist_email(stdlist_email);
    studyListVO.setStdlist_area(stdlist_area);
    studyListVO.setStdlist_dow(stdlist_dow);

    if (studylistProc.update(studyListVO) == 1) {
      msgs.add("���͵� ���� ���� ����");
    } else {
      msgs.add("���� ����");
      msgs.add("�ٽ� �õ� -> ���");
      links.add("<button type= 'button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='/study/nonuser/studylist/list.do'\">���</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * ���͵𸮽�Ʈ ����
   * 
   * @param stdlist_no
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/studylist/delete.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String delete(int stdlist_no) {
    // System.out.println("--> delete() POST executed");

    JSONObject obj = new JSONObject();

    recruitProc.delete(stdlist_no);
    ReplyProc.delete_all(stdlist_no);
    Std_RecomProc.delete(stdlist_no);
    
    int count = studylistProc.delete(stdlist_no);

    obj.put("count", count);

    return obj.toString();
  }

  /**
   * ���͵𸮽�Ʈ ajax�� �̿��� ������ + paging
   * 
   * @param studyList_memberVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/studylist/ajaxread1.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String ajaxread1(StudyListVO studyListVO) {
    // System.out.println("--> ajaxread1() executed");

    JSONObject obj = new JSONObject();

    int stdlist_no = studyListVO.getStdlist_no();
    int nowPage = studyListVO.getNowPage();

    HashMap hashmap = new HashMap();

    // ���͵� �׷캰 ����� �о���� ���� read�� ���͵�׷��ȣ ��������
    hashmap.put("stdlist_no", stdlist_no);
    hashmap.put("nowPage", nowPage);

    List<ReplyVO> list = ReplyProc.list2(hashmap);

    // ���͵�׷캰 ��� ���� ��������
    int search_count = ReplyProc.search_count(stdlist_no);

    // ����¡ó���� String append ��������
    String paging = ReplyProc.paging(search_count, nowPage, stdlist_no);

    // ����Ʈ�� json �迭�� ����
    JSONArray reply = JSONArray.fromObject(list);

    obj.put("reply", reply); // ���͵�׷캰 ��۸���Ʈ ����
    obj.put("search_count", search_count); // ���͵�׷캰 ��� ����
    obj.put("paging", paging); // ����¡ ó��
    obj.put("nowPage", nowPage);

    // System.out.println(obj);

    return obj.toString();

  }

  /**
   * ���͵𸮽�Ʈ ���� ��� �⺻ ��Ʈ�ѷ�
   * 
   * @param studyList_memberVO
   * @return
   */
  @RequestMapping(value = "/nonuser/studylist/read.do", method = RequestMethod.GET)
  public ModelAndView read(HttpSession session, StudyList_MemberVO studyList_memberVO) {
    ModelAndView mav = new ModelAndView();

    int stdlist_no = studyList_memberVO.getStdlist_no();

    // �α����� �� ���¶��
    if (session.getAttribute("memberno") != null) {

      HashMap hashmap = new HashMap();

      // ������ ���� memberno �� ������
      int memberno = (Integer) session.getAttribute("memberno");

      hashmap.put("memberno", memberno);
      hashmap.put("stdlist_no", stdlist_no);

      // std_recom ���̺� �̹� �Էµ� ȸ������ Ȯ��
      if (Std_RecomProc.std_recom_check(hashmap) == 0) {

        Std_RecomProc.create(hashmap);
        
      }
    }

    /*
     * studyList_memberVO.setMemberno(studyList_memberVO.getMemberno());
     * studyList_memberVO.setStdlist_no(studyList_memberVO.getStdlist_no());
     */

    mav.setViewName("/nonuser/studylist/study_read"); // webapp/categrp/message.jsp

    /*
     * System.out.println("Memberno:"+studyList_memberVO.getMemberno());
     * System.out.println("Stdlist_no:"+studyList_memberVO.getStdlist_no());
     */

    StudyList_MemberVO readVO = studylistProc.read(studyList_memberVO);

    /*
     * System.out.println("Memberno:"+readVO.getMemberno());
     * System.out.println("Stdlist_no:"+readVO.getStdlist_no());
     */

    studylistProc.up_cnt(stdlist_no);

    mav.addObject("readVO", readVO);

    return mav;
  }

  /**
   * ���͵𸮽�Ʈ ajax�� �̿��� ���
   * 
   * @param studyList_memberVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/studylist/ajaxread2.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String ajaxread2(StudyList_MemberVO studyList_memberVO) {
    // System.out.println("--> ajaxread2() executed");

    JSONObject obj = new JSONObject();

    StudyList_MemberVO readVO = studylistProc.read(studyList_memberVO);

    studylistProc.up_cnt(readVO.getStdlist_no());

    int stdlist_no = readVO.getStdlist_no();
    int memberno = readVO.getMemberno();
    String stdlist_title = readVO.getStdlist_title();
    String stdlist_topic = readVO.getStdlist_topic();
    String stdlist_area = readVO.getStdlist_area();
    String stdlist_time = readVO.getStdlist_time();
    String stdlist_content = readVO.getStdlist_content();
    String memname = readVO.getMemname();
    String mememail = readVO.getMememail();
    String memphone = readVO.getMemphone();

    obj.put("stdlist_no", stdlist_no);
    obj.put("memberno", memberno);
    obj.put("stdlist_title", stdlist_title);
    obj.put("stdlist_topic", stdlist_topic);
    obj.put("stdlist_area", stdlist_area);
    obj.put("stdlist_time", stdlist_time);
    obj.put("stdlist_content", stdlist_content);
    obj.put("memname", memname);
    obj.put("mememail", mememail);
    obj.put("memphone", memphone);

    /* System.out.println(obj); */

    return obj.toJSONString();

  }

  
  /**
   * ���͵𸮽�Ʈ ajax�� �̿��� ������ + paging
   * 
   * @param studyList_memberVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/nonuser/studylist/rank_top5.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String rank_top5() {
    // System.out.println("top_5()");
    
    List<StudyListVO> list =  studylistProc.rank_top5();
    
    JSONArray LIST = JSONArray.fromObject(list);
    
    // System.out.println(LIST);
    
    return LIST.toString();
   
  }
}
