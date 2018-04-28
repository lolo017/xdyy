package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "T_ZFQK", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Syzfqk {
	private String id;
	private String fpid;
	private String zfqk;
	
	@Id
	@Column(name = "ID", length = 50)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "FPID", length = 50)
	public String getFpid() {
		return fpid;
	}
	public void setFpid(String fpid) {
		this.fpid = fpid;
	}
	
	@Column(name = "ZFQK", length = 500)
	public String getZfqk() {
		return zfqk;
	}
	public void setZfqk(String zfqk) {
		this.zfqk = zfqk;
	}
	
	
}
