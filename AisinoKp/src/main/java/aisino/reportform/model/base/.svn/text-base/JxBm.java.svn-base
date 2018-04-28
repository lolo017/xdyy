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
 * JxBm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JX_BM", schema = "")
public class JxBm implements java.io.Serializable {

	// Fields

	private String id;
	private Syorganization syorganization;
	private SyEmpData syempdata;
	private BigDecimal year;
	private BigDecimal month;
	private BigDecimal zpfz;
	private BigDecimal ldpf;
	
	private String kpf;
	private BigDecimal zbqz;
	private String zbmc;
	private BigDecimal zbfs;
	private String zjdfsj;
	private String lddfsj;
	private String zbcjsj;
	private String jxlb;//绩效类别

	// Constructors

	/** default constructor */
	public JxBm() {
	}

	/** minimal constructor */
	public JxBm(String id) {
		this.id = id;
	}

	/** full constructor */
	public JxBm(String id, Syorganization syorganization, SyEmpData syempdata,
			BigDecimal year, BigDecimal month, BigDecimal zpfz,
			BigDecimal ldpf,  String kpf, BigDecimal zbqz,
			String zbmc, BigDecimal zbfs, String zjdfsj, String lddfsj,
			String zbcjsj) {
		this.id = id;
		this.syorganization = syorganization;
		this.syempdata = syempdata;
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
	public JxBm(String id,
			BigDecimal year, BigDecimal month, BigDecimal zpfz,
			BigDecimal ldpf,  String kpf, BigDecimal zbqz,
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
	@JoinColumn(name = "OGRID")
	public Syorganization getSyorganization() {
		return this.syorganization;
	}

	public void setSyorganization(Syorganization syorganization) {
		this.syorganization = syorganization;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPDATAID")
	public SyEmpData getSyempdata() {
		return this.syempdata;
	}

	public void setSyempdata(SyEmpData syempdata) {
		this.syempdata = syempdata;
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

	@Column(name = "ZJDFSJ", length = 70)
	public String getZjdfsj() {
		return this.zjdfsj;
	}

	public void setZjdfsj(String zjdfsj) {
		this.zjdfsj = zjdfsj;
	}

	@Column(name = "LDDFSJ", length = 70)
	public String getLddfsj() {
		return this.lddfsj;
	}

	public void setLddfsj(String lddfsj) {
		this.lddfsj = lddfsj;
	}

	@Column(name = "ZBCJSJ", length = 70)
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