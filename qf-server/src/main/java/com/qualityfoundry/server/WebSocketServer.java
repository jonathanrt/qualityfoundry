package com.qualityfoundry.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Service
public class WebSocketServer implements ApplicationContextAware, SmartLifecycle {
	private Server jetty;
	private AnnotationConfigWebApplicationContext applicationContext;
	private int port;
	
	public WebSocketServer(int port) {
		this.port = port;
	}

	public void setApplicationContext(ApplicationContext parentApplicationContext)
			throws BeansException {
		applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.setParent(parentApplicationContext);
	}
	
	public void start() {
	    jetty = new Server();
	    jetty.setStopAtShutdown(true);
	    
	    ServerConnector jettyConnector = new ServerConnector(jetty);
	    jettyConnector.setPort(port);
	    jetty.addConnector(jettyConnector);
	    
	    ServletContextHandler context = new ServletContextHandler();
	    context.setContextPath("/");
	    context.addServlet(new ServletHolder(new DispatcherServlet(applicationContext)), "/*");
	    context.addEventListener(new ContextLoaderListener(applicationContext));
	    jetty.setHandler(context);
	    
	    try {
			jetty.start();
		} catch (Exception e) {
			// TODO: Log exception.
		};
	}
	
	public void stop() {
		try {
			jetty.stop();
		}  catch (Exception e) {
			// TODO: Log exception.
		} finally {
			jetty = null;
		}
	}

	@Override
	public boolean isRunning() {
		if (jetty == null) {
			return false;
		}
		return jetty.isRunning();
	}

	@Override
	public int getPhase() {
		return 1;
	}

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		stop();
		callback.run();		
	}


	
}
