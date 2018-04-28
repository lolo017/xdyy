package aisino.reportform.service.base.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.dao.base.BaseDaoI;
import aisino.reportform.model.base.EmpDegree;
import aisino.reportform.model.base.EmpFamily;
import aisino.reportform.model.base.EmpTitle;
import aisino.reportform.model.base.SyEmpData;
import aisino.reportform.model.base.Syuser;
import aisino.reportform.service.base.EmpDataServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.MD5Util;
@Service
public class EmpDataServiceImpl extends BaseServiceImpl<SyEmpData>implements EmpDataServiceI {
	@Autowired
	private BaseDaoI<EmpDegree> empDegreeDao;
	@Autowired
	private BaseDaoI<EmpFamily> empFamilyDao;
	@Autowired
	private BaseDaoI<EmpTitle> empTitleDao;
	@Autowired
	private BaseDaoI<Syuser> userDao;
	
	@Override
	public void saveEmpData(List<EmpDegree> empDegreeList,List<EmpFamily> empFamilyList,List<EmpTitle> empTitleList,SyEmpData empData){
	
		if(null != empDegreeList && !empDegreeList.isEmpty() ){
		for (EmpDegree empDegree :empDegreeList){
			empDegree.setSyEmpData(empData);
		}
		Set empDegreeSet=new HashSet(empDegreeList);
		empData.setEmpDegrees(empDegreeSet);
		}
		
		if(null != empTitleList && !empTitleList.isEmpty() ){
		for (EmpTitle empTitle :empTitleList){
			empTitle.setSyEmpData(empData);
		}
		Set empTitleSet=new HashSet(empTitleList);
		empData.setEmpTitles(empTitleSet);
		}
		
		if(null != empFamilyList && !empFamilyList.isEmpty() ){
		for (EmpFamily empFamily :empFamilyList){
			empFamily.setSyEmpData(empData);
		}
		Set empFamilySet=new HashSet(empFamilyList);
		empData.setEmpFamilys(empFamilySet);
		}
		save(empData);
		
		String sql="select s.org_id from sys_org s where s.org_id2='"+empData.getOrgOrganization().getId()+"'";
		String orgcode=getBySql(sql);
		Syuser user=new Syuser();
		user.setEmpId(empData.getEmpId());
		user.setIsLeave("0");
		user.setIsPwd(0);
		user.setLoginname(empData.getEmpId());
		user.setName(empData.getName());
		user.setOrgcode(orgcode);
		user.setPwd(MD5Util.md5("123456"));
		user.setSex(empData.getSex().toString());
		user.setUserdpid(empData.getDepOrganization().getId());
		user.setUserorgid(empData.getOrgOrganization().getId());
		userDao.save(user);
//		String id=UUID.randomUUID().toString();
//		String sql="insert into syuser('"+id+"',null,)";

	}
	@Override
	public void updateEmpData(List<EmpDegree> empDegreeList,List<EmpFamily> empFamilyList,List<EmpTitle> empTitleList,SyEmpData empData){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("id", empData.getEmpId());
		String degreeHql="delete from EmpDegree t where t.syEmpData.id=:id";
		String titleHql="delete from EmpTitle t where t.syEmpData.id=:id";
		String familyHql="delete from EmpFamily t where t.syEmpData.id=:id";
		empDegreeDao.executeHql(degreeHql, params);
		empFamilyDao.executeHql(familyHql, params);
		empTitleDao.executeHql(titleHql, params);
		if(null != empDegreeList && !empDegreeList.isEmpty() ){
		for(EmpDegree empDegree: empDegreeList){
			empDegree.setSyEmpData(empData);
			empDegreeDao.save(empDegree);
		}
		}
		if(null != empFamilyList && !empFamilyList.isEmpty() ){
		for(EmpFamily empFamily: empFamilyList){
			empFamily.setSyEmpData(empData);
			empFamilyDao.save(empFamily);
		}
		}
		if(null != empTitleList && !empTitleList.isEmpty() ){
		for(EmpTitle empTitle: empTitleList){
			empTitle.setSyEmpData(empData);
			empTitleDao.save(empTitle);
		}
		}
		update(empData);
		String sql="select s.org_id from sys_org s where s.org_id2='"+empData.getOrgOrganization().getId()+"'";
		String orgcode=getBySql(sql);
		String hql=" from Syuser as p where p.empId=:empId ";
		Map<String, Object> paramss=new HashMap<String, Object>();
		paramss.put("empId", empData.getEmpId());
		Syuser user=userDao.getByHql(hql,paramss);
		user.setName(empData.getName());
		user.setOrgcode(orgcode);
		user.setSex(empData.getSex().toString());
		user.setUserdpid(empData.getDepOrganization().getId());
		user.setUserorgid(empData.getOrgOrganization().getId());
	}
	@Override
	public void updateComplete(String orgId,String depId,String posId,String leaderId,String empId){
		String sql="update syempdata t set t.orgid='"+orgId+"',t.dpid='"+depId+"',t.posId='"+posId+"',t.leaderid='"+leaderId+"' where t.empid='"+empId+"'";
	    executeSql(sql);
	}

}
