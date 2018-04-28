package aisino.reportform.service.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;








import aisino.reportform.model.base.PageCode;
import aisino.reportform.service.base.PageCodeServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
@Service
public class PageCodeServiceImpl extends BaseServiceImpl<PageCode> implements PageCodeServiceI{

	@Override
	public void savePageCode(List<PageCode> list){
		saveList(list);
	}
	
	@Override
	public List<PageCode> findPageCode(String pageId){
		String hql="select new PageCode(id,codeName,cnName,cnEnglish,orderId,url,width,isHighcharts, rowNum, colNum, rowspan, colspan) from PageCode as p where p.page.id=:pageId order by ORDERID";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("pageId", pageId);
		return find(hql,params);
		
	}

	@Override
	public Integer deletePageCode(String pageId) {
		// TODO Auto-generated method stub
		String hql="delete PageCode s where s.page.id=:pageId";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("pageId", pageId);
		return executeSql(hql, params);
	}

	@Override
	public List<Map> findPageCodes(String sql) {
		// TODO Auto-generated method stub
		return findBySql(sql);
	}
	
	@Override
	public List<Map> findWarnByPageId(String pageId){
		String sql="select t.cnenglish,t.cnenglish_exc,t.cnenglish_str,tt.cnname as cnname_exc  from (select  pp.cnenglish,p.cnenglish_exc,p.cnenglish_str from pagecode_avg_warn p left join pagecode pp on p.pagecodeid=pp.id left join page ppp on pp.pageid=ppp.id where ppp.id='"+pageId+"' ) t left join pagecode tt on t.cnenglish_exc=tt.cnenglish and tt.pageid='"+pageId+"'";
		return findBySql(sql);
	}
	@Override
	public String findMaxrowByPageId(String pageId){
		String sql="select max(tt.row_num) from pagecode tt where tt.pageid='"+pageId+"'";
		return getBySql(sql);
	}
}
