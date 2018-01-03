package dev.mvc.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.memsearch.MemsearchProcInter;
import dev.mvc.memsearch.MemsearchVO;
import net.sf.json.JSONArray;

@Controller
public class MessageCont {
  @Autowired
  @Qualifier("dev.mvc.message.MessageProc")
  private MessageProcInter messageProc;
  
  @Autowired
  @Qualifier("dev.mvc.memsearch.MemsearchProc")
  private MemsearchProcInter memsearchProc;
  
  /**
   * ���� �⺻ ������
   * @param request 
   * @return 
   */ 
  @RequestMapping(value="/user/message/message_home.do", method=RequestMethod.GET)
  public ModelAndView message_home(HttpServletRequest request){
    System.out.println("======================message_home() ȣ��=============================");
    ModelAndView mav=new ModelAndView();
    mav.setViewName("/user/message/message_home");
    
    HttpSession session=request.getSession(false);
    
    int memberno=(Integer)session.getAttribute("memberno");
    
    MemsearchVO memsearchVO=memsearchProc.search(memberno);
    
    mav.addObject("userid", memsearchVO.getMemid()); 
    
    return mav;
  }
  
  
  /** 
   * ���� ������ ����Ʈ ����
   * @param request
   * @param search_condition
   * @param msgword
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_recv_list.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String msg_list(HttpServletRequest request, String search_condition, String msgword, int nowpage){
    System.out.println("\n\n======================msg_list() ȣ��=============================");
    HttpSession session=request.getSession(false);
    
    if(search_condition=="" || search_condition==null){
      search_condition="none";
      msgword="none"; 
    }
    
    // JSP���� �Ѿ�� memberno ������ ��� -> ���� ������� ȸ�� ��ȣ
    int memberno=(Integer)session.getAttribute("memberno");
    
    // ȸ����ȣ�� �ش��ϴ� ���� ���� ����Ʈ + ����¡ �߰�
    List<MessageVO> msg_recv_list=messageProc.msg_list(memberno,search_condition, msgword, nowpage);
    
    //===========================================================================
    // ���� ���� �˻� ����
    int recv_search_count=messageProc.recv_search_count(memberno, search_condition, msgword);
    
    // ���� ���� ��� ���� memberno_recv --> �����ڰ� '��'�� �͵�
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    // ���� ���� ��� ���� memberno_send --> �߽��ڰ� '��'�� �͵�
    int msg_send_all_count=messageProc.msg_send_all_count(memberno); 
     
    // ������ ���� ��� ����
    int msg_repo_all_count=messageProc.msg_repo_all_count(memberno);
    
    // ���� �� ���� ������ ��� ����
    int msg_self_all_count=messageProc.msg_self_all_count(memberno);
    //===========================================================================
    
    //=========����¡========= 
    
    String paging=messageProc.paging(recv_search_count, nowpage, 0);
    
    //========================
    
    HashMap<String, Object> list_info=new HashMap<String, Object>();             // ������ HashMap�� ���� Key, value���� �����Ѵ�.
    list_info.put("list_info", msg_recv_list);
    
    HashMap<String, Object> count_info=new HashMap<String, Object>();
    count_info.put("recv_search_count", recv_search_count);
    count_info.put("recv_all_count", msg_recv_all_count);
    count_info.put("send_all_count", msg_send_all_count);
    count_info.put("repo_all_count", msg_repo_all_count);
    count_info.put("self_all_count", msg_self_all_count);
    
    HashMap<String, Object> paging_info=new HashMap<String, Object>();
    paging_info.put("paging", paging);
    
    List<HashMap<String, Object>> result=new ArrayList<HashMap<String,Object>>();   // �̷��� ������ �� HashMap�� �ϳ��� ArrayList�� ��ģ��.
    result.add(list_info);   // ��ȸ�� ����Ʈ ���� 
    result.add(count_info);  // ��� �׸��� ���� ����
    result.add(paging_info); // ����¡ ����
    
    JSONArray list=JSONArray.fromObject(result);                                     // ������ ArrayList�� JSONArray ���·� ��ȯ�Ѵ�.
    
    return list.toString();
  }
  
  @ResponseBody 
  @RequestMapping(value="/user/message/update_recv_msg_count.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
  public String update_recv_msg_count(HttpServletRequest request){
    HttpSession session=request.getSession(false);
    int memberno=(Integer)session.getAttribute("memberno"); 
    // ���� ���� ��� ���� memberno_recv --> �����ڰ� '��'�� �͵�
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    JSONObject obj=new JSONObject();
    obj.put("recv_cnt", msg_recv_all_count);
    
    return obj.toJSONString();
  }
  
  /**
   * ���� ������ ����Ʈ ����
   * @param request
   * @param search_condition
   * @param msgword
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_send_list.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String msg_send_list(HttpServletRequest request,String search_condition, String msgword, int nowpage){
    System.out.println("\n\n======================msg_send_list() ȣ��=============================");
    HttpSession session=request.getSession(false);
    
    if(search_condition=="" || search_condition==null){
      search_condition="none";
      msgword="none"; 
    }
     
    // JSP���� �Ѿ�� memberno ������ ��� -> ���� ������� ȸ�� ��ȣ
    int memberno=(Integer)session.getAttribute("memberno");
    
    // ȸ����ȣ�� �ش��ϴ� ���� ���� ����Ʈ 
    List<MessageVO> msg_recv_list=messageProc.msg_send_list(memberno, search_condition, msgword, nowpage);
     
    // ���� ���� �˻� ���� 
    int send_search_count=messageProc.send_search_count(memberno, search_condition, msgword);
    
    //===========================================================================
    // ���� ���� ��� ���� memberno_recv --> �����ڰ� '��'�� �͵�
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    // ���� ���� ��� ���� memberno_send --> �߽��ڰ� '��'�� �͵�
    int msg_send_all_count=messageProc.msg_send_all_count(memberno);
    
    // ������ ���� ��� ����
    int msg_repo_all_count=messageProc.msg_repo_all_count(memberno); 
    
    // ���� �� ���� ������ ��� ����
    int msg_self_all_count=messageProc.msg_self_all_count(memberno);
    //===========================================================================
    
    //=========����¡=========
    String paging=messageProc.paging(send_search_count, nowpage, 1);
    //========================
    
    HashMap<String, Object> list_info=new HashMap<String, Object>();             // ������ HashMap�� ���� Key, value���� �����Ѵ�.
    list_info.put("list_info", msg_recv_list);
    
    HashMap<String, Object> count_info=new HashMap<String, Object>();
    count_info.put("send_search_count", send_search_count);
    count_info.put("recv_all_count", msg_recv_all_count);
    count_info.put("send_all_count", msg_send_all_count);
    count_info.put("repo_all_count", msg_repo_all_count);
    count_info.put("self_all_count", msg_self_all_count);
    
    HashMap<String, Object> paging_info=new HashMap<String, Object>();
    paging_info.put("paging", paging);
     
    List<HashMap<String, Object>> test=new ArrayList<HashMap<String,Object>>();   // �̷��� ������ �� HashMap�� �ϳ��� ArrayList�� ��ģ��.
    test.add(list_info);
    test.add(count_info);
    test.add(paging_info);
    
    JSONArray list=JSONArray.fromObject(test);  
     
    return list.toString();
   }
  
  /**
   * ���� ������ ���
   * @param request
   * @param search_condition
   * @param msgword
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_repo_list.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String msg_repo_list(HttpServletRequest request,String search_condition, String msgword, int nowpage){
    System.out.println("\n\n======================msg_repo_list() ȣ��=============================");
    HttpSession session=request.getSession(false);
    
    if(search_condition=="" || search_condition==null){
      search_condition="none";
      msgword="none"; 
    }
     
    // JSP���� �Ѿ�� memberno ������ ��� -> ���� ������� ȸ�� ��ȣ
    int memberno=(Integer)session.getAttribute("memberno");
    
    // ȸ����ȣ�� �ش��ϴ� ���� ������ ����Ʈ
    List<MessageVO> msg_recv_list=messageProc.msg_repo_list(memberno, search_condition, msgword, nowpage);
    // ���� ������ �˻� ����
    int repo_search_count=messageProc.repo_search_count(memberno, search_condition, msgword);
    
    //===========================================================================
    // ���� ���� ��� ���� memberno_recv --> �����ڰ� '��'�� �͵�
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    // ���� ���� ��� ���� memberno_send --> �߽��ڰ� '��'�� �͵�
    int msg_send_all_count=messageProc.msg_send_all_count(memberno);
    
    // ������ ���� ��� ����
    int msg_repo_all_count=messageProc.msg_repo_all_count(memberno);
    
    // ���� �� ���� ������ ��� ����
    int msg_self_all_count=messageProc.msg_self_all_count(memberno);
    
    //===========================================================================
    
    //=========����¡=========
    String paging=messageProc.paging(repo_search_count, nowpage, 2);
    //========================
    
    HashMap<String, Object> list_info=new HashMap<String, Object>();             // ������ HashMap�� ���� Key, value���� �����Ѵ�.
    list_info.put("list_info", msg_recv_list);
    
    HashMap<String, Object> count_info=new HashMap<String, Object>();
    count_info.put("repo_search_count", repo_search_count);
    count_info.put("recv_all_count", msg_recv_all_count);
    count_info.put("send_all_count", msg_send_all_count);
    count_info.put("repo_all_count", msg_repo_all_count);
    count_info.put("self_all_count", msg_self_all_count);
    
    HashMap<String, Object> paging_info=new HashMap<String, Object>();
    paging_info.put("paging", paging);
     
    List<HashMap<String, Object>> test=new ArrayList<HashMap<String,Object>>();   // �̷��� ������ �� HashMap�� �ϳ��� ArrayList�� ��ģ��.
    test.add(list_info);
    test.add(count_info);
    test.add(paging_info);
    
    JSONArray list=JSONArray.fromObject(test); 
     
    return list.toString();
   }
  
  /** 
   * ���� �� ������ ����Ʈ ����
   * @param request
   * @param search_condition
   * @param msgword
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_self_list.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String msg_self_list(HttpServletRequest request, String search_condition, String msgword, int nowpage){
    System.out.println("\n\n======================msg_self_list() ȣ��=============================");
    HttpSession session=request.getSession(false);
    
    if(search_condition=="" || search_condition==null){
      search_condition="none";
      msgword="none"; 
    }
    
    // JSP���� �Ѿ�� memberno ������ ��� -> ���� ������� ȸ�� ��ȣ
    int memberno=(Integer)session.getAttribute("memberno");
    
    // ȸ����ȣ�� �ش��ϴ� ���� ���� ����Ʈ + ����¡ �߰�
    List<MessageVO> msg_self_list=messageProc.msg_self_list(memberno, search_condition, msgword, nowpage);
    
    //===========================================================================
    // ���� ���� �˻� ����
    int self_search_count=messageProc.self_search_count(memberno, search_condition, msgword);
    
    // ���� ���� ��� ���� memberno_recv --> �����ڰ� '��'�� �͵�
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    // ���� ���� ��� ���� memberno_send --> �߽��ڰ� '��'�� �͵�
    int msg_send_all_count=messageProc.msg_send_all_count(memberno); 
     
    // ������ ���� ��� ����
    int msg_repo_all_count=messageProc.msg_repo_all_count(memberno);
    
    // ���� �� ���� ������ ��� ����
    int msg_self_all_count=messageProc.msg_self_all_count(memberno);
    
    //===========================================================================
    
    //=========����¡========= 
    
    String paging=messageProc.paging(self_search_count, nowpage, 3);
    
    //========================
    
    HashMap<String, Object> list_info=new HashMap<String, Object>();             // ������ HashMap�� ���� Key, value���� �����Ѵ�.
    list_info.put("list_info", msg_self_list);
    
    HashMap<String, Object> count_info=new HashMap<String, Object>();
    count_info.put("self_search_count", self_search_count); 
    count_info.put("recv_all_count", msg_recv_all_count);
    count_info.put("send_all_count", msg_send_all_count);
    count_info.put("repo_all_count", msg_repo_all_count);
    count_info.put("self_all_count", msg_self_all_count);
    
    HashMap<String, Object> paging_info=new HashMap<String, Object>();
    paging_info.put("paging", paging);
    
    List<HashMap<String, Object>> test=new ArrayList<HashMap<String,Object>>();   // �̷��� ������ �� HashMap�� �ϳ��� ArrayList�� ��ģ��.
    test.add(list_info);
    test.add(count_info);
    test.add(paging_info); 
    
    JSONArray list=JSONArray.fromObject(test);                                     // ������ ArrayList�� JSONArray ���·� ��ȯ�Ѵ�.
    
    return list.toString();
  }
  
  /**
   * ���� ���� ��Ͽ��� Ư�� ���� ����.
   * @param param_data
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_recv_remove.do", method=RequestMethod.POST, produces="text/plain;")
  public String msg_recv_remove(@RequestBody String param_data, HttpServletRequest request){
    System.out.println("\n\n======================msg_recv_remove() ȣ��=============================");
    System.out.println("====================���� ���� ��Ͽ��� Ư�� ���� ����.=======================");
    JSONObject obj=new JSONObject();
    
    HttpSession session=request.getSession(false);
    int memberno=(Integer)session.getAttribute("memberno");
    
    int result=0;
    String[] data_arr=param_data.split("&");
    for(int i=0; i<data_arr.length; i++){
      
      data_arr[i]=data_arr[i].substring(8);
      System.out.println(data_arr[i]);
       
      int count=messageProc.recv_del(Integer.parseInt(data_arr[i]));
      if(count==1){
        result+=1;
      }
    }
    int after_count=messageProc.msg_recv_all_count(memberno); // ���� ó���� �� ���� ���� 
    
    if(result==data_arr.length){
      obj.put("result", "ok");
      obj.put("after_count", after_count);
    }else{
      obj.put("result", "fail"); 
      obj.put("after_count", after_count);
    }
    return obj.toJSONString();
  }
  
 /**
  * ���� ���� ��Ͽ��� Ư�� ���� ����.  
  * @param param_data
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/message/msg_send_remove.do", method=RequestMethod.POST, produces="text/plain;")
 public String msg_send_remove(@RequestBody String param_data, HttpServletRequest request){
   System.out.println("\n\n======================msg_send_remove() ȣ��=============================");
   System.out.println("=====================���� ���� ��Ͽ��� Ư�� ���� ����=======================");
   HttpSession session=request.getSession(false);
   int memberno=(Integer)session.getAttribute("memberno");
   
   JSONObject obj=new JSONObject();
   int result=0;
   
   System.out.println(" ==> param_data:"+param_data);
   
   // ���޹��� Query String�� '&'�� �������� �����Ѵ�.
   String[] data_arr=param_data.split("&");
   for(int i=0; i<data_arr.length; i++){ 
     
     // msg_no�� �ɷ����� ���� subString ���
     data_arr[i]=data_arr[i].substring(8);
     
     int count=messageProc.send_del(Integer.parseInt(data_arr[i]));
     if(count==1){
       result+=1;
     }
   }
   int after_count=messageProc.msg_send_all_count(memberno); // ���� ó���� �� ���� ���� 
   
   if(result==data_arr.length){ 
     obj.put("result", "ok");
     obj.put("after_count", after_count);
   }else{
     obj.put("result", "fail");
     obj.put("after_count", after_count);
   }
   return obj.toJSONString();
 }
 
 /**
  * ���� �����Կ��� Ư�� ���� ����
  * @param param_data
  * @param request
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/message/msg_repo_remove.do", method=RequestMethod.POST, produces="text/plain;")
 public String msg_repo_remove(@RequestBody String param_data, HttpServletRequest request){
   System.out.println("\n\n======================msg_repo_remove() ȣ��=============================");
   System.out.println("=====================���� �����Կ��� Ư�� ���� ����=======================");
   HttpSession session=request.getSession(false);
   
   int memberno=(Integer)session.getAttribute("memberno"); // ���� ������� ȸ�� ��ȣ
   
   JSONObject obj=new JSONObject();
   int result=0;
   
   // ���޹��� Query String�� '&'�� �������� �����Ѵ�.
   String[] data_arr=param_data.split("&");
   for(int i=0; i<data_arr.length; i++){ 
     
     // msg_no�� �ɷ����� ���� subString ���
     data_arr[i]=data_arr[i].substring(8);
     
     int count=messageProc.repo_del(Integer.parseInt(data_arr[i])); 
     if(count==1){
       result+=1;
     }
   } 
   
   int after_count=messageProc.msg_repo_all_count(memberno); // ���� ó���� �� ���� ���� 
   
   // ��û�� ������ ó���� ������ ��ġ�ϸ� ����.
   if(result==data_arr.length){ 
     obj.put("result", "ok");
     obj.put("after_count", after_count);
   }else{
     obj.put("result", "fail");
     obj.put("after_count", after_count); 
   }
   return obj.toJSONString();
 }
 
 /**
  * ���� �� ������ ����
  * @param param_data
  * @param request
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/message/msg_self_remove.do", method=RequestMethod.POST, produces="text/plain;")
 public String msg_self_remove(@RequestBody String param_data, HttpServletRequest request){
   HttpSession session=request.getSession(false);
   
   int memberno=(Integer)session.getAttribute("memberno"); // ���� ������� ȸ�� ��ȣ
   
   JSONObject obj=new JSONObject();
   int result=0;
   int recv_cnt=0;
   int send_cnt=0;
   // ���޹��� Query String�� '&'�� �������� �����Ѵ�.
   String[] data_arr=param_data.split("&");
   for(int i=0; i<data_arr.length; i++){ 
     
     // msg_no�� �ɷ����� ���� subString ���
     data_arr[i]=data_arr[i].substring(8);
     
     recv_cnt=messageProc.recv_del(Integer.parseInt(data_arr[i]));
     send_cnt=messageProc.send_del(Integer.parseInt(data_arr[i]));
     
     if(recv_cnt==1 && send_cnt==1){
       result+=1;
     }
   }
   
   System.out.println(" ==> ���� ���� ������ ����");
   System.out.println(data_arr.length);
   System.out.println(result);
   
   if(data_arr.length==result){
     obj.put("result", "ok");
   }else{
     obj.put("result", "fail");
   }
   
   return obj.toJSONString();
 }
 
 /**
  * ���� ������ ����Ʈ ���
  * @param request
  * @param param_data
  * @param index
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/message/move_repo.do", method=RequestMethod.POST, produces="text/plain;")
 public String move_repo(HttpServletRequest request, @RequestBody String param_data, int index){
   System.out.println("\n\n======================move_repo() ȣ��=============================");
   System.out.println("=====================���� �� �������� ���� ���������� �̵�==============");
   System.out.println(" ==> index : "+index);
   HttpSession session=request.getSession(false);  
   JSONObject obj=new JSONObject();  
   int result=0;
   
   // JSP���� �Ѿ�� memberno ������ ��� -> ���� ������� ȸ�� ��ȣ
   int memberno=(Integer)session.getAttribute("memberno");
   
   // ���޹��� Query String�� '&'�� �������� �����Ѵ�.
   String[] data_arr=param_data.split("&");
   for(int i=0; i<data_arr.length-1; i++){
     
     // msg_no�� �ɷ����� ���� subString ���
     data_arr[i]=data_arr[i].substring(8);
     
     int count=messageProc.move_repo(memberno, Integer.parseInt(data_arr[i]));  // Ư�� msg_no�� ���� ������ ���̺�� insert�Ѵ�.
     if(count==1){ 
       if(index==0){  // ���� �����Կ��� ���������� �Ѱ��̰�, �ش� ������ �����Ѵ�.
         messageProc.recv_del(Integer.parseInt(data_arr[i]));
       }else{         // ���� �����Կ��� ���������� �� ���̰�, �ش� ������ �����Ѵ�.
         messageProc.send_del(Integer.parseInt(data_arr[i])); 
       }
       result+=1;
     }
   }
   
   System.out.println(" ==> result:"+result);
   System.out.println(" ==> data_arr.length:"+data_arr.length);
   
   if(result==data_arr.length-1){
     obj.put("result", "ok");
   }else{
     obj.put("result", "fail");   
   }
   return obj.toJSONString();
 }
  
  /**
   * ���� ����
   * @return
   */ 
  @ResponseBody
  @RequestMapping(value="/user/message/msg_create.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
  public String msg_create(HttpServletRequest request, String memid, String msg_title, String msg_content){
    System.out.println("\n\n======================msg_create() ȣ��=============================");
    System.out.println("===============================���� ����================================");
    int msg_count=0; // ���� DB ó�� ��� ����
    String result=""; // ���� ���� ��� ���� 
    
    HttpSession session=request.getSession(false); 
    
    // JSP���� �Ѿ�� memberno ������ ��� -> ���� ������� ȸ�� ��ȣ
    int memberno_send=(Integer)session.getAttribute("memberno");
    
    JSONObject msg_obj=new JSONObject();
    
    // ������ memid�� ��ȿ���� �˻�.
    int member_exist=memsearchProc.exist_memid(memid);
    
    int memberno_recv=memsearchProc.search_memberno(memid); // �޴��� ��ȣ
    
    if(member_exist!=0){ 
      // ���޹��� parameter�� ������ DB�� ����.
      msg_count=messageProc.msg_create(memberno_send, memberno_recv, msg_title, msg_content);  
      
      int msg_no=messageProc.serach_last_msg_no();  // ��� ����� ���� ��ȣ 
      
      // ������ ���������� �Ǿ�����
      if(msg_count==1){ // ���� ���� ����
        messageProc.msgsend_insert(memberno_send, msg_no);
        messageProc.msgrecv_insert(memberno_recv, msg_no);
        if(memberno_send==memberno_recv){
          result="Self OK";
        }else{
          result="OK";
        }
      }else{            // ���� ���� ����
        result="FAIL";
      }
    
    }
    msg_obj.put("result", result);
    
    return msg_obj.toJSONString();
  }
  
  /** 
   * ȸ�� ���̵� ��ȿ���� �˻� ���
   */
  @ResponseBody
  @RequestMapping(value="/user/message/check_memid.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
  public String check_memid(String memid){
    System.out.println("\n\n======================check_memid() ȣ��=============================");
    System.out.println("=========================ȸ�� ���̵� ��ȿ �˻�==============================");
    JSONObject obj=new JSONObject();
    
    int memberno_recv=memsearchProc.exist_memid(memid);
    
    String result=""; 
    
    if(memberno_recv==1){
      result="success";
    }else{
      result="failed";
    }
    
    obj.put("result", result);
    return obj.toJSONString();
  }
  
  /**
   * ���� �б� (����, ���� ������ ����)
   * @param msg_no
   * @return 
   */ 
  @ResponseBody
  @RequestMapping(value="/user/message/read.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
  public String read(int msg_no, int index){
    System.out.println("\n\n======================read() ȣ��=============================");
    System.out.println("===========================���� �б�==============================");
    MessageVO read=messageProc.read_msg(msg_no);
    
    net.sf.json.JSONObject obj=net.sf.json.JSONObject.fromObject(read);
    System.out.println(" ==> index :"+index);
    System.out.println(" ==> confirm :"+read.getMsg_confirm());
     
    System.out.println(" ==> msg_contetnt : "+read.getMsg_content());
    
    // index==0�� ������������ read�� �ǹ�
    if(index==0 && read.getMsg_confirm().equals("N")){
      System.out.println(" ==> ��Ȯ�� ���� ���� : ���ſ��� ������Ʈ ����");
      messageProc.confirm(msg_no);   // ���ſ���, ���� �ð� ������Ʈ
    }
    
    return obj.toString();
  }
}
