package com.penglecode.xmodule.common.support;

import java.util.List;

import com.penglecode.xmodule.common.support.MvvmApp;

/**
 * 前后台分离应用服务
 * 
 * @author 	pengpeng
 * @date	2019年6月11日 下午3:24:42
 */
public interface MvvmAppService {

	/**
	 * 获取所有前后台分离应用列表
	 * @return
	 */
	public List<MvvmApp> getAllMvvmAppList();
	
}
