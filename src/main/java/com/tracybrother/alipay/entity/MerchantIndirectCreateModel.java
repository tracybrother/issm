package com.tracybrother.alipay.entity;

import java.util.List;

import com.alipay.api.domain.AddressInfo;
import com.alipay.api.domain.BankCardInfo;
import com.alipay.api.domain.ContactInfo;


/**
 * 银行间连商户入驻
 *
 * @author auto create
 * @since 1.0, 2017-03-16 13:16:13
 */
public class MerchantIndirectCreateModel {

	/**
	 * 商户地址信息
	 */
	private List<AddressInfo> address_info;

	/**
	 * 商户简称
	 */
	private String alias_name;

	/**
	 * 商户对应银行所开立的结算卡信息
	 */
	private List<BankCardInfo> bankcard_info;

	/**
	 * 商户证件编号（企业或者个体工商户提供营业执照，事业单位提供事证号）
	 */
	private String business_license;

	/**
	 * 商户证件类型，取值范围：NATIONAL_LEGAL：营业执照；NATIONAL_LEGAL_MERGE:营业执照(多证合一)；INST_RGST_CTF：事业单位法人证书
	 */
	private String business_license_type;

	/**
	 * 商户经营类目，参考文档：https://doc.open.alipay.com/doc2/detail?&docType=1&articleId=105444
	 */
	private String category_id;

	/**
	 * 商户联系人信息
	 */
	private List<ContactInfo> contact_info;

	/**
	 * 商户编号，由机构定义，需要保证在机构下唯一
	 */
	private String external_id;

	/**
	 * 商户编号，由机构定义，需要保证在机构下唯一
	 */
	private String sub_merchant_id;
	
	/**
	 * 商户的支付宝账号
	 */
	private List<String> logon_id;

	/**
	 * 商户备注信息，可填写额外信息
	 */
	private String memo;

	/**
	 * 商户名称
	 */
	private String name;

	/**
	 * 商户的支付二维码中信息，用于营销活动
	 */
	private List<String> pay_code_info;

	/**
	 * 商户客服电话
	 */
	private String service_phone;

	/**
	 * 商户来源机构标识，填写机构在支付宝的pid
	 */
	private String source;

	/**
	 * @return the address_info
	 */
	public List<AddressInfo> getAddress_info() {
		return address_info;
	}

	/**
	 * @param address_info the address_info to set
	 */
	public void setAddress_info(List<AddressInfo> address_info) {
		this.address_info = address_info;
	}

	/**
	 * @return the alias_name
	 */
	public String getAlias_name() {
		return alias_name;
	}

	/**
	 * @param alias_name the alias_name to set
	 */
	public void setAlias_name(String alias_name) {
		this.alias_name = alias_name;
	}

	/**
	 * @return the bankcard_info
	 */
	public List<BankCardInfo> getBankcard_info() {
		return bankcard_info;
	}

	/**
	 * @param bankcard_info the bankcard_info to set
	 */
	public void setBankcard_info(List<BankCardInfo> bankcard_info) {
		this.bankcard_info = bankcard_info;
	}

	/**
	 * @return the business_license
	 */
	public String getBusiness_license() {
		return business_license;
	}

	/**
	 * @param business_license the business_license to set
	 */
	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}

	/**
	 * @return the business_license_type
	 */
	public String getBusiness_license_type() {
		return business_license_type;
	}

	/**
	 * @param business_license_type the business_license_type to set
	 */
	public void setBusiness_license_type(String business_license_type) {
		this.business_license_type = business_license_type;
	}

	/**
	 * @return the category_id
	 */
	public String getCategory_id() {
		return category_id;
	}

	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	/**
	 * @return the contact_info
	 */
	public List<ContactInfo> getContact_info() {
		return contact_info;
	}

	/**
	 * @param contact_info the contact_info to set
	 */
	public void setContact_info(List<ContactInfo> contact_info) {
		this.contact_info = contact_info;
	}

	/**
	 * @return the external_id
	 */
	public String getExternal_id() {
		return external_id;
	}

	/**
	 * @param external_id the external_id to set
	 */
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}

	/**
	 * @return the logon_id
	 */
	public List<String> getLogon_id() {
		return logon_id;
	}

	/**
	 * @param logon_id the logon_id to set
	 */
	public void setLogon_id(List<String> logon_id) {
		this.logon_id = logon_id;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pay_code_info
	 */
	public List<String> getPay_code_info() {
		return pay_code_info;
	}

	/**
	 * @param pay_code_info the pay_code_info to set
	 */
	public void setPay_code_info(List<String> pay_code_info) {
		this.pay_code_info = pay_code_info;
	}

	/**
	 * @return the service_phone
	 */
	public String getService_phone() {
		return service_phone;
	}

	/**
	 * @param service_phone the service_phone to set
	 */
	public void setService_phone(String service_phone) {
		this.service_phone = service_phone;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the sub_merchant_id
	 */
	public String getSub_merchant_id() {
		return sub_merchant_id;
	}

	/**
	 * @param sub_merchant_id the sub_merchant_id to set
	 */
	public void setSub_merchant_id(String sub_merchant_id) {
		this.sub_merchant_id = sub_merchant_id;
	}


}
