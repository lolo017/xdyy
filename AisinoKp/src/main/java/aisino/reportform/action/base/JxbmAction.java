package aisino.reportform.action.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.JxBm;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.JxbmServiceI;
import aisino.reportform.util.base.DateUtil;
import aisino.reportform.util.base.HqlFilter;

@Namespace("/base")
@Action
public class JxbmAction extends BaseAction<JxBm>{
	@Autowired
	public void setService(JxbmServiceI service) {
		this.service = service;
	}
	
	private String syempdata;
	private String zbcjsj;
	
	public String getSyempdata() {
		return syempdata;
	}

	public void setSyempdata(String syempdata) {
		this.syempdata = syempdata;
	}

	public String getZbcjsj() {
		return zbcjsj;
	}

	public void setZbcjsj(String zbcjsj) {
		this.zbcjsj = zbcjsj;
	}

	public void grid2(){
		//Grid grid = new Grid();
		Json j=new Json();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		HqlFilter hqlFilter2 = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#syempdata.empId_S_EQ", id);
		hqlFilter2.addFilter("QUERY_t#syempdata.empId_S_EQ", id);
		if(zbcjsj!=null){
			hqlFilter.addFilter("QUERY_t#zbcjsj_S_EQ", zbcjsj);
			hqlFilter2.addFilter("QUERY_t#zbcjsj_S_EQ", DateUtil.nextMonth(zbcjsj));
		}
		Map map=new HashMap();
		//上月部门指标
		map.put("sy", ((JxbmServiceI)service).findByFilter(hqlFilter));
		//本月部门指标
		map.put("by", ((JxbmServiceI)service).findByFilter(hqlFilter2));
		writeJson(map);
	}
	
	public void gridDistinct(){
		Grid grid = new Grid();
		
		StringBuffer sql=new StringBuffer("  select t.id,t.zbcjsj,t.ogrid,t.empdataid,t2.name names,t1.name nam from JX_BM t left join syorganization t1 on t.ogrid=t1.id left join syempdata t2 on t.empdataid=t2.empid where t.id in ( select max(id) from JX_BM ");
		StringBuffer sqlcount=new StringBuffer(" select count(*) from JX_BM where id in ( select max(id) from JX_BM");
		
		if(zbcjsj!=null){
			System.out.println("进入了时间查询");
			sql=sql.append("where ").append("zbcjsj='").append(zbcjsj).append("'");
			sqlcount=sqlcount.append("where ").append("zbcjsj='").append(zbcjsj).append("'");
		}
		if(syempdata!=null){
			System.out.println("进度了工号查询");
			sql=sql.append("where ").append("syempdata='").append(syempdata).append("'");
			sqlcount=sqlcount.append("where ").append("syempdata='").append(syempdata).append("'");
		}
		sql=sql.append(" group by zbcjsj,empdataid  ) order by zbcjsj,empdataid desc");
		sqlcount=sqlcount.append(" group by zbcjsj,empdataid  ) order by zbcjsj,empdataid desc");
			grid.setTotal(service.countBySql(sqlcount.toString()));
			grid.setRows(service.findBySql(sql.toString(), page, rows));
			System.out.println(grid);
			writeJson(grid);
	}
	
	
	
	public void gridDistinct2(){
		Grid grid = new Grid();
		
		StringBuffer sql=new StringBuffer(" select * from JX_BM  left join syorganization t1on t.ogrid=t1.id left join syempdata t2 on t.empdataid=t2.empid  where id in ( select max(id) from JX_BM");
		StringBuffer sqlcount=new StringBuffer(" select count(*) from JX_BM where id in ( select max(id) from JX_BM");
		
		if(zbcjsj!=null){
			System.out.println("进入了时间查询");
			sql=sql.append("where ").append("zbcjsj='").append(zbcjsj).append("'");
			sqlcount=sqlcount.append("where ").append("zbcjsj='").append(zbcjsj).append("'");
		}
		if(syempdata!=null){
			System.out.println("进度了工号查询");
			sql=sql.append("where ").append("syempdata='").append(syempdata).append("'");
			sqlcount=sqlcount.append("where ").append("syempdata='").append(syempdata).append("'");
		}
		sql=sql.append(" group by zbcjsj,empdataid  ) order by zbcjsj,empdataid desc");
		sqlcount=sqlcount.append(" group by zbcjsj,empdataid  ) order by zbcjsj,empdataid desc");
			grid.setTotal(service.countBySql(sqlcount.toString()));
			grid.setRows(service.findBySql(sql.toString(), page, rows));
			System.out.println(grid);
			writeJson(grid);
	}
}
