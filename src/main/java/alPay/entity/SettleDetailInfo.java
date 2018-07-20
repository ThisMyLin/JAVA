package alPay.entity;

/**
 * by 陶复平
 * 日期：2018/07/20 17:04
 * 结算详细信息
 */
public class SettleDetailInfo {

	/**
	 *  cardSerialNo：结算收款方的银行卡编号; 
		userId：表示是支付宝账号对应的支付宝唯一用户号; 
		loginName：表示是支付宝登录号；
		必填
	 */
	private String trans_in_type;
	
	/**
	 * 结算收款方。当结算收款方类型是cardSerialNo时，本参数为用户在支付宝绑定的卡编号；结算收款方类型是userId时，本参数为用户的支付宝账号对应的支付宝唯一用户号，以2088开头的纯16位数字；当结算收款方类型是loginName时，本参数为用户的支付宝登录号
	 * 必填
	 */
	private String trans_in;
	
	/**
	 *  结算汇总维度，按照这个维度汇总成批次结算，由商户指定。 

		目前需要和结算收款方账户类型为cardSerialNo配合使用
		
		可选
	 */
	private String summary_dimension;
	
	/**
	 * 结算的金额，单位为元。目前必须和交易金额相同
	 * 必填
	 */
	private Integer amount;

	public String getTrans_in_type() {
		return trans_in_type;
	}

	public void setTrans_in_type(String trans_in_type) {
		this.trans_in_type = trans_in_type;
	}

	public String getTrans_in() {
		return trans_in;
	}

	public void setTrans_in(String trans_in) {
		this.trans_in = trans_in;
	}

	public String getSummary_dimension() {
		return summary_dimension;
	}

	public void setSummary_dimension(String summary_dimension) {
		this.summary_dimension = summary_dimension;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "SettleDetailInfo [trans_in_type=" + trans_in_type + ", trans_in=" + trans_in + ", summary_dimension="
				+ summary_dimension + ", amount=" + amount + "]";
	}
}
