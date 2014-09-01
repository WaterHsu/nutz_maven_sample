package com.my.maven.nutz.pro.my_maven_nutz_sample.spring_javamail;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月28日
 */
public class Email implements Serializable {
	
	private static final long serialVersionUID = 9063903350324510652L;
	
	/**
	 * 收件人
	 */
	private String addressee;
	
	/**
	 * 抄送给
	 */
	private String cc;
	
	/**
	 * 邮件主题
	 */
	private String subject;
	
	/**
	 * 邮件内容
	 */
	private String content;
	
	/**
	 * 附件
	 */
	private MultipartFile[] attachment = new MultipartFile[0];
	
	/**
	 * 分解邮件地址，邮件地址中如果有多个收件人，用;隔开
	 * @return
	 */
	public String[] getAddress(){
		if(null == addressee || addressee.trim().length() <= 0){
			return null;
		}
		
		addressee = addressee.trim();
		addressee.replaceAll("；", ";");
		addressee.replaceAll(" ", ";");
		addressee.replaceAll("|", ";");
		addressee.replace("，", ";");
		
		return addressee.split(";");
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
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

	public MultipartFile[] getAttachment() {
		return attachment;
	}

	public void setAttachment(MultipartFile[] attachment) {
		this.attachment = attachment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
