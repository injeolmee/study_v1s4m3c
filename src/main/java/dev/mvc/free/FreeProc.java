package dev.mvc.free;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import nation.web.tool.Tool;

@Repository("dev.mvc.free.FreeProc")
public class FreeProc implements FreeProcInter {
  
  @Autowired
  @Qualifier("dev.mvc.free.FreeDAO")
  private FreeDAOInter freeDAO;
  
  public FreeProc() {
    // System.out.println(" => FreeProc created");
  }

  /* 등록 */
  @Override
  public int create(FreeVO freeVO) {
    int count = 0;
    count = freeDAO.create(freeVO);
    return count;
  }

  /* 목록 */
  @Override
  public List<FreeVO> list() {
    List <FreeVO> list = freeDAO.list();
    
    Iterator<FreeVO> iter = list.iterator();
    
    while (iter.hasNext()) { // 제목이 길 경우 "..."로 처리하는 구문
      FreeVO freeVO = iter.next();
      String freetitle = Tool.textLength(freeVO.getFreetitle(), 20);
      freeVO.setFreetitle(freetitle);
    }

    return list;
  }

  /* 조회 */
  @Override
  public FreeVO read(int freeno) {
    FreeVO freeVO = freeDAO.read(freeno);
    return freeVO;
  }

  /* 조회수 증가 */
  @Override
  public int increaseCnt(int freeno) {
    int count = freeDAO.increaseCnt(freeno);
    return count;
  }
 
  /* 수정 */
  @Override
  public int update(FreeVO freeVO) {
    int count = freeDAO.update(freeVO);
    return count;
  }

  /* 패스워드 검사 */
  @Override
  public int passwd_check(FreeVO freeVO) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    hashMap.put("freeno", freeVO.getFreeno());
    hashMap.put("freepasswd", freeVO.getFreepasswd());
    
    /*System.out.println("passwd: " + freeVO.getFreepasswd());*/
    
    int count = freeDAO.passwd_check(hashMap);
    return count;
  }

  /* 삭제 */
  @Override
  public int delete(int freeno) {
    int count = freeDAO.delete(freeno);
    return count;
  }

  /* 검색 */
  @Override
  public List<FreeVO> list_search(HashMap hashMap) {
    // 페이지에서 출력할 시작 레코드 번호 계산, nowPage는 1부터 시작
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Free.RECORD_PER_PAGE; // 0 * 10 = 0
    
    int startNum = beginOfPage + 1;                             // 시작할 Rownum (1)
    int endNum = beginOfPage + Free.RECORD_PER_PAGE; // 종료할 Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <FreeVO> list = freeDAO.list_search(hashMap);
    
    Iterator<FreeVO> iter = list.iterator();
    
    while (iter.hasNext()) { // 제목이 길 경우 "..."로 처리하는 구문
      FreeVO freeVO = iter.next();
      String freetitle = Tool.textLength(freeVO.getFreetitle(), 20);
      freeVO.setFreetitle(freetitle);
    }

    return list;
  }
  
  /* 검색된 레코드 갯수 */
  @Override
  public int search_count(HashMap hashMap) {
    int count = freeDAO.search_count(hashMap);
    return count;
  } 

  /**
   * 페이징 처리
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   * @param nowPage 현재 페이지
   * @param word 검색어
   * @param serach 검색 Value Option
   * @return 페이징 생성 문자열
   */
  @Override
  public String paging(int nowPage, int search_count, String word, String search) { 
    // 참조 (1) public static int RECORD_PER_PAGE = 5; // 페이지당 출력할 레코드 갯수
    // 참조 (2) public static int PAGE_PER_BLOCK = 10; 블럭당 페이지 수
    
    int totalPage = (int)(Math.ceil((double)search_count/Free.RECORD_PER_PAGE)); // 전체 페이지 
    int totalGrp = (int)(Math.ceil((double)totalPage/Free.PAGE_PER_BLOCK));         // 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Free.PAGE_PER_BLOCK));          // 현재 그룹 
    int startPage = ((nowGrp - 1) * Free.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작 (1쪽의 그룹, 2쪽의 그룹...)  
    int endPage = (nowGrp * Free.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {margin-top: 5px; font-size: 1em;}"); 
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
    str.append("    float: none;"); 
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
    str.append("    float: none;"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>");
 
    int _nowPage = (nowGrp-1) * Free.PAGE_PER_BLOCK; // 이전 페이지로 이동 (현재 페이지 그룹 - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='/study/nonuser/free/list.do?&word="+word+"&nowPage="+_nowPage+"'>이전</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='/study/nonuser/free/list.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Free.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/nonuser/free/list.do?&word="+word+"&nowPage="+_nowPage+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  /* 추천수 최상위 3개 출력 */
  @Override
  public List<FreeVO> list_like(HashMap<String, Object> hashMap2) {
    
    int sNum = 1; // 출력할 페이지 시작번호
    int eNum = 3; // 출력할 페이지 종료번호
    
    hashMap2.put("sNum", sNum);
    hashMap2.put("eNum", eNum);
    
    List <FreeVO> list_like = freeDAO.list_like(hashMap2);
    
    Iterator<FreeVO> iter = list_like.iterator();
    
    while (iter.hasNext()) { // 제목이 길 경우 "..."로 처리하는 구문
      FreeVO freeVO = iter.next();
      String freetitle = Tool.textLength(freeVO.getFreetitle(), 14);
      freeVO.setFreetitle(freetitle);
    }

    return list_like;
  }

  /* 조회수 최상위 3개 출력 */
  @Override
  public List<FreeVO> list_cnt(HashMap<String, Object> hashMap3) {
    
    int sNum = 1;
    int eNum = 3;
    
    hashMap3.put("sNum", sNum);
    hashMap3.put("eNum", eNum);
    
    List <FreeVO> list_cnt = freeDAO.list_cnt(hashMap3);
    
    Iterator<FreeVO> iter = list_cnt.iterator();
    
    while (iter.hasNext()) { // 제목이 길 경우 "..."로 처리하는 구문
      FreeVO freeVO = iter.next();
      String freetitle = Tool.textLength(freeVO.getFreetitle(), 14);
      freeVO.setFreetitle(freetitle);
    }
 
    return list_cnt;
  }

  /* 이전글 조회 */
  @Override
  public int read_pre(int freeno) {
    int count = freeDAO.read_pre(freeno);
    return count;
  }

  /* 다음글 조회 */
  @Override
  public int read_post(int freeno) {
    int count = freeDAO.read_post(freeno);
    return count;
  }

  /* 아이디 검사 */
  @Override
  public int member_check(FreeVO freeVO) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    hashMap.put("freeno", freeVO.getFreeno());
    hashMap.put("memberno", freeVO.getMemberno());

    int count = freeDAO.member_check(hashMap);
    return count;
  }

  /* 좋아요가 0에서 1로 변경 */
  @Override
  public int increaseLike(int freeno) {
    int count = freeDAO.increaseLike(freeno);
    return count;
  }

  /* 좋아요가 1에서 0으로 변경 */
  @Override
  public int decreaseLike(int freeno) {
    int count = freeDAO.decreaseLike(freeno);
    return count;
  }

  



}
