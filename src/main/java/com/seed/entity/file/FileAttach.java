package com.seed.entity.file;

import com.seed.entity.*;
import com.seed.entity.publics.*;
import com.seed.enums.DataAction;
import com.seed.utils.*;

public class FileAttach implements BaseBean {

	private String tranid;
	private String trancode;
	private String tranname;
	private String attachuuid;
	private String attachtitle;
	private String attachname;
	private String attachurl;
	private java.util.Date uploaddate;
	private String tranuser;
	private String tranusername;
	private int sortorder;
	private String filetype;
	private String filetypename;
	private String attachtype;
	private String downtype;
	
	private SearchParams search;

	private DataDeal deal;

	private SelectBean<FileAttach> item;

	public FileAttach() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		if (ToolUtils.StringIsEmpty(this.getAttachuuid())) {
			msg.setErrmsg("文件编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if ((this.getDeal().getAction() ==  DataAction.Deal.getAction()) || (this.getDeal().getAction() ==  DataAction.Delete.getAction())){
			return rtn;
		}
				
		if (ToolUtils.StringIsEmpty(this.getTranid())) {
			msg.setErrmsg("业务编号不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.StringIsEmpty(this.getAttachname())) {
			msg.setErrmsg("附件名称不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}		
		
		if (ToolUtils.StringIsEmpty(this.getAttachurl())) {
			msg.setErrmsg("文件地址不能为空!"+ToolUtils.GetNewLines());
			rtn = true;
		}
		
		if (ToolUtils.CheckComboValue(this.getAttachtype())) {
			msg.setErrmsg("请选择文件类型!"+ToolUtils.GetNewLines());
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
		return ToolUtils.CompareProperty((FileAttach)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"业务编号:tranid", "业务代码:trancode", "文件UUID:attachuuid", "附件标题:attachtitle", 
				"附件名称:attachname", "文件地址:attachurl", "上传日期:uploaddate", "上传人:tranuser", "文件顺序:sortorder", 
				"上传人姓名:tranusername", "上传企业编号:entid", "上传企业名称:entname", "文件类型:filetype", "文件分类名称:attachtype"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.tranid = "";
		this.trancode = "";
		this.attachuuid = "";
		this.attachtitle = "";
		this.attachname = "";
		this.attachurl = "";
		this.uploaddate = ToolUtils.GetNowDate();
		this.sortorder = 0;
		this.attachtype = "";
		this.tranuser = "";
		this.tranusername = "";
		this.filetype = "";
		this.downtype = "";
	}

	public String getDowntype() {
		return downtype;
	}

	public void setDowntype(String downtype) {
		this.downtype = downtype;
	}

	public String getTranid() {
		return tranid;
	}

	public void setTranid(String tranid) {
		this.tranid = tranid;
	}

	public String getTrancode() {
		return trancode;
	}

	public void setTrancode(String trancode) {
		this.trancode = trancode;
	}

	public String getTranname() {
		return tranname;
	}

	public void setTranname(String tranname) {
		this.tranname = tranname;
	}

	public String getAttachuuid() {
		return attachuuid;
	}

	public void setAttachuuid(String attachuuid) {
		this.attachuuid = attachuuid;
	}

	public String getAttachtitle() {
		return attachtitle;
	}

	public void setAttachtitle(String attachtitle) {
		this.attachtitle = attachtitle;
	}

	public String getAttachname() {
		return attachname;
	}

	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}

	public String getAttachurl() {
		return attachurl;
	}

	public void setAttachurl(String attachurl) {
		this.attachurl = attachurl;
	}

	public java.util.Date getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(java.util.Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	public int getSortorder() {
		return sortorder;
	}

	public void setSortorder(int sortorder) {
		this.sortorder = sortorder;
	}

	public String getAttachtype() {
		return attachtype;
	}

	public void setAttachtype(String attachtype) {
		this.attachtype = attachtype;
	}

	public String getTranuser() {
		return tranuser;
	}

	public void setTranuser(String tranuser) {
		this.tranuser = tranuser;
	}

	public String getTranusername() {
		return tranusername;
	}

	public void setTranusername(String tranusername) {
		this.tranusername = tranusername;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFiletypename() {
		return filetypename;
	}

	public void setFiletypename(String filetypename) {
		this.filetypename = filetypename;
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

	public SelectBean<FileAttach> getItem() {
		if (item == null)
			item = new SelectBean<FileAttach>();

		return item;
	}

	public void setItem(SelectBean<FileAttach> item) {
		this.item = item;
	}

}
