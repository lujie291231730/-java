package com.seed.entity.publics;

import com.seed.entity.*;
import com.seed.utils.*;

import java.util.ArrayList;
import java.util.List;

public class PmtSelect implements BaseBean {
	
	private String pmtname;
		
	private List<PmtBean> pmtdata;
	
	public PmtSelect() {
		OnInit();
	}
	
	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((PmtSelect)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.pmtname = "";
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPmtname() {
		return pmtname;
	}

	public void setPmtname(String pmtname) {
		this.pmtname = pmtname;
	}

	public List<PmtBean> getPmtdata() {
		if (pmtdata == null)
			pmtdata = new ArrayList<PmtBean>();
		
		return pmtdata;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {

		return false;
	}
}
