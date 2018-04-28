package aisino.reportform.service.base.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import aisino.reportform.model.base.Budget;
import aisino.reportform.model.base.Specific;
import aisino.reportform.model.base.Syorganization;
import aisino.reportform.service.base.SpecificServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;

@Service
public class SpecificServiceImpl extends BaseServiceImpl<Specific> implements  SpecificServiceI{

	@Override
	public List<Specific> findSpecificBySid(String sid){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("sid", sid);
		String hql="select new Specific(t.id,t.budget,t.year,t.numbers,t.price,t.total,t.numbers2,t.price2,t.total2,t.budgetid,t.sid,t.sname,t.confirm) from Specific t where  t.sid=:sid order by budgetid asc";
		return find(hql,params);
	}
	
	@Override
	public void updateSpecific(String sid,String total[],String price[],String number[],String total2[],String price2[],String number2[],String budget[],String orgid,String orgName,String year){
		String sql1="select distinct t.confirm,t.orgid from SPECIFIC t where t.sid='"+sid+"'";
		List<Map> l=findBySql(sql1);
		String confirm=(String)l.get(0).get("CONFIRM");
		String org=(String)l.get(0).get("ORGID");
		//删除旧specific
		String sql2="delete from specific t where t.sid='"+sid+"'";
		executeSql(sql2);
		//新增
		List<Specific> list=new ArrayList<Specific>();
		Syorganization syorganization=new Syorganization();
		syorganization.setId(org);
		 sid=UUID.randomUUID().toString();
		for (int i = 0; i < budget.length; i++) {
			Specific specific=new Specific();
			specific.setBudgetid(i+1);
			Budget budget2=new Budget();
			budget2.setIndexs(budget[i]);
			specific.setYear(year);
			specific.setConfirm(confirm);
			specific.setBudget(budget2);
			specific.setSyorganization(syorganization);
			if(!number[i].equals("null")){
				specific.setNumbers(number[i]);
			}
			if(!price[i].equals("null")){
				specific.setPrice(price[i]);
			}
			if(!total[i].equals("null")){
				specific.setTotal(total[i]);
			}
			if(!number2[i].equals("null")){
				specific.setNumbers2(number2[i]);
			}
			if(!price2[i].equals("null")){
				specific.setPrice2(price2[i]);
			}
			if(!total2[i].equals("null")){
				specific.setTotal2(total2[i]);
			}
			specific.setSyorganization(syorganization);
			specific.setSid(sid);
			specific.setSname(year+orgName);
			list.add(specific);
		}
		saveList(list);
	}
}
