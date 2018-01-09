package dev.mvc.study;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import dev.mvc.visit.VisitCountDAO;
 
public class MemberInterceptor extends HandlerInterceptorAdapter {
  public MemberInterceptor() {
     System.out.println("--> MemberInterceptor created.");
  }
 
  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession(false);  
    
    if (session == null) {
      
      // ó���� ���� - ��Ʈ�ѷ� ��û�� ���� ����.
      response.sendRedirect(request.getContextPath() + "/nonuser/login/login.do");
      return false;
     }
    
    String memauth = (String) session.getAttribute("memauth");
    String admauth = (String) session.getAttribute("admauth");
    // System.out.println("���Ǳ��� member: " + memauth);
    
    if (admauth == null) {
      admauth = "";
    } 
    if (memauth == null) {
      memauth = "";
    } 

    if (memauth.equals("U") == true || admauth.equals("M") == true || admauth.equals("A") == true ) { // ���� ����, ��û ������ ó��
      // System.out.println("���ͼ��� �ȵ�");
      return true; // ��û �������� ��� ����
    } else{
      // System.out.println("���ͼ��͵�");
      response.sendRedirect(request.getContextPath() + "/nonuser/login/login.do");
      return false;
    }
 
  }
  
  
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
          ModelAndView modelAndView) throws Exception {
      // TODO Auto-generated method stub
      super.postHandle(request, response, handler, modelAndView);
  }

 
  
}