package aisino.reportform.action.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.ContractChargeInfo;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.ContractChargeInfoServiceI;
import aisino.reportform.util.base.ConfigUtil;


@Namespace("/base")
@Action
public class ContractChargeInfoAction extends BaseAction<ContractChargeInfo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long charge_id;
	private Long rent_cust_id;
	private Long contract_id;
	private Long payment_method;
	private ContractChargeInfo ccinfo;
	
	
	final TimeZone zone = TimeZone.getTimeZone("GMT+8"); // 获取中国时区
	@Autowired
	public void setService(ContractChargeInfoServiceI service) {
		this.service = service;
	}
	
	/**
	 * 获取收费信息列表
	 */
	public void doNotNeedSessionAndSecurity_grid() {
		Grid grid = new Grid();
		String sql="select * from CONTRACT_CHARGE_INFO m where  1=1";
		String sql_count = "select count(1) from CONTRACT_CHARGE_INFO where 1=1";
	    
		
		sql+=" order by CHARGE_ID desc";
		
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	
	/**
	 * 删除合同收费信息表
	 * @return
	 */
	public void doNotNeedSecurity_deleteMById(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			String sqlT = "select 1 from CONTRACT_CHARGE_INFO d where d.IS_CHARGE=1 and d.CHARGE_ID=" + charge_id;
			List list  =  service.findBySql(sqlT);
			if (list != null && list.size() >0){
				throw new Exception("该合同已经收费了，不能删除了！");
			}
			
			//删除主表
			String sql = " delete from CONTRACT_CHARGE_INFO tl where tl.CHARGE_ID=" + charge_id;
			service.executeSql(sql);
			
		
			j.setSuccess(true);
			j.setMsg("删除信息成功！");
		}catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	/**
	 * 获取合同主收费信息
	 * @return
	 */
	/**
	 * 获取主表和明细表
	 * @return
	 */
	
	public void doNotNeedSecurity_getOpenAllInfoById(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			//获取主表数据
			String sqlA = "select ti.* from CONTRACT_CHARGE_INFO ti where ti.CHARGE_ID" + charge_id;
			List mList=service.findBySql(sqlA);
			writeJson(mList);
			
		}catch (Exception e) {
			j.setMsg("加载失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	
	/**
	 * 保存门市信息主表及门市信息明细表
	 * @return
	 */
	
	public void doNotNeedSessionAndSecurity_saveCharge(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String name= sessionInfo.getUser().getName();
		String creater = sessionInfo.getUser().getId();
		String org_id = sessionInfo.getOrg_id();
		try{
		
			//处理主表信息
			if (ccinfo.getCharge_id()==null){
				String sql = "SELECT SEQ_CONTRACT_CHARGE_INFO.nextval aaa FROM dual";
				List<Map> list  = service.findBySql(sql);
				if(list!=null && list.size() >0){
					charge_id = Long.parseLong(list.get(0).get("AAA").toString());
				}
				ccinfo.setCharge_id(charge_id);
				ccinfo.setCreate_date(new Date());
				ccinfo.setCreate_by(creater);
				service.save(ccinfo);
			} else {
				charge_id = ccinfo.getCharge_id(); 
				ccinfo.setUpdate_by(creater);
				ccinfo.setUpdate_date(new Date());
				service.update(ccinfo);
			}
				j.setSuccess(true);
				j.setObj(charge_id);
				j.setMsg("保存行信息成功！");
			
		}catch (Exception e) {
			j.setMsg("保存失败:"+ e.getMessage());
		}
		writeJson(j);
		
	}
	
	/**
	 * 查询合同信息
	 * @return
	 */
	public void doNotNeedSessionAndSecurity_queryContract(){
		String q = getRequest().getParameter("q");
		if(q!=null){
		    q = q.replace("'", "");
			String sql = "select * from CONTRACT_RENT_DOORSM_CLASSFY_v v where 1=1 and m.CONTRACT_NUMBER like '%" + q  + "%'";
			List list = service.findBySql(sql);
		    writeJson(list);
		} else {
			String sql = "select * from CONTRACT_RENT_DOORSM_CLASSFY_v m where 1=1";
			List list = service.findBySql(sql);
            writeJson(list);
		}

	}
	
	
	/***
	 * 获取数据初始化
	 */
	public void doNotNeedSecurity_findOtherSet(){
		Double latefee = 0D;
		Long  lateType = 0L;
		Long step = 0L;
		Long cal_type = 1L;
		Double doors_rent = 0D;
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String dexpire = dateFormater.format(date);
		//判定滞纳金违约天数
		String sql="select floor(sysdate - to_date(t.RENT_EXPIRE_DATE,'yyyy-mm-dd')) dayd，to_char(t.RENT_EXPIRE_DATE,'yyyy-MM-dd') RENT_EXPIRE_DATE from RENT_CHARGE_EXPIRE t where t.CONTRACT_ID="+contract_id + " and t.RENT_CUST_ID=" + rent_cust_id;
		List<Map> list = service.findBySql(sql);
		if(list != null && list.size()>0){
			lateType  = Long.parseLong(list.get(0).get("DAYD").toString());
			dexpire = list.get(0).get("RENT_EXPIRE_DATE").toString();
		}
		//判定本次缴费是第几次缴费
		String sqla="select STEP_AMOUNT from RENT_CHARGE_EXPIRE e where e.CONTRACT_ID="+contract_id + " and e.RENT_CUST_ID=" +rent_cust_id;
		List<Map> lista = service.findBySql(sqla);
		if(lista != null && lista.size()>0){
			step = Long.parseLong(lista.get(0).get("STEP_AMOUNT").toString()) + 1L;
		} else {
			step = 1L;
		}
		
		//计算租金
		//1 先判定系统是否存在阶梯收费
		String sqlb="select CAL_TYPE,nvl(DOORS_RENT,0) DOORS_RENT from CONTRACT_STEP_CHARGE s where s.CONTRACT_ID=" + contract_id + " and s.START_DATE>=sysdate and sysdate <=s.END_DATE";
		List<Map> listb = service.findBySql(sqlb);
		if(listb != null && listb.size()>0){
			cal_type = Long.parseLong(listb.get(0).get("CAL_TYPE").toString());
			doors_rent = doors_rent + Double.parseDouble(listb.get(0).get("DOORS_RENT").toString());
		}
		
		Map map = new HashMap();
		map.put("LATETYPE", lateType);
		map.put("STEP", step);
		map.put("CAL_TYPE", cal_type);
		map.put("DEXPIRE", dexpire);
		map.put("DOORS_RENT", doors_rent);
		List listc = new ArrayList();
		listc.add(map);
		writeJson(listc);
		
	}
	
	
	public Long getCharge_id() {
		return charge_id;
	}
	public void setCharge_id(Long charge_id) {
		this.charge_id = charge_id;
	}
	public Long getRent_cust_id() {
		return rent_cust_id;
	}
	public void setRent_cust_id(Long rent_cust_id) {
		this.rent_cust_id = rent_cust_id;
	}
	public Long getContract_id() {
		return contract_id;
	}
	public void setContract_id(Long contract_id) {
		this.contract_id = contract_id;
	}
	public ContractChargeInfo getCcinfo() {
		return ccinfo;
	}
	public void setCcinfo(ContractChargeInfo ccinfo) {
		this.ccinfo = ccinfo;
	}

	public Long getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(Long payment_method) {
		this.payment_method = payment_method;
	}
	
	

}
