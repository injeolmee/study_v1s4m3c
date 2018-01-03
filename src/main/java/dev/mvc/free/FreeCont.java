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
  public String create(HttpServletRequest request, FreeVO freeVO) {

    JSONObject obj = new JSONObject();

    int count = freeProc.create(freeVO);
    
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
   * �Խñ� ��ȸ �� ��� ��� ���
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
  public String update(FreeVO freeVO) {
    
    JSONObject obj = new JSONObject(); 
 
    int freeno = freeVO.getFreeno();

    int check = freeProc.member_check(freeVO);
    int count= 0;
    
    if (check == 1) { // ��ϵ� ��� ���̵�� ��ġ�ϴ� ���
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
   * ��õ�� ���� (���� ������� ����)
   * </XMP>
   * @param freeno
   * @return
   */
  /*@RequestMapping(value="/user/free/increaseLike.do", method=RequestMethod.POST)
  public ModelAndView increaseLike(int freeno) {
    // System.out.println(" --> freelikeup POST create");
    ModelAndView mav = new ModelAndView();
    
    if (freeProc.increaseLike(freeno) == 1) {
      mav.setViewName("redirect:/nonuser/free/read.do?freeno=" + freeno);
    }
    
    return mav;
  }*/
  
  /**
   * <XMP>
   * �Խñ� ���� �� ��� (���� ������� ����)
   * </XMP> 
   * @param freeno
   * @return
   */
/*  @RequestMapping(value = "/user/free/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int freeno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/free/delete");
    
    mav.addObject("freeno", freeno);
    
    return mav;
  }*/
  
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
  public String delete(HttpServletRequest request, int freeno) {

    JSONObject obj = new JSONObject(); 
    String root = request.getContextPath(); // ���� ��� ����
    
    FreeVO freeVO = freeProc.read(freeno);
    int check = freeProc.member_check(freeVO); // ���̵� �˻�
    
    int count_all = 0; // ���̵� �˻� ������ ó���� ����1
    int count = 0; // ���̵� �˻� ������ ó���� ����2
  
    if (check == 1) { // ��ϵ� ��� ���̵�� ��ġ�ϴ� ���
      
      count_all = freereplyProc.delete_all(freeno); // �� ��� ��ü ����
      count = freeProc.delete(freeno);               // �� �Խñ� ����
      
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
  public ModelAndView reply(HttpServletRequest request, FreereplyVO freereplyVO, int nowPage) {
    // System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int freeno = freereplyVO.getFreeno();
    freereplyVO.setNowPage(nowPage);
    int count = freereplyProc.create(freereplyVO);
    
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
  public ModelAndView rereply(HttpServletRequest request, FreereplyVO freereplyVO, int nowPage) {
    // System.out.println("--> rereply() POST called.");
    ModelAndView mav = new ModelAndView();

    // *************************************************************************
    // ����(�亯) ��� ó�� ����
    // *************************************************************************
 
    FreereplyVO parentVO = freereplyProc.read(freereplyVO.getFreplyno()); // ���ۿ� ���� �θ��
    
    freereplyProc.update_seqno(freereplyVO.getFreplyno()); // �θ�ۿ� ���� seqno�� 0���� ó��

    int seqno_ch = 1; // �ڽİ��� �ִ� seqno�� 1�� ó���ϱ� ���� ����

    freereplyVO.setFreeno(parentVO.getFreeno());                       // �Խñ� ��ȣ
    freereplyVO.setMemberno(freereplyVO.getMemberno());          // ȸ����ȣ => �θ𿡼� ������ X 
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

    int count = freereplyProc.reply(freereplyVO);
    
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
   * ������ ���� �ش� ����� ��� ���� read
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
      
    int freplyno = freereplyVO.getFreplyno();
    int nowPage = freereplyVO.getNowPage();
    
    int count = freereplyProc.update_parent(freplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();  
  }
  
}
