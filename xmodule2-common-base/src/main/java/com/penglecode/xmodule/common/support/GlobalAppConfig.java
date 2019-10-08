package com.penglecode.xmodule.common.support;

/**
 * 全局应用配置
 * 
 * @author 	pengpeng
 * @date	2019年6月11日 上午10:56:48
 */
public class GlobalAppConfig {

	/** 整个应用的web服务url(例如基于nginx)，例如: http://127.0.0.1 */
	private String webServerUrl;
	
	/** 整个应用的静态文件服务url(例如基于nginx)，例如: http://127.0.0.1/static */
	private String fileServerUrl;
	
	/** 整个应用的静态文件真实存储根路径(例如基于nginx)，例如: d:/nginxfiles/static */
	private String fileServerDir;

	public String getWebServerUrl() {
		return webServerUrl;
	}

	public void setWebServerUrl(String webServerUrl) {
		this.webServerUrl = webServerUrl;
	}

	public String getFileServerUrl() {
		return fileServerUrl;
	}

	public void setFileServerUrl(String fileServerUrl) {
		this.fileServerUrl = fileServerUrl;
	}

	public String getFileServerDir() {
		return fileServerDir;
	}

	public void setFileServerDir(String fileServerDir) {
		this.fileServerDir = fileServerDir;
	}
	
}
