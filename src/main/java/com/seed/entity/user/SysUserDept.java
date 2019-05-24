package com.seed.entity.user;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.utils.ToolUtils;

public class SysUserDept implements BaseBean {
	
	// 操作员编号;
	private String userid;

	private String username;
	private String deptid;
	private String deptname;
	
	private DataDeal deal;
	private SearchParams search;
	private SelectBean<SysUserDept> item;

	public SysUserDept() {
		OnInit();
	}

	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((SysUserWorkGroup)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { "用户编号:userid", "部门编号:deptname" };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.userid = "";
		this.username = "";
		this.deptid = "";
		this.deptname = "";
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return new String[] {"deal"};
	}	
	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public SelectBean<SysUserDept> getItem() {
		if (item == null)
			item = new SelectBean<SysUserDept>();
		
		return item;
	}

	public void setItem(SelectBean<SysUserDept> item) {
		this.item = item;
	}

	public DataDeal getDeal() {
		if (deal == null)
			this.deal = new DataDeal();

		return deal;
	}

	public void setDeal(DataDeal deal) {
		this.deal = deal;
	}
	
	public SearchParams getSearch() {
		if (search == null)
			search = new SearchParams();
		
		return search;
	}

	public void setSearch(SearchParams search) {
		this.search = search;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;
		
		if (ToolUtils.CheckComboValue(this.getUserid())) {
			msg.setErrmsg("请选择操作人员！" + ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.CheckComboValue(this.getDeptid())) {
			msg.setErrmsg("请选择部门！" + ToolUtils.GetNewLines());
			rtn = true;
		}
		
		return rtn;
	}
}