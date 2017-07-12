package com.tracybrother.alipay.entity;

import java.util.Map;

import com.tracybrother.alipay.business.InputBase;

/**
 * Apps:微信，支付宝，京东，百度等
 * 支付退款查证相关业务实体类
 * @author wdd
 *
 */
public class AppsRequest extends InputBase {
	private String appId;		//spay的应用appid
//	private String merchantId;	//微信支付分配的商户号
//	private String signType;	//签名算法，支付宝：目前支持RSA2和RSA；微信：目前支持HMAC-SHA256和MD5，默认为MD5
//	private String sign;
//	private String timestamp;	//支付宝使用
	private String returnUrl;	//第三方同步返回给商户的地址
//	private String notifyUrl;	//异步通知地址
	private String body;		//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。例如：iphone 7 64G
	private String subject;		//商品的标题/交易标题/订单标题/订单关键字等。  例如：大乐透
	private String tradeNo;		//第三方交易号
	private String outTradeNo;	//商户订单号
	private String outRefundNo;	//商户退款订单号
	//微信公众号支付时，该参数必传；支付宝：合作者身份ID，签约的支付宝账号对应的支付宝唯一用户号。
//	private String openId;
	private String timeoutExpress;//超时时间
	private String totalAmount;	 //总金额
	private String discountableAmount;		//参与优惠金额 非必填（本身为支付宝字段，微信变相使用为代金券）
	private String enablePayChannels;		//允许支付渠道 非必填（支付宝拓展字段，用于限制可用渠道）
	/////支付宝间连业务必须字段
	private String storeId;					//商户门店号 间连业务必填（支付宝）（暂时用SPAY内部商户号替代）
	private String terminalId;				//商户终端号 间连业务必填（支付宝）（暂时用spay内部商户号替代）
	private String merchantId; 				//支付宝返回的商户号 间连业务必填（支付宝）
	private String buyerId;					//支付宝用户唯一id，当面付统一收单交易创建接口必填，通过用用户授权的方式获取会员信息
	
//	private String privateKey;
//	private String publicKey;
//	private String sellerId;	//收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
	private String clientIp;
	private String currency;
	private String description;
	private Map<String, String> metadata;
	private Map<String, String> extra;
	private String refundAmount;//退款金额
	private String refundReason;//退款原因
	
	private String subMchId;//报备子商户号
	
	
//	private String authCode;//支付授权码
	
	public String getSubMchId() {
		return subMchId;
	}
	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}
	private Map<String,String> channelDetailMap;//支付渠道参数配置
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getTimeoutExpress() {
		return timeoutExpress;
	}
	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<String, String> getMetadata() {
		return metadata;
	}
	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	public Map<String, String> getExtra() {
		return extra;
	}
	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getOutRefundNo() {
		return outRefundNo;
	}
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public Map<String, String> getChannelDetailMap() {
		return channelDetailMap;
	}
	public void setChannelDetailMap(Map<String, String> channelDetailMap) {
		this.channelDetailMap = channelDetailMap;
	}
	/**
	 * @return the discountableAmount
	 */
	public String getDiscountableAmount() {
		return discountableAmount;
	}
	/**
	 * @param discountableAmount the discountableAmount to set
	 */
	public void setDiscountableAmount(String discountableAmount) {
		this.discountableAmount = discountableAmount;
	}
	/**
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the terminalId
	 */
	public String getTerminalId() {
		return terminalId;
	}
	/**
	 * @param terminalId the terminalId to set
	 */
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}
	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * @return the buyerId
	 */
	public String getBuyerId() {
		return buyerId;
	}
	/**
	 * @param buyerId the buyerId to set
	 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	/**
	 * @return the enablePayChannels
	 */
	public String getEnablePayChannels() {
		return enablePayChannels;
	}
	/**
	 * @param enablePayChannels the enablePayChannels to set
	 */
	public void setEnablePayChannels(String enablePayChannels) {
		this.enablePayChannels = enablePayChannels;
	}
	@Override
	public String toString() {
		return "AppsRequest [appId=" + appId + ", returnUrl=" + returnUrl
				+ ", body=" + body + ", subject=" + subject + ", tradeNo="
				+ tradeNo + ", outTradeNo=" + outTradeNo + ", outRefundNo="
				+ outRefundNo + ", timeoutExpress=" + timeoutExpress
				+ ", totalAmount=" + totalAmount + ", discountableAmount="
				+ discountableAmount + ", enablePayChannels="
				+ enablePayChannels + ", storeId=" + storeId + ", terminalId="
				+ terminalId + ", merchantId=" + merchantId + ", buyerId="
				+ buyerId + ", clientIp=" + clientIp + ", currency=" + currency
				+ ", description=" + description + ", metadata=" + metadata
				+ ", extra=" + extra + ", refundAmount=" + refundAmount
				+ ", refundReason=" + refundReason + ", channelDetailMap="
				+ channelDetailMap + "]";
	}
	
	
}
