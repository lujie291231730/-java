package com.seed.entity.std;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.enums.DataAction;
import com.seed.utils.*;

public class StdAreaTree implements BaseBean {

	// 父区域编号;
	private String areapid;

	// 区域编号;
	private String areaid;

	// 区域名称;
	private String areaname;

	// 区域级别;
	private int arealevel;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<StdAreaTree> item;

	public StdAreaTree() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		if (ToolUtils.StringIsEmpty(this.getAreaid())) {
			msg.setErrmsg("区域编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.StringIsEmpty(this.getAreapid())) {
			msg.setErrmsg("父区域编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}

		if ((this.getDeal().getAction() ==  DataAction.Deal.getAction()) || (this.getDeal().getAction() ==  DataAction.Delete.getAction())){ 
			return rtn;
		}

		if (ToolUtils.StringIsEmpty(this.getAreaname())) {
			msg.setErrmsg("区域名称不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		return rtn;
	}

	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) {
		return ToolUtils.CompareProperty((StdAreaTree)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"父区域编号:areapid", "区域编号:areaid", "区域名称:areaname", "区域级别:arealevel"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.areapid = "";
		this.areaid = "";
		this.areaname = "";
		this.arealevel = 0;
	}

	public String getAreapid() {
		return areapid;
	}

	public void setAreapid(String areapid) {
		this.areapid=areapid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid=areaid;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname=areaname;
	}

	public int getArealevel() {
		return arealevel;
	}

	public void setArealevel(int arealevel) {
		this.arealevel=arealevel;
	}

	public SearchParams getSearch() {
		if (search == null)
			search = new SearchParams();

		return search;
	}

	public void setSearch(SearchParams search) {
		this.search = search;
	}

	public DataDeal getDeal() {
		if (deal == null)
			deal = new DataDeal();

		return deal;
	}

	public void setDeal(DataDeal deal) {
		this.deal = deal;
	}

	public SelectBean<StdAreaTree> getItem() {
		if (item == null)
			item = new SelectBean<StdAreaTree>();

		return item;
	}

	public void setItem(SelectBean<StdAreaTree> item) {
		this.item = item;
	}

}
