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

  /* 댓글 등록 */
  @Override
  public int create(FreereplyVO freereplyVO) {
    int count = freereplyDAO.create(freereplyVO);
    return count;
  }

  /* 대댓글 순서 변경 */
  @Override
  public int updateAnsnum(FreereplyVO freereplyVO) {
    int count = freereplyDAO.updateAnsnum(freereplyVO);
    return count;
  }

  /* 대댓글 등록 */
  @Override
  public int reply(FreereplyVO freereplyVO) {
    int count = freereplyDAO.reply(freereplyVO);
    return count;
  }

  /* 특정 댓글 조회 */
  @Override
  public FreereplyVO read(int freplyno) {
    FreereplyVO freereplyVO = freereplyDAO.read(freplyno);
    return freereplyVO;
  }

  /* 댓글 목록 + 페이징 */
  @Override
  public List<FreereplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Freereply.RECORD_PER_PAGE; // 0 * 10 = 0
    
    int startNum = beginOfPage + 1;                                   // 시작할 Rownum (1)
    int endNum = beginOfPage + Freereply.RECORD_PER_PAGE; // 종료할 Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <FreereplyVO> total_list_reply = freereplyDAO.total_list_reply(hashMap);

    return total_list_reply;
  }

  /* 댓글 총 레코드 갯수 산출 */
  @Override
  public int search_count(int freeno) {
    int count = freereplyDAO.search_count(freeno);
    return count;
  }

  /* 페이징 처리 */
  @Override
  public String paging(int nowPage, int search_count, int freeno) {
    // 참조 (1) public static int RECORD_PER_PAGE = 5; // 페이지당 출력할 레코드 갯수
    // 참조 (2) public static int PAGE_PER_BLOCK = 10; 블럭당 페이지 수
    
    int totalPage = (int)(Math.ceil((double)search_count/Freereply.RECORD_PER_PAGE)); // 전체 페이지 
    int totalGrp = (int)(Math.ceil((double)totalPage/Freereply.PAGE_PER_BLOCK));         // 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Freereply.PAGE_PER_BLOCK));          // 현재 그룹 
    int startPage = ((nowGrp - 1) * Freereply.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작 (1쪽의 그룹, 2쪽의 그룹...)  
    int endPage = (nowGrp * Freereply.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
 
    int _nowPage = (nowGrp-1) * Salereply.PAGE_PER_BLOCK; // 이전 페이지로 이동 (현재 페이지 그룹 - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='/study/nonuser/free/read.do?freeno="+freeno+"&nowPage="+_nowPage+"'>이전</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='/study/nonuser/free/read.do?freeno="+freeno +"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Salereply.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/nonuser/free/read.do?&freeno="+freeno+"&nowPage="+_nowPage + "'>다음</A></span>"); 
    } 
    str.append("</DIV>");
     
    return str.toString(); 
  }

  /* 댓글 수정 */
  @Override
  public int update(FreereplyVO freereplyVO) {
    int count = freereplyDAO.update(freereplyVO);
    return count;
  }

  /* 댓글 삭제 */
  @Override
  public int delete(int freplyno) {
    int count = freereplyDAO.delete(freplyno);
    return count;
  }

  /* 부모 댓글의 seqno를 0으로 변경*/
  @Override
  public int update_seqno(int freplyno) {
    int count = freereplyDAO.update_seqno(freplyno);
    return count;
  }
  
  /* 부모 댓글에 대해 삭제 처리 대신 content를 변경해 출력 */
  @Override
  public int update_parent(int freplyno) {
    int count = freereplyDAO.update_parent(freplyno);
    return count;
  }

  /* 게시글 삭제시 댓글 전체 삭제*/
  @Override
  public int delete_all(int freeno) {
    int count = freereplyDAO.delete_all(freeno);
    return count;
  }
  
  

}
