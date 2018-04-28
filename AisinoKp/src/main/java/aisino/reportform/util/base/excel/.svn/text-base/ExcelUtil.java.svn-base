package aisino.reportform.util.base.excel;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelUtil {
	public static void ExportExcel(String filename,HttpServletResponse response,String root,HttpServletRequest req) {
		try {
			InputStream in = new FileInputStream(root);
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            response.reset();  
           // response.setContentType("application/vnd.ms-excel");//設置輸出格式 Excel 頭信息.
            String fileName = filename;
            String agent = req.getHeader("USER-AGENT");
            if(agent != null && agent.indexOf("MSIE") != -1) {
                fileName = URLEncoder.encode(fileName, "UTF8");
                fileName = fileName.replaceAll("\\+", "%20");
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }

            response.setHeader("Content-Disposition", "attachment; filename="+fileName);  
            response.setContentType("application/octet-stream;charset=UTF-8"); 
            byte[] buffer = new byte[2048]; // 每次读8K字节  
            int count = 0;  
            // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
            while ((count = in.read(buffer)) > 0) { 
                out.write(buffer, 0, count); // 向服务端文件写入字节流  
            }
             out.flush();
             in.close();
             out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}



