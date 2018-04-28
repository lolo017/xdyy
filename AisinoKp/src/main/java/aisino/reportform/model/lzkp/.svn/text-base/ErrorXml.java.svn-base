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
@Table(name = "KP_SIGNATURE_ERROR_XML", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ErrorXml {

	private String dataexchangeid;
	private String xml;
	private String qz_xml;
	private String invoice_number;
	private String invoice_code;
	private String is_qz;
	private int fail_count;
	private Date last_date;
	private String id;
	private String order_id;
	private String ddh;
	public ErrorXml(String id,String dataexchangeid, String xml, String qz_xml,
			String invoice_number, String invoice_code, String is_qz,
			int fail_count, Date last_date,String order_id,String ddh) {
		super();
		this.id = id;
		this.dataexchangeid = dataexchangeid;
		this.xml = xml;
		this.qz_xml = qz_xml;
		this.invoice_code = invoice_code;
		this.invoice_number = invoice_number;
		this.is_qz = is_qz;
		this.fail_count = fail_count;
		this.last_date = last_date;
		this.order_id = order_id;
		this.ddh=ddh;
	}

	public ErrorXml() {
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

	@Column(name = "DATAEXCHANGEID", length = 50)
	public String getDataexchangeid() {
		return dataexchangeid;
	}

	public void setDataexchangeid(String dataexchangeid) {
		this.dataexchangeid = dataexchangeid;
	}

	@Column(name = "XML")
	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@Column(name = "QZ_XML")
	public String getQz_xml() {
		return qz_xml;
	}

	public void setQz_xml(String qz_xml) {
		this.qz_xml = qz_xml;
	}

	@Column(name = "INVOICE_NUMBER", length = 30)
	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	@Column(name = "INVOICE_CODE", length = 30)
	public String getInvoice_code() {
		return invoice_code;
	}

	public void setInvoice_code(String invoice_code) {
		this.invoice_code = invoice_code;
	}

	@Column(name = "IS_QZ", length = 1)
	public String getIs_qz() {
		return is_qz;
	}

	public void setIs_qz(String is_qz) {
		this.is_qz = is_qz;
	}

	@Column(name = "FAIL_COUNT", length = 5)
	public int getFail_count() {
		return fail_count;
	}

	public void setFail_count(int fail_count) {
		this.fail_count = fail_count;
	}

	@Column(name = "LAST_DATE")
	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}
	@Column(name = "ORDER_ID",length = 20)
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	@Column(name = "DDH",length = 20)
	public String getDdh() {
		return ddh;
	}

	public void setDdh(String ddh) {
		this.ddh = ddh;
	}
	
	

}
