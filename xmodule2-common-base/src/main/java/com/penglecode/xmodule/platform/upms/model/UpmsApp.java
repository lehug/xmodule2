package com.penglecode.xmodule.platform.upms.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.penglecode.xmodule.common.codegen.Id;
import com.penglecode.xmodule.common.codegen.Model;
import com.penglecode.xmodule.common.support.BaseModel;
import com.penglecode.xmodule.common.support.MvvmApp;
import com.penglecode.xmodule.platform.upms.consts.em.UpmsAppTypeEnum;

/**
 * UPMS应用 (upms_app) 实体类
 * 
 * @author Mybatis-Generator
 * @date	2019年06月27日 下午 14:10:28
 */
@Model(name="UPMS应用", alias="App")
public class UpmsApp implements BaseModel<UpmsApp>, MvvmApp {
     
    private static final long serialVersionUID = 1L;

    /** 应用ID */
    @Id
    private Long appId;

    /** 应用名称 */
    private String appName;

    /** 应用代码 */
    private String appCode;
    
    /** 应用的spring.application.name */
    private String appProjectName;

    /** 应用描述 */
    private String description;

    /** 应用类型：0-核心应用,1-普通应用 */
    private Integer appType;

    /** 应用key */
    private String appKey;

    /** 应用安全码 */
    private String appSecret;

    /** 前台web应用的contextpath */
    private String appWebContextPath;

    /** 后台api应用的contextpath */
    private String appApiContextPath;

    /** 启用/禁用 */
    private Boolean enabled;

    /** 创建时间 */
    private String createTime;

    /** 创建者ID */
    private Long createBy;
    
    //以下为辅助字段
    
    private String appTypeName;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppProjectName() {
		return appProjectName;
	}

	public void setAppProjectName(String appProjectName) {
		this.appProjectName = appProjectName;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppWebContextPath() {
        return appWebContextPath;
    }

    public void setAppWebContextPath(String appWebContextPath) {
        this.appWebContextPath = appWebContextPath;
    }

    public String getAppApiContextPath() {
        return appApiContextPath;
    }

    public void setAppApiContextPath(String appApiContextPath) {
        this.appApiContextPath = appApiContextPath;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    
    public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}
	
	@Override
	public UpmsApp decode() {
		if(appType != null){
			UpmsAppTypeEnum em = UpmsAppTypeEnum.getAppType(appType);
			if(em != null){
				setAppTypeName(em.getTypeName());
			}
		}
		return this;
	}

    public MapBuilder mapBuilder() {
        return new MapBuilder();
    }

    /**
     * Auto generated by Mybatis Generator
     */
    public class MapBuilder {
         
        private final Map<String, Object> modelProperties = new LinkedHashMap<String,Object>();

        MapBuilder() {
            
        }

        public MapBuilder withAppId(Long ... appId) {
            modelProperties.put("appId", BaseModel.first(appId, getAppId()));
            return this;
        }

        public MapBuilder withAppName(String ... appName) {
            modelProperties.put("appName", BaseModel.first(appName, getAppName()));
            return this;
        }

        public MapBuilder withAppCode(String ... appCode) {
            modelProperties.put("appCode", BaseModel.first(appCode, getAppCode()));
            return this;
        }

        public MapBuilder withDescription(String ... description) {
            modelProperties.put("description", BaseModel.first(description, getDescription()));
            return this;
        }

        public MapBuilder withAppType(Integer ... appType) {
            modelProperties.put("appType", BaseModel.first(appType, getAppType()));
            return this;
        }

        public MapBuilder withAppKey(String ... appKey) {
            modelProperties.put("appKey", BaseModel.first(appKey, getAppKey()));
            return this;
        }

        public MapBuilder withAppSecret(String ... appSecret) {
            modelProperties.put("appSecret", BaseModel.first(appSecret, getAppSecret()));
            return this;
        }

        public MapBuilder withAppWebContextPath(String ... appWebContextPath) {
            modelProperties.put("appWebContextPath", BaseModel.first(appWebContextPath, getAppWebContextPath()));
            return this;
        }

        public MapBuilder withAppApiContextPath(String ... appApiContextPath) {
            modelProperties.put("appApiContextPath", BaseModel.first(appApiContextPath, getAppApiContextPath()));
            return this;
        }

        public MapBuilder withEnabled(Boolean ... enabled) {
            modelProperties.put("enabled", BaseModel.first(enabled, getEnabled()));
            return this;
        }

        public MapBuilder withCreateTime(String ... createTime) {
            modelProperties.put("createTime", BaseModel.first(createTime, getCreateTime()));
            return this;
        }

        public MapBuilder withCreateBy(Long ... createBy) {
            modelProperties.put("createBy", BaseModel.first(createBy, getCreateBy()));
            return this;
        }

        public Map<String, Object> build() {
            return modelProperties;
        }
    }
}