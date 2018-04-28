package aisino.reportform.service.fpmng;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.alibaba.fastjson.JSONObject;

import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.service.BaseServiceI;

public interface OrderDataZServiceI extends BaseServiceI<OrderDataZ>{
	
	/**
	 * 
	 * @Title: checkOrderKP
	 * @Description: 检查OrderData中是否含有已开票数据
	 * @param: @param list
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	public String checkOrderKP(List<SysOrderLine> list);
	
	/**
	 * 
	 * @Title: excel2Odlist
	 * @Description: 传入上传的excel地址,将excel数据转为List<OrderDataZ>
	 * @param: @param filepaths
	 * @param: @return 
	 * @return: List<OrderDataZ>   
	 * @throws
	 */
	public List<OrderDataZ> excel2Odlist(String filepaths);

	/**
	 * 
	 * @Title: saveExcel2List
	 * @Description: 转换excel表订单数据至List
	 * @param: @param filepaths
	 * @param: @param column
	 * @param: @return 
	 * @return: List<HashMap>   
	 * @throws
	 */
	public List<HashMap> saveExcel2List(String filepaths, String column);

	/**
	 * 
	 * @Title: excel2Odlist
	 * @Description: 转换excel至List<OrderDataZ>
	 * @author 曹梦媛
	 * @date 2018年1月2日 上午10:21:49
	 * @param: @param filepaths	文件路径
	 * @param: @param columns	传入取值字段
	 * @param: @return 
	 * @return: List<OrderDataZ>   
	 * @throws
	 */
	public List<OrderDataZ> excel2Odlist(String filepaths, String columns);

	
}
