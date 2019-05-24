package com.seed.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seed.annotation.AuthMethod;
import com.seed.dao.SystemDao;
import com.seed.dao.UserDao;
import com.seed.entity.ReturnList;
import com.seed.entity.ReturnValue;
import com.seed.entity.log.TranLog;
import com.seed.entity.publics.TreePanel;
import com.seed.entity.system.SysMenu;
import com.seed.entity.user.OnlineUser;
import com.seed.entity.user.SysRole;
import com.seed.entity.user.SysRoleDetail;
import com.seed.entity.user.SysUser;
import com.seed.entity.user.SysUserDept;
import com.seed.entity.user.SysUserRole;
import com.seed.enums.ActionOutType;
import com.seed.enums.MenuAuth;
import com.seed.service.UserService;
import com.seed.utils.JsonUtils;
import com.seed.utils.StringUtils;
import com.seed.utils.ToolUtils;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	// region User Methods
	
	@InitBinder("user")
	public void InitBinderSysDept(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("user.");
	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public ReturnValue Login(@ModelAttribute("user") SysUser user) {
		ReturnValue rtv = new ReturnValue();

		try {
			UserService.Login(user, rtv, "");
		} catch (Exception e) {
			rtv.SetValues(true, "系统错误！" + e.getMessage(), "", false);
		}

		return rtv;
	}
	
	@RequestMapping(value = "/loginout", method = RequestMethod.POST)
	@ResponseBody
	public ReturnValue LoginOut() {
		ReturnValue rtv = new ReturnValue();

		try {
			OnlineUser ou = UserService.GetOnlineUser();
			
			if (ou != null)
				UserService.LoginOut(ou, rtv);
		} catch (Exception e) {
			rtv.SetValues(true, "系统错误！" + e.getMessage(), "", false);
		}

		return rtv;
	}

	@RequestMapping(value = "/getonlineuser")
	@ResponseBody
	public OnlineUser GetOnlineUser() {
		return UserService.GetOnlineUser();
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getmenu")
	public ResponseEntity GetMenu() {
		
		try {
			return this.OutReturnList(UserService.GetMenu());
		} catch (Exception e) {
			return this.OutReturnList(new ArrayList<TreePanel>());
		}
	}
	
	@RequestMapping(value = "/getuser")
	@ResponseBody
	@AuthMethod(Menus = "9913", Auth = MenuAuth.Browse, OutType = ActionOutType.Bean)
	public SysUser GetSysUser(@ModelAttribute("user") SysUser user) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			SysUser rtn = UserDao.GetUser(user.getUserid());
			
			rtn.setUserpassword("");
			
			return rtn;
		}
		else {
			return new SysUser();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchuser")
	@AuthMethod(Menus = "9913", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ResponseEntity SearchSysUser(@ModelAttribute("user") SysUser user) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			String search = "";
			String deptid = user.getDeptid();
			String username = StringUtils.Decode(user.getUsername());
			
			if(!ToolUtils.CheckComboValue(deptid))
			 search += StringUtils.GetAndSearch(search) + " a.deptid = '" + deptid + "' ";
			
			if(!ToolUtils.StringIsEmpty(username))
		     search += StringUtils.GetAndSearch(search) + " a.username like '%" + username + "%' ";
			
			this.SetSearch(user.getSearch(), user.getItem(), ou, search);
			
			List<SysUser> lists = UserDao.SearchUser(user);
			
			if (!user.getSearch().isHasexport())
				return this.OutReturnList(lists, user.getSearch().getTotal());
			else {
				try {
          return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(user.getSearch().getColumnsql())), "操作人员");
				} catch (IOException e) {
          e.printStackTrace();
				}
			}
		}
		
		return this.OutReturnList(new ArrayList<SysUser>());
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9913", Auth = MenuAuth.Modify, OutType = ActionOutType.Save)
  public ReturnValue SaveSysUser (@ModelAttribute("user") SysUser user) {
		OnlineUser ou = UserService.GetOnlineUser();

		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9913");
			
			UserService.SaveUser(user, rtv, log, ou);

			return rtv;
		} else
			return this.NotLoginBean();
  }
	
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
  @ResponseBody
  //@AuthMethod(Menus = "9931", Auth = MenuAuth.Deal, OutType = ActionOutType.Save)
	public ReturnValue ChangePwd(@ModelAttribute("user") SysUser user) throws Exception {		
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9931");		
			
			user.getDeal().setUserid(ou.getUser().getUserid());
			UserService.ChangePwd(user, rtv, log);
			
			return rtv;
		} else
			return this.NotLoginBean();
	}
	
	@RequestMapping(value = "/mustchangepwd", method = RequestMethod.POST)
  @ResponseBody
	public ReturnValue MustChangePwd(@ModelAttribute("user") SysUser user) throws Exception {		
		
		TranLog log = new TranLog();
		log.setTranuser(user.getUserid());
		log.setTrandept(user.getUserid());
		log.setTrancode("9999");
		
		user.getDeal().setUserid(user.getUserid());
		
		ReturnValue rtv = new ReturnValue();
		
		UserService.ChangePwd(user, rtv, log);
		
		return rtv;
	}
	
	@RequestMapping(value = "/unlockuser", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9932", Auth = MenuAuth.Deal, OutType = ActionOutType.Save)
	public ReturnValue SetUnLock(@ModelAttribute("user") SysUser user) throws Exception {		
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9932");		
			
			user.getDeal().setUserid(ou.getUser().getUserid());
			UserService.SetUnLock(user, rtv, log);
			
			return rtv;
		} else
			return this.NotLoginBean();
	}
	
	@RequestMapping(value = "/lockuser", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9932", Auth = MenuAuth.Deal, OutType = ActionOutType.Save)
	public ReturnValue SetLock(@ModelAttribute("user") SysUser user) throws Exception {		
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9932");		
			
			user.getDeal().setUserid(ou.getUser().getUserid());
			UserService.SetLock(user, rtv, log);
			
			return rtv;
		} else
			return this.NotLoginBean();
	}
	
	@RequestMapping(value = "/userpwd", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9931", Auth = MenuAuth.Deal, OutType = ActionOutType.Save)
	public ReturnValue SetPasswd(@ModelAttribute("user") SysUser user) throws Exception {		
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9931");		
			
			user.getDeal().setUserid(ou.getUser().getUserid());
			UserService.SetPasswd(user, rtv, log);
			
			return rtv;
		} else
			return this.NotLoginBean();
	}
	
	@RequestMapping(value = "/createmenu", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9933", Auth = MenuAuth.Deal, OutType = ActionOutType.Save)
	public ReturnValue CreateMenuDetail() throws Exception {		
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9933");	
			
			UserService.CreateMenuDetail(rtv, log);
			
			return rtv;
		} else
			return this.NotLoginBean();
	}
	
	// endregion User Methods
	
	// region SysUserDept Methods
	
	@InitBinder("ud")
	public void InitBinderSysUserDept(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("ud.");
	}
	
	@RequestMapping(value = "/getuserdept")
	@ResponseBody
	@AuthMethod(Menus = "9914", Auth = MenuAuth.Browse, OutType = ActionOutType.Bean)
	public SysUserDept GetSysUserDept(@ModelAttribute("ud") SysUserDept ud) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {			
			return UserDao.GetSysUserDept(ud);
		}
		else {
			return new SysUserDept();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchuserdept")
	@AuthMethod(Menus = "9914", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ResponseEntity SearchSysUserDept(@ModelAttribute("ud") SysUserDept ud) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			String search = "";
			String userid = ud.getUserid();
			String deptid = ud.getDeptid();
			String username = StringUtils.Decode(ud.getUsername());
			String deptname = StringUtils.Decode(ud.getDeptname());
						
			if(!ToolUtils.StringIsEmpty(username))
		     search += StringUtils.GetAndSearch(search) + " b.username like '%" + username + "%' ";
			
			if(!ToolUtils.StringIsEmpty(deptname))
		     search += StringUtils.GetAndSearch(search) + " a.deptname like '%" + deptname + "%' ";
			
			if(!ToolUtils.StringIsEmpty(userid))
		     search += StringUtils.GetAndSearch(search) + " a.userid like '%" + userid + "%' ";
			
			if(!ToolUtils.StringIsEmpty(deptid))
		     search += StringUtils.GetAndSearch(search) + " a.deptid like '%" + deptid + "%' ";
			
			this.SetSearch(ud.getSearch(), ud.getItem(), ou, search);
			
			List<SysUserDept> lists = UserDao.SearchSysUserDept(ud);
			
			if (!ud.getSearch().isHasexport())
				return this.OutReturnList(lists, ud.getSearch().getTotal());
			else {
				try {
          return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(ud.getSearch().getColumnsql())), "可操作部门");
				} catch (IOException e) {
          e.printStackTrace();
				}
			}
		}
		
		return this.OutReturnList(new ArrayList<SysUserDept>());
	}
	
	@RequestMapping(value = "/saveuserdept", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9914", Auth = MenuAuth.Modify, OutType = ActionOutType.Save)
  public ReturnValue SaveSysUserDept (@ModelAttribute("ud") SysUserDept ud) {
		OnlineUser ou = UserService.GetOnlineUser();

		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9914");
			
			UserService.SaveSysUserDept(ud, rtv, log);

			return rtv;
		} else
			return this.NotLoginBean();
  }
	
	@RequestMapping(value = "/deleteuserdept", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9914", Auth = MenuAuth.Modify, OutType = ActionOutType.Save)
  public ReturnValue DeleteSysUserDept (@ModelAttribute("ud") SysUserDept ud) {
		OnlineUser ou = UserService.GetOnlineUser();

		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9914");
			
			List<SysUserDept> deletes = JsonUtils.GetArrayFromJson(ud.getDeal().getDeletes(), SysUserDept.class);
			
			UserService.DeleteSysUserDept(deletes, rtv, log);

			return rtv;
		} else
			return this.NotLoginBean();
  }
	
	// endregion SysUserDept Methods
	
	// region SysRole Methods
	
	@InitBinder("role")
	public void InitBinderSysRole(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("role.");
	}
	
	@RequestMapping(value = "/getrole")
	@ResponseBody
	@AuthMethod(Menus = "9921", Auth = MenuAuth.Browse, OutType = ActionOutType.Bean)
	public SysRole GetSysRole(@ModelAttribute("role") SysRole role) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {			
			return UserDao.GetRole(role);
		}
		else {
			return new SysRole();
		}
	}
	
	@RequestMapping(value = "/unsetmenu")
	@ResponseBody
	@AuthMethod(Menus = "9921", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ReturnList GetUnSetMenu(@ModelAttribute("role") SysRole role) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		List<SysMenu> lists = new ArrayList<SysMenu>();
		
		if (ou != null) {			
			lists = UserDao.GetUnSetMenu(role);
		}
		
		return this.OutLists(lists);
	}
	
	@RequestMapping(value = "/setmenu")
	@ResponseBody
	@AuthMethod(Menus = "9921", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ReturnList GetSetMenu(@ModelAttribute("role") SysRole role) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		List<SysMenu> lists = new ArrayList<SysMenu>();
		
		if (ou != null) {			
			lists = UserDao.GetSetMenu(role);
		}
		
		return this.OutLists(lists);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchrole")
	@AuthMethod(Menus = "9921", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ResponseEntity SearchSysRole(@ModelAttribute("role") SysRole role) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			String search = "";
			
			this.SetSearch(role.getSearch(), role.getItem(), ou, search);
			
			List<SysRole> lists = UserDao.SearchRole(role);
			
			if (!role.getSearch().isHasexport())
				return this.OutReturnList(lists, role.getSearch().getTotal());
			else {
				try {
          return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(role.getSearch().getColumnsql())), "可操作部门");
				} catch (IOException e) {
          e.printStackTrace();
				}
			}
		}
		
		return this.OutReturnList(new ArrayList<SysUserDept>());
	}
	
	@RequestMapping(value = "/saverole", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9921", Auth = MenuAuth.Modify, OutType = ActionOutType.Save)
  public ReturnValue SaveSysRole (@ModelAttribute("role") SysRole role) {
		OnlineUser ou = UserService.GetOnlineUser();

		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9921");
			
			UserService.SaveRole(role, new ArrayList<SysRoleDetail>(), rtv, log);

			return rtv;
		} else
			return this.NotLoginBean();
  }
	
	// endregion SysRole Methods

	// region SysUserRole Methods
	
	@InitBinder("ur")
	public void InitBinderSysUserRole(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("ur.");
	}
	
	@RequestMapping(value = "/getuserrole")
	@ResponseBody
	@AuthMethod(Menus = "9922", Auth = MenuAuth.Browse, OutType = ActionOutType.Bean)
	public SysUserRole GetUserRole(@ModelAttribute("ur") SysUserRole ur) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {			
			return UserDao.GetUserRole(ur);
		}
		else {
			return new SysUserRole();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchuserrole")
	@AuthMethod(Menus = "9922", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ResponseEntity SearchUserRole(@ModelAttribute("ur") SysUserRole ur) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			String search = "";
			int roleid = ur.getRoleid();
			
			if (roleid > 0)
				search += StringUtils.GetAndSearch(search) + " a.roleid = " + String.valueOf(roleid) + " ";
			
			this.SetSearch(ur.getSearch(), ur.getItem(), ou, search);
			
			List<SysUserRole> lists = UserDao.SearchUserRole(ur);
			
			if (!ur.getSearch().isHasexport())
				return this.OutReturnList(lists, ur.getSearch().getTotal());
			else {
				try {
          return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(ur.getSearch().getColumnsql())), "角色分配");
				} catch (IOException e) {
          e.printStackTrace();
				}
			}
		}
		
		return this.OutReturnList(new ArrayList<SysUserRole>());
	}
	
	@RequestMapping(value = "/saveuserrole", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9922", Auth = MenuAuth.Modify, OutType = ActionOutType.Save)
  public ReturnValue SaveUserRole (@ModelAttribute("ur") SysUserRole ur) {
		OnlineUser ou = UserService.GetOnlineUser();

		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9922");
			
			UserService.SaveUserRole(ur, rtv, log);

			return rtv;
		} else
			return this.NotLoginBean();
  }
	
	@RequestMapping(value = "/deleteuserrole", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9922", Auth = MenuAuth.Modify, OutType = ActionOutType.Save)
  public ReturnValue DeleteUserRole (@ModelAttribute("ur") SysUserDept ur) {
		OnlineUser ou = UserService.GetOnlineUser();

		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9922");
			
			List<SysUserRole> deletes = JsonUtils.GetArrayFromJson(ur.getDeal().getDeletes(), SysUserRole.class);
			
			UserService.DeleteUserRole(deletes, rtv, log);

			return rtv;
		} else
			return this.NotLoginBean();
  }
	
	// endregion SysUserRole Methods

}
