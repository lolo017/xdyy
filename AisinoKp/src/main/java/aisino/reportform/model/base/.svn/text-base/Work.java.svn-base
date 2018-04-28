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
 * Work entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WORK", schema = "")
public class Work implements java.io.Serializable {

	// Fields
	
	private String id;
	private String empId;
	private String name;
	private Date datadetails=new Date();
	private String year;
	private String mouth;
	private String day;
	private String sjgt;
	private String fgldgt;
	private String xjgt;
	private String gzgsgt;
	private String qtbmgt;
	private String hzhbgt;
	private String sjgtpj;
	private String fgldgtpj;
	private String xjgtpj;
	private String gzgsgtpj;
	private String qtbmgtpj;
	private String hzhbgtpj;
	private String xhlsm;
	private String jjwt;
	private String xhlsmpj;
	private String jjwtpj;
	private String cjbg;
	private String tdbg;
	private String jjlcn;
	private String fgldcn;
	private String ffgldcn;
	private String jjlcnpj;
	private String fgldcnpj;
	private String ffgldcnpj;
	private Date pjsj=new Date();
	private String tdbgpj;
	private String cjbgoj;
	Calendar calendar = GregorianCalendar.getInstance();

	// Constructors

	/** default constructor */
	public Work() {
	}

	/** minimal constructor */
	public Work(String id) {
		this.id = id;
	}

	/** full constructor */
	public Work(String id, 
			Date datadetails, String year, String mouth, String day,
			String sjgt, String fgldgt, String xjgt, String gzgsgt,
			String qtbmgt, String hzhbgt, String sjgtpj, String fgldgtpj,
			String xjgtpj, String gzgsgtpj, String qtbmgtpj, String hzhbgtpj,
			String xhlsm, String jjwt, String xhlsmpj, String jjwtpj,
			String cjbg, String tdbg, String jjlcn, String fgldcn,
			String ffgldcn, String jjlcnpj, String fgldcnpj, String ffgldcnpj,
			Date pjsj, String tdbgpj, String cjbgoj) {
		this.id = id;
		
		this.datadetails = datadetails;
		this.year = year;
		this.mouth = mouth;
		this.day = day;
		this.sjgt = sjgt;
		this.fgldgt = fgldgt;
		this.xjgt = xjgt;
		this.gzgsgt = gzgsgt;
		this.qtbmgt = qtbmgt;
		this.hzhbgt = hzhbgt;
		this.sjgtpj = sjgtpj;
		this.fgldgtpj = fgldgtpj;
		this.xjgtpj = xjgtpj;
		this.gzgsgtpj = gzgsgtpj;
		this.qtbmgtpj = qtbmgtpj;
		this.hzhbgtpj = hzhbgtpj;
		this.xhlsm = xhlsm;
		this.jjwt = jjwt;
		this.xhlsmpj = xhlsmpj;
		this.jjwtpj = jjwtpj;
		this.cjbg = cjbg;
		this.tdbg = tdbg;
		this.jjlcn = jjlcn;
		this.fgldcn = fgldcn;
		this.ffgldcn = ffgldcn;
		this.jjlcnpj = jjlcnpj;
		this.fgldcnpj = fgldcnpj;
		this.ffgldcnpj = ffgldcnpj;
		this.pjsj = pjsj;
		this.tdbgpj = tdbgpj;
		this.cjbgoj = cjbgoj;
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

	
	@Column(name = "NAME", length = 10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USEREMPID", length = 36)
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATADETAILS", length = 7)
	public Date getDatadetails() {
		return this.datadetails;
	}

	public void setDatadetails(Date datadetails) {
		this.datadetails = datadetails;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "PJSJ", length = 7)
	public Date getPjsj() {
		return this.pjsj;
	}

	public void setPjsj(Date pjsj) {
		this.pjsj = pjsj;
	}

	@Column(name = "TDBGPJ", length = 200)
	public String getTdbgpj() {
		return this.tdbgpj;
	}

	public void setTdbgpj(String tdbgpj) {
		this.tdbgpj = tdbgpj;
	}

	@Column(name = "CJBGOJ", length = 200)
	public String getCjbgoj() {
		return this.cjbgoj;
	}

	public void setCjbgoj(String cjbgoj) {
		this.cjbgoj = cjbgoj;
	}

}