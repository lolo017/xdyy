package aisino.reportform.service.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import aisino.reportform.model.base.Sqlcondition;
import aisino.reportform.service.base.SqlconditionServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.HqlFilter;
@Service
public class SqlconditionServiceImpl extends BaseServiceImpl<Sqlcondition> implements SqlconditionServiceI{

	@Override
	public List<Sqlcondition> findSqlconditionByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		// TODO Auto-generated method stub
		String hql = "select distinct t from Sqlcondition t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

	@Override
	public Long countSqlconditionByFilter(HqlFilter hqlFilter) {
		// TODO Auto-generated method stub
		String hql = "select count(distinct t) from Sqlcondition t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}
	@Override
	public List<Sqlcondition> findSqlconditionByPageId(String pageId){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("pageId", pageId);
		String hql="select new Sqlcondition(id,cncondition,encondition,conditiontype,conditionsql,seq,defaultValue) from Sqlcondition t where  t.page.id=:pageId order by t.seq asc";
		return find(hql,params);
	}

}
