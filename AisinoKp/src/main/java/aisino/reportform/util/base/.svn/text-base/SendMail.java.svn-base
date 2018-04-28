package aisino.reportform.util.base;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import java.util.Date;

public class SendMail {
	private static SendMail instance = null;

	

	public static SendMail getInstance() {
		if (instance == null) {
			instance = new SendMail();
		}
		return instance;
	}
	/**
	 * @param myemail 自己的邮箱
	 * @param to 收件人
	 * @param cs 抄送
	 * @param ms 密送
	 * @param subject 主题
	 * @param content 内容
	 * @param formEmail发件人
	 * @param pwd 自己邮箱的密码
	 * @param fileList 附件
	 */
	public void send(String to[], String cs[], String ms[], String subject,
			String content, String formEmail,String pwd, String fileList[]) {
		try {
			Properties p = new Properties(); // Properties p =
			// System.getProperties();
			p.put("mail.smtp.auth", "true");
			p.put("mail.transport.protocol", "smtp");
			p.put("mail.smtp.host", "smtp.qq.com");
			p.put("mail.smtp.port", "25");
			// 建立会话
			Session session = Session.getInstance(p);
			Message msg = new MimeMessage(session); // 建立信息
			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			msg.setFrom(new InternetAddress(formEmail)); // 发件人

			String toList = null;
			String toListcs = null;
			String toListms = null;

			// 发送,
			if (to != null) {
				toList = getMailList(to);
				new InternetAddress();
				InternetAddress[] iaToList = InternetAddress
						.parse(toList);
				msg.setRecipients(Message.RecipientType.TO, iaToList); // 收件人
			}

			// 抄送
			if (cs != null) {
				toListcs = getMailList(cs);
				new InternetAddress();
				InternetAddress[] iaToListcs = InternetAddress
						.parse(toListcs);
				msg.setRecipients(Message.RecipientType.CC, iaToListcs); // 抄送人
			}

			// 密送
			if (ms != null) {
				toListms = getMailList(ms);
				new InternetAddress();
				InternetAddress[] iaToListms = InternetAddress
						.parse(toListms);
				msg.setRecipients(Message.RecipientType.BCC, iaToListms); // 密送人
			}
			msg.setSentDate(new Date()); // 发送日期
			msg.setSubject(subject); // 主题
			msg.setText(content); // 内容
			// 显示以html格式的文本内容
			messageBodyPart.setContent(content, "text/html;charset=gbk");
			multipart.addBodyPart(messageBodyPart);

			// 2.保存多个附件
			if (fileList != null) {
				addTach(fileList, multipart);
			}

			msg.setContent(multipart);
			// 邮件服务器进行验证
			Transport tran = session.getTransport("smtp");
			tran.connect("smtp.qq.com", formEmail, pwd);
			tran.sendMessage(msg, msg.getAllRecipients()); // 发送
			System.out.println("邮件发送成功");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	// 添加多个附件
	public void addTach(String fileList[], Multipart multipart)
			throws MessagingException, UnsupportedEncodingException {
		for (int index = 0; index < fileList.length; index++) {
			MimeBodyPart mailArchieve = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(fileList[index]);
			mailArchieve.setDataHandler(new DataHandler(fds));
			mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(),
					"GBK", "B"));
			multipart.addBodyPart(mailArchieve);
		}
	}

	private String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}

			}
		}
		return toList.toString();

	}
	public static void main(String[] args) {
		SendMail mail=new SendMail();
				String to[] = { "723836333@qq.com"}; 
				String[] arrArchievList = new String[1];
				arrArchievList[0] = "d://Betrayal.jpg";
		//mail.send( "723836333@qq.com", null, null, "测试", "内容", "442876771@qq.com" , null);
		mail.send(to, null, null, "ceshi", "ceshi", "442876771@qq.com", "", arrArchievList);
	}
}
