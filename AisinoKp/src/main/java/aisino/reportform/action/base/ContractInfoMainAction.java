package aisino.reportform.action.base;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.ContractInfoDetail;
import aisino.reportform.model.base.ContractInfoMain;
import aisino.reportform.model.base.ContractStepCharge;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.ContractInfoDetailServiceI;
import aisino.reportform.service.base.ContractInfoMainServiceI;
import aisino.reportform.service.base.ContractStepChargeServiceI;
import aisino.reportform.util.base.ConfigUtil;

@Namespace("/base")
@Action
public class ContractInfoMainAction extends BaseAction<ContractInfoMain> {

	private static final long serialVersionUID = 1L;
	final TimeZone zone = TimeZone.getTimeZone("GMT+8"); // 获取中国时区
	private Long contract_id;
	private Long contract_line_id;
	private Long doors_id;
	private Long doors_line_id;
	private Long step_id;
	private ContractInfoMain contractinfomain;
	private List<ContractInfoDetail> contractinfodetail;
	private List<ContractStepCharge> stepchargelist;
	
	
	
	

	@Autowired
	public void setService(ContractInfoMainServiceI service) {
		this.service = service;
	}
	
	@Autowired
	private ContractInfoDetailServiceI contractinfodetailservicei;

	@Autowired
	private ContractStepChargeServiceI contractstepchargeservicei;
    
	/**
	 * 获取合同信息列表
	 */
	public void doNotNeedSessionAndSecurity_grid() {
		Grid grid = new Grid();
		String sql="select c.* from CONTRACT_INFO_MAIN c,DOORS_INFOMATION_MASTER d where c.DOORS_ID = d.DOORS_ID and 1=1";
		String sql_count = "select count(1) from CONTRACT_INFO_MAIN c,DOORS_INFOMATION_MASTER d where c.DOORS_ID = d.DOORS_ID and 1=1";
		sql+=" order by c.CONTRACT_ID desc";
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	/**
	 * 获取门户信息
	 */
	public void doNotNeedSessionAndSecurity_getDoors(){
		
		String q = getRequest().getParameter("q");
		if(q!=null){
		    q = q.replace("'", "");
			String sql = "select m.DOORS_ID,m.DOORS_NAME from DOORS_INFOMATION_MASTER m where 1=1 and m.DOORS_NAME like '%" + q  + "%'";
			List list = service.findBySql(sql);
		    writeJson(list);
		} else {
			String sql = "select m.DOORS_ID,m.DOORS_NAME from DOORS_INFOMATION_MASTER m where 1=1";
			List list = service.findBySql(sql);
            writeJson(list);
		}

	}
	
	/**
	 *获取待租户信息
	 * @return
	 */
	
	public void doNotNeedSessionAndSecurity_queryCus(){
		String q = getRequest().getParameter("q");
		if(q!=null){
		    q = q.replace("'", "");
			String sql = "select * from RENT_CUST_INFO m where 1=1 and m.RENT_CUST_NAME like '%" + q  + "%' or m.RENT_CUST_CODE like '%" + q + "%'";
			List list = service.findBySql(sql);
		    writeJson(list);
		} else {
			String sql = "select * from RENT_CUST_INFO m where 1=1";
			List list = service.findBySql(sql);
            writeJson(list);
		}
		
	}

	/**
	 * 获取合同详细信息
	 * @return
	 */
	public void doNotNeedSecurity_getOpenAllInfoById(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			
			//获取主表数据
			String sqlA = "select ti.*,to_char(ti.create_date,'yyyy-mm-dd') create_date1,d.doors_name from CONTRACT_INFO_MAIN ti,DOORS_INFOMATION_MASTER d where  ti.doors_id = d.doors_id  and ti.CONTRACT_ID=" + contract_id;
			List<Map> mList=service.findBySql(sqlA);
			
			//获取明细表数据
			String  sqlB =  "select * from CONTRACT_INFO_DETAIL tl where tl.CONTRACT_ID=" + contract_id;
			List<Map> tList=service.findBySql(sqlB);
			
			Map obMap = new HashMap();
			obMap.put("main", mList);
			obMap.put("list", tList);
			
			writeJson(obMap);
			
		}catch (Exception e) {
			j.setMsg("加载失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	public void doNotNeedSecurity_findListById(){
		String sql = "select * from DOORS_INFOMATION_DETAIL v where v.FLOOR_STATUS=1 and v.DOORS_ID="+doors_id;
		List<Map> tlist = service.findBySql(sql);
		Map obMap = new HashMap();
		obMap.put("list", tlist);
		writeJson(obMap);
	}
	
	/**
	 * 删除整张商户信息
	 * @return
	 */
	
	public void doNotNeedSecurity_deleteMById(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			String sqlT = "select 1 from CONTRACT_CHARGE_INFO d where d.CONTRACT_ID=" + contract_id;
			List list  =  service.findBySql(sqlT);
			if (list != null && list.size() >0){
				throw new Exception("该合同已经被收费了，不能删除了！");
			}
			
			//删除主表
			String sql = " delete from CONTRACT_INFO_MAIN tl where tl.CONTRACT_ID=" + contract_id;
			service.executeSql(sql);
			
			
			//将楼层使用状态设置为未使用
			
			String sqlb = "SELECT * FROM CONTRACT_INFO_DETAIL tl WHERE tl.CONTRACT_ID=" + contract_id;
			List<Map> list1 = service.findBySql(sqlb);
			if(list1 != null && list1.size() >0){
				for (int i=0;i<list1.size();i++){
					String sql1="update DOORS_INFOMATION_DETAIL e set e.FLOOR_STATUS=1 where e.DOORS_LINE_ID="+Long.parseLong(list1.get(i).get("DOORS_LINE_ID").toString());
					service.executeSql(sql1);
				}
			}
			
			//删除明细表
			String sqlA = " delete from CONTRACT_INFO_DETAIL tl where tl.CONTRACT_ID=" + contract_id;
			service.executeSql(sqlA);
			
			j.setSuccess(true);
			j.setMsg("删除行信息成功！");
		}catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	
	/**
	 * 删除合同信息
	 * @return
	 */
	
	public void doNotNeedSecurity_deleteListById(){
		
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			
			String sql1 = "select CONTRACT_ID,DOORS_LINE_ID from CONTRACT_INFO_DETAIL tl where tl.CONTRACT_LINE_ID="+ contract_line_id;
			List<Map> list1  =  service.findBySql(sql1);
			if(list1 != null && list1.size() >0){
				contract_id = Long.parseLong(list1.get(0).get("CONTRACT_ID").toString());
				doors_line_id = Long.parseLong(list1.get(0).get("DOORS_LINE_ID").toString());
			} else {
				throw new Exception("该合同数据异常，不能删除了！");
			}
			String sqlT = "select 1 from CONTRACT_CHARGE_INFO d where d.CONTRACT_ID=" + contract_id;
			List list  =  service.findBySql(sqlT);
			if (list != null && list.size() >0){
				throw new Exception("该合同已经被收费了，不能删除了！");
			}
			
			String sql = " delete from CONTRACT_INFO_DETAIL tl where tl.CONTRACT_LINE_ID=" + contract_line_id;
			service.executeSql(sql);
			
			String sql2="update DOORS_INFOMATION_DETAIL e set e.FLOOR_STATUS=1 where e.DOORS_LINE_ID="+doors_line_id;
			service.executeSql(sql2);
			
			j.setSuccess(true);
			j.setMsg("删除行信息成功！");
		} catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	
	/**
	 * 保存门市信息主表及门市信息明细表
	 * @return
	 */
	
	public void doNotNeedSessionAndSecurity_saveContract(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String name= sessionInfo.getUser().getName();
		String creater = sessionInfo.getUser().getId();
		String org_id = sessionInfo.getOrg_id();
		try{
		
			//处理主表信息
			if (contractinfomain.getContract_id()==null){
				String sql = "SELECT SEQ_CONTRACT_INFO_MAIN.nextval aaa FROM dual";
				List<Map> list  = service.findBySql(sql);
				if(list!=null && list.size() >0){
					contract_id = Long.parseLong(list.get(0).get("AAA").toString());
				}
				contractinfomain.setContract_id(contract_id);
				contractinfomain.setCreate_date(new Date());
				contractinfomain.setCreate_by(creater);
				service.save(contractinfomain);
			} else {
				contract_id = contractinfomain.getContract_id();
				contractinfomain.setUpdate_by(creater);
				contractinfomain.setUpdate_date(new Date());
				service.update(contractinfomain);
			}
		
		//处理明细表
			//处理明细记录表
			 if (contractinfodetail.size() >0){
				 for (int i=0;i<contractinfodetail.size();i++){
					 if (contractinfodetail.get(i) != null) {
					 if(contractinfodetail.get(i).getContract_line_id() == null){
						 String sql1 = "SELECT SEQ_CONTRACT_INFO_DETAIL.nextval aaa FROM dual";
							List<Map> list1  = service.findBySql(sql1);
							if(list1!=null && list1.size() >0){
								contract_line_id = Long.parseLong(list1.get(0).get("AAA").toString());
								contractinfodetail.get(i).setContract_line_id(contract_line_id);
								contractinfodetail.get(i).setContract_id(contract_id);
								contractinfodetail.get(i).setCreate_by(creater);
								contractinfodetail.get(i).setCreate_date(new Date());
								contractinfodetailservicei.save(contractinfodetail.get(i));
								//反写商户状态
								String sql="update DOORS_INFOMATION_DETAIL e set e.FLOOR_STATUS=2 where e.DOORS_LINE_ID="+contractinfodetail.get(i).getDoors_line_id();
								service.executeSql(sql);
							}
					 } else {
							contractinfodetail.get(i).setUpdate_by(creater);
							contractinfodetail.get(i).setUpdate_date(new Date());
						    contractinfodetailservicei.update(contractinfodetail.get(i));
					 }
				  }
				 }
			 }
				j.setSuccess(true);
				j.setObj(contract_id);
				j.setMsg("保存信息成功！"); 
			 
		}catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
		}
		writeJson(j);
		
	}
	
	
	/**
	 * 阶段收费设置
	 * @return
	 */
	
	public void doNotNeedSecurity_getStepChargeByid(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			
			//获取主表数据
			String sqlA = "select v.CONTRACT_ID,v.contract_line_id,v.classfy_name,v.doors_name,v.doors_floor,v.doors_size,v.doors_rent from CONTRACT_DOORS_CLASSFY_V v where v.contract_line_id=" + contract_line_id;
			List<Map> mList=service.findBySql(sqlA);
			
			//获取明细表数据
			String  sqlB =  "select * from CONTRACT_STEP_CHARGE tl where tl.contract_line_id=" + contract_line_id;
			List<Map> tList=contractstepchargeservicei.findBySql(sqlB);
			
			Map obMap = new HashMap();
			obMap.put("main", mList);
			obMap.put("list", tList);
			
			writeJson(obMap);
			
		}catch (Exception e) {
			j.setMsg("加载失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	/**
	 * 保存明细表
	 * @return
	 */
	public void doNotNeedSessionAndSecurity_saveStep(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String name= sessionInfo.getUser().getName();
		String creater = sessionInfo.getUser().getId();
		String org_id = sessionInfo.getOrg_id();
		try{
           if (stepchargelist == null){
        	   throw new Exception("行上无数据，更新失败");
           }
			//处理明细记录表
			 if (stepchargelist.size() >0){
				 for (int i=0;i<stepchargelist.size();i++){
					 if (stepchargelist.get(i) != null) {
					 if(stepchargelist.get(i).getStep_id() == null){
						 String sql1 = "SELECT SEQ_CONTRACT_STEP_CHARGE.nextval aaa FROM dual";
							List<Map> list1  = service.findBySql(sql1);
							if(list1!=null && list1.size() >0){
								step_id = Long.parseLong(list1.get(0).get("AAA").toString());
								stepchargelist.get(i).setStep_id(step_id);
								stepchargelist.get(i).setContract_id(contract_id);
								stepchargelist.get(i).setContract_line_id(contract_line_id);
								stepchargelist.get(i).setCreate_by(creater);
								stepchargelist.get(i).setCreate_date(new Date());
								contractstepchargeservicei.save(stepchargelist.get(i));
							
							}
					 } else {
						    stepchargelist.get(i).setUpdate_by(creater);
						    stepchargelist.get(i).setUpdate_date(new Date());
							contractstepchargeservicei.update(stepchargelist.get(i));
					 }
				  }
				 }
				 String  u = "update CONTRACT_INFO_DETAIL c set c.IS_STEP_CHARGE=1 where c.CONTRACT_LINE_ID="+contract_line_id;
				 contractinfodetailservicei.executeSql(u);
			 }
			
			 j.setSuccess(true);
			 j.setObj(contract_line_id);
			 j.setMsg("保存信息成功！"); 
			 
		}catch (Exception e) {
			j.setMsg("更新失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	/**
	 * 删除阶梯收费表
	 * @return
	 */
	public void doNotNeedSecurity_deleteStepById(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		//先判定是否改变阶段收费标志
		try{
		String a = " select nvl(count(*),0) a,c.contract_line_id  "  +
                   " from CONTRACT_STEP_CHARGE c "+
                   " where exists (select 1 "+
                   " from CONTRACT_STEP_CHARGE d " +
                   "  where c.contract_line_id = d.contract_line_id "+
                   " and d.step_id ="+ step_id+") group by c.contract_line_id";
		List<Map> list  = service.findBySql(a);
		if(list != null && list.size() >0){
			if(Integer.parseInt(list.get(0).get("A").toString())==1){
				String s = "update CONTRACT_INFO_DETAIL c set c.IS_STEP_CHARGE=0 where c.contract_line_id=" + list.get(0).get("CONTRACT_LINE_ID").toString();
				contractinfodetailservicei.executeSql(s);
			}
			String u =" delete from CONTRACT_STEP_CHARGE s where s.step_id="+step_id;
			contractstepchargeservicei.executeSql(u);
			}
		else {
			throw new Exception("无数据了，数据已经被删除掉！");
		}
		j.setSuccess(true);
		j.setMsg("删除行信息成功！");
	} catch (Exception e) {
		j.setMsg("删除失败:"+ e.getMessage());
	}
	writeJson(j);
	}
	
	
	public Long getContract_id() {
		return contract_id;
	}

	public void setContract_id(Long contract_id) {
		this.contract_id = contract_id;
	}

	public Long getContract_line_id() {
		return contract_line_id;
	}

	public void setContract_line_id(Long contract_line_id) {
		this.contract_line_id = contract_line_id;
	}

	public ContractInfoMain getContractinfomain() {
		return contractinfomain;
	}

	public void setContractinfomain(ContractInfoMain contractinfomain) {
		this.contractinfomain = contractinfomain;
	}

	public List<ContractInfoDetail> getContractinfodetail() {
		return contractinfodetail;
	}

	public void setContractinfodetail(List<ContractInfoDetail> contractinfodetail) {
		this.contractinfodetail = contractinfodetail;
	}

	public ContractInfoDetailServiceI getContractinfodetailservicei() {
		return contractinfodetailservicei;
	}

	public void setContractinfodetailservicei(
			ContractInfoDetailServiceI contractinfodetailservicei) {
		this.contractinfodetailservicei = contractinfodetailservicei;
	}

	public Long getDoors_id() {
		return doors_id;
	}

	public void setDoors_id(Long doors_id) {
		this.doors_id = doors_id;
	}

	public Long getDoors_line_id() {
		return doors_line_id;
	}

	public void setDoors_line_id(Long doors_line_id) {
		this.doors_line_id = doors_line_id;
	}

	public List<ContractStepCharge> getStepchargelist() {
		return stepchargelist;
	}

	public void setStepchargelist(List<ContractStepCharge> stepchargelist) {
		this.stepchargelist = stepchargelist;
	}

	public ContractStepChargeServiceI getContractstepchargeservicei() {
		return contractstepchargeservicei;
	}

	public void setContractstepchargeservicei(
			ContractStepChargeServiceI contractstepchargeservicei) {
		this.contractstepchargeservicei = contractstepchargeservicei;
	}

	public Long getStep_id() {
		return step_id;
	}

	public void setStep_id(Long step_id) {
		this.step_id = step_id;
	}
	
	
	
	
}
