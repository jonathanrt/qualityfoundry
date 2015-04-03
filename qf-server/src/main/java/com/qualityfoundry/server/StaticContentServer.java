package com.qualityfoundry.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class StaticContentServer implements SmartLifecycle {
	private Server jetty;
	private int port;
	
	public StaticContentServer(int port) {
		this.port = port;
	}
	
	public void start() {
	    jetty = new Server();
	    jetty.setStopAtShutdown(true);
	    
	    ServerConnector jettyConnector = new ServerConnector(jetty);
	    jettyConnector.setPort(port);
	    jetty.addConnector(jettyConnector);
	    
	    ServletContextHandler context = new ServletContextHandler();
	    context.setContextPath("/");
	    // TODO: The static resource path should probably not be hard coded.
	    context.setResourceBase(App.class.getClassLoader().getResource("web").toExternalForm());
	    context.addServlet(new ServletHolder(new DefaultServlet()), "/");
	    jetty.setHandler(context);
	    
	    try {
			jetty.start();
		} catch (Exception e) {
			// TODO: Log exception.
		}
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
