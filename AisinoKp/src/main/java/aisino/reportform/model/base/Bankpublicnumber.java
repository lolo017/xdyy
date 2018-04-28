package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Bankpublicnumber entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANKPUBLICNUMBER", schema = "")
public class Bankpublicnumber implements java.io.Serializable {

	// Fields

	private String id;
	private String idcard;
	private String unumber1;
	private String unumber2;
	private String unumber3;
	private String unumber4;

	// Constructors

	/** default constructor */
	public Bankpublicnumber() {
	}

	/** minimal constructor */
	public Bankpublicnumber(String id) {
		this.id = id;
	}

	/** full constructor */
	public Bankpublicnumber(String id, String idcard, String unumber1,
			String unumber2, String unumber3, String unumber4) {
		this.id = id;
		this.idcard = idcard;
		this.unumber1 = unumber1;
		this.unumber2 = unumber2;
		this.unumber3 = unumber3;
		this.unumber4 = unumber4;
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

	@Column(name = "IDCARD", length = 100)
	public String getIdcard() {
		return this.idcard;
		}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "UNUMBER1", length = 100)
	public String getUnumber1() {
		return this.unumber1;
	}

	public void setUnumber1(String unumber1) {
		this.unumber1 = unumber1;
	}

	@Column(name = "UNUMBER2", length = 100)
	public String getUnumber2() {
		return this.unumber2;
	}

	public void setUnumber2(String unumber2) {
		this.unumber2 = unumber2;
	}

	@Column(name = "UNUMBER3", length = 100)
	public String getUnumber3() {
		return this.unumber3;
	}

	public void setUnumber3(String unumber3) {
		this.unumber3 = unumber3;
	}

	@Column(name = "UNUMBER4", length = 100)
	public String getUnumber4() {
		return this.unumber4;
	}

	public void setUnumber4(String unumber4) {
		this.unumber4 = unumber4;
	}

}