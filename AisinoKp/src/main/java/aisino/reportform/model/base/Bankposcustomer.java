package aisino.reportform.model.base;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.StringUtils;

/**
 * Bankposcustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANKPOSCUSTOMER", schema = "")
public class Bankposcustomer implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String terminalnumber;
	private String terminalstate;
	private String terminalopeningdate;
	private String phonesign;
	private String tradingphonenumber;
	private String installedaddress;
	private String implementnumber;
	private String tradingphone1;
	private String tradingphone2;
	private String tradingphone3;
	private String querysign;
	private String premark;
	private String revocationmark;
	private String completeflag;
	private String revocationoflogo;
	private String revocationmarkon;
	private String returnsigns;
	private String integralsign;
	private String integralundomark;
	private String integralreturnmark;
	private String integralquerysign;
	private String installmentsign;
	private String revocationmarkof;
	private String ysid;

	// Constructors

	/** default constructor */
	public Bankposcustomer() {
	}

	/** minimal constructor */
	public Bankposcustomer(String id) {
		this.id = id;
	}

	/** full constructor */
	public Bankposcustomer(String id, String name, String terminalnumber,
			String terminalstate, String terminalopeningdate, String phonesign,
			String tradingphonenumber, String installedaddress,
			String implementnumber, String tradingphone1, String tradingphone2,
			String tradingphone3, String querysign, String premark,
			String revocationmark, String completeflag,
			String revocationoflogo, String revocationmarkon,
			String returnsigns, String integralsign, String integralundomark,
			String integralreturnmark, String integralquerysign,
			String installmentsign, String revocationmarkof, String ysid) {
		this.id = id;
		this.name = name;
		this.terminalnumber = terminalnumber;
		this.terminalstate = terminalstate;
		this.terminalopeningdate = terminalopeningdate;
		this.phonesign = phonesign;
		this.tradingphonenumber = tradingphonenumber;
		this.installedaddress = installedaddress;
		this.implementnumber = implementnumber;
		this.tradingphone1 = tradingphone1;
		this.tradingphone2 = tradingphone2;
		this.tradingphone3 = tradingphone3;
		this.querysign = querysign;
		this.premark = premark;
		this.revocationmark = revocationmark;
		this.completeflag = completeflag;
		this.revocationoflogo = revocationoflogo;
		this.revocationmarkon = revocationmarkon;
		this.returnsigns = returnsigns;
		this.integralsign = integralsign;
		this.integralundomark = integralundomark;
		this.integralreturnmark = integralreturnmark;
		this.integralquerysign = integralquerysign;
		this.installmentsign = installmentsign;
		this.revocationmarkof = revocationmarkof;
		this.ysid = ysid;
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

	@Column(name = "NAME", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TERMINALNUMBER", length = 60)
	public String getTerminalnumber() {
		return this.terminalnumber;
	}

	public void setTerminalnumber(String terminalnumber) {
		this.terminalnumber = terminalnumber;
	}

	@Column(name = "TERMINALSTATE", length = 60)
	public String getTerminalstate() {
		return this.terminalstate;
	}

	public void setTerminalstate(String terminalstate) {
		this.terminalstate = terminalstate;
	}

	@Column(name = "TERMINALOPENINGDATE", length = 30)
	public String getTerminalopeningdate() {
		return this.terminalopeningdate;
	}

	public void setTerminalopeningdate(String terminalopeningdate) {
		this.terminalopeningdate = terminalopeningdate;
	}

	@Column(name = "PHONESIGN", length = 60)
	public String getPhonesign() {
		return this.phonesign;
	}

	public void setPhonesign(String phonesign) {
		this.phonesign = phonesign;
	}

	@Column(name = "TRADINGPHONENUMBER", length = 60)
	public String getTradingphonenumber() {
		return this.tradingphonenumber;
	}

	public void setTradingphonenumber(String tradingphonenumber) {
		this.tradingphonenumber = tradingphonenumber;
	}

	@Column(name = "INSTALLEDADDRESS", length = 200)
	public String getInstalledaddress() {
		return this.installedaddress;
	}

	public void setInstalledaddress(String installedaddress) {
		this.installedaddress = installedaddress;
	}

	@Column(name = "IMPLEMENTNUMBER", length = 60)
	public String getImplementnumber() {
		return this.implementnumber;
	}

	public void setImplementnumber(String implementnumber) {
		this.implementnumber = implementnumber;
	}

	@Column(name = "TRADINGPHONE1", length = 60)
	public String getTradingphone1() {
		return this.tradingphone1;
	}

	public void setTradingphone1(String tradingphone1) {
		this.tradingphone1 = tradingphone1;
	}

	@Column(name = "TRADINGPHONE2", length = 60)
	public String getTradingphone2() {
		return this.tradingphone2;
	}

	public void setTradingphone2(String tradingphone2) {
		this.tradingphone2 = tradingphone2;
	}

	@Column(name = "TRADINGPHONE3", length = 60)
	public String getTradingphone3() {
		return this.tradingphone3;
	}

	public void setTradingphone3(String tradingphone3) {
		this.tradingphone3 = tradingphone3;
	}

	@Column(name = "QUERYSIGN", length = 60)
	public String getQuerysign() {
		return this.querysign;
	}

	public void setQuerysign(String querysign) {
		this.querysign = querysign;
	}

	@Column(name = "PREMARK", length = 60)
	public String getPremark() {
		return this.premark;
	}

	public void setPremark(String premark) {
		this.premark = premark;
	}

	@Column(name = "REVOCATIONMARK", length = 60)
	public String getRevocationmark() {
		return this.revocationmark;
	}

	public void setRevocationmark(String revocationmark) {
		this.revocationmark = revocationmark;
	}

	@Column(name = "COMPLETEFLAG", length = 60)
	public String getCompleteflag() {
		return this.completeflag;
	}

	public void setCompleteflag(String completeflag) {
		this.completeflag = completeflag;
	}

	@Column(name = "REVOCATIONOFLOGO", length = 60)
	public String getRevocationoflogo() {
		return this.revocationoflogo;
	}

	public void setRevocationoflogo(String revocationoflogo) {
		this.revocationoflogo = revocationoflogo;
	}

	@Column(name = "REVOCATIONMARKON", length = 60)
	public String getRevocationmarkon() {
		return this.revocationmarkon;
	}

	public void setRevocationmarkon(String revocationmarkon) {
		this.revocationmarkon = revocationmarkon;
	}

	@Column(name = "RETURNSIGNS", length = 60)
	public String getReturnsigns() {
		return this.returnsigns;
	}

	public void setReturnsigns(String returnsigns) {
		this.returnsigns = returnsigns;
	}

	@Column(name = "INTEGRALSIGN", length = 60)
	public String getIntegralsign() {
		return this.integralsign;
	}

	public void setIntegralsign(String integralsign) {
		this.integralsign = integralsign;
	}

	@Column(name = "INTEGRALUNDOMARK", length = 60)
	public String getIntegralundomark() {
		return this.integralundomark;
	}

	public void setIntegralundomark(String integralundomark) {
		this.integralundomark = integralundomark;
	}

	@Column(name = "INTEGRALRETURNMARK", length = 60)
	public String getIntegralreturnmark() {
		return this.integralreturnmark;
	}

	public void setIntegralreturnmark(String integralreturnmark) {
		this.integralreturnmark = integralreturnmark;
	}

	@Column(name = "INTEGRALQUERYSIGN", length = 60)
	public String getIntegralquerysign() {
		return this.integralquerysign;
	}

	public void setIntegralquerysign(String integralquerysign) {
		this.integralquerysign = integralquerysign;
	}

	@Column(name = "INSTALLMENTSIGN", length = 60)
	public String getInstallmentsign() {
		return this.installmentsign;
	}

	public void setInstallmentsign(String installmentsign) {
		this.installmentsign = installmentsign;
	}

	@Column(name = "REVOCATIONMARKOF", length = 60)
	public String getRevocationmarkof() {
		return this.revocationmarkof;
	}

	public void setRevocationmarkof(String revocationmarkof) {
		this.revocationmarkof = revocationmarkof;
	}

	@Column(name = "YSID", length = 60)
	public String getYsid() {
		return this.ysid;
	}

	public void setYsid(String ysid) {
		this.ysid = ysid;
	}

}