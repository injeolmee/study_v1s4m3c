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
      
      // 처리를 끝냄 - 컨트롤로 요청이 가지 않음.
      response.sendRedirect(request.getContextPath() + "/nonuser/login/login.do");
      return false;
     }
    
    String memauth = (String) session.getAttribute("memauth");
    String admauth = (String) session.getAttribute("admauth");
    // System.out.println("세션권한 member: " + memauth);
    
    if (admauth == null) {
      admauth = "";
    } 
    if (memauth == null) {
      memauth = "";
    } 

    if (memauth.equals("U") == true || admauth.equals("M") == true || admauth.equals("A") == true ) { // 접근 가능, 요청 페이지 처리
      // System.out.println("인터셉터 안됨");
      return true; // 요청 페이지로 계속 진행
    } else{
      // System.out.println("인터셉터됨");
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