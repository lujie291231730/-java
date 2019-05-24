package com.seed.entity.frm;

import java.util.ArrayList;
import java.util.List;

import com.seed.utils.*;
import com.seed.entity.BaseBean;
import com.seed.entity.ErrorMsg;

public class PrintPage implements BaseBean {

	private FrmForm form;
	
	private List<PrintData> details;
	
	public PrintPage() {
		this.OnInit();
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
		return ToolUtils.CompareProperty((PrintPage)item, this, this.OnProperties());
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

	public FrmForm getForm() {
		if (form == null)
			form = new FrmForm();
		
		return form;
	}

	public void setForm(FrmForm form) {
		this.form = form;
	}

	public List<PrintData> getDetails() {
		if (details == null)
			details = new ArrayList<PrintData>();
		
		return details;
	}

	public void setDetails(List<PrintData> details) {
		this.details = details;
	}	
	
}
