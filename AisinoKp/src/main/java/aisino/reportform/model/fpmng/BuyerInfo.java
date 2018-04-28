package aisino.reportform.model.fpmng;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
* @Title:BuyerInfo 
* @Description: 购方信息
* Company    JS-YFB LTD
* @author 吕振宇
* @version V1.0    
* @date 2017年12月22日 上午9:31:34
 */
@Entity
@Table(name = "T_BUYERINFO")
public class BuyerInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id; //主键id
	private String gfhm; //购方号码
	private String gfmc; //购方名称
	private String gfsh; //购方税号
	private String dzdh; //地址电话
	private String yhzh; //银行账户
	private String mobile; //手机号码
	private String email; //邮箱
	
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
	@Column(name="YHZH",length=256)
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
