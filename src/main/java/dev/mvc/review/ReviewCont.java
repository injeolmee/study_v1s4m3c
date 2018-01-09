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
  
  
  //http://localhost:9090/study/review/create.jsp ��
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
    // ���� ���� �ڵ� ����
    //***************************************************************** 
    String upDir = Tool.getRealPath(request, "/user/review/storage"); // ���� upload ���� ��ġ
    
    MultipartFile file1MF = reviewVO.getFile1MF(); // ���ε� �� ����
    
    // System.out.println("file ����: " + file1MF);
    long rvsize1 = 0;
    String rvfile1="";
    
    String rvthumb = ""; // ����� ���
    
    if (file1MF != null) { // ���ϱ��� �޾ƿ´ٸ��� ���
      rvsize1 = file1MF.getSize();
    }
    
    System.out.println("rvsize1 : " + rvsize1);
    
    if (rvsize1 > 0) { // ���ϻ���� 0 �̻��� ��� (���ε��� ���)
      rvfile1 = Upload.saveFileSpring(file1MF, upDir);
      //System.out.println("rvfile1 ---> " + rvfile1);
      //System.out.println("rvsize1 ---> " + rvsize1);
      //System.out.println("rvthumb ---> " + rvthumb);
      //System.out.println("upDir ---> " + upDir);
      if (Tool.isImage(rvfile1)) { // �̹��� �� ���
        rvthumb = Tool.preview(upDir, rvfile1, 200, 250); // Thumb �̹��� ����
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
      

      System.out.println("����޾�");
    } else {
      System.out.println("��� ���޾�");
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
    // ���� ���� �ڵ� ����
    // ---------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    MultipartFile file1MF = reviewVO.getFile1MF();
    String rvfile1 = ""; // �÷��� ������ ���ϸ�
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
    // ���� ���� ����
    // ---------------------------------------------------------------
    
    if (reviewProc.create(reviewVO) == 1) {
      msgs.add("���� ��� ����");
    } else {
      msgs.add("���� ��� ����");
      msgs.add("�ٽ� �õ� -> ���");
      links.add("<button type= 'button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    }
    
    links.add("<button type= 'button' onclick=\"location.href='./list.do'\">���</button>");
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
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Object ���
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

    //System.out.println("reviewVO�� memberno:" + reviewVO.getMemberno());
    return mav;
  }

  
  
  @ResponseBody
  @RequestMapping(value= "/user/review/update.do", method= RequestMethod.POST,  produces = "application/text; charset=utf8")
  public String update(HttpServletRequest request, ReviewVO reviewVO) {
    
    System.out.println(" Update Post called");
    
    JSONObject obj = new JSONObject();
    ReviewVO reviewVO_old = reviewVO; 
    
    System.out.println("cont�� memberno: " + reviewVO_old.getMemberno());
    
    //***************************************************************** 
    // ���� ���� �ڵ� ����
    //***************************************************************** 
    String upDir = Tool.getRealPath(request, "/user/review/storage"); // ���� upload ���� ��ġ
    MultipartFile file1MF = reviewVO.getFile1MF(); // ���ε� �� ����
    
    System.out.println("file1MF ---> " + file1MF);
    
    long rvsize1 = 0;
    String rvfile1 = "";
    String rvthumb = ""; // ����� ���

    if (file1MF != null) { // ���ϱ��� �޾ƿ´ٸ��� ���
      rvsize1 = file1MF.getSize();
    }
    
    System.out.println("rvsize1 : " + rvsize1);
    
    if (rvsize1 > 0) { // ���ϻ���� 0 �̻��� ��� (���ε��� ���)
      Tool.deleteFile(upDir, reviewVO_old.getRvfile1()); // ���� ���� ����
      Tool.deleteFile(upDir, reviewVO_old.getRvthumb());
      
      rvfile1 = Upload.saveFileSpring(file1MF, upDir); // �ű� ���� ���ε�
      /*System.out.println("rvfile1 ---> " + rvfile1);
      System.out.println("rvsize1 ---> " + rvsize1);
      System.out.println("rvthumb ---> " + rvthumb);
      System.out.println("upDir ---> " + upDir);*/
      System.out.println("file1MF ---> " + file1MF);
      if (Tool.isImage(rvfile1)) { // �̹��� �� ���
        rvthumb = Tool.preview(upDir, rvfile1, 200, 250); // Thumb �̹��� ����
      }
    }  else {
      // ������ �������� �ʴ� ��� ���� ���� ���� ���
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
    
    System.out.println("reviewVO�� memno: " + reviewVO.getMemberno());
    
    int count = 0;
    count = reviewProc.update(reviewVO);
    
    System.out.println("count: " + count);
    if (count == 1) {
      System.out.println("�����޾�"); 
      System.out.println("==========================================");
    } else {
      System.out.println("���� ���޾�");
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
    // ���� ���� �ڵ� ����
    // ---------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    MultipartFile file1MF = reviewVO.getFile1MF();
    String rvfile1 = ""; // �÷��� ������ ���ϸ�
    Long rvsize1 = file1MF.getSize();
    String rvthumb = "";

    ReviewVO reviewVO_old = reviewProc.read(reviewVO.getRvno());

    if (rvsize1 > 0) {
      Tool.deleteFile(upDir, reviewVO_old.getRvfile1()); // ���� ���� ����
      Tool.deleteFile(upDir, reviewVO_old.getRvthumb()); // ���� Thumb ���� ����

      rvfile1 = Upload.saveFileSpring(file1MF, upDir); // �ű� ���� ���ε�

      if (Tool.isImage(rvfile1)) { // ���ο� ���� �̹������� �˻�
        rvthumb = Tool.preview(upDir, rvfile1, 320, 240); // Thumb �̹��� ����
      }
    } else {
      // ������ �������� �ʴ� ��� ���� ���� ���� ���
      rvfile1 = reviewVO_old.getRvfile1();
      rvsize1 = reviewVO_old.getRvsize1();
      rvthumb = reviewVO_old.getRvthumb();
    }

    reviewVO.setRvfile1(rvfile1);
    reviewVO.setRvsize1(rvsize1);
    reviewVO.setRvthumb(rvthumb);
    // ---------------------------------------------------------------
    // ���� ���� ����
    // ---------------------------------------------------------------

    
     * //ȸ�� ���� �� session���� ���� roomVO.setMno(1);
     

    String root = request.getContextPath();

    if (reviewProc.update(reviewVO) == 1) {
      msgs.add("���� ����");
      msgs.add("���� �����߽��ϴ�.");
    } else {
      msgs.add("���� ����");
      msgs.add("�� ������ �����߽��ϴ�.");
      msgs.add("�ٽ� �õ� -> ���");
      links.add("<button type= 'button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='../room/read.do?rono="+reviewVO.getRono()+ "&rvno=" +reviewVO.getRvno() + "'\">���</button>");
    links.add("<button type='button' onclick=\"location.href='" + root + "/home.do'\">Ȩ������</button>");

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
   * ����� ������� ���
   *  </XMP>
   * @param rvno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/review/delete.do", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String delete(HttpServletRequest request, int rvno) {
    
    JSONObject obj = new JSONObject();
    
    ReviewVO reviewVO = reviewProc.read(rvno);

    String root = request.getContextPath(); // ������ ����

    // *************************************************************************
    // ���� ���� �ڵ� ����
    // *************************************************************************

    String upDir = Tool.getRealPath(request, "/user/review/storage"); // ���ε� ���

    Tool.deleteFile(upDir, reviewVO.getRvfile1()); // ���� ���ϸ� ����
    Tool.deleteFile(upDir, reviewVO.getRvthumb()); // ���ε� ���ϸ� ����

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
    int count = reviewProc.delete(rvno);
    if (count == 1) { // ���� ó���� �������� ���
      // System.out.println("�� ���� ����");
    } else { // ���� ó���� �������� ���
      // System.out.println("�� ���� ����");
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
    // ���ϻ���
    // ---------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");

    Tool.deleteFile(upDir, reviewVO_old.getRvfile1()); // ���� ���� ����
    Tool.deleteFile(upDir, reviewVO_old.getRvthumb()); // ���� Thumb ���� ����

    // ---------------------------------------------------------------

    
     * //ȸ�� ���� �� session���� ���� roomVO.setMno(1);
     

    String root = request.getContextPath();

    if (reviewProc.delete(reviewVO.getRvno()) == 1) {

      msgs.add("���� ����");
      msgs.add("���� �����߽��ϴ�.");
    } else {
      msgs.add("���� ����");
      msgs.add("�� ������ �����߽��ϴ�.");
      msgs.add("�ٽ� �õ� -> ���");
      links.add("<button type= 'button' onclick=\"history.back()\">�ٽ� �õ�</button>");
    }

    links.add("<button type= 'button' onclick=\"location.href='./list.do?nowPage=" + reviewVO.getNowPage() + "&roname="
        + roomVO.getRoname() + "'\">���</button>");
    links.add("<button type='button' onclick=\"location.href='" + root + "/home.do'\">Ȩ������</button>");

    mav.addObject("msgs", msgs);
    mav.addObject("links", links);

    return mav;
  }*/

}
