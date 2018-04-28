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
@Table(name = "DOORS_INFOMATION_MASTER", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DoorsInformationM {
	
	private Long doors_id;
	private String doors_name;
	private String doors_addrss;
	private Long classfy_id;
	private Long doors_status;
	private String create_by;
	private Date create_date;
	
	@Id
	@Column(name = "DOORS_ID")
	public Long getDoors_id() {
		return doors_id;
	}
	public void setDoors_id(Long doors_id) {
		this.doors_id = doors_id;
	}
	@Column(name = "DOORS_NAME")
	public String getDoors_name() {
		return doors_name;
	}
	public void setDoors_name(String doors_name) {
		this.doors_name = doors_name;
	}
	@Column(name = "DOORS_ADDRSS")
	public String getDoors_addrss() {
		return doors_addrss;
	}
	public void setDoors_addrss(String doors_addrss) {
		this.doors_addrss = doors_addrss;
	}
	@Column(name = "CLASSFY_ID")
	public Long getClassfy_id() {
		return classfy_id;
	}
	public void setClassfy_id(Long classfy_id) {
		this.classfy_id = classfy_id;
	}
	@Column(name = "DOORS_STATUS")
	public Long getDoors_status() {
		return doors_status;
	}
	public void setDoors_status(Long doors_status) {
		this.doors_status = doors_status;
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
	
	

}
