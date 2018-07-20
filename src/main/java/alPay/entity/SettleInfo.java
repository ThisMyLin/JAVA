package alPay.entity;

import java.util.List;

/**
 * by 陶复平
 * 日期：2018/07/20 16:58
 * 描述结算信息
 */
public class SettleInfo {

	/**
	 * 结算详细信息，json数组，目前只支持一条。
	 * 必填
	 */
	private List<SettleDetailInfo> settle_detail_infos;
	
	/**
	 * 商户id类型
	 * 可选
	 */
	private String merchant_type;

	public List<SettleDetailInfo> getSettle_detail_infos() {
		return settle_detail_infos;
	}

	public void setSettle_detail_infos(List<SettleDetailInfo> settle_detail_infos) {
		this.settle_detail_infos = settle_detail_infos;
	}

	public String getMerchant_type() {
		return merchant_type;
	}

	public void setMerchant_type(String merchant_type) {
		this.merchant_type = merchant_type;
	}

	@Override
	public String toString() {
		return "SettleInfo [settle_detail_infos=" + settle_detail_infos + ", merchant_type=" + merchant_type + "]";
	}
}
