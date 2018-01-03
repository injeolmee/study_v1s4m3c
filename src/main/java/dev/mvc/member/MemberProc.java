package dev.mvc.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;



@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
  private static final int Map = 0;
  @Autowired
  @Qualifier("dev.mvc.member.MemberDAO")
  private MemberDAOInter memberDAO;

  public MemberProc() {
    
  }

  @Override
  public int memberjoin(MemberVO memberVO) {
    return memberDAO.memberjoin(memberVO);
  }
  
  @Override
  public int check_id(String memid) {
    return memberDAO.check_id(memid);
  }

  @Override
  public int check_email(String mememail) {
    return memberDAO.check_email(mememail);
  }
  
  /**
   * Ű�� �����մϴ�. ��) ABC + ����ð�: ABC1234567890123
   * @return
   */
  public String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26��)
    Random rnd = new Random();
    int su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
 
    code = code + new Date().getTime();
    // System.out.println("CODE: " + code); // CODE: XGL1449819012230 
    
    return code;
  }

  @Override
  public List<MemberVO> mem_list(HashMap hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Member.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // ���� rownum/ 1������: 1/ 2������: 11
    int endNum = beginOfPage + Member.RECORD_PER_PAGE; // ���� rownum/ 1������: 10/ 2������: 20
    
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    List<MemberVO> mem_list = memberDAO.mem_list(hashMap);
    return mem_list;
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return memberDAO.search_count(hashMap);
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @param search �˻��ɼ�
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String word, String search){ 
    int totalPage = (int)(Math.ceil((double)search_count/Member.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Member.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Member.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Member.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Member.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
 
    int _nowPage = (nowGrp-1) * Member.PAGE_PER_BLOCK; // ���� �������� �̵� 
    
      if (nowGrp >= 2){ 
        //str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
         str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>����</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
        }else{
          // ���� �������� �ƴ� ������
          // str.append("<span class='span_box_1'><A href='./mem_list.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
          str.append("<span class='span_box_1'><A href='./mem_list.do?word="+word+"&search="+search+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Member.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
      if (nowGrp < totalGrp){ 
        // str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
        str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>����</A></span>"); 
      } 
      str.append("</DIV>"); 
     
    return str.toString(); 
  } 

  @Override
  public MemberVO mem_read(int memberno) {
    return memberDAO.mem_read(memberno);
  }

  @Override
  public MemberVO mem_read_id(String memid) {
    return memberDAO.mem_read_id(memid);
  }

  @Override
  public int mem_update(MemberVO memberVO) {
    return memberDAO.mem_update(memberVO);
  }

  @Override
  public int login(String memid, String mempasswd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("memid", memid);
    map.put("mempasswd", mempasswd);
    
    int cnt = memberDAO.login(map);
    return cnt;
  }

  @Override
  public String find_memid(HashMap hashMap) {
    return memberDAO.find_memid(hashMap);
  }

  @Override
  public String find_mempasswd(HashMap hashMap) {
    return memberDAO.find_mempasswd(hashMap);
  }

  @Override
  public int mempasswd_change(MemberVO memberVO) {
    return memberDAO.mempasswd_change(memberVO);
  }




}
