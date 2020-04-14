package com.saty.springdemo.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.*;
public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {DemoConfigApp.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}
	
	  @Override
	    protected Filter[] getServletFilters() {
	        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	        characterEncodingFilter.setEncoding("UTF-8");
	        return new Filter[] { characterEncodingFilter , new HiddenHttpMethodFilter()};
	    }
	
}
