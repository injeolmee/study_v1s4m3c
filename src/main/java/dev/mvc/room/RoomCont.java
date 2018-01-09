package dev.mvc.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberProc;
import dev.mvc.rvlike.RvlikeProcInter;
import dev.mvc.review.ReviewVO;
import dev.mvc.review.ReviewProc;
import dev.mvc.review.ReviewProcInter;
import nation.web.tool.Tool;
import nation.web.tool.Upload;
import net.sf.json.JSONArray;

@Controller
public class RoomCont {
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc = null;

  @Autowired
  @Qualifier("dev.mvc.room.RoomProc")
  private RoomProcInter roomProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.rvlike.RvlikeProc")
  private RvlikeProcInter rvlikeProc = null;
  
  /*
   * @Autowired
   * 
   * @Qualifier("dev.mvc.member.MemberProc") private MemberProc memberProc =
   * null;
   */

  public RoomCont() {
    //System.out.println("--> RoomCont created.");
  }

  /*// http://localhost:9090/study/room/create.jsp ��
  // http://localhost:9090/study/room/create.do
  @RequestMapping(value = "/room/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/room/create"); // webapp/room/create.jsp

    return mav;
  }

  @RequestMapping(value = "/room/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, RoomVO roomVO) {
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/room/message"); // webapp/room/message.jsp

    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();

    // ---------------------------------------------------------------
    // �ּ� ���� & ���� ����
    // ---------------------------------------------------------------
    String rolocation = roomVO.getRocity() + " " + roomVO.getRogu() + " " + roomVO.getRodong();
    roomVO.setRolocation(rolocation);
    // ---------------------------------------------------------------
    // �ּ� ���� & ���� ����
    // ---------------------------------------------------------------

    // ---------------------------------------------------------------
    // �ɼ� ���� & ���� ����
    // ---------------------------------------------------------------
    String rooption = "";
    String option1 = roomVO.getOption1();
    String option2 = roomVO.getOption2();
    String option3 = roomVO.getOption3();
    String option4 = roomVO.getOption4();
    String option5 = roomVO.getOption5();

    if (option1 != null) {
      rooption += option1;
    }
    if (option2 != null) {
      rooption += option2;
    }
    if (option3 != null) {
      rooption += option3;
    }
    if (option4 != null) {
      rooption += option4;
    }
    if (option5 != null) {
      rooption += option5;
    }

    roomVO.setRooption(rooption);

    // ---------------------------------------------------------------
    // �ɼ� ���� & ���� ����
    // ---------------------------------------------------------------

    // ---------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // ---------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/room/storage");
    MultipartFile file1MF = roomVO.getFile1MF();
    String rofile1 = ""; // �÷��� ������ ���ϸ�
    Long rosize1 = file1MF.getSize();
    String rothumb = "";

    if (rosize1 > 0) {
      rofile1 = Upload.saveFileSpring(file1MF, upDir);

      if (Tool.isImage(rofile1)) {
        rothumb = Tool.preview(upDir, rofile1, 320, 240);
      }
    }
    roomVO.setRofile1(rofile1);
    roomVO.setRosize1(rosize1);
    roomVO.setRothumb(rothumb);
    // ---------------------------------------------------------------
    // ���� ���� ����
    // ---------------------------------------------------------------

    if (roomProc.create(roomVO) == 1) {
      msgs.add("��� ����");
      msgs.add("���� ����߽��ϴ�.");
    } else {
      msgs.add("��� ����");
      msgs.add("�� ��Ͽ� �����߽��ϴ�.");
      msgs.add("�ٽ� �õ� -> ���");
      links.add("<button type= 'button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    }

    links.add("<button type='button' class='btn btn-default' onclick=\"location.href='./list.do?" + "'\">���</button>");
    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }*/
  
  
  
  /**
   * ��� �� ���
   * http://localhost:9090/study/room/create.do
   * @return 
   */
  @RequestMapping(value="/admin/room/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
   // System.out.println("--> create() GET executed");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/admin/room/create");
   
   return mav;
  }
    
  /**
   * ��� ��� ���
   * http://localhost:9090/study/room/create.do
   * @param request
   * @param roomVO
   * @return ��� ��� ���
   */
  @ResponseBody
  @RequestMapping(value="/admin/room/create.do", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String create(HttpServletRequest request, RoomVO roomVO) {

    JSONObject obj = new JSONObject();
    
    ///// NULL �� ó�� ���� ���� ȣ�� ///////
    String rotel = roomVO.getRotel(); 
    String rosite = roomVO.getRosite();
    String rorunday = roomVO.getRorunday();
    String rocost = roomVO.getRocost();
    String rocount = roomVO.getRocount();
    //////////////////////////////////////////////////

    // ---------------------------------------------------------------
    // �ּ� ���� & ���� ����
    // ---------------------------------------------------------------
    String rolocation = roomVO.getRocity() + " " + roomVO.getRogu() + " " + roomVO.getRodong();
    roomVO.setRolocation(rolocation);
    System.out.println(rolocation);
    // ---------------------------------------------------------------
    // �ּ� ���� & ���� ����
    // ---------------------------------------------------------------
    
     // ---------------------------------------------------------------
    //  �˻� ���� & ���� ����
    // ---------------------------------------------------------------
    String ronalo = roomVO.getRoname() + " " + roomVO.getRocity() + " " + roomVO.getRogu() + " " + roomVO.getRodong();
    roomVO.setRonalo(ronalo);
    System.out.println(ronalo); 
    // ---------------------------------------------------------------
    //  �˻� ���� & ���� ����
    // ---------------------------------------------------------------
    
    
    // ---------------------------------------------------------------
    // �ɼ� ���� & ���� ����
    // ---------------------------------------------------------------
    String rooption = "";
    String option1 = roomVO.getOption1();
    String option2 = roomVO.getOption2();
    String option3 = roomVO.getOption3();
    String option4 = roomVO.getOption4();
    String option5 = roomVO.getOption5();
    
    System.out.println("option1"+option1);
    System.out.println("option2"+option2);
    System.out.println("option3"+option3);
    System.out.println("option4"+option4);
    System.out.println("option5"+option5);
    
    if (option1 != null) {
      rooption += option1;
    }
    if (option2 != null) {
      rooption += option2;
    }
    if (option3 != null) {
      rooption += option3;
    }
    if (option4 != null) {
      rooption += option4;
    }
    if (option5 != null) {
      rooption += option5;
    }

    roomVO.setRooption(rooption);

    // ---------------------------------------------------------------
    // �ɼ� ���� & ���� ����
    // ---------------------------------------------------------------

    // ---------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // ---------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/admin/room/storage");
    
    MultipartFile file1MF = roomVO.getFile1MF();
    
    String rofile1 = ""; // �÷��� ������ ���ϸ�
    long rosize1 = 0;
    
    String rothumb = "";
    
    if (file1MF != null) {
      rosize1 = file1MF.getSize();
    }
     

    if (rosize1 > 0) {
      rofile1 = Upload.saveFileSpring(file1MF, upDir);

      if (Tool.isImage(rofile1)) {
        rothumb = Tool.preview(upDir, rofile1, 320, 240);
      }
    }
    
    //***** Null �� (������� �ʾ������) ó�� *****
    rofile1 = Tool.checkNull(rofile1);
    rothumb = Tool.checkNull(rothumb);
    rotel = Tool.checkNull(rotel);
    rosite = Tool.checkNull(rosite);
    rorunday = Tool.checkNull(rorunday);
    rocost = Tool.checkNull(rocost);
    rocount = Tool.checkNull(rocount);
    //************************************************
    roomVO.setRofile1(rofile1);
    roomVO.setRosize1(rosize1);
    roomVO.setRothumb(rothumb);
    // ---------------------------------------------------------------
    // ���� ���� ����
    // ---------------------------------------------------------------
    
    int count = 0;
    count = roomProc.create(roomVO);
    
    if ( count == 1) {
      System.out.println("����޾�");
    } else {
      System.out.println("��� ���޾�");
    }
    
    obj.put("count", count);
    
 
    return obj.toJSONString();
  }
  
  
  
  

  /**
   * �˻� ���
   * 
   * @param word
   * @return
   */
  @RequestMapping(value = "/nonuser/room/list.do", method = RequestMethod.GET)
  public ModelAndView list(RoomVO roomVO, ReviewVO reviewVO) { // int cateno, String roname, int nowPage
    System.out.println("--> list(RoomVO roomVO) GET called");
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/room/list"); // webapp/room/list_by_categoryno.jsp
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Object ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("ronalo", roomVO.getRonalo()); // #{ronalo}
    hashMap.put("nowPage", roomVO.getNowPage()); // #{nowPage}
    

    // �˻� ���
    List<RoomVO> list = roomProc.list(hashMap);
    mav.addObject("list", list);
    
    System.out.println("--> nowPage:" + roomVO.getNowPage());
    System.out.println("--> Rolocation:" + roomVO.getRolocation());
    System.out.println("--> Ronalo:" + roomVO.getRonalo()); 
    
    // �˻��� ���ڵ� ����
    int search_count = roomProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    

    // mav.addObject("word", word);

    String paging = roomProc.paging(search_count, roomVO.getNowPage(), roomVO.getRoname());
    mav.addObject("paging", paging);

    mav.addObject("nowPage", roomVO.getNowPage());

    return mav;
  }

  @RequestMapping(value = "/nonuser/room/read.do", method = RequestMethod.GET)
  public ModelAndView read(int rono, HttpSession session) {
    System.out.println("--> read() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/room/read"); // webapp/room/read.jsp
    
    /*ReviewVO reviewVO = reviewProc.read(rono);
    System.out.println("reviewVO" + reviewVO);*/
    /*if (session.getAttribute("memberno") != null) {
      HashMap hashMap = new HashMap();
      
      // ������ ���� memberno �� ������
      int memberno = (Integer)session.getAttribute("memberno");
      // int memberno = reviewVO.getMemberno();
      
      hashMap.put("memberno", memberno);
      hashMap.put("rvno", reviewVO.getRvno());
      
      // rvlike ���̺� �̹� �Էµ� ȸ������ Ȯ��
      if (rvlikeProc.good_chk(hashMap) == 0) {
        rvlikeProc.create(hashMap); 
      }
    }*/
    

    RoomVO roomVO = roomProc.read(rono);
    
    System.out.println("roomVO" + roomVO);
    mav.addObject("roomVO", roomVO);

    /*
     * HashMap<String, Object> hashMap = new HashMap<String, Object>();
     * hashMap.put("rono", reviewVO.getRono()); hashMap.put("nowPage",
     * reviewVO.getNowPage());
     * 
     * List<ReviewVO> list = reviewProc.list(hashMap); mav.addObject("list",
     * list);
     * 
     * int search_count = reviewProc.search_count(hashMap);
     * mav.addObject("search_count", search_count);
     * 
     * 
     * String paging = reviewProc.paging(reviewVO.getRono(), search_count,
     * reviewVO.getNowPage()); mav.addObject("paging", paging);
     * mav.addObject("nowPage", reviewVO.getNowPage());
     */

    // ���� ��������
    String rooption = roomVO.getRooption();
    // ���� ������
    String[] split = rooption.split(",");

    for (int i = 0; i < split.length; i++) {
      if (split[i].equals("��Ʈ�� �뿩 ����")) {
        String option1 = split[i];
        roomVO.setOption1(option1 + ",");
      } else if (split[i].equals("�� �������� ��� ����")) {
        String option2 = split[i];
        roomVO.setOption2(option2 + ",");
      } else if (split[i].equals("�μ�/���� ����")) {
        String option3 = split[i];
        roomVO.setOption3(option3 + ",");
      } else if (split[i].equals("�������� ����")) {
        String option4 = split[i];
        roomVO.setOption4(option4 + ",");
      } else if (split[i].equals("ȭ��Ʈ���� ��� ����")) {
        String option5 = split[i];
        roomVO.setOption5(option5 + ",");
      }
    }
    ;

    return mav;
  }

  // ���� ����Ʈ ajax
  @ResponseBody
  @RequestMapping(value = "/nonuser/room/list2.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String review(ReviewVO reviewVO, HttpSession session) { //
    JSONObject obj = new JSONObject();

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("rono", reviewVO.getRono());
    hashMap.put("nowPage", reviewVO.getNowPage());

    List<ReviewVO> list = reviewProc.list(hashMap);
    int search_count = reviewProc.search_count(reviewVO.getRono());
    String paging = reviewProc.paging(reviewVO.getRono(), search_count, reviewVO.getNowPage());
    
    if (session.getAttribute("memberno") != null) {
      
      for(int index = 0; index < list.size(); index++){
        
        int memberno = (Integer)session.getAttribute("memberno");
        // int memberno =  list.get(index).getMemberno();
        int rvno =  list.get(index).getRvno();
       
        System.out.println("memberno ---> " + memberno);
        System.out.println("rvno ---> " + rvno);
        HashMap hashmap1 = new HashMap();
        hashmap1.put("memberno", memberno);
        hashmap1.put("rvno",  rvno);
 
        int count = rvlikeProc.like_chk(hashmap1);
        
        if (count == 0) {
          rvlikeProc.create(hashmap1);
        }
        
        int cnt = rvlikeProc.check(hashmap1);
        
        list.get(index).setCount(cnt);
        
        System.out.println("count ---> " + count);
      }
    }
    
    JSONArray list2 = JSONArray.fromObject(list);
    System.out.println("list2 ---> " + list2);
    obj.put("list2", list2);
    obj.put("search_count", search_count);
    obj.put("paging", paging);

    System.out.println(obj);
    return obj.toJSONString();

  }
  

  @RequestMapping(value = "/admin/room/update.do", method = RequestMethod.GET)
  public ModelAndView update(int rono) {
    System.out.println("--> update() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/admin/room/update"); // webapp/room/update.jsp

    RoomVO roomVO = roomProc.read(rono);
    mav.addObject("roomVO", roomVO);

    // ���� ��������
    String rooption = roomVO.getRooption();
    // ���� ������
    String[] split = rooption.split(",");

    for (int i = 0; i < split.length; i++) {
      if (split[i].equals("��Ʈ�� �뿩 ����")) {
        String option1 = split[i];
        roomVO.setOption1(option1 + ",");
      } else if (split[i].equals("�� �������� ��� ����")) {
        String option2 = split[i];
        roomVO.setOption2(option2 + ",");
      } else if (split[i].equals("�μ�/���� ����")) {
        String option3 = split[i];
        roomVO.setOption3(option3 + ",");
      } else if (split[i].equals("�������� ����")) {
        String option4 = split[i];
        roomVO.setOption4(option4 + ",");
      } else if (split[i].equals("ȭ��Ʈ���� ��� ����")) {
        String option5 = split[i];
        roomVO.setOption5(option5 + ",");
      }
    };

    return mav;
  }
  
  /**
  * <XMP>
  * �Խñ� ������� ���
  * <XMP>
  * @param request
  * @param sharedVO
  * @return ����� ���ڵ� ����
  */
  // ajax ���� POST
 @ResponseBody
 @RequestMapping(value="/admin/room/update.do", method=RequestMethod.POST, produces = "application/text; charset=utf8")
 public String update(HttpServletRequest request, RoomVO roomVO) {
   System.out.println("rono" + roomVO.getRono());
   JSONObject obj = new JSONObject();

   String root = request.getContextPath(); // ������ ����
   
   ///// NULL �� ó�� ���� ���� ȣ�� ///////
   String rotel = roomVO.getRotel(); 
   String rosite = roomVO.getRosite();
   String rorunday = roomVO.getRorunday();
   String rocost = roomVO.getRocost();
   String rocount = roomVO.getRocount();
   //////////////////////////////////////////////////
   
  // ---------------------------------------------------------------
  // �ּ� ���� & ���� ����
  // ---------------------------------------------------------------
   String rolocation = roomVO.getRocity() + " " + roomVO.getRogu() + " " + roomVO.getRodong();
   roomVO.setRolocation(rolocation);
  // ---------------------------------------------------------------
  // �ּ� ���� & ���� ����
  // ---------------------------------------------------------------
   
   // ---------------------------------------------------------------
   //  �˻� ���� & ���� ����
   // ---------------------------------------------------------------
   String ronalo = roomVO.getRoname() + " " + rolocation;
   roomVO.setRonalo(ronalo);
   // ---------------------------------------------------------------
   //  �˻� ���� & ���� ����
   // ---------------------------------------------------------------
   
   
  // ---------------------------------------------------------------
  // �ɼ� ���� & ���� ����
  // ---------------------------------------------------------------
  String rooption = "";
  String option1 = roomVO.getOption1();
  String option2 = roomVO.getOption2();
  String option3 = roomVO.getOption3();
  String option4 = roomVO.getOption4();
  String option5 = roomVO.getOption5();

   if (option1 != null) {
     rooption += option1;
   }
   if (option2 != null) {
     rooption += option2;
   }
   if (option3 != null) {
     rooption += option3;
   }
   if (option4 != null) {
     rooption += option4;
   }
   if (option5 != null) {
     rooption += option5;
   }

   roomVO.setRooption(rooption);

  // ---------------------------------------------------------------
  // �ɼ� ���� & ���� ����
  // ---------------------------------------------------------------
   
  //*********************************************************************************
  // �������� �ڵ� ����
  //*********************************************************************************
   String upDir = Tool.getRealPath(request, "/admin/room/storage"); // Upload ��� ����
   MultipartFile file1MF = roomVO.getFile1MF(); // ���� ����
   
   String rofile1 = ""; // ���� ���ϸ�
   long rosize1 = 0; // ���ϻ�����
   String rothumb = ""; // �����

   if (file1MF != null) {// ���ϱ��� �޾ƿ´ٸ��� ���
     rosize1 = file1MF.getSize();
   }
   RoomVO roomVO_old= roomVO;
   
   
   if (rosize1 > 0) { // ���� ����� 0 �̻��� ��� (=������ �����Ұ��)
   
     // �ű� ���� ��Ͻ� ���� ���� ����
     Tool.deleteFile(upDir, roomVO_old.getRofile1());
     Tool.deleteFile(upDir, roomVO_old.getRothumb());
     
     rofile1 = Upload.saveFileSpring(file1MF, upDir); // �ű� ���� ���ε�

     if (Tool.isImage(rofile1)) { // ���ε� ������ �̹����� ���
       rothumb = Tool.preview(upDir, rofile1, 320, 240); // Thumb �̹��� ����
     }
     
   } else { // ������ �������� �ʴ� ��� ���� ���� ���� ���
     rofile1 = roomVO_old.getRofile1();
     rosize1 = roomVO_old.getRosize1();
     rothumb = roomVO_old.getRothumb();
   }
   
 //***** Null �� (������� �ʾ������) ó�� *****
   rofile1 = Tool.checkNull(rofile1);
   rothumb = Tool.checkNull(rothumb);
   rotel = Tool.checkNull(rotel);
   rosite = Tool.checkNull(rosite);
   rorunday = Tool.checkNull(rorunday);
   rocost = Tool.checkNull(rocost);
   rocount = Tool.checkNull(rocount);
  //************************************************

  //************************************************
   roomVO.setRofile1(rofile1);
   roomVO.setRosize1(rosize1);
   roomVO.setRothumb(rothumb);
  //*********************************************************************************
  // �������� �ڵ� ����
  //*********************************************************************************
   int count = 0;
   count = roomProc.update(roomVO);
   
   if ( count == 1) {
     System.out.println("�����߾�");
   } else {
     System.out.println("���� ���߾�");
   }
   
   obj.put("count", count);

   return obj.toJSONString();
 }
  

  
  @ResponseBody 
  @RequestMapping(value = "/admin/room/delete.do", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
  public String delete(int rono) {
    System.out.println("--> delete() GET executed");
    
    JSONObject obj = new JSONObject();

    int count = reviewProc.countByRono(rono);
    System.out.println("����� ����" + count);
    obj.put("count", count);
    
    return obj.toJSONString();
  }
  
  /**
   * <XMP> 
   * ���͵�� �� ������� ���
   *  </XMP>
   * @param rono
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/admin/room/delete.do", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String delete(HttpServletRequest request, int rono) {
    JSONObject obj = new JSONObject();
    
    int rvcnt = reviewProc.countByRono(rono);
    System.out.println(rvcnt);
    obj.put("rvcnt", rvcnt);

    int rono_delete = reviewProc.deleteByRono(rono);
    
    RoomVO roomVO = roomProc.read(rono);

    String root = request.getContextPath(); // ������ ����

    // *************************************************************************
    // ���� ���� �ڵ� ����
    // *************************************************************************

    String upDir = Tool.getRealPath(request, "/admin/room/storage"); // ���ε� ���

    Tool.deleteFile(upDir, roomVO.getRofile1()); // ���� ���ϸ� ����
    Tool.deleteFile(upDir, roomVO.getRothumb()); // ���ε� ���ϸ� ����

    // *************************************************************************
    // ���� ���� �ڵ� ����
    // *************************************************************************

    
   /* int check = saleProc.member_check(saleVO);
    int count_check;
    int count = 0;
    
    if (check == 1) { // ��ϵ� ���̵�� ��ġ�ϴ� ���
      count_check = salereplyProc.delete_all(saleno);
      count = saleProc.delete(saleno);

      if (count == 1) { // ���� ó���� �������� ���
        // System.out.println("�� ���� ����");
      } else { // ���� ó���� �������� ���
        // System.out.println("�� ���� ����");
      }
      
    } else { // ��ϵ� ���̵�� ��ġ���� �ʴ� ���
      // System.out.println("���̵� ��ġ X");
    }
    */
    int count = roomProc.delete(rono);
    if (count == 1) { // ���� ó���� �������� ���
      // System.out.println("�� ���� ����");
    } else { // ���� ó���� �������� ���
      // System.out.println("�� ���� ����");
    }
    
    
    obj.put("count", count);
    
    return obj.toString();
    
  }

}
