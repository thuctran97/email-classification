package model;


public class EmailData {
	
	private EmailClass emailClass;
	private String content;
	
	public EmailData() {
		this(null, null);
	}
	
	
	public EmailData(EmailClass emailClass, String content) {
		this.emailClass = emailClass;
		this.content = content;
	}
		
	public EmailClass getEmailClass() {
		return this.emailClass;
	}

	public void setEmailClass(EmailClass emailClass) {
		this.emailClass = emailClass;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setClassAndContent(EmailClass emailClass, String content) {
		this.emailClass = emailClass;
		this.content = content;
	}
	
	public static EmailData parseClassAndContent(String text) {
		EmailData email = new EmailData();
		String emailClass = text.substring(0,  text.indexOf('\t'));
		String content = text.substring(text.indexOf('\t') + 1);
		if (emailClass.compareTo("spam") == 0) {
			email.setClassAndContent(EmailClass.SPAM, content);
		} else {
			email.setClassAndContent(EmailClass.HAM, content);
		}
		return email;
	}
	
	@Override
	public String toString() {
		return "Email class : " + this.emailClass + " | Content : " + this.content;
	}
	
	
	
}
