package aisino.reportform.model.fpmng;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import aisino.reportform.model.fpmng.T_invoicedata;
import aisino.reportform.model.fpmng.SysOrderLine;

/**
 * 
* @Title:T_invoicedata 
* @Description: 中间库
* Company    JS-YFB LTD
* @author 楚珂
* @version V1.0    
* @date 2018年1月8日 上午10:12:34
 */
@Entity
@Table(name = "t_invoicedata", schema = "dbo",catalog="mytest")
public class T_invoicedata implements Serializable{
	private static final long serialVersionUID = 6001653686284225991L;

	private String hid;	//	HID	varchar(36)	本表主键	
	private Date createtime;	//	CREATETIME	datetime	创建时间	
	private String fpzl;	//	FPZL	varchar(2)	发票种类	0:专票,2:普票,41:卷票,51:电子发票
	private String skr;		//	SKR	varchar(36)	收款人	
	private String fhr;		//	FHR	varchar(36)	复核人	
	private String kpr;		//	KPR	varchar(36)	开票人
	private String gfhm;	//	GFHM	varchar(36)	购方代码	
	private String gfmc;	//	GFMC	varchar(256)	购方名称	
	private String gfsh;	//	GFSH	varchar(36)	购方税号	
	private String gfyhzh;	//	GFYHZH	varchar(256)	购方银行账户	
	private String gfdzdh;	//	GFDZDH	varchar(512)	购方地址电话	
	private String bz;		//	BZ	varchar(512)	备注	
	private String mix;		//	MIX	varchar(2)	含多税率商品行	1:是,0:否
	private BigDecimal slv;		//  SLV decimal(4,2) 税率
	private String fpdm;	//	FPDM	varchar(36)	发票代码
	private String fphm;	//	FPHM	varchar(36)	发票号码
	private BigDecimal amount;	//	AMOUNT	decimal(30,16)	不含税金额
	private BigDecimal tax_amount;	//	TAX_AMOUNT	decimal(30,16)	税额
	private BigDecimal total;	//	TOTAL	decimal(30,16)	单据商品金额合计
	private int sphs;    //SPHS varchar(36) 商品行数
	private String has_qd;	//	HAS_QD	varchar(2)	清单标志
	private String is_zf;	//	IS_ZF	varchar(2)	作废标志
	private Date zf_date;		//	ZF_DATE	datetime	作废日期
	private String is_red;		//	IS_RED	varchar(2)	冲红标志
	private String red_fpdm;	//	RED_FPDM	varchar(36)	红票的发票代码
	private String red_fphm;	//	RED_FPHM	varchar(36)	红票的发票编码
	private Date red_date;		//	RED_DATE	datetime	冲红日期
	private String is_qz;		//	IS_QZ	varchar(2)	签章状态
	private String mobile;	//	MOBILE	varchar(36)	手机号	
	private String email;	//	EMAIL	varchar(36)	邮箱	
	private String is_qz_h;		//	IS_QZ_H	varchar(2)	签章状态
	private BigDecimal tax_amount_h;	//	TAX_AMOUNT	decimal(30,16)	税额
	
	@Id
	@Column(name = "HID", unique = true, nullable = false, length = 36)
	public String getHid() {
		if (!StringUtils.isBlank(this.hid)) {
			return hid;
		}
		return UUID.randomUUID().toString();
	}
	public void setHid(String hid) {
		this.hid = hid;
	}
	@Column(name = "CREATETIME")
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Column(name = "FPZL", length = 2)
	public String getFpzl() {
		return fpzl;
	}
	public void setFpzl(String fpzl) {
		this.fpzl = fpzl;
	}
	@Column(name = "GFHM", length = 36)
	public String getGfhm() {
		return gfhm;
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
	@Column(name = "TOTAL")
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	@Column(name = "MIX", length = 2)
	public String getMix() {
		if (!StringUtils.isBlank(this.mix)) {
			return mix;
		}
		return "0";
	}
	public void setMix(String mix) {
		this.mix = mix;
	}
	@Column(name="SLV")
	public BigDecimal getSlv() {
		return slv;
	}

	public void setSlv(BigDecimal slv) {
		this.slv = slv;
	}
	@Column(name = "KPR", length = 36)
	public String getKpr() {
		return kpr;
	}
	public void setKpr(String kpr) {
		this.kpr = kpr;
	}
	
	@Column(name = "FPDM", length = 36)
	public String getFpdm() {
		return fpdm;
	}
	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}
	@Column(name = "FPHM", length = 36)
	public String getFphm() {
		return fphm;
	}
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}
	@Column(name = "AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Column(name = "TAX_AMOUNT")
	public BigDecimal getTax_amount() {
		return tax_amount;
	}
	public void setTax_amount(BigDecimal tax_amount) {
		this.tax_amount = tax_amount;
	}
	@Column(name = "SPHS")
	public int getSphs() {
		return sphs;
	}

	public void setSphs(int sphs) {
		this.sphs = sphs;
	}
	@Column(name = "HAS_QD", length = 2)
	public String getHas_qd() {
		if (!StringUtils.isBlank(this.has_qd)) {
			return has_qd;
		}
		return "0";
	}
	public void setHas_qd(String has_qd) {
		this.has_qd = has_qd;
	}
	
	@Column(name = "IS_ZF", length = 2)
	public String getIs_zf() {
		if (!StringUtils.isBlank(this.is_zf)) {
			return is_zf;
		}
		return "0";
	}
	public void setIs_zf(String is_zf) {
		this.is_zf = is_zf;
	}
	@Column(name = "ZF_DATE")
	public Date getZf_date() {
		return zf_date;
	}
	public void setZf_date(Date zf_date) {
		this.zf_date = zf_date;
	}
	@Column(name = "IS_RED", length = 2)
	public String getIs_red() {
		if (!StringUtils.isBlank(this.is_red)) {
			return is_red;
		}
		return "0";
	}
	public void setIs_red(String is_red) {
		this.is_red = is_red;
	}
	@Column(name = "RED_FPDM", length = 36)
	public String getRed_fpdm() {
		return red_fpdm;
	}
	public void setRed_fpdm(String red_fpdm) {
		this.red_fpdm = red_fpdm;
	}
	@Column(name = "RED_FPHM", length = 36)
	public String getRed_fphm() {
		return red_fphm;
	}
	public void setRed_fphm(String red_fphm) {
		this.red_fphm = red_fphm;
	}
	@Column(name = "RED_DATE")
	public Date getRed_date() {
		return red_date;
	}
	public void setRed_date(Date red_date) {
		this.red_date = red_date;
	}
	
	@Column(name = "IS_QZ", length = 2)
	public String getIs_qz() {
		if (!StringUtils.isBlank(this.is_qz)) {
			return is_qz;
		} else {
			return "0";
		}
	}
	public void setIs_qz(String is_qz) {
		this.is_qz = is_qz;
	}
	
	
	
	
	@Column(name = "MOBILE", length = 36)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "EMAIL", length = 36)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IS_QZ_H", length = 2)
	public String getIs_qz_h() {
		if (!StringUtils.isBlank(this.is_qz_h)) {
			return is_qz_h;
		} else {
			return "0";
		}
	}
	public void setIs_qz_h(String is_qz_h) {
		this.is_qz_h = is_qz_h;
	}
	
	@Column(name = "TAX_AMOUNT_H")
	public BigDecimal getTax_amount_h() {
		return tax_amount_h;
	}
	public void setTax_amount_h(BigDecimal tax_amount_h) {
		this.tax_amount_h = tax_amount_h;
	}

	public boolean compare(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		T_invoicedata other = (T_invoicedata) obj;
		
		if (fhr == null) {
			if (this.fhr != null)
				return false;
		} else if (!fhr.equals(other.fhr))
			return false;
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
		if (has_qd == null) {
			if (other.has_qd != null)
				return false;
		} else if (!has_qd.equals(other.has_qd))
			return false;
		if (kpr == null) {
			if (other.kpr != null)
				return false;
		} else if (!kpr.equals(other.kpr))
			return false;
		if (mix == null) {
			if (other.mix != null)
				return false;
		} else if (!mix.equals(other.mix))
			return false;
		if (slv == null) {
			if (other.slv != null)
				return false;
		} else if (!slv.equals(other.slv))
			return false;
		if (skr == null) {
			if (other.skr != null)
				return false;
		} else if (!skr.equals(other.skr))
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
		return true;
	}
	
}
