package com.penglecode.xmodule.springboot.examples.aop.autoproxy.simple;

public interface AccountService {

	public void createAccount(Account account);
	
	public void updateAccount(Account account);
	
	public void deleteAccountById(Long accountId);
	
	public Account getAccountById(Long accountId);
	
}
