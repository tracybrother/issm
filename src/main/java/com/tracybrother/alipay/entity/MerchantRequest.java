package com.tracybrother.alipay.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.api.domain.AddressInfo;
import com.alipay.api.domain.BankCardInfo;
import com.alipay.api.domain.ContactInfo;
import com.alipay.api.internal.util.StringUtils;
import com.tracybrother.utils.StringUtil;

/**
 * @ClassName: MerchantRequest
 * @Description: 商户信息报备实体
 * @author weiss
 * @date 2017年5月18日
 */
public class MerchantRequest extends AppsRequest {
	// 通用
	private String merchantId; // 受理商户编号，由受理机构定义，需要保证在受理机构下唯一
	private String merchantName; // 受理商户名称
	private String merchantShortname; // 受理商户简称（会显示在支付宝付款成功页
	private String servicePhone; // 受理商户客服电话（用于客诉咨询）
	private String categoryId; // 受理商户经营类目（简单类目，对应不同风控模型，填写最贴切商户的行业）
	private String contactName; // 负责人姓名
	private String contactPhone; // 负责人电话

	// 支付宝M3独有
	private String source; // 受理商户来源标识，填写商户所属的银行或ISV的企业支付宝pid（即商户邀请人pid）
	private String businessLicense; // 受理商户营业执照编号 必填
	private String businessLicenseType; // 受理商户营业执照类型 必填
	// private String contact_info; //受理商户负责人（如法人）信息（json数组结构体，含name, type,
	// id_card_no字段，mobile和phone二选一必填） 必填 必填 必填
	private String contactType; // 受理商户负责人类型（什么类型）
	private String contactIdCardNo; // 受理商户负责人身份证号
	// private String address_info; //受理商户地址信息（json数组结构体，含province_code,
	// city_code, district_code, address字段） 必填 必填
	private String provinceCode;
	private String cityCode;
	private String districtCode;
	private String address;
	// private String bankcard_info; //受理商户对应银行开立的结算卡信息（json数组结构体，含card_no,
	// card_name字段） 必填
	private String cardNo;
	private String cardName;

	// 微信独有
	private String appid; // 举例：wxd678efh567hg6787 银行服务商的公众账号ID
	private String mchId; // 举例：1230000109 银行服务商的商户号
	private String sign; // 签名
	private String channelId; // 举例：10100000
								// 银行为其渠道商申请（在服务商平台申请，请见《渠道录入指引》）的渠道标识，如是银行自行拓展的商户，即自有渠道，则渠道号填写银行商户号
	private String contactEmail;// 联系邮箱
    private String merchantRemark;//同一个受理机构，子商户“商户备注”唯一。 商户备注重复时，生成商户识别码失败，并返回提示信息“商户备注已存在，请修改后重新提交
	
    private String pageIndex;  //当前查询的具体分页页面
    private String pageSize;   //每一个分页，可展示多少条资料信息
    
	
	private String subMerchantId; //通道返回商户ID
	
	/**
	 * @Title: getAliCreate
	 * @Description: 阿里商户录入json
	 * @author: weiss
	 * @date: 2017年5月18日
	 */
	public String getAliCreateJSON() {
		MerchantIndirectCreateModel bea = new MerchantIndirectCreateModel();
		if(!StringUtils.isEmpty(subMerchantId))
			bea.setSub_merchant_id(subMerchantId);
		if(!StringUtils.isEmpty(merchantId))
			bea.setExternal_id(merchantId); // 受理商户编号，由受理机构定义，需要保证在受理机构下唯一
		bea.setName(merchantName);// 受理商户名称
		bea.setAlias_name(merchantShortname);// 受理商户简称（会显示在支付宝付款成功页
		bea.setService_phone(servicePhone);// 受理商户客服电话（用于客诉咨询）
		bea.setCategory_id(categoryId);// 受理商户经营类目（简单类目，对应不同风控模型，填写最贴切商户的行业）
		// 支付宝M3独有
		bea.setSource(source);// 受理商户来源标识，填写商户所属的银行或ISV的企业支付宝pid（即商户邀请人pid）
		if (StringUtil.hasText(businessLicense)){
			bea.setBusiness_license(businessLicense);// 受理商户营业执照编号 必填
		}
		if (StringUtil.hasText(businessLicenseType)){
			bea.setBusiness_license_type(businessLicenseType);// 受理商户营业执照类型 必填
		}
		// contact_info; //受理商户负责人（如法人）信息（json数组结构体，含name, type, id_card_no字段，mobile和phone二选一必填） 必填 必填 必填
		if (StringUtil.hasText(contactName)
			&& StringUtil.hasText(contactIdCardNo)
			&& StringUtil.hasText(contactType)){
			
			ContactInfo contactInfo = new ContactInfo();
			contactInfo.setName(contactName);
			contactInfo.setIdCardNo(contactIdCardNo);
			contactInfo.setType(contactType);
			if (StringUtil.hasText(contactPhone)){
				if (contactPhone.length() == 11){
					contactInfo.setMobile(contactPhone);
				} else {
					contactInfo.setPhone(contactPhone);
				}
			}
			List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
			contactInfoList.add(contactInfo);
			bea.setContact_info(contactInfoList);
		}
		//M1否，M2是，M3是
		// address_info; //受理商户地址信息（json数组结构体，含province_code, city_code, district_code, address字段） 必填 必填
		if (StringUtil.hasText(provinceCode) && StringUtil.hasText(cityCode)
			&& StringUtil.hasText(districtCode) && StringUtil.hasText(address)){
			
			AddressInfo addressInfo = new AddressInfo();
			addressInfo.setProvinceCode(provinceCode);
			addressInfo.setCityCode(cityCode);
			addressInfo.setDistrictCode(districtCode);
			addressInfo.setAddress(address);
			
			List<AddressInfo>  addressInfoList = new ArrayList<AddressInfo>();
			addressInfoList.add(addressInfo);
			bea.setAddress_info(addressInfoList);
		}
		
		// bankcard_info; //受理商户对应银行开立的结算卡信息（json数组结构体，含card_no, card_name字段） 必填
		if (StringUtil.hasText(cardNo) && StringUtil.hasText(cardName)){
			BankCardInfo bankCardInfo = new BankCardInfo();
			bankCardInfo.setCardName(cardNo);
			bankCardInfo.setCardNo(cardName);
			List<BankCardInfo> bankCardInfoList = new ArrayList<BankCardInfo>();
			bankCardInfoList.add(bankCardInfo);
			bea.setBankcard_info(bankCardInfoList);
		}
		String res = JSON.toJSONString(bea);
		System.out.println(res);
		return res;
	}

	
	/**
	 * @Title: getAliQuery
	 * @Description: 阿里商户查询json
	 * @author: weiss
	 * @date: 2017年5月18日
	 */
	public String getAliQueryJSON() {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(merchantId)){
			map.put("external_id", merchantId); // 受理商户编号，由受理机构定义，需要保证在受理机构下唯一
		}
		if(!StringUtils.isEmpty(subMerchantId)){
			map.put("sub_merchant_id", subMerchantId); // 商户ID 支付宝返回ID
		}
		
		String res = JSON.toJSONString(map);
		return res;
	}
	
	public Map<String, Object> getWxCreateMerchantMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("appid", appid); // 银行服务商的公众账号ID
		map.put("mch_id", mchId); // 银行服务商的商户号
		map.put("merchant_name", merchantName); // 该名称是公司主体全称，绑定公众号时会对主体一致性校验
		map.put("merchant_shortname", merchantShortname); // 该名称是显示给消费者看的商户名称
		map.put("service_phone", servicePhone); //方便微信在必要时能联系上商家，会在支付详情展示给消费者
		
		map.put("contact", contactName); // 方便微信在必要时能联系上商家。非必填
		map.put("contact_phone", contactPhone); // 方便微信在必要时能联系上商家。非必填
		map.put("contact_email", contactEmail); // 方便微信在必要时能联系上商家。非必填
		
		map.put("channel_id", channelId); //银行为其渠道商申请（在服务商平台申请，请见《渠道录入指引》）的渠道标识，如是银行自行拓展的商户，即自有渠道，则渠道号填写银行商户号  
		map.put("business", categoryId);//行业类目，请填写对应的ID，见详细列表
		
		map.put("merchant_remark", merchantRemark);//商户备注信息
		return map;
	}
	
	public Map<String, Object> getWxQuerySubMerchantMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("appid", appid); // 银行服务商的公众账号ID
		map.put("mch_id", mchId); // 银行服务商的商户号
		map.put("page_index", pageIndex);//当前查询的具体分页页面
		map.put("page_size", pageSize);//每一个分页，可展示多少条资料信息
		map.put("sub_mch_id", subMerchantId);//微信支付分配的商户识别码
		return map;
	}

	/**
	 * @return the merchantId
	 */
	@Override
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId
	 *            the merchantId to set
	 */
	@Override
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the merchantName
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName
	 *            the merchantName to set
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * @return the merchantShortname
	 */
	public String getMerchantShortname() {
		return merchantShortname;
	}

	/**
	 * @param merchantShortname
	 *            the merchantShortname to set
	 */
	public void setMerchantShortname(String merchantShortname) {
		this.merchantShortname = merchantShortname;
	}

	/**
	 * @return the servicePhone
	 */
	public String getServicePhone() {
		return servicePhone;
	}

	/**
	 * @param servicePhone
	 *            the servicePhone to set
	 */
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone
	 *            the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the businessLicense
	 */
	public String getBusinessLicense() {
		return businessLicense;
	}

	/**
	 * @param businessLicense
	 *            the businessLicense to set
	 */
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	/**
	 * @return the businessLicenseType
	 */
	public String getBusinessLicenseType() {
		return businessLicenseType;
	}

	/**
	 * @param businessLicenseType
	 *            the businessLicenseType to set
	 */
	public void setBusinessLicenseType(String businessLicenseType) {
		this.businessLicenseType = businessLicenseType;
	}

	/**
	 * @return the contactType
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * @param contactType
	 *            the contactType to set
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * @return the contactIdCardNo
	 */
	public String getContactIdCardNo() {
		return contactIdCardNo;
	}

	/**
	 * @param contactIdCardNo
	 *            the contactIdCardNo to set
	 */
	public void setContactIdCardNo(String contactIdCardNo) {
		this.contactIdCardNo = contactIdCardNo;
	}

	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode
	 *            the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode
	 *            the districtCode to set
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @param cardName
	 *            the cardName to set
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid
	 *            the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the mchId
	 */
	public String getMchId() {
		return mchId;
	}

	/**
	 * @param mchId
	 *            the mchId to set
	 */
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId
	 *            the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param contactEmail
	 *            the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the subMerchantId
	 */
	public String getSubMerchantId() {
		return subMerchantId;
	}


	/**
	 * @param subMerchantId the subMerchantId to set
	 */
	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
	}


	public String getMerchantRemark() {
		return merchantRemark;
	}


	public void setMerchantRemark(String merchantRemark) {
		this.merchantRemark = merchantRemark;
	}


	public String getPageIndex() {
		return pageIndex;
	}


	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}


	public String getPageSize() {
		return pageSize;
	}


	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	@Override
	public String toString() {
		return "MerchantRequest [merchantId=" + merchantId + ", merchantName="
				+ merchantName + ", merchantShortname=" + merchantShortname
				+ ", servicePhone=" + servicePhone + ", categoryId="
				+ categoryId + ", contactName=" + contactName
				+ ", contactPhone=" + contactPhone + ", source=" + source
				+ ", businessLicense=" + businessLicense
				+ ", businessLicenseType=" + businessLicenseType
				+ ", contactType=" + contactType + ", contactIdCardNo="
				+ contactIdCardNo + ", provinceCode=" + provinceCode
				+ ", cityCode=" + cityCode + ", districtCode=" + districtCode
				+ ", address=" + address + ", cardNo=" + cardNo + ", cardName="
				+ cardName + ", appid=" + appid + ", mchId=" + mchId
				+ ", sign=" + sign + ", channelId=" + channelId
				+ ", contactEmail=" + contactEmail + ", merchantRemark="
				+ merchantRemark + ", pageIndex=" + pageIndex + ", pageSize="
				+ pageSize + ", subMerchantId="
				+ subMerchantId + "]";
	}

}
