package alPay.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * by： 陶复平
 * 日期：2018年7月26日 下午2:42:40
 * 拦截器
 */
public class HandleInterceptorAll implements HandlerInterceptor {
	
	private Logger logger = Logger.getLogger(HandleInterceptorAll.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Enumeration parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			String parameter = parameterNames.nextElement().toString();
			String parameterV = request.getParameter(parameter);
			logger.info("请求参数："+parameter+"="+parameterV);
		}
		String url = request.getRequestURL().toString();//获取请求的路径
		String methodType = request.getMethod();//请求方式
		if(url.endsWith(".do")) {
			String requestType = request.getHeader("User-Agent");//获取请求类型
			logger.info("请求类型："+requestType+",请求路径："+url+",请求方式："+methodType);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
