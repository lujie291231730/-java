package com.zhongji.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seed.controller.BaseController;
import com.seed.dao.CrmDao;
import com.seed.dao.SystemDao;
import com.seed.entity.ReturnList;
import com.seed.entity.ReturnValue;
import com.seed.entity.crm.CrmCust;
import com.seed.entity.log.TranLog;
import com.seed.entity.user.OnlineUser;
import com.seed.enums.DataAction;
import com.seed.service.CrmService;
import com.seed.service.UserService;
import com.seed.utils.StringUtils;
import com.seed.utils.ToolUtils;
import com.zhongji.dao.StudDao;
import com.zhongji.entity.stud.StudInfo;
import com.zhongji.service.StudService;

@Controller
@RequestMapping("stud")
public class StudController extends BaseController{
	@InitBinder("stud")
	public void InitBinderGovSealApply(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("stud.");
	}
	
	@RequestMapping(value = "/getstud")
	@ResponseBody
	public ResponseEntity<StudInfo> GetStud (@ModelAttribute("stud") StudInfo stud){
		StudInfo rtn = new StudInfo();
		rtn = StudDao.GetStud(stud);
		return this.OutReturnBean(rtn);
	}
	
	@RequestMapping(value = "/getclasses")
	@ResponseBody
	public ResponseEntity<ReturnList> GetClasses (@ModelAttribute("stud") StudInfo stud){
		List<StudInfo> lists = StudDao.GetClasses(stud);
		return this.OutReturnList(lists , stud.getSearch().getTotal());
	}
	
	@RequestMapping(value = "/getgrade")
	@ResponseBody
	public ResponseEntity<ReturnList> GetGrade (@ModelAttribute("stud") StudInfo stud){
		List<StudInfo> lists = StudDao.GetGrade(stud);
		return this.OutReturnList(lists , stud.getSearch().getTotal());
	}
	
	@RequestMapping(value = "/savestudinfo", method = RequestMethod.POST)
	  @ResponseBody
	  public ReturnValue SaveStudInfo (@ModelAttribute("stud") StudInfo stud) {
			OnlineUser ou = UserService.GetOnlineUser();
	
			if (ou != null) {
				ReturnValue rtv = new ReturnValue();
				stud.setCreatedate(new Date());
				StudService.SaveStudInfo(stud, rtv);	
				return rtv;
			} else
			return this.NotLoginBean();			
	  }
	
	@RequestMapping(value = "/deletetestudinfo")
	@ResponseBody
	public ReturnValue DeleteCrmCust(@ModelAttribute("stud") StudInfo stud) {
		ReturnValue rtn = new ReturnValue();
		
		stud.getDeal().setAction(DataAction.Delete.getAction());

		StudService.SaveStudInfo(stud, rtn);
		
		return rtn;
	}
	
	@RequestMapping(value = "/searchstud")
	@ResponseBody
	public ResponseEntity SearchStud (@ModelAttribute("stud") StudInfo stud){
		String search = "";
		if (!ToolUtils.StringIsEmpty(stud.getName())) {
			search += StringUtils.GetAndSearch(search) + " a.name like '%" + stud.getName() + "%' ";
		}
		if (!StringUtils.StringIsEmpty(stud.getGrade())) {
			search += (StringUtils.GetAndSearch(search) + " a.grade = '" + stud.getGrade() + "' ");
		}
				
		this.SetSearch(stud.getSearch(), stud.getItem(), new OnlineUser(), search);
		List<StudInfo> lists =  StudDao.SearchStud(stud);
		if (!stud.getSearch().isHasexport())
			return this.OutReturnList(lists, stud.getSearch().getTotal());
		else {
			try {
				return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(stud.getSearch().getColumnsql())), "客户信息");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.OutReturnList(new ArrayList<StudInfo>());


	}
}
