package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Communicate;
import aisino.reportform.service.base.CommunicateServiceI;


@Namespace("/base")
@Action
public class CommunicateAction extends BaseAction<Communicate>{
	@Autowired
	public void setService(CommunicateServiceI service) {
		this.service = service;
	}
	
	
}
