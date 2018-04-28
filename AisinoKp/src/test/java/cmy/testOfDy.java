package cmy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.fpmng.VInvoiceHeadServiceI;
import aisino.reportform.service.fpmng.dzfp.EInvoiceServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-hibernate.xml"})
public class testOfDy {

	private static final Logger logger = Logger.getLogger(testOfDy.class);

	@Autowired
	public SysOrderHeadServiceI ohservice;	//系统单据Service注入
	@Autowired
	public VInvoiceHeadServiceI vihservice;	//系统单据明细Service注入
	@Autowired
	public SysOrderLineServiceI olservice;	//系统单据明细Service注入
	@Autowired
	public EInvoiceServiceI eiservice;	//单据/发票头视图Service注入
	@Autowired
	public OrderDataZServiceI odservice;
	
	@Test
	public void orderAction_getOrderListGrid() {	//获取单据列表
		Grid grid = new Grid();
		
		//条件筛选:开票时间，发票种类，购方名称，销售单据号，单据金额正负，是否多税率
		String createtime1 = "2017-01-01";	//request.getParameter("createtime1");//创建时间起
		String createtime2 = "2017-12-31";	//request.getParameter("createtime2");//创建时间止
		
		String fpzl = "";	//request.getParameter("fpzl");//发票种类 0:专票,2:普票,51:电子发票
		String gfmc = "";	//request.getParameter("gfmc");//购方名称
		String djhm = "";	//request.getParameter("djhm");//单据号码
		String mix = "";	//request.getParameter("mix");//多税率订单标志 0:否,1:是
		String ddje = "";	//request.getParameter("ddje");//订单金额 1:正数,0:非正
	  
		StringBuffer buff = new StringBuffer();
		if(createtime1 != null && !"".equals(createtime1)){
			buff.append( " and t.createtime >= '"+createtime1+"' ");
		}
		if(createtime2 != null && !"".equals(createtime2)){
			buff.append( " and t.createtime < DATEADD(day,1,'"+createtime2+"') ");
		}
		
		if(gfmc != null && !"".equals(gfmc)){
			buff.append( " and t.gfmc like '%"+gfmc+"%' ");
		}
		if(djhm != null && !"".equals(djhm)){
			buff.append( " and t.djhm like '%"+djhm+"%' ");
		}
		if(mix != null && !"".equals(mix)){
			buff.append(" and t.mix ='"+ mix +"' ");
		} 
		if(fpzl != null && !"".equals(fpzl)){
			buff.append(" and t.fpzl ='"+ fpzl +"' ");
		} 
		if(ddje != null && !"".equals(ddje)){
			if ("1".equals(ddje)) {
				buff.append(" and t.total > 0 ");
			} else if ("0".equals(ddje)) {
				buff.append(" and t.total <= 0 ");
			}
		}
		
//		grid = vihservice.getOrderListGrid(buff, page, rows, sort, order );
		grid = vihservice.getOrderListGrid(buff, 1, 50, "CREATETIME", "desc" );
		System.out.println(grid);
	}
	@Test
	public void invoiceAction_invalidToOrder() {	//生成单据
		Json json = new Json();
		String msg="";
		String ohids="39380196008";//测试用ohids
				
		try {
//			HttpServletRequest request = ServletActionContext.getRequest();
//			request.setCharacterEncoding("utf-8");
//			String ohids = request.getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
			
			for (String ohid : ohids.split(",")) {	//传入单条开票数据
				//1.验证该订单已作废
				boolean zf_flag=false;
				zf_flag = ohservice.judgeZfStatus(ohid);
				if (zf_flag) {
					Boolean is_invalid = true;
					is_invalid = vihservice.judgeOhIs2Order(ohid);
					if (!is_invalid) {
						//2.复制oh,ol记录,并更新ohid为ohid+'_f',更新原oh,ol记录的kpbz:1->0,更新原oh的is_zf:1->0
						json = vihservice.updateKpbzAndIz_zf120(ohid);
						//3.检查涉及的od记录,是否都未开票,是:更新kpbz:1->0
						
						msg = msg + ohid + ":生成单据成功!";
					} else {
						msg = msg + ohid + ":已生成单据不能再次生成!";
					}
					
				} else {
					msg =  msg + ohid + ":发票未作废!";
				}
				msg+="\n";
			}
			
			json.setSuccess(true);
			json.setMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
		} finally {
//			writeJson(json);
		}
	}
	@Test
	public void invoiceAction_invalidToOrder_judgeOhIs2Order() {	//生成单据
		Boolean is_invalid=true;	//初始值,默认
		String ohid="39380196008_3";//测试用ohids
		
		String sql_select="select convert(varchar(255),DJHM) DJHM from v_invoiceHead where OHID = '"+ohid+"'";
		List<HashMap> ls_rs = vihservice.findBySql(sql_select);
		if (ls_rs.get(0).get("DJHM") != null) {
			is_invalid=false;
		} 
		
	}
	@Test
	public void orderAction_einvoice() {	//生成单据
		String ohids="100063765025331000_2";//测试用ohids
		String qz_lx="1";//红字发票
		
		Json json = new Json();
		
		try {
//			HttpServletRequest request = ServletActionContext.getRequest();
//			request.setCharacterEncoding("utf-8");
//		
//
//		String ohids = request.getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
//		String qz_lx = request.getParameter("qz_lx");//获取开票类型:0-蓝票,1-红票
		
		//System.out.println("ohids=="+ohids);
		String msg="";
		for (String ohid : ohids.split(",")) {	//传入单条开票数据
			SysOrderHead oh = new SysOrderHead();
			oh = ohservice.getById(ohid);
			if ("0".equals(qz_lx)) { //蓝票
				String kp_msg = eiservice.update_zj_einvoice(ohid,qz_lx);	//调用接口开票
				msg += ohid+":"+kp_msg;
				
				if ("开票成功!".equals(kp_msg)) {
					//更新初始订单明细t_orderdata的KPBZ字段0->1
					//通过ohid找t_sysorderline的olid,再通过olid找t_orderdata的odid
					String olids = olservice.getOlidsByOhid(ohid);
					int rs = olservice.updateKPBZ(olids);
					if (oh.getFpzl().toString().equals("51")) {
						//电子发票
						msg += eiservice.update_zj_qz(ohid,qz_lx);
					}
				}
				msg+="\n";
			} else if ("1".equals(qz_lx)) {	//红票
				if (oh.getRed_fphm() != null) {
					//已开过红票, 不允许再次冲红
					msg += "已冲红发票不允许再次冲红!\n";
				} else {
					String rs = eiservice.update_zj_einvoice(ohid,qz_lx);	//调用开票接口开红票,并回写数据库
					msg += rs;
					if (oh.getFpzl().toString().equals("51")) {
						//电子发票
						msg += eiservice.update_zj_qz(ohid,qz_lx);
					}
				}
			} else {
				msg="错误的发票了类型!";
			}
			
		}
		
			json.setSuccess(true);
			json.setMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.toString());
			json.setSuccess(false);
		} finally {
			
			//json.setObj(obj);
//			writeJson(json);
		}
		
	}
	
	@Test
	public void ExcltemplateAction_saveExcel2Db() {
		//传入参数,测试
		String filepaths="E:\\apache-tomcat-7.0.77\\webapps\\AisinoReportForm\\upload\\测试111.xlsx";
		String columns="DJHM,SPHM,SPMC,GGXH,DW,SL,DJ,JE,GFMC,GFSH,SKR,FHR,HSBZ,SLV,EMAIL,FPZL,BZ,SSFLBM";
		String tablename="t_orderdata";
		
		Json json = new Json();
		json.setSuccess(true);
		
		List<HashMap> odz_list = new ArrayList<HashMap>();
		odz_list = odservice.saveExcel2List(filepaths,columns);
		//if ("t_orderdata".equals(tablename)) {
			//发票订单信息
			
		//} else {
			//其他表导入, 以HashMap方式存储
			for(HashMap hash : odz_list) {
				String sql_insert="insert into "+tablename+" ("+"odid,"+columns+") ";
				String buff = "'"+UUID.randomUUID().toString()+"',";
				for(String col : columns.split(",")) {
					buff += "'"+hash.get(col)+"',";
				}
				buff = buff.substring(0, buff.lastIndexOf(","));

				sql_insert=sql_insert+"values ("+buff+") ";
//				System.out.println(sql_insert);
				odservice.executeSql(sql_insert);
			}
		//}
	}
	
	@Test
	public void VInvoiceHeadServiceImpl_updateOdKpbz() {
		String ohid = "100448120744379000";	//传入参数
		/**
		 * 1. 检查涉及的od记录,是否都未开票
		 *  1.1 是:更新kpbz:1->0
		 */
		SysOrderHead soh = ohservice.getById(ohid);
		List<SysOrderLine> ols = soh.getSysOrderLines();
		if (ols != null) {
			for (SysOrderLine ol : ols) {
				List<OrderDataZ> ods = ol.getOrderDataZs();
				if (ods != null) {
					for (OrderDataZ od : ods) {
						List<SysOrderLine> ols_tmp = od.getSysOrderlines();
						
					}
				}
				
			}
		}
		
	}
}
