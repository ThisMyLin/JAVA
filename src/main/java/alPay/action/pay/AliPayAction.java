package alPay.action.pay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;

import alPay.action.BaseAction;
import alPay.common.CommonResponse;
import alPay.entity.GoodsDetail;
import alPay.entity.PayEntity;

/**
 * 
 * by： 陶复平
 * 日期：2018年7月24日 下午2:26:40
 * 支付宝支付
 */
@RestController
public class AliPayAction extends BaseAction {
	
	@ResponseBody
	@RequestMapping(value = "alipay.do", method = RequestMethod.POST)
	public CommonResponse alipay() throws AlipayApiException {
		Map<String, Object> datas = new HashMap<String, Object>();
		//初始化参数
		PayEntity aliPayPublicEntity = new PayEntity();
		List<GoodsDetail> goodsList = new ArrayList<>();//商品明细列表，填写商品详细信息
		GoodsDetail aliPayGoodsDetailEntity = new GoodsDetail();
		aliPayPublicEntity.setOut_trade_no("20180918112145");//商户订单号
		aliPayPublicEntity.setSubject("xxx品牌xxx门店当面付扫码消费");//订单标题
		aliPayPublicEntity.setTotal_amount(new BigDecimal("0.01"));//订单总金额
		aliPayPublicEntity.setBody("购买商品3件共20.00元");//订单描述
		aliPayGoodsDetailEntity.setGoods_id("123");//商品ID
		aliPayGoodsDetailEntity.setGoods_name("iphone8");//商品名称
		aliPayGoodsDetailEntity.setPrice(new BigDecimal("1000"));//商品金额，单价为分
		aliPayGoodsDetailEntity.setQuantity(1);//商品数量
		goodsList.add(aliPayGoodsDetailEntity);
		aliPayPublicEntity.setGoods_detail(goodsList);//商品详细信息
		AlipayClient alipayClient = 
				new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
						aliPayPublicEntity.getApp_id(),
						"",
						aliPayPublicEntity.getFormat(),
						aliPayPublicEntity.getCharset(),
						"",
						aliPayPublicEntity.getSign_type());//实例化支付客户端
		AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
		request.setNotifyUrl(aliPayPublicEntity.getNotify_url());//支付回调路径
		String bizContent = JSONObject.toJSONString(aliPayPublicEntity);
		request.setBizContent(bizContent);
		System.out.println("bizContent:"+bizContent);
		AlipayTradePrecreateResponse response = alipayClient.execute(request);
		if(response!=null) {
			String code = response.getCode();//响应码
			String msg = response.getMsg();//响应消息内容
			String outTradeNo = response.getOutTradeNo();//商户订单号
			String qrCode = response.getQrCode();//二维码信息
			datas.put("qrCode", qrCode);
			datas.put("outTradeNo", outTradeNo);
		}
		return getSuccess(datas);
	}
}
