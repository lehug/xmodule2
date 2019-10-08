package com.penglecode.xmodule.common.support;

/**
 * 前后端分离应用
 * 
 * @author 	pengpeng
 * @date	2019年6月11日 上午10:19:56
 */
public interface MvvmApp {

	/**
	 * 应用ID
	 * @return
	 */
	public Long getAppId();
	
	/**
	 * 应用名称
	 * @return
	 */
	public String getAppName();
	
	/**
	 * 应用代码
	 * @return
	 */
	public String getAppCode();
	
}
