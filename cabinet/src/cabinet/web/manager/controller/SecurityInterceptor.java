package cabinet.web.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cabinet.infrastructure.SecurityUtility;

import javax.servlet.http.Cookie;
import cabinet.web.util.*;
public class SecurityInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		String uri = arg0.getServletPath().toLowerCase();	
		if (uri.contains("/manager/login.jsp")) 
		{
			return true;
		 }
		Cookie[] cookies = arg0.getCookies();
		Cookie userNameCookie=null;//new  Cookie("userName", userName);
    	Cookie safetyKeyCookie=null;//new  Cookie("safetyKey", safetyKey);
		for (Cookie cookie : cookies)
		{
			if(Keys.Cookie.userName.equals(cookie.getName()))
			{
				userNameCookie=cookie;
			}
			if(Keys.Cookie.safetyKey.equals(cookie.getName()))
			{
				safetyKeyCookie=cookie;
			}
		}
		if(userNameCookie==null||safetyKeyCookie==null)
		{
			arg1.sendRedirect(httpUtil.getContextPath(arg0)+"/manager/login");
			return false;
		}
		String safetyKey=SecurityUtility.getMd5(userNameCookie.getValue()+Keys.Cookie.loginSecretKey);
		if(!safetyKey.equals(safetyKeyCookie.getValue()))
		{
			arg1.sendRedirect(httpUtil.getContextPath(arg0)+"/manager/login");
			return false;
		}
		return true;
	}

}
