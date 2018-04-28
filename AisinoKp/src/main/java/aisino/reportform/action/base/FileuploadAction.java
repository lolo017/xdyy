package aisino.reportform.action.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Bankcardnumber;
import aisino.reportform.model.base.Bankcustomer;
import aisino.reportform.model.base.Bankfee;
import aisino.reportform.model.base.Bankposcustomer;
import aisino.reportform.model.base.Bankprivatenumber;
import aisino.reportform.model.base.Bankpublicnumber;
import aisino.reportform.model.base.Bankratio;
import aisino.reportform.model.base.Banktransactionrecord;
import aisino.reportform.model.base.Fileupload;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.BankCustomerServiceI;
import aisino.reportform.service.base.BankcardnumberServiceI;
import aisino.reportform.service.base.BankfeeServiceI;
import aisino.reportform.service.base.BankposcustomerServiceI;
import aisino.reportform.service.base.BankprivatenumberServiceI;
import aisino.reportform.service.base.BankpublicnumberServiceI;
import aisino.reportform.service.base.BankratioServiceI;
import aisino.reportform.service.base.BanktransactionrecordServiceI;
import aisino.reportform.service.base.FileUploadServiceI;
import aisino.reportform.util.base.ConfigUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

@Namespace("/base")
@Action
public class FileuploadAction extends BaseAction<Fileupload>{
	private BankCustomerServiceI bankCustomerServiceI;
	private BankposcustomerServiceI bankposcustomerServiceI;
	private BanktransactionrecordServiceI banktransactionrecordServiceI;
	private BankcardnumberServiceI bankcardnumberServiceI;
	private BankratioServiceI bankratioServiceI;
	private BankpublicnumberServiceI bankpublicnumberServiceI;
	private BankprivatenumberServiceI bankprivatenumberServiceI;
	private BankfeeServiceI bankfeeServiceI;
	
	public BankfeeServiceI getBankfeeServiceI() {
		return bankfeeServiceI;
	}
	@Autowired
	public void setBankfeeServiceI(BankfeeServiceI bankfeeServiceI) {
		this.bankfeeServiceI = bankfeeServiceI;
	}
	public BankprivatenumberServiceI getBankprivatenumberServiceI() {
		return bankprivatenumberServiceI;
	}
	@Autowired
	public void setBankprivatenumberServiceI(
			BankprivatenumberServiceI bankprivatenumberServiceI) {
		this.bankprivatenumberServiceI = bankprivatenumberServiceI;
	}
	public BankpublicnumberServiceI getBankpublicnumberServiceI() {
		return bankpublicnumberServiceI;
	}
	@Autowired
	public void setBankpublicnumberServiceI(
			BankpublicnumberServiceI bankpublicnumberServiceI) {
		this.bankpublicnumberServiceI = bankpublicnumberServiceI;
	}
	public BankratioServiceI getBankratioServiceI() {
		return bankratioServiceI;
	}
	@Autowired
	public void setBankratioServiceI(BankratioServiceI bankratioServiceI) {
		this.bankratioServiceI = bankratioServiceI;
	}
	public BankcardnumberServiceI getBankcardnumberServiceI() {
		return bankcardnumberServiceI;
	}
	@Autowired
	public void setBankcardnumberServiceI(
			BankcardnumberServiceI bankcardnumberServiceI) {
		this.bankcardnumberServiceI = bankcardnumberServiceI;
	}
	public BanktransactionrecordServiceI getBanktransactionrecordServiceI() {
		return banktransactionrecordServiceI;
	}
	@Autowired
	public void setBanktransactionrecordServiceI(
			BanktransactionrecordServiceI banktransactionrecordServiceI) {
		this.banktransactionrecordServiceI = banktransactionrecordServiceI;
	}

	public BankposcustomerServiceI getBankposcustomerServiceI() {
		return bankposcustomerServiceI;
	}

	@Autowired
	public void setBankposcustomerServiceI(
			BankposcustomerServiceI bankposcustomerServiceI) {
		this.bankposcustomerServiceI = bankposcustomerServiceI;
	}

	public BankCustomerServiceI getBankCustomerServiceI() {
		return bankCustomerServiceI;
	}

	@Autowired
	public void setBankCustomerServiceI(
			BankCustomerServiceI bankCustomerServiceI) {
		this.bankCustomerServiceI = bankCustomerServiceI;
	}
	private Integer types;
	private String filepath;
	
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Integer getTypes() {
		return types;
	}
	public void setTypes(Integer types) {
		this.types = types;
	}
	@Autowired
	public void setService(FileUploadServiceI service) {
		this.service = service;
	}
	@Override
	public void save(){
		Json json = new Json();
		try {
			this.getRequest().setCharacterEncoding("utf-8");
			this.getResponse().setCharacterEncoding("utf-8");
			String filenames=this.getRequest().getParameter("fileName");
			String fileUrls=this.getRequest().getParameter("fileUrl");
			String[] filename=filenames.split(",");
			String[]fileUrl=fileUrls.split(",");
			int a=0;
			String root = this.getRequest().getSession().getServletContext().getRealPath("/");
			root=root.replace("\\", "/");
			for (int i = 0; i < filename.length; i++) {
				Fileupload fileupload=new Fileupload();
				fileupload.setName(filename[i]);
				fileupload.setFilepath(fileUrl[i]);
				SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
				String fils=root+fileUrl[i];
				fils=fils.replace("//", "/");
				fileupload.setAlias(fils);
				fileupload.setUploadauthor(sessionInfo.getUser().getName());
				service.save(fileupload);
			}
			
			System.out.println("____---root:"+root);
			json.setSuccess(true);
			json.setMsg("OK");
			writeJson(json);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void insertBb(){
		Json json=new Json();
		switch (this.getTypes()) {
		case 1:
			try {
				test(filepath);
				json.setSuccess(true);
				json.setMsg("OK");
				writeJson(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				json.setSuccess(false);
				json.setMsg("NO");
				writeJson(json);
			}
		case 2:
			try {
				test2(filepath);
				json.setSuccess(true);
				json.setMsg("OK");
				writeJson(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				json.setSuccess(false);
				json.setMsg("NO");
				writeJson(json);
			}
		case 3:
			try {
				test3(filepath);
				json.setSuccess(true);
				json.setMsg("OK");
				writeJson(json);
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
				json.setSuccess(false);
				json.setMsg("NO");
				writeJson(json);
			}
		case 4:
			try {
				test4(filepath);
				json.setSuccess(true);
				json.setMsg("OK");
				writeJson(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("NO");
				writeJson(json);
			}
		
		case 5:
			try {
				test5(filepath);
				json.setSuccess(true);
				json.setMsg("OK");
				writeJson(json);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				json.setSuccess(false);
				json.setMsg("NO");
				writeJson(json);
			}
		case 6:try {
				test6(filepath);
				json.setSuccess(true);
				json.setMsg("OK");
				writeJson(json);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				json.setSuccess(false);
				json.setMsg("NO");
				writeJson(json);
			}
			
		case 7:
			try {
				test7(filepath);
				json.setSuccess(true);
				json.setMsg("OK");
				writeJson(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("NO");
				writeJson(json);
			}
			break;
		case 8:
			test8(filepath);
			json.setSuccess(true);
			json.setMsg("OK");
			writeJson(json);
		default:	
		json.setSuccess(false);
		json.setMsg("NO");
		writeJson(json);
			break;
		}
		
	}
	//BANKCUSTOMER
	
		public void test(String filepath) throws IOException {
			String encoding = "GBK";
			File file = new File(filepath);
			int line=0;
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					
					line++;
					//System.out.println(lineTxt);
					String[]lineTxts=lineTxt.split("\\|");
					System.out.println("lineTxts----------:"+lineTxts.length);
					if(line==1){
						continue;
					}else{
						//for (int i = 0; i < lineTxts.length; i++) {
						if(lineTxts[0].trim().equals("")){
							break;
						}else{
							Bankcustomer bankcustomer=new Bankcustomer();
							bankcustomer.setId(lineTxts[0].trim());
							bankcustomer.setClientname(lineTxts[1].trim());						
							bankcustomer.setShort_(lineTxts[2].trim());
							bankcustomer.setTypenumber (lineTxts[3].trim());
							bankcustomer.setType (lineTxts[4].trim());
							bankcustomer.setAccountmanager (lineTxts[5].trim());
							bankcustomer.setManagementorganization(lineTxts[6].trim());
							bankcustomer.setOpeningdate (lineTxts[7].trim());
							bankcustomer.setBusinesslicensenumber (lineTxts[8].trim());
							bankcustomer.setMerchantaddress(lineTxts[9].trim());
						
							
						
							bankcustomer.setPersonincharge (lineTxts[10].trim());				
							bankcustomer.setIdcard (lineTxts[11].trim());
							bankcustomer.setContactperson(lineTxts[12].trim());
							bankcustomer.setContactphone (lineTxts[13].trim());
							bankcustomer.setContactmobilephone(lineTxts[14].trim());
							bankcustomer.setEmail (lineTxts[15].trim());
							bankcustomer.setMarketfor (lineTxts[16].trim());
							bankcustomer.setSubordinatemerchant (lineTxts[17].trim());
							bankcustomer.setWholesalemarket (lineTxts[18].trim());
							bankcustomer.setLiquidationtype (lineTxts[19].trim());
							
							bankcustomer.setOpenanaccount (lineTxts[20].trim());				
							bankcustomer.setPrivatesettlementcardnumber (lineTxts[21].trim());
							bankcustomer.setForprivate1 (lineTxts[22].trim());
							bankcustomer.setAccountname1 (lineTxts[23].trim());
							bankcustomer.setName1(lineTxts[24].trim());
							bankcustomer.setForpublic2 (lineTxts[25].trim());
							bankcustomer.setAccountname2 (lineTxts[26].trim());
							bankcustomer.setName2 (lineTxts[27].trim());
							bankcustomer.setRelation (lineTxts[28].trim());
							bankcustomer.setAmountlimit (lineTxts[29].trim());
							
							bankcustomer.setCreditcardsign (lineTxts[30].trim());				
							bankcustomer.setLiquidationmode (lineTxts[31].trim());
							bankcustomer.setSettlementaccount (lineTxts[32].trim());
							bankcustomer.setDebitcardsign (lineTxts[33].trim());
							bankcustomer.setDebitcardclearingmode (lineTxts[34].trim());
							bankcustomer.setDebitcardsettlementaccount (lineTxts[35].trim());
							bankcustomer.setQuasicreditcardsign (lineTxts[36].trim());
							bankcustomer.setQuasicreditcardclearingway (lineTxts[37].trim());
							bankcustomer.setQuasicreditcardaccount (lineTxts[38].trim());
							bankcustomer.setPrepaidcardlogo (lineTxts[39].trim());
							
							
							bankcustomer.setPrepaidcardclearingway (lineTxts[40].trim());	
							bankcustomer.setPrepaidcardsettlementaccount (lineTxts[41].trim());
							bankcustomer.setIntegralsign (lineTxts[42].trim());
							bankcustomer.setIntegralselfpaymark (lineTxts[43].trim());
							bankcustomer.setIntegralconversionratio (lineTxts[44].trim());
							bankcustomer.setStagingandmarking (lineTxts[45].trim());
							bankcustomer.setConsumptionmark (lineTxts[46].trim());
							bankcustomer.setIdentityverificationmark (lineTxts[47].trim());
							bankcustomer.setCvv2checkmark (lineTxts[48].trim());
							bankcustomer.setIntegralrate (lineTxts[49].trim());


							bankcustomer.setMinimumdeduction (lineTxts[50].trim());				
							bankcustomer.setMaximumdeduction (lineTxts[51].trim());
							bankcustomer.setFixeddeduction(lineTxts[52].trim());
							bankcustomer.setSingletradequotamark (lineTxts[53].trim());
							bankcustomer.setOftransaction (lineTxts[54].trim());
							bankcustomer.setAmount (lineTxts[55].trim());
							bankcustomer.setAmountlimitsigns (lineTxts[56].trim());
							bankcustomer.setOnedaytransactionamountlimit (lineTxts[57].trim());
							bankcustomer.setTransfersign (lineTxts[58].trim());
							bankcustomer.setCrosslinesinglelimit (lineTxts[59].trim());
							
							
							bankcustomer.setCrosslinesingledaylimit (lineTxts[60].trim());
							bankcustomer.setCrosslinefee (lineTxts[61].trim());
							bankcustomer.setMaximuminterbankcharges (lineTxts[62].trim());
							bankcustomer.setGeneraltrademark (lineTxts[63].trim());
							
							bankCustomerServiceI.save(bankcustomer);
						}
							
//							
//							
							
							//System.out.println(lineTxts[i].trim()+"//:"+i);
						//}
					}
				}
				read.close();
			} else {
				
				System.out.println("找不到指定的文件");
			}
			
			
		}

		
		
		//BANKPOSCUSTOMER
			
			public void test2(String filepath) throws IOException {
				String encoding = "GBK";
				File file = new File(filepath);
				int line=0;
				if (file.isFile() && file.exists()) { // 判断文件是否存在
					InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						
						line++;
						//System.out.println(lineTxt);
						String[]lineTxts=lineTxt.split("\\|");
						System.out.println("lineTxts----------:"+lineTxts.length);
						if(line==1){
							continue;
						}else{
							//for (int i = 0; i < lineTxts.length; i++) {
							if(lineTxts[0].trim().equals("")){
								break;
							}else{
								
								
								Bankposcustomer bankposcustomer=new Bankposcustomer();
								
								bankposcustomer.setYsid(lineTxts[0].trim());
								bankposcustomer.setName(lineTxts[1].trim());
								bankposcustomer.setTerminalnumber(lineTxts[2].trim());
								bankposcustomer.setTerminalstate(lineTxts[3].trim());
								bankposcustomer.setTerminalopeningdate(lineTxts[4].trim());
								
								bankposcustomer.setPhonesign(lineTxts[5].trim());
								bankposcustomer.setTradingphonenumber(lineTxts[6].trim());
								bankposcustomer.setInstalledaddress(lineTxts[7].trim());
								bankposcustomer.setImplementnumber(lineTxts[8].trim());
								bankposcustomer.setTradingphone1(lineTxts[9].trim());
								
								bankposcustomer.setTradingphone2(lineTxts[10].trim());
								bankposcustomer.setTradingphone3(lineTxts[11].trim());
								bankposcustomer.setQuerysign(lineTxts[12].trim());
								bankposcustomer.setPremark(lineTxts[13].trim());
								bankposcustomer.setRevocationmark(lineTxts[14].trim());
								
								bankposcustomer.setCompleteflag(lineTxts[15].trim());
								bankposcustomer.setRevocationoflogo(lineTxts[16].trim());
								bankposcustomer.setRevocationmarkon(lineTxts[17].trim());
								bankposcustomer.setReturnsigns(lineTxts[18].trim());
								bankposcustomer.setIntegralsign(lineTxts[19].trim());
								
								bankposcustomer.setIntegralundomark(lineTxts[20].trim());
								bankposcustomer.setIntegralreturnmark(lineTxts[21].trim());
								bankposcustomer.setIntegralquerysign(lineTxts[22].trim());
								bankposcustomer.setInstallmentsign(lineTxts[23].trim());
								bankposcustomer.setRevocationmarkof(lineTxts[24].trim());
								bankposcustomerServiceI.save(bankposcustomer);
							}
								
//								
//								
								
								//System.out.println(lineTxts[i].trim()+"//:"+i);
							//}
						}
					}
					read.close();
				} else {
					System.out.println("找不到指定的文件");
				}
				
				
			}
			
			
			
			
					//Banktransactionrecord
					
					public void test3(String filepath) throws IOException {
						String encoding = "GBK";
						File file = new File(filepath);
						int line=0;
						if (file.isFile() && file.exists()) { // 判断文件是否存在
							InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
							BufferedReader bufferedReader = new BufferedReader(read);
							String lineTxt = null;
							while ((lineTxt = bufferedReader.readLine()) != null) {
								
								line++;
								System.out.println(lineTxt);
								String[]lineTxts=lineTxt.split("\\|");
								//System.out.println("lineTxts----------:"+lineTxts.length);
								if(line==1){
									continue;
								}else{
									//for (int i = 0; i < lineTxts.length; i++) {
									if(lineTxts[0].trim().equals("")){
										break;
									}else{
										Banktransactionrecord banktransactionrecord=new Banktransactionrecord();
										
										
										banktransactionrecord.setMonth(lineTxts[0].trim());
										banktransactionrecord.setBranch(lineTxts[1].trim());
										banktransactionrecord.setMerchantnumber(lineTxts[2].trim());
										banktransactionrecord.setTerminalnumber(lineTxts[3].trim());
										banktransactionrecord.setTradingcardnumber(lineTxts[4].trim());
										
										banktransactionrecord.setTransaction(lineTxts[5].trim());
										banktransactionrecord.setTradetypes(lineTxts[6].trim());
										banktransactionrecord.setTradingdate(lineTxts[7].trim());
										banktransactionrecord.setTradingtime(lineTxts[8].trim());
										banktransactionrecord.setCounterfee(lineTxts[9].trim());
										
										banktransactionrecord.setLoanmark(lineTxts[10].trim());
										banktransactionrecord.setCounterfee1(lineTxts[11].trim());
										banktransactionrecord.setRebate(lineTxts[12].trim());
										banktransactionrecord.setTransfercommission(lineTxts[13].trim());
										banktransactionrecord.setProfit(lineTxts[14].trim());
										banktransactionrecordServiceI.save(banktransactionrecord);
									}
						
								}
							}
							read.close();
						} else {
							System.out.println("找不到指定的文件");
						}
						
						
					}
					
					//Bankcardnumber
					
					public void test4(String filepath) throws IOException {
						String encoding = "GBK";
						File file = new File(filepath);
						int line=0;
						if (file.isFile() && file.exists()) { // 判断文件是否存在
							InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
							BufferedReader bufferedReader = new BufferedReader(read);
							String lineTxt = null;
							while ((lineTxt = bufferedReader.readLine()) != null) {
								
								line++;
								System.out.println(lineTxt);
								String[]lineTxts=lineTxt.split("\\|");
								//System.out.println("lineTxts----------:"+lineTxts.length);
								
									//for (int i = 0; i < lineTxts.length; i++) {
									if(lineTxts[0].trim().equals("")){
										break;
									}else{
										Bankcardnumber bankcardnumber=new Bankcardnumber();
										
										
										bankcardnumber.setIdcard(lineTxts[0].trim());
										bankcardnumber.setNumber1(lineTxts[1].trim());
										bankcardnumber.setNumber2(lineTxts[2].trim());
										bankcardnumber.setNumber3(lineTxts[3].trim());
										bankcardnumber.setNumber4(lineTxts[4].trim());
										
										bankcardnumberServiceI.save(bankcardnumber);
									}
						
								}
							
							read.close();
						} else {
							System.out.println("找不到指定的文件");
						}
						
						
					}
					
					
					
					//Bankratio
				
					public void test5(String filepath) throws IOException {
						String encoding = "GBK";
						File file = new File(filepath);
						int line=0;
						if (file.isFile() && file.exists()) { // 判断文件是否存在
							InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
							BufferedReader bufferedReader = new BufferedReader(read);
							String lineTxt = null;
							while ((lineTxt = bufferedReader.readLine()) != null) {
								
								line++;
								System.out.println(lineTxt);
								String[]lineTxts=lineTxt.split("\\|");
								//System.out.println("lineTxts----------:"+lineTxts.length);
								
									//for (int i = 0; i < lineTxts.length; i++) {
									if(lineTxts[0].trim().equals("")){
										break;
									}else{
										Bankratio bankratio=new Bankratio();
										
										
										bankratio.setCustomerid(lineTxts[0].trim());
										bankratio.setName(lineTxts[1].trim());
										bankratio.setTradetypes(lineTxts[2].trim());
										bankratio.setTradetypes1(lineTxts[3].trim());
										bankratio.setO1(lineTxts[4].trim());
										bankratio.setO2(lineTxts[5].trim());
										
										bankratio.setO3(lineTxts[6].trim());
										bankratio.setO4(lineTxts[7].trim());
										bankratio.setO5(lineTxts[8].trim());
										bankratio.setO6(lineTxts[9].trim());
										bankratio.setO7(lineTxts[10].trim());
										
										
										
										bankratioServiceI.save(bankratio);
									}
						
								}
							
							read.close();
						} else {
							System.out.println("找不到指定的文件");
						}
						
						
					}
					
					
					//Bankpublicnumber
				
					public void test6(String filepath) throws IOException {
						String encoding = "GBK";
						File file = new File(filepath);
						int line=0;
						if (file.isFile() && file.exists()) { // 判断文件是否存在
							InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
							BufferedReader bufferedReader = new BufferedReader(read);
							String lineTxt = null;
							while ((lineTxt = bufferedReader.readLine()) != null) {
								
								line++;
								System.out.println(lineTxt);
								String[]lineTxts=lineTxt.split("\\|");
								//System.out.println("lineTxts----------:"+lineTxts.length);
								
									//for (int i = 0; i < lineTxts.length; i++) {
									if(lineTxts[0].trim().equals("")){
										break;
									}else{
										Bankpublicnumber bankpublicnumber=new Bankpublicnumber();
										
										
										bankpublicnumber.setIdcard(lineTxts[0].trim());
										bankpublicnumber.setUnumber1(lineTxts[1].trim());
										bankpublicnumber.setUnumber2(lineTxts[2].trim());
										bankpublicnumber.setUnumber3(lineTxts[3].trim());
										bankpublicnumber.setUnumber4(lineTxts[4].trim());
									
										
										
										bankpublicnumberServiceI.save(bankpublicnumber);
									}
						
								}
							
							read.close();
						} else {
							System.out.println("找不到指定的文件");
						}
						
						
					}
					
					
					//Bankprivatenumber
					
					public void test7(String filepath) throws IOException {
						String encoding = "GBK";
						File file = new File(filepath);
						int line=0;
						if (file.isFile() && file.exists()) { // 判断文件是否存在
							InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
							BufferedReader bufferedReader = new BufferedReader(read);
							String lineTxt = null;
							while ((lineTxt = bufferedReader.readLine()) != null) {
								
								line++;
								System.out.println(lineTxt);
								String[]lineTxts=lineTxt.split("\\|");
								//System.out.println("lineTxts----------:"+lineTxts.length);
								
									//for (int i = 0; i < lineTxts.length; i++) {
									if(lineTxts[0].trim().equals("")){
										break;
									}else{
										Bankprivatenumber bankprivatenumber=new Bankprivatenumber();
										
										
										bankprivatenumber.setIdcard(lineTxts[0].trim());
										bankprivatenumber.setPnumber1(lineTxts[1].trim());
										bankprivatenumber.setPnumber2(lineTxts[2].trim());
										bankprivatenumber.setPnumber3(lineTxts[3].trim());
										bankprivatenumber.setPnumber4(lineTxts[4].trim());
										bankprivatenumber.setPnumber5(lineTxts[4].trim());
										
										
										bankprivatenumberServiceI.save(bankprivatenumber);
									}
						
								}
							
							read.close();
						} else {
							System.out.println("找不到指定的文件");
						}
						
						
					}
					
					//删除文件
					public void deletefile(){
						Json json=new Json();
						String id=this.getRequest().getParameter("id");
						System.out.println("id----------:"+id);
					Fileupload fileupload=service.getById(id);
						   File file = new File(fileupload.getAlias());
						   if(file.exists()){
						    boolean d = file.delete();

						    if(d){
						    	service.delete(fileupload);
						    	json.setSuccess(false);
								json.setMsg("删除成功");
								writeJson(json);
						    }else{
						    	json.setSuccess(false);
								json.setMsg("删除失败");
								writeJson(json);
						   
						    }
						   } 
					}
					
					//BANKFEE
					public void test8(String filepaths) {
						String filepath=filepaths;
					
						boolean isE2007 = false; // 判断是否是excel2007格式
						if (filepath.endsWith("xlsx"))
							isE2007 = true;
						try {
							InputStream input = new FileInputStream(filepath); // 建立输入流
							Workbook wb = null;
							// 根据文件格式(2003或者2007)来初始化
							if (isE2007) {
								
								wb = new XSSFWorkbook(input);
								int se = wb.getNumberOfSheets();// 得到sheet
								System.out.println(se);
								for (int d = 0; d < se; d++) {
									XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(d);// 得到工作表
									XSSFRow row = null;// 对应excel的行
									XSSFCell cell = null;// 对应excel的列

									int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
									System.out.println("totalRow:"+totalRow);
									for (int i = 3; i <= totalRow; i++) {
										
										row = sheet.getRow(i);
										if (row != null) {		
											//所属单位名称
											cell = row.getCell((short) 1);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												System.out.println("b:"+b);
												
											//	jgrybz.setXmmc(Charset.writeDB(b));
											}
										}
										//jgrybzService.save(jgrybz);
									}
								}
							} else {
								wb = new HSSFWorkbook(input);
								int se = wb.getNumberOfSheets();// 得到sheet
								for (int d = 0; d < se; d++) {
									HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(d);// 得到工作表
									HSSFRow row = null;// 对应excel的行
									HSSFCell cell = null;// 对应excel的列

									int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
									System.out.println("totalRow:"+totalRow);
									for (int i = 4; i <= totalRow; i++) {
										Bankfee  bankfee=new Bankfee();
										row = sheet.getRow(i);
										if (row != null) {		
											cell = row.getCell((short) 0);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("a:"+b+"/"+totalRow+"I:"+i);
												//BigDecimal bd = new BigDecimal(b);	
												bankfee.setType(b);
												//jgrybz.setXmmc(Charset.writeDB(b));
											}
											cell = row.getCell((short) 1);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												bankfee.setBigtype(b);
											}
											
											cell = row.getCell((short) 2);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												bankfee.setSmalltype(b);
											}
											
											cell = row.getCell((short) 3);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												bankfee.setFine(b);
											}
											
											cell = row.getCell((short) 4);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												bankfee.setNewscheme(b);
											}
											
											cell = row.getCell((short) 5);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												//BigDecimal mcc = new BigDecimal(b);	
												bankfee.setMcc(b);
											}
											
											cell = row.getCell((short) 6);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												bankfee.setMerchantclassalias(b);
											}
											
											cell = row.getCell((short) 7);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												//BigDecimal w1 = new BigDecimal(b);	
												bankfee.setW1(b);
											}
											
											cell = row.getCell((short) 8);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												//BigDecimal w2 = new BigDecimal(b);	
												bankfee.setW2(b);
											}
											
											cell = row.getCell((short) 9);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												//System.out.println("BBBBBBBBBB:"+b);
												//BigDecimal merchantratenew = new BigDecimal(b);	
												bankfee.setMerchantratenew(b);
											}
											
											cell = row.getCell((short) 11);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												//BigDecimal merchantrateold = new BigDecimal(b);	
												System.out.println("Merchantrateold:"+b);
												bankfee.setMerchantrateold(b);
											}
											
											cell = row.getCell((short) 12);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
											//	BigDecimal change = new BigDecimal(b);	
												bankfee.setChange(b);
											}
											
											cell = row.getCell((short) 13);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
											//	BigDecimal hairpinprofit = new BigDecimal(b);
												Float f=null;
												  if(b.contains("%")) {  
												         b = b.replaceAll("%", "");  
												          f= Float.valueOf(b);  
												         f=f/100;
												  }
												bankfee.setHairpinprofit(f+"");
											}
											
											cell = row.getCell((short) 14);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												bankfee.setHairpincapped(b);
											}
											
											cell = row.getCell((short) 15);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
											//	System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
											
												//BigDecimal hairpinmoney = new BigDecimal(b);	
												bankfee.setHairpinmoney(b);
											}
											
											cell = row.getCell((short) 16);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												//BigDecimal unionpayprofit = new BigDecimal(b);	
												Float f=null;
												  if(b.contains("%")) {  
												         b = b.replaceAll("%", "");  
												          f= Float.valueOf(b);  
												         f=f/100;
												  }
												bankfee.setUnionpayprofit(f+"");
											}
											
											cell = row.getCell((short) 17);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
											
												bankfee.setUnionpaycapped(b);
											}
											
											cell = row.getCell((short) 18);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
											//	BigDecimal unionpaymoney = new BigDecimal(b);	
												bankfee.setUnionpaymoney(b);
											}
											
											cell = row.getCell((short) 19);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
											//	BigDecimal standardprofit = new BigDecimal(b);	
												Float f=null;
												  if(b.contains("%")) {  
												         b = b.replaceAll("%", "");  
												          f= Float.valueOf(b);  
												         f=f/100;
												  }
												bankfee.setStandardprofit(f+"");
											}
											
											cell = row.getCell((short) 20);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												bankfee.setStandardcapped(b);
											}
											
											cell = row.getCell((short) 21);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												//BigDecimal standardmoney = new BigDecimal(b);	
												bankfee.setStandardmoney(b);
											}
											cell = row.getCell((short) 22);
											if(cell.getCellType()!=Cell.CELL_TYPE_BLANK){
												cell.setCellType(Cell.CELL_TYPE_STRING); // 设置其单元格类型为数字
												String b = cell.getStringCellValue(); // 获取数字值
												//System.out.println("b:"+b+"/"+totalRow+"II:"+i);
												//jgrybz.setJjz(new BigDecimal(b));
												bankfee.setRemarks(b);
											}
											
										}
										bankfeeServiceI.save(bankfee);
									}
								}
							}
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
}
