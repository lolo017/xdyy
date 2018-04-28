package aisino.reportform.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "CONTRACT_INFO_MAIN", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ContractInfoMain {
	
	private Long contract_id;
	private Long rent_cust_id;
	private String rent_cust_name;
	private String rent_cust_code;
	private String tel;
	private String contact;
	private String address;
	private Long doors_id;
	private Date start_date;
	private Date end_date;
	private Long payment_method;
	private Long rent_type;
	private Double rent_rise_rote;
	private Long is_invoice_mul;
	private Double contract_sum;
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	private String contract_number;
	private Double pre_sum;
	
	@Id
	@Column(name = "CONTRACT_ID")
	public Long getContract_id() {
		return contract_id;
	}
	public void setContract_id(Long contract_id) {
		this.contract_id = contract_id;
	}
	@Column(name = "RENT_CUST_ID")
	public Long getRent_cust_id() {
		return rent_cust_id;
	}
	public void setRent_cust_id(Long rent_cust_id) {
		this.rent_cust_id = rent_cust_id;
	}
	@Column(name = "RENT_CUST_NAME")
	public String getRent_cust_name() {
		return rent_cust_name;
	}
	public void setRent_cust_name(String rent_cust_name) {
		this.rent_cust_name = rent_cust_name;
	}
	@Column(name = "RENT_CUST_CODE")
	public String getRent_cust_code() {
		return rent_cust_code;
	}
	public void setRent_cust_code(String rent_cust_code) {
		this.rent_cust_code = rent_cust_code;
	}
	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name = "CONTACT")
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "DOORS_ID")
	public Long getDoors_id() {
		return doors_id;
	}
	public void setDoors_id(Long doors_id) {
		this.doors_id = doors_id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE")
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE")
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	@Column(name = "PAYMENT_METHOD")
	public Long getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(Long payment_method) {
		this.payment_method = payment_method;
	}
	@Column(name = "RENT_TYPE")
	public Long getRent_type() {
		return rent_type;
	}
	public void setRent_type(Long rent_type) {
		this.rent_type = rent_type;
	}
	@Column(name = "RENT_RISE_ROTE")
	public Double getRent_rise_rote() {
		return rent_rise_rote;
	}
	public void setRent_rise_rote(Double rent_rise_rote) {
		this.rent_rise_rote = rent_rise_rote;
	}
	@Column(name = "IS_INVOICE_MUL")
	public Long getIs_invoice_mul() {
		return is_invoice_mul;
	}
	public void setIs_invoice_mul(Long is_invoice_mul) {
		this.is_invoice_mul = is_invoice_mul;
	}
	@Column(name = "CONTRACT_SUM")
	public Double getContract_sum() {
		return contract_sum;
	}
	public void setContract_sum(Double contract_sum) {
		this.contract_sum = contract_sum;
	}
	@Column(name = "CREATE_BY")
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Column(name = "UPDATE_BY")
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	@Column(name = "CONTRACT_NUMBER")
	public String getContract_number() {
		return contract_number;
	}
	public void setContract_number(String contract_number) {
		this.contract_number = contract_number;
	}
	@Column(name = "PRE_SUM")
	public Double getPre_sum() {
		return pre_sum;
	}
	public void setPre_sum(Double pre_sum) {
		this.pre_sum = pre_sum;
	}
	
	

}
