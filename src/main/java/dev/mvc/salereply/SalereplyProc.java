package dev.mvc.salereply;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import dev.mvc.sale.Sale;
import dev.mvc.sale.SaleVO;
import nation.web.tool.Tool;

@Repository("dev.mvc.salereply.SalereplyProc")
public class SalereplyProc implements SalereplyProcInter {

  @Autowired
  @Qualifier("dev.mvc.salereply.SalereplyDAO")
  private SalereplyDAOInter salereplyDAO;
  
  public SalereplyProc() {
    // System.out.println(" => SalereplyProc created");
  }
  
  /* ��� */
  @Override
  public int create(SalereplyVO salereplyVO) {
    int count = salereplyDAO.create(salereplyVO);
    return count;
  }

  /* ��� */
  @Override
  public List<SalereplyVO> list_reply(int saleno) {
    List <SalereplyVO> list_reply = salereplyDAO.list_reply(saleno);
    return list_reply;
  }

  /* ���� ���� ���� */
  @Override
  public int updateAnsnum(SalereplyVO salereplyVO) {
    int count = salereplyDAO.updateAnsnum(salereplyVO);
    return count;
  }

  /* ���� ��� */
  @Override
  public int reply(SalereplyVO salereplyVO) {
    int count = salereplyDAO.reply(salereplyVO);
    return count;
  }

  /* Ư�� ��� ��ȸ */
  @Override
  public SalereplyVO read(int sreplyno) {
    SalereplyVO salereplyVO = salereplyDAO.read(sreplyno);
    return salereplyVO;
  }

  /* ����� �� ���ڵ� ���� ���� */
  @Override
  public int search_count(int saleno) {
    int count = salereplyDAO.search_count(saleno);
    return count;
  }

  /* ����¡ ó�� */
  @Override
  public String paging(int nowPage, int search_count, int saleno) {
    // ���� (1) public static int RECORD_PER_PAGE = 5; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 5; ���� ������ ��
    
    int totalPage = (int)(Math.ceil((double)search_count/Salereply.RECORD_PER_PAGE)); // ��ü ������ 
    int totalGrp = (int)(Math.ceil((double)totalPage/Salereply.PAGE_PER_BLOCK));         // ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Salereply.PAGE_PER_BLOCK));          // ���� �׷� 
    int startPage = ((nowGrp - 1) * Salereply.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)  
    int endPage = (nowGrp * Salereply.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 0px;");  //str.append("    border: 1px;");
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
 
    int _nowPage = (nowGrp-1) * Salereply.PAGE_PER_BLOCK; // ���� �������� �̵� (���� ������ �׷� - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='/study/user/sale/read.do?saleno="+saleno+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='/study/user/sale/read.do?saleno="+saleno +"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Salereply.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/user/sale/read.do?&saleno="+saleno+"&nowPage="+_nowPage + "'>����</A></span>"); 
    } 
    str.append("</DIV>");
     
    return str.toString(); 
  }

  /* ��� ��� + ����¡ */
  @Override
  public List<SalereplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Salereply.RECORD_PER_PAGE;
    
    int startNum = beginOfPage + 1;                                  // ������ Rownum (1)
    int endNum = beginOfPage + Salereply.RECORD_PER_PAGE; // ������ Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <SalereplyVO> total_list_reply = salereplyDAO.total_list_reply(hashMap);

    return total_list_reply;
  }

  /* ��� ���� */
  @Override
  public int update(SalereplyVO salereplyVO) {
    int count = salereplyDAO.update(salereplyVO);
    return count;
  }

  /* ��� ���� */
  @Override
  public int delete(int sreplyno) {
    int count = salereplyDAO.delete(sreplyno);
    return count;
  }

  /* �θ� ����� seqno�� 0���� ����*/
  @Override
  public int update_seqno(int sreplyno) {
    int count = salereplyDAO.update_seqno(sreplyno);
    return count;
  }

  /* �θ� ��ۿ� ���� ���� ó�� ��� content�� ������ ��� */
  @Override
  public int update_parent(int sreplyno) {
    int count = salereplyDAO.update_parent(sreplyno);
    return count;
  }

  /* �Խñ� ������ ��� ��ü ���� */
  @Override
  public int delete_all(int saleno) {
    int count = salereplyDAO.delete_all(saleno);
    return count;
  }

}
