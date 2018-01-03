package dev.mvc.message;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.memsearch.MemsearchProcInter;
import dev.mvc.memsearch.MemsearchVO;
import nation.web.tool.Tool;

@Component("dev.mvc.message.MessageProc")
public class MessageProc implements MessageProcInter{
  @Autowired
  @Qualifier("dev.mvc.message.MessageDAO")
  private MessageDAOInter messageDAO; 
  
  @Autowired
  @Qualifier("dev.mvc.memsearch.MemsearchProc")
  private MemsearchProcInter memsearchProc;

  /**
   * 쪽지 저장
   */
  @Override
  public int msg_create(int memberno_send, int memberno_recv, String msg_title, String msg_content) {
    System.out.println(" ==> msg_create() 실행 : 쪽지 보내기, 보내는 사람 : "+memberno_send+" 받는 사람:"+memberno_recv);
    HashMap<String, Object> hashMap =new HashMap<String, Object>();
    hashMap.put("memberno_send", memberno_send);
    hashMap.put("memberno_recv", memberno_recv);
    hashMap.put("msg_title", msg_title);
    hashMap.put("msg_content", msg_content);
    
    System.out.println(" ==> 쪽지 저장 msg_content:"+msg_content); 
      
    return messageDAO.msg_create(hashMap);
  }

  /**
   * 회원번호 유효 검사
   */
  @Override
  public int member_count(int memberno) {
    return messageDAO.member_count(memberno);
  }

  /**
   * 전송 내역 저장
   */
  @Override
  public int msgsend_insert(int memberno_send, int msg_no) {
    System.out.println(" ==> [쪽지 전송 내역 저장], memberno_send:"+memberno_send);
    HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
    hashMap.put("memberno_send", memberno_send);
    hashMap.put("msg_no", msg_no);
    
    return messageDAO.msgsend_insert(hashMap);
  }

  /**
   * 수신 내역 저장
   */
  @Override
  public int msgrecv_insert(int memberno_recv, int msg_no) { 
    System.out.println(" ==> [쪽지 수신 내역 저장], memberno_send:"+memberno_recv);
    HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
    hashMap.put("memberno_recv", memberno_recv);
    hashMap.put("msg_no", msg_no);
    
    return messageDAO.msgrecv_insert(hashMap);
  }

  /**
   * 가장 최근에 등록된 쪽지 번호
   * @return
   */
  @Override
  public int serach_last_msg_no() {
    return messageDAO.serach_last_msg_no();
  }

  /**
   * 내가 받은 쪽지 목록
   * 필요한 매개변수 : memberno_recv - 로그인한 회원
   *                   memberno_send - 쪽지 보낸 회원
   *                   msg_title - 검색한 제목
   *                   msg_content - 검색한 내용
   *                   
   */
  @Override 
  public List<MessageVO> msg_list(int memberno_recv, String search_condition, String msgword, int nowpage) {
    System.out.println(" ==> [Proc 내가 받은 쪽지 목록] [msg_list()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    
    if(search_condition.equals("search_id")){
      /*System.out.println(memsearchDAO.search_memberno(msgword));*/
      /*hashMap.put("search_condition", "sender_id"); */
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));      
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword);
    }else{
      System.out.println(" Proc--> 검색 조건 x");
    }
    
    // ===============페이징을 위한 코드 시작==================================
    int beginOfPage = (nowpage - 1) * 5;
    
    int startNum = beginOfPage + 1; // 시작 rownum, 1
    int endNum = beginOfPage + 5; // 종료 rownum, 5
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    //==========================================================================
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_recv", memberno_recv);
    
    System.out.println("==> 찾은 회원 번호:"+memsearchProc.search_memberno(msgword));
    System.out.println("==> 검색 조건:"+search_condition);
    System.out.println("==> 현재 회원:"+memberno_recv);
    
    List<MessageVO> list=messageDAO.msg_list(hashMap);  // hashmap 필요함
    
    MemsearchVO id_name;
    for(int i=0; i<list.size(); i++){
      id_name= memsearchProc.search(list.get(i).getMemberno_send());  // 보낸 사람의 회원번호를 통해 id_name 객체 정의
      list.get(i).setMemid(id_name.getMemid());                      // 보낸 사람 아이디 조회
      list.get(i).setMemname(id_name.getMemname());                  // 보낸 사람 이름 조회
      list.get(i).setMsg_content(Tool.textLength(list.get(i).getMsg_content(), 65)); // 쪽지 본문 내용 길이 제한
      list.get(i).setMsg_title(Tool.textLength(list.get(i).getMsg_title(), 10));     // 쪽지 제목 길이 제한
      list.get(i).setMsg_date(list.get(i).getMsg_date().substring(2));
    }
    return list; 
  } 

  /**
   * 내가 받은 쪽지 목록 삭제
   */
  @Override
  public int recv_del(int msg_no) {
    return messageDAO.recv_del(msg_no);
  }

  /**
   * 내가 보낸 쪽지 목록
   */
  @Override
  public List<MessageVO> msg_send_list(int memberno_send, String search_condition, String msgword, int nowpage) {
    System.out.println(" ==> [내가 보낸 쪽지 목록] [msg_list()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
     
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_recv", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{ 
      System.out.println(" --> 검색 조건 x");
    }
    
    // ===============페이징을 위한 코드 시작==================================
    int beginOfPage = (nowpage - 1) * 5; 
    int startNum = beginOfPage + 1; // 시작 rownum, 1
    int endNum = beginOfPage + 5; // 종료 rownum, 5
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    //==========================================================================
    
    hashMap.put("search_condition", search_condition);   // mybatis의 HashpMap에 대입된다. VO가 아닌 hashmap으로 전달
    hashMap.put("memberno_send", memberno_send);
    
    List<MessageVO> list=messageDAO.msg_send_list(hashMap); // 나의 회원 번호를 기준으로 내가 보낸 쪽지 리스트
    
    // 내가 보낸 회원들의 번호를 -> 아이디와 이름으로 변환해야 한다. Memsearch를 사용.
    MemsearchVO id_name;
    for(int i=0; i<list.size(); i++){
      id_name= memsearchProc.search(list.get(i).getMemberno_recv());   // 받는 회원의 번호를 가지고 ID와 이름을 탐색.
      list.get(i).setMemid(id_name.getMemid());                       // search()를 통해 찾은 ID 값을 list.get(i).setMemid()를 통해 저장.
      list.get(i).setMemname(id_name.getMemname());                   // 위와 동일 개념.
      list.get(i).setMsg_content(Tool.textLength(list.get(i).getMsg_content(), 65)); // 쪽지 본문 내용 30byte로 압축
      list.get(i).setMsg_title(Tool.textLength(list.get(i).getMsg_title(), 10)); 
      list.get(i).setMsg_date(list.get(i).getMsg_date().substring(2)); 
      if(list.get(i).getMsg_rev_date()!=null){  
        list.get(i).setMsg_rev_date(list.get(i).getMsg_rev_date().substring(2)); 
      }
    } 
    return list;
  }

  /**
   * 내가 보낸 쪽지 목록 삭제.
   * @param msg_no
   * @return
   */
  @Override
  public int send_del(int msg_no) {
    System.out.println(" ==> [내가 보낸 쪽지 삭제][send_del()] msg_no:"+msg_no);
    return messageDAO.send_del(msg_no);
  }
   
  /**
   * 쪽지 보관함 목록
   * @param memberno
   * @param search_condition
   * @param msgword
   * @return
   */
  @Override
  public List<MessageVO> msg_repo_list(int memberno, String search_condition, String msgword, int nowpage) {
    System.out.println(" ==> [쪽지 보관함 목록] [msg_list()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
     
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword);
    }else{
      System.out.println(" --> 검색 조건 x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno", memberno);
    
    // ===============페이징을 위한 코드 시작==================================
    int beginOfPage = (nowpage - 1) * 5; 
    int startNum = beginOfPage + 1; // 시작 rownum, 1 
    int endNum = beginOfPage + 5; // 종료 rownum, 5 
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    //==========================================================================
     
    List<MessageVO> list=messageDAO.msg_repo_list(hashMap);  // hashmap 필요함
    
    MemsearchVO sender_id_name;    // 발신자 아이디, 이름 객체
    MemsearchVO receiver_id_name;  // 수신자 아이디, 이름 객체
    for(int i=0; i<list.size(); i++){
      sender_id_name= memsearchProc.search(list.get(i).getMemberno_send());  // 보낸 사람의 회원번호를 통해 id_name 객체 정의
      receiver_id_name=memsearchProc.search(list.get(i).getMemberno_recv());
      
      list.get(i).setMemid(sender_id_name.getMemid());                      // 보낸 사람 아이디 조회
      list.get(i).setMemname(sender_id_name.getMemname());                  // 보낸 사람 이름 조회
      list.get(i).setMsg_content(Tool.textLength(list.get(i).getMsg_content(), 65)); // 쪽지 본문 내용 길이 제한
      list.get(i).setMsg_title(Tool.textLength(list.get(i).getMsg_title(), 10));     // 쪽지 제목 길이 제한
      list.get(i).setMsg_date(list.get(i).getMsg_date().substring(2));
      list.get(i).setSender_id(sender_id_name.getMemid());                  // 보낸 사람 ID
      list.get(i).setSender_name(sender_id_name.getMemname());              // 보낸 사람 이름
      list.get(i).setReceiver_id(receiver_id_name.getMemid());              // 받는 사람 ID 
      list.get(i).setReceiver_name(receiver_id_name.getMemname());          // 받는 사람 이름
      if(list.get(i).getMsg_rev_date()!=null){  
        list.get(i).setMsg_rev_date(list.get(i).getMsg_rev_date().substring(2));  // 수신한 날짜(포맷 변경) 
      }
    }
    return list; 
  }
  
  /**
   * 내게 쓴 쪽지함 리스트 출력
   * @param memberno_send
   * @param search_condition
   * @param msgword
   * @param nowpage
   * @return
   */
  @Override
  public List<MessageVO> msg_self_list(int memberno_send, String search_condition, String msgword, int nowpage) {
    System.out.println(" ==> [Proc 내게 쓴 쪽지 목록] [msg_self_list()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    
    if(search_condition.equals("search_id")){
      /*System.out.println(memsearchDAO.search_memberno(msgword));*/
      /*hashMap.put("search_condition", "sender_id"); */
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));      
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword);
    }else{
      System.out.println(" Proc--> 검색 조건 x");
    }
    
    // ===============페이징을 위한 코드 시작==================================
    int beginOfPage = (nowpage - 1) * 5;
    
    int startNum = beginOfPage + 1; // 시작 rownum, 1
    int endNum = beginOfPage + 5; // 종료 rownum, 5
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    //==========================================================================
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_send", memberno_send);
    hashMap.put("memberno_recv", memberno_send); 
    
    List<MessageVO> list=messageDAO.msg_self_list(hashMap);  // hashmap 필요함
    
    MemsearchVO id_name;
    for(int i=0; i<list.size(); i++){
      id_name= memsearchProc.search(list.get(i).getMemberno_send());  // 보낸 사람의 회원번호를 통해 id_name 객체 정의
      list.get(i).setMemid(id_name.getMemid());                      // 보낸 사람 아이디 조회
      list.get(i).setMemname(id_name.getMemname());                  // 보낸 사람 이름 조회
      list.get(i).setMsg_content(Tool.textLength(list.get(i).getMsg_content(), 65)); // 쪽지 본문 내용 길이 제한
      list.get(i).setMsg_title(Tool.textLength(list.get(i).getMsg_title(), 10));     // 쪽지 제목 길이 제한
      list.get(i).setMsg_date(list.get(i).getMsg_date().substring(2));
    }
    return list;
  }

  /**
   * 쪽지 읽기
   * @param msg_no
   * @return
   */
  @Override
  public MessageVO read_msg(int msg_no) {
    System.out.println(" ==>[쪽지 읽기][read_msg] msg_no:"+msg_no);
    MemsearchVO sender_id_name;    // 발신자 아이디, 이름 객체
    MemsearchVO receiver_id_name;  // 수신자 아이디, 이름 객체
    MessageVO read = messageDAO.read_msg(msg_no);
    // System.out.println(" --> @@@@@ :"+Tool.convertChar3(read.getMsg_content()));
    sender_id_name= memsearchProc.search(read.getMemberno_send());   // 보낸 회원의 번호 -> ID와 이름으로 변환.
    read.setSender_id(sender_id_name.getMemid());
    read.setSender_name(sender_id_name.getMemname());
     
    receiver_id_name=memsearchProc.search(read.getMemberno_recv());  // 받는 회원의 번호 -> ID와 이름으로 변환.
    read.setReceiver_id(receiver_id_name.getMemid());
    read.setReceiver_name(receiver_id_name.getMemname());
    
    return read;
  } 
  
  /**
   * 수신자의 확인시 쪽지의 수신상태 변경
   * @param msg_no
   * @return
   */
  @Override
  public int confirm(int msg_no) {
    System.out.println(" ==>[쪽지 수신 상태 변경][msg_no] msg_no:"+msg_no);
    return messageDAO.confirm(msg_no);  
  }

  /**
   * 받은 쪽지 검색 결과의 갯수를 반환.
   * @param hashMap
   * @return 
   */
  @Override
  public int recv_search_count(int memberno_recv, String search_condition, String msgword) {
    System.out.println(" ==> [내가 받은 쪽지 검색 갯수] [recv_search_count()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){ 
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{
      System.out.println(" --> 검색 조건 x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_recv", memberno_recv);
    
    return messageDAO.recv_search_count(hashMap); 
  }
  
  /**
   * 보낸 쪽지 검색 결과의 갯수를 반환.
   * @param hashMap
   * @return 
   */
  @Override
  public int send_search_count(int memberno_send, String search_condition, String msgword) {
    System.out.println(" ==> [내가 받은 쪽지 검색 갯수] [send_search_count] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_recv", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{
      System.out.println(" --> 검색 조건 x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_send", memberno_send);
    
    return messageDAO.send_search_count(hashMap); 
  }

  /**
   * 쪽지 보관함 검색 갯수
   */
  @Override
  public int repo_search_count(int memberno, String search_condition, String msgword) {
    System.out.println(" ==> [쪽지 보관함 검색 갯수] [send_search_count] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{
      System.out.println(" --> 검색 조건 x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno", memberno);
    
    return messageDAO.repo_search_count(hashMap);
  }
  
  /**
   * 받은 쪽지 검색 결과의 갯수를 반환.
   * @param hashMap
   * @return 
   */
  @Override 
  public int self_search_count(int memberno_recv, String search_condition, String msgword) {
    System.out.println(" ==> [내게 쓴 쪽지 검색 갯수] [recv_search_count()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){ 
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{
      System.out.println(" --> 검색 조건 x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_recv", memberno_recv);
    hashMap.put("memberno_send", memberno_recv);
    
    return messageDAO.self_search_count(hashMap);
  }

  /**
   * 받은 쪽지함 모든 갯수 (검색과 무관) 
   */
  @Override
  public int msg_recv_all_count(int memberno_recv) {
    return messageDAO.msg_recv_all_count(memberno_recv);
  }

  /**
   * 보낸 쪽지함 모든 갯수 (검색과 무관)
   * @param memberno_send
   * @return
   */
  @Override
  public int msg_send_all_count(int memberno_send) {
    return messageDAO.msg_send_all_count(memberno_send);
  }

  /**
   * 쪽지 보관함 모든 갯수(검색과 무관)
   * @param memberno
   * @return
   */
  @Override
  public int msg_repo_all_count(int memberno) {
    return messageDAO.msg_repo_all_count(memberno); 
  }
  
  /**
   * 내게 쓴 쪽지함 모든 갯수(검색과 무관)
   * @param memberno
   * @return
   */
  @Override
  public int msg_self_all_count(int memberno) {
    return messageDAO.msg_self_all_count(memberno); 
  }

  /**
   * 쪽지 보관함으로 쪽지 이동 
   * memberno : 로그인한 현재 사용자
   * msg_no : 현재 선택된 쪽지 번호(Message 테이블에 존재)
   */
  @Override
  public int move_repo(int memberno, int msg_no) {
    HashMap<String, Object> hashMap =new HashMap<String, Object>();
    hashMap.put("memberno", memberno);
    hashMap.put("msg_no", msg_no);
    return messageDAO.move_repo(hashMap);
  }

  /**
   * 쪽지 보관함의 쪽지를 삭제한다.
   * @param msg_no
   * @param memberno
   * @return
   */
  @Override
  public int repo_del(int msg_no) {
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    hashMap.put("msg_no", msg_no);
    
    return messageDAO.repo_del(hashMap);
  }
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]
   * 
   * RECORD_PER_PAGE = 페이지 당 레코드 수
   * PAGE_PER_BLOCK = 블록당 페이지 수
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */
  @Override 
  public String paging(int search_count, int nowPage, int index){
    int RECORD_PER_PAGE=5;
    int PAGE_PER_BLOCK=5; 
     
    int totalPage = (int)(Math.ceil((double)search_count/RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
    
    StringBuffer str = new StringBuffer(); 
    
    str.append("<DIV id='paging'>");
    int _nowPage = (nowGrp-1) * PAGE_PER_BLOCK; // 이전 페이지로 이동 
    if (nowGrp >= 2){
      if(index==0){  
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_recv_list("+_nowPage+")'>이전</A></span>");
      }else if(index==1){
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_send_list("+_nowPage+")'>이전</A></span>");
      }else if(index==2){
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_repo_list("+_nowPage+")'>이전</A></span>");
      }else{ 
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_self_list("+_nowPage+")'>이전</A></span>");
      }
    }
    
    /*System.out.println(" ==> Message 페이징 테스트:"+nowGrp);
    System.out.println(" ==> Message 페이징 테스트:"+totalGrp); 
    System.out.println(" ==> Message 페이징 테스트:"+startPage);
    System.out.println(" ==> Message 페이징 테스트:"+endPage);
    System.out.println(" ==> Message 페이징 테스트:"+totalPage);*/
    
    for(int i=startPage; i<=endPage; i++){
      if (i > totalPage){
        break;  
      } 
      
      if (nowPage == i){
        str.append("<span id='nowpage_value' class='span_box_2' data-nowpage="+i+">"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지 
        if(index==0){  
          str.append("<span class='span_box_1' id='cate_page"+i+"' data-nowpage="+i+"><A onclick='javascript:msg_recv_list("+i+")'>"+i+"</A></span>");
        }else if(index==1){
          str.append("<span class='span_box_1' id='cate_page"+i+"' data-nowpage="+i+"><A onclick='javascript:msg_send_list("+i+")'>"+i+"</A></span>");
        }else if(index==2){
          str.append("<span class='span_box_1' id='cate_page"+i+"' data-nowpage="+i+"><A onclick='javascript:msg_repo_list("+i+")'>"+i+"</A></span>");
        }else{
          str.append("<span class='span_box_1' id='cate_page"+i+"' data-nowpage="+i+"><A onclick='javascript:msg_self_list("+i+")'>"+i+"</A></span>");
        }
      }
    } 
    
    _nowPage = (nowGrp * PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동 
    if (nowGrp < totalGrp){
      if(index==0){  
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_recv_list("+_nowPage+")'>다음</A></span>");
      }else if(index==1){
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_send_list("+_nowPage+")'>다음</A></span>");
      }else if(index==2){
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_repo_list("+_nowPage+")'>다음</A></span>");
      }else{ 
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_self_list("+_nowPage+")'>다음</A></span>");
      }
    } 
    str.append("</DIV>"); 
     
    return str.toString();
  }

  //=======================메세지 관리자 관련 Proc DAO=================================//
  
  /**
   * 컨트롤러에 리턴을 하기전에 memberno_send, memberno_recv를 회원 아이디, 이름으로 변환하여 저장후 보내야 함. => 해결
   */
  @Override
  public List<MessageVO> msg_list_admin(HashMap<String, Object> hashMap) {
    
    List<MessageVO> msg_list=messageDAO.msg_list_admin(hashMap);
    
    MemsearchVO sender;
    MemsearchVO receiver;
    for(int i=0; i<msg_list.size(); i++){
      sender=memsearchProc.search(msg_list.get(i).getMemberno_send());    // sender는 보낸 회원번호를 기반으로 id와 name 정보를 가지고 있다.
      receiver=memsearchProc.search(msg_list.get(i).getMemberno_recv());
      
      msg_list.get(i).setSender_id(sender.getMemid());
      msg_list.get(i).setSender_name(sender.getMemname());
      
      msg_list.get(i).setReceiver_id(receiver.getMemid());
      msg_list.get(i).setReceiver_name(receiver.getMemname());
    }
    
    return msg_list; 
  } 
  
  @Override
  public int message_admin_search_cnt(HashMap<String, Object> hashMap) {
    return messageDAO.message_admin_search_cnt(hashMap); 
  }

  /**
   * 관리자용 쪽지함 페이징 
   */
  @Override
  public String paging_admin(int page_num, int search_count, int nowPage, String first_day, String second_day, String search_condition, String msgword) {
    int RECORD_PER_PAGE=page_num;
    int PAGE_PER_BLOCK=5;  
     
    int totalPage = (int)(Math.ceil((double)search_count/RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
    
    StringBuffer str = new StringBuffer(); 
    
    str.append("<DIV id='paging'>");
    int _nowPage = (nowGrp-1) * PAGE_PER_BLOCK; // 이전 페이지로 이동
    if (nowGrp >= 2){
      str.append("<span class='span_box_1'><A href='./message_admin.do?page_num="+page_num+"&nowPage="+_nowPage+"&first_day="+first_day+"&second_day="+second_day+"&search_condition="+search_condition+"&msgword="+msgword+"'>이전</A></span>");
    }
    
    System.out.println(" ==>페이징 테스트:"+startPage);
    System.out.println(" ==>페이징 테스트:"+endPage);
    System.out.println(" ==>페이징 테스트:"+totalPage);
    
    for(int i=startPage; i<=endPage; i++){
      if (i > totalPage){
        break;
      }
       
      if(nowPage == i){
        str.append("<span id='nowpage_value' class='span_box_2' data-nowpage="+i+">"+i+"</span>"); // 현재 페이지, 강조
      }else{ 
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1' data-nowpage="+i+"><A href='./message_admin.do?page_num="+page_num+"&nowPage="+i+"&first_day="+first_day+"&second_day="+second_day+"&search_condition="+search_condition+"&msgword="+msgword+"'>"+i+"</A></span>");
      } 
    } 
    
    
    _nowPage = (nowGrp * PAGE_PER_BLOCK)+1; // 10개 다음 페이지로 이동  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./message_admin.do?page_num="+page_num+"&nowPage="+_nowPage+"&first_day="+first_day+"&second_day="+second_day+"&search_condition="+search_condition+"&msgword="+msgword+"'>다음</A></span>");
    } 
    str.append("</DIV>"); 
    
    return str.toString();
  }

  @Override
  public int message_del_admin(int msg_no) { 
    return messageDAO.message_del_admin(msg_no);
  }
  
}
