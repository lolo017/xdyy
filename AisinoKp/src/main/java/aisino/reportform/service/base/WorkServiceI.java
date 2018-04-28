package aisino.reportform.service.base;

import java.util.List;

import aisino.reportform.model.base.Work;
import aisino.reportform.service.BaseServiceI;
import aisino.reportform.util.base.HqlFilter;

public interface WorkServiceI  extends BaseServiceI<Work>{
 
	public List<Work> findRoleByFilter(HqlFilter hqlFilter, int page, int rows) ;
 
	public Long countWorkByFilter(HqlFilter hqlFilter) ;
}
