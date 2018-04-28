package aisino.reportform.action.base;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import aisino.reportform.action.BaseAction;
import aisino.reportform.model.view.SysOrg;
import aisino.reportform.service.base.SysOrgServiceI;

/**
 * 所有分子公司
 * 
 * @author
 * 
 */
@Namespace("/base")
@Action
public class SysorgAction extends BaseAction<SysOrg> {
	
	@Autowired
	public void setService(SysOrgServiceI service) {
		this.service = service;
	}

	/**
	 * 获得企业combobox
	 */
	public void doNotNeedSecurity_combobox() {
		writeJson(((SysOrgServiceI) service).findSysOrg());
	}
}
