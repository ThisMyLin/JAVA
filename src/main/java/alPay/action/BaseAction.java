package alPay.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import alPay.common.CommonResponse;
import alPay.interceptor.HandleInterceptorAll;

/**
 * 控制层基类
 * by： 陶复平
 * 日期：2018年7月24日 下午2:09:14
 *
 */
@Controller
@RequestMapping(value = "/v1.0")
public class BaseAction {
	
	protected Logger logger = Logger.getLogger(HandleInterceptorAll.class);
	
	@Autowired
	protected HttpServletRequest request;

	/**
	 * 成功返回实体
	 * @param datas
	 * @return
	 */
	protected CommonResponse getSuccess(Map<String, Object> datas) {
		CommonResponse result = new CommonResponse();
		result.setCode(CommonResponse.SUCCESS);
		result.setDatas(datas);
		return result;
	}
	
	/**
	 * 返回成功实体
	 * @return
	 */
	protected CommonResponse getSuccess() {
		CommonResponse result = new CommonResponse();
		result.setCode(CommonResponse.SUCCESS);
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("success", "成功");
		result.setDatas(datas);
		return result;
	}
	
	/**
	 * 失败返回实体
	 * @param error
	 * @return
	 */
	protected CommonResponse getError(String error) {
		CommonResponse result = new CommonResponse();
		result.setCode(CommonResponse.FAIL);
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("error", error);
		return result;
	}
	
	/**
	 * 失败返回实体
	 * @return
	 */
	protected CommonResponse getError() {
		CommonResponse result = new CommonResponse();
		result.setCode(CommonResponse.FAIL);
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("error", "失败");
		return result;
	}
}
