package com.my.maven.nutz.pro.my_maven_nutz_sample.javamail_asyn_exam2;

import java.util.List;

/**
 * @author WaterHsu@xiu8.com
 * @version 2014年8月27日
 */
public class Mail {
	/**
	 * 发件人
	 */
	private String sender;
	
	/**
	 * 收件人
	 */
	private List<String> recipiensTo;
	
	/**
	 * 抄送人
	 */
	private List<String> recipientsCc;
	
	/**
	 * 密送人
	 */
	private List<String> recipientsBcc;
	
	/**
	 * 主题
	 */
	private String subject;
	
	/**
	 * 正文
	 */
	private String body;
	
	/**
	 * 附件列表
	 */
	private List<String> attachments;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public List<String> getRecipiensTo() {
		return recipiensTo;
	}

	public void setRecipiensTo(List<String> recipiensTo) {
		this.recipiensTo = recipiensTo;
	}

	public List<String> getRecipientsCc() {
		return recipientsCc;
	}

	public void setRecipientsCc(List<String> recipientsCc) {
		this.recipientsCc = recipientsCc;
	}

	public List<String> getRecipientsBcc() {
		return recipientsBcc;
	}

	public void setRecipientsBcc(List<String> recipientsBcc) {
		this.recipientsBcc = recipientsBcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}
}
