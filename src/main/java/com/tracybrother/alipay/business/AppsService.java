package com.tracybrother.alipay.business;

import com.tracybrother.alipay.entity.AppsRequest;

/**
 * Apps:微信，支付宝，京东，百度等
 * 支付退款查证相关业务接口类
 * @author wdd
 *
 */
public interface AppsService extends SpayServiceBase {
	
	//支付
	public OutputBase execute(AppsRequest appsRequest) throws Exception;
	
	//查证
	public OutputBase check(AppsRequest appsRequest) throws Exception;
	
	//退款
	public OutputBase refund(AppsRequest appsRequest) throws Exception;
	
	//退款查证
	public OutputBase refundCheck(AppsRequest appsRequest) throws Exception;
	
	//查询
	public OutputBase query(AppsRequest appsRequest) throws Exception;
	
	
}
