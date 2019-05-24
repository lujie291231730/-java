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
import com.seed.dao.OrgDao;
import com.seed.dao.SystemDao;
import com.seed.entity.ReturnValue;
import com.seed.entity.log.TranLog;
import com.seed.entity.org.SysDept;
import com.seed.entity.user.OnlineUser;
import com.seed.enums.ActionOutType;
import com.seed.enums.MenuAuth;
import com.seed.service.OrgService;
import com.seed.service.UserService;
import com.seed.utils.ToolUtils;

@Controller
@RequestMapping("org")
public class OrgController extends BaseController {

	// region SysDept Methods
	
	@InitBinder("dept")
	public void InitBinderGovSealApply(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("dept.");
	}
	
	@RequestMapping(value = "/getdept")
	@ResponseBody
	@AuthMethod(Menus = "9912", Auth = MenuAuth.Browse, OutType = ActionOutType.Bean)
	public SysDept GetSysDept(@ModelAttribute("dept") SysDept dept) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			return OrgDao.GetDept(dept.getDeptid());
		}
		else {
			return new SysDept();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchdept")
	@AuthMethod(Menus = "9912", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ResponseEntity SearchSysDept(@ModelAttribute("dept") SysDept dept) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			String search = "";
			
			this.SetSearch(dept.getSearch(), dept.getItem(), ou, search);
			
			List<SysDept> lists = OrgDao.SearchDept(dept);
			
			if (!dept.getSearch().isHasexport())
				return this.OutReturnList(lists, dept.getSearch().getTotal());
			else {
				try {
          return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(dept.getSearch().getColumnsql())), "部门");
				} catch (IOException e) {
          e.printStackTrace();
				}
			}
		}
		
		return this.OutReturnList(new ArrayList<SysDept>());
	}
	
	@RequestMapping(value = "/savedept", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "9912", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
  public ReturnValue SaveSysDept (@ModelAttribute("dept") SysDept dept) {
		OnlineUser ou = UserService.GetOnlineUser();

		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "9912");
			
			OrgService.SaveDept(dept, rtv, log);

			return rtv;
		} else
			return this.NotLoginBean();
  }

	// endregion SysDept Methods
}
