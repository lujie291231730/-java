package com.seed.mapper.sqlserver;

import java.util.List;

import com.seed.entity.org.*;
import com.seed.entity.publics.PmtBean;

import com.seed.mapper.BaseMapper;

public interface OrgMapper extends BaseMapper {
	
	// region Dept Mapper Methods
	
	public SysDept GetDept(SysDept item);
	
	public List<SysDept> GetListDept(SysDept item);
	
	public List<SysDept> GetListDeptByCo(SysDept item);
	
	public List<SysDept> SearchDept(SysDept item);
	
	public void SaveDept(SysDept item);
	
	public List<PmtBean> GetDeptByUser(SysDept item);
	
	// endregion Dept Mapper Methods
	
	// region Company Mapper Methods
	
	public SysCompany GetCompany(SysCompany item);
	
	public List<SysCompany> GetListCompany(SysCompany item);
	
	public void SaveCompany(SysCompany item);
	
	// endregion Company Mapper Methods
}
