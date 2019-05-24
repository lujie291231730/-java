package com.seed.service;

import org.apache.ibatis.session.SqlSession;

import com.seed.dao.CrmDao;
import com.seed.entity.ReturnValue;
import com.seed.entity.crm.CrmCust;
import com.seed.utils.Consts;
import com.seed.utils.DBUtils;
import com.seed.utils.ToolUtils;

public class CrmService {
	public static void SaveCrmCust(CrmCust item, ReturnValue rtv) {
		rtv.setSuccess(false);

		SqlSession session = DBUtils.getFactory();

		try {
			
			CrmDao.SaveCrmCust(session, item);

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
