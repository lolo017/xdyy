package aisino.reportform.test;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HrorganizationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class HrorganizationId implements java.io.Serializable {

	// Fields

	private String codesetid;
	private String codeitemid;
	private String codeitemdesc;
	private String parentid;
	private String childid;
	private String state;
	private Long grade;
	private Long a0000;
	private Long groupId;
	private String posCond;
	private Long invalid;
	private Long flag;
	private Long layer;
	private String corCode;
	private Date endDate;
	private Date startDate;

	// Constructors

	/** default constructor */
	public HrorganizationId() {
	}

	/** minimal constructor */
	public HrorganizationId(String codesetid, String codeitemid) {
		this.codesetid = codesetid;
		this.codeitemid = codeitemid;
	}

	/** full constructor */
	public HrorganizationId(String codesetid, String codeitemid,
			String codeitemdesc, String parentid, String childid, String state,
			Long grade, Long a0000, Long groupId, String posCond, Long invalid,
			Long flag, Long layer, String corCode, Date endDate, Date startDate) {
		this.codesetid = codesetid;
		this.codeitemid = codeitemid;
		this.codeitemdesc = codeitemdesc;
		this.parentid = parentid;
		this.childid = childid;
		this.state = state;
		this.grade = grade;
		this.a0000 = a0000;
		this.groupId = groupId;
		this.posCond = posCond;
		this.invalid = invalid;
		this.flag = flag;
		this.layer = layer;
		this.corCode = corCode;
		this.endDate = endDate;
		this.startDate = startDate;
	}

	// Property accessors

	@Column(name = "codesetid", nullable = false, length = 2)
	public String getCodesetid() {
		return this.codesetid;
	}

	public void setCodesetid(String codesetid) {
		this.codesetid = codesetid;
	}

	@Column(name = "codeitemid", nullable = false, length = 30)
	public String getCodeitemid() {
		return this.codeitemid;
	}

	public void setCodeitemid(String codeitemid) {
		this.codeitemid = codeitemid;
	}

	@Column(name = "codeitemdesc", length = 50)
	public String getCodeitemdesc() {
		return this.codeitemdesc;
	}

	public void setCodeitemdesc(String codeitemdesc) {
		this.codeitemdesc = codeitemdesc;
	}

	@Column(name = "parentid", length = 30)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(name = "childid", length = 30)
	public String getChildid() {
		return this.childid;
	}

	public void setChildid(String childid) {
		this.childid = childid;
	}

	@Column(name = "state", length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "grade", precision = 10, scale = 0)
	public Long getGrade() {
		return this.grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	@Column(name = "A0000", precision = 10, scale = 0)
	public Long getA0000() {
		return this.a0000;
	}

	public void setA0000(Long a0000) {
		this.a0000 = a0000;
	}

	@Column(name = "GroupId", precision = 10, scale = 0)
	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Column(name = "pos_cond", length = 0)
	public String getPosCond() {
		return this.posCond;
	}

	public void setPosCond(String posCond) {
		this.posCond = posCond;
	}

	@Column(name = "invalid", precision = 10, scale = 0)
	public Long getInvalid() {
		return this.invalid;
	}

	public void setInvalid(Long invalid) {
		this.invalid = invalid;
	}

	@Column(name = "flag", precision = 10, scale = 0)
	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	@Column(name = "layer", precision = 10, scale = 0)
	public Long getLayer() {
		return this.layer;
	}

	public void setLayer(Long layer) {
		this.layer = layer;
	}

	@Column(name = "corCode", length = 30)
	public String getCorCode() {
		return this.corCode;
	}

	public void setCorCode(String corCode) {
		this.corCode = corCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HrorganizationId))
			return false;
		HrorganizationId castOther = (HrorganizationId) other;

		return ((this.getCodesetid() == castOther.getCodesetid()) || (this
				.getCodesetid() != null && castOther.getCodesetid() != null && this
				.getCodesetid().equals(castOther.getCodesetid())))
				&& ((this.getCodeitemid() == castOther.getCodeitemid()) || (this
						.getCodeitemid() != null
						&& castOther.getCodeitemid() != null && this
						.getCodeitemid().equals(castOther.getCodeitemid())))
				&& ((this.getCodeitemdesc() == castOther.getCodeitemdesc()) || (this
						.getCodeitemdesc() != null
						&& castOther.getCodeitemdesc() != null && this
						.getCodeitemdesc().equals(castOther.getCodeitemdesc())))
				&& ((this.getParentid() == castOther.getParentid()) || (this
						.getParentid() != null
						&& castOther.getParentid() != null && this
						.getParentid().equals(castOther.getParentid())))
				&& ((this.getChildid() == castOther.getChildid()) || (this
						.getChildid() != null && castOther.getChildid() != null && this
						.getChildid().equals(castOther.getChildid())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null && castOther.getState() != null && this
						.getState().equals(castOther.getState())))
				&& ((this.getGrade() == castOther.getGrade()) || (this
						.getGrade() != null && castOther.getGrade() != null && this
						.getGrade().equals(castOther.getGrade())))
				&& ((this.getA0000() == castOther.getA0000()) || (this
						.getA0000() != null && castOther.getA0000() != null && this
						.getA0000().equals(castOther.getA0000())))
				&& ((this.getGroupId() == castOther.getGroupId()) || (this
						.getGroupId() != null && castOther.getGroupId() != null && this
						.getGroupId().equals(castOther.getGroupId())))
				&& ((this.getPosCond() == castOther.getPosCond()) || (this
						.getPosCond() != null && castOther.getPosCond() != null && this
						.getPosCond().equals(castOther.getPosCond())))
				&& ((this.getInvalid() == castOther.getInvalid()) || (this
						.getInvalid() != null && castOther.getInvalid() != null && this
						.getInvalid().equals(castOther.getInvalid())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())))
				&& ((this.getLayer() == castOther.getLayer()) || (this
						.getLayer() != null && castOther.getLayer() != null && this
						.getLayer().equals(castOther.getLayer())))
				&& ((this.getCorCode() == castOther.getCorCode()) || (this
						.getCorCode() != null && castOther.getCorCode() != null && this
						.getCorCode().equals(castOther.getCorCode())))
				&& ((this.getEndDate() == castOther.getEndDate()) || (this
						.getEndDate() != null && castOther.getEndDate() != null && this
						.getEndDate().equals(castOther.getEndDate())))
				&& ((this.getStartDate() == castOther.getStartDate()) || (this
						.getStartDate() != null
						&& castOther.getStartDate() != null && this
						.getStartDate().equals(castOther.getStartDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCodesetid() == null ? 0 : this.getCodesetid().hashCode());
		result = 37
				* result
				+ (getCodeitemid() == null ? 0 : this.getCodeitemid()
						.hashCode());
		result = 37
				* result
				+ (getCodeitemdesc() == null ? 0 : this.getCodeitemdesc()
						.hashCode());
		result = 37 * result
				+ (getParentid() == null ? 0 : this.getParentid().hashCode());
		result = 37 * result
				+ (getChildid() == null ? 0 : this.getChildid().hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result
				+ (getGrade() == null ? 0 : this.getGrade().hashCode());
		result = 37 * result
				+ (getA0000() == null ? 0 : this.getA0000().hashCode());
		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		result = 37 * result
				+ (getPosCond() == null ? 0 : this.getPosCond().hashCode());
		result = 37 * result
				+ (getInvalid() == null ? 0 : this.getInvalid().hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		result = 37 * result
				+ (getLayer() == null ? 0 : this.getLayer().hashCode());
		result = 37 * result
				+ (getCorCode() == null ? 0 : this.getCorCode().hashCode());
		result = 37 * result
				+ (getEndDate() == null ? 0 : this.getEndDate().hashCode());
		result = 37 * result
				+ (getStartDate() == null ? 0 : this.getStartDate().hashCode());
		return result;
	}

}