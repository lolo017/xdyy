package aisino.reportform.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import aisino.reportform.model.base.Work;
import aisino.reportform.service.base.WorkServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.HqlFilter;

@Service
public class WorkServiceImpl  extends BaseServiceImpl<Work> implements WorkServiceI{
	@Override
	public List<Work> findRoleByFilter(HqlFilter hqlFilter, int page, int rows) {
		// TODO Auto-generated method stub
		String hql = "select distinct t from Work t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

	@Override
	public Long countWorkByFilter(HqlFilter hqlFilter) {
		// TODO Auto-generated method stub
		String hql = "select count(distinct t) from Work t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

 
}
