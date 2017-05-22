package com.tracybrother.service;

import com.tracybrother.domain.User;

public interface IUserService {
	/**
	 * 插入user数据
	 * 
	 * @param user
	 * @return: void
	 * @author luyan
	 * @date: 2017年5月20日
	 */
	void insert(User user);

	/**
	 * 登录的方法
	 * 
	 * @param username
	 * @param password
	 * @return      
	 * @return: boolean      
	 * @author luyan
	 * @date:  2017年5月20日
	 */
	User login(String username , String password);
	
}
