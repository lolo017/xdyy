package aisino.reportform.model.lzkp;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "KP_EINVOICE_COMPANY", schema = "")
public class CompanyRegister {

	private String id;
	private String   taxcode;//          VARCHAR2(30) not null,
	private String   company;//            VARCHAR2(200),
	private Date   regdate;//            DATE,
	private String   username;//           VARCHAR2(50),
	private String   password;//           VARCHAR2(100),
	private String   authorizationcode;//  VARCHAR2(50),
	private String   regcode;//            VARCHAR2(50),
	private Date   modified_date;//      DATE

	public CompanyRegister() {
	}

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

	@Column(name = "TAXCODE",length = 30)
	public String getTaxcode() {
		return taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	@Column(name = "COMPANY",length = 200)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "REGDATE")
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Column(name = "USERNAME",length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD",length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "AUTHORIZATIONCODE",length = 50)
	public String getAuthorizationcode() {
		return authorizationcode;
	}

	public void setAuthorizationcode(String authorizationcode) {
		this.authorizationcode = authorizationcode;
	}

	@Column(name = "REGCODE",length = 50)
	public String getRegcode() {
		return regcode;
	}

	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}

	@Column(name = "MODIFIED_DATE")
	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}



}
