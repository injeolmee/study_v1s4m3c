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
   * �� ���
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
   * ��� (�˻� & ����¡)
   * <select id="list" resultType="RoomVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public List<RoomVO> list(HashMap hashMap) {
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10  --> 0
    2 ������: nowPage = 2, (2 - 1) * 10  --> 10
    3 ������: nowPage = 3, (3 - 1) * 10  --> 20
    */
     int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Room.RECORD_PER_PAGE;
 
     // ���� rownum, 1������: 1, 2������: 11, 3������: 21
     int startNum = beginOfPage + 1; 
     // ���� rownum, 1������: 10, 2������: 20, 3������: 30
     int endNum = beginOfPage + Room.RECORD_PER_PAGE; 
 
     /*
    1 ������: WHRER r >= 1 AND r <= 10
    2 ������: WHRER r >= 11 AND r <= 20
    3 ������: WHRER r >= 21 AND r <= 30
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
   * �˻��� ���ڵ� ����
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * @return
   */
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = roomDAO.search_count(hashMap);
    
    return cnt;
  }
  
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param ronalo �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String ronalo){ 
    int totalPage = (int)(Math.ceil((double)search_count/Room.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Room.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Room.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Room.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Room.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
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
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
    //    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Room.PAGE_PER_BLOCK; // ���� �������� �̵� 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list.do?nowPage="+_nowPage+"&ronalo="+ronalo+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list.do?nowPage="+i+"&ronalo="+ronalo+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Room.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list.do?nowPage="+_nowPage+"&ronalo="+ronalo+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * <select id="read" resultType="RoomVO" parameterType="int">
   * @param rono �� ��ȣ
   * @return
   */
  @Override
  public RoomVO read(int rono) {
    RoomVO roomVO = roomDAO.read(rono);
    
    long rosize1 = roomVO.getRosize1();
    
    if (rosize1 > 0) {
      String size1Label = Tool.unit(rosize1); // ���� ���� ���ڿ� ��ȯ ��) KB, MB, GB
      roomVO.setSize1Label(size1Label);
    }
    
    String roname = roomVO.getRoname();
    roname = Tool.convertChar(roname);
    roomVO.setRoname(roname);
    
    String rocontent = roomVO.getRocontent();
    //content = Tool.convertChar(content); // Ư�� ���� ��ȯ
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
