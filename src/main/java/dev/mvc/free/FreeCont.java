package dev.mvc.free;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.freelike.FreelikeProcInter;
import dev.mvc.freereply.FreereplyProcInter;
import dev.mvc.freereply.FreereplyVO;
import nation.web.tool.Tool;

@Controller
public class FreeCont {
  
  @Autowired
  @Qualifier("dev.mvc.free.FreeProc")
  private FreeProcInter freeProc;
  
  @Autowired
  @Qualifier("dev.mvc.freereply.FreereplyProc")
  private FreereplyProcInter freereplyProc;
  
  @Autowired
  @Qualifier("dev.mvc.freelike.FreelikeProc")
  private FreelikeProcInter freelikeProc;
  
  public FreeCont() {
    // System.out.println(" => FreeCont create");
  }
  
  /**
   * <XMP>
   * ��� �� ���
   * </XMP>
   * @return 
   */
  @RequestMapping(value="/user/free/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
   // System.out.println("--> create() GET executed");
   ModelAndView mav = new ModelAndView();
   mav.setViewName("/user/free/create");
   
   return mav;
  }
    
  /**
   * <XMP>
   * ��� ��� ���
   * </XMP>
   * @param request
   * @param freeVO
   * @return count
   */
  @ResponseBody
  @RequestMapping(value="/user/free/create.do", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String create(HttpSession session, HttpServletRequest request, FreeVO freeVO) {

    JSONObject obj = new JSONObject();

    int count = 0; // �Խñ� ����� ó���� ����
    
    if (session.getAttribute("memberno") != null) { // ȸ���� ���
      count = freeProc.create(freeVO);
    } else if (session.getAttribute("adminno") != null) { // �������� ���
      count = freeProc.create_admin(freeVO);
    }
    obj.put("count", count);
 
    return obj.toString();
  }
  
  /**
   * <XMP>
   * �Խ��� ��� + �˻� +����¡
   * </XMP>
   * @param word �˻���
   * @param search �˻� Value Option
   * @param nowPage ���� ������
   * @return
   */
  @RequestMapping(value="/nonuser/free/list.do", method=RequestMethod.GET)
  public ModelAndView list( @RequestParam(value="word", defaultValue="") String word, // �˻���
                                    @RequestParam(value="search", defaultValue="") String search, // �˻��з���
                                    @RequestParam(value="nowPage", defaultValue = "1") int nowPage) { // ����������
    
    // System.out.println("--> list_search() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/free/list");

    //*******************************************************************************
    // �˻��� ���õ� �⺻ �ɼ� ó�� ����                                               
    //*******************************************************************************
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);           // �˻���
    hashMap.put("search", search);        // �˻� Option Value
    hashMap.put("nowPage", nowPage); // ���� ������
    //*******************************************************************************
    // �˻��� ���õ� �⺻ �ɼ� ó�� ����                                               
    //*******************************************************************************
      
    //*******************************************************************************
    // �� �˻� + ����¡ ����� ��� ��� ����                                   
    //*******************************************************************************
    List<FreeVO> list = freeProc.list_search(hashMap); // �Խ��� �⺻ ��� ��� (�˻�+����¡)
    mav.addObject("list", list);
    //*******************************************************************************
    // �˻� + ����¡ ����� ��� ��� ����                                        
    //*******************************************************************************
    
    //*******************************************************************************
    // �� ��õ�� ���� 3�� ��� ��� ����                             
    //*******************************************************************************
    HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
    List<FreeVO> list_like = freeProc.list_like(hashMap2); // �Խ��� ��õ��(Like) �ֻ��� ��� ���
    mav.addObject("list_like", list_like);
    //*******************************************************************************
    // ��õ�� ���� 3�� ��� ��� ����                        
    //*******************************************************************************
    
    //*******************************************************************************
    // �� ��ȸ�� ���� 3�� ��� ��� ����                             
    //*******************************************************************************
    HashMap<String, Object> hashMap3 = new HashMap<String, Object>();
    List<FreeVO> list_cnt = freeProc.list_cnt(hashMap3); // �Խ��� ��ȸ��(Cnt) �ֻ��� ��� ���
    mav.addObject("list_cnt", list_cnt); 
    //*******************************************************************************
    // ��ȸ�� ���� 3�� ��� ��� ����                         
    //*******************************************************************************
    
    int search_count = freeProc.search_count(hashMap); // �˻��� ���ڵ� ����
    mav.addObject("search_count", search_count); 
   
    String paging = freeProc.paging(nowPage, search_count, word, search); // ����¡ ���
    mav.addObject("paging", paging);
    
    mav.addObject("nowPage", nowPage); // ����������
       
    return mav;
  }
  
  /**
   * <XMP>
   * �Խñ� ��ȸ + ��� ���
   * </XMP>
   * @param nowPage ���� ������
   * @param freeno �Խñ� ��ȣ
   * @return freeVO, pre_num(������ ��ȣ), post_num(������ ��ȣ)
   */
  @RequestMapping(value = "/nonuser/free/read.do", method = RequestMethod.GET)
  public ModelAndView read( HttpSession session,
                                      @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
                                      @RequestParam(value = "freeno") int freeno) {
    
    // System.out.println("--> read() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/nonuser/free/read");

    //***************************************************************** 
    // �Խñ� ��ȸ ó�� ����(From "Free" Package)
    //*****************************************************************  
    FreeVO freeVO = freeProc.read(freeno);
    
    int pre_num = freeProc.read_pre(freeno); // ������ ��ȣ
    int post_num = freeProc.read_post(freeno); // ������ ��ȣ
    
    freeVO.setPre_num(pre_num); 
    freeVO.setPost_num(post_num); 
    mav.addObject("freeVO", freeVO);
    //***************************************************************** 
    // �Խñ� ��ȸ ó�� ����
    //***************************************************************** 
    
    //***************************************************************** 
    // ��� ��� ó�� ���� (From "Freereply" Package)
    //*****************************************************************    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("freeno", freeno);
    hashMap.put("nowPage", nowPage);
    
    List <FreereplyVO> list_reply = freereplyProc.total_list_reply(hashMap);
    mav.addObject("list_reply", list_reply);
    
    int search_count = freereplyProc.search_count(freeno);
    mav.addObject("search_count", search_count); // �˻��� ���ڵ� ����

    String paging = freereplyProc.paging(nowPage, search_count, freeno);  // ����¡ ó��
    mav.addObject("paging", paging);              

    freeVO.setNowPage(nowPage);
    mav.addObject("nowPage", nowPage);         // ���� ������ ����
    //***************************************************************** 
    // ��� ��� ó�� ����
    //*****************************************************************
    
    //***************************************************************** 
    // ���ƿ�� ���õ� ó�� ���� (= �Էµ� ȸ���� ���õǾ Check)
    //*****************************************************************
    if (session.getAttribute("memberno") != null) {
      HashMap hashMap2 = new HashMap();
      
      int memberno = (Integer)session.getAttribute("memberno"); // ������ ���� ȸ����ȣ�� ������!
      
      hashMap2.put("memberno", memberno);
      hashMap2.put("freeno", freeno);
      
      // Freelike ���̺� �̹� �Էµ� ȸ������ Check
      if (freelikeProc.good_chk(hashMap2) == 0) { 
        freelikeProc.create(hashMap2);
      }
    }
    //***************************************************************** 
    // ���ƿ�� ���õ� ó�� ����
    //*****************************************************************
    
    freeProc.increaseCnt(freeno); // ��ȸ �� ��ȸ�� ��� ó��
    
    return mav;
  }
  
  /**
   * <XMP>
   * ���� �� ���
   * </XMP>
   * @param freeno
   * @return http://localhost:9090/user/study/free/update.do
   */
  @RequestMapping(value="/user/free/update.do", method=RequestMethod.GET)
  public ModelAndView update(int freeno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/free/update");
    
    FreeVO freeVO = freeProc.read(freeno);
    
    //**************************************************************************
    // ������ Ư������ ó���� ���� ���� ���� 
    //**************************************************************************
    String freecontent = freeVO.getFreecontent(); // ���� ������ �����ͼ�
    String new_freecontent = Tool.toJS(freecontent); // �ڹٽ�ũ��Ʈ�� �ν��� �� �ִ� �ڵ�� ��ȯ�Ѵ�.
    
    freeVO.setFreecontent(new_freecontent);
    //**************************************************************************
    // ������ Ư������ ó���� ���� ���� ����
    //**************************************************************************

    mav.addObject("freeVO", freeVO);
    
    return mav;
  }
    
  /**
   * <XMP>
   * ���� ��� ó��
   * </XMP>
   * @param request
   * @param freeVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/user/free/update.do", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String update(HttpSession session, FreeVO freeVO) {
    
    JSONObject obj = new JSONObject(); 
 
    int freeno = freeVO.getFreeno();

    int check = 0; // ���̵� �˻縦 ó���� ���� (�� ȸ���� �ش�)
    int count= 0; // �Ƶ�� �˻縦 ������ ��� ó���� ����
    
    if (session.getAttribute("memberno") != null) { // ȸ���� ���
      check = freeProc.member_check(freeVO); // ���̵� �˻�
    }
    
    if (check == 1 || session.getAttribute("adminno") != null) { // ��ϵ� ��� ���̵�� ��ġ�ϰų� �������� ���
       count = freeProc.update(freeVO);
       
       if (count == 1) { // ���� ó���� ������ ���
         // System.out.println("���� ����");
       } else { // ���� ó���� ������ ���
         // System.out.println("���� ����");
       }
       
    } else { // ��ϵ� ��� ���̵�� ��ġ���� �ʴ� ���
      // System.out.println("���̵� ��ġ X");
    }

    obj.put("count", count);
    obj.put("freeno", freeno);
    
    return obj.toString();
  }
  
  /**
   * <XMP>
   * �Խñ� ������� ���
   * </XMP>
   * @param request
   * @param freeVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/delete.do", method = RequestMethod.POST,  produces = "text/html; charset=UTF-8")
  public String delete(HttpSession session, HttpServletRequest request, int freeno) {

    JSONObject obj = new JSONObject(); 
    String root = request.getContextPath(); // ���� ��� ����
    
    FreeVO freeVO = freeProc.read(freeno);
    
    int check = 0; // ���̵� �˻� ó���� ���� (�� ȸ���� �ش�)
    
    if (session.getAttribute("memberno") != null) { // ȸ���� ���
     check = freeProc.member_check(freeVO); // ���̵� �˻�
    }
    
    int like_all = 0; // �Ƶ�� �˻� ������ ó���� ���� ��
    int count_all = 0; // ���̵� �˻� ������ ó���� ���� ��
    int count = 0; // ���̵� �˻� ������ ó���� ���� ��
  
    if (check == 1 || session.getAttribute("adminno") != null) { // ��ϵ� ��� ���̵�� ��ġ�ϰų� �������� ���
      
      like_all = freelikeProc.like_delete(freeno); // �� ���ƿ� ����
      count_all = freereplyProc.delete_all(freeno); // �� ��� ��ü ����
      count = freeProc.delete(freeno);               //  �� �Խñ� ����
      
      if (count == 1) { // ���� ó���� ������ ���
        // System.out.println("���� ó�� ����");
      } else { // ���� ó���� ������ ���
        // System.out.println("���� ó�� ����");
      }
      
    } else { // ��ϵ� ��� ���̵�� ��ġ���� �ʴ� ���
      // System.out.println("���̵� ��ġ X");
    }

    obj.put("count", count);

    return obj.toString();
  }
  
  /**
   * <XMP>
   * ��� ���
   * <insert id="create" parameterType="FreereplyVO">
   * </XMP>
   * @param request
   * @param freereplyVO
   * @return
   */
  @RequestMapping(value = "/user/free/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(HttpSession session, HttpServletRequest request, FreereplyVO freereplyVO, int nowPage) {
    // System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int freeno = freereplyVO.getFreeno();
    freereplyVO.setNowPage(nowPage);
    int count = 0; // ��� ����� ó���ϱ����� ����
    
    if (session.getAttribute("memberno") != null) { // ȸ���� ���
      count = freereplyProc.create(freereplyVO);
    } else if (session.getAttribute("adminno") != null) { // �������� ���
      count = freereplyProc.create_admin(freereplyVO);
    }
    
    if (count == 1) {
      // System.out.println("��۵�� ����");
      mav.setViewName("redirect:/nonuser/free/read.do?freeno=" + freeno + "&nowPage=" + nowPage);
    } else {
      // System.out.println("��۵�� ����");
    }
    
    return mav;
  }

  /**
   * <XMP> 
   * ���� ��� 
   * </XMP>
   * @param request
   * @param freereplyVO
   * @return
   */
  @RequestMapping(value = "/user/free/rereply.do", method = RequestMethod.POST)
  public ModelAndView rereply(HttpSession session, HttpServletRequest request, FreereplyVO freereplyVO, int nowPage) {
    // System.out.println("--> rereply() POST called.");
    ModelAndView mav = new ModelAndView();

    // *************************************************************************
    // ����(�亯) ��� ó�� ����
    // *************************************************************************
 
    FreereplyVO parentVO = freereplyProc.read(freereplyVO.getFreplyno()); // ���ۿ� ���� �θ��
    
    freereplyProc.update_seqno(freereplyVO.getFreplyno()); // �θ�ۿ� ���� seqno�� 0���� ó��

    int seqno_ch = 1; // �ڽİ��� �ִ� seqno�� 1�� ó���ϱ� ���� ����

    freereplyVO.setFreeno(parentVO.getFreeno());                       // �Խñ� ��ȣ
    
    //**************** session�� ���� �޾ƿ��� �� ���� ���� ***********************************
    if (session.getAttribute("memberno") != null) { // ȸ���� ���
      freereplyVO.setMemberno(freereplyVO.getMemberno());      // ȸ����ȣ => �θ𿡼� ������ X
    } else if (session.getAttribute("adminno") != null) { // �������� ���
      freereplyVO.setAdminno(freereplyVO.getAdminno());          // �����ڹ�ȣ => �θ𿡼� ������ X
    }
    //**************** session�� ���� �޾ƿ��� �� ���� ���� ***********************************

    freereplyVO.setFreplyname(freereplyVO.getFreplyname());          // �ۼ���   => �θ𿡼� ������ X
    freereplyVO.setFreplygrpno(parentVO.getFreplygrpno());          // �׷� ��ȣ
    freereplyVO.setFreplyansnum(parentVO.getFreplyansnum());       // ��� ����
    freereplyVO.setNowPage(nowPage);

    freereplyProc.updateAnsnum(freereplyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.

    freereplyVO.setFreplyindent(parentVO.getFreplyindent() + 1);  // �亯 ���� ���� (�亯���� �亯�ۿ� ���� ���� ����)             
    freereplyVO.setFreplyansnum(parentVO.getFreplyansnum() + 1);// �ֽ� ���� �亯 ���� ����
    freereplyVO.setSeqno(seqno_ch); // ���� ��ϵǴ� ��ۿ� ���Ͽ� seqno�� ���� 1�� ó��
    
    // *************************************************************************
    // ����(�亯) ��� ó�� ����
    // *************************************************************************

    int count = 0; // ��� ����� ó���ϱ� ���� ����
    
    if (session.getAttribute("memberno") != null) { // ȸ���� ���
      count = freereplyProc.reply(freereplyVO);
    } else if (session.getAttribute("adminno") != null) { // �������� ���
      count = freereplyProc.reply_admin(freereplyVO);
    }

    if (count == 1) {
      // System.out.println("���� ��� ����");     
      mav.setViewName("redirect:/nonuser/free/read.do?freeno=" + freereplyVO.getFreeno() + "&nowPage=" + freereplyVO.getNowPage());
    } else {
      // System.out.println("���� ��� ����");
    }

    return mav;
  }
  
  /**
   * <XMP>
   * ������ ���� �ش� ��۳��� ��ȸ
   * </XMP>
   * @param freplyno ��� ��ȣ
   * @return freplyno, freplycontent
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/reply_update.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String reply_update(int freplyno) {
    // System.out.println(" => Update() GET executed");
    
    JSONObject obj = new JSONObject(); 
    
    FreereplyVO freereplyVO = freereplyProc.read(freplyno);
    
    obj.put("freplyno", freplyno);
    obj.put("freplycontent", freereplyVO.getFreplycontent());

    return obj.toString();
  }
  
  /**
   * <XMP>
   * ��� ���� ��� ���
   * </XMP>
   * @param freereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/reply_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_update(FreereplyVO freereplyVO) {
    // System.out.println(" => Update() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int freplyno = freereplyVO.getFreplyno(); // ��۹�ȣ
    String freplycontent = freereplyVO.getFreplycontent(); // ��� ����
    int nowPage = freereplyVO.getNowPage(); // ���� ������
    int freeno = freereplyVO.getFreeno(); // �Խñ� ��ȣ
    
    int count = freereplyProc.update(freereplyVO);
    
    obj.put("count", count);
    obj.put("freplycontent", freplycontent);
    obj.put("nowPage", nowPage);

    return obj.toString();
  }
  
  /**
   * <XMP>
   * ��� ���� ��� ��� (�Ϲ� ����)
   * </XMP>
   * @param freereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/reply_delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_delete(FreereplyVO freereplyVO) {
    // System.out.println(" => delete() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int freplyno = freereplyVO.getFreplyno();
    int nowPage = freereplyVO.getNowPage();
    
    int count = freereplyProc.delete(freplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * ��� ���� ��� ���
   * (+) �θ��� ��� delete ��� content ���븸 update ����
   * </XMP>
   * @param freereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/free/update_parent.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update_parent(FreereplyVO freereplyVO) {
    // System.out.println(" => Update_parent() POST executed");
    
    JSONObject obj = new JSONObject(); 
    
    int parent_check = 0; // �� �θ� ��ۿ� ���� �˻縦 ó���ϱ� ���� ����
    int reply_check = 0;   // �� �� ������ ��� �˻縦 ó���ϱ� ���� ����
    int count = 0;          // �� �˻� �� ��ۿ� ���� ó���ϱ� ���� ����
    
    //*****************************************************************************************************
    // ��۰� ���õ� ������ ��ȸ�ϴ� �κ� ����
    //*****************************************************************************************************
    int freplyno = freereplyVO.getFreplyno();                                   // ��� ��ȣ
    freereplyVO = freereplyProc.read(freplyno);                                // ��� ��ȸ
    int freplygrpno = freereplyVO.getFreplygrpno();                          // ��� �׷��ȣ
    int nowPage = freereplyVO.getNowPage();                                // ���� ������
    int freplyindent = freereplyVO.getFreplyindent();                         // ���� ����
    //*****************************************************************************************************
    // ��۰� ���õ� ������ ��ȸ�ϴ� �κ� ����
    //*****************************************************************************************************
   
    //*****************************************************************************************************
    // �� ������ ������� �˻��ϱ� ���� �κ� ����
    //*****************************************************************************************************
    HashMap hashMap = new HashMap();
    hashMap.put("freeno", freereplyVO.getFreeno());
    hashMap.put("freplygrpno", freplygrpno);
    
    reply_check = freereplyProc.reply_check(hashMap); // ����� ���� �ִ밪 �˻�
    int max_value = reply_check;                           // ���� ���� �ִ밪
    //*****************************************************************************************************
    // �� ������ ������� �˻��ϱ� ���� �κ� ����
    //*****************************************************************************************************
    
    parent_check = freereplyProc.parent_check(freplygrpno);  // �θ� ��ۿ� ���Ͽ� ���� ����� �����ϴ��� �˻�
    
    if (parent_check == 1 || max_value == freplyindent) { // �� ���� ����� �������� �ʴ� ��� �Ǵ� ���� ������ �ִ밪�� ���
      count = freereplyProc.delete(freplyno);
    } else {             // �� ���� ����� �����ϴ� ���
      count = freereplyProc.update_parent(freplyno);
    }
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();  
  }
  
}