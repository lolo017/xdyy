package aisino.reportform.service.fpmng.dzfp;

import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.service.BaseServiceI;

public interface EInvoiceServiceI extends BaseServiceI<SysOrderHead>{

	/**
	 * 
	 * @Title: update_zj_einvoice
	 * @Description: 调用开票服务器(刘经理)v1.0.3进行开票
	 * @param: @param ohid
	 * @param: @param qz_lx	发票类型0-蓝字,1-红字
	 * @return: String 开票结果
	 * @throws
	 */
	String update_zj_einvoice(String ohid, String qz_lx);

	/**
	 * 
	 * @Title: save_einvoice
	 * @Description: 保存发票号码等返回结果
	 * @param: @param ohid
	 * @param: @param rs 
	 * @return: void   
	 * @throws
	 */
	String save_einvoice(String ohid, String rs);

	/**
	 * 
	 * @Title: zj_qz
	 * @Description: 调用组件接口签章
	 * @param: @param ohid
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	//String zj_qz(String ohid);

	/**
	 * 
	 * @Title: save_qzInfo
	 * @Description: 保存签章信息
	 * @param: @param ohid
	 * @param: @param rs 签章信息
	 * @return: String 签章状态   
	 * @throws
	 */
	//String save_qzInfo(String ohid, String rs);

	/**
	 * 
	 * @Title: zj_einvoice_red
	 * @Description: 调用接口开红票
	 * @param: @param ohid
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	//String zj_einvoice_red(String ohid);

	/**
	 * 
	 * @Title: save_einvoice_red
	 * @Description: 开红票返回保存
	 * @param: @param ohid
	 * @param: @param rs 
	 * @return: String   
	 * @throws
	 */
	String save_einvoice_red(String ohid, String rs);

	/**
	 * 
	 * @Title: zj_qz
	 * @Description: 根据ohid和qz_lx进行蓝/红发票签章
	 * @param: @param ohid	单据主键
	 * @param: @param qz_lx	发票类型:0-蓝字,1-红字
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	String update_zj_qz(String ohid, String qz_lx);
	
	/**
	 * 
	 * @Title: save_redinvoice
	 * @Description: 开红票请求
	 * @param: @param oh 
	 * @param: @param qz_lx
	 * @param: @return 
	 * @return: String   
	 * @throws
	 */
	public String save_redinvoice(SysOrderHead oh, String qz_lx);

}
