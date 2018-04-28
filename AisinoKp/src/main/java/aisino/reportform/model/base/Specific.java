package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import org.apache.commons.lang3.StringUtils;

/**
 * Specific entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SPECIFIC", schema = "")
public class Specific implements java.io.Serializable {

	// Fields

	private String id;
	private Budget budget;
	private Syorganization syorganization;
	private String year;
	private String numbers;
	private String price;
	private String total;
	private String numbers2;
	private String price2;
	private String total2;
	private Integer budgetid;
	private String sid;
	private String sname;
	private String confirm;

	// Constructors

	/** default constructor */
	public Specific() {
	}

	/** minimal constructor */
	public Specific(String id) {
		this.id = id;
	}

	
	public Specific(String id, Budget budget,
			String year, String numbers, String price, String total,String numbers2, String price2, String total2,
			Integer budgetid,String sid,String sname,String confirm) {
		this.id = id;
		this.budget = budget;
		this.year = year;
		this.numbers = numbers;
		this.price = price;
		this.total = total;
		this.numbers2 = numbers2;
		this.price2 = price2;
		this.total2 = total2;
		this.budgetid = budgetid;
		this.sid=sid;
		this.sname=sname;
		this.confirm=confirm;
	}
	/** full constructor */
	public Specific(String id, Budget budget, Syorganization syorganization,
			String year, String numbers, String price, String total,
			Integer budgetid,String sid,String sname) {
		this.id = id;
		this.budget = budget;
		this.syorganization = syorganization;
		this.year = year;
		this.numbers = numbers;
		this.price = price;
		this.total = total;
		this.budgetid = budgetid;
		this.sid=sid;
		this.sname=sname;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUDGETINDEX")
	public Budget getBudget() {
		return this.budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGID")
	public Syorganization getSyorganization() {
		return this.syorganization;
	}

	public void setSyorganization(Syorganization syorganization) {
		this.syorganization = syorganization;
	}

	@Column(name = "YEAR", length = 36)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "NUMBERS", length = 36)
	public String getNumbers() {
		return this.numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	@Column(name = "PRICE", length = 36)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "TOTAL", length = 36)
	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	
	@Column(name = "NUMBERS2", length = 36)
	public String getNumbers2() {
		return numbers2;
	}

	public void setNumbers2(String numbers2) {
		this.numbers2 = numbers2;
	}
	@Column(name = "PRICE2", length = 36)
	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	@Column(name = "TOTAL2", length = 36)
	public String getTotal2() {
		return total2;
	}

	public void setTotal2(String total2) {
		this.total2 = total2;
	}

	@Column(name = "BUDGETID", length = 36)
	public Integer getBudgetid() {
		return this.budgetid;
	}

	public void setBudgetid(Integer budgetid) {
		this.budgetid = budgetid;
	}
	@Column(name = "SID", length = 36)
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	@Column(name = "SNAME", length = 36)
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getConfirm() {
		return confirm;
	}
	@Column(name = "CONFIRM", length = 10)
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

}