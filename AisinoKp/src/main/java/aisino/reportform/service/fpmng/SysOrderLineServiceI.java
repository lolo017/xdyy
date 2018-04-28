package aisino.reportform.service.fpmng;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.xd.OrderInfo;
import aisino.reportform.service.BaseServiceI;

public interface SysOrderLineServiceI extends BaseServiceI<SysOrderLine>{

	/**
	 * 
	 * @Title: checkOrderDetail
	 * @Description: 根据ohid查找商品明细
	 * @param: @param ohid 订单头id
	 * @param page 页数
	 * @param rows 每页行数
	 * @param: @return 
	 * @return: Grid  
	 * @throws
	 */
	Grid checkOrderDetail(String ohid, int page, int rows);

	/**
	 * 
	 * @Title: getOlidsByOhid
	 * @Description: 根据ohid查找相关olids
	 * @param: @param ohid
	 * @param: @return 
	 * @return: String ohid查找到的olids
	 * @throws
	 */
	String getOlidsByOhid(String ohid);

	/**
	 * 
	 * @Title: updateKPBZ
	 * @Description: 根据olids更新对应的kpbz字段:t_sysorderline和t_orderdata
	 * @param: @param olids
	 * @param: @return 
	 * @return: int   
	 * @throws
	 */
	int updateKPBZ(String olids);
	
	/**
	 * 
	 * @Title: getOdidsByOlids
	 * @Description: 通过olids查找相应odids
	 * @param: @param olids
	 * @param: @return 
	 * @return: String   odids
	 * @throws
	 */
	public String getOdidsByOlids(String olids);
	
	/**
	 * 
	 * @Title: getorderlinelist
	 * @Description: TODO
	 * @param: @param ohid
	 * @param: @return 
	 * @return: List<HashMap>   
	 * @throws
	 */
	public List<HashMap> getorderlinelist(String ohid);
	/**
	 * 
	 * @Title: splitByTax
	 * @Description: 拆分多税率单据
	 * @param: @param string 
	 * @return: void   
	 * @throws
	 */
	public void savesplitByTax(String ohid);
	/**
	 * 
	 * @Title: recoverOrder
	 * @Description: 恢复原始订单
	 * @param: @param ohid 
	 * @return: void   
	 * @throws
	 */
	public void save_recoverOrder(SysOrderHead oh);
	/**
	 * 
	 * @Title: mixOrderGoods
	 * @Description: 商品行汇总
	 * @param: @param ohid
	 * @param: @param b 
	 * @return: void   
	 * @throws
	 */
	public void save_mixOrderGoods(SysOrderHead oh,boolean isNega);
	
	/**
	 * 
	 * @Title: save_new_orderline
	 * @Description: orderdata生成orderline
	 * @param: @param orderlist 
	 * @return: void   
	 * @throws
	 */
	public void save_new_orderline(List<OrderInfo> orderlist,String kpr);
	/**
	 * 
	 * @Title: save_new_OrderheadID
	 * @Description: 根据djhm生成ohid
	 * @param: @param djhm 
	 * @return: void   
	 * @throws
	 */
	public String generate_OrderheadID(String djhm);

	/**
	 * 
	 * @Title: discountE
	 * @Description: 订单折扣
	 * @param: @param ohid
	 * @param: @param rate
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	public String discountE(String ohid, BigDecimal rate);
	
	/**
	 * @return 
	 * 
	 * @Title: updateZkmc
	 * @Description: 根据传入ohid更新对应折扣行名字
	 * @param: @param ohid 
	 * @return: void   
	 * @throws
	 */
	public int updateZkmc(SysOrderHead oh);
	/**
	 * 
	 * @Title: save_NewInvoiceInfo
	 * @Description: 单据中折扣行均摊生成每个商品行折扣后保存
	 * @param: @param oh 
	 * @return: void   
	 * @throws
	 */
	public void save_NewInvoiceInfo(SysOrderHead oh,int zkh_count);
	/**
	 * 
	 * @Title: delete_return_Order
	 * @Description: 删除退回单据数据
	 * @param: @param oh 
	 * @return: void   
	 * @throws
	 */
	public void delete_return_Order(SysOrderHead oh);
	/**
	 * 
	 * @Title: save_splitByLineOver
	 * @Description: 单据中商品行数量超限拆分
	 * @param: @param max_size 商品行数上限
	 * @param: @param ohid
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	public String save_splitByLineOver(int max_size, String ohid);
	/**
	 * @throws Exception 
	 * 
	 * @Title: save_splitOrderByAmount
	 * @Description: 订单超限额拆分
	 * @param: @param ohid 订单号
	 * @param: @param limitAmount 订单限额
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	public String save_splitOrderByAmount(String ohid, String limitAmount) throws Exception;

	/**
	 * 
	 * @Title: updateSsflbm
	 * @Description: 税收分类编码匹配更新
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	String updateSsflbm();
	/**
	 * 
	 * @Title: save_returnOrder
	 * @Description: 退回订单
	 * @param: @param oh 
	 * @return: void   
	 * @throws
	 */
	public void save_returnOrder(SysOrderHead oh);
	/**
	 * @param userName 
	 * 信德医药从中间库生成订单
	 * @Title: save_xd_Order
	 * @Description: TODO
	 * @param: @param odlist 
	 * @return: void   
	 * @throws
	 */
	public int save_xd_Order(List<OrderInfo> odlist, String userName);
	
	/**
	 * 
	 * @Title: save_disocount
	 * @Description: 折扣处理
	 * @param: @param oh
	 * @param: @param zkh_count 
	 * @return: void   
	 * @throws
	 */
	public void save_disocount(SysOrderHead oh, int zkh_count);
	
}
