package com.seed.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.dao.sqlserver.*;
import com.seed.webservice.entity.*;
import com.seed.utils.*;

public class WsPubDao {

	// region WsApp Methods
	
	public static WsApp GetWsApp(String appid) {
		WsApp item = new WsApp();
		
		item.setAppid(appid);
		
		return GetWsApp(item);
	}

	public static WsApp GetWsApp(WsApp item) {
		SqlSession session = DBUtils.getFactory();

		try {
			return GetWsApp(session, item);
		} catch (Exception e) {
			return new WsApp();
		}
		finally {
			session.close();
		}
	}

	public static WsApp GetWsApp(SqlSession session, WsApp item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				return new WsApp();
			default:
				return SqlWsPubDao.GetWsApp(session, item);
		}
	}

	public static List<WsApp> GetListWsApp() {
		SqlSession session = DBUtils.getFactory();

		try {
			return GetListWsApp(session);
		} catch (Exception e) {
			return new ArrayList<WsApp>();
		}
		finally {
			session.close();
		}
	}

	public static List<WsApp> GetListWsApp(SqlSession session) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				return new ArrayList<WsApp>();
			default:
				return SqlWsPubDao.GetListWsApp(session);
		}
	}

	public static List<WsApp> SearchWsApp(WsApp item) {
		SqlSession session = DBUtils.getFactory();

		try {
			switch(ToolUtils.GetDataBaseType()) {
				case Oracle10:
				case MYSQL:
					return new ArrayList<WsApp>();
				default:
					return SqlWsPubDao.SearchWsApp(session, item);

			}
		} catch (Exception e) {
			return new ArrayList<WsApp>();
		}
		finally {
			session.close();
		}
	}

	public static void SaveWsApp(SqlSession session, WsApp item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				break;

			default:
				SqlWsPubDao.SaveWsApp(session, item);
				break;
		}
	}

	// endregion WsApp Methods

	// region WsVisit Methods

	public static WsVisit GetWsVisit(WsVisit item) {
		SqlSession session = DBUtils.getFactory();

		try {
			return GetWsVisit(session, item);
		} catch (Exception e) {
			return new WsVisit();
		}
		finally {
			session.close();
		}
	}

	public static WsVisit GetWsVisit(SqlSession session, WsVisit item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				return new WsVisit();
			default:
				return SqlWsPubDao.GetWsVisit(session, item);
		}
	}

	public static List<WsVisit> SearchWsVisit(WsVisit item) {
		SqlSession session = DBUtils.getFactory();

		try {
			switch(ToolUtils.GetDataBaseType()) {
				case Oracle10:
				case MYSQL:
					return new ArrayList<WsVisit>();
				default:
					return SqlWsPubDao.SearchWsVisit(session, item);

			}
		} catch (Exception e) {
			return new ArrayList<WsVisit>();
		}
		finally {
			session.close();
		}
	}

	public static void SaveWsVisit(SqlSession session, WsVisit item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				break;

			default:
				SqlWsPubDao.SaveWsVisit(session, item);
				break;
		}
	}

	// endregion WsVisit Methods

	// region WsGuid Methods
	
	public static WsGuid GetWsGuid(String wsguid) {
		SqlSession session = DBUtils.getFactory();

		try {
			return GetWsGuid(session, wsguid);
		} catch (Exception e) {
			return new WsGuid();
		}
		finally {
			session.close();
		}
	}

	public static WsGuid GetWsGuid(SqlSession session, String wsguid) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				return new WsGuid();
				
			default:
				return SqlWsPubDao.GetWsGuid(session, wsguid);
		}
	}

	public static void SaveWsGuid(SqlSession session, String wsguid) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
			case MYSQL:
				break;

			default:
				SqlWsPubDao.SaveWsGuid(session, wsguid);
				break;
		}
	}
	
	// endregion WsGuid Methods
}
