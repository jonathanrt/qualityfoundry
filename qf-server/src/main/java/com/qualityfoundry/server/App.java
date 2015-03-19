package com.qualityfoundry.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Contains the entry point of the QF server.
 * @author jonathanrt
 */
public class App 
{
	/**
	 * The entry point of the QF server.
	 * @param Command line arguments.
	 * @throws Exception 
	 */
    public static void main( String[] args ) throws Exception
    {
    	// Create a Spring context.
    	AnnotationConfigWebApplicationContext rootSpringContext = new AnnotationConfigWebApplicationContext();
    	rootSpringContext.setConfigLocation(AppConfig.class.getName());
    	
        Server jetty = new Server();
        jetty.setStopAtShutdown(true);
        
        ServerConnector jettyConnector = new ServerConnector(jetty);
        jettyConnector.setPort(8000);
        jetty.addConnector(jettyConnector);
        
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.setResourceBase(App.class.getClassLoader().getResource("web").toExternalForm());
        context.addServlet(new ServletHolder(new DefaultServlet()), "/");
        context.addServlet(new ServletHolder(new DispatcherServlet(rootSpringContext)), "/qf-server/*");
        context.addEventListener(new ContextLoaderListener(rootSpringContext));
        jetty.setHandler(context);
        
        jetty.start();
    }
}
