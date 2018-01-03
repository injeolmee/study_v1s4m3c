package dev.mvc.visit;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SessionListener implements HttpSessionListener {

  // @Autowired
  // @Qualifier("dev.mvc.visit.VisitCountDAO")
  // private VisitCountDAO visitCountDAO;
  
  private static ApplicationContext applicationContext2 = new FileSystemXmlApplicationContext(
      "C:/bigdata1/ws_frame/study_v1s4m3c/src/main/webapp/WEB-INF/spring/root-context.xml");
  private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:listener.xml");

  public SessionListener() {
    System.out.println("���� ������");
    // String names[] = applicationContext.getBeanDefinitionNames();
    // for(String n : names){ System.out.println(n);}
/*    String names2[] = applicationContext2.getBeanDefinitionNames();
    for (String n : names2) {
      System.out.println(n);
    }*/


  }

  @Override
  public void sessionCreated(HttpSessionEvent sessionEve) {
    org.mybatis.spring.SqlSessionTemplate sqlsession = applicationContext2.getBean("sqlSessionTemplate",
        org.mybatis.spring.SqlSessionTemplate.class);
    // System.out.println(sqlsession);
    VisitCountDAO visitCountDAO = applicationContext.getBean("visitCountDAO", VisitCountDAO.class);
    visitCountDAO.setMybatis(sqlsession);
    // System.out.println(visitCountDAO);

    HttpSession session = sessionEve.getSession();

    //VisitCountDAO visitCountDAO = new VisitCountDAO();

    System.out.println("SessionListener: session create! " + session);
    // System.out.println(visitCountDAO);//NULL

    int todayCount = 0;
    int totalCount = 0;

    // // ��ü �湮�� �� +1
    try {

      visitCountDAO.setVisitTotalCount();
      // ���� �湮�� ��
      todayCount = visitCountDAO.getVisitTodayCount();

      // ��ü �湮�� ��
      totalCount = visitCountDAO.getVisitTotalCount();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // ���� �Ӽ��� ����ش�.
    session.setAttribute("totalCount", totalCount); // ��ü �湮�� ��
    session.setAttribute("todayCount", todayCount); // ���� �湮�� ��
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent sessionEve) {
    HttpSession session = sessionEve.getSession();
    System.out.println("SessionListener: session invalidated! " + session);
  }
}
