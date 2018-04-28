
package aisino.reportform.action.base.fpmng;


import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.xd.InvoiceHead;
import aisino.reportform.model.xd.OrderInfo;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.fpmng.VInvoiceHeadServiceI;
import aisino.reportform.service.fpmng.dzfp.EInvoiceServiceI;
import aisino.reportform.service.fpmng.xd.InvoiceBodyServiceI;
import aisino.reportform.service.fpmng.xd.InvoiceHeadServiceI;
import aisino.reportform.service.fpmng.xd.OrderInfoServiceI;
import aisino.reportform.util.base.BeanUtils;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.DbUtil;
import aisino.reportform.util.base.StringUtil;

/**
 * 
* @Title:OrderAction 
* @Description: 订单管理
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年9月22日 下午2:29:40
 */
@Namespace("/base/fpmng")
@Action
public class OrderAction extends BaseAction<SysOrderHead>{
	
	@Autowired
	public OrderInfoServiceI odinfoservice; //信德医药初始订单
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
	@Autowired
	public InvoiceHeadServiceI invHeadService; //信德医药回写发票头
	@Autowired
	public InvoiceBodyServiceI invBodyService; //信德医药回写发票明细
	/**
	 * 
	 * @Title: getOrderListGrid
	 * @Description: 单据列表显示
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void getOrderListGrid() {
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
				//条件筛选:开票时间，发票种类，购方名称，销售单据号，单据金额正负，是否多税率
				String createtime1 = request.getParameter("createtime1");//创建时间起
				String createtime2 = request.getParameter("createtime2");//创建时间止
				String shop_name = request.getParameter("shop_name"); //店名
				
				String fpzl = request.getParameter("fpzl");//发票种类 0:专票,2:普票,51:电子发票
				String gfmc = request.getParameter("gfmc");//购方名称
				String djhm = request.getParameter("djhm");//单据号码
				String mix = request.getParameter("mix");//多税率订单标志 0:否,1:是
				String ddje = request.getParameter("ddje");//订单金额 1:正数,0:非正
			  
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
				if(shop_name != null && !"".equals(shop_name)){
					buff.append(" and t.bz like '%"+ shop_name +"%' ");
				} 
				
				grid = vihservice.getOrderListGrid(buff, page, rows, sort, order );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeJson(grid);
			
		}
	}
	
	
	/**
	 * 
	 * @Title: checkOrder
	 * @Description: 根据传来id查看单据头信息
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void checkOrder() {
		Json json = new Json();
		
		String ohid = id;
		List<SysOrderHead> l = ohservice.checkOrder(ohid);
		
		json.setSuccess(true);
		json.setObj(l);
		writeJson(json);
		
	}
	
	
	/**
	 * 
	 * @Title: doNotNeedSecurity_checkOrderDetail
	 * @Description: 根据传来id查看单据头信息
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void doNotNeedSecurity_checkOrderDetail() {
		Grid grid = new Grid();
		
		grid = olservice.checkOrderDetail(id,page,rows);
		
		writeJson(grid);
		
	}
	
	
	/**
	 * 
	 * @Title: editOrderDetail
	 * @Description: 保存修改单据明细信息
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void editOrderDetail(){
		String olids[] = null;
		String ssflbms[]=null;
		String sphms[]=null;
		String spmcs[]=null;
		String ggxhs[]=null;
		String dws[]=null;
		String sls[]=null;
		String slvs[]=null;
		String djs[]=null;
		String jes[]=null;
		//获取页面修改明细的参数
		if(!StringUtils.isBlank(getRequest().getParameter("olids"))){
			olids =getRequest().getParameter("olids").split(",");
			
			if(!StringUtils.isBlank(getRequest().getParameter("sphms").trim())){
				sphms =getRequest().getParameter("sphms").split(",");
			}else{
				sphms=new String[]{""};
			}
			if(!StringUtils.isBlank(getRequest().getParameter("spmcs").trim())){
				spmcs =getRequest().getParameter("spmcs").split(",");
			}else{
				spmcs=new String[]{""};
			}
			if(!StringUtils.isBlank(getRequest().getParameter("ggxhs").trim())){
				ggxhs =getRequest().getParameter("ggxhs").split(",");
			}else{
				ggxhs=new String[]{""};
			}
			if(!StringUtils.isBlank(getRequest().getParameter("dws").trim())){
				dws =getRequest().getParameter("dws").split(",");
			}else{
				dws=new String[]{""};
			}
			if(!StringUtils.isBlank(getRequest().getParameter("sls").trim())){
				sls =getRequest().getParameter("sls").split(",");
			}else{
				sls=new String[]{""};
			}
			if(!StringUtils.isBlank(getRequest().getParameter("slvs").trim())){
				slvs =getRequest().getParameter("slvs").split(",");
			}else{
				slvs=new String[]{""};
			}
			if(!StringUtils.isBlank(getRequest().getParameter("djs").trim())){
				djs =getRequest().getParameter("djs").split(",");
			}else{
				djs=new String[]{""};
			}
			if(!StringUtils.isBlank(getRequest().getParameter("jes").trim())){
				jes =getRequest().getParameter("jes").split(",");
			}else{
				jes=new String[]{""};
			}
//			sphms=getRequest().getParameter("sphms").trim().split(",");
//			spmcs =getRequest().getParameter("spmcs").trim().split(",");
//			ggxhs=getRequest().getParameter("ggxhs").trim().split(",");
//			dws =getRequest().getParameter("dws").trim().split(",");
//			sls=getRequest().getParameter("sls").trim().split(",");
//			slvs =getRequest().getParameter("slvs").trim().split(",");
//			djs=getRequest().getParameter("djs").trim().split(",");
//			jes=getRequest().getParameter("jes").trim().split(",");
			
		}
		if(!StringUtils.isBlank(getRequest().getParameter("ssflbms").trim())){
			ssflbms =getRequest().getParameter("ssflbms").split(",");
		}else{
			ssflbms=new String[]{""};
		}
		
		
		String ohid=getRequest().getParameter("ohid");
		String fpzl=getRequest().getParameter("fpzl");
		String kpr=getRequest().getParameter("kpr");
		String gfmc=getRequest().getParameter("gfmc");
		String gfdzdh=getRequest().getParameter("gfdzdh");
		String gfsh=getRequest().getParameter("gfsh");
		String gfyhzh=getRequest().getParameter("gfyhzh");
		String skr=getRequest().getParameter("skr");
		String fhr=getRequest().getParameter("fhr");
		String mobile=getRequest().getParameter("mobile");
		String email=getRequest().getParameter("email");
		String bz=getRequest().getParameter("bz");
		if(!StringUtils.isBlank(ohid)){
			SysOrderHead orderhead=new SysOrderHead();
			orderhead=ohservice.getByHql("from SysOrderHead where ohid='"+ohid+"'");
			orderhead.setOhid(ohid);
			orderhead.setFpzl(fpzl);
			orderhead.setKpr(kpr);
			orderhead.setGfmc(gfmc);
			orderhead.setGfdzdh(gfdzdh);
			orderhead.setGfsh(gfsh);
			orderhead.setGfyhzh(gfyhzh);
			orderhead.setSkr(skr);
			orderhead.setFhr(fhr);
			orderhead.setMobile(mobile);
			orderhead.setEmail(email);
			orderhead.setBz(bz);
			ohservice.update(orderhead);
		}
		
		try {
			if(olids!=null&&ssflbms!=null){
				for(int i=0;i<olids.length;i++){
					olservice.executeSql("update t_sysorderline set "
							+ "ssflbm='"+ssflbms[i]+"',sphm='"+sphms[i]+"',"
							+ "spmc='"+spmcs[i]+"',ggxh='"+ggxhs[i]+"',"
							+ "dw='"+dws[i]+"',sl='"+sls[i]+"',"
							+ "slv='"+slvs[i]+"',dj='"+djs[i]+"',"
							+ "je='"+jes[i]+"'where olid='"+olids[i]+"'");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Json json=new Json();
		json.setMsg("保存成功！");
		json.setSuccess(true);
		
		writeJson(json);
	}
	
	
	/**
	 * 
	 * @Title: invoice
	 * @Description: 选中订单开具发票
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void invoice() {
		Json json = new Json();
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");

			String ohids = request.getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
			
			//System.out.println("ohids=="+ohids);
			List<List<Object>> obj = new ArrayList<>();
			obj = ohservice.getInvoiceInfoByOhids(ohids);
			
			json.setSuccess(true);
			json.setObj(obj);	//返回待开票数据
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			json.setSuccess(false);
		} finally {
			writeJson(json);
		}
	}
	
	/**
	 * 
	 * @Title: doNotNeedSecurity_updateInvoice
	 * @Description: 作废/开票成功/查询成功后获取返回参数, 更新数据库信息
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void doNotNeedSecurity_updateInvoice() {
		Json json = new Json();
		try {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			
			//获取前台返回数据
			String errlog = request.getParameter("errlog");	//错误描述信息
			String createtime = request.getParameter("createtime").replace("+0800", "+08:00");	//开票时间	
			String fpdm = request.getParameter("fpdm");	//发票代码
			String fphm = request.getParameter("fphm");	//发票号码
			String fpzl = request.getParameter("fpzl");	//发票种类
			String amount = request.getParameter("amount");	//不含税金额
			String tax_amount = request.getParameter("tax_amount");	//税额
			String xml = request.getParameter("xml");	//除以上信息外的其他信息,xml格式
			String ohid = request.getParameter("ohid");	//订单头主键
			
			String is_print = request.getParameter("is_print");	//打印标志
			String is_zf = request.getParameter("is_zf");	//作废标志
			
			String xfmc = "";
			String xfsh = "";
			String xfdzdh = "";
			String xfyhzh = "";
			String qdbz = "";
			String kpr = "";
			//解析info<xml>
			if (xml != null && !"".equals(xml)) {
				SAXReader sax = new SAXReader();
				Document xmlDoc;
				xmlDoc = sax.read(new ByteArrayInputStream(xml
						.getBytes("GBK")));
				Element root = xmlDoc.getRootElement();
				xfmc = root.element("XFMC").getText();
				xfsh = root.element("XFSH").getText();
				xfdzdh = root.element("XFDZDH").getText();
				xfyhzh = root.element("XFYHZH").getText();
				qdbz = root.element("QDBZ").getText();
				kpr = root.element("KPR").getText();
			}
			
			SysOrderHead oh = new SysOrderHead();
			oh = ohservice.getById(ohid);
			
			if(createtime != null && !"".equals(createtime)){
				SimpleDateFormat sdf;
				Date date = new Date();
				if (createtime.contains("GMT")) {
					sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z",Locale.ENGLISH);
					date = sdf.parse(createtime);
				} else if (createtime.contains("UTC")) {
					createtime = createtime.replace("UTC", "GMT");
					sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy",Locale.ENGLISH);
					date = sdf.parse(createtime);
				}
				oh.setCreatetime(date);
				//System.out.println(date);
			}
			
			if(xfmc != null && !"".equals(xfmc)){
				oh.setXfmc(xfmc);
			}
			if(xfsh != null && !"".equals(xfsh)){
				oh.setXfsh(xfsh);
			}
			if(xfyhzh != null && !"".equals(xfyhzh)){
				oh.setXfyhzh(xfyhzh);
			}
			if(xfdzdh != null && !"".equals(xfdzdh)){
				oh.setXfdzdh(xfdzdh);
			}
			if(fpdm != null && !"".equals(fpdm)){
				oh.setFpdm(fpdm);
			}
			if(fphm != null && !"".equals(fphm)){
				oh.setFphm(fphm);
			}
			if(kpr != null && !"".equals(kpr)){
				oh.setKpr(kpr);
			}
			if(qdbz != null && !"".equals(qdbz)){
				oh.setHas_qd(qdbz);
			}
			if(amount != null && !"".equals(amount) && tax_amount != null && !"".equals(tax_amount)){
				BigDecimal b_amount = new BigDecimal(amount);
				BigDecimal b_tax_amount = new BigDecimal(tax_amount);
				oh.setAmount(b_amount);
				oh.setTax_amount(b_tax_amount);
				BigDecimal total = b_amount.add(b_tax_amount);
				oh.setTotal(total);
			}
			if(errlog != null && !"".equals(errlog)){
				oh.setErrlog(errlog);
			}
			if(is_print != null && !"".equals(is_print)){
				if ("1".equals(is_print)) {
					oh.setIs_print(is_print);
				}
			}
			if(is_zf != null && !"".equals(is_zf)){
				if ("1".equals(is_zf)) {
					oh.setIs_zf(is_zf);
//					oh.setFpdm(null);
//					oh.setFphm(null);
					oh.setIs_hx("0"); //回写标志: 0-未回写
					oh.setZf_date(new Date());
					//删除原纪录
					List<SysOrderLine> olist=oh.getSysOrderLines();
					for(SysOrderLine ol:olist){
						olservice.delete(ol);
					}
				}
			}
			//System.out.println("total=amount"+amount+"+tax_amount"+tax_amount+"="+amount+tax_amount);
			//更新订单头信息
			ohservice.update(oh);
			//更新初始订单明细t_orderdata的KPBZ字段0->1
			//通过ohid找t_sysorderline的olid,再通过olid查t_order_line找t_orderdata的odid
			String olids = olservice.getOlidsByOhid(ohid);
			int rs = olservice.updateKPBZ(olids);
			//信德医药回写
			List<SysOrderLine> ols=oh.getSysOrderLines();
			DbUtil.setDb("2");
			invHeadService.save_rewrite_xd(oh,ols);
			DbUtil.setDb("1");
//			System.out.println("XFMC:"+XFMC);
			json.setSuccess(true);
			json.setMsg("单据"+ohid+"更新"+rs+"条明细!");
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		} finally {
			writeJson(json);
		}
		
	}
	
	
	/**
	 * 
	 * @Title: doNotNeedSecurity_updateErrLog
	 * @Description: 开票失败后获取失败原因, 并更新数据库
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void doNotNeedSecurity_updateErrLog() {
		Json json = new Json();
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			request.setCharacterEncoding("utf-8");
		
			String errlog = request.getParameter("errlog");	//错误描述信息
			String ohid = request.getParameter("ohid");	//订单头主键
			
			SysOrderHead oh = new SysOrderHead();
			oh = ohservice.getById(ohid);
			oh.setErrlog(errlog);
			
			//更新订单头信息
			ohservice.update(oh);
			
			json.setSuccess(true);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			json.setSuccess(false);
			
		} finally {
			writeJson(json);
		}
		
	}
	
	
	/**
	 * 
	 * @Title: einvoice
	 * @Description: 选中订单开具发票
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void einvoice() {
		Json json = new Json();
		
		HttpServletRequest request = getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		

		String ohids = request.getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
		String qz_lx = request.getParameter("qz_lx");//获取开票类型:0-蓝票,1-红票
		
		//System.out.println("ohids=="+ohids);
		String msg="";
		
		for (String ohid : ohids.split(",")) {	//传入单条开票数据
			SysOrderHead oh = ohservice.getById(ohid);
//			oh = ohservice.getById(ohid);
			if ("0".equals(qz_lx)) { //蓝票
				String kp_msg = eiservice.update_zj_einvoice(ohid,qz_lx);	//调用接口开票
				msg += ohid+":"+kp_msg;
				
				if (!("开票成功!".equals(kp_msg))) {
					//更新初始订单明细t_orderdata的KPBZ字段0->1
					//通过ohid找t_sysorderline的olid,再通过olid找t_orderdata的odid
//					String olids = olservice.getOlidsByOhid(ohid);
//					int rs = olservice.updateKPBZ(olids);
					//信德回写
					
					List<SysOrderLine> ols=oh.getSysOrderLines();
					DbUtil.setDb("2");
					invHeadService.save_rewrite_xd(oh,ols);
					DbUtil.setDb("1");
					if (oh.getFpzl().toString().equals("51")) {
						//电子发票签章
						//msg += eiservice.update_zj_qz(ohid,qz_lx);
					}
				}
				msg+="\n";
			} else if ("1".equals(qz_lx)) {	//红票
				if (oh.getRed_fphm() != null) {
					//已开过红票, 不允许再次冲红
					msg += ohid+":"+"已冲红发票不允许再次冲红!\n";
				} else {
					
					olservice.save_mixOrderGoods(oh,false);//开红票前进行商品行汇总
					//String rs = eiservice.update_zj_einvoice(ohid,qz_lx);	//调用开票接口开红票,并回写数据库
					String rs=eiservice.save_redinvoice(oh,qz_lx);//构建开红票请求，并回写
					msg += ohid+":"+rs;
					if ("开票成功!".equals(rs) || "冲红成功!".equals(rs)) {
						if (oh.getFpzl().toString().equals("51")) {
							//电子发票
							//msg += eiservice.update_zj_qz(ohid,qz_lx);
						}
					}
				}
			} else {
				msg="错误的发票类型!";
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
			writeJson(json);
		}
	}
	/**
	 * 
	 * @Title: mixOrder
	 * @Description: 合并订单
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void mixOrder(){
		String msg="";
		Json json=new Json();
		
		String ohidstr = getRequest().getParameter("ohids");
		ohidstr=ohidstr.replace("[", "").replace("]", "").replace("\"", "");
		String ohids[]=ohidstr.split(",");
		
		json.setMsg("合并生成"+ohservice.mixOrder(ohids)+"张新单据");
		json.setSuccess(true);
		writeJson(json);
	}
	/**
	 * 
	 * @Title: initOrder
	 * @Description: 恢复订单
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void initOrder(){
		Json json=new Json();
		String ohidstr = getRequest().getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");
		String ohids[]=ohidstr.split(",");
		int total=0;
		for(int i=0;i<ohids.length;i++){
			String ohid=ohids[i];
			//获取订单所有商品行
			List<SysOrderLine> list =olservice.find("from SysOrderLine where OHID='"+ohid+"'");
			//判断是否含有开票标志为1的单据
			String kpdj="";
			kpdj=odservice.checkOrderKP(list);
			if(kpdj!=""){
				json.setMsg("单据号"+kpdj+"含有已开票数据，不能恢复！");
				json.setSuccess(false);
				writeJson(json);
				return;
			}else
			{
				olservice.save_recoverOrder(ohservice.getById(ohid));
			}
		}
		json.setMsg("单据恢复完成！");
		json.setSuccess(true);
		writeJson(json);
	}
	/**
	 * 
	 * @Title: splitByTax
	 * @Description: 多税率单据拆分
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void splitByTax(){
		String ohidstr = getRequest().getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");
		String ohids[]=ohidstr.split(",");
		for(int i=0;i<ohids.length;i++){
			olservice.savesplitByTax(ohids[i]);
		}
	}
	/**
	 * 
	 * @Title: xd_generateOrder
	 * @Description: 简阳信德orderhead生成
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void xd_generateOrder(){
//		Long start=new Date().getTime();
		Json json=new Json();
		//切换至中间库数据源，获取开票数据
		DbUtil.setDb("2");
		List<OrderInfo> odlist=odinfoservice.find("from OrderInfo where hqsj is null and is_return='0' ");
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String userName = sessionInfo.getUser().getName();
		DbUtil.setDb("1");
		json.setSuccess(true);
		int count=olservice.save_xd_Order(odlist,userName);
		if(count>0){
			//修改中间库订单获取时间字段
			DbUtil.setDb("2");
			for(OrderInfo odi:odlist){
				odi.setHqsj(new Date());
				odinfoservice.update(odi);
			}
			DbUtil.setDb("1");
		}
		json.setMsg("生成"+count+"张开票单据！");
		writeJson(json);
//		Long end=new Date().getTime();
//		System.out.println(end-start);
	}
	
	/**
	 * 
	 * @Title: mixOrderGoods
	 * @Description: 商品行汇总
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void mixOrderGoods() {
		String ohidstr = getRequest().getParameter("ohids");
		if(!StringUtils.isBlank(ohidstr)){
			ohidstr=ohidstr.replace("[", "").replace("]", "").replace("\"", "");
			String ohids[]=ohidstr.split(",");
			
			for(int i=0;i<ohids.length;i++){
				String ohid=ohids[i];
				SysOrderHead orderhead=ohservice.getById(ohid);
				olservice.save_mixOrderGoods(orderhead,false);
			}
		}
		Json json=new Json();
		json.setMsg("汇总完成！");
		json.setSuccess(true);
		writeJson(json);
	}
	
	/**
	 * 
	 * @Title: discountByValue
	 * @Description: 汇总商品行均摊折扣,每行商品都有折扣
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void discountByValue(){
		String ohidstr = getRequest().getParameter("ohids");
		String discountRate = ConfigUtil.get("max_zkl");
		BigDecimal rate=new BigDecimal(discountRate);
		String msg="";
		if(!StringUtils.isBlank(ohidstr)){
			ohidstr=ohidstr.replace("[", "").replace("]", "").replace("\"", "");
			String ohids[]=ohidstr.split(",");
			
			for(int i=0;i<ohids.length;i++){	//单个订单进行折扣处理
				String ohid=ohids[i];
				SysOrderHead oh=ohservice.getById(ohid);
				String flag=olservice.discountE(ohid,rate);	//判断是否可折扣,超过折扣率不允许折扣
				if("1".equals(flag)){
					int zkh_count=olservice.updateZkmc(oh);//折扣行更新，使负数折扣行满足合并条件
					olservice.save_mixOrderGoods(oh,true);	//负数商品行合并
					
					
					olservice.save_NewInvoiceInfo(oh,zkh_count);	//传入的list排序处理, 修改spxh, 并将折扣行均摊处理
					olservice.save_disocount(oh,zkh_count);
				}else{
					msg+=","+flag;
				}
				
			}
		}
		Json json=new Json();
		if(msg!=""){
			json.setMsg(msg.replaceFirst(",", ""));
		}
		
		json.setSuccess(true);
		writeJson(json);
	}/**
	 * 退回订单至中间库
	 * @Title: returnOrder
	 * @Description: TODO
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void returnOrder(){
		Json json=new Json();
		String ohidstr = getRequest().getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");
		String ohids[]=ohidstr.split(",");
		int total=0;
		for(int i=0;i<ohids.length;i++){
			
			String ohid=ohids[i];
			SysOrderHead oh=ohservice.getById(ohid);
			//根据ohid获取关联的orderline
			List<SysOrderLine> ol_list=oh.getSysOrderLines();
//			List<HashMap> list =olservice.getorderlinelist(ohid);
			olservice.save_returnOrder(oh);
			List<String> odid_list=new ArrayList<>();
			for(int j=0;j<ol_list.size();j++){
				SysOrderLine ol=ol_list.get(j);
				odid_list.add(ol.getOlid());
			}
			DbUtil.setDb("2");
			for(String odid:odid_list){
				ohservice.executeSql("update t_orderInfo set is_return='1', hqsj=NULL where odid ='"+odid+"'");
			}
			DbUtil.setDb("1");
			//中间库中退回单据记录
			/*DbUtil.setDb("2");
			for(String djhm:orderDJHMlist){
				ohservice.executeSql("insert into returnOrderInfo (ID,DJHM,THRQ) values('"+UUID.randomUUID().toString()+"','"+djhm+"',getdate())");
			}*/
			
		}
		
		json.setMsg("单据退回完成！");
		json.setSuccess(true);
		writeJson(json);
	}
	/**
	 * 
	 * @Title: splitByLineOver
	 * @Description: 商品行数量超限拆分
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void splitByLineOver(){
		String max_size=getRequest().getParameter("max_size");
		int size=Integer.parseInt(max_size);
		String ohidstr = getRequest().getParameter("ohids");
		if(!StringUtils.isBlank(ohidstr)){
			ohidstr=ohidstr.replace("[", "").replace("]", "").replace("\"", "");
		}
		String ohids[]=ohidstr.split(",");
		for(int i=0;i<ohids.length;i++){
			String ohid=ohids[i];
			String msg=olservice.save_splitByLineOver(size,ohid);
			msg+=msg;
		}
		
		
	}
	/**
	 * 
	 * @Title: splitByAmount
	 * @Description: 单据超限额拆分
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void splitByAmount(){
		String ohidstr = getRequest().getParameter("ohids");
		if(!StringUtils.isBlank(ohidstr)){
			ohidstr=ohidstr.replace("[", "").replace("]", "").replace("\"", "");
		}
		//获取限额
		String limitAmount=getRequest().getParameter("limitAmount");
		limitAmount=limitAmount+"0000";
//		limitAmount="50";
		String ohids[]=ohidstr.split(",");
		for(String ohid:ohids){
			try {
				String result=olservice.save_splitOrderByAmount(ohid,limitAmount);
			} catch (Exception e) {
				e.printStackTrace();
				writeJson(e);
			}
			
		}
		
		Json json=new Json();
		json.setMsg("拆分成功！");
		json.setSuccess(true);
		writeJson(json);
	}
	
	
	/**
	 * 
	 * @Title: ssflbmpp
	 * @Description: 税收分类编码匹配
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void ssflbmpp() {
		Json json=new Json();
		String msg = "";
		msg = olservice.updateSsflbm();
		
        json.setMsg(msg);
		json.setSuccess(true);
		writeJson(json);
	}
	
	/**
	 * 
	 * @Title: gfpp
	 * @Description: 购方信息匹配
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void gfpp() {
		Json json=new Json();
		String msg = "";
		msg = ohservice.updateGfxx();
		
		json.setMsg(msg);
		json.setSuccess(true);
		writeJson(json);
	}
	/**
	 * 
	 * @Title: discountByValue
	 * @Description: 部分折扣
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void discountPartByValue(){
		String ohidstr = getRequest().getParameter("ohids");
		String msg="";
		if(!StringUtils.isBlank(ohidstr)){
			ohidstr=ohidstr.replace("[", "").replace("]", "").replace("\"", "");
			String ohids[]=ohidstr.split(",");
			
			for(int i=0;i<ohids.length;i++){	//单个订单进行折扣处理
				String ohid=ohids[i];
				SysOrderHead oh=ohservice.getById(ohid);
//				String flag=olservice.discountE(ohid,rate);	//判断是否可折扣,超过折扣率不允许折扣
					int zkh_count=olservice.updateZkmc(oh);//折扣行更新，使负数折扣行满足合并条件
					olservice.save_mixOrderGoods(oh,true);	//负数商品行合并
					
//					olservice.save_NewInvoiceInfo(oh,zkh_count);	//传入的list排序处理, 修改spxh, 并将折扣行均摊处理
					olservice.save_disocount(oh,zkh_count);
			}
		}
		Json json=new Json();
		if(msg!=""){
			json.setMsg(msg.replaceFirst(",", ""));
		}
		
		json.setSuccess(true);
		writeJson(json);
	}
	
	public void rewrite(){
		String ohids = getRequest().getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
		List<SysOrderHead> ohlist=new ArrayList<>();
		for (String ohid : ohids.split(",")) {	//传入单条开票数据
				SysOrderHead oh = ohservice.getById(ohid);
				ohlist.add(oh);
			}
		try {
			for(SysOrderHead oh:ohlist){
				if(StringUtils.isNotBlank(oh.getFphm())){
					List<SysOrderLine> ols=oh.getSysOrderLines();
//					ols.addAll(oh.getSysOrderLines());
//					DbUtil.setDb("2");
					invHeadService.save_rewrite_xd(oh,ols);
//					DbUtil.setDb("1");
//					System.out.println(oh.getFphm()+":回写完成！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtil.setDb("1");
		}
		
	}
}



