package com.penglecode.xmodule.springboot.examples.aop.proxyfactory.configurable;

public interface MessageService {

	public boolean sendSms(String mobile, String content);
	
	public boolean sendEmail(String email, String title, String content);
	
}
