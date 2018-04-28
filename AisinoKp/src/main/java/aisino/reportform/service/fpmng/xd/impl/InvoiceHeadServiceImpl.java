package aisino.reportform.service.fpmng.xd.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.xd.InvoiceBody;
import aisino.reportform.model.xd.InvoiceHead;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.fpmng.xd.InvoiceBodyServiceI;
import aisino.reportform.service.fpmng.xd.InvoiceHeadServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.BigdecimalUtil;
import aisino.reportform.util.base.DbUtil;
import aisino.reportform.util.base.xd.OrderNumUtil;

@Service
public class InvoiceHeadServiceImpl extends BaseServiceImpl<InvoiceHead> implements InvoiceHeadServiceI{
	
	@Autowired
	private InvoiceBodyServiceI invBodyService;
	@Autowired
	private SysOrderLineServiceI olService;
	@Autowired
	private SysOrderHeadServiceI ohService;

	@Override
	public int save_rewrite_xd(SysOrderHead oh,List<SysOrderLine> ohol_list) throws Exception {
		
		//信德医药发票头信息回写
		InvoiceHead invHead=new InvoiceHead();
		
		invHead.setOrigin(oh.getOrigin());
		invHead.setYwy(oh.getYwy());
		invHead.setFpid(oh.getOhid());
		invHead.setFph(oh.getFphm());
		if(oh.getFpzl().equals("51")){
			invHead.setFtype(new BigDecimal(1));
		}else if(oh.getFpzl().equals("0")){
			invHead.setFtype(new BigDecimal(0));
		}else if(oh.getFpzl().equals("2")){
			invHead.setFtype(new BigDecimal(2));
		}else{
			invHead.setFtype(new BigDecimal(3));
		}
		invHead.setJshj(oh.getTotal());
		invHead.setJe(oh.getAmount());
		invHead.setSe(oh.getTax_amount());
		
		
		invHead.setFprq(oh.getCreatetime());
		invHead.setTjbh(oh.getGfhm());
		invHead.setInvoiceNo(oh.getFpdm());
		invHead.setBz(oh.getBz());
		invHead.setInvoiceamount(new BigDecimal(0));
		invHead.setT_date(new Date());
		
		//回写发票明细信息
//		List<SysOrderLine> ohol_list=oh.getSysOrderLines();
		List<InvoiceBody> invBodyList=new ArrayList<>();
		int i=0;
		for(SysOrderLine ol:ohol_list){
			i++;
			InvoiceBody invBody=new InvoiceBody();
			invBody.setDh(OrderNumUtil.makeOrderNum());
			invBody.setHh(ol.getSphm());
			invBody.setJj(new BigDecimal(0));
			invBody.setDj(ol.getDj());
			invBody.setSl(ol.getSl());
			invBody.setJe(ol.getJe());
			invBody.setSe(new BigDecimal(0));
			invBody.setJshj(ol.getJe());
			invBody.setOutquantity(ol.getSl());
			invBody.setOutamounttax(ol.getJe());
			invBody.setBz(ol.getBz());
			invBody.setFpid(oh.getOhid());
			//invBody.setOutdate(outdate);
			invBody.setCustomerguid(ol.getGfhm());
			invBody.setT_date(new Date());
			invBody.setOutdate(ol.getCreatetime());
			
			invBody.setFenpiaobiaoshi(ol.getFenpiaobiaoshi());
			//批次单号
			invBody.setBatchno(ol.getBatchno());
			//开票人编号
			//invBody.setEmployeeguid();
			//销售日期
			//invBody.setOutdate(outdate);
			invBody.setCustomerguid(ol.getGfhm());
			//销售单号
			//invBody.setQutdetaino(qutdetaino);
			invBodyList.add(invBody);
			//开单时间
			invHead.setKdrq(ol.getCreatetime());
			
		}
		invBodyService.saveList(invBodyList);
		this.save(invHead);
		return 0;
	}
}
