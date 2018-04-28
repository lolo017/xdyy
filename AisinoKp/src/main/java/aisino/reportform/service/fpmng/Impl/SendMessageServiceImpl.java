/**
 * 
 */
package aisino.reportform.service.fpmng.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;



import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.service.fpmng.SendMessageServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.ConfigUtil;

/**
 * 
 * @Title: 
 * @Description:
 * Company    JS-YFB LTD
 * @author:张欢
 * @version    
 * @date 2017-11-9 上午11:09:39
 */
@Service
public class SendMessageServiceImpl extends BaseServiceImpl<SysOrderHead> implements SendMessageServiceI{
	
	@Override
	public int sendMessage(String ohid) {
		String mobile = "";
		//判断短信是否发送成功的标识
		int result = 0;
		SysOrderHead sysOrderHead = getById(ohid);
		mobile = sysOrderHead.getMobile();
		//	System.out.println(mobile);
		// 调用短信接口
		try {
			sendSMSPost(mobile,"尊敬的客户，您在我店购买的办公用品的电子发票已开具，请输入发票抬头下载：网址http://t.cn/RlQqQGd", "");
			result = 1;
			//发送短信成功，修改表中的推送状态
			String updateOrderSql = "update t_sysorderhead set is_ts = '1'  where ohid = '"+ohid+"' ";
			executeSql(updateOrderSql);
		//	System.out.println(result);
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	  * @author:张欢
	  * @Title: sendSMSPost
	  * @Description: Hppt POST请求发送方法 返回值>0 为 提交成功
	  * @param Mobile 电话号码
	  * @param Content 发送内容
	  * @param send_time 定时发送时间，为空时，为及时发送 
	  * @return value 短信验证码    
	  * @throws MalformedURLException
	  * @throws UnsupportedEncodingException
	  * @dateTime:2017-11-1下午15:12:27
	 */
	public  int sendSMSPost(String Mobile, String Content,
			String send_time) throws MalformedURLException,
			UnsupportedEncodingException {

		String inputLine = "";
		int value = -2;
//		String CorpID = "CDJS003787";// 账户名
//		String Pwd = "zm0513@";// 密码
		String CorpID = ConfigUtil.get("CorpID");// 账户名
		String Pwd = ConfigUtil.get("Pwd");// 密码
		String send_content = URLEncoder.encode(
				Content.replaceAll("<br/>", " "), "GBK");// 发送内容
//		String strUrl = "https://sdk2.028lk.com/sdk2/BatchSend2.aspx";
		String strUrl = ConfigUtil.get("StrUrl");
		String param = "CorpID=" + CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile
				+ "&Content=" + send_content + "&Cell=&SendTime=" + send_time;
		try {
			inputLine = sendPost(strUrl, param);
			System.out.println("开始发送短信手机号码为 ：" + Mobile);
			value = new Integer(inputLine).intValue();
		} catch (Exception e) {
			System.out.println("网络异常,发送短信失败！");
			value = -2;
		}
		System.out.println(String.format("返回值：%d", value));
		return value;
	}

	/**
	  * @author:张欢
	  * @Title: sendPost
	  * @Description: 向指定 URL 发送POST方法的请求
	  * @param url 发送请求的 URL
	  * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	  * @return result 传入的参数值   
	  * @dateTime:2017-11-1下午15:23:54
	 */
	public String sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	

}
