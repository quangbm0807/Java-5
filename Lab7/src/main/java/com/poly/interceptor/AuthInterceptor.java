package com.poly.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.entity.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
@Override
public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
	Account acc = (Account)request.getSession().getAttribute("taikhoan");
	 if(acc != null && !acc.isAdmin()) {
	request.getSession().setAttribute("Login", "Phải là admin mới được truy cập!");
	 request.getSession().setAttribute("UrlSecure", request.getRequestURI());
	 response.sendRedirect("/login");
	 return false;
	 
}
	 return true;
}

}