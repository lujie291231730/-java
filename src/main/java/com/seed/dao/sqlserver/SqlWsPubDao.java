package com.seed.dao.sqlserver;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.webservice.entity.*;
import com.seed.enums.*;
import com.seed.mapper.sqlserver.*;
import com.seed.utils.*;

public class SqlWsPubDao {

	// region WsApp Methods

	public static WsApp GetWsApp(SqlSession session, WsApp item) { 
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	
		item.getItem().setGetaction(ActionGetType.row.toString());

		return mapper.GetWsApp(item);
	}

	public static List<WsApp> GetListWsApp(SqlSession session) { 
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	

		return mapper.GetListWsApp();
	}

	public static List<WsApp> SearchWsApp(SqlSession session, WsApp item) { 
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	

		return mapper.SearchWsApp(item);
	}

	public static void SaveWsApp(SqlSession session, WsApp item) {
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	

		mapper.SaveWsApp(item);
	}

	// endregion WsApp Methods

	// region WsVisit Methods

	public static WsVisit GetWsVisit(SqlSession session, WsVisit item) { 
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	
		item.getItem().setGetaction(ActionGetType.row.toString());

		return mapper.GetWsVisit(item);
	}

	public static List<WsVisit> SearchWsVisit(SqlSession session, WsVisit item) { 
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	

		return mapper.SearchWsVisit(item);
	}

	public static void SaveWsVisit(SqlSession session, WsVisit item) {
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	

		mapper.SaveWsVisit(item);
	}

	// endregion WsVisit Methods

	// region WsGuid Methods
	
	public static WsGuid GetWsGuid(SqlSession session, String wsguid) { 
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	

		return mapper.GetWsGuid(wsguid);
	}

	public static void SaveWsGuid(SqlSession session, String wsguid) {
		WsPubMapper mapper = DBUtils.getMapper(session, WsPubMapper.class);	

		mapper.SaveWsGuid(wsguid);
	}
	
	// endregion WsGuid Methods
}
