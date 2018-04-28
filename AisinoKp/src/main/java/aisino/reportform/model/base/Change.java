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
 * Change entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHANGE", schema = "")
public class Change implements java.io.Serializable {

	// Fields

	private String id;
	private Syuser syuser;
	private String cjbg;
	private String tdbg;
	private String jjlcn;
	private String fgldcn;
	private String ffgldcn;
	private String jjlcnpj;
	private String fgldcnpj;
	private String ffgldcnpj;
	private String year;
	private String mouth;
	private String day;
	private Date datadetails=new Date();
	Calendar calendar = GregorianCalendar.getInstance();
	// Constructors

	/** default constructor */
	public Change() {
	}

	/** minimal constructor */
	public Change(String id) {
		this.id = id;
	}

	/** full constructor */
	public Change(String id, Syuser syuser, String cjbg, String tdbg,
			String jjlcn, String fgldcn, String ffgldcn, String jjlcnpj,
			String fgldcnpj, String ffgldcnpj, String year, String mouth,
			String day, Date datadetails) {
		this.id = id;
		this.syuser = syuser;
		this.cjbg = cjbg;
		this.tdbg = tdbg;
		this.jjlcn = jjlcn;
		this.fgldcn = fgldcn;
		this.ffgldcn = ffgldcn;
		this.jjlcnpj = jjlcnpj;
		this.fgldcnpj = fgldcnpj;
		this.ffgldcnpj = ffgldcnpj;
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

	@Column(name = "CJBG", length = 200)
	public String getCjbg() {
		return this.cjbg;
	}

	public void setCjbg(String cjbg) {
		this.cjbg = cjbg;
	}

	@Column(name = "TDBG", length = 200)
	public String getTdbg() {
		return this.tdbg;
	}

	public void setTdbg(String tdbg) {
		this.tdbg = tdbg;
	}

	@Column(name = "JJLCN", length = 200)
	public String getJjlcn() {
		return this.jjlcn;
	}

	public void setJjlcn(String jjlcn) {
		this.jjlcn = jjlcn;
	}

	@Column(name = "FGLDCN", length = 200)
	public String getFgldcn() {
		return this.fgldcn;
	}

	public void setFgldcn(String fgldcn) {
		this.fgldcn = fgldcn;
	}

	@Column(name = "FFGLDCN", length = 200)
	public String getFfgldcn() {
		return this.ffgldcn;
	}

	public void setFfgldcn(String ffgldcn) {
		this.ffgldcn = ffgldcn;
	}

	@Column(name = "JJLCNPJ", length = 200)
	public String getJjlcnpj() {
		return this.jjlcnpj;
	}

	public void setJjlcnpj(String jjlcnpj) {
		this.jjlcnpj = jjlcnpj;
	}

	@Column(name = "FGLDCNPJ", length = 200)
	public String getFgldcnpj() {
		return this.fgldcnpj;
	}

	public void setFgldcnpj(String fgldcnpj) {
		this.fgldcnpj = fgldcnpj;
	}

	@Column(name = "FFGLDCNPJ", length = 200)
	public String getFfgldcnpj() {
		return this.ffgldcnpj;
	}

	public void setFfgldcnpj(String ffgldcnpj) {
		this.ffgldcnpj = ffgldcnpj;
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