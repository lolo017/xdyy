package aisino.reportform.action.base;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.sun.mail.util.MailSSLSocketFactory;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.ScjsInvoiceLog;
import aisino.reportform.model.easyui.Json;

@Namespace("/base")
@Action
public class MailAction extends BaseAction<ScjsInvoiceLog> {
	private static final long serialVersionUID = 1L;
	private String PDF;
	private String email;
	private String Id;
	private String content;
	private Vector file=new Vector();
	public void doNotNeedSessionAndSecurity_show(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("application/pdf");
		   String pdfPath= PDF;
		   pdfPath=pdfPath.replace("pdf=","");
		   try {
		    String strPdfPath = new String("D://pdf//"+pdfPath);
		    //判断该路径下的文件是否存在
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
		     
		    }

		   } catch (Exception e) {
		   
		   }
		
	}
	/**
	 * 163邮箱发送
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void doNotNeedSessionAndSecurity_sendmail() throws AddressException, MessagingException{
		System.out.println("开始发邮件");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.163.com");// 设置smtp主机
		properties.put("mail.smtp.auth", "true");// 使用smtp身份验证
		MimeMessage message = new MimeMessage(Session.getInstance(properties,
				new Authenticator() {
					@Override
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(//设置发送帐号密码
								"wxc278282218", "wxc278282218");
					}
				}));
		// 设置邮件的属性
		// 设置邮件的发件人
		message.setFrom(new InternetAddress("wxc278282218@163.com"));
		// 设置邮件的收件人 cc表示抄送 bcc 表示暗送
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(
						email));
		// 设置邮件的主题
		message.setSubject("明宇豪雅酒店电子发票");
		
		
		
		//以附件发送图片
		File usFile=new File("D:\\pdf"+PDF);
		MimeBodyPart fileBody=new MimeBodyPart();
		DataSource source=new FileDataSource("D:\\pdf"+PDF);
		fileBody.setDataHandler(new DataHandler(source));
		sun.misc.BASE64Encoder enc=new sun.misc.BASE64Encoder();
		fileBody.setFileName("=?GBK?B?"+enc.encode(usFile.getName().getBytes())+"?=");
		Multipart multipart=new MimeMultipart();
		BodyPart contentPart=new MimeBodyPart();
		contentPart.setText("欢迎光临明宇豪雅饭店");
		contentPart.setHeader("Content-Type", "text/html;charset=GBK");
		multipart.addBodyPart(contentPart);
		multipart.addBodyPart(fileBody);
		
		message.setContent(multipart);
		message.setSentDate(new Date());
		message.saveChanges(); // 保存修改

		Transport.send(message);// 发送邮件
		System.out.println("邮件发送成功");
		System.out.println(Id);
		//修改下载标志
		//String sql = "update dzfp set xzbz='1' where id="+Id;
		//int a=service.executeSql(sql);
		
		Json json=new Json();
		json.setSuccess(true);
		writeJson(json);
	}
	/**
	 * qq企业邮箱发送
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public void doNotNeedSessionAndSecurity_sendqqmail() throws AddressException, MessagingException, UnsupportedEncodingException{
		String userName="ddfapiao@minyounhotels.com";
		String password="Myhy123";
		String host="smtp.exmail.qq.com";
		String protocal="smtp";
		Properties props=new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", protocal);
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.host", host);
		MailSSLSocketFactory sf=null;
		try{
			sf=new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
		}catch(GeneralSecurityException el){
			System.out.println("开启SSL加密异常！");
			el.printStackTrace();
		}
		props.put("mail.smtp.ssl.enable","true");
		props.put("mail.smtp.ssl.socketFactory", sf);
		Session session=Session.getDefaultInstance(props,new Authenticator() {
					@Override
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(//设置发送帐号密码
								"ddfapiao@minyounhotels.com", "Myhy123");
					}
				});
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		
		message.setRecipients(javax.mail.Message.RecipientType.TO,email);
		
		
		// 设置邮件的主题
		message.setSubject("明宇豪雅酒店电子发票");
		InternetAddress from=new InternetAddress(MimeUtility.encodeWord(userName));
		message.setFrom(from);
		
		
		//以附件发送图片
		File usFile=new File("D:\\pdf"+PDF);
		MimeBodyPart fileBody=new MimeBodyPart();
		DataSource source=new FileDataSource("D:\\pdf"+PDF);
		fileBody.setDataHandler(new DataHandler(source));
		sun.misc.BASE64Encoder enc=new sun.misc.BASE64Encoder();
		fileBody.setFileName("=?GBK?B?"+enc.encode(usFile.getName().getBytes())+"?=");
		Multipart multipart=new MimeMultipart();
		BodyPart contentPart=new MimeBodyPart();
		contentPart.setText("欢迎光临明宇豪雅饭店");
		contentPart.setHeader("Content-Type", "text/html;charset=gbk");
		multipart.addBodyPart(contentPart);
		multipart.addBodyPart(fileBody);
		
		message.setContent(multipart);
		message.setSentDate(new Date());
		message.saveChanges(); // 保存修改
		Transport.send(message);
		System.out.println("邮件发送成功");
		System.out.println(Id);
		//修改下载标志
		//String sql = "update dzfp set xzbz='1' where id="+Id;
		//int a=service.executeSql(sql);
		
		Json json=new Json();
		json.setSuccess(true);
		writeJson(json);
		
	}
	public String getPDF() {
		return PDF;
	}
	public void setPDF(String pDF) {
		PDF = pDF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String getId() {
		return Id;
	}
	@Override
	public void setId(String id) {
		Id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
