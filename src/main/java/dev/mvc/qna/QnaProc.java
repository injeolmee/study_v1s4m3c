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
         페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
     1페이지: nowPage = 1, (1-1) * 10 => 0
     2페이지: nowPage = 2, (2-1) * 10 => 10
     3페이지: nowPage = 3, (3-1) * 10 => 20   
     */    
     int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Qna.RECORD_PER_PAGE;
     int startNum = beginOfPage + 1; // 시작 rownum/ 1페이지: 1/ 2페이지: 11
     int endNum = beginOfPage + Qna.RECORD_PER_PAGE; // 종료 rownum/ 1페이지: 10/ 2페이지: 20
     /*    
     1페이지: WHERE r >= 1 AND r <= 10
     2페이지: WHERE r >= 11 AND r <= 20
     3페이지: WHERE r >= 21 AND r <= 30
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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String qnatitle){ 
    int totalPage = (int)(Math.ceil((double)search_count/Qna.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Qna.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Qna.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Qna.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Qna.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Qna.PAGE_PER_BLOCK; // 이전 페이지로 이동 
    
      if (nowGrp >= 2){ 
        str.append("<span class='span_box_1'><A href='./list_all_qna.do?qnatitle="+qnatitle+"&nowPage="+_nowPage+"'>이전</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
        }else{
          // 현재 페이지가 아닌 페이지
          str.append("<span class='span_box_1'><A href='./list_all_qna.do?qnatitle="+qnatitle+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Contest.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
      if (nowGrp < totalGrp){ 
        str.append("<span class='span_box_1'><A href='./list_all_qna.do?qnatitle="+qnatitle+"&nowPage="+_nowPage+"'>다음</A></span>"); 
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
