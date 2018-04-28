package aisino.reportform.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "T_TICKETSTORE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syticket implements java.io.Serializable{
	private String id;
	private String name;
	private Integer count;
	private String status;
	private Date lrrq;
	private Date xgrq;
	private String fpdm;
	private String fphm;
	private String lyr;
	private Date lysj;
	private String jbid;
	private String org_id;
	private String type_id;
	private Long sort_id;
	
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
	
	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "COUNT", precision = 10, scale = 0)
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Column(name = "STATUS", length = 5)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LRRQ", length = 7)
	public Date getLrrq() {
		if (this.lrrq != null)
			return this.lrrq;
		return new Date();
	}
	
	public void setLrrq(Date lrrq) {
		this.lrrq = lrrq;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "XGRQ", length = 7)
	public Date getXgrq() {
		if (this.xgrq != null)
			return this.xgrq;
		return new Date();
	}
	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
	}

	@Column(name = "FPDM", length = 20)
	public String getFpdm() {
		return fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	@Column(name = "FPHM", length = 20)
	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	@Column(name = "LYR", length = 50)
	public String getLyr() {
		return lyr;
	}

	public void setLyr(String lyr) {
		this.lyr = lyr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LYSJ", length = 7)
	public Date getLysj() {
		return lysj;
	}

	public void setLysj(Date lysj) {
		this.lysj = lysj;
	}

	@Column(name = "JBID", length = 50)
	public String getJbid() {
		return jbid;
	}
	
	public void setJbid(String jbid) {
		this.jbid = jbid;
	}
	@Column(name = "ORG_ID", length = 20)
	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String orgId) {
		org_id = orgId;
	}

	@Column(name = "TYPE_ID")
	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	@Column(name = "SORT_ID")
	public Long getSort_id() {
		return sort_id;
	}

	public void setSort_id(Long sort_id) {
		this.sort_id = sort_id;
	}
	
	
}
