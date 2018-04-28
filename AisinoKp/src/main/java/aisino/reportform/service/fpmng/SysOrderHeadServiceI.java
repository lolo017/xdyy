package aisino.reportform.service.fpmng;

import java.math.BigDecimal;
import java.util.List;

import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.xd.OrderInfo;
import aisino.reportform.service.BaseServiceI;

public interface SysOrderHeadServiceI extends BaseServiceI<SysOrderHead>{
	
	/**
	 * 
	 * @Title: mixOrder
	 * @Description: 合定订单
	 * @param: @param ohids
	 * @param: @return 
	 * @return: int   
	 * @throws
	 */
	public int mixOrder(String[] ohids);
	/**
	 * 
	 * @Title: checkOrder
	 * @Description: 通过传入主键值查找系统订单头信息
	 * @param: @param ohid 
	 * @return: void   
	 * @throws
	 */
	List<SysOrderHead> checkOrder(String ohid);

	/**
	 * 
	 * @Title: getInvoiceInfoByOhids
	 * @Description: 根据传入ohids查找带开具发票头信息及发票明细
	 * @param: @param ohids 待开具发票单据头主键(以,分隔)
	 * @param: @return 
	 * @return: List<HashMap>   
	 * @throws
	 */
	List<List<Object>> getInvoiceInfoByOhids(String ohids);

	/**
	 * @param qz_lx 
	 * 
	 * @Title: postPdf
	 * @Description: 推送PDF至吕振宇写的查询下载平台
	 * @param: @param ohid
	 * @param: @param jk_url
	 * @param: @param qz_lx 签章类型 1 红字 0 蓝字
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	String postPdf(String ohid, String jk_url, String qz_lx);
	/**
	 * 
	 * @Title: save_new_orderhead
	 * @Description: 保存新单据
	 * @param: @param ohid
	 * @param: @param odlist
	 * @param: @param kpr 
	 * @return: void   
	 * @throws
	 */
	public void save_new_orderhead(String ohid, List<OrderDataZ> odlist,String kpr);
	

	/**
	 * 
	 * @Title: judgeZfStatus
	 * @Description: 判断oh表的ohid对应记录是否已作废
	 * @param: @param ohid
	 * @param: @return 
	 * @return: boolean   
	 * @throws
	 */
	boolean judgeZfStatus(String ohid);
	
	/**
	 * 
	 * @Title: updateGfxx
	 * @Description: 购方信息匹配
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	public String updateGfxx();
	
}