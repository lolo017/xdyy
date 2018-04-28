package aisino.reportform.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BUYERINFO")
public class Buyerinfo {
	private String id;
	private String gfhm;
	private String gfmc;
	private String gfsh;
	private String dzdh;
	private String yhzh;
	private String mobile;
	private String email;
	
	@Id
	@Column(name="ID",length=36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="GFHM",length=36)
	public String getGfhm() {
		return gfhm;
	}
	public void setGfhm(String gfhm) {
		this.gfhm = gfhm;
	}
	@Column(name="GFMC",length=256)
	public String getGfmc() {
		return gfmc;
	}
	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}
	@Column(name="GFSH",length=36)
	public String getGfsh() {
		return gfsh;
	}
	public void setGfsh(String gfsh) {
		this.gfsh = gfsh;
	}
	@Column(name="DZDH",length=512)
	public String getDzdh() {
		return dzdh;
	}
	public void setDzdh(String dzdh) {
		this.dzdh = dzdh;
	}
	@Column(name="YHZH",length=36)
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	@Column(name="MOBILE",length=36)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="EMAIL",length=36)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
