package com.my.maven.nutz.pro.my_maven_nutz_sample.spring_quartz;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.maven.nutz.pro.my_maven_nutz_sample.javamail_asyn_exam1.MailSenderE1;
import com.my.maven.nutz.pro.my_maven_nutz_sample.spring_javamail.Email;
import com.my.maven.nutz.pro.my_maven_nutz_sample.spring_javamail.MailService;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月28日
 */
public class SpringJavaMailTest {
	private static BeanFactory factory = new ClassPathXmlApplicationContext("spring-core-config.xml");

	@Test
	public void testSpringJavaMailSend() throws Exception{
		MailService mailService = (MailService)factory.getBean("mailService");
		Email email = new Email();
		email.setAddressee("xxxxxxx");
		email.setSubject("Spring java 测试");
		email.setContent("这是一封测试邮件");
	//	email.setCc("hsupangfei@outlook.com");
		//mailService.sendMail(email);
		//mailService.sendMailByAsynchronousMode(email);
		mailService.sendMailBysynchronousMode(email);
		System.out.println("邮件发送成功!");
		System.out.println();
	}
	
	@Test
	public void testMailSenderE1() throws Exception{
		MailSenderE1.sendMail("xxxxxx", "异步发邮件测试", "异步发邮件的内容部分......", "E://123.jpg");
			//	MailSenderE1.sendMailByAsynchronous("hsupangfei1989@163.com", "异步发邮件测试", "异步发邮件的内容部分......", "E://123.jpg");
				System.out.println("已发送一封邮件 1");
				MailSenderE1.sendMail("xxxxxx", "异步发邮件测试2", "异步发邮件的内容部分......", "E://123.jpg");
			//	MailSenderE1.sendMailByAsynchronous("hsupangfei1989@163.com", "异步发邮件测试2", "异步发邮件的内容部分......", "E://123.jpg");
				System.out.println("已发送一封邮件2");
	}
}
