package aisino.reportform.service.fpmng.dzfp;

import java.util.HashMap;

import aisino.reportform.model.fpmng.Ssflbm;
import aisino.reportform.service.BaseServiceI;

public interface ExcelServiceI extends BaseServiceI<Ssflbm>{

	/**
	 * 
	 * @Title: saveHsahMap
	 * @Description: 
	 * @author 曹梦媛
	 * @date 2018年1月2日 上午10:21:49
	 * @param: @param hash
	 * @param: @param tablename
	 * @param: @param columns 
	 * @return: void   
	 * @throws
	 */
	void saveHashMap(HashMap hash, String tablename, String columns);


}
