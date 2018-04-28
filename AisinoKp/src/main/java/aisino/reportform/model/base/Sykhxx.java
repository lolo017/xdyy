package aisino.reportform.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "T_KHXX", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Sykhxx {
	private String id;
	private String name;
	private String taxcode;
	private String dzdh;
	private String telphone;
	private String mobile;
	private String org_id;
	private String creater_id;
	private Date create_date;
	private Date update_date;
	public Sykhxx(String id, String name, String taxcode, String dzdh) {
		super();
		this.id = id;
		this.name = name;
		this.taxcode = taxcode;
		this.dzdh = dzdh;
	}
	public Sykhxx() {
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
	
	@Column(name="NAME",length=250)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="TAXCODE",length=30)
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	
	@Column(name="DZDH" ,length=200)
	public String getDzdh() {
		return dzdh;
	}
	public void setDzdh(String dzdh) {
		this.dzdh = dzdh;
	}
	
	@Column(name="TELPHONE" ,length=20)
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Column(name="MOBILE" ,length=20)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name="ORG_ID" ,length=20)
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String orgId) {
		org_id = orgId;
	}
	
	@Column(name="CREATER_ID" ,length=50)
	public String getCreater_id() {
		return creater_id;
	}
	public void setCreater_id(String createrId) {
		creater_id = createrId;
	}
	@Column(name="CREATE_DATE" )
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}
	@Column(name="UPDATE_DATE" )
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}
	
	
}
