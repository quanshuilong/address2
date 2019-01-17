package com.tscloud.address.domain.entity;

import com.tscloud.address.domain.utils.ConstantLayer;

import java.io.Serializable;

/**
 * @author Leighton
 * @create 2018-12-29.
 */
public class SearchParams implements Serializable {

    private Integer pageSize;
    private Integer pageNum;

    private Integer searchId;
    private String dataType;

    public Integer getPageSize() {
        return pageSize;
    }

    public SearchParams(Integer pageSize, Integer pageNum, Integer searchId, String dataType) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.searchId = searchId;
        this.dataType = dataType;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public String getDataType() {
        if (dataType==null||"".equals(dataType)){
           return ConstantLayer.district.name();
        }
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
