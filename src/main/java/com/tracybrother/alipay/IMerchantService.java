package com.tracybrother.alipay;

import com.tracybrother.alipay.business.OutputBase;
import com.tracybrother.alipay.business.SpayServiceBase;
import com.tracybrother.alipay.entity.MerchantRequest;

/**
 * @ClassName: MerchantService
 * @Description: 三方通道 信息报备接口（应用于支付宝微信）
 * @author weiss
 * @date 2017年5月18日
 */
public interface IMerchantService extends SpayServiceBase {
	
	/**
	 * @Title: merchantCreate
	 * @Description: 商户信息报备
	 * @author: weiss
	 * @date: 2017年5月18日
	 */
	public OutputBase merchantCreate(MerchantRequest bean) throws Exception;
	
	/**
	 * @Title: merchantQuery
	 * @Description: 商户信息查询
	 * @author: weiss
	 * @date: 2017年5月18日
	 */
	public OutputBase merchantQuery(MerchantRequest bean) throws Exception;
	
	/**
	 * @Title: merchantUpdate
	 * @Description: 商户信息修改（只有支付宝有）
	 * @author: weiss
	 * @date: 2017年5月18日
	 */
	public OutputBase merchantUpdate(MerchantRequest bean) throws Exception;
	
}
