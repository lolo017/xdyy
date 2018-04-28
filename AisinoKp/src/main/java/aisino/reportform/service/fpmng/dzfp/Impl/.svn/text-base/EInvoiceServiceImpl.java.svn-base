package aisino.reportform.service.fpmng.dzfp.Impl;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.fpmng.dzfp.EInvoiceServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.ConfigUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import freemarker.core.Configurable;

@Service
public class EInvoiceServiceImpl extends BaseServiceImpl<SysOrderHead>
		implements EInvoiceServiceI {
	static String jk_url = ConfigUtil.get("jk_url"); // config.properties的配置

	@Autowired
	private SysOrderHeadServiceI ohservice; // 系统单据Service注入
	@Autowired
	private SysOrderLineServiceI olservice;

	@Override
	public String update_zj_einvoice(String ohid, String qz_lx) {
		List<List<Object>> obj = new ArrayList<>();
		obj = ohservice.getInvoiceInfoByOhids(ohid); // 获取开票数据
		List<Object> fp = obj.get(0); // 传参获取的开票数据是List<Object> fp
		HashMap fpHead = (HashMap) fp.get(0);
		String tmp_fpzl = fpHead.get("FPZL").toString();
		if ("0".equals(tmp_fpzl)) {
			// 专票
			tmp_fpzl = "8";
		} else if ("2".equals(tmp_fpzl)) {
			// 普票
			tmp_fpzl = "7";
		} else if ("51".equals(tmp_fpzl)) {
			// 电子发票
			tmp_fpzl = "1";
		} else {
			return "错误的发票种类!";
		}

		HashMap hs = (HashMap) fp.get(1);
		List<HashMap> fpMx = (List<HashMap>) hs.get("infoLists");
		String return_s = ""; // 存储开票结果返回
		String msg = "";
		// 接口访问地址拼接
		String param_url = jk_url; // 接口程序安装IP地址:端口号

		try {
			URL url = new URL(param_url);
			URLConnection urllConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urllConnection;
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Content-type",
					"application/x-www-form-urlencoded");

			OutputStream os = httpUrlConnection.getOutputStream();

			String params = "";
			params += "Action="
					+ URLEncoder.encode("ECXML.FPKJ.BC.E_INV", "UTF-8"); // Action：开票(ECXML.FPKJ.BC.E_INV)
			params += "&SJLY=09"; // SJLY：09-税控组件开票
			params += "&FPLX=" + tmp_fpzl; // FPLX：发票类型(1-增普电 7-增普 8-增专)
			params += "&FPQQLSH="
					+ UUID.randomUUID().toString().replace("-", ""); // FPQQLSH：业务流水号CD161122185412648712
			params += "&KPLX=" + qz_lx; // KPLX：开票类型(0-蓝字发票 1-红字发票)
			if ("1".equals(fpHead.get("HAS_QD"))) {
				params += "&QD_BZ=1"; // QD_BZ：清单标志(默认为0,1：取清单对应票面内容字段打印到发票票面上)
				params += "&QDXMMC=" + URLEncoder.encode("(详见销货清单)", "UTF-8"); // QDXMMC：清单发票项目名称(清单标识（QD_BZ）为1时必填:
																				// (详见销货清单)，纸质发票超过8行自动开具清单，为0不进行处理)
			} else {
				params += "&QD_BZ=0";
				params += "&QDXMMC=";
			}
			if (fpHead.get("GFSH") != null) {
				params += "&GMF_NSRSBH="
						+ URLEncoder.encode(fpHead.get("GFSH").toString(),
								"UTF-8"); // GMF_NSRSBH: 购买方纳税人识别号
			} else {
				params += "&GMF_NSRSBH="; // GMF_NSRSBH: 购买方纳税人识别号
			}
			if (fpHead.get("GFMC") != null) {
				params += "&GMF_MC="
						+ URLEncoder.encode(fpHead.get("GFMC").toString(),
								"UTF-8"); // GMF_MC: 购买方名称
			} else {
				params += "&GMF_MC=";
			}
			if (fpHead.get("GFDZDH") != null) {
				params += "&GMF_DZDH="
						+ URLEncoder.encode(fpHead.get("GFDZDH").toString(),
								"UTF-8"); // GMF_DZDH= 购买方地址、电话
			} else {
				params += "&GMF_DZDH=";
			}
			if (fpHead.get("GFYHZH") != null) {
				params += "&GMF_YHZH="
						+ URLEncoder.encode(fpHead.get("GFYHZH").toString(),
								"UTF-8"); // GMF_YHZH: 购买方银行账号
			} else {
				params += "&GMF_YHZH=";
			}
			if (fpHead.get("KPR") != null) {
				params += "&KPR="
						+ URLEncoder.encode(fpHead.get("KPR").toString(),
								"UTF-8"); // KPR: 开票人
			} else {
				params += "&KPR=";
			}
			if (fpHead.get("SKR") != null) {
				params += "&SKR="
						+ URLEncoder.encode(fpHead.get("SKR").toString(),
								"UTF-8"); // SKR: 收款人
			} else {
				params += "&SKR=";
			}
			if (fpHead.get("FHR") != null) {
				params += "&FHR="
						+ URLEncoder.encode(fpHead.get("FHR").toString(),
								"UTF-8"); // FHR: 复核人
			} else {
				params += "&FHR=";
			}
			if ("1".equals(qz_lx)) {
				// 红字
				params += "&YFP_DM=" + fpHead.get("FPDM"); // YFP_DM:
															// 原发票代码(红字发票时必须)
				params += "&YFP_HM=" + fpHead.get("FPHM"); // YFP_HM:
															// 原发票号码(红字发票时必须)
			} else {
				params += "&YFP_DM="; // YFP_DM: 原发票代码(红字发票时必须)
				params += "&YFP_HM="; // YFP_HM: 原发票号码(红字发票时必须)
			}
			String jshj = (new BigDecimal(fpHead.get("TOTAL").toString()))
					.setScale(2, RoundingMode.HALF_UP).toString();
			params += "&JSHJ=" + URLEncoder.encode(jshj, "UTF-8"); // JSHJ:
																	// 价税合计(单位：元（2位小数）)
			if (fpHead.get("BZ") != null && "0".equals(qz_lx)) {
				params += "&BZ="
						+ URLEncoder.encode(fpHead.get("BZ").toString(),
								"UTF-8"); // BZ:备注(红字:对应正数发票代码:XXXXXXXXX号码:YYYYYYYY)
			} else {
				params += "&BZ=";
			}
			params += "&FPZT=0"; // FPZT：发票状态 0 正常发票 1 作废发票
			// if ("1".equals(qz_lx)) {
			// params += "&FP_MW="+fpHead.get("FPMW"); //发票密文
			// params += "&JYM="+fpHead.get("JYM"); //校验码
			// }
			params += "&PDF_XZFS=2"; // PDF_XZFS:发票下载方式 1 发票下载地址 2 发票内容BASE64文件流
			params += "&XM_COUNT=" + fpHead.get("SPHS"); // XM_COUNT:发票明细条数

			int infoNo = fpMx.size(); // 明细数
			if (!"1".equals(qz_lx)) { // 蓝字
				// for循环拼接商品明细参数
				for (int i = 0; i < infoNo; i++) {
					int j = i + 1;
					if (fpMx.get(i).get("SPMC") != null) {
						params += "&XMMC"
								+ j
								+ "="
								+ URLEncoder.encode(fpMx.get(i).get("SPMC")
										.toString(), "UTF-8"); // XMMC1：项目名称1
					} else {
						params += "&XMMC" + j + "=";
					}
					if (fpMx.get(i).get("DW") != null) {
						params += "&DW"
								+ j
								+ "="
								+ URLEncoder.encode(fpMx.get(i).get("DW")
										.toString(), "UTF-8"); // DW1：计量单位1
					} else {
						params += "&DW" + j + "=";
					}
					if (fpMx.get(i).get("GGXH") != null) {
						params += "&GGXH"
								+ j
								+ "="
								+ URLEncoder.encode(fpMx.get(i).get("GGXH")
										.toString(), "UTF-8"); // GGXH1：规格型号1
					} else {
						params += "&GGXH" + j + "=";
					}
					if (fpMx.get(i).get("SL") != null) {
						String sl = (new BigDecimal(fpMx.get(i).get("SL")
								.toString())).setScale(6, RoundingMode.HALF_UP)
								.toString();
						params += "&XMSL" + j + "="
								+ URLEncoder.encode(sl, "UTF-8"); // XMSL1：项目数量1(小数点后6位),即商品数量
					} else {
						params += "&XMSL" + j + "=";
					}
					if (fpMx.get(i).get("DJ") != null) {
						String dj = (new BigDecimal(fpMx.get(i).get("DJ")
								.toString())).setScale(6, RoundingMode.HALF_UP)
								.toString();
						params += "&XMDJ" + j + "="
								+ URLEncoder.encode(dj, "UTF-8"); // XMDJ1：项目单价1(小数点后6位
																	// )
					} else {
						params += "&XMDJ" + j + "=";
					}
					if (fpMx.get(i).get("JE") != null) {
						String je = (new BigDecimal(fpMx.get(i).get("JE")
								.toString())).setScale(2, RoundingMode.HALF_UP)
								.toString();
						params += "&XMJE" + j + "="
								+ URLEncoder.encode(je, "UTF-8"); // XMJE1：项目金额1(单位：元（2位小数）)
					} else {
						params += "&XMJE" + j + "=";
					}
					BigDecimal slv = new BigDecimal(fpMx.get(i).get("SLV")
							.toString());
					if (!fpMx.get(i).get("SLV").toString().startsWith("0.")) {
						slv = slv.divide(new BigDecimal("100"), 6,
								RoundingMode.HALF_UP);
						// params += "&SL"+j+"="+slv.divide(new
						// BigDecimal("100"), 6, RoundingMode.HALF_UP);
						// //SL1：税率1(6位小数，例1%为0.01)
					} else {
					}
					params += "&SL" + j + "=" + slv; // SL1：税率1(6位小数，例1%为0.01)
					params += "&HSBZ" + j + "=" + fpMx.get(i).get("HSBZ"); // HSBZ1：含税标志1(表示项目单价和项目金额是否含税。0表示都不含税)
					if (!fpMx.get(i).get("JE").toString().startsWith("-")) {
						params += "&FPHXZ" + j + "=0"; // FPHXZ1：发票行性质1(0正常行、1折扣行)
					} else {
						params += "&FPHXZ" + j + "=1"; // FPHXZ1：发票行性质1(0正常行、1折扣行)
					}
					params += "&SPBM" + j + "=" + fpMx.get(i).get("SSFLBM"); // SPBM1：商品编码1
					params += "&ZXBM" + j + "="; // ZXBM1：自行编码1
//					if ("1".equals(tmp_fpzl)) {
//						params += "&YHZCBS" + j + "="
//								+ ConfigUtil.get("YHZCBS"); // YHZCBS1：优惠政策标识1(0：不使用，1：使用)
//						if (slv.compareTo(new BigDecimal(0)) == 0) {
//							params += "&LSLBS" + j + "="
//									+ ConfigUtil.get("LSLBS"); // LSLBS1：零税率标识1(空：非零税率，
//																// 1：免税，2：不征税，3普通零税率)
//						} else {
//							params += "&LSLBS" + j + "=";
//						}
//						params += "&ZZSTSGL" + j + "="
//								+ ConfigUtil.get("ZZSTSGL"); // ZZSTSGL1：当YHZCBS为1时必填
//					} else { // 纸票
//						params += "&YHZCBS" + j + "=0"; // YHZCBS1：优惠政策标识1(0：不使用，1：使用)
//						if (slv.compareTo(new BigDecimal(0)) == 0) {
//							params += "&LSLBS" + j + "="
//									+ ConfigUtil.get("LSLBS"); // LSLBS1：零税率标识1(空：非零税率，
//																// 1：免税，2：不征税，3普通零税率)
//						} else {
//							params += "&LSLBS" + j + "=";
//						}
//						params += "&ZZSTSGL" + j + "="; // ZZSTSGL1：当YHZCBS为1时必填
//					}
					if(slv.compareTo(new BigDecimal("0")) == 0) {
						//商品税率为0
						params += "&YHZCBS"+j+"="+ConfigUtil.get("YHZCBS");	//YHZCBS1：优惠政策标识1(0：不使用，1：使用)
						params += "&LSLBS"+j+"="+ConfigUtil.get("LSLBS");	//LSLBS1：零税率标识1(空：非零税率， 1：免税，2：不征税，3普通零税率)
						if ("1".equals(ConfigUtil.get("LSLBS"))) {
							params += "&ZZSTSGL"+j+"="+URLEncoder.encode("免税", "UTF-8");	//ZZSTSGL1：增值税特殊管理1
						} else {
							params += "&ZZSTSGL"+j+"=";
						}
					} else {
						params += "&YHZCBS"+j+"=0";	//YHZCBS1：优惠政策标识1(0：不使用，1：使用)
						params += "&LSLBS"+j+"=";	//LSLBS1：零税率标识1(空：非零税率， 1：免税，2：不征税，3普通零税率)
						params += "&ZZSTSGL"+j+"=";	//ZZSTSGL1：增值税特殊管理1
					}
					params += "&KCE" + j + "="; // KCE1:扣除额1

				}
			}
			params += "&TSFS=1"; // TSFS:推送方式（1 电子邮件）
			if (fpHead.get("EMAIL") != null) {
				params += "&EMAIL=" + fpHead.get("EMAIL"); // EMAIL:购货方邮箱
			}
			params += "&RETTYPE=1"; // RETTYPE:结果返回类型(1:json 0:xml)

			os.write(params.getBytes());

			InputStream is = httpUrlConnection.getInputStream();
			byte[] b = new byte[1024];
			int len = 0;
			String s = "";
			while ((len = is.read(b)) != -1) {
				String ss = new String(b, 0, len, "UTF-8");
				s += ss;
			}
			// System.out.println("返回:"+URLDecoder.decode(s, "UTF-8"));
			return_s = URLDecoder.decode(s, "UTF-8");
			if ("0".equals(qz_lx)) {
				// 蓝字
				msg = save_einvoice(fpHead.get("OHID").toString(), return_s); // 回写开票信息
			} else {
				// 红字
				msg = save_einvoice_red(fpHead.get("OHID").toString(), return_s); // 回写开票信息
			}

		} catch (IOException e) {
			e.printStackTrace();
			msg = e.toString();
		}

		return msg;
	}

	@Override
	public String save_einvoice(String ohid, String rs) {
		// TODO Auto-generated method stub
		String msg = "";
		JSONObject obj = (JSONObject) JSON.parseObject(rs);
		JSONObject message = (JSONObject) obj.get("message");
		String value = (String) message.get("value");
		if ("200".equals(value)) {
			msg = "开票成功!";
			JSONObject result = (JSONObject) message.get("result");
			// 开票成功
			String fpdm = result.get("FP_DM").toString();
			String fphm = result.getString("FP_HM").toString();
			String hjse = result.getString("HJSE").toString();
			String fpmw = result.getString("FP_MW").toString();
			String jym = result.getString("JYM").toString();
			String fpqqlsh = result.getString("FPQQLSH").toString();
			// String upload=result.getString("UPLOAD").toString();
			SysOrderHead oh = new SysOrderHead();
			oh = getById(ohid);
			oh.setCreatetime(new Date());
			oh.setFpdm(fpdm);
			oh.setFphm(fphm);
			oh.setTax_amount(new BigDecimal(hjse));
			oh.setFpmw(fpmw);
			oh.setJym(jym);
			oh.setFpqqlsh(fpqqlsh);
			oh.setErrlog("");
			update(oh);

			// String
			// sql="update t_sysorderhead set createtime=getdate(),fpdm='"+fpdm+"',fphm='"+fphm+"',"
			// +
			// "tax_amount='"+hjse+"',fpmw='"+fpmw+"',jym='"+jym+"',fpqqlsh='"+fpqqlsh+"',"
			// + "upload='"+upload+"',errlog='' where ohid='"+ohid+"' ";
			// executeSql(sql);
		} else {
			// 开票失败
			String rst = message.get("result").toString().replace("'", "''");
			msg = rst;
			SysOrderHead oh = new SysOrderHead();
			oh = getById(ohid);
			oh.setErrlog(rst);
			update(oh);
			// String
			// sql="update t_sysorderhead set errlog='"+rst+"' where ohid='"+ohid+"' ";
			// executeSql(sql);
		}
		return msg;
	}

	// @Override
	// public String save_qzInfo(String ohid, String rs) {
	// String msg="";
	// JSONObject obj=(JSONObject) JSON.parseObject(rs);
	// JSONObject message=(JSONObject) obj.get("message");
	// String value=(String) message.get("value");
	// if("200".equals(value)) {
	// JSONObject result=(JSONObject) message.get("result");
	// //签章成功
	// msg="签章成功!";
	// String qzbs=result.get("QZDM").toString();
	// byte[] pdf_file=result.getString("PDF_FILE").toString().getBytes();
	//
	// SysOrderHead
	// orderhead=getByHql("from SysOrderHead where ohid='"+ohid+"'");
	//
	// orderhead.setErrlog("");
	// orderhead.setPdfdata(pdf_file);
	// orderhead.setQzbs(qzbs);
	// orderhead.setIs_qz("1");
	// update(orderhead,true);
	//
	//
	// } else {
	// String result=(String) message.get("result");
	// //失败
	// String qzbs=value;
	// String errlog=result;
	// String
	// sql="update t_sysorderhead set qzbs='"+qzbs+"',errlog='"+errlog+"' where ohid='"+ohid+"' ";
	// executeSql(sql, true);
	// msg=errlog;
	// }
	// return msg;
	// }

	@Override
	public String save_einvoice_red(String ohid, String rs) {
		String msg = "";
		JSONObject obj = (JSONObject) JSON.parseObject(rs);
		JSONObject message = (JSONObject) obj.get("message");
		String value = (String) message.get("value");

		if ("200".equals(value)) {
			JSONObject result = (JSONObject) message.get("result");
			// 开红票成功
			msg = "冲红成功!";
			String fpdm_h = result.get("FP_DM").toString();
			String fphm_h = result.getString("FP_HM").toString();
			String hjse = result.getString("HJSE").toString();
			String fpmw = result.getString("FP_MW").toString();
			String jym = result.getString("JYM").toString();
			String fpqqlsh = result.getString("FPQQLSH").toString();

			SysOrderHead oh = new SysOrderHead();
			oh = getById(ohid);
			oh.setRed_date(new Date());
			oh.setRed_fpdm(fpdm_h);
			oh.setRed_fphm(fphm_h);
			oh.setTax_amount_h(new BigDecimal(hjse));
			oh.setFpmw_h(fpmw);
			oh.setJym_h(jym);
			oh.setFpqqlsh_h(fpqqlsh);
			oh.setErrlog("");
			oh.setIs_red("1");
			update(oh);

			// String
			// sql="update t_sysorderhead set red_fpdm='"+fpdm_h+"',red_fphm='"+fphm_h+"',"
			// +
			// "tax_amount_h='"+hjse+"',fpmw_h='"+fpmw+"',jym_h='"+jym+"',fpqqlsh_h='"+fpqqlsh+"',"
			// + "red_date=getdate() "
			// + " where ohid='"+ohid+"' ";
			//
			// executeSql(sql);
		} else {
			// 开票失败
			String rst = message.get("result").toString();
			msg = rst;
			SysOrderHead oh = new SysOrderHead();
			oh = getById(ohid);
			oh.setErrlog(rst);
			update(oh);
			// String
			// sql="update t_sysorderhead set errlog='"+rst+"' where ohid='"+ohid+"' ";
			// executeSql(sql);
		}
		return msg;
	}

	@Override
	public String update_zj_qz(String ohid, String qz_lx) {
		List<List<Object>> obj = new ArrayList<>();
		obj = ohservice.getInvoiceInfoByOhids(ohid);
		List<Object> fp = obj.get(0);
		HashMap fpHead = (HashMap) fp.get(0);
		// System.out.println(fpHead.get("TOTAL"));
		HashMap hs = (HashMap) fp.get(1);
		List<HashMap> fpMx = (List<HashMap>) hs.get("infoLists");
		String return_s = ""; // 存储开票结果返回
		String msg = "";

		// 接口访问地址拼接
		String param_url = jk_url; // 接口程序安装IP地址:端口号

		try {
			URL url = new URL(param_url);
			URLConnection urllConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urllConnection;
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Content-type",
					"application/x-www-form-urlencoded");

			OutputStream os = httpUrlConnection.getOutputStream();

			String params = "";
			params += "Action="
					+ URLEncoder.encode("ECXML.FPQZ.BC.E.INV", "UTF-8"); // Action：开票(ECXML.FPKJ.BC.E_INV)
			params += "&SJLY=09"; // SJLY：09-税控组件开票
			params += "&FPLX=1"; // FPLX：发票类型(1-增普电 7-增普 8-增专)
			params += "&FPQQLSH="
					+ UUID.randomUUID().toString().replace("-", ""); // FPQQLSH：业务流水号CD161122185412648712
			params += "&KPLX=" + qz_lx; // KPLX：开票类型(0-蓝字发票 1-红字发票)
			params += "&BMB_BBH=1.0";
			if ("1".equals(fpHead.get("HAS_QD"))) {
				params += "&QD_BZ=1"; // QD_BZ：清单标志(默认为0,1：取清单对应票面内容字段打印到发票票面上)
				params += "&QDXMMC=" + URLEncoder.encode("(详见销货清单)", "UTF-8"); // QDXMMC：清单发票项目名称(清单标识（QD_BZ）为1时必填:
																				// (详见销货清单)，纸质发票超过8行自动开具清单，为0不进行处理)
			} else {
				params += "&QD_BZ=0";
				params += "&QDXMMC=";
			}
			if (fpHead.get("XFSH") != null) {
				params += "&XSF_NSRSBH="
						+ URLEncoder.encode(fpHead.get("XFSH").toString(),
								"UTF-8"); // XSF_NSRSBH: 销售方纳税人识别号
			} else {
				params += "&XSF_NSRSBH="; // GMF_NSRSBH: 购买方纳税人识别号
			}
			if (fpHead.get("XFMC") != null) {
				params += "&XSF_MC="
						+ URLEncoder.encode(fpHead.get("XFMC").toString(),
								"UTF-8"); // XSF_MC: 销售方名称
			} else {
				params += "&XSF_MC=";
			}
			if (fpHead.get("XFDZDH") != null) {
				params += "&XSF_DZDH="
						+ URLEncoder.encode(fpHead.get("XFDZDH").toString(),
								"UTF-8"); // XSF_DZDH: 销售方地址、电话
			} else {
				params += "&XSF_DZDH=";
			}
			if (fpHead.get("XFYHZH") != null) {
				params += "&XSF_YHZH="
						+ URLEncoder.encode(fpHead.get("XFYHZH").toString(),
								"UTF-8"); // XSF_YHZH: 销售方银行账号
			} else {
				params += "&XSF_YHZH=";
			}
			if (fpHead.get("GFSH") != null) {
				params += "&GMF_NSRSBH="
						+ URLEncoder.encode(fpHead.get("GFSH").toString(),
								"UTF-8"); // GMF_NSRSBH: 购买方纳税人识别号
			} else {
				params += "&GMF_NSRSBH="; // GMF_NSRSBH: 购买方纳税人识别号
			}
			if (fpHead.get("GFMC") != null) {
				params += "&GMF_MC="
						+ URLEncoder.encode(fpHead.get("GFMC").toString(),
								"UTF-8"); // GMF_MC: 购买方名称
			} else {
				params += "&GMF_MC=";
			}
			if (fpHead.get("GFDZDH") != null) {
				params += "&GMF_DZDH="
						+ URLEncoder.encode(fpHead.get("GFDZDH").toString(),
								"UTF-8"); // GMF_DZDH= 购买方地址、电话
			} else {
				params += "&GMF_DZDH=";
			}
			if (fpHead.get("GFYHZH") != null) {
				params += "&GMF_YHZH="
						+ URLEncoder.encode(fpHead.get("GFYHZH").toString(),
								"UTF-8"); // GMF_YHZH: 购买方银行账号
			} else {
				params += "&GMF_YHZH=";
			}
			if (fpHead.get("KPR") != null) {
				params += "&KPR="
						+ URLEncoder.encode(fpHead.get("KPR").toString(),
								"UTF-8"); // KPR: 开票人
			} else {
				params += "&KPR=";
			}
			if (fpHead.get("SKR") != null) {
				params += "&SKR="
						+ URLEncoder.encode(fpHead.get("SKR").toString(),
								"UTF-8"); // SKR: 收款人
			} else {
				params += "&SKR=";
			}
			if (fpHead.get("FHR") != null) {
				params += "&FHR="
						+ URLEncoder.encode(fpHead.get("FHR").toString(),
								"UTF-8"); // FHR: 复核人
			} else {
				params += "&FHR=";
			}
			if ("1".equals(qz_lx)) {
				// 红字发票
				params += "&YFP_DM=" + fpHead.get("FPDM"); // YFP_DM:
															// 原发票代码(红字发票时必须)
				params += "&YFP_HM=" + fpHead.get("FPHM"); // YFP_HM:
															// 原发票号码(红字发票时必须)
			}
			String jshj = (new BigDecimal(fpHead.get("TOTAL").toString()))
					.setScale(2, RoundingMode.HALF_UP).toString();
			params += "&JSHJ=" + URLEncoder.encode(jshj, "UTF-8"); // JSHJ:
																	// 价税合计(单位：元（2位小数）)
			String hjse = (new BigDecimal(fpHead.get("TAX_AMOUNT").toString()))
					.setScale(2, RoundingMode.HALF_UP).toString();
			params += "&HJSE=" + URLEncoder.encode(hjse, "UTF-8"); // HJSE:
																	// 合计税额(单位：元（2位小数）)
			if (fpHead.get("BZ") != null) {
				params += "&BZ="
						+ URLEncoder.encode(fpHead.get("BZ").toString(),
								"UTF-8"); // BZ:备注(红字:对应正数发票代码:XXXXXXXXX号码:YYYYYYYY)
			} else {
				params += "&BZ=";
			}
			params += "&FPZT=0"; // FPZT：发票状态 0 正常发票 1 作废发票
			params += "&JQBH=";
			if ("1".equals(qz_lx)) {
				// 红字发票
				params += "&FP_DM=" + fpHead.get("RED_FPDM"); // 发票代码
				params += "&FP_HM=" + fpHead.get("RED_FPHM"); // 发票号码
			} else {
				params += "&FP_DM=" + fpHead.get("FPDM"); // 发票代码
				params += "&FP_HM=" + fpHead.get("FPHM"); // 发票号码
			}
			params += "&KPRQ="; // 开票日期
			// params += "&FP_MW="+fpHead.get("FPMW"); //发票密文
			// params += "&JYM="+fpHead.get("JYM"); //校验码
			params += "&EWM="; // 二维码
			params += "&PDF_XZFS=2"; // PDF_XZFS:发票下载方式 1 发票下载地址 2 发票内容BASE64文件流
			params += "&XM_COUNT=" + fpHead.get("SPHS"); // XM_COUNT:发票明细条数

			int infoNo = fpMx.size(); // 明细数
			// for循环拼接商品明细参数
			for (int i = 0; i < infoNo; i++) {
				int j = i + 1;
				if (fpMx.get(i).get("SPMC") != null) {
					params += "&XMMC"
							+ j
							+ "="
							+ URLEncoder.encode(fpMx.get(i).get("SPMC")
									.toString(), "UTF-8"); // XMMC1：项目名称1
				} else {
					params += "&XMMC" + j + "=";
				}
				if (fpMx.get(i).get("DW") != null) {
					params += "&DW"
							+ j
							+ "="
							+ URLEncoder.encode(fpMx.get(i).get("DW")
									.toString(), "UTF-8"); // DW1：计量单位1
				} else {
					params += "&DW" + j + "=";
				}
				if (fpMx.get(i).get("GGXH") != null) {
					params += "&GGXH"
							+ j
							+ "="
							+ URLEncoder.encode(fpMx.get(i).get("GGXH")
									.toString(), "UTF-8"); // GGXH1：规格型号1
				} else {
					params += "&GGXH" + j + "=";
				}
				if (fpMx.get(i).get("SL") != null) {
					String sl = (new BigDecimal(fpMx.get(i).get("SL")
							.toString())).setScale(6, RoundingMode.HALF_UP)
							.toString();
					params += "&XMSL" + j + "="
							+ URLEncoder.encode(sl, "UTF-8"); // XMSL1：项目数量1(小数点后6位),即商品数量
				} else {
					params += "&XMSL" + j + "=";
				}
				if (fpMx.get(i).get("DJ") != null) {
					String dj = (new BigDecimal(fpMx.get(i).get("DJ")
							.toString())).setScale(6, RoundingMode.HALF_UP)
							.toString();
					params += "&XMDJ" + j + "="
							+ URLEncoder.encode(dj, "UTF-8"); // XMDJ1：项目单价1(小数点后6位
																// )
				} else {
					params += "&XMDJ" + j + "=";
				}
				if (fpMx.get(i).get("JE") != null) {
					String je = (new BigDecimal(fpMx.get(i).get("JE")
							.toString())).setScale(2, RoundingMode.HALF_UP)
							.toString();
					params += "&XMJE" + j + "="
							+ URLEncoder.encode(je, "UTF-8"); // XMJE1：项目金额1(单位：元（2位小数）)
				} else {
					params += "&XMJE" + j + "=";
				}
				BigDecimal slv = new BigDecimal(0);
				if (!fpMx.get(i).get("SLV").toString().startsWith("0.")) {
					slv = new BigDecimal(fpMx.get(i).get("SLV")
							.toString());
					params += "&SL"
							+ j
							+ "="
							+ slv.divide(new BigDecimal("100"), 6,
									RoundingMode.HALF_UP); // SL1：税率1(6位小数，例1%为0.01)
				} else {
					params += "&SL" + j + "="
							+ fpMx.get(i).get("SLV").toString(); // SL1：税率1(6位小数，例1%为0.01)
				}
				params += "&HSBZ" + j + "=" + fpMx.get(i).get("HSBZ"); // HSBZ1：含税标志1(表示项目单价和项目金额是否含税。0表示都不含税)
				if (!fpMx.get(i).get("JE").toString().startsWith("-")) {
					params += "&FPHXZ" + j + "=0"; // FPHXZ1：发票行性质1(0正常行、1折扣行)
				} else {
					params += "&FPHXZ" + j + "=1"; // FPHXZ1：发票行性质1(0正常行、1折扣行)
				}
				params += "&SPBM" + j + "=" + fpMx.get(i).get("SSFLBM"); // SPBM1：商品编码1
				params += "&ZXBM" + j + "="; // ZXBM1：自行编码1
//				params += "&YHZCBS" + j + "=" + ConfigUtil.get("YHZCBS"); // YHZCBS1：优惠政策标识1(0：不使用，1：使用)
//				params += "&LSLBS" + j + "=" + ConfigUtil.get("LSLBS"); // LSLBS1：零税率标识1(空：非零税率，
//																		// 1：免税，2：不征税，3普通零税率)
//				params += "&ZZSTSGL" + j + "=" + ConfigUtil.get("ZZSTSGL"); // ZZSTSGL1：增值税特殊管理1
				if(slv.compareTo(new BigDecimal("0")) == 0) {
					//商品税率为0
					params += "&YHZCBS"+j+"="+ConfigUtil.get("YHZCBS");	//YHZCBS1：优惠政策标识1(0：不使用，1：使用)
					params += "&LSLBS"+j+"="+ConfigUtil.get("LSLBS");	//LSLBS1：零税率标识1(空：非零税率， 1：免税，2：不征税，3普通零税率)
					if ("1".equals(ConfigUtil.get("LSLBS"))) {
						params += "&ZZSTSGL"+j+"="+URLEncoder.encode("免税", "UTF-8");	//ZZSTSGL1：增值税特殊管理1
					} else {
						params += "&ZZSTSGL"+j+"=";
					}
				} else {
					params += "&YHZCBS"+j+"=0";	//YHZCBS1：优惠政策标识1(0：不使用，1：使用)
					params += "&LSLBS"+j+"=";	//LSLBS1：零税率标识1(空：非零税率， 1：免税，2：不征税，3普通零税率)
					params += "&ZZSTSGL"+j+"=";	//ZZSTSGL1：增值税特殊管理1
				}
				params += "&KCE" + j + "="; // KCE1:扣除额1

			}
			params += "&TSFS=1"; // TSFS:推送方式（1 电子邮件）
			if (fpHead.get("EMAIL") != null) {
				params += "&EMAIL=" + fpHead.get("EMAIL"); // EMAIL:购货方邮箱
			} else {
				params += "&EMAIL=";
			}
			params += "&RETTYPE=1"; // RETTYPE:结果返回类型(1:json 0:xml)
			// URLEncoder.encode(params, "UTF-8");
			// System.out.println(params);

			os.write(params.getBytes());

			InputStream is = httpUrlConnection.getInputStream();
			byte[] b = new byte[1024];
			int len = 0;
			String s = "";
			while ((len = is.read(b)) != -1) {
				String ss = new String(b, 0, len, "UTF-8");
				s += ss;
			}
			// System.out.println("返回:"+URLDecoder.decode(s, "UTF-8"));
			return_s = URLDecoder.decode(s, "UTF-8");
			System.out.println("签章返回状态:" + return_s);
			msg = save_qzInfo(fpHead.get("OHID").toString(), return_s, qz_lx);
		} catch (IOException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	/**
	 * 
	 * @Title: save_qzInfo
	 * @Description: 保存签章信息
	 * @param: @param ohid
	 * @param: @param return_s
	 * @param: @param qz_lx
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	private String save_qzInfo(String ohid, String return_s, String qz_lx) {
		String msg = "";
		JSONObject obj = (JSONObject) JSON.parseObject(return_s);
		JSONObject message = (JSONObject) obj.get("message");
		String value = (String) message.get("value");
		if ("0".equals(qz_lx)) {
			// 蓝字签章
			if ("200".equals(value)) {
				JSONObject result = (JSONObject) message.get("result");
				// 签章成功
				msg = "签章成功!";
				String qzbs = result.get("QZDM").toString();
				byte[] pdf_file = result.getString("PDF_FILE").toString()
						.getBytes();

				SysOrderHead orderhead = getByHql("from SysOrderHead where ohid='"
						+ ohid + "'");

				orderhead.setErrlog("");
				orderhead.setPdfdata(pdf_file);
				orderhead.setQzbs(qzbs);
				orderhead.setIs_qz("1");
				update(orderhead);

			} else {
				String result = (String) message.get("result");
				// 失败
				String qzbs = value;
				String errlog = result;
				String sql = "update t_sysorderhead set qzbs='" + qzbs
						+ "',errlog='" + errlog + "' where ohid='" + ohid
						+ "' ";
				executeSql(sql, true);
				msg = errlog;
			}
		} else {
			if ("200".equals(value)) {
				JSONObject result = (JSONObject) message.get("result");
				// 签章成功
				msg = "签章成功!";
				String qzbs = result.get("QZDM").toString();
				byte[] pdf_file = result.getString("PDF_FILE").toString()
						.getBytes();

				SysOrderHead orderhead = getByHql("from SysOrderHead where ohid='"
						+ ohid + "'");

				orderhead.setErrlog("");
				orderhead.setPdfdata_h(pdf_file);
				orderhead.setQzbs_h(qzbs);
				orderhead.setIs_qz_h("1");
				update(orderhead);

			} else {
				String result = (String) message.get("result");
				// 失败
				String qzbs = value;
				String errlog = result;
				String sql = "update t_sysorderhead set qzbs_h='" + qzbs
						+ "',errlog='" + errlog + "' where ohid='" + ohid
						+ "' ";
				executeSql(sql, true);
				msg = errlog;
			}
		}
		return msg;
	}

	@Override
	public String save_redinvoice(SysOrderHead oh, String qz_lx) {
//		SysOrderHead oh = ohservice.getById(ohid);

		String tmp_fpzl = oh.getFpzl();
		if ("0".equals(tmp_fpzl)) {
			// 专票
			tmp_fpzl = "8";
		} else if ("2".equals(tmp_fpzl)) {
			// 普票
			tmp_fpzl = "7";
		} else if ("51".equals(tmp_fpzl)) {
			// 电子发票
			tmp_fpzl = "1";
		} else {
			return "错误的发票种类!";
		}
		String return_s = ""; // 存储开票结果返回
		String msg = "";
		// 接口访问地址拼接
		String param_url = jk_url; // 接口程序安装IP地址:端口号

		try {
			URL url = new URL(param_url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-type",
					"application/x-www-form-urlencoded");

			OutputStream os = con.getOutputStream();

			String params = "";
			params += "Action=ECXML.FPKJ.BC.E_INV"; // Action：开票(ECXML.FPKJ.BC.E_INV)
			params += "&SJLY=09"; // SJLY：09-税控组件开票
			params += "&FPLX=" + tmp_fpzl; // FPLX：发票类型(1-增普电 7-增普 8-增专)
			params += "&FPQQLSH="
					+ UUID.randomUUID().toString().replace("-", ""); // FPQQLSH：业务流水号CD161122185412648712
			params += "&KPLX=" + qz_lx; // KPLX：开票类型(0-蓝字发票 1-红字发票)
			params += "&YFP_DM=" + oh.getFpdm();
			params += "&YFP_HM=" + oh.getFphm();
			params += "&PDF_XZFS=2";// 返回pdf文件base64编码

//			 if ("1".equals(oh.getHas_qd())) {
//			 params += "&QD_BZ=1"; //QD_BZ：清单标志(默认为0,1：取清单对应票面内容字段打印到发票票面上)
//			 params += "&QDXMMC="+URLEncoder.encode("(详见销货清单)", "UTF-8");
//			 //QDXMMC：清单发票项目名称(清单标识（QD_BZ）为1时必填:
//			 //(详见销货清单)，纸质发票超过8行自动开具清单，为0不进行处理)
//			 } else {
//			 params += "&QD_BZ=0";
//			 params += "&QDXMMC=";
//			 }
//			 if (oh.getGfsh() != null) {
//			 params += "&GMF_NSRSBH="+URLEncoder.encode(oh.getGfsh(),
//			 "UTF-8"); //GMF_NSRSBH: 购买方纳税人识别号
//			 } else {
//			 params += "&GMF_NSRSBH="; //GMF_NSRSBH: 购买方纳税人识别号
//			 }
			 if (oh.getGfmc() != null) {
			 params += "&GMF_MC="+URLEncoder.encode(oh.getGfmc(), "UTF-8");
			 //GMF_MC: 购买方名称
			 } else {
			 params += "&GMF_MC=";
			 }
//			 if (oh.getGfdzdh() != null) {
//			 params += "&GMF_DZDH="+URLEncoder.encode(oh.getGfdzdh(),
//			 "UTF-8"); //GMF_DZDH= 购买方地址、电话
//			 } else {
//			 params += "&GMF_DZDH=";
//			 }
//			 if (oh.getGfyhzh() != null) {
//			 params += "&GMF_YHZH="+URLEncoder.encode(oh.getGfyhzh() ,
//			 "UTF-8"); //GMF_YHZH: 购买方银行账号
//			 } else {
//			 params += "&GMF_YHZH=";
//			 }
//			 if (oh.getKpr() != null) {
//			 params += "&KPR="+URLEncoder.encode(oh.getKpr(), "UTF-8"); //KPR:
////			 开票人
//			 } else {
//			 params += "&KPR=";
//			 }
//			 if (oh.getSkr() != null) {
//			 params += "&SKR="+URLEncoder.encode(oh.getSkr(), "UTF-8"); //SKR:
////			 收款人
//			 } else {
//			 params += "&SKR=";
//			 }
//			 if (oh.getFhr() != null) {
//			 params += "&FHR="+URLEncoder.encode(oh.getFhr(), "UTF-8"); //FHR:
////			 复核人
//			 } else {
//			 params += "&FHR=";
//			 }
			
			 String jshj = (oh.getTotal().negate()).setScale(2,
			 RoundingMode.HALF_UP).toString();
			 params += "&JSHJ="+URLEncoder.encode(jshj, "UTF-8"); //JSHJ:
//			 价税合计(单位：元（2位小数）)
			 if (oh.getBz() != null && "0".equals(qz_lx)) {
			 params += "&BZ="+URLEncoder.encode(oh.getBz(), "UTF-8");
			 //BZ:备注(红字:对应正数发票代码:XXXXXXXXX号码:YYYYYYYY)
			 } else {
			 params += "&BZ=";
			 }
			 params += "&FPZT=0"; //FPZT：发票状态 0 正常发票 1 作废发票
			 

			List<SysOrderLine> ollist = oh.getSysOrderLines();
			int j = 1;
			for (SysOrderLine ol : ollist) {
				BigDecimal je1=ol.getJe();
				if (je1.compareTo(new BigDecimal(0))!=0) {
					if (ol.getSpmc() != null) {
						params += "&XMMC" + j + "="
							
								+ URLEncoder.encode(ol.getSpmc(), "UTF-8"); // XMMC1：项目名称1
					} else {
						params += "&XMMC" + j + "=";
					}
					if (ol.getDw() != null) {
						params += "&DW" + j + "="
								+ URLEncoder.encode(ol.getDw(), "UTF-8"); // DW1：计量单位1
					} else {
						params += "&DW" + j + "=";
					}
					if (ol.getGgxh() != null) {
						params += "&GGXH" + j + "="
								+ URLEncoder.encode(ol.getGgxh(), "UTF-8"); // GGXH1：规格型号1
					} else {
						params += "&GGXH" + j + "=";
					}
					if (ol.getSl() != null) {
						String sl = "";
						sl = (new BigDecimal(ol.getSl().toString()).negate())
								.setScale(6, RoundingMode.HALF_UP).toString();
						params += "&XMSL" + j + "="
								+ URLEncoder.encode(sl, "UTF-8"); // XMSL1：项目数量1(小数点后6位),即商品数量
					} else {
						params += "&XMSL" + j + "=";
					}
					if (ol.getDj() != null) {
						String dj = (new BigDecimal(ol.getDj().toString()))
								.setScale(6, RoundingMode.HALF_UP).toString();
						params += "&XMDJ" + j + "="
								+ URLEncoder.encode(dj, "UTF-8"); // XMDJ1：项目单价1(小数点后6位
																	// )
					} else {
						params += "&XMDJ" + j + "=";
					}
					if (ol.getJe() != null) {

						String je = "";
						// 红票金额取反
						je = (ol.getJe()).setScale(2, RoundingMode.HALF_UP)
								.negate().toString();
						params += "&XMJE" + j + "="
								+ URLEncoder.encode(je, "UTF-8"); // XMJE1：项目金额1(单位：元（2位小数）)
					} else {
						params += "&XMJE" + j + "=";
					}
					BigDecimal slv = new BigDecimal(ol.getSlv().toString());
					if (!ol.getSlv().toString().startsWith("0.")) {
						slv = slv.divide(new BigDecimal("100"), 6,
								RoundingMode.HALF_UP);
						// params += "&SL"+j+"="+slv.divide(new
						// BigDecimal("100"), 6, RoundingMode.HALF_UP);
						// //SL1：税率1(6位小数，例1%为0.01)
					} else {
					}
					params += "&SL" + j + "=" + slv; // SL1：税率1(6位小数，例1%为0.01)
					params += "&HSBZ" + j + "=" + ol.getHsbz(); // HSBZ1：含税标志1(表示项目单价和项目金额是否含税。0表示都不含税)
					if (!ol.getJe().toString().startsWith("-")) {
						params += "&FPHXZ" + j + "=0"; // FPHXZ1：发票行性质1(0正常行、1折扣行)
					} else {
						params += "&FPHXZ" + j + "=1"; // FPHXZ1：发票行性质1(0正常行、1折扣行)
					}
					params += "&SPBM" + j + "=" + ol.getSsflbm(); // SPBM1：商品编码1
					params += "&ZXBM" + j + "="; // ZXBM1：自行编码1
//					if ("1".equals(tmp_fpzl)) {
//						params += "&YHZCBS" + j + "="
//								+ ConfigUtil.get("YHZCBS"); // YHZCBS1：优惠政策标识1(0：不使用，1：使用)
//						if (slv.compareTo(new BigDecimal(0)) == 0) {
//							params += "&LSLBS" + j + "="
//									+ ConfigUtil.get("LSLBS"); // LSLBS1：零税率标识1(空：非零税率，
//																// 1：免税，2：不征税，3普通零税率)
//						} else {
//							params += "&LSLBS" + j + "=";
//						}
//						params += "&ZZSTSGL" + j + "="
//								+ ConfigUtil.get("ZZSTSGL"); // ZZSTSGL1：当YHZCBS为1时必填
//					} else { // 纸票
//						params += "&YHZCBS" + j + "=0"; // YHZCBS1：优惠政策标识1(0：不使用，1：使用)
//						if (slv.compareTo(new BigDecimal(0)) == 0) {
//							params += "&LSLBS" + j + "="
//									+ ConfigUtil.get("LSLBS"); // LSLBS1：零税率标识1(空：非零税率，
//																// 1：免税，2：不征税，3普通零税率)
//						} else {
//							params += "&LSLBS" + j + "=";
//						}
//						params += "&ZZSTSGL" + j + "="; // ZZSTSGL1：当YHZCBS为1时必填
//					}
					if(slv.compareTo(new BigDecimal("0")) == 0) {
						//商品税率为0
						params += "&YHZCBS"+j+"="+ConfigUtil.get("YHZCBS");	//YHZCBS1：优惠政策标识1(0：不使用，1：使用)
						params += "&LSLBS"+j+"="+ConfigUtil.get("LSLBS");	//LSLBS1：零税率标识1(空：非零税率， 1：免税，2：不征税，3普通零税率)
						if ("1".equals(ConfigUtil.get("LSLBS"))) {
							params += "&ZZSTSGL"+j+"="+URLEncoder.encode("免税", "UTF-8");	//ZZSTSGL1：增值税特殊管理1
						} else {
							params += "&ZZSTSGL"+j+"=";
						}
					} else {
						params += "&YHZCBS"+j+"=0";	//YHZCBS1：优惠政策标识1(0：不使用，1：使用)
						params += "&LSLBS"+j+"=";	//LSLBS1：零税率标识1(空：非零税率， 1：免税，2：不征税，3普通零税率)
						params += "&ZZSTSGL"+j+"=";	//ZZSTSGL1：增值税特殊管理1
					}
					params += "&KCE" + j + "="; // KCE1:扣除额1
					j++;
				}
			}
			params += "&XM_COUNT="+(j-1); //XM_COUNT:发票明细条数
			params += "&TSFS=1";

			params += "&EMAIL=" + oh.getEmail();
			params += "&RETTYPE=1";
			//System.out.println(params);
			os.write(params.getBytes());

			InputStream is = con.getInputStream();
			byte[] b = new byte[1024];
			int len = 0;
			String s = "";
			while ((len = is.read(b)) != -1) {
				String ss = new String(b, 0, len, "UTF-8");
				s += ss;
			}
			return_s = URLDecoder.decode(s, "UTF-8");
			msg = save_einvoice_red(oh.getOhid(), return_s); // 回写

		} catch (Exception e) {
			e.printStackTrace();
			msg = e.toString();
		}

		return msg;
	}

}
