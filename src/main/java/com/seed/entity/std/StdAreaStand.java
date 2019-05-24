package com.seed.entity.std;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.enums.DataAction;
import com.seed.utils.*;

public class StdAreaStand implements BaseBean {

	// 省份编号;
	private String provid;

	// 省份名称;
	private String provname;

	// 市编号;
	private String cityid;

	// 市名称;
	private String cityname;

	// 县区编号;
	private String areaid;

	// 县区名称;
	private String areaname;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<StdAreaStand> item;

	public StdAreaStand() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		if (ToolUtils.StringIsEmpty(this.getProvid())) {
			msg.setErrmsg("省份编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.StringIsEmpty(this.getCityid())) {
			msg.setErrmsg("市编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.StringIsEmpty(this.getAreaid())) {
			msg.setErrmsg("县区编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}		

		if ((this.getDeal().getAction() ==  DataAction.Deal.getAction()) || (this.getDeal().getAction() ==  DataAction.Delete.getAction())){ 
			return rtn;
		}

		if (ToolUtils.StringIsEmpty(this.getProvname())) {
			msg.setErrmsg("省份名称不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.StringIsEmpty(this.getCityname())) {
			msg.setErrmsg("市名称不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.StringIsEmpty(this.getAreaname())) {
			msg.setErrmsg("县区名称不能为空!"+ToolUtils.GetNewLines());
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
		return ToolUtils.CompareProperty((StdAreaStand)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"省份编号:provid", "省份名称:provname", "市编号:cityid", "市名称:cityname", "县区编号:areaid", "县区名称:areaname"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.provid = "";
		this.provname = "";
		this.cityid = "";
		this.cityname = "";
		this.areaid = "";
		this.areaname = "";
	}

	public String getProvid() {
		return provid;
	}

	public void setProvid(String provid) {
		this.provid=provid;
	}

	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname=provname;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid=cityid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname=cityname;
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

	public SelectBean<StdAreaStand> getItem() {
		if (item == null)
			item = new SelectBean<StdAreaStand>();

		return item;
	}

	public void setItem(SelectBean<StdAreaStand> item) {
		this.item = item;
	}

}
