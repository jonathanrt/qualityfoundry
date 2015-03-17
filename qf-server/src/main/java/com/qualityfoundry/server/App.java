package com.qualityfoundry.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

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
        Server jetty = new Server();
        jetty.setStopAtShutdown(true);
        
        ServerConnector jettyConnector = new ServerConnector(jetty);
        jettyConnector.setPort(8000);
        jetty.addConnector(jettyConnector);
        
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.setResourceBase(App.class.getClassLoader().getResource("web").toExternalForm());
        context.addServlet(DefaultServlet.class, "/");
        jetty.setHandler(context);
        
        jetty.start();
    }
}
