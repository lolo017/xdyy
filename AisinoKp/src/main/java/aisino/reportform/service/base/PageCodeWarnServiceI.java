package aisino.reportform.service.base;

import java.util.List;
import java.util.Map;

import aisino.reportform.model.base.PageCodeSelfWarn;
import aisino.reportform.service.BaseServiceI;

public interface PageCodeWarnServiceI extends BaseServiceI<PageCodeSelfWarn>{
	/**
	 * 根据pageid查询所有自定义预警区间
	 * 
	 */
	public List<Map> findSelfWarnByPageId(String pageId);
	/**
	 * 根据pageid查询所有平均值预警
	 * 
	 */
	public List<Map> findAvgWarnByPageId(String pageId);
	
	/**查询报表时使用:
	 * 根据pageid查询所有SQL值预警
	 * 
	 */
	public List<Map> findSqlWarnByPageId(String pageId);
	
	/**编辑报表时使用:
	 * 根据pageid查询所有SQL值预警()
	 * 
	 */
	public List<Map> findSqlWarnByPageId2(String pageId);

}
