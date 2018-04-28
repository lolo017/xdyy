package aisino.reportform.action.base.fpmng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.fpmng.dzfp.ExcelServiceI;

/**
 * 
* @Title:ExcltemplateAction 
* @Description: excel模板导入
* Company    JS-YFB LTD
* @author 吕振宇 曹梦媛
* @version V1.0    
* @date 2018年1月2日 上午10:21:49
 */
@Namespace("/base/fpmng")
@Action
public class ExcltemplateAction extends BaseAction<T>{
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
	
	@Autowired
	public OrderDataZServiceI odservice;
	@Autowired
	public ExcelServiceI exservice;
	
	/**
	 * 
	 * @Title: load
	 * @Description: 加载导入表模板
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void load(){
		Json json=new Json();
		
		String tablename=getRequest().getParameter("tablename");
		String sql="SELECT CONVERT(VARCHAR(20),a.name) name,CONVERT(VARCHAR(20),b.[value]) remark FROM syscolumns a"+
				" INNER JOIN sysobjects d ON a.id = d.id AND d.xtype = 'U'"+
				" LEFT JOIN sys.extended_properties b ON a.id = b.major_id"+
				" AND a.colid = b.minor_id WHERE d.name = '"+tablename+"'";
		List<HashMap> columns1=odservice.findBySql(sql);
		columns1.remove(0);
		List<HashMap> cols=odservice.findBySql("select tablename from t_excl_field where tablename='"+tablename+"'");
		//若t_excl_field中没有该表记录，获取除主键id外所有字段，初始化
		if(cols.size()==0){
		json.setSuccess(true);
		json.setObj(columns1);
		writeJson(json);
		}else{
			//t_excl_field中有记录则
			List<HashMap> columns2=odservice.findBySql("select col name,colname remark from t_excl_field where tablename='"+tablename+"' order by seq asc");
			List<HashMap> new_total=new ArrayList<>();
			
			json.setSuccess(false);

			List<List> Summary=new ArrayList<>();
			Summary.add(columns1);
			Summary.add(columns2);
			json.setObj(Summary);
			writeJson(json);
		}
	}
	
	/**
	 * 
	 * @Title: importExcel
	 * @Description: 导入excel
	 * @author 曹梦媛
	 * @date 2018年1月2日 上午10:21:49
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void importExcel() {
		Json json = new Json();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		
		String tablename=request.getParameter("tablename");
		String column=request.getParameter("column");
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
			json = saveExcel2Db(toFile.getPath(),tablename,column);
			//删除上传文件临时文件
			toFile.delete();
			
		}catch(Exception e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("导入错误!");
		} finally {
			writeJson(json);
		}
		
	}
	
	/**
	 * 
	 * @Title: saveExcel2Db
	 * @Description: 传入excel文件路径, 转换后存入数据库
	 * @param: @param filepaths	excel上传服务器路径
	 * @param: @param tablename 数据待插入数据库表名
	 * @param: @param column 	字段名(以逗号分隔)
	 * @param: @return 
	 * @return: Json   
	 * @throws
	 */
	public Json saveExcel2Db(String filepaths,String tablename,String columns) throws Exception {
		Json json = new Json();
		json.setMsg("导入成功!");
		json.setSuccess(true);
		boolean is_sameFlag=true;	//标记: 同订单号的单据头信息是否相同
		
		try {
			if ("t_orderdata".equals(tablename)) {
				//发票订单信息
				List<OrderDataZ> odz_list = new ArrayList<OrderDataZ>();
				odz_list = odservice.excel2Odlist(filepaths,columns);
				//判断l里的相同单据号码是否对应相同的购房信息等订单头信息
				List<String> djhm_list = new ArrayList<>();	//存储l里的djhm,distinct
				for (OrderDataZ odz : odz_list) {
					if (!djhm_list.contains(odz.getDjhm()) ) {
						//如果djhm_list内没有该odz的djhm
						djhm_list.add(odz.getDjhm());
					}
				}
				//logger.info(JSON.toJSONStringWithDateFormat(djhm_list, "yyyy-MM-dd HH:mm:ss"));
//			System.out.println(djhm_list);
				
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
						if ( !(tmplist.get(odz_No)).compare(tmplist.get(odz_No+1)) ) {
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
				
			} else {	//非发票信息导入
				List<HashMap> odz_list = new ArrayList<HashMap>();
				odz_list = odservice.saveExcel2List(filepaths,columns);
				//其他表导入, 以HashMap方式存储
				for(HashMap hash : odz_list) {
					exservice.saveHashMap(hash,tablename,columns);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			
		} finally {
		}
		return json;
		
	}
	
	/**
	 * 
	 * @Title: save_template
	 * @Description: 保存模板字段
	 * @author 吕振宇
	 * @date 2018年1月2日 上午10:21:49
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void save_template(){
		String table_name=getRequest().getParameter("table_name");
		String array=getRequest().getParameter("array");
		String name_arr=getRequest().getParameter("name_arr");
		Json json=new Json();
		if(!StringUtils.isBlank(array)){
			odservice.executeSql("delete t_excl_field where tablename='"+table_name+"'");
			array=array.replace("[", "").replace("]", "").replace("\"", "");
			name_arr=name_arr.replace("[", "").replace("]", "").replace("\"", "");
			String fields[]=array.split(",");
			String names[]=name_arr.split(",");
			for(int i=0;i<fields.length;i++){
				String sql="insert into t_excl_field(id,tablename,col,seq,colname) values('"+UUID.randomUUID().toString()+"','"+table_name+"','"+fields[i]+"','"+i+"','"+names[i]+"')";
				odservice.executeSql(sql);
			}
		}else{
			json.setMsg("未设置模板！");
			json.setSuccess(true);
		}
		json.setMsg("保存成功！");
		json.setSuccess(true);
		writeJson(json);
	}
	/**
	 * 
	 * @Title: delete_template
	 * @Description: 删除模板
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void delete_template(){
		Json json=new Json();
		String table_name=getRequest().getParameter("table_name");
		int i=odservice.executeSql("delete t_excl_field where tablename='"+table_name+"'");
		if(i>0){
			json.setMsg("删除成功！");	
		}
		writeJson(json);
	}


}