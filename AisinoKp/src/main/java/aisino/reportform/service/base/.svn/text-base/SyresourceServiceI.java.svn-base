package aisino.reportform.service.base;

import java.util.List;
import java.util.Map;

import aisino.reportform.model.base.PageCodeAvgWarn;
import aisino.reportform.model.base.PageCodeSelfWarn;
import aisino.reportform.model.base.PageCodeSqlWarn;
import aisino.reportform.model.base.Sqlcondition;
import aisino.reportform.model.base.Syresource;
import aisino.reportform.service.BaseServiceI;
import aisino.reportform.util.base.HqlFilter;

/**
 * 资源业务逻辑
 * 
 * @author 
 * 
 */
public interface SyresourceServiceI extends BaseServiceI<Syresource> {

	/**
	 * 获得资源树，或者combotree(只查找菜单类型的节点)
	 * 
	 * @return
	 */
	public List<Syresource> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 获得资源treeGrid
	 * 
	 * @return
	 */
	public List<Syresource> resourceTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新资源
	 */
	public void updateResource(Syresource syresource);

	/**
	 * 保存一个资源
	 * 
	 * @param syresource
	 * @param userId
	 * @return
	 */
	public void saveResource(Syresource syresource, String userId);
	/**
	 * 删除一个资源，若资源对应一个报表，同时删除报表
	 */
	public void deleteResource(String id);

	/**
	 * 查找符合条件的资源
	 */
	public List<Syresource> findResourceByFilter(HqlFilter hqlFilter);
	
	/**
	 * 查找所有表名
	 */
	public List findTable();
	/**
	 * 根据表名查找所有字段
	 */
	public List findColumn(String tableName);
	/**
	 * 根据SQL查询gridField(结果集列名)
	 */
	public List findGridField(String sqlStr);
	/**
	 * 根据SQL预览报表
	 */
	public List previewSql(String sqlStr,int page,int rows);
	/**
	 * 查询报表记录总数
	 */
	public Long countSql(String sqlStr);
	/**
	 * 保存报表
	 */
	public void saveSql(String db,String unit,String sortOrder,Integer highchartsBtn,String[] tArray,String[] cArray,String[] indexArray,String sqlStr,Syresource syresource, String userId,List<Sqlcondition> conditionList,List<String> urlList,List<Integer> widthList,List<Integer> hcList,List<PageCodeAvgWarn> avgList,List<PageCodeSelfWarn> selfList,List<PageCodeSqlWarn> sqlList);
	/**
	 * 修改报表
	 */
	public void updateSql(String db,String unit,String sortOrder,Integer highchartsBtn,String pageName,String[] tArray,String[] cArray,String[] indexArray,String sqlStr,String pageId,List<Sqlcondition> conditionList,List<String> urlList,List<Integer> widthList,List<Integer> hcList,List<PageCodeAvgWarn> avgList,List<PageCodeSelfWarn> selfList,List<PageCodeSqlWarn> sqlList);
    /**
     * 查询平均值预警map
     * @return
     */
	public Map findAvgMap(List<Map> totalData,List<Map> warnCode,String sql);
}
