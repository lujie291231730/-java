package com.seed.entity.frm;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.enums.DataAction;
import com.seed.utils.*;

public class FrmFormType implements BaseBean {

	// 表单分类编号;
	private String formtype;

	// 表单分类名称;
	private String formtypename;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<FrmFormType> item;

	public FrmFormType() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		if (ToolUtils.StringIsEmpty(this.getFormtype())) {
			msg.setErrmsg("表单分类编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if ((this.getDeal().getAction() ==  DataAction.Deal.getAction()) || (this.getDeal().getAction() ==  DataAction.Delete.getAction())){
			return rtn;
		}
				
		if (ToolUtils.StringIsEmpty(this.getFormtypename())) {
			msg.setErrmsg("表单分类名称不能为空!"+ToolUtils.GetNewLines());
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
		return ToolUtils.CompareProperty((FrmFormType)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"表单分类编号:formtype", "表单分类名称:formtypename"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.formtype = "";
		this.formtypename = "";
	}

	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype=formtype;
	}

	public String getFormtypename() {
		return formtypename;
	}

	public void setFormtypename(String formtypename) {
		this.formtypename=formtypename;
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

	public SelectBean<FrmFormType> getItem() {
		if (item == null)
			item = new SelectBean<FrmFormType>();

		return item;
	}

	public void setItem(SelectBean<FrmFormType> item) {
		this.item = item;
	}

}
