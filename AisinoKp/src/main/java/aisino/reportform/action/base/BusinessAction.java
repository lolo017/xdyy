package aisino.reportform.action.base;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;
import aisino.reportform.action.BaseAction;
import aisino.reportform.db.DatabaseContextHolder;
import aisino.reportform.model.base.BusinessMessage;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.service.base.BusinessServiceI;
import aisino.reportform.util.base.ConfigUtil;

@Namespace("/base")
@Action
public class BusinessAction extends BaseAction<BusinessMessage> {
	
	
	@Autowired
	public void setService(BusinessServiceI service) {
		this.service = service;	
	}
	
	public void doNotNeedSecurity_businessList() throws SQLException, IOException, DocumentException{
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_3);
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Grid grid =new Grid();
		String sql="select xh,khdid,mx,sj from pm_message_mx  where xh='PZZ0000001'";
		String allData = "select count(1) from pm_message_mx where xh='PZZ0000001'";
		List<Map> list = service.findBySql(sql,page,rows);
		for(Map map : list){
			Clob clob = (Clob) map.get("MX");
	        Element root = ClobToElement(clob);
	        map.put("companyname", root.element("msg").element("companyname").getText());
	        map.put("contacts", root.element("msg").element("contacts").getText());
	        map.put("tel", root.element("msg").element("tel").getText());
	        map.put("filename", root.element("msg").element("filename").getText());
	        map.remove("MX");
		}
		grid.setRows(list);
		grid.setTotal(service.countBySql(allData));
		writeJson(grid);
	}
	
	public void doNotNeedSecurity_downFile() throws IOException, SQLException, DocumentException{
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_3);
		String xh = this.getRequest().getParameter("xh");
		String khdid = this.getRequest().getParameter("khdid");
		String sql = "select xh,khdid,mx,sj from pm_message_mx  where xh='"+ xh +"'and khdid='"+ khdid +"'";
		List<Map> list = service.findBySql(sql);
		Map map = new HashMap();
		map = list.get(0);
		Clob clob = (Clob) map.get("MX");
        Element root = ClobToElement(clob);
        String filename = root.element("msg").element("filename").getText();
        String str=root.element("msg").element("attachment").getText();
		byte[] xml = getFromBASE64(str);
		byte[] last = null ;
		try {
			last = uncompress(xml);
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputStream toClient = new BufferedOutputStream(this.getResponse().getOutputStream());
		this.getResponse().setContentType("application/octet-stream;charset=UTF-8"); 
		this.getResponse().addHeader("Content-Disposition", "attachment; filename=\"" 
		+ new String(filename.getBytes("UTF-8"), "ISO8859-1") + "\"");
        toClient.write(last);
        toClient.flush();
        toClient.close();
		
	}
	
	 public Element ClobToElement(Clob clob) throws SQLException, IOException, DocumentException {
		 Reader is = clob.getCharacterStream();// 得到流
		 BufferedReader br = new BufferedReader(is);
		 String s = br.readLine();
		 StringBuffer sb = new StringBuffer();
		 while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			 sb.append(s);
			 s = br.readLine();
		 }
		 SAXReader sax = new SAXReader();
		 Document xmlDoc = sax.read(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
         Element root = xmlDoc.getRootElement();
		 return root;
	}
	
	// 解密
	public static byte[] getFromBASE64(String s) {
		if (s == null)
			return null;
		System.out.println("开始解密...");
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			System.out.println("解密完成。");
			return b;
		} catch (Exception e) {
			return null;
		}
	}
	
	// 解压
	public static byte[] uncompress(byte[] str) throws IOException{
		if (str == null || str.toString().length() == 0) {
			return str;
		}
		System.out.println("开始解压...");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[1024];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		System.out.println("解压完成。");
		return out.toByteArray();
	}
	
}
