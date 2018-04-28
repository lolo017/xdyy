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
@Table(name = "CONTRACT_CHARGE_INFO", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ContractChargeInfo {
	private Long charge_id;
	private Long rent_cust_id;
	private String rent_cust_code;
	private String rent_cust_name;
	private Long contract_id;
	private Date charge_date;
	private Date start_date;
	private Date end_date;
	private Long payment_method;
	private Double rent_sum;
	private Long step_amount;
	private Double late_fee;
	private Double pre_fee;
	private Long is_invoice;
	private Long is_charge;
	private Double water_fee;
	private Double electric_fee;
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	
	@Id
	@Column(name = "CHARGE_ID")
	public Long getCharge_id() {
		return charge_id;
	}
	public void setCharge_id(Long charge_id) {
		this.charge_id = charge_id;
	}
	@Column(name = "RENT_CUST_ID")
	public Long getRent_cust_id() {
		return rent_cust_id;
	}
	public void setRent_cust_id(Long rent_cust_id) {
		this.rent_cust_id = rent_cust_id;
	}
	@Column(name = "RENT_CUST_CODE")
	public String getRent_cust_code() {
		return rent_cust_code;
	}
	public void setRent_cust_code(String rent_cust_code) {
		this.rent_cust_code = rent_cust_code;
	}
	@Column(name = "RENT_CUST_NAME")
	public String getRent_cust_name() {
		return rent_cust_name;
	}
	public void setRent_cust_name(String rent_cust_name) {
		this.rent_cust_name = rent_cust_name;
	}
	@Column(name = "CONTRACT_ID")
	public Long getContract_id() {
		return contract_id;
	}
	public void setContract_id(Long contract_id) {
		this.contract_id = contract_id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHARGE_DATE")
	public Date getCharge_date() {
		return charge_date;
	}
	public void setCharge_date(Date charge_date) {
		this.charge_date = charge_date;
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
	@Column(name = "RENT_SUM")
	public Double getRent_sum() {
		return rent_sum;
	}
	public void setRent_sum(Double rent_sum) {
		this.rent_sum = rent_sum;
	}
	@Column(name = "STEP_AMOUNT")
	public Long getStep_amount() {
		return step_amount;
	}
	public void setStep_amount(Long step_amount) {
		this.step_amount = step_amount;
	}
	@Column(name = "LATE_FEE")
	public Double getLate_fee() {
		return late_fee;
	}
	public void setLate_fee(Double late_fee) {
		this.late_fee = late_fee;
	}
	@Column(name = "PRE_FEE")
	public Double getPre_fee() {
		return pre_fee;
	}
	public void setPre_fee(Double pre_fee) {
		this.pre_fee = pre_fee;
	}
	@Column(name = "IS_INVOICE")
	public Long getIs_invoice() {
		return is_invoice;
	}
	public void setIs_invoice(Long is_invoice) {
		this.is_invoice = is_invoice;
	}
	@Column(name = "IS_CHARGE")
	public Long getIs_charge() {
		return is_charge;
	}
	public void setIs_charge(Long is_charge) {
		this.is_charge = is_charge;
	}
	@Column(name = "WATER_FEE")
	public Double getWater_fee() {
		return water_fee;
	}
	public void setWater_fee(Double water_fee) {
		this.water_fee = water_fee;
	}
	@Column(name = "ELECTRIC_FEE")
	public Double getElectric_fee() {
		return electric_fee;
	}
	public void setElectric_fee(Double electric_fee) {
		this.electric_fee = electric_fee;
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
