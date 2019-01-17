package com.tscloud.common.framework.rest.view;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	private int pageSize = 10;
	private int pageNumber = 1;
	private int total = 0;
	private int totalPageNum = 0;
	private int startRowNum = 0;
	private int endRowNum = 0;
	private Object objCondition;
	@SuppressWarnings("rawtypes")
	private List rows;
	

    public Page() {
    }


    public Page(int pageSize, int total) {
        this.pageSize = pageSize;
        this.total = total;
        int mod = total % pageSize;
        totalPageNum = mod == 0 ? (total / pageSize) : (total / pageSize) + 1;
        if (startRowNum<=0) {
        	startRowNum = 0;
            endRowNum = pageSize;
		}
    }

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		this.startRowNum = (pageSize*(pageNumber-1));
		this.endRowNum = (pageSize*pageNumber);
		
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
        int mod = total % pageSize;
        totalPageNum = mod == 0 ? (total / pageSize) : (total / pageSize) + 1;
        if (startRowNum<=0) {
        	startRowNum = 0;
            endRowNum = pageSize;
		}
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum(int startRowNum) {
//		this.startRowNum = startRowNum;
		this.startRowNum = (pageSize*(pageNumber-1));
		
	}
	public int getEndRowNum() {
		this.endRowNum = (pageSize*pageNumber);
		return endRowNum;
	}
	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public void setObjCondition(Object objCondition) {
		this.objCondition = objCondition;
	}

	public Object getObjCondition() {
		return objCondition;
	}
	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}
	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}
	
}
