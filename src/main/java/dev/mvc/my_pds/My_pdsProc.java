package dev.mvc.my_pds;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.my_std_catelist.My_std_catelistVO;

@Component("dev.mvc.my_pds.My_pdsProc")
public class My_pdsProc implements My_pdsProcInter{
  
  private int RECORD_PER_PAGE=3;
  private int PAGE_PER_BLOCK=10;

  @Autowired
  @Qualifier("dev.mvc.my_pds.My_pdsDAO")
  private My_pdsDAOInter my_pdsDAO;
  
  @Override
  
  public int insert(My_pdsVO my_pdsVO) {
    return my_pdsDAO.insert(my_pdsVO);
  }

  @Override
  public List<My_pdsVO> list(HashMap<String, Object> hashMap) {
    return my_pdsDAO.list(hashMap);
  }

  @Override
  public My_std_catelistVO search_mylistno(HashMap<String, Integer> hashMap) {
    return my_pdsDAO.search_mylistno(hashMap);
  } 

  @Override
  public My_std_catelistVO search_cateno_stdlist_no(int mylistno) {
    return my_pdsDAO.search_cateno_stdlist_no(mylistno);
  }

  @Override
  public String search_memname(int memberno) {
    return my_pdsDAO.search_memname(memberno);
  }

  @Override
  public My_pdsVO read(int pdsno) {
    return my_pdsDAO.read(pdsno);
  }

  @Override
  public int update(My_pdsVO my_pdsVO) {
    return my_pdsDAO.update(my_pdsVO);
  }

  @Override
  public int check_passwd(HashMap<String, Object> hashMap) {
    return my_pdsDAO.check_passwd(hashMap);
  }

  @Override
  public int delete(int pdsno) {
    return my_pdsDAO.delete(pdsno);
  }

  @Override
  public int inc_cnt(int pdsno) {
    return my_pdsDAO.inc_cnt(pdsno);
  }
 
  @Override
  public int inc_like(int pdsno) {
    return my_pdsDAO.inc_like(pdsno);
  }

  @Override
  public int lastest_pdsno() {
    return my_pdsDAO.lastest_pdsno();
  }

  @Override
  public int del_file(int pdsno) {
    return my_pdsDAO.del_file(pdsno);
  }

  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    return my_pdsDAO.search_count(hashMap);
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */
  public String paging(int search_count, int nowPage, int cateno, int stdlist_no){ 
    int totalPage = (int)(Math.ceil((double)search_count/RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
    
    StringBuffer str = new StringBuffer(); 
    
    str.append("<DIV id='paging'>");
    int _nowPage = (nowGrp-1) * PAGE_PER_BLOCK; // ���� �������� �̵� 
    if (nowGrp >= 2){
      str.append("<span class='span_box_1'><A href=''>����</A></span>");
    }
    str.append("<span class='span_box_1'><A onclick='javascript:set_nowpage_cookie("+cateno+","+stdlist_no+","+startPage+");'><<</A></span>");
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break;  
      } 
       
      if (nowPage == i){
        str.append("<span class='span_box_2' data-nowpage="+i+">"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������ 
        str.append("<span class='span_box_1' id='cate_page"+i+"' data-nowpage="+i+"><A onclick='javascript:set_nowpage_cookie("+cateno+","+stdlist_no+","+i+");'>"+i+"</A></span>");   
      } 
    } 
    str.append("<span class='span_box_1'><A onclick='javascript:set_nowpage_cookie("+cateno+","+stdlist_no+","+totalPage+");'>>></A></span>");
    _nowPage = (nowGrp * PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href=''>����</A></span>");
    }  
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
}
