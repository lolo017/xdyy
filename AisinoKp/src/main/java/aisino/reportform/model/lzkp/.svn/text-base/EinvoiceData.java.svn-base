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
@Table(name = "KP_EINVOICE_DATA", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class EinvoiceData {

	private String id;
	private String invoice_code;// VARCHAR2(20) not null,
	private String invoice_number;// VARCHAR2(20) not null,
	private String saler_taxcode;// VARCHAR2(20),
	private String buyer_taxcode;// VARCHAR2(20),
	private Date invoice_date;// DATE,
	private String amount;// VARCHAR2(20),
	private String tax_amount;// VARCHAR2(20),
	private String pdfdata;// CLOB,
	private String pdf_file;// CLOB,
	private String total;// VARCHAR2(20),
	private int is_download;// NUMBER(1)
	private String order_id;
	private String old_invoice_code;
	private String old_invoice_number;
	private String BUYER_NAME;//	N	VARCHAR2(100)	Y			购方名称
	private String KPR;//	N	VARCHAR2(100)	Y			开票人
	private String SKR;//	N	VARCHAR2(100)	Y			收款人
	private String FHR;//	N	VARCHAR2(100)	Y			复核人
	private String INVOICE_TYPE;//	N	VARCHAR2(100)	Y			发票各类名称
	private String BUYER_BANK_NO;//	N	VARCHAR2(200)	Y			购方银行和账号
	private String IS_RED;
	private String RED_INVOICE_CODE;
	private String RED_INVOICE_NUMBER;
	private Date RED_DATE;
	private String fpdd;

	public EinvoiceData(String id, String invoice_code, String invoice_number, String saler_taxcode,
			String buyer_taxcode, Date invoice_date, String amount, String tax_amount,String pdfdata,
			String total, int is_download) {
		super();
		this.id = id;
		this.amount = amount;
		this.buyer_taxcode = buyer_taxcode;
		this.invoice_code = invoice_code;
		this.invoice_date = invoice_date;
		this.invoice_number = invoice_number;
		this.is_download = is_download;
		this.pdfdata = pdfdata;
		this.saler_taxcode = saler_taxcode;
		this.tax_amount = tax_amount;
		this.total = total;
	}

	public EinvoiceData() {
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

	@Column(name = "INVOICE_CODE",length = 20)
	public String getInvoice_code() {
		return invoice_code;
	}

	public void setInvoice_code(String invoice_code) {
		this.invoice_code = invoice_code;
	}

	@Column(name = "INVOICE_NUMBER",length =20 )
	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	@Column(name = "SALER_TAXCODE",length = 20)
	public String getSaler_taxcode() {
		return saler_taxcode;
	}

	public void setSaler_taxcode(String saler_taxcode) {
		this.saler_taxcode = saler_taxcode;
	}

	@Column(name = "BUYER_TAXCODE",length = 20)
	public String getBuyer_taxcode() {
		return buyer_taxcode;
	}

	public void setBuyer_taxcode(String buyer_taxcode) {
		this.buyer_taxcode = buyer_taxcode;
	}

	@Column(name = "INVOICE_DATE")
	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	@Column(name = "AMOUNT",length = 20)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "TAX_AMOUNT",length =20 )
	public String getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(String tax_amount) {
		this.tax_amount = tax_amount;
	}

	@Column(name = "PDFDATA" )
	public String getPdfdata() {
		return pdfdata;
	}

	public void setPdfdata(String pdfdata) {
		this.pdfdata = pdfdata;
	}

	@Column(name = "TOTAL",length = 20)
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Column(name = "IS_DOWNLOAD",length = 1)
	public int getIs_download() {
		return is_download;
	}

	public void setIs_download(int is_download) {
		this.is_download = is_download;
	}

	@Column(name = "ORDER_ID",length = 20)
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	@Column(name = "OLD_INVOICE_CODE",length = 20)
	public String getOld_invoice_code() {
		return old_invoice_code;
	}

	public void setOld_invoice_code(String old_invoice_code) {
		this.old_invoice_code = old_invoice_code;
	}

	@Column(name = "OLD_INVOICE_NUMBER",length = 20)
	public String getOld_invoice_number() {
		return old_invoice_number;
	}

	public void setOld_invoice_number(String old_invoice_number) {
		this.old_invoice_number = old_invoice_number;
	}

	@Column(name = "BUYER_NAME",length = 100)
	public String getBUYER_NAME() {
		return BUYER_NAME;
	}

	public void setBUYER_NAME(String bUYER_NAME) {
		BUYER_NAME = bUYER_NAME;
	}

	@Column(name = "KPR",length = 100)
	public String getKPR() {
		return KPR;
	}

	public void setKPR(String kPR) {
		KPR = kPR;
	}

	@Column(name = "SKR",length = 100)
	public String getSKR() {
		return SKR;
	}

	public void setSKR(String sKR) {
		SKR = sKR;
	}

	@Column(name = "FHR",length = 100)
	public String getFHR() {
		return FHR;
	}

	public void setFHR(String fHR) {
		FHR = fHR;
	}

	@Column(name = "INVOICE_TYPE",length = 100)
	public String getINVOICE_TYPE() {
		return INVOICE_TYPE;
	}

	public void setINVOICE_TYPE(String iNVOICE_TYPE) {
		INVOICE_TYPE = iNVOICE_TYPE;
	}

	@Column(name = "BUYER_BANK_NO",length = 200)
	public String getBUYER_BANK_NO() {
		return BUYER_BANK_NO;
	}

	public void setBUYER_BANK_NO(String bUYER_BANK_NO) {
		BUYER_BANK_NO = bUYER_BANK_NO;
	}

	@Column(name = "IS_RED",length = 1)
	public String getIS_RED() {
		return IS_RED;
	}

	public void setIS_RED(String iS_RED) {
		IS_RED = iS_RED;
	}

	@Column(name = "RED_INVOICE_CODE",length = 20)
	public String getRED_INVOICE_CODE() {
		return RED_INVOICE_CODE;
	}

	public void setRED_INVOICE_CODE(String rED_INVOICE_CODE) {
		RED_INVOICE_CODE = rED_INVOICE_CODE;
	}

	@Column(name = "RED_INVOICE_NUMBER",length = 20)
	public String getRED_INVOICE_NUMBER() {
		return RED_INVOICE_NUMBER;
	}

	public void setRED_INVOICE_NUMBER(String rED_INVOICE_NUMBER) {
		RED_INVOICE_NUMBER = rED_INVOICE_NUMBER;
	}

	@Column(name = "RED_DATE")
	public Date getRED_DATE() {
		return RED_DATE;
	}

	public void setRED_DATE(Date rED_DATE) {
		RED_DATE = rED_DATE;
	}

	@Column(name = "PDF_FILE" )
	public String getPdf_file() {
		return pdf_file;
	}

	public void setPdf_file(String pdf_file) {
		this.pdf_file = pdf_file;
	}

	public String getFpdd() {
		return fpdd;
	}

	public void setFpdd(String fpdd) {
		this.fpdd = fpdd;
	}
	

}
