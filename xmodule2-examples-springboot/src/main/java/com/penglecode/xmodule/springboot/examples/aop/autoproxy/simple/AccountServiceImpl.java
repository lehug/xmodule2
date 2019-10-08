package com.penglecode.xmodule.springboot.examples.aop.autoproxy.simple;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.penglecode.xmodule.springboot.examples.aop.SimpleLogging;

@SimpleLogging
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	private final ConcurrentMap<Long,Account> allAccounts = new ConcurrentHashMap<>();
	
	@Override
	@SimpleLogging
	public void createAccount(Account account) {
		System.out.println(">>> creating account: " + account);
		allAccounts.put(account.getAccountId(), account);
	}

	@Override
	@SimpleLogging
	public void updateAccount(Account account) {
		System.out.println(">>> updating account: " + account);
		allAccounts.put(account.getAccountId(), account);
	}

	@Override
	@SimpleLogging
	public void deleteAccountById(Long accountId) {
		System.out.println(">>> deleting account by id: " + accountId);
		allAccounts.remove(accountId);
	}

	@Override
	@SimpleLogging
	public Account getAccountById(Long accountId) {
		System.out.println(">>> geting account by id: " + accountId);
		return allAccounts.get(accountId);
	}

}
