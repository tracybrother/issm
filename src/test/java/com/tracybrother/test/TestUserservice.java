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
	@Resource(name="userservice")
	private IUserService service;
	@Test
	public void insert(){
		User user = new User();
//		user.setId(55);
		user.setUsername("666");
		user.setPassword("888");
		service.insert(user);
	}
}
