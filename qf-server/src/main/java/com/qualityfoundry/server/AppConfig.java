package com.qualityfoundry.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The root application Spring configuration class.
 * @author jonathanrt
 *
 */
@Configuration
public class AppConfig {

	/**
	 * Creates a static content server on port 8000.
	 * @return A static content server.
	 */
	@Bean
	public StaticContentServer staticContentServer() {
		return new StaticContentServer(8000);
	}
	
	/**
	 * Creates a web socket server on port 8001.
	 * @return A web socket server.
	 */
	@Bean
	public WebSocketServer webSocketServer1() {
		return new WebSocketServer(8001);
	}
	
	/**
	 * Creates a web socket server on port 8002.
	 * @return A web socket server.
	 */
	@Bean
	public WebSocketServer webSocketServer2() {
		return new WebSocketServer(8002);
	}
	
}
