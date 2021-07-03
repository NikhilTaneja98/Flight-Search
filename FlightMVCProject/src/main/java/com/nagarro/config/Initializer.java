package com.nagarro.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Class for dispatcher servlet configuration
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { Configuration.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

}
