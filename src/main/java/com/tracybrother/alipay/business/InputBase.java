package com.tracybrother.alipay.business;

import java.util.Map;

public class InputBase{
	/** 共有属性 */
	private Map<String,Object> commonMap;
	/** 独特属性 */
	private Map<String,Object> uniqueMap;
	
	public Map<String, Object> getCommonMap() {
		return commonMap;
	}
	public void setCommonMap(Map<String, Object> commonMap) {
		this.commonMap = commonMap;
	}
	public Map<String, Object> getUniqueMap() {
		return uniqueMap;
	}
	public void setUniqueMap(Map<String, Object> uniqueMap) {
		this.uniqueMap = uniqueMap;
	}

	
}
