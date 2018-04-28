package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Study;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.service.base.StudyServiceI;
import aisino.reportform.util.base.HqlFilter;

@Namespace("/base")
@Action
public class StudyAction extends BaseAction<Study>{



	@Autowired
	public void setService( StudyServiceI service) {
		this.service = service;
	}
	public void grids() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#empId_S_EQ", data.getEmpId());
		grid.setTotal(((StudyServiceI) service).countStudyByFilter(hqlFilter));
		grid.setRows(((StudyServiceI) service).findStudyByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	
}
