package com.seed.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.dao.sqlserver.*;
import com.seed.entity.ReturnValue;
import com.seed.entity.log.*;
import com.seed.utils.*;

public class PublicDao {
	
	public static void ExecSql(SqlSession session, String execsql) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				break;
				
			default:
				SqlSystemDao.ExecSql(session, execsql);
				break;
		}
	}
	
	public static void ExecSql(String sql, ReturnValue rtv) {
		SqlSession session = DBUtils.getFactory();
		
		rtv.setSuccess(false);
		
		try {
			
			ExecSql(session, sql);
			
			rtv.setSuccess(true);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.rollback();
			rtv.setMsg(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public static void ExecSql(SqlSession session, List<String> sqls) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				break;
				
			default:
				SqlSystemDao.ExecSql(session, sqls);
				break;
		}
	}
	
	public static void ExecSql(List<String> sqls, ReturnValue rtv) {
		SqlSession session = DBUtils.getFactory();
		
		rtv.setSuccess(false);
		
		try {
			
			ExecSql(session, sqls);
			
			rtv.setSuccess(true);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.rollback();
			rtv.setMsg(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	public static void SaveTranLog(SqlSession session, TranLog log) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				break;
				
			default:
				SqlSystemDao.SaveTranLog(session, log);
				break;
		}
	}
	
}
