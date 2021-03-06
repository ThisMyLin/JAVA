package alPay.entity;

/**
 * 
 * by： 陶复平
 * 日期：2018年7月26日 下午5:03:38
 * 微信支付实体
 */
public class WxPayEntity {

	/**
	 * 公众账号ID
	 * 微信支付分配的公众账号ID（企业号corpid即为此appId）
	 * 必填 是
	 */
	private String appid = "";
	
	/**
	 * 商户号
	 * 微信支付分配的商户号
	 * 必填 是
	 */
	private String mch_id = "";
	
	/**
	 * 商户子账号
	 */
	private String sub_mch_id = "";
	
	/**
	 * 设备号
	 * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
	 * 必填 否
	 */
	private String device_info;
	
	/**
	 * 随机字符串
	 * 随机字符串，长度要求在32位以内。推荐随机数生成算法
	 * 必填 是
	 */
	private String nonce_str;
	
	/**
	 * 签名
	 * 通过签名算法计算得出的签名值，详见签名生成算法
	 * 必填 是
	 */
	private String sign;
	
	/**
	 * 签名类型
	 * 签名类型，默认为MD5，支持HMAC-SHA256和MD5
	 * 必填 是
	 */
	private String sign_type;
	
	/**
	 * 商品描述
	 * 商品简单描述，该字段请按照规范传递，具体请见参数规定
	 * 必填 是
	 */
	private String body;
	
	/**
	 * 商品详情
	 * 商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明”
	 * 必填 否
	 */
	private String detail;
	
	/**
	 * 附加数据
	 * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
	 * 必填 否
	 */
	private String attach;
	
	/**
	 * 商户订单号
	 * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
	 * 必填 是
	 */
	private String out_trade_no;
	
	/**
	 * 标价币种
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
	 * 必填 否
	 */
	private String fee_type;
	
	/**
	 * 标价金额
	 * 订单总金额，单位为分，详见支付金额
	 * 必填 是
	 */
	private int total_fee;
	
	/**
	 * 终端IP
	 * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	 * 必填 是
	 */
	private String spbill_create_ip;
	
	/**
	 * 交易起始时间
	 * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * 必填 否
	 */
	private String time_start;
	
	/**
	 * 交易结束时间
	 * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
	 * 必填 否
	 */
	private String time_expire;
	
	/**
	 * 订单优惠标记
	 * 订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
	 * 必填 否
	 */
	private String goods_tag;
	
	/**
	 * 通知地址
	 * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
	 * 必填 是
	 */
	private String notify_url = "";
	
	/**
	 * 交易类型
	 * JSAPI 公众号支付 NATIVE 扫码支付 APP APP支付说明详见参数规定
	 * 必填 是
	 */
	private String trade_type = "NATIVE";
	
	/**
	 * 商品ID
	 * trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义
	 * 必填 否
	 */
	private String product_id;
	
	/**
	 * 指定支付方式
	 * 上传此参数no_credit--可限制用户不能使用信用卡支付
	 * 必填 否
	 */
	private String limit_pay;
	
	/**
	 * 用户标识
	 * trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
	 * 必填 否
	 */
	private String openid;
	
	/**
	 * 场景信息
	 * 该字段用于上报场景信息，目前支持上报实际门店信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，字段详细说明请点击行前的+展开
	 * 必填 否
	 */
	private String scene_info;

	
	public WxPayEntity(String appid, String mch_id, String device_info, String nonce_str, String sign, String sign_type,
			String body, String detail, String attach, String out_trade_no, String fee_type, int total_fee,
			String spbill_create_ip, String time_start, String time_expire, String goods_tag, String notify_url,
			String trade_type, String product_id, String limit_pay, String openid, String scene_info) {
		this.appid = appid;
		this.mch_id = mch_id;
		this.device_info = device_info;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.sign_type = sign_type;
		this.body = body;
		this.detail = detail;
		this.attach = attach;
		this.out_trade_no = out_trade_no;
		this.fee_type = fee_type;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.time_start = time_start;
		this.time_expire = time_expire;
		this.goods_tag = goods_tag;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		this.product_id = product_id;
		this.limit_pay = limit_pay;
		this.openid = openid;
		this.scene_info = scene_info;
	}
	
	public WxPayEntity() {
	}


	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScene_info() {
		return scene_info;
	}

	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}

	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	@Override
	public String toString() {
		return "WxPayEntity [appid=" + appid + ", mch_id=" + mch_id + ", sub_mch_id=" + sub_mch_id + ", device_info="
				+ device_info + ", nonce_str=" + nonce_str + ", sign=" + sign + ", sign_type=" + sign_type + ", body="
				+ body + ", detail=" + detail + ", attach=" + attach + ", out_trade_no=" + out_trade_no + ", fee_type="
				+ fee_type + ", total_fee=" + total_fee + ", spbill_create_ip=" + spbill_create_ip + ", time_start="
				+ time_start + ", time_expire=" + time_expire + ", goods_tag=" + goods_tag + ", notify_url="
				+ notify_url + ", trade_type=" + trade_type + ", product_id=" + product_id + ", limit_pay=" + limit_pay
				+ ", openid=" + openid + ", scene_info=" + scene_info + "]";
	}
}
