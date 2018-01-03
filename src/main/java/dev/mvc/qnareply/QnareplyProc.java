package dev.mvc.qnareply;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.qnareply.QnareplyProc")
public class QnareplyProc implements QnareplyProcInter {
  @Autowired
  @Qualifier("dev.mvc.qnareply.QnareplyDAO")
  private QnareplyDAOInter qnareplyDAO;
  
  public QnareplyProc(){}
  
  /* ��� ��� */
  @Override
  public int create(QnareplyVO qnareplyVO) {
    int count = qnareplyDAO.create(qnareplyVO);
    return count;
  }

  /* Ư�� ��� ��ȸ */
  @Override
  public QnareplyVO read(int qrno) {
    QnareplyVO qnareplyVO = qnareplyDAO.read(qrno);
    return qnareplyVO;
  }

  /* ��� ��� + ����¡ */
  @Override
  public List<QnareplyVO> list_all_qnareply(HashMap hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Qnareply.RECORD_PER_PAGE; // 0 * 10 = 0
    
    int startNum = beginOfPage + 1;                                      // ������ Rownum (1)
    int endNum = beginOfPage + Qnareply.RECORD_PER_PAGE; // ������ Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<QnareplyVO> list_all_qnareply = qnareplyDAO.list_all_qnareply(hashMap);

    return list_all_qnareply;
  }

  /* ����� �� ���ڵ� ���� ���� */
  @Override
  public int search_count(int qnano) {
    int count = qnareplyDAO.search_count(qnano);
    return count;
  }

  /* ����¡ ó�� */
  @Override
  public String paging(int nowPage, int search_count, int qnano) {
    // ���� (1) public static int RECORD_PER_PAGE = 5; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 10; ���� ������ ��
    
    int totalPage = (int)(Math.ceil((double)search_count/Qnareply.RECORD_PER_PAGE)); // ��ü ������ 
    int totalGrp = (int)(Math.ceil((double)totalPage/Qnareply.PAGE_PER_BLOCK));         // ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Qnareply.PAGE_PER_BLOCK));          // ���� �׷� 
    int startPage = ((nowGrp - 1) * Qnareply.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)  
    int endPage = (nowGrp * Qnareply.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
 
    int _nowPage = (nowGrp-1) * Qnareply.PAGE_PER_BLOCK; // ���� �������� �̵� (���� ������ �׷� - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./read.do?qnano="+qnano+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./read.do?qnano="+qnano +"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Qnareply.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./read.do?&qnano="+qnano+"&nowPage="+_nowPage + "'>����</A></span>"); 
    } 
    str.append("</DIV>");
     
    return str.toString(); 
  }

  /* ��� ���� */
  @Override
  public int update(QnareplyVO qnareplyVO) {
    int count = qnareplyDAO.update(qnareplyVO);
    return count;
  }

  /* ��� ���� */
  @Override
  public int delete(int qrno) {
    int count = qnareplyDAO.delete(qrno);
    return count;
  }

  /* �Խñ� ������ ��� ��ü ����*/
  @Override
  public int delete_all(int qnano) {
    int count = qnareplyDAO.delete_all(qnano);
    return count;
  }
  
}
