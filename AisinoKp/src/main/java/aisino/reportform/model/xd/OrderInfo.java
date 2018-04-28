package aisino.reportform.model.xd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
* @Title:OrderInfo 
* @Description: 信德医药原始单据实体
* Company    JS-YFB LTD
* @author 吕振宇
* @version V1.0    
* @date 2018年2月22日 上午8:41:25
 */
@Entity
@Table(name = "T_ORDERINFO")
public class OrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8097927003226627872L;
	
	private String odid;
	private String ohid;
	private Date createtime;
	private String gfmc;
	private String gfhm;
	private String gfsh;
	private int spxh;
	private String gfyhzh;
	private String gfdzdh;
	private String fpzl; //FPZL	varchar(2)	发票种类	0:专票,2:普票,41:卷票,51:电子发票
	private String skr;
	private String fhr;
	
	private String sphm;
	private String spmc;
	private String ggxh;
	private String dw;
	private BigDecimal sl;
	private BigDecimal dj;
	private BigDecimal je;
	private String hsbz;
	private BigDecimal slv;
	private String ssflbm;
	private String email;
	private Date hqsj;
	private String is_return;
	
	private int fenpiaobiaoshi;
	private String origin;
	private String ywy;
	private String batchno;
	
	private String khh;
	@Id
	@Column(name="ODID",length=36)
	public String getOdid() {
		return odid;
	}
	public void setOdid(String odid) {
		this.odid = odid;
	}
	@Column(name="GFYHZH",length=256)
	public String getGfyhzh() {
		return gfyhzh;
	}
	public void setGfyhzh(String gfyhzh) {
		this.gfyhzh = gfyhzh;
	}
	@Column(name="OHID",length=36)
	public String getOhid() {
		return ohid;
	}
	public void setOhid(String ohid) {
		this.ohid = ohid;
	}
	@Column(name="CREATETIME")
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Column(name="GFMC",length=256)
	public String getGfmc() {
		return gfmc;
	}
	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}
	@Column(name="GFHM",length=36)
	public String getGfhm() {
		return gfhm;
	}
	public void setGfhm(String gfhm) {
		this.gfhm = gfhm;
	}
	@Column(name="GFSH",length=36)
	public String getGfsh() {
		return gfsh;
	}
	public void setGfsh(String gfsh) {
		this.gfsh = gfsh;
	}
	@Column(name="SPXH")
	public int getSpxh() {
		return spxh;
	}
	public void setSpxh(int spxh) {
		this.spxh = spxh;
	}
	@Column(name="GFDZDH",length=512)
	public String getGfdzdh() {
		return gfdzdh;
	}
	public void setGfdzdh(String gfdzdh) {
		this.gfdzdh = gfdzdh;
	}
	@Column(name="FPZL",length=20)
	public String getFpzl() {
		return fpzl;
	}
	public void setFpzl(String fpzl) {
		this.fpzl = fpzl;
	}
	@Column(name="SKR",length=36)
	public String getSkr() {
		return skr;
	}
	public void setSkr(String skr) {
		this.skr = skr;
	}
	@Column(name="FHR",length=36)
	public String getFhr() {
		return fhr;
	}
	public void setFhr(String fhr) {
		this.fhr = fhr;
	}
	@Column(name="SPHM",length=36)
	public String getSphm() {
		return sphm;
	}
	public void setSphm(String sphm) {
		this.sphm = sphm;
	}
	@Column(name="SPMC",length=256)
	public String getSpmc() {
		return spmc;
	}
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	@Column(name="GGXH",length=36  )
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	@Column(name="DW",length=36 )
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	@Column(name="SL")
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	@Column(name="DJ")
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}
	@Column(name="JE")
	public BigDecimal getJe() {
		return je;
	}
	
	public void setJe(BigDecimal je) {
		this.je = je;
	}
	@Column(name="HSBZ",length=2)
	public String getHsbz() {
		return hsbz;
	}
	
	public void setHsbz(String hsbz) {
		this.hsbz = hsbz;
	}
	@Column(name="SLV")
	public BigDecimal getSlv() {
		return slv;
	}
	public void setSlv(BigDecimal slv) {
		this.slv = slv;
	}
	@Column(name="SSFLBM",length=36)
	public String getSsflbm() {
		return ssflbm;
	}
	public void setSsflbm(String ssflbm) {
		this.ssflbm = ssflbm;
	}
	@Column(name="E_MAIL",length=36)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="HQSJ")
	public Date getHqsj() {
		return hqsj;
	}
	public void setHqsj(Date hqsj) {
		this.hqsj = hqsj;
	}
	@Column(name="is_return")
	public String getIs_return() {
		return is_return;
	}
	public void setIs_return(String is_return) {
		this.is_return = is_return;
	}
	@Column(name="fenpiaobiaoshi")
	public int getFenpiaobiaoshi() {
		return fenpiaobiaoshi;
	}
	public void setFenpiaobiaoshi(int fenpiaobiaoshi) {
		this.fenpiaobiaoshi = fenpiaobiaoshi;
	}
	@Column(name="origin")
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	@Column(name="ywy")
	public String getYwy() {
		return ywy;
	}
	public void setYwy(String ywy) {
		this.ywy = ywy;
	}
	@Column(name="batchno")
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	@Column(name="khh")
	public String getKhh() {
		return khh;
	}
	public void setKhh(String khh) {
		this.khh = khh;
	}
	
	
	
	
}
