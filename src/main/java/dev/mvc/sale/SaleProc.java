package dev.mvc.sale;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import dev.mvc.shared.Shared;
import dev.mvc.shared.SharedVO;
import nation.web.tool.Tool;

@Repository("dev.mvc.sale.SaleProc")
public class SaleProc implements SaleProcInter {

  @Autowired
  @Qualifier("dev.mvc.sale.SaleDAO")
  private SaleDAOInter saleDAO;

  public SaleProc() {
    // System.out.println(" => SaleProc created" );
  }

  /* ��� */
  @Override
  public int create(SaleVO saleVO) {
    int count = saleDAO.create(saleVO);
    return count;
  }

  /* ��� + �˻� + ����¡ */
  @Override
  public List<SaleVO> list_search(HashMap hashMap) {
    
    int beginOfPage = ((Integer) hashMap.get("nowPage") - 1) * Sale.RECORD_PER_PAGE;

    int startNum = beginOfPage + 1;                            // ������ Rownum (1)
    int endNum = beginOfPage + Sale.RECORD_PER_PAGE; // ������ Rownum (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);

    List<SaleVO> list = saleDAO.list_search(hashMap);

    Iterator<SaleVO> iter = list.iterator();

    //*************** ������ �� ��� "..."�� ó���ϴ� ���� ***************
    while (iter.hasNext()) {
      SaleVO saleVO = iter.next();
      String saletitle = Tool.textLength(saleVO.getSaletitle(), 25);
      saleVO.setSaletitle(saletitle);
    }
    //*********************************************************************
    
    return list;
  }

  /* ��� Grid�� */
  @Override
  public List<SaleVO> list_grid(HashMap hashMap) {

    int beginOfPage = ((Integer) hashMap.get("nowPage") - 1) * Sale.RECORD_PER_PAGE_G; // 0
    
    int startNum = beginOfPage + 1;                                // ������ Rownum (1)
    int endNum = beginOfPage + Sale.RECORD_PER_PAGE_G; // ������ Rownum (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);

    List<SaleVO> list_grid = saleDAO.list_search(hashMap);

    Iterator<SaleVO> iter = list_grid.iterator();

    //*************** ������ �� ��� "..."�� ó���ϴ� ���� ***************
    while (iter.hasNext()) { 
      SaleVO saleVO = iter.next();
      String saletitle = Tool.textLength(saleVO.getSaletitle(), 20);
      saleVO.setSaletitle(saletitle);
    }
    //***********************************************************************

    return list_grid;
  }

  /* �˻��� ���ڵ� ���� ���� */
  @Override
  public int search_count(HashMap hashMap) {
    int count = saleDAO.search_count(hashMap);
    return count;
  }

  /* ����� ����¡ */
  @Override
  public String paging(int nowPage, int search_count, String word, String search) {
    // ���� (1) public static int RECORD_PER_PAGE = 10; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 10; ���� ������ ��

    int totalPage = (int) (Math.ceil((double) search_count / Sale.RECORD_PER_PAGE)); // ��ü ������
    int totalGrp = (int) (Math.ceil((double) totalPage / Sale.PAGE_PER_BLOCK)); // ��ü �׷�
    int nowGrp = (int) (Math.ceil((double) nowPage / Sale.PAGE_PER_BLOCK)); // ���� �׷�
    int startPage = ((nowGrp - 1) * Sale.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)
    int endPage = (nowGrp * Sale.PAGE_PER_BLOCK); // Ư�� �׷��� ������ ��� ����

    StringBuffer str = new StringBuffer();

    str.append("<style type='text/css'>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    text-align: center;");
    str.append("    font-size: 1em;");
    str.append("    border: 0px;"); // str.append(" border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/");
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    text-align: center;");
    str.append("    border-radius: 5px;");
    str.append("    background-color: #FECE1A;");
    str.append("    color: #666666;");
    str.append("    font-size: 1em;");
    str.append("    font-weight: bold;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #ffffff;");
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/");
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");

    int _nowPage = (nowGrp - 1) * Sale.PAGE_PER_BLOCK; // ���� �������� �̵� (���� ������ �׷� - 1)
    if (nowGrp >= 2) {
      str.append("<span class='span_box_1'><A href='/study/user/sale/list.do?&word=" + word + "&nowPage=" + _nowPage + "'>����</A></span>");
    }

    for (int i = startPage; i <= endPage; i++) {
      if (i > totalPage) {
        break;
      }

      if (nowPage == i) {
        str.append("<span class='span_box_2'>" + i + "</span>"); // ���� ������, ����
      } else {
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='/study/user/sale/list.do?word=" + word + "&search=" + search + "&nowPage=" + i+ "'>" + i + "</A></span>");
      }
    }

    _nowPage = (nowGrp * Sale.PAGE_PER_BLOCK) + 1; // 10�� ���� �������� �̵�
    if (nowGrp < totalGrp) {
      str.append("<span class='span_box_1'><A href='/study/user/sale/list.do?&word=" + word + "&nowPage=" + _nowPage + "&search="+ search + "'>����</A></span>");
    }
    str.append("</DIV>");

    return str.toString();
  }

  /* grid�� ����¡ */
  @Override
  public String paging_grid(int nowPage, int search_count, String word, String search) {
    // ���� (1) public static int RECORD_PER_PAGE = 6; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 10; ���� ������ ��

    int totalPage = (int) (Math.ceil((double) search_count / Sale.RECORD_PER_PAGE_G)); // ��ü ������
    int totalGrp = (int) (Math.ceil((double) totalPage / Sale.PAGE_PER_BLOCK_G)); // ��ü �׷�
    int nowGrp = (int) (Math.ceil((double) nowPage / Sale.PAGE_PER_BLOCK_G)); // ���� �׷�
    int startPage = ((nowGrp - 1) * Sale.PAGE_PER_BLOCK_G) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)
    int endPage = (nowGrp * Sale.PAGE_PER_BLOCK_G); // Ư�� �׷��� ������ ��� ����

    StringBuffer str = new StringBuffer();

    str.append("<style type='text/css'>");
    str.append("  #paging {display: inline-table; text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    text-align: center;");
    str.append("    font-size: 1em;");
    str.append("    border: 0px;"); // str.append(" border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/");
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    text-align: center;");
    str.append("    border-radius: 5px;");
    str.append("    background-color: #FECE1A;");
    str.append("    color: #666666;");
    str.append("    font-size: 1em;");
    str.append("    font-weight: bold;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #ffffff;");
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/");
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");

    int _nowPage = (nowGrp - 1) * Sale.PAGE_PER_BLOCK_G; // ���� �������� �̵� (���� ������ �׷� - 1)
    if (nowGrp >= 2) {
      str.append("<span class='span_box_1'><A href='/study/user/sale/list_grid.do?&word=" + word + "&nowPage=" + _nowPage + "&search="+ search + "'>����</A></span>");
    }

    for (int i = startPage; i <= endPage; i++) {
      if (i > totalPage) {
        break;
      }

      if (nowPage == i) {
        str.append("<span class='span_box_2'>" + i + "</span>"); // ���� ������, ����
      } else {
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='/study/user/sale/list_grid.do?word=" + word + "&search=" + search + "&nowPage="+ i + "'>" + i + "</A></span>");
      }
    }

    _nowPage = (nowGrp * Sale.PAGE_PER_BLOCK_G) + 1; // 10�� ���� �������� �̵�
    if (nowGrp < totalGrp) {
      str.append("<span class='span_box_1'><A href='/study/user/sale/list_grid.do?&word=" + word + "&nowPage=" + _nowPage + "&search="+ search + "'>����</A></span>");
    }
    str.append("</DIV>");

    return str.toString();
  }

  /* �Խñ� ��ȸ */
  @Override
  public SaleVO read(int saleno) {
    
    SaleVO saleVO = saleDAO.read(saleno);
    long salesize = saleVO.getSalesize();

    if (salesize > 0) {
      String salesizeLabel = Tool.unit(salesize); // ���� ���� ���ڿ� ��ȯ KB, MB...
      saleVO.setSalesizeLabel(salesizeLabel);
    }

    String saletitle = saleVO.getSaletitle();
    saletitle = Tool.convertChar(saletitle);
    saleVO.setSaletitle(saletitle);

    return saleVO;
  }

  /* ������ ��ȸ */
  @Override
  public int read_pre(int saleno) {
    int count = saleDAO.read_pre(saleno);
    return count;
  }

  /* ������ ��ȸ */
  @Override
  public int read_post(int saleno) {
    int count = saleDAO.read_post(saleno);
    return count;
  }

  /* ��ȸ�� ��� */
  @Override
  public int increaseCnt(int saleno) {
    int count = saleDAO.increaseCnt(saleno);
    return count;
  }

  /* ���� */
  @Override
  public int update(SaleVO saleVO) {
    int count = saleDAO.update(saleVO);
    return count;
  }

  /* ���� */
  @Override
  public int delete(int saleno) {
    int count = saleDAO.delete(saleno);
    return count;
  }

  /* ���̵� �˻� */
  @Override
  public int member_check(SaleVO saleVO) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    hashMap.put("memberno", saleVO.getMemberno());
    hashMap.put("saleno", saleVO.getSaleno());

    int count = saleDAO.member_check(hashMap);
    return count;
  }

  /* ������ �Խñ� ��� */
  @Override
  public int create_admin(SaleVO saleVO) {
    return saleDAO.create_admin(saleVO);
  }

}
