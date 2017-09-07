package com.tracybrother.test;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.tracybrother.domain.User;
import com.tracybrother.mapper.impl.TestDaoImpl;
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
	SqlSessionFactory sqlSessionFactory;
	@Before
	public void beforeConfig() throws Exception{
		String resource ="sqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
	}
	@Test
	public void testInsert(){
		com.tracybrother.domain.Test t = new com.tracybrother.domain.Test();
		t.setId("886");
		t.setName("lucy");
		t.setTest("test");
		TestDaoImpl userDao = new TestDaoImpl(sqlSessionFactory);
		int insert = userDao.insert(t);
		System.out.println(t);
	}
	
	@Test
	public void testDao(){
		TestDaoImpl userDao = new TestDaoImpl(sqlSessionFactory);
		List<com.tracybrother.domain.Test> list = userDao.findAll();
		String id = list.get(0).getId();
		System.out.println("id:"+id);
		System.out.println(list.toString());
		
	}
}
