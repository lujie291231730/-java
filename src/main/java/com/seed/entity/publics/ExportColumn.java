package com.seed.entity.publics;

import com.seed.entity.*;

public class ExportColumn implements BaseBean {
	
	private String text;
	
	private String dataindex;
	
	private int width;
	
	private String format;
	
	public ExportColumn() {
		this.OnInit();
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return null;
	}

	@Override
	public String[] OnProperties() {
		return new String[0];
	}

	@Override
	public String OnDebug() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.text = "";
		this.dataindex = "";
		this.width = 0;
		this.format = "";
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDataindex() {
		return dataindex;
	}

	public void setDataindex(String dataindex) {
		this.dataindex = dataindex;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
