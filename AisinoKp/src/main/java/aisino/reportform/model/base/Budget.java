package aisino.reportform.model.base;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Budget entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BUDGET"
    ,schema="SSHE"
)

public class Budget  implements java.io.Serializable {

	// Fields

	private String indexs;
	private String directives;
	private BigDecimal iscu;
	private BigDecimal pid;
	private Integer uu;
	private String code;
	private String type;
	private Set<Specific> specifics = new HashSet<Specific>(0);
	
	@Column(name = "UU", precision = 22, scale = 0)
	public Integer getUu() {
		return uu;
	}

	public void setUu(Integer uu) {
		this.uu = uu;
	}

	

	// Constructors

	/** default constructor */
	public Budget() {
	}

	/** minimal constructor */
	public Budget(String indexs) {
		this.indexs = indexs;
	}

	/** full constructor */
	public Budget(String indexs, String directives, BigDecimal iscu,
			BigDecimal pid, Integer uu,Set<Specific> specifics) {
		this.indexs = indexs;
		this.directives = directives;
		this.iscu = iscu;
		this.pid = pid;
		this.uu=uu;
		this.specifics = specifics;
	}

	// Property accessors
	@Id
	@Column(name = "INDEXS", unique = true, nullable = false, length = 36)
	public String getIndexs() {
		return this.indexs;
	}

	public void setIndexs(String indexs) {
		this.indexs = indexs;
	}

	@Column(name = "DIRECTIVES", length = 200)
	public String getDirectives() {
		return this.directives;
	}

	public void setDirectives(String directives) {
		this.directives = directives;
	}

	@Column(name = "ISCU", precision = 22, scale = 0)
	public BigDecimal getIscu() {
		return this.iscu;
	}

	public void setIscu(BigDecimal iscu) {
		this.iscu = iscu;
	}

	@Column(name = "PID", precision = 22, scale = 0)
	public BigDecimal getPid() {
		return this.pid;
	}

	public void setPid(BigDecimal pid) {
		this.pid = pid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "budget")
	public Set<Specific> getSpecifics() {
		return this.specifics;
	}

	public void setSpecifics(Set<Specific> specifics) {
		this.specifics = specifics;
	}
	@Column(name = "CODE", length=36)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "TYPE", length=36)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}