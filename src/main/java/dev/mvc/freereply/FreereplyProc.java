package dev.mvc.freereply;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import dev.mvc.salereply.Salereply;

@Repository("dev.mvc.freereply.FreereplyProc")
public class FreereplyProc implements FreereplyProcInter {
  
  @Autowired
  @Qualifier("dev.mvc.freereply.FreereplyDAO")
  private FreereplyDAOInter freereplyDAO;
  
  public FreereplyProc() {
    // System.out.println(" => FreereplyProc created");
  }

  /* ��� ��� */
  @Override
  public int create(FreereplyVO freereplyVO) {
    int count = freereplyDAO.create(freereplyVO);
    return count;
  }

  /* ���� ���� ���� */
  @Override
  public int updateAnsnum(FreereplyVO freereplyVO) {
    int count = freereplyDAO.updateAnsnum(freereplyVO);
    return count;
  }

  /* ���� ��� */
  @Override
  public int reply(FreereplyVO freereplyVO) {
    int count = freereplyDAO.reply(freereplyVO);
    return count;
  }

  /* Ư�� ��� ��ȸ */
  @Override
  public FreereplyVO read(int freplyno) {
    FreereplyVO freereplyVO = freereplyDAO.read(freplyno);
    return freereplyVO;
  }

  /* ��� ��� + ����¡ */
  @Override
  public List<FreereplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Freereply.RECORD_PER_PAGE; // 0 * 10 = 0
    
    int startNum = beginOfPage + 1;                                   // ������ Rownum (1)
    int endNum = beginOfPage + Freereply.RECORD_PER_PAGE; // ������ Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <FreereplyVO> total_list_reply = freereplyDAO.total_list_reply(hashMap);

    return total_list_reply;
  }

  /* ��� �� ���ڵ� ���� ���� */
  @Override
  public int search_count(int freeno) {
    int count = freereplyDAO.search_count(freeno);
    return count;
  }

  /* ����¡ ó�� */
  @Override
  public String paging(int nowPage, int search_count, int freeno) {
    // ���� (1) public static int RECORD_PER_PAGE = 5; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 10; ���� ������ ��
    
    int totalPage = (int)(Math.ceil((double)search_count/Freereply.RECORD_PER_PAGE)); // ��ü ������ 
    int totalGrp = (int)(Math.ceil((double)totalPage/Freereply.PAGE_PER_BLOCK));         // ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Freereply.PAGE_PER_BLOCK));          // ���� �׷� 
    int startPage = ((nowGrp - 1) * Freereply.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)  
    int endPage = (nowGrp * Freereply.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
      str.append("<span class='span_box_1'><A href='/study/nonuser/free/read.do?freeno="+freeno+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='/study/nonuser/free/read.do?freeno="+freeno +"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Salereply.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/nonuser/free/read.do?&freeno="+freeno+"&nowPage="+_nowPage + "'>����</A></span>"); 
    } 
    str.append("</DIV>");
     
    return str.toString(); 
  }

  /* ��� ���� */
  @Override
  public int update(FreereplyVO freereplyVO) {
    int count = freereplyDAO.update(freereplyVO);
    return count;
  }

  /* ��� ���� */
  @Override
  public int delete(int freplyno) {
    int count = freereplyDAO.delete(freplyno);
    return count;
  }

  /* �θ� ����� seqno�� 0���� ����*/
  @Override
  public int update_seqno(int freplyno) {
    int count = freereplyDAO.update_seqno(freplyno);
    return count;
  }
  
  /* �θ� ��ۿ� ���� ���� ó�� ��� content�� ������ ��� */
  @Override
  public int update_parent(int freplyno) {
    int count = freereplyDAO.update_parent(freplyno);
    return count;
  }

  /* �Խñ� ������ ��� ��ü ����*/
  @Override
  public int delete_all(int freeno) {
    int count = freereplyDAO.delete_all(freeno);
    return count;
  }
  
  

}
