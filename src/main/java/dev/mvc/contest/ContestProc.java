package dev.mvc.contest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nation.web.tool.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.contest.ContestProc")
public class ContestProc implements ContestProcInter {
  @Autowired
  @Qualifier("dev.mvc.contest.ContestDAO")
  private ContestDAOInter contestDAO;

  @Override
  public int create(ContestVO contestVO) {
    int count = contestDAO.create(contestVO);
    
    return count;
  }
  
  @Override
  public List<ContestVO> list_all_contest(HashMap hashMap) throws Exception {
    /* 
       페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1페이지: nowPage = 1, (1-1) * 10 => 0
    2페이지: nowPage = 2, (2-1) * 10 => 10
    3페이지: nowPage = 3, (3-1) * 10 => 20   
    */    
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Contest.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // 시작 rownum/ 1페이지: 1/ 2페이지: 11
    int endNum = beginOfPage + Contest.RECORD_PER_PAGE; // 종료 rownum/ 1페이지: 10/ 2페이지: 20
    /*    
    1페이지: WHERE r >= 1 AND r <= 10
    2페이지: WHERE r >= 11 AND r <= 20
    3페이지: WHERE r >= 21 AND r <= 30
    */   
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<ContestVO> list = contestDAO.list_all_contest(hashMap);
    
    for (int i = 0; i < list.size(); i++) {
      ContestVO contestVO = list.get(i);
      String conEnd = contestVO.getConEnd();
      String conStart = contestVO.getConStart();
      
      long time = System.currentTimeMillis();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      String today = df.format(new Date(time));
      System.out.println("today ---> " + today);
      
      Date date_e = df.parse(conEnd);
      Date date_s = df.parse(conStart);
      System.out.println("date_e ---> " + date_e);
      System.out.println("date_s ---> " + date_s);
      
      String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date_e);
      String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date_s);
      System.out.println("endDate ---> " + endDate);
      System.out.println("startDate ---> " + startDate);
      
      long now = (df.parse(today)).getTime();
      long end = (df.parse(endDate)).getTime();
      long start = (df.parse(startDate)).getTime();
      
      System.out.println("now ---> " + now);
      System.out.println("end ---> " + end);
      System.out.println("start ---> " + start);
      
      long diff_e = end - now;
      long diff_s = start - now;
      long conRemain_e = diff_e / (24 * 60 * 60 * 1000);
      long conRemain_s = diff_s / (24 * 60 * 60 * 1000);
      
      System.out.println("diff_e ---> " + diff_e);
      System.out.println("diff_s ---> " + diff_s);
      
      System.out.println("conRemain_e ---> " + conRemain_e);
      System.out.println("conRemain_s ---> " + conRemain_s);
      
      contestVO.setConRemain_e(conRemain_e);
      contestVO.setConRemain_s(conRemain_s);
    }
    
    Iterator<ContestVO> iter = list.iterator();
    
    while (iter.hasNext()) {
      ContestVO contestVO = iter.next();
      String conTitle = Tool.textLength(contestVO.getConTitle(), 200);
      contestVO.setConTitle(conTitle);
    }
    
    return list;
  }
  
  @Override
  public List<ContestVO> list_by_count() {
    List<ContestVO> list = contestDAO.list_by_count();
    
    Iterator<ContestVO> iter = list.iterator();
    
    while (iter.hasNext()) {
      ContestVO contestVO = iter.next();
      String conTitle = Tool.textLength(contestVO.getConTitle(), 200);
      contestVO.setConTitle(conTitle);
    }

    return list;
  }
  
  @Override
  public List<ContestVO> list_by_good() {
    List<ContestVO> list = contestDAO.list_by_good();
    
    Iterator<ContestVO> iter = list.iterator();
    
    while (iter.hasNext()) {
      ContestVO contestVO = iter.next();
      String conTitle = Tool.textLength(contestVO.getConTitle(), 200);
      contestVO.setConTitle(conTitle);
    }

    return list;
  }

  @Override
  public int min() {
    return contestDAO.min();
  }
  
  @Override
  public int max() {
    return contestDAO.max();
  }

  @Override
  public int increaseCnt(int conNo) {
    return contestDAO.increaseCnt(conNo);
  }
  
  @Override
  public int goodcnt_up(int conNo) {
    return contestDAO.goodcnt_up(conNo);
  }

  @Override
  public int goodcnt_down(int conNo) {
    return contestDAO.goodcnt_down(conNo);
  }

  @Override
  public int search_count(HashMap hashMap) {
    int count = contestDAO.search_count(hashMap);
    
    return count;
  }
  

  @Override
  public ContestVO read(int conNo) throws Exception {
    ContestVO contestVO = contestDAO.read(conNo);
    
    long conSize1 = contestVO.getConSize1();
    if (conSize1 > 0) {
      String size1Label = Tool.unit(conSize1);
      contestVO.setSize1Label(size1Label);
    }
    
    long conSize2 = contestVO.getConSize2();
    if (conSize2 > 0) {
      String size2Label = Tool.unit(conSize2);
      contestVO.setSize2Label(size2Label);
    }
    
    long conSize3 = contestVO.getConSize3();
    if (conSize3 > 0) {
      String size3Label = Tool.unit(conSize3);
      contestVO.setSize3Label(size3Label);
    }
    
    String conTitle = contestVO.getConTitle();
    conTitle = Tool.convertChar(conTitle);
    contestVO.setConTitle(conTitle);
    
    String conHost = contestVO.getConHost();
    conHost = Tool.convertChar(conHost);
    contestVO.setConHost(conHost);
    
    String conUrl = contestVO.getConUrl();
    conUrl = Tool.convertChar(conUrl);
    contestVO.setConUrl(conUrl);
    
    String conYou = contestVO.getConYou();
    conYou = Tool.convertChar(conYou);
    contestVO.setConYou(conYou);
    
    String conStart = contestVO.getConStart();
    conStart = Tool.convertChar(conStart);
    contestVO.setConStart(conStart);
    
    String conEnd = contestVO.getConEnd();
    conEnd = Tool.convertChar(conEnd);
    contestVO.setConEnd(conEnd);
    
    /*String conCont = contestVO.getConCont();
    conCont = Tool.convertChar(conCont);
    contestVO.setConCont(conCont);
    
    System.out.println("conEnd ---> " + conEnd);
    System.out.println("conStart ---> " + conStart);*/
    
    long time = System.currentTimeMillis();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String today = df.format(new Date(time));
    
    Date date_e = df.parse(conEnd);
    Date date_s = df.parse(conStart);
    
    String endDate = new SimpleDateFormat("yyyy-MM-dd").format(date_e);
    String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date_s);
    
    long now = (df.parse(today)).getTime();
    long end = (df.parse(endDate)).getTime();
    long start = (df.parse(startDate)).getTime();
    
    long diff_e = end - now;
    long conRemain_e = diff_e / (24 * 60 * 60 * 1000);
    
    long diff_s = start - now;
    long conRemain_s = diff_s / (24 * 60 * 60 * 1000);
    
    contestVO.setConRemain_e(conRemain_e);
    contestVO.setConRemain_s(conRemain_s);
    
    /*System.out.println("conRemain_e ---> " + conRemain_e);
    System.out.println("conRemain_s ---> " + conRemain_s);*/
    
    return contestVO;
  }
  
  @Override
  public int update(ContestVO contestVO) {
    return contestDAO.update(contestVO);
  }

  @Override
  public ContestVO update(int conNo) {
    ContestVO contestVO = contestDAO.read(conNo);
    
    String conTitle = contestVO.getConTitle();
    conTitle = Tool.convertChar(conTitle);
    contestVO.setConTitle(conTitle);
    
    String conHost = contestVO.getConHost();
    conHost = Tool.convertChar(conHost);
    contestVO.setConHost(conHost);
    
    String conStart = contestVO.getConStart();
    conStart = Tool.convertChar(conStart);
    contestVO.setConStart(conStart);
    
    String conEnd = contestVO.getConEnd();
    conEnd = Tool.convertChar(conEnd);
    contestVO.setConEnd(conEnd);
    
    String conCont = contestVO.getConCont();
    conCont = Tool.convertChar(conCont);
    contestVO.setConCont(conCont);
    
    String conUrl = contestVO.getConUrl();
    conUrl = Tool.convertChar(conUrl);
    contestVO.setConUrl(conUrl);
    
    String conYou = contestVO.getConYou();
    conYou = Tool.convertChar(conYou);
    contestVO.setConYou(conYou);
    
    return contestVO;
  }
  
  @Override
  public ContestVO update_thumb(int conNo) {
    ContestVO contestVO = contestDAO.read(conNo);
    
    return contestVO;
  }       

  @Override
  public int update_thumb(ContestVO contestVO) {
    return contestDAO.update_thumb(contestVO);
  }

  @Override
  public ContestVO update_img(int conNo) {
    ContestVO contestVO = contestDAO.read(conNo);
    
    return contestVO;
  }

  @Override
  public int update_img(ContestVO contestVO) {
    return contestDAO.update_img(contestVO);
  }

  @Override
  public ContestVO update_file(int conNo) {
    ContestVO contestVO = contestDAO.read(conNo);
    
    return contestVO;
  }

  @Override
  public int update_file(ContestVO contestVO) {
    return contestDAO.update_file(contestVO);
  }

  @Override
  public int total_count() {
    return contestDAO.total_count();        
  }

  @Override  
  public int day_count() {
    return contestDAO.day_count();        
  }

  @Override
  public int delete(int conNo) {
    int count = contestDAO.delete(conNo);
    
    return count;
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
  public String paging(int search_count, int nowPage, String conWord){ 
    int totalPage = (int)(Math.ceil((double)search_count/Contest.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Contest.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Contest.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Contest.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Contest.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
 
    int _nowPage = (nowGrp-1) * Contest.PAGE_PER_BLOCK; // 이전 페이지로 이동 
    
      if (nowGrp >= 2){ 
        str.append("<span class='span_box_1'><A href='./list_all_contest.do?conWord="+conWord+"&nowPage="+_nowPage+"'>이전</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
        }else{
          // 현재 페이지가 아닌 페이지
          str.append("<span class='span_box_1'><A href='./list_all_contest.do?conWord="+conWord+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Contest.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
      if (nowGrp < totalGrp){ 
        str.append("<span class='span_box_1'><A href='./list_all_contest.do?&conWord="+conWord+"&nowPage="+_nowPage+"'>다음</A></span>"); 
      } 
      str.append("</DIV>"); 
     
    return str.toString(); 
  } 
  
}
