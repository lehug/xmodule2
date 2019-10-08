package com.penglecode.xmodule.springboot.examples.aop.autoproxy.simple;

import com.penglecode.xmodule.common.util.DateTimeUtils;

public class Account {

	private Long accountId;
	
	private String accountNo;
	
	private Double accountBalance;
	
	private String createTime;

	public Account() {
		super();
		this.createTime = DateTimeUtils.formatNow();
	}
	

	public Account(Long accountId, String accountNo, Double accountBalance) {
		super();
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.accountBalance = accountBalance;
		this.createTime = DateTimeUtils.formatNow();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNo=" + accountNo + ", accountBalance=" + accountBalance
				+ ", createTime=" + createTime + "]";
	}
	
}
