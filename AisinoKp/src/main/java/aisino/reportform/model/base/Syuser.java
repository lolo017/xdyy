package aisino.reportform.model.base;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SYUSER", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syuser implements java.io.Serializable {

	// Fields
	private String ip;
	
	private String id;
	private String empId;
	private Integer age;
	private Date createdatetime;
	private String loginname;
	private String name;
	private String photo;
	private String pwd;
	private String sex;
	private Date updatedatetime;
	private String email;
	private String userorgid;
	private String userdpid;
	private String orgcode;
	private String mainRole;
	private String isLeave;//是否离职，1表示离职，0和null表示在职
	private List<HashMap> resourceMap;
	private Set<Syorganization> syorganizations = new HashSet<Syorganization>(0);
	private Set<Syrole> syroles = new HashSet<Syrole>(0);

	private Set<Workstudu> workstudus = new HashSet<Workstudu>(0);
	private Set<Communicate> communicates = new HashSet<Communicate>(0);
	private Set<Change> changes = new HashSet<Change>(0);
	private int isPwd;//判断是否修改过初始密码

	// Constructors

	/** default constructor */
	public Syuser() {
	}

	/** minimal constructor */
	public Syuser(String id, String loginname) {
		this.id = id;
		this.loginname = loginname;
	}

	/** full constructor */
	public Syuser(String id, String empId, Integer age,
			Date createdatetime, String loginname, String name, String photo,
			String pwd, String sex, Date updatedatetime, String email,
			String userorgid, String userdpid,
			Set<Syorganization> syorganizations, Set<Syrole> syroles,
			Set<Workstudu> workstudus, Set<Communicate> communicates,
			Set<Change> changes) {
		this.id = id;
		this.empId = empId;
		this.age = age;
		this.createdatetime = createdatetime;
		this.loginname = loginname;
		this.name = name;
		this.photo = photo;
		this.pwd = pwd;
		this.sex = sex;
		this.updatedatetime = updatedatetime;
		this.email = email;
		this.userorgid = userorgid;
		this.userdpid = userdpid;
		this.syorganizations = syorganizations;
		this.syroles = syroles;
	
		this.workstudus = workstudus;
		this.communicates = communicates;
		this.changes = changes;
	}

	// Property accessors
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

	
	@Column(name = "USEREMPID", length = 40)
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Column(name = "AGE", precision = 10, scale = 0)
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATETIME", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Column(name = "LOGINNAME", nullable = false, length = 100)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PHOTO", length = 200)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "PWD", length = 100)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "SEX", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATEDATETIME", length = 7)
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	@Column(name = "EMAIL", length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "USERORGID", length = 40)
	public String getUserorgid() {
		return this.userorgid;
	}

	public void setUserorgid(String userorgid) {
		this.userorgid = userorgid;
	}

	
	
	@Column(name = "IS_LEAVE", length = 1)
	public String getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(String isLeave) {
		this.isLeave = isLeave;
	}

	@Column(name = "ORGCODE", length = 40)
	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	@Column(name = "USERDPID", length = 40)
	public String getUserdpid() {
		return this.userdpid;
	}

	public void setUserdpid(String userdpid) {
		this.userdpid = userdpid;
	}

	
	@Column(name = "MAIN_ROLE", length = 36)
	public String getMainRole() {
		return mainRole;
	}

	public void setMainRole(String mainRole) {
		this.mainRole = mainRole;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "SYUSER_SYORGANIZATION", schema = "", joinColumns = { @JoinColumn(name = "SYUSER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYORGANIZATION_ID", nullable = false, updatable = false) })
	public Set<Syorganization> getSyorganizations() {
		return this.syorganizations;
	}

	public void setSyorganizations(Set<Syorganization> syorganizations) {
		this.syorganizations = syorganizations;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "SYUSER_SYROLE", schema = "", joinColumns = { @JoinColumn(name = "SYUSER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYROLE_ID", nullable = false, updatable = false) })
	public Set<Syrole> getSyroles() {
		return this.syroles;
	}

	public void setSyroles(Set<Syrole> syroles) {
		this.syroles = syroles;
	}

	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syuser")
	public Set<Workstudu> getWorkstudus() {
		return this.workstudus;
	}

	public void setWorkstudus(Set<Workstudu> workstudus) {
		this.workstudus = workstudus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syuser")
	public Set<Communicate> getCommunicates() {
		return this.communicates;
	}

	public void setCommunicates(Set<Communicate> communicates) {
		this.communicates = communicates;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syuser")
	public Set<Change> getChanges() {
		return this.changes;
	}

	public void setChanges(Set<Change> changes) {
		this.changes = changes;
	}

	@Transient
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	@Transient
	public List<HashMap> getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(List<HashMap> resourceMap) {
		this.resourceMap = resourceMap;
	}
	@Transient
	public int getIsPwd() {
		return isPwd;
	}

	public void setIsPwd(int isPwd) {
		this.isPwd = isPwd;
	}
	

	
}
