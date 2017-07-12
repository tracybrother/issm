package com.tracybrother.channel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <pre>通道基础类</pre>  
 *     
 * @author luyan
 * @date:  2017年7月12日
 */
public abstract class ChannelBase {
	protected static final String FORMAT_JSON = "json";
	protected static final String SIGN_TYPE_RSA = "RSA";
	protected static final String SIGN_TYPE_RSA2 = "RSA2";
	protected static final String SIGN_TYPE_MD5 = "MD5";
	protected static final String CHARSET_UTF8 = "utf-8";
	protected static final String SPAYX_RETURN_URL = "http://spayx.com/pay";
	/** 通道状态 * 0：未知 */
	protected static final String CHANNEL_CODE_UNKNOWN = "0";
	/** 通道状态 * 1：成功*/
	protected static final String CHANNEL_CODE_SUCC = "1";
	/** 通道状态 * 2:失败 */
	protected static final String CHANNEL_CODE_FAIL = "2";
	/** 通道状态 * 3：异常 */
	protected static final String CHANNEL_CODEL_EXCEPTION = "3";
	
	public static final String CHANNEL_PARAMS_APPID = "appId";
	public static final String CHANNEL_PARAMS_PRIKEY = "privateKey";
	public static final String CHANNEL_PARAMS_PUBKEY = "publicKey";
	public static final String CHANNEL_PARAMS_AUTHCODE = "authCode";
	public static final String CHANNEL_PARAMS_OPENID = "openId";
	public static final String CHANNEL_PARAMS_MERCHANTNO = "merchantNo";
	public static final String CHANNEL_PARAMS_SUB_MERCHANTNO = "subMerchantNo";//子商户号，如果是银行签约商户必填
	public static final String CHANNEL_PARAMS_SUB_OPENID = "subOpenId";//公众号支付必填，如果是银行签约商户必填
	public static final String CHANNEL_PARAMS_RETURNURL = "returnUrl";
	public static final String CHANNEL_PARAMS_PRODUCTID = "productId";
	public static final String CHANNEL_PARAMS_APIKEY = "apiKey";
	public static final String CHANNEL_PARAMS_APPSECRET = "appSecret";
	
	public static Properties properties;
	static {
		InputStream inStream = ChannelBase.class.getClassLoader().getResourceAsStream("config/config.properties");
		properties = new Properties(); 
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
