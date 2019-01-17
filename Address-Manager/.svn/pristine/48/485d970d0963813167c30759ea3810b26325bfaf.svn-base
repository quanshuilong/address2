package com.tscloud.common.framework.rest.view;


import com.tscloud.common.framework.Constants;

/**
 * restful对外的JSON 对象封装
 */
public class JsonViewObject {
	
	private String status;
	private String message;
	private Object content;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}


    public JsonViewObject success(){
        this.setContent("true");
        this.setStatus(Constants.jsonView.STATUS_SUCCESS);
        return this;
    }

	public JsonViewObject successPack(Object result){
		this.setMessage("");
		this.setContent(result);
		this.setStatus(Constants.jsonView.STATUS_SUCCESS);
        return this;
	}

    public JsonViewObject successPack(Object result, String msg){
        this.setContent(result);
        this.setMessage(msg);
        this.setStatus(Constants.jsonView.STATUS_SUCCESS);
        return this;
    }

	public JsonViewObject failPack(Exception e){
        String message = e.getMessage();
        int index = message.indexOf( ":" );
		setMessage( index == -1 ? message : message.substring( index + 1 ) );
		setContent("");
		setStatus(Constants.jsonView.STATUS_FAIL);
        return this;
	}

	public JsonViewObject failPack(String errMsg){
		setMessage(errMsg);
		setContent("");
		setStatus(Constants.jsonView.STATUS_FAIL);
		return this;
	}

    public JsonViewObject failPack(Object result, String errMsg){
        setMessage(errMsg);
        setContent(result);
        setStatus(Constants.jsonView.STATUS_FAIL);
        return this;
    }

	public JsonViewObject failPackMessage(String errMsg, Object content){
		setMessage(errMsg);
		setContent(content);
		setStatus(Constants.jsonView.STATUS_FAIL);
        return this;
	}

	
	
	

}
