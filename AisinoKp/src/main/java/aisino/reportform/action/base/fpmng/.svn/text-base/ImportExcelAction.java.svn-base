
package aisino.reportform.action.base.fpmng;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;

/**
 * 
* @Title:ImportExcelAction 
* @Description: 导入Excel:商品匹配表,开票数据表
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年10月27日 上午9:10:34
 */
@Namespace("/base/fpmng")
@Action
public class ImportExcelAction extends BaseAction<SysOrderHead>{
	
	@Autowired
	public OrderDataZServiceI odservice;	//初始订单Service注入
	@Autowired
	public SysOrderHeadServiceI ohservice;	//系统单据Service注入
	@Autowired
	public SysOrderLineServiceI olservice;	//系统单据明细Service注入
	
	private File file;	//jsp页面传来的文件
	private String fileFileName;	//jsp页面传来的文件的文件名
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	/**
	 * 
	 * @Title: importEInvoiceInfoExcel
	 * @Description: 导入excel数据至订单表
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void importEInvoiceInfoExcel() {
		Json json = new Json();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		String uploadPath = "" ;
		File toFile = null;
		try{
			if(file != null){
				//上传文件输入流
				InputStream is = new FileInputStream(file);
				//获取上传存储路径
				uploadPath = ServletActionContext.getServletContext().getRealPath("upload");
				//System.out.println("uploadPath==>"+uploadPath);
				//输出文件创建
				toFile = new File(uploadPath, fileFileName);
				//文件输出流
				OutputStream os = new FileOutputStream(toFile);
				byte[] buffer = new byte[1024]; 
				int length = 0;
				//读文件
				while ((length = is.read(buffer)) > 0) {  
		            os.write(buffer, 0, length);  
		        }
				is.close();//输入流关闭
				os.flush();//强制全部输出
				os.close();//输出流关闭
			}
			//获取excel表数据至对象列表,并保存至t_orderdata表
			json = saveExcel2Db(toFile.getPath());
				//删除上传文件临时文件
				toFile.delete();
			
		}catch(Exception e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		writeJson(json);
	}
	
	
	/**
	 * 
	 * @Title: saveExcel2Db
	 * @Description: 传入excel文件路径, 转换后存入数据库
	 * @param: @param filepaths
	 * @param: @return 
	 * @return: Json   
	 * @throws
	 */
	public Json saveExcel2Db(String filepaths) {
		Json json = new Json();
		json.setSuccess(true);
		
		List<OrderDataZ> odz_list = new ArrayList<OrderDataZ>();
		odz_list = odservice.excel2Odlist(filepaths);
		//判断l里的相同单据号码是否对应相同的购房信息等订单头信息
		List<String> djhm_list = new ArrayList<>();	//存储l里的djhm,distinct
		for (OrderDataZ odz : odz_list) {
			if (!djhm_list.contains(odz.getDjhm()) ) {
				//如果djhm_list内没有该odz的djhm
				djhm_list.add(odz.getDjhm());
			}
		}
		//logger.info(JSON.toJSONStringWithDateFormat(djhm_list, "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(djhm_list);
		boolean is_sameFlag=true;	//标记: 同订单号的单据头信息是否相同
		for (String djhm : djhm_list ) {
			if (!is_sameFlag) {
				break;
			}
			List<OrderDataZ> tmplist = new ArrayList<OrderDataZ>();
			for(OrderDataZ od : odz_list){
				//存放单据号码为djhm的OrderDataZ
				if(od.getDjhm().equals(djhm)){
					tmplist.add(od);
				}
			}	

			for(int odz_No=0; odz_No<tmplist.size()-1; odz_No++){
				
				//判断tmplist 里面的购方名称相同不
				if (!(tmplist.get(odz_No).getBz()).equals(tmplist.get(odz_No+1).getBz()) //备注不相同
						|| !(tmplist.get(odz_No).getEmail()).equals(tmplist.get(odz_No+1).getEmail())
						|| !(tmplist.get(odz_No).getFhr()).equals(tmplist.get(odz_No+1).getFhr())
						|| !(tmplist.get(odz_No).getSkr()).equals(tmplist.get(odz_No+1).getSkr())
						|| !(tmplist.get(odz_No).getFpzl()).equals(tmplist.get(odz_No+1).getFpzl())
						|| !(tmplist.get(odz_No).getGfdzdh()).equals(tmplist.get(odz_No+1).getGfdzdh())
//						|| !(tmplist.get(odz_No).getGfhm()).equals(tmplist.get(odz_No+1).getGfhm())
						|| !(tmplist.get(odz_No).getGfmc()).equals(tmplist.get(odz_No+1).getGfmc())
						|| !(tmplist.get(odz_No).getGfsh()).equals(tmplist.get(odz_No+1).getGfsh())
						|| !(tmplist.get(odz_No).getGfyhzh()).equals(tmplist.get(odz_No+1).getGfyhzh())
						|| !(tmplist.get(odz_No).getHsbz()).equals(tmplist.get(odz_No+1).getHsbz())
						|| !(tmplist.get(odz_No).getKpbz()).equals(tmplist.get(odz_No+1).getKpbz())
						|| !(tmplist.get(odz_No).getMobile()).equals(tmplist.get(odz_No+1).getMobile())
				) {
					is_sameFlag=false;
					break;
				}					
			}//一个djhm的订单头判断完毕
			
			
		}
		if (!is_sameFlag) {
			//存在不相同订单头的djhm
			json.setSuccess(false);
			json.setMsg("excel存在同订单号的数据订单头信息不唯一!");
		} else {
			
			odservice.saveList(odz_list);
			json.setMsg("保存成功!");
		}
		return json;
	}
	
	
}



