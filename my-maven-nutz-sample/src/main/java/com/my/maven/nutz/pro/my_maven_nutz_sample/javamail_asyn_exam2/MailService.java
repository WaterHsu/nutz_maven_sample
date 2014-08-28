package com.my.maven.nutz.pro.my_maven_nutz_sample.javamail_asyn_exam2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
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
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;


/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月27日
 */
public class MailService {
//	private static final String MAIL_PROPERTIE_NAME = "mail_2.properties";
	private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	private static Properties mailPro = new Properties();
	private static Executor executor = Executors.newFixedThreadPool(10);
	
	static{
		InputStream in = null;
		try{
			/*in = MailService.class.getResourceAsStream(MAIL_PROPERTIE_NAME);
			mailPro.load(in);*/
			
			mailPro.load(classLoader.getResourceAsStream("mail_2.properties"));
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(null != in){
				try{
					in.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 异步发送邮件
	 * @param mail
	 * @return
	 */
	public boolean sendMailAsyn(final Mail mail){
		if(null == mail){
			return false;
		}
		
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				final String username = mailPro.getProperty(MailProperties.MAIL_SMTP_USER);
				final String password = mailPro.getProperty(MailProperties.MAIL_SMTP_PASSWORD);
				Session session = Session.getDefaultInstance(mailPro, new Authenticator() {
				
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						return new PasswordAuthentication(username, password);
					}
					
				});
				
				try{
					session.setDebug(false);
					Transport transport = session.getTransport();
					MimeMessage msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress(StringUtils.isEmpty(mail.getSender()) ? mailPro.getProperty(MailProperties.MAIL_SMTP_USER):mail.getSender()));
					msg.setRecipients(Message.RecipientType.TO, strListToInternetAddress(mail.getRecipiensTo()));
					msg.setRecipients(Message.RecipientType.CC, strListToInternetAddress(mail.getRecipientsCc()));
					msg.setRecipients(Message.RecipientType.BCC, strListToInternetAddress(mail.getRecipientsBcc()));
					msg.setSubject(mail.getSubject());
					Multipart mp = new MimeMultipart();
					MimeBodyPart mbp1 = new MimeBodyPart();
					mbp1.setText(mail.getBody());
					mp.addBodyPart(mbp1);
					
					if(!CollectionUtils.isEmpty(mail.getAttachments())){
						MimeBodyPart attach = null;
						for(String path : mail.getAttachments()){
							attach = new MimeBodyPart();
							try{
								attach.attachFile(path);
								mp.addBodyPart(attach);
							}catch(IOException e){
								e.printStackTrace();
							}
						}
					}
					
					msg.setContent(mp);
					msg.setSentDate(new Date());
				//	Transport.send(msg);
					transport.connect();	
					transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
					transport.close();
				}catch(AddressException e){
					e.printStackTrace();
				}catch(MessagingException e){
					e.printStackTrace();
				}
				
			}
		};
		
		executor.execute(task);
		return true;
	}
	

	public boolean sendMailSyn(Mail mail){
		if(null == mail){
			return false;
		}
		
		final String username = mailPro.getProperty(MailProperties.MAIL_SMTP_USER);
		final String password = mailPro.getProperty(MailProperties.MAIL_SMTP_PASSWORD);
		System.out.println("username: " + username);
		Session session = Session.getDefaultInstance(mailPro, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(username, password);
			}
			
		});
		
		try{
			Transport transport = session.getTransport();
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(StringUtils.isEmpty(mail.getSender()) ? mailPro.getProperty(MailProperties.MAIL_SMTP_USER):mail.getSender()));
			msg.setRecipients(Message.RecipientType.TO, strListToInternetAddress(mail.getRecipiensTo()));
			msg.setRecipients(Message.RecipientType.CC, strListToInternetAddress(mail.getRecipientsCc()));
			msg.setRecipients(Message.RecipientType.BCC, strListToInternetAddress(mail.getRecipientsBcc()));
			msg.setSubject(mail.getSubject());
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(mail.getBody());
			mp.addBodyPart(mbp1);
			
			if(!CollectionUtils.isEmpty(mail.getAttachments())){
				MimeBodyPart attach = null;
				for(String path : mail.getAttachments()){
					attach = new MimeBodyPart();
					try{
						attach.attachFile(path);
						mp.addBodyPart(attach);
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
			
			msg.setContent(mp);
			msg.setSentDate(new Date());

			
			transport.connect();
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
			transport.close();

		}catch(AddressException e){
			e.printStackTrace();
		}catch(MessagingException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	private InternetAddress[] strListToInternetAddress(List<String> list){
		if(null == list || list.isEmpty()){
			return null;
		}
		int size = list.size();
		InternetAddress[] arr = new InternetAddress[size];
		for(int i = 0; i < size; i++){
			try{
				arr[i] = new InternetAddress(list.get(i));
				System.out.println("list: " + list.get(i) + "   arr[i] " + arr[i]);
			}catch(AddressException e){
				e.printStackTrace();
			}
		}
		return arr;
	}
	
	public static void main(String args[]){
		MailService mailService = new MailService();
		Mail mail = new Mail();
		mail.setSender("xxxxx@qq.com");
		List<String> list = new ArrayList<String>();
		list.add("xxxxxxxx");
		list.add("xxxxxxxxx");
		mail.setRecipiensTo(list);
		mail.setBody("这是一封测试邮件！");
		mail.setSubject("测试");
		/*System.out.println("--------同步发送邮件--------");
		for(int i = 0; i < 10; i++){
			System.out.println(mailService.sendMailSyn(mail));
		}
		System.out.println("------同步发送邮件结束------");*/
		
		System.out.println("--------异步发送邮件--------");
	//	for(int i = 0; i < 10; i++){
			System.out.println(mailService.sendMailAsyn(mail));
			System.out.println("i = " );
	//	}
		System.out.println("------异步发送邮件结束------");
	}
}
