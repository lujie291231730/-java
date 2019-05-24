package com.seed.entity.publics;

import com.seed.entity.*;

public class ListMenu implements BaseBean {
	
	private int mid;
	
	private String mcode;
	
	private int mauth;

	public ListMenu() {
		this.OnInit();
	}
	
	@Override
	public String OnDebug() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.mid = 0;
		this.mcode = "";
		this.mauth = 0;
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

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public int getMauth() {
		return mauth;
	}

	public void setMauth(int mauth) {
		this.mauth = mauth;
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
