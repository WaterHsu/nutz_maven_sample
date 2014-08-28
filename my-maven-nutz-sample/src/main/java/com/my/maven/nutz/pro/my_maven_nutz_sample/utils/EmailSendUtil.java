/**
 * 秀吧网络科技有限公司版权所有
 * Copyright (C) xiu8 Corporation. All Rights Reserved
 */
package com.my.maven.nutz.pro.my_maven_nutz_sample.utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



/**
 * 邮件发送工具类
 * 
 * @author WaterHsu@xiu8.com
 * @version 2014年8月28日
 */
public class EmailSendUtil {
	
	/**
	 * 发送邮件的外部接口，传进来email，和是否异步发送的标志位
	 * @param email  封装发邮件参数
	 * @param isAsyn  true异步， false同步
	 */
	public static void sendMail(final Email email, boolean isAsyn){
		if(isAsyn){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						EmailSendUtil.send(email);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}).start();
		}
		else{
			try{
				System.out.println("同步发送邮件 11");
				EmailSendUtil.send(email);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 具体发邮件的处理
	 * 
	 * @param email
	 * @throws Exception
	 */
	private static void send(final Email email) throws Exception{
		Session mailSession = Session.getDefaultInstance(email.getProperties(), new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(email.getUsername(), email.getPassword());
			}
			
		});
		System.out.println("同步发送邮件 22");
		mailSession.setDebug(false);
		MimeMessage  message = new MimeMessage(mailSession);
		message.setSubject(email.getSubject());
		message.setFrom(new InternetAddress(email.getFromAddress()));
		/**
		 * 设置收件人
		 */
		message.setRecipients(Message.RecipientType.TO, getAllAddress(email.getToAddresses()));
		message.setRecipients(Message.RecipientType.CC, getAllAddress(email.getCcAddresses()));
		message.setRecipients(Message.RecipientType.BCC, getAllAddress(email.getBccAddresses()));
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(email.getContent(), "text/html; charset=utf-8");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		System.out.println("同步发送邮件 33");
		if(null != email.getAttachFileName() && email.getAttachFileName().length > 0){
			for(String attachFile : email.getAttachFileName()){
				MimeBodyPart fileBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachFile);
				fileBodyPart.setDataHandler(new DataHandler(source));
				String fileName = new String(attachFile.getBytes("UTF-8"), "UTF-8");
				int pos = fileName.lastIndexOf("//");
				fileName = fileName.substring(pos + 1); 
				fileBodyPart.setFileName(fileName);
				multipart.addBodyPart(fileBodyPart);
			}		
		}
		System.out.println("同步发送邮件 44");
		message.setContent(multipart);
		Transport.send(message);
	}
	
	/**
	 * 将收件人地址由String转为InternetAddress类型
	 * @param strs
	 * @return
	 */
	private static InternetAddress[] getAllAddress(String[] strs){
		if(null == strs || strs.length <= 0){
			return null;
		}
		InternetAddress[] addrs = new InternetAddress[strs.length];
		
		for(int i = 0; i < strs.length; i++){
			try{
				addrs[i] = new InternetAddress(strs[i]);
			}catch(AddressException e){
				e.printStackTrace();
			}
		}
		
		return addrs;
	}
}
