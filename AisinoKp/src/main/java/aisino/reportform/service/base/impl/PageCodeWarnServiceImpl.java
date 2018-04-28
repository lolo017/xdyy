package aisino.reportform.service.base.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import aisino.reportform.model.base.PageCodeSelfWarn;
import aisino.reportform.service.base.PageCodeWarnServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.ClobUtil;
@Service
public class PageCodeWarnServiceImpl extends BaseServiceImpl<PageCodeSelfWarn> implements PageCodeWarnServiceI{
	@Override
	public List<Map> findSelfWarnByPageId(String pageId){
		String sql="select p2.cnenglish,p1.gt,p1.lt,p1.color from  pagecode_self_warn p1 left join pagecode p2 on p1.pagecodeid=p2.id left join page p3 on p2.pageid=p3.id where p3.id='"+pageId+"'";
		return findBySql(sql);
	}
	
	@Override
	public List<Map> findAvgWarnByPageId(String pageId){
		String sql="select distinct p.cnenglish_exc,p.cnenglish_str, pp.cnenglish,p.color from pagecode_avg_warn p left join pagecode pp on p.pagecodeid=pp.id left join page ppp on pp.pageid=ppp.id where ppp.id='"+pageId+"'";
		return findBySql(sql);
	}
	
	@Override
	public List<Map> findSqlWarnByPageId(String pageId){
		String sql="select p2.cnenglish,p1.gt_sql,p1.lt_sql,p1.color from  pagecode_sql_warn p1 left join pagecode p2 on p1.pagecodeid=p2.id left join page p3 on p2.pageid=p3.id where p3.id='"+pageId+"'";
		List<Map>list= findBySql(sql);
		String gtValue;
		String ltValue;
		for(Map m :list){
			if(m.get("GT_SQL")!=null&&m.get("GT_SQL")!=""){
				//先获取clob类型，clob类型再转换成string
				gtValue=getBySql(ClobUtil.getStringByProxy(m.get("GT_SQL")));
				m.put("gtValue", gtValue);
			}
			if(m.get("LT_SQL")!=null&&m.get("LT_SQL")!=""){
				//先获取clob类型，clob类型再转换成string
				ltValue=getBySql(ClobUtil.getStringByProxy(m.get("LT_SQL")));
				m.put("ltValue", ltValue);
			}
		}
		return list;
	}
	@Override
	public List<Map> findSqlWarnByPageId2(String pageId){
		String sql="select p2.cnenglish,p1.gt_sql,p1.lt_sql,p1.color from  pagecode_sql_warn p1 left join pagecode p2 on p1.pagecodeid=p2.id left join page p3 on p2.pageid=p3.id where p3.id='"+pageId+"'";
		return findBySql(sql);
	}
}
