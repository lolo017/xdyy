package aisino.reportform.action.base;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;



import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.SysProduct;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.SysProductServiceI;
import aisino.reportform.util.base.ConfigUtil;

/**
 * @author 赵亮
 * 产品信息ACTION
 *
 */
@Namespace("/base")
@Action
public class ProductListAction extends BaseAction<SysProduct> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String product_number;
	private String product_name;
	

	public String getProduct_number() {
		return product_number;
	}

	public void setProduct_number(String productNumber) {
		product_number = productNumber;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String productName) {
		product_name = productName;
	}

	@Autowired
	public void setService(SysProductServiceI service) {
		this.service = service;
	}		

	/**
	 * 获取产品列表
	 */
	final TimeZone zone = TimeZone.getTimeZone("GMT+8"); // 获取中国时区
	public void doNotNeedSecurity_getProductList()
	{
		Grid grid = new Grid();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String sql = "SELECT sp.id,sp.unit, sp.product_name, tt.name tax_category,to_char(tt.rate,990.99) || '%' rate,sp.product_number,sp.tax_price FROM sys_product sp, t_taxrate tt WHERE 1 = 1 AND sp.tax_category = tt.id";
		String sql_count = "select count(1) from sys_product where 1=1";
		
		sql = sql + " and sp.org_id = '" + sessionInfo.getOrg_id() + "'";
		sql_count = sql_count + " and org_id = '" + sessionInfo.getOrg_id() + "'";
		if(product_name!=null)
		{
			sql+=" and product_name like '%"+product_name+"%'";
			sql_count+=" and product_name like '%"+product_name+"%'";
		}
		
		if(product_number!=null)
		{
			sql+=" and product_number like '%"+product_number+"%'";
			sql_count+=" and product_number like '%"+product_number+"%'";
		}
		
		sql+=" ORDER BY SP.product_number ASC";
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	/**
	 * 获取产品下拉列表
	 */
	public void doNotNeedSecurity_getProductComList()
	{
		Grid grid = new Grid();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String q = getRequest().getParameter("q");
		String sql = "SELECT sp.id, sp.product_name, tt.name tax_category,tt.rate/100 rate,sp.product_number,sp.tax_price,sp.unit FROM sys_product sp, t_taxrate tt WHERE 1 = 1 AND sp.tax_category = tt.id";
		String sql_count = "select count(1) from sys_product where 1=1";
		
		sql = sql + " and sp.org_id = '" + sessionInfo.getOrg_id() + "'";
		sql_count = sql_count + " and org_id = '" + sessionInfo.getOrg_id() + "'";
		
		if(q!=null)
		{
			sql = sql + " and sp.product_name like '%"+q+"%'";
			sql_count = sql_count + " and product_name like '%"+q+"%'";

		}
		if ("0223475d-a339-45f4-8b39-bba4d18968e9".equals(sessionInfo.getUser().getId())){
			sql = sql + " and product_number in ('5021','5022')";
			sql_count = sql_count + " and product_number in ('5021','5022')";
		} else {
			sql = sql + " and product_number not in ('5021','5022')";
			sql_count = sql_count + " and product_number not in ('5021','5022')";
		}
		sql+=" order by sp.product_number asc";
        
		
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	
	/**
	 * 新增或编辑产品信息
	 */
	public void doNotNeedSecurity_addOrEditProduct()
	{
		TimeZone.setDefault(zone); // 设置时区
		Json json=new Json();
		SysProduct pro=service.getById(data.getId());	
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(pro==null)
		{
			//新增客户信息
			try {
				data.setCreate_date(new Date());
				data.setOrg_id(sessionInfo.getOrg_id());
				service.save(data);
				json.setSuccess(true);
				json.setMsg("新增产品成功！");
			} catch (Exception e) {
				json.setMsg("新增购方产品失败！"+e.toString());
			}
		}
		else
		{
			//修改客户信息
			try {
				Date now = new Date();
				pro.setProduct_name(data.getProduct_name());
				pro.setProduct_number(data.getProduct_number());
				pro.setTax_category(data.getTax_category());
				pro.setTax_price(data.getTax_price());
				pro.setUnit(data.getUnit());
				pro.setUpdate_date(now);
				pro.setOrg_id(sessionInfo.getOrg_id());
				service.update(pro);
				json.setSuccess(true);
				json.setMsg("更新产品信息成功！");
			} catch (Exception e) {
				json.setMsg("编辑产品信息失败！"+e.toString());
			}
		}
		writeJson(json);
	}
	
	/**
	 * 根据产品ID查找
	 */
	public void doNotNeedSecurity_findById()
	{
		String sql = "select * from sys_product t where id='"+id+"'";
		List<Map> tList=service.findBySql(sql);
		if(tList.size()>0)
		{
			writeJson(tList.get(0));
		}
		else
		{
			Json j = new Json();
			j.setMsg("无产品信息！");
			writeJson(j);
		}
	}
	
	/**
	 * 根据产品ID删除产品
	 */
	public void doNotNeedSecurity_deleteProduct()
	{
		Json json=new Json();
		SysProduct pro=service.getById(id);
		if(pro!=null)
		{
			service.delete(pro);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		else
		{
			json.setMsg("删除失败，请确认该产品是否存在！");
		}
		writeJson(json);
	}

}
