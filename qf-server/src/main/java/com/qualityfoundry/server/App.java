package com.qualityfoundry.server;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

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
    	rootSpringContext.refresh();
    	
    	// Wait for key press.
    	// TODO: Replace this with an actual service (Windows) and daemon (Unix) implementation.
    	System.out.append("Press enter to terminate.");
    	System.in.read();
    	
    	// Clean up the Spring context.
    	rootSpringContext.close();
    }
}
