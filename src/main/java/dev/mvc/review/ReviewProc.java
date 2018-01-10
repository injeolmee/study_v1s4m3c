package dev.mvc.review;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.room.Room;
import nation.web.tool.Tool;

@Component("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter {
  @Autowired
  @Qualifier("dev.mvc.review.ReviewDAO")
  private ReviewDAOInter reviewDAO = null;
  
  public ReviewProc() {
    //System.out.println("--> ReviewProc created.");
  }

  @Override
  public int create(ReviewVO reviewVO) {
    int count = reviewDAO.create(reviewVO);
    
    return count;
  }

  @Override
  public List<ReviewVO> list(HashMap hashMap) {
/*    
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지: nowPage = 1, (1 - 1) * 10  --> 0
    2 페이지: nowPage = 2, (2 - 1) * 10  --> 10
    3 페이지: nowPage = 3, (3 - 1) * 10  --> 20
   */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Review.RECORD_PER_PAGE;
   
    // 시작 rownum, 1페이지: 1, 2페이지: 11, 3페이지: 21
    int startNum = beginOfPage + 1; 
    // 종료 rownum, 1페이지: 10, 2페이지: 20, 3페이지: 30
    int endNum = beginOfPage + Review.RECORD_PER_PAGE; 
    
 /*   
      1 페이지: WHRER r >= 1 AND r <= 10
      2 페이지: WHRER r >= 11 AND r <= 20
      3 페이지: WHRER r >= 21 AND r <= 30
*/
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<ReviewVO> list = reviewDAO.list(hashMap); 
    Iterator<ReviewVO> iter = list.iterator();
   
    while(iter.hasNext() == true) {
      ReviewVO reviewVO = iter.next();
      String rvcont = Tool.textLength(reviewVO.getRvcont(), 90);
      reviewVO.setRvcont(rvcont);
    }
   
    return list;
   }
  

  @Override
  public int search_count(int rono) {
    int cnt = reviewDAO.search_count(rono);
    return cnt;
  }

  @Override
  public String paging(int rono, int search_count, int nowPage) {
    int totalPage = (int)(Math.ceil((double)search_count/Review.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Review.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Review.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Review.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Review.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:underline; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 0px;");  //str.append("    border: 1px;");
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
 
    int _nowPage = (nowGrp-1) * Review.PAGE_PER_BLOCK; // 이전 페이지로 이동 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A onclick='reviewlist("+_nowPage+")'>이전</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A onclick='reviewlist("+i+")'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Review.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A onclick='reviewlist("+_nowPage+")'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public ReviewVO read(int rvno) {
    ReviewVO reviewVO = reviewDAO.read(rvno);
    
    long rvsize1 = reviewVO.getRvsize1();
    
    if( rvsize1 > 0 ) {
      String size1Label = Tool.unit(rvsize1);
      reviewVO.setSize1Label(size1Label);
    }

    return reviewVO;
  }

  @Override
  public ReviewVO update(int rvno) {
    ReviewVO reviewVO = reviewDAO.read(rvno);
    return reviewVO;
  }

  @Override
  public int update(ReviewVO reviewVO) {
    return reviewDAO.update(reviewVO);
  }

  @Override
  public int delete(int rvno) {
    int count = reviewDAO.delete(rvno);
    return count;
  }

  @Override
  public int countByRono(int rono) {
    int count = reviewDAO.countByRono(rono);
    return count; 
  }

  @Override
  public int deleteByRono(int rono) {
    int count = reviewDAO.deleteByRono(rono);
    return count; 
  }

  @Override
  public int likecnt_up(int rvno) {
    return reviewDAO.likecnt_up(rvno);
  }

  @Override
  public int likecnt_down(int rvno) {
    return reviewDAO.likecnt_down(rvno);
  }

  @Override
  public int rvno() {
    
    return reviewDAO.rvno();
  }
  
  
  

}
