package aisino.reportform.action.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Budget;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.Specific;
import aisino.reportform.model.base.Syorganization;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.SpecificServiceI;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.SecurityUtil;

@Namespace("/base")
@Action
public class SpecificAction extends BaseAction<Specific>{
	@Autowired
	public void setService(SpecificServiceI service) {
		this.service = service;
	}
	private String year;
	private String sid;
	private String orgName;
	

	
	
	
	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getSid() {
		return sid;
	}


	public void setSid(String sid) {
		this.sid = sid;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	@Override
	public void save(){
		Json json=new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String orgid=sessionInfo.getUser().getUserorgid();
		String sql="select count(*) from specific t where t.year='"+year+"' and t.orgid='"+orgid+"'";
		long count=service.countBySql(sql);
		if(count>0){
			json.setMsg(year+orgName+"已存在,如需修改请使用编辑功能!");
			writeJson(json);
		}else{
		String total[]=data.getTotal().split(",");//今年总价
		String price[]=data.getPrice().split(",");//今年单价
		String number[]=data.getNumbers().split(",");//今年数量
		String total2[]=data.getTotal2().split(",");//明年总价
		String price2[]=data.getPrice2().split(",");//明年单价
		String number2[]=data.getNumbers2().split(",");//明年数量
		//String year=this.getRequest().getParameter("year");
		//Budget budget=new Budget();
		String budget[]=data.getBudget().getIndexs().split(",");
		List<Specific> list=new ArrayList<Specific>();
		Syorganization syorganization=new Syorganization();
		//syorganization.setId();
		syorganization.setId(orgid);
		String sid=UUID.randomUUID().toString();
		for (int i = 0; i < budget.length; i++) {
			Specific specific=new Specific();
			specific.setBudgetid(i+1);
			Budget budget2=new Budget();
			budget2.setIndexs(budget[i]);
			specific.setYear(year);
			specific.setConfirm("0");
			specific.setBudget(budget2);
			specific.setSyorganization(syorganization);
			if(!number[i].equals("null")){
				specific.setNumbers(number[i]);
			}
			if(!price[i].equals("null")){
				specific.setPrice(price[i]);
			}
			if(!total[i].equals("null")){
				specific.setTotal(total[i]);
			}
			if(!number2[i].equals("null")){
				specific.setNumbers2(number2[i]);
			}
			if(!price2[i].equals("null")){
				specific.setPrice2(price2[i]);
			}
			if(!total2[i].equals("null")){
				specific.setTotal2(total2[i]);
			}
			
			specific.setSyorganization(syorganization);
			specific.setSid(sid);
			specific.setSname(year+orgName);
			list.add(specific);
		}
		service.saveList(list);
		
		json.setSuccess(true);
		json.setMsg("保存成功");
		writeJson(json);
		}
	}
	
	@Override
	public void update(){
		SecurityUtil securityUtil=new SecurityUtil(getSession());
		Json json=new Json();
		String sql1="select distinct t.confirm from SPECIFIC t where t.sid='"+sid+"'";
		List<Map> l=service.findBySql(sql1);
		String confirm=(String)l.get(0).get("CONFIRM");
		//如果用户没有审核权限，且该内容已审核过，就不能再编辑
		if(confirm.equals("1")){
			json.setMsg("该内容已被审核通过,无法进行编辑!");
			writeJson(json);
		}else{
		
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String orgid=sessionInfo.getUser().getUserorgid();
		((SpecificServiceI)service).updateSpecific(sid,data.getTotal().split(","), data.getPrice().split(","), data.getNumbers().split(","), data.getTotal2().split(","), data.getPrice2().split(","), data.getNumbers2().split(","),data.getBudget().getIndexs().split(","), orgid, orgName, year);
		json.setSuccess(true);
		json.setMsg("保存成功");
		writeJson(json);
		}
	}
	


	@Override
	public void grid() {
		Grid grid = new Grid();
		if(sid==null){
			writeJson(grid);
		}else{

		grid.setRows(((SpecificServiceI)service).findSpecificBySid(sid));
		writeJson(grid);
		}
	}
	//查询,省公司可以看到所有,分公司只能看到本公司
	public void doNotNeedSecurity_getSname(){
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		String sql;
		if(sessionInfo.getUser().getUserorgid().equals("0901")){
		sql="select distinct t.sid as value,t.sname  as text ,t.confirm from SPECIFIC t where t.year='"+year+"'";
		}else{
		sql="select distinct t.sid as value,t.sname as text,t.confirm from SPECIFIC t where t.year='"+year+"' and t.orgid='"+sessionInfo.getUser().getUserorgid()+"'";
		}
		List<Map> l=service.findBySql(sql);
		writeJson(l);
	}
//删除
	public void delSpecific(){
		String sql="delete from SPECIFIC t where t.sid='"+sid+"'";
		Json json = new Json();
			service.executeSql(sql);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		writeJson(json);
	}
	//通过审核,该内容将不能再修改
	public void conSpecific(){
		String sql="update  SPECIFIC t set t.confirm='1' where t.sid='"+sid+"'";
		Json json = new Json();
			service.executeSql(sql);
			json.setSuccess(true);
			json.setMsg("审核成功！");
		writeJson(json);
	}
	//反审核,执行后，该内容将可以编辑
	public void backSpecific(){
		String sql="update  SPECIFIC t set t.confirm='0' where t.sid='"+sid+"'";
		Json json = new Json();
			service.executeSql(sql);
			json.setSuccess(true);
			json.setMsg("反审核成功！");
		writeJson(json);
	}
	//验证是否能编辑
	public void doNotNeedSecurity_isEdit(){
		Json json=new Json();
		String sql1="select distinct t.confirm from SPECIFIC t where t.sid='"+sid+"'";
		List<Map> l=service.findBySql(sql1);
		String confirm=(String)l.get(0).get("CONFIRM");
		if(confirm.equals("1")){
			json.setMsg("该内容已被审核通过,无法进行编辑!");
			writeJson(json);
		}else{
			json.setSuccess(true);
			writeJson(json);
		}
		
	}
}
