package aisino.reportform.model.fpmng;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
/**
 * 
* @Title:V_InvoiceHead 
* @Description: 发票头视图实体
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年11月22日 上午9:10:23
 */
@Entity
@Table(name = "V_INVOICEHEAD")
public class V_InvoiceHead implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String ohid;	//	OHID	varchar(36)	本表主键	
	private Date createtime;	//	CREATETIME	datetime	创建时间	
	private String djhm;	//	DJHM	varchar(36)	单据号码
	private String fpzl;	//	FPZL	varchar(2)	发票种类	0:专票,2:普票,41:卷票,51:电子发票
	private String fpdm;	//	FPDM	varchar(36)	发票代码
	private String fphm;	//	FPHM	varchar(36)	发票号码
	private String kpr;		//	KPR	varchar(36)	开票人
	private String fhr;		//	FHR	varchar(36)	复核人	
	private String skr;		//	SKR	varchar(36)	收款人	
	private String gfhm;	//	GFHM	varchar(36)	购方代码	
	private String gfmc;	//	GFMC	varchar(256)	购方名称	
	private String gfsh;	//	GFSH	varchar(36)	购方税号	
	private String gfyhzh;	//	GFYHZH	varchar(256)	购方银行账户	
	private String gfdzdh;	//	GFDZDH	varchar(512)	购方地址电话	
	private String xfmc;	//	XFMC	varchar(256)	销方名称
	private String xfsh;	//	XFSH	varchar(36)	销方税号
	private String xfdzdh;	//	XFDZDH	varchar(512)	销方地址电话
	private String xfyhzh;	//	XFYHZH	varchar(256)	销方银行账户
	private String bz;		//	BZ	varchar(512)	备注	
	private String mix;		//	MIX	varchar(2)	含多税率商品行	1:是,0:否
	private BigDecimal amount;	//	AMOUNT	decimal(30,16)	不含税金额
	private BigDecimal tax_amount;	//	TAX_AMOUNT	decimal(30,16)	税额
	private BigDecimal total;	//	TOTAL	decimal(30,16)	单据商品金额合计	
	private int sphs;	//	SPHS	int	商品行数
	private String has_qd;	//	HAS_QD	varchar(2)	清单标志
	private String is_hx;	//	IS_HX	varchar(2)	回写业务系统标识
	private String is_zf;	//	IS_ZF	varchar(2)	作废标志
	private String old_fpdm;	//	OLD_FPDM	varchar(36)	原发票代码
	private String old_fphm;	//	OLD_FPHM	varchar(36)	原发票号码
	private Date zf_date;		//	ZF_DATE	datetime	作废日期
	private String is_red;		//	IS_RED	varchar(2)	冲红标志
	private String red_fpdm;	//	RED_FPDM	varchar(36)	红票的发票代码
	private String red_fphm;	//	RED_FPHM	varchar(36)	红票的发票编码
	private Date red_date;		//	RED_DATE	datetime	冲红日期
	private String is_print;	//	IS_PRINT	varchar(2)	打印状态
	private String is_download;	//	IS_DOWNLOAD	varchar(2)	下载状态
	private String is_qz;		//	IS_QZ	varchar(2)	签章状态
	private String qzbs;		//	QZBS	varchar(36)	签章返回标识
	private String pdf_file;	//	PDF_FILE	varchar(256)	PDF存放的本地路径
	private String is_tsyx;		//	IS_TSYX	varchar(2)	推送邮箱状态
	private String is_tsdx;		//	IS_TSDX	varchar(2)	推送短信状态
	private String tsfs;		//	TSFS	varchar(2)	推送方式
	private String errlog;		//	ERRLOG	varchar(512)	错误原因(含开票失败和签章失败原因)
	private String mobile;	//	MOBILE	varchar(36)	推送手机号
	private String email;	//	EMAIL	varchar(36)	推送邮箱号
	private String fpqqlsh;	//	FPQQLSH	varchar(36)	发票请求流水号

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@Column(name = "OHID", unique = true, nullable = false, length = 36)
	public String getOhid() {
		return ohid;
	}
	public void setOhid(String ohid) {
		this.ohid = ohid;
	}
	@Column(name = "DJHM")
	public String getDjhm() {
		return djhm;
	}
	public void setDjhm(String djhm) {
		this.djhm = djhm;
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
		return mix;
	}
	public void setMix(String mix) {
		this.mix = mix;
	}
	@Column(name = "KPR", length = 36)
	public String getKpr() {
		return kpr;
	}
	public void setKpr(String kpr) {
		this.kpr = kpr;
	}
	@Column(name = "XFMC", length = 256)
	public String getXfmc() {
		return xfmc;
	}
	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}
	@Column(name = "XFSH", length = 36)
	public String getXfsh() {
		return xfsh;
	}
	public void setXfsh(String xfsh) {
		this.xfsh = xfsh;
	}
	@Column(name = "XFYHZH", length = 256)
	public String getXfyhzh() {
		return xfyhzh;
	}
	public void setXfyhzh(String xfyhzh) {
		this.xfyhzh = xfyhzh;
	}
	@Column(name = "XFDZDH", length = 512)
	public String getXfdzdh() {
		return xfdzdh;
	}
	public void setXfdzdh(String xfdzdh) {
		this.xfdzdh = xfdzdh;
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
	@Column(name = "HAS_QD", length = 2)
	public String getHas_qd() {
		return has_qd;
	}
	public void setHas_qd(String has_qd) {
		this.has_qd = has_qd;
	}
	public int getSphs() {
		return sphs;
	}

	public void setSphs(int sphs) {
		this.sphs = sphs;
	}

	@Column(name = "IS_HX", length = 2)
	public String getIs_hx() {
		return is_hx;
	}
	public void setIs_hx(String is_hx) {
		this.is_hx = is_hx;
	}
	@Column(name = "IS_ZF", length = 2)
	public String getIs_zf() {
		return is_zf;
	}
	public void setIs_zf(String is_zf) {
		this.is_zf = is_zf;
	}
	@Column(name = "OLD_FPDM", length = 36)
	public String getOld_fpdm() {
		return old_fpdm;
	}
	public void setOld_fpdm(String old_fpdm) {
		this.old_fpdm = old_fpdm;
	}
	@Column(name = "OLD_FPHM", length = 36)
	public String getOld_fphm() {
		return old_fphm;
	}
	public void setOld_fphm(String old_fphm) {
		this.old_fphm = old_fphm;
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
		return is_red;
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
	@Column(name = "IS_PRINT", length = 2)
	public String getIs_print() {
		return is_print;
	}
	public void setIs_print(String is_print) {
		this.is_print = is_print;
	}
	@Column(name = "IS_DOWNLOAD", length = 2)
	public String getIs_download() {
		return is_download;
	}
	public void setIs_download(String is_download) {
		this.is_download = is_download;
	}
	@Column(name = "IS_QZ", length = 2)
	public String getIs_qz() {
		return is_qz;
	}
	public void setIs_qz(String is_qz) {
		this.is_qz = is_qz;
	}
	@Column(name = "QZBS", length = 36)
	public String getQzbs() {
		return qzbs;
	}
	public void setQzbs(String qzbs) {
		this.qzbs = qzbs;
	}
	@Column(name = "PDF_FILE", length = 256)
	public String getPdf_file() {
		return pdf_file;
	}
	public void setPdf_file(String pdf_file) {
		this.pdf_file = pdf_file;
	}
	@Column(name = "IS_TSYX", length = 2)
	public String getIs_tsyx() {
		return is_tsyx;
	}
	public void setIs_tsyx(String is_tsyx) {
		this.is_tsyx = is_tsyx;
	}
	@Column(name = "IS_TSDX", length = 2)
	public String getIs_tsdx() {
		return is_tsdx;
	}
	public void setIs_tsdx(String is_tsdx) {
		this.is_tsdx = is_tsdx;
	}
	@Column(name = "TSFS", length = 2)
	public String getTsfs() {
		return tsfs;
	}
	public void setTsfs(String tsfs) {
		this.tsfs = tsfs;
	}
	@Column(name = "ERRLOG", length = 512)
	public String getErrlog() {
		return errlog;
	}
	public void setErrlog(String errlog) {
		this.errlog = errlog;
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
	@Column(name = "FPQQLSH", length = 36)
	public String getFpqqlsh() {
		return fpqqlsh;
	}
	public void setFpqqlsh(String fpqqlsh) {
		this.fpqqlsh = fpqqlsh;
	}
	
	
}
