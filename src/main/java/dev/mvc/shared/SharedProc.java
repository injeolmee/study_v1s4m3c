package dev.mvc.shared;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import nation.web.tool.Tool;

@Repository("dev.mvc.shared.SharedProc")
public class SharedProc implements SharedProcInter {
 
  @Autowired
  @Qualifier("dev.mvc.shared.SharedDAO")
  private SharedDAOInter sharedDAO;
  
  public SharedProc(){
    // System.out.println(" => SharedProc created");
  }
  
  /* ��� */
  @Override
  public int create(SharedVO sharedVO) {
    int count = sharedDAO.create(sharedVO);
    return count;
  }

  /* ��� Grid�� */
  @Override
  public List<SharedVO> list_grid(HashMap hashMap) {
    
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Shared.RECORD_PER_PAGE_G;
    
    int startNum = beginOfPage + 1;                             // ������ Rownum (1)
    int endNum = beginOfPage + Shared.RECORD_PER_PAGE_G; // ������ Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <SharedVO> list_grid = sharedDAO.list_search(hashMap);
    Iterator<SharedVO> iter = list_grid.iterator();
    
    //*********** ������ �� ��� "..."�� ó���ϴ� ���� ****************************
    while (iter.hasNext()) { 
      SharedVO sharedVO = iter.next();
      String sharedtitle = Tool.textLength(sharedVO.getSharedtitle(), 20);
      sharedVO.setSharedtitle(sharedtitle);
    }
    //********************************************************************************
    
    return list_grid;
  }

  /* ��� + �˻� + ����¡ */
  @Override
  public List<SharedVO> list_search(HashMap hashMap) {
    
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Shared.RECORD_PER_PAGE;
    
    int startNum = beginOfPage + 1;                             // ������ Rownum (1)
    int endNum = beginOfPage + Shared.RECORD_PER_PAGE; // ������ Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <SharedVO> list = sharedDAO.list_search(hashMap);
    
    Iterator<SharedVO> iter = list.iterator();
    
    //*********** ������ �� ��� "..."�� ó���ϴ� ���� ****************************
    while (iter.hasNext()) {
      SharedVO sharedVO = iter.next();
      String sharedtitle = Tool.textLength(sharedVO.getSharedtitle(), 25);
      sharedVO.setSharedtitle(sharedtitle);
    }
    //********************************************************************************
    
    return list;
  }

  /* �˻��� ���ڵ� ���� */
  @Override
  public int search_count(HashMap hashMap) {
    int count = sharedDAO.search_count(hashMap);
    return count;
  }

  /* ����� ����¡ */
  @Override
  public String paging(int nowPage, int search_count, String word, String search) { 
    // ���� (1) public static int RECORD_PER_PAGE = 5; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 10; ���� ������ ��
    
    int totalPage = (int)(Math.ceil((double)search_count/Shared.RECORD_PER_PAGE)); // ��ü ������ 
    int totalGrp = (int)(Math.ceil((double)totalPage/Shared.PAGE_PER_BLOCK));         // ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Shared.PAGE_PER_BLOCK));          // ���� �׷� 
    int startPage = ((nowGrp - 1) * Shared.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)  
    int endPage = (nowGrp * Shared.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
 
    int _nowPage = (nowGrp-1) * Shared.PAGE_PER_BLOCK; // ���� �������� �̵� (���� ������ �׷� - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='/study/user/shared/list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='/study/user/shared/list.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Shared.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/user/shared/list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  /* Grid�� ����¡ */
  @Override
  public String paging_grid(int nowPage, int search_count, String word, String search) { 
    // ���� (1) public static int RECORD_PER_PAGE = 5; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 10; ���� ������ ��
       
    int totalPage = (int)(Math.ceil((double)search_count/Shared.RECORD_PER_PAGE_G)); // ��ü ������ 
    int totalGrp = (int)(Math.ceil((double)totalPage/Shared.PAGE_PER_BLOCK_G));         // ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Shared.PAGE_PER_BLOCK_G));          // ���� �׷� 
    int startPage = ((nowGrp - 1) * Shared.PAGE_PER_BLOCK_G) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)  
    int endPage = (nowGrp * Shared.PAGE_PER_BLOCK_G);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {display: inline-table; text-align: center; margin-top: 5px; font-size: 1em;}"); 
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
 
    int _nowPage = (nowGrp-1) * Shared.PAGE_PER_BLOCK_G; // ���� �������� �̵� (���� ������ �׷� - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='/study/user/shared/list_grid.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='/study/user/shared/list_grid.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Shared.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/user/shared/list_grid.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  /* ��ȸ */
  @Override
  public SharedVO read(int sharedno) {
    SharedVO sharedVO = sharedDAO.read(sharedno);
    
    long sharedsize = sharedVO.getSharedsize();
    
    if (sharedsize > 0) {
      String sharedsizeLabel = Tool.unit(sharedsize); // ���� ���� ���ڿ� ��ȯ KB, MB...
      sharedVO.setSharedsizeLabel(sharedsizeLabel);
    }
    
    String sharedtitle = sharedVO.getSharedtitle();
    sharedtitle = Tool.convertChar(sharedtitle);
    sharedVO.setSharedtitle(sharedtitle);

    return sharedVO;
  }

  /* ��ȸ�� ��� */
  @Override
  public int increaseCnt(int sharedno) {
    int count = sharedDAO.increaseCnt(sharedno);
    return count;
  }

  /* ���� */
  @Override
  public int update(SharedVO sharedVO) {
    int count = sharedDAO.update(sharedVO);
    return count;
  }

  /* ���� */
  @Override
  public int delete(int sharedno) {
    int count = sharedDAO.delete(sharedno);
    return count;
  }

  /* ������ ��ȸ */
  @Override
  public int read_pre(int sharedno) {
    int count = sharedDAO.read_pre(sharedno);
    return count;
  }

  /* ������ ��ȸ */
  @Override
  public int read_post(int sharedno) {
    int count = sharedDAO.read_post(sharedno);
    return count;
  }

  /* ���̵� �˻� */
  @Override
  public int member_check(SharedVO sharedVO) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    
    hashMap.put("memberno", sharedVO.getMemberno());
    hashMap.put("sharedno", sharedVO.getSharedno());
    
    int count = sharedDAO.member_check(hashMap);
    return count;
  }

  /* ������ �Խñ� ��� */
  @Override
  public int create_admin(SharedVO sharedVO) {
    return sharedDAO.create_admin(sharedVO);
  }

}
