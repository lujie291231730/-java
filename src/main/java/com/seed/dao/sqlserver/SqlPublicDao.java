package com.seed.dao.sqlserver;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.entity.log.*;
import com.seed.mapper.sqlserver.*;
import com.seed.utils.*;

public class SqlPublicDao {

	// region Log Methods
	
	public static void SaveTranLog(SqlSession session, TranLog log) {
		PublicMapper mapper = DBUtils.getMapper(session, PublicMapper.class);

		mapper.SaveTranLog(log);
	}
	
	public static List<TranLog> SearchTranLog(SqlSession session, TranLog item) {
		PublicMapper mapper = DBUtils.getMapper(session, PublicMapper.class);
			
		return mapper.SearchTranLog(item);
	}
	
	public static List<LoginLog> SearchLoginLog(SqlSession session, LoginLog item) {
		PublicMapper mapper = DBUtils.getMapper(session, PublicMapper.class);
		
		return mapper.SearchLoginLog(item);
	}
	
	// endregion TranLog Methods

	// region ExecSql Methods
	
	public static void ExecSql(SqlSession session, List<String> sqls) {
		PublicMapper mapper = DBUtils.getMapper(session, PublicMapper.class);

		for (String execsql : sqls) {
			mapper.ExecSQL(execsql);
		}	
	}
	
	public static void ExecSql(SqlSession session, String execsql) {
		PublicMapper mapper = DBUtils.getMapper(session, PublicMapper.class);

		mapper.ExecSQL(execsql);
	}
	
	// endregion ExecSql Methods
}
