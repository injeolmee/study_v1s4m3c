package dev.mvc.sale;

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

import dev.mvc.freereply.FreereplyVO;
import dev.mvc.salereply.SalereplyProcInter;
import dev.mvc.salereply.SalereplyVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class SaleCont {

  @Autowired
  @Qualifier("dev.mvc.sale.SaleProc")
  private SaleProcInter saleProc;

  @Autowired
  @Qualifier("dev.mvc.salereply.SalereplyProc")
  private SalereplyProcInter salereplyProc;

  public SaleCont() {
    // System.out.println(" => SaleCont created ");
  }

  /**
   * ��� �� ��� 
   * @return
   */
  @RequestMapping(value = "/user/sale/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    // System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/create");

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
  @RequestMapping(value = "/user/sale/create.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String create(HttpServletRequest request, SaleVO saleVO) {
    // System.out.println("--> create() POST executed");
    JSONObject obj = new JSONObject();
    
    String root = request.getContextPath(); // ������ ����
    
    ///// NULL �� ó�� ���� ���� ���� ////////
    String saletel = saleVO.getSaletel(); 
    String saleaddress = saleVO.getSaleaddress();
    String saleemail = saleVO.getSaleemail();
    //////////////////////////////////////////////////
    
    //***************************************************************** 
    // ���� ���� �ڵ� ����
    //***************************************************************** 
    String upDir = Tool.getRealPath(request, "/user/sale/storage"); // ���� upload ���� ��ġ
    MultipartFile file1MF = saleVO.getFile1MF(); // ���ε� �� ����
    long salesize = 0;
    String salefile="";
    String salefstor = ""; // ���� ���ε� ���ϸ��� ������ ����
    String saletum = ""; // ����� ���
    
    if (file1MF != null) { // ���ϱ��� �޾ƿ´ٸ��� ���
      salesize = file1MF.getSize();
    }
    
    if (salesize > 0) { // ���ϻ���� 0 �̻��� ��� (= ���ε��� ���)
      salefstor = Upload.saveFileSpring(file1MF, upDir);
      salefile = file1MF.getOriginalFilename(); // ���� ���ϸ� (����ڿ��� ���� ���ϸ�)

      if (Tool.isImage(salefstor)) { // �̹��� �� ���
        saletum = Tool.preview(upDir, salefstor, 200, 250); // Thumb �̹��� ����
      }
    }
    
    //**** Null �� (=�� ������� �ʾ������) ó�� ***
    salefile = Tool.checkNull(salefile);
    salefstor = Tool.checkNull(salefstor);
    saletum = Tool.checkNull(saletum);
    saletel = Tool.checkNull(saletel);
    saleaddress = Tool.checkNull(saleaddress);
    saleemail = Tool.checkNull(saleemail);
    //**************************************************
    
    saleVO.setSalefile(salefile);
    saleVO.setSalefstor(salefstor);
    saleVO.setSalesize(salesize);
    saleVO.setSaletum(saletum);

    //***************************************************************** 
    // ���� ���� �ڵ� ����
    //***************************************************************** 

    int count = saleProc.create(saleVO);
    
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
   * �˻�+ ����¡ ��� ��� 
   * </XMP>
   * @param word �˻���
   * @param search �˻� �з���
   * @param nowPage ���� ������
   * @return
   */
  @RequestMapping(value = "/user/sale/list.do", method = RequestMethod.GET)
  public ModelAndView list(@RequestParam(value = "word", defaultValue = "") String word,  // �˻���
                                   @RequestParam(value = "search", defaultValue = "") String search,  // �˻��з���
                                   @RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {  // ����������

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/list");

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);           // �˻���
    hashMap.put("search", search);        // �˻� Option Value
    hashMap.put("nowPage", nowPage); // ���� ������

    List<SaleVO> list = saleProc.list_search(hashMap);
    mav.addObject("list", list);              // �˻� ������ ����Ʈ ���

    int search_count = saleProc.search_count(hashMap);
    mav.addObject("search_count", search_count); // �˻��� ���ڵ� ����

    String paging = saleProc.paging(nowPage, search_count, word, search);
    mav.addObject("paging", paging);  // ����¡ ó��

    mav.addObject("nowPage", nowPage);

    return mav;
  }

  /**
   * <XMP> 
   * Grid�� ��� ��� 
   * </XMP>
   * @param word �˻���
   * @param search �˻��з���
   * @param nowPage ����������
   * @return
   */
  @RequestMapping(value = "/user/sale/list_grid.do", method = RequestMethod.GET)
  public ModelAndView list_grid(@RequestParam(value = "word", defaultValue = "") String word, // �˻���
                                         @RequestParam(value = "search", defaultValue = "") String search, // �˻��з���
                                         @RequestParam(value = "nowPage", defaultValue = "1") int nowPage) { // ����������

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/list_grid");

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word); // �˻���
    hashMap.put("search", search); // �˻� Option Value
    hashMap.put("nowPage", nowPage); // ���� ������

    List<SaleVO> list_grid = saleProc.list_grid(hashMap);
    mav.addObject("list_grid", list_grid); // �˻� ������ ����Ʈ ���

    int search_count = saleProc.search_count(hashMap);
    mav.addObject("search_count", search_count); // �˻��� ���ڵ� ����

    String paging_grid = saleProc.paging_grid(nowPage, search_count, word, search);
    mav.addObject("paging_grid", paging_grid); // ����¡ ó��

    mav.addObject("nowPage", nowPage);

    return mav;
  }

  /**
   * �Խñ� ��ȸ �� ��� ��� ���
   * @param saleno �Խñ� ��ȣ
   * @param nowPage ���� ������
   * @return saleVO, pre_num(������ ��ȣ), post_num(������ ��ȣ)
   */
  @RequestMapping(value = "/user/sale/read.do", method = RequestMethod.GET)
  public ModelAndView read( @RequestParam(value = "nowPage", defaultValue = "1") int nowPage,
                                      @RequestParam(value = "saleno") int saleno) {
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/read");

    //***************************************************************** 
    // �Խñ� ��ȸ ó�� ����(From "Sale" Package)
    //***************************************************************** 
    SaleVO saleVO = saleProc.read(saleno);
    int pre_num = saleProc.read_pre(saleno); // ������ ��ȣ
    int post_num = saleProc.read_post(saleno); // ������ ��ȣ

    saleVO.setPre_num(pre_num);
    saleVO.setPost_num(post_num);
    mav.addObject("saleVO", saleVO);
    //***************************************************************** 
    // �Խñ� ��ȸ ó�� ����
    //***************************************************************** 

    //***************************************************************** 
    // ��� ��� ó�� ���� (From "Salereply" Package)
    //***************************************************************** 
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("saleno", saleno);
    hashMap.put("nowPage", nowPage);
    
    List<SalereplyVO> list_reply = salereplyProc.total_list_reply(hashMap);
    mav.addObject("list_reply", list_reply);
    
    int search_count = salereplyProc.search_count(saleno);
    mav.addObject("search_count", search_count); // �˻��� ���ڵ� ����

    String paging = salereplyProc.paging(nowPage, search_count, saleno);
    mav.addObject("paging", paging);               // ����¡ ó��

    saleVO.setNowPage(nowPage);
    mav.addObject("nowPage", nowPage);        // ���� ������ ����
    //***************************************************************** 
    // ��� ��� ó�� ����
    //*****************************************************************

    saleProc.increaseCnt(saleno); // ��ȸ �� ��ȸ�� ��� ó��

    return mav;
  }

  /**
   * <XMP> 
   * �Խñ� ������ ���
   *  <XMP>
   * @param saleVO
   * @return
   */
  @RequestMapping(value = "/user/sale/update.do", method = RequestMethod.GET)
  public ModelAndView update(int saleno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/update");

    SaleVO saleVO = saleProc.read(saleno);
   
    //**************************************************************************
    // ������ Ư������ ó���� ���� ���� ���� 
    //**************************************************************************
    String salecontent = saleVO.getSalecontent(); // ���� ������ �����ͼ�
    String new_salecontent = Tool.toJS(salecontent); // �ڹٽ�ũ��Ʈ�� �ν��� �� �ִ� �ڵ�� ��ȯ�Ѵ�.
    
    saleVO.setSalecontent(new_salecontent);
    // System.out.println("���� ����: " + salecontent);
    // System.out.println("��ȯ�� ���� ����: " + new_salecontent);
    //**************************************************************************
    // ������ Ư������ ó���� ���� ���� ����
    //**************************************************************************

    mav.addObject("saleVO", saleVO);

    return mav;
  }

  /**
   * <XMP> 
   * �Խñ� ������� ��� 
   * </XMP>
   * @param request
   * @param saleVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update(HttpServletRequest request, SaleVO saleVO) {
    
    // System.out.println("=> update post called");
    // System.out.println("Cont���� saleno: " + saleVO.getSaleno() + " �׸��� memberno: " + saleVO.getMemberno());
    // System.out.println("salefstor: " + saleVO.getSalefstor());

    JSONObject obj = new JSONObject();

    //***************************************************************** 
    // ���� ���� �ڵ� ����
    //*****************************************************************
    String upDir = Tool.getRealPath(request, "/user/sale/storage"); // ���� upload ���� ��ġ
    MultipartFile file1MF = saleVO.getFile1MF(); // ���ε� �� ����
    String salefile = ""; // ���� ���ϸ�
    String salefstor = ""; // ���� ���ε� ���ϸ��� ������ ����
    long salesize = 0; // ���� ������
    String saletum = ""; // ����� ���
    
    if (file1MF != null) { // ���ϱ��� �޾ƿ´ٸ��� ���
      salesize = file1MF.getSize();
    }

    // System.out.println("salesize: " + salesize);
    
    SaleVO saleVO_od = saleVO; // �޾ƿ� saleVO�� saleVO_od�� ���� (= ���ε��� ��� �����ϱ� ���� �������� �и��س���)
    
    ////////////// Null �� ó���� ���� ���� ���� //////////////////
    String saletel = saleVO_od.getSaletel();
    String saleaddress = saleVO_od.getSaleaddress();
    String saleemail = saleVO_od.getSaleemail();
    ///////////////////////////////////////////////////////////////////////

    if (salesize > 0) { // �� ���ϻ���� 0 �̻��� ��� (������ ���)

      //////////// ���� ���� ���� ó�� //////////////////
      Tool.deleteFile(upDir, saleVO_od.getSalefstor());
      Tool.deleteFile(upDir, saleVO_od.getSalefile());
      Tool.deleteFile(upDir, saleVO_od.getSaletum());
      /////////////////////////////////////////////////////////

      salefstor = Upload.saveFileSpring(file1MF, upDir);
      salefile = file1MF.getOriginalFilename();

      if (Tool.isImage(salefstor)) { // �̹��� �� ���
        saletum = Tool.preview(upDir, salefstor, 200, 250); // Thumb �̹��� ����
      }

    } else { // �� ������ �������� �ʴ� ��� ���� ���� ���� ���
      salefile = saleVO_od.getSalefile();
      salefstor = saleVO_od.getSalefstor();
      saletum = saleVO_od.getSaletum();
      salesize = saleVO_od.getSalesize();
    }

    //***** Null �� (������� �ʾ������) ó�� *****
    salefile = Tool.checkNull(salefile);
    salefstor = Tool.checkNull(salefstor);
    saletum = Tool.checkNull(saletum);
    saletel = Tool.checkNull(saletel);
    saleaddress = Tool.checkNull(saleaddress);
    saleemail = Tool.checkNull(saleemail);
    //************************************************
    
    saleVO.setSalefile(salefile);
    saleVO.setSalefstor(salefstor);
    saleVO.setSalesize(salesize);
    saleVO.setSaletum(saletum);
    
    //***************************************************************** 
    // ���� ���� �ڵ� ����
    //*****************************************************************

    int check = saleProc.member_check(saleVO_od); // ���̵� �˻�
    int count=0; // ���̵� �˻簡 ������ ��� ó���� ����
    
    if (check == 1) { // ��ϵ� ���̵�� ��ġ�ϴ� ���
      count = saleProc.update(saleVO);
      
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
   * �Խñ� ������ ��� (������ ���� ����)
   * </XMP>
   * @param saleno
   * @return
   */
/*  @RequestMapping(value = "/user/sale/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int saleno) {
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/user/sale/delete");

    SaleVO saleVO = saleProc.read(saleno);
    mav.addObject("saleVO", saleVO);

    return mav;
  }*/

  /**
   * <XMP> 
   * �Խñ� ������� ���
   *  </XMP>
   * @param sharedno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/delete.do", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public String delete(HttpServletRequest request, int saleno) {
    
    JSONObject obj = new JSONObject();
    String root = request.getContextPath(); // ������ ����
    
    SaleVO saleVO = saleProc.read(saleno);

    // *************************************************************************
    // ���� ���� �ڵ� ����
    // *************************************************************************
    String upDir = Tool.getRealPath(request, "/user/sale/storage"); // ���ε� ���

    Tool.deleteFile(upDir, saleVO.getSalefstor()); // ���� ���ϸ� ����
    Tool.deleteFile(upDir, saleVO.getSalefile()); // ���ε� ���ϸ� ����
    Tool.deleteFile(upDir, saleVO.getSaletum()); // ���� ����� ����
    // *************************************************************************
    // ���� ���� �ڵ� ����
    // *************************************************************************

    int check = saleProc.member_check(saleVO);
    int count_check;
    int count = 0;
    
    if (check == 1) { // ��ϵ� ���̵�� ��ġ�ϴ� ���
      
      count_check = salereplyProc.delete_all(saleno); // �ۿ� �ش��ϴ� ��� ��� ����
      count = saleProc.delete(saleno);                   // �Խñ� ����

      if (count == 1) { // ���� ó���� �������� ���
        // System.out.println("�� ���� ����");
      } else { // ���� ó���� �������� ���
        // System.out.println("�� ���� ����");
      }
      
    } else { // ��ϵ� ���̵�� ��ġ���� �ʴ� ���
      // System.out.println("���̵� ��ġ X");
    }
    
    obj.put("count", count);
    
    return obj.toString();
    
  }

  /**
   * <XMP> 
   * ��� ��� 
   * <insert id="create" parameterType="SalereplyVO" > 
   * </XMP>
   * @param request
   * @param salereplyVO
   * @return
   */
  @RequestMapping(value = "/user/sale/reply.do", method = RequestMethod.POST)
  public ModelAndView reply(HttpServletRequest request, SalereplyVO salereplyVO) {
    
    ModelAndView mav = new ModelAndView();

    int saleno = salereplyVO.getSaleno();
    int count = salereplyProc.create(salereplyVO);

    if (count == 1) {
      // System.out.println("��۵�� ����");
      mav.setViewName("redirect:/user/sale/read.do?saleno=" + saleno);
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
  @RequestMapping(value = "/user/sale/rereply.do", method = RequestMethod.POST)
  public ModelAndView rereply(HttpServletRequest request, SalereplyVO salereplyVO) {
    
    // System.out.println("--> reply() POST called.");
    ModelAndView mav = new ModelAndView();

    // *************************************************************************
    // ����(�亯) ��� ó�� ����
    // *************************************************************************
    
    SalereplyVO parentVO = salereplyProc.read(salereplyVO.getSreplyno()); // ���ۿ� ���� �θ��

    salereplyProc.update_seqno(salereplyVO.getSreplyno()); // �θ�ۿ� ���� seqno�� 0���� ó��
    
    int seqno_ch = 1; // �ڽİ��� �ִ� seqno�� 1�� ó���ϱ� ���� ����
    
    salereplyVO.setSaleno(parentVO.getSaleno());                  // �Խñ� ��ȣ
    salereplyVO.setMemberno(salereplyVO.getMemberno());    // ȸ����ȣ => �θ𿡼� ������ �ȵȴ�! (����)
    salereplyVO.setSreplyname(salereplyVO.getSreplyname());   // �ۼ���   => �θ𿡼� ������ �ȵȴ�! (����)
    salereplyVO.setSreplygrpno(parentVO.getSreplygrpno());    // �׷� ��ȣ
    salereplyVO.setSreplyansnum(parentVO.getSreplyansnum()); // ��� ����
    
    salereplyProc.updateAnsnum(salereplyVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.

    salereplyVO.setSreplyindent(parentVO.getSreplyindent() + 1);    // �亯 ���� ���� (�亯���� �亯�ۿ� ���� ���� ����)             
    salereplyVO.setSreplyansnum(parentVO.getSreplyansnum() + 1); // �ֽ� ���� �亯 ���� ����
    salereplyVO.setSeqno(seqno_ch); // ���� ��ϵǴ� ��ۿ� ���Ͽ� seqno�� ���� 1�� ó��
    
    // *************************************************************************
    // ����(�亯) ��� ó�� ����
    // *************************************************************************

    int count = salereplyProc.reply(salereplyVO);
    
    if (count == 1) {
      // System.out.println("���� ��� ����");     
      mav.setViewName("redirect:/user/sale/read.do?saleno=" + salereplyVO.getSaleno());
    } else {
      // System.out.println("���� ��� ����");
    }

    return mav;
  }
  
  /**
   * <XMP>
   * ������ ���� �ش� ����� ��� ���� read
   * </XMP>
   * @param sreplyno ��� ��ȣ
   * @return sreplyno, sreplycontent
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/reply_update.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
  public String reply_update(int sreplyno) {
    
    // System.out.println(" => Update() GET executed");
    JSONObject obj = new JSONObject(); 
    
    SalereplyVO salereplyVO = salereplyProc.read(sreplyno);
    
    obj.put("sreplyno", sreplyno);
    obj.put("sreplycontent", salereplyVO.getSreplycontent());

    return obj.toString();
  }
  
  /**
   * <XMP>
   * ��� ���� ��� ���
   * </XMP>
   * @param salereplyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/reply_update.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_update(SalereplyVO salereplyVO) {
    
    // System.out.println(" => Update() POST executed");
    JSONObject obj = new JSONObject(); 
      
    int sreplyno = salereplyVO.getSreplyno(); // ��� ��ȣ
    String sreplycontent = salereplyVO.getSreplycontent(); // ��� ����
    int nowPage = salereplyVO.getNowPage(); // ���� ������
    int saleno = salereplyVO.getSaleno(); // �Խñ� ��ȣ
    
    int count = salereplyProc.update(salereplyVO);
    
    obj.put("count", count);
    obj.put("sreplycontent", sreplycontent);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * ��� ���� ��� ���
   * </XMP>
   * @param salereplyVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/reply_delete.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String reply_delete(SalereplyVO salereplyVO) {
    
    // System.out.println(" => delete() POST executed");
    JSONObject obj = new JSONObject(); 
      
    int sreplyno = salereplyVO.getSreplyno();
    int nowPage = salereplyVO.getNowPage();
    
    int count = salereplyProc.delete(sreplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  
  /**
   * <XMP>
   * �θ� ����� delete�� ���Ͽ� update ó��
   * (= "���� �� ����Դϴ�."��� �������� ����Ϸ��� ��)
   * </XMP>
   * @param salereplyVO
   * @param response
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/user/sale/update_parent.do", method = RequestMethod.POST, produces = "application/text; charset=utf8")
  public String update_parent(SalereplyVO salereplyVO) {
    
    // System.out.println(" => Update_parent() POST executed");
    JSONObject obj = new JSONObject(); 
      
    int sreplyno = salereplyVO.getSreplyno();
    int nowPage = salereplyVO.getNowPage();
    
    int count = salereplyProc.update_parent(sreplyno);
    
    obj.put("count", count);
    obj.put("nowPage", nowPage);

    return obj.toString();
    
  }
  

}
