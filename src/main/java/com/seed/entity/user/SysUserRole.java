package com.seed.entity.user;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.utils.ToolUtils;

public class SysUserRole implements BaseBean {
	
	// 操作员编号;
	private String userid;
	
	// 角色编号;
	private int roleid;
	
	private String username;
	private String rolename;
	
	private DataDeal deal;
	private SearchParams search;
	private SelectBean<SysUserRole> item;
	
	public SysUserRole() {
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
		return new String[] { "用户编号:userid", "角色编号:roleid" };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.userid = "";
		this.roleid = 0;
		this.username = "";
		this.rolename = "";
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return new String[] {"deal"};
	}		

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public DataDeal getDeal() {
		if (deal == null)
			this.deal = new DataDeal();

		return deal;
	}

	public void setDeal(DataDeal deal) {
		this.deal = deal;
	}
	
	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;
		
		if (ToolUtils.CheckComboValue(this.getUserid())) {
			msg.setErrmsg("请选择操作人员！" + ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (this.getRoleid() <= 0) {
			msg.setErrmsg("请选择角色！" + ToolUtils.GetNewLines());
			rtn = true;
		}
		
		return rtn;
	}

	public SearchParams getSearch() {
		if (search == null)
			search = new SearchParams();
		
		return search;
	}

	public void setSearch(SearchParams search) {
		this.search = search;
	}

	public SelectBean<SysUserRole> getItem() {
		if (item == null)
			item = new SelectBean<SysUserRole>();
		
		return item;
	}

	public void setItem(SelectBean<SysUserRole> item) {
		this.item = item;
	}
	
	
}