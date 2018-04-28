package aisino.reportform.model.xd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_jsfp")
public class InvoiceHead implements Serializable{

	
	private static final long serialVersionUID = 7306204368276501813L;
	
	private String fpid;
	private String fph;
	private Date kdrq;
	private Date fprq;
	private String tjbh;
	private BigDecimal je;
	private BigDecimal se;
	private BigDecimal jshj;
	private String ywy;
	private BigDecimal ftype;
	private BigDecimal invoiceamount;
	private String bz;
	private String invoiceNo;
	private Date t_date;
	private String origin;
	@Id
	@Column(name="FPID",length=36)
	public String getFpid() {
		return fpid;
	}
	public void setFpid(String fpid) {
		this.fpid = fpid;
	}
	@Column(name="FPH",length=36)
	public String getFph() {
		return fph;
	}
	public void setFph(String fph) {
		this.fph = fph;
	}
	@Column(name="KDRQ")
	public Date getKdrq() {
		return kdrq;
	}
	public void setKdrq(Date kdrq) {
		this.kdrq = kdrq;
	}
	@Column(name="FPRQ")
	public Date getFprq() {
		return fprq;
	}
	public void setFprq(Date date) {
		this.fprq = date;
	}
	@Column(name="TJBH",length=255)
	public String getTjbh() {
		return tjbh;
	}
	public void setTjbh(String tjbh) {
		this.tjbh = tjbh;
	}
	@Column(name="JE")
	public BigDecimal getJe() {
		return je;
	}
	public void setJe(BigDecimal je) {
		this.je = je;
	}
	@Column(name="SE")
	public BigDecimal getSe() {
		return se;
	}
	public void setSe(BigDecimal se) {
		this.se = se;
	}
	@Column(name="JSHJ",length=36)
	public BigDecimal getJshj() {
		return jshj;
	}
	public void setJshj(BigDecimal jshj) {
		this.jshj = jshj;
	}
	@Column(name="YWY",length=36)
	public String getYwy() {
		return ywy;
	}
	public void setYwy(String ywy) {
		this.ywy = ywy;
	}
	@Column(name="FTYPE",length=255)
	public BigDecimal getFtype() {
		return ftype;
	}
	public void setFtype(BigDecimal ftype) {
		this.ftype = ftype;
	}
	@Column(name="INVOICEAMOUNT")
	public BigDecimal getInvoiceamount() {
		return invoiceamount;
	}
	public void setInvoiceamount(BigDecimal invoiceamount) {
		this.invoiceamount = invoiceamount;
	}
	@Column(name="t_date")
	public Date getT_date() {
		return t_date;
	}
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	@Column(name="INVOICENO")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	@Column(name="BZ")
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Column(name="origin")
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	
}
