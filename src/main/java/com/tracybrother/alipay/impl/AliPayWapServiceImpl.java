package com.tracybrother.alipay.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.tracybrother.alipay.business.AppsService;
import com.tracybrother.alipay.business.OutputBase;
import com.tracybrother.alipay.entity.AliPayBase;
import com.tracybrother.alipay.entity.AppsRequest;
import com.tracybrother.utils.DateUtil;

/**
 * 支付宝手机wap网页支付退款相关业务实现类
 * @author wdd
 *
 */
public class AliPayWapServiceImpl extends AliPayBase implements AppsService {

	@Override
	public OutputBase execute(AppsRequest appsRequest) throws Exception {
//		//初始化alipay的client对象
//		alipayClient = getClient(appsRequest);
		
		//-------自己拼加密开始--------
		// 公共参数
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("app_id", appsRequest.getChannelDetailMap().get(CHANNEL_PARAMS_APPID));
		paramsMap.put("charset", CHARSET_UTF8);
		paramsMap.put("format", FORMAT_JSON);
		paramsMap.put("method", ALIPAY_METHOD_WAPPAY);
		paramsMap.put("notify_url", ALIPAY_NOTIFY_URL);
		paramsMap.put("sign_type", SIGN_TYPE_RSA);
		paramsMap.put("timestamp", DateUtil.dateFormat(new Date()));//TODO "yyyy-MM-dd HH:mm:ss"
		paramsMap.put("version", ALIPAY_APIVERSION);
		
		// 拼装biz_content 业务参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("subject", appsRequest.getSubject());
		map.put("body", appsRequest.getBody());
		map.put("out_trade_no", appsRequest.getOutTradeNo());
		map.put("total_amount", appsRequest.getTotalAmount());
		map.put("product_code", ALIPAY_WAP_PRODUCT_CODE);
		String bizContent = JSONObject.toJSONString(map);
		paramsMap.put("biz_content", bizContent);
		
		//对未签名原始字符串进行签名       
        String rsaSign = AlipaySignature.rsaSign(paramsMap, appsRequest.getChannelDetailMap().get(CHANNEL_PARAMS_PRIKEY), CHARSET_UTF8);
        paramsMap.put("sign", rsaSign);
        String credential = ALIPAY_BASEURL+"?"+getSignEncodeUrl(paramsMap, true);
		System.out.println("credential:"+credential);
		//-------自己拼加密结束--------
		
		
		
		/*//-------支付宝SDK方式开始-------返回form格式支付凭据
		AlipayTradeWapPayRequest alipayWapRequest = new AlipayTradeWapPayRequest();
		alipayWapRequest.setNotifyUrl(SPAYX_NOTIFY_URL);
		alipayWapRequest.setReturnUrl(SPAYX_NOTIFY_URL);
		
		
		AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	    model.setOutTradeNo(appsRequest.getOutTradeNo());
	    model.setSubject(appsRequest.getSubject());
	    model.setTotalAmount(appsRequest.getTotalAmount());
	    model.setBody(appsRequest.getBody());
	    model.setProductCode(ALIPAY_WAP_PRODUCT_CODE);
	    alipayWapRequest.setBizModel(model);
		String credential = alipayClient.pageExecute(alipayWapRequest).getBody();
		//--------支付宝SDK方式结束-----
		*/
		
		OutputBase outPutBase = new OutputBase();
		outPutBase.setRetCode(CHANNEL_CODE_SUCC);//返回成功码
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("credential", credential);
		outPutBase.setMap(m);//返回给商户支付凭据
		
//		httpResponse.setContentType("text/html;charset=" + AlipayServiceEnvConstants.CHARSET);
//	    httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
//	    httpResponse.getWriter().flush();
		
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
