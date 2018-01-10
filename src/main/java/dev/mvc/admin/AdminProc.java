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
   * 키를 조합합니다. 예) ABC + 현재시간: ABC1234567890123
   * @return
   */
  public String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26자)
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
    int startNum = beginOfPage + 1; // 시작 rownum/ 1페이지: 1/ 2페이지: 11
    int endNum = beginOfPage + Admin.RECORD_PER_PAGE; // 종료 rownum/ 1페이지: 10/ 2페이지: 20
    
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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @param search 검색옵션
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String word, String search){ 
    int totalPage = (int)(Math.ceil((double)search_count/Admin.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Admin.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Admin.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Admin.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Admin.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
 
    int _nowPage = (nowGrp-1) * Admin.PAGE_PER_BLOCK; // 이전 페이지로 이동 
    
      if (nowGrp >= 2){ 
        //str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>이전</A></span>"); 
         str.append("<span class='span_box_1'><A href='./admin_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>이전</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
        }else{
          // 현재 페이지가 아닌 페이지
          // str.append("<span class='span_box_1'><A href='./mem_list.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
          str.append("<span class='span_box_1'><A href='./admin_list.do?word="+word+"&search="+search+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Admin.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
      if (nowGrp < totalGrp){ 
        // str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>다음</A></span>"); 
        str.append("<span class='span_box_1'><A href='./admin_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>다음</A></span>"); 
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
