import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;



public class jtest {

	public static void main(String[] args) {
		int a=0;
		int b=1;
		System.out.println(a==0&&(b==0||b==2));
	}
	
	
	
	
	public static void imm(){
		
	}
	public static void poi(){
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("name");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
//		CellRangeAddress cra=new CellRangeAddress(0, 3, 3, 9);        
//        
//        //在sheet里增加合并单元格  
//        sheet.addMergedRegion(cra);  
//          
//        Row r = sheet.createRow(0);  
//          
//        Cell cell_1 = r.createCell(3);  
//          
//        cell_1.setCellValue("When you're right , no one remembers, when you're wrong ,no one forgets .");  
//        Cell cell_11 = r.createCell(5);  
//        
//        cell_11.setCellValue(" one forgets .");  
          
        //cell 位置3-9被合并成一个单元格，不管你怎样创建第4个cell还是第5个cell…然后在写数据。都是无法写入的。  
        //Cell cell_2 = row.createCell(10);  
          
    //    cell_2.setCellValue("what's up ! "); 
//		for (int i = 0; i < pageCodeList.size(); i++) {
//			cell.setCellValue(pageCodeList.get(i).getCnName());
//			cell.setCellStyle(style);
//			cell = row.createCell((short) i + 1);
//		}
//		cell.setCellValue("id");
//		cell.setCellStyle(style);
//		cell = row.createCell( 1);
//		cell.setCellValue("name");
//		cell.setCellStyle(style);
//		cell = row.createCell( 2);
//		cell.setCellValue("age");
//		cell.setCellStyle(style);
//		cell = row.createCell( 3);
//		cell.setCellValue("sex");
//		cell.setCellStyle(style);
		
//		 for (int i = 0; i < dataList.size(); i++)
//		 {
//		 row = sheet.createRow((int) i + 1);
//		// PageCode pagecode = (PageCode) list.get(i);
//		 // 第四步，创建单元格，并设置值
//		 for (int j = 0; j < pageCodeList.size(); j++) {
//			 if(dataList.get(i).get(pageCodeList.get(j).getCnEnglish()) == null)
//				 continue;
//			 row.createCell((short) j).setCellValue(dataList.get(i).get(pageCodeList.get(j).getCnEnglish()).toString());
//		}
		CellRangeAddress cra1=new CellRangeAddress(0, 4, 0, 0); 
		 sheet.addMergedRegion(cra1); 
		 Row r = sheet.createRow(0);  
         Cell cell1 = r.createCell(0);  
         cell1.setCellValue(" 1");  
		 CellRangeAddress cra2=new CellRangeAddress(0, 2, 1, 1); 
		 sheet.addMergedRegion(cra2); 
         Cell cell2 = r.createCell(1);  
         cell2.setCellValue(" 2");
		 CellRangeAddress cra3=new CellRangeAddress(0, 1, 2, 2); 
		 sheet.addMergedRegion(cra3); 
         Cell cell3 = r.createCell(2);  
         cell3.setCellValue(" 3");
		 CellRangeAddress cra4=new CellRangeAddress(0, 1, 3, 4); 
		 sheet.addMergedRegion(cra4); 
		 Cell cell4 = r.createCell(3);  
         cell4.setCellValue(" 4");
		 CellRangeAddress cra5=new CellRangeAddress(3, 4, 1, 1); 
		 sheet.addMergedRegion(cra5); 
		 Row r3 = sheet.createRow(3); 
		 Cell cell5 = r3.createCell(1);  
         cell5.setCellValue(" 5");
		 CellRangeAddress cra6=new CellRangeAddress(0, 0, 5, 6); 
		 sheet.addMergedRegion(cra6); 
		 Cell cell6 = r.createCell(5);  
         cell6.setCellValue(" 6");
		// 第六步，将文件存到指定位置
		 //yyyy年MM月dd日 HH时mm分ss秒 E 
		 SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");   
	        String d=dateformat2.format(new Date());
		
		//String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/excel");
		 String fileName="name"+d+".xls";
		 String filepath="c:"+"\\"+fileName;
		 try{
			 FileOutputStream fout = new FileOutputStream(filepath);
				wb.write(fout);
				fout.close();
				
		 }catch(Exception e){
			 e.printStackTrace();
			 
		 }
	}
}

