package aisino.reportform.action.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.Sykhxx;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.TicketKHXXServiceI;
import aisino.reportform.util.base.ConfigUtil;

@Namespace("/base")
@Action
public class TicketCustomerAction extends BaseAction<Sykhxx> {
	private String gfsh;
	private String gfmc;
	private String gfdzdh;
	
	@Autowired
	public void setService(TicketKHXXServiceI service) {
		this.service = service;
	}
	
	public String getGfsh() {
		return gfsh;
	}



	public void setGfsh(String gfsh) {
		this.gfsh = gfsh;
	}



	public String getGfmc() {
		return gfmc;
	}



	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}



	public String getGfdzdh() {
		return gfdzdh;
	}



	public void setGfdzdh(String gfdzdh) {
		this.gfdzdh = gfdzdh;
	}



	public void doNotNeedSessionAndSecurity_gridCus()
	{
		Grid grid = new Grid();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String sql = "select * from t_khxx where 1=1";
		String sql_count = "select count(1) from t_khxx where 1=1";
		
		sql = sql + " and org_id = '" + sessionInfo.getOrg_id() + "'";
		sql_count = sql_count + " and org_id = '" + sessionInfo.getOrg_id() + "'";
		
		if(gfsh!=null)
		{
			sql+=" and taxcode='"+gfsh+"'";
			sql_count+=" and taxcode='"+gfsh+"'";
		}
		
		if(gfmc!=null)
		{
			sql+=" and name like '%"+gfmc+"%'";
			sql_count+=" and name like '%"+gfmc+"%'";
		}
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	public void doNotNeedSessionAndSecurity_queryCus()
	{
		String q = getRequest().getParameter("q");
		
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		
		Grid grid = new Grid();
		String sql = "select * from t_khxx where 1=1 ";
		String sql_count = "select count(1) from t_khxx where 1=1 ";
		
		sql = sql + " and org_id = '" + sessionInfo.getOrg_id() + "'";
		sql_count = sql_count + " and org_id = '" + sessionInfo.getOrg_id() + "'";
		
		if(q!=null)
		{
			sql = sql + " and name like '%"+q.replaceAll("'", "")+"%'";
			sql_count = sql_count + " and name='%"+q.replaceAll("'", "")+"%'";
		}
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	public void doNotNeedSessionAndSecurity_deleteCus()
	{
		Json json=new Json();
		Sykhxx sykhxx=service.getById(id);
		if(sykhxx!=null)
		{
			service.delete(sykhxx);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		else
		{
			json.setMsg("删除失败，请确认该用户是否存在！");
		}
		writeJson(json);
	}
	
	public void doNotNeedSessionAndSecurity_addoreditCus()
	{
		Json json=new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Sykhxx sykhxx=service.getById(data.getId());
		if(sykhxx==null)
		{
			//新增客户信息
			try {
				data.setCreate_date(new Date());
				data.setOrg_id(sessionInfo.getOrg_id());
				data.setCreater_id(sessionInfo.getUser().getId());
				service.save(data);
				json.setSuccess(true);
				json.setMsg("新增购方信息成功！");
			} catch (Exception e) {
				json.setMsg("新增购方信息失败！"+e.toString());
			}
		}
		else
		{
			//修改客户信息
			try {
				sykhxx.setName(data.getName());
				sykhxx.setTaxcode(data.getTaxcode());
				sykhxx.setDzdh(data.getDzdh());
				sykhxx.setTelphone(data.getTelphone());
				sykhxx.setMobile(data.getMobile());
				sykhxx.setUpdate_date(new Date());
				sykhxx.setOrg_id(sessionInfo.getOrg_id());
				service.update(sykhxx);
				json.setSuccess(true);
				json.setMsg("更新购方信息成功！");
			} catch (Exception e) {
				json.setMsg("编辑购方信息失败！"+e.toString());
			}
		}
		writeJson(json);
	}
	
	public void doNotNeedSessionAndSecurity_getCusInfoById()
	{
		String sql = "select * from T_KHXX t where id='"+id+"'";
		List<Map> tList=service.findBySql(sql);
		if(tList.size()>0)
		{
			writeJson(tList.get(0));
		}
		else
		{
			Json j = new Json();
			j.setMsg("无客户信息！");
			writeJson(j);
		}
	}
}
