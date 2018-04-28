
package aisino.reportform.action.base.fpmng;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.fpmng.VInvoiceHeadServiceI;
import aisino.reportform.service.fpmng.dzfp.EInvoiceServiceI;
import aisino.reportform.util.base.ConfigUtil;

/**
 * 
* @Title:EInvoiceAction 
* @Description: 电子发票管理
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年11月22日 下午5:25:27
 */
@Namespace("/base/fpmng")
@Action
public class EInvoiceAction extends BaseAction<SysOrderHead>{
	
	@Autowired
	public OrderDataZServiceI odservice;	//初始订单Service注入
	@Autowired
	public SysOrderHeadServiceI ohservice;	//系统单据Service注入
	@Autowired
	public SysOrderLineServiceI olservice;	//系统单据明细Service注入
	@Autowired
	public VInvoiceHeadServiceI vihservice;	//单据/发票头视图Service注入
	@Autowired
	public EInvoiceServiceI eiservice;	//单据/发票头视图Service注入
	
	/**
	 * 
	 * @Title: postPdf
	 * @Description: 贝誉推送发票PDF接口
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void postPdf() {
		Json json = new Json();
		String ohids = ServletActionContext.getRequest().getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
		String qz_lx=getRequest().getParameter("qz_lx");
		String msg="";
//		String jk_url="http://zooooucom.gotoip1.com/fp/katu_admin/index.php//C_Invoice_Info/upload_invoice";
		String jk_url=ConfigUtil.get("pdf_url");
		
		String rs_suc = "推送PDF success!";
		String rs_fal = "推送PDF false!";
		for (String ohid : ohids.split(",")) {
			String re =  ohservice.postPdf(ohid, jk_url, qz_lx);
			
			if (!msg.contains(rs_suc) && rs_suc.equals(re) ) {
				msg += re+"\n";
			} else if (!msg.contains(rs_fal) && rs_fal.equals(re) ) {
				msg += re+"\n";
			} else if (!rs_suc.equals(re) && !rs_fal.equals(re) ) {
				msg += re+"\n";
			}
		}
		
		json.setMsg(msg);
		json.setSuccess(true);
		writeJson(json);
	}
	
	/**
	 * 
	 * @Title: getInvoiceGrid
	 * @Description: 获取发票列表(电子发票)
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void getInvoiceGrid() {
		Grid grid = new Grid();
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
		
			//初始化不加载数据
			String init_flag=ConfigUtil.get("init_flag");
			String load_flag=request.getParameter("flag");
			if(init_flag.equals("0")&&(!StringUtils.isEmpty(load_flag))){
				List empty=new ArrayList<>();
				grid.setRows(empty);
				grid.setTotal((long) 0);
				//writeJson(grid);
			}else{	
				//条件筛选
				String createtime1 = request.getParameter("createtime1");//创建时间起
				String createtime2 = request.getParameter("createtime2");//创建时间止
				
				//String fpzl = request.getParameter("fpzl");//发票种类 0:专票,2:普票,51:电子发票
				String fpdm = request.getParameter("fpdm");//发票代码
				String fphm = request.getParameter("fphm");//发票号码
		//		String is_zf = request.getParameter("is_zf");//作废标志
				String has_qd = request.getParameter("has_qd");//清单标志
				String is_qz = request.getParameter("is_qz");//清单标志
		//		String is_print = request.getParameter("is_print");//打印标志
				String gfmc = request.getParameter("gfmc");//购方名称
				String djhm = request.getParameter("djhm");//单据号码
			  
				StringBuffer buff = new StringBuffer();
				if(createtime1 != null && !"".equals(createtime1)){
					buff.append( " and t.createtime >= '"+createtime1+"' ");
				}
				if(createtime2 != null && !"".equals(createtime2)){
					buff.append( " and t.createtime < DATEADD(day,1,'"+createtime2+"') ");
				}
				//if(fpzl != null && !"".equals(fpzl)){
					buff.append(" and t.fpzl ='51' ");
				//} 
				if(fpdm != null && !"".equals(fpdm)){
					buff.append(" and (t.fpdm like '%"+ fpdm +"%' or t.red_fpdm like '%"+ fpdm +"%') ");
				} 
				if(fphm != null && !"".equals(fphm)){
					buff.append(" and (t.fphm like '%"+ fphm +"%' or t.red_fphm like '%"+ fphm +"%') ");
				} 
		//		if(is_zf != null && !"".equals(is_zf)){
		//			buff.append(" and t.is_zf ='"+ is_zf +"' ");
		//		} 
				if(has_qd != null && !"".equals(has_qd)){
					buff.append(" and t.has_qd ='"+ has_qd +"' ");
				} 
				if(is_qz != null && !"".equals(is_qz)){
					buff.append(" and (t.is_qz ='"+ is_qz +"' or t.is_qz_h='"+ is_qz +"') ");
				} 
		//		if(is_print != null && !"".equals(is_print)){
		//			buff.append(" and t.is_print ='"+ is_print +"' ");
		//		} 
				if(gfmc != null && !"".equals(gfmc)){
					buff.append( " and t.gfmc like '%"+gfmc+"%' ");
				}
				if(djhm != null && !"".equals(djhm)){
					buff.append( " and t.djhm like '%"+djhm+"%' ");
				}
				
				grid = vihservice.getInvoiceGrid(buff, page, rows, sort, order);
			} 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			writeJson(grid);
		}
			
	}
	
	
	/**
	 * 
	 * @Title: zj_qz
	 * @Description: 调用接口签章/续签
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void zj_qz(){
		Json json = new Json();
		String msg="";
		try {
			
			String ohids = ServletActionContext.getRequest().getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
			String qz_lx = ServletActionContext.getRequest().getParameter("qz_lx");	//签章类型:0-蓝字,1-红字
			//System.out.println("ohids=="+ohids);
			for (String ohid : ohids.split(",")) {
//			String rs = eiservice.zj_qz(ohid);	//调用接口签章(蓝字)
				String rs = eiservice.update_zj_qz(ohid,qz_lx);	//调用接口签章
				msg += (ohid+"返回:"+rs);	//回写签章信息
			}
			
			json.setSuccess(true);
		} catch(Exception e) {
			e.getStackTrace();
			msg+=e.getMessage();
		} finally {
			json.setMsg(msg);
			writeJson(json);
			
		}
	}
	
	
	
	/**
	 * 
	 * @Title: einvoice_red
	 * @Description: 开红票
	 * @param:  
	 * @return: void   
	 * @throws
	 */
//	public void einvoice_red(){
//		Json json = new Json();
//		String msg="";
//		
//		String ohids = ServletActionContext.getRequest().getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
//		
//		//System.out.println("ohids=="+ohids);
//		
//		for (String ohid : ohids.split(",")) {	//单条发票数据
//			SysOrderHead oh = new SysOrderHead();
//			oh = ohservice.getById(ohid);
//			if (oh.getRed_fphm() != null) {
//				//已开过红票, 不允许再次冲红
//				msg += "已冲红发票不允许再次冲红!";
//			} else {
//				//String rs = eiservice.zj_einvoice_red(ohid);	//调用开票接口开红票,并回写数据库
//				//msg += rs;
//			}
//		}
//		
//		
//		json.setSuccess(true);
//		json.setMsg(msg);
//		writeJson(json);
//	}
	
	public void checkOrder(){
		String ohid=getRequest().getParameter("id");
		String type=getRequest().getParameter("type");
		Json json=new Json();
		SysOrderHead oh= ohservice.getById(ohid);
		byte[] pdf_file=null;
		if(type.equals("0")){
			pdf_file=oh.getPdfdata();
		}
		else{
			pdf_file=oh.getPdfdata_h();
		}
		if(pdf_file!=null){
			String pdf_base64=new String(pdf_file);
			
			json.setObj(pdf_base64.replaceAll(" ", "+"));
			json.setSuccess(true);
			writeJson(json);
		}else{
			json.setMsg("未找到pdf文件！");
			json.setSuccess(false);
			writeJson(json);
		}

		
	}
}



