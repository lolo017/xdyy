package aisino.reportform.action.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.Syresource;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.easyui.Tree;
import aisino.reportform.service.base.SyresourceServiceI;
import aisino.reportform.util.base.BeanUtils;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.HqlFilter;

/**
 * 资源
 * 
 * @author 
 * 
 */
@Namespace("/base")
@Action
public class SyresourceAction extends BaseAction<Syresource> {

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	private String roleid;
	

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Autowired
	public void setService(SyresourceServiceI service) {
		this.service = service;
	}

	/**
	 * 更新资源
	 */
	@Override
	public void update() {
		Json json = new Json();
		if (!StringUtils.isBlank(data.getId())) {
			if (data.getSyresource() != null && StringUtils.equals(data.getId(), data.getSyresource().getId())) {
				json.setMsg("父资源不可以是自己！");
			} else {
				((SyresourceServiceI) service).updateResource(data);
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 获得主菜单tree，也用于获得上级资源菜单combotree
	 */
	public void doNotNeedSecurity_getMainMenu() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		hqlFilter.addFilter("QUERY_t#syresourcetype#id_S_EQ", "0");// 0就是只查菜单
		//hqlFilter.addFilter("QUERY_t#syresource#id_S_EQ", null);
		List<Syresource> resources = ((SyresourceServiceI) service).getMainMenu(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (Syresource resource : resources) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(resource, node);
			node.setText(resource.getName());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getUrl());
			attributes.put("target", resource.getTarget());
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}
	/**
	 * welcome页面中，根据id获得指定菜单tree
	 */
	public void doNotNeedSecurity_getMainMenuById() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		String id=getRequest().getParameter("id");
		String roleid=getRequest().getParameter("roleid");
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		hqlFilter.addFilter("QUERY_role#id_S_EQ", roleid);
		hqlFilter.addFilter("QUERY_t#syresourcetype#id_S_EQ", "0");// 0就是只查菜单
		if(getSession().getAttribute("roleid") != null)
		hqlFilter.addFilter("QUERY_role#id_S_EQ", getSession().getAttribute("roleid").toString());
		List<Syresource> resources = ((SyresourceServiceI) service).getMainMenu(hqlFilter);
		//只保留指定ID的父资源和子资源
		List<Syresource> remainResource = new ArrayList<Syresource>(); 
		for (Syresource resource : resources) {
			Syresource resource1=resource;
			while(resource!= null){
			if(resource.getId().equals(id)){
				remainResource.add(resource1);
			break;
			}else{
				
				resource=resource.getSyresource();
			}
		}
	}
		
		List<Tree> tree = new ArrayList<Tree>();
		for (Syresource resource : remainResource) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(resource, node);
			node.setText(resource.getName());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getUrl());
			attributes.put("target", resource.getTarget());
			node.setAttributes(attributes);
			tree.add(node);
//			if(node.getId().equals(id)||id.equals(node.getPid())){
//				tree.add(node);
//			}
		}
		
		writeJson(tree);
	}

	/**
	 * 获得资源treeGrid
	 */
	@Override
	public void treeGrid() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
		writeJson(((SyresourceServiceI) service).resourceTreeGrid(hqlFilter));
	}

	/**
	 * 获得角色的资源列表
	 */
	public void doNotNeedSecurity_getRoleResources() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_role#id_S_EQ", id);
		writeJson(((SyresourceServiceI) service).findResourceByFilter(hqlFilter));
	}

	/**
	 * 获得机构的资源列表
	 */
	public void doNotNeedSecurity_getOrganizationResources() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_ORGANIZATION#ID_S_EQ", id);
		writeJson(((SyresourceServiceI) service).findResourceByFilter(hqlFilter));
	}

	/**
	 * 获得资源树
	 */
	public void doNotNeedSecurity_getResourcesTree() {
		treeGrid();
	}

	/**
	 * 保存一个资源
	 */
	@Override
	public void save() {
		Json json = new Json();
		if (data != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			((SyresourceServiceI) service).saveResource(data, sessionInfo.getUser().getId());
			json.setSuccess(true);
		}
		writeJson(json);
	}
	/**
	 * 删除一个资源，若资源对应一个报表，同时删除报表
	 */
	@Override
	public void delete(){
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			((SyresourceServiceI) service).deleteResource(id);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}
	/**
	 * 根据userid查询对应的所有角色
	 */
	public void doNotNeedSecurity_getRolesByUserid(){
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		String  sql1="select s.NAME,s.ID,s.PIC from SYROLE s where s.ID in(select sy.MAIN_ROLE from SYUSER sy where sy.ID='"+sessionInfo.getUser().getId()+"') ";
		List<Map> l1=service.findBySql(sql1);
		String sql2="select t.NAME,t.ID,t.PIC from SYROLE t where t.ID in(select tt.SYROLE_ID from SYUSER_SYROLE tt where tt.SYUSER_ID='"+sessionInfo.getUser().getId()+"') order by t.SEQ";
		List<Map> l2=service.findBySql(sql2);
		if(l1.size()>0){
		for(int i=0;i<l2.size();i++){
			if(!l2.get(i).get("ID").toString().equals(l1.get(0).get("ID").toString())){
				l1.add(l2.get(i));
			}
		}
		}
		else{
			l1=l2;
		}
		Object ob = l1.get(0);
		Map map = (Map) ob;
		map.put("NAME", "主舱");
		writeJson(l1);

	}
	
	/**
	 * 根据角色id查询对应的最上层资源
	 */
	public void doNotNeedSecurity_getResourcesByRoleid(){
		getSession().setAttribute("roleid", roleid);
		String type = getRequest().getParameter("type");
		
		String sql="select s.ID,s.NAME,s.ICONCLS,s.URL from SYRESOURCE s where s.SYRESOURCE_ID is null and s.ID in(select ss.SYRESOURCE_ID from SYROLE_SYRESOURCE ss where ss.SYROLE_ID='"+roleid+"') and s.MANAGE_ACTIVE='" + type + "' order by s.SEQ";
		writeJson(service.findBySql(sql));
	}
	/**
	 * 根据用户id查询对应的最上层资源
	 */
	public void doNotNeedSecurity_getResourcesByUserid(){
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		String type = getRequest().getParameter("type");
		String sql="select s.ID,s.NAME,s.ICONCLS,s.URL from SYRESOURCE s where s.SYRESOURCE_ID is null and s.ID in(select ss.SYRESOURCE_ID from SYROLE_SYRESOURCE ss where ss.SYROLE_ID in(select tt.SYROLE_ID from SYUSER_SYROLE tt where tt.SYUSER_ID='"+sessionInfo.getUser().getId()+"'))";
		if(type != null && !"".equals(type) && !"undefined".equals(type)){
			sql += " and s.MANAGE_ACTIVE='" + type + "'";
		}
		sql += " order by s.SEQ";
		writeJson(service.findBySql(sql));
	}
	/**
	 * 根据资源id查询对应子资源
	 */
	public void doNotNeedSecurity_getResourcesByPid(){
String type = getRequest().getParameter("type");
		
		String sql="select t.ID,t.NAME,t.ICONCLS,t.URL from SYRESOURCE t where t.SYRESOURCETYPE_ID='0' and  t.SYRESOURCE_ID='"+id+"' and  t.MANAGE_ACTIVE='"+type+"'";
		if(roleid != null && !"".equals(roleid) && !"undefined".equals(roleid)){
		sql=sql+" and t.ID in (select tt.SYRESOURCE_ID from SYROLE_SYRESOURCE tt where tt.SYROLE_ID='"+roleid+"') ";
		}
		sql=sql+" order by t.SEQ";
		
		writeJson(service.findBySql(sql));
	}
	/**
	 * 根据资源id查询父资源
	 */
	public void doNotNeedSecurity_getResourcesLast(){
		String type = getRequest().getParameter("type");
		String sql="select t.ID,t.ICONCLS,t.URL,t.NAME,t.SYRESOURCE_ID as pid from SYRESOURCE t where (nvl(t.SYRESOURCE_ID,1) =(select nvl(tt.SYRESOURCE_ID,1)  from SYRESOURCE tt where tt.ID=(select ttt.SYRESOURCE_ID from syresource ttt where ttt.ID='"+id+"' ))) and t.manage_active='"+type+"' and t.syresourcetype_id='0' ";
		if(roleid != null && !"".equals(roleid) && !"undefined".equals(roleid)){
		sql=sql+"and t.ID in(select r.SYRESOURCE_ID from SYROLE_SYRESOURCE r where r.SYROLE_ID='"+roleid+"') ";
		}
		sql=sql+"order by t.SEQ";
		
		writeJson(service.findBySql(sql));
	}

}
