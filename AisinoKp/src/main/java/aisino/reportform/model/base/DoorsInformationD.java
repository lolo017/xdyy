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
@Table(name = "DOORS_INFOMATION_DETAIL", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DoorsInformationD {
	
	private Long doors_id;
	private Long doors_line_id;
	private String doors_floor;
	private Double doors_size;
	private Double doors_rent;
	private Long floor_status;
	private String create_by;
	private Date create_date;
	
	
	@Column(name = "DOORS_ID")
	public Long getDoors_id() {
		return doors_id;
	}
	public void setDoors_id(Long doors_id) {
		this.doors_id = doors_id;
	}
	@Id
	@Column(name = "DOORS_LINE_ID")
	public Long getDoors_line_id() {
		return doors_line_id;
	}
	public void setDoors_line_id(Long doors_line_id) {
		this.doors_line_id = doors_line_id;
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
	@Column(name = "FLOOR_STATUS")
	public Long getFloor_status() {
		return floor_status;
	}
	public void setFloor_status(Long floor_status) {
		this.floor_status = floor_status;
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
