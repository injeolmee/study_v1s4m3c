package dev.mvc.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.member.MemberVO;


@Component("dev.mvc.admin.AdminProc")
public class AdminProc implements AdminProcInter {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminDAO")
  private AdminDAOInter adminDAO;

  public AdminProc() {
  }

  @Override
  public int check_master(String admauth) {
    return adminDAO.check_master(admauth);
  }

  @Override
  public int admin_create(AdminVO adminVO) {
    return adminDAO.admin_create(adminVO);
  }

  @Override
  public int check_admid(String admid) {
    return adminDAO.check_admid(admid);
  }

  @Override
  public int check_admemail(String admemail) {
    return adminDAO.check_admemail(admemail);
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
  public List<AdminVO> admin_list(HashMap hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Admin.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // ���� rownum/ 1������: 1/ 2������: 11
    int endNum = beginOfPage + Admin.RECORD_PER_PAGE; // ���� rownum/ 1������: 10/ 2������: 20
    
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<AdminVO> admin_list = adminDAO.admin_list(hashMap);
    return admin_list;
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return adminDAO.search_count(hashMap);
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @param search �˻��ɼ�
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String word, String search){ 
    int totalPage = (int)(Math.ceil((double)search_count/Admin.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Admin.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Admin.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Admin.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Admin.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em; float:left;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    text-align: center;");
    str.append("    font-size: 1em;");
    str.append("    border: 0px;"); // str.append(" border: 1px;");
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
 
    int _nowPage = (nowGrp-1) * Admin.PAGE_PER_BLOCK; // ���� �������� �̵� 
    
      if (nowGrp >= 2){ 
        //str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
         str.append("<span class='span_box_1'><A href='./admin_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>����</A></span>"); 
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
          str.append("<span class='span_box_1'><A href='./admin_list.do?word="+word+"&search="+search+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Admin.PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
      if (nowGrp < totalGrp){ 
        // str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
        str.append("<span class='span_box_1'><A href='./admin_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>����</A></span>"); 
      } 
      str.append("</DIV>"); 
     
    return str.toString(); 
  } 

  @Override
  public AdminVO admin_read(int adminno) {
    return adminDAO.admin_read(adminno);
  }

  @Override
  public AdminVO admin_read_id(String admid) {
    return adminDAO.admin_read_id(admid);
  }

  @Override
  public int admin_update(AdminVO adminVO) {
    return adminDAO.admin_update(adminVO);
  }

  @Override
  public int admin_login(String admid, String admpasswd) {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("admid", admid);
    hashMap.put("admpasswd", admpasswd);
    System.out.println("admid: " + admid);
    System.out.println("admpasswd: " + admpasswd);
    
    int cnt = adminDAO.admin_login(hashMap);
    System.out.println("cnt: " + cnt);
    return cnt;
  }
  
  
  @Override
  public String find_admid(HashMap hashMap) {
    return adminDAO.find_admid(hashMap);
  }

  @Override
  public String find_admpasswd(HashMap hashMap) {
    return adminDAO.find_admpasswd(hashMap);
  }

  @Override
  public int admpasswd_change(AdminVO adminVO) {
    return adminDAO.admpasswd_change(adminVO);
  }

  @Override
  public int admin_delete(AdminVO adminVO) {
    return adminDAO.admin_delete(adminVO);
  }


}
