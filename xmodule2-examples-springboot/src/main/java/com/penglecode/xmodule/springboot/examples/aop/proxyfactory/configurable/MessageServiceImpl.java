package com.penglecode.xmodule.springboot.examples.aop.proxyfactory.configurable;

public class MessageServiceImpl implements MessageService {

	@Override
	public boolean sendSms(String mobile, String content) {
		System.out.println(">>> Sending SMS ......");
		return true;
	}

	@Override
	public boolean sendEmail(String email, String title, String content) {
		System.out.println(">>> Sending EMAIL ......");
		return true;
	}

}
