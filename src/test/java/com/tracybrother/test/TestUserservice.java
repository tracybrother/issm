package com.tracybrother.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.tracybrother.domain.User;
import com.tracybrother.service.IUserService;

/**
 * UserService测试类
 * 
 * @author Tracy
 *
 */
public class TestUserservice extends BaseTest {
	@Resource(name = "userservice")
	private IUserService service;

	@Test
	public void insert() {
		User user = new User();
		// user.setId(55);
		user.setUsername("aaa");
		user.setPassword("55555");
		service.insert(user);
	}

	@Test
	public void testLogin() {
		
		service.login("aaa", "55555");
//		System.out.println(login);
	}
}
