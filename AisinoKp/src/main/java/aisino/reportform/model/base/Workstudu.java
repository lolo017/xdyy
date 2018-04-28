package aisino.reportform.model.base;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

/**
 * Workstudu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WORKSTUDU", schema = "")
public class Workstudu implements java.io.Serializable {

	// Fields

	private String id;
	private Syuser syuser;
	private String xhlsm;
	private String jjwt;
	private String xhlsmpj;
	private String jjwtpj;
	private String year;
	private String mouth;
	private String day;
	private Date datadetails=new Date();
	Calendar calendar = GregorianCalendar.getInstance();
	// Constructors

	/** default constructor */
	public Workstudu() {
	}

	/** minimal constructor */
	public Workstudu(String id) {
		this.id = id;
	}

	/** full constructor */
	public Workstudu(String id, Syuser syuser, String xhlsm, String jjwt,
			String xhlsmpj, String jjwtpj, String year, String mouth,
			String day, Date datadetails) {
		this.id = id;
		this.syuser = syuser;
		this.xhlsm = xhlsm;
		this.jjwt = jjwt;
		this.xhlsmpj = xhlsmpj;
		this.jjwtpj = jjwtpj;
		this.year = year;
		this.mouth = mouth;
		this.day = day;
		this.datadetails = datadetails;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERSID")
	public Syuser getSyuser() {
		return this.syuser;
	}

	public void setSyuser(Syuser syuser) {
		this.syuser = syuser;
	}

	@Column(name = "XHLSM", length = 200)
	public String getXhlsm() {
		return this.xhlsm;
	}

	public void setXhlsm(String xhlsm) {
		this.xhlsm = xhlsm;
	}

	@Column(name = "JJWT", length = 200)
	public String getJjwt() {
		return this.jjwt;
	}

	public void setJjwt(String jjwt) {
		this.jjwt = jjwt;
	}

	@Column(name = "XHLSMPJ", length = 200)
	public String getXhlsmpj() {
		return this.xhlsmpj;
	}

	public void setXhlsmpj(String xhlsmpj) {
		this.xhlsmpj = xhlsmpj;
	}

	@Column(name = "JJWTPJ", length = 200)
	public String getJjwtpj() {
		return this.jjwtpj;
	}

	public void setJjwtpj(String jjwtpj) {
		this.jjwtpj = jjwtpj;
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

}