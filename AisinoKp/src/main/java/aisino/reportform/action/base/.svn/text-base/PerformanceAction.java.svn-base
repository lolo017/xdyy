package aisino.reportform.action.base;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Performance;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.EmpDataServiceI;
import aisino.reportform.service.base.PerformanceServiceI;
import aisino.reportform.service.base.SyorganizationServiceI;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.SecurityUtil;

/**
 * 绩效管理Action
 * 
 * @author 廖宸宇
 * @date 2015-3-20
 */
@Namespace("/base")
@Action
public class PerformanceAction extends BaseAction<Performance>{
	private String empId;
	private String perDate;
	private String empName;
	private String grIds;
	private String bmIds;
	private String grfs;//个人分数
	private String bmfs;//部门分数
	private String grldfs;//个人领导评分
	private String bmldfs;//部门领导评分
	private String zbmc_gr_sy;//上月个人指标名称
	private String zbqz_gr_sy;//上月个人指标权重
	private String zpfz_gr_sy;//上月个人自评分值
	private String zbmc_bm_sy;//上月部门指标名称
	private String zbqz_bm_sy;//上月部门指标名称
	private String zpfz_bm_sy;//上月部门自评分值
	private String zbmc_gr_by;//本月个人指标名称
	private String zbqz_gr_by;//本月个人指标权重
	private String zbmc_bm_by;//本月部门指标名称
	private String zbqz_bm_by;//本月部门指标权重
	private String ldpf_bm_sy;//上月部门领导评分
	private String ldpf_gr_sy;//上月个人领导评分
	private String jxlb_gr_sy;//上月个人绩效类别
	private String jxlb_gr_by;//本月个人绩效类别
	private String jxlb_bm_sy;//上月部门绩效类别
	private String jxlb_bm_by;//本月部门绩效类别
	
	@Autowired
	private SyorganizationServiceI organizationService;
	@Autowired
	private EmpDataServiceI empDataService;
	
	
	

	public String getLdpf_bm_sy() {
		return ldpf_bm_sy;
	}

	public void setLdpf_bm_sy(String ldpf_bm_sy) {
		this.ldpf_bm_sy = ldpf_bm_sy;
	}

	public String getLdpf_gr_sy() {
		return ldpf_gr_sy;
	}

	public void setLdpf_gr_sy(String ldpf_gr_sy) {
		this.ldpf_gr_sy = ldpf_gr_sy;
	}

	public String getZbmc_gr_sy() {
		return zbmc_gr_sy;
	}

	public void setZbmc_gr_sy(String zbmc_gr_sy) {
		this.zbmc_gr_sy = zbmc_gr_sy;
	}

	public String getZbqz_gr_sy() {
		return zbqz_gr_sy;
	}

	public void setZbqz_gr_sy(String zbqz_gr_sy) {
		this.zbqz_gr_sy = zbqz_gr_sy;
	}

	public String getZpfz_gr_sy() {
		return zpfz_gr_sy;
	}

	public void setZpfz_gr_sy(String zpfz_gr_sy) {
		this.zpfz_gr_sy = zpfz_gr_sy;
	}

	public String getZbmc_bm_sy() {
		return zbmc_bm_sy;
	}

	public void setZbmc_bm_sy(String zbmc_bm_sy) {
		this.zbmc_bm_sy = zbmc_bm_sy;
	}

	public String getZbqz_bm_sy() {
		return zbqz_bm_sy;
	}

	public void setZbqz_bm_sy(String zbqz_bm_sy) {
		this.zbqz_bm_sy = zbqz_bm_sy;
	}

	public String getZpfz_bm_sy() {
		return zpfz_bm_sy;
	}

	public void setZpfz_bm_sy(String zpfz_bm_sy) {
		this.zpfz_bm_sy = zpfz_bm_sy;
	}

	public String getZbmc_gr_by() {
		return zbmc_gr_by;
	}

	public void setZbmc_gr_by(String zbmc_gr_by) {
		this.zbmc_gr_by = zbmc_gr_by;
	}

	public String getZbqz_gr_by() {
		return zbqz_gr_by;
	}

	public void setZbqz_gr_by(String zbqz_gr_by) {
		this.zbqz_gr_by = zbqz_gr_by;
	}

	public String getZbmc_bm_by() {
		return zbmc_bm_by;
	}

	public void setZbmc_bm_by(String zbmc_bm_by) {
		this.zbmc_bm_by = zbmc_bm_by;
	}

	public String getZbqz_bm_by() {
		return zbqz_bm_by;
	}

	public void setZbqz_bm_by(String zbqz_bm_by) {
		this.zbqz_bm_by = zbqz_bm_by;
	}

	public String getGrldfs() {
		return grldfs;
	}

	public void setGrldfs(String grldfs) {
		this.grldfs = grldfs;
	}

	public String getBmldfs() {
		return bmldfs;
	}

	public void setBmldfs(String bmldfs) {
		this.bmldfs = bmldfs;
	}

	public String getGrIds() {
		return grIds;
	}

	public void setGrIds(String grIds) {
		this.grIds = grIds;
	}

	public String getBmIds() {
		return bmIds;
	}

	public void setBmIds(String bmIds) {
		this.bmIds = bmIds;
	}

	public String getGrfs() {
		return grfs;
	}

	public void setGrfs(String grfs) {
		this.grfs = grfs;
	}

	public String getBmfs() {
		return bmfs;
	}

	public void setBmfs(String bmfs) {
		this.bmfs = bmfs;
	}

	public SyorganizationServiceI getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(SyorganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	public EmpDataServiceI getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataServiceI empDataService) {
		this.empDataService = empDataService;
	}

	public String getPerDate() {
		return perDate;
	}

	public void setPerDate(String perDate) {
		this.perDate = perDate;
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
	
	public String getJxlb_gr_sy() {
		return jxlb_gr_sy;
	}

	public void setJxlb_gr_sy(String jxlb_gr_sy) {
		this.jxlb_gr_sy = jxlb_gr_sy;
	}

	public String getJxlb_gr_by() {
		return jxlb_gr_by;
	}

	public void setJxlb_gr_by(String jxlb_gr_by) {
		this.jxlb_gr_by = jxlb_gr_by;
	}

	public String getJxlb_bm_sy() {
		return jxlb_bm_sy;
	}

	public void setJxlb_bm_sy(String jxlb_bm_sy) {
		this.jxlb_bm_sy = jxlb_bm_sy;
	}

	public String getJxlb_bm_by() {
		return jxlb_bm_by;
	}

	public void setJxlb_bm_by(String jxlb_bm_by) {
		this.jxlb_bm_by = jxlb_bm_by;
	}

	@Autowired
	public void setService(PerformanceServiceI service) {
		this.service = service;
	}
	
	
	/**
	 *根据empid获取部门、岗位、上级 
	 **/
	public void doNotNeedSecurity_getEmpData(){
		String hql="from SyEmpData t where t.empId='"+id+"'";
		 writeJson(empDataService.getByHql(hql));
	}
	/**
	 * 查询所有绩效表
	 *
	 */
	@Override
	public void grid(){
		Grid grid = new Grid();
		
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("empId", empId);
		String hql="from Performance t where t.syuser.empId=:empId order by t.perDate desc";
		String countHql="select count(*) from Performance t where t.syuser.empId=:empId ";
		grid.setTotal(service.count(countHql,params));
		grid.setRows(service.find(hql,params,page, rows));
		writeJson(grid);
	}
	/**
	 * 一次性查询出所有上下级关系
	 *
	 */
	public void doNotNeedSecurity_leaderTreeGrid(){
		SecurityUtil securityUtil=new SecurityUtil(getSession());
		//判断是否具有绩效超级权限,不是的话查询自己和自己的下级
		if(securityUtil.havePermission("/base/performance/all")){
			StringBuffer sql=new StringBuffer("select t.empid,t.leaderid,t.name ,tt.name as dep,tt.id as depid from syempdata t left join syorganization tt on t.dpid=tt.id where t.orgid='");
			sql.append(id).append("'");
			//如果empid不为空，增加查询参数
			if(empId!=null&&empId.length()>0){
				sql=sql.append(" and t.empid='").append(empId).append("'");
			}
			//如果emp不为空，增加查询参数
			if(empName!=null&&empName.length()>0){
				sql=sql.append(" and t.name like '%").append(empName).append("%'");
			}
			writeJson(service.findBySql(sql.toString()));
		}else{
		SessionInfo sessionInfo = (SessionInfo) getSession()
				.getAttribute(ConfigUtil.getSessionInfoName());
		StringBuffer sql=new StringBuffer("select t.empid,t.name,t.leaderid,tt.name as dep,tt.id as depid from  (select sy.* from syempdata sy start with sy.empid= '");
		sql.append(sessionInfo.getUser().getEmpId()).append("' connect by prior sy.empid=sy.leaderid）t left join syorganization tt on t.dpid=tt.id where t.orgid='").append(id).append("'");
		//如果empid不为空，增加查询参数
		if(empId!=null&&empId.length()>0){
			sql=sql.append(" and t.empid='").append(empId).append("'");
		}
		//如果emp不为空，增加查询参数
		if(empName!=null&&empName.length()>0){
			sql=sql.append(" and t.name like '%").append(empName).append("%'");
		}
		writeJson(service.findBySql(sql.toString()));
		}
	}
	/**
	 * 展开上下级关系（已不用）
	 *
	 */
	public void doNotNeedSecurity_leaderTreeGridExpand(){
		String sql="select t.name,t.empid,t.leaderid,tt.name as dep from syempdata t left join  syorganization tt on t.dpid=tt.id where t.leaderid ="+empId;
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
	 * 获取orgtreegrid，用于控制查看哪些公司绩效
	 *
	 */
	public void doNotNeedSecurity_orgTreeGrid(){
		SessionInfo sessionInfo = (SessionInfo) getSession()
				.getAttribute(ConfigUtil.getSessionInfoName());
		String sql="select t.* from SYORGANIZATION t left join SYUSER_SYORGANIZATION tt on t.id=tt.syorganization_id left join SYUSER ttt on tt.syuser_id=ttt.id where ttt.id='"+sessionInfo.getUser().getId()+"' and t.levelid=1";
		writeJson(service.findBySql(sql));
	}
	/**
	 * 保存绩效、个人指标、部门指标
	 * 
	 */
	public void doNotNeedSecurity_savePerformance(){
		Json json = new Json();
		String date=ServletActionContext.getRequest().getParameter("data.perDate");
		String userid=ServletActionContext.getRequest().getParameter("data.syuser.id");
		Long l=((PerformanceServiceI)service).countPerformance(date.substring(0, 7), userid);
		if(l>0){
			json.setSuccess(false);
			json.setMsg("该月已填写过绩效，请使用编辑功能!");
		}else if (data != null) {
			//((PerformanceServiceI)service).savePerformance(data,grIds,grfs,bmIds,bmfs);
			((PerformanceServiceI)service).savePerformance(data, zbmc_gr_sy, zbqz_gr_sy, zpfz_gr_sy, zbmc_bm_sy, zbqz_bm_sy, zpfz_bm_sy, zbmc_gr_by, zbqz_gr_by, zbmc_bm_by, zbqz_bm_by,jxlb_gr_sy,
					jxlb_gr_by,jxlb_bm_sy,jxlb_bm_by);
			
			json.setSuccess(true);
			json.setMsg("新建成功！");
		}else{
			json.setSuccess(false);
			json.setMsg("新建失败！");
		}
		writeJson(json);
	}
	/**
	 * 保存绩效、个人指标、部门指标
	 * 
	 */
   public void doNotNeedSecurity_updatePerformance(){
	   Json json = new Json();
		if (data != null) {
			//((PerformanceServiceI)service).updatePerformance(data,grIds,grfs,grldfs,bmIds,bmfs,bmldfs);
			((PerformanceServiceI)service).updatePerformance(data, zbmc_gr_sy, zbqz_gr_sy, zpfz_gr_sy, ldpf_gr_sy, zbmc_bm_sy, zbqz_bm_sy, zpfz_bm_sy, ldpf_bm_sy, zbmc_gr_by, zbqz_gr_by, zbmc_bm_by, zbqz_bm_by,jxlb_gr_sy,jxlb_gr_by,jxlb_bm_sy,jxlb_bm_by);
			json.setSuccess(true);
			json.setMsg("更新成功！");
		}
		writeJson(json);
	}
 
   public void deletePerformance(){
	   Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			((PerformanceServiceI)service).deletePerformance(id);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
   }
	

}
