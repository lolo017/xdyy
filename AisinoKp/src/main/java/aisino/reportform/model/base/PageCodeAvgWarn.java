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
@Table(name = "PAGECODE_AVG_WARN", schema = "")
public class PageCodeAvgWarn implements java.io.Serializable{
	private String id;
	private PageCode pagecode;
	private String cnEnglishExc;//排除字段
	private String cnEnglishStr;//当排除字段等于这些值时，不计入平均值计算，用+分隔
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
	@Column(name = "CNENGLISH_EXC",length=50)
	public String getCnEnglishExc() {
		return cnEnglishExc;
	}
	public void setCnEnglishExc(String cnEnglishExc) {
		this.cnEnglishExc = cnEnglishExc;
	}
	@Column(name = "CNENGLISH_STR",length=500)
	public String getCnEnglishStr() {
		return cnEnglishStr;
	}
	public void setCnEnglishStr(String cnEnglishStr) {
		this.cnEnglishStr = cnEnglishStr;
	}
	@Column(name = "COLOR",length=10)
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
