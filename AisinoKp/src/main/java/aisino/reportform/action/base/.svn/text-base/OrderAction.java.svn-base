package aisino.reportform.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import aisino.reportform.model.base.ShippingList;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.FileUploadServiceI;
import aisino.reportform.service.base.ShippingListServiceI;
import aisino.reportform.util.base.SmsBase;

@Namespace("/base")
@Action
public class OrderAction extends BaseAction<ShippingList>{

	private File file;
    private String fileContentType;
	private String fileFileName;
	
	
	private FileUploadServiceI fileUploadServiceI;

	@Autowired
	public void setFileUploadServiceI(FileUploadServiceI fileUploadServiceI) {
		this.fileUploadServiceI = fileUploadServiceI;
	}
	@Autowired
	public void setService(ShippingListServiceI service) {
		this.service = service;
	}
	
	@Override
	public void save(){
		Json json = new Json();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		String uploadPath = "" ;
		File toFile = null;
		try{
			if(file != null){
				InputStream is = new FileInputStream(file);
				uploadPath = ServletActionContext.getServletContext().getRealPath("upload");
				toFile = new File(uploadPath, fileFileName);
//					if(toFile.exists())
//						toFile.delete();
				OutputStream os = new FileOutputStream(toFile);
				byte[] buffer = new byte[1024]; 
				int length = 0;
				while ((length = is.read(buffer)) > 0) {  
		            os.write(buffer, 0, length);  
		        }
				is.close();
				os.close();
			}
			List<ShippingList> list = gainList(toFile.getPath());
			service.saveList(list);
			json.setSuccess(true);
			json.setMsg("OK");
			
//			System.out.println("---------> " + SmsBase.SendSms("15002810848,18080188195,15208390145", "【四川金穗】你的发票已寄出"));
			
			ExecutorService threadPool = Executors.newSingleThreadExecutor();
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					List<ShippingList> li = service.findBySql("select id,ems,distribution_num,sender,recevier,telphone,company,address,type,internal_info,remarks,tax_code,order_num,is_sms,amount from T_SHIPPING_LIST where IS_SMS is null");
					for(int i=0;i<li.size();i++){
						Object ob = li.get(i);
						Map map = (Map) ob;
						String statue = "";
						try {
							statue = SmsBase.SendSms(map.get("TELPHONE").toString(), "【四川金穗】您的发票已寄出！");
							System.out.println("=======> " + map.get("TELPHONE").toString() + ",状态：" + statue);
							service.executeSql("update T_SHIPPING_LIST set IS_SMS='1' WHERE ID='" + map.get("ID").toString() + "'");
						} catch (UnsupportedEncodingException e) {
							System.out.println("短信发送状态： " + statue);
							service.executeSql("update T_SHIPPING_LIST set IS_SMS='"+ statue +"' WHERE ID='" + map.get("ID").toString() + "'");
							e.printStackTrace();
						}
					}
				}
			});
		}catch(Exception e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		writeJson(json);
			
	}
	
	
	public List<ShippingList> gainList(String filepaths) throws Exception {
		String filepath=filepaths;
	
		boolean isE2007 = false; // 判断是否是excel2007格式
		if (filepath.endsWith("xlsx"))
			isE2007 = true;
			InputStream input = new FileInputStream(filepath); // 建立输入流
			Workbook wb = null;
			
			if(isE2007){
				wb = new XSSFWorkbook(input);
			}else{
				wb = new HSSFWorkbook(input);
			}
			int se = wb.getNumberOfSheets();// 得到sheet
			System.out.println(se);
			wb.getSheetAt(0);
			
			Sheet sheet = wb.getSheetAt(0);
			int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
			System.out.println("totalRow:"+totalRow);
			List<ShippingList> l = new ArrayList<ShippingList>();
			for (int i = 1; i <= totalRow; i++) {
				Row row = sheet.getRow(i); 	
				ShippingList ship = new ShippingList();
				int cellNum = row.getLastCellNum(); 
				for(int j=0;j<cellNum;j++){
					Cell cell = row.getCell(j); 
					cell.setCellType(Cell.CELL_TYPE_STRING);
	                String cellValue = cell.getStringCellValue();
	                switch(j){
		                case 0: ship.setEms(cellValue); break;
		                case 1: ship.setDistributionNum(cellValue);break;
		                case 2: ship.setSender(cellValue);break;
		                case 3: ship.setRecevier(cellValue);break;
		                case 4: ship.setTelphone(cellValue);break;
		                case 5: ship.setCompany(cellValue);break;
		                case 6: ship.setAddress(cellValue);break;
		                case 7: ship.setType(cellValue);break;
		                case 8: ship.setInternalInfo(cellValue);break;
		                case 9: ship.setRemarks(cellValue);break;
		                case 10: ship.setTaxCode(cellValue);break;
		                case 11: ship.setOrderNum(cellValue);break;
		                case 12: ship.setAmount(cellValue);break;
		                default: break;
	                }
				}
				l.add(ship);
			}
			return l;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
}
