package aisino.reportform.model.base;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
/**
 * SyEmpLead model
 * @author 廖宸宇
 * @date 2015-3-23
 */
@Entity
@Table(name = "SYEMPLEAD", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SyEmpLead implements java.io.Serializable{
	
	private String state;//节点是否展开
	private String pid;//父ID

	// Fields

	private String empId;
	private SyEmpLead syemplead;//上级
	private String empName;
	private String depId;
	private String depName;
	private Set<SyEmpLead> syempleads = new HashSet<SyEmpLead>(0);//所有下级

	// Constructors

	/** default constructor */
	public SyEmpLead() {
	}

	/** minimal constructor */
	public SyEmpLead(String empId) {
		this.empId = empId;
	}

	/** full constructor */
	public SyEmpLead(String empId, SyEmpLead syemplead, String empName,
			String depId, String depName, 
			Set<SyEmpLead> syempleads) {
		this.empId = empId;
		this.syemplead = syemplead;
		this.empName = empName;
		this.depId = depId;
		this.depName = depName;
		
		this.syempleads = syempleads;
	}

	// Property accessors
	@Id
	@Column(name = "EMP_ID", unique = true, nullable = false, length = 50)
	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@ManyToOne()
	@JoinColumn(name = "LEADER_ID")
	public SyEmpLead getSyemplead() {
		return this.syemplead;
	}

	public void setSyemplead(SyEmpLead syemplead) {
		this.syemplead = syemplead;
	}

	@Column(name = "EMP_NAME", length = 50)
	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Column(name = "DEP_ID", length = 50)
	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Column(name = "DEP_NAME", length = 50)
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syemplead")
	public Set<SyEmpLead> getSyempleads() {
		return this.syempleads;
	}

	public void setSyempleads(Set<SyEmpLead> syempleads) {
		this.syempleads = syempleads;
	}
	
    @Transient
	public String getState() {
    	if(syempleads!=null&&!syempleads.isEmpty()){
    		return "closed";
    	}
    	if(syempleads==null||syempleads.isEmpty()){
    		return "open";
    	}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Transient
	public String getPid() {
		if(syemplead!=null && !StringUtils.isBlank(syemplead.getEmpId())){
    		return syemplead.getEmpId();
    	}
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	

}
