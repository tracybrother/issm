package com.tracybrother.utils;

import org.springframework.util.Assert;

/**
 * <pre>
 * 密码生成器和校验器，使用MD5加密
 * </pre>
 * 
 * @author 鲁炎 2016年12月1日
 */
public class PasswordEncoder {
	/** 对密码进行编码时，额外的key */
	private static final String PASSWORD_ENCODE_KEY = "\t梁韦江是个好人";

	/**
	 * 对密码进行编码
	 * 
	 * @param password
	 * @return
	 */
	public static String encodePassword(String password) {
		Assert.hasText(password);
		return EncryptUtil.md5(password + PASSWORD_ENCODE_KEY);
	}

}
