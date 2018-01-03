package dev.mvc.message;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.memsearch.MemsearchProcInter;

@Controller
public class MessageCont_admin {
  @Autowired
  @Qualifier("dev.mvc.message.MessageProc")
  private MessageProcInter messageProc;
  
  @Autowired
  @Qualifier("dev.mvc.memsearch.MemsearchProc")
  private MemsearchProcInter memsearchProc;
  
  /**
   * ���� ������ �⺻ ������
   * @param request
   * @return 
   */  
  @RequestMapping(value="admin/message/message_admin.do", method=RequestMethod.GET)
  public ModelAndView message_admin(HttpServletRequest request,
      @RequestParam(value="page_num", defaultValue="10") String page_num,
      @RequestParam(value="first_day", defaultValue="") String first_day,
      @RequestParam(value="second_day", defaultValue="") String second_day,
      @RequestParam(value="search_condition", defaultValue="") String search_condition,
      @RequestParam(value="msgword", defaultValue="") String msgword,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      )
  { 
    System.out.println("======================message_admin() ȣ��=============================");
    
    HttpSession session=request.getSession(false);  // false - ������ �ִ� ������ �����ͼ� ����.
    ModelAndView mav=new ModelAndView();
    mav.setViewName("/admin/message/message_admin");
     
    System.out.println(" ==> first_day:"+first_day);
    System.out.println(" ==> second_day:"+second_day);
    System.out.println(" ==> search_condtion:"+search_condition);
    System.out.println(" ==> msgword:"+msgword);
    System.out.println(" ==> nowPage:"+nowPage);
    
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    hashMap.put("first_day", first_day);
    hashMap.put("second_day", second_day);
    hashMap.put("search_condition", search_condition);
    
    if(search_condition.equals("sender_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
      hashMap.put("memberno_recv", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword);
    }else{
      System.out.println(" --> �˻� ���� x");
    }
    
    System.out.println("==> ����¡:"+page_num);
    
    int page_num_int=Integer.parseInt(page_num);
    
    // ===============����¡�� ���� �ڵ� ����==================================
    int beginOfPage = (nowPage - 1) * page_num_int;
     
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + page_num_int; // ���� rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    //==========================================================================
    
    int search_count=messageProc.message_admin_search_cnt(hashMap);
    System.out.println(" ==> search_count:"+search_count);
    List<MessageVO> msg_list=messageProc.msg_list_admin(hashMap);
    
    String paging=messageProc.paging_admin(page_num_int, search_count, nowPage, first_day, second_day, search_condition, msgword);
    
    mav.addObject("msg_list",msg_list);
    mav.addObject("first_day", first_day);
    mav.addObject("second_day", second_day);
    mav.addObject("search_condition", search_condition);
    mav.addObject("msgword", msgword); 
    mav.addObject("paging", paging); 
    mav.addObject("page_num",page_num_int);
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  /**
   * [����������] Ư�� ���� ����.
   * @param Stringp[] msg_no;
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/admin/message/message_del_admin.ajax", method=RequestMethod.POST, produces="text/plain;")
  public String message_del_admin(HttpServletRequest request, String[] msg_no,
      @RequestParam(value="first_day", defaultValue="") String first_day,
      @RequestParam(value="second_day", defaultValue="") String second_day,
      @RequestParam(value="search_condition", defaultValue="") String search_condition,
      @RequestParam(value="msgword", defaultValue="") String msgword
      ){
    JSONObject obj=new JSONObject();
    
    HashMap<String, Object> hashMap=new HashMap<String, Object>();
    hashMap.put("first_day", first_day);
    hashMap.put("second_day", second_day);
    hashMap.put("search_condition", search_condition);
    
    if(search_condition.equals("sender_id")){
      hashMap.put("memberno_send", memsearchProc.search_memberno(msgword));
      hashMap.put("memberno_recv", memsearchProc.search_memberno(msgword));
    }else if(search_condition.equals("msg_title")){
      hashMap.put("msg_title", msgword);
    }else if(search_condition.equals("msg_content")){
      hashMap.put("msg_content", msgword);
    }else{
      System.out.println(" --> �˻� ���� x");
    }
    
    int result=0;  // ���� ��� ����
    
    for(int i=0; i<msg_no.length; i++){
      messageProc.recv_del(Integer.parseInt(msg_no[i]));  // ���� �������� ����
      messageProc.send_del(Integer.parseInt(msg_no[i]));  // �۽� �������� ����
      messageProc.repo_del(Integer.parseInt(msg_no[i]));  // ���� �����Կ��� ���� 
      
      int count=messageProc.message_del_admin(Integer.parseInt(msg_no[i]));
      if(count==1){
        result+=1;
      }
    }
    
    int after_count=messageProc.message_admin_search_cnt(hashMap);
    
    System.out.println("==> msg_no.length() :"+msg_no.length);
    System.out.println("==> result(ó���� ����):"+result);
    System.out.println("==> first_day:"+first_day); 
    
    if(result==msg_no.length){
      obj.put("result", "OK");
    }else{
      obj.put("result", "fail");
    }
    
    obj.put("after_count", after_count);
    
    return obj.toJSONString();
  }
}
