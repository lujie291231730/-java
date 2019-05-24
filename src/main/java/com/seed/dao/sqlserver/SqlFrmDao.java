package com.seed.dao.sqlserver;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.enums.ActionGetType;
import com.seed.utils.DBUtils;
import com.seed.entity.frm.*;

public class SqlFrmDao {
	
	// region FrmForm Methods

	public static FrmForm GetFrmForm(SqlSession session, FrmForm item) { 
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	
		item.getItem().setGetaction(ActionGetType.row.toString());

		return mapper.GetFrmForm(item);
	}

	public static List<FrmForm> SearchFrmForm(SqlSession session, FrmForm item) { 
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	

		return mapper.SearchFrmForm(item);
	}

	public static void SaveFrmForm(SqlSession session, FrmForm item) {
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	

		mapper.SaveFrmForm(item);
	}

	// endregion FrmForm Methods
	
	// region FrmFormDetail Methods

	public static FrmFormDetail GetFrmFormDetail(SqlSession session, FrmFormDetail item) { 
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	
		item.getItem().setGetaction(ActionGetType.row.toString());

		return mapper.GetFrmFormDetail(item);
	}

	public static List<FrmFormDetail> GetFrmFormDetailByForm(SqlSession session, FrmForm item) { 
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	

		return mapper.GetFrmFormDetailByForm(item);
	}

	public static void SaveFrmFormDetail(SqlSession session, FrmFormDetail item) {
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	

		mapper.SaveFrmFormDetail(item);
	}

	// endregion FrmFormDetail Methods
	
	// region FrmFormType Methods

	public static FrmFormType GetFrmFormType(SqlSession session, FrmFormType item) { 
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	
		item.getItem().setGetaction(ActionGetType.row.toString());

		return mapper.GetFrmFormType(item);
	}

	public static List<FrmFormType> SearchFrmFormType(SqlSession session, FrmFormType item) { 
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	

		return mapper.SearchFrmFormType(item);
	}

	public static void SaveFrmFormType(SqlSession session, FrmFormType item) {
		com.seed.mapper.sqlserver.FrmMapper mapper = DBUtils.getMapper(session, com.seed.mapper.sqlserver.FrmMapper.class);	

		mapper.SaveFrmFormType(item);
	}

	// endregion FrmFormType Methods

}
