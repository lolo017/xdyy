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
* @Title:TjData 
* @Description: 调价单实体类t_tjdata
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年11月15日 下午3:30:17
 */
@Entity
@Table(name = "T_TJDATA")
public class TjData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String odid;	//	ODID	varchar(36)	本表主键	
	private String djhm;	//	DJHM	varchar(36)	单据号码
	private Date createtime;	//	CREATETIME	datetime	创建时间	
	private String old_odid;	//	OLD_ODID	varchar(36)	原单据id
	private String old_djhm;	//	OLD_DJHM	varchar(36)	原单据号码
	private BigDecimal tjsl;	//	TJSL	decimal(30,16)	调价后数量
	private BigDecimal tjdj;	//	TJDJ	decimal(30,6)	调价后单价
	private BigDecimal tjje;	//	TJJE	decimal(30,6)	调价后金额
	private String is_tj;	//	IS_TJ	varchar(2)	调价状态:0-未调价,1-已调价	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@Column(name = "ODID", unique = true, nullable = false, length = 36)
	public String getOdid() {
		if (!StringUtils.isBlank(this.odid)) {
			return odid;
		}
		return UUID.randomUUID().toString();
	}
	public void setOdid(String odid) {
		this.odid = odid;
	}
	@Column(name = "DJHM", length = 36)
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

	@Column(name = "OLD_ODID", length = 36)
	public String getOld_odid() {
		return old_odid;
	}
	public void setOld_odid(String old_odid) {
		this.old_odid = old_odid;
	}

	@Column(name = "OLD_DJHM", length = 36)
	public String getOld_djhm() {
		return old_djhm;
	}
	public void setOld_djhm(String old_djhm) {
		this.old_djhm = old_djhm;
	}

	@Column(name = "TJSL")
	public BigDecimal getTjsl() {
		return tjsl;
	}
	public void setTjsl(BigDecimal tjsl) {
		this.tjsl = tjsl;
	}

	@Column(name = "TJDJ")
	public BigDecimal getTjdj() {
		return tjdj;
	}
	public void setTjdj(BigDecimal tjdj) {
		this.tjdj = tjdj;
	}

	@Column(name = "TJJE")
	public BigDecimal getTjje() {
		return tjje;
	}
	public void setTjje(BigDecimal tjje) {
		this.tjje = tjje;
	}

	@Column(name = "IS_TJ", length = 2)
	public String getIs_tj() {
		return is_tj;
	}
	public void setIs_tj(String is_tj) {
		this.is_tj = is_tj;
	}
	
	
}
