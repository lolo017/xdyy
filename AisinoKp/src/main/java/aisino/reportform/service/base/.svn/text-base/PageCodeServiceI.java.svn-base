package aisino.reportform.service.base;

import java.util.List;
import java.util.Map;

import aisino.reportform.model.base.PageCode;
import aisino.reportform.service.BaseServiceI;
/**
 * pageCode业务逻辑接口
 * 
 * @author 廖宸宇
 * @date 2015-2-3
 * 
 */
public interface PageCodeServiceI extends BaseServiceI<PageCode>{
	
	/**
	 * 保存pageCode
	 * 
	 * @return
	 */
	public void savePageCode(List<PageCode> list);
	/**
	 * 查询pageCode
	 * 
	 * @return
	 */
	public List<PageCode> findPageCode(String pageId);
	/**
	 * 删除pageCode
	 * 
	 * @return
	 */
	public Integer deletePageCode(String pageId);
	
	
	public List<Map> findPageCodes(String sql);
	/**
	 * 根据pageid查询需要平均值预警的pageCode
	 * 
	 * @return
	 */
	public List<Map> findWarnByPageId(String pageId);
	/**
	 * 根据pageid查询对应表头的行数
	 */
	public String findMaxrowByPageId(String pageId);
}
