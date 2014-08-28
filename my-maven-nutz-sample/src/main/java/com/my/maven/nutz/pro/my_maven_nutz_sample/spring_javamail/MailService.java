package com.my.maven.nutz.pro.my_maven_nutz_sample.spring_javamail;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月28日
 */
public interface MailService {
	/**
	 * 邮件分发器
	 * @param email
	 * @throws Exception
	 */
	public void sendMail(Email email) throws Exception;
	
	/**
	 * 异步发送邮件
	 * @param email
	 */
	public void sendMailByAsynchronousMode(final Email email);
	
	/**
	 * 同步发送邮件
	 * @param email
	 */
	public void sendMailBysynchronousMode(final Email email);
}
