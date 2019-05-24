package com.seed.mapper.sqlserver;

import java.util.List;

import com.seed.entity.publics.*;
import com.seed.entity.std.StdArea;
import com.seed.entity.system.*;
import com.seed.entity.log.*;
import com.seed.mapper.BaseMapper;

public interface SystemMapper extends BaseMapper {
	
	public List<SysSet> GetListSet();
	
	public List<PmtBean> GetPmtSelect(PmtBean item);
	
	public SysPmt GetPmt(SysPmt item);
	
	public List<SysPmt> GetListPmt(SysPmt item);	
	
	public List<IntPmt> IntPmtByTable(short pmtid);
	
	public List<ShortPmt> ShortPmtByTable(short pmtid);
	
	public List<StringPmt> StringPmtByTable(short pmtid);
	
	public void ExecSQL(String execsql);
	
	public void SaveTranLog(TranLog log);
	
	public List<TranLog> SearchTranLog(TranLog item);
	
	public List<LoginLog> SearchLoginLog(LoginLog item);
	
	public List<JsonSqlColumn> SqlColumn(JsonSqlColumn item);
	
	public String GetStringValue(String sql);
	
	public int GetIntValue(String sql);
	
	public short GetShortValue(String sql);	
	
	// region StdArea Methods

	public StdArea GetStdArea(StdArea item);

	public List<StdArea> SearchStdArea(StdArea item);

	public void SaveStdArea(StdArea item);

	public List<PmtBean> GetCity(String provid);
	
	public List<PmtBean> GetAreaByCity(String cityid);

	public List<StdArea> GetProv(StdArea area);
	
	// endregion StdArea Methods

}
