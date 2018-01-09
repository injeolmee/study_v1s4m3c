package dev.mvc.qna;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nation.web.tool.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import dev.mvc.contest.Contest;

@Repository("dev.mvc.qna.QnaProc")
public class QnaProc implements QnaProcInter {

  @Autowired
  @Qualifier("dev.mvc.qna.QnaDAO")
  private QnaDAOInter qnaDAO;

  @Override
  public int create(QnaVO qnaVO) {
    return qnaDAO.create(qnaVO);
  }

  @Override
  public List<QnaVO> list_all_qna(HashMap hashMap) {
    /* 
         ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
     1������: nowPage = 1, (1-1) * 10 => 0
     2������: nowPage = 2, (2-1) * 10 => 10
     3������: nowPage = 3, (3-1) * 10 => 20   
     */    
     int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Qna.RECORD_PER_PAGE;
     int startNum = beginOfPage + 1; // ���� rownum/ 1������: 1/ 2������: 11
     int endNum = beginOfPage + Qna.RECORD_PER_PAGE; // ���� rownum/ 1������: 10/ 2������: 20
     /*    
     1������: WHERE r >= 1 AND r <= 10
     2������: WHERE r >= 11 AND r <= 20
     3������: WHERE r >= 21 AND r <= 30
     */   
     hashMap.put("startNum", startNum);
     hashMap.put("endNum", endNum);
    
    List<QnaVO> list = qnaDAO.list_all_qna(hashMap);
    
    Iterator<QnaVO> iter = list.iterator();
    
    while (iter.hasNext()) {
      QnaVO qnaVO = iter.next();
      String qnatitle = Tool.textLength(qnaVO.getQnatitle(), 200);
      qnaVO.setQnatitle(qnatitle);
    }
    
    return list;
  }

  @Override
  public QnaVO read(int qnano) {
    QnaVO qnaVO = qnaDAO.read(qnano);
    
    long qnasize1 = qnaVO.getQnasize1();
    if (qnasize1 > 0) {
      String size1Label = Tool.unit(qnasize1);
      qnaVO.setSize1Label(size1Label);
    }
    
    return qnaVO;
  }
  
  @Override
  public QnaVO update(int qnano) {
    QnaVO qnaVO = qnaDAO.read(qnano);
    
    String qnatitle = qnaVO.getQnatitle();
    qnatitle = Tool.convertChar(qnatitle);
    qnaVO.setQnatitle(qnatitle);
    
    String wname = qnaVO.getWname();
    wname = Tool.convertChar(wname);
    qnaVO.setWname(wname);
    
    String qnacont = qnaVO.getQnacont();
    qnacont = Tool.convertChar(qnacont);
    qnaVO.setQnacont(qnacont);
    
    return qnaVO;
  }

  @Override
  public int update(QnaVO qnaVO) {
    return qnaDAO.update(qnaVO);
  }
  
  @Override
  public int delete(int qnano) {
    return qnaDAO.delete(qnano);
  }

  @Override
  public int increaseCnt(int qnano) {
    return qnaDAO.increaseCnt(qnano);
  }
  
  @Override
  public int total_count() {
    return qnaDAO.total_count();
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return qnaDAO.search_count(hashMap);
  }

  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String qnatitle){ 
    int totalPage = (int)(Math.ceil((double)search_count/Qna.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Qna.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Qna.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Qna.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Qna.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Qna.PAGE_PER_BLOCK; // ���� �������� �̵� 
    
      if (nowGrp >= 2){ 
        str.append("<span class='span_box_1'><A href='./list_all_qna.do?qnatitle="+qnatitle+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
        }else{
          // ���� �������� �ƴ� ������
          str.append("<span class='span_box_1'><A href='./list_all_qna.do?qnatitle="+qnatitle+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Contest.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
      if (nowGrp < totalGrp){ 
        str.append("<span class='span_box_1'><A href='./list_all_qna.do?qnatitle="+qnatitle+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 
      str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  @Override
  public int member_check(QnaVO qnaVO) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    hashMap.put("memberno", qnaVO.getMemberno());
    hashMap.put("qnano", qnaVO.getQnano());
    
    int count = qnaDAO.member_check(hashMap);
    
    return count;
  }

  @Override
  public int pwdchk(String qnapwd) {
    return qnaDAO.pwdchk(qnapwd);
  }
  
}
