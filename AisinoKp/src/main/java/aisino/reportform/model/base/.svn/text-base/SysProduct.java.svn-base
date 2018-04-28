package aisino.reportform.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SYS_PRODUCT", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysProduct {
	private String id;
	private String product_name;
	private String tax_category;
	private double rate;
	private String product_number;
	private double tax_price;
	private Date create_date;
	private Date update_date;
	private String unit;
	private String org_id;

	public SysProduct(String id, String product_name, String tax_category,
			String product_number, double tax_price, Date create_date) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.tax_category = tax_category;
		this.product_number = product_number;
		this.tax_price = tax_price;
		this.create_date = create_date;
	}

	public SysProduct() {
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PRODUCT_NAME", length = 100)
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String productName) {
		product_name = productName;
	}
	@Column(name = "TAX_CATEGORY", length = 100)
	public String getTax_category() {
		return tax_category;
	}

	public void setTax_category(String taxCategory) {
		tax_category = taxCategory;
	}
	@Column(name = "PRODUCT_NUMBER", length = 20)
	public String getProduct_number() {
		return product_number;
	}

	public void setProduct_number(String productNumber) {
		product_number = productNumber;
	}
	@Column(name = "TAX_PRICE", length = 10)
	public double getTax_price() {
		return tax_price;
	}

	public void setTax_price(double taxPrice) {
		tax_price = taxPrice;
	}
	@Column(name = "CREATE_DATE")
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}

	@Column(name = "UPDATE_DATE")
	public Date getUpdate_date() {
		return update_date;
	}
	
	
	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "ORG_ID")
	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String orgId) {
		org_id = orgId;
	}

	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}
	@Transient
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	

}
