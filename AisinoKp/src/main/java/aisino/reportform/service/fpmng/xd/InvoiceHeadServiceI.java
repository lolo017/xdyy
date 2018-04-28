package aisino.reportform.service.fpmng.xd;

import java.util.List;

import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.xd.InvoiceHead;
import aisino.reportform.service.BaseServiceI;

public interface InvoiceHeadServiceI extends BaseServiceI<InvoiceHead>{
	public int save_rewrite_xd(SysOrderHead oh,List<SysOrderLine> ols) throws Exception;
}
