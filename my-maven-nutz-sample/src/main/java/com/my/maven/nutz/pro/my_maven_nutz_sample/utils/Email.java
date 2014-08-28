/**
 * 秀吧网络科技有限公司版权所有
 * Copyright (C) xiu8 Corporation. All Rights Reserved
 */
package com.my.maven.nutz.pro.my_maven_nutz_sample.utils;

import java.util.Properties;

import org.nutz.ioc.impl.PropertiesProxy;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月28日
 */
public class Email {
	/**
	 * 发送邮件的服务器的地址
	 */
	private String mailServerHost = "mail.xiu8.com";
	/**
	 * 发送邮件的服务器端口
	 */
	private String mailServerPort = "25";
	/**
	 * 邮件发送者地址
	 */
	private String fromAddress = "service@xiu8.com";
	/**
	 * 邮箱用户名
	 */
	private String username = "service@xiu8.com";
	/**
	 * 邮箱密码
	 */
	private String password = "QImo5Bo.com";
	/**
	 * 用于读取配置文件
	 */
	private Properties properties;
	/**
	 * 是否需要身份验证
	 */
	private boolean validate = true;
	/**
	 * 收件人地址，可以同时发给多个人，多个地址用;隔开
	 */
	private String toAddress;
	/**
	 * 邮件抄送地址   多个地址用;隔开
	 */
	private String ccAddress;
	/**
	 * 邮件密送地址  多个地址用;隔开
	 */
	private String bccAddress;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件内容
	 */
	private String content;
	/**
	 * 邮件附件
	 */
	private String[] attachFileName;
	
	public Email(){
		
	}
	
	public Email(PropertiesProxy appProp){
		readProperties(appProp);
	}
	
	public Email(PropertiesProxy appProp, String toAddress, String ccAddress, String bccAddress, String subject, String content, String[] attachment){
		readProperties(appProp);
		this.toAddress = toAddress;
		this.ccAddress = ccAddress;
		this.bccAddress = bccAddress;
		this.subject = subject;
		this.content = content;
		this.attachFileName = attachment;
	}
	
	public Email(PropertiesProxy appProp, String toAddress, String subject, String content){
		readProperties(appProp);
		this.toAddress = toAddress;
		this.subject = subject;
		this.content = content;
	}
	
	private void readProperties(PropertiesProxy appProp){
		this.mailServerHost = (null == appProp) ? this.mailServerHost : appProp.get("mail.host", "mail.xiu8.com");
		this.mailServerPort = (null == appProp) ? this.mailServerPort : appProp.get("mail.port", "25");
		this.fromAddress = (null == appProp) ? this.fromAddress : appProp.get("mail.userName", "service@xiu8.com");
		this.username = (null == appProp) ? this.username : appProp.get("mail.userName", "service@xiu8.com");
		this.password = (null == appProp) ? this.password : appProp.get("mail.pass", "QImo5Bo.com");
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String[] attachFileName) {
		this.attachFileName = attachFileName;
	}

	public Properties getProperties() {
		properties = new Properties();
		properties.put("mail.smtp.host", this.mailServerHost);
		properties.put("mail.smtp.port", this.mailServerPort);
		properties.put("mail.smtp.auth", validate ? "true" : "false");
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}	
	
	/**
	 * 将所有收件人地址取出放入数组中
	 * @return
	 */
	public String[] getToAddresses(){
		if(null == this.toAddress || this.toAddress.trim().length() <= 0){
			return null;
		}
		
		String address = this.toAddress.trim();
		address.replaceAll("；", ";");
		address.replaceAll(" ", ";");
		address.replaceAll("|", ";");
		address.replace("，", ";");
		
		return address.split(";");
	}
	
	/**
	 * 将所有抄送人地址取出放入数组中
	 * @return
	 */
	public String[] getCcAddresses(){
		if(null == this.ccAddress || this.ccAddress.trim().length() <= 0){
			return null;
		}
		
		String address = this.ccAddress.trim();
		address.replaceAll("；", ";");
		address.replaceAll(" ", ";");
		address.replaceAll("|", ";");
		address.replace("，", ";");
		
		return address.split(";");
	}
	
	/**
	 * 将所有密送人地址取出放入数组中
	 * @return
	 */
	public String[] getBccAddresses(){
		if(null == this.bccAddress || this.bccAddress.trim().length() <= 0){
			return null;
		}
		
		String address = this.bccAddress.trim();
		address.replaceAll("；", ";");
		address.replaceAll(" ", ";");
		address.replaceAll("|", ";");
		address.replace("，", ";");
		
		return address.split(";");
	}
}
