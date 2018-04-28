package aisino.reportform.service.base.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.dao.base.BaseDaoI;
import aisino.reportform.model.base.Page;
import aisino.reportform.model.base.PageCode;
import aisino.reportform.model.base.PageCodeAvgWarn;
import aisino.reportform.model.base.PageCodeSelfWarn;
import aisino.reportform.model.base.PageCodeSqlWarn;
import aisino.reportform.model.base.Sqlcondition;
import aisino.reportform.model.base.Syorganization;
import aisino.reportform.model.base.Syresource;
import aisino.reportform.model.base.Syrole;
import aisino.reportform.model.base.Syuser;
import aisino.reportform.service.base.SyresourceServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.BeanUtils;
import aisino.reportform.util.base.HqlFilter;

/**
 * 资源业务逻辑
 * 
 * @author
 * 
 */
@Service
public class SyresourceServiceImpl extends BaseServiceImpl<Syresource>
		implements SyresourceServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;
	@Autowired
	private BaseDaoI<Page> pageDao;
	@Autowired
	private BaseDaoI<PageCode> pageCodeDao;
	@Autowired
	private BaseDaoI<Sqlcondition> sqlConditionDao;

	/**
	 * 由于角色与资源关联，机构也与资源关联，所以查询用户能看到的资源菜单应该查询两次，最后合并到一起。
	 */
	@Override
	public List<Syresource> getMainMenu(HqlFilter hqlFilter) {
		List<Syresource> l = new ArrayList<Syresource>();
		String hql = "select distinct t from Syresource t join t.syroles role join role.syusers user";
		List<Syresource> resource_role = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(resource_role);
		hql = "select distinct t from Syresource t join t.syroles role join t.syorganizations organization join organization.syusers user";
		List<Syresource> resource_organization = find(
				hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_organization);
		l = new ArrayList<Syresource>(new HashSet<Syresource>(l));// 去重

		Collections.sort(l, new Comparator<Syresource>() {// 排序
					@Override
					public int compare(Syresource o1, Syresource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return l;
	}

	@Override
	public List<Syresource> resourceTreeGrid(HqlFilter hqlFilter) {
		List<Syresource> l = new ArrayList<Syresource>();
		String hql = "select distinct t from Syresource t join t.syroles role join role.syusers user";
		List<Syresource> resource_role = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(resource_role);
		hql = "select distinct t from Syresource t join t.syorganizations organization join organization.syusers user";
		List<Syresource> resource_organization = find(
				hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_organization);
		l = new ArrayList<Syresource>(new HashSet<Syresource>(l));// 去重
		Collections.sort(l, new Comparator<Syresource>() {// 排序
					@Override
					public int compare(Syresource o1, Syresource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return l;
	}

	@Override
	public void updateResource(Syresource syresource) {
		if (!StringUtils.isBlank(syresource.getId())) {
			Syresource t = getById(syresource.getId());
			// 如果是报表资源，page名称一同修改
			if (!t.getName().equals(syresource.getName())
					&& t.getPageId() != null && t.getPageId().length() > 0) {
				String sql = "update page t set t.pagename='"
						+ syresource.getName() + "' where t.id='"
						+ t.getPageId() + "'";
				pageDao.executeSql(sql);
			}
			Syresource oldParent = t.getSyresource();
			BeanUtils.copyNotNullProperties(syresource, t,
					new String[] { "createdatetime" });
			if (syresource.getSyresource() != null) {// 说明要修改的节点选中了上级节点
				Syresource pt = getById(syresource.getSyresource().getId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setSyresource(pt);
			} else {
				t.setSyresource(null);
			}
		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点下
	 * 
	 * @param t
	 *            当前节点
	 * @param pt
	 *            要修改到的节点
	 * 
	 * @param oldParent
	 *            原上级节点
	 * @return
	 */
	private boolean isParentToChild(Syresource t, Syresource pt,
			Syresource oldParent) {
		if (pt != null && pt.getSyresource() != null) {
			if (StringUtils.equals(pt.getSyresource().getId(), t.getId())) {
				pt.setSyresource(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getSyresource(), oldParent);
			}
		}
		return false;
	}

	/**
	 * 由于新添加的资源，当前用户的角色或者机构并没有访问此资源的权限，所以这个地方重写save方法，
	 * 将新添加的资源放到用户的第一个角色里面或者第一个机构里面
	 */
	@Override
	public void saveResource(Syresource syresource, String userId) {
		save(syresource);
		Syuser user = userDao.getById(Syuser.class, userId);
		Set<Syrole> roles = user.getSyroles();
		if (roles != null && !roles.isEmpty()) {// 如果用户有角色，就将新资源放到用户的第一个角色里面
			List<Syrole> l = new ArrayList<Syrole>();
			l.addAll(roles);
			Collections.sort(l, new Comparator<Syrole>() {
				@Override
				public int compare(Syrole o1, Syrole o2) {
					if (o1.getCreatedatetime().getTime() > o2
							.getCreatedatetime().getTime()) {
						return 1;
					}
					if (o1.getCreatedatetime().getTime() < o2
							.getCreatedatetime().getTime()) {
						return -1;
					}
					return 0;
				}
			});
			l.get(0).getSyresources().add(syresource);
		} else {// 如果用户没有角色
			Set<Syorganization> organizations = user.getSyorganizations();
			if (organizations != null && !organizations.isEmpty()) {// 如果用户没有角色，但是有机构，那就将新资源放到第一个机构里面
				List<Syorganization> l = new ArrayList<Syorganization>();
				l.addAll(organizations);
				Collections.sort(l, new Comparator<Syorganization>() {
					@Override
					public int compare(Syorganization o1, Syorganization o2) {
						if (o1.getCreatedatetime().getTime() > o2
								.getCreatedatetime().getTime()) {
							return 1;
						}
						if (o1.getCreatedatetime().getTime() < o2
								.getCreatedatetime().getTime()) {
							return -1;
						}
						return 0;
					}
				});
				l.get(0).getSyresources().add(syresource);
			}
		}
	}

	/**
	 * 删除一个资源，若资源对应一个报表，同时删除报表
	 */
	@Override
	public void deleteResource(String id) {
		Syresource t = getById(id);
		if (t.getPageId() != null && t.getPageId().length() > 0) {
			Page p = pageDao.getById(Page.class, t.getPageId());
			pageDao.delete(p);
		}
		delete(t);
	}

	@Override
	public List<Syresource> findResourceByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from Syresource t left join t.syroles role left join t.syorganizations organization";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List findTable() {
		String sql = "select table_name from user_tables ";
		return findBySql(sql);
	}

	@Override
	public List findColumn(String tableName) {
		String sql = "select column_name,data_type from user_tab_columns where table_name=";
		sql = sql + "'" + tableName + "'";
		return findBySql(sql);
	}

	@Override
	public List findGridField(String sqlStr) {
		// 这里应该判断数据库类型，添加条件语句
		String sql = " select * from (" + sqlStr + ") where rownum<2";
		return findColumnBySql(sqlStr);
	}

	@Override
	public List previewSql(String sqlStr, int page, int rows) {
		return findBySql(sqlStr, page, rows);

	}

	@Override
	public Long countSql(String sqlStr) {
		String sql = "select count(*) from ("+sqlStr+") a";
		//String sql = "select count(*) from syuser";
		return countBySql(sql);

	}

	@Override
	public void saveSql(String db, String unit, String sortOrder,
			Integer highchartsBtn, String[] tArray, String[] cArray,String[] indexArray,
			String sqlStr, Syresource syresource, String userId,
			List<Sqlcondition> conditionList, List<String> urlList,
			List<Integer> widthList, List<Integer> hcList,
			List<PageCodeAvgWarn> avgList, List<PageCodeSelfWarn> selfList,List<PageCodeSqlWarn> sqlList) {

		// 保存page
		Page page = new Page();
		Set<Sqlcondition> sqlConditions = new HashSet<Sqlcondition>();
		Set<PageCode> pageCodes = new HashSet<PageCode>();
		String pageId = page.getId();
		page.setId(pageId);
		page.setHighchartsBtn(highchartsBtn);
		page.setUnit(unit);
		page.setSortOrder(sortOrder);
		page.setPageName(syresource.getName());
		page.setSqlCode(sqlStr);
		page.setDb(db);
		// 保存sqlcondition
		if (conditionList != null && !conditionList.isEmpty()) {
			for (Sqlcondition con : conditionList) {
				con.setPage(page);
				sqlConditions.add(con);
			}
			page.setSqlConditions(sqlConditions);
		}
		// 保存pagecode
		for (int i = 0; i < tArray.length; i++) {
			String[] cellIndex=indexArray[i].split(",");
			PageCode pageCode = new PageCode();
			pageCode.setPage(page);
			pageCode.setId(UUID.randomUUID().toString());
			pageCode.setPage(page);
			pageCode.setCnName(tArray[i]);
			pageCode.setCnEnglish(cArray[i]);
			pageCode.setOrderId(i);
			pageCode.setWidth(widthList.get(i));
			pageCode.setIsHighcharts(hcList.get(i));
			pageCode.setRowNum(Integer.valueOf(cellIndex[0]));
			pageCode.setColNum(Integer.valueOf(cellIndex[1]));
			pageCode.setRowspan(Integer.valueOf(cellIndex[2]));
			pageCode.setColspan(Integer.valueOf(cellIndex[3]));
			// 如果url值是empty，则为null
			if (!"empty".equals(urlList.get(i))) {
				try {
					System.out.println("save-url:"+urlList.get(i));
					pageCode.setUrl(URLDecoder.decode(urlList.get(i), "UTF-8"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 保存平均值预警
			if (avgList != null && avgList.size() > 0) {
				for (PageCodeAvgWarn avg : avgList) {
				
					if (avg.getCnEnglishExc()!=null&&avg.getCnEnglishStr()!=null&&pageCode.getCnEnglish().equals(
							avg.getCnEnglish().toUpperCase())) {
						avg.setCnEnglishExc(avg.getCnEnglishExc().toUpperCase());
						avg.setPagecode(pageCode);
						pageCode.getPageCodeAvgWarns().add(avg);
					}
					if (avg.getCnEnglishExc()==null&&avg.getCnEnglishStr()==null&pageCode.getCnEnglish().equals(
							avg.getCnEnglish().toUpperCase())) {
						avg.setPagecode(pageCode);
						pageCode.getPageCodeAvgWarns().add(avg);
					}
				}
			}
			// 保存自定义预警
			if (selfList != null && selfList.size() > 0) {
				for (PageCodeSelfWarn self : selfList) {
					if (pageCode.getCnEnglish().equals(
							self.getCnEnglish().toUpperCase())) {
						self.setPagecode(pageCode);
						pageCode.getPageCodeSelfWarns().add(self);
					}
				}
			}
			
			//保存sql预警
			if(sqlList!=null&&sqlList.size()>0){
				for(PageCodeSqlWarn sql :sqlList){
					if (pageCode.getCnEnglish().equals(
							sql.getCnEnglish().toUpperCase())&&((sql.getLtSql()!=null&&sql.getLtSql()!="")||(sql.getGtSql()!=null&&sql.getGtSql()!=""))) {
						try {
							sql.setGtSql(URLDecoder.decode(sql.getGtSql(), "UTF-8"));
							sql.setLtSql(URLDecoder.decode(sql.getLtSql(), "UTF-8"));
						} catch (Exception e) {
							e.printStackTrace();
						}
						sql.setPagecode(pageCode);
						pageCode.getPageCodeSqlWarns().add(sql);
					}
				}
			}
			
			pageCodes.add(pageCode);

		}
		page.setPageCodes(pageCodes);
		pageId = (String) pageDao.save(page);
		// 保存resource
		syresource.setPageId(pageId);
		syresource.setUrl("/securityJsp/base/ReportForm.jsp?pageId=" + pageId);
		syresource.setManage_active("1");
		saveResource(syresource, userId);

	}

	@Override
	public void updateSql(String db, String unit, String sortOrder,
			Integer highchartsBtn, String pageName, String[] tArray,
			String[] cArray, String[] indexArray,String sqlStr, String pageId,
			List<Sqlcondition> conditionList, List<String> urlList,
			List<Integer> widthList, List<Integer> hcList,
			List<PageCodeAvgWarn> avgList, List<PageCodeSelfWarn> selfList,List<PageCodeSqlWarn> sqlList) {
		// 删除旧page
		Page p = pageDao.getById(Page.class, pageId);
		pageDao.delete(p);
		// 保存新page
		Page page = new Page();
		Set<Sqlcondition> sqlConditions = new HashSet<Sqlcondition>();
		Set<PageCode> pageCodes = new HashSet<PageCode>();
		page.setId(pageId);
		page.setHighchartsBtn(highchartsBtn);
		page.setUnit(unit);
		page.setSortOrder(sortOrder);
		page.setPageName(pageName);
		page.setSqlCode(sqlStr);
		page.setDb(db);
		// 保存sqlcondition
		if (conditionList != null && !conditionList.isEmpty()) {
			for (Sqlcondition con : conditionList) {
				con.setPage(page);
				sqlConditions.add(con);
			}
			page.setSqlConditions(sqlConditions);
		}
		// 保存pagecode
		for (int i = 0; i < tArray.length; i++) {
			String[] cellIndex=indexArray[i].split(",");
			PageCode pageCode = new PageCode();
			pageCode.setPage(page);
			pageCode.setId(UUID.randomUUID().toString());
			pageCode.setPage(page);
			pageCode.setCnName(tArray[i]);
			pageCode.setCnEnglish(cArray[i]);
			pageCode.setOrderId(i);
			pageCode.setWidth(widthList.get(i));
			pageCode.setIsHighcharts(hcList.get(i));
			pageCode.setRowNum(Integer.valueOf(cellIndex[0]));
			pageCode.setColNum(Integer.valueOf(cellIndex[1]));
			pageCode.setRowspan(Integer.valueOf(cellIndex[2]));
			pageCode.setColspan(Integer.valueOf(cellIndex[3]));
			// 如果url值是empty，则为null
			if (!"empty".equals(urlList.get(i))) {
				try {
					System.out.println("save-url:"+urlList.get(i));
					pageCode.setUrl(URLDecoder.decode(urlList.get(i), "UTF-8"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 保存平均值预警
			if (avgList != null && avgList.size() > 0) {
				for (PageCodeAvgWarn avg : avgList) {
					if (avg.getCnEnglishExc()!=null&&avg.getCnEnglishStr()!=null&&pageCode.getCnEnglish().equals(
							avg.getCnEnglish().toUpperCase())) {
						avg.setCnEnglishExc(avg.getCnEnglishExc().toUpperCase());
						avg.setPagecode(pageCode);
						pageCode.getPageCodeAvgWarns().add(avg);
					}
					if (avg.getCnEnglishExc()==null&&avg.getCnEnglishStr()==null&pageCode.getCnEnglish().equals(
							avg.getCnEnglish().toUpperCase())) {
						avg.setPagecode(pageCode);
						pageCode.getPageCodeAvgWarns().add(avg);
					}
				}
			}
			// 保存自定义预警
			if (selfList != null && selfList.size() > 0) {
				for (PageCodeSelfWarn self : selfList) {
					if (self.getCnEnglish()!=null&&(self.getGtValue()!=null||self.getLtValue()!=null)&&pageCode.getCnEnglish().equals(
							self.getCnEnglish().toUpperCase())) {
						self.setPagecode(pageCode);
						pageCode.getPageCodeSelfWarns().add(self);
					}
				}
			}
			//保存sql预警
			if(sqlList!=null&&sqlList.size()>0){
				for(PageCodeSqlWarn sql :sqlList){
					if (pageCode.getCnEnglish().equals(
							sql.getCnEnglish().toUpperCase())&&((sql.getLtSql()!=null&&sql.getLtSql()!="")||(sql.getGtSql()!=null&&sql.getGtSql()!=""))) {
						try {
							if(sql.getGtSql()!=null&&sql.getGtSql()!=""){
							sql.setGtSql(URLDecoder.decode(sql.getGtSql(), "UTF-8"));
							}
							if(sql.getLtSql()!=null&&sql.getLtSql()!=""){
							sql.setLtSql(URLDecoder.decode(sql.getLtSql(), "UTF-8"));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						sql.setPagecode(pageCode);
						pageCode.getPageCodeSqlWarns().add(sql);
					}
				}
			}
			pageCodes.add(pageCode);

		}
		page.setPageCodes(pageCodes);
		pageDao.save(page);
		
	}

	@Override
	public Map findAvgMap(List<Map> totalData, List<Map> warnCode, String sql) {
		BigDecimal sum;
		BigDecimal codeNum;
		BigDecimal avgVal;
		Map avgMap = new HashMap();
		String cnEnglishExc;
		String[] cnEnglishStr;
		String excInfo;// 预警信息，用于显示在报表上
		Map<Integer, Map> excMap = new HashMap();// 用于深拷贝
		// 将满足排除条件的值设置为null，后面计算总和将不计算null
		for (Map m1 : warnCode) {
			
			if (m1.get("CNENGLISH_EXC") != null
					&& m1.get("CNENGLISH_STR") != null) {
				cnEnglishExc = m1.get("CNENGLISH_EXC").toString();
				cnEnglishStr = m1.get("CNENGLISH_STR").toString().split("\\+");
				//有排除的时候,才生成excInfo
				excInfo = "排除" + m1.get("CNNAME_EXC") + ": "
						+ m1.get("CNENGLISH_STR").toString().replace("+", ",")
						+ "\n";
				for (int i = 0; i < totalData.size(); i++) {
					for (String s : cnEnglishStr) {
						if (totalData.get(i).get(cnEnglishExc) != null
								&& totalData.get(i).get(cnEnglishExc)
										.toString().equals(s)) {
							Map map = new HashMap();
							// 深拷贝，计算平均值后替换回原始数据
							//判断这行数据是否已经被拷贝过，防止出现第二条覆盖前一条出现null
							boolean bl=false;
							for (Integer key : excMap.keySet()) {
								if(key==i){
									bl=true;
								}
							}
							if(!bl){
								map.putAll(totalData.get(i));
								excMap.put(i, map);
							}
							
							totalData.get(i).put(m1.get("CNENGLISH"), null);
						}
					}
				}
				// 设置排序信息
				if (avgMap.get(m1.get("CNENGLISH") + "INFO") == null) {
					avgMap.put(m1.get("CNENGLISH") + "INFO", excInfo);
				} else {
					excInfo = avgMap.get(m1.get("CNENGLISH") + "INFO")
							+ excInfo;
					avgMap.put(m1.get("CNENGLISH") + "INFO", excInfo);
				}
			}
		}
		// 计算总和，平均值
		for (Map m1 : warnCode) {
			codeNum = new BigDecimal(0);
			sum = new BigDecimal(0);
			if (avgMap.get(m1.get("CNENGLISH")) == null) {
				for (Map m2 : totalData) {
					if (m2.get(m1.get("CNENGLISH")) != null) {
						codeNum = codeNum.add(new BigDecimal(1));
						BigDecimal val = new BigDecimal(m2.get(
								m1.get("CNENGLISH")).toString());
						sum = sum.add(val);
					}
				}
				// 计算平均值，保留两位，四舍五入
				avgVal = sum.divide(codeNum, 2, RoundingMode.HALF_UP);
				avgMap.put(m1.get("CNENGLISH"), avgVal);
			}

		}
		// 还原原始数据
		for (Integer key : excMap.keySet()) {
			totalData.set(key, excMap.get(key));
		}

		return avgMap;
	}

}
