package com.tracybrother.alipay.impl;

import java.util.HashMap;
import java.util.Map;

import com.tracybrother.alipay.RSA;
import com.tracybrother.alipay.business.AppsService;
import com.tracybrother.alipay.business.OutputBase;
import com.tracybrother.alipay.entity.AliPayBase;
import com.tracybrother.alipay.entity.AppsRequest;

/**
 * 支付宝PC网页支付退款相关业务实现类
 * @author wdd
 *
 */
public class AliPayPcServiceImpl extends AliPayBase implements AppsService {

	@Override
	public OutputBase execute(AppsRequest appsRequest) throws Exception {
		
		//把请求参数打包成数组
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("service", ALIPAY_PC_SERVICE);
		paramsMap.put("partner", appsRequest.getExtra().get(CHANNEL_PARAMS_OPENID));
		paramsMap.put("seller_id", appsRequest.getExtra().get(CHANNEL_PARAMS_OPENID));//一般情况下收款账号就是签约账号  seller_id是以2088开头的纯16位数字
		paramsMap.put("_input_charset", CHARSET_UTF8);
		paramsMap.put("payment_type", PAYMENT_TYPE);
		paramsMap.put("notify_url", ALIPAY_NOTIFY_URL);//支付宝异步通知地址，SPAY++的异步接收地址
		paramsMap.put("return_url", appsRequest.getExtra().get(CHANNEL_PARAMS_RETURNURL));//商户同步接收地址，发送到SPAY++进行封装
//		paramsMap.put("anti_phishing_key", AlipayConfig.anti_phishing_key);//如果已申请开通防钓鱼时间戳验证，则此字段必填
//		paramsMap.put("exter_invoke_ip", appsRequest.getClientIp());
		paramsMap.put("out_trade_no", appsRequest.getOutTradeNo());//商户网站唯一订单号
		paramsMap.put("subject", appsRequest.getSubject());
		paramsMap.put("total_fee", appsRequest.getTotalAmount());
		paramsMap.put("body", appsRequest.getBody());
		
		
		/*//参数进行处理拼装
		String content = createLinkString(paramsMap);
		
		//对原始字符串进行RSA签名
		String sign = AlipaySignature.rsaSign(content, appsRequest.getChannelDetailMap().get(CHANNEL_PARAMS_PRIKEY), 
				CHARSET_UTF8, SIGN_TYPE_RSA);
		//返回支付凭据
		//https://mapi.alipay.com/gateway.do?body=%C3%C0%B9%FA%D7%A8%D2%B5%B
		String credential = ALIPAY_BASEURL+"?"+content+"&sign_type=RSA&sign="+sign;*/
		
		
		//除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(paramsMap);
        String prestr = createLinkString(sPara);
        String mysign = RSA.sign(prestr, String.valueOf(appsRequest.getChannelDetailMap().get(CHANNEL_PARAMS_PRIKEY)));
        sPara.put("sign", mysign);
        sPara.put("sign_type", SIGN_TYPE_RSA);
        
        String content = getEncodeForParams(sPara);
        String credential = ALIPAY_GATEWAY_PC+"?"+content;
		
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("credential", credential);
		System.out.println("credential:"+credential);
		OutputBase outPutBase = new OutputBase();
		outPutBase.setRetCode(CHANNEL_CODE_SUCC);//返回成功
		outPutBase.setMap(m);
		
		return outPutBase;
	}

	@Override
	public OutputBase check(AppsRequest appsRequest) throws Exception {
		return super.baseQuery(appsRequest);
	}

	@Override
	public OutputBase refundCheck(AppsRequest appsRequest) throws Exception {
		return super.baseRefundQuery(appsRequest);
	}

	@Override
	public OutputBase refund(AppsRequest appsRequest) throws Exception {
		return super.baseRefund(appsRequest);
	}

	@Override
	public OutputBase query(AppsRequest appsRequest) throws Exception {
		return super.baseQuery(appsRequest);
	}

}
