package aisino.reportform.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ORG", schema = "")
public class SysOrg implements java.io.Serializable {

	// Fields

	private Integer orgId;
	private String orgName;
	private String orgShortName;

	// Constructors

	/** default constructor */
	public SysOrg() {
	}

	/** minimal constructor */
	public SysOrg(Integer orgId, String orgName) {
		this.orgId = orgId;
		this.orgName = orgName;
	}

	/** full constructor */
	public SysOrg(Integer orgId, String orgName, String orgShortName) {
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgShortName = orgShortName;
	}

	// Property accessors
	@Id
	@Column(name = "ORG_ID", nullable = false, precision = 5, scale = 0)
	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_NAME", nullable = false, length = 200)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "ORG_SHORT_NAME", length = 40)
	public String getOrgShortName() {
		return this.orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

}