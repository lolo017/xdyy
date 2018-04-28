package aisino.reportform.service.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import aisino.reportform.model.base.Workstudu;
import aisino.reportform.service.base.WordstudyServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.HqlFilter;

@Service
public class WordstudyServiceImpl  extends BaseServiceImpl<Workstudu> implements WordstudyServiceI{

	@Override
	public List<Workstudu> findWorkstudu(String usersid) {
		// TODO Auto-generated method stub
		String hql=" from WORKSTUDU as p where p.usersid=:usersid";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("usersid", usersid);
		return find(hql,params);
	}

	@Override
	public List<Workstudu> findRoleByFilter(HqlFilter hqlFilter, int page,
			int rows) {
		// TODO Auto-generated method stub
		String hql = "select distinct t from Workstudu t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

	@Override
	public Long countWorkstuduByFilter(HqlFilter hqlFilter) {
		// TODO Auto-generated method stub
		String hql = "select count(distinct t) from Workstudu t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
