package com.tracybrother.alipay.entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.aspectj.weaver.tools.Trace;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.tracybrother.alipay.business.OutputBase;
import com.tracybrother.channel.ChannelBase;

public abstract class AliPayBase extends ChannelBase {
	protected AlipayClient alipayClient = null;
	//支付宝gatewayUrl
	protected static final String ALIPAY_BASEURL = "https://openapi.alipay.com/gateway.do";
	//沙箱环境  
	protected static final String ALIPAY_DEVURL = "https://openapi.alipaydev.com/gateway.do";
	
	protected static final String ALIPAY_NOTIFY_URL = "https://ebank.fudian-bank.com/pay-app-server/AS00206.do";
	
	//支付宝提供给商户的服务接入网关URL(新)PC网页
	protected static final String ALIPAY_GATEWAY_PC = "https://mapi.alipay.com/gateway.do";
	//支付宝API版本号
	protected static final String ALIPAY_APIVERSION = "1.0";
	//销售产品码  固定值
	protected static final String ALIPAY_APP_PRODUCT_CODE = "QUICK_MSECURITY_PAY";
	protected static final String ALIPAY_WAP_PRODUCT_CODE = "QUICK_WAP_PAY";
	protected static final String ALIPAY_NATIVE_PRODUCT_CODE = "FACE_TO_FACE_PAYMENT";
	//PC即时到帐接口名称
	protected static final String ALIPAY_PC_SERVICE = "create_direct_pay_by_user";
	//APP支付接口名称
	protected static final String ALIPAY_METHOD_APPPAY = "alipay.trade.app.pay";
	protected static final String ALIPAY_METHOD_WAPPAY = "alipay.trade.wap.pay";
	protected static final String ALIPAY_METHOD_PRECREATE = "alipay.trade.wap.pay";
	// 支付类型 ，无需修改
	protected static final String PAYMENT_TYPE = "1";
	//支付场景-条码支付
	protected static final String ALIPAY_NATIVE_BAR_CODE = "bar_code";
	//支付场景-声波支付
	protected static final String ALIPAY_NATIVE_WAVE_CODE = "wave_code";
	
	//创建支付宝client
	//建议改成，根据商户初始化一次，alipayClient只需要初始化一次
	public AlipayClient getClient(AppsRequest appsRequest){
		return new DefaultAlipayClient(ALIPAY_BASEURL, appsRequest.getChannelDetailMap().get(CHANNEL_PARAMS_APPID),
				appsRequest.getChannelDetailMap().get(CHANNEL_PARAMS_PRIKEY), FORMAT_JSON, CHARSET_UTF8, 
				appsRequest.getChannelDetailMap().get(CHANNEL_PARAMS_PUBKEY), SIGN_TYPE_RSA);
	}
	
	//支付宝通用查询接口：alipay.trade.query (统一收单线下交易查询)
	public OutputBase baseQuery(AppsRequest appsRequest) throws Exception {
		alipayClient = getClient(appsRequest);
		AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
		queryRequest.setApiVersion(ALIPAY_APIVERSION);
		Map<String, String> map = new HashMap<String, String>();
		map.put("out_trade_no", appsRequest.getOutTradeNo());
		String bizContent = JSONObject.toJSONString(map);
		queryRequest.setBizContent(bizContent);
		
		AlipayTradeQueryResponse queryResponse = alipayClient.execute(queryRequest);
		OutputBase outPutBase = new OutputBase();
		Map<String, Object> outMap = new HashMap<String, Object>();
		if(queryResponse.isSuccess()){
			String tradeStatus = queryResponse.getTradeStatus();
			//交易成功  
			//TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）  
			if("TRADE_SUCCESS".equals(tradeStatus)||"TRADE_FINISHED".equals(tradeStatus)){
				outPutBase.setRetCode(CHANNEL_CODE_SUCC);
			}
			//交易失败  
			//TRADE_CLOSED(未付款交易超时关闭，或支付完成后全额退款)
			else if("TRADE_CLOSED".equals(tradeStatus)){
				outPutBase.setRetCode(CHANNEL_CODE_FAIL);
			}
			//交易未知
			//WAIT_BUYER_PAY（交易创建，等待买家付款）
			else{
				outPutBase.setRetCode(CHANNEL_CODE_UNKNOWN);
			}
			outMap.put("transactionNo", queryResponse.getTradeNo());//支付宝交易号
			outMap.put("outTradeNo", queryResponse.getOutTradeNo());
			outMap.put("totalAmount", queryResponse.getTotalAmount());
			outMap.put("timeCompleted", "");//支付宝查证无返回完成时间
			outPutBase.setMap(outMap);
		}else{
			//业务失败
			outPutBase.setRetCode(queryResponse.getSubCode());
			outPutBase.setRetMessage(queryResponse.getSubMsg());
		}
		return outPutBase;
	}
	
	// 支付宝通用退款接口：alipay.trade.refund (统一收单交易退款接口)
	public OutputBase baseRefund(AppsRequest appsRequest) throws Exception {
		alipayClient = getClient(appsRequest);
		AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
		refundRequest.setApiVersion(ALIPAY_APIVERSION);
		Map<String, String> map = new HashMap<String, String>();
		map.put("out_trade_no", appsRequest.getOutTradeNo());
		map.put("refund_amount", appsRequest.getRefundAmount());
		map.put("refund_reason", appsRequest.getRefundReason());
		//退款请求号，如果会多次退款该号必须唯一，部分退款时该参数必传
		map.put("out_request_no", appsRequest.getOutRefundNo());//商户退款订单号
		String bizContent = JSONObject.toJSONString(map);
		refundRequest.setBizContent(bizContent);

		AlipayTradeRefundResponse refundResponse = alipayClient
				.execute(refundRequest);

		OutputBase outPutBase = new OutputBase();
		outPutBase.setRetCode(CHANNEL_CODE_UNKNOWN);
		if (refundResponse.isSuccess()) {
			// 成功
			outPutBase.setRetCode(CHANNEL_CODE_SUCC);
			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put("transactionNo", refundResponse.getTradeNo());// 支付宝交易号 //退款是新的单号，非上传支付单号？待确认
			outMap.put("outTradeNo", refundResponse.getOutTradeNo());//商户订单号
			outMap.put("outRefundNo", appsRequest.getOutRefundNo());//商户退款订单号
//			outMap.put("refundTime", refundResponse.getGmtRefundPay());//退款支付时间
			outPutBase.setMap(outMap);
		} else {
			// 失败
			outPutBase.setRetMessage(refundResponse.getCode()+"-"+refundResponse.getMsg());
		}
		return outPutBase;
	}
	
	// 支付通用退款查询接口：alipay.trade.fastpay.refund.query (统一收单交易退款查询)
	public OutputBase baseRefundQuery(AppsRequest appsRequest) throws Exception {
		alipayClient = getClient(appsRequest);
		AlipayTradeFastpayRefundQueryRequest refundQueryRequest = new AlipayTradeFastpayRefundQueryRequest();
		refundQueryRequest.setApiVersion(ALIPAY_APIVERSION);
		Map<String, String> map = new HashMap<String, String>();
		map.put("out_trade_no", appsRequest.getOutTradeNo());
		// 退款请求号，如果会多次退款该号必须唯一，部分退款时该参数必传
		map.put("out_request_no", appsRequest.getOutRefundNo());
		String bizContent = JSONObject.toJSONString(map);
		refundQueryRequest.setBizContent(bizContent);

		AlipayTradeFastpayRefundQueryResponse refundQueryResponse = alipayClient
				.execute(refundQueryRequest);

		OutputBase outPutBase = new OutputBase();
		outPutBase.setRetCode(CHANNEL_CODE_UNKNOWN);
		if (refundQueryResponse.isSuccess()) {
			// 成功
			outPutBase.setRetCode(CHANNEL_CODE_SUCC);
			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put("transactionNo", refundQueryResponse.getTradeNo());// 支付宝交易号
			outMap.put("outRefundNo", refundQueryResponse.getOutRequestNo());// 退款请求号
			outMap.put("outTradeNo", refundQueryResponse.getOutTradeNo());// 商户订单号
			outPutBase.setMap(outMap);
		} else {
			// 失败
			outPutBase.setRetMessage(refundQueryResponse.getCode()+"-"+refundQueryResponse.getMsg());
		}
		return outPutBase;
	}
	
	
	/**
	 * @Title: downloadFile
	 * @Description: 支付宝统一对账单下载
	 * @author: weiss
	 * @date: 2017年5月16日
	 */
	public String downloadFile(AppsRequest appRequest , String date){
//		Trace.logInfo(Trace.MODULE_CONNECT, "下载支付宝对账单启动，日期："+date);
		// 获取地址
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				"APP_ID", "APP_PRIVATE_KEY", "json", "CHARSET", "ALIPAY_PUBLIC_KEY", "RSA2");//获得初始化的AlipayClient
		AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();//创建API对应的request类
		request.setBizContent("{" +
		"    \"bill_type\":\"trade\"," +
		"    \"bill_date\":\"2016-04-05\"}"); //设置业务参数
		AlipayDataDataserviceBillDownloadurlQueryResponse response;
		String urlStr = null;
		try {
			response = alipayClient.execute(request);
			if("100000".equals(response.getCode())){
				urlStr = response.getBody();
			}else{
				
			}
			
		} catch (AlipayApiException e1) {
//			Trace.logError(Trace.MODULE_CONNECT, "获取对账文件地址错误", e1);
		}//通过alipayClient调用API，获得对应的response类
		
//		Trace.logInfo(Trace.MODULE_CONNECT, "下载支付宝对账单地址"+urlStr);
		//根据response中的结果继续业务逻辑处理
		//将接口返回的对账单下载地址传入urlStr
//		String urlStr = "http://dwbillcenter.alipay.com/downloadBillFile.resource?bizType=X&userId=X&fileType=X&bizDates=X&downloadFileName=X&fileId=X";
		//指定希望保存的文件路径
		String filePath = "/Users/fund_bill_20160405.csv自己规则";
//		Trace.logInfo(Trace.MODULE_CONNECT, "下载支付宝对账单保存位置"+filePath);
		URL url = null;
		HttpURLConnection httpUrlConnection = null;
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
			url = new URL(urlStr);
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setConnectTimeout(5 * 1000);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setRequestProperty("CHARSET", "UTF-8");
			httpUrlConnection.connect();
			fis = httpUrlConnection.getInputStream();
			byte[] temp = new byte[1024];
			int b;
			fos = new FileOutputStream(new File(filePath));
			while ((b = fis.read(temp)) != -1) {
				fos.write(temp, 0, b);
				fos.flush();
			}
		} catch (MalformedURLException e) {
//			Trace.logError(Trace.MODULE_CONNECT, "下载支付宝对账单出错。"+e);
		} catch (IOException e) {
//			Trace.logError(Trace.MODULE_CONNECT, "下载支付宝对账单出错。"+e);
		} finally {
			try {
				if(fis!=null) fis.close();
				if(fos!=null) fos.close();
				if(httpUrlConnection!=null) 
					httpUrlConnection.disconnect();
			} catch(IOException e) {
//				Trace.logError(Trace.MODULE_CONNECT, "下载支付宝对账单关闭流出错。"+e);
			}
			
		}
		return filePath;
	}
	
	
	/**
	 * @Title: fileConverted
	 * @Description: 文件转换，将支付宝对账文件转换为通用格式
	 * @author: weiss
	 * @date: 2017年5月16日
	 */
	public String fileConverted(String filePath){
		FileInputStream fis = null;
		InputStreamReader iReader = null;
		BufferedReader in =null;
		String line;
		StringBuffer sb = null;
		PrintWriter merOut = null;
		String merFileCatage = "文件地址 : PLDF/merback/"+"/";
		String merFileName = "H.txt";
		try {
			
			fis = new FileInputStream(filePath);
			iReader = new InputStreamReader(fis);
			in = new BufferedReader(iReader);
			Map<String,Object> ma ;
			int lines = 0;
			int i = 0;
			
			File channelFile = null;  
        	channelFile = new File(merFileCatage,"");  
            if (!channelFile.isDirectory())  
            	channelFile.mkdir();  
            else {  
            	channelFile = new File(merFileCatage + merFileName);  
                if (!channelFile.exists()){
                	channelFile.createNewFile();
                }
            }
            merOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(channelFile,true),"GBK")));
			
			/*********************开始读取回盘文件，读取后修改相应的内容组装推送给商户的文件内容**************************/
			while ((line = in.readLine()) != null) {
				//文件1000条写一次
				if(i == 1000){
					merOut.write(sb.toString());
		            merOut.flush();
		            sb = null;
		            i = 0;
				}
				
				if (line.trim().length() == 0) {
					throw new RuntimeException("内容为空时是否可以继续怎么判断");
				}
				//文件头处理
				if(lines==0){
//					Trace.logInfo(Trace.MODULE_DATA, "支付宝通道回盘文件头信息"+line);
					String[] fileHeadArray = line.trim().split("\\|"); //文件头格式:
					String businessId = fileHeadArray[0];
					String channelBatchNo = fileHeadArray[1];
					String fileTotalNum = fileHeadArray[2];
					String fileTotalAmount = fileHeadArray[3];
					String fileSuccessNum = fileHeadArray[4];
					String fileSuccessAmount = fileHeadArray[5];
					
					//组装商户回盘文件头信息
					sb.append("").append("|")
					  .append("").append("|")
					  .append(fileTotalNum).append("|")
					  .append(fileTotalAmount).append("|")
					  .append(fileSuccessNum).append("|")
					  .append(fileSuccessAmount).append("|")
					  .append("");
//					Trace.logInfo(Trace.MODULE_DATA, "支付宝通道回盘文件头信息"+sb.toString());
					
				}else{
					//文件体处理
					//文件体格式:
					String[] fileBodyArray = line.trim().split("\\|");
					String batchNo = fileBodyArray[2]; //批次号
					String sendOrderNo = fileBodyArray[3]; //发送给银联的订单号
					String cardNo = fileBodyArray[4];  //银行卡号
					String userName = fileBodyArray[5]; //持卡人姓名
					String bankName = fileBodyArray[6]; //开户银行代码
					String transAmt = fileBodyArray[7]; //金额
					String status = fileBodyArray[8];   //交易代码
					String errMessage = fileBodyArray[9];   //退单日期
					//通用文件
					sb.append("").append("|")
						.append("").append("|")
						.append(cardNo).append("|")
						.append(userName).append("|")
						.append(bankName).append("|")
						.append(transAmt).append("|")
						.append(status).append("|")
						.append(errMessage).append("|")
						.append("");
				}
				lines++;
			}
			if(sb.length() > 0){
				merOut.write(sb.toString());
				merOut.flush();
			}
		} catch (Exception e) {
//			Trace.logError(Trace.MODULE_CONNECT, e.getMessage(), e);
		} finally {
			try {
				fis.close();
				iReader.close();
				in.close();
				merOut.close();
			} catch (Exception e) {
//				Trace.logError(Trace.MODULE_CONNECT, e.getMessage(), e);
			}
		}
		
		return merFileCatage+merFileName;
	}
	
	/**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
//    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp,String signType,String privateKey) {
//        //除去数组中的空值和签名参数
//        Map<String, String> sPara = paraFilter(sParaTemp);
//        //生成签名结果
//        String mysign = buildRequestMysign(sPara,signType,privateKey);
//
//        //签名结果与签名方式加入请求提交参数组中
//        sPara.put("sign", mysign);
//        sPara.put("sign_type", signType);
//        return sPara;
//    }
    
//    /**
//     * 生成签名结果
//     * @param sPara 要签名的数组
//     * @return 签名结果字符串
//     */
//	public static String buildRequestMysign(Map<String, String> sPara,String signType,String privateKey) {
//    	String prestr = createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
//        String mysign = "";
//        if("RSA".equals(signType) ){
//        	mysign = RSA.sign(prestr, privateKey, CHARSET_UTF8);
//        }
//        return mysign;
//    }
	
    /**
     * 参数名ASCII码从小到大排序（字典序）
     * 如果参数的值为空不参与签名
     * 使用URL键值对的格式（即key1=value1&key2=v2&key3=value3…）拼接成字符串
     * @param map
     * @return
     */
    public static String getASCStringByMap(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        if (result.endsWith("&")) {  
        	result = org.apache.commons.lang.StringUtils.substringBeforeLast(result, "&");  
        }  
        return result;
    }
    
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    /**
     * URL参数转成Map
     * @param param aa=11&bb=22&cc=33 
     * @return
     */
    public static Map<String, Object> getUrlParams(String param) {  
        Map<String, Object> map = new HashMap<String, Object>(0);  
        if (param==null||param==""||param.isEmpty()) {  
            return map;  
        }  
        String[] params = param.split("&");  
        for (int i = 0; i < params.length; i++) {  
            String[] p = params[i].split("=");  
            if (p.length == 2) {  
                map.put(p[0], p[1]);  
            }  
        }  
        return map;  
    } 
    
    
    /**
     * 带&的参数一级value进行encode转码
     * @param param
     * @return
     * @throws Exception
     */
    public static String getEncodeForParams(String param) throws Exception{
    	StringBuffer sbf = new StringBuffer();
    	String[] params = param.split("&");  
        for (int i = 0; i < params.length; i++) {  
            String[] p = params[i].split("=");  
            if (p.length == 2) {  
            	sbf.append(p[0]+"="+baseEncode(p[1]));
            }
            if(i!=params.length-1){
            	sbf.append("&");
            }
        }  
    	
    	return sbf.toString();
    }
    
    /**
     * Map转url格式且value进行encode转码
     * @param map
     * @return
     * @throws Exception
     */
    public static String getEncodeForParams(Map<String, String> map) throws Exception {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + baseEncode(entry.getValue()));
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}
    
    
    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
    
    /**
     * encode
     * @param param
     * @return
     * @throws Exception
     */
    public static String baseEncode(String param) throws Exception{
		return URLEncoder.encode(param, CHARSET_UTF8);
    }
    
    /**
     * encode
     * @param param
     * @return
     * @throws Exception
     */
    public static String baseDecode(String param) throws Exception{
		return URLDecoder.decode(param, CHARSET_UTF8);
    }
    
    
    /**
     * 返回签名编码拼接url
     * 
     * @param params
     * @param isEncode
     * @return
     */
    public static String getSignEncodeUrl(Map<String, String> map, boolean isEncode) {
        String sign = map.get("sign");
        String encodedSign = "";
        if (!map.isEmpty()&&map.size()>0) {
            map.remove("sign");
            List<String> keys = new ArrayList<String>(map.keySet());
            // key排序
            Collections.sort(keys);

            StringBuilder authInfo = new StringBuilder();

            boolean first = true;// 是否第一个
            for (String key: keys) {
                if (first) {
                    first = false;
                } else {
                    authInfo.append("&");
                }
                authInfo.append(key).append("=");
                if (isEncode) {
                    try {
                        authInfo.append(URLEncoder.encode(map.get(key), CHARSET_UTF8));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    authInfo.append(map.get(key));
                }
            }

            try {
                encodedSign = authInfo.toString() + "&sign=" + URLEncoder.encode(sign, CHARSET_UTF8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return encodedSign.replaceAll("\\+", "%20");
    }
    
    
    public static void main(String[] args) throws Exception {
	
    	String param = "app_id=2088911849466231&biz_content={\"total_amount\":\"0.01\",\"body\":\"手机M8\",\"subject\":\"M8\"}&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http://spayx.com/pay&sign_type=RSA&timestamp=2017-03-22 16:37:00&version=1.0&sign=gMQHDNgYJA/CSzu96byX2faltSM9ryk8LZNCeN94htucobsnObogKsl6JdYJW+KbjUvXOFwG/yS5egejasQPRd9pOe1QA49RngPjfuC41ycF7NzPL8qZH5+NvkRxz6c3oxag7iK/6zq6dDgS0yCsx16JzixE8OKDgtjKjdn3iJ8=";
    	
    	System.out.println(getEncodeForParams(param));
	}
}
