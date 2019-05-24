package com.seed.entity.publics;

import com.seed.entity.*;
import com.seed.utils.*;

public class RequestParams implements BaseBean {

	private String begindate;
	
	private String enddate;
	
	private String year;
	
	private String month;
	
	private String day;
	
	public RequestParams() {
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
		this.begindate = "";
		this.enddate = "";
		this.year = "";
		this.month = "";
		this.day = "";
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

	public String getBegindate() {
		return begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String GetDateBetween(String datedefine) {
		String rtn = "";
				
		if (!ToolUtils.StringIsEmpty(this.getEnddate()))
			rtn += StringUtils.GetAndSearch(rtn) + " " + datedefine + " <= " + StringUtils.GetEndDate(this.getEnddate()) + " ";
		
		if (!ToolUtils.StringIsEmpty(this.getBegindate()))
			rtn += StringUtils.GetAndSearch(rtn) + " " + datedefine + " >= " + StringUtils.GetBeginDate(this.getBegindate()) + " ";
		
		return rtn;
	}
	
	public String GetExportDateBetween() {		
		return "日期：" + this.getBegindate() + "至" + this.getEnddate();
	}
	
	public String GetDayBetween(String datedefine) {
		String rtn = "";
				
		if (!ToolUtils.StringIsEmpty(this.getDay()))
			rtn += StringUtils.GetAndSearch(rtn) + " " + datedefine + " <= " + StringUtils.GetEndDate(this.getDay()) + " ";
		
		if (!ToolUtils.StringIsEmpty(this.getDay()))
			rtn += StringUtils.GetAndSearch(rtn) + " " + datedefine + " >= " + StringUtils.GetBeginDate(this.getDay()) + " ";
		
		return rtn;
	}
	
	public String GetExportDayBetween() {		
		return "日期：" + this.getDay();
	}
} 
