package aisino.reportform.service.fpmng.dzfp.Impl;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import aisino.reportform.model.fpmng.Ssflbm;
import aisino.reportform.service.fpmng.dzfp.ExcelServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;

@Service
public class ExcelServiceImpl extends BaseServiceImpl<Ssflbm> implements ExcelServiceI{

	@Override
	public void saveHashMap(HashMap hash, String tablename, String columns) {
		String sql_insert="insert into "+tablename+" ("+"ID,"+columns+") ";
		String buff = "'"+UUID.randomUUID().toString()+"',";
		for(String col : columns.split(",")) {
			buff += "'"+hash.get(col)+"',";
		}
		buff = buff.substring(0, buff.lastIndexOf(","));

		sql_insert=sql_insert+"values ("+buff+") ";
//		System.out.println(sql_insert);
		executeSql(sql_insert);
	}


}
