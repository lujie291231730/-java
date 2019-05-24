package com.zhongji.service;

import org.apache.ibatis.session.SqlSession;

import com.seed.dao.PublicDao;
import com.seed.entity.ReturnValue;
import com.seed.entity.log.TranLog;
import com.seed.utils.Consts;
import com.seed.utils.DBUtils;
import com.seed.utils.ToolUtils;
import com.zhongji.dao.StudDao;
import com.zhongji.entity.stud.StudInfo;

public class StudService {
	public static void SaveStudInfo(StudInfo item, ReturnValue rtv) {
		rtv.setSuccess(false);

		SqlSession session = DBUtils.getFactory();

		try {
			
			StudDao.SaveStudInfo(session, item);


			rtv.setSuccess(true);
			rtv.setMsg(Consts.STR_SAVE_S);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			rtv.setMsg(ToolUtils.GetErrorMessage(e, Consts.STR_SAVE_F));
		} finally {
			session.close();
		}
	}
}
