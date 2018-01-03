/*package dev.mvc.visit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

class SpringExample implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = ;

        VisitCountDAO visitCountDAO = context.getBean(VisitCountDAO.class);
        SessionListener listener = new SessionListener();
        listener.setVisitCountDAO(visitCountDAO);

        // register the listener instance
        servletContext.addListener(listener);

        // then register DispatcherServlet and ContextLoaderListener, as appropriate
        
    }
}*/