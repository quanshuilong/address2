package com.tscloud.common.framework.domain.entity.manager;

import com.tscloud.common.framework.domain.TrackableEntity;

import java.io.Serializable;

/**
 * 角色管理
 * Created by shm on 2015/9/9.
 */
public class Role extends TrackableEntity implements Serializable {
    private static final long serialVersionUID = 8465602276318733275L;

    private String name;    //角色名称

    private String description;// 描述

    private String appId;   //应用id

    private String code;    //角色编码

    private String roleLevel; //角色级别

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
