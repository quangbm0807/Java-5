package com.poly.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
	throws Exception{
		System.out.println("postHandle() -> "+request.getRequestURI() );
		return true;
	}
	
	@Override
	 public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{
		System.out.println("postHandle() ->"+request.getRequestURI());
	}
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex)throws Exception {
		System.out.println("afterCompletion() ->"+request.getRequestURI());
	}
}
