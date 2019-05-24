package com.zhongji.mapper.sqlserver;

import java.util.List;

import com.seed.mapper.BaseMapper;
import com.zhongji.entity.stud.StudInfo;

public interface StudMapper extends BaseMapper{
	
	public void SaveStudInfo (StudInfo item);

	public List<StudInfo> SearchStud(StudInfo stud);

	public StudInfo GetStud(StudInfo stud);

	public List<StudInfo> GetClasses(StudInfo stud);

	public List<StudInfo> GetGrade(StudInfo stud);
}
