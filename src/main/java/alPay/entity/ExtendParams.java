package alPay.entity;

/**
 * by 陶复平
 * 日期：2018/07/20 16:55
 * 业务扩展
 */
public class ExtendParams {

	/**
	 * 系统商编号 该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
	 * 可选
	 */
	private String sys_service_provider_id;
	
	/**
	 * 行业数据回流信息, 详见：地铁支付接口参数补充说明
	 * 可选
	 */
	private String industry_reflux_info;
	
	/**
	 * 卡类型
	 * 可选
	 */
	private String card_type;

	public String getSys_service_provider_id() {
		return sys_service_provider_id;
	}

	public void setSys_service_provider_id(String sys_service_provider_id) {
		this.sys_service_provider_id = sys_service_provider_id;
	}

	public String getIndustry_reflux_info() {
		return industry_reflux_info;
	}

	public void setIndustry_reflux_info(String industry_reflux_info) {
		this.industry_reflux_info = industry_reflux_info;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	@Override
	public String toString() {
		return "ExtendParams [sys_service_provider_id=" + sys_service_provider_id + ", industry_reflux_info="
				+ industry_reflux_info + ", card_type=" + card_type + "]";
	}
}
