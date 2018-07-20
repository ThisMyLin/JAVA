package alPay.entity;

import java.math.BigDecimal;

/**
 * by 陶复平
 * 日期：2018/07/20 16:51
 * 商品列表信息
 */
public class GoodsDetail {

	/**
	 * 商品的编号
	 * 必填
	 */
	private String goods_id;
	
	/**
	 * 商品名称
	 * 必填
	 */
	private String goods_name;
	
	/**
	 * 商品数量
	 * 必填
	 */
	private Integer quantity;
	
	/**
	 * 商品单价，单位为元
	 * 必填
	 */
	private BigDecimal price;
	
	/**
	 * 商品类目
	 * 可选
	 */
	private String goods_category;
	
	/**
	 * 商品描述信息
	 * 可选
	 */
	private String body;
	
	/**
	 * 商品的展示地址
	 * 可选
	 */
	private String show_url;

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getGoods_category() {
		return goods_category;
	}

	public void setGoods_category(String goods_category) {
		this.goods_category = goods_category;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	@Override
	public String toString() {
		return "GoodsDetail [goods_id=" + goods_id + ", goods_name=" + goods_name + ", quantity=" + quantity
				+ ", price=" + price + ", goods_category=" + goods_category + ", body=" + body + ", show_url="
				+ show_url + "]";
	}
}
