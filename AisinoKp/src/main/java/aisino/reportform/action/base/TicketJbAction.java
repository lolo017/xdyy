package aisino.reportform.action.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Syjb;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.TicketJbServiceI;
import aisino.reportform.service.base.TicketStoreServiceI;

@Namespace("/base")
@Action
public class TicketJbAction extends BaseAction<Syjb> {
	private String ksrq;
	private String jsrq;
	private String name;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	@Autowired
	public void setService(TicketJbServiceI service) {
		this.service = service;
	}
	
	@Autowired
	private TicketStoreServiceI ticketStoreService;
	
	public void doNotNeedSessionAndSecurity_query()
	{
		Grid grid = new Grid();
		String sql = "select * from T_JB t";
		String sql_count = "select count(1) from t_jb";
		sql+=" order by lrrq desc";
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	
	public void doNotNeedSessionAndSecurity_fallback()
	{
		Json json=new Json();
		Syjb syjb=service.getById(id);
		service.delete(syjb);
		String updatesql="update t_ticketstore set jbid=null where jbid='"+syjb.getId()+"'";
		ticketStoreService.executeSql(updatesql);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(id+"结报状态已经回退！");
		writeJson(j);
	}
	
	public void doNotNeedSessionAndSecurity_loadOldStat()
	{
		Syjb syjb=service.getById(id);
		if(syjb!=null)
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String sql=LoadSqlJBSJCX(sdf.format(syjb.getJbks()), sdf.format(syjb.getJbjs()),syjb.getName());
			List<Map> iList=service.findBySql(sql);
			if(iList.size()>0)
			{
				writeJson(iList);
			}
			else {
				Json j = new Json();
				j.setMsg("没有结报数据！");
				writeJson(j);
			}
		}
		else {
			Json j = new Json();
			j.setMsg("没有结报数据！");
			writeJson(j);
		}
	}
	
	public void doNotNeedSessionAndSecurity_doJb() throws ParseException
	{
		Syjb syjb=new Syjb();
		String jbid=syjb.getId();
		String sqlString="update T_TICKETSTORE set jbid='"+jbid+"'"
				+ " where id in ("
				+ "select xx.fpid from t_xxfp xx,t_ticketstore t where xx.fpid=t.id "
				+ "and kprq between to_date('"+ksrq+"', 'yyyy-mm-dd') "
				+ "and to_date('"+jsrq+"', 'yyyy-mm-dd')+1 "
				+ "and t.name='"+name+"'"
				+ ") and jbid is null";
		int ret=ticketStoreService.executeSql(sqlString);
		if(ret>0)
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			syjb.setName(name);
			syjb.setId(jbid);
			syjb.setJbks(sdf.parse(ksrq));
			syjb.setJbjs(sdf.parse(jsrq)); 
			service.save(syjb);
			String sql=LoadSqlJBSJCX(ksrq, jsrq, name);
			List<Map> iList=service.findBySql(sql);
			if(iList.size()>0)
			{
				writeJson(iList);
			}
			else {
				Json j = new Json();
				j.setMsg("没有结报数据！");
				writeJson(j);
			}
		}
		else
		{
			Json j = new Json();
			j.setMsg("没有结报数据！");
			writeJson(j);
		}
	}
	
	private String LoadSqlJBSJCX(String ksrq,String jsrq,String name)
	{
		String sql="with xlist as "
				+ "(select t.name, t.fpdm, t.fphm, "
				+ "decode(t.status, '9', '1', t.status) status, "
				+ "decode(decode(t.status, '9', '1', t.status), '1', x.sjje, '-1', 0) sjje,"
				+ " x.kprq, "
				+ "lead(fphm, 1, fphm) over(order by fphm) next_fphm, "
				+ "lag(fphm, 1, fphm) over(order by fphm) prev_fphm, "
				+ "nvl(lead(decode(t.status, '9', '1', t.status)) over(order by fphm), 0) next_st, "
				+ "nvl(lag(decode(t.status, '9', '1', t.status)) over(order by fphm), 0) prev_st "
				+ "from t_ticketstore t, t_xxfp x "
				+ "where t.id = x.fpid and x.kprq >= to_date('"+ksrq+"', 'yyyy-mm-dd') "
				+ "and x.kprq <= to_date('"+jsrq+"', 'yyyy-mm-dd') + 1 "
				+ "and t.name='"+name+"' order by fphm asc) "
				+ "select nvl(b.name, '-') name, nvl(b.fpdm, '-') fpdm, nvl(lpad(b.kshm, 8, '0'), '-') kshm, "
				+ "nvl(lpad(b.jshm, 8, '0'), '-') jshm, sum(b.kjs) kjs, sum(b.zfs) zfs, sum(b.hj) hjje "
				+ "from ("
				+ "select a.*, sum(xl.sjje) hj from "
				+ "(select name, fpdm, kshm, jshm,"
				+ " sum(case when status = '1' then jshm - kshm + 1 else 0 end) kjs,"
				+ " sum(case when status = '-1' then jshm - kshm + 1 else 0 end) zfs "
				+ "from ("
				+ "select name, fpdm, status, fphm kshm, "
				+ "F_GetFinalFphm(fphm, status, '"+ksrq+"','"+jsrq+"') jshm,"
				+ " sjje from ("
				+ "select x.* from xlist x where x.status <> x.prev_st or x.prev_fphm <> x.fphm - 1"
				+ ")) group by name, fpdm, kshm, jshm, status) a, xlist xl "
				+ "where xl.fphm >= a.kshm and xl.fphm <= a.jshm group by a.name, a.fpdm, a.kshm, a.jshm, a.kjs, a.zfs) b "
				+ "group by grouping sets((b.name, b.fpdm, b.kshm, b.jshm),()) order by b.kshm";
		return sql;
	}
}
