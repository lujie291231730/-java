package com.zhongji.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.dao.sqlserver.SqlCrmDao;
import com.seed.entity.crm.CrmCust;
import com.seed.utils.DBUtils;
import com.seed.utils.ToolUtils;
import com.zhongji.dao.sqlserver.SqlStudDao;
import com.zhongji.entity.stud.StudInfo;

public class StudDao {
	public static void SaveStudInfo(SqlSession session, StudInfo item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlStudDao.SaveStudInfo(session, item);
				break;
		}
	}

	public static List<StudInfo> SearchStud(StudInfo stud) {
		SqlSession session = DBUtils.getFactory();

		try {
			return SqlStudDao.SearchStud(session, stud);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<StudInfo>();
		} finally {
			session.close();
		}
	}

	public static StudInfo GetStud(StudInfo stud) {
		SqlSession session = DBUtils.getFactory();

		try {
			return SqlStudDao.GetStud(session , stud);
		} catch (Exception e) {
			e.printStackTrace();
			return new StudInfo();
		} finally {
			session.close();
		}
	}

	public static List<StudInfo> GetClasses(StudInfo stud) {
		SqlSession session = DBUtils.getFactory();

		try {
			return SqlStudDao.GetClasses(session , stud);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<StudInfo>();
		} finally {
			session.close();
		}
	}

	public static List<StudInfo> GetGrade(StudInfo stud) {
		SqlSession session = DBUtils.getFactory();

		try {
			return SqlStudDao.GetGrade(session , stud);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<StudInfo>();
		} finally {
			session.close();
		}
	}
}
