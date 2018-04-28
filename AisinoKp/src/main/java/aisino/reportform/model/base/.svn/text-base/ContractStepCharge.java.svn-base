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
@Table(name = "CONTRACT_STEP_CHARGE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ContractStepCharge {
	private Long step_id;
	private Long contract_line_id;
	private Long contract_id;
	private Date start_date;
	private Date end_date;
	private Long cal_type;
	private Double doors_rent;
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	
	@Id
	@Column(name = "STEP_ID")
	public Long getStep_id() {
		return step_id;
	}
	public void setStep_id(Long step_id) {
		this.step_id = step_id;
	}
	
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
	@Column(name = "CAL_TYPE")
	public Long getCal_type() {
		return cal_type;
	}
	public void setCal_type(Long cal_type) {
		this.cal_type = cal_type;
	}
	@Column(name = "DOORS_RENT")
	public Double getDoors_rent() {
		return doors_rent;
	}
	public void setDoors_rent(Double doors_rent) {
		this.doors_rent = doors_rent;
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
