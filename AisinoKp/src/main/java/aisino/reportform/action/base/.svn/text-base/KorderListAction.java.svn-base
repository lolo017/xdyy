package aisino.reportform.action.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.ScjsInvoiceLog;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.ScjsInvoiceLogServiceI;

@Namespace("/base")
@Action
public class KorderListAction extends BaseAction<ScjsInvoiceLog> {

	private static final long serialVersionUID = 1L;
	private String bill_Id; // 单据号
	private String bill_No; // 合同编号
	private String cust_name; // 客户名称
	private String cust_code;
	private String line_id;
	private String cust_id;
	private String invoice_type;
	private String invoice_number;
	private String phone;
	private String xz;
	private String pdf;
	final TimeZone zone = TimeZone.getTimeZone("GMT+8"); // 获取中国时区

	@Autowired
	public void setService(ScjsInvoiceLogServiceI service) {
		this.service = service;
	}
	//获取预览图片文件流
	public void doNotNeedSessionAndSecuritys_tpyl() {
		HttpServletResponse response = ServletActionContext.getResponse();
		FileInputStream is;   
		
		           try {   
		        	   System.out.println(pdf);
		                is = new FileInputStream(pdf);   
		                int i = is.available(); // 得到文件大小   
		                byte data[] = new byte[i];   
		                is.read(data); // 读数据   
		                is.close();   
		                response.setContentType("image/pdf"); // 设置返回的文件类型   
		                OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象   
		                toClient.write(data); // 输出数据   
		                toClient.close();   
		            } catch (FileNotFoundException e) {   
		                e.printStackTrace();   
		            } catch (IOException e) {   
		                e.printStackTrace();   
		            }   

	}
	
	//获取待下载发票数据
	public void doNotNeedSessionAndSecurity_fpxz(){
		System.out.println(phone);
		Grid grid = new Grid();
		//String sql = "select id,type,log_time,pdf,email,CASE WHEN(xzbz=0) THEN '未下载' WHEN(xzbz=1) THEN '已下载' END xzbz,phone,xzsj,pdf_mapper  from dzfp v where 1=1 ";
		String sql = "select ID,'电子发票' TYPE,KPRQ,FP_DM,FP_HM,GFDH,concat('\\\\',FP_DM,'_',FP_HM,'.pdf') pdf_mapper  from responseDataView v where 1=1 ";
		String sql_count = "select COUNT(*) from responseData v where 1=1 ";
		if (phone != null) {
			sql = sql + " and v.GFDH='" + phone + "'";
			sql_count = sql_count + " and v.GFDH='" + phone+ "'";
		}else{
			sql=sql+"and 1=2";
			sql_count=sql_count+"and 1=2";
		}
		
		sql += " order by KPRQ";

		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	// 获取待开票数据
	public void doNotNeedSessionAndSecurity_grid() {
		Grid grid = new Grid();
		String sql = "select * from dzfp v where 1=1 ";
		String sql_count = "select COUNT(*) from dzfp v where 1=1 ";
		if (phone != null) {
			sql = sql + " and v.phone='" + phone + "'";
			sql_count = sql_count + " and v.phone='" + phone + "'";
		}
		if (xz != null) {
			sql = sql + " and v.xzbz='" + xz + "'";
			sql_count = sql_count + " and v.xzbz='" + xz + "'";
		}
		if (cust_name != null) {
			sql = sql + " and v.CUST_NAME='" + cust_name + "'";
			sql_count = sql_count + " and v.CUST_NAME='" + cust_name + "'";
		}
		if (cust_code != null) {
			sql = sql + " and v.CUST_CODE='" + cust_code + "'";
			sql_count = sql_count + " and v.CUST_CODE='" + cust_code + "'";
		}
		sql += " order by log_time";

		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}

	// 查询已开发票
	public void doNotNeedSessionAndSecurity_selectgrid(){
		Grid grid = new Grid();
		
		String sql="select sv.*,sl.INVOICE_NUMBER,sl.CDATE,sl.INVOICE_TYPE,(sv.FQty*sv.FPrice*sv.FCESS/100) FSE from SCJS_INVOICE_LOG sl  inner join SCJS_K_SALELISTALL_V sv  on  sl.FINTERID=sv.FINTERID  and sl.FEntryID=sv.FEntryID  and 1=1 ";
		String sql_count = "select COUNT(*) from SCJS_INVOICE_LOG sl inner join SCJS_K_SALELISTALL_V sv on  sl.FINTERID=sv.FINTERID and sl.FEntryID=sv.FEntryID and 1=1 ";
	    if(bill_Id != null){
	    	sql = sql+ " and sv.FbillNo='" + bill_Id + "'";
	    	sql_count = sql_count + " and sv.FbillNo='" + bill_Id + "'";
	    }
	    if(bill_No != null){
	    	sql+="and sl.INVOICE_NUMBER='"+bill_No+"'";
	    	sql_count+="and sl.INVOICE_NUMBER='" + bill_Id + "'";
	    }
		if (cust_name != null){
			sql = sql+ " and sv.CUST_NAME='" + cust_name + "'";
	    	sql_count = sql_count + " and sv.CUST_NAME='" + cust_name + "'";
		}
		if(cust_code != null){
			sql = sql+ " and sv.CUST_CODE='" + cust_code + "'";
	    	sql_count = sql_count + " and sv.CUST_CODE='" + cust_code + "'";
		}
		sql+=" order by INVOICE_TYPE,INVOICE_NUMBER";
		
		grid.setRows(service.findBySql(sql, page, rows));
		grid.setTotal(service.countBySql(sql_count));
		writeJson(grid);
	}
	//作废发票
	public void doNotNeedSecurity_doInvoiceZF(){
		Json json = new Json();
		try {
			String[] ss1;
			String[] ss2;
			ss1 = line_id.substring(0, line_id.length() - 1).split(",");
			ss2 = bill_Id.substring(0, bill_Id.length() - 1).split(",");
			if (ss1.length > 0) {
				for (int i = 0; i < ss1.length; i++) {
					String sql = " update SCJS_INVOICE_LOG set INVOICE_STATS='2' where"
							+ " INVOICE_NUMBER = "
							+ ss1[i]
							+ " and "
							+ "INVOICE_TYPE = "
							+ ss2[i];
					service.executeSql(sql);
				}
				json.setSuccess(true);
				json.setMsg("作废成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			json.setMsg("输入数据错误，请检查" + e.getMessage());
		}
		writeJson(json);
	}
	// 回写数据
	public void doNotNeedSecurity_doUpdateLog() {
		Json json = new Json();
		try {
			String[] ss1;
			ss1 = line_id.substring(0, line_id.length() - 1).split(",");
			if (ss1.length > 0) {
				for (int i = 0; i < ss1.length; i++) {
					String sql = " insert into SCJS_INVOICE_LOG(finterid,fcustid,fentryid,cdate,invoice_type,invoice_number) "
							+ " values ("
							+ bill_Id
							+ ","
							+ cust_id
							+ ","
							+ ss1[i]
							+ ",getdate(),'"
							+ invoice_type
							+ "','"
							+ invoice_number + "')";
					service.executeSql(sql);
				}
				json.setSuccess(true);
				json.setMsg("保存成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			json.setMsg("输入数据错误，请检查" + e.getMessage());
		}
		writeJson(json);
	}

	public String getBill_Id() {
		return bill_Id;
	}

	public void setBill_Id(String bill_Id) {
		this.bill_Id = bill_Id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getBill_No() {
		return bill_No;
	}

	public void setBill_No(String bill_No) {
		this.bill_No = bill_No;
	}

	public String getCust_code() {
		return cust_code;
	}

	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}

	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	
	//

}
