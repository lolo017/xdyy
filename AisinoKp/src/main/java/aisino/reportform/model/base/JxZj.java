package aisino.reportform.model.base;

import java.math.BigDecimal;
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
 * JxZj entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JX_ZJ", schema = "")
public class JxZj implements java.io.Serializable {

	// Fields

	private String id;
	private SyEmpData syempdataByLdempdataid;//领导员工工号
	private SyEmpData syempdataByEmpdataid;//被打分人员id
	
	private BigDecimal year;//年
	private BigDecimal month;//月
	private BigDecimal zpfz;//自评评分
	private BigDecimal ldpf;//领导评分
	private String kpf;//考评方法
	private BigDecimal zbqz;//指标权重
	private String zbmc;//指标名称
	private BigDecimal zbfs;//指标分数
	private String zjdfsj;//自己考评时间
	private String lddfsj;//领导考评时间
	private String zbcjsj;//指标创建时间
	private String jxlb;//绩效类别

	// Constructors

	/** default constructor */
	public JxZj() {
	}

	/** minimal constructor */
	public JxZj(String id) {
		this.id = id;
	}

	/** full constructor */
	public JxZj(String id, SyEmpData syempdataByLdempdataid,
			SyEmpData syempdataByEmpdataid, BigDecimal year, BigDecimal month,
			BigDecimal zpfz, BigDecimal ldpf, String kpf, BigDecimal zbqz,
			String zbmc, BigDecimal zbfs, String zjdfsj, String lddfsj,
			String zbcjsj) {
		this.id = id;
		this.syempdataByLdempdataid = syempdataByLdempdataid;
		this.syempdataByEmpdataid = syempdataByEmpdataid;
		this.year = year;
		this.month = month;
		this.zpfz = zpfz;
		this.ldpf = ldpf;
		this.kpf = kpf;
		this.zbqz = zbqz;
		this.zbmc = zbmc;
		this.zbfs = zbfs;
		this.zjdfsj = zjdfsj;
		this.lddfsj = lddfsj;
		this.zbcjsj = zbcjsj;
	}
	public JxZj(String id,  BigDecimal year, BigDecimal month,
			BigDecimal zpfz, BigDecimal ldpf, String kpf, BigDecimal zbqz,
			String zbmc, BigDecimal zbfs, String zjdfsj, String lddfsj,
			String zbcjsj) {
		this.id = id;
		this.year = year;
		this.month = month;
		this.zpfz = zpfz;
		this.ldpf = ldpf;
		this.kpf = kpf;
		this.zbqz = zbqz;
		this.zbmc = zbmc;
		this.zbfs = zbfs;
		this.zjdfsj = zjdfsj;
		this.lddfsj = lddfsj;
		this.zbcjsj = zbcjsj;
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
	@JoinColumn(name = "LDEMPDATAID")
	public SyEmpData getSyempdataByLdempdataid() {
		return this.syempdataByLdempdataid;
	}

	public void setSyempdataByLdempdataid(SyEmpData syempdataByLdempdataid) {
		this.syempdataByLdempdataid = syempdataByLdempdataid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPDATAID")
	public SyEmpData getSyempdataByEmpdataid() {
		return this.syempdataByEmpdataid;
	}

	public void setSyempdataByEmpdataid(SyEmpData syempdataByEmpdataid) {
		this.syempdataByEmpdataid = syempdataByEmpdataid;
	}

	

	@Column(name = "YEAR", precision = 22, scale = 0)
	public BigDecimal getYear() {
		return this.year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	@Column(name = "MONTH", precision = 22, scale = 0)
	public BigDecimal getMonth() {
		return this.month;
	}

	public void setMonth(BigDecimal month) {
		this.month = month;
	}

	@Column(name = "ZPFZ", precision = 22, scale = 0)
	public BigDecimal getZpfz() {
		return this.zpfz;
	}

	public void setZpfz(BigDecimal zpfz) {
		this.zpfz = zpfz;
	}

	@Column(name = "LDPF", precision = 22, scale = 0)
	public BigDecimal getLdpf() {
		return this.ldpf;
	}

	public void setLdpf(BigDecimal ldpf) {
		this.ldpf = ldpf;
	}

	@Column(name = "KPF", length = 36)
	public String getKpf() {
		return this.kpf;
	}

	public void setKpf(String kpf) {
		this.kpf = kpf;
	}

	@Column(name = "ZBQZ", precision = 22, scale = 0)
	public BigDecimal getZbqz() {
		return this.zbqz;
	}

	public void setZbqz(BigDecimal zbqz) {
		this.zbqz = zbqz;
	}

	@Column(name = "ZBMC", length = 200)
	public String getZbmc() {
		return this.zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}

	@Column(name = "ZBFS", precision = 22, scale = 0)
	public BigDecimal getZbfs() {
		return this.zbfs;
	}

	public void setZbfs(BigDecimal zbfs) {
		this.zbfs = zbfs;
	}

	@Column(name = "ZJDFSJ", length = 50)
	public String getZjdfsj() {
		return this.zjdfsj;
	}

	public void setZjdfsj(String zjdfsj) {
		this.zjdfsj = zjdfsj;
	}

	@Column(name = "LDDFSJ", length = 50)
	public String getLddfsj() {
		return this.lddfsj;
	}

	public void setLddfsj(String lddfsj) {
		this.lddfsj = lddfsj;
	}

	@Column(name = "ZBCJSJ", length = 50)
	public String getZbcjsj() {
		return this.zbcjsj;
	}

	public void setZbcjsj(String zbcjsj) {
		this.zbcjsj = zbcjsj;
	}

	public String getJxlb() {
		return jxlb;
	}

	public void setJxlb(String jxlb) {
		this.jxlb = jxlb;
	}
	
	

}