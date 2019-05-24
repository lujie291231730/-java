package com.seed.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.dao.sqlserver.SqlCrmDao;
import com.seed.entity.crm.CrmCust;
import com.seed.utils.DBUtils;
import com.seed.utils.ToolUtils;

public class CrmDao {
	public static void SaveCrmCust(SqlSession session, CrmCust item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlCrmDao.SaveCrmCust(session, item);
				break;
		}
	}

	public static List<CrmCust> GetLsitCust(CrmCust cust) {
		SqlSession session = DBUtils.getFactory();

		try {
			return SqlCrmDao.GetListCust(session , cust);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CrmCust>();
		} finally {
			session.close();
		}
	}

	public static CrmCust GetCust(CrmCust cust) {
		SqlSession session = DBUtils.getFactory();

		try {
			return SqlCrmDao.GetCust(session , cust);
		} catch (Exception e) {
			e.printStackTrace();
			return new CrmCust();
		} finally {
			session.close();
		}
	}

	public static List<CrmCust> SearchCust(CrmCust cust) {
		SqlSession session = DBUtils.getFactory();

		try {
			return SqlCrmDao.SearchCust(session, cust);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<CrmCust>();
		} finally {
			session.close();
		}
	}

}
