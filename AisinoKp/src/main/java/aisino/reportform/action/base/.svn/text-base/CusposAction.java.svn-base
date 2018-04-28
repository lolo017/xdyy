package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.CusPos;
import aisino.reportform.service.base.CusposServiceI;

/**
 * 用户
 * 
 * action访问地址是/base/cuspos.sy
 * 
 * @author
 * 
 */
@Namespace("/base")
@Action
public class CusposAction extends BaseAction<CusPos> {
	@Autowired
	public void setService(CusposServiceI service) {
		this.service = service;
	}
}
