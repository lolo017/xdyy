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
* @Title:Ssflbm 
* @Description: 税收分类编码
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年12月22日 下午4:48:04
 */
@Entity
@Table(name = "T_SSFLBM")
public class Ssflbm implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;	//	ID	varchar(36)	本表主键	
	private String sphm;	//	SPHM	varchar(36)	商品号码	
	private String spmc;	//	SPMC	varchar(256)	商品名称	
	private String ggxh;	//	GGXH	varchar(36)	规格型号	
	private String dw;		//	DW	varchar(36)	单位	
	private BigDecimal slv;	//	SLV	decimal(4,2)	税率	可为0.17  0.13   0.11  0.06  0.03  0.00
	private String ssflbm;	//	SSFLBM	varchar(36)	税收分类编码

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return id;
		}
		return UUID.randomUUID().toString();
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SPHM", length = 36)
	public String getSphm() {
		return sphm;
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
	
}
