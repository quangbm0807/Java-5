package com.poly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poly.interceptor.AdminInterceptor;
import com.poly.interceptor.AuthInterceptor;
import com.poly.interceptor.LoggerInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	
	@Autowired
	LoggerInterceptor logger;
	
	@Autowired
	AuthInterceptor auth;
	
	@Autowired
	AdminInterceptor admin;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logger).addPathPatterns("/**").excludePathPatterns("/css/**");
		
		registry.addInterceptor(auth).addPathPatterns("/mail/send","/info","/infor/**");
	
		registry.addInterceptor(admin).addPathPatterns("/info","/info/**");
	}
}
