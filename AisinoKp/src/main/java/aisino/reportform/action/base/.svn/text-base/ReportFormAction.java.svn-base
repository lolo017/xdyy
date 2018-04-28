package aisino.reportform.action.base;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Page;
import aisino.reportform.model.base.PageCode;
import aisino.reportform.model.base.PageCodeAvgWarn;
import aisino.reportform.model.base.PageCodeSelfWarn;
import aisino.reportform.model.base.PageCodeSqlWarn;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.Sqlcondition;
import aisino.reportform.model.base.Syresource;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.PageCodeServiceI;
import aisino.reportform.service.base.PageServiceI;
import aisino.reportform.service.base.SqlconditionServiceI;
import aisino.reportform.service.base.SyresourceServiceI;
import aisino.reportform.service.base.PageCodeWarnServiceI;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.DbUtil;
import aisino.reportform.util.base.PoiUtil;
import aisino.reportform.util.base.SqlUtil;
import aisino.reportform.util.base.excel.ExcelUtil;

/**
 * 报表管理Action
 * 
 * @author 廖宸宇
 * @date 2015-1-26
 */

@Namespace("/base")
@Action

public class ReportFormAction extends BaseAction<Syresource> {
	private String excelName;//导出excel名称
	private String tableName;// SQL表名
	private String sqlStr;// sql语句
	private String tSql;//动态表头sql
	private String paramStr;// 条件
	private String tNameStr;// 表头名
	private String cNameStr;// 字段名
	private String pageName;//报表命
	private String db;//数据源
	private List<Sqlcondition> conditionList;
	private List<String> urlList;//跳转路径
	private List<Integer> widthList;//宽度
	private List<Integer> hcList;//pagecode是否显示在highcharts
	private String filenames;
	private String unit;//highcharts单位
	private Integer highchartsBtn;//是否显示highcharts按钮
	private String sortOrder;//排序方式
	private String tableIndex;//行，列，rowSpan,colSpan
	private List<PageCodeAvgWarn> avgList;
	private List<PageCodeSelfWarn> selfList;
	private List<PageCodeSqlWarn> sqlList;

	
	
	public String getTableIndex() {
		return tableIndex;
	}

	public void setTableIndex(String tableIndex) {
		this.tableIndex = tableIndex;
	}

	public List<PageCodeSqlWarn> getSqlList() {
		return sqlList;
	}

	public void setSqlList(List<PageCodeSqlWarn> sqlList) {
		this.sqlList = sqlList;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public List<Integer> getHcList() {
		return hcList;
	}

	public void setHcList(List<Integer> hcList) {
		this.hcList = hcList;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getHighchartsBtn() {
		return highchartsBtn;
	}

	public void setHighchartsBtn(Integer highchartsBtn) {
		this.highchartsBtn = highchartsBtn;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<PageCodeAvgWarn> getAvgList() {
		return avgList;
	}

	public void setAvgList(List<PageCodeAvgWarn> avgList) {
		this.avgList = avgList;
	}

	public List<PageCodeSelfWarn> getSelfList() {
		return selfList;
	}

	public void setSelfList(List<PageCodeSelfWarn> selfList) {
		this.selfList = selfList;
	}

	public String gettSql() {
		return tSql;
	}

	public void settSql(String tSql) {
		this.tSql = tSql;
	}

	public List<Integer> getWidthList() {
		return widthList;
	}

	public void setWidthList(List<Integer> widthList) {
		this.widthList = widthList;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getFilenames() {
		return filenames;
	}

	public void setFilenames(String filenames) {
		this.filenames = filenames;
	}
	@Autowired
	private PageServiceI pageService;
	@Autowired
	private PageCodeServiceI pageCodeService;
	@Autowired
	private SqlconditionServiceI sqlconditionService;
	@Autowired
	private PageCodeWarnServiceI pageCodeWarnService;

	public List<Sqlcondition> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<Sqlcondition> conditionList) {
		this.conditionList = conditionList;
	}

	public String getParamStr() {
		return paramStr;
	}

	public void setParamStr(String pramStr) {
		this.paramStr = pramStr;
	}

	public String getTNameStr() {
		return tNameStr;
	}

	public void setTNameStr(String tNameStr) {
		this.tNameStr = tNameStr;
	}

	public String getCNameStr() {
		return cNameStr;
	}

	public void setCNameStr(String cNameStr) {
		this.cNameStr = cNameStr;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Autowired
	public void setService(SyresourceServiceI service) {
		this.service = service;
	}

	/**
	 * 查询表
	 */
	public void findTable() {
		List list = ((SyresourceServiceI) service).findTable();
		writeJson(list);
	}

	/**
	 * 查询字段
	 */
	public void findColumn() {
		List list = ((SyresourceServiceI) service).findColumn(tableName);
		writeJson(list);
	}

	/**
	 * 根据SQL查询gridField(结果集列名)
	 */

	public void findGridField(){
		DbUtil.setDb(db);

		Json json = new Json();

		try{
		    sqlStr=URLDecoder.decode(sqlStr, "UTF-8");
			}catch(Exception e){
				e.printStackTrace();
			}
		if(!paramStr.equals("")){
		List fieldList=((SyresourceServiceI)service).findGridField(SqlUtil.formatSqlForPreview(sqlStr, paramStr));

		json.setSuccess(true);
		json.setObj(fieldList);
	    writeJson(json);
		}else{
	    List fieldList=((SyresourceServiceI)service).findGridField(sqlStr);
	    json.setSuccess(true);
		json.setObj(fieldList);
		 writeJson(json);

	}
		writeJson(json);
	}
	/**
	 * 预览报表
	 */
	public void previewSql() {
	
		DbUtil.setDb(db);
		Grid grid = new Grid();
		try {
			sqlStr = URLDecoder.decode(sqlStr, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!paramStr.equals("")) {
			String newSql = SqlUtil.formatSqlForPreview(sqlStr, paramStr);
			grid.setRows(((SyresourceServiceI) service).previewSql(newSql,
					page, rows));
			grid.setTotal(((SyresourceServiceI) service).countSql(newSql));
		} else {
			grid.setRows(((SyresourceServiceI) service).previewSql(sqlStr,
					page, rows));
			grid.setTotal(((SyresourceServiceI) service).countSql(sqlStr));
		}

		writeJson(grid);
	}

	/**
	 * 保存报表
	 */
	public void saveSql() {
		Json json = new Json();
		
		String sqlcon;
		try {
			tNameStr=URLDecoder.decode(tNameStr, "UTF-8");
			cNameStr=URLDecoder.decode(cNameStr, "UTF-8");
			sqlStr = URLDecoder.decode(sqlStr, "UTF-8");
			if(null != conditionList && !conditionList.isEmpty() ){
			for(Sqlcondition con:conditionList){
				if(con.getConditionsql()!=null&&con.getConditionsql().length()>0){
				sqlcon=URLDecoder.decode(con.getConditionsql(), "UTF-8");
				con.setConditionsql(sqlcon);
				}
				System.out.println(conditionList);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] tArray = tNameStr.split("\\+");
		String[] cArray = cNameStr.split("\\+");
		String[] indexArray = tableIndex.split("\\+");
		if (tArray.length != cArray.length) {
			json.setMsg("中文字段和英文字段不匹配！");
			writeJson(json);
		} else {
			if (data != null) {
				
				SessionInfo sessionInfo = (SessionInfo) getSession()
						.getAttribute(ConfigUtil.getSessionInfoName());
				
				((SyresourceServiceI) service).saveSql(db,unit,sortOrder,highchartsBtn,tArray, cArray, indexArray,sqlStr,
						data, sessionInfo.getUser().getId(), conditionList,urlList,widthList,hcList,avgList,selfList,sqlList);
				json.setSuccess(true);
				json.setMsg("保存成功！");
			}
			writeJson(json);
		}
	}

	
	/**
	 * 修改报表
	 */
	public void updateSql(){
		String sqlcon;
		Json json = new Json();
		
		try {
			tNameStr = URLDecoder.decode(tNameStr, "UTF-8");
			cNameStr=URLDecoder.decode(cNameStr, "UTF-8");
			sqlStr = URLDecoder.decode(sqlStr, "UTF-8");
			if(null != conditionList && !conditionList.isEmpty() ){
			for(Sqlcondition con:conditionList){
				if(con.getConditionsql()!=null&&con.getConditionsql().length()>0){
				sqlcon=URLDecoder.decode(con.getConditionsql(), "UTF-8");
				con.setConditionsql(sqlcon);
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] tArray = tNameStr.split("\\+");
		String[] cArray = cNameStr.split("\\+");
		String[] indexArray = tableIndex.split("\\+");
		if (tArray.length != cArray.length) {
			json.setMsg("中文字段和英文字段不匹配！");
			writeJson(json);
		} else {
			if (id != null&&id.length()>0) {
				((SyresourceServiceI) service).updateSql(db,unit,sortOrder,highchartsBtn,pageName,tArray, cArray, indexArray,sqlStr,
						id, conditionList,urlList,widthList,hcList,avgList,selfList,sqlList);
				json.setSuccess(true);
				json.setMsg("保存成功！");
				writeJson(json);
			}else{
				json.setSuccess(false);
				json.setMsg("保存失败！");
				writeJson(json);
			}
			
		}
	}

	/**
	 * 构建报表
	 * 
	 */
	public void build() {
		Page page=pageService.getById(id);
		List<PageCode> pageCodes=pageCodeService.findPageCode(id);
	    conditionList=sqlconditionService.findSqlconditionByPageId(id);
	    List<Map> selfWarn=pageCodeWarnService.findSelfWarnByPageId(id);
	    List<Map> avgWarn=pageCodeWarnService.findAvgWarnByPageId(id);
	    List<Map> sqlWarn=pageCodeWarnService.findSqlWarnByPageId(id);
	    String maxrow=pageCodeService.findMaxrowByPageId(id);
		Map map=new HashMap();
		map.put("page", page);
		map.put("pageCodes", pageCodes);
		map.put("sqlconditions", conditionList);
		map.put("selfWarn", selfWarn);
		map.put("avgWarn", avgWarn);
		map.put("sqlWarn", sqlWarn);
		map.put("maxrow", maxrow);
		writeJson(map);
		
	}

	/**
	 * easyui加载报表
	 * 
	 */
	@Override
	public void grid(){
		Grid grid=new Grid();
		Map avgMap=new HashMap();
		String sql=pageService.findPage(id).getSqlCode();
		conditionList=sqlconditionService.findSqlconditionByPageId(id);
		List<Map> warnCode=pageCodeService.findWarnByPageId(id);
		sql=SqlUtil.formatSqlForGrid(conditionList, sql);	
		DbUtil.setDb(db);
		//如果有需要平均值预警的pagecode,计算平均值
		if(warnCode.size()>0){
			List<Map> totalData=((SyresourceServiceI)service).findBySql(sql);
			//数据量不为0，才计算平均值
			if(totalData.size()>0){
			avgMap=((SyresourceServiceI) service).findAvgMap(totalData,warnCode, sql);
			}
			
			if(totalData.size()>rows){
				int start=rows*(page-1);
				int end=rows*page-1;
				List<Map> row=totalData.subList(start, end+1);
				grid.setRows(row);
				grid.setTotal(Long.valueOf(totalData.size()));
			}else{
				grid.setRows(totalData);
				grid.setTotal(Long.valueOf(totalData.size()));
			}
			
		}else{
			grid.setRows(((SyresourceServiceI)service).previewSql(sql,page,rows));
			grid.setTotal(((SyresourceServiceI)service).countSql(sql));
		}
		grid.setAvgMap(avgMap);
		writeJson(grid);
	}
	
	

	
	/**
	 * 获得自定义SQL combobox
	 */
	public void doNotNeedSecurity_sqlCombobox() {
		Sqlcondition sqlcon=sqlconditionService.getById(id);
		DbUtil.setDb(db);
		writeJson(((SyresourceServiceI)service).findBySql(sqlcon.getConditionsql()));
	}
	/**
	 * 生成excel，存在服务器上
	 */
	public void exportExcel() {
		Json json = new Json();
		String fileName;
		String sql = pageService.findPage(id).getSqlCode();
		conditionList = sqlconditionService.findSqlconditionByPageId(id);
		String maxrow=pageCodeService.findMaxrowByPageId(id);
		sql = SqlUtil.formatSqlForGrid(conditionList, sql);
		List<PageCode> pageCodeList = pageCodeService.findPageCode(id);
		DbUtil.setDb(db);
		List<Map> dataList = ((SyresourceServiceI)service).findBySql(sql);
		try {
			excelName = URLDecoder.decode(excelName, "UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		if(dataList.size()<50000){
		 fileName = PoiUtil.exportExcel(excelName, dataList, pageCodeList,maxrow);
		}else{
		 fileName=PoiUtil.exportBigExcel(excelName, dataList, pageCodeList, maxrow);
		}
		if (fileName != null) {
			json.setSuccess(true);
			json.setObj(fileName);
			json.setMsg("生成excel完成！");
			writeJson(json);
		} else {
			json.setSuccess(false);
			json.setMsg("生成excel失败！");
			writeJson(json);
		}
		

	}
	/**
	 * 从服务器下载excel
	 */
	public void download(){
		try {
			filenames = URLDecoder.decode(filenames, "UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		 String path = this.getRequest().getSession().getServletContext().getRealPath("/excel");
		 String filepath=path+"\\"+filenames;
		try {
			ExcelUtil.ExportExcel(filenames,this.getResponse(),filepath,this.getRequest() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取跳转页面信息
	 */
	public void doNotNeedSecurity_getJumpResource(){
		String sql="select t.name,t.iconcls from syresource t where t.pageid='"+id+"'";
		writeJson(((SyresourceServiceI)service).findBySql(sql));
	}
	/**
	 * 获取所有资源
	 */
	public void doNotNeedSecurity_getResourceUrl(){
		String sql="select t.id,t.name,t.syresource_id,t.url from syresource t ";
		writeJson(((SyresourceServiceI)service).findBySql(sql));
	}
	public void doNotNeedSecurity_test(){
		PoiUtil.testEx();
	}
	
}
