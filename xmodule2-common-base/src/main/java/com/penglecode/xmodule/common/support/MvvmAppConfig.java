package com.penglecode.xmodule.common.support;

import java.util.Map;

/**
 * 前后端分离应用配置
 * 
 * @author 	pengpeng
 * @date	2019年5月28日 下午12:59:36
 */
public class MvvmAppConfig {

	/** 当前应用的appCode */
	private String appCode;
	
	/** 当前应用配置 */
	private volatile MvvmApp current;
	
	/** 所有应用配置 */
	private volatile Map<Long,? extends MvvmApp> allApps;

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public MvvmApp getCurrent() {
		return current;
	}

	public void setCurrent(MvvmApp current) {
		this.current = current;
	}

	public Map<Long, ? extends MvvmApp> getAllApps() {
		return allApps;
	}

	public void setAllApps(Map<Long, ? extends MvvmApp> allApps) {
		this.allApps = allApps;
	}

}
