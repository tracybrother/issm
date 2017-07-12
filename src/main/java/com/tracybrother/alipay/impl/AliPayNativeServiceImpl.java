package com.tracybrother.alipay.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.tracybrother.alipay.business.AppsService;
import com.tracybrother.alipay.business.OutputBase;
import com.tracybrother.alipay.entity.AliPayBase;
import com.tracybrother.alipay.entity.AppsRequest;
import com.tracybrother.utils.DateUtil;
import com.tracybrother.utils.StringUtil;

/**
 * 支付宝条码支付退款相关接口实现类
 * @author ly
 *
 */
public class AliPayNativeServiceImpl extends AliPayBase implements AppsService {

	@Override
	public OutputBase execute(AppsRequest appsRequest) throws Exception {
		alipayClient = getClient(appsRequest);
		AlipayTradePayRequest request = new AlipayTradePayRequest();
		// 拼装biz_content 业务参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("out_trade_no", appsRequest.getOutTradeNo());
		map.put("scene", ALIPAY_NATIVE_BAR_CODE);
		map.put("auth_code", appsRequest.getExtra().get(CHANNEL_PARAMS_AUTHCODE));//支付授权码
		map.put("subject", appsRequest.getSubject());
		map.put("total_amount", appsRequest.getTotalAmount());
		map.put("body", appsRequest.getBody());
		map.put("product_code", ALIPAY_NATIVE_PRODUCT_CODE);
		
		//间连业务判断是否为空
		if(StringUtil.hasText(appsRequest.getStoreId())){
			map.put("store_id", appsRequest.getStoreId());
		}
		if(StringUtil.hasText(appsRequest.getTerminalId())){
			map.put("terminal_id", appsRequest.getTerminalId());
		}
		if(StringUtil.hasText(appsRequest.getMerchantId())){
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("merchant_id", appsRequest.getMerchantId());
			map.put("sub_merchant", map1);
		}
		if(StringUtil.hasText(appsRequest.getBuyerId())){
			map.put("buyer_id", appsRequest.getBuyerId());
		}
		
		
		String bizContent = JSONObject.toJSONString(map);
		
		request.setBizContent(bizContent); //设置业务参数
		OutputBase outPutBase = new OutputBase();
		AlipayTradePayResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			outPutBase.setRetCode(CHANNEL_CODE_SUCC);//返回成功
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("transactionNo", response.getTradeNo());
			m.put("outTradeNo", response.getOutTradeNo());
			m.put("timeCompleted", DateUtil.dateFormat(response.getGmtPayment())); //TODO有修改
			m.put("totalAmount", response.getTotalAmount());
			outPutBase.setMap(m);
		}else{
			outPutBase.setRetCode(CHANNEL_CODE_FAIL);
			outPutBase.setRetMessage(response.getSubMsg());
		}
		return outPutBase;
	}

	@Override
	public OutputBase check(AppsRequest appsRequest) throws Exception {
		return super.baseQuery(appsRequest);
	}

	@Override
	public OutputBase refund(AppsRequest appsRequest) throws Exception {
		return super.baseRefund(appsRequest);
	}

	@Override
	public OutputBase refundCheck(AppsRequest appsRequest) throws Exception {
		return super.baseRefundQuery(appsRequest);
	}

	@Override
	public OutputBase query(AppsRequest appsRequest) throws Exception {
		return super.baseQuery(appsRequest);
	}

}
