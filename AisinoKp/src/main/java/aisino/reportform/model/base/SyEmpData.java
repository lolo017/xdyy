package aisino.reportform.model.base;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * empdata model
 * @author 廖宸宇
 * @date 2015-5-19
 */
@Entity
@Table(name = "SYEMPDATA", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SyEmpData implements java.io.Serializable{
	private String state="closed";
	private String pid;
	
	private String empId;
	private String name;
	private Integer sex;
	private Date birthday;
	private String hight;//身高
	private String blood;//血型
	private String mobile;//手机
	private String home;//籍贯
	private Date indate;//入职时间
	private String address;//住址
	private String phone;//住址电话
	private Integer political;//政治面貌
	private Syorganization depOrganization;//所属部门
	private Syorganization orgOrganization;//所属公司
	private Syorganization posOrganization;//所属岗位
	private SyEmpData empData;//上级
	private Set<SyEmpData> empDatas;//下级
	private Set<EmpDegree> empDegrees;//学历
	private Set<EmpTitle> empTitles ;//职称
	private Set<EmpFamily> empFamilys ;//家庭成员
	
	
	public SyEmpData(String empId, String name,
			Syorganization depOrganization, SyEmpData empData,Set<SyEmpData> empDatas
			) {
		super();
		
		this.empId = empId;
		this.name = name;
		this.depOrganization = depOrganization;
		this.empData = empData;
		this.empDatas = empDatas;
	}

	public SyEmpData() {
		
	}

	public SyEmpData(String empId) {
		this.empId = empId;
	}

	@Id
	@Column(name = "EMPID", unique = true, nullable = false, length = 50)
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "SEX", length = 1)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", length = 10)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column(name = "HIGHT", length = 50)
	public String getHight() {
		return hight;
	}
	public void setHight(String hight) {
		this.hight = hight;
	}
	@Column(name = "BLOOD", length = 10)
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	@Column(name = "MOBILE", length = 50)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "HOME", length = 50)
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INDATE", length = 10)
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	@Column(name = "ADDRESS", length = 10)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "PHONE", length = 10)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
//	@Column(name = "DPID", length = 10)
//	public String getDpid() {
//		return dpid;
//	}
//	public void setDpid(String dpid) {
//		this.dpid = dpid;
//	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syEmpData")
	public Set<EmpDegree> getEmpDegrees() {
		return empDegrees;
	}

	public void setEmpDegrees(Set<EmpDegree> empDegrees) {
		this.empDegrees = empDegrees;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syEmpData")
	public Set<EmpTitle> getEmpTitles() {
		return empTitles;
	}

	public void setEmpTitles(Set<EmpTitle> empTitles) {
		this.empTitles = empTitles;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "syEmpData")
	public Set<EmpFamily> getEmpFamilys() {
		return empFamilys;
	}

	public void setEmpFamilys(Set<EmpFamily> empFamilys) {
		this.empFamilys = empFamilys;
	}
	@Column(name = "POLITICAL", length = 1)
	public Integer getPolitical() {
		return political;
	}

	public void setPolitical(Integer political) {
		this.political = political;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DPID")
	public Syorganization getDepOrganization() {
		return depOrganization;
	}

	public void setDepOrganization(Syorganization depOrganization) {
		this.depOrganization = depOrganization;
	}
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORGID")
	public Syorganization getOrgOrganization() {
		return orgOrganization;
	}

	public void setOrgOrganization(Syorganization orgOrganization) {
		this.orgOrganization = orgOrganization;
	}
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POSID")
	public Syorganization getPosOrganization() {
		return posOrganization;
	}

	public void setPosOrganization(Syorganization posOrganization) {
		this.posOrganization = posOrganization;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEADERID")
	public SyEmpData getEmpData() {
		return empData;
	}

	public void setEmpData(SyEmpData empData) {
		this.empData = empData;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empData")
   public Set<SyEmpData> getEmpDatas() {
		return empDatas;
	}

	public void setEmpDatas(Set<SyEmpData> empDatas) {
		this.empDatas = empDatas;
	}



	
	@Transient
	public String getPid() {
		if(empData!=null && !StringUtils.isBlank(empData.getEmpId())){
    		return empData.getEmpId();
    	}
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}	
	
	

	@Transient
	public String getState() {
		if(empDatas!=null&&!empDatas.isEmpty()){
    		return "closed";
    	}
    	if(empDatas==null||empDatas.isEmpty()){
    		return "open";
    	}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
