package com.seed.entity.frm;

import java.util.ArrayList;
import java.util.List;

import com.seed.entity.*;
import com.seed.utils.*;

public class PrintData implements BaseBean {
	
	private List<FrmFormDetail> datas;
	
	public PrintData() {
		this.OnInit();
	}
	
	public PrintData(List<FrmFormDetail> datas) {
		this.datas = datas;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		return rtn;
	}

	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((PrintData)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public String[] OnExclusions() {
		return new String[] { };
	}

	@Override
	public void OnInit() {

	}

	public List<FrmFormDetail> getDatas() {
		if (datas == null)
			datas = new ArrayList<FrmFormDetail>();
		
		return datas;
	}

	public void setDatas(List<FrmFormDetail> datas) {
		this.datas = datas;
	}	
	
}
