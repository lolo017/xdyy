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
@Table(name = "CONTRACT_INFO_DETAIL", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ContractInfoDetail {
	
	private Long contract_id;
	private Long contract_line_id;
	private Long doors_line_id;
	private Long doors_id;
	private String doors_floor;
	private Double doors_size;
	private Double doors_rent;
	private Double rent_rise_rote;
	private Long is_step_charge;
	private Double doors_rent_sum;
	private Long rent_rise_type;
	private Double rent_rise_sum;
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	

	@Id
	@Column(name = "CONTRACT_LINE_ID")
	public Long getContract_line_id() {
		return contract_line_id;
	}
	public void setContract_line_id(Long contract_line_id) {
		this.contract_line_id = contract_line_id;
	}
	@Column(name = "CONTRACT_ID")
	public Long getContract_id() {
		return contract_id;
	}
	public void setContract_id(Long contract_id) {
		this.contract_id = contract_id;
	}
	@Column(name = "DOORS_LINE_ID")
	public Long getDoors_line_id() {
		return doors_line_id;
	}
	public void setDoors_line_id(Long doors_line_id) {
		this.doors_line_id = doors_line_id;
	}
	@Column(name = "DOORS_ID")
	public Long getDoors_id() {
		return doors_id;
	}
	public void setDoors_id(Long doors_id) {
		this.doors_id = doors_id;
	}
	@Column(name = "DOORS_FLOOR")
	public String getDoors_floor() {
		return doors_floor;
	}
	public void setDoors_floor(String doors_floor) {
		this.doors_floor = doors_floor;
	}
	@Column(name = "DOORS_SIZE")
	public Double getDoors_size() {
		return doors_size;
	}
	public void setDoors_size(Double doors_size) {
		this.doors_size = doors_size;
	}
	@Column(name = "DOORS_RENT")
	public Double getDoors_rent() {
		return doors_rent;
	}
	public void setDoors_rent(Double doors_rent) {
		this.doors_rent = doors_rent;
	}
	@Column(name = "RENT_RISE_ROTE")
	public Double getRent_rise_rote() {
		return rent_rise_rote;
	}
	public void setRent_rise_rote(Double rent_rise_rote) {
		this.rent_rise_rote = rent_rise_rote;
	}
	@Column(name = "IS_STEP_CHARGE")
	public Long getIs_step_charge() {
		return is_step_charge;
	}
	public void setIs_step_charge(Long is_step_charge) {
		this.is_step_charge = is_step_charge;
	}
	@Column(name = "DOORS_RENT_SUM")
	public Double getDoors_rent_sum() {
		return doors_rent_sum;
	}
	public void setDoors_rent_sum(Double doors_rent_sum) {
		this.doors_rent_sum = doors_rent_sum;
	}
	@Column(name = "RENT_RISE_TYPE")
	public Long getRent_rise_type() {
		return rent_rise_type;
	}
	public void setRent_rise_type(Long rent_rise_type) {
		this.rent_rise_type = rent_rise_type;
	}
	@Column(name = "RENT_RISE_SUM")
	public Double getRent_rise_sum() {
		return rent_rise_sum;
	}
	public void setRent_rise_sum(Double rent_rise_sum) {
		this.rent_rise_sum = rent_rise_sum;
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
