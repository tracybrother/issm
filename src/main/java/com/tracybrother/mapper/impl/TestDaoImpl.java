package com.tracybrother.mapper.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.tracybrother.domain.Test;
import com.tracybrother.mapper.TestDao;


public class TestDaoImpl implements TestDao{

	private SqlSessionFactory sqlSessionFactory;
	public TestDaoImpl(SqlSessionFactory sqlSessionFactory){
		
		this.sqlSessionFactory=sqlSessionFactory;
	}
	
	@Override
	public List<Test> findAll() {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			List<Test> list = sqlSession.selectList("test.findAll");
			return list;
	}

	@Override
	public int insert(Test t) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int i = sqlSession.insert("test.insert");
		return i;
	}

}
