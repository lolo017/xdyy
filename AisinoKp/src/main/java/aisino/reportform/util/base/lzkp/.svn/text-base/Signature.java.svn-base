package aisino.reportform.util.base.lzkp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Signature {
//签章类
	
	public static String makeXml(PageData pd,String InvoiceXML,String dataExchangeId)
	{
	    //生成签章XML
		
		try{
			//CA加密
//			final String trustsBytes = CaConstant.getProperty("PUBLIC_TRUSTS");
//			String decryptPFXBytes = CaConstant.getProperty("CLIENT_DECRYPTPFX");
//			String decryptPFXKey = CaConstant.getProperty("CLIENT_DECRYPTPFX_KEY");
//			final PKCS7 pkcs7Client = new PKCS7(FileUtils.readFileToByteArray(new File(trustsBytes)), FileUtils.readFileToByteArray(new File(decryptPFXBytes)), decryptPFXKey);
//			final byte[] encodeData = pkcs7Client.pkcs7Encrypt(InvoiceXML, FileUtils.readFileToByteArray(new File(CaConstant.getProperty("PLATFORM_DECRYPTCER"))));
//			//base64加密
//			final byte[] base64Date = Base64Helper.encode(encodeData);
//			String strXML= new String(base64Date);
			
			String strXML= Base64Helper.encode(InvoiceXML);
			
			
			String s = "SJLY" + (char)2 + "06" + (char)3 + "FPLX" + (char)2 + "1";
		    System.out.println(new String(Base64Helper.encode(s)));
		    Date date=new Date();
		    DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	          String dateStr=df.format(date);
	          
	          
     		String sigXml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"+
				 "<interface xmlns=\"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.chinatax.gov.cn/tirip/dataspec/interfaces.xsd\" version=\"DZFP1.0\" >"+
				 "<globalInfo>"+
				 "<terminalCode>0</terminalCode>"+
				 "<appId>AA0DDF541B152FBA</appId>"+
				 "<version>2.0</version>"+
				"<interfaceCode>ECXML.FPQZ.BC.E.INV</interfaceCode>"+
				 "<requestCode>"+pd.getString("USERNAME").toString()+"</requestCode>"+
				 "<requestTime>"+dateStr+"</requestTime>"+
				 "<responseCode>144</responseCode>"+
				 "<dataExchangeId>"+dataExchangeId+"</dataExchangeId>"+
				 "<userName>"+pd.getString("USERNAME").toString()+"</userName>"+
				 "<passWord>"+pd.getString("PASSWORD").toString()+"</passWord>"+
				 "<taxpayerId>"+pd.getString("TAXCODE").toString()+"</taxpayerId>"+
				 "<authorizationCode>"+pd.getString("AUTHORIZATIONCODE").toString()+"</authorizationCode>"+
				"</globalInfo>"+
				"<returnStateInfo>"+
				"<returnCode/>"+
				 "<returnMessage/>"+
				"</returnStateInfo>"+ 
				"<Data>"+
				 "<dataDescription>"+
				" <zipCode>0</zipCode>"+
				  "<encryptCode>0</encryptCode>"+
				  "<codeType>0</codeType>"+
				 "</dataDescription>"+
				 "<content>" +strXML+
				 "</content>"+
				"</Data>"+
				"</interface>";
			return sigXml;
		}catch(Exception e)
		{
			return "失败";
		}
	}
	
	public static void main(String[] args) {
		PageData pd=new PageData();
		Map<String, String> mp = new HashMap<String, String>();
		mp.put("TAXCODE", "510198062426015");
		mp.put("USERNAME", "15126582");
		mp.put("PASSWORD", "meLGL1cQn7LGMxndLeL28AzenecQL1nQM7t08jf1MQt=");
		mp.put("AUTHORIZATIONCODE", "5162426015");
		pd.addMap(mp);
		System.out.println(makeXml(pd,"111","20160101010"));
	}
}
