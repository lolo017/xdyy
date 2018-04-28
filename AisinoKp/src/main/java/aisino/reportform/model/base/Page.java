package aisino.reportform.model.base;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
/**
 * page model
 * @author 廖宸宇
 * @date 2015-2-3
 */
@Entity
@Table(name = "PAGE", schema = "")
public class Page implements java.io.Serializable{
	private String id;
	private String sqlCode;//sql语句
	private String pageInformation;//报表信息
	private String pageName;//报表名称
	private String db;//数据源
	private Integer highchartsBtn;//highcharts按钮
	private String unit;//单位
	private String sortOrder;//升序或降序
	private Set<PageCode> pageCodes;//对应的所有pagecode信息
	private Set<Sqlcondition> sqlConditions;//对应所有的条件信息
	//private List<PageCode> pageCodes=new ArrayList<PageCode>();
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
	@Column(name = "SQLCODE", length = 4000)
	public String getSqlCode() {
		return sqlCode;
	}
	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}
	@Column(name = "PAGEINFORMATION", length = 4000)
	public String getPageInformation() {
		return pageInformation;
	}
	public void setPageInformation(String pageInformation) {
		this.pageInformation = pageInformation;
	}
	@Column(name = "PAGENAME", length = 36)
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	@Column(name = "DB", length = 3)
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	
	
	@Column(name = "SORT_ORDER", length = 10)
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Column(name = "HIGHCHARTS_BTN", length = 1)
	public Integer getHighchartsBtn() {
		return highchartsBtn;
	}
	public void setHighchartsBtn(Integer highchartsBtn) {
		this.highchartsBtn = highchartsBtn;
	}
	@Column(name = "UNIT", length = 10)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "page")
	public Set<PageCode> getPageCodes() {
		return this.pageCodes;
	}
	public void setPageCodes(Set<PageCode> pageCodes) {
		this.pageCodes = pageCodes;
	}
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "page")
//	public List<PageCode> getPageCodes() {
//		return pageCodes;
//	}
//	public void setPageCodes(List<PageCode> pageCodes) {
//		this.pageCodes = pageCodes;
//	}
//	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "page")
	public Set<Sqlcondition> getSqlConditions() {
		return this.sqlConditions;
	}

	public void setSqlConditions(Set<Sqlcondition> sqlConditions) {
		this.sqlConditions = sqlConditions;
	}
	
	

}
