package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "PAGECODE_SELF_WARN", schema = "")
public class PageCodeSelfWarn implements java.io.Serializable{
	private String id;
	private PageCode pagecode;
	private Double ltValue;//小于预警值
	private Double gtValue;//大于预警值
	private String color;//颜色代码
	private String cnEnglish;//pagecode英文名
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAGECODEID")
	public PageCode getPagecode() {
		return pagecode;
	}
	public void setPagecode(PageCode pagecode) {
		this.pagecode = pagecode;
	}
	@Column(name = "LT")
	public Double getLtValue() {
		return ltValue;
	}
	public void setLtValue(Double ltValue) {
		this.ltValue = ltValue;
	}	
	
	@Column(name = "GT")
	public Double getGtValue() {
		return gtValue;
	}
	public void setGtValue(Double gtValue) {
		this.gtValue = gtValue;
	}
	
	@Column(name = "COLOR", length=10)
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	@Transient
	public String getCnEnglish() {
		return cnEnglish;
	}
	public void setCnEnglish(String cnEnglish) {
		this.cnEnglish = cnEnglish;
	}
	
	

}
