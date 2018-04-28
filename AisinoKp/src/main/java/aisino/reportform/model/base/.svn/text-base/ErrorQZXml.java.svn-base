package aisino.reportform.model.base;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Bankfee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "KP_SIGNATURE_ERROR_XML", schema = "")
public class ErrorQZXml implements java.io.Serializable {

	// Fields

	private String dataexchangeid;
	private String xml;
	private String qz_xml;
	private String invoice_number;
	private String invoice_code;
	private String is_qz;
	private String fail_count;
	private Date last_DATE;
	private String id;
	private String order_id;
	private String ddh;
	

	// Constructors

	public ErrorQZXml(){
		
	}

	/** full constructor */
	public ErrorQZXml(String dataexchangeid,String xml,String qz_xml,
			String invoice_number,String invoice_code,String is_qz,
			String fail_count,Date last_DATE,String id,String order_id,String ddh) {
		this.id = id;
		this.dataexchangeid=dataexchangeid;
		this.xml=xml;
		this.qz_xml=qz_xml;
		this.invoice_code=invoice_code;
		this.invoice_number=invoice_number;
		this.is_qz=is_qz;
		this.fail_count=fail_count;
		this.order_id=order_id;
		this.last_DATE=last_DATE;
		this.ddh=ddh;
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
	@Column(name="dataexchangeid")
	public String getDataexchangeid() {
		return dataexchangeid;
	}

	public void setDataexchangeid(String dataexchangeid) {
		this.dataexchangeid = dataexchangeid;
	}
	@Column(name="xml")
	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	@Column(name="qz_xml")
	public String getQz_xml() {
		return qz_xml;
	}

	public void setQz_xml(String qz_xml) {
		this.qz_xml = qz_xml;
	}
	@Column(name="invoice_number")
	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}
	@Column(name="invoice_code")
	public String getInvoice_code() {
		return invoice_code;
	}

	public void setInvoice_code(String invoice_code) {
		this.invoice_code = invoice_code;
	}
	@Column(name="is_qz")
	public String getIs_qz() {
		return is_qz;
	}

	public void setIs_qz(String is_qz) {
		this.is_qz = is_qz;
	}
	@Column(name="fail_count")
	public String getFail_count() {
		return fail_count;
	}

	public void setFail_count(String fail_count) {
		this.fail_count = fail_count;
	}
	@Column(name="last_DATE")
	public Date getLast_DATE() {
		return last_DATE;
	}

	public void setLast_DATE(Date last_DATE) {
		this.last_DATE = last_DATE;
	}
	@Column(name="order_id")
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	@Column(name="ddh")
	public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}

	

}