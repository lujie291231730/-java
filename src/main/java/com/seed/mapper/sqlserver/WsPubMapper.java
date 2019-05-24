package com.seed.mapper.sqlserver;

import java.util.List;

import com.seed.webservice.entity.*;

import com.seed.mapper.BaseMapper;

public interface WsPubMapper extends BaseMapper {

	// region WsApp Methods

	public WsApp GetWsApp(WsApp item);

	public List<WsApp> GetListWsApp();

	public List<WsApp> SearchWsApp(WsApp item);

	public void SaveWsApp(WsApp item);

	// endregion WsApp Methods
	
	// region WsVisit Methods

	public WsVisit GetWsVisit(WsVisit item);

	public List<WsVisit> SearchWsVisit(WsVisit item);

	public void SaveWsVisit(WsVisit item);

	// endregion WsVisit Methods

	// region WsGuid Methods
	
	public WsGuid GetWsGuid(String wsguid);

	public void SaveWsGuid(String wsguid);
	
	// endregion WsGuid Methods
}
