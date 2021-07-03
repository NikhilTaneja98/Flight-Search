package com.nagarro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//Class for spring beans configuration(XML replacement)

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = { "com.nagarro.Controllers", "com.nagarro.dao", "com.nagarro.watcher",
		"com.nagarro.model" })
public class Configuration {

	@Bean
	public ViewResolver viewresolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(0);
		return resolver;
	}
}
