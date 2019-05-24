package com.seed.entity.std;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.enums.DataAction;
import com.seed.utils.*;

public class StdArea implements BaseBean {

	// 区域编号;
	private String areaid;

	// 区域名称;
	private String areaname;

	// 上级区域编号;
	private String areapid;

	// 区域简称;
	private String areashort;

	// 区域代码;
	private String areacode;

	// 区域类型;
	private String areatype;

	// 区域类型名称;

	private String areatypename;

	// 耕作面积;
	private double areaarea;

	// 主要作物介绍;
	private String areacrop;

	// 区域级别;
	private int arealevel;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<StdArea> item;

	public StdArea() {
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

		if ((this.getDeal().getAction() ==  DataAction.Deal.getAction()) || (this.getDeal().getAction() ==  DataAction.Delete.getAction())){ 
			return rtn;
		}

		if (ToolUtils.StringIsEmpty(this.getAreaname())) {
			msg.setErrmsg("区域名称不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.StringIsEmpty(this.getAreashort())) {
			msg.setErrmsg("区域简称不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.CheckComboValue(this.getAreatype())) {
			msg.setErrmsg("请选择区域类型!"+ToolUtils.GetNewLines());
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
		return ToolUtils.CompareProperty((StdArea)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"区域编号:areaid", "区域名称:areaname", "上级区域编号:areapid", "区域简称:areashort", "区域代码:areacode", 
				"区域类型:areatype", "区域类型名称:areatypename", "耕作面积:areaarea", "主要作物介绍:areacrop", "区域级别:arealevel"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.areaid = "";
		this.areaname = "";
		this.areapid = "";
		this.areashort = "";
		this.areacode = "";
		this.areatype = "";
		this.areatypename = "";
		this.areaarea = 0;
		this.areacrop = "";
		this.arealevel = 0;
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

	public String getAreapid() {
		return areapid;
	}

	public void setAreapid(String areapid) {
		this.areapid=areapid;
	}

	public String getAreashort() {
		return areashort;
	}

	public void setAreashort(String areashort) {
		this.areashort=areashort;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode=areacode;
	}

	public String getAreatype() {
		return areatype;
	}

	public void setAreatype(String areatype) {
		this.areatype=areatype;
	}

	public String getAreatypename() {
		return areatypename;
	}

	public void setAreatypename(String areatypename) {
		this.areatypename=areatypename;
	}

	public double getAreaarea() {
		return areaarea;
	}

	public void setAreaarea(double areaarea) {
		this.areaarea=areaarea;
	}

	public String getAreacrop() {
		return areacrop;
	}

	public void setAreacrop(String areacrop) {
		this.areacrop=areacrop;
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

	public SelectBean<StdArea> getItem() {
		if (item == null)
			item = new SelectBean<StdArea>();

		return item;
	}

	public void setItem(SelectBean<StdArea> item) {
		this.item = item;
	}

}

