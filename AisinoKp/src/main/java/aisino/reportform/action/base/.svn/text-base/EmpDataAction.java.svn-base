package aisino.reportform.action.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.EmpDegree;
import aisino.reportform.model.base.EmpFamily;
import aisino.reportform.model.base.EmpTitle;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.SyEmpData;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.EmpDataServiceI;
import aisino.reportform.service.base.SyorganizationServiceI;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.HqlFilter;
import aisino.reportform.util.base.SecurityUtil;

/**
 * 员工基本信息action
 * 
 *@author 廖宸宇
 * @date 2015-5-25
 * 
 */
@Namespace("/base")
@Action
public class EmpDataAction extends BaseAction<SyEmpData>{
	private List<EmpDegree> empDegreeList;
	private List<EmpFamily> empFamilyList;
	private List<EmpTitle> empTitleList;
	private String empId;
	private String empName;
	private String qid;
	private String qname;
	private String orgId;
	private String depId;
	private String posId;
	private String leaderId;
	

	@Autowired
	private SyorganizationServiceI syorganizationService;
	
	public List<EmpFamily> getEmpFamilyList() {
		return empFamilyList;
	}
	public void setEmpFamilyList(List<EmpFamily> empFamilyList) {
		this.empFamilyList = empFamilyList;
	}
	public List<EmpTitle> getEmpTitleList() {
		return empTitleList;
	}
	public void setEmpTitleList(List<EmpTitle> empTitleList) {
		this.empTitleList = empTitleList;
	}
	public List<EmpDegree> getEmpDegreeList() {
		return empDegreeList;
	}
	public void setEmpDegreeList(List<EmpDegree> empDegreeList) {
		this.empDegreeList = empDegreeList;
	}
	@Autowired
	public void setService(EmpDataServiceI service) {
		this.service = service;
	}
	
	
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * 根据dpid查询员工基本信息datagrid
	 */
	public void doNotNeedSecurity_empDataGrid(){
		SecurityUtil securityUtil=new SecurityUtil(getSession());
		//判断是否具有超级权限,是的话查询所有，不是的话查询自己和自己的下级
		if(securityUtil.havePermission("/base/emp-data/all")){
        Grid grid = new Grid();
		StringBuffer sql=new StringBuffer("select * from syempdata sye where sye.dpid in (select syo.id from syorganization syo start with syo.id= '");
		sql.append(id).append("' connect by prior syo.id=syo.syorganization_id）");
		//如果empid不为空，增加查询参数
		if(empId!=null&&empId.length()>0){
			sql=sql.append(" and sye.empid='").append(empId).append("'");
		}
		//如果emp不为空，增加查询参数
		if(empName!=null&&empName.length()>0){
			sql=sql.append(" and sye.name like '%").append(empName).append("%'");
		}
		sql=sql.append("order by indate");
		String countSql=sql.toString().replace("*", "count(*)");
		grid.setTotal(service.countBySql(countSql));
		grid.setRows(service.findBySql(sql.toString(), page, rows));
		writeJson(grid);
		}else{
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			String empid=sessionInfo.getUser().getEmpId();
			Grid grid = new Grid();
			StringBuffer sql=new StringBuffer("select * from syempdata sye where sye.dpid in (select syo.id from syorganization syo start with syo.id= '");
			sql.append(id).append("' connect by prior syo.id=syo.syorganization_id )and (sye.empid='").append(empid).append("'or sye.leaderid='").append(empid).append("' )");
			//如果empid不为空，增加查询参数
			if(empId!=null&&empId.length()>0){
				sql=sql.append(" and sye.empid='").append(empId).append("'");
			}
			//如果emp不为空，增加查询参数
			if(empName!=null&&empName.length()>0){
				sql=sql.append(" and sye.name like '%").append(empName).append("%'");
			}
			sql=sql.append("order by indate");
			String countSql=sql.toString().replace("*", "count(*)");
			grid.setTotal(service.countBySql(countSql));
			grid.setRows(service.findBySql(sql.toString(), page, rows));
			writeJson(grid);
		}
	}
	/**
	 * 根据posid查询员工基本信息datagrid
	 */
	public void doNotNeedSecurity_empDataGridByPosId(){
		SecurityUtil securityUtil=new SecurityUtil(getSession());
		//判断是否具有超级权限,是的话查询所有，不是的话查询自己和自己的下级
		if(securityUtil.havePermission("/base/emp-data/all")){
		    Grid grid = new Grid();
			StringBuffer sql=new StringBuffer("select * from syempdata t where t.posid='");
			sql.append(id).append("'");
			//如果empid不为空，增加查询参数
			if(empId!=null&&empId.length()>0){
				sql=sql.append(" and t.empid='").append(empId).append("'");
			}
			//如果emp不为空，增加查询参数
			if(empName!=null&&empName.length()>0){
				sql=sql.append(" and t.name like '%").append(empName).append("%'");
			}
			sql=sql.append("order by indate");
			String countSql=sql.toString().replace("*", "count(*)");
			grid.setTotal(service.countBySql(countSql));
			grid.setRows(service.findBySql(sql.toString(), page, rows));
			writeJson(grid);
		}else{
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			String empid=sessionInfo.getUser().getEmpId();
			Grid grid = new Grid();
			StringBuffer sql=new StringBuffer("select * from syempdata t where t.posid='");
			sql.append(id).append("' and (t.empid='").append(empid).append("' or t.leaderid='").append(empid).append("') ");
			//如果empid不为空，增加查询参数
			if(empId!=null&&empId.length()>0){
				sql=sql.append(" and t.empid='").append(empId).append("'");
			}
			//如果emp不为空，增加查询参数
			if(empName!=null&&empName.length()>0){
				sql=sql.append(" and t.name like '%").append(empName).append("%'");
			}
			sql=sql.append("order by indate");
			String countSql=sql.toString().replace("*", "count(*)");
			grid.setTotal(service.countBySql(countSql));
			grid.setRows(service.findBySql(sql.toString(), page, rows));
			writeJson(grid);
		}
	}
	/**
	 * 保存员工信息
	 */
	@Override
	public void save(){
		//String dataId=data.getId();
		//data.setId(dataId);
		//将list参数转为set集合存入一方
//		if(null != empDegreeList && !empDegreeList.isEmpty() ){
//		for (EmpDegree empDegree :empDegreeList){
//			empDegree.setSyEmpData(data);
//		}
//		Set empDegreeSet=new HashSet(empDegreeList);
//		data.setEmpDegrees(empDegreeSet);
//		}
//		
//		if(null != empTitleList && !empTitleList.isEmpty() ){
//		for (EmpTitle empTitle :empTitleList){
//			empTitle.setSyEmpData(data);
//		}
//		Set empTitleSet=new HashSet(empTitleList);
//		data.setEmpTitles(empTitleSet);
//		}
//		
//		if(null != empFamilyList && !empFamilyList.isEmpty() ){
//		for (EmpFamily empFamily :empFamilyList){
//			empFamily.setSyEmpData(data);
//		}
//		Set empFamilySet=new HashSet(empFamilyList);
//		data.setEmpFamilys(empFamilySet);
//		}
//		
//		Json json = new Json();
//	    service.save(data);
//		json.setSuccess(true);
//		json.setMsg("新建成功！");
//		writeJson(json);
		Json json = new Json();
		((EmpDataServiceI)service).saveEmpData(empDegreeList,empFamilyList,empTitleList,data);
		json.setSuccess(true);
		json.setMsg("新增成功！");
	    writeJson(json);
	}
	/**
	 * 更新员工信息
	 */
	@Override
	public void update() {
		Json json = new Json();

			((EmpDataServiceI)service).updateEmpData(empDegreeList,empFamilyList,empTitleList,data);
			json.setSuccess(true);
			json.setMsg("更新成功！");
		writeJson(json);
	}
	/**
	 * 查询员工所有信息
	 */
	@Override
	public void getById(){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("id", id);
		String degreeHql="from EmpDegree t where t.syEmpData.id=:id";
		String titleHql="from EmpTitle t where t.syEmpData.id=:id";
		String familyHql="from EmpFamily t where t.syEmpData.id=:id";
		Map map=new HashMap();
		map.put("data", service.getById(id));
		map.put("degree", service.find(degreeHql,params));
		map.put("title", service.find(titleHql,params));
		map.put("family", service.find(familyHql,params));
		writeJson(map);
	}
	
	/**
	 * 查询第一级
	 *
	 */
	public void doNotNeedSecurity_treeGrid(){
//		Map<String, Object> params= new HashMap<String, Object>();
//		params.put("id", "09");
//		String hql="from Syorganization t where t.id=:id or t.syorganization.id=:id";
//		writeJson(service.find(hql,params));
		
		SessionInfo sessionInfo = (SessionInfo) getSession()
				.getAttribute(ConfigUtil.getSessionInfoName());
		String sql="select t.* from SYORGANIZATION t left join SYUSER_SYORGANIZATION tt on t.id=tt.syorganization_id left join SYUSER ttt on tt.syuser_id=ttt.id where ttt.id='"+sessionInfo.getUser().getId()+"'";
		sql = sql + " and t.id = '" + sessionInfo.getOrg_id() + "'";
		writeJson(service.findBySql(sql));
	}
	/**
	 * 展开树节点
	 *
	 */
	public void doNotNeedSecurity_treeGridExpand(){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("id", id);
		String hql="from Syorganization t where  t.syorganization.id=:id";
		writeJson(service.find(hql,params));
	}
	/**
	 * 获取上级架构
	 *
	 */
	public void doNotNeedSecurity_empComboTree(){
		HqlFilter hqlFilter = new HqlFilter();
		writeJson(service.findByFilter(hqlFilter));
	}
	/**
	 * 查询公司treegrid
	 *
	 */
	public void doNotNeedSecurity_orgTreeGrid(){
		String sql="select * from syorganization t where  t.levelId=1";
		writeJson(syorganizationService.findBySql(sql));
	}
	/**
	 * 查询部门treegrid
	 *
	 */
	public void doNotNeedSecurity_depTreeGrid(){
		String sql="select * from syorganization sye where sye.id in (select syo.id from syorganization syo start with syo.id='"+id+"' connect by prior syo.id=syo.syorganization_id）and sye.levelid<>3";
		writeJson(syorganizationService.findBySql(sql));
	}
	/**
	 * 查询岗位treegrid
	 *
	 */
	public void doNotNeedSecurity_posTreeGrid(){
		String sql="select * from syorganization sye where sye.id in (select syo.id from syorganization syo start with syo.id='"+id+"' connect by prior syo.id=syo.syorganization_id）and sye.levelid<>1 order by sye.levelid desc";
		writeJson(syorganizationService.findBySql(sql));
	}
	/**
	 * 查询上级treegrid第一级
	 *
	 */
	public void doNotNeedSecurity_leaderTreeGrid(){
		 List<Map> l;
		if(qid!=null||qname!=null){
			String sql="select t.name,t.empid,t.leaderid,t.empid as id from syempdata t  where t.empid='"+qid+"' or t.name='"+qname+"'";
		    l=service.findBySql(sql);
		}else{
		
		String sql="select t.name,t.empid,t.leaderid,t.empid as id from syempdata t  where t.empid='51010001' or t.leaderid='51010001'";
	     l=service.findBySql(sql);
	    Long num;
	    //查询是否有下级，设置state
	    for(Map m :l){
	    num=service.countBySql("select count(*) from syempdata t where t.leaderid='"+m.get("EMPID")+"'");
	    	 if(num>0){
	    		 m.put("state", "closed");
	    	 }else{
	    		 m.put("state", "open");
	    	 }
	    }
		}
	    writeJson(l);
	}
	/**
	 * 展开上级treegrid
	 *
	 */
	public void doNotNeedSecurity_leaderTreeGridExpand(){
		String sql="select t.name,t.empid,t.leaderid,t.empid as id from syempdata t  where  t.leaderid='"+id+"'";
	    List<Map> l=service.findBySql(sql);
	    Long num;
	    //查询是否有下级，设置state
	    for(Map m :l){
	    num=service.countBySql("select count(*) from syempdata t where t.leaderid='"+m.get("EMPID")+"'");
	    if(num>0){
	    		 m.put("state", "closed");
	    	 }else{
	    		 m.put("state", "open");
	    	 }
	    }
	    writeJson(l);
	}
	
	/**
	 * 填写绩效之前，完善基本信息
	 */
	public void doNotNeedSecurity_updateComplete(){
		((EmpDataServiceI)service).updateComplete(orgId,depId,posId,leaderId,empId);
		Json j=new Json();
		j.setSuccess(true);
		writeJson(j);
	}
	
}
