package com.tracybrother.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tracybrother.context.Context;
import com.tracybrother.context.ContextWrap;


public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		System.out.println("PermissionInterceptor.preHandle[" + request.getRequestURL() + ".." + handler.getClass().getSimpleName() + "]...");
		String requestUri = request.getRequestURI();
		if((requestUri.indexOf(".") > 0) || requestUri.contains("loginUI") || requestUri.contains("login") 
		  || requestUri.contains("getOrgAndDepts") || requestUri.contains("getEmps")
		  ||requestUri.contains("getNcIndex")||requestUri.contains("getNcMessage")
		  ||requestUri.contains("getOaIndex")||requestUri.contains("modifyPwdUI")
		  ||requestUri.contains("modify-pwd")||requestUri.contains("modifyPwd")
		  ||requestUri.contains("sendMessage")||requestUri.contains("checkMessage")){
			//直接放行 
		}else{
			Context context = ContextWrap.getContext(request);
			if(context == null) {
				response.sendRedirect(request.getContextPath()+"/loginValidate/loginUI");
				return false;
			}else{
				
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndView mv) throws Exception {
		System.out.println("PermissionInterceptor.postHandle[" + request.getRequestURL() + ".." + handler.getClass().getSimpleName() + "]...");
	
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("PermissionInterceptor.afterCompletion[" + request.getRequestURL() + ".." + handler.getClass().getSimpleName() + "]...");
		if (ex == null) {
			//记录操作日志
		} else {
			System.out.println("ExceptionInterceptor.afterCompletion[" + request.getRequestURL() + ".." + handler.getClass().getSimpleName() + "]出现异常...");
			ex.printStackTrace();
		}
	}

}
