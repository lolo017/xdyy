package aisino.reportform.action.base;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.RentCustInfo;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.RentCustInfoServiceI;
import aisino.reportform.util.base.ConfigUtil;



@Namespace("/base")
@Action
public class RentCustinfoAction extends BaseAction<RentCustInfo> {
	final TimeZone zone = TimeZone.getTimeZone("GMT+8"); // 获取中国时区
	private static final long serialVersionUID = 1L;
	@Autowired
	public void setService(RentCustInfoServiceI service) {
		this.service = service;
	}
	
	private Long rent_cust_id;
	private RentCustInfo rentCustInfo;
	
	
	
	/**
	 * 获取承租户信息列表
	 */
	public void doNotNeedSessionAndSecurity_grid() {
		Grid grid = new Grid();
		String sql="select * from RENT_CUST_INFO m where 1=1";
		String sql_count = "select count(1) from RENT_CUST_INFO where 1=1";
	    
		
		sql+=" order by RENT_CUST_ID desc";
		
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	

	/**
	 * 删除整张商户信息
	 * @return
	 */
	
	public void doNotNeedSecurity_deleteMById(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			String sqlT = "select 1 from CONTRACT_INFO_MAIN d where d.RENT_CUST_ID=" + rent_cust_id;
			List list  =  service.findBySql(sqlT);
			if (list != null && list.size() >0){
				throw new Exception("该租户已经被使用了，不能删除了！");
			}
			
			//删除主表
			String sql = " delete from RENT_CUST_INFO tl where tl.RENT_CUST_ID=" + rent_cust_id;
			service.executeSql(sql);
			
			j.setSuccess(true);
			j.setMsg("删除成功！");
			
		}catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	/**
	 * 获取主表和明细表
	 * @return
	 */
	
	public void doNotNeedSecurity_getOpenAllInfoById(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			
			//获取主表数据
			String sqlA = "select ti.*,to_char(ti.create_date,'yyyy-mm-dd') create_date1,to_char(ti.update_date,'yyyy-mm-dd') update_date1 from RENT_CUST_INFO ti where ti.rent_cust_id=" + rent_cust_id;
			List<Map> mList=service.findBySql(sqlA);
			
			writeJson(mList.get(0));
			
		}catch (Exception e) {
			j.setMsg("加载失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	/**
	 * 保存门市信息主表及门市信息明细表
	 * @return
	 */
	
	public void doNotNeedSessionAndSecurity_saveDoor(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String name= sessionInfo.getUser().getName();
		String creater = sessionInfo.getUser().getId();
		String org_id = sessionInfo.getOrg_id();
		try{
			//处理主表信息
		 if (rentCustInfo.getRent_cust_id()==null){
				String sql = "SELECT SEQ_RENT_CUST_INFO.nextval aaa FROM dual";
				List<Map> list  = service.findBySql(sql);
				if(list!=null && list.size() >0){
					rent_cust_id = Long.parseLong(list.get(0).get("AAA").toString());
				}
				rentCustInfo.setRent_cust_id(rent_cust_id);
				rentCustInfo.setCreate_date(new Date());
				rentCustInfo.setCreate_by(creater);
				service.save(rentCustInfo);
			} else {
				rent_cust_id =rentCustInfo.getRent_cust_id(); 
				rentCustInfo.setUpdate_date(new Date());
				rentCustInfo.setUpdate_by(creater);
				service.update(rentCustInfo);
		
			}
		    j.setObj(rent_cust_id);
			j.setSuccess(true);
			j.setMsg("保存成功！");
		}catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
		}
		writeJson(j);
		
	}
	
	
	public Long getRent_cust_id() {
		return rent_cust_id;
	}

	public void setRent_cust_id(Long rent_cust_id) {
		this.rent_cust_id = rent_cust_id;
	}

	public RentCustInfo getRentCustInfo() {
		return rentCustInfo;
	}

	public void setRentCustInfo(RentCustInfo rentCustInfo) {
		this.rentCustInfo = rentCustInfo;
	}
}
