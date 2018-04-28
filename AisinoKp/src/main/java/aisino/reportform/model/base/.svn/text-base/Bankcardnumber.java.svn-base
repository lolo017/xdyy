package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Bankcardnumber entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANKCARDNUMBER", schema = "")
public class Bankcardnumber implements java.io.Serializable {

	// Fields

	private String id;
	private String idcard;
	private String number1;
	private String number2;
	private String number3;
	private String number4;

	// Constructors

	/** default constructor */
	public Bankcardnumber() {
	}

	/** minimal constructor */
	public Bankcardnumber(String id) {
		this.id = id;
	}

	/** full constructor */
	public Bankcardnumber(String id, String idcard, String number1,
			String number2, String number3, String number4) {
		this.id = id;
		this.idcard = idcard;
		this.number1 = number1;
		this.number2 = number2;
		this.number3 = number3;
		this.number4 = number4;
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

	@Column(name = "IDCARD", length = 50)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "NUMBER1", length = 200)
	public String getNumber1() {
		return this.number1;
	}

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	@Column(name = "NUMBER2", length = 200)
	public String getNumber2() {
		return this.number2;
	}

	public void setNumber2(String number2) {
		this.number2 = number2;
	}

	@Column(name = "NUMBER3", length = 200)
	public String getNumber3() {
		return this.number3;
	}

	public void setNumber3(String number3) {
		this.number3 = number3;
	}

	@Column(name = "NUMBER4", length = 200)
	public String getNumber4() {
		return this.number4;
	}

	public void setNumber4(String number4) {
		this.number4 = number4;
	}

}