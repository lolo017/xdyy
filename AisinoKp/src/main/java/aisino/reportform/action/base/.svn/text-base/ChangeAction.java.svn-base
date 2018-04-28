package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Change;
import aisino.reportform.service.base.ChangeServiceI;

@Namespace("/base")
@Action
public class ChangeAction extends BaseAction<Change>{

	@Autowired
	public void setService(ChangeServiceI service) {
		this.service = service;
	}
}
