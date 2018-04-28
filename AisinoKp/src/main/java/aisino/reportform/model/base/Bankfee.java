package aisino.reportform.model.base;


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
@Table(name = "BANKFEE", schema = "")
public class Bankfee implements java.io.Serializable {

	// Fields

	private String id;
	private String type;
	private String bigtype;
	private String smalltype;
	private String fine;
	private String newscheme;
	private String mcc;
	private String merchantclassalias;
	private String w1;
	private String w2;
	private String merchantratenew;
	private String merchantrateold;
	private String change;
	private String hairpinprofit;
	private String hairpincapped;
	private String hairpinmoney;
	private String unionpayprofit;
	private String unionpaycapped;
	private String unionpaymoney;
	private String standardprofit;
	private String standardcapped;
	private String standardmoney;
	private String remarks;

	// Constructors

	/** default constructor */
	public Bankfee() {
	}

	/** minimal constructor */
	public Bankfee(String id) {
		this.id = id;
	}

	/** full constructor */
	public Bankfee(String id, String type, String bigtype,
			String smalltype, String fine, String newscheme, String mcc,
			String merchantclassalias, String w1, String w2,
			String merchantratenew, String merchantrateold, String change,
			String hairpinprofit, String hairpincapped,
			String hairpinmoney, String unionpayprofit,
			String unionpaycapped, String unionpaymoney,
			String standardprofit, String standardcapped,
			String standardmoney, String remarks) {
		this.id = id;
		this.type = type;
		this.bigtype = bigtype;
		this.smalltype = smalltype;
		this.fine = fine;
		this.newscheme = newscheme;
		this.mcc = mcc;
		this.merchantclassalias = merchantclassalias;
		this.w1 = w1;
		this.w2 = w2;
		this.merchantratenew = merchantratenew;
		this.merchantrateold = merchantrateold;
		this.change = change;
		this.hairpinprofit = hairpinprofit;
		this.hairpincapped = hairpincapped;
		this.hairpinmoney = hairpinmoney;
		this.unionpayprofit = unionpayprofit;
		this.unionpaycapped = unionpaycapped;
		this.unionpaymoney = unionpaymoney;
		this.standardprofit = standardprofit;
		this.standardcapped = standardcapped;
		this.standardmoney = standardmoney;
		this.remarks = remarks;
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

	@Column(name = "TYPE", precision = 22, scale = 0)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "BIGTYPE", length = 36)
	public String getBigtype() {
		return this.bigtype;
	}

	public void setBigtype(String bigtype) {
		this.bigtype = bigtype;
	}

	@Column(name = "SMALLTYPE", length = 100)
	public String getSmalltype() {
		return this.smalltype;
	}

	public void setSmalltype(String smalltype) {
		this.smalltype = smalltype;
	}

	@Column(name = "FINE", length = 100)
	public String getFine() {
		return this.fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	@Column(name = "NEWSCHEME", length = 100)
	public String getNewscheme() {
		return this.newscheme;
	}

	public void setNewscheme(String newscheme) {
		this.newscheme = newscheme;
	}

	@Column(name = "MCC", precision = 22, scale = 0)
	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	@Column(name = "MERCHANTCLASSALIAS", length = 200)
	public String getMerchantclassalias() {
		return this.merchantclassalias;
	}

	public void setMerchantclassalias(String merchantclassalias) {
		this.merchantclassalias = merchantclassalias;
	}

	@Column(name = "W1", precision = 22, scale = 0)
	public String getW1() {
		return this.w1;
	}

	public void setW1(String w1) {
		this.w1 = w1;
	}

	@Column(name = "W2", precision = 22, scale = 0)
	public String getW2() {
		return this.w2;
	}

	public void setW2(String w2) {
		this.w2 = w2;
	}

	@Column(name = "MERCHANTRATENEW", length = 100)
	public String getMerchantratenew() {
		return this.merchantratenew;
	}

	public void setMerchantratenew(String merchantratenew) {
		this.merchantratenew = merchantratenew;
	}

	@Column(name = "MERCHANTRATEOLD", length = 100)
	public String getMerchantrateold() {
		return this.merchantrateold;
	}

	public void setMerchantrateold(String merchantrateold) {
		this.merchantrateold = merchantrateold;
	}

	@Column(name = "CHANGE", length = 100)
	public String getChange() {
		return this.change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	@Column(name = "HAIRPINPROFIT", length = 100)
	public String getHairpinprofit() {
		return this.hairpinprofit;
	}

	public void setHairpinprofit(String hairpinprofit) {
		this.hairpinprofit = hairpinprofit;
	}

	@Column(name = "HAIRPINCAPPED", length = 4)
	public String getHairpincapped() {
		return this.hairpincapped;
	}

	public void setHairpincapped(String hairpincapped) {
		this.hairpincapped = hairpincapped;
	}

	@Column(name = "HAIRPINMONEY", precision = 22, scale = 0)
	public String getHairpinmoney() {
		return this.hairpinmoney;
	}

	public void setHairpinmoney(String hairpinmoney) {
		this.hairpinmoney = hairpinmoney;
	}

	@Column(name = "UNIONPAYPROFIT", length = 100)
	public String getUnionpayprofit() {
		return this.unionpayprofit;
	}

	public void setUnionpayprofit(String unionpayprofit) {
		this.unionpayprofit = unionpayprofit;
	}

	@Column(name = "UNIONPAYCAPPED", length = 4)
	public String getUnionpaycapped() {
		return this.unionpaycapped;
	}

	public void setUnionpaycapped(String unionpaycapped) {
		this.unionpaycapped = unionpaycapped;
	}

	@Column(name = "UNIONPAYMONEY", precision = 22, scale = 0)
	public String getUnionpaymoney() {
		return this.unionpaymoney;
	}

	public void setUnionpaymoney(String unionpaymoney) {
		this.unionpaymoney = unionpaymoney;
	}

	@Column(name = "STANDARDPROFIT", length = 100)
	public String getStandardprofit() {
		return this.standardprofit;
	}

	public void setStandardprofit(String standardprofit) {
		this.standardprofit = standardprofit;
	}

	@Column(name = "STANDARDCAPPED", length = 4)
	public String getStandardcapped() {
		return this.standardcapped;
	}

	public void setStandardcapped(String standardcapped) {
		this.standardcapped = standardcapped;
	}

	@Column(name = "STANDARDMONEY", precision = 22, scale = 0)
	public String getStandardmoney() {
		return this.standardmoney;
	}

	public void setStandardmoney(String standardmoney) {
		this.standardmoney = standardmoney;
	}

	@Column(name = "REMARKS", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}