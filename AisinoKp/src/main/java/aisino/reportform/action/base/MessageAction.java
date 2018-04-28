package aisino.reportform.action.base;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.ScjsInvoiceLog;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.ScjsInvoiceLogServiceI;

@Namespace("/base")
@Action
public class MessageAction extends BaseAction<ScjsInvoiceLog> {
	private static final long serialVersionUID = 1L;
	@Autowired
	public void setService(ScjsInvoiceLogServiceI service) {
		this.service = service;
	}
	Json json=new Json();
	private String phone;
	public String msg="";
	private String jym;
	public void doNotNeedSessionAndSecurity_send() throws HttpException, IOException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String yzm=session.getAttribute("verifyCode").toString();
		if(yzm.equals(jym)){
			String sql1="select count(*) sl from fwjl where  phone='"+phone+"' and time=convert(varchar(10),getdate(),120)";
			String sql2="select count(*) sl from fwjl where ip='"+request.getRemoteAddr()+"' and time=convert(varchar(10),getdate(),120)";
			long tj1=service.countBySql(sql1);
			long tj2=service.countBySql(sql2);
			if(tj1<=5&&tj2<=10){
				for(int i=0;i<5;i++){
					msg+=(int)(Math.random()*10);
				}
//				HttpClient client = new HttpClient();
//				PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
//				post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
//				NameValuePair[] data ={ new NameValuePair("Uid", "wxc278282218"),new NameValuePair("Key", "94494243cbbaff9fe58d"),new NameValuePair("smsMob",phone),new NameValuePair("smsText","[欢迎光临明宇豪雅酒店]您的验证码是："+msg+"请在90秒内登陆")};
//				post.setRequestBody(data);
//				client.executeMethod(post);
//				Header[] headers = post.getResponseHeaders();
//				int statusCode = post.getStatusCode();
//				System.out.println("statusCode:"+statusCode);
//				for(Header h : headers)
//				{
//				System.out.println(h.toString());
//				}
//				String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
//				System.out.println(result);
				String sql3="insert into fwjl(phone,ip,time) values('"+phone+"','"+request.getRemoteAddr()+"',GETDATE())";
				service.executeSql(sql3);
//				post.releaseConnection();
				HttpClient httpclient = new HttpClient();
				PostMethod post = new PostMethod("https://api.ums86.com:9600/sms/Api/Send.do");//
				post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
				post.addParameter("SpCode", "108293");
				post.addParameter("LoginName", "cd_myhy");
				post.addParameter("Password", "myhy0216");
				post.addParameter("MessageContent", "尊敬的客户，您本次验证码为"+msg+"，请在90秒内使用！");
				post.addParameter("UserNumber", phone);
				post.addParameter("SerialNumber", "");
				post.addParameter("ScheduleTime", "");
				post.addParameter("f", "1");
				httpclient.executeMethod(post);
				String info = new String(post.getResponseBody(),"gbk");
				System.out.println(info);
				json.setSuccess(true);
				json.setMsg(msg);
				writeJson(json);
			}else{
				json.setSuccess(false);
				json.setMsg("您今天已达到最大验证码申请数，请明天再试");
				writeJson(json);
			}
		}else{
			json.setSuccess(false);
			json.setMsg("图形校验码错误,请重新输入");
			writeJson(json);
		}	
	}
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJym() {
		return jym;
	}
	public void setJym(String jym) {
		this.jym = jym;
	}
	
	
}
