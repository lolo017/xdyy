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
@Table(name = "EMPDEGREE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EmpDegree implements java.io.Serializable{
	private String id;
	private String degree;//学历
	private String school;//毕业学校
    private Date graduationDate;//毕业时间
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
	@Column(name = "DEGREE", length = 50)
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	@Column(name = "SCHOOL", length = 50)
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "GRADUATION_DATE", length = 10)
	public Date getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
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
