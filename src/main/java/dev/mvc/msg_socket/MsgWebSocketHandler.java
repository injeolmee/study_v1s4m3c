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
  
  //웹 소켓 연결이 성립 된 후  
  @Override
  public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
    
    // HandshakeInterceptor에서 attributes.put(); 를 통해 전달 받은 세션의 값 들을 사용할 수 있다.
    Map<String, Object> map=arg0.getAttributes();
    // System.out.println(map); 
    // System.out.println(map.get("memid"));
    
    String memid;
    d_cnt=0;
    
    iterator=hash_session.keySet().iterator(); // 해쉬맵을 탐색하기 위한 준비
    
    // 세션들 중에 새로운 연결 세션과 동일한 IP를 가지고 있는 세션이 있는지 검사한다.
    while(iterator.hasNext()){
      String key=(String) iterator.next(); 
      //System.out.println(hash_session.get(key));
      session=hash_session.get(key);
      map=session.getAttributes();
      // 기존에 있는 연결 정보의 IP와 HttpSession에 있는 memid값을 비교한다.
      // 새로운 연결과 기존 연결의 정보가 동일하면 중복 접속으로 판단.
      
      if(session.getRemoteAddress().getAddress().equals(arg0.getRemoteAddress().getAddress())
          && map.get("memid").equals(arg0.getAttributes().get("memid"))
          ){ 
        System.out.println(" ==> 중복 연결 발견!!!!!");
        d_cnt=1;
        hash_session.remove(key);  // 중복된 연결 세션을 제거.
        break;
      }
    } 
    
    hash_session.put((String)map.get("memid"), arg0);  // 연결된 세션을 hashmap에 추가
     
    System.out.println("===================소켓연결 정보=====================");
    System.out.println("접속한 사용자의 IP:"+arg0.getRemoteAddress());
    System.out.println("접속한 사용자의 ID:"+arg0.getAttributes().get("memid"));
    System.out.println("=================================================");
  }
  
  //메세지를 전송하는 메소드
  @Override
  public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1) throws Exception {
    String original=(String)arg1.getPayload();
    String[] parse=original.split("/");
    String who=parse[0];
    String action=parse[1]; // 클라이언트에게 하나의 메세지 수신.
    
    if(action.equals("connect")){
      System.out.println("현재 접속한 ID:"+who);
      System.out.println("동작:"+action);
    }else if(action.equals("msg_send")){
      System.out.println("받는이:"+who);
      System.out.println("동작:"+action);
      alaram(who);
    }
    
  }
  
  // 쪽지를 받을 상대의 세션이 필요함.
  // who => 받는 사람 id
  public void alaram(String who) throws IOException{
    iterator=hash_session.keySet().iterator();
    Map<String, Object> map;
    
    while(iterator.hasNext()){
      String key=(String) iterator.next(); 
      session=hash_session.get(key);
      map=session.getAttributes();
      
      if(map.get("memid").equals(who)){
        System.out.println("대상을 찾았음.");
        WebSocketMessage<String> sendMsg=new TextMessage("쪽지가 왔습니다.");
        session.sendMessage(sendMsg);
      }
    }
  }
  
  //웹 소켓 연결이 해제 된 후
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
