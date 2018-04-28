package aisino.reportform.model.base;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
/**
 * pageCode model
 * @author 廖宸宇
 * @date 2015-2-3
 */
@Entity
@Table(name = "PAGECODE", schema = "")
public class PageCode implements java.io.Serializable{
	private String id;
	private String codeName;//暂没用
	private String cnName;//字段中文名
	private String cnEnglish;//字段应为名
	private int orderId;//排序ID
	private Page page;//报表页面
	private String url;//跳转路径
	private Integer width;//单元格宽度
	private Integer isHighcharts;//是否展示在highcharts
	private Integer rowNum;
	private Integer colNum;
	private Integer rowspan;
	private Integer colspan;
	private Set<PageCodeSelfWarn> pageCodeSelfWarns=new HashSet<PageCodeSelfWarn>();//自定义预警区间集合
	private Set<PageCodeAvgWarn>pageCodeAvgWarns=new HashSet<PageCodeAvgWarn>();//平均值预警集合
	private Set<PageCodeSqlWarn>pageCodeSqlWarns=new HashSet<PageCodeSqlWarn>();//SQL值预警集合
	
	
	public PageCode(){
		
	}

	public PageCode(String id, String codeName, String cnName,
			String cnEnglish, int orderId,String url,Integer width,Integer isHighcharts, Integer rowNum,Integer colNum,Integer rowspan,Integer colspan) {
		super();
		this.id = id;
		this.codeName = codeName;
		this.cnName = cnName;
		this.cnEnglish = cnEnglish;
		this.orderId = orderId;
		this.url = url;
		this.width = width;
		this.isHighcharts = isHighcharts;
		this.rowNum=rowNum;
		this.colNum=colNum;
		this.rowspan=rowspan;
		this.colspan=colspan;
	}
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
	
	@Column(name = "CODENAME", length = 36)
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	@Column(name = "CNNAME", length = 36)
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	@Column(name = "CNENGLISH", length = 36)
	public String getCnEnglish() {
		return cnEnglish;
	}
	public void setCnEnglish(String cnEnglish) {
		this.cnEnglish = cnEnglish;
	}
	@Column(name = "ORDERID", length = 36)
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PAGEID")
	public Page getPage() {
		return page;
	}
	
	
	public void setPage(Page page) {
		this.page = page;
	}


	@Column(name = "URL", length = 500)
	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}


	@Column(name = "WIDTH", length = 5)
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	@Column(name = "IS_HIGHCHARTS", length = 1)
	public Integer getIsHighcharts() {
		return isHighcharts;
	}

	public void setIsHighcharts(Integer isHighcharts) {
		this.isHighcharts = isHighcharts;
	}


	@Column(name = "ROW_NUM", length = 5)
	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	@Column(name = "COL_NUM", length = 5)
	public Integer getColNum() {
		return colNum;
	}

	public void setColNum(Integer colNum) {
		this.colNum = colNum;
	}
	@Column(name = "ROWSPAN", length = 5)
	public Integer getRowspan() {
		return rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}
	@Column(name = "COLSPAN", length = 5)
	public Integer getColspan() {
		return colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pagecode")
	public Set<PageCodeSelfWarn> getPageCodeSelfWarns() {
		return pageCodeSelfWarns;
	}

	public void setPageCodeSelfWarns(Set<PageCodeSelfWarn> pageCodeSelfWarns) {
		this.pageCodeSelfWarns = pageCodeSelfWarns;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pagecode")
	public Set<PageCodeAvgWarn> getPageCodeAvgWarns() {
		return pageCodeAvgWarns;
	}

	public void setPageCodeAvgWarns(Set<PageCodeAvgWarn> pageCodeAvgWarns) {
		this.pageCodeAvgWarns = pageCodeAvgWarns;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pagecode")
	public Set<PageCodeSqlWarn> getPageCodeSqlWarns() {
		return pageCodeSqlWarns;
	}

	public void setPageCodeSqlWarns(Set<PageCodeSqlWarn> pageCodeSqlWarns) {
		this.pageCodeSqlWarns = pageCodeSqlWarns;
	}
	
	
	
	

}
