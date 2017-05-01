package com.tracybrother.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracybrother.domain.User;
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
}
