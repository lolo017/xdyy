package aisino.reportform.model.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysUsersView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USERS_VIEW", schema = "")
public class SysUsersView implements java.io.Serializable {

	// Fields

	private String id;
	private Long age;
	private Date createdatetime;
	private String loginname;
	private String name;
	private String photo;
	private String pwd;
	private String sex;
	private Date updatedatetime;
	private Integer userempid;
	private Integer userorgid;
	private Integer userdpid;
	private Integer orgid;
	private String orgname;
	private String orgshortname;
	private Integer employeeid;
	private String telephoneone;
	private String mobilenumber;
	private String email;
	private Integer departmentid;
	private String departmentname;
	private String departmentshortname;

	// Constructors

	/** default constructor */
	public SysUsersView() {
	}

	/** minimal constructor */
	public SysUsersView(String id, String loginname) {
		this.id = id;
		this.loginname = loginname;
	}

	/** full constructor */
	public SysUsersView(String id, Long age, Date createdatetime,
			String loginname, String name, String photo, String pwd,
			String sex, Date updatedatetime, Integer userempid,
			Integer userorgid, Integer userdpid, Integer orgid, String orgname,
			String orgshortname, Integer employeeid, String telephoneone,
			String mobilenumber, String email, Integer departmentid,
			String departmentname, String departmentshortname) {
		this.id = id;
		this.age = age;
		this.createdatetime = createdatetime;
		this.loginname = loginname;
		this.name = name;
		this.photo = photo;
		this.pwd = pwd;
		this.sex = sex;
		this.updatedatetime = updatedatetime;
		this.userempid = userempid;
		this.userorgid = userorgid;
		this.userdpid = userdpid;
		this.orgid = orgid;
		this.orgname = orgname;
		this.orgshortname = orgshortname;
		this.employeeid = employeeid;
		this.telephoneone = telephoneone;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.departmentid = departmentid;
		this.departmentname = departmentname;
		this.departmentshortname = departmentshortname;
	}

	// Property accessors
	@Id
	@Column(name = "ID", nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "AGE", precision = 10, scale = 0)
	public Long getAge() {
		return this.age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATETIME", length = 7)
	public Date getCreatedatetime() {
		return this.createdatetime;
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
		return this.updatedatetime;
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	@Column(name = "USEREMPID", precision = 6, scale = 0)
	public Integer getUserempid() {
		return this.userempid;
	}

	public void setUserempid(Integer userempid) {
		this.userempid = userempid;
	}

	@Column(name = "USERORGID", precision = 6, scale = 0)
	public Integer getUserorgid() {
		return this.userorgid;
	}

	public void setUserorgid(Integer userorgid) {
		this.userorgid = userorgid;
	}

	@Column(name = "USERDPID", precision = 6, scale = 0)
	public Integer getUserdpid() {
		return this.userdpid;
	}

	public void setUserdpid(Integer userdpid) {
		this.userdpid = userdpid;
	}

	@Column(name = "ORGID", precision = 5, scale = 0)
	public Integer getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	@Column(name = "ORGNAME", length = 200)
	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	@Column(name = "ORGSHORTNAME", length = 40)
	public String getOrgshortname() {
		return this.orgshortname;
	}

	public void setOrgshortname(String orgshortname) {
		this.orgshortname = orgshortname;
	}

	@Column(name = "EMPLOYEEID", precision = 9, scale = 0)
	public Integer getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	@Column(name = "TELEPHONEONE", length = 20)
	public String getTelephoneone() {
		return this.telephoneone;
	}

	public void setTelephoneone(String telephoneone) {
		this.telephoneone = telephoneone;
	}

	@Column(name = "MOBILENUMBER", length = 26)
	public String getMobilenumber() {
		return this.mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "DEPARTMENTID", precision = 9, scale = 0)
	public Integer getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	@Column(name = "DEPARTMENTNAME", length = 80)
	public String getDepartmentname() {
		return this.departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	@Column(name = "DEPARTMENTSHORTNAME", length = 20)
	public String getDepartmentshortname() {
		return this.departmentshortname;
	}

	public void setDepartmentshortname(String departmentshortname) {
		this.departmentshortname = departmentshortname;
	}

}