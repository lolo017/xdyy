package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL条件 model
 * @author 廖宸宇
 * @date 2015-3-17
 */
@Entity
@Table(name = "SQLCONDITION", schema = "")
public class Sqlcondition implements java.io.Serializable{

	// Fields

	private String id;
	private Page page;
	private String cncondition;
	private String encondition;
	private String conditiontype;
	private String conditionsql;
	private Integer seq;
	private String defaultValue;

	// Constructors

	/** default constructor */
	public Sqlcondition() {
	}

	/** minimal constructor */
	public Sqlcondition(String id) {
		this.id = id;
	}

	/** full constructor */
	public Sqlcondition(String id, Page page, String cncondition,
			String encondition, String conditiontype, String conditionsql ,Integer seq) {
		this.id = id;
		this.page = page;
		this.cncondition = cncondition;
		this.encondition = encondition;
		this.conditiontype = conditiontype;
		this.conditionsql = conditionsql;
		this.seq = seq;
	}
	public Sqlcondition(String id, String cncondition,
			String encondition, String conditiontype, String conditionsql,Integer seq,String defaultValue) {
		this.id = id;
		this.cncondition = cncondition;
		this.encondition = encondition;
		this.conditiontype = conditiontype;
		this.conditionsql = conditionsql;
		this.seq = seq;
		this.defaultValue=defaultValue;
	}
	

	// Property accessors
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
	@JoinColumn(name = "PAGEID")
	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Column(name = "CNCONDITION", length = 36)
	public String getCncondition() {
		return this.cncondition;
	}

	public void setCncondition(String cncondition) {
		this.cncondition = cncondition;
	}

	@Column(name = "ENCONDITION", length = 36)
	public String getEncondition() {
		return this.encondition;
		
	}

	public void setEncondition(String encondition) {
		this.encondition = encondition;
	}

	@Column(name = "CONDITIONTYPE", length = 10)
	public String getConditiontype() {
		return this.conditiontype;
	}

	public void setConditiontype(String conditiontype) {
		this.conditiontype = conditiontype;
	}

	@Column(name = "CONDITIONSQL", length = 4000)
	public String getConditionsql() {
		return this.conditionsql;
	}

	public void setConditionsql(String conditionsql) {
		this.conditionsql = conditionsql;
	}
	@Column(name = "SEQ", length = 10)
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	@Column(name = "DEFAULT_VALUE", length = 100)
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
