package aisino.reportform.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * emptitle model
 * @author 廖宸宇
 * @date 2015-5-26
 */
@Entity
@Table(name = "EMPTITLE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EmpTitle implements java.io.Serializable{
	private String id;
	private String title;//职称
	private String grade;//等级
	private Date getDate;//获得时间
	private SyEmpData syEmpData;
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "TITLE", length = 50)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "GRADE", length = 50)
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "GET_DATE", length = 10)
	public Date getGetDate() {
		return getDate;
	}
	public void setGetDate(Date getDate) {
		this.getDate = getDate;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPDATAID")
	public SyEmpData getSyEmpData() {
		return syEmpData;
	}
	public void setSyEmpData(SyEmpData syEmpData) {
		this.syEmpData = syEmpData;
	}
	
	
	

}
