package com.tracybrother.context;

import javax.servlet.http.HttpServletRequest;

public class ContextWrap {
 
	public static Context getContext(HttpServletRequest request) {
		Context context = (Context) request.getSession().getAttribute("context");
		return context;
	}
	
	public static void setContext(HttpServletRequest request,Context context) {
		request.getSession().setAttribute("context", context);
	}
	
	public static void removeContext(HttpServletRequest request){
		request.getSession().removeAttribute("context");
	}
}
