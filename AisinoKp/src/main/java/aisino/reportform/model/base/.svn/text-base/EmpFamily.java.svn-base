package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * emptitle model
 * @author 廖宸宇
 * @date 2015-5-26
 */
@Entity
@Table(name = "EMPFAMILY", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EmpFamily implements java.io.Serializable{
	private String id;
	private String name;
	private String relationship;//关系
	private String company;//工作单位
	private String phone;//电话
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
	@Column(name = "RELATIONSHIP", length = 50)
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	@Column(name = "COMPANY", length = 50)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name = "PHONE", length = 50)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPDATAID")
	public SyEmpData getSyEmpData() {
		return syEmpData;
	}
	public void setSyEmpData(SyEmpData syEmpData) {
		this.syEmpData = syEmpData;
	}
	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
