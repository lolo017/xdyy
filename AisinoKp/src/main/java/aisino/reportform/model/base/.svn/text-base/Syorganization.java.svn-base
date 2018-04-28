package aisino.reportform.model.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SYORGANIZATION", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syorganization implements java.io.Serializable {

	private String pid;// 虚拟属性，用于获得当前机构的父机构ID
	private String state="closed";//节点是否展开
	private boolean leaf=true;//是否是子节点

	private String id;
	private Date createdatetime;
	private Date updatedatetime;
	private String name;
	private String address;
	private String code;
	private String iconCls;
	private Integer seq;
	private Integer levelId;
	private String telphone;
	private String mobile;
	private String tax_code;
	private String bank_account;
	private String org_type;
	private Syorganization syorganization;
	private Set<Syorganization> syorganizations = new HashSet<Syorganization>(0);
	private Set<Syuser> syusers = new HashSet<Syuser>(0);
	private Set<Syresource> syresources = new HashSet<Syresource>(0);

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYORGANIZATION_ID")
	public Syorganization getSyorganization() {
		return this.syorganization;
	}

	public void setSyorganization(Syorganization syorganization) {
		this.syorganization = syorganization;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATETIME", length = 7)
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "CODE", length = 200)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "ICONCLS", length = 100)
	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	@Column(name = "SEQ", precision = 8, scale = 0)
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "syorganization", cascade = CascadeType.ALL)
	public Set<Syorganization> getSyorganizations() {
		return this.syorganizations;
	}

	public void setSyorganizations(Set<Syorganization> syorganizations) {
		this.syorganizations = syorganizations;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYUSER_SYORGANIZATION", schema = "", joinColumns = { @JoinColumn(name = "SYORGANIZATION_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYUSER_ID", nullable = false, updatable = false) })
	public Set<Syuser> getSyusers() {
		return this.syusers;
	}

	public void setSyusers(Set<Syuser> syusers) {
		this.syusers = syusers;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYORGANIZATION_SYRESOURCE", schema = "", joinColumns = { @JoinColumn(name = "SYORGANIZATION_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYRESOURCE_ID", nullable = false, updatable = false) })
	public Set<Syresource> getSyresources() {
		return this.syresources;
	}

	public void setSyresources(Set<Syresource> syresources) {
		this.syresources = syresources;
	}
	@Column(name = "LEVELID", length = 1)
	public Integer getLevelId() {
		return levelId;
	}
	
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	
	
	@Column(name = "TELPHONE", length = 20)
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "TAX_CODE", length = 40)
	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String taxCode) {
		tax_code = taxCode;
	}
	@Column(name = "BANK_ACCOUNT", length = 40)
	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bankAccount) {
		bank_account = bankAccount;
	}
	
	
	@Column(name = "ORG_TYPE", length = 40)
	public String getOrg_type() {
		return org_type;
	}

	public void setOrg_type(String orgType) {
		org_type = orgType;
	}

	@Transient
	public String getState() {
		if(syorganizations!=null&&!syorganizations.isEmpty()){
    		return "closed";
    	}
    	if(syorganizations==null||syorganizations.isEmpty()){
    		return "open";
    	}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Transient
	public boolean isLeaf() {
		if(syorganizations!=null&&!syorganizations.isEmpty()){
    		return false;
    	}
    	if(syorganizations==null||syorganizations.isEmpty()){
    		return true;
    	}
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * 用于业务逻辑的字段，注解@Transient代表不需要持久化到数据库中
	 * 
	 * @return
	 */
	@Transient
	public String getPid() {
		if (syorganization != null && !StringUtils.isBlank(syorganization.getId())) {
			return syorganization.getId();
		}
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
