package dev.mvc.shared;

import java.util.ArrayList;
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

import dev.mvc.salereply.SalereplyVO;
import dev.mvc.sharedreply.SharedreplyProcInter;
import dev.mvc.sharedreply.SharedreplyVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class SharedCont {
  
  @Autowired
  @Qualifier("dev.mvc.shared.SharedProc")
  private SharedProcInter sharedProc;
  
  @Autowired
  @Qualifier("dev.mvc.sharedreply.SharedreplyProc")
  private SharedreplyProcInter sharedreplyProc;
  
  public SharedCont() {
    // System.out.println(" => SharedCont created");
  }
  
  /**
   * <XMP>
   * ��� �� ���
   * </XMP>
   * @return 
   */
  @RequestMapping(value="/user/shared/create.do", method=RequestMethod.GET )
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/create");
    
    return mav;
  }
  
  /**
   * <XMP>
   * ��� ��� ���
   * </XMP>
   * @param request
   * @param sharedVO
   * @return 
   */
  @ResponseBody
  @RequestMapping(value="/user/shared/create.do", method=RequestMethod.POST, produces = "application/text; charset=utf8")
  public String create(HttpServletRequest request, SharedVO sharedVO) {
    
    JSONObject obj = new JSONObject();
    
    String root = request.getContextPath(); // ������ ����
    
    //*********************************************************************************
    // �������� �ڵ� ����
    //*********************************************************************************
    String upDir = Tool.getRealPath(request, "/user/shared/storage"); // ���� upload ���� ��ġ
    MultipartFile file1MF = sharedVO.getFile1MF(); // ���� ���� ������
    String sharedfile="";
    long sharedsize = 0;
    String sharedfstor = ""; //  ���� ���ε� ���ϸ��� ������ ����
    String sharedtum = ""; // ����� ���
    
    if (file1MF != null) { // ������ ���ε� �� ���
      sharedsize=  file1MF.getSize(); // ���� ������
    }
    
    if (sharedsize > 0) { // ���ϻ���� 0 �̻��� ��� (���ε��� ���)
      sharedfstor = Upload.saveFileSpring(file1MF, upDir);
      sharedfile = file1MF.getOriginalFilename(); // ���� ���ϸ�
     
      if (Tool.isImage(sharedfstor)) {
        sharedtum = Tool.preview(upDir, sharedfstor, 120, 80); // Thumb �̹��� ����
      }
    }
    
    sharedVO.setSharedfile(sharedfile);
    sharedVO.setSharedfstor(sharedfstor);
    sharedVO.setSharedsize(sharedsize);
    sharedVO.setSharedtum(sharedtum);
    
    //*********************************************************************************
    // �������� �ڵ� ����
    //*********************************************************************************
    
    int count = 0;
    count = sharedProc.create(sharedVO);
    
    if (count == 1) { // �� ��Ͽ� �������� ���
      // System.out.println("�� ��� ����");
    } else { // �� ��Ͽ� �������� ���
      // System.out.println("�� ��� ����");
    }
    
    obj.put("count", count);
    
    return obj.toString();
  }

  /**
   * <XMP>
   * �˻� + ����¡ ��� ���
   * </XMP>
   * @param word �˻���
   * @param search �˻��з���
   * @param nowPage ����������
   * @return
   */
  @RequestMapping(value = "/user/shared/list.do", method = RequestMethod.GET )
  public ModelAndView list( @RequestParam(value="word", defaultValue="") String word, // �˻���
                                    @RequestParam(value="search", defaultValue="") String search, // �˻��з���
                                    @RequestParam(value="nowPage", defaultValue = "1") int nowPage) { // ����������
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/list");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);           // �˻���
    hashMap.put("search", search);        // �˻� Option Value
    hashMap.put("nowPage", nowPage); // ���� ������
    
    List <SharedVO> list = sharedProc.list_search(hashMap);
    mav.addObject("list", list); // �˻� ������ ����Ʈ ���
    
    int search_count = sharedProc.search_count(hashMap);
    mav.addObject("search_count", search_count); // �˻��� ���ڵ� ����
    
    String paging = sharedProc.paging(nowPage, search_count, word, search);
    mav.addObject("paging", paging); // ����¡ ó��
    
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  /**
   * <XMP>
   * �Խñ� ��ȸ �� ��� ��� ���
   * </XMP>
   * @param sharedno �Խñ� ��ȣ
   * @param nowPage ���� ������
   * @return sharedVO, pre_num(������ ��ȣ), post_num(������ ��ȣ)
   */
  @RequestMapping(value = "/user/shared/read.do", method = RequestMethod.GET )
  public ModelAndView read( @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
                                      @RequestParam(value = "sharedno") int sharedno) {    
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/read");
    
    //***************************************************************** 
    // �Խñ� ��ȸ ó�� ����(From "Shared" Package)
    //*****************************************************************     
    SharedVO sharedVO = sharedProc.read(sharedno);
    
    int pre_num = sharedProc.read_pre(sharedno);   // ������ ��ȣ
    int post_num = sharedProc.read_post(sharedno); // ������ ��ȣ
      
    sharedVO.setPre_num(pre_num); 
    sharedVO.setPost_num(post_num);
    mav.addObject("sharedVO", sharedVO);    
    //***************************************************************** 
    // �Խñ� ��ȸ ó�� ����
    //***************************************************************** 
    
    //***************************************************************** 
    // ��� ��� ó�� ���� (From "Sharedreply" Package)
    //***************************************************************** 
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("sharedno", sharedno);
    hashMap.put("nowPage", nowPage);
    
    List <SharedreplyVO> list_reply = sharedreplyProc.total_list_reply(hashMap);
    mav.addObject("list_reply", list_reply);
    
    int search_count = sharedreplyProc.search_count(sharedno);
    mav.addObject("search_count", search_count); // �˻��� ���ڵ� ����

    String paging = sharedreplyProc.paging(nowPage, search_count, sharedno);  // ����¡ ó��
    mav.addObject("paging", paging);              

    mav.addObject("nowPage", nowPage);         // ���� ������ ����
    //***************************************************************** 
    // ��� ��� ó�� ����
    //*****************************************************************
    
    sharedProc.increaseCnt(sharedno); // ��ȸ�� ��ȸ�� ��� ó��
    
    return mav;  
  }
  
  /**
   * <XMP>
   * ��õ�� ��� (���� ������� ����)
   * </XMP>
   * @param sharedno
   * @return
   */
/*  @RequestMapping(value = "/user/shared/increaseLike.do", method = RequestMethod.POST)
  public ModelAndView increaseLike(int sharedno) {
    ModelAndView mav = new ModelAndView();
   
    if (sharedProc.increaseLike(sharedno) == 1) {
      mav.setViewName("redirect:/user/shared/read.do?sharedno=" + sharedno);
    }
    
    return mav;
  }*/
  
  /**
   * <XMP>
   * �Խñ� ������ ���
   * </XMP>
   * @param sharedVO
   * @return
   */
  @RequestMapping(value="/user/shared/update.do", method=RequestMethod.GET)
  public ModelAndView update(int sharedno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/update");
    
    SharedVO sharedVO = sharedProc.read(sharedno);
    
    //********************������ Ư������ ó���� ���� ���� **************************
    String sharedcontent = sharedVO.getSharedcontent(); // ���� ������ �����ͼ�
    String new_sharedcontent = Tool.toJS(sharedcontent); // �ڹٽ�ũ��Ʈ�� �ν��� �� �ִ� �ڵ�� ��ȯ�Ѵ�.

    sharedVO.setSharedcontent(new_sharedcontent);
    // System.out.println("���� ����: " + sharedcontent);
    // System.out.println("��ȯ�� ���� ����: " + new_sharedcontent);
    //**********************************************************************************
    
    mav.addObject("sharedVO", sharedVO);
    
    return mav;
  }
  
  /**
   * <XMP>
   * �Խñ� ������� ���
   * </XMP>
   * @param request
   * @param sharedVO
   * @return ����� ���ڵ� ����
   */
  @ResponseBody
  @RequestMapping(value="/user/shared/update.do", method=RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update(HttpServletRequest request, SharedVO sharedVO) {
    
    JSONObject obj = new JSONObject();
    String root = request.getContextPath(); // ������ ����
    
    //*********************************************************************************
    // �������� �ڵ� ����
    //*********************************************************************************
    String upDir = Tool.getRealPath(request, "/user/shared/storage"); // Upload ��� ����
    MultipartFile file1MF = sharedVO.getFile1MF(); // ���� ����
    
    String sharedfile = ""; // ���� ���ϸ�
    String sharedfstor = ""; //  ���� ���ε� ���ϸ��� ������ ����
    long sharedsize = 0; // ���ϻ�����
    String sharedtum = ""; // �����

    if (file1MF != null) {// ���ϱ��� �޾ƿ´ٸ��� ���
      sharedsize = file1MF.getSize();
    }
    SharedVO sharedVO_od= sharedVO;
    
    String sharedyoutube = sharedVO_od.getSharedyoutube(); // Null �� ó�� ���� ���� ����
    
    if (sharedsize > 0) { // ���� ����� 0 �̻��� ��� (=������ �����Ұ��)
    
      // �ű� ���� ��Ͻ� ���� ���� ����
      Tool.deleteFile(upDir, sharedVO_od.getSharedfstor());
      Tool.deleteFile(upDir, sharedVO_od.getSharedfile());
      Tool.deleteFile(upDir, sharedVO_od.getSharedtum());
      
      sharedfstor = Upload.saveFileSpring(file1MF, upDir);
      sharedfile = file1MF.getOriginalFilename();

      if (Tool.isImage(sharedfstor)) { // ���ε� ������ �̹����� ���
        sharedtum = Tool.preview(upDir, sharedfstor, 120, 80); // Thumb �̹��� ����
      }
      
    } else { // ������ �������� �ʴ� ��� ���� ���� ���� ���
      sharedfile = sharedVO_od.getSharedfile();
      sharedfstor = sharedVO_od.getSharedfstor();
      sharedsize = sharedVO_od.getSharedsize();
      sharedtum = sharedVO_od.getSharedtum();
    }
    
    //***** Null �� (������� �ʾ������) ó�� *****
    sharedfile = Tool.checkNull(sharedfile);
    sharedfstor = Tool.checkNull(sharedfstor);
    sharedtum = Tool.checkNull(sharedtum);
    sharedyoutube = Tool.checkNull(sharedyoutube);
    //************************************************

    sharedVO.setSharedyoutube(sharedyoutube);
    sharedVO.setSharedfile(sharedfile);
    sharedVO.setSharedfstor(sharedfstor);
    sharedVO.setSharedsize(sharedsize);
    sharedVO.setSharedtum(sharedtum);
    
    //*********************************************************************************
    // �������� �ڵ� ����
    //*********************************************************************************
    
    int sharedno = sharedVO.getSharedno();
    
    int check = sharedProc.member_check(sharedVO);
    int count = 0; 

    if (check == 1) { // ��ϵ� ���̵�� ��ġ�ϴ� ���
      count = sharedProc.update(sharedVO);
    
      if(count == 1) { 
        // System.out.println("���� ó�� ����");
      } else { // ���� ó���� �������� ���
        // System.out.println("���� ó�� ����");
      }
    } else { // ��ϵ� ���̵�� ��ġ���� �ʴ� ���
      // System.out.println("���̵� ��ġ X");
    }
      
    obj.put("count", count);
    obj.put("sharedno", sharedno);
     
    return obj.toString();
  }
  
  /**
   * <XMP>
   * �Խñ� ������ ��� (������ ������� ����)
   * </XMP>
   * @param sharedno
   * @return
   */
/*  @RequestMapping(value = "/user/shared/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int sharedno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/delete");
    
    SharedVO sharedVO = sharedProc.read(sharedno);
    mav.addObject("sharedVO", sharedVO);
    
    return mav;
  }*/
  
  /**
   * <XMP>
   * �Խñ� ������� ���
   * </XMP>
   * @param sharedno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String delete(HttpServletRequest request, int sharedno) {

    JSONObject obj = new JSONObject();
    String root = request.getContextPath(); // ������ ����
    
    SharedVO sharedVO = sharedProc.read(sharedno);
    
    // *************************************************************************
    // ���� ���� �ڵ� ����
    // *************************************************************************
    String upDir = Tool.getRealPath(request, "/user/shared/storage"); // ���ε� ���
    
    Tool.deleteFile(upDir, sharedVO.getSharedfstor()); // ���� ���ϸ� ����
    Tool.deleteFile(upDir, sharedVO.getSharedfile()); // ���ε� ���ϸ� ����
    Tool.deleteFile(upDir, sharedVO.getSharedtum()); // ���� ����� ����
    // *************************************************************************
    // ���� ���� �ڵ� ����
    // *************************************************************************
          
    int check = sharedProc.member_check(sharedVO); // ���̵� �˻�
    int count_check;
    int count = 0; 
    
    if (check == 1) { // ��ϵ� ���̵�� ��ġ�ϴ� ���
      
      count_check = sharedreplyProc.delete_all(sharedno); // �ش� �Խñ��� ��� ��ü ����
      count = sharedProc.delete(sharedno);                   // �Խñ� ����
      
      if (count == 1) { // ���� ó���� �������� ���
        // System.out.println("���� ó�� ����");
      } else { // ���� ó���� �������� ���
        // System.out.println("���� ó�� ����");
      }
      
    } else { // ��ϵ� ���̵�� ��ġ���� �ʴ� ���
      // System.out.println("���̵� ��ġ X");
    }
    
    obj.put("count", count);
    
    return obj.toString();
  }
  
  /**
   * <XMP>
   * Grid ��� ���
   * </XMP>
   * @param word �˻���
   * @param search �˻��з���
   * @param nowPage ����������
   * @return
   */
  @RequestMapping(value = "/user/shared/list_grid.do", method = RequestMethod.GET )
  public ModelAndView list_gird( @RequestParam(value="word", defaultValue="") String word, // �˻���
                                          @RequestParam(value="search", defaultValue="") String search, // �˻��з���
                                          @RequestParam(value="nowPage", defaultValue = "1") int nowPage) { // ����������
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/shared/list_grid");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);           // �˻���
    hashMap.put("search", search);        // �˻� Option Value
    hashMap.put("nowPage", nowPage); // ���� ������
    
    List <SharedVO> list_grid = sharedProc.list_grid(hashMap);
    mav.addObject("list_grid", list_grid); // �˻� ������ ����Ʈ ���
    
    int search_count = sharedProc.search_count(hashMap);
    mav.addObject("search_count", search_count); // �˻��� ���ڵ� ����
    
    String paging_grid = sharedProc.paging_grid(nowPage, search_count, word, search);
    mav.addObject("paging_grid", paging_grid); // ����¡ ó��
    
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  /**
   * <XMP>
   * ��� ���
   * <insert id="create" parameterType="SharedreplyVO">
   * </XMP>
   * @param request
   * @param sharedreplyVO
   * @return
   */
  @RequestMapping(value = "/user/shared/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(HttpServletRequest request, SharedreplyVO sharedreplyVO) {
    // System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int sharedno = sharedreplyVO.getSharedno();
    int count = sharedreplyProc.create(sharedreplyVO);
    
    if (count == 1) {
      // System.out.println("��۵�� ����");
      mav.setViewName("redirect:/user/shared/read.do?sharedno=" + sharedno);
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
   * @param salereplyVO
   * @return
   */
  @RequestMapping(value = "/user/shared/rereply.do", method = RequestMethod.POST)
  public ModelAndView rereply(HttpServletRequest request, SharedreplyVO sharedreplyVO) {
    // System.out.println("--> rereply() POST called.");
    ModelAndView mav = new ModelAndView();

    // *************************************************************************
    // ����(�亯) ��� ó�� ����
    // *************************************************************************
 
    SharedreplyVO parentVO = sharedreplyProc.read(sharedreplyVO.getShreplyno()); // ���ۿ� ���� �θ��

    sharedreplyProc.update_seqno(sharedreplyVO.getShreplyno());
    
    int seqno_ch = 1; // �ڽİ��� �ִ� seqno�� 1�� ó���ϱ� ���� ����

    sharedreplyVO.setSharedno(parentVO.getSharedno());                // �Խñ� ��ȣ
    sharedreplyVO.setMemberno(sharedreplyVO.getMemberno());      // ȸ����ȣ => �θ𿡼� ������ X
    sharedreplyVO.setShreplyname(sharedreplyVO.getShreplyname());  // �ۼ��� => �θ𿡼� ������ X
    sharedreplyVO.setShreplygrpno(parentVO.getShreplygrpno());      // �׷� ��ȣ
    sharedreplyVO.setShreplyansnum(parentVO.getShreplyansnum());  // ��� ����

    sharedreplyProc.updateAnsnum(sharedreplyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.

    sharedreplyVO.setShreplyindent(parentVO.getShreplyindent() + 1);   // �亯 ���� ���� (�亯���� �亯�ۿ� ���� ���� ����)             
    sharedreplyVO.setShreplyansnum(parentVO.getShreplyansnum()+ 1); // �ֽ� ���� �亯 ���� ����
    sharedreplyVO.setSeqno(seqno_ch);
    
    // *************************************************************************
    // ����(�亯) ��� ó�� ����
    // *************************************************************************

    int count = sharedreplyProc.reply(sharedreplyVO);
    
    if (count == 1) {
      // System.out.println("���� ��� ����");     
      mav.setViewName("redirect:/user/shared/read.do?sharedno=" + sharedreplyVO.getSharedno());
    } else {
      // System.out.println("���� ��� ����");
    }

    return mav;
  }
  
  /**
   * <XMP>
   * ������ ���� �ش� ����� ��� ���� read
   * </XMP>
   * @param shreplyno ��� ��ȣ
   * @return shreplyno, shreplycontent
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/reply_update.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String reply_update(int shreplyno) {
    // System.out.println(" => Update() GET executed");
    
    JSONObject obj = new JSONObject(); 
    
    SharedreplyVO sharedreplyVO = sharedreplyProc.read(shreplyno);
    
    obj.put("shreplyno", shreplyno);
    obj.put("shreplycontent", sharedreplyVO.getShreplycontent());

    return obj.toString();
  }
  
  /**
   * <XMP>
   * ��� ���� ��� ���
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/reply_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_update(SharedreplyVO sharedreplyVO) {
    // System.out.println(" => Update() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int shreplyno = sharedreplyVO.getShreplyno(); // ��۹�ȣ
    String shreplycontent = sharedreplyVO.getShreplycontent(); // ��� ����
    int nowPage = sharedreplyVO.getNowPage(); // ���� ������
    int sharedno = sharedreplyVO.getSharedno(); // �Խñ۹�ȣ
    
    int count = sharedreplyProc.update(sharedreplyVO);
    
    obj.put("count", count);
    obj.put("shreplycontent", shreplycontent);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * ��� ���� ��� ���
   * </XMP>
   * @param sharedreplyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/reply_delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_delete(SharedreplyVO sharedreplyVO) {
    // System.out.println(" => delete() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int shreplyno = sharedreplyVO.getShreplyno();
    int nowPage = sharedreplyVO.getNowPage();
    
    int count = sharedreplyProc.delete(shreplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * �θ� ����� delete�� ���Ͽ� update ó��
   * (= "������ �����Դϴ�."�� ����ϱ� ���� ���� ����)
   * </XMP>
   * @param salereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/shared/update_parent.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update_parent(SharedreplyVO sharedreplyVO) {
    // System.out.println(" => Update_parent() POST executed");
    
    JSONObject obj = new JSONObject(); 
      
    int shreplyno = sharedreplyVO.getShreplyno();
    int nowPage = sharedreplyVO.getNowPage();

    int count = sharedreplyProc.update_parent(shreplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  
  

}
