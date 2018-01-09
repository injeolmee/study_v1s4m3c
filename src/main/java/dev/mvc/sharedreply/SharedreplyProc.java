package dev.mvc.sharedreply;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.sharedreply.SharedreplyProc")
public class SharedreplyProc implements SharedreplyProcInter {

  @Autowired
  @Qualifier("dev.mvc.sharedreply.SharedreplyDAO")
  private SharedreplyDAOInter sharedreplyDAO;
  
  public SharedreplyProc() {
    // System.out.println(" => SharedreplyProc created");
  }
  
  /* 댓글 등록 */
  @Override
  public int create(SharedreplyVO sharedreplyVO) {
    int count = sharedreplyDAO.create(sharedreplyVO);
    return count;
  }

  /* 대댓글 순서 변경 */ 
  @Override
  public int updateAnsnum(SharedreplyVO sharedreplyVO) {
    int count = sharedreplyDAO.updateAnsnum(sharedreplyVO);
    return count;
  }

  /* 대댓글 등록 */
  @Override
  public int reply(SharedreplyVO sharedreplyVO) {
    int count = sharedreplyDAO.reply(sharedreplyVO);
    return count;
  }

  /* 특정 댓글 조회 */
  @Override
  public SharedreplyVO read(int shreplyno) {
    SharedreplyVO sharedreplyVO = sharedreplyDAO.read(shreplyno);
    return sharedreplyVO;
  }

  /* 댓글 목록 + 페이징 */
  @Override
  public List<SharedreplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Sharedreply.RECORD_PER_PAGE;
    
    int startNum = beginOfPage + 1;                                      // 시작할 Rownum (1)
    int endNum = beginOfPage + Sharedreply.RECORD_PER_PAGE; // 종료할 Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <SharedreplyVO> total_list_reply = sharedreplyDAO.total_list_reply(hashMap);

    return total_list_reply;
  }

  /* 댓글의 총 레코드 갯수 산출 */
  @Override
  public int search_count(int sharedno) {
    int count = sharedreplyDAO.search_count(sharedno);
    return count;
  }

  /* 페이징 처리 */
  @Override
  public String paging(int nowPage, int search_count, int sharedno) {
    // 참조 (1) public static int RECORD_PER_PAGE = 5; // 페이지당 출력할 레코드 갯수
    // 참조 (2) public static int PAGE_PER_BLOCK = 5; 블럭당 페이지 수
    
    int totalPage = (int)(Math.ceil((double)search_count/Sharedreply.RECORD_PER_PAGE)); // 전체 페이지 
    int totalGrp = (int)(Math.ceil((double)totalPage/Sharedreply.PAGE_PER_BLOCK));         // 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Sharedreply.PAGE_PER_BLOCK));          // 현재 그룹 
    int startPage = ((nowGrp - 1) * Sharedreply.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작 (1쪽의 그룹, 2쪽의 그룹...)  
    int endPage = (nowGrp * Sharedreply.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
 
    int _nowPage = (nowGrp-1) * Sharedreply.PAGE_PER_BLOCK; // 이전 페이지로 이동 (현재 페이지 그룹 - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='/study/user/shared/read.do?sharedno="+sharedno+"&nowPage="+_nowPage+"'>이전</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='/study/user/shared/read.do?sharedno="+sharedno +"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Sharedreply.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/user/shared/read.do?&sharedno="+sharedno+"&nowPage="+_nowPage + "'>다음</A></span>"); 
    } 
    str.append("</DIV>");
     
    return str.toString(); 
  }

  /* 댓글 수정 */
  @Override
  public int update(SharedreplyVO sharedreplyVO) {
    int count = sharedreplyDAO.update(sharedreplyVO);
    return count;
  }

  /* 댓글 삭제 */
  @Override
  public int delete(int shreplyno) {
    int count = sharedreplyDAO.delete(shreplyno);
    return count;
  }

  /* 부모 댓글의 seqno를 0으로 변경*/
  @Override
  public int update_seqno(int shreplyno) {
    int count = sharedreplyDAO.update_seqno(shreplyno);
    return count;
  }

  /* 부모 댓글에 대해 삭제 처리 대신 content를 변경해 출력 */
  @Override
  public int update_parent(int shreplyno) {
    int count = sharedreplyDAO.update_parent(shreplyno);
    return count;
  }

  /* 게시글 삭제시 댓글 전체 삭제*/
  @Override
  public int delete_all(int sharedno) {
    int count = sharedreplyDAO.delete_all(sharedno);
    return count;
  }

  /* 관리자 댓글 등록 */
  @Override
  public int create_admin(SharedreplyVO sharedreplyVO) {
    return sharedreplyDAO.create_admin(sharedreplyVO);
  }

  /* 관리자 대댓글 등록 */
  @Override
  public int reply_admin(SharedreplyVO sharedreplyVO) {
    return sharedreplyDAO.reply_admin(sharedreplyVO);
  }

  /* 부모 댓글일 경우 하위 댓글이 존재하는지 검사 */
  @Override
  public int parent_check(int shreplygrpno) {
    return sharedreplyDAO.parent_check(shreplygrpno);
  }

  /* 대댓글과 관련되어서 맨 마지막 댓글인지 검사 */
  @Override
  public int reply_check(HashMap hashMap) {
    return sharedreplyDAO.reply_check(hashMap);
  }
  
  
  
  

}
