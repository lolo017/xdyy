package aisino.reportform.service.base.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.dao.base.BaseDaoI;
import aisino.reportform.model.base.Syorganization;
import aisino.reportform.model.base.Syrole;
import aisino.reportform.model.base.Syuser;
import aisino.reportform.service.base.SyuserServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.HqlFilter;

/**
 * 用户业务逻辑
 * 
 * @author 
 * 
 */
@Service
public class SyuserServiceImpl extends BaseServiceImpl<Syuser> implements SyuserServiceI {

	@Autowired
	private BaseDaoI<Syrole> roleDao;

	@Autowired
	private BaseDaoI<Syorganization> organizationDao;

	@Override
	public void grantRole(String id, String roleIds) {
		Syuser user = getById(id);
		if (user != null) {
			user.setSyroles(new HashSet<Syrole>());
			for (String roleId : roleIds.split(",")) {
				if (!StringUtils.isBlank(roleId)) {
					Syrole role = roleDao.getById(Syrole.class, roleId);
					if (role != null) {
						user.getSyroles().add(role);
					}
				}
			}
		}
	}

	@Override
	public void grantOrganization(String id, String organizationIds) {
		Syuser user = getById(id);
		if (user != null) {
			user.setSyorganizations(new HashSet<Syorganization>());
			for (String organizationId : organizationIds.split(",")) {
				if (!StringUtils.isBlank(organizationId)) {
					Syorganization organization = organizationDao.getById(Syorganization.class, organizationId);
					if (organization != null) {
						user.getSyorganizations().add(organization);
					}
				}
			}
		}
	}

	@Override
	public List<Long> userCreateDatetimeChart() {
		List<Long> l = new ArrayList<Long>();
		int k = 0;
		for (int i = 0; i < 12; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("a", k);
			params.put("b", k + 2);
			k = k + 2;
			l.add(count("select count(*) from Syuser t where HOUR(t.createdatetime)>=:a and HOUR(t.createdatetime)<:b", params));
		}
		return l;
	}

	@Override
	public Long countUserByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		String hql = "select count(*) from Syuser t join t.syroles role where role.id = :roleId";
		return count(hql, params);
	}

	@Override
	public Long countUserByNotRoleId() {
		String hql = "select count(*) from Syuser t left join t.syroles role where role.id is null";
		return count(hql);
	}

	@Override
	public List<Syuser> userTreeGrid(HqlFilter hqlFilter) {
		List<Syuser> l = new ArrayList<Syuser>();
		String hql = "select distinct t from Syuser t join t.syroles role join role.syusers user";
		List<Syuser> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_role);
		l = new ArrayList<Syuser>(new HashSet<Syuser>(l));// 去重
		return l;
	}
	@Override
	public void updateLeaveUser(String id){
		Syuser t =getById(id);
		String empId=t.getEmpId();
		t.setIsLeave("1");
		update(t);
		String sql1="update syempdata t set t.leaderid=null where t.leaderid='"+empId+"'";
		String sql2="insert into leave_emp(select tt.sex,tt.birthday,tt.hight,tt.blood,tt.mobile,tt.home,tt.indate,tt.address,tt.phone,tt.dpid,tt.political,tt.leaderid,tt.orgid,tt.posid,tt.empid,tt.name,sysdate from syempdata tt where tt.empid='"+empId+"' )";
	    String sql3="delete jx_bm t where t.empdataid='"+empId+"'";
	    String sql4="delete jx_zj t where t.empdataid='"+empId+"'";
	    String sql5="delete syempdata t where t.empid='"+empId+"'";
	    executeSql(sql1);
	    executeSql(sql2);
	    executeSql(sql3);
	    executeSql(sql4);
	    executeSql(sql5);
	}

}
