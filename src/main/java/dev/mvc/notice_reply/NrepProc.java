package dev.mvc.notice_reply;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.notice.Notice;

@Component("dev.mvc.notice_reply.NrepProc")
public class NrepProc implements NrepProcInter {
  @Autowired
  @Qualifier("dev.mvc.notice_reply.NrepDAO")
  private NrepDAOInter nrepDAO;

  public NrepProc() {
  }

  @Override
  public int nrep_create(NrepVO nrepVO) {
    return nrepDAO.nrep_create(nrepVO);
  }

  @Override
  public List<NrepVO> nrep_list(HashMap hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Notice_reply.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // ���� rownum/ 1������: 1/ 2������: 11
    int endNum = beginOfPage + Notice_reply.RECORD_PER_PAGE; // ���� rownum/ 1������: 10/ 2������: 20
    
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);

    
    return nrepDAO.nrep_list(hashMap);
  }
  
  @Override
  public String paging(int search_count, int nowPage) {
    int totalPage = (int)(Math.ceil((double)search_count/Notice_reply.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Notice_reply.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Notice_reply.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Notice_reply.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Notice_reply.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    float: none;");   
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    float: none;");    
    str.append("    text-align: center;");    
    str.append("    background-color: #FECE1A;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Notice_reply.PAGE_PER_BLOCK; // ���� �������� �̵� 
    
      if (nowGrp >= 2){ 
         str.append("<span class='span_box_1'><A href='./nrep_list.do?nowPage="+_nowPage+"'>����</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
        }else{
          // ���� �������� �ƴ� ������
          str.append("<span class='span_box_1'><A href='./nrep_list.do?nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Notice.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
      if (nowGrp < totalGrp){ 
        str.append("<span class='span_box_1'><A href='./nrep_list.do?nowPage="+_nowPage+"'>����</A></span>"); 
      } 
      str.append("</DIV>"); 
     
    return str.toString(); 
  }

  @Override
  public int search_count(int noticeno) {
    return nrepDAO.search_count(noticeno);
  }

  @Override
  public NrepVO nrep_read(HashMap hashMap) {
    return nrepDAO.nrep_read(hashMap);
  }

  @Override
  public int nrep_update(NrepVO nrepVO) {
    return nrepDAO.nrep_update(nrepVO);
  }

  @Override
  public int nrep_delete(HashMap hashMap) {
    return nrepDAO.nrep_delete(hashMap);
  }

  @Override
  public int nrep_deleteAll(int noticeno) {
    return nrepDAO.nrep_deleteAll(noticeno);
  }

  @Override
  public int nrep_ck_memberno(HashMap hashMap) {
    return nrepDAO.nrep_ck_memberno(hashMap);
  }



}
