
package aisino.reportform.action.base.fpmng;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.T_invoicedata;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.fpmng.T_invoicedataServiceI;
import aisino.reportform.service.fpmng.VInvoiceHeadServiceI;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.DbUtil;

/**
 * 
* @Title:InvoiceAction 
* @Description: 发票管理
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年11月22日 下午5:25:27
 */
@Namespace("/base/fpmng")
@Action
public class InvoiceAction extends BaseAction<SysOrderHead>{
	
	@Autowired
	public VInvoiceHeadServiceI vihservice;	//发票Service注入
	@Autowired
	public T_invoicedataServiceI inservice;	//回写中间库Service注入
	@Autowired
	public SysOrderHeadServiceI ohservice;	//系统单据Service注入
	@Autowired
	public SysOrderLineServiceI olservice;	//系统单据明细Service注入
	
	/**
	 * 
	 * @Title: getInvoiceGrid
	 * @Description: 获取发票列表
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
				
				String fpzl = request.getParameter("fpzl");//发票种类 0:专票,2:普票,51:电子发票
				String fpdm = request.getParameter("fpdm");//发票代码
				String fphm = request.getParameter("fphm");//发票号码
				String is_zf = request.getParameter("is_zf");//作废标志
				String has_qd = request.getParameter("has_qd");//清单标志
				String is_print = request.getParameter("is_print");//打印标志
				String gfmc = request.getParameter("gfmc");//购方名称
				String djhm = request.getParameter("djhm");//单据号码
			  
				StringBuffer buff = new StringBuffer();
				if(createtime1 != null && !"".equals(createtime1)){
					buff.append( " and t.createtime >= '"+createtime1+"' ");
				}
				if(createtime2 != null && !"".equals(createtime2)){
					buff.append( " and t.createtime < DATEADD(day,1,'"+createtime2+"') ");
				}
				buff.append(" and t.fpzl !='51' ");
				if(fpzl != null && !"".equals(fpzl)){
					buff.append(" and t.fpzl ='"+ fpzl +"' ");
				} 
				if(fpdm != null && !"".equals(fpdm)){
					buff.append(" and t.fpdm like '%"+ fpdm +"%' ");
				} 
				if(fphm != null && !"".equals(fphm)){
					buff.append(" and t.fphm like '%"+ fphm +"%' ");
				} 
				if(is_zf != null && !"".equals(is_zf)){
					buff.append(" and t.is_zf ='"+ is_zf +"' ");
				} 
				if(has_qd != null && !"".equals(has_qd)){
					buff.append(" and t.has_qd ='"+ has_qd +"' ");
				} 
				if(is_print != null && !"".equals(is_print)){
					buff.append(" and t.is_print ='"+ is_print +"' ");
				} 
				if(gfmc != null && !"".equals(gfmc)){
					buff.append( " and t.gfmc like '%"+gfmc+"%' ");
				}
				if(djhm != null && !"".equals(djhm)){
					buff.append( " and t.djhm like '%"+djhm+"%' ");
				}

				grid = vihservice.getInvoiceGrid(buff, page, rows, sort, order);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeJson(grid);
		}
	}
	
	/**
	 * 
	 * @Title: returnInvoiceToMiddleDb
	 * @Description: 纸票回写
	 * @param:  楚珂
	 * @return: void   
	 * @throws
	 */
	public void returnInvoiceToTheDb(){
		String sql1="select * from v_hk_invoiceHead where IS_HX='0'";
		String sql2="update t_sysorderhead set is_hx='1' where ohid='";
		List<HashMap> HxList=new ArrayList<HashMap>();
		int index=0;
		HxList=inservice.findBySql(sql1);
		for(HashMap hk:HxList){
//			System.out.println(hk);
			T_invoicedata in=new T_invoicedata();
			in.setHid(UUID.randomUUID().toString());
			in.setCreatetime((Date)hk.get("CREATETIME"));
			in.setFpzl((String)hk.get("FPZL"));
			in.setSkr((String)hk.get("SKR"));
			in.setFhr((String)hk.get("FHR"));
			in.setKpr((String)hk.get("KPR"));
			in.setGfhm((String)hk.get("GFHM"));
			in.setGfmc((String)hk.get("GFMC"));
			in.setGfsh((String)hk.get("GFSH"));
			in.setGfyhzh((String)hk.get("GFYHZH"));
			in.setGfdzdh((String)hk.get("GFDZDH"));
			in.setBz((String)hk.get("BZ"));
			in.setMix((String)hk.get("MIX"));
			in.setSlv((BigDecimal)hk.get("SLV"));
			in.setFpdm((String)hk.get("FPDM"));
			in.setFphm((String)hk.get("FPHM"));
			in.setAmount((BigDecimal)hk.get("AMOUNT"));
			in.setTax_amount((BigDecimal)hk.get("TAX_AMOUNT"));
			in.setTotal((BigDecimal)hk.get("TOTAL"));
			in.setSphs((int)hk.get("SPHS"));
			in.setHas_qd((String)hk.get("HAS_QD"));
			in.setIs_zf((String)hk.get("IS_ZF"));
			in.setZf_date((Date)hk.get("ZF_DATE"));
			in.setRed_fpdm((String)hk.get("RED_FPDM"));
			in.setRed_fphm((String)hk.get("RED_FPHM"));
			in.setIs_qz((String)hk.get("IS_QZ"));
			in.setMobile((String)hk.get("MOBILE"));
			in.setEmail((String)hk.get("EMAIL"));
			in.setIs_qz_h((String)hk.get("IS_QZ_H"));
			DbUtil.setDb("2");
			inservice.save(in);
			String ohid=(String)hk.get("OHID")+"'";
			DbUtil.setDb("1");
			ohservice.executeSql(sql2+ohid);
			index++;
		}
		Json json= new Json();
        json.setSuccess(true);
        String msg="";
        if(index==0){
        	msg="没有数据需要回写";
        }else{
        	msg="已经成功回写"+index+"条数据";
        }
        json.setMsg(msg);
        writeJson(json);
	}
	
	
	/**
	 * 
	 * @Title: exportInvoiceExcel
	 * @Description: 导出Excel(已开发票明细)
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void exportInvoiceExcel() {
		String fileName="";
		try  
        {
		
		// 第一步，创建一个webbook，对应一个Excel文件  
		XSSFWorkbook wb = new XSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = wb.createSheet("sheet1");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        XSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        XSSFCellStyle style = wb.createCellStyle();  
  
        XSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("单据号码");  
        cell = row.createCell((short) 1);  
        cell.setCellValue("发票代码");  
        cell = row.createCell((short) 2);  
        cell.setCellValue("发票号码");  
        cell = row.createCell((short) 3);  
        cell.setCellValue("发票种类");  
        cell = row.createCell((short) 4); 
        cell.setCellValue("开票时间");  
        cell = row.createCell((short) 5); 
        cell.setCellValue("购方名称");  
        cell = row.createCell((short) 6); 
        cell.setCellValue("购方税号");  
        cell = row.createCell((short) 7); 
        cell.setCellValue("购方地址电话");  
        cell = row.createCell((short) 8); 
        cell.setCellValue("购方银行账户");  
        cell = row.createCell((short) 9); 
        cell.setCellValue("商品名称");  
        cell = row.createCell((short) 10); 
        cell.setCellValue("规格型号");  
        cell = row.createCell((short) 11); 
        cell.setCellValue("单位");  
        cell = row.createCell((short) 12); 
        cell.setCellValue("单价");  
        cell = row.createCell((short) 13); 
        cell.setCellValue("数量");  
        cell = row.createCell((short) 14); 
        cell.setCellValue("金额");  
        cell = row.createCell((short) 15); 
        cell.setCellValue("含税标志");  
        cell = row.createCell((short) 16); 
        cell.setCellValue("税率");  
        cell = row.createCell((short) 17); 
        cell.setCellValue("税收分类编码");  
        cell = row.createCell((short) 18); 
        cell.setCellValue("客户手机");  
        cell = row.createCell((short) 19); 
        cell.setCellValue("客户邮箱");  
        cell = row.createCell((short) 20); 
        cell.setCellValue("红字发票代码");  
        cell = row.createCell((short) 21); 
        cell.setCellValue("红字发票号码");  
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<HashMap> LgList=getMxList();
        
        for (int i = 0; i < LgList.size(); i++)  
        {  //TODO List传入
            row = sheet.createRow((int) i + 1);  
            HashMap stu = LgList.get(i);  
            // 第四步，创建单元格，并设置值  
            String tmp = "";
            //cell.setCellValue("单据号码");
            if (LgList.get(i).get("DJHM") != null) {
            	row.createCell((short) 0).setCellValue(LgList.get(i).get("DJHM").toString());  
            } else {
            	row.createCell((short) 0).setCellValue(tmp);  
            }
            //cell.setCellValue("发票代码");  
            if (LgList.get(i).get("FPDM") != null) {
            	row.createCell((short) 1).setCellValue(LgList.get(i).get("FPDM").toString());  
            } else {
            	row.createCell((short) 1).setCellValue(tmp);  
            }
            //cell.setCellValue("发票号码");  
            if (LgList.get(i).get("FPHM") != null) {
            	row.createCell((short) 2).setCellValue(LgList.get(i).get("FPHM").toString());  
            } else {
            	row.createCell((short) 2).setCellValue(tmp);  
            }
            //cell.setCellValue("发票种类");  
            if (LgList.get(i).get("FPZL") != null) {
            	String fpzl_c = "";
            	fpzl_c = LgList.get(i).get("FPZL").toString();
            	switch(fpzl_c) {
            	case "0" :
            		row.createCell((short) 3).setCellValue("增值税专用发票");  
            		break;
            	case "2" :
            		row.createCell((short) 3).setCellValue("增值税普通发票");  
            		break;
            	case "41" :
            		row.createCell((short) 3).setCellValue("卷式发票");  
            		break;
            	case "51" :
            		row.createCell((short) 3).setCellValue("增值税电子普通发票");  
            		break;
            	default:
            		row.createCell((short) 3).setCellValue("其他");  
            		break;
            	}
            } else {
            	row.createCell((short) 3).setCellValue(tmp);  
            }
            //cell.setCellValue("开票时间");
            if (LgList.get(i).get("CREATETIME") != null) {
            	row.createCell((short) 4).setCellValue(LgList.get(i).get("CREATETIME").toString());  
            } else {
            	row.createCell((short) 4).setCellValue(tmp);  
            }
            //cell.setCellValue("购方名称");  
            if (LgList.get(i).get("GFMC")!= null) {
            	row.createCell((short) 5).setCellValue(LgList.get(i).get("GFMC").toString());  
            } else {
            	row.createCell((short) 5).setCellValue(tmp);  
            }
            //cell.setCellValue("购方税号");  
            if (LgList.get(i).get("GFSH")!= null) {
            	row.createCell((short) 6).setCellValue(LgList.get(i).get("GFSH").toString());  
            } else {
            	row.createCell((short) 6).setCellValue(tmp);  
            }
            //cell.setCellValue("购方地址电话");
            if (LgList.get(i).get("GFDZDH")!= null) {
            	row.createCell((short) 7).setCellValue(LgList.get(i).get("GFDZDH").toString());  
            } else {
            	row.createCell((short) 7).setCellValue(tmp);  
            }
            //cell.setCellValue("购方银行账户");  
            if (LgList.get(i).get("GFYHZH")!= null) {
            	row.createCell((short) 8).setCellValue(LgList.get(i).get("GFYHZH").toString());  
            } else {
            	row.createCell((short) 8).setCellValue(tmp);  
            }
            //cell.setCellValue("商品名称");  
            if (LgList.get(i).get("SPMC")!= null) {
            	row.createCell((short) 9).setCellValue(LgList.get(i).get("SPMC").toString());  
            } else {
            	row.createCell((short) 9).setCellValue(tmp);  
            }
            //cell.setCellValue("规格型号");  
            if (LgList.get(i).get("GGXH")!= null) {
            	row.createCell((short) 10).setCellValue(LgList.get(i).get("GGXH").toString());  
            } else {
            	row.createCell((short) 10).setCellValue(tmp);  
            }
            //cell.setCellValue("单位");  
            if (LgList.get(i).get("DW")!= null) {
            	row.createCell((short) 11).setCellValue(LgList.get(i).get("DW").toString());  
            } else {
            	row.createCell((short) 11).setCellValue(tmp);  
            }
            //cell.setCellValue("单价");  
            if (LgList.get(i).get("DJ")!= null) {
            	row.createCell((short) 12).setCellValue(LgList.get(i).get("DJ").toString());  
            } else {
            	row.createCell((short) 12).setCellValue(tmp);  
            }
            //cell.setCellValue("数量"); 
            if (LgList.get(i).get("SL")!= null) {
            	row.createCell((short) 13).setCellValue(LgList.get(i).get("SL").toString());  
            } else {
            	row.createCell((short) 13).setCellValue(tmp);  
            }
            //cell.setCellValue("金额");  
            if (LgList.get(i).get("JE")!= null) {
            	row.createCell((short) 14).setCellValue(LgList.get(i).get("JE").toString());  
            } else {
            	row.createCell((short) 14).setCellValue(tmp);  
            }
            //cell.setCellValue("含税标志");
            if (LgList.get(i).get("HSBZ")!= null) {
            	row.createCell((short) 15).setCellValue(LgList.get(i).get("HSBZ").toString());  
            } else {
            	row.createCell((short) 15).setCellValue(tmp);  
            }
            //cell.setCellValue("税率"); 
            if (LgList.get(i).get("SLV")!= null) {
            	row.createCell((short) 16).setCellValue(LgList.get(i).get("SLV").toString());  
            } else {
            	row.createCell((short) 16).setCellValue(tmp);  
            }
            //cell.setCellValue("税收分类编码");
            if (LgList.get(i).get("SSFLBM")!= null) {
            	row.createCell((short) 17).setCellValue(LgList.get(i).get("SSFLBM").toString());  
            } else {
            	row.createCell((short) 17).setCellValue(tmp);  
            }
            //cell.setCellValue("客户手机"); 
            if (LgList.get(i).get("MOBILE")!= null) {
            	row.createCell((short) 18).setCellValue(LgList.get(i).get("MOBILE").toString());  
            } else {
            	row.createCell((short) 18).setCellValue(tmp);  
            }
            //cell.setCellValue("客户邮箱");  
            if (LgList.get(i).get("EMAIL")!= null) {
            	row.createCell((short) 19).setCellValue(LgList.get(i).get("EMAIL").toString());  
            } else {
            	row.createCell((short) 19).setCellValue(tmp);  
            }
            //红字发票代码
            if (LgList.get(i).get("RED_FPDM")!= null) {
            	row.createCell((short) 20).setCellValue(LgList.get(i).get("RED_FPDM").toString());  
            } else {
            	row.createCell((short) 20).setCellValue(tmp);  
            }
            //红字发票号码
            if (LgList.get(i).get("RED_FPHM")!= null) {
            	row.createCell((short) 21).setCellValue(LgList.get(i).get("RED_FPHM").toString());  
            } else {
            	row.createCell((short) 21).setCellValue(tmp);  
            }
        }  
        
        // 第六步，将文件存到指定位置  
          
        	//获取上传存储路径
			String uploadPath = ServletActionContext.getServletContext().getRealPath("upload");
			 
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhh24mmss");//设置日期格式  
			String date = df.format(new Date());// new Date()为获取当前系统时间  
			
			fileName =date+"_invoiceExcel.xlsx";
            FileOutputStream fout = new FileOutputStream(uploadPath+"/"+fileName);  
            wb.write(fout);  
            fout.close();  
            
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        
        Json json= new Json();
        json.setSuccess(true);
        json.setMsg(fileName);
        writeJson(json);
	}
	
	
	/**
	 * 
	 * @Title: getMxList
	 * @Description: 获取发票明细列表
	 * @param: @return 
	 * @return: List<HashMap>   
	 * @throws
	 */
	public List<HashMap> getMxList() {
		List<HashMap> LgList=new ArrayList<HashMap>(); 
		
		//条件筛选
				HttpServletRequest request = ServletActionContext.getRequest();
				try {
					request.setCharacterEncoding("utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				String createtime1 = request.getParameter("createtime1");//创建时间起
				String createtime2 = request.getParameter("createtime2");//创建时间止
				
				String fpzl = request.getParameter("fpzl");//发票种类 0:专票,2:普票,51:电子发票
				String fpdm = request.getParameter("fpdm");//发票代码
				String fphm = request.getParameter("fphm");//发票号码
				String is_zf = request.getParameter("is_zf");//作废标志
				String has_qd = request.getParameter("has_qd");//清单标志
				String is_print = request.getParameter("is_print");//打印标志
				String gfmc = request.getParameter("gfmc");//购方名称
			  
				StringBuffer buff = new StringBuffer();
				buff.append("");
				if(createtime1 != null && !"".equals(createtime1)){
					buff.append( " and createtime >= '"+createtime1+"' ");
				}
				if(createtime2 != null && !"".equals(createtime2)){
					buff.append( " and createtime < DATEADD(day,1,'"+createtime2+"') ");
				}
				if(fpzl != null && !"".equals(fpzl)){
					buff.append(" and fpzl ='"+ fpzl +"' ");
				} 
				if(fpdm != null && !"".equals(fpdm)){
					buff.append(" and fpdm ='"+ fpdm +"' ");
				} 
				if(fphm != null && !"".equals(fphm)){
					buff.append(" and fphm ='"+ fphm +"' ");
				} 
				if(is_zf != null && !"".equals(is_zf)){
					buff.append(" and is_zf ='"+ is_zf +"' ");
				} 
				if(has_qd != null && !"".equals(has_qd)){
					buff.append(" and has_qd ='"+ has_qd +"' ");
				} 
				if(is_print != null && !"".equals(is_print)){
					buff.append(" and is_print ='"+ is_print +"' ");
				} 
				if(gfmc != null && !"".equals(gfmc)){
					buff.append( " and gfmc like '%"+gfmc+"%' ");
				}
				
				String sql="select * from v_invoiceMx where 1=1 and fphm is not null "+buff;
				String sql_count="select count(1) from v_invoiceMx where 1=1 and fphm is not null "+buff;
		
				//System.out.println("sql=="+sql);
		LgList = ohservice.findBySql(sql);
		return LgList;
	}
	
	
	/**
	 * 
	 * @Title: download
	 * @Description: 从服务器下载Excel
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void download(){
		try {
		HttpServletRequest request=getRequest();
		HttpServletResponse response=getResponse();
//		String fileName="2017080705242435_导出数据.xlsx";
		String fileName = request.getParameter("fileName");
//		fileName = new String(fileName.getBytes("iso8859-1"),"GBK");
        String ParentPath =ServletActionContext.getServletContext().getRealPath("upload");
        String realPath = ParentPath+"/"+fileName;
		  
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(realPath));
	        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	        long fileLength = new File(realPath).length();  
	        response.setCharacterEncoding("ISO-8859-1");
	        response.setContentType("application/octet-stream");
	        //解决中文乱码
	        String userAgent = request.getHeader("User-Agent");
	        // fileName.getBytes("UTF-8")处理safari的乱码问题
	        byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes(): fileName.getBytes("UTF-8"); 
	        fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
	        response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"", fileName));
	        response.setHeader("Content-Length", String.valueOf(fileLength));
	        byte[] buff = new byte[2048];
	        int bytesRead;
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	            bos.write(buff, 0, bytesRead);
	        }
	        bis.close();
	        bos.close();
	        File file = new File(realPath);
            file.delete();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
	
	/**
	 * 
	 * @Title: invalidToOrder
	 * @Description: 已作废发票重新生成单据
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void invalidToOrder() {
		Json json = new Json();
		String msg="";
//		String ohids="39380196008";//测试用ohids
				
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String ohids = request.getParameter("ohids").replace("[", "").replace("]", "").replace("\"", "");//获取到jsp中的值check
			
			for (String ohid : ohids.split(",")) {	//传入单条开票数据
				//1.验证该订单已作废
				boolean zf_flag=false;
				zf_flag = ohservice.judgeZfStatus(ohid);
				if (zf_flag) {
					Boolean is_invalid = true;
					is_invalid = vihservice.judgeOhIs2Order(ohid);	//判断是否已进行生成单据操作
					if (!is_invalid) {
						//2.复制oh,ol记录,并更新ohid为ohid+'_*',更新原oh,ol记录的kpbz:1->0,更新原oh的is_zf:1->0
						json = vihservice.updateKpbzAndIz_zf120(ohid);
						//3.检查涉及的od记录,是否都未开票,是:更新kpbz:1->0
						vihservice.updateOdKpbz(ohid);
						
						msg = msg + ohid + ":生成单据成功!";
					} else {
						msg = msg + ohid + ":已生成单据不能再次生成!";
					}
					
				} else {
					msg =  msg + ohid + ":发票未作废!";
				}
				msg+="\n";
			}
			
			json.setSuccess(true);
			json.setMsg(msg);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
		} finally {
			writeJson(json);
		}
	}
	
	
}



