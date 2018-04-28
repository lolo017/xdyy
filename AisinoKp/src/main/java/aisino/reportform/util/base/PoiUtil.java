package aisino.reportform.util.base;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import aisino.reportform.model.base.PageCode;

/**
 * POI工具类
 * 
 * @author 
 * 
 */
public class PoiUtil {

	/**
	 * 由于Excel当中的单元格Cell存在类型,若获取类型错误就会产生异常, 所以通过此方法将Cell内容全部转换为String类型
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String str = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				str = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				str = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				str = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
					str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cell.getDateCellValue());
				} else {
					str = String.valueOf((long) cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				str = String.valueOf(cell.getStringCellValue());
				break;
			default:
				str = null;
				break;
			}
		}
		return StringUtils.trim(str);
	}
	/**
	 * 导出excel
	 */
	public static String exportExcel(String excelName,List<Map> dataList,List<PageCode> pageCodeList,String maxrow){
		// 第一步，创建一个webbook，对应一个Excel文件
				HSSFWorkbook wb = new HSSFWorkbook();
				// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
				HSSFSheet sheet = wb.createSheet(excelName);
				// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
				//HSSFRow row = sheet.createRow((int) 0);
				// 第四步，创建单元格，并设置值表头 设置表头居中
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
				style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				//先合并表头单元格
				for (int i = 0; i < pageCodeList.size(); i++) {
					CellRangeAddress cra=new CellRangeAddress(pageCodeList.get(i).getRowNum(), pageCodeList.get(i).getRowNum()+pageCodeList.get(i).getRowspan()-1, pageCodeList.get(i).getColNum(), pageCodeList.get(i).getColNum()+pageCodeList.get(i).getColspan()-1); 
					 sheet.addMergedRegion(cra); 
				}
				//合并表头后赋值
				for(int j=0;j<=Integer.parseInt(maxrow);j++){
					Row r = sheet.createRow(j);  
					for (int jj = 0; jj < pageCodeList.size(); jj++) {
						if(pageCodeList.get(jj).getRowNum()==j){
							Cell cell = r.createCell(pageCodeList.get(jj).getColNum());  
					         cell.setCellValue(pageCodeList.get(jj).getCnName());  
					         cell.setCellStyle(style);
						}
					}
				}
				//写入数据
				for (int k = 0; k < dataList.size(); k++)
					 {
					 Row rr = sheet.createRow( k + Integer.parseInt(maxrow)+1);
					 for (int l = 0; l < pageCodeList.size(); l++) {
						 if(pageCodeList.get(l).getCnEnglish() != null){
							 
							 Cell cell = rr.createCell(pageCodeList.get(l).getColNum());  
							 if(dataList.get(k).get(pageCodeList.get(l).getCnEnglish())!=null)
							 {
					         cell.setCellValue(dataList.get(k).get(pageCodeList.get(l).getCnEnglish()).toString());  
							 }
							 else{
								 cell.setCellValue("");
							 }  
					         cell.setCellStyle(style);
							 }
						 }
					}

				// 第六步，将文件存到指定位置
				 //yyyy年MM月dd日 HH时mm分ss秒 E 
				 SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");   
			        String d=dateformat2.format(new Date());
				
				String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/excel");
				 String fileName=excelName+d+".xls";
				 String filepath=path+"\\"+fileName;
				 try{
					 FileOutputStream fout = new FileOutputStream(filepath);
						wb.write(fout);
						fout.close();
						return fileName;
				 }catch(Exception e){
					 e.printStackTrace();
					 return null;
				 }
				 
	}
	/**超出5W条数据时
	 * 导出大数据excel
	 */
	public static String exportBigExcel(String excelName,List<Map> dataList,List<PageCode> pageCodeList,String maxrow){
		SXSSFWorkbook wb = new SXSSFWorkbook(10000);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Sheet sheet = wb.createSheet(excelName);
		//先合并表头单元格
		for (int i = 0; i < pageCodeList.size(); i++) {
			CellRangeAddress cra=new CellRangeAddress(pageCodeList.get(i).getRowNum(), pageCodeList.get(i).getRowNum()+pageCodeList.get(i).getRowspan()-1, pageCodeList.get(i).getColNum(), pageCodeList.get(i).getColNum()+pageCodeList.get(i).getColspan()-1); 
			 sheet.addMergedRegion(cra); 
		}
		//合并表头后赋值
		for(int j=0;j<=Integer.parseInt(maxrow);j++){
			Row r = sheet.createRow(j);  
			for (int jj = 0; jj < pageCodeList.size(); jj++) {
				if(pageCodeList.get(jj).getRowNum()==j){
					Cell cell = r.createCell(pageCodeList.get(jj).getColNum());  
			         cell.setCellValue(pageCodeList.get(jj).getCnName());  
			         cell.setCellStyle(style);
				}
			}
		}
		//写入数据
		try{
		for (int k = 0; k < dataList.size(); k++)
			 {
			 Row rr = sheet.createRow( k + Integer.parseInt(maxrow)+1);
			 for (int l = 0; l < pageCodeList.size(); l++) {
				 if(pageCodeList.get(l).getCnEnglish() != null){
					 
					 Cell cell = rr.createCell(pageCodeList.get(l).getColNum());  
					 if(dataList.get(k).get(pageCodeList.get(l).getCnEnglish())!=null)
					 {
			         cell.setCellValue(dataList.get(k).get(pageCodeList.get(l).getCnEnglish()).toString());  
					 }
					 else{
						 cell.setCellValue("");
					 }
			         cell.setCellStyle(style);

					 }
				 }
			}
}catch( Exception e){
			System.out.println(e.toString());
		}

		SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String d = dateformat2.format(new Date());
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/excel");
		String fileName = excelName + d + ".xls";
		String filepath = path + "\\" + fileName;
		try {
			FileOutputStream fout = new FileOutputStream(filepath);
			wb.write(fout);
			fout.close();
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public static String testEx(){
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		 CellRangeAddress cra=new CellRangeAddress(0, 1, 0, 0);        
		 
	        //在sheet里增加合并单元格  
	        sheet.addMergedRegion(cra);  
	        cra=new CellRangeAddress(0, 0, 1, 2);        
			 
	        //在sheet里增加合并单元格  
	        sheet.addMergedRegion(cra);  
	         cra=new CellRangeAddress(0, 1, 3, 3);        
			 
	        //在sheet里增加合并单元格  
	        sheet.addMergedRegion(cra);  
	       // Row row = sheet.createRow(0);  
	          
	        //Cell cell_1 = row.createCell(3);  
	          
	       // cell_1.setCellValue("When you're right , no one remembers, when you're wrong ,no one forgets .");  
	          
	        //cell 位置3-9被合并成一个单元格，不管你怎样创建第4个cell还是第5个cell…然后在写数据。都是无法写入的。  
	        //Cell cell_2 = row.createCell(10);  
	          
	      //  cell_2.setCellValue("what's up ! ");  
		// 第六步，将文件存到指定位置
		 //yyyy年MM月dd日 HH时mm分ss秒 E 
		 SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");   
	        String d=dateformat2.format(new Date());
		
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/excel");
		 String fileName="ex"+d+".xls";
		 String filepath=path+"\\"+fileName;
		 try{
			 FileOutputStream fout = new FileOutputStream(filepath);
				wb.write(fout);
				fout.close();
				return fileName;
		 }catch(Exception e){
			 e.printStackTrace();
			 return null;
		 }
	}
	
	

}
