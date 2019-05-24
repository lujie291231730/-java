package com.seed.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.utils.DBUtils;
import com.seed.utils.ToolUtils;
import com.seed.dao.sqlserver.SqlFrmDao;
import com.seed.entity.frm.*;

public class FrmDao {
	
	// region FrmForm Methods

	public static FrmForm GetFrmForm(FrmForm item) {
		SqlSession session = DBUtils.getFactory();

		try {
			return GetFrmForm(session, item);
		} catch (Exception e) {
			return new FrmForm();
		}
		finally {
			session.close();
		}
	}

	public static FrmForm GetFrmForm(SqlSession session, FrmForm item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new FrmForm();
			default:
				return SqlFrmDao.GetFrmForm(session, item);
		}
	}

	public static List<FrmForm> SearchFrmForm(FrmForm item) {
		SqlSession session = DBUtils.getFactory();

		try {
			switch(ToolUtils.GetDataBaseType()) {
				case Oracle10:
					return new ArrayList<FrmForm>();
				default:
					return SqlFrmDao.SearchFrmForm(session, item);

			}
		} catch (Exception e) {
			return new ArrayList<FrmForm>();
		}
		finally {
			session.close();
		}
	}

	public static void SaveFrmForm(SqlSession session, FrmForm item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlFrmDao.SaveFrmForm(session, item);
				break;
		}
	}

	// endregion FrmForm Methods
	
	// region FrmFormDetail Methods

	public static FrmFormDetail GetFrmFormDetail(FrmFormDetail item) {
		SqlSession session = DBUtils.getFactory();

		try {
			return GetFrmFormDetail(session, item);
		} catch (Exception e) {
			return new FrmFormDetail();
		}
		finally {
			session.close();
		}
	}

	public static FrmFormDetail GetFrmFormDetail(SqlSession session, FrmFormDetail item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new FrmFormDetail();
			default:
				return SqlFrmDao.GetFrmFormDetail(session, item);
		}
	}

	public static List<FrmFormDetail> GetFrmFormDetailByForm(FrmForm item) {
		SqlSession session = DBUtils.getFactory();

		try {
			return GetFrmFormDetailByForm(session, item);
		} catch (Exception e) {
			return new ArrayList<FrmFormDetail>();
		}
		finally {
			session.close();
		}
	}

	public static List<FrmFormDetail> GetFrmFormDetailByForm(SqlSession session, FrmForm item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<FrmFormDetail>();
			default:
				return SqlFrmDao.GetFrmFormDetailByForm(session, item);
		}
	}

	public static void SaveFrmFormDetail(SqlSession session, FrmFormDetail item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlFrmDao.SaveFrmFormDetail(session, item);
				break;
		}
	}

	// endregion FrmFormDetail Methods
	
	// region FrmFormType Methods

	public static FrmFormType GetFrmFormType(FrmFormType item) {
		SqlSession session = DBUtils.getFactory();

		try {
			return GetFrmFormType(session, item);
		} catch (Exception e) {
			return new FrmFormType();
		}
		finally {
			session.close();
		}
	}

	public static FrmFormType GetFrmFormType(SqlSession session, FrmFormType item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new FrmFormType();
			default:
				return SqlFrmDao.GetFrmFormType(session, item);
		}
	}

	public static List<FrmFormType> SearchFrmFormType(FrmFormType item) {
		SqlSession session = DBUtils.getFactory();

		try {
			switch(ToolUtils.GetDataBaseType()) {
				case Oracle10:
					return new ArrayList<FrmFormType>();
				default:
					return SqlFrmDao.SearchFrmFormType(session, item);

			}
		} catch (Exception e) {
			return new ArrayList<FrmFormType>();
		}
		finally {
			session.close();
		}
	}

	public static void SaveFrmFormType(SqlSession session, FrmFormType item) {
		switch(ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlFrmDao.SaveFrmFormType(session, item);
				break;
		}
	}

	// endregion FrmFormType Methods

}
