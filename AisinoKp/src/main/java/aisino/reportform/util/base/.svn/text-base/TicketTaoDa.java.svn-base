package aisino.reportform.util.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import aisino.reportform.service.base.TicketTaoDaServiceI;

@Component
public class TicketTaoDa {
	
	@Autowired
	TicketTaoDaServiceI ticketTaoDaService;
	private static TicketTaoDa ticketTaoDa;
	
	@PostConstruct
	public void init() {  
		ticketTaoDa = this;  
		ticketTaoDa.ticketTaoDaService = this.ticketTaoDaService;  
  
    }  

	public static List<Map> LoadTaoDaGS(String id)
	{
		List<Map> iList=new ArrayList();
		String sql="select u.name kpy,t.name fpmc,t.fpdm,lpad(t.fphm,8,'0') fphm,x.ORDER_DATE kprq,z.zfqk "
				+"from t_ticketstore t,T_INVOICE_head x,t_zfqk z,syuser u "
				+"where t.id=x.fp_id and t.id=z.fpid and x.CREATER=u.id and t.id='"+id+"'";
//		String sql="select * from T_PRINTPARAM where ticketmethod='"+method+"'";
		iList=ticketTaoDa.ticketTaoDaService.findBySql(sql);
		return iList;
	}
}
