package alPay.action.pay;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import alPay.action.BaseAction;
import alPay.common.CommonResponse;

/**
 * 
 * by： 陶复平
 * 日期：2018年7月24日 下午2:26:40
 * 支付宝支付
 */
@RestController
public class AliPayAction extends BaseAction {
	
	@RequestMapping(value = "/alipay.do",method= {RequestMethod.POST})
	public CommonResponse aliPay() {
		String userAgent = request.getHeader("User-Agent");
		System.out.println(userAgent);
		return getSuccess();
	}
}
