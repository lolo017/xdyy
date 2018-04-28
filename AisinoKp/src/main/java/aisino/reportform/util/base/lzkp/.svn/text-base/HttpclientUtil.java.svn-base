package aisino.reportform.util.base.lzkp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.log4j.Logger;

import aisino.reportform.model.lzkp.Hc;

public class HttpclientUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HttpclientUtil.class);

	private HttpClient httpClient;
	private String namespace;
	private String methodName;
	private String wsdlLocation;
	private Map<String, String> patameterMap;

	public HttpclientUtil() {

	}

	public HttpclientUtil(String namespace, String methodName,
			String wsdlLocation, HttpClient httpClient,
			Map<String, String> patameterMap) {
		this.namespace = namespace;
		this.methodName = methodName;
		this.wsdlLocation = wsdlLocation;
		this.httpClient = httpClient;
		this.patameterMap = patameterMap;
	}

	private int invoke(Map<String, String> patameterMap) throws Exception {
		PostMethod postMethod = new PostMethod(wsdlLocation);
		String soapRequestData = buildRequestData(patameterMap);
		byte[] bytes = soapRequestData.getBytes("utf-8");
		InputStream inputStream = new ByteArrayInputStream(bytes, 0,
				bytes.length);
		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
				bytes.length, "application/soap+xml; charset=utf-8");
		postMethod.setRequestEntity(requestEntity);
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);

		} catch (Exception e) {
			logger.error("httpclientUtil.invoke调用失败：" + e.getMessage());
		} finally {
			logger.debug("释放连接");
			//postMethod.releaseConnection();
		}

		return statusCode;
	}

	private Hc invokeS(Map<String, String> patameterMap) throws Exception {
		PostMethod postMethod = new PostMethod(wsdlLocation);
		Hc hc = new Hc();
		hc.setStatus(0);
		String soapRequestData = buildRequestData(patameterMap);

		byte[] bytes = soapRequestData.getBytes("utf-8");
		InputStream inputStream = new ByteArrayInputStream(bytes, 0,
				bytes.length);
		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
				bytes.length, "application/soap+xml; charset=utf-8");
		postMethod.setRequestEntity(requestEntity);
		try {
			hc.setStatus(httpClient.executeMethod(postMethod));
			if (hc.getStatus() == 200) {
				hc.setRespon(postMethod.getResponseBodyAsString());
			}

		} catch (Exception e) {
			logger.error("httpclientUtil.invoke调用失败：" + e.getMessage());
		} finally {
			logger.debug("释放连接");
			//postMethod.releaseConnection();
			return hc;
		}

	}


	private String buildRequestData(Map<String, String> patameterMap) {
		StringBuffer soapRequestData = new StringBuffer();
		soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		soapRequestData
				.append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
						+ " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
						+ " xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
		soapRequestData.append("<soap12:Body>");
		soapRequestData.append("<" + methodName + " xmlns=\"" + namespace
				+ "\">");

		Set<String> nameSet = patameterMap.keySet();
		for (String name : nameSet) {
			soapRequestData.append("<" + name + ">" + patameterMap.get(name)
					+ "</" + name + ">");
		}

		soapRequestData.append("</" + methodName + ">");
		soapRequestData.append("</soap12:Body>");
		soapRequestData.append("</soap12:Envelope>");

		return soapRequestData.toString();
	}

	public String doGet() throws Exception {

		return String.valueOf(invoke(patameterMap));
	}

	public Hc doGetS() throws Exception {

		return invokeS(patameterMap);
	}
	public Hc doGetSoap1_1() throws Exception {

		return invoke_Soap1_1(patameterMap);
	}
	public Hc doGetSoap_qz()  throws Exception{
		return invokeS_qz(patameterMap);
	}
	public Hc doGetSoap_kp() throws Exception {
		return invokeS_kp(patameterMap);
	}


	private String buildRequestData_Soap1_1(Map<String, String> patameterMap) {
		StringBuffer soapRequestData = new StringBuffer();
		soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		soapRequestData.append("<soap:Envelope "
				+ "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		soapRequestData.append("<soap:Body>");
		soapRequestData.append("<ns2:" + methodName + " xmlns:ns2=\""
				+ namespace + "\" >");

		Set<String> nameSet = patameterMap.keySet();
		for (String name : nameSet) {
			soapRequestData.append("<" + name + ">" + patameterMap.get(name)
					+ "</" + name + ">");
		}

		soapRequestData.append("</ns2:" + methodName + ">");
		soapRequestData.append("</soap:Body>");
		soapRequestData.append("</soap:Envelope>");

		return soapRequestData.toString();
	}

	private Hc invoke_Soap1_1(Map<String, String> patameterMap)
			throws Exception {
		PostMethod postMethod = new PostMethod(wsdlLocation);
		Hc hc = new Hc();
		hc.setStatus(0);
		String soapRequestData = buildRequestData_Soap1_1(patameterMap);

		System.out.println(soapRequestData);
		byte[] bytes = soapRequestData.getBytes("utf-8");
		InputStream inputStream = new ByteArrayInputStream(bytes, 0,
				bytes.length);
		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
				bytes.length, "text/xml;charset=utf-8");
		postMethod.setRequestEntity(requestEntity);
		try {
			hc.setStatus(httpClient.executeMethod(postMethod));
			if (hc.getStatus() == 200) {
				hc.setRespon(postMethod.getResponseBodyAsString());
			}

		} catch (Exception e) {
			logger.error("httpclientUtil.invoke调用失败：" + e.getMessage());
		} finally {
			logger.debug("释放连接");
			//postMethod.releaseConnection();
			return hc;
		}

	}
/**
 * 发票签章请求
 * @param patameterMap
 * @return
 * @throws Exception
 */
	public Hc invokeS_qz(Map<String, String> patameterMap) throws Exception{
		PostMethod postMethod = new PostMethod(wsdlLocation);
		Hc hc = new Hc();
		hc.setStatus(0);
		//String soapRequestData=buildRequestData_qz(patameterMap);
		String soapRequestData=buildRequestData_qz_beta(patameterMap);
		//String soapRequestData="<?xml version=\"1.0\" encoding=\"utf-8\"?><interface xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.chinatax.gov.cn/tirip/dataspec/interfaces.xsd\" version=\"DZFP1.0\"><globalInfo><terminalCode>1</terminalCode><appId>ZZS_PT_DZFP</appId><version>2.0</version><interfaceCode>ECXML.FPQZ.BC.E.INV</interfaceCode><userName>15126582</userName><passWord>1234567890QjMxMzhGREMxNTZCMjM0OUVDRjhGMzZGNEIyODkzNkI=</passWord><taxpayerId>510198062426015</taxpayerId><authorizationCode>5162426015</authorizationCode><requestCode>15126582</requestCode><requestTime>2016-06-13 13:14:20 231</requestTime><responseCode></responseCode><dataExchangeId>1512658220160613111111111</dataExchangeId></globalInfo><returnStateInfo><returnCode/><returnMessage/></returnStateInfo><Data><dataDescription> <zipCode>0</zipCode><encryptCode>0</encryptCode><codeType>0</codeType></dataDescription><content>PFJFUVVFU1RfRlBRWiBjbGFzcz0iUkVRVUVTVF9GUFFaIj48RlBRWl9JTkZPPlUwcE1XQUl3TVFOR1VFeFlBakU9PC9GUFFaX0lORk8+PEZQUVpfRlBUIG线程 0x2548 已退出，返回值为 259 (0x103)。NsYXNzPSJGUFFaX0ZQVCI+PEZQUVFMU0g+c2NqczIwMTYwNjE0MTUyNzAwMDE8L0ZQUVFMU0g+PEtQTFg+MDwvS1BMWD48Qk1CX0JCSD4xLjA8L0JNQl9CQkg+PFFEX0JaPjA8L1FEX0JaPjxRRFhNTUM+PC9RRFhNTUM+PFhTRl9OU1JTQkg+NTEwMTk4MDYyNDI2MDE1PC9YU0ZfTlNSU0JIPjxYU0ZfTUM+5Zub5bed6Iiq5aSp6YeR56mX6auY5oqA5pyv5pyJ6ZmQ5YWs5Y+45oiQ6YO95YiG5YWs5Y+4PC9YU0ZfTUM+PFhTRl9EWkRIPjY2Nzc4ODExPC9YU0ZfRFpESD48WFNGX1lIWkg+MTExMTExMTE8L1hTRl9ZSFpIPjxHTUZfTlNSU0JIPjEyMzQ1Njc4PC9HTUZfTlNSU0JIPjxHTUZfTUM+5Zub5bed6YeR56mX5oiQ6YO95YiG5YWs5Y+4PC9HTUZfTUM+PEdNRl9EWkRIPjY2Nzc4ODIyPC9HTUZfRFpESD48R01GX1lIWkg+ODY1NDMyMTwvR01GX1lIWkg+PEtQUj7mm77mr4U8L0tQUj48U0tSPjwvU0tSPjxGSFI+PC9GSFI+PFlGUF9ETT48L1lGUF9ETT48WUZQX0hNPjwvWUZQX0hNPjxCWj7lpIfms6g8L0JaPjxKU0hKPjEzNS42MDwvSlNISj48SEpKRT4xMjA8L0hKSkU+PEhKU0U+MTUuNjA8L0hKU0U+PEZQWlQ+MDwvRlBaVD48SlFCSD42NjE1NDkzODYyNzk8L0pRQkg+PEZQX0RNPjA1MTAwMTUwMDExMTwvRlBfRE0+PEZQX0hNPjMyNjk5ODAxPC9GUF9ITT48S1BSUT4yMDE2MDYwNjE0MTMxMzwvS1BSUT48RlBfTVc+PC9GUF9NVz48SllNPjwvSllNPjxFV00+PC9FV00+PFBERl9YWkZTPjM8L1BERl9YWkZTPjwvRlBRWl9GUFQ+PEZQUVpfWE1YWFMg“eInvoice.vshost.exe”(CLR v4.0.30319: EInvoice.vshost.exe):  已加载“Microsoft.GeneratedCode”。Y2xhc3M9IkZQUVpfWE1YWDsiIHNpemU9IjEiPjxGUFFaX1hNWFg+PFhNTUM+57u/6Imy5rC056i7PC9YTU1DPjxYTUpFPjEyMDwvWE1KRT48U0w+MC4xMzwvU0w+PFNFPjE1LjY8L1NFPjxIU0JaPjA8L0hTQlo+PC9GUFFaX1hNWFg+PC9GUFFaX1hNWFhTPjxGUFFaX1RTRlNYWCBjbGFzcz0iRlBRWl9UU0ZTWFgiPjxDT01NT05fTk9ERVMgY2xhc3M9IkNPTU1PTl9OT0RFOyIgc2l6ZT0iMyI+PENPTU1PTl9OT0RFPjxOQU1FPlRTRlM8L05BTUU+PFZBTFVFPjA8L1ZBTFVFPjwvQ09NTU9OX05PREU+PENPTU1PTl9OT0RFPjxOQU1FPlNKPC9OQU1FPjxWQUxVRT4xODA4MDE4ODA4MzwvVkFMVUU+PC9DT01NT05fTk9ERT48Q09NTU9OX05PREU+PE5BTUU+RU1BSUw8L05BTUU+PFZBTFVFPjI3NzA2NDYwNUBxcS5jb208L1ZBTFVFPjwvQ09NTU9OX05PREU+PC9DT01NT05fTk9ERVM+PC9GUFFaX1RTRlNYWD48L1JFUVVFU1RfRlBRWj4=</content></Data></interface>";
		System.out.println(soapRequestData);
		try {
		byte[] bytes = soapRequestData.getBytes("utf-8");
		InputStream inputStream = new ByteArrayInputStream(bytes, 0,
				bytes.length);
		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
				bytes.length, "text/xml;charset=utf-8");
		postMethod.setRequestEntity(requestEntity);
		
			hc.setStatus(httpClient.executeMethod(postMethod));
			int nn=hc.getStatus();
			System.out.println(nn);
			if (nn == 200) {
				hc.setRespon(postMethod.getResponseBodyAsString());
			}

		} catch (Exception e) {
			logger.error("httpclientUtil.invoke调用异常：" + e.getMessage());
		} finally {
			logger.debug("释放连接");
			//postMethod.releaseConnection();
			return hc;
		}
	}
	
	/**
	 * 构建发票签章报文(js平台)
	 * @param patameterMap
	 * @return
	 */
	private String buildRequestData_qz_beta(Map<String, String> patameterMap) {
		StringBuffer soapRequestData = new StringBuffer();
		System.out.println(soapRequestData);
		
		soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:demo=\"http://demo.cxf.reportform.aisino/\">");
		soapRequestData.append("<soapenv:Header/>");
		soapRequestData.append("<soapenv:Body>");
		soapRequestData.append("<demo:eiInterface>");

		Set<String> nameSet = patameterMap.keySet();
		for (String name : nameSet) {
			soapRequestData.append("<" + name + ">" + patameterMap.get(name)
					+ "</" + name + ">");
		}
		soapRequestData.append("</demo:eiInterface></soapenv:Body></soapenv:Envelope>");

		return soapRequestData.toString();
	}

	
	/**
	 * 构建发票签章报文(51平台)
	 * @param patameterMap
	 * @return
	 */
	private String buildRequestData_qz(Map<String, String> patameterMap) {
		StringBuffer soapRequestData = new StringBuffer();
		soapRequestData.append("<soapenv:Envelope xmlns:q0=\"");
		soapRequestData.append(namespace);
		soapRequestData.append("\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header></soapenv:Header><soapenv:Body>");
		soapRequestData.append("<q0:").append(methodName).append(">");
		
//		soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"");
//		soapRequestData.append(namespace);
//		soapRequestData.append("\"><soapenv:Header/><soapenv:Body>");
//		soapRequestData.append("<ws:");
//		soapRequestData.append(methodName);
//		soapRequestData.append(">");
		Set<String> nameSet = patameterMap.keySet();
		for (String name : nameSet) {
			soapRequestData.append("<" + name + ">" + patameterMap.get(name)
					+ "</" + name + ">");
		}
		soapRequestData.append("</q0:").append(methodName).append(">");
		soapRequestData.append("</soapenv:Body></soapenv:Envelope>");
//		soapRequestData.append("</ws:");
//		soapRequestData.append(methodName);
//		soapRequestData.append(">");
//		soapRequestData.append("</soapenv:Body></soapenv:Envelope>");
		return soapRequestData.toString();
	}
	/**
	 * 开票发送请求
	 * @param patameterMap
	 * @return
	 * @throws Exception
	 */
	public Hc invokeS_kp(Map<String, String> patameterMap) throws Exception{
		PostMethod postMethod = new PostMethod(wsdlLocation);
		Hc hc = new Hc();
		hc.setStatus(0);
		String soapRequestData=buildRequestData_kp(patameterMap);
		System.out.println("开票报文    :   "+soapRequestData);
		try {
		byte[] bytes = soapRequestData.getBytes("utf-8");
		InputStream inputStream = new ByteArrayInputStream(bytes, 0,
				bytes.length);
		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
				bytes.length, "text/xml;charset=utf-8");
		postMethod.setRequestEntity(requestEntity);
			hc.setStatus(httpClient.executeMethod(postMethod));
			if (hc.getStatus() == 200) {
				hc.setRespon(postMethod.getResponseBodyAsString());
			}

		} catch (Exception e) {
			logger.error("httpclientUtil.invoke调用异常：" + e.getMessage());
		} finally {
			logger.debug("释放连接");
			//postMethod.releaseConnection();
			return hc;
		}
	}
	/**
	 * 构建开票报文
	 * @param patameterMap
	 * @return
	 */
	private String buildRequestData_kp(Map<String, String> patameterMap) {
		StringBuffer soapRequestData = new StringBuffer();
		soapRequestData.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"");
		soapRequestData.append(namespace);
		soapRequestData.append("\"><soapenv:Header/><soapenv:Body>");
		soapRequestData.append("<ws:");
		soapRequestData.append(methodName);
		soapRequestData.append(">");
		Set<String> nameSet = patameterMap.keySet();
		for (String name : nameSet) {
			soapRequestData.append("<" + name + ">" + patameterMap.get(name)
					+ "</" + name + ">");
		}
		soapRequestData.append("</ws:");
		soapRequestData.append(methodName);
		soapRequestData.append(">");
		soapRequestData.append("</soapenv:Body></soapenv:Envelope>");
		return soapRequestData.toString();
	}

	public Hc SmsSend(String url) throws Exception {
		PostMethod postMethod = new PostMethod(url);
		Hc hc = new Hc();
		hc.setStatus(0);
		postMethod.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		// int statusCode=0;
		try {
			hc.setStatus(httpClient.executeMethod(postMethod));
			if (hc.getStatus() == 200) {
				hc.setRespon(postMethod.getResponseBodyAsString());
			}

		} catch (Exception e) {
			logger.error("httpclientUtil.invoke调用失败：" + e.getMessage());
		} finally {
			logger.debug("释放连接");
			//postMethod.releaseConnection();
			return hc;
		}

	}

	public static void main(String[] args) throws Exception {

		String  s= "SJLY" + (char)2 +"06" + (char)3 + "FPLX" + (char)2 +"1";
		System.out.println(Base64Helper.encode(s));


	}
}
