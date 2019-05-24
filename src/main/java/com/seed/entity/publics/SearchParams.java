package com.seed.entity.publics;

import com.seed.entity.*;
import com.seed.utils.*;

public class SearchParams implements BaseBean {
	private String search;
	
	private int start;
	
	private int limit;
	
	private int end;
	
	private int total;
	
	private String getaction;
	
	private String procedurename;
	
	private String userid;
	
	private String deptid;
	
	private String begindate;
	
	private String enddate;
	
	private String year;
	
	private String month;
	
	private String day;
	
	private boolean hasexport;
	
	private int expcnt;
	
	private String columnsql;
	
	public SearchParams() {
		OnInit();
	}
	
	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((SearchParams)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.search = "";
		this.start = 0;
		this.limit = 0;
		this.end = 0;
		this.total = 0;
		this.getaction = "";
		this.procedurename = "";
		this.userid = "";
		this.deptid = "";
		this.begindate = "";
		this.enddate = "";
		this.year = "";
		this.month = "";
		this.day = "";
		this.hasexport = false;
		this.expcnt = 0;
		this.columnsql = "";
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getExpcnt() {
		return expcnt;
	}

	public void setExpcnt(int expcnt) {
		this.expcnt = expcnt;
	}

	public boolean isHasexport() {
		return hasexport;
	}

	public void setHasexport(boolean hasexport) {
		this.hasexport = hasexport;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getGetaction() {
		return getaction;
	}

	public void setGetaction(String getaction) {
		this.getaction = getaction;
	}

	public String getProcedurename() {
		return procedurename;
	}

	public void setProcedurename(String procedurename) {
		this.procedurename = procedurename;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {

		return false;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
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
	
	public String getColumnsql() {
		return columnsql;
	}

	public void setColumnsql(String columnsql) {
		this.columnsql = columnsql;
	}

	public String GetDateBetween(String datedefine) {
		String rtn = "";
				
		if (!StringUtils.StringIsEmpty(this.getEnddate()))
			rtn += StringUtils.GetAndSearch(rtn) + " " + datedefine + " <= " + StringUtils.GetEndDate(this.getEnddate()) + " ";
		
		if (!StringUtils.StringIsEmpty(this.getBegindate()))
			rtn += StringUtils.GetAndSearch(rtn) + " " + datedefine + " >= " + StringUtils.GetBeginDate(this.getBegindate()) + " ";
		
		return rtn;
	}
	
	public String GetExportDateBetween() {		
		return Consts.STR_CN_DATE + Consts.STR_CN_COLON + this.getBegindate() + Consts.STR_CN_DATESPLIT + this.getEnddate();
	}
	
	public String GetDayBetween(String datedefine) {
		String rtn = "";
				
		if (!StringUtils.StringIsEmpty(this.getDay()))
			rtn += StringUtils.GetAndSearch(rtn) + " " + datedefine + " <= " + StringUtils.GetEndDate(this.getDay()) + " ";
		
		if (!StringUtils.StringIsEmpty(this.getDay()))
			rtn += StringUtils.GetAndSearch(rtn) + " " + datedefine + " >= " + StringUtils.GetBeginDate(this.getDay()) + " ";
		
		return rtn;
	}
	
	public String GetExportDayBetween() {		
		return Consts.STR_CN_DATE + Consts.STR_CN_COLON + this.getDay();
	}
}
