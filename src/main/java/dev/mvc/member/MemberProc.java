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
  public List<MemberVO> mem_list(HashMap hashMap) {
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Member.RECORD_PER_PAGE;
    int startNum = beginOfPage + 1; // 시작 rownum/ 1페이지: 1/ 2페이지: 11
    int endNum = beginOfPage + Member.RECORD_PER_PAGE; // 종료 rownum/ 1페이지: 10/ 2페이지: 20
    
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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @param search 검색옵션
   * @return 페이징 생성 문자열
   */ 
  public String paging(int search_count, int nowPage, String word, String search){ 
    int totalPage = (int)(Math.ceil((double)search_count/Member.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Member.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Member.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Member.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Member.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
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
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    int _nowPage = (nowGrp-1) * Member.PAGE_PER_BLOCK; // 이전 페이지로 이동 
    
      if (nowGrp >= 2){ 
        //str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>이전</A></span>"); 
         str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>이전</A></span>"); 
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
          str.append("<span class='span_box_1'><A href='./mem_list.do?word="+word+"&search="+search+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
      _nowPage = (nowGrp * Member.PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
      if (nowGrp < totalGrp){ 
        // str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"'>다음</A></span>"); 
        str.append("<span class='span_box_1'><A href='./mem_list.do?&word="+word+"&nowPage="+_nowPage+"&search="+search+"'>다음</A></span>"); 
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
