package com.seed.webservice.entity;

import com.seed.entity.BaseBean;
import com.seed.entity.ErrorMsg;
import com.seed.entity.publics.DataDeal;
import com.seed.entity.publics.SearchParams;
import com.seed.entity.publics.SelectBean;
import com.seed.utils.StringUtils;
import com.seed.utils.ToolUtils;

public class WsGuid implements BaseBean {

	private String wsguid;
	private java.util.Date crtdate;
	
	private SearchParams search;

	private DataDeal deal;

	private SelectBean<WsGuid> item;

	public WsGuid() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		if (StringUtils.StringIsEmpty(this.getWsguid()))
    {
      msg.setErrmsg("GUID编号不能为空！" + StringUtils.GetNewLines());
      rtn = true;
    }
		
		return rtn;
	}

	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((WsGuid)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"GUID编号:wsguid", "建立日期:crtdate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.wsguid = "";
		this.crtdate = ToolUtils.GetMinDate();
	}
	
	public String getWsguid() {
		return wsguid;
	}

	public void setWsguid(String wsguid) {
		this.wsguid = wsguid;
	}

	public java.util.Date getCrtdate() {
		return crtdate;
	}

	public void setCrtdate(java.util.Date crtdate) {
		this.crtdate = crtdate;
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

	public SelectBean<WsGuid> getItem() {
		if (item == null)
			item = new SelectBean<WsGuid>();

		return item;
	}

	public void setItem(SelectBean<WsGuid> item) {
		this.item = item;
	}
}
