package com.tscloud.common.framework.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 继承自该类的entity表示是可被跟踪的，可以查询对应实体的创建时间，修改时间，修改人
 */
public class TrackableEntity implements Serializable {

    private static final long serialVersionUID = -4052705808523280313L;

    private String id;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 操作者ID
     */
    private String operatorId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 组织机构ID
     */
    private String orgId;

    /**
     * 组织机构名称
     */
    private String orgName;

    /**
     * 用户id
     */
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
