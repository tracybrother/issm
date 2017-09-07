package com.tracybrother.mapper;

import java.util.List;

import com.tracybrother.domain.Test;

public interface TestDao {
	public List<Test> findAll();
	
	public int  insert(Test t);
}
