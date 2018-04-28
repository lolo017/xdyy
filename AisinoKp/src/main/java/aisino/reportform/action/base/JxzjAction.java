package aisino.reportform.action.base;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.JxZj;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.JxzjServiceI;
import aisino.reportform.util.base.DateUtil;
import aisino.reportform.util.base.HqlFilter;
@Namespace("/base")
@Action
public class JxzjAction extends BaseAction<JxZj>{
	@Autowired
	public void setService(JxzjServiceI service) {
		this.service = service;
	}
	
	private String syempdataByEmpdataid;
	private String zbcjsj;
	
	
	public String getZbcjsj() {
		return zbcjsj;
	}


	public void setZbcjsj(String zbcjsj) {
		this.zbcjsj = zbcjsj;
	}


	public String getSyempdataByEmpdataid() {
		return syempdataByEmpdataid;
	}


	public void setSyempdataByEmpdataid(String syempdataByEmpdataid) {
		this.syempdataByEmpdataid = syempdataByEmpdataid;
	}


	@Override
	public void grid(){
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		
		hqlFilter.addFilter("QUERY_t#syempdataByEmpdataid.empId_S_EQ", syempdataByEmpdataid);
		System.out.println("zbcjsj:"+zbcjsj);
		if(zbcjsj!=null){
			hqlFilter.addFilter("QUERY_t#zbcjsj_S_EQ", zbcjsj);
		}
		grid.setTotal(((JxzjServiceI)service).countByFilter(hqlFilter));
		List<JxZj> list=((JxzjServiceI)service).findByFilter(hqlFilter, page, rows);
		grid.setRows(list);
		writeJson(grid);
	}
	
	
	public void grid2(){
		Json j = new Json();
	    HqlFilter hqlFilter = new HqlFilter(getRequest());
	    HqlFilter hqlFilter2 = new HqlFilter(getRequest());

	    hqlFilter.addFilter("QUERY_t#syempdataByEmpdataid.empId_S_EQ", this.id);
	    hqlFilter2.addFilter("QUERY_t#syempdataByEmpdataid.empId_S_EQ", this.id);
	    if (this.zbcjsj != null) {
	      hqlFilter.addFilter("QUERY_t#zbcjsj_S_EQ", this.zbcjsj);
	      hqlFilter2.addFilter("QUERY_t#zbcjsj_S_EQ", DateUtil.nextMonth(this.zbcjsj));
	    }

	    List list = ((JxzjServiceI)this.service).findByFilter(hqlFilter);

	    List list2 = ((JxzjServiceI)this.service).findByFilter(hqlFilter2);

	    Map map = new HashMap();
	    map.put("sy", list);
	    map.put("by", list2);
	    writeJson(map);
		//Grid grid = new Grid();
		//Json j=new Json();
//		HqlFilter hqlFilter = new HqlFilter(getRequest());
//		HqlFilter hqlFilter2 = new HqlFilter(getRequest());
//		
//		hqlFilter.addFilter("QUERY_t#syempdataByEmpdataid.empId_S_EQ", id);
//		hqlFilter2.addFilter("QUERY_t#syempdataByEmpdataid.empId_S_EQ", id);
//		if(zbcjsj!=null){
//			hqlFilter.addFilter("QUERY_t#zbcjsj_S_EQ", zbcjsj);
//			hqlFilter2.addFilter("QUERY_t#zbcjsj_S_EQ", DateUtil.nextMonth(zbcjsj));
//		}
//		Map<String, Object> params1= new HashMap<String, Object>();
//		params1.put("empId", id);
//		params1.put("zbcjsj", zbcjsj);
//		//上月指标
//		String hql1="select new JxZj(id,year,month,zpfz, ldpf, kpf, zbqz,zbmc, zbfs, zjdfsj, lddfsj,zbcjsj) from JxZj t where t.syempdataByEmpdataid.empId=:empId and t.zbcjsj=:zbcjsj";
//		List<JxZj> list=((JxzjServiceI)service).find(hql1,params1);
//		//List<JxZj> list=((JxzjServiceI)service).findByFilter(hqlFilter);
//		//本月指标
//		Map<String, Object> params2= new HashMap<String, Object>();
//		params2.put("empId", id);
//		params2.put("nextMonth", DateUtil.nextMonth(zbcjsj));
//		
//		String hql2="select new JxZj(id,year,month,zpfz, ldpf, kpf, zbqz,zbmc, zbfs, zjdfsj, lddfsj,zbcjsj) from JxZj t where t.syempdataByEmpdataid.empId=:empId and t.zbcjsj=:nextMonth";
//		List<JxZj> list2=((JxzjServiceI)service).find(hql2,params2);
//		//List<JxZj> list2=((JxzjServiceI)service).findByFilter(hqlFilter2);
//
//		Map map=new HashMap();
//		map.put("sy", list);
//		map.put("by", list2);
//		writeJson(map);
	}
	
	public void upd() throws UnsupportedEncodingException{
		System.out.println("进来了");
		this.getRequest().setCharacterEncoding("utf-8");
		String ids =this.getRequest().getParameter("ids");
		String zpfz =this.getRequest().getParameter("zpfz");
		String ldpf =this.getRequest().getParameter("ldpf");
		System.out.println("ids:"+ids);
		System.out.println("zpfz:"+zpfz);
		System.out.println("ldpf:"+ldpf);
		String []ldpfs=null;
		
		String []zpfzs=zpfz.split(",");
		String []idss=ids.split(",");
		if(ldpf!=null){
			ldpfs=ldpf.split(",");
		};
		
		System.out.println("进来了1");
		System.out.println("长度:"+idss.length);
		for (int i = 0; i < idss.length; i++) {
			System.out.println("进来了2");
			System.out.println("次数:"+i);
			JxZj jxZj=new JxZj();
			jxZj.setId(idss[i]);
			BigDecimal fz=new BigDecimal(zpfzs[i]); 
			jxZj.setZpfz(fz);
			service.executeHql(" update  JxZj  set zpfz='"+fz+"' where id = '"+idss[i]+"'");	
		}
		if(ldpf!=null){
			for (int j = 0; j < idss.length; j++) {
				if(ldpfs.length>0){
					System.out.println("进来了3");
					String hql="update JxZj  set ldpf=:ldpf where id=:id";
					Map<String, Object> params=new HashMap<String, Object>();
					BigDecimal pf=new BigDecimal(ldpfs[j]); 
					params.put("ldpf", pf);
					params.put("id", idss[j]);
					service.executeHql(hql, params);
				}
			}
		}
		//if(){}
		Json json = new Json();
		json.setSuccess(true);
		json.setMsg("打分成功！");
		writeJson(json);
		//System.out.println(zpfzs.length);
	
	}
	
	
	@Override
	public void find(){
		
		String zbcjsjs=zbcjsj;
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		System.out.println("syempdataByEmpdataid:"+syempdataByEmpdataid);
		hqlFilter.addFilter("QUERY_t#syempdataByEmpdataid.empId_S_EQ", syempdataByEmpdataid);
		hqlFilter.addFilter("QUERY_t#zbcjsj_S_EQ", zbcjsjs);
		
		grid.setTotal(((JxzjServiceI)service).countByFilter(hqlFilter));
		List<JxZj> list=((JxzjServiceI)service).findByFilter(hqlFilter, page, rows);
		System.out.println(list);
		grid.setRows(list);
		writeJson(grid);
	
		
	}
	
	
	
	
	
}
