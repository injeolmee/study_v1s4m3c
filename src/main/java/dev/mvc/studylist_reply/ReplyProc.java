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
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1페이지: nowPage = 1, (1-1) * 10 => 0
    2페이지: nowPage = 2, (2-1) * 10 => 10
    3페이지: nowPage = 3, (3-1) * 10 => 20   
*/    
    int beginOfPage = ((Integer)hashmap.get("nowPage") - 1) * Reply.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // 시작 rownum/ 1페이지: 1/ 2페이지: 11
    int endNum = beginOfPage + Reply.RECORD_PER_PAGE;; // 종료 rownum/ 1페이지: 10/ 2페이지: 20
/*    
    1페이지: WHERE r >= 1 AND r <= 10
    2페이지: WHERE r >= 11 AND r <= 20
    3페이지: WHERE r >= 21 AND r <= 30
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

    int totalPage = (int)(Math.ceil((double)search_count/Reply.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Reply.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Reply.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Reply.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Reply.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
     
    StringBuffer str = new StringBuffer(); 
    
    str.append("<ul class='pagination'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Reply.PAGE_PER_BLOCK; // 이전 페이지로 이동 
    
      if (nowGrp >= 2){ 
        str.append("<ul class='pager'>"); 
        str.append("<A onclick='reply_List("+stdlist_no+","+_nowPage+");'>이전</A>"); 
        str.append("</ul>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
       if (nowPage == i){ 
          str.append("<li class='active'><A onclick='reply_List("+stdlist_no+","+i+");'>"+i+"</A></li>"); // 현재 페이지, 강조 
        }else{
          
          // 현재 페이지가 아닌 페이지
          str.append("<li><A onclick='reply_List("+stdlist_no+","+i+")'>"+i+"</A></li>");
        } 
      } 
       
      _nowPage = (nowGrp * StudyList.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
      
      if (nowGrp < totalGrp){ 
        str.append("<ul class='pager'>"); 
        str.append("<A onclick='reply_List("+stdlist_no+","+_nowPage+");'>다음</A>"); 
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
