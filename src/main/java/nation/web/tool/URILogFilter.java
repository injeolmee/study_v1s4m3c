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
  
  // ��Ĺ ���۽� �ڵ�����Ǿ� �ʱ�ȭ ����
  @Override
  public void init(FilterConfig config) throws ServletException {
    // /WEB-INF/web.xml������ <init-param>�±��� ��
    charSet = config.getInitParameter("charSet");
 
    System.out.println("������������������������");
    System.out.println(" URI Logger start...");
    System.out.println("������������������������");
  }
 
  // ��û�� ���� �ڵ� ����
  public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    
    // ------------------------------------------------------------------------------
    // session�� ��� ��
    // ------------------------------------------------------------------------------
    // ���ο� ������ ���������ʰ� ������ ���� ��ü�� ��ȯ
    HttpSession session = request.getSession(true);
    // System.out.println("session: " + session);
    
/*    String email = (String)session.getAttribute("email");
    if (email == null) {
      email = "Guest";
    }*/
    // ------------------------------------------------------------------------------
    
    // �ѱ� ���ڼ� ����
    request.setCharacterEncoding(charSet);
 
    // ��û uri ���� �κ�, �������� ������ �ּ�
    String uri = request.getRequestURI();
 
    String ip = request.getRemoteAddr();
    // System.out.println("ip: " + ip);
 
    try {

    } catch (Exception e) {
      System.out.println(e.toString());
    } 
 
    // �ٸ� ���� ����, ��û JSP ����
    chain.doFilter(request, response);
  }
 
  public void destroy() {
 
  }
}
 