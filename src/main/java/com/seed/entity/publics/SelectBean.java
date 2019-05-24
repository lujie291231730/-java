package com.seed.entity.publics;

import com.seed.entity.*;
import com.seed.utils.*;

import java.util.List;

public class SelectBean<T> implements BaseBean {

	private String getaction;
	
	private String procedurename;
	
	private String userid;
	
	private boolean isadmin;
	
	private String deptid;
	
	private String accountid;
	
	private short tableid;
	
	private List<T> bean;
	
	public SelectBean() {
		this.OnInit();
	}
	
	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) {
		return "";
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.getaction = "";
		this.procedurename = "";
		this.userid = "";
		this.deptid = "";
		this.tableid = 0;
		this.accountid = "";
		this.isadmin = false;
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

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
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

	public short getTableid() {
		return tableid;
	}

	public void setTableid(short tableid) {
		this.tableid = tableid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public List<T> getBean() {
		return bean;
	}

	public void setBean(List<T> bean) {
		this.bean = bean;
	}
}

