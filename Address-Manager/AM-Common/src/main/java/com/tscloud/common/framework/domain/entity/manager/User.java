package com.tscloud.common.framework.domain.entity.manager;

import com.tscloud.common.framework.domain.TrackableEntity;

import java.io.Serializable;

/**
 * 用户管理
 * Created by shm on 2015/2/3.
 */
public class User extends TrackableEntity implements Serializable {

    private static final long serialVersionUID = 5451064022048828850L;
    private String loginName;   //登录名称
    private String userName;    //真实姓名
    private String oldPassword; //原密码
    private String passWord;    //登录密码
    private String phone;   //电话
    private String gender;  //性别
    private String email;   //邮箱
    private String address; //地址
    private String status;  //状态
    private String[] orgIds;  //机构数组
    private String orgFlag;
    private String parentOrgId; //父机构的ID
    private String caId;
    private String secretId;
    private String secretKey;

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    private String parentOrgName;//父机构名称

    private String orgCode;//机构编码

    private String provinceCode;//机构所属城市编码

    private String provinceName;//机构所属城市名称

    private String loginDate;//最后登录时间

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * 是否是租户管理员
     */
    private boolean isTenantManager = false;

    /**
     * 是否是系统管理员
     */
    private boolean isSysManager = false;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String[] orgIds) {
        this.orgIds = orgIds;
    }

    public String getOrgFlag() {
        return orgFlag;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }

    public boolean isTenantManager() {
        return this.isTenantManager;
    }

    public void setTenantManager(boolean isTenantManager) {
        this.isTenantManager = isTenantManager;
    }

    public boolean isSysManager() {
        return this.isSysManager;
    }

    public void setSysManager(boolean isSysManager) {
        this.isSysManager = isSysManager;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getCaId() {
        return caId;
    }

    public void setCaId(String caId) {
        this.caId = caId;
    }

    public String getOldPassword() {

        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {

        this.oldPassword = oldPassword;
    }
}
