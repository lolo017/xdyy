package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Bankprivatenumber entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANKPRIVATENUMBER", schema = "")
public class Bankprivatenumber implements java.io.Serializable {

	// Fields

	private String id;
	private String idcard;
	private String pnumber1;
	private String pnumber2;
	private String pnumber3;
	private String pnumber4;
	private String pnumber5;

	// Constructors

	/** default constructor */
	public Bankprivatenumber() {
	}

	/** minimal constructor */
	public Bankprivatenumber(String id) {
		this.id = id;
	}

	/** full constructor */
	public Bankprivatenumber(String id, String idcard, String pnumber1,
			String pnumber2, String pnumber3, String pnumber4, String pnumber5) {
		this.id = id;
		this.idcard = idcard;
		this.pnumber1 = pnumber1;
		this.pnumber2 = pnumber2;
		this.pnumber3 = pnumber3;
		this.pnumber4 = pnumber4;
		this.pnumber5 = pnumber5;
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

	@Column(name = "IDCARD", length = 60)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "PNUMBER1", length = 100)
	public String getPnumber1() {
		return this.pnumber1;
	}

	public void setPnumber1(String pnumber1) {
		this.pnumber1 = pnumber1;
	}

	@Column(name = "PNUMBER2", length = 100)
	public String getPnumber2() {
		return this.pnumber2;
	}

	public void setPnumber2(String pnumber2) {
		this.pnumber2 = pnumber2;
	}

	@Column(name = "PNUMBER3", length = 100)
	public String getPnumber3() {
		return this.pnumber3;
	}

	public void setPnumber3(String pnumber3) {
		this.pnumber3 = pnumber3;
	}

	@Column(name = "PNUMBER4", length = 100)
	public String getPnumber4() {
		return this.pnumber4;
	}

	public void setPnumber4(String pnumber4) {
		this.pnumber4 = pnumber4;
	}

	@Column(name = "PNUMBER5", length = 100)
	public String getPnumber5() {
		return this.pnumber5;
	}

	public void setPnumber5(String pnumber5) {
		this.pnumber5 = pnumber5;
	}

}