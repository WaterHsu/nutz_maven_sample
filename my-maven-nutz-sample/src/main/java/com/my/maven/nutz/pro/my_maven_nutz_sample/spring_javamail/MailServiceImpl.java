package com.my.maven.nutz.pro.my_maven_nutz_sample.spring_javamail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月28日
 */
public class MailServiceImpl implements MailService {
	
	private JavaMailSender mailSender;
	
	private TaskExecutor taskExecutor;
	
	private StringBuffer message = new StringBuffer();

	@Override
	public void sendMail(Email email) throws Exception {
		if(null == email.getAddress() || email.getAddress().length == 0){
			message.append("没有收件人");
			return;
		}
		if(email.getAddress().length > 1){
			sendMailByAsynchronousMode(email);
			message.append("收件人过多，正在采用异步方式发送..<br/>");
			System.out.println("异步发送邮件");
		}
		else{
			sendMailBysynchronousMode(email);
			message.append("正在同步的方式发送邮件...<br/>");
			System.out.println("同步发送邮件");
		}
	}

	/**
	 * 异步发送邮件
	 */
	@Override
	public void sendMailByAsynchronousMode(final Email email) {
		taskExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				try{
					System.out.println("正在异步发送邮件");
					send(email);
					System.out.println("异步发送邮件成功");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					send(email);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();*/
	}

	@Override
	public void sendMailBysynchronousMode(Email email) {
		try{
			System.out.println("正在异步发送邮件");
			send(email);
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	private void send(Email email) throws Exception {
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");
		helper.setFrom("374299447@qq.com");
		helper.setTo(email.getAddress());
		helper.setBcc("waterhsu@outlook.com");
		if(null != email.getCc() && email.getCc().trim().length() > 0){
			String cc[] = email.getCc().split(";");
			helper.setCc(cc);
		}
		helper.setReplyTo("374299447@qq.com");
		helper.setSubject("spring java mail 测试");
		helper.setText(email.getContent(), true);
		
		if(null != email.getAttachment()){
			for(MultipartFile file : email.getAttachment()){
				if(null == file || file.isEmpty()){
					continue;
				}
				String fileName = file.getOriginalFilename();
			
				try{
					fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
				}catch(Exception e){
					e.printStackTrace();
				}
				helper.addAttachment(fileName, new ByteArrayResource(file.getBytes()));
			}
		}
		
		mailSender.send(mime);
	}

	public StringBuffer getMessage() {
		return message;
	}

	public void setMessage(StringBuffer message) {
		this.message = message;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	public static void main(String args[]) throws Exception{
		BeanFactory factory = new ClassPathXmlApplicationContext("spring-core-config.xml");
		
		MailService mailService = (MailService)factory.getBean("mailService");
		Email email = new Email();
		email.setAddressee("xxxxxxxxx");
		email.setSubject("Spring java 测试");
		email.setContent("这是一封测试邮件fff");
	//	email.setCc("hsupangfei@outlook.com");
		for(int i = 0; i < 7; i++){
			mailService.sendMail(email);
		}
		//mailService.sendMailByAsynchronousMode(email);
		//mailService.sendMailBysynchronousMode(email);
		System.out.println("邮件发送成功!");
		System.out.println();
	}
	
}
