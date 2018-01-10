package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter {
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeDAO")
  private NoticeDAOInter noticeDAO;

  public NoticeProc() {
  }

  @Override
  public int notice_create(NoticeVO noticeVO) {
    return noticeDAO.notice_create(noticeVO);
  }

  @Override
  public List<NoticeVO> notice_list(HashMap hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Notice.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // 시작 rownum/ 1페이지: 1/ 2페이지: 11
    int endNum = beginOfPage + Notice.RECORD_PER_PAGE; // 종료 rownum/ 1페이지: 10/ 2페이지: 20
    
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    return noticeDAO.notice_list(hashMap);
  }

  @Override
  public String paging(int search_count, int nowPage, String word, String search) {
    int totalPage = (int)(Math.ceil((double)search_count/Notice.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Notice.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Notice.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Notice.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Notice.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em; float:left;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    text-align: center;");
    str.append("    font-size: 1em;");
    str.append("    border: 0px;"); // str.append(" border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/");
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
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Notice.PAGE_PER_BLOCK; // 이전 페이지로 이동 
    
      if (nowGrp >= 2){ 
        //str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>이전</A></span>"); 
         str.append("<span class='span_box_1'><A href='./notice_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>이전</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
        }else{
          // 현재 페이지가 아닌 페이지
          // str.append("<span class='span_box_1'><A href='./mem_list.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
          str.append("<span class='span_box_1'><A href='./notice_list.do?word="+word+"&search="+search+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Notice.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
      if (nowGrp < totalGrp){ 
        // str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>다음</A></span>"); 
        str.append("<span class='span_box_1'><A href='./notice_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>다음</A></span>"); 
      } 
      str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public int search_count(HashMap hashMap) {
    return noticeDAO.search_count(hashMap);
  }

  @Override
  public NoticeVO notice_read(int noticeno) {
    return noticeDAO.notice_read(noticeno);
  }

  @Override
  public int notice_update(NoticeVO noticeVO) {
    return noticeDAO.notice_update(noticeVO);
  }

  @Override
  public int notice_delete(int noticeno) {
    return noticeDAO.notice_delete(noticeno);
  }

  @Override
  public int nseqno_up(int noticeno) {
    return noticeDAO.nseqno_up(noticeno);
  }

  @Override
  public int nseqno_down(int noticeno) {
    return noticeDAO.nseqno_down(noticeno);
  }

  @Override
  public int ncnt_up(int noticeno) {
    return noticeDAO.ncnt_up(noticeno);
  }

}
