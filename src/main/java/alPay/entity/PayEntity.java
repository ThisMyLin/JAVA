package alPay.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 支付宝支付实体
 * 日期：2018/07/20 16:11
 * by 陶复平
 */
public class PayEntity {

	//公共参数
	
	/**
	 * 支付宝分配给开发者的应用ID
	 */
	private String app_id = "";
	
	/**
	 * 接口名称
	 */
	private String method = "alipay.trade.precreate";
	
	/**
	 * 仅支持JSON
	 * 可以不传
	 */
	private String format = "json";
	
	/**
	 * 请求使用的编码格式，如utf-8,gbk,gb2312等
	 */
	private String charset = "utf-8";
	
	/**
	 * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	 */
	private String sign_type = "RSA2";
	
	/**
	 * 商户请求参数的签名串，详见签名
	 */
	private String sign;
	
	/**
	 * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
	 */
	private String timestamp;
	
	/**
	 * 调用的接口版本，固定为：1.0
	 */
	private String version = "1.0";
	
	/**
	 * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
	 * 可以不传
	 */
	private String notify_url;
	
	/**
	 * 详见应用授权概述
	 * 可以不传
	 */
	private String app_auth_token;
	
	
	//请求参数
	
	/**
	 * 商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
	 * 必选
	 */
	private String out_trade_no;
	
	/**
	 * 卖家支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
	 * 可选
	 */
	private String seller_id;
	
	/**
	 * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果同时传入了【打折金额】，【不可打折金额】，【订单总金额】三者，则必须满足如下条件：【订单总金额】=【打折金额】+【不可打折金额】
	 * 必选
	 */
	private BigDecimal total_amount;
	
	/**
	 * 可打折金额. 参与优惠计算的金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果该值未传入，但传入了【订单总金额】，【不可打折金额】则该值默认为【订单总金额】-【不可打折金额】
	 * 可选
	 */
	private BigDecimal discountable_amount;
	
	/**
	 * 订单标题
	 * 必选
	 */
	private String subject;
	
	/**
	 * 订单包含的商品列表信息.json格式. 其它说明详见：“商品明细说明”
	 * 可选
	 */
	private List<GoodsDetail> goods_detail;
	
	/**
	 * 对交易或商品的描述
	 * 可选
	 */
	private String body;
	
	/**
	 * 商户操作员编号
	 * 可选
	 */
	private String operator_id;
	
	/**
	 * 商户门店编号
	 * 可选
	 */
	private String store_id;
	
	/**
	 * 禁用渠道，用户不可用指定渠道支付 当有多个渠道时用“,”分隔 注，与enable_pay_channels互斥 渠道列表：https://docs.open.alipay.com/common/wifww7
	 * 可选
	 */
	private String disable_pay_channels;
	
	/**
	 * 可用渠道，用户只能在指定渠道范围内支付 当有多个渠道时用“,”分隔 注，与disable_pay_channels互斥 渠道列表
	 * 可选
	 */
	private String enable_pay_channels;
	
	/**
	 * 商户机具终端编号
	 * 可选
	 */
	private String terminal_id;
	
	/**
	 * 业务扩展参数
	 * 可选
	 */
	private ExtendParams extend_params;
	
	/**
	 * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
	 * 可选
	 */
	private String timeout_express;
	
	/**
	 * 描述结算信息，json格式，详见结算参数说明
	 */
	private SettleInfo settle_info;
	
	/**
	 * 商户传入业务信息，具体值要和支付宝约定，应用于安全，营销等参数直传场景，格式为json格式
	 * 可选
	 */
	private String business_params;
	
	/**
	 * 该笔订单允许的最晚付款时间，逾期将关闭交易，从生成二维码开始计时。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
	 * 可选
	 */
	private String qr_code_timeout_express;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getApp_auth_token() {
		return app_auth_token;
	}

	public void setApp_auth_token(String app_auth_token) {
		this.app_auth_token = app_auth_token;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	public BigDecimal getDiscountable_amount() {
		return discountable_amount;
	}

	public void setDiscountable_amount(BigDecimal discountable_amount) {
		this.discountable_amount = discountable_amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<GoodsDetail> getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(List<GoodsDetail> goods_detail) {
		this.goods_detail = goods_detail;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getDisable_pay_channels() {
		return disable_pay_channels;
	}

	public void setDisable_pay_channels(String disable_pay_channels) {
		this.disable_pay_channels = disable_pay_channels;
	}

	public String getEnable_pay_channels() {
		return enable_pay_channels;
	}

	public void setEnable_pay_channels(String enable_pay_channels) {
		this.enable_pay_channels = enable_pay_channels;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public ExtendParams getExtend_params() {
		return extend_params;
	}

	public void setExtend_params(ExtendParams extend_params) {
		this.extend_params = extend_params;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public SettleInfo getSettle_info() {
		return settle_info;
	}

	public void setSettle_info(SettleInfo settle_info) {
		this.settle_info = settle_info;
	}

	public String getBusiness_params() {
		return business_params;
	}

	public void setBusiness_params(String business_params) {
		this.business_params = business_params;
	}

	public String getQr_code_timeout_express() {
		return qr_code_timeout_express;
	}

	public void setQr_code_timeout_express(String qr_code_timeout_express) {
		this.qr_code_timeout_express = qr_code_timeout_express;
	}

	@Override
	public String toString() {
		return "PayEntity [app_id=" + app_id + ", method=" + method + ", format=" + format + ", charset=" + charset
				+ ", sign_type=" + sign_type + ", sign=" + sign + ", timestamp=" + timestamp + ", version=" + version
				+ ", notify_url=" + notify_url + ", app_auth_token=" + app_auth_token + ", out_trade_no=" + out_trade_no
				+ ", seller_id=" + seller_id + ", total_amount=" + total_amount + ", discountable_amount="
				+ discountable_amount + ", subject=" + subject + ", goods_detail=" + goods_detail + ", body=" + body
				+ ", operator_id=" + operator_id + ", store_id=" + store_id + ", disable_pay_channels="
				+ disable_pay_channels + ", enable_pay_channels=" + enable_pay_channels + ", terminal_id=" + terminal_id
				+ ", extend_params=" + extend_params + ", timeout_express=" + timeout_express + ", settle_info="
				+ settle_info + ", business_params=" + business_params + ", qr_code_timeout_express="
				+ qr_code_timeout_express + "]";
	}
}
