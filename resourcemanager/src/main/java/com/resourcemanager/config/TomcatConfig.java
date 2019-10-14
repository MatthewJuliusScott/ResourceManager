/*
 *
 */

package com.resourcemanager.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the internal Apache Tomcat / Jasper container launched by the Spring Boot Application.
 */
@Configuration
public class TomcatConfig {

	/**
	 * Servlet container. Configures port, protocol and connector.
	 *
	 * @return the tomcat servlet web server factory
	 */
	@Bean
	public TomcatServletWebServerFactory servletContainer() {

		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		Connector ajpConnector = new Connector("AJP/1.3");
		ajpConnector.setPort(8009);
		ajpConnector.setSecure(false);
		ajpConnector.setAllowTrace(false);
		ajpConnector.setScheme("https");
		tomcat.addAdditionalTomcatConnectors(ajpConnector);

		return tomcat;
	}

}