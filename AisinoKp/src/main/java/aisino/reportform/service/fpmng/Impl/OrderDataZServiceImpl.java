package aisino.reportform.service.fpmng.Impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.fsg.Details;
import aisino.reportform.model.fsg.FsgOrderHead;
import aisino.reportform.model.fsg.FsgShop;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class OrderDataZServiceImpl extends BaseServiceImpl<OrderDataZ> implements OrderDataZServiceI {

	@Override
	public String checkOrderKP(List<SysOrderLine> list) {
		for(SysOrderLine orderline:list){
			List<OrderDataZ> odlist = orderline.getOrderDataZs();
			for(OrderDataZ od:odlist){
				if(od.getKpbz().equals("1")){
					return orderline.getSysOrderHead().getOhid();
				}
			}
		}
		return "";
	}
	
	@Override
	public List<OrderDataZ> excel2Odlist(String filepaths) {
		String filepath=filepaths;
		boolean sucFlag=true;	//出错标志: true-正常, false-出错
		int excNo=0;	//执行行数
		boolean isE2007 = false;	// 判断是否是excel2007格式
		List<OrderDataZ> l = new ArrayList<OrderDataZ>();
		
		try {
			if (filepath.endsWith("xlsx")) {
				isE2007 = true;
			}
			InputStream input = new FileInputStream(filepath); // 建立输入流
			Workbook wb = null;
			
			//根据文件类型调用不同的poi方法
			if(isE2007){
				wb = new XSSFWorkbook(input);
			}else{
				wb = new HSSFWorkbook(input);
			}
			int se = wb.getNumberOfSheets();// 得到sheet
//			System.out.println(se);
			wb.getSheetAt(0);
			
			Sheet sheet = wb.getSheetAt(0);
			int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
//			System.out.println("totalRow:"+totalRow);
			
			for (int i = 1; i <= totalRow; i++) {
				Row row = sheet.getRow(i);//获取表格行数据 	
				OrderDataZ ship = new OrderDataZ();
				int cellNum = row.getLastCellNum(); //获取表格行单元格数
				
				for(int j=0;j<cellNum;j++){
					Cell cell = row.getCell(j);
					String cellValue="";
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellValue = cell.getStringCellValue().replace("\t", "").trim();
					} else {
					}
					
					
					switch(j){
					case 0: 
						ship.setDjhm(cellValue); 
						break;
					case 1: 
						ship.setSphm(cellValue);
						break;
					case 2: 
						ship.setSpmc(cellValue);
						break;
					case 3: 
						ship.setGgxh(cellValue);
						break;
					case 4: 
						ship.setDw(cellValue);
						break;
					case 5: 
						if (!"".equals(cellValue)) {
							ship.setSl(new BigDecimal(cellValue));
						} else {
							ship.setSl(new BigDecimal("0"));
						}
						
						break;
					case 6: 
						if (!"".equals(cellValue)) {
							ship.setDj(new BigDecimal(cellValue));
						} else {
							ship.setDj(new BigDecimal("0"));
						}
						
						break;
					case 7: 
						if (!"".equals(cellValue)) {
							ship.setJe(new BigDecimal(cellValue));
						} else {
							ship.setJe(new BigDecimal("0"));
						}
						
						break;
					case 8: 
						ship.setGfmc(cellValue);
						break;
					case 9: 
						ship.setGfsh(cellValue);
						break;
					case 10: 
						ship.setMobile(cellValue);
						break;
					case 11: 
						ship.setGfdzdh(cellValue);
						break;
					case 12: 
						ship.setGfyhzh(cellValue);
						break;
					case 13: 
						ship.setSkr(cellValue);
						break;
					case 14: 
						ship.setFhr(cellValue);
						break;
					case 15: 
						if (!"".equals(cellValue)) {
							ship.setHsbz(cellValue);
						} else {
							ship.setHsbz("1");
						}
						
						break;
					case 16: 
						if (!"".equals(cellValue)) {
							ship.setSlv(new BigDecimal(cellValue));
						} else {
							ship.setSlv(new BigDecimal("17"));
						}
						break;
					case 17: 
						ship.setEmail(cellValue);
						break;
					case 18: 
						if (!"".equals(cellValue)) {
							ship.setFpzl(cellValue);
						} else {
							ship.setFpzl("51");
						}
						break;
					case 19: 
						ship.setBz(cellValue);
						break;
					case 20: 
						ship.setSsflbm(cellValue);
						break;
					default: break;
					}
//					ship.setOlid(UUID.randomUUID().toString());
					ship.setKpbz("0");
					ship.setCreatetime(new Date());
				}
				//service.save(ship);
				l.add(ship);//单行数据存入List<OrderDataZ>
				excNo++;	//执行行数自增
				
			}//for循环
			
			
			
			//保存excel数据至数据库t_orderdata
			//odservice.saveList(l);
			return l;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
		}
		//返回执行结果
		
		
	}

	@Override
	public List<HashMap> saveExcel2List(String filepaths, String column) {
		// TODO Auto-generated method stub
		String filepath=filepaths;
		boolean sucFlag=true;	//出错标志: true-正常, false-出错
		int excNo=0;	//执行行数
		boolean isE2007 = false;	// 判断是否是excel2007格式
		List<HashMap> l = new ArrayList<HashMap>();
		
		try {
			if (filepath.endsWith("xlsx")) {
				isE2007 = true;
			}
			InputStream input = new FileInputStream(filepath); // 建立输入流
			Workbook wb = null;
			
			//根据文件类型调用不同的poi方法
			if(isE2007){
				wb = new XSSFWorkbook(input);
			}else{
				wb = new HSSFWorkbook(input);
			}
			int se = wb.getNumberOfSheets();// 得到sheet
//			System.out.println(se);
			wb.getSheetAt(0);
			
			Sheet sheet = wb.getSheetAt(0);
			int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
//			System.out.println("totalRow:"+totalRow);
			
			for (int i = 1; i <= totalRow; i++) {
				Row row = sheet.getRow(i);//获取表格行数据 	
				HashMap<String,String> rowInfo = new HashMap<String,String>();
				//int cellNum = row.getLastCellNum(); //获取表格行单元格数
				String cn[] = column.split(",");	//页面传入选择的表字段
				for(int j=0;j<cn.length;j++){
					Cell cell = row.getCell(j);
					String cellValue="";
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellValue = cell.getStringCellValue().replace("\t", "").trim();
					} else {
					}
					
					rowInfo.put(cn[j], cellValue);
					
				}
				//service.save(ship);
				l.add(rowInfo);//单行数据存入List<OrderDataZ>
				excNo++;	//执行行数自增
				
			}//for循环
			
			
			
			//保存excel数据至数据库t_orderdata
			//odservice.saveList(l);
			return l;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
		}
		//返回执行结果
	}
	
	@Override
	public List<OrderDataZ> excel2Odlist(String filepaths, String columns) {
		String filepath=filepaths;
		boolean sucFlag=true;	//出错标志: true-正常, false-出错
		int excNo=0;	//执行行数
		boolean isE2007 = false;	// 判断是否是excel2007格式
		List<OrderDataZ> l = new ArrayList<OrderDataZ>();
		String columArr[] = columns.split(",");
		
		try {
			if (filepath.endsWith("xlsx")) {
				isE2007 = true;
			}
			InputStream input = new FileInputStream(filepath); // 建立输入流
			Workbook wb = null;
			
			//根据文件类型调用不同的poi方法
			if(isE2007){
				wb = new XSSFWorkbook(input);
			}else{
				wb = new HSSFWorkbook(input);
			}
			int se = wb.getNumberOfSheets();// 得到sheet
//			System.out.println(se);
			wb.getSheetAt(0);
			
			Sheet sheet = wb.getSheetAt(0);
			int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
//			System.out.println("totalRow:"+totalRow);
			
			for (int i = 1; i <= totalRow; i++) {
				Row row = sheet.getRow(i);//获取表格行数据 	
				OrderDataZ ship = new OrderDataZ();
				ship.setCreatetime(new Date());
				int cellNum = columArr.length;//row.getLastCellNum(); //获取表格行单元格数
				
				for(int j=0;j<cellNum;j++){
					Cell cell = row.getCell(j);
					String cellValue="";
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cellValue = cell.getStringCellValue().replace("\t", "").trim();
					} else {
					}
					
					String column = ""; 
					column = columArr[j];
					if (column.equals("DJHM")) {
						ship.setDjhm(cellValue); 
					} else if (column.equals("FPZL")) {
						if (!"".equals(cellValue)) {
							ship.setFpzl(cellValue);
						} else {
							ship.setFpzl("51");
						}
					} else if (column.equals("GFHM")) {
						if (!"".equals(cellValue)) {
							ship.setGfhm(cellValue);
						} 
					} else if (column.equals("GFMC")) {
						if (!"".equals(cellValue)) {
							ship.setGfmc(cellValue);
						}
					} else if (column.equals("GFSH")) {
						if (!"".equals(cellValue)) {
							ship.setGfsh(cellValue);
						}
					} else if (column.equals("GFYHZH")) {
						if (!"".equals(cellValue)) {
							ship.setGfyhzh(cellValue);
						}
					} else if (column.equals("GFDZDH")) {
						if (!"".equals(cellValue)) {
							ship.setGfdzdh(cellValue);
						}
					} else if (column.equals("BZ")) {
						if (!"".equals(cellValue)) {
							ship.setBz(cellValue);
						}
					} else if (column.equals("SKR")) {
						if (!"".equals(cellValue)) {
							ship.setSkr(cellValue);
						}
					} else if (column.equals("FHR")) {
						if (!"".equals(cellValue)) {
							ship.setFhr(cellValue);
						}
					} else if (column.equals("SPXH")) {
						if (!"".equals(cellValue)) {
							ship.setSpxh(Integer.parseInt(cellValue));
						} else {
							ship.setSpxh(1);
						}
					} else if (column.equals("SPHM")) {
						if (!"".equals(cellValue)) {
							ship.setSphm(cellValue);
						}
					} else if (column.equals("SPMC")) {
						if (!"".equals(cellValue)) {
							ship.setSpmc(cellValue);
						}
					} else if (column.equals("GGXH")) {
						if (!"".equals(cellValue)) {
							ship.setGgxh(cellValue);
						}
					} else if (column.equals("DW")) {
						if (!"".equals(cellValue)) {
							ship.setDw(cellValue);
						}
					} else if (column.equals("SL")) {
						if (!"".equals(cellValue)) {
							ship.setSl(new BigDecimal(cellValue));
						} else {
							ship.setSl(new BigDecimal("0"));
						}
					} else if (column.equals("DJ")) {
						if (!"".equals(cellValue)) {
							ship.setDj(new BigDecimal(cellValue));
						} else {
							ship.setDj(new BigDecimal("0"));
						}
					} else if (column.equals("JE")) {
						if (!"".equals(cellValue)) {
							ship.setJe(new BigDecimal(cellValue));
						} else {
							ship.setJe(new BigDecimal("0"));
						}
					} else if (column.equals("HSBZ")) {
						if (!"".equals(cellValue) && ("1".equals(cellValue) || "0".equals(cellValue) ) ) {
							ship.setHsbz(cellValue);
						} else {
							ship.setHsbz("1");
						}
					} else if (column.equals("SLV")) {
						if (!"".equals(cellValue)) {
							ship.setSlv(new BigDecimal(cellValue));
						} else {
							ship.setSlv(new BigDecimal("17"));
						}
					} else if (column.equals("SSFLBM")) {
						if (!"".equals(cellValue)) {
							ship.setSsflbm(cellValue);
						} 
					} else if (column.equals("MOBILE")) {
						if (!"".equals(cellValue)) {
							ship.setMobile(cellValue);
						} 
					} else if (column.equals("EMAIL")) {
						if (!"".equals(cellValue)) {
							ship.setEmail(cellValue);
						} 
					} 
				} //一行存入类完毕
				//service.save(ship);
				l.add(ship);//单行数据存入List<OrderDataZ>
				excNo++;	//执行行数自增
				
			}//for循环
			
			
			
			//保存excel数据至数据库t_orderdata
			//odservice.saveList(l);
			//返回执行结果
			return l;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("excel出错行:"+excNo+",建议检查此上下行.");
			return null;
		} finally {
		}
		
	}

	
}
