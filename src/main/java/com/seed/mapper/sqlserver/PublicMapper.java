package com.seed.mapper.sqlserver;

import java.util.List;

import com.seed.entity.log.*;
import com.seed.mapper.BaseMapper;

public interface PublicMapper extends BaseMapper {
	
	public void ExecSQL(String execsql);
	
	public void SaveTranLog(TranLog log);
	
	public List<TranLog> SearchTranLog(TranLog item);
	
	public List<LoginLog> SearchLoginLog(LoginLog item);
}
