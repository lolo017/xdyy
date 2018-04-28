package aisino.reportform.service.fpmng.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.fpmng.V_InvoiceHead;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.fpmng.VInvoiceHeadServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.BeanUtils;

@Service
public class VInvoiceHeadServiceImpl extends BaseServiceImpl<V_InvoiceHead> implements VInvoiceHeadServiceI {

	@Autowired
	public SysOrderHeadServiceI ohservice;	//系统单据Service注入
	@Autowired
	public SysOrderLineServiceI olservice;	//系统单据明细Service注入
	@Autowired
	public OrderDataZServiceI odservice;
	
	@Override
	public Grid getOrderListGrid(StringBuffer buff, int page, int rows,
			String sort, String order) {
		Grid grid = new Grid();
		if (sort == null || "".equals(sort)) {
			sort = "CREATETIME";
			order = "desc";
		}
		String sql="select top "+rows+" OHID,convert(varchar(255),DJHM) AS DJHM,CREATETIME,FPZL,FPDM,FPHM,KPR,FHR,SKR,"
				+ "GFHM,GFMC,GFSH,GFYHZH,GFDZDH,XFMC,XFSH,XFDZDH,XFYHZH,BZ,MIX,SLV,AMOUNT,TAX_AMOUNT,TOTAL,SPHS,HAS_QD,IS_HX,IS_ZF,ZF_DATE,"
				+ "IS_PRINT,IS_DOWNLOAD,IS_QZ,QZBS,PDF_FILE,IS_TSYX,IS_TSDX,TSFS,ERRLOG,MOBILE,EMAIL, IS_QZ_H, QZBS_H from "
				+ "(select row_number()over(order by "+sort+" "+order+")rownumber,t.* from v_invoiceHead t where t.fphm is null "+buff.toString()+" ) a "
				+ "where rownumber> "+(page-1)*rows+" ";
//		String sql="select t.* from t_sysorderhead t where t.fphm is null "+buff.toString()+" order by "+sort+" "+order+" ";
		String sql_count="select count(1) from t_sysorderhead t where t.fphm is null "+buff.toString();
		
		try {
			List list=findBySql(sql);
			grid.setRows(list);//从系统单据列表获取单据grid
			grid.setTotal(countBySql(sql_count));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grid;
	}

	@Override
	public Grid getInvoiceGrid(StringBuffer buff, int page, int rows, String sort, String order) {
		
		Grid grid = new Grid();
		if (sort == null || "".equals(sort)) {
			sort = "CREATETIME";
			order = "desc";
		}
		String sql="select top "+rows+" OHID,convert(varchar(255),DJHM) AS DJHM,CREATETIME,FPZL,FPDM,FPHM,KPR,FHR,SKR,"
				+ "GFHM,GFMC,GFSH,GFYHZH,GFDZDH,XFMC,XFSH,XFDZDH,XFYHZH,BZ,MIX,SLV,AMOUNT,TAX_AMOUNT,TOTAL,SPHS,HAS_QD,IS_HX,IS_ZF,ZF_DATE,"
				+ "IS_PRINT,IS_DOWNLOAD,IS_QZ,QZBS,PDF_FILE,IS_TSYX,IS_TSDX,TSFS,ERRLOG,MOBILE,EMAIL,IS_QZ_H,QZBS_H,RED_FPDM,RED_FPHM,RED_DATE,TAX_AMOUNT_H from "
				+ "(select row_number()over(order by FPHM "+order+")rownumber,t.* from v_invoiceHead t where t.fphm is not null "+buff.toString()+" ) a "
				+ "where rownumber> "+(page-1)*rows+" ";
		String sql_count="select count(1) from v_invoiceHead t where t.fphm is not null "+buff.toString();
		
		try {
			List list=findBySql(sql);
			grid.setRows(list);//从系统单据列表获取单据grid
			grid.setTotal(countBySql(sql_count));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grid;
	}

	@Override
	public Json updateKpbzAndIz_zf120(String ohid) {
		// TODO 复制oh,ol记录,并更新ohid,更新原oh,ol记录的kpbz:1->0,更新原oh的is_zf:1->0
		Json json = new Json();
		try {
			SysOrderHead soh = new SysOrderHead();
			SysOrderHead new_soh = new SysOrderHead();
			soh = ohservice.getById(ohid);
			
			BeanUtils.copyNotNullProperties(soh, new_soh);
			String new_ohid=olservice.generate_OrderheadID(ohid);	//通过ohid获取新的ohid
			new_soh.setOhid(new_ohid);
			
			List<SysOrderLine> ol_list = olservice.find("from SysOrderLine where ohid='"+ohid+"' ");
			List<SysOrderLine> new_ol_list = new ArrayList<SysOrderLine>();
			for (SysOrderLine ol : ol_list) {
				SysOrderLine new_ol = new SysOrderLine();
				BeanUtils.copyNotNullProperties(ol, new_ol);
				new_ol.setOlid(UUID.randomUUID().toString());
				new_ol.setOrderDataZs(null);
				new_ol.setSysOrderHead(new_soh);
				new_ol_list.add(new_ol);
				
				ol.setCreatetime(new Date());
				ol.setKpbz("0");
				
			}
			new_soh.setSysOrderLines(new_ol_list);
			ohservice.save(new_soh); //复制ol记录,并生成对应oh,保存
			
			//更新原oh,ol记录的kpbz:1->0,更新原oh的is_zf:1->0
			soh.setFpdm(null);
			soh.setFphm(null);
			soh.setCreatetime(new Date());
			soh.setIs_hx("0");
			soh.setIs_zf("0");
			soh.setIs_print("0");
			soh.setErrlog(null);
			soh.setSysOrderLines(ol_list);
			ohservice.update(soh);
			
			json.setSuccess(true);
			json.setMsg("生成单据成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		
		return json;
	}

	@Override
	public Boolean judgeOhIs2Order(String ohid) {
		// TODO Auto-generated method stub
		Boolean is_invalid=true;	//初始值,默认
		String sql_select="select convert(varchar(255),DJHM) DJHM from v_invoiceHead where OHID = '"+ohid+"'";
		List<HashMap> ls_rs = findBySql(sql_select);
		if (ls_rs.get(0).get("DJHM") != null) {
			is_invalid=false;
		} 
		return is_invalid;
	}

	@Override
	public void updateOdKpbz(String ohid) {
		/**
		 * 1. 检查涉及的od记录,是否都未开票
		 *  1.1 是:更新kpbz:1->0
		 */
		SysOrderHead soh = ohservice.getById(ohid);
		List<SysOrderLine> ols = soh.getSysOrderLines();
		Set<String> kpbzSet=new HashSet<>();
		List<OrderDataZ> orderDataZList=new ArrayList<>();
		if (ols != null) {
			for (SysOrderLine ol : ols) {
				List<OrderDataZ> ods = ol.getOrderDataZs();
				if (ods != null) {
					for (OrderDataZ od : ods) {
//						List<SysOrderLine> ols_tmp = od.getSysOrderlines();
						//所有kpbz存入kpbzSet
						kpbzSet.add(od.getKpbz());
						
					}
				}
				//该单据所有od存入orderDataZList
				orderDataZList.addAll(ods);
			}
		}
		//判断一单据中所有ol对应od的kpbz字段是否相同
		if(kpbzSet.size()==1){
			String kpbz="";
			Iterator<String> it=kpbzSet.iterator();
			while(it.hasNext()){
				kpbz=it.next();
			}
			//若相同且kpbz为1
			if(kpbz.equals("1")){
				for(OrderDataZ od:orderDataZList){
					//修改开票标志为0
					od.setKpbz("0");
					odservice.update(od);
				}
			}
		}
		
		
	}
	
	
	
}
