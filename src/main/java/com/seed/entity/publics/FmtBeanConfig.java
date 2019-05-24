package com.seed.entity.publics;

import com.seed.entity.BaseBean;
import com.seed.entity.ErrorMsg;
import com.seed.utils.StringUtils;
import com.seed.utils.ToolUtils;

public class FmtBeanConfig implements BaseBean {

	private String colcode;
	
	private int coltype;
	
	private int colspan;
	
	private int colwidth;
	
	private String colfmt;
	
	private int colheight;
	
	public FmtBeanConfig() {
		this.OnInit();
	}
	
	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((FmtBeanConfig)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public void OnInit() {
		this.colcode = "";
		this.coltype = 0;
		this.colspan = 1;
		this.colwidth = 0;
		this.colheight = 1;
		this.colfmt = "";
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getColcode() {
		return colcode;
	}

	public void setColcode(String colcode) {
		this.colcode = colcode;
	}

	public int getColtype() {
		return coltype;
	}

	public void setColtype(int coltype) {
		this.coltype = coltype;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public int getColwidth() {
		return colwidth;
	}

	public void setColwidth(int colwidth) {
		this.colwidth = colwidth;
	}

	public String getColfmt() {
		return colfmt;
	}

	public void setColfmt(String colfmt) {
		this.colfmt = colfmt;
	}

	public int getColheight() {
		return colheight;
	}

	public void setColheight(int colheight) {
		this.colheight = colheight;
	}

	public void SetConfig(String fmt) {
		this.OnInit();
		String[] fmts = fmt.split(":");
		
		if (fmts.length == 4) {
			this.setColcode(fmts[0]);
			this.setColtype(Integer.valueOf(fmts[1]));
			this.setColwidth(Integer.valueOf(fmts[2]));
			this.setColspan(Integer.valueOf(fmts[3]));
		}
		else if (fmts.length == 5) {
			this.setColcode(fmts[0]);
			this.setColtype(Integer.valueOf(fmts[1]));
			this.setColwidth(Integer.valueOf(fmts[2]));
			this.setColspan(Integer.valueOf(fmts[3]));
			
			if (StringUtils.IsNumber(fmts[4]))
				this.setColheight(Integer.valueOf(fmts[4]));
			else
				this.setColfmt(fmts[4]);
		}
		else if (fmts.length == 6) {
			this.setColcode(fmts[0]);
			this.setColtype(Integer.valueOf(fmts[1]));
			this.setColwidth(Integer.valueOf(fmts[2]));
			this.setColspan(Integer.valueOf(fmts[3]));
			this.setColheight(Integer.valueOf(fmts[4]));
			this.setColfmt(fmts[5]);
		}
	}
}

