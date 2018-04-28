package aisino.reportform.model.base;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Performance model
 * @author 廖宸宇
 * @date 2015-3-20
 */
@Entity
@Table(name = "PERFORMANCE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Performance implements java.io.Serializable{

	// Fields

	private String id;
	private Syuser syuser;
	private String dep;
	private String position;
	private Date perDate;
	private String perPlace;
	private String summary;
	private String target;
	private String workImprove;
	private String workTarget;
	private String isConfer;
	private String improve;
	private String result;
	private String isLeaderConfer;
	private String isLeaderConfer2;
	private String isPersonalConfer;
	private String leader;
	private Integer type;
	private String isConfergrzb;
	private String isLeaderConferGrzb;

	// Constructors

	/** default constructor */
	public Performance() {
	}

	/** minimal constructor */
	public Performance(String id) {
		this.id = id;
	}

	/** full constructor */
	public Performance(String id, Syuser syuser, String dep, 
			String position, Date perDate, String perPlace, String summary,
			String target, String workImprove, String workTarget,
			String isConfer, String improve, String result,
			String isLeaderConfer, String isPersonalConfer) {
		this.id = id;
		this.syuser = syuser;
		this.dep = dep;
		this.position = position;
		this.perDate = perDate;
		this.perPlace = perPlace;
		this.summary = summary;
		this.target = target;
		this.workImprove = workImprove;
		this.workTarget = workTarget;
		this.isConfer = isConfer;
		this.improve = improve;
		this.result = result;
		this.isLeaderConfer = isLeaderConfer;
		this.isPersonalConfer = isPersonalConfer;
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
	@JoinColumn(name = "USERSID")
	public Syuser getSyuser() {
		return this.syuser;
	}

	public void setSyuser(Syuser syuser) {
		this.syuser = syuser;
	}

	@Column(name = "DEP", length = 50)
	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}


	@Column(name = "POSITION", length = 50)
	public String getPosition() {
		return this.position;
	}


	public void setPosition(String position) {
		this.position = position;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PER_DATE", length = 10)
	public Date getPerDate() {
		if (this.perDate != null){
			return this.perDate;
		}
		return new Date();
	}

	public void setPerDate(Date perDate) {
		this.perDate = perDate;
	}

	@Column(name = "PER_PLACE", length = 50)
	public String getPerPlace() {
		return this.perPlace;
	}

	public void setPerPlace(String perPlace) {
		this.perPlace = perPlace;
	}

	@Column(name = "SUMMARY", length = 500)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "TARGET", length = 500)
	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(name = "WORK_IMPROVE", length = 500)
	public String getWorkImprove() {
		return this.workImprove;
	}

	public void setWorkImprove(String workImprove) {
		this.workImprove = workImprove;
	}

	@Column(name = "WORK_TARGET", length = 500)
	public String getWorkTarget() {
		return this.workTarget;
	}

	public void setWorkTarget(String workTarget) {
		this.workTarget = workTarget;
	}

	@Column(name = "IS_CONFER", length = 500)
	public String getIsConfer() {
		return this.isConfer;
	}

	public void setIsConfer(String isConfer) {
		this.isConfer = isConfer;
	}

	@Column(name = "IMPROVE", length = 500)
	public String getImprove() {
		return this.improve;
	}

	public void setImprove(String improve) {
		this.improve = improve;
	}

	@Column(name = "RESULT", length = 500)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	
	@Column(name = "IS_LEADER_CONFER2", length = 50)
	public String getIsLeaderConfer2() {
		return isLeaderConfer2;
	}

	public void setIsLeaderConfer2(String isLeaderConfer2) {
		this.isLeaderConfer2 = isLeaderConfer2;
	}

	@Column(name = "IS_LEADER_CONFER", length = 50)
	public String getIsLeaderConfer() {
		return this.isLeaderConfer;
	}

	public void setIsLeaderConfer(String isLeaderConfer) {
		this.isLeaderConfer = isLeaderConfer;
	}

	@Column(name = "IS_PERSONAL_CONFER", length = 1)
	public String getIsPersonalConfer() {
		return this.isPersonalConfer;
	}

	public void setIsPersonalConfer(String isPersonalConfer) {
		this.isPersonalConfer = isPersonalConfer;
	}
	@Column(name = "LEADER", length = 50)
	public String getLeader() {
		return leader;
	}
	
	public void setLeader(String leader) {
		this.leader = leader;
	}
	@Column(name = "TYPE", length = 1)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "IS_CONFER_GRZB", length = 1)
	public String getIsConfergrzb() {
		return isConfergrzb;
	}

	public void setIsConfergrzb(String isConfergrzb) {
		this.isConfergrzb = isConfergrzb;
	}
	@Column(name = "IS_LEADER_CONFER_GRZB", length = 1)
	public String getIsLeaderConferGrzb() {
		return isLeaderConferGrzb;
	}

	public void setIsLeaderConferGrzb(String isLeaderConferGrzb) {
		this.isLeaderConferGrzb = isLeaderConferGrzb;
	}
	
	

}
