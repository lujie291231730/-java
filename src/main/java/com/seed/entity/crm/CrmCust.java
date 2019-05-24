package com.seed.entity.crm;

import java.util.Date;

import com.seed.entity.*;
import com.seed.entity.publics.DataDeal;
import com.seed.entity.publics.SearchParams;
import com.seed.entity.publics.SelectBean;
import com.seed.entity.std.StdArea;

public class CrmCust implements BaseBean{
	
	private String custid;
	
	private String custtype;
	
	private String custname;
	
	private String provid;
	
	private String cityid;
	
	private String areaid;
	
	private String industryclass;
	
	private String custpost;
	
	private String custaddress;
	
	private String linkman;
	
	private String linktele;
	
	private String linkfax;
	
	private String custemail;
	
	private String website;
	
	private String custstatus;
	
	private String bankid;
	
	private String enterbank;
	
	private String bankaccount;
	
	private String entertax;
	
	private String indcode;
	
	private String taxcode;
	
	private String deptcode;
	
	private String shortname;
	
	private String custdesc;
	
	private String remark;
	
	private String dutyuser;
	
	private String dutyusername;

	private String tranuser;
	
	private String trandate;
	
	private SearchParams search;

	private DataDeal deal;
	
	private SelectBean<CrmCust> item;
	
	public SelectBean<CrmCust> getItem() {
		if (item == null)
			item = new SelectBean<CrmCust>();

		return item;
	}

	public void setItem(SelectBean<CrmCust> item) {
		this.item = item;
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

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getCusttype() {
		return custtype;
	}

	public void setCusttype(String custtype) {
		this.custtype = custtype;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getProvid() {
		return provid;
	}

	public void setProvid(String provid) {
		this.provid = provid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getIndustryclass() {
		return industryclass;
	}

	public void setIndustryclass(String industryclass) {
		this.industryclass = industryclass;
	}

	public String getCustpost() {
		return custpost;
	}

	public void setCustpost(String custpost) {
		this.custpost = custpost;
	}

	public String getCustaddress() {
		return custaddress;
	}

	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinktele() {
		return linktele;
	}

	public void setLinktele(String linktele) {
		this.linktele = linktele;
	}

	public String getLinkfax() {
		return linkfax;
	}

	public void setLinkfax(String linkfax) {
		this.linkfax = linkfax;
	}

	public String getCustemail() {
		return custemail;
	}

	public void setCustemail(String custemail) {
		this.custemail = custemail;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCuststatus() {
		return custstatus;
	}

	public void setCuststatus(String custstatus) {
		this.custstatus = custstatus;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getEnterbank() {
		return enterbank;
	}

	public void setEnterbank(String enterbank) {
		this.enterbank = enterbank;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getEntertax() {
		return entertax;
	}

	public void setEntertax(String entertax) {
		this.entertax = entertax;
	}

	public String getIndcode() {
		return indcode;
	}

	public void setIndcode(String indcode) {
		this.indcode = indcode;
	}

	public String getTaxcode() {
		return taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getCustdesc() {
		return custdesc;
	}

	public void setCustdesc(String custdesc) {
		this.custdesc = custdesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDutyuser() {
		return dutyuser;
	}

	public void setDutyuser(String dutyuser) {
		this.dutyuser = dutyuser;
	}

	public String getDutyusername() {
		return dutyusername;
	}

	public void setDutyusername(String dutyusername) {
		this.dutyusername = dutyusername;
	}

	public String getTranuser() {
		return tranuser;
	}

	public void setTranuser(String tranuser) {
		this.tranuser = tranuser;
	}

	public String getTrandate() {
		return trandate;
	}

	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}

	@Override
	public String OnDebug() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CrmCust() {
		this.OnInit();
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.custtype = "";
		this.custname = "";
		this.provid = "";
		this.cityid = "";
		this.areaid = "";
		this.industryclass = "1";
		this.custpost = "1";
		this.custaddress = "";
		this.linkman = "1";
		this.linktele = "1";
		this.linkfax = "1";
		this.custemail = "1";
		this.website = "1";
		this.custstatus = "1";
		this.bankid = "1";
		this.enterbank = "1";
		this.bankaccount = "1";
		this.entertax = "1";
		this.indcode = "1";
		this.taxcode = "1";
		this.deptcode = "1";
		this.shortname = "1";
		this.custdesc = "1";
		this.remark = "1";
		this.dutyuser = "1";
		this.dutyusername = "1";
		this.trandate = "1";
		this.tranuser = "1";
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		return false;
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] OnProperties() {
		// TODO Auto-generated method stub
		return null;
	}
}
