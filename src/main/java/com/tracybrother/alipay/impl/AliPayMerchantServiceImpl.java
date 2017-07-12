package com.tracybrother.alipay.impl;

import java.util.HashMap;
import java.util.Map;

import com.alipay.api.request.AntMerchantExpandIndirectCreateRequest;
import com.alipay.api.request.AntMerchantExpandIndirectModifyRequest;
import com.alipay.api.request.AntMerchantExpandIndirectQueryRequest;
import com.alipay.api.response.AntMerchantExpandIndirectCreateResponse;
import com.alipay.api.response.AntMerchantExpandIndirectModifyResponse;
import com.alipay.api.response.AntMerchantExpandIndirectQueryResponse;
import com.tracybrother.alipay.IMerchantService;
import com.tracybrother.alipay.business.OutputBase;
import com.tracybrother.alipay.entity.AliPayBase;
import com.tracybrother.alipay.entity.MerchantRequest;

/**
 * @ClassName: AliPayPrecreateServiceImpl
 * @Description: 支付宝 间连商户信息报备接口
 * @author weiss
 * @date 2017年5月18日
 */
public class AliPayMerchantServiceImpl extends AliPayBase implements IMerchantService {
	
	//private Logger log = LoggerFactory.getLogger(AliPayMerchantServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.ynet.spay.business.apps.MerchantService#merchantCreate(com.ynet.spay.business.apps.MerchantRequest)
	 * ant.merchant.expand.indirect.create M3商户信息报备
	 */
	@Override
	public OutputBase merchantCreate(MerchantRequest bean) throws Exception {
		
		alipayClient = getClient(bean);
		
		AntMerchantExpandIndirectCreateRequest request = new AntMerchantExpandIndirectCreateRequest();
		request.setBizContent(bean.getAliCreateJSON());
		OutputBase outPutBase = new OutputBase();
		AntMerchantExpandIndirectCreateResponse response = null;
		try{
			 response = alipayClient.execute(request);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			Trace.logError(Trace.MODULE_ACCESS, "支付宝调用失败，异常原因："+e.getMessage());
			throw new RuntimeException("调用支付宝异常");
		}
	
//		Trace.logInfo(Trace.MODULE_ACCESS, "支付宝调用商户报备返回内容："+response.getBody());
		if(response.isSuccess()){
			outPutBase.setRetCode(CHANNEL_CODE_SUCC);//返回成功
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("subMerchantId", response.getSubMerchantId());
			outPutBase.setMap(m);
		}else{
			outPutBase.setRetCode(CHANNEL_CODE_FAIL);
			outPutBase.setRetMessage(response.getSubMsg());
		}
		return outPutBase;
	}

	/* (non-Javadoc)
	 * @see com.ynet.spay.business.apps.MerchantService#merchantQuery(com.ynet.spay.business.apps.MerchantRequest)
	 * ant.merchant.expand.indirect.query 商户信息查询
	 */
	@Override
	public OutputBase merchantQuery(MerchantRequest bean) throws Exception {
		alipayClient = getClient(bean);
		
		AntMerchantExpandIndirectQueryRequest request = new AntMerchantExpandIndirectQueryRequest();
//		AntMerchantExpandIndirectCreateModel request = new AntMerchantExpandIndirectCreateModel();
		request.setBizContent(bean.getAliQueryJSON());
		
		OutputBase outPutBase = new OutputBase();
		AntMerchantExpandIndirectQueryResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			outPutBase.setRetCode(CHANNEL_CODE_SUCC);//返回成功
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("subMerchantId", response.getSubMerchantId());
//System.out.println(response.getBody());	2088621876856681		
			outPutBase.setMap(m);
		}else{
			outPutBase.setRetCode(CHANNEL_CODE_FAIL);
			outPutBase.setRetMessage(response.getSubMsg());
		}
		return outPutBase;
	}

	/* (non-Javadoc)
	 * @see com.ynet.spay.business.apps.MerchantService#merchantUpdate(com.ynet.spay.business.apps.MerchantRequest)
	 * ant.merchant.expand.indirect.modify 商户信息修改
	 */
	@Override
	public OutputBase merchantUpdate(MerchantRequest bean) throws Exception {
		alipayClient = getClient(bean);
		
		AntMerchantExpandIndirectModifyRequest request = new AntMerchantExpandIndirectModifyRequest();
//		AntMerchantExpandIndirectCreateModel request = new AntMerchantExpandIndirectCreateModel();
		request.setBizContent(bean.getAliCreateJSON());
		
		OutputBase outPutBase = new OutputBase();
		AntMerchantExpandIndirectModifyResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			outPutBase.setRetCode(CHANNEL_CODE_SUCC);//返回成功
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("subMerchantId", response.getSubMerchantId());
System.out.println(response.getBody());			
			outPutBase.setMap(m);
		}else{
			outPutBase.setRetCode(CHANNEL_CODE_FAIL);
			outPutBase.setRetMessage(response.getSubMsg());
		}
		return outPutBase;
	}

}
