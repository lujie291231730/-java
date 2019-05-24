package com.seed.entity.frm;

import java.util.ArrayList;
import java.util.List;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.enums.DataAction;
import com.seed.utils.*;

public class FrmForm implements BaseBean {

	// 表单编号;
	private String formid;

	// 表单分类编号;
	private String formtype;

	// 表单分类名称;
	private String formtypename;

	// 表单名称;
	private String formname;

	// 长度隔断;
	private int formlength;

	// 宽度隔断;
	private int formwidth;

	// 表单方向;
	private String formdirect;

	// 表单方向名称;
	private String formdirectname;

	// 分页隔断;
	private int pagewidth;

	// 建立日期;
	private java.util.Date createdate;

	// 建立人;
	private String createuser;

	// 建立人姓名;
	private String createusername;

	// 修改日期;
	private java.util.Date modifydate;

	// 修改人;
	private String modifyuser;

	// 修改人名称;
	private String modifyusername;

	// 当前序号;
	private int modifyserial;

	// 循环页;
	private boolean isloop;

	// 循环类;
	private String loopclass;

	// 首页条数;
	private int loopfirst;

	// 循环页条数;
	private int looprecord;

	private List<FrmFormDetail> details;
	
	private SearchParams search;

	private DataDeal deal;

	private SelectBean<FrmForm> item;

	public FrmForm() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		if (ToolUtils.StringIsEmpty(this.getFormid())) {
			msg.setErrmsg("表单编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if ((this.getDeal().getAction() ==  DataAction.Deal.getAction()) || (this.getDeal().getAction() ==  DataAction.Delete.getAction())){
			return rtn;
		}
				
		if (ToolUtils.CheckComboValue(this.getFormtype())) {
			msg.setErrmsg("请选择表单分类编号!"+ToolUtils.GetNewLines());
			rtn = true;
		}	
		
		if (ToolUtils.StringIsEmpty(this.getFormname())) {
			msg.setErrmsg("表单名称不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.StringIsEmpty(this.getFormdirect())) {
			msg.setErrmsg("请选择表单方向!"+ToolUtils.GetNewLines());
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
		return ToolUtils.CompareProperty((FrmForm)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"表单编号:formid", "表单分类编号:formtype", "表单名称:formname", "长度隔断:formlength", "宽度隔断:formwidth", 
				"表单方向:formdirect", "分页隔断:pagewidth", "建立日期:createdate", "建立人:createuser", "建立人姓名:createusername", 
				"修改日期:modifydate", "修改人:modifyuser", "修改人名称:modifyusername", "当前序号:modifyserial", "循环页:isloop", 
				"循环类:loopclass", "首页条数:loopfirst", "循环页条数:looprecord"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.formid = "";
		this.formtype = "";
		this.formtypename = "";
		this.formname = "";
		this.formlength = 0;
		this.formwidth = 0;
		this.formdirect = "";
		this.formdirectname = "";
		this.pagewidth = 0;
		this.createdate = ToolUtils.GetMinDate();
		this.createuser = "";
		this.createusername = "";
		this.modifydate = ToolUtils.GetMinDate();
		this.modifyuser = "";
		this.modifyusername = "";
		this.modifyserial = 0;
		this.isloop = false;
		this.loopclass = "";
		this.loopfirst = 0;
		this.looprecord = 0;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid=formid;
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

	public String getFormname() {
		return formname;
	}

	public void setFormname(String formname) {
		this.formname=formname;
	}

	public int getFormlength() {
		return formlength;
	}

	public void setFormlength(int formlength) {
		this.formlength=formlength;
	}

	public int getFormwidth() {
		return formwidth;
	}

	public void setFormwidth(int formwidth) {
		this.formwidth=formwidth;
	}

	public String getFormdirect() {
		return formdirect;
	}

	public void setFormdirect(String formdirect) {
		this.formdirect=formdirect;
	}

	public String getFormdirectname() {
		return formdirectname;
	}

	public void setFormdirectname(String formdirectname) {
		this.formdirectname=formdirectname;
	}

	public int getPagewidth() {
		return pagewidth;
	}

	public void setPagewidth(int pagewidth) {
		this.pagewidth=pagewidth;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser=createuser;
	}

	public String getCreateusername() {
		return createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername=createusername;
	}

	public java.util.Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(java.util.Date modifydate) {
		this.modifydate=modifydate;
	}

	public String getModifyuser() {
		return modifyuser;
	}

	public void setModifyuser(String modifyuser) {
		this.modifyuser=modifyuser;
	}

	public String getModifyusername() {
		return modifyusername;
	}

	public void setModifyusername(String modifyusername) {
		this.modifyusername=modifyusername;
	}

	public int getModifyserial() {
		return modifyserial;
	}

	public void setModifyserial(int modifyserial) {
		this.modifyserial = modifyserial;
	}

	public boolean getIsloop() {
		return isloop;
	}

	public void setIsloop(boolean isloop) {
		this.isloop=isloop;
	}

	public String getLoopclass() {
		return loopclass;
	}

	public void setLoopclass(String loopclass) {
		this.loopclass=loopclass;
	}

	public int getLoopfirst() {
		return loopfirst;
	}

	public void setLoopfirst(int loopfirst) {
		this.loopfirst=loopfirst;
	}

	public int getLooprecord() {
		return looprecord;
	}

	public void setLooprecord(int looprecord) {
		this.looprecord=looprecord;
	}

	public List<FrmFormDetail> getDetails() {
		if (details == null)
			details = new ArrayList<FrmFormDetail>();
		
		return details;
	}

	public void setDetails(List<FrmFormDetail> details) {
		this.details = details;
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

	public SelectBean<FrmForm> getItem() {
		if (item == null)
			item = new SelectBean<FrmForm>();

		return item;
	}

	public void setItem(SelectBean<FrmForm> item) {
		this.item = item;
	}

}
