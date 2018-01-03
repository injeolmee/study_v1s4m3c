package nation.web.tool;
 
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
public class URILogFilter implements Filter {
  private String charSet = null;
  
  public URILogFilter(){

  }
  
  // 톰캣 시작시 자동실행되어 초기화 진행
  @Override
  public void init(FilterConfig config) throws ServletException {
    // /WEB-INF/web.xml파일의 <init-param>태그의 값
    charSet = config.getInitParameter("charSet");
 
    System.out.println("────────────");
    System.out.println(" URI Logger start...");
    System.out.println("────────────");
  }
 
  // 요청이 오면 자동 실행
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    
    // ------------------------------------------------------------------------------
    // session의 사용 ★
    // ------------------------------------------------------------------------------
    // 새로운 세션을 생성하지않고 기존의 세션 객체를 반환
    HttpSession session = request.getSession(true);
    // System.out.println("session: " + session);
    
/*    String email = (String)session.getAttribute("email");
    if (email == null) {
      email = "Guest";
    }*/
    // ------------------------------------------------------------------------------
    
    // 한글 문자셋 변경
    request.setCharacterEncoding(charSet);
 
    // 요청 uri 추출 부분, 도메인을 제거한 주소
    String uri = request.getRequestURI();
 
    String ip = request.getRemoteAddr();
    // System.out.println("ip: " + ip);
 
    try {

    } catch (Exception e) {
      System.out.println(e.toString());
    } 
 
    // 다른 필터 실행, 요청 JSP 실행
    chain.doFilter(request, response);
  }
 
  public void destroy() {
 
  }
}
 