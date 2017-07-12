package com.tracybrother.alipay.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.tracybrother.alipay.business.AppsService;
import com.tracybrother.alipay.business.OutputBase;
import com.tracybrother.alipay.entity.AliPayBase;
import com.tracybrother.alipay.entity.AppsRequest;

/**
 * 支付宝APP支付退款相关接口实现类
 * @author wdd
 *
 */
public class AliPayAppServiceImpl extends AliPayBase implements AppsService {
	
	@Override
	public OutputBase execute(AppsRequest appsRequest) throws Exception {
				
		//使用sdk方式
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(appsRequest.getBody());
		model.setSubject(appsRequest.getSubject());
		model.setOutTradeNo(appsRequest.getOutTradeNo());
//		model.setTimeoutExpress("30m");
		model.setTotalAmount(appsRequest.getTotalAmount());
		model.setProductCode(ALIPAY_APP_PRODUCT_CODE);
		
		AlipayTradeAppPayRequest appPayRequest  = new AlipayTradeAppPayRequest();
		appPayRequest.setApiVersion(ALIPAY_APIVERSION);
		appPayRequest.setNotifyUrl(ALIPAY_NOTIFY_URL);
		appPayRequest.setBizModel(model);
		alipayClient = getClient(appsRequest);
		AlipayTradeAppPayResponse appPesponse = alipayClient.sdkExecute(appPayRequest);
		String credential = "";
		OutputBase outPutBase = new OutputBase();
		if(appPesponse.isSuccess()){
			credential = appPesponse.getBody();//就是orderString 可以直接给客户端请求，无需再做处理
			System.out.println("credential:"+credential);
			outPutBase.setRetCode(CHANNEL_CODE_SUCC);//返回成功
			Map<String,Object> m = new HashMap<String,Object>();//返回支付凭据
			m.put("credential", credential);
			outPutBase.setMap(m);
		}else{
			outPutBase.setRetCode(appPesponse.getSubCode());
			outPutBase.setRetMessage(appPesponse.getSubMsg());
		}
		return outPutBase;
		
		/*//自己加密方式
		//公共参数
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("app_id", appsRequest.getAppId());
		paramsMap.put("charset", CHARSET_UTF8);
		paramsMap.put("format", JSON);
		paramsMap.put("method", ALIPAY_METHOD_APPPAY);
		paramsMap.put("notify_url", SPAYX_NOTIFY_URL);
		paramsMap.put("sign_type", appsRequest.getSignType());
		paramsMap.put("timestamp", appsRequest.getTimestamp());//"yyyy-MM-dd HH:mm:ss"
		paramsMap.put("version", ALIPAY_APIVERSION);
		
		//拼装biz_content  业务参数
		Map<String, String> map = new HashMap<String, String>();
//		map.put("seller_id", appsRequest.getSellerId());
		map.put("product_code", ALIPAY_APP_PRODUCT_CODE);
		map.put("total_amount", appsRequest.getTotalAmount());
		map.put("subject", appsRequest.getSubject());
		map.put("body", appsRequest.getBody());
		map.put("timeout_express", "30m");
		map.put("out_trade_no", appsRequest.getOutTradeNo());
		String bizContent = JSONObject.toJSONString(map);
		paramsMap.put("biz_content", bizContent);
		
		
		//对未签名原始字符串进行签名       
        String rsaSign = AlipaySignature.rsaSign(paramsMap, appsRequest.getPrivateKey(), "utf-8");
        
        paramsMap.put("sign", rsaSign);
        String credential = getSignEncodeUrl(paramsMap, true);*/
        
        
//        Map<String, String> map4 = new HashMap<String, String>();
//
//        map4.put("app_id", appsRequest.getAppId());
//        map4.put("method", ALIPAY_METHOD_APPPAY);
//        map4.put("format", JSON);
//        map4.put("charset", CHARSET_UTF8);
//        map4.put("sign_type", appsRequest.getSignType());
//        map4.put("timestamp", URLEncoder.encode(appsRequest.getTimestamp(),CHARSET_UTF8));
//        map4.put("version", "1.0");
//        map4.put("notify_url",  URLEncoder.encode(SPAYX_NOTIFY_URL,CHARSET_UTF8));
//        //最后对请求字符串的所有一级value（biz_content作为一个value）进行encode，编码格式按请求串中的charset为准，没传charset按UTF-8处理
//        map4.put("biz_content", URLEncoder.encode(bizContent, "UTF-8"));
//
//        Map par = AlipayCore.paraFilter(map4); //除去数组中的空值和签名参数
//        String json4 = AlipayCore.createLinkString(map4);   //拼接后的字符串
//        json4 = json4 + "&sign=" + URLEncoder.encode(rsaSign, "UTF-8");
//        
//        String credential = json4;
		
        /*//参数进行处理拼装
		String content = createLinkString(paramsMap);
		
		//对原始字符串进行RSA签名
		String sign = AlipaySignature.rsaSign(content, appsRequest.getPrivateKey(), CHARSET_UTF8, appsRequest.getSignType());
		String credential = getEncodeForParams(content+"&sign="+sign);*/
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
	
	public static void main(String[] args) throws Exception {
		String out_trade_no = "20150320010101001";
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("out_trade_no", out_trade_no);
//		System.out.println(JSONObject.toJSONString(map));
		
		Map<String, Object> sParaTemp = new HashMap<String, Object>();
//		sParaTemp.put("service", ALIPAY_PC_SERVICE);
//        sParaTemp.put("partner","2000001");
//        sParaTemp.put("seller_id", "10000001");//一般情况下收款账号就是签约账号
//        sParaTemp.put("_input_charset", CHARSET_UTF8);
//		sParaTemp.put("payment_type", PAYMENT_TYPE);
//		sParaTemp.put("notify_url", SPAYX_NOTIFY_URL);//支付宝异步通知地址，SPAY++的异步接收地址
//		sParaTemp.put("return_url", SPAYX_NOTIFY_URL);//商户同步接收地址，发送到SPAY++进行封装
////		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);//如果已申请开通防钓鱼时间戳验证，则此字段必填
//		sParaTemp.put("exter_invoke_ip", "127.0.0.1");
//		sParaTemp.put("out_trade_no", "ord000002");//商户网站唯一订单号
//		sParaTemp.put("subject", "测试支付凭据");
//		sParaTemp.put("total_fee", "0.01");
//		sParaTemp.put("body", "这是厕所支付凭据");
		
		String app_id = "2015052600090779";
		String method = "alipay.trade.app.pay";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product_code", "张三");
		map.put("charset", CHARSET_UTF8);
		map.put("format", FORMAT_JSON);
		map.put("method", ALIPAY_METHOD_APPPAY);
		String biz_content = JSONObject.toJSONString(map);
//		String biz_content="{\"timeout_express\":\"30m\",\"out_trade_no\":\"IQJZSRC1YMQB5HU\"}";
		sParaTemp.put("app_id", baseEncode(app_id));
		sParaTemp.put("biz_content",baseEncode(biz_content));
		sParaTemp.put("method", baseEncode(method));
		sParaTemp.put("sign", baseEncode("dasdsafewwqeqweq"));
		sParaTemp.put("signType", baseEncode("RSA"));
		//待请求参数数组，返回给商户，商户再发送到支付宝进行支付
//        Map<String, String> sPara = buildRequestPara(sParaTemp, "RSA", "erfdswads");
		String content = getASCStringByMap(sParaTemp);
				
				
        System.out.println(content);
		
	}

}
