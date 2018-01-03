package dev.mvc.msg_socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class MsgWebSocketHandler implements WebSocketHandler{

  public WebSocketSession session;
  //public List<WebSocketSession> list=new ArrayList<WebSocketSession>();
  public HashMap<String, WebSocketSession> hash_session=new HashMap<String, WebSocketSession>();
  
  public Iterator iterator;
  public int d_cnt;
  
  //�� ���� ������ ���� �� ��  
  @Override
  public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
    
    // HandshakeInterceptor���� attributes.put(); �� ���� ���� ���� ������ �� ���� ����� �� �ִ�.
    Map<String, Object> map=arg0.getAttributes();
    // System.out.println(map); 
    // System.out.println(map.get("memid"));
    
    String memid;
    d_cnt=0;
    
    iterator=hash_session.keySet().iterator(); // �ؽ����� Ž���ϱ� ���� �غ�
    
    // ���ǵ� �߿� ���ο� ���� ���ǰ� ������ IP�� ������ �ִ� ������ �ִ��� �˻��Ѵ�.
    while(iterator.hasNext()){
      String key=(String) iterator.next(); 
      //System.out.println(hash_session.get(key));
      session=hash_session.get(key);
      map=session.getAttributes();
      // ������ �ִ� ���� ������ IP�� HttpSession�� �ִ� memid���� ���Ѵ�.
      // ���ο� ����� ���� ������ ������ �����ϸ� �ߺ� �������� �Ǵ�.
      
      if(session.getRemoteAddress().getAddress().equals(arg0.getRemoteAddress().getAddress())
          && map.get("memid").equals(arg0.getAttributes().get("memid"))
          ){ 
        System.out.println(" ==> �ߺ� ���� �߰�!!!!!");
        d_cnt=1;
        hash_session.remove(key);  // �ߺ��� ���� ������ ����.
        break;
      }
    } 
    
    hash_session.put((String)map.get("memid"), arg0);  // ����� ������ hashmap�� �߰�
     
    System.out.println("===================���Ͽ��� ����=====================");
    System.out.println("������ ������� IP:"+arg0.getRemoteAddress());
    System.out.println("������ ������� ID:"+arg0.getAttributes().get("memid"));
    System.out.println("=================================================");
  }
  
  //�޼����� �����ϴ� �޼ҵ�
  @Override
  public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
    String original=(String)arg1.getPayload();
    String[] parse=original.split("/");
    String who=parse[0];
    String action=parse[1]; // Ŭ���̾�Ʈ���� �ϳ��� �޼��� ����.
    
    if(action.equals("connect")){
      System.out.println("���� ������ ID:"+who);
      System.out.println("����:"+action);
    }else if(action.equals("msg_send")){
      System.out.println("�޴���:"+who);
      System.out.println("����:"+action);
      alaram(who);
    }
    
  }
  
  // ������ ���� ����� ������ �ʿ���.
  // who => �޴� ��� id
  public void alaram(String who) throws IOException{
    iterator=hash_session.keySet().iterator();
    Map<String, Object> map;
    
    while(iterator.hasNext()){
      String key=(String) iterator.next(); 
      session=hash_session.get(key);
      map=session.getAttributes();
      
      if(map.get("memid").equals(who)){
        System.out.println("����� ã����.");
        WebSocketMessage<String> sendMsg=new TextMessage("������ �Խ��ϴ�.");
        session.sendMessage(sendMsg);
      }
    }
  }
  
  //�� ���� ������ ���� �� ��
  @Override 
  public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
    
  }

  @Override
  public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean supportsPartialMessages() {
    // TODO Auto-generated method stub
    return false;
  }
  
}
