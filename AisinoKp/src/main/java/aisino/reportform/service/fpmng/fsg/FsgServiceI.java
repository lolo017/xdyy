package aisino.reportform.service.fpmng.fsg;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.alibaba.fastjson.JSONObject;

import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.service.BaseServiceI;

public interface FsgServiceI extends BaseServiceI<T>{

	/**
	 * 
	 * @Title: getGyResponseMsg
	 * @Description: 处理调用管易获取发货单接口返回的字符串, 并返回单据号码不存在的数据
	 * @author 曹梦媛
	 * @date 2018年1月4日 下午16:36:49
	 * @param: @param result_s 调用管易获取发货单接口返回的字符串
	 * @param: @return 
	 * @return:  List<OrderDataZ>  
	 * @throws
	 */
	public List<OrderDataZ> getGyResponseMsg(JSONObject object) throws Exception;

	/**
	 * 
	 * @Title: getGyShops
	 * @Description: 解析调用管易接口获取的店铺列表, 返回List<HashMap>(shop_code 店铺代码, shop_name 店铺名称)
	 * @author 曹梦媛
	 * @date 2018年1月8日 上午9:28:12
	 * @param: @param result_s
	 * @param: @return 
	 * @return: List<HashMap>   
	 * @throws
	 */
	public List<HashMap> getGyShops(Object object);
	
	/**
	 * 
	 * @Title: getZpPrice
	 * @Description: 根据商品代码获取单价
	 * @author 曹梦媛
	 * @date 2018年1月9日 下午2:07:07
	 * @param: @param item_code
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	public String getZpPrice(String item_code);

}
