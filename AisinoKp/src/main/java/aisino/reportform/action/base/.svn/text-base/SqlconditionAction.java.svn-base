package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Sqlcondition;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.service.base.SqlconditionServiceI;
import aisino.reportform.util.base.HqlFilter;

@Namespace("/base")
@Action
public class SqlconditionAction  extends BaseAction<Sqlcondition>{
	

	@Autowired
	public void setService(SqlconditionServiceI service) {
		this.service = service;
	}
	
	public void grids() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#page.id_S_EQ", null);
		grid.setTotal(((SqlconditionServiceI) service).countSqlconditionByFilter(hqlFilter));
		grid.setRows(((SqlconditionServiceI) service).findSqlconditionByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
}
