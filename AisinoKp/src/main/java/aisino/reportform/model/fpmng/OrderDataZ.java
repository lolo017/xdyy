package aisino.reportform.model.fpmng;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
/**
 * 
* @Title:InvoiceData 
* @Description: 初始订单
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年9月22日 上午10:12:34
 */
@Entity
@IdClass(value=OrderDataZUnionPkId.class)
@Table(name = "T_ORDERDATA",schema = "dbo",catalog="aisinoKpDz")
public class OrderDataZ implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String odid;	//	ODID	varchar(36)	本表主键	
	private String djhm;	//	DJHM	varchar(36)	原始单据id	
	private Date createtime;	//	CREATETIME	datetime	创建时间	
	private String fpzl;	//	FPZL	varchar(2)	发票种类	0:专票,2:普票,41:卷票,51:电子发票
	private String gfhm;	//	GFHM	varchar(36)	购方代码	
	private String gfmc;	//	GFMC	varchar(256)	购方名称	
	private String gfsh;	//	GFSH	varchar(36)	购方税号	
	private String gfyhzh;	//	GFYHZH	varchar(256)	购方银行账户	
	private String gfdzdh;	//	GFDZDH	varchar(512)	购方地址电话	
	private String bz;		//	BZ	varchar(512)	备注	
	private String skr;		//	SKR	varchar(36)	收款人	
	private String fhr;		//	FHR	varchar(36)	复核人	
	private int spxh;		//	SPXH	int(11)	商品序号	
	private String sphm;	//	SPHM	varchar(36)	商品号码	
	private String spmc;	//	SPMC	varchar(256)	商品名称	
	private String ggxh;	//	GGXH	varchar(36)	规格型号	
	private String dw;		//	DW	varchar(36)	单位	
	private BigDecimal sl;	//	SL	decimal(15,2)	数量	
	private BigDecimal dj;	//	DJ	decimal(30,16)	单价	商品实际价格(调价后的价格)
	private BigDecimal je;	//	JE	decimal(30,16)	金额	
	private String hsbz;	//	HSBZ	varchar(2)	含税标志	1:含税,0:不含税
	private BigDecimal slv;	//	SLV	decimal(4,2)	税率	可为0.17  0.13   0.11  0.06  0.03  0.00
	private String ssflbm;	//	SSFLBM	varchar(36)	税收分类编码	
	private String mobile;	//	MOBILE	varchar(36)	手机号	仅电子发票需要
	private String email;	//	EMAIL	varchar(128)	电子邮箱	仅电子发票需要
	private String kpbz;	//	KPBZ	varchar(2)	开票标志	0	0:未开票, 1: 已开票
	private Date hqsj;	//	HQSJ	dateatime	获取订单时间		读取该条明细记录的时间
	
	private List<SysOrderLine> sysOrderlines=new ArrayList<>();
	
	@ManyToMany( fetch = FetchType.EAGER, mappedBy = "orderDataZs")
//	@JoinTable(name = "t_order_line", schema = "", joinColumns = { @JoinColumn(name = "ODID", nullable = false, updatable = false),@JoinColumn(name = "DJHM", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "OLID", nullable = false, updatable = false)})
	public List<SysOrderLine> getSysOrderlines() {
		return sysOrderlines;
	}
	public void setSysOrderlines(List<SysOrderLine> sysOrderlines) {
		this.sysOrderlines = sysOrderlines;
	}
	@Id
	@Column(name = "ODID", nullable = false, length = 36)
	public String getOdid() {
		if (!StringUtils.isBlank(this.odid)) {
			return odid;
		}
		return UUID.randomUUID().toString();
	}
	public void setOdid(String odid) {
		this.odid = odid;
	}
	@Id
	@Column(name = "DJHM", length = 36)
	public String getDjhm() {
		return djhm;
	}
	public void setDjhm(String djhm) {
		this.djhm = djhm;
	}
	@Column(name = "CREATETIME")
	public Date getCreatetime() {
		if (this.createtime!=null) {
			return createtime;
		}
		return new Date();
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Column(name = "FPZL", length = 2)
	public String getFpzl() {
		if (!StringUtils.isBlank(this.fpzl)) {
			return fpzl;
		}
		return "51";
	}
	public void setFpzl(String fpzl) {
		this.fpzl = fpzl;
	}
	@Column(name = "GFHM", length = 36)
	public String getGfhm() {
		if (!StringUtils.isBlank(this.gfhm)) {
			return gfhm;
		}
		return "";
	}
	public void setGfhm(String gfhm) {
		this.gfhm = gfhm;
	}
	@Column(name = "GFMC", length = 256)
	public String getGfmc() {
		return gfmc;
	}
	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}
	@Column(name = "GFSH", length = 36)
	public String getGfsh() {
		return gfsh;
	}
	public void setGfsh(String gfsh) {
		this.gfsh = gfsh;
	}
	@Column(name = "GFYHZH", length = 256)
	public String getGfyhzh() {
		return gfyhzh;
	}
	public void setGfyhzh(String gfyhzh) {
		this.gfyhzh = gfyhzh;
	}
	@Column(name = "GFDZDH", length = 512)
	public String getGfdzdh() {
		return gfdzdh;
	}
	public void setGfdzdh(String gfdzdh) {
		this.gfdzdh = gfdzdh;
	}
	@Column(name = "BZ", length = 512)
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Column(name = "SKR", length = 36)
	public String getSkr() {
		return skr;
	}
	public void setSkr(String skr) {
		this.skr = skr;
	}
	@Column(name = "FHR", length = 36)
	public String getFhr() {
		return fhr;
	}
	public void setFhr(String fhr) {
		this.fhr = fhr;
	}
	@Column(name = "SPXH")
	public int getSpxh() {
		return spxh;
	}
	public void setSpxh(int spxh) {
		this.spxh = spxh;
	}
	@Column(name = "SPHM", length = 36)
	public String getSphm() {
		if (!StringUtils.isBlank(this.sphm)) {
			return sphm;
		}
		return "";
	}
	public void setSphm(String sphm) {
		this.sphm = sphm;
	}
	@Column(name = "SPMC", length = 256)
	public String getSpmc() {
		return spmc;
	}
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	@Column(name = "GGXH", length = 36)
	public String getGgxh() {
		return ggxh;
	}
	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}
	@Column(name = "DW", length = 36)
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	@Column(name = "SL")
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	@Column(name = "DJ")
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}
	@Column(name = "JE")
	public BigDecimal getJe() {
		return je;
	}
	public void setJe(BigDecimal je) {
		this.je = je;
	}
	@Column(name = "HSBZ", length = 2)
	public String getHsbz() {
		if (!StringUtils.isBlank(this.hsbz)) {
			return hsbz;
		}
		return "1";
	}
	public void setHsbz(String hsbz) {
		this.hsbz = hsbz;
	}
	@Column(name = "SLV")
	public BigDecimal getSlv() {
		return slv;
	}
	public void setSlv(BigDecimal slv) {
		this.slv = slv;
	}
	@Column(name = "SSFLBM", length = 36)
	public String getSsflbm() {
		return ssflbm;
	}
	public void setSsflbm(String ssflbm) {
		this.ssflbm = ssflbm;
	}
	@Column(name = "MOBILE", length = 36)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "EMAIL", length = 128)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Column(name = "KPBZ", length = 2)
	public String getKpbz() {
		if (!StringUtils.isBlank(this.kpbz)) {
			return kpbz;
		}
		return "0";
	}
	public void setKpbz(String kpbz) {
		this.kpbz = kpbz;
	}
	@Column(name = "HQSJ")
	public Date getHqsj() {
		return hqsj;
	}
	public void setHqsj(Date hqsj) {
		this.hqsj = hqsj;
	}
	
	public boolean compare(Object obj) {
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OrderDataZ other = (OrderDataZ) obj;
			if (fpzl == null) {
				if (other.fpzl != null)
					return false;
			} else if (!fpzl.equals(other.fpzl))
				return false;
			if (gfdzdh == null) {
				if (other.gfdzdh != null)
					return false;
			} else if (!gfdzdh.equals(other.gfdzdh))
				return false;
			if (gfhm == null) {
				if (other.gfhm != null)
					return false;
			} else if (!gfhm.equals(other.gfhm))
				return false;
			if (gfmc == null) {
				if (other.gfmc != null)
					return false;
			} else if (!gfmc.equals(other.gfmc))
				return false;
			if (gfsh == null) {
				if (other.gfsh != null)
					return false;
			} else if (!gfsh.equals(other.gfsh))
				return false;
			if (gfyhzh == null) {
				if (other.gfyhzh != null)
					return false;
			} else if (!gfyhzh.equals(other.gfyhzh))
				return false;
			if (hsbz == null) {
				if (other.hsbz != null)
					return false;
			} else if (!hsbz.equals(other.hsbz))
				return false;
			if (bz == null) {
				if (other.bz != null)
					return false;
			} else if (!bz.equals(other.bz))
				return false;
			if (skr == null) {
				if (other.skr != null)
					return false;
			} else if (!skr.equals(other.skr))
				return false;
			if (fhr == null) {
				if (other.fhr != null)
					return false;
			} else if (!fhr.equals(other.fhr))
				return false;
			if (mobile == null) {
				if (other.mobile != null)
					return false;
			} else if (!mobile.equals(other.mobile))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (sysOrderlines == null) {
				if (other.sysOrderlines != null)
					return false;
			} else if (!sysOrderlines.equals(other.sysOrderlines))
				return false;
			return true;
		}
	}
}
