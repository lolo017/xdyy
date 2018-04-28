package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Workstudu;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.service.base.WordstudyServiceI;
import aisino.reportform.util.base.HqlFilter;


@Namespace("/base")
@Action
public class WordandstudyAction extends BaseAction<Workstudu>{

	@Autowired
	public void setService( WordstudyServiceI service) {
		this.service = service;
	}
	
	public void grids() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#syuser.id_S_EQ", data.getSyuser().getId());
		grid.setTotal(((WordstudyServiceI) service).countWorkstuduByFilter(hqlFilter));
		grid.setRows(((WordstudyServiceI) service).findRoleByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
}
