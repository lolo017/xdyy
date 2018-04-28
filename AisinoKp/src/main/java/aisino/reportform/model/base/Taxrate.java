package aisino.reportform.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "T_TAXRATE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Taxrate {
	private String id;
	private String name;
	private String rate;
	private Date create_date;
	private Date update_date;
	private String org_id;

	public Taxrate(String id, String name, String rate,
			 Date create_date,Date update_date) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
		this.create_date = create_date;
		this.update_date = update_date;
	}

	public Taxrate() {
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

	@Column(name = "CREATE_DATE")
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}
	
	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RATE", length = 10)
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@Column(name = "ORG_ID", length = 20)
	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String orgId) {
		org_id = orgId;
	}

	@Column(name = "UPDATE_DATE")
	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}
	
	

}
