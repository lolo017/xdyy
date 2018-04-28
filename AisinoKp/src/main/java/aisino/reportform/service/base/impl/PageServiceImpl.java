package aisino.reportform.service.base.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.dao.base.BaseDaoI;
import aisino.reportform.model.base.Page;
import aisino.reportform.model.base.PageCode;
import aisino.reportform.model.base.Sqlcondition;
import aisino.reportform.model.base.Syresource;
import aisino.reportform.service.base.PageServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
/**
 * page业务逻辑实现类
 * 
 * @author 廖宸宇
 * @date 2015-2-3
 * 
 */
@Service
public class PageServiceImpl extends BaseServiceImpl<Page> implements PageServiceI{
	
	@Autowired
	private BaseDaoI<Syresource> SyresourceDao;
	@Autowired
	private BaseDaoI<Sqlcondition> SqlconditionDao;
	@Autowired
	private BaseDaoI<PageCode> pagecodeDao;
	
	@Autowired
	private BaseDaoI<Page> pageDao;
	
	  
	  
	
	@Override
	public String savePage(Page page){
		return (String)save(page);
	}
	@Override
	public Page findPage(String id){
		return getById(id);
	}
	@Override
	public void deletePage(String id) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("pageId", id);
		String SyresourceHql="delete Syresource s where s.pageId=:pageId";
		SyresourceDao.executeHql(SyresourceHql, params);
		Page p=pageDao.getById(Page.class,id);
		pageDao.delete(p);

		
		
		
	}
}
