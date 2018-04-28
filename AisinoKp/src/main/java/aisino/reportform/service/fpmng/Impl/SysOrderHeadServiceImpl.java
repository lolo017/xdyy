package aisino.reportform.service.fpmng.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.xd.OrderInfo;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.BigdecimalUtil;

@Service
public class SysOrderHeadServiceImpl extends BaseServiceImpl<SysOrderHead> implements SysOrderHeadServiceI {
	
	
	@Override
	public List<SysOrderHead> checkOrder(String ohid) {
		List<SysOrderHead> ohList = new ArrayList<SysOrderHead>();
		String sql = "select t.* from t_sysorderhead t where  t.ohid='"+ohid+"' ";
		ohList = findBySql(sql);
		return ohList;
	}
	
	@Override
	public List<List<Object>> getInvoiceInfoByOhids(String ohids) {
		List<List<Object>> invoiceLists = new ArrayList<>();
		for (String ohid : ohids.split(",")) {
			
			List<Object> invoiceList = new ArrayList<>();
			
			String sql_head = "select t.FPZL,t.HAS_QD,t.GFSH,t.GFMC,t.GFDZDH,t.GFYHZH,t.KPR,t.SKR"
					+ "t.FHR,t.FPDM,t.FPHM,t.TOTAL,t.BZ,t.SPHS,t.EMAIL,t.OHID from t_sysorderhead t where ohid='"+ohid+"' ";
			invoiceList.add(findBySql(sql_head).get(0));
			
			List<Object> infoLists = new ArrayList<>();
			String sql_line = "select t.SPMC,t.DW,t.GGXH,t.SL,t.DJ,t.JE,t.SLV,t.HSBZ,t.SSFLBM from t_sysorderline t where ohid='"+ohid+"' order by t.spxh asc,t.je desc ";
			
			infoLists.add(findBySql(sql_line));
			HashMap hs = new HashMap<>();
			hs.put("infoLists", infoLists.get(0));
			invoiceList.add(hs);
			
			invoiceLists.add(invoiceList);
		}
		
		
		return invoiceLists;
	}
	
	@Override
	public String postPdf(String ohid, String jk_url, String qz_lx) {
		List<List<Object>> obj = new ArrayList<>();
		obj = GetInvoiceInfoByOhids(ohid);
		List<Object> fp = obj.get(0);
		
		SysOrderHead orderhead=(SysOrderHead) fp.get(0);
		HashMap hs= (HashMap) fp.get(1);
		List<HashMap> fpMx = (List<HashMap>) hs.get("infoLists");
		
		try {
			//接口访问地址拼接
			String param_url=jk_url;	//接口程序安装IP地址:端口号
			
			String params="";
			if (orderhead.getGfsh() != null) {
				params += "nsrsbh="+URLEncoder.encode(orderhead.getGfsh().toString(), "UTF-8");	//GMF_NSRSBH: 购买方纳税人识别号
			} else {
				params += "&nsrsbh=";	//GMF_NSRSBH: 购买方纳税人识别号
			}
			if (orderhead.getGfmc() != null) {
				params += "&nsrmc="+URLEncoder.encode(orderhead.getGfmc().toString(), "UTF-8");	//GMF_MC: 购买方名称
			} else {
				params += "&nsrmc=";
			}
			if (orderhead.getBz() != null) {
				params += "&bz="+URLEncoder.encode(orderhead.getBz().toString(), "UTF-8");	//BZ:备注(红字:对应正数发票代码:XXXXXXXXX号码:YYYYYYYY)
			} else {
				params += "&bz=";
			}
			
			params += "&mobile="+orderhead.getMobile();
			byte[] byt=null;
			if ("0".equals(qz_lx)) {	//蓝字发票
				if (orderhead.getPdfdata() == null ) {
					return "蓝字发票"+orderhead.getFphm()+"的PDF文件不存在,请重新签章后再推送!";
				}
				params += "&fpdm="+orderhead.getFpdm();
				params += "&fphm="+orderhead.getFphm();
				params += "&kprq="+orderhead.getCreatetime();
				byt=orderhead.getPdfdata();
			} else if ("1".equals(qz_lx)) {	//红字发票
				if (orderhead.getRed_fphm() == null ) {
					return "蓝字发票"+orderhead.getFphm()+"未冲红!";
				}
				if (orderhead.getPdfdata_h() == null ) {
					return "红字发票"+orderhead.getRed_fphm()+"的PDF文件不存在,请重新签章后再推送!";
				}
				params += "&fpdm="+orderhead.getRed_fpdm();
				params += "&fphm="+orderhead.getRed_fphm();
				params += "&kprq="+orderhead.getRed_date();
				byt=orderhead.getPdfdata_h();
			} else {
				return "错误的发票信息!";
			}
			
			String tmp=new String(byt);
			params += "&pdf="+tmp;
			
			URL url=new URL(param_url);
			URLConnection urllConnection=url.openConnection();
			HttpURLConnection httpUrlConnection=(HttpURLConnection) urllConnection;
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Content-type","application/x-www-form-urlencoded");
			
			OutputStream os=httpUrlConnection.getOutputStream();
			
			os.write(params.getBytes());
			
			InputStream is=httpUrlConnection.getInputStream();
			byte[] b=new byte[1024];
			int len=0;
			String s="";
			while((len=is.read(b))!=-1){
				String ss=new String(b,0,len,"UTF-8");
				s+=ss;
			}
			//System.out.println("返回:"+s);
			//TODO 若要做开票自动推送, 需回写IS_TX**字段0->1, 此功能未做
			if ("{\"result\":1}".equals(s)) {
				return "推送PDF success!";	
			} else {
				return "推送PDF false!";
			}
        }  catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
	}
	
	public List<List<Object>> GetInvoiceInfoByOhids(String ohids) {
		List<List<Object>> invoiceLists = new ArrayList<>();
		for (String ohid : ohids.split(",")) {
			
			List<Object> invoiceList = new ArrayList<>();
			
			String sql_head = "from SysOrderHead where ohid='"+ohid+"' ";
			invoiceList.add(getByHql(sql_head));
			
			List<Object> infoLists = new ArrayList<>();
			String sql_line = "select t.* from t_sysorderline t where ohid='"+ohid+"' order by t.spxh asc,t.je desc ";
			
			infoLists.add(findBySql(sql_line));
			HashMap hs = new HashMap<>();
			hs.put("infoLists", infoLists.get(0));
			invoiceList.add(hs);
			
			invoiceLists.add(invoiceList);
		}
		
		
		return invoiceLists;
	}
	@Override
	public int mixOrder(String[] ohids) {
		List<SysOrderHead> linklist=new ArrayList<>();
		for(int i=0;i<ohids.length;i++){
			
			//根据ohid查询出所有SysOrderHead信息
			SysOrderHead orderhead=getByHql("from SysOrderHead where ohid='"+ohids[i]+"'");
			linklist.add(orderhead);
		}
		outer:
			while(true){
				for(int j=0;j<linklist.size();j++){
					SysOrderHead orderhead=linklist.get(j);
					for(int i=j+1;i<linklist.size();i++){
						if(i!=j){
						SysOrderHead orderhead1=linklist.get(i);
						if(orderhead.compare(orderhead1)){
							
							//计算合并后商品行数
							int sphs=orderhead.getSphs();
							int sphs1=orderhead1.getSphs();
							int sum=sphs+sphs1;
							List<SysOrderLine> newlist=new ArrayList<>();
							newlist.addAll(orderhead.getSysOrderLines());
							newlist.addAll(orderhead1.getSysOrderLines());
							orderhead.setSysOrderLines(newlist);
							orderhead.setSphs(sum);
							
							String tmpstr="";
							//单据金额、税额、税价合并,判断合并后清单标志，多税率标志是否变化
							if(!(orderhead.getSlv().equals(orderhead1.getSlv())&&orderhead.getMix().equals("0"))){
								orderhead.setMix("1");
								tmpstr+=",slv=null,mix="+orderhead.getMix();
							}
							if(sum>8){
								orderhead.setHas_qd("1");
								tmpstr+=",has_qd="+orderhead.getHas_qd();
							}
							if(orderhead.getAmount()!=null){
								if(orderhead1.getAmount()!=null){
									orderhead.setAmount(BigdecimalUtil.add(orderhead.getAmount(), orderhead1.getAmount()));
									BigDecimal tmp=orderhead.getAmount();
									
									if(BigdecimalUtil.add(tmp, new BigDecimal(0)).compareTo(new BigDecimal(0))==0){
										tmpstr+=",amount='0'";
									}else{
										tmpstr+=",amount='"+orderhead.getAmount().toString()+"'";
									}
								}
							}else{
								orderhead.setAmount(orderhead1.getAmount());
							}
							if(orderhead.getTax_amount()!=null){
								if(orderhead1.getTax_amount()!=null){
									orderhead.setTax_amount(BigdecimalUtil.add(orderhead.getTax_amount(), orderhead1.getTax_amount()));
										tmpstr+=",tax_amount='"+orderhead.getTax_amount()+"'";
								}
							}else{
								orderhead.setTax_amount(orderhead1.getTax_amount());
							}
							if(orderhead.getTotal()!=null){
								if(orderhead1.getTotal()!=null){
									orderhead.setTotal(BigdecimalUtil.add(orderhead.getTotal(), orderhead1.getTotal()));
										tmpstr+=",total='"+orderhead.getTotal()+"'";
								}
							}
							else{
								orderhead.setTotal(orderhead1.getTotal());
							}
							
							
							
							//delete orderhead1 
							executeHql("delete SysOrderHead where ohid='"+orderhead1.getOhid()+"'");
							//update orderhead 
							String oldohid=orderhead.getOhid();
							String newohid=UUID.randomUUID().toString();
							
							String updatehql="update t_SysOrderHead set createtime=getdate(),ohid='"+newohid+"',sphs='"+sum+"'"+tmpstr+" where ohid='"+orderhead.getOhid()+"'";
							orderhead.setOhid(newohid);
							//更新orderline表中的ohid
							String sql="update t_sysorderline set ohid='"+orderhead.getOhid()+"' where ohid='"+orderhead1.getOhid()+"' or ohid='"+oldohid+"'";
							executeSql(sql);
							executeSql(updatehql);
							
							linklist.remove(i);
							continue outer;
						}
						}
					}
				}
				break outer;
			}
			
		//计算新产生单据条数
		for(int i=0;i<linklist.size();i++){
			for(int j=0;j<ohids.length;j++){
				if(linklist.get(i).getOhid().equals(ohids[j])){
					linklist.remove(i);
				}
			}
		}
		
		return linklist.size();
	}

	@Override
	public void save_new_orderhead(String ohid, List<OrderDataZ> odlist,String kpr) {
		SysOrderHead oh=new SysOrderHead();
		List<SysOrderLine> ollist=new ArrayList<>();
		List<String> fpzl_list=new ArrayList<>();
		List<BigDecimal> slv_list=new ArrayList<>();
		List<String> skr_list=new ArrayList<>();
		List<String> fhr_list=new ArrayList<>();
		List<String> gfdzdh_list=new ArrayList<>();
		List<String> gfyhzh_list=new ArrayList<>();
		List<String> mobile_list=new ArrayList<>();
		List<String> email_list=new ArrayList<>();
		List<String> bz_list=new ArrayList<>();
		List<String> gfmc_list=new ArrayList<>();
		BigDecimal total=new BigDecimal(0);
		for(OrderDataZ od:odlist){
			if(!fpzl_list.contains(od.getFpzl())){
				fpzl_list.add(od.getFpzl());
			}
			if(!slv_list.contains(od.getSlv())){
				slv_list.add(od.getSlv());
			}
			if(!skr_list.contains(od.getSkr())){
				skr_list.add(od.getSkr());
			}
			if(!fhr_list.contains(od.getFhr())){
				fhr_list.add(od.getFhr());
			}
			if(!gfdzdh_list.contains(od.getGfdzdh())){
				gfdzdh_list.add(od.getGfdzdh());
			}
			if(!gfyhzh_list.contains(od.getGfyhzh())){
				gfyhzh_list.add(od.getGfyhzh());
			}
			if(!mobile_list.contains(od.getMobile())){
				mobile_list.add(od.getMobile());
			}
			if(!email_list.contains(od.getEmail())){
				email_list.add(od.getEmail());
			}
			if(!bz_list.contains(od.getBz())){
				bz_list.add(od.getBz());
			}
			if(!gfmc_list.contains(od.getGfmc())){
				gfmc_list.add(od.getGfmc());
			}
			total=BigdecimalUtil.add(total, od.getJe());
			ollist.addAll(od.getSysOrderlines());
			
			oh.setGfsh(od.getGfsh());
			oh.setGfhm(od.getGfhm());
		}
		
		oh.setSphs(odlist.size());
		oh.setCreatetime(new Date());
		oh.setOhid(ohid);
//		oh.setSysOrderLines(ollist);
		oh.setTotal(total);
		oh.setKpr(kpr);
		oh.setMix("0");
		oh.setIs_zf("0");
		oh.setIs_download("0");
		oh.setIs_qz("0");
		oh.setIs_red("0");
		oh.setIs_hx("0");
		oh.setIs_print("0");
		
		if(fpzl_list.size()==1){
			oh.setFpzl(fpzl_list.get(0));
		}else{
			oh.setFpzl("");
		}
		if(slv_list.size()==1){
			oh.setSlv(slv_list.get(0).toString());
		}else{
			oh.setSlv("");
		}
		if(skr_list.size()==1){
			oh.setSkr(skr_list.get(0));
		}else{
			oh.setSkr("");
		}
		if(gfdzdh_list.size()==1){
			oh.setGfdzdh(gfdzdh_list.get(0));
		}else{
			oh.setGfdzdh("");
		}
		if(fhr_list.size()==1){
			oh.setFhr(fhr_list.get(0));
		}else{
			oh.setFhr("");
		}
		if(gfyhzh_list.size()==1){
			oh.setGfyhzh(gfyhzh_list.get(0));
		}else{
			oh.setGfyhzh("");
		}
		if(mobile_list.size()==1){
			oh.setMobile(mobile_list.get(0));
		}else{
			oh.setMobile("");
		}
		if(email_list.size()==1){
			oh.setEmail(email_list.get(0));
		}else{
			oh.setEmail("");
		}
		if(bz_list.size()==1){
			oh.setBz(bz_list.get(0));
		}else{
			oh.setBz("");
		}
		if(gfmc_list.size()==1){
			oh.setGfmc(gfmc_list.get(0));
		}else{
			oh.setGfmc("");
		}
		if(oh.getFpzl()!="51"&&ollist.size()>8){
			oh.setHas_qd("1");
		}else{
			oh.setHas_qd("0");
		}
		this.save(oh);
		for(SysOrderLine ol:ollist){
			executeSql("update t_sysorderline set ohid='"+ohid+"' where olid='"+ol.getOlid()+"'");
		}
		
	}
	
	
	@Override
	public boolean judgeZfStatus(String ohid) {
		// TODO 判断ohid记录是否已作废
		SysOrderHead soh = new SysOrderHead();
		soh = getById(ohid);
		String is_zf = soh.getIs_zf();
		if("1".equals(is_zf)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String updateGfxx() {
		// TODO Auto-generated method stub
		String sql_update="update t_sysorderhead set GFMC=tmp.GFMC, GFSH=tmp.GFSH, GFDZDH=tmp.DZDH, GFYHZH=tmp.YHZH, EMAIL=tmp.EMAIL, MOBILE=tmp.MOBILE "
				+ "from (select oh.OHID,bm.GFHM,bm.GFMC,bm.GFSH,bm.DZDH,bm.YHZH,bm.EMAIL,bm.MOBILE "
				+ "from t_sysorderhead oh,t_buyerinfo bm "
					+ "where (oh.GFHM=bm.GFHM or (oh.GFMC=bm.GFMC and oh.GFSH=bm.GFSH ))"
				+ ") tmp where t_sysorderhead.OHID=tmp.OHID and t_sysorderhead.FPHM is null ";
		int rs = executeSql(sql_update);
		if (rs > 0) {
			return "更新"+rs+"条单据购方信息!";
		} else {
			return "无更新数据!";
		}
	}

	
	
	
	

	
}
