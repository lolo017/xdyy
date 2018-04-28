package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "T_XXFPMX", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syxxfpmx {
	private String id;
	private String fpid;
	private String sz;
	private String pmmc;
	private String kssl;
	private String xssr;
	private String slv;
	private String ssq;
	private String kce;
	private String sjje;
	private String dxje;
	private String bz;


	@Id
	@Column(name = "ID", length = 10)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "FPID", length = 50)
	public String getFpid() {
		return fpid;
	}

	public void setFpid(String fpid) {
		this.fpid = fpid;
	}

	@Column(name = "SZ", length = 50)
	public String getSz() {
		return sz;
	}

	public void setSz(String sz) {
		this.sz = sz;
	}

	@Column(name = "PMMC", length = 50)
	public String getPmmc() {
		return pmmc;
	}

	public void setPmmc(String pmmc) {
		this.pmmc = pmmc;
	}

	
	@Column(name = "KSSL", length = 10)
	public String getKssl() {
		return kssl;
	}

	public void setKssl(String kssl) {
		this.kssl = kssl;
	}

	
	@Column(name = "XSSR", length = 10)
	public String getXssr() {
		return xssr;
	}

	public void setXssr(String xssr) {
		this.xssr = xssr;
	}

	@Column(name = "SLV", length = 10)
	public String getSlv() {
		return slv;
	}



	public void setSlv(String slv) {
		this.slv = slv;
	}

	@Column(name = "SSQ", length = 20)
	public String getSsq() {
		return ssq;
	}

	public void setSsq(String ssq) {
		this.ssq = ssq;
	}

	@Column(name = "KCE", length = 10)
	public String getKce() {
		return kce;
	}

	public void setKce(String kce) {
		this.kce = kce;
	}

	@Column(name = "SJJE", length = 10)
	public String getSjje() {
		return sjje;
	}

	public void setSjje(String sjje) {
		this.sjje = sjje;
	}

	@Column(name = "DXJE", length = 20)
	public String getDxje() {
		return dxje;
	}

	public void setDxje(String dxje) {
		this.dxje = dxje;
	}

	@Column(name = "BZ", length = 100)
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
	
}
