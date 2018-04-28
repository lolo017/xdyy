package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * Banktransactionrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANKTRANSACTIONRECORD", schema = "")
public class Banktransactionrecord implements java.io.Serializable {

	// Fields

	private String id;
	private String particularyear;
	private String month;
	private String branch;
	private String merchantnumber;
	private String terminalnumber;
	private String tradingcardnumber;
	private String transaction;
	private String tradetypes;
	private String tradingdate;
	private String tradingtime;
	private String counterfee;
	private String loanmark;
	private String counterfee1;
	private String rebate;
	private String transfercommission;
	private String profit;

	// Constructors

	/** default constructor */
	public Banktransactionrecord() {
	}

	/** minimal constructor */
	public Banktransactionrecord(String id) {
		this.id = id;
	}

	/** full constructor */
	public Banktransactionrecord(String id, String particularyear,
			String month, String branch, String merchantnumber,
			String terminalnumber, String tradingcardnumber,
			String transaction, String tradetypes, String tradingdate,
			String tradingtime, String counterfee, String loanmark,
			String counterfee1, String rebate, String transfercommission,
			String profit) {
		this.id = id;
		this.particularyear = particularyear;
		this.month = month;
		this.branch = branch;
		this.merchantnumber = merchantnumber;
		this.terminalnumber = terminalnumber;
		this.tradingcardnumber = tradingcardnumber;
		this.transaction = transaction;
		this.tradetypes = tradetypes;
		this.tradingdate = tradingdate;
		this.tradingtime = tradingtime;
		this.counterfee = counterfee;
		this.loanmark = loanmark;
		this.counterfee1 = counterfee1;
		this.rebate = rebate;
		this.transfercommission = transfercommission;
		this.profit = profit;
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

	@Column(name = "PARTICULARYEAR", length = 60)
	public String getParticularyear() {
		return this.particularyear;
	}

	public void setParticularyear(String particularyear) {
		this.particularyear = particularyear;
	}

	@Column(name = "MONTH", length = 60)
	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "BRANCH", length = 60)
	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Column(name = "MERCHANTNUMBER", length = 60)
	public String getMerchantnumber() {
		return this.merchantnumber;
	}

	public void setMerchantnumber(String merchantnumber) {
		this.merchantnumber = merchantnumber;
	}

	@Column(name = "TERMINALNUMBER", length = 60)
	public String getTerminalnumber() {
		return this.terminalnumber;
	}

	public void setTerminalnumber(String terminalnumber) {
		this.terminalnumber = terminalnumber;
	}

	@Column(name = "TRADINGCARDNUMBER", length = 60)
	public String getTradingcardnumber() {
		return this.tradingcardnumber;
	}

	public void setTradingcardnumber(String tradingcardnumber) {
		this.tradingcardnumber = tradingcardnumber;
	}

	@Column(name = "TRANSACTION", length = 60)
	public String getTransaction() {
		return this.transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	@Column(name = "TRADETYPES", length = 60)
	public String getTradetypes() {
		return this.tradetypes;
	}

	public void setTradetypes(String tradetypes) {
		this.tradetypes = tradetypes;
	}

	@Column(name = "TRADINGDATE", length = 60)
	public String getTradingdate() {
		return this.tradingdate;
	}

	public void setTradingdate(String tradingdate) {
		this.tradingdate = tradingdate;
	}

	@Column(name = "TRADINGTIME", length = 60)
	public String getTradingtime() {
		return this.tradingtime;
	}

	public void setTradingtime(String tradingtime) {
		this.tradingtime = tradingtime;
	}

	@Column(name = "COUNTERFEE", length = 60)
	public String getCounterfee() {
		return this.counterfee;
	}

	public void setCounterfee(String counterfee) {
		this.counterfee = counterfee;
	}

	@Column(name = "LOANMARK", length = 60)
	public String getLoanmark() {
		return this.loanmark;
	}

	public void setLoanmark(String loanmark) {
		this.loanmark = loanmark;
	}

	@Column(name = "COUNTERFEE1", length = 60)
	public String getCounterfee1() {
		return this.counterfee1;
	}

	public void setCounterfee1(String counterfee1) {
		this.counterfee1 = counterfee1;
	}

	@Column(name = "REBATE", length = 60)
	public String getRebate() {
		return this.rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	@Column(name = "TRANSFERCOMMISSION", length = 60)
	public String getTransfercommission() {
		return this.transfercommission;
	}

	public void setTransfercommission(String transfercommission) {
		this.transfercommission = transfercommission;
	}

	@Column(name = "PROFIT", length = 60)
	public String getProfit() {
		return this.profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

}