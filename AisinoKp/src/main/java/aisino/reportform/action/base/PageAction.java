package aisino.reportform.action.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Page;
import aisino.reportform.model.base.PageCode;
import aisino.reportform.model.base.Sqlcondition;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.PageCodeServiceI;
import aisino.reportform.service.base.PageCodeWarnServiceI;
import aisino.reportform.service.base.PageServiceI;
import aisino.reportform.service.base.SqlconditionServiceI;

@Namespace("/base")
@Action
public class PageAction extends BaseAction<Page> {

	private String pageId;

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	@Autowired
	private PageServiceI pageService;
	@Autowired
	private PageCodeServiceI pageCodeService;
	@Autowired
	private SqlconditionServiceI sqlconditionService;
	@Autowired
	private PageCodeWarnServiceI pageCodeWarnService;

	@Autowired
	public void setService(PageServiceI service) {
		this.service = service;
	}
	
	/**
	 * 查询报表信息，和对应的表头名、条件
	 */
    public void getPageInfo(){
    	Page page=service.getById(id);
    	List<PageCode> list=pageCodeService.findPageCode(id);
    	String tName="";
    	for (int i = 0; i < list.size(); i++) {
    		if(i==0){
    			tName+=list.get(i).getCnName();
    		}else{
    			tName+="+"+list.get(i).getCnName();
    		}
    	}
//    	String hql="select new Sqlcondition(id,cncondition,encondition,conditiontype,conditionsql,seq,defaultValue) from Sqlcondition as p where p.page.id='"+id+"' order by seq ";
//		List<Sqlcondition> l=sqlconditionService.find(hql);
		List<Sqlcondition> conditionList=sqlconditionService.findSqlconditionByPageId(id);
	    List<Map> selfWarn=pageCodeWarnService.findSelfWarnByPageId(id);
	    List<Map> avgWarn=pageCodeWarnService.findAvgWarnByPageId(id);
	    List<Map> sqlWarn=pageCodeWarnService.findSqlWarnByPageId2(id);
		Map map=new HashMap();
		map.put("selfWarn", selfWarn);
		map.put("avgWarn", avgWarn);
		map.put("sqlWarn", sqlWarn);
		map.put("page", page);
		map.put("tName", tName);
		map.put("pageCodes", list);
		map.put("sqlcondition", conditionList);
		writeJson(map);
    }
    



	/**
	 * 删除报表，同时删除对应资源、条件、pagecode
	 */
	public void deletePage() {
		pageService.deletePage(pageId);
		Json json = new Json();
		json.setMsg("删除成功");
		json.setSuccess(true);
		writeJson(json);
	}
	
	@Override
	public void grid()
	{   
		Grid grid=new Grid();
		String sql="select t.ID,t.PAGENAME,t.SQLCODE from page t" ;
		String sql_count="select count(1) from page";
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
}
