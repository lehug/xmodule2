package com.penglecode.xmodule.common.security.authz;

import java.util.Arrays;

import org.springframework.security.access.vote.AffirmativeBased;

import com.penglecode.xmodule.common.security.support.MultiAppRoleVoter;

/**
 * 自定义的动态URL-ROLE权限拦截AccessDecisionManager
 * 
 * @author 	pengpeng
 * @date	2019年5月20日 下午1:38:45
 */
public class DynamicUrlAccessDecisionManager extends AffirmativeBased {

    public DynamicUrlAccessDecisionManager() {
        super(Arrays.asList(new MultiAppRoleVoter()));
        setAllowIfAllAbstainDecisions(false);
    }

}