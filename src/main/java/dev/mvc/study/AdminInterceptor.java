package dev.mvc.study;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
// '/webapp/resources' ������ ������ ����ڸ� ���� ����
public class AdminInterceptor extends HandlerInterceptorAdapter {
  public AdminInterceptor() {
    System.out.println("--> AdminInterceptor created.");
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
    
    String admauth = (String) session.getAttribute("admauth");
    System.out.println("���Ǳ��� admauth: " + admauth);
    
    if (admauth == null) {
      admauth = "";
    } 

     //if (admauth.contentEquals("M") || admauth.contentEquals("A")) {       
     if (admauth.equals("A") == true || admauth.equals("M") == true) { // ���� ����, ��û ������ ó��
       System.out.println("�ȴپƤ���");
      return true; // ��û �������� ��� ����  
      
    } else{
      /*System.out.println("== : " + admauth.contentEquals("A"));*/
      System.out.println("���� ���ͼ���");
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