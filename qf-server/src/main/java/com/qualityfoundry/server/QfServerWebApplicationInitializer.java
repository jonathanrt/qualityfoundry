package com.qualityfoundry.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class QfServerWebApplicationInitializer implements WebApplicationInitializer {
	private static final Logger log = LogManager.getLogger(QfServerWebApplicationInitializer.class);

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		log.trace("Create the Spring context.");
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
		
		log.trace("Register the Spring Dispatcher servlet.");
		ServletRegistration.Dynamic registration = container.addServlet("qf-server", new DispatcherServlet(appContext));
		registration.setLoadOnStartup(1);
		registration.addMapping("/qf-server/*");
	}

}
