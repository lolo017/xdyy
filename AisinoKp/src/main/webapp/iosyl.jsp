<%@ page language="java" contentType="application/pdf;charset=UTF-8" import="java.util.*,java.io.*"
pageEncoding="UTF-8"%>
<%	
   out.clear();
   out = pageContext.pushBody();
   response.setContentType("application/pdf");
  String pdfPath= (String)request.getParameter("pdf");
   pdfPath=pdfPath.replace("pdf=","");
   try {
    String strPdfPath = new String("D://pdf//"+pdfPath);
	//String name=pdfPath.substring(pdfPath.lastIndexOf("//")+1)
	//name=name.replace(".jsp",".pdf");
	//System.out.println(name);
	 //response.addHeader("Content-Disposition","attachment;filename=" + name);  


    //�жϸ�·���µ��ļ��Ƿ����
    File file = new File(strPdfPath);
    if (file.exists()) {
     DataOutputStream temps = new DataOutputStream(response
       .getOutputStream());
     DataInputStream in = new DataInputStream(
       new FileInputStream(strPdfPath));
     byte[] b = new byte[2048];
     while ((in.read(b)) != -1) {
      temps.write(b);
      temps.flush();
     }
     in.close();
     temps.close();
    } else {
     out.print(strPdfPath + " �ļ�������!");
    }

   } catch (Exception e) {
    out.println(e.getMessage());
   }
%>
