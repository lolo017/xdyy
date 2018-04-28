package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.PageCode;
import aisino.reportform.service.base.PageCodeServiceI;

@Namespace("/base")
@Action
public class PagecodeAction extends BaseAction<PageCode>{
	@Autowired
	public void setService(PageCodeServiceI service) {
		this.service = service;
	}
}
