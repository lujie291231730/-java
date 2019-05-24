package com.seed.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.seed.dao.sqlserver.*;
import com.seed.entity.system.*;
import com.seed.entity.user.*;
import com.seed.entity.org.*;
import com.seed.utils.*;

public class UserDao {

	// region User Method

	public static SysUser GetUser(String userid) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetUser(session, userid);
		}
		catch (Exception e) {
			return new SysUser();
		}
		finally {
			session.close();
		}
	}
	
	public static SysUser GetUser(SqlSession session, String userid) {
		SysUser item = new SysUser();
		item.setUserid(userid);
		
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new SysUser();

			default:
				return SqlUserDao.GetUser(session, item);
		}
	}
	
	public static List<SysUser> ListUser() {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return ListUser(session);
		}
		catch (Exception e) {
			return new ArrayList<SysUser>();
		}
		finally {
			session.close();
		}
	}

	public static List<SysUser> ListUser(SqlSession session) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysUser>();

			default:
				return SqlUserDao.ListUser(session);
		}
	}

	public static OnlineUser GetOnlineUser(String userid) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetOnlineUser(session, userid);
		}
		catch (Exception e) {
			return new OnlineUser();
		}
		finally {
			session.close();
		}
	}
	
	public static OnlineUser GetOnlineUser(SqlSession session, String userid) {
		OnlineUser ou = new OnlineUser();

		ou.setUser(GetUser(session, userid));

		if (!ToolUtils.StringIsEmpty(ou.getUser().getDeptid()))
			ou.setDept(OrgDao.GetDept(session, ou.getUser().getDeptid()));

		return ou;
	}

	public static void UserLock(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.UserLock(session, item);
				break;
		}
	}

	public static void UserError(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.UserError(session, item);
				break;
		}
	}

	public static void UserLoginLog(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.UserLoginLog(session, item);
				break;
		}
	}

	public static void UserLoginOutLog(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.UserLoginOutLog(session, item);
				break;
		}
	}

	public static void ChangePwd(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.ChangePwd(session, item);
				break;
		}
	}

	public static String GetUserName(SysUser item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetUserName(session, item);
		}
		catch (Exception e) {
			return "";
		}
		finally {
			session.close();
		}
	}
	
	public static String GetUserName(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return "";

			default:
				return SqlUserDao.GetUserName(session, item);
		}
	}

	public static void SetUnLock(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SetUnLock(session, item);
				break;
		}
	}
	
	public static void SetLock(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SetLock(session, item);
				break;
		}
	}

	public static void SetPasswd(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SetPasswd(session, item);
				break;
		}
	}
	
	public static List<SysUser> SearchUser(SysUser item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return SearchUser(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysUser>();
		}
		finally {
			session.close();
		}
	}

	public static List<SysUser> SearchUser(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysUser>();

			default:
				return SqlUserDao.SearchUser(session, item);
		}
	}

	public static void SaveUser(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveUser(session, item);
				break;
		}
	}
	
	public static List<SelectUser> SelectAllUser() {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return SelectAllUser(session);
		}
		catch (Exception e) {
			return new ArrayList<SelectUser>();
		}
		finally {
			session.close();
		}
	}

	public static List<SelectUser> SelectAllUser(SqlSession session) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SelectUser>();

			default:
				return SqlUserDao.SelectAllUser(session);
		}
	}
	
	public static void ChangeTele(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.ChangeTele(session, item);
				break;
		}
	}
	
	public static void ChangeEMail(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.ChangeEMail(session, item);
				break;
		}
	}

	// endregion User Methods

	// region Role Methods

	public static SysRole GetRole(SysRole item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetRole(session, item);
		}
		catch (Exception e) {
			return new SysRole();
		}
		finally {
			session.close();
		}
	}
	
	public static SysRole GetRole(SqlSession session, SysRole item) {
		
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new SysRole();

			default:
				return SqlUserDao.GetRole(session, item);
		}
	}
	
	public static List<SysMenu> GetUnSetMenu(SysRole item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetUnSetMenu(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysMenu>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysMenu> GetUnSetMenu(SqlSession session, SysRole item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysMenu>();

			default:
				return SqlUserDao.GetUnSetMenu(session, item);
		}
	}
	
	public static List<SysMenu> GetSetMenu(SysRole item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetSetMenu(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysMenu>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysMenu> GetSetMenu(SqlSession session, SysRole item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysMenu>();

			default:
				return SqlUserDao.GetSetMenu(session, item);
		}
	}
	
	public static List<SysRole> SearchRole(SysRole item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return SearchRole(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysRole>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysRole> SearchRole(SqlSession session, SysRole item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysRole>();

			default:
				return SqlUserDao.SearchRole(session, item);
		}
	}
	
	public static List<SysRole> GetListRole() {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetListRole(session);
		}
		catch (Exception e) {
			return new ArrayList<SysRole>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysRole> GetListRole(SqlSession session) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysRole>();

			default:
				return SqlUserDao.GetListRole(session);
		}
	}

	public static List<SysRole> GetSetRole(SysUser item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetSetRole(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysRole>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysRole> GetSetRole(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysRole>();

			default:
				return SqlUserDao.GetSetRole(session, item);
		}
	}

	public static List<SysRoleDetail> GetRoleDetail(SysRole item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetRoleDetail(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysRoleDetail>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysRoleDetail> GetRoleDetail(SqlSession session, SysRole item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysRoleDetail>();

			default:
				return SqlUserDao.GetRoleDetail(session, item);
		}
	}

	public static void SaveRole(SqlSession session, SysRole role, List<SysRoleDetail> roledetail) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveRole(session, role, roledetail);
				break;
		}
	}

	public static void SaveSetRole(SqlSession session, SysUser user, List<SysRole> setroles) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveSetRole(session, user, setroles);
				break;
		}
	}
	
	public static void EndSetRole(SqlSession session, SysUser user) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.EndSetRole(session, user);
				break;
		}
	}
	
	public static void SaveRoleMenu(SqlSession session, SysRoleAuth item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveRoleMenu(session, item);
				break;
		}
	}

	// endregion Role Methods

	// region SysUserRole Methods
	
	public static SysUserRole GetUserRole(SysUserRole item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetUserRole(session, item);
		}
		catch (Exception e) {
			return new SysUserRole();
		}
		finally {
			session.close();
		}
	}
	
	public static SysUserRole GetUserRole(SqlSession session, SysUserRole item) {
		
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new SysUserRole();

			default:
				return SqlUserDao.GetUserRole(session, item);
		}
	}
	
	public static List<SysUserRole> SearchUserRole(SysUserRole item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return SearchUserRole(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysUserRole>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysUserRole> SearchUserRole(SqlSession session, SysUserRole item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysUserRole>();

			default:
				return SqlUserDao.SearchUserRole(session, item);
		}
	}
	
	public static void SaveUserRole(SqlSession session, SysUserRole item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveUserRole(session, item);
				break;
		}
	}
	
	// endregion SysUserRole Methods
	
	// region User Menu Methods

	public static List<SysMenuGroup> GetMenuGroupByUser(String userid) {
		SqlSession session = DBUtils.getFactory();

		try {
			SysUser item = new SysUser();
			item.setUserid(userid);
			
			return GetMenuGroupByUser(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysMenuGroup>();
		}
		finally {
			session.close();
		}
	}

	public static List<SysMenuGroup> GetMenuGroupByUser(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysMenuGroup>();

			default:
				return SqlUserDao.GetMenuGroupByUser(session, item);
		}
	}

	public static List<SysMenu> GetMenuByUser(String userid) {
		SqlSession session = DBUtils.getFactory();

		try {
			SysUser item = new SysUser();
			item.setUserid(userid);
			
			return GetMenuByUser(session, item);
		} 
		catch (Exception e) {
			return new ArrayList<SysMenu>();
		}
		finally {
			session.close();
		}
	}

	public static List<SysMenu> GetMenuByUser(SqlSession session, SysUser item) {

		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysMenu>();

			default:
				return SqlUserDao.GetMenuByUser(session, item);
		}
	}
	
	public static List<SysMenu> GetListMenu(SqlSession session) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysMenu>();

			default:
				return SqlUserDao.GetListMenu(session);
		}
	}
	
	public static void SaveMenuDetial(SqlSession session, SysMenuDetail item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveMenuDetial(session, item);
				break;
		}
	}
	
	public static void LastCreateMenuDetail(SqlSession session) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.LastCreateMenuDetail(session);
				break;
		}
	}

	// endregion User Menu Mehtods

	// region User Dept Methods

	public static SysUserDept GetSysUserDept(SysUserDept item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetSysUserDept(session, item);
		}
		catch (Exception e) {
			return new SysUserDept();
		}
		finally {
			session.close();
		}
	}
	
	public static SysUserDept GetSysUserDept(SqlSession session, SysUserDept item) {		
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new SysUserDept();

			default:
				return SqlUserDao.GetSysUserDept(session, item);
		}
	}
	
	public static void SaveSysUserDept(SqlSession session, SysUserDept item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveSysUserDept(session, item);
				break;
		}
	}
	
	public static List<SysUserDept> SearchSysUserDept(SysUserDept item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return SearchSysUserDept(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysUserDept>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysUserDept> SearchSysUserDept(SqlSession session, SysUserDept item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysUserDept>();

			default:
				return SqlUserDao.SearchSysUserDept(session, item);
		}
	}
	
	public static List<SysDept> GetUserDept(SysUser item) {
		SqlSession session = DBUtils.getFactory();

		try {			
			return GetUserDept(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysDept>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysDept> GetUserDept(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysDept>();

			default:
				return SqlUserDao.GetUserDept(session, item);
		}
	}
	
	public static void SaveUserDept(SqlSession session, SysUser item, List<SysDept> detps) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveUserDept(session, item, detps);
				break;
		}
	}
	
	// endregion User Dept Methods

	// region WorkGroup Methods
	
	public static List<SysWorkGroup> SearchWorkGroup(SysWorkGroup item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return SearchWorkGroup(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysWorkGroup>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysWorkGroup> SearchWorkGroup(SqlSession session, SysWorkGroup item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysWorkGroup>();

			default:
				return SqlUserDao.SearchWorkGroup(session, item);
		}
	}
	
	public static List<SysWorkGroup> GetSetWorkGroup(SysUser item) {
		SqlSession session = DBUtils.getFactory();		
		
		try {
			return GetSetWorkGroup(session, item);
		}
		catch (Exception e) {
			return new ArrayList<SysWorkGroup>();
		}
		finally {
			session.close();
		}
	}
	
	public static List<SysWorkGroup> GetSetWorkGroup(SqlSession session, SysUser item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				return new ArrayList<SysWorkGroup>();

			default:
				return SqlUserDao.GetSetWorkGroup(session, item);
		}
	}
	
	public static void SaveWorkGroup(SqlSession session, SysWorkGroup item) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveWorkGroup(session, item);
				break;
		}
	}
	
	public static void SaveSetWorkGroup(SqlSession session, SysUser user, List<SysWorkGroup> sets) {
		switch (ToolUtils.GetDataBaseType()) {
			case Oracle10:
				break;

			default:
				SqlUserDao.SaveSetWorkGroup(session, user, sets);
				break;
		}
	}
	
	// endregion WorkGroup Methods
}
