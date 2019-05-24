package com.seed.entity.publics;

import com.seed.entity.*;
import com.seed.utils.*;

public class ShortPmt implements BaseBean {

	private short id;
	
	private String name;
	
	private SelectBean<ShortPmt> item;
	
	public ShortPmt() {
		OnInit();
	}
	
	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((ShortPmt)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.id = 0;
		this.name = "";
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public SelectBean<ShortPmt> getItem() {
		if (item == null)
			item = new SelectBean<ShortPmt>();
			
		return item;
	}

	public void setItem(SelectBean<ShortPmt> item) {
		this.item = item;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {

		return false;
	}
}
