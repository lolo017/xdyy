package aisino.reportform.action.base;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Budget;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.service.base.BudgetServiceI;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.HqlFilter;

@Namespace("/base")
@Action
public class BudgetAction extends BaseAction<Budget>{
	private String year;

	
	
	
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Autowired
	public void setService(BudgetServiceI service) {
		this.service = service;
	}
	
	public void grid2() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String orgid=sessionInfo.getUser().getUserorgid();
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		List<Budget> l = service.findByFilter(hqlFilter);
		String sql="select t.name from syorganization t where t.id='"+orgid+"'";
		List<Map> list=service.findBySql(sql);
		grid.getAvgMap().put("orgName", list.get(0).get("NAME"));
		grid.setTotal((long) l.size());
		grid.setRows(l);
		writeJson(grid);
	}
	
}
