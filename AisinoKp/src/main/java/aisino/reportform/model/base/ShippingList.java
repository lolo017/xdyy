package aisino.reportform.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SHIPPING_LIST"
,schema="SSHE"
)
public class ShippingList implements java.io.Serializable{

	/**
	 * CMK
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String ems;
	private String distributionNum;
	private String sender;
	private String recevier;
	private String telphone;
	private String company;
	private String address;
	private String type;
	private String internalInfo;
	private String remarks;
	private String taxCode;
	private String orderNum;
	private String isSms;
	private String amount;
	private String smsType;
	
	public ShippingList() {
		super();
	}
	public ShippingList(String id, String ems, String distributionNum,
			String sender, String recevier, String telphone, String company,
			String address, String type, String internalInfo, String remarks,
			String taxCode, String orderNum, String isSms, String amount, String smsType) {
		super();
		this.id = id;
		this.ems = ems;
		this.distributionNum = distributionNum;
		this.sender = sender;
		this.recevier = recevier;
		this.telphone = telphone;
		this.company = company;
		this.address = address;
		this.type = type;
		this.internalInfo = internalInfo;
		this.remarks = remarks;
		this.taxCode = taxCode;
		this.orderNum = orderNum;
		this.isSms = isSms;
		this.amount = amount;
		this.smsType = smsType;
	}
	@Id
	@GeneratedValue(generator = "uuid")  
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "EMS", length = 20)
	public String getEms() {
		return ems;
	}
	public void setEms(String ems) {
		this.ems = ems;
	}
	@Column(name = "DISTRIBUTION_NUM", length = 10)
	public String getDistributionNum() {
		return distributionNum;
	}
	public void setDistributionNum(String distributionNum) {
		this.distributionNum = distributionNum;
	}
	@Column(name = "SENDER", length = 10)
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	@Column(name = "RECEVIER", length = 10)
	public String getRecevier() {
		return recevier;
	}
	public void setRecevier(String recevier) {
		this.recevier = recevier;
	}
	@Column(name = "TELPHONE", length = 10)
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "INTERNAL_INFO", length = 200)
	public String getInternalInfo() {
		return internalInfo;
	}
	public void setInternalInfo(String internalInfo) {
		this.internalInfo = internalInfo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "TAX_CODE", length = 20)
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	@Column(name = "ORDER_NUM", length = 20)
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	@Column(name = "IS_SMS", length = 4)
	public String getIsSms() {
		return isSms;
	}
	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Column(name = "SMS_TYPE", length = 4)
	public String getSmsType() {
		return smsType;
	}
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}
	
}
