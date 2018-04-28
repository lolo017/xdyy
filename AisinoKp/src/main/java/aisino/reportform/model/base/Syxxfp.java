package aisino.reportform.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "T_XXFP", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syxxfp {
	private String fpid;
	private String gfsh;
	private String gfmc;
	private String gfdzdh;
	private Date kprq;
	private String kpy;
	private String sjje;
	
	public Syxxfp() {
	}

	
	
	public Syxxfp(String fpid, String gfsh, String gfmc, String gfdzdh, Date kprq, String kpy) {
		super();
		this.fpid = fpid;
		this.gfsh = gfsh;
		this.gfmc = gfmc;
		this.gfdzdh = gfdzdh;
		this.kprq = kprq;
		this.kpy = kpy;
	}



	@Id
	@Column(name = "FPID", length = 50)
	public String getFpid() {
		return fpid;
	}

	public void setFpid(String fpid) {
		this.fpid = fpid;
	}

	@Column(name = "GFSH", length = 50)
	public String getGfsh() {
		return gfsh;
	}

	public void setGfsh(String gfsh) {
		this.gfsh = gfsh;
	}


	@Column(name = "GFMC", length = 50)
	public String getGfmc() {
		return gfmc;
	}



	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}


	@Column(name = "GFDZDH", length = 100)
	public String getGfdzdh() {
		return gfdzdh;
	}


	public void setGfdzdh(String gfdzdh) {
		this.gfdzdh = gfdzdh;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "KPRQ", length = 7)
	public Date getKprq() {
		if (this.kprq != null)
			return this.kprq;
		return new Date();
	}

	public void setKprq(Date kprq) {
		this.kprq = kprq;
	}

	@Column(name = "KPY", length = 100)
	public String getKpy() {
		return kpy;
	}

	public void setKpy(String kpy) {
		this.kpy = kpy;
	}

	@Column(name = "SJJE", length = 10)
	public String getSjje() {
		return sjje;
	}

	public void setSjje(String sjje) {
		this.sjje = sjje;
	}
}
