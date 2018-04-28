package aisino.reportform.service.base;

import java.util.List;
import aisino.reportform.model.base.Sqlcondition;
import aisino.reportform.service.BaseServiceI;
import aisino.reportform.util.base.HqlFilter;

public interface SqlconditionServiceI  extends BaseServiceI<Sqlcondition>{

	public List<Sqlcondition> findSqlconditionByFilter(HqlFilter hqlFilter, int page, int rows) ;
	 
	public Long countSqlconditionByFilter(HqlFilter hqlFilter) ;
	//根据pageid查询List<Sqlcondition>
	public List<Sqlcondition> findSqlconditionByPageId(String pageId);
}
