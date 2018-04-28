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
import aisino.reportform.model.base.DoorsInformationD;
import aisino.reportform.model.base.DoorsInformationM;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.DoorsInformationDServiceI;
import aisino.reportform.service.base.DoorsInformationMServiceI;
import aisino.reportform.util.base.ConfigUtil;





@Namespace("/base")
@Action
public class DoorsInformationAction extends BaseAction<DoorsInformationM>  {
	private static final long serialVersionUID = 1L;
	private List<DoorsInformationD> doorlist;
	private Long  doors_id;
	private Long doors_line_id;
	private Long classfy_id;
	private DoorsInformationM doorM;
	private List<DoorsInformationD> doorD;
	final TimeZone zone = TimeZone.getTimeZone("GMT+8"); // 获取中国时区
	@Autowired
	public void setService(DoorsInformationMServiceI service) {
		this.service = service;
	}
	
	@Autowired
	private DoorsInformationDServiceI doorsInformationDService;

	/**
	 * 获取门市信息列表
	 */
	public void doNotNeedSessionAndSecurity_grid() {
		Grid grid = new Grid();
		String sql="select m.*,d.CLASSFY_NAME CLASSFY_ID_NAME from DOORS_INFOMATION_MASTER m,DOORS_CLASSFY d where m.CLASSFY_ID = d.CLASSFY_ID and 1=1";
		String sql_count = "select count(1) from DOORS_INFOMATION_MASTER where 1=1";
	    
		
		sql+=" order by DOORS_ID desc";
		
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	/**
	 * 获取门市分类信息
	 * @return
	 */
	
	public void doNotNeedSessionAndSecurity_getClassfy(){
		Grid grid = new Grid();
		String sql="select * from DOORS_CLASSFY m where 1=1 and IS_VALID=1";
		String sql_count = "select count(1) from DOORS_CLASSFY where 1=1 and IS_VALID=1";
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	/**
	 * 删除列表信息
	 * @return
	 */
	
	public void doNotNeedSecurity_deleteListById(){
		
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			
			String sqlT = "select 1 from CONTRACT_INFO_DETAIL d where d.DOORS_LINE_ID=" + doors_line_id;
			List list  =  service.findBySql(sqlT);
			if (list != null && list.size() >0){
				throw new Exception("该商户楼层已经被使用了，不能删除了！");
			}
			
			String sql = " delete from DOORS_INFOMATION_DETAIL tl where tl.DOORS_LINE_ID=" + doors_line_id;
			service.executeSql(sql);
			j.setSuccess(true);
			j.setMsg("删除行信息成功！");
		} catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
		}
		writeJson(j);
	}
	
	/**
	 * 删除整张商户信息
	 * @return
	 */
	
	public void doNotNeedSecurity_deleteMById(){
		Json j=new Json();
		TimeZone.setDefault(zone); // 设置时区
		try{
			String sqlT = "select 1 from CONTRACT_INFO_MAIN d where d.DOORS_ID=" + doors_id;
			List list  =  service.findBySql(sqlT);
			if (list != null && list.size() >0){
				throw new Exception("该门市信息已经被使用了，不能删除了！");
			}
			
			//删除主表
			String sql = " delete from DOORS_INFOMATION_MASTER tl where tl.DOORS_ID=" + doors_id;
			service.executeSql(sql);
			
			//删除明细表
			String sqlA = " delete from DOORS_INFOMATION_DETAIL tl where tl.DOORS_ID=" + doors_id;
			service.executeSql(sqlA);
			j.setSuccess(true);
			j.setMsg("删除信息成功！");
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
			String sqlA = "select ti.*,to_char(ti.create_date,'yyyy-mm-dd') create_date1,d.classfy_name classfy_id_name from DOORS_INFOMATION_MASTER ti,DOORS_CLASSFY d where ti.CLASSFY_ID = d.CLASSFY_ID and ti.DOORS_ID=" + doors_id;
			List<Map> mList=service.findBySql(sqlA);
			
			//获取明细表数据
			String  sqlB =  "select * from DOORS_INFOMATION_DETAIL tl where tl.DOORS_ID=" + doors_id;
			List<Map> tList=service.findBySql(sqlB);
			
			Map obMap = new HashMap();
			obMap.put("main", mList);
			obMap.put("list", tList);
			
			writeJson(obMap);
			
		}catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
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
			if (doorM.getDoors_id()==null){
				String sql = "SELECT SEQ_DOORS_INFOMATION_MASTER.nextval aaa FROM dual";
				List<Map> list  = service.findBySql(sql);
				if(list!=null && list.size() >0){
					doors_id = Long.parseLong(list.get(0).get("AAA").toString());
				}
				doorM.setDoors_id(doors_id);
				doorM.setCreate_date(new Date());
				doorM.setCreate_by(creater);
				service.save(doorM);
			} else {
				doors_id = doorM.getDoors_id(); 
				service.update(doorM);
			}
		
		//处理明细表
			//处理明细记录表
			 if (doorD.size() >0){
				 for (int i=0;i<doorD.size();i++){
					 if (doorD.get(i) != null) {
					 if(doorD.get(i).getDoors_line_id() == null){
						 String sql1 = "SELECT SEQ_DOORS_INFOMATION_DETAIL.nextval aaa FROM dual";
							List<Map> list1  = service.findBySql(sql1);
							if(list1!=null && list1.size() >0){
								doors_line_id = Long.parseLong(list1.get(0).get("AAA").toString());
								doorD.get(i).setDoors_line_id(doors_line_id);
								doorD.get(i).setDoors_id(doors_id);
								doorD.get(i).setCreate_by(creater);
								doorD.get(i).setCreate_date(new Date());
								doorsInformationDService.save(doorD.get(i));
							}
					 } else {
						 doorsInformationDService.update(doorD.get(i));
					 }
				  }
				 }
			 }
				j.setSuccess(true);
				j.setObj(doors_id);
				j.setMsg("保存行信息成功！");
			
		}catch (Exception e) {
			j.setMsg("删除失败:"+ e.getMessage());
		}
		writeJson(j);
		
	}
	
	
	
	
	public DoorsInformationDServiceI getDoorsInformationDService() {
		return doorsInformationDService;
	}

	public void setDoorsInformationDService(
			DoorsInformationDServiceI doorsInformationDService) {
		this.doorsInformationDService = doorsInformationDService;
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



	public DoorsInformationM getDoorM() {
		return doorM;
	}



	public void setDoorM(DoorsInformationM doorM) {
		this.doorM = doorM;
	}



	public List<DoorsInformationD> getDoorD() {
		return doorD;
	}



	public void setDoorD(List<DoorsInformationD> doorD) {
		this.doorD = doorD;
	}

	public Long getClassfy_id() {
		return classfy_id;
	}

	public void setClassfy_id(Long classfy_id) {
		this.classfy_id = classfy_id;
	}
	
	
	
	
	
}
