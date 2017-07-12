package com.tracybrother.alipay.business;

import java.util.Map;

public class OutputBase{
	/** 
	 * 	@Description 交易类返回字段为  returnTransFlow:通道返回流水  retCode:返回码 retMessage:返回信息;
	 * 	@Description 查证类返回字段为
	 * 	@Description 查询类返回字段为
	 */
	private Map<String,Object> map;
	/** 0：未知，1：成功，2:失败，3：异常 */
	private String retCode;			//返回码 0：未知，1：成功，2:失败，3：异常
	private String retMessage;		//返回信息
	
	@Override
	public String toString() {
		return "BeanBase : [ retCode = " +retCode+
				"; retMessage = "+retMessage +"; ]";
	}
	/** 0：未知，1：成功，2:失败，3：异常 */
	public String getRetCode() {
		return retCode;
	}
	/** 0：未知，1：成功，2:失败，3：异常 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMessage() {
		return retMessage;
	}
	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}
	
	/** 
	 * 	@Description 交易类返回字段为  returnTransFlow:通道返回流水  retCode:返回码 retMessage:返回信息;
	 * 	@Description 查证类返回字段为
	 * 	@Description 查询类返回字段为
	 */
	public Map<String,Object> getMap() {
		return map;
	}
	/** 
	 * 	@Description 交易类返回字段为  returnTransFlow:通道返回流水  retCode:返回码 retMessage:返回信息;
	 * 	@Description 查证类返回字段为
	 * 	@Description 查询类返回字段为
	 */
	public void setMap(Map<String,Object> map) {
		this.map = map;
	}
}
