package aisino.reportform.service.fpmng;

import java.util.List;

import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.V_InvoiceHead;
import aisino.reportform.service.BaseServiceI;

public interface VInvoiceHeadServiceI extends BaseServiceI<V_InvoiceHead>{

	/**
	 * 
	 * @Title: getOrderListGrid
	 * @Description: 查询v_invoiceHead视图
	 * @param: @param buff 查询条件
	 * @param: @param page 页数
	 * @param: @param rows 行数
	 * @param: @param sort 排序字段
	 * @param: @param order 顺序/逆序
	 * @param: @return 
	 * @return: Grid   
	 * @throws
	 */
	Grid getOrderListGrid(StringBuffer buff, int page, int rows, String sort,
			String order);


	/**
	 * 
	 * @Title: getInvoiceGrid
	 * @Description: TODO
	 * @param: @param buff
	 * @param: @param page
	 * @param: @param rows
	 * @param: @param sort
	 * @param: @param order
	 * @param: @return 
	 * @return: Grid   
	 * @throws
	 */
	Grid getInvoiceGrid(StringBuffer buff, int page, int rows, String sort,
			String order);


	/**
	 * 
	 * @Title: updateKpbzAndIz_zf120
	 * @Description: 更新oh和ol表的kpbz,is_zf标志:1->0
	 * @param: String ohid
	 * @return: Json   
	 * @throws
	 */
	Json updateKpbzAndIz_zf120(String ohid);


	/**
	 * 
	 * @Title: judgeOhIs2Order
	 * @Description: 判断ohid对应的发票是否已重新生成单据
	 * @param: @param ohid
	 * @param: @return 
	 * @return: Boolean   
	 * @throws
	 */
	Boolean judgeOhIs2Order(String ohid);

	/**
	 * 
	 * @Title: updateOdKpbz
	 * @Description: 根据传入ohid进行判断涉及的ol表及od表, 若od对应的ol记录都未开票, 将kpbz1->0
	 * @author 曹梦媛
	 * @date 2018年1月2日 上午10:21:49
	 * @param: @param ohid 
	 * @return: void   
	 * @throws
	 */
	void updateOdKpbz(String ohid);

}
