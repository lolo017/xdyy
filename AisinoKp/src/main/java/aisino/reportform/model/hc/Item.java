package aisino.reportform.model.hc;

import java.math.BigDecimal;

public class Item {
	private BigDecimal goods_price;
	private BigDecimal goods_quantity;
	private String goods_sku;
	private String goods_title;
	private String invoice_tid;
	private String tid;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	private String measurement;
	private String property;
	private BigDecimal quoted_price;
	private String title;
	public BigDecimal getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}
	public BigDecimal getGoods_quantity() {
		return goods_quantity;
	}
	public void setGoods_quantity(BigDecimal goods_quantity) {
		this.goods_quantity = goods_quantity;
	}
	public String getGoods_sku() {
		return goods_sku;
	}
	public void setGoods_sku(String goods_sku) {
		this.goods_sku = goods_sku;
	}
	public String getGoods_title() {
		return goods_title;
	}
	public void setGoods_title(String goods_title) {
		this.goods_title = goods_title;
	}
	public String getInvoice_tid() {
		return invoice_tid;
	}
	public void setInvoice_tid(String invoice_tid) {
		this.invoice_tid = invoice_tid;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public BigDecimal getQuoted_price() {
		return quoted_price;
	}
	public void setQuoted_price(BigDecimal quoted_price) {
		this.quoted_price = quoted_price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
