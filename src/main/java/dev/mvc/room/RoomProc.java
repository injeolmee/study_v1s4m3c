package dev.mvc.room;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;

@Component("dev.mvc.room.RoomProc")
public class RoomProc implements RoomProcInter {
  @Autowired
  @Qualifier("dev.mvc.room.RoomDAO")
  private RoomDAOInter roomDAO = null;
  
  public RoomProc() {
    System.out.println("--> RoomProc created.");
  }

  /**
   * <Xmp>
   * 글 등록
   * <insert id="create" parameterType="RoomVO">
   * </Xmp>
   * @param roomVO
   * @return int
   */
  @Override
  public int create(RoomVO roomVO) {
    int count = 0;
    count = roomDAO.create(roomVO);
    
    return count; 
  }

  /**
   * <Xmp>
   * 목록 (검색 & 페이징)
   * <select id="list" resultType="RoomVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public List<RoomVO> list(HashMap hashMap) {
    /* 
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지: nowPage = 1, (1 - 1) * 10  --> 0
    2 페이지: nowPage = 2, (2 - 1) * 10  --> 10
    3 페이지: nowPage = 3, (3 - 1) * 10  --> 20
    */
     int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Room.RECORD_PER_PAGE;
 
     // 시작 rownum, 1페이지: 1, 2페이지: 11, 3페이지: 21
     int startNum = beginOfPage + 1; 
     // 종료 rownum, 1페이지: 10, 2페이지: 20, 3페이지: 30
     int endNum = beginOfPage + Room.RECORD_PER_PAGE; 
 
     /*
    1 페이지: WHRER r >= 1 AND r <= 10
    2 페이지: WHRER r >= 11 AND r <= 20
    3 페이지: WHRER r >= 21 AND r <= 30
    */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
 
    List<RoomVO> list = roomDAO.list(hashMap);  
    Iterator<RoomVO> iter = list.iterator();
 
    while(iter.hasNext() == true) {
      RoomVO roomVO = iter.next();
      String roname = Tool.textLength(roomVO.getRoname(), 90);
      roomVO.setRoname(roname);
    }
 
    return list;
  }
  
  
  /**
   * 검색된 레코드 갯수
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = roomDAO.search_count(hashMap);
    
    return cnt;
  }
  
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param ronalo 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String ronalo){ 
    int totalPage = (int)(Math.ceil((double)search_count/Room.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Room.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Room.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Room.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Room.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
 
    int _nowPage = (nowGrp-1) * Room.PAGE_PER_BLOCK; // 이전 페이지로 이동 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list.do?nowPage="+_nowPage+"&ronalo="+ronalo+"'>이전</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='./list.do?nowPage="+i+"&ronalo="+ronalo+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Room.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list.do?nowPage="+_nowPage+"&ronalo="+ronalo+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  
  /**
   * 한건의 레코드 조회
   * <select id="read" resultType="RoomVO" parameterType="int">
   * @param rono 글 번호
   * @return
   */
  @Override
  public RoomVO read(int rono) {
    RoomVO roomVO = roomDAO.read(rono);
    
    long rosize1 = roomVO.getRosize1();
    
    if (rosize1 > 0) {
      String size1Label = Tool.unit(rosize1); // 파일 단위 문자열 변환 예) KB, MB, GB
      roomVO.setSize1Label(size1Label);
    }
    
    String roname = roomVO.getRoname();
    roname = Tool.convertChar(roname);
    roomVO.setRoname(roname);
    
    String rocontent = roomVO.getRocontent();
    //content = Tool.convertChar(content); // 특수 문자 변환
    roomVO.setRocontent(rocontent);
    
    return roomVO;
  }
  
  
  @Override
  public RoomVO update(int rono) {
    RoomVO roomVO = roomDAO.read(rono);
    
    String roname = roomVO.getRoname();
    roname = Tool.convertChar(roname);
    roomVO.setRoname(roname);   
    
    String rocontent = roomVO.getRocontent();
    rocontent = Tool.convertChar(rocontent);
    roomVO.setRocontent(rocontent);
    return roomVO;
  }
  
  @Override
  public int update(RoomVO roomVO) {
    return roomDAO.update(roomVO);
  }

  @Override
  public int delete(int rono) {
    int count = roomDAO.delete(rono);
    return count;
  }

}
