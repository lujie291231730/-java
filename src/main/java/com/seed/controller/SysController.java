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
import com.seed.dao.CrmDao;
import com.seed.dao.SystemDao;
import com.seed.entity.ReturnList;
import com.seed.entity.ReturnValue;
import com.seed.entity.crm.CrmCust;
import com.seed.entity.log.LoginLog;
import com.seed.entity.log.TranLog;
import com.seed.entity.publics.JsonSqlColumn;
import com.seed.entity.publics.PmtBean;
import com.seed.entity.publics.PmtSelect;
import com.seed.entity.std.StdArea;
import com.seed.entity.user.OnlineUser;
import com.seed.enums.ActionOutType;
import com.seed.enums.MenuAuth;
import com.seed.service.SystemService;
import com.seed.service.UserService;
import com.seed.utils.StringUtils;
import com.seed.utils.ToolUtils;

@Controller
@RequestMapping("sys")
public class SysController extends BaseController {
	
	// region Pmt Methods
	
	@InitBinder("pmt")
	public void InitBinderPmtBean(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("pmt.");
	}
	
	// 根据参数标识获得参数值 /sys/listselects.do
	@RequestMapping(value = "/listselects")
	@ResponseBody
	public List<PmtSelect> ListSelects(@ModelAttribute("pmt") PmtBean pmt) {
		List<String> pmts = ToolUtils.GetArrayFromJson(pmt.getId(), String.class);
		
		return SystemService.GetPmtSelects(pmts);
	}
	
	// 根据参数标识获得参数值 /sys/select.do?pmt.id=UserStatus
	@RequestMapping(value = "/select")
	@ResponseBody
	public List<PmtBean> GetSelect(@ModelAttribute("pmt") PmtBean pmt) {
		
		return SystemDao.GetPmtSelect(pmt.getId());
	}
	
	// endregion Pmt Methods
	
	// region Column Methods
	
	// 根据SQLID获得表格字段列表 /sys/sqlcolumn.do
	@RequestMapping(value = "/sqlcolumn")
	@ResponseBody
	public List<JsonSqlColumn> SqlColumn(String sqlid) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			return SystemDao.SqlColumn(sqlid);
		}
		else
			return new ArrayList<JsonSqlColumn>();
	}
	
	// endregion Column Methods

	// region Log Methods
	
	@InitBinder("tranlog")
	public void InitBinderTranLog(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("tranlog.");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchtranlog")
	@AuthMethod(Menus = "9802", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ResponseEntity SearchTranLog(@ModelAttribute("tranlog") TranLog tranlog) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			String search = "";
			String begindate = tranlog.getSearch().getBegindate();
			String enddate = tranlog.getSearch().getEnddate();
			
			if (!ToolUtils.StringIsEmpty(enddate))
				search += StringUtils.GetAndSearch(search) + " a.trandate <= " + StringUtils.GetEndDate(enddate) + " ";
			
			if (!ToolUtils.StringIsEmpty(begindate))
				search += StringUtils.GetAndSearch(search) + " a.trandate >= " + StringUtils.GetBeginDate(begindate) + " ";
			
			this.SetSearch(tranlog.getSearch(), tranlog.getItem(), ou, search);
			
			List<TranLog> lists = SystemDao.SearchTranLog(tranlog);
			
			if (!tranlog.getSearch().isHasexport())
				return this.OutReturnList(lists, tranlog.getSearch().getTotal());
			else {
				try {
          return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(tranlog.getSearch().getColumnsql())), "业务日志");
				} catch (IOException e) {
          e.printStackTrace();
				}
			}
		}
		
		return this.OutReturnList(new ArrayList<TranLog>());
	}
	
	@InitBinder("loginlog")
	public void InitBinderLoginLog(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("loginlog.");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchloginlog")
	@AuthMethod(Menus = "9801", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ResponseEntity SearchLoginLog(@ModelAttribute("loginlog") LoginLog loginlog) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			String search = "";
			String begindate = loginlog.getSearch().getBegindate();
			String enddate = loginlog.getSearch().getEnddate();
			
			if (!ToolUtils.StringIsEmpty(enddate))
				search += StringUtils.GetAndSearch(search) + " a.trandate <= " + StringUtils.GetEndDate(enddate) + " ";
			
			if (!ToolUtils.StringIsEmpty(begindate))
				search += StringUtils.GetAndSearch(search) + " a.trandate >= " + StringUtils.GetBeginDate(begindate) + " ";
			
			this.SetSearch(loginlog.getSearch(), loginlog.getItem(), ou, search);
			
			List<LoginLog> lists = SystemDao.SearchLoginLog(loginlog);
			
			if (!loginlog.getSearch().isHasexport())
				return this.OutReturnList(lists, loginlog.getSearch().getTotal());
			else {
				try {
          return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(loginlog.getSearch().getColumnsql())), "登录日志");
				} catch (IOException e) {
          e.printStackTrace();
				}
			}
		}
		
		return this.OutReturnList(new ArrayList<LoginLog>());
	}
	
	// endregion Log Methods

	// region StdArea Methods
	
	@InitBinder("area")
	public void InitBinderStdArea(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("area.");
	}
	
	@RequestMapping(value = "/getcity")
	@ResponseBody
	public List<PmtBean> GetCity(@ModelAttribute("area") StdArea area) {
		
		return SystemDao.GetCity(area.getAreaid());
	}
	
	@RequestMapping(value = "/getprov")
	@ResponseBody
	public ResponseEntity<ReturnList> GetProv(@ModelAttribute("area") StdArea area) {
		List<StdArea> lists = SystemDao.GetProv(area);
		return this.OutReturnList(lists);
	}
	
	
	@RequestMapping(value = "/getareabycity")
	@ResponseBody
	public List<PmtBean> GetAreaByCity(@ModelAttribute("area") StdArea area) {
		
		return SystemDao.GetAreaByCity(area.getAreaid());
	}
	
	
	@RequestMapping(value = "/getarea")
	@ResponseBody
	@AuthMethod(Menus = "", Auth = MenuAuth.Browse, OutType = ActionOutType.Bean)
	public StdArea GetLibCrop(@ModelAttribute("area") StdArea area) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			return SystemDao.GetStdArea(area);
		}
		else {
			return new StdArea();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searcharea")
	@AuthMethod(Menus = "", Auth = MenuAuth.Browse, OutType = ActionOutType.Array)
	public ResponseEntity SearchLibCrop(@ModelAttribute("area") StdArea area) {
		OnlineUser ou = UserService.GetOnlineUser();
		
		if (ou != null) {
			String search = "";
			String areapid = area.getAreapid();
			String areaname = StringUtils.Decode(area.getAreaname());
			String areaid = area.getAreaid();
			
			if (!StringUtils.StringIsEmpty(areaid))
				search += StringUtils.GetAndSearch(search) + " a.areaid = '" + areaid + "' ";
			
			if (!StringUtils.StringIsEmpty(areaname))
				search += StringUtils.GetAndSearch(search) + " a.areaname like '%" + areaname + "%' ";
			
			if (!StringUtils.StringIsEmpty(areapid))
				search += StringUtils.GetAndSearch(search) + " a.areapid = '" + areapid + "' ";
			
			this.SetSearch(area.getSearch(), area.getItem(), ou, search);
			
			List<StdArea> lists = SystemDao.SearchStdArea(area);
			
			if (!area.getSearch().isHasexport())
				return this.OutReturnList(lists, area.getSearch().getTotal());
			else {
				try {
          return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(area.getSearch().getColumnsql())), "行政区划");
				} catch (IOException e) {
          e.printStackTrace();
				}
			}
		}
		
		return this.OutReturnList(new ArrayList<StdArea>());
	}
	
	@RequestMapping(value = "/savearea", method = RequestMethod.POST)
  @ResponseBody
  @AuthMethod(Menus = "", Auth = MenuAuth.Modify, OutType = ActionOutType.Save)
  public ReturnValue SaveStdArea (@ModelAttribute("area") StdArea area) {
		OnlineUser ou = UserService.GetOnlineUser();

		if (ou != null) {
			ReturnValue rtv = new ReturnValue();
			TranLog log = ToolUtils.InitTranLog(ou, "");
			
			SystemService.SaveStdArea(area, rtv, log);

			return rtv;
		} else
			return this.NotLoginBean();
  }
	
	// endregion StdArea Methods

}
