package com.penglecode.xmodule.platform.upms.consts;

import static com.penglecode.xmodule.common.consts.Constant.defaultOf;

import java.util.Map;

import com.penglecode.xmodule.common.consts.Constant;
import com.penglecode.xmodule.common.support.VersionedObject;
import com.penglecode.xmodule.platform.upms.model.UpmsApp;

/**
 * 统一用户权限管理系统常量
 * 
 * @author 	pengpeng
 * @date	2019年5月18日 上午11:40:52
 */
public class UpmsConstants {

	public static final String DEFAULT_UPMS_USER_LOGIN_URL = "/api/auth/login";
	
	/**
	 * upms登录用户在会话中的key
	 */
	public static final String UPMS_LOGIN_USER_SESSION_KEY = "upmsLoginUser";
	
	/**
	 * 系统默认的上传文件临时存储路径
	 */
	public static final String DEFAULT_USER_ICON_SAVE_PATH = "/img/usericon/";
	
	/**
	 * 用户默认头像
	 */
	public static final Constant<String> DEFAULT_USER_AVATAR = new Constant<String>("appone.upms.defaultUserAvatar", "/img/default-user-avatar.png") {};
	
	/**
	 * 默认的资源ICON
	 */
	public static final Constant<String> DEFAULT_RESOURCE_ICON = new Constant<String>("appone.upms.defaultResourceIcon" ,"fas fa-dot") {};
	
	/**
	 * 默认的应用ICON
	 */
	public static final Constant<String> DEFAULT_APPLICATION_ICON = new Constant<String>("appone.upms.defaultApplicationIcon" ,"fas fa-cloud") {};
	
	/**
	 * 所有应用的全局缓存
	 */
	public static final VersionedObject<Map<String,UpmsApp>> ALL_UPMS_APPS = defaultOf(new VersionedObject<>());
	
}
