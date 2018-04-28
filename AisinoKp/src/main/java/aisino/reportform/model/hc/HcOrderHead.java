package aisino.reportform.model.hc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HcOrderHead {
	private String address;
	private String phone;
	private String buyerEmail;
	private String buyerPhone;
	private String buyername;
	private String express_no;
	private String invoice_company_account;
	private String invoice_company_bank;
	private String invoice_company_phone;
	private String invoice_content;
	private String invoice_identificationnumber;
	private BigDecimal invoice_money;
	private String invoice_tid;
	private String invoice_type;
	private ArrayList<Item> invoice_tid_item;
	private String invoice_title;
	private BigDecimal exess_Money;
	
	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getExpress_no() {
		return express_no;
	}

	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}

	public String getInvoice_company_account() {
		return invoice_company_account;
	}

	public void setInvoice_company_account(String invoice_company_account) {
		this.invoice_company_account = invoice_company_account;
	}

	public String getInvoice_company_bank() {
		return invoice_company_bank;
	}

	public void setInvoice_company_bank(String invoice_company_bank) {
		this.invoice_company_bank = invoice_company_bank;
	}

	public String getInvoice_company_phone() {
		return invoice_company_phone;
	}

	public void setInvoice_company_phone(String invoice_company_phone) {
		this.invoice_company_phone = invoice_company_phone;
	}

	public String getInvoice_content() {
		return invoice_content;
	}

	public void setInvoice_content(String invoice_content) {
		this.invoice_content = invoice_content;
	}

	public String getInvoice_identificationnumber() {
		return invoice_identificationnumber;
	}

	public void setInvoice_identificationnumber(String invoice_identificationnumber) {
		this.invoice_identificationnumber = invoice_identificationnumber;
	}

	public BigDecimal getInvoice_money() {
		return invoice_money;
	}

	public void setInvoice_money(BigDecimal invoice_money) {
		this.invoice_money = invoice_money;
	}

	public String getInvoice_tid() {
		return invoice_tid;
	}

	public void setInvoice_tid(String invoice_tid) {
		this.invoice_tid = invoice_tid;
	}

	public ArrayList<Item> getInvoice_tid_item() {
		return invoice_tid_item;
	}

	public void setInvoice_tid_item(ArrayList<Item> invoice_tid_item) {
		this.invoice_tid_item = invoice_tid_item;
	}

	public String getInvoice_title() {
		return invoice_title;
	}

	public void setInvoice_title(String invoice_title) {
		this.invoice_title = invoice_title;
	}

	public BigDecimal getExess_Money() {
		return exess_Money;
	}

	public void setExess_Money(BigDecimal exess_Money) {
		this.exess_Money = exess_Money;
	}
	
	
}
