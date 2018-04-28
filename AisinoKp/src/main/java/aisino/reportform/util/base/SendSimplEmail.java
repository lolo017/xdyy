package aisino.reportform.util.base;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;


public class SendSimplEmail {
	 public static void sendMail()throws Exception{
		  List<InternetAddress> list=new ArrayList<InternetAddress>();
	         list.add(new InternetAddress("723836333@qq.com"));
		   // 创建一个Email附件  
	        EmailAttachment emailattachment = new EmailAttachment();  
	        emailattachment.setPath("d://Betrayal.jpg");  
	        emailattachment.setDisposition(EmailAttachment.ATTACHMENT);  
	        emailattachment.setDescription("This is Smile picture");  
	        emailattachment.setName("img.jpg");            
	        // 创建一个email  
	        MultiPartEmail multipartemail = new MultiPartEmail();   
	        multipartemail.setHostName("smtp.qq.com"); //设置邮件服务器地址   
	        multipartemail.setTo(list);//对方  	 
	        multipartemail.setFrom("442876771@qq.com");//我方  	 
	        multipartemail.setAuthentication("442876771@qq.com", "自己的密码");//设置你邮箱和密码（这里我是把企业域名绑定QQ的企业邮箱里，所以可以用QQ的邮件服务器）  	 
	        multipartemail.setSubject("This is a attachment Email");//标题   
	        multipartemail.setMsg("this a attachment Eamil Test");//文本  
	        // 添加附件  
	        multipartemail.attach(emailattachment);  
	        // 发送邮件  
	        multipartemail.send();  
	        System.out.println("The attachmentEmail send sucessful!!!");   
//         List<InternetAddress> list=new ArrayList<InternetAddress>();
//         list.add(new InternetAddress("723836333@qq.com"));
//         SimpleEmail email=new SimpleEmail();
//         email.setFrom("442876771@qq.com"); //发送方
//         email.setCharset("utf-8");
//         email.setSentDate(new Date());
//         email.setSubject("测试Quartz");//邮件标题
//         email.setHostName("smtp.qq.com");
//         email.setAuthentication("442876771@qq.com", "");//你的邮箱帐号和密码
//         email.setTo(list);
//         email.setContent("<h1>Hello,123131231把凤姐许配给你,你看咋样?</h1>", "text/html;charset=utf-8");
//         email.send();
     }
}
