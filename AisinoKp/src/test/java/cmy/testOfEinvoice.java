package cmy;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import aisino.reportform.util.base.ConfigUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-hibernate.xml"})
public class testOfEinvoice {

	private static final Logger logger = Logger.getLogger(testOfEinvoice.class);

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
	public void EInvoiceService_update_zj_einvoice_interface() {
		eiservice.update_zj_einvoice("f1eb809e-f1a5-4e67-b3b0-0a6253548ca7", "1");//冲红
		
	}
	
	@Test
	public void EInvoiceService_update_zj_einvoice_red() {
		//eiservice.update_zj_einvoice("f1eb809e-f1a5-4e67-b3b0-0a6253548ca7", "1");//冲红
		
		String ohid = "f1eb809e-f1a5-4e67-b3b0-0a6253548ca7";
		String qz_lx="1";
				
		List<List<Object>> obj = new ArrayList<>();
		obj = ohservice.getInvoiceInfoByOhids(ohid);	//获取开票数据
		List<Object> fp = obj.get(0);	//传参获取的开票数据是List<Object> fp
		HashMap fpHead = (HashMap) fp.get(0);
		String tmp_fpzl = fpHead.get("FPZL").toString();
		if ("0".equals(tmp_fpzl)) {
			//专票
			tmp_fpzl="8";
		} else if ("2".equals(tmp_fpzl)) {
			//普票
			tmp_fpzl="7";
		} else if ("51".equals(tmp_fpzl)) {
			//电子发票
			tmp_fpzl="1";
		} else {
			//return "错误的发票种类!";
		}
			
		HashMap hs= (HashMap) fp.get(1);
		List<HashMap> fpMx = (List<HashMap>) hs.get("infoLists");
		String return_s="";	//存储开票结果返回
		String msg="";
		//接口访问地址拼接
		String param_url=ConfigUtil.get("jk_url");	//接口程序安装IP地址:端口号
		

		try {
			URL url=new URL(param_url);
			URLConnection urllConnection=url.openConnection();
			HttpURLConnection httpUrlConnection=(HttpURLConnection) urllConnection;
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Content-type","application/x-www-form-urlencoded");
			
			OutputStream os=httpUrlConnection.getOutputStream();
			
			String params="";
			params += "Action="+URLEncoder.encode("ECXML.FPKJ.BC.E_INV", "UTF-8");	//Action：开票(ECXML.FPKJ.BC.E_INV)
			params += "&SJLY=09";	//SJLY：09-税控组件开票
			params += "&FPLX="+tmp_fpzl;	//FPLX：发票类型(1-增普电 7-增普 8-增专)
			params += "&FPQQLSH="+UUID.randomUUID().toString().replace("-", "");	//FPQQLSH：业务流水号CD161122185412648712
			params += "&KPLX="+qz_lx;	//KPLX：开票类型(0-蓝字发票 1-红字发票)
			if ("1".equals(qz_lx)) {
				//红字
				params += "&YFP_DM="+fpHead.get("FPDM");	//YFP_DM: 原发票代码(红字发票时必须)
				params += "&YFP_HM="+fpHead.get("FPHM");	//YFP_HM: 原发票号码(红字发票时必须)
			} else {
				params += "&YFP_DM=";	//YFP_DM: 原发票代码(红字发票时必须)
				params += "&YFP_HM=";	//YFP_HM: 原发票号码(红字发票时必须)
			}
			BigDecimal jshj = (new BigDecimal(fpHead.get("TOTAL").toString())).setScale(2, RoundingMode.HALF_UP).negate();
			//String jshj = (new BigDecimal(fpHead.get("TOTAL").toString())).setScale(2, RoundingMode.HALF_UP).toString();
			params += "&JSHJ="+URLEncoder.encode(jshj.toString(), "UTF-8");	//JSHJ: 价税合计(单位：元（2位小数）)
			if (fpHead.get("BZ") != null && "0".equals(qz_lx)) {
				params += "&BZ="+URLEncoder.encode(fpHead.get("BZ").toString(), "UTF-8");	//BZ:备注(红字:对应正数发票代码:XXXXXXXXX号码:YYYYYYYY)
			} else {
				params += "&BZ=";
			}
			params += "&FPZT=0";	//FPZT：发票状态 0 正常发票 1 作废发票
			params += "&PDF_XZFS=2";	//PDF_XZFS:发票下载方式 1 发票下载地址 2 发票内容BASE64文件流
			//params += "&XM_COUNT="+fpHead.get("SPHS");	//XM_COUNT:发票明细条数
			
//			int infoNo=fpMx.size();	//明细数
//			if (!"1".equals(qz_lx)) {	//蓝字
//				//for循环拼接商品明细参数
//				for (int i=0; i<infoNo; i++){
//					int j=i+1;
//					if (fpMx.get(i).get("SPMC") != null) {
//						params += "&XMMC"+j+"="+URLEncoder.encode(fpMx.get(i).get("SPMC").toString(), "UTF-8");	//XMMC1：项目名称1
//					} else {
//						params += "&XMMC"+j+"=";
//					}
//					if (fpMx.get(i).get("DW") != null) {
//						params += "&DW"+j+"="+URLEncoder.encode(fpMx.get(i).get("DW").toString(), "UTF-8");	//DW1：计量单位1
//					} else {
//						params += "&DW"+j+"=";
//					}
//					if (fpMx.get(i).get("GGXH") != null) {
//						params += "&GGXH"+j+"="+URLEncoder.encode(fpMx.get(i).get("GGXH").toString(), "UTF-8");	//GGXH1：规格型号1
//					} else {
//						params += "&GGXH"+j+"=";
//					}
//					if (fpMx.get(i).get("SL") != null) {
//						String sl = (new BigDecimal(fpMx.get(i).get("SL").toString())).setScale(6, RoundingMode.HALF_UP).toString();
//						params += "&XMSL"+j+"="+URLEncoder.encode(sl, "UTF-8");	//XMSL1：项目数量1(小数点后6位),即商品数量
//					} else {
//						params += "&XMSL"+j+"=";
//					}
//					if (fpMx.get(i).get("DJ") != null) {
//						String dj = (new BigDecimal(fpMx.get(i).get("DJ").toString())).setScale(6, RoundingMode.HALF_UP).toString();
//						params += "&XMDJ"+j+"="+URLEncoder.encode(dj, "UTF-8");	//XMDJ1：项目单价1(小数点后6位 )
//					} else {
//						params += "&XMDJ"+j+"=";
//					}
//					if (fpMx.get(i).get("JE") != null) {
//						String je = (new BigDecimal(fpMx.get(i).get("JE").toString())).setScale(2, RoundingMode.HALF_UP).toString();
//						params += "&XMJE"+j+"="+URLEncoder.encode(je, "UTF-8");	//XMJE1：项目金额1(单位：元（2位小数）)
//					} else {
//						params += "&XMJE"+j+"=";
//					}
//					BigDecimal slv = new BigDecimal(fpMx.get(i).get("SLV").toString());
//					if ( !fpMx.get(i).get("SLV").toString().startsWith("0.") ) {
//						slv = slv.divide(new BigDecimal("100"), 6, RoundingMode.HALF_UP);
//	//					params += "&SL"+j+"="+slv.divide(new BigDecimal("100"), 6, RoundingMode.HALF_UP);	//SL1：税率1(6位小数，例1%为0.01)
//					} else {
//					}
//					params += "&SL"+j+"="+slv;	//SL1：税率1(6位小数，例1%为0.01)
//					params += "&HSBZ"+j+"="+fpMx.get(i).get("HSBZ");	//HSBZ1：含税标志1(表示项目单价和项目金额是否含税。0表示都不含税)
//					if (!fpMx.get(i).get("JE").toString().startsWith("-")) {
//						params += "&FPHXZ"+j+"=0";	//FPHXZ1：发票行性质1(0正常行、1折扣行)
//					} else {
//						params += "&FPHXZ"+j+"=1";	//FPHXZ1：发票行性质1(0正常行、1折扣行)
//					}
//					params += "&SPBM"+j+"="+fpMx.get(i).get("SSFLBM");	//SPBM1：商品编码1
//					params += "&ZXBM"+j+"=";	//ZXBM1：自行编码1
//					if ("1".equals(tmp_fpzl)) {
//						params += "&YHZCBS"+j+"="+ConfigUtil.get("YHZCBS");	//YHZCBS1：优惠政策标识1(0：不使用，1：使用)
//						if(slv.compareTo(new BigDecimal(0)) == 0) {
//							params += "&LSLBS"+j+"="+ConfigUtil.get("LSLBS");	//LSLBS1：零税率标识1(空：非零税率， 1：免税，2：不征税，3普通零税率)
//						} else {
//							params += "&LSLBS"+j+"=";
//						}
//						params += "&ZZSTSGL"+j+"="+ConfigUtil.get("ZZSTSGL");	//ZZSTSGL1：当YHZCBS为1时必填
//					} else { //纸票
//						params += "&YHZCBS"+j+"=0";	//YHZCBS1：优惠政策标识1(0：不使用，1：使用)
//						if(slv.compareTo(new BigDecimal(0)) == 0) {
//							params += "&LSLBS"+j+"="+ConfigUtil.get("LSLBS");	//LSLBS1：零税率标识1(空：非零税率， 1：免税，2：不征税，3普通零税率)
//						} else {
//							params += "&LSLBS"+j+"=";
//						}
//						params += "&ZZSTSGL"+j+"=";	//ZZSTSGL1：当YHZCBS为1时必填
//					}
//					params += "&KCE"+j+"=";	//KCE1:扣除额1
//	
//				}
//			}
			params += "&TSFS=1";	//TSFS:推送方式（1 电子邮件）
			if (fpHead.get("EMAIL") != null) {
				params += "&EMAIL="+fpHead.get("EMAIL");	//EMAIL:购货方邮箱
			} 
			params += "&RETTYPE=1";	//RETTYPE:结果返回类型(1:json 0:xml)
			 
			os.write(params.getBytes());
			
			InputStream is=httpUrlConnection.getInputStream();
			byte[] b=new byte[1024];
			int len=0;
			String s="";
			while((len=is.read(b))!=-1){
				String ss=new String(b,0,len,"UTF-8");
				s+=ss;
			}
			//System.out.println("返回:"+URLDecoder.decode(s, "UTF-8"));
			return_s=URLDecoder.decode(s, "UTF-8");
			if ("0".equals(qz_lx)) {
				//蓝字
				msg = eiservice.save_einvoice(fpHead.get("OHID").toString(),return_s);	//回写开票信息
			} else {
				//红字
				msg = eiservice.save_einvoice_red(fpHead.get("OHID").toString(),return_s);	//回写开票信息
			}
			
        }  catch (IOException e) {
            e.printStackTrace();
            msg=e.toString();
        }
		
		//return msg;
		
	}
	
}
