package alPay.action.pay;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import alPay.action.BaseAction;
import alPay.common.CommonResponse;
import alPay.entity.WxPayEntity;
import alPay.util.Common;
import alPay.util.Util;

@RestController
public class WxPayAction extends BaseAction {

	@RequestMapping(value = "/wxPay.do", method = {RequestMethod.POST})
	public CommonResponse wxPay() {
		CommonResponse result = new CommonResponse();
		Map<String, Object> datas = new HashMap<String, Object>();
		WxPayEntity wxPayEntity = new WxPayEntity();
		wxPayEntity.setDevice_info("IOS");//设备号
		wxPayEntity.setNonce_str(Util.getTokenCode());//32位随机字符串
		wxPayEntity.setBody("只要一块钱");//商品描述
		wxPayEntity.setOut_trade_no("XZ"+Util.getTokenCode().subSequence(0, 30));//商户订单号
		wxPayEntity.setTotal_fee(1);//商品金额
		wxPayEntity.setFee_type("CNY");//币种
		wxPayEntity.setSpbill_create_ip("192.168.50.144");//终端IP
		SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();//将所有的参数加入到map集合中去，然后算出签名
        packageParams.put("appid", wxPayEntity.getAppid());
        packageParams.put("mch_id", wxPayEntity.getMch_id());
        packageParams.put("sub_mch_id",wxPayEntity.getSub_mch_id());
        packageParams.put("nonce_str", wxPayEntity.getNonce_str());
        packageParams.put("body", wxPayEntity.getBody());
        packageParams.put("out_trade_no", wxPayEntity.getOut_trade_no());
        packageParams.put("total_fee", wxPayEntity.getTotal_fee()+"");
        packageParams.put("fee_type",wxPayEntity.getFee_type());
        packageParams.put("spbill_create_ip", wxPayEntity.getSpbill_create_ip());
        packageParams.put("notify_url", wxPayEntity.getNotify_url());
        packageParams.put("trade_type", wxPayEntity.getTrade_type());
        String sign = Util.createSign(packageParams, Common.API_KEY);//获取签名
        packageParams.put("sign", sign);
        String requestXml = Util.getRequestXml(packageParams);
        logger.info(requestXml);
        try {
			String response = Util.httpPost(Common.WXPAY, requestXml);
			Map<String, Object> map = Util.doXMLParse(response);
			String returnCode = map.get("return_code")+"";//返回状态码
			String returnMsg = map.get("return_msg")+"";//返回信息
			if("SUCCESS".equals(returnCode)) {
				String resultCode = map.get("result_code")+"";
				if("SUCCESS".equals(resultCode)) {
					String tradeType = map.get("trade_type")+"";//交易类型
					String prepayId = map.get("prepay_id")+"";//预支付交易会话标识
					String codeUrl = map.get("code_url")+"";//二维码链接
					logger.info("交易类型："+tradeType+",预支付交易回话标识："+prepayId+",二维码链接："+codeUrl);
					result.setCode(CommonResponse.SUCCESS);
					datas.put("tradeType", tradeType);
					datas.put("prepayId", prepayId);
					datas.put("codeUrl", codeUrl);
					result.setDatas(datas);
				}else {
					String errCode = map.get("err_code")+"";//错误代码
					String errCodeDes = map.get("err_code_des")+"";//错误代码描述
					logger.info("状态："+errCode+",描述："+errCodeDes);
					result.setCode(CommonResponse.FAIL);
					datas.put("message", errCodeDes);
					result.setDatas(datas);
				}
			}else {
				result.setCode(CommonResponse.FAIL);
				datas.put("message", returnMsg);
				result.setDatas(datas);
				logger.info(returnMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
