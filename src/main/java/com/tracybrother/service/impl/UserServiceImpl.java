package com.tracybrother.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracybrother.domain.User;
import com.tracybrother.domain.UserExample;
import com.tracybrother.domain.UserExample.Criteria;
import com.tracybrother.mapper.UserMapper;
import com.tracybrother.service.IUserService;

@Service("userservice")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper usermapper;

	@Override
	public void insert(User user) {
		usermapper.insert(user);
	}

	@Override
	public User login(String username, String password) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		//List<User> list = usermapper.selectByExample(example);
		return null;//list.isEmpty() ? false : true;
	}
}
