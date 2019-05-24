package com.seed.entity;

public class ServiceReturn {
	private boolean success;
	
	private Object data;
	
	private String msg;
	
	public ServiceReturn() {
		this.success = true;
		this.data = null;
		this.msg = "";
	}
	
	public ServiceReturn(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
