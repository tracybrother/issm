package com.tracybrother.channel;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.tracybrother.alipay.business.OutputBase;
import com.tracybrother.alipay.entity.AppsRequest;
import com.tracybrother.alipay.entity.MerchantRequest;
import com.tracybrother.alipay.impl.AliPayAppServiceImpl;
import com.tracybrother.alipay.impl.AliPayMerchantServiceImpl;
import com.tracybrother.alipay.impl.AliPayNativeServiceImpl;
import com.tracybrother.alipay.impl.AliPayPcServiceImpl;
import com.tracybrother.alipay.impl.AliPayWapServiceImpl;

public class TestChannel extends ChannelBase{
	//加密类型
	public static String SIGN_RSA = "RSA";
	//加密类型
	public static String SIGN_MD5 = "MD5";
	//支付宝RSA公钥
	public static String AliPublic_RSA = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	//支付宝MD5密钥
	public static String AliPublic_MD5 = "5khfvmbkcp8tjvg1ef3jpfmyxa579dcv";
	//商户RSA私钥
	public static String SpayPrivate_RSA = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAITS+EtPM67m3PUAkl9rw6OslSJm0GVmVXfGRZ3OC/3BYAEnmb3sH8oHFB0qsF/uCO5OGM4c0bA3uj5bGp6aYDsr9zfU+ytImq71Kc3ui7lAFmOT+cPDYt36Mj4cjHYrorBKjwK7NqM8jcmdYdgyWe1km54RxOjmEWMzUuBCNwM3AgMBAAECgYBfNFTyrgeOoz3XgxxntveZFvgim21ecRZr7rsxnGIt9fCZg0gIwPocNiytp06axCS0o1fWTQPj+l3NmkGfU2oLk+/w5mf52FfLv2LXrl8+SfOjhMq4m3Mi0ATWOP6fD7gq+SBgh/A3+Ww/neRkj9IaA6LkreZds2YkfjVkVteziQJBANi2AAXutM/TIJ3q5aZ90AaI33d7AORiy1C72KRKpYfBy+Tt4Q5emT9pOOmh2g+ezV8gnvHucmAITwvSFMZWk4MCQQCc55v+iyaX/IsD9y2NMJKxfCJmsnMTVzZYhnzv5pu/tt8EOve+T8Oc/3U9hL2z3pDAAhJBA+vRo94K0QRbEx89AkBnVmouagR4cZHFG+TEfov7AbulytbDEUUoS1rkBOBoGwZyNOizpHJpJXDJH6/O8Qe3i+OfM/gASRYAaf4UEeanAkBai94d2rd+5iJjO3VyESHWgshnc3utIZsfIx1eZDplPGcezdjCXs2IAHPr+HpxHnNyGr/l/RlNdb9hhpV46WQRAkBcOwy6EG2LU7TAEEU4U82Xmv79DRJYBG509VcguqM9n1E0ROVn2A+b65eMyzNGWIGf272sKlx4UVMWiSx2PcNh";
	//支付宝appid
	public static String AliAppId = "2017032306364738";
	
	//微信appid
	public static String WxAppId = "wx8bf5ca635f55f7c1";
	//微信商户号
	public static String MerchantId = "1431517802";
	//微信app应用密钥
	public static String WxAppSecret = "30e7e3d197278c45c1742e4eb21b2426";
	//微信api密钥
	public static String WxApiSecret = "JVnYPPT0daW2KZY1Duv06x71C6iJfth8";
	
	static Map<String,String> channelDetailMap = new HashMap<String,String>();
	
	/** @Description: TODO
	 * @author: 鲁炎
	 * @date: 2017年5月18日
	 */
	public TestChannel() {
		channelDetailMap.put("appId", "2016080700188738");
		channelDetailMap.put("privateKey", "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOnqP/WGjFo8Z0Tb"+
											"8sXg4QU6BZWbj8MIsE8k0vwbWWopzS23jluq+5dYiCg+cc4xLiyuaYW+DAGviUn4"+
											"JGc+N+v3ztmeCchh8FkKKpaMc/mvz8HDUbs5kaBODQiREKlbALR/YDG/eyUpzmhD"+
											"peDE7dJtJFyq5b6Rzz9YeABoMEVhAgMBAAECgYBq2e+nh3O7kJYX1WU70PLZQVzl"+
											"r27nR6PKM2cRdKjOnxOzy4KDC0LsKqvfvk6cQsj17iITzvGLH5VWLbzrUHeYOi0h"+
											"hQT+WOu+ljwm13wLFTlLOZxtBA+b807THsY4UFRNAN5cAOyQhe875IlQALP6eRAT"+
											"dd+gpBM3ddoKt5wjxQJBAPvUUaPAUsRVfYbGqQJs2L9szccjBw+W9/9mSQLAgNsh"+
											"MUa1K14hKwvX/vkKn2vz3A25GYZLFYbqyb1nzVp69QcCQQDtyfrCqArI/B2Sd+/v"+
											"x9ghi3CJE5UPC7eY/4qMxV638nwsdCcCc/O3ZoL7Qd0vqCACNInAXXrgbYyS6ww5"+
											"oQBXAkBE9ljBChrgTzUH4oFH+7zDdy1G+k2ltEcHtuAJk+P1njpbTaOprFWYcbcO"+
											"Rh1wN6CsVWMrBobHoqZKaxGuVHujAkBih9MUXbU6Wt2WF4SggtqX68ORyT92oJBP"+
											"IXQzqIT7C8tQUEDpz0opsJyNqFkSqkO5JjP+Nb34yOv65NKH/qK5AkAhFiehz1Cl"+
											"56ojFnMgsVsDQFaHO1/A2yQJHWcsAPzTb22YvXL2L0IclvJODQVMTFP2mtnJT6Z5"+
											"pDTMwYoO9+BR");
		channelDetailMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB");
	}
	
	@Test
	public void testWxPay(){
		
	}

	/**
	 * 支付宝手机网页支付测试
	 * @return
	 * @throws Exception
	 * <form name="punchout_form" method="post" 
	 * 			action="https://openapi.alipay.com/gateway.do?sign=N4aXADcWhKyXG1SdTh1p%2Fm93LPS%2FWPEAtLvg7fL6uI%2FWTyk7TcvonvFxwKW4ddtfgcAaI48wjO1%2FgrCRdRifhbcgI4UmW%2FVawkmF96ollJblVFYLDX1bCCp3zu59gclBL%2FZPMUGDoY7OM1r2DPARQymDnekyjxrcxY6h7PSGz9I%3D&timestamp=2017-03-22+14%3A36%3A07&sign_type=RSA&notify_url=http%3A%2F%2Fspayx.com%2Fpay&charset=utf-8&app_id=2088911849466231&method=alipay.trade.wap.pay&return_url=http%3A%2F%2Fspayx.com%2Fpay&version=1.0&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json">
			<input type="hidden" name="biz_content" value="{&quot;total_amount&quot;:&quot;1&quot;,&quot;product_code&quot;:&quot;QUICK_MSECURITY_PAY&quot;,&quot;out_trade_no&quot;:&quot;999888&quot;}">
			<input type="submit" value="立即支付" style="display:none" >
		</form>
		<script>document.forms[0].submit();</script>
	 * 
	 * 
	 * 
	 * 
	 */
	public static OutputBase testAliWapPay() throws Exception{
		AppsRequest appRequest = new AppsRequest();
		appRequest.setSubject("spayWapSubject");
		appRequest.setBody("spayWapTest");
		appRequest.setOutTradeNo("sapy000008");//商户订单号
		appRequest.setTotalAmount("0.02");//元
		appRequest.setReturnUrl("http://spayx.com/pay");
		
		AliPayWapServiceImpl aliPayWap = new AliPayWapServiceImpl();
		OutputBase out = aliPayWap.execute(appRequest);
		System.out.println("AliWap:"+out.toString());
		return out;
	}
	
	
	/**
	 * 支付宝PC网页支付测试
	 * @return
	 * @throws Exception
	 * 
	 * 支付凭据：
	 * https://mapi.alipay.com/gateway.do?_input_charset=utf-8&body=手机M8&notify_url=http://spayx.com/pay&out_trade_no=9998884&partner=2088911849466231&payment_type=1&return_url=http://spayx.com/pay&seller_id=2088911849466231&service=create_direct_pay_by_user&subject=M8&total_fee=1&sign_type=RSA&sign=mqJ/cF0V10q0b4OChu4bCRuLtavjvGhHj8jUce0bnJBSJPyAJP5IMvgWQhDiqT0RgR9dXejGs28DQRzneaVdPFPduAZEXMZ2zUIgR6KTVPCkgJ7GQyjioCeMGc75RWs7J+p2XTPu42NYmkun3+fmxZKiifh1A2rwEl202FZqX3I=
	 * 
	 */
	public static OutputBase testAliPcPay() throws Exception{
		AppsRequest appRequest = new AppsRequest();
		appRequest.setSubject("testAliPc");
		appRequest.setBody("testAliPcBody");
		appRequest.setOutTradeNo(System.currentTimeMillis()+"");//商户订单号
		appRequest.setTotalAmount("0.01");//元
		
		Map<String,String> extra = new HashMap<String,String>();
		extra.put("openId", "2088911849466231");
		extra.put("returnUrl", "http://www.baidu.com");
		appRequest.setExtra(extra);
		Map<String,String> channelDetailMap = new HashMap<String,String>();
		channelDetailMap.put("privateKey", "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJ2AE8smK8umty0a6RPWN5345Fh4OY6Znz0ZJ4UUXLeRd3FWItyc5So75euJn3hv9XT4IRB4Kp2riR0d+8p+TYWqGiWeAgecKkpfA2B7/5FpnsyeVwCvPJ3yFcJdGFsArHuz9xuT6Mqs4rWOJKh4MFa7iAG6EGoZkSFz00O9GY6RAgMBAAECgYEAkpKk2QPW8hleku1eR4WuEKeV6fdIqTqyBKwqKSEAAB2yKJlerCye4CUKHZ7f97dJmOdp7efG0SDX428J6jKMdP+uRwSVM413f/YR1pBw3YadyUbnNYFQm9wT93BP5Hk0S2B5uZqe8cVG5rk0OYU1oCCQIHNnpNHGjRNHsa9IpAECQQD42pZ1YBobfDf7Z6XgYFYPfxB4SgCwUgH7lB1RUoZ63lnxx1J/9DOIkTkQiG2w4+Jhb0Dbj30bWHWlq1Hr+UpxAkEAogXq3lZj/iLYniVvVJQ8GfLOa9fgOBwgFgnAfvGwlpd0N7Wh0oP45grvrO+wFw+4hF83Go9L91Co8YDd6sVWIQJBAOCphrgEwM0bgfboBerhThDDZ/0Q4jpMbxnPCADI/juizZTnm+WEc5nRcbEpWc4ejdvk8qrFie7MF7ovRtBAySECQANudKb41W1Qd54B1PXJnew+xDhsfgcyMUvGMRl/LOpDkuaLJj47JU5Z9PGIsgYLAzFqgtRBLK60Op/CxvBt9YECQQCr54v2R8/hfIeNwOaz2SkVL73Drjqk0Pn1o2FZ/396KXuR7OS2h2XZQ59DhkEdIPY4bCHtoM2hco8GUPkYH11v");
		appRequest.setChannelDetailMap(channelDetailMap);
		
		
		AliPayPcServiceImpl aliPay = new AliPayPcServiceImpl();
		OutputBase out = aliPay.execute(appRequest);
		System.out.println("AliPc:"+out.toString());
		return out;
	}
	
	
	/**
	 * App支付测试
	 * @return
	 * 
	 * app_id=2088911849466231&biz_content=%7B%22total_amount%22%3A%220.01%22%2C%22body%22%3A%22%E6%89%8B%E6%9C%BAM8%22%2C%22subject%22%3A%22M8%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22out_trade_no%22%3A%22app9999888%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fspayx.com%2Fpay&sign_type=RSA&timestamp=2017-03-22+16%3A37%3A00&version=1.0&sign=gMQHDNgYJA%2FCSzu96byX2faltSM9ryk8LZNCeN94htucobsnObogKsl6JdYJW%2BKbjUvXOFwG%2FyS5egejasQPRd9pOe1QA49RngPjfuC41ycF7NzPL8qZH5%2BNvkRxz6c3oxag7iK%2F6zq6dDgS0yCsx16JzixE8OKDgtjKjdn3iJ8
	 * @throws Exception
	 */
	public static OutputBase testAliAppPay() throws Exception{
		AppsRequest appRequest = new AppsRequest();
		appRequest.setSubject("SPAY003");
		appRequest.setBody("SPAY001Body");
		appRequest.setOutTradeNo(System.currentTimeMillis()+"");//商户订单号
		appRequest.setTotalAmount("0.01");//元
//		appRequest.setReturnUrl("http://spayx.com/pay");
//		appRequest.setClientIp("142.1.224.217");
		
		AliPayAppServiceImpl aliPay = new AliPayAppServiceImpl();
		OutputBase out = aliPay.execute(appRequest);
		System.out.println("AliPc:"+out.toString());
		return out;
	}
	
	public static OutputBase testAliNativePay() throws Exception{
		AppsRequest appRequest = getAliPayParams();
		
		AliPayNativeServiceImpl aliPay = new AliPayNativeServiceImpl();
		OutputBase out = aliPay.execute(appRequest);
		System.out.println("AliPayNative:"+out.toString());
		return out;
	}
	
	public static OutputBase testAliRefund() throws Exception{
		AppsRequest appRequest = new AppsRequest();
		appRequest.setOutTradeNo("ALTM201705041000071000");
		appRequest.setOutRefundNo("refund00005");
		appRequest.setRefundAmount("0.18");
		appRequest.setRefundReason("退款同意");
		Map<String,String> channelDetailMap = new HashMap<String,String>();
		channelDetailMap.put("appId", "2017032306364738");
		channelDetailMap.put("privateKey", "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAITS+EtPM67m3PUAkl9rw6OslSJm0GVmVXfGRZ3OC/3BYAEnmb3sH8oHFB0qsF/uCO5OGM4c0bA3uj5bGp6aYDsr9zfU+ytImq71Kc3ui7lAFmOT+cPDYt36Mj4cjHYrorBKjwK7NqM8jcmdYdgyWe1km54RxOjmEWMzUuBCNwM3AgMBAAECgYBfNFTyrgeOoz3XgxxntveZFvgim21ecRZr7rsxnGIt9fCZg0gIwPocNiytp06axCS0o1fWTQPj+l3NmkGfU2oLk+/w5mf52FfLv2LXrl8+SfOjhMq4m3Mi0ATWOP6fD7gq+SBgh/A3+Ww/neRkj9IaA6LkreZds2YkfjVkVteziQJBANi2AAXutM/TIJ3q5aZ90AaI33d7AORiy1C72KRKpYfBy+Tt4Q5emT9pOOmh2g+ezV8gnvHucmAITwvSFMZWk4MCQQCc55v+iyaX/IsD9y2NMJKxfCJmsnMTVzZYhnzv5pu/tt8EOve+T8Oc/3U9hL2z3pDAAhJBA+vRo94K0QRbEx89AkBnVmouagR4cZHFG+TEfov7AbulytbDEUUoS1rkBOBoGwZyNOizpHJpJXDJH6/O8Qe3i+OfM/gASRYAaf4UEeanAkBai94d2rd+5iJjO3VyESHWgshnc3utIZsfIx1eZDplPGcezdjCXs2IAHPr+HpxHnNyGr/l/RlNdb9hhpV46WQRAkBcOwy6EG2LU7TAEEU4U82Xmv79DRJYBG509VcguqM9n1E0ROVn2A+b65eMyzNGWIGf272sKlx4UVMWiSx2PcNh");
		channelDetailMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB");
		appRequest.setChannelDetailMap(channelDetailMap);
		
		AliPayNativeServiceImpl aliPay = new AliPayNativeServiceImpl();
		OutputBase out = aliPay.baseRefund(appRequest);
		
		System.out.println("退款返回:"+out.toString());
		
		return out;
	}
	
	
	public static AppsRequest getAliPayParams(){
		AppsRequest appRequest = new AppsRequest();
		appRequest.setSubject("TestSubject");
		appRequest.setBody("TestBody");
		appRequest.setOutTradeNo("99911111");//商户订单号
		appRequest.setTotalAmount("0.01");//元
		appRequest.setReturnUrl("http://spayx.com/pay");
		appRequest.setClientIp("142.1.224.217");
		return appRequest;
	}
	
	public static AppsRequest getWxPayParams(){
		AppsRequest appRequest = new AppsRequest();
		appRequest.setBody("WxTest");
		appRequest.setOutTradeNo("wx"+System.currentTimeMillis());//商户订单号
		appRequest.setTotalAmount("10000");
		appRequest.setClientIp("142.1.224.217");
		return appRequest;
	}
	
	
	public static OutputBase testWxNativePay() throws Exception{
//		AppsRequest appRequest = getWxPayParams();
//		
//		Map<String,String> extra = new HashMap<String,String>();
//		extra.put("authCode", "130058303512160321");
//		appRequest.setExtra(extra);
//		Map<String,String> channelDetailMap = new HashMap<String,String>();
//		channelDetailMap.put("appId", "wx0e6efea9059beb26");
//		channelDetailMap.put("merchantNo", "1448390602");
//		channelDetailMap.put("apiKey", "9ea5a28588cd44e78b054f9f907e4663");
//		appRequest.setChannelDetailMap(channelDetailMap);
//		
		
//		WxPayNativeServiceImpl wxPay = new WxPayNativeServiceImpl();
//		OutputBase out = wxPay.execute(appRequest);
//		System.out.println("wxPay:"+out.toString());
//		return out;
		return null;
	}
	
	
	public static OutputBase testHfNativePay() throws Exception{
//		AppsRequest appRequest = new AppsRequest();
//		appRequest.setSubject("你的益达");
//		appRequest.setBody("是你的益达");
//		appRequest.setOutTradeNo("hf"+System.currentTimeMillis());//商户订单号
//		appRequest.setTotalAmount("0.02");
//		appRequest.setClientIp("142.1.224.217");
//		Map<String,String> extra = new HashMap<String,String>();
//		extra.put("channelCode", "ALIPAY");
//		extra.put("authCode", "288370901832630571");
//		appRequest.setExtra(extra);
//		Map<String,String> channelDetailMap = new HashMap<String,String>();
//		channelDetailMap.put("account", "15314129520");
//		channelDetailMap.put("privateKey", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5KAbxKSIYsxMTuppJGyh57GbJIPn3ZNBv2Axc6h3BP+OX/FFM+T8fjv1+OZwwbztlrPVEnEn+0KwIFc3naTm666Pf7I18NjCsglytEnXGpnSo6pNGC3BFxFLNTeOO58/Ktwu/NiReQjdrtxGUGx9OHcSVx6tRQCBoLRtN5eu3xjy9Co6QRT0yaR55umBqJoTwcVCIQILESpebJfM/kdwd+8SbDMDm09r9Jp5X0Xj8n23DZsISO6m2XUtf/yKDwwU7DtmpV0Vp9aPspmkBI8bozRCEZm0YaG5g/LXPiFMLw1Wjehc46Wt7poxmHd4vCVnVazDRuH3fU3/qj5YvjuFBAgMBAAECggEAS7XQUOBO7uaakGCnIemW/XPq+6ceT0jL6k/fjka08AZd3BHydJUeeJsRkIGiGjQBh6vXhryZjTZqgcvYrB1c28/q7G9f00CdWozbtHFt81KxNTjmC6g1unFyojyNJiwnzW5XwVI0QM62trbe2z7T6C9H6S8CIHEmTzLSjWHMpxIWBjLcumpTjkvx4ZLgvRPgSqsQ03NeycuW3Ore15J4K6Lcy3o3hFTjkAeAo2p5eaFSq5C59pfD81ozumzZ4bEHKxGcMjCmJ6WoQFgZn46f1mLRa8HaSFQVP4VQwpgve3ZIZhtwVcg06/s8l56OK1zlnkJXyh5GYCAaRdXWNg84GQKBgQD/XRo+a9Qb7c7qWONuLlgbtJ+jgWVdkpi3XVMNYI67WZ5iajTTLULFyvdwbMaZecwjJHGaBcpiA1Af+OSdgzA8bJBAWnxKnOHFYaP7CIdcOsgiSlSXjOXJ3fKhkBygKgQ+/m8JTz/wYSdJNCN0HjCxgY63OsITw8k8lUyH8zoMgwKBgQC5niOagmntY7YnY6VOXNqHvZes2ZK7TE5FRMfqEfln0iY9Y7tf+RIk6VLEbPiZbfkjLYgavQCz8Vq1x+N49dilf3vzPc4KrN3qSwohKy6VKWE7qFY2vCaEm9pBG/hf8ktdhhy+AAyJy003+M1nH9CuVbvmIDzkNac+LNBOG5H36wKBgQDnDLUvH18WOhAKh4yX54S+5KnARdaSWI6m8eCwbNoX95lP0GqlXAFbdy504Wa7/LDQZywGQREBp5+6nfcz8TwWn9/Q6k4lMFcywQqemvPmZiUoqXpqeQh8or3iosLTw9B9sraChNsEQtNJ78vYR2ylXuTjMl3hhjcxAqDxLQgrwwKBgBKiKGrTWrTlBJ0xqJ+UPK/RvY8xjERXJahYfkUmJsZgV0xkXaQFyeeAsLldQxuL6a25XWdpv89QlE2ZUmGLNINwp83ET5HMxr5FaC/qONPqgoNNo9H801UOnDtbBnUDSsOJugrs0FVo3kiXJm+KxxUpMCKq69Hw/OgF8adVpzKfAoGAVlF4xtkXiorVI/MCyR6FUagbfIPhMqU7jbpH8UIFEE/gTnc/TbrI1E18GkbBL/Jf3zbI8E1LYHME91UPP2O8MQGCmmeNxudqJ6sepdut0422U6N7fWWNTMMcI6YyV6OP98gYmQX16U7T/OV+NTUc6jnWPcwWLPtqB8dIZhLIXlM=");
//		appRequest.setChannelDetailMap(channelDetailMap);
//		
//		
//		HfPayNativeServiceImpl hfPay = new HfPayNativeServiceImpl();
//		OutputBase out = hfPay.execute(appRequest);
//		System.out.println("hfPay:"+out.toString());
//		return out;
		return null;
	}
	
	
	public static OutputBase testHfPayNoAmtScan() throws Exception{
//		AppsRequest appRequest = new AppsRequest();
//		appRequest.setSubject("易诚条码支付");
//		appRequest.setBody("易诚条码支付详情");
//		appRequest.setOutTradeNo("hf"+System.currentTimeMillis());//商户订单号
//		appRequest.setTotalAmount("0.01");
//		appRequest.setClientIp("142.1.224.217");
////		Map<String,String> extra = new HashMap<String,String>();
////		extra.put("channelCode", "WXPAY");
////		extra.put("authCode", "131944000213894504");
////		appRequest.setExtra(extra);
//		Map<String,String> channelDetailMap = new HashMap<String,String>();
//		channelDetailMap.put("account", "15314129520");
//		channelDetailMap.put("password", "123456");
//		channelDetailMap.put("privateKey", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5KAbxKSIYsxMTuppJGyh57GbJIPn3ZNBv2Axc6h3BP+OX/FFM+T8fjv1+OZwwbztlrPVEnEn+0KwIFc3naTm666Pf7I18NjCsglytEnXGpnSo6pNGC3BFxFLNTeOO58/Ktwu/NiReQjdrtxGUGx9OHcSVx6tRQCBoLRtN5eu3xjy9Co6QRT0yaR55umBqJoTwcVCIQILESpebJfM/kdwd+8SbDMDm09r9Jp5X0Xj8n23DZsISO6m2XUtf/yKDwwU7DtmpV0Vp9aPspmkBI8bozRCEZm0YaG5g/LXPiFMLw1Wjehc46Wt7poxmHd4vCVnVazDRuH3fU3/qj5YvjuFBAgMBAAECggEAS7XQUOBO7uaakGCnIemW/XPq+6ceT0jL6k/fjka08AZd3BHydJUeeJsRkIGiGjQBh6vXhryZjTZqgcvYrB1c28/q7G9f00CdWozbtHFt81KxNTjmC6g1unFyojyNJiwnzW5XwVI0QM62trbe2z7T6C9H6S8CIHEmTzLSjWHMpxIWBjLcumpTjkvx4ZLgvRPgSqsQ03NeycuW3Ore15J4K6Lcy3o3hFTjkAeAo2p5eaFSq5C59pfD81ozumzZ4bEHKxGcMjCmJ6WoQFgZn46f1mLRa8HaSFQVP4VQwpgve3ZIZhtwVcg06/s8l56OK1zlnkJXyh5GYCAaRdXWNg84GQKBgQD/XRo+a9Qb7c7qWONuLlgbtJ+jgWVdkpi3XVMNYI67WZ5iajTTLULFyvdwbMaZecwjJHGaBcpiA1Af+OSdgzA8bJBAWnxKnOHFYaP7CIdcOsgiSlSXjOXJ3fKhkBygKgQ+/m8JTz/wYSdJNCN0HjCxgY63OsITw8k8lUyH8zoMgwKBgQC5niOagmntY7YnY6VOXNqHvZes2ZK7TE5FRMfqEfln0iY9Y7tf+RIk6VLEbPiZbfkjLYgavQCz8Vq1x+N49dilf3vzPc4KrN3qSwohKy6VKWE7qFY2vCaEm9pBG/hf8ktdhhy+AAyJy003+M1nH9CuVbvmIDzkNac+LNBOG5H36wKBgQDnDLUvH18WOhAKh4yX54S+5KnARdaSWI6m8eCwbNoX95lP0GqlXAFbdy504Wa7/LDQZywGQREBp5+6nfcz8TwWn9/Q6k4lMFcywQqemvPmZiUoqXpqeQh8or3iosLTw9B9sraChNsEQtNJ78vYR2ylXuTjMl3hhjcxAqDxLQgrwwKBgBKiKGrTWrTlBJ0xqJ+UPK/RvY8xjERXJahYfkUmJsZgV0xkXaQFyeeAsLldQxuL6a25XWdpv89QlE2ZUmGLNINwp83ET5HMxr5FaC/qONPqgoNNo9H801UOnDtbBnUDSsOJugrs0FVo3kiXJm+KxxUpMCKq69Hw/OgF8adVpzKfAoGAVlF4xtkXiorVI/MCyR6FUagbfIPhMqU7jbpH8UIFEE/gTnc/TbrI1E18GkbBL/Jf3zbI8E1LYHME91UPP2O8MQGCmmeNxudqJ6sepdut0422U6N7fWWNTMMcI6YyV6OP98gYmQX16U7T/OV+NTUc6jnWPcwWLPtqB8dIZhLIXlM=");
//		appRequest.setChannelDetailMap(channelDetailMap);
//		
//		
//		HfPayNoAmtScanServiceImpl hfPay = new HfPayNoAmtScanServiceImpl();
//		OutputBase out = hfPay.execute(appRequest);
//		System.out.println("hfPay:"+out.toString());
//		return out;
		return null;
	}
	
	
	public static OutputBase testHfPayScan() throws Exception{
//		AppsRequest appRequest = new AppsRequest();
//		appRequest.setSubject("易诚扫码支付");
//		appRequest.setBody("易诚扫码支付详情");
//		appRequest.setOutTradeNo("hf"+System.currentTimeMillis());//商户订单号
//		appRequest.setTotalAmount("0.01");
//		appRequest.setClientIp("142.1.224.217");
//		appRequest.setDescription("易诚扫码");
//		Map<String,String> extra = new HashMap<String,String>();
//		extra.put("channelCode", "ALIPAY");
//		appRequest.setExtra(extra);
//		Map<String,String> channelDetailMap = new HashMap<String,String>();
//		channelDetailMap.put("account", "15314129520");
//		channelDetailMap.put("privateKey", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5KAbxKSIYsxMTuppJGyh57GbJIPn3ZNBv2Axc6h3BP+OX/FFM+T8fjv1+OZwwbztlrPVEnEn+0KwIFc3naTm666Pf7I18NjCsglytEnXGpnSo6pNGC3BFxFLNTeOO58/Ktwu/NiReQjdrtxGUGx9OHcSVx6tRQCBoLRtN5eu3xjy9Co6QRT0yaR55umBqJoTwcVCIQILESpebJfM/kdwd+8SbDMDm09r9Jp5X0Xj8n23DZsISO6m2XUtf/yKDwwU7DtmpV0Vp9aPspmkBI8bozRCEZm0YaG5g/LXPiFMLw1Wjehc46Wt7poxmHd4vCVnVazDRuH3fU3/qj5YvjuFBAgMBAAECggEAS7XQUOBO7uaakGCnIemW/XPq+6ceT0jL6k/fjka08AZd3BHydJUeeJsRkIGiGjQBh6vXhryZjTZqgcvYrB1c28/q7G9f00CdWozbtHFt81KxNTjmC6g1unFyojyNJiwnzW5XwVI0QM62trbe2z7T6C9H6S8CIHEmTzLSjWHMpxIWBjLcumpTjkvx4ZLgvRPgSqsQ03NeycuW3Ore15J4K6Lcy3o3hFTjkAeAo2p5eaFSq5C59pfD81ozumzZ4bEHKxGcMjCmJ6WoQFgZn46f1mLRa8HaSFQVP4VQwpgve3ZIZhtwVcg06/s8l56OK1zlnkJXyh5GYCAaRdXWNg84GQKBgQD/XRo+a9Qb7c7qWONuLlgbtJ+jgWVdkpi3XVMNYI67WZ5iajTTLULFyvdwbMaZecwjJHGaBcpiA1Af+OSdgzA8bJBAWnxKnOHFYaP7CIdcOsgiSlSXjOXJ3fKhkBygKgQ+/m8JTz/wYSdJNCN0HjCxgY63OsITw8k8lUyH8zoMgwKBgQC5niOagmntY7YnY6VOXNqHvZes2ZK7TE5FRMfqEfln0iY9Y7tf+RIk6VLEbPiZbfkjLYgavQCz8Vq1x+N49dilf3vzPc4KrN3qSwohKy6VKWE7qFY2vCaEm9pBG/hf8ktdhhy+AAyJy003+M1nH9CuVbvmIDzkNac+LNBOG5H36wKBgQDnDLUvH18WOhAKh4yX54S+5KnARdaSWI6m8eCwbNoX95lP0GqlXAFbdy504Wa7/LDQZywGQREBp5+6nfcz8TwWn9/Q6k4lMFcywQqemvPmZiUoqXpqeQh8or3iosLTw9B9sraChNsEQtNJ78vYR2ylXuTjMl3hhjcxAqDxLQgrwwKBgBKiKGrTWrTlBJ0xqJ+UPK/RvY8xjERXJahYfkUmJsZgV0xkXaQFyeeAsLldQxuL6a25XWdpv89QlE2ZUmGLNINwp83ET5HMxr5FaC/qONPqgoNNo9H801UOnDtbBnUDSsOJugrs0FVo3kiXJm+KxxUpMCKq69Hw/OgF8adVpzKfAoGAVlF4xtkXiorVI/MCyR6FUagbfIPhMqU7jbpH8UIFEE/gTnc/TbrI1E18GkbBL/Jf3zbI8E1LYHME91UPP2O8MQGCmmeNxudqJ6sepdut0422U6N7fWWNTMMcI6YyV6OP98gYmQX16U7T/OV+NTUc6jnWPcwWLPtqB8dIZhLIXlM=");
//		appRequest.setChannelDetailMap(channelDetailMap);
//		
//		
//		HfPayScanServiceImpl hfPay = new HfPayScanServiceImpl();
//		OutputBase out = hfPay.execute(appRequest);
//		System.out.println("hfPay:"+out.toString());
//		return out;
		return null;
	}
	
	
	public static OutputBase testWxAppPay() throws Exception{
//		AppsRequest appRequest = getWxPayParams();
//		WxPayAppServiceImpl wxPay = new WxPayAppServiceImpl();
//		OutputBase out = wxPay.execute(appRequest);
//		System.out.println("WxApp:"+out.toString());
//		return out;
		return null;
	}
	
	/**
	 * @Title: testAliMerchantCreate
	 * @Description: 测试阿里间接商户创建
	 * @author: weiss
	 * @date: 2017年5月19日
	 */
	public static void testAliMerchantCreate() throws Exception{
		
		
		MerchantRequest mer = new MerchantRequest();
//		mer.setMerchantId(RandomUtil.getRandom(11)); // 受理商户编号，由受理机构定义，需要保证在受理机构下唯一 2088621876520052
		mer.setMerchantName("昆明鑫之道汽车维修有限责任公司"); // 受理商户名称
		mer.setMerchantShortname("昆明鑫之道汽车维修有限责任公司"); // 受理商户简称（会显示在支付宝付款成功页
		mer.setServicePhone("15925163537"); // 受理商户客服电话（用于客诉咨询）
		mer.setCategoryId("2015091000052157"); // 受理商户经营类目（简单类目，对应不同风控模型，填写最贴切商户的行业）
//		mer.setContactName("安凌鹏"); // 负责人姓名
//		mer.setContactPhone("13393333333"); // 负责人电话

		// 支付宝M3独有
		mer.setSource("2088102170359392"); // 受理商户来源标识，填写商户所属的银行或ISV的企业支付宝pid（即商户邀请人pid）
//		mer.setBusinessLicense("3123123123123"); // 受理商户营业执照编号 必填
//		mer.setBusinessLicenseType("NATIONAL_LEGAL"); // 受理商户营业执照类型 必填
		// mer.setcontact_info; //受理商户负责人（如法人）信息（json数组结构体，含name, type,
		// id_card_no字段，mobile和phone二选一必填） 必填 必填 必填
//		mer.setContactType("LEGAL_PERSON"); // 受理商户负责人类型（什么类型）
//		mer.setContactIdCardNo("23123123123123123123"); // 受理商户负责人身份证号
		// mer.setaddress_info; //受理商户地址信息（json数组结构体，含province_code,
		// city_code, district_code, address字段） 必填 必填
//		mer.setProvinceCode("530000");
//		mer.setCityCode("530100");
//		mer.setDistrictCode("530102");
//		mer.setAddress("云南省昆明市晋宁县昆阳街道办事处东凤路洗马桥");
		// mer.setbankcard_info; //受理商户对应银行开立的结算卡信息（json数组结构体，含card_no,
		// card_name字段） 必填
//		mer.setCardNo("954011010000036321");
//		mer.setCardName("中国很行");
		channelDetailMap.put("appId", "2016080700188738");
		channelDetailMap.put("privateKey", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALhCeHAx7Ma98C6QN1uQcpPXsbSddvFIPK53wTMjRQIluq5kkeVfh0iiIvVWShQfnNQqTB1O1uFbFWrG3uM2FQCvsf1Lmz6snWu0MDI/ZRJOMfkaaKOW2YlfM7szskHbzPnIHVAg4KASowh4LD7ZXJcAFYl5mXxUl8WkVZ/eDcdRAgMBAAECgYAs97vg1a5NSmllU7jO1c/AmPE7fPpcJB77DSrPSho7Te3jRZP9gHTJmxgg+7AUfSqO9Muik20JjmsJpgMbPEWffPaBgKvnJQsoThV7AWB5lR7IVu3FUk/hJI2NBHwTjqu1lPgOrJdARjUta1GXUn/XBfQM+BRSJ+LK8bzQxZQuKQJBAOmpeplwi20/VO/FoLPP+veKLkAOfmlV+3VijmLapz5BQ1/qUtDjdVipXaXPxmH4ofmDhbwwqd2Znu2hHfM1jp8CQQDJ3/Ghd07Pi3Xa5GOHv8/UhVL4/GDIZpebJwnDF29rVKXYkSyuCpkczhrbFZXyrp3kbaqNZx8pimHtat8pehQPAkAzkp2lukp73Wug1Gzo4zHQayZUKx7Jnxm2Sf+FYB9jxUHarLyEsH+ZByDNVTPDMf6GNPpSlCcZ6ytB5brXkG2fAkEAx+Aq5auzJFr4fZnT0fxgUhMPa+a2DLVHNh8Y00jI0IQnEJ3slwV3Q8XtYS2mIXoyapEKZKbNe9u/ycoe2no8kQJABFwJ3BRqWQh25aTV1ZgQMKOab39HSHhoRnRyp6SzQEcU9YaYs6ebOg+MQdouNj9brGZ49avYp8v5l+3otce1TQ==");
		channelDetailMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB");
		System.out.println("--------------执行开始-------------");
		mer.setChannelDetailMap(channelDetailMap);
		AliPayMerchantServiceImpl service = new AliPayMerchantServiceImpl();
		OutputBase ob = service.merchantCreate(mer);
		System.out.println(ob);
		System.out.println(ob.getMap().get("subMerchantId"));
	}
	/**
	 * @Title: testAliMerchantQuery
	 * @Description: 测试阿里间接商户查询
	 * @author: weiss
	 * @date: 2017年5月19日
	 */
	public static void testAliMerchantQuery() throws Exception{
		MerchantRequest mer = new MerchantRequest();
		mer.setSubMerchantId("2088621876520052");
		String res = mer.getAliQueryJSON();
		channelDetailMap.put("appId", "2016080700188738");
		channelDetailMap.put("privateKey", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALhCeHAx7Ma98C6QN1uQcpPXsbSddvFIPK53wTMjRQIluq5kkeVfh0iiIvVWShQfnNQqTB1O1uFbFWrG3uM2FQCvsf1Lmz6snWu0MDI/ZRJOMfkaaKOW2YlfM7szskHbzPnIHVAg4KASowh4LD7ZXJcAFYl5mXxUl8WkVZ/eDcdRAgMBAAECgYAs97vg1a5NSmllU7jO1c/AmPE7fPpcJB77DSrPSho7Te3jRZP9gHTJmxgg+7AUfSqO9Muik20JjmsJpgMbPEWffPaBgKvnJQsoThV7AWB5lR7IVu3FUk/hJI2NBHwTjqu1lPgOrJdARjUta1GXUn/XBfQM+BRSJ+LK8bzQxZQuKQJBAOmpeplwi20/VO/FoLPP+veKLkAOfmlV+3VijmLapz5BQ1/qUtDjdVipXaXPxmH4ofmDhbwwqd2Znu2hHfM1jp8CQQDJ3/Ghd07Pi3Xa5GOHv8/UhVL4/GDIZpebJwnDF29rVKXYkSyuCpkczhrbFZXyrp3kbaqNZx8pimHtat8pehQPAkAzkp2lukp73Wug1Gzo4zHQayZUKx7Jnxm2Sf+FYB9jxUHarLyEsH+ZByDNVTPDMf6GNPpSlCcZ6ytB5brXkG2fAkEAx+Aq5auzJFr4fZnT0fxgUhMPa+a2DLVHNh8Y00jI0IQnEJ3slwV3Q8XtYS2mIXoyapEKZKbNe9u/ycoe2no8kQJABFwJ3BRqWQh25aTV1ZgQMKOab39HSHhoRnRyp6SzQEcU9YaYs6ebOg+MQdouNj9brGZ49avYp8v5l+3otce1TQ==");
		channelDetailMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB");
System.out.println("--------------执行开始-------------");
		mer.setChannelDetailMap(channelDetailMap);
		
		AliPayMerchantServiceImpl service = new AliPayMerchantServiceImpl();
		OutputBase ob = service.merchantQuery(mer);
		System.out.println(ob);
		
	}
	/**
	 * @Title: testAliMerchantCreate
	 * @Description: 测试阿里间接商户创建
	 * @author: weiss
	 * @date: 2017年5月19日
	 */
	public static void testAliMerchantUpdate() throws Exception{
		
		
		MerchantRequest mer = new MerchantRequest();
		mer.setSubMerchantId("2088621876520052");
//		mer.setMerchantId(RandomUtil.getRandom(11)); // 受理商户编号，由受理机构定义，需要保证在受理机构下唯一 2088621876520052
		mer.setMerchantName("家家乐小超市"); // 受理商户名称
		mer.setMerchantShortname("家家乐"); // 受理商户简称（会显示在支付宝付款成功页
		mer.setServicePhone("13383313311"); // 受理商户客服电话（用于客诉咨询）
		mer.setCategoryId("2015091000052157"); // 受理商户经营类目（简单类目，对应不同风控模型，填写最贴切商户的行业）
		mer.setContactName("苏鹏浩"); // 负责人姓名
		mer.setContactPhone("13393333333"); // 负责人电话

		// 支付宝M3独有
		mer.setSource("2088102170359392"); // 受理商户来源标识，填写商户所属的银行或ISV的企业支付宝pid（即商户邀请人pid）
		mer.setBusinessLicense("3123123123133"); // 受理商户营业执照编号 必填
		mer.setBusinessLicenseType("NATIONAL_LEGAL"); // 受理商户营业执照类型 必填
		// mer.setcontact_info; //受理商户负责人（如法人）信息（json数组结构体，含name, type,
		// id_card_no字段，mobile和phone二选一必填） 必填 必填 必填
		mer.setContactType("LEGAL_PERSON"); // 受理商户负责人类型（什么类型）
		mer.setContactIdCardNo("23123123123123123133"); // 受理商户负责人身份证号
		// mer.setaddress_info; //受理商户地址信息（json数组结构体，含province_code,
		// city_code, district_code, address字段） 必填 必填
		mer.setProvinceCode("370000");
		mer.setCityCode("371000");
		mer.setDistrictCode("371002");
		mer.setAddress("中华大街");
		// mer.setbankcard_info; //受理商户对应银行开立的结算卡信息（json数组结构体，含card_no,
		// card_name字段） 必填
		mer.setCardNo("123123123123123");
		mer.setCardName("中国很行");
		channelDetailMap.put("appId", "2016080700188738");
		channelDetailMap.put("privateKey", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALhCeHAx7Ma98C6QN1uQcpPXsbSddvFIPK53wTMjRQIluq5kkeVfh0iiIvVWShQfnNQqTB1O1uFbFWrG3uM2FQCvsf1Lmz6snWu0MDI/ZRJOMfkaaKOW2YlfM7szskHbzPnIHVAg4KASowh4LD7ZXJcAFYl5mXxUl8WkVZ/eDcdRAgMBAAECgYAs97vg1a5NSmllU7jO1c/AmPE7fPpcJB77DSrPSho7Te3jRZP9gHTJmxgg+7AUfSqO9Muik20JjmsJpgMbPEWffPaBgKvnJQsoThV7AWB5lR7IVu3FUk/hJI2NBHwTjqu1lPgOrJdARjUta1GXUn/XBfQM+BRSJ+LK8bzQxZQuKQJBAOmpeplwi20/VO/FoLPP+veKLkAOfmlV+3VijmLapz5BQ1/qUtDjdVipXaXPxmH4ofmDhbwwqd2Znu2hHfM1jp8CQQDJ3/Ghd07Pi3Xa5GOHv8/UhVL4/GDIZpebJwnDF29rVKXYkSyuCpkczhrbFZXyrp3kbaqNZx8pimHtat8pehQPAkAzkp2lukp73Wug1Gzo4zHQayZUKx7Jnxm2Sf+FYB9jxUHarLyEsH+ZByDNVTPDMf6GNPpSlCcZ6ytB5brXkG2fAkEAx+Aq5auzJFr4fZnT0fxgUhMPa+a2DLVHNh8Y00jI0IQnEJ3slwV3Q8XtYS2mIXoyapEKZKbNe9u/ycoe2no8kQJABFwJ3BRqWQh25aTV1ZgQMKOab39HSHhoRnRyp6SzQEcU9YaYs6ebOg+MQdouNj9brGZ49avYp8v5l+3otce1TQ==");
		channelDetailMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB");
System.out.println("--------------执行开始-------------");
		mer.setChannelDetailMap(channelDetailMap);
		AliPayMerchantServiceImpl service = new AliPayMerchantServiceImpl();
		OutputBase ob = service.merchantUpdate(mer);
		System.out.println(ob);
		System.out.println();
	}
	
	
	
	/**
	 * @Title: testAliMerchantCreate
	 * @Description: 测试阿里间接商户创建
	 * @author: weiss
	 * @date: 2017年5月19日
	 */
	public static void testAliBillDown() throws Exception{
		
//		
//		channelDetailMap.put("appId", "2016080700188738");
//		channelDetailMap.put("privateKey", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALhCeHAx7Ma98C6QN1uQcpPXsbSddvFIPK53wTMjRQIluq5kkeVfh0iiIvVWShQfnNQqTB1O1uFbFWrG3uM2FQCvsf1Lmz6snWu0MDI/ZRJOMfkaaKOW2YlfM7szskHbzPnIHVAg4KASowh4LD7ZXJcAFYl5mXxUl8WkVZ/eDcdRAgMBAAECgYAs97vg1a5NSmllU7jO1c/AmPE7fPpcJB77DSrPSho7Te3jRZP9gHTJmxgg+7AUfSqO9Muik20JjmsJpgMbPEWffPaBgKvnJQsoThV7AWB5lR7IVu3FUk/hJI2NBHwTjqu1lPgOrJdARjUta1GXUn/XBfQM+BRSJ+LK8bzQxZQuKQJBAOmpeplwi20/VO/FoLPP+veKLkAOfmlV+3VijmLapz5BQ1/qUtDjdVipXaXPxmH4ofmDhbwwqd2Znu2hHfM1jp8CQQDJ3/Ghd07Pi3Xa5GOHv8/UhVL4/GDIZpebJwnDF29rVKXYkSyuCpkczhrbFZXyrp3kbaqNZx8pimHtat8pehQPAkAzkp2lukp73Wug1Gzo4zHQayZUKx7Jnxm2Sf+FYB9jxUHarLyEsH+ZByDNVTPDMf6GNPpSlCcZ6ytB5brXkG2fAkEAx+Aq5auzJFr4fZnT0fxgUhMPa+a2DLVHNh8Y00jI0IQnEJ3slwV3Q8XtYS2mIXoyapEKZKbNe9u/ycoe2no8kQJABFwJ3BRqWQh25aTV1ZgQMKOab39HSHhoRnRyp6SzQEcU9YaYs6ebOg+MQdouNj9brGZ49avYp8v5l+3otce1TQ==");
//		channelDetailMap.put("publicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB");
//System.out.println("--------------执行开始-------------");
//		IndirectCodePay service = new IndirectCodePay();
//		BillDownloadRequest bean = new BillDownloadRequest();
//		bean.setChannelDetailMap(channelDetailMap);
//		bean.setBillDate("20170601");
//		OutputBase ob = service.billDownload(bean);
//		System.out.println(ob);
		
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
//		testAliWapPay();
//		testWxNativePay();
//		testAliAppPay();
//		testAliNativePay();
		
//		System.out.println(DateUtil.getStringDate());
//		testWxAppPay();
//		testHfNativePay();
//		testHfPayNoAmtScan();
//		testHfPayScan();
//		testAliRefund();
		
		
		testAliMerchantCreate();
//		testAliMerchantQuery();
//		testAliMerchantUpdate();
//		System.out.println(properties.getProperty("name"));
//		testAliBillDown();
//			Map<String, Object> map = new HashMap<String, Object>();
//			Map<String, String> map1 = new HashMap<String, String>();
//			map1.put("222", "2222");
//			map.put("out_trade_no", "111");
//			map.put("1111111", map1);
//			map.put("sub_merchant", "{" + "\"merchant_id\":\""+"222"+"\"" +"}");
//			
//			
//			String bizContent = JSONObject.toJSONString(map);
//			String s = new JSONProcessor().format(map);
//			System.out.println(s);
	
	}
}
