package aisino.reportform.model.lzkp;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "KP_FP_KJMX", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EinvoiceDetail {
	private String id;

	private String invoice_code;
	private String invoice_number;
	private String spmc;
	private String sm;
	private String sl;
	private String ggxh;
	private String jldw;
	private String spsl;
	private String spdj;
	private String spje;
	private String fphxz;
	private String hsjbz;
	private String se;
	private String spbm;
	private String zxbm;
	private String yhzcbs;
	private String lslbs;
	private String zzstsgl;
	private String kce;

	public EinvoiceDetail(String id,String invoice_code, String invoice_number,
			String spmc, String sm, String sl, String ggxh, String jldw,
			String spsl, String spdj, String spje, String fphxz, String hsjbz,
			String se, String spbm, String zxbm, String yhzcbs, String lslbs,
			String zzstsgl, String kce) {
		this.fphxz = fphxz;
		this.ggxh = ggxh;
		this.hsjbz = hsjbz;
		this.invoice_code = invoice_code;
		this.invoice_number = invoice_number;
		this.jldw = jldw;
		this.kce = kce;
		this.lslbs = lslbs;
		this.se = se;
		this.sl = sl;
		this.sm = sm;
		this.spbm = spbm;
		this.spdj = spdj;
		this.spje = spje;
		this.spmc = spmc;
		this.spsl = spsl;
		this.yhzcbs = yhzcbs;
		this.zxbm = zxbm;
		this.zzstsgl = zzstsgl;

	}

	public EinvoiceDetail() {
	}
	
	
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

	@Column(name = "INVOICE_CODE", length = 200)
	public String getInvoice_code() {
		return invoice_code;
	}

	public void setInvoice_code(String invoice_code) {
		this.invoice_code = invoice_code;
	}

	@Column(name = "INVOICE_NUMBER", length = 200)
	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	@Column(name = "SPMC", length = 200)
	public String getSpmc() {
		return spmc;
	}

	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}

	@Column(name = "SM", length = 200)
	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	@Column(name = "SL", length = 200)
	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	@Column(name = "GGXH", length = 200)
	public String getGgxh() {
		return ggxh;
	}

	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}

	@Column(name = "JLDW", length = 200)
	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	@Column(name = "SPSL", length = 200)
	public String getSpsl() {
		return spsl;
	}

	public void setSpsl(String spsl) {
		this.spsl = spsl;
	}

	@Column(name = "SPDJ", length = 200)
	public String getSpdj() {
		return spdj;
	}

	public void setSpdj(String spdj) {
		this.spdj = spdj;
	}

	@Column(name = "SPJE", length = 200)
	public String getSpje() {
		return spje;
	}

	public void setSpje(String spje) {
		this.spje = spje;
	}

	@Column(name = "FPHXZ", length = 200)
	public String getFphxz() {
		return fphxz;
	}

	public void setFphxz(String fphxz) {
		this.fphxz = fphxz;
	}

	@Column(name = "HSJBZ", length = 200)
	public String getHsjbz() {
		return hsjbz;
	}

	public void setHsjbz(String hsjbz) {
		this.hsjbz = hsjbz;
	}

	@Column(name = "SE", length = 200)
	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	@Column(name = "SPBM", length = 200)
	public String getSpbm() {
		return spbm;
	}

	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}

	@Column(name = "ZXBM", length = 200)
	public String getZxbm() {
		return zxbm;
	}

	public void setZxbm(String zxbm) {
		this.zxbm = zxbm;
	}

	@Column(name = "YHZCBS", length = 200)
	public String getYhzcbs() {
		return yhzcbs;
	}

	public void setYhzcbs(String yhzcbs) {
		this.yhzcbs = yhzcbs;
	}

	@Column(name = "LSLBS", length = 200)
	public String getLslbs() {
		return lslbs;
	}

	public void setLslbs(String lslbs) {
		this.lslbs = lslbs;
	}

	@Column(name = "ZZSTSGL", length = 200)
	public String getZzstsgl() {
		return zzstsgl;
	}

	public void setZzstsgl(String zzstsgl) {
		this.zzstsgl = zzstsgl;
	}

	@Column(name = "KCE", length = 200)
	public String getKce() {
		return kce;
	}

	public void setKce(String kce) {
		this.kce = kce;
	}

}
