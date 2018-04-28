package aisino.reportform.service.base.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.dao.base.BaseDaoI;
import aisino.reportform.model.base.JxBm;
import aisino.reportform.model.base.JxZj;
import aisino.reportform.model.base.Performance;
import aisino.reportform.model.base.SyEmpData;
import aisino.reportform.service.base.PerformanceServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.BeanUtils;
import aisino.reportform.util.base.DateUtil;
@Service
public class PerformanceServiceImpl extends BaseServiceImpl<Performance> implements PerformanceServiceI{
	@Autowired
	private BaseDaoI<JxZj> jxzjDao;
	@Autowired
	private BaseDaoI<JxBm> jxbmDao;
	@Override
	public void savePerformance(Performance p,String grIds,String grfs,String bmIds,String bmfs){
		save(p);
		JxZj jxzj ;
		JxBm jxbm;
		if(grIds!=null&&grIds.length()>0){
			String []grIdsArray=grIds.split(",");
			String []grfsArray=grfs.split(",");
		for(int i=0;i<grIdsArray.length;i++){
			jxzj=jxzjDao.getById(JxZj.class, grIdsArray[i]);
			jxzj.setZpfz(new BigDecimal(grfsArray[i]));
			jxzjDao.update(jxzj);
		}
		}
		if(bmIds!=null&&bmIds.length()>0){
			String []bmIdsArray=bmIds.split(",");
			String []bmfsArray=bmfs.split(",");
		for(int j=0;j<bmIdsArray.length;j++){
			jxbm=jxbmDao.getById(JxBm.class, bmIdsArray[j]);
			jxbm.setZpfz(new BigDecimal(bmfsArray[j]));
			jxbmDao.update(jxbm);
		}
		}
		
	}
	@Override
	public void savePerformance(Performance p,String zbmc_gr_sy,String zbqz_gr_sy,String zpfz_gr_sy,String zbmc_bm_sy,
			String zbqz_bm_sy,String zpfz_bm_sy,String zbmc_gr_by,String zbqz_gr_by,String zbmc_bm_by,String zbqz_bm_by,
			String jxlb_gr_sy,String jxlb_gr_by,String jxlb_bm_sy,String jxlb_bm_by){
		save(p);
		String date=ServletActionContext.getRequest().getParameter("data.perDate");
		String empId=ServletActionContext.getRequest().getParameter("empId");
		String sql1="delete from JX_BM t where t.empdataid='"+empId+"' and t.zbcjsj='"+date.substring(0, 7)+"'";
		String sql2="delete from JX_ZJ t where t.empdataid='"+empId+"' and t.zbcjsj='"+date.substring(0, 7)+"'";
		executeSql(sql1);
		executeSql(sql2);
		List<JxZj> jxzj =new ArrayList<JxZj>();
		List<JxBm> jxbm=new ArrayList<JxBm>();
		//保存上月个人指标
		if(zbmc_gr_sy!=null&&zbmc_gr_sy.length()>0
			&&zbqz_gr_sy!=null&&zbqz_gr_sy.length()>0
			&&zpfz_gr_sy!=null&&zpfz_gr_sy.length()>0
			&&jxlb_gr_sy!=null&&jxlb_gr_sy.length()>0)
		{
			String[] a=zbmc_gr_sy.split(",");
			String[] b=zbqz_gr_sy.split(",");
			String[] c=zpfz_gr_sy.split(",");
			String[] d = jxlb_gr_sy.split(",");
			for(int i=0;i<a.length;i++){
				JxZj j=new JxZj();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				j.setSyempdataByEmpdataid(emp);
				j.setZbcjsj(date.substring(0, 7));
				j.setZbmc(a[i]);
				j.setZbqz(new BigDecimal(b[i]));
				j.setZpfz(new BigDecimal(c[i]));
				j.setZbfs(new BigDecimal(1));
				j.setJxlb(d[i]);
				jxzj.add(j);
			}
			
		}
		
		//保存上月部门指标
		if(zbmc_bm_sy!=null&&zbmc_bm_sy.length()>0
				&&zbqz_bm_sy!=null&&zbqz_bm_sy.length()>0
				&&zpfz_bm_sy!=null&&zpfz_bm_sy.length()>0
				&&jxlb_bm_sy!=null&&jxlb_bm_sy.length()>0){
			String[] a=zbmc_bm_sy.split(",");
			String[] b=zbqz_bm_sy.split(",");
			String[] c=zpfz_bm_sy.split(",");
			String[] d = jxlb_bm_sy.split(",");
			for(int i=0;i<a.length;i++){
				System.out.println(1);
				JxBm jx=new JxBm();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				jx.setSyempdata(emp);
				jx.setZbcjsj(date.substring(0, 7));
				jx.setZpfz(new BigDecimal(c[i]));
				jx.setZbqz(new BigDecimal(b[i]));
				jx.setZbmc(a[i]);
				jx.setZbfs(new BigDecimal(1));
				jx.setJxlb(d[i]);
				jxbm.add(jx);
			}
		
		}
		//保存本月个人指标
		if(zbmc_gr_by!=null&&zbmc_gr_by.length()>0
				&&zbqz_gr_by!=null&&zbqz_gr_by.length()>0
				&&jxlb_gr_by!=null&&jxlb_gr_by.length()>0){
			String[] a=zbmc_gr_by.split(",");
			String[] b=zbqz_gr_by.split(",");
			String[] c = jxlb_gr_by.split(",");
			for(int i=0;i<a.length;i++){
				JxZj j1=new JxZj();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				j1.setSyempdataByEmpdataid(emp);
				j1.setZbcjsj(DateUtil.nextMonth(date.substring(0, 7)));
				j1.setZbmc(a[i]);
				j1.setZbqz(new BigDecimal(b[i]));
				j1.setZbfs(new BigDecimal(1));
				j1.setJxlb(c[i]);
				jxzj.add(j1);
			}
		}
		//保存本月部门指标
		if(zbmc_bm_by!=null&&zbmc_bm_by.length()>0
				&&zbqz_bm_by!=null&&zbqz_bm_by.length()>0
				&&jxlb_bm_by!=null&&jxlb_bm_by.length()>0){
			String[] a=zbmc_bm_by.split(",");
			String[] b=zbqz_bm_by.split(",");
			String[] c = jxlb_bm_by.split(",");
			for(int i=0;i<a.length;i++){
				JxBm jx1=new JxBm();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				jx1.setSyempdata(emp);
				jx1.setZbcjsj(DateUtil.nextMonth(date.substring(0, 7)));
				jx1.setZbmc(a[i]);
				jx1.setZbqz(new BigDecimal(b[i]));
				jx1.setZbfs(new BigDecimal(1));
				jx1.setJxlb(c[i]);
				jxbm.add(jx1);
			}
		}
		jxzjDao.saveList(jxzj);
		jxbmDao.saveList(jxbm);
	}
	
	@Override
	public void updatePerformance(Performance p,String grIds,String grfs,String grldfs,String bmIds,String bmfs,String bmldfs){
		Performance t = getById(p.getId());
		BeanUtils.copyNotNullProperties(p, t, new String[] { "createdatetime" });
		update(t);
		JxZj jxzj ;
		JxBm jxbm;
		if(grIds!=null&&grIds.length()>0){
			String []grIdsArray=grIds.split(",");
			String []grfsArray=grfs.split(",");
            if(grldfs!=null&&grldfs.length()>0){
            	String []grldfsArray=grldfs.split(",");
            	for(int i=0;i<grIdsArray.length;i++){
        			jxzj=jxzjDao.getById(JxZj.class, grIdsArray[i]);
        			jxzj.setLdpf(new BigDecimal(grldfsArray[i]));
        			jxzj.setZpfz(new BigDecimal(grfsArray[i]));
        			jxzjDao.update(jxzj);
        		}
			}else{
				for(int i=0;i<grIdsArray.length;i++){
					jxzj=jxzjDao.getById(JxZj.class, grIdsArray[i]);
					jxzj.setZpfz(new BigDecimal(grfsArray[i]));
					jxzjDao.update(jxzj);
				}
			}
		
		}
		if(bmIds!=null&&bmIds.length()>0){
			String []bmIdsArray=bmIds.split(",");
			String []bmfsArray=bmfs.split(",");
			if(bmldfs!=null&&bmldfs.length()>0){
				String []bmldfsArray=bmldfs.split(",");
				for(int j=0;j<bmIdsArray.length;j++){
					jxbm=jxbmDao.getById(JxBm.class, bmIdsArray[j]);
					jxbm.setZpfz(new BigDecimal(bmfsArray[j]));
					jxbm.setLdpf(new BigDecimal(bmldfsArray[j]));
					jxbmDao.update(jxbm);
				}
			}else{
		for(int j=0;j<bmIdsArray.length;j++){
			jxbm=jxbmDao.getById(JxBm.class, bmIdsArray[j]);
			jxbm.setZpfz(new BigDecimal(bmfsArray[j]));
			jxbmDao.update(jxbm);
		}
			}
		}
	}
	@Override
	public void updatePerformance(Performance p,String zbmc_gr_sy,String zbqz_gr_sy,String zpfz_gr_sy,
			String ldpf_gr_sy,String zbmc_bm_sy,String zbqz_bm_sy,String zpfz_bm_sy,String ldpf_bm_sy,
			String zbmc_gr_by,String zbqz_gr_by,String zbmc_bm_by,String zbqz_bm_by,String jxlb_gr_sy,
			String jxlb_gr_by,String jxlb_bm_sy,String jxlb_bm_by){
		Performance t = getById(p.getId());
		BeanUtils.copyNotNullProperties(p, t, new String[] { "createdatetime" });
		update(t);
		String date=ServletActionContext.getRequest().getParameter("data.perDate");
		String empId=ServletActionContext.getRequest().getParameter("empId");
		String sql1="delete from JX_BM t where t.empdataid='"+empId+"' and (t.zbcjsj='"+date.substring(0, 7)+"' or  t.zbcjsj='"+DateUtil.nextMonth(date.substring(0, 7))+"')";
		String sql2="delete from JX_ZJ t where t.empdataid='"+empId+"' and (t.zbcjsj='"+date.substring(0, 7)+"' or  t.zbcjsj='"+DateUtil.nextMonth(date.substring(0, 7))+"')";
		executeSql(sql1);
		executeSql(sql2);
		List<JxZj> jxzj =new ArrayList<JxZj>();
		List<JxBm> jxbm=new ArrayList<JxBm>();
		//保存上月个人指标
		if(zbmc_gr_sy!=null&&zbmc_gr_sy.length()>0
			&&zbqz_gr_sy!=null&&zbqz_gr_sy.length()>0
			&&zpfz_gr_sy!=null&&zpfz_gr_sy.length()>0
			&&ldpf_gr_sy!=null&&ldpf_gr_sy.length()>0
			&&jxlb_gr_sy!=null&&jxlb_gr_sy.length()>0)
		{
			String[] a=zbmc_gr_sy.split(",");
			String[] b=zbqz_gr_sy.split(",");
			String[] c=zpfz_gr_sy.split(",");
			String[] d=ldpf_gr_sy.split(",");
			String[] ee = jxlb_gr_sy.split(",");
			for(int i=0;i<a.length;i++){
				JxZj j=new JxZj();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				j.setSyempdataByEmpdataid(emp);
				j.setZbcjsj(date.substring(0, 7));
				j.setZbmc(a[i]);
				j.setZbqz(new BigDecimal(b[i]));
				j.setZpfz(new BigDecimal(c[i]));
				j.setZbfs(new BigDecimal(1));
				j.setJxlb(ee[i]);
				try {
					j.setLdpf(new BigDecimal(d[i]));
					} catch (ArrayIndexOutOfBoundsException e) {
						j.setLdpf(null);
					}
				jxzj.add(j);
			}
			
		}else if(zbmc_gr_sy!=null&&zbmc_gr_sy.length()>0
				&&zbqz_gr_sy!=null&&zbqz_gr_sy.length()>0
				&&zpfz_gr_sy!=null&&zpfz_gr_sy.length()>0
				&&jxlb_gr_sy!=null&&jxlb_gr_sy.length()>0){
			String[] a=zbmc_gr_sy.split(",");
			String[] b=zbqz_gr_sy.split(",");
			String[] c=zpfz_gr_sy.split(",");
			String[] d = jxlb_gr_sy.split(",");
			for(int i=0;i<a.length;i++){
				JxZj j=new JxZj();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				j.setSyempdataByEmpdataid(emp);
				j.setZbcjsj(date.substring(0, 7));
				j.setZbmc(a[i]);
				j.setZbqz(new BigDecimal(b[i]));
				j.setZpfz(new BigDecimal(c[i]));
				j.setZbfs(new BigDecimal(1));
				j.setJxlb(d[i]);
				jxzj.add(j);
			}
		}
		
		//保存上月部门指标
		if(zbmc_bm_sy!=null&&zbmc_bm_sy.length()>0
				&&zbqz_bm_sy!=null&&zbqz_bm_sy.length()>0
				&&zpfz_bm_sy!=null&&zpfz_bm_sy.length()>0
				&&ldpf_bm_sy!=null&&ldpf_bm_sy.length()>0
				&&jxlb_bm_sy!=null&&jxlb_bm_sy.length()>0){
			String[] a=zbmc_bm_sy.split(",");
			String[] b=zbqz_bm_sy.split(",");
			String[] c=zpfz_bm_sy.split(",");
			String[] d=ldpf_bm_sy.split(",");
			String[] ee = jxlb_bm_sy.split(",");
			for(int i=0;i<a.length;i++){
				JxBm jx=new JxBm();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				jx.setSyempdata(emp);
				jx.setZbcjsj(date.substring(0, 7));
				jx.setZpfz(new BigDecimal(c[i]));
				jx.setZbqz(new BigDecimal(b[i]));
				jx.setZbmc(a[i]);
				jx.setZbfs(new BigDecimal(1));
				jx.setJxlb(ee[i]);
				try {
					jx.setLdpf(new BigDecimal(d[i]));
					} catch (ArrayIndexOutOfBoundsException e) {
						jx.setLdpf(null);
					}
				
				jxbm.add(jx);
			}
		
		}else if(zbmc_bm_sy!=null&&zbmc_bm_sy.length()>0
				&&zbqz_bm_sy!=null&&zbqz_bm_sy.length()>0
				&&zpfz_bm_sy!=null&&zpfz_bm_sy.length()>0
				&&jxlb_bm_sy!=null&&jxlb_bm_sy.length()>0){
			String[] a=zbmc_bm_sy.split(",");
			String[] b=zbqz_bm_sy.split(",");
			String[] c=zpfz_bm_sy.split(",");
			String[] d = jxlb_bm_sy.split(",");
			for(int i=0;i<a.length;i++){
				JxBm jx=new JxBm();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				jx.setSyempdata(emp);
				jx.setZbcjsj(date.substring(0, 7));
				jx.setZpfz(new BigDecimal(c[i]));
				jx.setZbqz(new BigDecimal(b[i]));
				jx.setZbmc(a[i]);
				jx.setZbfs(new BigDecimal(1));
				jx.setJxlb(d[i]);
				jxbm.add(jx);
			}
		}
		//保存本月个人指标
		if(zbmc_gr_by!=null&&zbmc_gr_by.length()>0
				&&zbqz_gr_by!=null&&zbqz_gr_by.length()>0
				&&jxlb_gr_by!=null&&jxlb_gr_by.length()>0){
			String[] a=zbmc_gr_by.split(",");
			String[] b=zbqz_gr_by.split(",");
			String[] c=jxlb_gr_by.split(",");
			for(int i=0;i<a.length;i++){
				JxZj j1=new JxZj();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				j1.setSyempdataByEmpdataid(emp);
				j1.setZbcjsj(DateUtil.nextMonth(date.substring(0, 7)));
				j1.setZbmc(a[i]);
				j1.setZbqz(new BigDecimal(b[i]));
				j1.setZbfs(new BigDecimal(1));
				j1.setJxlb(c[i]);
				jxzj.add(j1);
			}
		}
		//保存本月部门指标
		if(zbmc_bm_by!=null&&zbmc_bm_by.length()>0
				&&zbqz_bm_by!=null&&zbqz_bm_by.length()>0
				&&jxlb_bm_by!=null&&jxlb_bm_by.length()>0){
			String[] a=zbmc_bm_by.split(",");
			String[] b=zbqz_bm_by.split(",");
			String[] c = jxlb_bm_by.split(",");
			for(int i=0;i<a.length;i++){
				JxBm jx1=new JxBm();
				SyEmpData emp=new SyEmpData();
				emp.setEmpId(empId);
				jx1.setSyempdata(emp);
				jx1.setZbcjsj(DateUtil.nextMonth(date.substring(0, 7)));
				jx1.setZbmc(a[i]);
				jx1.setZbqz(new BigDecimal(b[i]));
				jx1.setZbfs(new BigDecimal(1));
				jx1.setJxlb(c[i]);
				jxbm.add(jx1);
			}
		}
		jxzjDao.saveList(jxzj);
		jxbmDao.saveList(jxbm);
	}

	@Override
	public Long countPerformance(String date,String userid){
		String sql="select count(*) from performance t where  to_char(t.per_date,'yyyy-mm')='"+date+"' and t.usersid='"+userid+"'";
		return countBySql(sql);
	}
	@Override
	public void deletePerformance(String id){
		Performance t=getById(id);
		delete(t);
		String date=ServletActionContext.getRequest().getParameter("date");
		String empId=ServletActionContext.getRequest().getParameter("empId");
//		String sql1="delete from JX_BM t where t.empdataid='"+empId+"' and (t.zbcjsj='"+date.substring(0, 7)+"' or  t.zbcjsj='"+DateUtil.nextMonth(date.substring(0, 7))+"')";
//		String sql2="delete from JX_ZJ t where t.empdataid='"+empId+"' and (t.zbcjsj='"+date.substring(0, 7)+"' or  t.zbcjsj='"+DateUtil.nextMonth(date.substring(0, 7))+"')";
		String sql1="delete from JX_BM t where t.empdataid='"+empId+"' and ( t.zbcjsj='"+DateUtil.nextMonth(date.substring(0, 7))+"')";
		String sql2="delete from JX_ZJ t where t.empdataid='"+empId+"' and ( t.zbcjsj='"+DateUtil.nextMonth(date.substring(0, 7))+"')";
		executeSql(sql1);
		executeSql(sql2);
	}
	
}
