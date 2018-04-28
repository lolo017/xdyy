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
 * Communicate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMMUNICATE", schema = "")
public class Communicate implements java.io.Serializable {

	// Fields

	private String id;
	private Syuser syuser;
	private String sjgt;
	private String fgldgt;
	private String xjgt;
	private String gzgsgt;
	private String qtbmgt;
	private String hzhbgt;
	private String year;
	private String mouth;
	private String day;
	private Date datadetails=new Date();
	private String sjgtpj;
	private String fgldgtpj;
	private String xjgtpj;
	private String gzgsgtpj;
	private String qtbmgtpj;
	private String hzhbgtpj;
	Calendar calendar = GregorianCalendar.getInstance();
	// Constructors
	
	/** default constructor */
	public Communicate() {
	}

	/** minimal constructor */
	public Communicate(String id) {
		this.id = id;
	}

	/** full constructor */
	public Communicate(String id, Syuser syuser, String sjgt, String fgldgt,
			String xjgt, String gzgsgt, String qtbmgt, String hzhbgt,
			String year, String mouth, String day, Date datadetails,
			String sjgtpj, String fgldgtpj, String xjgtpj, String gzgsgtpj,
			String qtbmgtpj, String hzhbgtpj) {
		this.id = id;
		this.syuser = syuser;
		this.sjgt = sjgt;
		this.fgldgt = fgldgt;
		this.xjgt = xjgt;
		this.gzgsgt = gzgsgt;
		this.qtbmgt = qtbmgt;
		this.hzhbgt = hzhbgt;
		this.year = year;
		this.mouth = mouth;
		this.day = day;
		this.datadetails = datadetails;
		this.sjgtpj = sjgtpj;
		this.fgldgtpj = fgldgtpj;
		this.xjgtpj = xjgtpj;
		this.gzgsgtpj = gzgsgtpj;
		this.qtbmgtpj = qtbmgtpj;
		this.hzhbgtpj = hzhbgtpj;
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

	@Column(name = "SJGT", length = 200)
	public String getSjgt() {
		return this.sjgt;
	}

	public void setSjgt(String sjgt) {
		this.sjgt = sjgt;
	}

	@Column(name = "FGLDGT", length = 200)
	public String getFgldgt() {
		return this.fgldgt;
	}

	public void setFgldgt(String fgldgt) {
		this.fgldgt = fgldgt;
	}

	@Column(name = "XJGT", length = 200)
	public String getXjgt() {
		return this.xjgt;
	}

	public void setXjgt(String xjgt) {
		this.xjgt = xjgt;
	}

	@Column(name = "GZGSGT", length = 200)
	public String getGzgsgt() {
		return this.gzgsgt;
	}

	public void setGzgsgt(String gzgsgt) {
		this.gzgsgt = gzgsgt;
	}

	@Column(name = "QTBMGT", length = 200)
	public String getQtbmgt() {
		return this.qtbmgt;
	}

	public void setQtbmgt(String qtbmgt) {
		this.qtbmgt = qtbmgt;
	}

	@Column(name = "HZHBGT", length = 200)
	public String getHzhbgt() {
		return this.hzhbgt;
	}

	public void setHzhbgt(String hzhbgt) {
		this.hzhbgt = hzhbgt;
	}

	@Column(name = "YEAR", length = 8)
	public String getYear() {
		calendar.setTime(datadetails);
		String years = calendar.get(Calendar.YEAR)+"";//获取年份
		return years;
	}

	public void setYear(String year) {
		calendar.setTime(datadetails);
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
		return datadetails;
	}

	public void setDatadetails(Date datadetails) {
		this.datadetails = datadetails;
	}

	@Column(name = "SJGTPJ", length = 200)
	public String getSjgtpj() {
		return this.sjgtpj;
	}
	public void setSjgtpj(String sjgtpj) {
		this.sjgtpj = sjgtpj;
	}

	@Column(name = "FGLDGTPJ", length = 200)
	public String getFgldgtpj() {
		return this.fgldgtpj;
	}

	public void setFgldgtpj(String fgldgtpj) {
		this.fgldgtpj = fgldgtpj;
	}

	@Column(name = "XJGTPJ", length = 200)
	public String getXjgtpj() {
		return this.xjgtpj;
	}

	public void setXjgtpj(String xjgtpj) {
		this.xjgtpj = xjgtpj;
	}

	@Column(name = "GZGSGTPJ", length = 200)
	public String getGzgsgtpj() {
		return this.gzgsgtpj;
	}

	public void setGzgsgtpj(String gzgsgtpj) {
		this.gzgsgtpj = gzgsgtpj;
	}

	@Column(name = "QTBMGTPJ", length = 200)
	public String getQtbmgtpj() {
		return this.qtbmgtpj;
	}

	public void setQtbmgtpj(String qtbmgtpj) {
		this.qtbmgtpj = qtbmgtpj;
	}

	@Column(name = "HZHBGTPJ", length = 200)
	public String getHzhbgtpj() {
		return this.hzhbgtpj;
	}

	public void setHzhbgtpj(String hzhbgtpj) {
		this.hzhbgtpj = hzhbgtpj;
	}

}