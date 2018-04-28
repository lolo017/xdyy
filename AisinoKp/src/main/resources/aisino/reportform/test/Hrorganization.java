package aisino.reportform.test;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Hrorganization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HRORGANIZATION", schema = "")
public class Hrorganization implements java.io.Serializable {

	// Fields

	private HrorganizationId id;

	// Constructors

	/** default constructor */
	public Hrorganization() {
	}

	/** full constructor */
	public Hrorganization(HrorganizationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codesetid", column = @Column(name = "codesetid", nullable = false, length = 2)),
			@AttributeOverride(name = "codeitemid", column = @Column(name = "codeitemid", nullable = false, length = 30)),
			@AttributeOverride(name = "codeitemdesc", column = @Column(name = "codeitemdesc", length = 50)),
			@AttributeOverride(name = "parentid", column = @Column(name = "parentid", length = 30)),
			@AttributeOverride(name = "childid", column = @Column(name = "childid", length = 30)),
			@AttributeOverride(name = "state", column = @Column(name = "state", length = 10)),
			@AttributeOverride(name = "grade", column = @Column(name = "grade", precision = 10, scale = 0)),
			@AttributeOverride(name = "a0000", column = @Column(name = "A0000", precision = 10, scale = 0)),
			@AttributeOverride(name = "groupId", column = @Column(name = "GroupId", precision = 10, scale = 0)),
			@AttributeOverride(name = "posCond", column = @Column(name = "pos_cond", length = 0)),
			@AttributeOverride(name = "invalid", column = @Column(name = "invalid", precision = 10, scale = 0)),
			@AttributeOverride(name = "flag", column = @Column(name = "flag", precision = 10, scale = 0)),
			@AttributeOverride(name = "layer", column = @Column(name = "layer", precision = 10, scale = 0)),
			@AttributeOverride(name = "corCode", column = @Column(name = "corCode", length = 30)),
			@AttributeOverride(name = "endDate", column = @Column(name = "end_date", length = 7)),
			@AttributeOverride(name = "startDate", column = @Column(name = "start_date", length = 7)) })
	public HrorganizationId getId() {
		return this.id;
	}

	public void setId(HrorganizationId id) {
		this.id = id;
	}

}