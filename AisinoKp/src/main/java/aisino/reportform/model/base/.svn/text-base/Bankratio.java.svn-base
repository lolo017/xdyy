package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Bankratio entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANKRATIO", schema = "")
public class Bankratio implements java.io.Serializable {

	// Fields

	private String id;
	private String customerid;
	private String name;
	private String tradetypes;
	private String tradetypes1;
	private String o1;
	private String o2;
	private String o3;
	private String o4;
	private String o5;
	private String o6;
	private String o7;

	// Constructors

	/** default constructor */
	public Bankratio() {
	}

	/** minimal constructor */
	public Bankratio(String id) {
		this.id = id;
	}

	/** full constructor */
	public Bankratio(String id, String customerid, String name,
			String tradetypes, String tradetypes1, String o1, String o2,
			String o3, String o4, String o5, String o6, String o7) {
		this.id = id;
		this.customerid = customerid;
		this.name = name;
		this.tradetypes = tradetypes;
		this.tradetypes1 = tradetypes1;
		this.o1 = o1;
		this.o2 = o2;
		this.o3 = o3;
		this.o4 = o4;
		this.o5 = o5;
		this.o6 = o6;
		this.o7 = o7;
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

	@Column(name = "CUSTOMERID", length = 100)
	public String getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TRADETYPES", length = 100)
	public String getTradetypes() {
		return this.tradetypes;
	}

	public void setTradetypes(String tradetypes) {
		this.tradetypes = tradetypes;
	}

	@Column(name = "TRADETYPES1", length = 100)
	public String getTradetypes1() {
		return this.tradetypes1;
	}

	public void setTradetypes1(String tradetypes1) {
		this.tradetypes1 = tradetypes1;
	}

	@Column(name = "O1", length = 100)
	public String getO1() {
		return this.o1;
	}

	public void setO1(String o1) {
		this.o1 = o1;
	}

	@Column(name = "O2", length = 100)
	public String getO2() {
		return this.o2;
	}

	public void setO2(String o2) {
		this.o2 = o2;
	}

	@Column(name = "O3", length = 100)
	public String getO3() {
		return this.o3;
	}

	public void setO3(String o3) {
		this.o3 = o3;
	}

	@Column(name = "O4", length = 100)
	public String getO4() {
		return this.o4;
	}

	public void setO4(String o4) {
		this.o4 = o4;
	}

	@Column(name = "O5", length = 100)
	public String getO5() {
		return this.o5;
	}

	public void setO5(String o5) {
		this.o5 = o5;
	}

	@Column(name = "O6", length = 100)
	public String getO6() {
		return this.o6;
	}

	public void setO6(String o6) {
		this.o6 = o6;
	}

	@Column(name = "O7", length = 100)
	public String getO7() {
		return this.o7;
	}

	public void setO7(String o7) {
		this.o7 = o7;
	}

}