package aisino.reportform.service.base.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import aisino.reportform.model.base.Study;
import aisino.reportform.service.base.StudyServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.HqlFilter;

@Service
public class StudyServiceImpl extends BaseServiceImpl<Study> implements StudyServiceI{

	@Override
	public List<Study> findStudyByFilter(HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from Study t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

	@Override
	public Long countStudyByFilter(HqlFilter hqlFilter) {
		// TODO Auto-generated method stub
		String hql = "select count(distinct t) from Study t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
