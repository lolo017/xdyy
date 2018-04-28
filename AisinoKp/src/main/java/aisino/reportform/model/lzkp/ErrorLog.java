package aisino.reportform.model.lzkp;

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
@Table(name = "KP_SIGNATURE_ERROR_LOG", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ErrorLog {

	private String id;
	private  String taxcode;
	private Date sig_date;
	private String message;
	private String code;
	private String dataexchangeid;
	private String order_id;

	public ErrorLog(String id, String taxcode, String message,String code,
			 Date sig_date,String dataexchangeid) {
		super();
		this.id = id;
		this.taxcode = taxcode;
		this.sig_date = sig_date;
		this.message = message;
		this.code = code;
		this.dataexchangeid = dataexchangeid;
	}

	public ErrorLog() {
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

	@Column(name = "TAXCODE", length = 20)
	public String getTaxcode() {
		return taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	@Column(name = "SIG_DATE")
	public Date getSig_date() {
		return sig_date;
	}

	public void setSig_date(Date sig_date) {
		this.sig_date = sig_date;
	}

	@Column(name = "MESSAGE", length = 2000)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "CODE", length = 20)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "DATAEXCHANGEID", length = 25)
	public String getDataexchangeid() {
		return dataexchangeid;
	}

	public void setDataexchangeid(String dataexchangeid) {
		this.dataexchangeid = dataexchangeid;
	}

	@Column(name = "ORDER_ID",length = 20)
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	
}
