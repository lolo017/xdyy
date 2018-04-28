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
@Table(name = "PAGECODE_SQL_WARN", schema = "")
public class PageCodeSqlWarn  implements java.io.Serializable{
	private String id;
	private PageCode pagecode;
	private String gtSql;//下限SQL
	private String ltSql;//上限SQL
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
	@Column(name = "GT_SQL")
	public String getGtSql() {
		return gtSql;
	}
	public void setGtSql(String gtSql) {
		this.gtSql = gtSql;
	}
	@Column(name = "LT_SQL")
	public String getLtSql() {
		return ltSql;
	}
	public void setLtSql(String ltSql) {
		this.ltSql = ltSql;
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
