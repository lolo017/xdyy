package aisino.reportform.model.fsg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
* @Title:FsgOrderHead 
* @Description: 饭烧光订单头
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2018年1月4日 下午5:19:41
 */
public class FsgOrderHead {
	private String code;	//单据号
	private int qty;	//数量
	private BigDecimal payment;	//支付金额
	private BigDecimal post_fee;	//物流费用
	private String receiver_name;	//收货人姓名
	private String receiver_mobile;	//收货人手机号
	private String receiver_address;	//收货人地址
	private String shop_name;	//店铺名
	private String platform_code;	//平台编号
	private ArrayList<Details> details;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public BigDecimal getPost_fee() {
		return post_fee;
	}
	public void setPost_fee(BigDecimal post_fee) {
		this.post_fee = post_fee;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_mobile() {
		return receiver_mobile;
	}
	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getPlatform_code() {
		return platform_code;
	}
	public void setPlatform_code(String platform_code) {
		this.platform_code = platform_code;
	}
	public ArrayList<Details> getDetails() {
		return details;
	}
	public void setDetails(ArrayList<Details> details) {
		this.details = details;
	}
	
}
