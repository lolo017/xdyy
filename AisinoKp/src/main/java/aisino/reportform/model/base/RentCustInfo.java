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
@Table(name = "RENT_CUST_INFO", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class RentCustInfo {
	
	private Long rent_cust_id;
	private String rent_cust_name;
	private String rent_cust_code;
	private String tel;
	private String contact;
	private String address;
	private Long rent_type;
	private Long is_collection;
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	
	
	@Id
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
	@Column(name = "RENT_TYPE")
	public Long getRent_type() {
		return rent_type;
	}
	public void setRent_type(Long rent_type) {
		this.rent_type = rent_type;
	}
	@Column(name = "IS_COLLECTION")
	public Long getIs_collection() {
		return is_collection;
	}
	public void setIs_collection(Long is_collection) {
		this.is_collection = is_collection;
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
	
	

}
