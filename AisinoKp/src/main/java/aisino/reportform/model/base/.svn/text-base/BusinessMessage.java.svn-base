package aisino.reportform.model.base;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PM_MESSAGE_MX"
    ,schema="JSY"
)
public class BusinessMessage implements java.io.Serializable{

	private String xh;
	private String khdid;
	private String mx;
	private Date sj;
	
	
	public BusinessMessage(String xh, String khdid) {
		super();
		this.xh = xh;
		this.khdid = khdid;
	}
	public BusinessMessage() {
		super();
	}
	public BusinessMessage(String xh, String khdid, String mx,
			Date sj) {
		super();
		this.xh = xh;
		this.khdid = khdid;
		this.mx = mx;
		this.sj = sj;
	}
	
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	@Id
	public String getKhdid() {
		return khdid;
	}
	public void setKhdid(String khdid) {
		this.khdid = khdid;
	}
	public String getMx() {
		return mx;
	}
	public void setMx(String mx) {
		this.mx = mx;
	}
	public Date getSj() {
		return sj;
	}
	public void setSj(Date sj) {
		this.sj = sj;
	}
}
