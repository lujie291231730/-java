package com.zhongji.dao.sqlserver;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.seed.enums.ActionGetType;
import com.seed.utils.DBUtils;
import com.zhongji.entity.stud.StudInfo;

public class SqlStudDao {
	
	public static void SaveStudInfo(SqlSession session, StudInfo item) {
		com.zhongji.mapper.sqlserver.StudMapper mapper = DBUtils.getMapper(session, com.zhongji.mapper.sqlserver.StudMapper.class);	

		mapper.SaveStudInfo(item);
	}

	public static List<StudInfo> SearchStud(SqlSession session, StudInfo stud) {
		com.zhongji.mapper.sqlserver.StudMapper mapper = DBUtils.getMapper(session, com.zhongji.mapper.sqlserver.StudMapper.class);	

		return mapper.SearchStud(stud);
	}

	public static StudInfo GetStud(SqlSession session, StudInfo stud) {
		com.zhongji.mapper.sqlserver.StudMapper mapper = DBUtils.getMapper(session, com.zhongji.mapper.sqlserver.StudMapper.class);
		stud.getItem().setGetaction(ActionGetType.row.toString());
		return mapper.GetStud(stud);
	}

	public static List<StudInfo> GetClasses(SqlSession session, StudInfo stud) {
		com.zhongji.mapper.sqlserver.StudMapper mapper = DBUtils.getMapper(session, com.zhongji.mapper.sqlserver.StudMapper.class);
		return mapper.GetClasses(stud);
	}

	public static List<StudInfo> GetGrade(SqlSession session, StudInfo stud) {
		com.zhongji.mapper.sqlserver.StudMapper mapper = DBUtils.getMapper(session, com.zhongji.mapper.sqlserver.StudMapper.class);
		return mapper.GetGrade(stud);
	}
}
