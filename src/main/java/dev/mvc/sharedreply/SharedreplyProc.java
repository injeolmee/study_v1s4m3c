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
  
  /* ��� ��� */
  @Override
  public int create(SharedreplyVO sharedreplyVO) {
    int count = sharedreplyDAO.create(sharedreplyVO);
    return count;
  }

  /* ���� ���� ���� */ 
  @Override
  public int updateAnsnum(SharedreplyVO sharedreplyVO) {
    int count = sharedreplyDAO.updateAnsnum(sharedreplyVO);
    return count;
  }

  /* ���� ��� */
  @Override
  public int reply(SharedreplyVO sharedreplyVO) {
    int count = sharedreplyDAO.reply(sharedreplyVO);
    return count;
  }

  /* Ư�� ��� ��ȸ */
  @Override
  public SharedreplyVO read(int shreplyno) {
    SharedreplyVO sharedreplyVO = sharedreplyDAO.read(shreplyno);
    return sharedreplyVO;
  }

  /* ��� ��� + ����¡ */
  @Override
  public List<SharedreplyVO> total_list_reply(HashMap<String, Object> hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Sharedreply.RECORD_PER_PAGE;
    
    int startNum = beginOfPage + 1;                                      // ������ Rownum (1)
    int endNum = beginOfPage + Sharedreply.RECORD_PER_PAGE; // ������ Rownum  (11)

    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List <SharedreplyVO> total_list_reply = sharedreplyDAO.total_list_reply(hashMap);

    return total_list_reply;
  }

  /* ����� �� ���ڵ� ���� ���� */
  @Override
  public int search_count(int sharedno) {
    int count = sharedreplyDAO.search_count(sharedno);
    return count;
  }

  /* ����¡ ó�� */
  @Override
  public String paging(int nowPage, int search_count, int sharedno) {
    // ���� (1) public static int RECORD_PER_PAGE = 5; // �������� ����� ���ڵ� ����
    // ���� (2) public static int PAGE_PER_BLOCK = 5; ���� ������ ��
    
    int totalPage = (int)(Math.ceil((double)search_count/Sharedreply.RECORD_PER_PAGE)); // ��ü ������ 
    int totalGrp = (int)(Math.ceil((double)totalPage/Sharedreply.PAGE_PER_BLOCK));         // ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Sharedreply.PAGE_PER_BLOCK));          // ���� �׷� 
    int startPage = ((nowGrp - 1) * Sharedreply.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ���� (1���� �׷�, 2���� �׷�...)  
    int endPage = (nowGrp * Sharedreply.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
 
    int _nowPage = (nowGrp-1) * Sharedreply.PAGE_PER_BLOCK; // ���� �������� �̵� (���� ������ �׷� - 1)
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='/study/user/shared/read.do?sharedno="+sharedno+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='/study/user/shared/read.do?sharedno="+sharedno +"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    _nowPage = (nowGrp * Sharedreply.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='/study/user/shared/read.do?&sharedno="+sharedno+"&nowPage="+_nowPage + "'>����</A></span>"); 
    } 
    str.append("</DIV>");
     
    return str.toString(); 
  }

  /* ��� ���� */
  @Override
  public int update(SharedreplyVO sharedreplyVO) {
    int count = sharedreplyDAO.update(sharedreplyVO);
    return count;
  }

  /* ��� ���� */
  @Override
  public int delete(int shreplyno) {
    int count = sharedreplyDAO.delete(shreplyno);
    return count;
  }

  /* �θ� ����� seqno�� 0���� ����*/
  @Override
  public int update_seqno(int shreplyno) {
    int count = sharedreplyDAO.update_seqno(shreplyno);
    return count;
  }

  /* �θ� ��ۿ� ���� ���� ó�� ��� content�� ������ ��� */
  @Override
  public int update_parent(int shreplyno) {
    int count = sharedreplyDAO.update_parent(shreplyno);
    return count;
  }

  /* �Խñ� ������ ��� ��ü ����*/
  @Override
  public int delete_all(int sharedno) {
    int count = sharedreplyDAO.delete_all(sharedno);
    return count;
  }

  /* ������ ��� ��� */
  @Override
  public int create_admin(SharedreplyVO sharedreplyVO) {
    return sharedreplyDAO.create_admin(sharedreplyVO);
  }

  /* ������ ���� ��� */
  @Override
  public int reply_admin(SharedreplyVO sharedreplyVO) {
    return sharedreplyDAO.reply_admin(sharedreplyVO);
  }

  /* �θ� ����� ��� ���� ����� �����ϴ��� �˻� */
  @Override
  public int parent_check(int shreplygrpno) {
    return sharedreplyDAO.parent_check(shreplygrpno);
  }

  /* ���۰� ���õǾ �� ������ ������� �˻� */
  @Override
  public int reply_check(HashMap hashMap) {
    return sharedreplyDAO.reply_check(hashMap);
  }
  
  
  
  

}
