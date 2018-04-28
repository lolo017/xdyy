package aisino.reportform.action.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.SyEmpLead;
import aisino.reportform.service.base.EmpleadServiceI;
import aisino.reportform.util.base.ConfigUtil;

@Namespace("/base")
@Action
public class EmpleadAction extends BaseAction<SyEmpLead> {
	@Autowired
	public void setService(EmpleadServiceI service) {
		this.service = service;
	}
private String empId;
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * 查询第一级
	 *
	 */
	@Override
	public void treeGrid(){
		SessionInfo sessionInfo = (SessionInfo) getSession()
				.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("empId", sessionInfo.getUser().getEmpId());
		params.put("leaderId", sessionInfo.getUser().getEmpId());
		String hql="from SyEmpLead t where t.empId=:empId or t.syemplead.empId=:leaderId";
		writeJson(service.find(hql,params));
	}
	/**
	 * 展开树节点
	 *
	 */
	public void treeGridExpand(){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("leaderId", empId);
		String hql="from SyEmpLead t where  t.syemplead.empId=:leaderId";
		writeJson(service.find(hql,params));
	}
	
}
