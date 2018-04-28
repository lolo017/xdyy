package aisino.reportform.util.base;

import aisino.reportform.db.DatabaseContextHolder;
/**
 * 设置数据源
 * 
 * @author 廖宸宇
 * @date 2015-7-24
 */
public class DbUtil {
	
	public static void setDb(String db){
		if(db==null||db.equals("1")){
			//参数db为空或1，连接pos
				DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_1);
			}
		if(db!=null&&db.equals("2")){
			//参数db为2，连接scjs
				DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_2);
			}
		if(db!=null&&db.equals("3")){
			//参数db为3，连接jsy
				DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_3);
			}
	}
}
