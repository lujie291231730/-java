package com.seed.dao.sqlserver;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.entity.crm.CrmCust;
import com.seed.enums.ActionGetType;
import com.seed.utils.DBUtils;

public class SqlCrmDao {
	public static void SaveCrmCust(SqlSession session, CrmCust item) {
		com.seed.mapper.sqlserver.CrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.CrmMapper.class);	

		mapper.SaveCrmCust(item);
	}

	public static List<CrmCust> GetListCust(SqlSession session, CrmCust cust ) {
		com.seed.mapper.sqlserver.CrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.CrmMapper.class);
		cust.getItem().setGetaction(ActionGetType.full.toString());
		return mapper.GetListCust(cust);	
	}

	public static CrmCust GetCust(SqlSession session, CrmCust cust) {
		com.seed.mapper.sqlserver.CrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.CrmMapper.class);
		cust.getItem().setGetaction(ActionGetType.row.toString());
		return mapper.GetCust(cust);	
	}

	public static List<CrmCust> SearchCust(SqlSession session, CrmCust cust) {
		com.seed.mapper.sqlserver.CrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.CrmMapper.class);	

		return mapper.SearchCust(cust);
	}

}
