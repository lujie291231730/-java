package com.seed.mapper.sqlserver;

import java.util.List;

import com.seed.entity.org.*;
import com.seed.entity.system.*;
import com.seed.entity.user.*;
import com.seed.mapper.BaseMapper;

public interface UserMapper extends BaseMapper {
	
	// region User Methods
	
	public SysUser GetUser(SysUser item);
	
	public List<SysUser> ListUser();
	
	public void UserPublic(SysUser item);
	
	public void UserLoginLog(SysUser item);
	
	public List<SysUser> SearchUser(SysUser item);
	
	public void SaveUser(SysUser item);
	
	public String GetUserName (SysUser item);
	
	public void SetPasswd (SysUser item);
	
	public void SetUnLock (SysUser item);
	
	public void SetLock (SysUser item);
	
	public void ChangePwd (SysUser item);
	
	public void ChangeTele (SysUser item);
	
	public void ChangeEMail (SysUser item);
	
	public List<SelectUser> SelectAllUser();
	
	// endregion User Methods
	
	// region Role Methods
	
	public List<SysMenu> GetUnSetMenu(SysRole item);
	
	public List<SysMenu> GetSetMenu(SysRole item);
	
	public List<SysRole> GetListRole();
	
	public List<SysRole> GetSetRole(SysUser item);
	
	public List<SysRoleDetail> GetRoleDetail(SysRole item);
	
	public void SaveRole(SysRole item);
	
	public void SaveRoleMenu(SysRoleAuth item);
	
	public void SaveRoleDetail(SysRoleAuth item);
	
	public void SaveSetRole(SysUserRole item);
	
	public void EndSetRole(SysUser item);
	
	public List<SysRole> SearchRole(SysRole item);
	
	public SysRole GetRole(SysRole item);
	
	// endregion Role Methods
	
	// region SysUserRole Methods
	
	public SysUserRole GetUserRole(SysUserRole item);
	
	public List<SysUserRole> SearchUserRole(SysUserRole item);
	
	public void SaveUserRole(SysUserRole item);
	
	// endregion SysUserRole Methods
	
	// region User Menu Methods
	
	public List<SysMenuGroup> GetMenuGroupByUser(SysUser item);
	
	public List<SysMenu> GetMenuByUser(SysUser item);
	
	public List<SysMenu> GetListMenu();
	
	public void SaveMenuDetial(SysMenuDetail item);
	
	public void LastCreateMenuDetail();
	
	// endregion User Menu Methods
	
	// region User Dept Methods
	
	public List<SysDept> GetDeptByUser(SysUser item);
	
	public List<SysDept> GetUserDept(SysUser item);
	
	public void SaveUserDept(SysUser item);
	
	public SysUserDept GetSysUserDept(SysUserDept item);
	
	public List<SysUserDept> SearchSysUserDept(SysUserDept item);
	
	public void SaveSysUserDept(SysUserDept item);
	
	// endregion User Dept Methods
	
	// region WorkGroup Methods
	
	public List<SysWorkGroup> SearchWorkGroup(SysWorkGroup item);
	
	public void SaveWorkGroup(SysWorkGroup item);
	
	public List<SysWorkGroup> GetSetWorkGroup(SysUser item);
	
	public void SaveSetWorkGroup(SysUserWorkGroup item);
	
	// endregion WorkGroup Methods
}
