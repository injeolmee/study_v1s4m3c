package dev.mvc.msg_socket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{

  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
      Map<String, Object> attributes) throws Exception {
    
    // ���� �Ķ���� ��, attributes �� ���� �����ϸ� ������ �ڵ鷯 Ŭ������ WebSocketSession�� ���޵ȴ�
    System.out.println("Before Handshake");
      
      
    ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
    System.out.println("URI:"+request.getURI());

    HttpServletRequest req =  ssreq.getServletRequest();

    /*String userId = req.getParameter("userid");
    System.out.println("param, id:"+userId);
    attributes.put("userId", userId);*/ 

    // HttpSession �� ����� �̿����� ���̵� �����ϴ� ��� 
    String id = (String)req.getSession().getAttribute("memid");
    attributes.put("userId", id);
    System.out.println("HttpSession�� ����� id:"+id); 
     
    return super.beforeHandshake(request, response, wsHandler, attributes);

  }

  @Override
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
      Exception ex) {
    System.out.println("After Handshake");
    super.afterHandshake(request, response, wsHandler, ex);
  }
  
}
