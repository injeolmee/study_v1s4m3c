package dev.mvc.study;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
// '/webapp/resources' 폴더는 인증된 사용자만 접근 가능
public class AdminInterceptor extends HandlerInterceptorAdapter {
  public AdminInterceptor() {
    System.out.println("--> AdminInterceptor created.");
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
    
    String admauth = (String) session.getAttribute("admauth");
    System.out.println("세션권한 admauth: " + admauth);
    
    if (admauth == null) {
      admauth = "";
    } 

     //if (admauth.contentEquals("M") || admauth.contentEquals("A")) {       
     if (admauth.equals("A") == true || admauth.equals("M") == true) { // 접근 가능, 요청 페이지 처리
       System.out.println("된다아ㅏㅇ");
      return true; // 요청 페이지로 계속 진행  
      
    } else{
      /*System.out.println("== : " + admauth.contentEquals("A"));*/
      System.out.println("어드민 인터셉터");
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