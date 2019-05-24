package com.seed.extend.umeng.ios;

import java.io.Serializable;

public class Alert implements Serializable{

	private static final long serialVersionUID = 6270474731330314029L;

	private String title;
	
	private String subtitle;
	
	private String body;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/*@Override
	public String toString() {
		// TODO Auto-generated method stub
		return JsonUtils.getJsonFromBean(this);
	}*/
	
	
}
