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
   * create form 데이터베이스에 조합해서 저장
   * 
   * @param studyListVO
   * @return
   */
  @RequestMapping(value = "/user/studylist/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpSession session, StudyListVO studyListVO) {
    // System.out.println("--> create() POST executed");

    ModelAndView mav = new ModelAndView();

    // 세션에 대해서 id값에 대한 고유 memberno 값을 가져옴
    int memberno = (Integer) session.getAttribute("memberno");

    /* System.out.println("memberno :"+memberno); */

    // create 실행전 memberno값 VO에 저장
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

    // email + area 조합
    String stdlist_email = studyListVO.getEmail1() + "@" + studyListVO.getEmail2();
    String stdlist_area = studyListVO.getArea() + " " + studyListVO.getSelected_area();

    // 요일 조합
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

    // 값 저장
    studyListVO.setStdlist_email(stdlist_email);
    studyListVO.setStdlist_area(stdlist_area);
    studyListVO.setStdlist_dow(stdlist_dow);

    if (studylistProc.create(studyListVO) == 1) {

      // 저장된 스터디리스트의 가장 큰 스터디 번호 가져옴.
      studyListVO.setStdlist_no(studylistProc.stdlist_no());

      // 스터디리스트 등록과 동시에 신청테이블 생성
      if (recruitProc.create(studyListVO) == 1) {
        // 스터디리스트 리더 권한 변경

        HashMap hashmap = new HashMap();
        hashmap.put("memberno", studyListVO.getMemberno());
        hashmap.put("stdlist_no", studylistProc.stdlist_no());

        recruitProc.leader_auth(hashmap);
        Std_RecomProc.create(hashmap);
        msgs.add("등록 성공");
      }
    } else {
      msgs.add("등록 실패");
      msgs.add("다시 시도 -> 운영팀");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='/study/nonuser/studylist/list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /*
   * 스터디리스트 목록 출력
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
   * 체크박스 검색을 통한 스터디리스트 목록 출력
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
   * select option을 이용한 검색 ajax 스터디리스트 목록 + paging 출력
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

    // 분야
    String gettopic = studyListVO.getTopic();
    // 분야별 선택된 세부 카테고리
    String getselected_topic = studyListVO.getSelected_topic();
    // 검색어
    String word = studyListVO.getWord();
    // 검색어 select option 값
    String search = studyListVO.getSearch();
    // 데이터베이스에 보내는 실제 값
    // gettopic + getselected_topic
    String topic;
    // 페이지의 현재 값
    int nowPage = studyListVO.getNowPage();
    // 검색된 list의 개수
    int search_count;

    // null 체크 검사
    gettopic = Tool.checkNull(gettopic);
    getselected_topic = Tool.checkNull(getselected_topic);
    word = Tool.checkNull(word);

/*    System.out.println("memberno:" + studyListVO.getMemberno());
    System.out.println("topic:" + gettopic);
    System.out.println("selected_topic:" + getselected_topic);
    System.out.println("word:" + word);
    System.out.println("search:" + search);
    System.out.println("nowPage:" + nowPage);*/

    // 분야가 공백이 들어오면 topic을 공백으로
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

    // 검색 + paging
    List<StudyListVO> list = studylistProc.search_list3(hashmap);

    // arraylist 를 JSONArray 로 변환해서 저장
    searchlist = JSONArray.fromObject(list);

    JSONObject obj = new JSONObject();

    // 검색된 list의 개수
    search_count = studylistProc.search_count(hashmap);

    // paging append 값 가져오기
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

    // 지역값 가져오기
    String Stdlist_area = studyListVO.getStdlist_area();
    // 이메일 값 가져오기
    String Stdlist_email = studyListVO.getStdlist_email();
    // 요일 가져오기
    String Stdlist_dow = studyListVO.getStdlist_dow();

    // 지역 나누기
    String[] split = Stdlist_area.split(" ");
    String area = split[0];
    String selected_area = split[1];

    // 이메일 나누기
    String[] split1 = Stdlist_email.split("@");
    String email1 = split1[0];
    String email2 = split1[1];

    // 요일 나누기
    String[] split2 = Stdlist_dow.split("");

    // System.out.println(split2.length);

    for (int i = 0; i < split2.length; i++) {

      if (split2[i].equals("월")) {
        String dow1 = split2[i];
        studyListVO.setDow1(dow1);
      } else if (split2[i].equals("화")) {
        String dow2 = split2[i];
        studyListVO.setDow2(dow2);
      } else if (split2[i].equals("수")) {
        String dow3 = split2[i];
        studyListVO.setDow3(dow3);
      } else if (split2[i].equals("목")) {
        String dow4 = split2[i];
        studyListVO.setDow4(dow4);
      } else if (split2[i].equals("금")) {
        String dow5 = split2[i];
        studyListVO.setDow5(dow5);
      } else if (split2[i].equals("토")) {
        String dow6 = split2[i];
        studyListVO.setDow6(dow6);
      } else if (split2[i].equals("일")) {
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

    // 나누어서 저장
    studyListVO.setArea(area); // 지역 저장
    studyListVO.setSelected_area(selected_area);

    studyListVO.setEmail1(email1); // 이메일 저장
    studyListVO.setEmail2(email2);

    mav.addObject("studyListVO", studyListVO);

    return mav;
  }

  /**
   * 스터디리스트 변경
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

    // email + area 조합
    String stdlist_email = studyListVO.getEmail1() + "@" + studyListVO.getEmail2();
    String stdlist_area = studyListVO.getArea() + " " + studyListVO.getSelected_area();

    // 요일 조합
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

    // 값 저장
    studyListVO.setStdlist_email(stdlist_email);
    studyListVO.setStdlist_area(stdlist_area);
    studyListVO.setStdlist_dow(stdlist_dow);

    if (studylistProc.update(studyListVO) == 1) {
      msgs.add("스터디 모집 변경 성공");
    } else {
      msgs.add("변경 실패");
      msgs.add("다시 시도 -> 운영팀");
      links.add("<button type= 'button' onclick=\"history.back()\">다시 시도</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='/study/nonuser/studylist/list.do'\">목록</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }

  /**
   * 스터디리스트 삭제
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
   * 스터디리스트 ajax를 이용한 댓글출력 + paging
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

    // 스터디 그룹별 댓글을 읽어오기 위한 read의 스터디그룹번호 가져오기
    hashmap.put("stdlist_no", stdlist_no);
    hashmap.put("nowPage", nowPage);

    List<ReplyVO> list = ReplyProc.list2(hashmap);

    // 스터디그룹별 댓글 갯수 가져오기
    int search_count = ReplyProc.search_count(stdlist_no);

    // 페이징처리된 String append 가져오기
    String paging = ReplyProc.paging(search_count, nowPage, stdlist_no);

    // 리스트를 json 배열에 저장
    JSONArray reply = JSONArray.fromObject(list);

    obj.put("reply", reply); // 스터디그룹별 댓글리스트 저장
    obj.put("search_count", search_count); // 스터디그룹별 댓글 개수
    obj.put("paging", paging); // 페이징 처리
    obj.put("nowPage", nowPage);

    // System.out.println(obj);

    return obj.toString();

  }

  /**
   * 스터디리스트 정보 출력 기본 컨트롤러
   * 
   * @param studyList_memberVO
   * @return
   */
  @RequestMapping(value = "/nonuser/studylist/read.do", method = RequestMethod.GET)
  public ModelAndView read(HttpSession session, StudyList_MemberVO studyList_memberVO) {
    ModelAndView mav = new ModelAndView();

    int stdlist_no = studyList_memberVO.getStdlist_no();

    // 로그인을 한 상태라면
    if (session.getAttribute("memberno") != null) {

      HashMap hashmap = new HashMap();

      // 세션을 통해 memberno 값 가져옴
      int memberno = (Integer) session.getAttribute("memberno");

      hashmap.put("memberno", memberno);
      hashmap.put("stdlist_no", stdlist_no);

      // std_recom 테이블에 이미 입력된 회원인지 확인
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
   * 스터디리스트 ajax를 이용한 출력
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
   * 스터디리스트 ajax를 이용한 댓글출력 + paging
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
