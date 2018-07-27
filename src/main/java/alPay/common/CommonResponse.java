package alPay.common;

import java.util.Map;

/**
 * 
 * by： 陶复平
 * 日期：2018年7月24日 下午2:12:56
 * 通用返回实体
 */
public class CommonResponse {

	//成功返回code
	public static final int SUCCESS = 200;
	//失败返回code
	public static final int FAIL = 400;
	
	private int code;
	private Map<String, Object> datas;
	
	public CommonResponse() {
		super();
	}
	public CommonResponse(int code, Map<String, Object> datas) {
		super();
		this.code = code;
		this.datas = datas;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Map<String, Object> getDatas() {
		return datas;
	}
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}
	@Override
	public String toString() {
		return "CommonResponse [code=" + code + ", datas=" + datas + "]";
	}
	
}
