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
@Table(name = "T_JB", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syjb {
	private String id;
	private String jbbh;
	private Date jbks;
	private Date jbjs;
	private Date lrrq;
	private Date xgrq;
	private String name;
	
	@Id
	@Column(name = "ID",length = 50)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "JBBH",length = 50)
	public String getJbbh() {
		return jbbh;
	}
	public void setJbbh(String jbbh) {
		this.jbbh = jbbh;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "JBKS", length = 7)
	public Date getJbks() {
		return jbks;
	}
	public void setJbks(Date jbks) {
		this.jbks = jbks;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "JBJS", length = 7)
	public Date getJbjs() {
		return jbjs;
	}
	public void setJbjs(Date jbjs) {
		this.jbjs = jbjs;
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
	
	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
