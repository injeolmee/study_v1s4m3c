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

  /* ��� */
  @Override
  public int create(FreeVO freeVO) {
    int count = 0;
    count = freeDAO.create(freeVO);
    return count;
  }

  /* ��� */
  @Override
  public List<FreeVO> list() {
    List <FreeVO> list = freeDAO.list();
    
    Iterator<FreeVO> iter = list.iterator();
    
    while (iter.hasNext()) { // ������ �� ��� "..."�� ó���ϴ� ����
      FreeVO freeVO = iter.next();
      String freetitle = Tool.textLength(freeVO.getFreetitle(), 20);
      freeVO.setFreetitle(freetitle);
    }

    return list;
  }

  /* ��ȸ */
  @Override
  public FreeVO read(int freeno) {
    FreeVO freeVO = freeDAO.read(freeno);
    return freeVO;
  }

  /* ��ȸ�� ���� */
  @Override
  public int increaseCnt(int freeno) {
    int count = freeDAO.increaseCnt(freeno);
    return count;
  }
 
  /* ���� */
  @Override
  public int update(FreeVO freeVO) {
    int count = freeDAO.update(freeVO);
    return count;
  }

  /* �н����� �˻� */
  @Override
  public int passwd_check(FreeVO freeVO) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    hashMap.put("freeno", freeVO.getFreeno());
    hashMap.put("freepasswd", freeVO.getFreepasswd());
    
    /*System.out.println("passwd: " + freeVO.getFreepasswd());*/
    
    int count = freeDAO.passwd_check(hashMap);
    return count;
  }

  /* ���� */
  @Override
  public int delete(int freeno) {
    int count = freeDAO.delete(freeno);
    return count;
  }

  /* �˻� */
  @Override
  public List<FreeVO> list_search(HashMap hashMap) {
    // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 1���� ����
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Free.RECORD_PER_PAGE; // 0 * 10 = 0
    
    int startNum = beginOfPage + 1;                             // ������ Rownum (1)
    int endNum = beginOfPage + Free.RECORD_PER_PAGE; // ������ Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <FreeVO> list = freeDAO.list_search(hashMap);
    
    Iterator<FreeVO> iter = list.iterator();
    
    while (iter.hasNext()) { // ������ �� ��� "..."�� ó���ϴ� ����
      FreeVO freeVO = iter.next();
      String freetitle = Tool.textLength(freeVO.getFreetitle(), 20);
      freeVO.setFreetitle(freetitle);
    }

    return list;
  }
  
  /* �˻��� ���ڵ� ���� */
  @Override
  public int search_count(HashMap hashMap) {
    int count = freeDAO.search_count(hashMap);
    return count;
  } 

  /**
   * ����¡ ó��
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   * @param nowPage ���� ������
   * @param word �˻���
   * @param serach �˻� Value Option
   * @return ����¡ ���� ���ڿ�
   */
  @Override
  public String paging(int nowPage, int search_count, String word, String search) { 
    // ���� (1) public static int RECORD_PER_PAGE = 5; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 10; ���� ������ ��
    
    int totalPage = (int)(Math.ceil((double)search_count/Free.RECORD_PER_PAGE)); // ��ü ������ 
    int totalGrp = (int)(Math.ceil((double)totalPage/Free.PAGE_PER_BLOCK));         // ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Free.PAGE_PER_BLOCK));          // ���� �׷� 
    int startPage = ((nowGrp - 1) * Free.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)  
    int endPage = (nowGrp * Free.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
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
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    float: none;"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>");
 
    int _nowPage = (nowGrp-1) * Free.PAGE_PER_BLOCK; // ���� �������� �̵� (���� ������ �׷� - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='/study/nonuser/free/list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='/study/nonuser/free/list.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Free.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/nonuser/free/list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  /* ��õ�� �ֻ��� 3�� ��� */
  @Override
  public List<FreeVO> list_like(HashMap<String, Object> hashMap2) {
    
    int sNum = 1; // ����� ������ ���۹�ȣ
    int eNum = 3; // ����� ������ �����ȣ
    
    hashMap2.put("sNum", sNum);
    hashMap2.put("eNum", eNum);
    
    List <FreeVO> list_like = freeDAO.list_like(hashMap2);
    
    Iterator<FreeVO> iter = list_like.iterator();
    
    while (iter.hasNext()) { // ������ �� ��� "..."�� ó���ϴ� ����
      FreeVO freeVO = iter.next();
      String freetitle = Tool.textLength(freeVO.getFreetitle(), 14);
      freeVO.setFreetitle(freetitle);
    }

    return list_like;
  }

  /* ��ȸ�� �ֻ��� 3�� ��� */
  @Override
  public List<FreeVO> list_cnt(HashMap<String, Object> hashMap3) {
    
    int sNum = 1;
    int eNum = 3;
    
    hashMap3.put("sNum", sNum);
    hashMap3.put("eNum", eNum);
    
    List <FreeVO> list_cnt = freeDAO.list_cnt(hashMap3);
    
    Iterator<FreeVO> iter = list_cnt.iterator();
    
    while (iter.hasNext()) { // ������ �� ��� "..."�� ó���ϴ� ����
      FreeVO freeVO = iter.next();
      String freetitle = Tool.textLength(freeVO.getFreetitle(), 14);
      freeVO.setFreetitle(freetitle);
    }
 
    return list_cnt;
  }

  /* ������ ��ȸ */
  @Override
  public int read_pre(int freeno) {
    int count = freeDAO.read_pre(freeno);
    return count;
  }

  /* ������ ��ȸ */
  @Override
  public int read_post(int freeno) {
    int count = freeDAO.read_post(freeno);
    return count;
  }

  /* ���̵� �˻� */
  @Override
  public int member_check(FreeVO freeVO) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    hashMap.put("freeno", freeVO.getFreeno());
    hashMap.put("memberno", freeVO.getMemberno());

    int count = freeDAO.member_check(hashMap);
    return count;
  }

  /* ���ƿ䰡 0���� 1�� ���� */
  @Override
  public int increaseLike(int freeno) {
    int count = freeDAO.increaseLike(freeno);
    return count;
  }

  /* ���ƿ䰡 1���� 0���� ���� */
  @Override
  public int decreaseLike(int freeno) {
    int count = freeDAO.decreaseLike(freeno);
    return count;
  }

  



}
