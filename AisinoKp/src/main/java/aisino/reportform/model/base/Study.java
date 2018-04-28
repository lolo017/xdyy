package aisino.reportform.model.base;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

/**
 * Study entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STUDY", schema = "")
public class Study implements java.io.Serializable {

	// Fields

	private String id;
	private String empId;
	private String name;
	private String year;
	private String mouth;
	private String day;
	private Date datadetails=new Date();
	private String yqdxl;
	private String zdxlxz;
	private String zdxlkm;
	private String nkqxlyxmc;
	private String nkqxlxlxz;
	private Date nkqxlkssj;
	private String zcyqdzc;
	private String zcnkqzc;
	private Date zcbmsj;
	private String zckskm;
	Calendar calendar = GregorianCalendar.getInstance();
	// Constructors

	/** default constructor */
	public Study() {
	}

	/** minimal constructor */
	public Study(String id) {
		this.id = id;
	}

	/** full constructor */
	public Study(String id, String year, String mouth,
			String day, Date datadetails, String yqdxl, String zdxlxz,
			String zdxlkm, String nkqxlyxmc, String nkqxlxlxz, Date nkqxlkssj,
			String zcyqdzc, String zcnkqzc, Date zcbmsj, String zckskm) {
		this.id = id;
		this.year = year;
		this.mouth = mouth;
		this.day = day;
		this.datadetails = datadetails;
		this.yqdxl = yqdxl;
		this.zdxlxz = zdxlxz;
		this.zdxlkm = zdxlkm;
		this.nkqxlyxmc = nkqxlyxmc;
		this.nkqxlxlxz = nkqxlxlxz;
		this.nkqxlkssj = nkqxlkssj;
		this.zcyqdzc = zcyqdzc;
		this.zcnkqzc = zcnkqzc;
		this.zcbmsj = zcbmsj;
		this.zckskm = zckskm;
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

	
	@Column(name = "EMPID", length = 36)
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	@Column(name = "NAME", length = 10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "YEAR", length = 8)
	public String getYear() {
		calendar.setTime(datadetails);
		String years = calendar.get(Calendar.YEAR)+"";//获取年份
		return years;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "MOUTH", length = 8)
	public String getMouth() {
		calendar.setTime(datadetails);
		String mouths = calendar.get(Calendar.MONTH)+1+"";//获取年份
		return mouths;
	}

	public void setMouth(String mouth) {
		this.mouth = mouth;
	}

	@Column(name = "DAY", length = 8)
	public String getDay() {
		calendar.setTime(datadetails);
		String days = calendar.get(Calendar.DATE)+"";//获取年份
		return days;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATADETAILS", length = 7)
	public Date getDatadetails() {
		return this.datadetails;
	}

	public void setDatadetails(Date datadetails) {
		this.datadetails = datadetails;
	}

	@Column(name = "YQDXL", length = 36)
	public String getYqdxl() {
		return this.yqdxl;
	}

	public void setYqdxl(String yqdxl) {
		this.yqdxl = yqdxl;
	}

	@Column(name = "ZDXLXZ", length = 36)
	public String getZdxlxz() {
		return this.zdxlxz;
	}

	public void setZdxlxz(String zdxlxz) {
		this.zdxlxz = zdxlxz;
	}

	@Column(name = "ZDXLKM", length = 36)
	public String getZdxlkm() {
		return this.zdxlkm;
	}

	public void setZdxlkm(String zdxlkm) {
		this.zdxlkm = zdxlkm;
	}

	@Column(name = "NKQXLYXMC", length = 36)
	public String getNkqxlyxmc() {
		return this.nkqxlyxmc;
	}

	public void setNkqxlyxmc(String nkqxlyxmc) {
		this.nkqxlyxmc = nkqxlyxmc;
	}

	@Column(name = "NKQXLXLXZ", length = 36)
	public String getNkqxlxlxz() {
		return this.nkqxlxlxz;
	}

	public void setNkqxlxlxz(String nkqxlxlxz) {
		this.nkqxlxlxz = nkqxlxlxz;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NKQXLKSSJ", length = 7)
	public Date getNkqxlkssj() {
		return this.nkqxlkssj;
	}

	public void setNkqxlkssj(Date nkqxlkssj) {
		this.nkqxlkssj = nkqxlkssj;
	}

	@Column(name = "ZCYQDZC", length = 36)
	public String getZcyqdzc() {
		return this.zcyqdzc;
	}

	public void setZcyqdzc(String zcyqdzc) {
		this.zcyqdzc = zcyqdzc;
	}

	@Column(name = "ZCNKQZC", length = 36)
	public String getZcnkqzc() {
		return this.zcnkqzc;
	}

	public void setZcnkqzc(String zcnkqzc) {
		this.zcnkqzc = zcnkqzc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ZCBMSJ", length = 7)
	public Date getZcbmsj() {
		return this.zcbmsj;
	}

	public void setZcbmsj(Date zcbmsj) {
		this.zcbmsj = zcbmsj;
	}

	@Column(name = "ZCKSKM", length = 36)
	public String getZckskm() {
		return this.zckskm;
	}

	public void setZckskm(String zckskm) {
		this.zckskm = zckskm;
	}

}