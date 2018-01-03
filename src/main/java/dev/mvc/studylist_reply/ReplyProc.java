package dev.mvc.studylist_reply;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.studylist.StudyList;

@Component("dev.mvc.studylist_reply.ReplyProc")
public class ReplyProc implements ReplyProcInter{

  @Autowired
  @Qualifier("dev.mvc.studylist_reply.ReplyDAO")
  private ReplyDAOInter ReplyDAO = null;
  
  public ReplyProc(){
    // System.out.println("--> ReplyProc created.");
  }

  @Override
  public int create(ReplyVO replyVO) {
    
        return ReplyDAO.create(replyVO);
  }

  @Override
  public List<ReplyVO> list(int stdlist_no) {
    
    return ReplyDAO.list(stdlist_no);
  }
  
  @Override
  public List<ReplyVO> list2(HashMap hashmap) {
    
    /*    
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1������: nowPage = 1, (1-1) * 10 => 0
    2������: nowPage = 2, (2-1) * 10 => 10
    3������: nowPage = 3, (3-1) * 10 => 20   
*/    
    int beginOfPage = ((Integer)hashmap.get("nowPage") - 1) * Reply.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // ���� rownum/ 1������: 1/ 2������: 11
    int endNum = beginOfPage + Reply.RECORD_PER_PAGE;; // ���� rownum/ 1������: 10/ 2������: 20
/*    
    1������: WHERE r >= 1 AND r <= 10
    2������: WHERE r >= 11 AND r <= 20
    3������: WHERE r >= 21 AND r <= 30
 */   
    hashmap.put("startNum", startNum);
    hashmap.put("endNum", endNum);
    
    return ReplyDAO.list2(hashmap);
  }

  @Override
  public int update(ReplyVO replyVO) {
    
    return ReplyDAO.update(replyVO);
  }

  @Override
  public int delete(HashMap hashmap) {
    
    return ReplyDAO.delete(hashmap);
  }

  @Override
  public int check_memberno(HashMap hashmap) {
    
    return ReplyDAO.check_memberno(hashmap);
  }

  @Override
  public int search_count(int stdlist_no) {
    
    return ReplyDAO.search_count(stdlist_no);
  }
  
  @Override
  public ReplyVO read(HashMap hashmap) {
    
    return ReplyDAO.read(hashmap);
  }

  @Override
  public String paging(int search_count, int nowPage, int stdlist_no) {

    int totalPage = (int)(Math.ceil((double)search_count/Reply.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Reply.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Reply.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Reply.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Reply.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
     
    StringBuffer str = new StringBuffer(); 
    
    str.append("<ul class='pagination'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Reply.PAGE_PER_BLOCK; // ���� �������� �̵� 
    
      if (nowGrp >= 2){ 
        str.append("<ul class='pager'>"); 
        str.append("<A onclick='reply_List("+stdlist_no+","+_nowPage+");'>����</A>"); 
        str.append("</ul>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
       if (nowPage == i){ 
          str.append("<li class='active'><A onclick='reply_List("+stdlist_no+","+i+");'>"+i+"</A></li>"); // ���� ������, ���� 
        }else{
          
          // ���� �������� �ƴ� ������
          str.append("<li><A onclick='reply_List("+stdlist_no+","+i+")'>"+i+"</A></li>");
        } 
      } 
       
      _nowPage = (nowGrp * StudyList.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
      
      if (nowGrp < totalGrp){ 
        str.append("<ul class='pager'>"); 
        str.append("<A onclick='reply_List("+stdlist_no+","+_nowPage+");'>����</A>"); 
        str.append("</ul>"); 
      } 
      str.append("</ul>"); 
     
    return str.toString(); 
  }

  @Override
  public int delete_all(int stdlist_no) {
    
    return ReplyDAO.delete_all(stdlist_no);
  }

 
}
