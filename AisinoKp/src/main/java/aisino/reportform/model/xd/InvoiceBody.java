package aisino.reportform.model.xd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "t_jsfpmx")
public class InvoiceBody implements Serializable{

	private static final long serialVersionUID = 335263337709823661L;
	
	private String dh;
	private String hh;
	private BigDecimal jj;
	private BigDecimal dj; //无税单价
	private BigDecimal sl;
	private BigDecimal jshj;
	private BigDecimal je;
	private BigDecimal se;
	private BigDecimal outquantity;
	private BigDecimal outamounttax;
	private String bz;
	private String outdetailno; //销售单号
	private Date outdate; //销售日期
	private String fpid;
	private String customerguid;
	private Date t_date;
	
	private int fenpiaobiaoshi;
	private String batchno; 
	@Column(name="HH",length=36)
	public String getHh() {
		return hh;
	}
	public void setHh(String hh) {
		this.hh = hh;
	}
	@Id
	@Column(name="DH")
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	
	@Column(name="JJ")
	public BigDecimal getJj() {
		return jj;
	}
	public void setJj(BigDecimal jj) {
		this.jj = jj;
	}
	
	
	@Column(name="JSHJ")
	public BigDecimal getJshj() {
		return jshj;
	}
	public void setJshj(BigDecimal jshj) {
		this.jshj = jshj;
	}
	@Column(name="JE")
	public BigDecimal getJe() {
		return je;
	}
	public void setJe(BigDecimal je) {
		this.je = je;
	}
	
	
	@Column(name="OUTDATE")
	public Date getOutdate() {
		return outdate;
	}
	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}
	
	
	@Column(name="CUSTOMERGUID",length=36)
	public String getCustomerguid() {
		return customerguid;
	}
	public void setCustomerguid(String customerguid) {
		this.customerguid = customerguid;
	}
	
	@Column(name="DJ")
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}
	@Column(name="SL")
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	@Column(name="OUTQUANTITY")
	public BigDecimal getOutquantity() {
		return outquantity;
	}
	public void setOutquantity(BigDecimal outquantity) {
		this.outquantity = outquantity;
	}
	@Column(name="FPID")
	public String getFpid() {
		return fpid;
	}
	public void setFpid(String fpid) {
		this.fpid = fpid;
	}
	@Column(name="OUTDETAILNO")
	public String getOutdetailno() {
		return outdetailno;
	}
	public void setOutdetailno(String outdetailno) {
		this.outdetailno = outdetailno;
	}
	@Column(name="T_DATE")
	public Date getT_date() {
		return t_date;
	}
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	@Column(name="SE")
	public BigDecimal getSe() {
		return se;
	}
	public void setSe(BigDecimal se) {
		this.se = se;
	}
	@Column(name="OUTAMOUNTTAX")
	public BigDecimal getOutamounttax() {
		return outamounttax;
	}
	public void setOutamounttax(BigDecimal outamounttax) {
		this.outamounttax = outamounttax;
	}
	@Column(name="BZ")
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Column(name="fenpiaobiaoshi")
	public int getFenpiaobiaoshi() {
		return fenpiaobiaoshi;
	}
	public void setFenpiaobiaoshi(int fenpiaobiaoshi) {
		this.fenpiaobiaoshi = fenpiaobiaoshi;
	}
	@Column(name="batchno")
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	
	
}
