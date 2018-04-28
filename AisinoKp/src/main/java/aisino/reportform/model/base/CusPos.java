package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * CusPos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CUS_POS", schema = "")
public class CusPos implements java.io.Serializable {

	// Fields

	private String id;
	private String customeroutname;
	private Long custposcode;
	private String buscode;
	private String bustel;
	private String poslegalperson;
	private String legalcode;
	private String busname;
	private String outnumer;
	private String personnumber;
	private String mccnumber;
	private String org;
	private Long temp;
	private Integer poscount;
	private Long ser;
	private String posbranch;
	private String posoperator;
	private String notes;
	private Boolean isic;
	private Byte posproductid;
	private Boolean isjoint;
	private CusPosDetail cusPosDetail;

	// Constructors

	/** default constructor */
	public CusPos() {
	}

	/** minimal constructor */
	public CusPos(String id) {
		this.id = id;
	}

	/** full constructor */
	public CusPos(String id, String customeroutname, Long custposcode,
			String buscode, String bustel, String poslegalperson,
			String legalcode, String busname, String outnumer,
			String personnumber, String mccnumber, String org, Long temp,
			Integer poscount, Long ser, String posbranch, String posoperator,
			String notes, Boolean isic, Byte posproductid, Boolean isjoint,
			CusPosDetail cusPosDetail) {
		this.id = id;
		this.customeroutname = customeroutname;
		this.custposcode = custposcode;
		this.buscode = buscode;
		this.bustel = bustel;
		this.poslegalperson = poslegalperson;
		this.legalcode = legalcode;
		this.busname = busname;
		this.outnumer = outnumer;
		this.personnumber = personnumber;
		this.mccnumber = mccnumber;
		this.org = org;
		this.temp = temp;
		this.poscount = poscount;
		this.ser = ser;
		this.posbranch = posbranch;
		this.posoperator = posoperator;
		this.notes = notes;
		this.isic = isic;
		this.posproductid = posproductid;
		this.isjoint = isjoint;
		this.cusPosDetail = cusPosDetail;
	}

	// Property accessors
	@Id
	@Column(name = "CUSTOMERID", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CUSTOMEROUTNAME", length = 100)
	public String getCustomeroutname() {
		return this.customeroutname;
	}

	public void setCustomeroutname(String customeroutname) {
		this.customeroutname = customeroutname;
	}

	@Column(name = "CUSTPOSCODE", precision = 15, scale = 0)
	public Long getCustposcode() {
		return this.custposcode;
	}

	public void setCustposcode(Long custposcode) {
		this.custposcode = custposcode;
	}

	@Column(name = "BUSCODE", length = 50)
	public String getBuscode() {
		return this.buscode;
	}

	public void setBuscode(String buscode) {
		this.buscode = buscode;
	}

	@Column(name = "BUSTEL", length = 50)
	public String getBustel() {
		return this.bustel;
	}

	public void setBustel(String bustel) {
		this.bustel = bustel;
	}

	@Column(name = "POSLEGALPERSON", length = 50)
	public String getPoslegalperson() {
		return this.poslegalperson;
	}

	public void setPoslegalperson(String poslegalperson) {
		this.poslegalperson = poslegalperson;
	}

	@Column(name = "LEGALCODE", length = 50)
	public String getLegalcode() {
		return this.legalcode;
	}

	public void setLegalcode(String legalcode) {
		this.legalcode = legalcode;
	}

	@Column(name = "BUSNAME", length = 100)
	public String getBusname() {
		return this.busname;
	}

	public void setBusname(String busname) {
		this.busname = busname;
	}

	@Column(name = "OUTNUMER", length = 50)
	public String getOutnumer() {
		return this.outnumer;
	}

	public void setOutnumer(String outnumer) {
		this.outnumer = outnumer;
	}

	@Column(name = "PERSONNUMBER", length = 50)
	public String getPersonnumber() {
		return this.personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	@Column(name = "MCCNUMBER", length = 50)
	public String getMccnumber() {
		return this.mccnumber;
	}

	public void setMccnumber(String mccnumber) {
		this.mccnumber = mccnumber;
	}

	@Column(name = "ORG", length = 50)
	public String getOrg() {
		return this.org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@Column(name = "TEMP", precision = 14, scale = 0)
	public Long getTemp() {
		return this.temp;
	}

	public void setTemp(Long temp) {
		this.temp = temp;
	}

	@Column(name = "POSCOUNT", precision = 5, scale = 0)
	public Integer getPoscount() {
		return this.poscount;
	}

	public void setPoscount(Integer poscount) {
		this.poscount = poscount;
	}

	@Column(name = "SER", precision = 14, scale = 0)
	public Long getSer() {
		return this.ser;
	}

	public void setSer(Long ser) {
		this.ser = ser;
	}

	@Column(name = "POSBRANCH", length = 150)
	public String getPosbranch() {
		return this.posbranch;
	}

	public void setPosbranch(String posbranch) {
		this.posbranch = posbranch;
	}

	@Column(name = "POSOPERATOR", length = 50)
	public String getPosoperator() {
		return this.posoperator;
	}

	public void setPosoperator(String posoperator) {
		this.posoperator = posoperator;
	}

	@Column(name = "NOTES", length = 150)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "ISIC", precision = 1, scale = 0)
	public Boolean getIsic() {
		return this.isic;
	}

	public void setIsic(Boolean isic) {
		this.isic = isic;
	}

	@Column(name = "POSPRODUCTID", precision = 2, scale = 0)
	public Byte getPosproductid() {
		return this.posproductid;
	}

	public void setPosproductid(Byte posproductid) {
		this.posproductid = posproductid;
	}

	@Column(name = "ISJOINT", precision = 1, scale = 0)
	public Boolean getIsjoint() {
		return this.isjoint;
	}

	public void setIsjoint(Boolean isjoint) {
		this.isjoint = isjoint;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "cusPos")
	public CusPosDetail getCusPosDetail() {
		return this.cusPosDetail;
	}

	public void setCusPosDetail(CusPosDetail cusPosDetail) {
		this.cusPosDetail = cusPosDetail;
	}

}