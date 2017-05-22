package com.tracybrother.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tracybrother.context.Context;
import com.tracybrother.context.ContextWrap;
import com.tracybrother.domain.User;
import com.tracybrother.service.IUserService;

@Controller
@RequestMapping("/*")
public class LoginController {
	@Resource(name = "userservice")
	private IUserService userService;
	
	/**
	 * 登陆的方法
	 * 
	 * @param request
	 * @return      
	 * @return: String      
	 * @author luyan
	 * @date:  2017年5月22日
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.login(username, password);
		if(user!= null){
			HttpSession session = request.getSession();
			Context context = ContextWrap.getContext(request);
		}
		return null;

	}
}
