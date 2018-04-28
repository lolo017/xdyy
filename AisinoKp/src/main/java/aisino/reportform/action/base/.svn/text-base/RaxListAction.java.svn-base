package aisino.reportform.action.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.Taxrate;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.TaxRateServiceI;
import aisino.reportform.util.base.ConfigUtil;

/**
 * @author 赵亮
 * 税率信息ACTION
 *
 */
@Namespace("/base")
@Action
public class RaxListAction extends BaseAction<Taxrate> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Autowired
	public void setService(TaxRateServiceI service) {
		this.service = service;
	}		

	/**
	 * 获取产品列表
	 */
	public void doNotNeedSecurity_getRaxList()
	{
		Grid grid = new Grid();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String sql = "select id,name,to_char(rate,990.99) || '%' rate from t_taxrate where 1=1";
		String sql_count = "select count(1) from t_taxrate where 1=1";
		
		sql = sql + " and org_id = '" + sessionInfo.getOrg_id() + "'";
		sql_count = sql_count + " and org_id = '" + sessionInfo.getOrg_id() + "'";
		
		if(name!=null)
		{
			sql+=" and name like '%"+name+"%'";
			sql_count+=" and name like '%"+name+"%'";
		}
		
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}	
	
	
	/**
	 * 新增或编辑产品信息
	 */
	public void doNotNeedSecurity_addOrEditRax()
	{
		Json json=new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Taxrate rate=service.getById(data.getId());		
		if(rate==null)
		{
			//新增客户信息
			try {
				data.setCreate_date(new Date());
				data.setOrg_id(sessionInfo.getOrg_id());
				service.save(data);
				json.setSuccess(true);
				json.setMsg("新增税率成功！");
			} catch (Exception e) {
				json.setMsg("新增税率失败！"+e.toString());
			}
		}
		else
		{
			//修改客户信息
			try {
				Date now = new Date();
				rate.setName(data.getName());
				rate.setRate(data.getRate());
				rate.setUpdate_date(now);
				rate.setOrg_id(sessionInfo.getOrg_id());
				service.update(rate);
				json.setSuccess(true);
				json.setMsg("编辑税率成功！");
			} catch (Exception e) {
				json.setMsg("编辑税率失败！"+e.toString());
			}
		}
		writeJson(json);
	}
	
	/**
	 * 根据产品ID查找
	 */
	public void doNotNeedSecurity_findById()
	{
		String sql = "select * from t_taxrate t where id='"+id+"'";
		List<Map> tList=service.findBySql(sql);
		if(tList.size()>0)
		{
			writeJson(tList.get(0));
		}
		else
		{
			Json j = new Json();
			j.setMsg("无税率！");
			writeJson(j);
		}
	}
	
	/**
	 * 根据产品ID删除产品
	 */
	public void doNotNeedSecurity_deleteRate()
	{
		Json json=new Json();
		Taxrate rate=service.getById(id);
		if(rate!=null)
		{
			service.delete(rate);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		else
		{
			json.setMsg("删除失败！");
		}
		writeJson(json);
	}

}
