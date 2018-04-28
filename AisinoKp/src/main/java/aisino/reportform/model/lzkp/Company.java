package aisino.reportform.model.lzkp;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "KP_COMPANY", schema = "SSHE")
public class Company {

	private String id;
	private String   name;//           VARCHAR2(200),
	private String   tax_code;//       VARCHAR2(40),
	private String   addr;//           VARCHAR2(400),
	private String   tel;//            VARCHAR2(30),
	private String   mobile;//         VARCHAR2(30),
	private String   bank;//           VARCHAR2(200),
	private String   bank_no;//        VARCHAR2(50),
	private String   receipt_person;// VARCHAR2(50),
	private String   check_person;//   VARCHAR2(50),
	private String   kp_person;//      VARCHAR2(50)

	public Company() {
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

	@Column(name = "NAME",length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TAX_CODE",length = 40)
	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	@Column(name = "ADDR",length = 400)
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "TEL",length = 30)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "MOBILE",length = 30)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBank() {
		return bank;
	}

	@Column(name = "BANK",length = 200)
	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public String getReceipt_person() {
		return receipt_person;
	}

	public void setReceipt_person(String receipt_person) {
		this.receipt_person = receipt_person;
	}

	public String getCheck_person() {
		return check_person;
	}

	public void setCheck_person(String check_person) {
		this.check_person = check_person;
	}

	public String getKp_person() {
		return kp_person;
	}

	public void setKp_person(String kp_person) {
		this.kp_person = kp_person;
	}


	
	

}
