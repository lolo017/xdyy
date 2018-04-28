package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Work;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.service.base.WorkServiceI;
import aisino.reportform.util.base.HqlFilter;

@Namespace("/base")
@Action
public class WorkAction extends BaseAction<Work>{
	@Autowired
	public void setService( WorkServiceI service) {
		this.service = service;
	}
	
	public void grids() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#empId_S_EQ", id);
		grid.setTotal(((WorkServiceI) service).countWorkByFilter(hqlFilter));
		grid.setRows(((WorkServiceI) service).findRoleByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
}
