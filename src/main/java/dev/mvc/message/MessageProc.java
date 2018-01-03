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
   * ���� ����
   */
  @Override
  public int msg_create(int memberno_send, int memberno_recv, String msg_title, String msg_content) {
    System.out.println(" ==> msg_create() ���� : ���� ������, ������ ��� : "+memberno_send+" �޴� ���:"+memberno_recv);
    HashMap<String, Object> hashMap =new HashMap<String, Object>();
    hashMap.put("memberno_send", memberno_send);
    hashMap.put("memberno_recv", memberno_recv);
    hashMap.put("msg_title", msg_title);
    hashMap.put("msg_content", msg_content);
    
    System.out.println(" ==> ���� ���� msg_content:"+msg_content); 
      
    return messageDAO.msg_create(hashMap);
  }

  /**
   * ȸ����ȣ ��ȿ �˻�
   */
  @Override
  public int member_count(int memberno) {
    return messageDAO.member_count(memberno);
  }

  /**
   * ���� ���� ����
   */
  @Override
  public int msgsend_insert(int memberno_send, int msg_no) {
    System.out.println(" ==> [���� ���� ���� ����], memberno_send:"+memberno_send);
    HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
    hashMap.put("memberno_send", memberno_send);
    hashMap.put("msg_no", msg_no);
    
    return messageDAO.msgsend_insert(hashMap);
  }

  /**
   * ���� ���� ����
   */
  @Override
  public int msgrecv_insert(int memberno_recv, int msg_no) { 
    System.out.println(" ==> [���� ���� ���� ����], memberno_send:"+memberno_recv);
    HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
    hashMap.put("memberno_recv", memberno_recv);
    hashMap.put("msg_no", msg_no);
    
    return messageDAO.msgrecv_insert(hashMap);
  }

  /**
   * ���� �ֱٿ� ��ϵ� ���� ��ȣ
   * @return
   */
  @Override
  public int serach_last_msg_no() {
    return messageDAO.serach_last_msg_no();
  }

  /**
   * ���� ���� ���� ���
   * �ʿ��� �Ű����� : memberno_recv - �α����� ȸ��
   *                   memberno_send - ���� ���� ȸ��
   *                   msg_title - �˻��� ����
   *                   msg_content - �˻��� ����
   *                   
   */
  @Override 
  public List<MessageVO> msg_list(int memberno_recv, String search_condition, String msgword, int nowpage) {
    System.out.println(" ==> [Proc ���� ���� ���� ���] [msg_list()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
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
      System.out.println(" Proc--> �˻� ���� x");
    }
    
    // ===============����¡�� ���� �ڵ� ����==================================
    int beginOfPage = (nowpage - 1) * 5;
    
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + 5; // ���� rownum, 5
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    //==========================================================================
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_recv", memberno_recv);
    
    System.out.println("==> ã�� ȸ�� ��ȣ:"+memsearchProc.search_memberno(msgword));
    System.out.println("==> �˻� ����:"+search_condition);
    System.out.println("==> ���� ȸ��:"+memberno_recv);
    
    List<MessageVO> list=messageDAO.msg_list(hashMap);  // hashmap �ʿ���
    
    MemsearchVO id_name;
    for(int i=0; i<list.size(); i++){
      id_name= memsearchProc.search(list.get(i).getMemberno_send());  // ���� ����� ȸ����ȣ�� ���� id_name ��ü ����
      list.get(i).setMemid(id_name.getMemid());                      // ���� ��� ���̵� ��ȸ
      list.get(i).setMemname(id_name.getMemname());                  // ���� ��� �̸� ��ȸ
      list.get(i).setMsg_content(Tool.textLength(list.get(i).getMsg_content(), 65)); // ���� ���� ���� ���� ����
      list.get(i).setMsg_title(Tool.textLength(list.get(i).getMsg_title(), 10));     // ���� ���� ���� ����
      list.get(i).setMsg_date(list.get(i).getMsg_date().substring(2));
    }
    return list; 
  } 

  /**
   * ���� ���� ���� ��� ����
   */
  @Override
  public int recv_del(int msg_no) {
    return messageDAO.recv_del(msg_no);
  }

  /**
   * ���� ���� ���� ���
   */
  @Override
  public List<MessageVO> msg_send_list(int memberno_send, String search_condition, String msgword, int nowpage) {
    System.out.println(" ==> [���� ���� ���� ���] [msg_list()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
     
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_recv", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{ 
      System.out.println(" --> �˻� ���� x");
    }
    
    // ===============����¡�� ���� �ڵ� ����==================================
    int beginOfPage = (nowpage - 1) * 5; 
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + 5; // ���� rownum, 5
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    //==========================================================================
    
    hashMap.put("search_condition", search_condition);   // mybatis�� HashpMap�� ���Եȴ�. VO�� �ƴ� hashmap���� ����
    hashMap.put("memberno_send", memberno_send);
    
    List<MessageVO> list=messageDAO.msg_send_list(hashMap); // ���� ȸ�� ��ȣ�� �������� ���� ���� ���� ����Ʈ
    
    // ���� ���� ȸ������ ��ȣ�� -> ���̵�� �̸����� ��ȯ�ؾ� �Ѵ�. Memsearch�� ���.
    MemsearchVO id_name;
    for(int i=0; i<list.size(); i++){
      id_name= memsearchProc.search(list.get(i).getMemberno_recv());   // �޴� ȸ���� ��ȣ�� ������ ID�� �̸��� Ž��.
      list.get(i).setMemid(id_name.getMemid());                       // search()�� ���� ã�� ID ���� list.get(i).setMemid()�� ���� ����.
      list.get(i).setMemname(id_name.getMemname());                   // ���� ���� ����.
      list.get(i).setMsg_content(Tool.textLength(list.get(i).getMsg_content(), 65)); // ���� ���� ���� 30byte�� ����
      list.get(i).setMsg_title(Tool.textLength(list.get(i).getMsg_title(), 10)); 
      list.get(i).setMsg_date(list.get(i).getMsg_date().substring(2)); 
      if(list.get(i).getMsg_rev_date()!=null){  
        list.get(i).setMsg_rev_date(list.get(i).getMsg_rev_date().substring(2)); 
      }
    } 
    return list;
  }

  /**
   * ���� ���� ���� ��� ����.
   * @param msg_no
   * @return
   */
  @Override
  public int send_del(int msg_no) {
    System.out.println(" ==> [���� ���� ���� ����][send_del()] msg_no:"+msg_no);
    return messageDAO.send_del(msg_no);
  }
   
  /**
   * ���� ������ ���
   * @param memberno
   * @param search_condition
   * @param msgword
   * @return
   */
  @Override
  public List<MessageVO> msg_repo_list(int memberno, String search_condition, String msgword, int nowpage) {
    System.out.println(" ==> [���� ������ ���] [msg_list()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
     
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword);
    }else{
      System.out.println(" --> �˻� ���� x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno", memberno);
    
    // ===============����¡�� ���� �ڵ� ����==================================
    int beginOfPage = (nowpage - 1) * 5; 
    int startNum = beginOfPage + 1; // ���� rownum, 1 
    int endNum = beginOfPage + 5; // ���� rownum, 5 
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    //==========================================================================
     
    List<MessageVO> list=messageDAO.msg_repo_list(hashMap);  // hashmap �ʿ���
    
    MemsearchVO sender_id_name;    // �߽��� ���̵�, �̸� ��ü
    MemsearchVO receiver_id_name;  // ������ ���̵�, �̸� ��ü
    for(int i=0; i<list.size(); i++){
      sender_id_name= memsearchProc.search(list.get(i).getMemberno_send());  // ���� ����� ȸ����ȣ�� ���� id_name ��ü ����
      receiver_id_name=memsearchProc.search(list.get(i).getMemberno_recv());
      
      list.get(i).setMemid(sender_id_name.getMemid());                      // ���� ��� ���̵� ��ȸ
      list.get(i).setMemname(sender_id_name.getMemname());                  // ���� ��� �̸� ��ȸ
      list.get(i).setMsg_content(Tool.textLength(list.get(i).getMsg_content(), 65)); // ���� ���� ���� ���� ����
      list.get(i).setMsg_title(Tool.textLength(list.get(i).getMsg_title(), 10));     // ���� ���� ���� ����
      list.get(i).setMsg_date(list.get(i).getMsg_date().substring(2));
      list.get(i).setSender_id(sender_id_name.getMemid());                  // ���� ��� ID
      list.get(i).setSender_name(sender_id_name.getMemname());              // ���� ��� �̸�
      list.get(i).setReceiver_id(receiver_id_name.getMemid());              // �޴� ��� ID 
      list.get(i).setReceiver_name(receiver_id_name.getMemname());          // �޴� ��� �̸�
      if(list.get(i).getMsg_rev_date()!=null){  
        list.get(i).setMsg_rev_date(list.get(i).getMsg_rev_date().substring(2));  // ������ ��¥(���� ����) 
      }
    }
    return list; 
  }
  
  /**
   * ���� �� ������ ����Ʈ ���
   * @param memberno_send
   * @param search_condition
   * @param msgword
   * @param nowpage
   * @return
   */
  @Override
  public List<MessageVO> msg_self_list(int memberno_send, String search_condition, String msgword, int nowpage) {
    System.out.println(" ==> [Proc ���� �� ���� ���] [msg_self_list()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
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
      System.out.println(" Proc--> �˻� ���� x");
    }
    
    // ===============����¡�� ���� �ڵ� ����==================================
    int beginOfPage = (nowpage - 1) * 5;
    
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + 5; // ���� rownum, 5
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    //==========================================================================
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_send", memberno_send);
    hashMap.put("memberno_recv", memberno_send); 
    
    List<MessageVO> list=messageDAO.msg_self_list(hashMap);  // hashmap �ʿ���
    
    MemsearchVO id_name;
    for(int i=0; i<list.size(); i++){
      id_name= memsearchProc.search(list.get(i).getMemberno_send());  // ���� ����� ȸ����ȣ�� ���� id_name ��ü ����
      list.get(i).setMemid(id_name.getMemid());                      // ���� ��� ���̵� ��ȸ
      list.get(i).setMemname(id_name.getMemname());                  // ���� ��� �̸� ��ȸ
      list.get(i).setMsg_content(Tool.textLength(list.get(i).getMsg_content(), 65)); // ���� ���� ���� ���� ����
      list.get(i).setMsg_title(Tool.textLength(list.get(i).getMsg_title(), 10));     // ���� ���� ���� ����
      list.get(i).setMsg_date(list.get(i).getMsg_date().substring(2));
    }
    return list;
  }

  /**
   * ���� �б�
   * @param msg_no
   * @return
   */
  @Override
  public MessageVO read_msg(int msg_no) {
    System.out.println(" ==>[���� �б�][read_msg] msg_no:"+msg_no);
    MemsearchVO sender_id_name;    // �߽��� ���̵�, �̸� ��ü
    MemsearchVO receiver_id_name;  // ������ ���̵�, �̸� ��ü
    MessageVO read = messageDAO.read_msg(msg_no);
    // System.out.println(" --> @@@@@ :"+Tool.convertChar3(read.getMsg_content()));
    sender_id_name= memsearchProc.search(read.getMemberno_send());   // ���� ȸ���� ��ȣ -> ID�� �̸����� ��ȯ.
    read.setSender_id(sender_id_name.getMemid());
    read.setSender_name(sender_id_name.getMemname());
     
    receiver_id_name=memsearchProc.search(read.getMemberno_recv());  // �޴� ȸ���� ��ȣ -> ID�� �̸����� ��ȯ.
    read.setReceiver_id(receiver_id_name.getMemid());
    read.setReceiver_name(receiver_id_name.getMemname());
    
    return read;
  } 
  
  /**
   * �������� Ȯ�ν� ������ ���Ż��� ����
   * @param msg_no
   * @return
   */
  @Override
  public int confirm(int msg_no) {
    System.out.println(" ==>[���� ���� ���� ����][msg_no] msg_no:"+msg_no);
    return messageDAO.confirm(msg_no);  
  }

  /**
   * ���� ���� �˻� ����� ������ ��ȯ.
   * @param hashMap
   * @return 
   */
  @Override
  public int recv_search_count(int memberno_recv, String search_condition, String msgword) {
    System.out.println(" ==> [���� ���� ���� �˻� ����] [recv_search_count()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){ 
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{
      System.out.println(" --> �˻� ���� x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_recv", memberno_recv);
    
    return messageDAO.recv_search_count(hashMap); 
  }
  
  /**
   * ���� ���� �˻� ����� ������ ��ȯ.
   * @param hashMap
   * @return 
   */
  @Override
  public int send_search_count(int memberno_send, String search_condition, String msgword) {
    System.out.println(" ==> [���� ���� ���� �˻� ����] [send_search_count] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_recv", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{
      System.out.println(" --> �˻� ���� x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_send", memberno_send);
    
    return messageDAO.send_search_count(hashMap); 
  }

  /**
   * ���� ������ �˻� ����
   */
  @Override
  public int repo_search_count(int memberno, String search_condition, String msgword) {
    System.out.println(" ==> [���� ������ �˻� ����] [send_search_count] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{
      System.out.println(" --> �˻� ���� x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno", memberno);
    
    return messageDAO.repo_search_count(hashMap);
  }
  
  /**
   * ���� ���� �˻� ����� ������ ��ȯ.
   * @param hashMap
   * @return 
   */
  @Override 
  public int self_search_count(int memberno_recv, String search_condition, String msgword) {
    System.out.println(" ==> [���� �� ���� �˻� ����] [recv_search_count()] \n ==> search_condition:"+search_condition+"\n ==> msgword:"+msgword);
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    if(search_condition.equals("search_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){ 
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword); 
    }else{
      System.out.println(" --> �˻� ���� x");
    }
    
    hashMap.put("search_condition", search_condition);
    hashMap.put("memberno_recv", memberno_recv);
    hashMap.put("memberno_send", memberno_recv);
    
    return messageDAO.self_search_count(hashMap);
  }

  /**
   * ���� ������ ��� ���� (�˻��� ����) 
   */
  @Override
  public int msg_recv_all_count(int memberno_recv) {
    return messageDAO.msg_recv_all_count(memberno_recv);
  }

  /**
   * ���� ������ ��� ���� (�˻��� ����)
   * @param memberno_send
   * @return
   */
  @Override
  public int msg_send_all_count(int memberno_send) {
    return messageDAO.msg_send_all_count(memberno_send);
  }

  /**
   * ���� ������ ��� ����(�˻��� ����)
   * @param memberno
   * @return
   */
  @Override
  public int msg_repo_all_count(int memberno) {
    return messageDAO.msg_repo_all_count(memberno); 
  }
  
  /**
   * ���� �� ������ ��� ����(�˻��� ����)
   * @param memberno
   * @return
   */
  @Override
  public int msg_self_all_count(int memberno) {
    return messageDAO.msg_self_all_count(memberno); 
  }

  /**
   * ���� ���������� ���� �̵� 
   * memberno : �α����� ���� �����
   * msg_no : ���� ���õ� ���� ��ȣ(Message ���̺� ����)
   */
  @Override
  public int move_repo(int memberno, int msg_no) {
    HashMap<String, Object> hashMap =new HashMap<String, Object>();
    hashMap.put("memberno", memberno);
    hashMap.put("msg_no", msg_no);
    return messageDAO.move_repo(hashMap);
  }

  /**
   * ���� �������� ������ �����Ѵ�.
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
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����]
   * 
   * RECORD_PER_PAGE = ������ �� ���ڵ� ��
   * PAGE_PER_BLOCK = ��ϴ� ������ ��
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */
  @Override 
  public String paging(int search_count, int nowPage, int index){
    int RECORD_PER_PAGE=5;
    int PAGE_PER_BLOCK=5; 
     
    int totalPage = (int)(Math.ceil((double)search_count/RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
    
    StringBuffer str = new StringBuffer(); 
    
    str.append("<DIV id='paging'>");
    int _nowPage = (nowGrp-1) * PAGE_PER_BLOCK; // ���� �������� �̵� 
    if (nowGrp >= 2){
      if(index==0){  
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_recv_list("+_nowPage+")'>����</A></span>");
      }else if(index==1){
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_send_list("+_nowPage+")'>����</A></span>");
      }else if(index==2){
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_repo_list("+_nowPage+")'>����</A></span>");
      }else{ 
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_self_list("+_nowPage+")'>����</A></span>");
      }
    }
    
    /*System.out.println(" ==> Message ����¡ �׽�Ʈ:"+nowGrp);
    System.out.println(" ==> Message ����¡ �׽�Ʈ:"+totalGrp); 
    System.out.println(" ==> Message ����¡ �׽�Ʈ:"+startPage);
    System.out.println(" ==> Message ����¡ �׽�Ʈ:"+endPage);
    System.out.println(" ==> Message ����¡ �׽�Ʈ:"+totalPage);*/
    
    for(int i=startPage; i<=endPage; i++){
      if (i > totalPage){
        break;  
      } 
      
      if (nowPage == i){
        str.append("<span id='nowpage_value' class='span_box_2' data-nowpage="+i+">"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������ 
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
    
    _nowPage = (nowGrp * PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵� 
    if (nowGrp < totalGrp){
      if(index==0){  
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_recv_list("+_nowPage+")'>����</A></span>");
      }else if(index==1){
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_send_list("+_nowPage+")'>����</A></span>");
      }else if(index==2){
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_repo_list("+_nowPage+")'>����</A></span>");
      }else{ 
        str.append("<span class='span_box_1' id='cate_page"+_nowPage+"' data-nowpage="+_nowPage+"><A onclick='javascript:msg_self_list("+_nowPage+")'>����</A></span>");
      }
    } 
    str.append("</DIV>"); 
     
    return str.toString();
  }

  //=======================�޼��� ������ ���� Proc DAO=================================//
  
  /**
   * ��Ʈ�ѷ��� ������ �ϱ����� memberno_send, memberno_recv�� ȸ�� ���̵�, �̸����� ��ȯ�Ͽ� ������ ������ ��. => �ذ�
   */
  @Override
  public List<MessageVO> msg_list_admin(HashMap<String, Object> hashMap) {
    
    List<MessageVO> msg_list=messageDAO.msg_list_admin(hashMap);
    
    MemsearchVO sender;
    MemsearchVO receiver;
    for(int i=0; i<msg_list.size(); i++){
      sender=memsearchProc.search(msg_list.get(i).getMemberno_send());    // sender�� ���� ȸ����ȣ�� ������� id�� name ������ ������ �ִ�.
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
   * �����ڿ� ������ ����¡ 
   */
  @Override
  public String paging_admin(int page_num, int search_count, int nowPage, String first_day, String second_day, String search_condition, String msgword) {
    int RECORD_PER_PAGE=page_num;
    int PAGE_PER_BLOCK=5;  
     
    int totalPage = (int)(Math.ceil((double)search_count/RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
    
    StringBuffer str = new StringBuffer(); 
    
    str.append("<DIV id='paging'>");
    int _nowPage = (nowGrp-1) * PAGE_PER_BLOCK; // ���� �������� �̵�
    if (nowGrp >= 2){
      str.append("<span class='span_box_1'><A href='./message_admin.do?page_num="+page_num+"&nowPage="+_nowPage+"&first_day="+first_day+"&second_day="+second_day+"&search_condition="+search_condition+"&msgword="+msgword+"'>����</A></span>");
    }
    
    System.out.println(" ==>����¡ �׽�Ʈ:"+startPage);
    System.out.println(" ==>����¡ �׽�Ʈ:"+endPage);
    System.out.println(" ==>����¡ �׽�Ʈ:"+totalPage);
    
    for(int i=startPage; i<=endPage; i++){
      if (i > totalPage){
        break;
      }
       
      if(nowPage == i){
        str.append("<span id='nowpage_value' class='span_box_2' data-nowpage="+i+">"+i+"</span>"); // ���� ������, ����
      }else{ 
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1' data-nowpage="+i+"><A href='./message_admin.do?page_num="+page_num+"&nowPage="+i+"&first_day="+first_day+"&second_day="+second_day+"&search_condition="+search_condition+"&msgword="+msgword+"'>"+i+"</A></span>");
      } 
    } 
    
    
    _nowPage = (nowGrp * PAGE_PER_BLOCK)+1; // 10�� ���� �������� �̵�  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./message_admin.do?page_num="+page_num+"&nowPage="+_nowPage+"&first_day="+first_day+"&second_day="+second_day+"&search_condition="+search_condition+"&msgword="+msgword+"'>����</A></span>");
    } 
    str.append("</DIV>"); 
    
    return str.toString();
  }

  @Override
  public int message_del_admin(int msg_no) { 
    return messageDAO.message_del_admin(msg_no);
  }
  
}
