package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
public class InterceptorValidateIsLogin implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//拦截所有的请求，防止用户不登录直接访问某个url
		//获取url地址  
        String reqUrl=request.getRequestURI().replace(request.getContextPath(), "");  
        //System.out.println("url:"+reqUrl);
        //当url地址为登录的url的时候跳过拦截器
        
        if((!reqUrl.contains("action"))||reqUrl.contains("/login.action")||reqUrl.contains("/validation.action")||reqUrl.contains("/registerPage.action")||reqUrl.contains("/register.action")||request.getSession().getAttribute("user")!=null){
			
        	return true;
		}else{
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			return false;
		}
		
	}

}
