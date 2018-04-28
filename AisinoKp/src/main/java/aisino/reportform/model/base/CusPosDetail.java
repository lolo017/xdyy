package aisino.reportform.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

/**
 * CusPosDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CUS_POS_DETAIL", schema = "")
public class CusPosDetail implements java.io.Serializable {

	// Fields

	private String id;
	private CusPos cusPos;
	private Short postype;
	private Integer poscode;
	private String amount;
	private String posaddr;
	private Date posstartDate;
	private Boolean isvalid;
	private String over;
	private Date posenddate;
	private Long detailid;
	private Boolean posstyle;
	private Long addrid;
	private Double koupro;
	private String poscard;
	private String tradephone;
	private String posnotes;
	private String settlementtype;
	private String keycard;
	private Integer overperson;
	private Double kouoff;

	// Constructors

	/** default constructor */
	public CusPosDetail() {
	}

	/** minimal constructor */
	public CusPosDetail(String id, CusPos cusPos) {
		this.id = id;
		this.cusPos = cusPos;
	}

	/** full constructor */
	public CusPosDetail(String id, CusPos cusPos, Short postype,
			Integer poscode, String amount, String posaddr, Date posstartDate,
			Boolean isvalid, String over, Date posenddate, Long detailid,
			Boolean posstyle, Long addrid, Double koupro, String poscard,
			String tradephone, String posnotes, String settlementtype,
			String keycard, Integer overperson, Double kouoff) {
		this.id = id;
		this.cusPos = cusPos;
		this.postype = postype;
		this.poscode = poscode;
		this.amount = amount;
		this.posaddr = posaddr;
		this.posstartDate = posstartDate;
		this.isvalid = isvalid;
		this.over = over;
		this.posenddate = posenddate;
		this.detailid = detailid;
		this.posstyle = posstyle;
		this.addrid = addrid;
		this.koupro = koupro;
		this.poscard = poscard;
		this.tradephone = tradephone;
		this.posnotes = posnotes;
		this.settlementtype = settlementtype;
		this.keycard = keycard;
		this.overperson = overperson;
		this.kouoff = kouoff;
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

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public CusPos getCusPos() {
		return this.cusPos;
	}

	public void setCusPos(CusPos cusPos) {
		this.cusPos = cusPos;
	}

	@Column(name = "POSTYPE", precision = 3, scale = 0)
	public Short getPostype() {
		return this.postype;
	}

	public void setPostype(Short postype) {
		this.postype = postype;
	}

	@Column(name = "POSCODE", precision = 8, scale = 0)
	public Integer getPoscode() {
		return this.poscode;
	}

	public void setPoscode(Integer poscode) {
		this.poscode = poscode;
	}

	@Column(name = "AMOUNT", length = 30)
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "POSADDR", length = 200)
	public String getPosaddr() {
		return this.posaddr;
	}

	public void setPosaddr(String posaddr) {
		this.posaddr = posaddr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "POSSTART_DATE", length = 7)
	public Date getPosstartDate() {
		return this.posstartDate;
	}

	public void setPosstartDate(Date posstartDate) {
		this.posstartDate = posstartDate;
	}

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	@Column(name = "OVER", length = 100)
	public String getOver() {
		return this.over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "POSENDDATE", length = 7)
	public Date getPosenddate() {
		return this.posenddate;
	}

	public void setPosenddate(Date posenddate) {
		this.posenddate = posenddate;
	}

	@Column(name = "DETAILID", precision = 14, scale = 0)
	public Long getDetailid() {
		return this.detailid;
	}

	public void setDetailid(Long detailid) {
		this.detailid = detailid;
	}

	@Column(name = "POSSTYLE", precision = 1, scale = 0)
	public Boolean getPosstyle() {
		return this.posstyle;
	}

	public void setPosstyle(Boolean posstyle) {
		this.posstyle = posstyle;
	}

	@Column(name = "ADDRID", precision = 14, scale = 0)
	public Long getAddrid() {
		return this.addrid;
	}

	public void setAddrid(Long addrid) {
		this.addrid = addrid;
	}

	@Column(name = "KOUPRO", precision = 3)
	public Double getKoupro() {
		return this.koupro;
	}

	public void setKoupro(Double koupro) {
		this.koupro = koupro;
	}

	@Column(name = "POSCARD", length = 50)
	public String getPoscard() {
		return this.poscard;
	}

	public void setPoscard(String poscard) {
		this.poscard = poscard;
	}

	@Column(name = "TRADEPHONE", length = 50)
	public String getTradephone() {
		return this.tradephone;
	}

	public void setTradephone(String tradephone) {
		this.tradephone = tradephone;
	}

	@Column(name = "POSNOTES", length = 100)
	public String getPosnotes() {
		return this.posnotes;
	}

	public void setPosnotes(String posnotes) {
		this.posnotes = posnotes;
	}

	@Column(name = "SETTLEMENTTYPE", length = 10)
	public String getSettlementtype() {
		return this.settlementtype;
	}

	public void setSettlementtype(String settlementtype) {
		this.settlementtype = settlementtype;
	}

	@Column(name = "KEYCARD", length = 50)
	public String getKeycard() {
		return this.keycard;
	}

	public void setKeycard(String keycard) {
		this.keycard = keycard;
	}

	@Column(name = "OVERPERSON", precision = 9, scale = 0)
	public Integer getOverperson() {
		return this.overperson;
	}

	public void setOverperson(Integer overperson) {
		this.overperson = overperson;
	}

	@Column(name = "KOUOFF", precision = 3)
	public Double getKouoff() {
		return this.kouoff;
	}

	public void setKouoff(Double kouoff) {
		this.kouoff = kouoff;
	}

}