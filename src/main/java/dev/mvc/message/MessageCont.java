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
   * 쪽지 기본 페이지
   * @param request 
   * @return 
   */ 
  @RequestMapping(value="/user/message/message_home.do", method=RequestMethod.GET)
  public ModelAndView message_home(HttpServletRequest request){
    System.out.println("======================message_home() 호출=============================");
    ModelAndView mav=new ModelAndView();
    mav.setViewName("/user/message/message_home");
    
    HttpSession session=request.getSession(false);
    
    int memberno=(Integer)session.getAttribute("memberno");
    
    MemsearchVO memsearchVO=memsearchProc.search(memberno);
    
    mav.addObject("userid", memsearchVO.getMemid()); 
    
    return mav;
  }
  
  
  
  /** 
   * 받은 쪽지함 리스트 정보
   * @param request
   * @param search_condition
   * @param msgword
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_recv_list.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String msg_list(HttpServletRequest request, String search_condition, String msgword, int nowpage){
    System.out.println("\n\n======================msg_list() 호출=============================");
    HttpSession session=request.getSession(false);
    
    if(search_condition=="" || search_condition==null){
      search_condition="none";
      msgword="none"; 
    }
    
    // JSP에서 넘어온 memberno 세션을 사용 -> 현재 사용자의 회원 번호
    int memberno=(Integer)session.getAttribute("memberno");
    
    // 회원번호에 해당하는 받은 쪽지 리스트 + 페이징 추가
    List<MessageVO> msg_recv_list=messageProc.msg_list(memberno,search_condition, msgword, nowpage);
    
    //===========================================================================
    // 받은 쪽지 검색 갯수
    int recv_search_count=messageProc.recv_search_count(memberno, search_condition, msgword);
    
    // 받은 쪽지 모든 갯수 memberno_recv --> 수신자가 '나'인 것들
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    // 보낸 쪽지 모든 갯수 memberno_send --> 발신자가 '나'인 것들
    int msg_send_all_count=messageProc.msg_send_all_count(memberno); 
     
    // 보관한 쪽지 모든 갯수
    int msg_repo_all_count=messageProc.msg_repo_all_count(memberno);
    
    // 내게 쓴 쪽지 보관함 모든 갯수
    int msg_self_all_count=messageProc.msg_self_all_count(memberno);
    //===========================================================================
    
    //=========페이징========= 
    
    String paging=messageProc.paging(recv_search_count, nowpage, 0);
    
    //========================
    
    HashMap<String, Object> list_info=new HashMap<String, Object>();             // 각각의 HashMap을 통해 Key, value값을 정의한다.
    list_info.put("list_info", msg_recv_list);
    
    HashMap<String, Object> count_info=new HashMap<String, Object>();
    count_info.put("recv_search_count", recv_search_count);
    count_info.put("recv_all_count", msg_recv_all_count);
    count_info.put("send_all_count", msg_send_all_count);
    count_info.put("repo_all_count", msg_repo_all_count);
    count_info.put("self_all_count", msg_self_all_count);
    
    HashMap<String, Object> paging_info=new HashMap<String, Object>();
    paging_info.put("paging", paging);
    
    List<HashMap<String, Object>> result=new ArrayList<HashMap<String,Object>>();   // 이렇게 정의한 두 HashMap을 하나의 ArrayList로 합친다.
    result.add(list_info);   // 조회된 리스트 정보 
    result.add(count_info);  // 모든 항목의 갯수 정보
    result.add(paging_info); // 페이징 정보
    
    JSONArray list=JSONArray.fromObject(result);                                     // 합쳐진 ArrayList를 JSONArray 형태로 변환한다.
    
    return list.toString();
  }
  
  @ResponseBody 
  @RequestMapping(value="/user/message/update_recv_msg_count.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
  public String update_recv_msg_count(HttpServletRequest request){
    HttpSession session=request.getSession(false);
    int memberno=(Integer)session.getAttribute("memberno"); 
    // 받은 쪽지 모든 갯수 memberno_recv --> 수신자가 '나'인 것들
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    JSONObject obj=new JSONObject();
    obj.put("recv_cnt", msg_recv_all_count);
    
    return obj.toJSONString();
  }
  
  /**
   * 보낸 쪽지함 리스트 정보
   * @param request
   * @param search_condition
   * @param msgword
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_send_list.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String msg_send_list(HttpServletRequest request,String search_condition, String msgword, int nowpage){
    System.out.println("\n\n======================msg_send_list() 호출=============================");
    HttpSession session=request.getSession(false);
    
    if(search_condition=="" || search_condition==null){
      search_condition="none";
      msgword="none"; 
    }
     
    // JSP에서 넘어온 memberno 세션을 사용 -> 현재 사용자의 회원 번호
    int memberno=(Integer)session.getAttribute("memberno");
    
    // 회원번호에 해당하는 보낸 쪽지 리스트 
    List<MessageVO> msg_recv_list=messageProc.msg_send_list(memberno, search_condition, msgword, nowpage);
     
    // 보낸 쪽지 검색 갯수 
    int send_search_count=messageProc.send_search_count(memberno, search_condition, msgword);
    
    //===========================================================================
    // 받은 쪽지 모든 갯수 memberno_recv --> 수신자가 '나'인 것들
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    // 보낸 쪽지 모든 갯수 memberno_send --> 발신자가 '나'인 것들
    int msg_send_all_count=messageProc.msg_send_all_count(memberno);
    
    // 보관한 쪽지 모든 갯수
    int msg_repo_all_count=messageProc.msg_repo_all_count(memberno); 
    
    // 내게 쓴 쪽지 보관함 모든 갯수
    int msg_self_all_count=messageProc.msg_self_all_count(memberno);
    //===========================================================================
    
    //=========페이징=========
    String paging=messageProc.paging(send_search_count, nowpage, 1);
    //========================
    
    HashMap<String, Object> list_info=new HashMap<String, Object>();             // 각각의 HashMap을 통해 Key, value값을 정의한다.
    list_info.put("list_info", msg_recv_list);
    
    HashMap<String, Object> count_info=new HashMap<String, Object>();
    count_info.put("send_search_count", send_search_count);
    count_info.put("recv_all_count", msg_recv_all_count);
    count_info.put("send_all_count", msg_send_all_count);
    count_info.put("repo_all_count", msg_repo_all_count);
    count_info.put("self_all_count", msg_self_all_count);
    
    HashMap<String, Object> paging_info=new HashMap<String, Object>();
    paging_info.put("paging", paging);
     
    List<HashMap<String, Object>> test=new ArrayList<HashMap<String,Object>>();   // 이렇게 정의한 두 HashMap을 하나의 ArrayList로 합친다.
    test.add(list_info);
    test.add(count_info);
    test.add(paging_info);
    
    JSONArray list=JSONArray.fromObject(test);  
     
    return list.toString();
   }
  
  /**
   * 쪽지 보관함 목록
   * @param request
   * @param search_condition
   * @param msgword
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_repo_list.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String msg_repo_list(HttpServletRequest request,String search_condition, String msgword, int nowpage){
    System.out.println("\n\n======================msg_repo_list() 호출=============================");
    HttpSession session=request.getSession(false);
    
    if(search_condition=="" || search_condition==null){
      search_condition="none";
      msgword="none"; 
    }
     
    // JSP에서 넘어온 memberno 세션을 사용 -> 현재 사용자의 회원 번호
    int memberno=(Integer)session.getAttribute("memberno");
    
    // 회원번호에 해당하는 쪽지 보관함 리스트
    List<MessageVO> msg_recv_list=messageProc.msg_repo_list(memberno, search_condition, msgword, nowpage);
    // 쪽지 보관함 검색 갯수
    int repo_search_count=messageProc.repo_search_count(memberno, search_condition, msgword);
    
    //===========================================================================
    // 받은 쪽지 모든 갯수 memberno_recv --> 수신자가 '나'인 것들
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    // 보낸 쪽지 모든 갯수 memberno_send --> 발신자가 '나'인 것들
    int msg_send_all_count=messageProc.msg_send_all_count(memberno);
    
    // 보관한 쪽지 모든 갯수
    int msg_repo_all_count=messageProc.msg_repo_all_count(memberno);
    
    // 내게 쓴 쪽지 보관함 모든 갯수
    int msg_self_all_count=messageProc.msg_self_all_count(memberno);
    
    //===========================================================================
    
    //=========페이징=========
    String paging=messageProc.paging(repo_search_count, nowpage, 2);
    //========================
    
    HashMap<String, Object> list_info=new HashMap<String, Object>();             // 각각의 HashMap을 통해 Key, value값을 정의한다.
    list_info.put("list_info", msg_recv_list);
    
    HashMap<String, Object> count_info=new HashMap<String, Object>();
    count_info.put("repo_search_count", repo_search_count);
    count_info.put("recv_all_count", msg_recv_all_count);
    count_info.put("send_all_count", msg_send_all_count);
    count_info.put("repo_all_count", msg_repo_all_count);
    count_info.put("self_all_count", msg_self_all_count);
    
    HashMap<String, Object> paging_info=new HashMap<String, Object>();
    paging_info.put("paging", paging);
     
    List<HashMap<String, Object>> test=new ArrayList<HashMap<String,Object>>();   // 이렇게 정의한 두 HashMap을 하나의 ArrayList로 합친다.
    test.add(list_info);
    test.add(count_info);
    test.add(paging_info);
    
    JSONArray list=JSONArray.fromObject(test); 
     
    return list.toString();
   }
  
  /** 
   * 내게 쓴 쪽지함 리스트 정보
   * @param request
   * @param search_condition
   * @param msgword
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_self_list.do", method=RequestMethod.GET, produces="text/plain; charset=utf-8")
  public String msg_self_list(HttpServletRequest request, String search_condition, String msgword, int nowpage){
    System.out.println("\n\n======================msg_self_list() 호출=============================");
    HttpSession session=request.getSession(false);
    
    if(search_condition=="" || search_condition==null){
      search_condition="none";
      msgword="none"; 
    }
    
    // JSP에서 넘어온 memberno 세션을 사용 -> 현재 사용자의 회원 번호
    int memberno=(Integer)session.getAttribute("memberno");
    
    // 회원번호에 해당하는 받은 쪽지 리스트 + 페이징 추가
    List<MessageVO> msg_self_list=messageProc.msg_self_list(memberno, search_condition, msgword, nowpage);
    
    //===========================================================================
    // 받은 쪽지 검색 갯수
    int self_search_count=messageProc.self_search_count(memberno, search_condition, msgword);
    
    // 받은 쪽지 모든 갯수 memberno_recv --> 수신자가 '나'인 것들
    int msg_recv_all_count=messageProc.msg_recv_all_count(memberno);
    
    // 보낸 쪽지 모든 갯수 memberno_send --> 발신자가 '나'인 것들
    int msg_send_all_count=messageProc.msg_send_all_count(memberno); 
     
    // 보관한 쪽지 모든 갯수
    int msg_repo_all_count=messageProc.msg_repo_all_count(memberno);
    
    // 내게 쓴 쪽지 보관함 모든 갯수
    int msg_self_all_count=messageProc.msg_self_all_count(memberno);
    
    //===========================================================================
    
    //=========페이징========= 
    
    String paging=messageProc.paging(self_search_count, nowpage, 3);
    
    //========================
    
    HashMap<String, Object> list_info=new HashMap<String, Object>();             // 각각의 HashMap을 통해 Key, value값을 정의한다.
    list_info.put("list_info", msg_self_list);
    
    HashMap<String, Object> count_info=new HashMap<String, Object>();
    count_info.put("self_search_count", self_search_count); 
    count_info.put("recv_all_count", msg_recv_all_count);
    count_info.put("send_all_count", msg_send_all_count);
    count_info.put("repo_all_count", msg_repo_all_count);
    count_info.put("self_all_count", msg_self_all_count);
    
    HashMap<String, Object> paging_info=new HashMap<String, Object>();
    paging_info.put("paging", paging);
    
    List<HashMap<String, Object>> test=new ArrayList<HashMap<String,Object>>();   // 이렇게 정의한 두 HashMap을 하나의 ArrayList로 합친다.
    test.add(list_info);
    test.add(count_info);
    test.add(paging_info); 
    
    JSONArray list=JSONArray.fromObject(test);                                     // 합쳐진 ArrayList를 JSONArray 형태로 변환한다.
    
    return list.toString();
  }
  
  /**
   * 받은 쪽지 목록에서 특정 쪽지 삭제.
   * @param param_data
   * @return
   */
  @ResponseBody 
  @RequestMapping(value="/user/message/msg_recv_remove.do", method=RequestMethod.POST, produces="text/plain;")
  public String msg_recv_remove(@RequestBody String param_data, HttpServletRequest request){
    System.out.println("\n\n======================msg_recv_remove() 호출=============================");
    System.out.println("====================받은 쪽지 목록에서 특정 쪽지 삭제.=======================");
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
    int after_count=messageProc.msg_recv_all_count(memberno); // 삭제 처리가 된 후의 갯수 
    
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
  * 보낸 쪽지 목록에서 특정 쪽지 삭제.  
  * @param param_data
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/message/msg_send_remove.do", method=RequestMethod.POST, produces="text/plain;")
 public String msg_send_remove(@RequestBody String param_data, HttpServletRequest request){
   System.out.println("\n\n======================msg_send_remove() 호출=============================");
   System.out.println("=====================보낸 쪽지 목록에서 특정 쪽지 삭제=======================");
   HttpSession session=request.getSession(false);
   int memberno=(Integer)session.getAttribute("memberno");
   
   JSONObject obj=new JSONObject();
   int result=0;
   
   System.out.println(" ==> param_data:"+param_data);
   
   // 전달받은 Query String을 '&'을 기준으로 분할한다.
   String[] data_arr=param_data.split("&");
   for(int i=0; i<data_arr.length; i++){ 
     
     // msg_no만 걸러내기 위해 subString 사용
     data_arr[i]=data_arr[i].substring(8);
     
     int count=messageProc.send_del(Integer.parseInt(data_arr[i]));
     if(count==1){
       result+=1;
     }
   }
   int after_count=messageProc.msg_send_all_count(memberno); // 삭제 처리가 된 후의 갯수 
   
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
  * 쪽지 보관함에서 특정 쪽지 삭제
  * @param param_data
  * @param request
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/message/msg_repo_remove.do", method=RequestMethod.POST, produces="text/plain;")
 public String msg_repo_remove(@RequestBody String param_data, HttpServletRequest request){
   System.out.println("\n\n======================msg_repo_remove() 호출=============================");
   System.out.println("=====================쪽지 보관함에서 특정 쪽지 삭제=======================");
   HttpSession session=request.getSession(false);
   
   int memberno=(Integer)session.getAttribute("memberno"); // 현재 사용자의 회원 번호
   
   JSONObject obj=new JSONObject();
   int result=0;
   
   // 전달받은 Query String을 '&'을 기준으로 분할한다.
   String[] data_arr=param_data.split("&");
   for(int i=0; i<data_arr.length; i++){ 
     
     // msg_no만 걸러내기 위해 subString 사용
     data_arr[i]=data_arr[i].substring(8);
     
     int count=messageProc.repo_del(Integer.parseInt(data_arr[i])); 
     if(count==1){
       result+=1;
     }
   } 
   
   int after_count=messageProc.msg_repo_all_count(memberno); // 삭제 처리가 된 후의 갯수 
   
   // 요청된 갯수와 처리된 갯수가 일치하면 성공.
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
  * 내게 쓴 쪽지함 삭제
  * @param param_data
  * @param request
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/message/msg_self_remove.do", method=RequestMethod.POST, produces="text/plain;")
 public String msg_self_remove(@RequestBody String param_data, HttpServletRequest request){
   HttpSession session=request.getSession(false);
   
   int memberno=(Integer)session.getAttribute("memberno"); // 현재 사용자의 회원 번호
   
   JSONObject obj=new JSONObject();
   int result=0;
   int recv_cnt=0;
   int send_cnt=0;
   // 전달받은 Query String을 '&'을 기준으로 분할한다.
   String[] data_arr=param_data.split("&");
   for(int i=0; i<data_arr.length; i++){ 
     
     // msg_no만 걸러내기 위해 subString 사용
     data_arr[i]=data_arr[i].substring(8);
     
     recv_cnt=messageProc.recv_del(Integer.parseInt(data_arr[i]));
     send_cnt=messageProc.send_del(Integer.parseInt(data_arr[i]));
     
     if(recv_cnt==1 && send_cnt==1){
       result+=1;
     }
   }
   
   System.out.println(" ==> 내게 보낸 쪽지함 삭제");
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
  * 쪽지 보관함 리스트 출력
  * @param request
  * @param param_data
  * @param index
  * @return
  */
 @ResponseBody 
 @RequestMapping(value="/user/message/move_repo.do", method=RequestMethod.POST, produces="text/plain;")
 public String move_repo(HttpServletRequest request, @RequestBody String param_data, int index){
   System.out.println("\n\n======================move_repo() 호출=============================");
   System.out.println("=====================선택 된 쪽지들을 쪽지 보관함으로 이동==============");
   System.out.println(" ==> index : "+index);
   HttpSession session=request.getSession(false);  
   JSONObject obj=new JSONObject();  
   int result=0;
   
   // JSP에서 넘어온 memberno 세션을 사용 -> 현재 사용자의 회원 번호
   int memberno=(Integer)session.getAttribute("memberno");
   
   // 전달받은 Query String을 '&'을 기준으로 분할한다.
   String[] data_arr=param_data.split("&");
   for(int i=0; i<data_arr.length-1; i++){
     
     // msg_no만 걸러내기 위해 subString 사용
     data_arr[i]=data_arr[i].substring(8);
     
     int count=messageProc.move_repo(memberno, Integer.parseInt(data_arr[i]));  // 특정 msg_no를 쪽지 보관함 테이블로 insert한다.
     if(count==1){ 
       if(index==0){  // 받은 쪽지함에서 보관동작을 한것이고, 해당 쪽지를 삭제한다.
         messageProc.recv_del(Integer.parseInt(data_arr[i]));
       }else{         // 보낸 쪽지함에서 보관동작을 한 것이고, 해당 쪽지를 삭제한다.
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
   * 쪽지 전송
   * @return
   */ 
  @ResponseBody
  @RequestMapping(value="/user/message/msg_create.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
  public String msg_create(HttpServletRequest request, String memid, String msg_title, String msg_content){
    System.out.println("\n\n======================msg_create() 호출=============================");
    System.out.println("===============================쪽지 전송================================");
    int msg_count=0; // 쪽지 DB 처리 결과 변수
    String result=""; // 쪽지 전송 결과 변수 
    
    HttpSession session=request.getSession(false); 
    
    // JSP에서 넘어온 memberno 세션을 사용 -> 현재 사용자의 회원 번호
    int memberno_send=(Integer)session.getAttribute("memberno");
    
    JSONObject msg_obj=new JSONObject();
    
    // 전달한 memid가 유효한지 검사.
    int member_exist=memsearchProc.exist_memid(memid);
    
    int memberno_recv=memsearchProc.search_memberno(memid); // 받는이 번호
    
    if(member_exist!=0){ 
      // 전달받은 parameter로 쪽지를 DB에 저장.
      msg_count=messageProc.msg_create(memberno_send, memberno_recv, msg_title, msg_content);  
      
      int msg_no=messageProc.serach_last_msg_no();  // 방금 저장된 쪽지 번호 
      
      // 저장이 성공적으로 되었으면
      if(msg_count==1){ // 쪽지 전송 성공
        messageProc.msgsend_insert(memberno_send, msg_no);
        messageProc.msgrecv_insert(memberno_recv, msg_no);
        if(memberno_send==memberno_recv){
          result="Self OK";
        }else{
          result="OK";
        }
      }else{            // 쪽지 전송 실패
        result="FAIL";
      }
    
    }
    msg_obj.put("result", result);
    
    return msg_obj.toJSONString();
  }
  
  /** 
   * 회원 아이디가 유효한지 검사 결과
   */
  @ResponseBody
  @RequestMapping(value="/user/message/check_memid.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
  public String check_memid(String memid){
    System.out.println("\n\n======================check_memid() 호출=============================");
    System.out.println("=========================회원 아이디 유효 검사==============================");
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
   * 쪽지 읽기 (받은, 보낸 쪽지함 공통)
   * @param msg_no
   * @return 
   */ 
  @ResponseBody
  @RequestMapping(value="/user/message/read.do", method=RequestMethod.POST, produces="text/plain; charset=utf-8")
  public String read(int msg_no, int index){
    System.out.println("\n\n======================read() 호출=============================");
    System.out.println("===========================쪽지 읽기==============================");
    MessageVO read=messageProc.read_msg(msg_no);
    
    net.sf.json.JSONObject obj=net.sf.json.JSONObject.fromObject(read);
    System.out.println(" ==> index :"+index);
    System.out.println(" ==> confirm :"+read.getMsg_confirm());
     
    System.out.println(" ==> msg_contetnt : "+read.getMsg_content());
    
    // index==0은 보낸쪽지함의 read를 의미
    if(index==0 && read.getMsg_confirm().equals("N")){
      System.out.println(" ==> 미확인 쪽지 열람 : 수신여부 업데이트 동작");
      messageProc.confirm(msg_no);   // 수신여부, 수신 시간 업데이트
    }
    
    return obj.toString();
  }
}
