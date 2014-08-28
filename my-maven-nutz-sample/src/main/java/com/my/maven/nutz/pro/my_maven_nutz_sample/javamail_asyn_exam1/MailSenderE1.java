package com.my.maven.nutz.pro.my_maven_nutz_sample.javamail_asyn_exam1;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月27日
 */
public class MailSenderE1 {
	
	public static void sendMail(final String emailAddr, final String mailTitle, final String mailConcept, final String fileAttachment) throws Exception{
		Properties props = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		props.load(classLoader.getResourceAsStream("mail_1.properties"));
		final String username = props.getProperty("mail.username");
		final String password = props.getProperty("mail.password");
		final String mailFrom = props.getProperty("mail.from");
		Session mailSession = Session.getDefaultInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(username, password);
			}
		});
		mailSession.setDebug(false);
		Transport transport = mailSession.getTransport();
		MimeMessage message = new MimeMessage(mailSession);
		message.setSubject(mailTitle);
		message.setFrom(new InternetAddress(mailFrom));
		message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(emailAddr, false));
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(mailConcept, "text/html; charset=utf-8");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		if(null != fileAttachment){
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileAttachment);
			messageBodyPart.setDataHandler(new DataHandler(source));
			String fileName = new String(fileAttachment.getBytes("UTF-8"), "UTF-8");
			int pos = fileName.lastIndexOf("//");
			fileName = fileName.substring(pos + 1);
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);
		}
		message.setContent(multipart);
		transport.connect();
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.BCC));
		transport.close();
	}
	
	public static void sendMailByAsynchronous(final String emailAddr, final String mailTitle, final String mailConcept, final String fileAttachment){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					MailSenderE1.sendMail(emailAddr, mailTitle, mailConcept, fileAttachment);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public static void main(String args[]) throws Exception{
		//MailSenderE1.sendMail("hsupangfei1989@163.com", "异步发邮件测试", "异步发邮件的内容部分......", "E://123.jpg");
		MailSenderE1.sendMailByAsynchronous("xxxxxx", "异步发邮件测试", "异步发邮件的内容部分......", "E://123.jpg");
		System.out.println("已发送一封邮件 1");
	//	MailSenderE1.sendMail("hsupangfei1989@163.com", "异步发邮件测试2", "异步发邮件的内容部分......", "E://123.jpg");
		MailSenderE1.sendMailByAsynchronous("xxxxx", "异步发邮件测试2", "异步发邮件的内容部分......", "E://123.jpg");
		System.out.println("已发送一封邮件2");
	}
}
