package com.seed.controller;

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

import com.seed.dao.CrmDao;
import com.seed.dao.SystemDao;
import com.seed.entity.ReturnList;
import com.seed.entity.ReturnValue;
import com.seed.entity.crm.CrmCust;
import com.seed.entity.org.SysDept;
import com.seed.entity.user.OnlineUser;
import com.seed.enums.DataAction;
import com.seed.service.CrmService;
import com.seed.utils.StringUtils;
import com.seed.utils.ToolUtils;

@Controller
@RequestMapping("crm")
public class CrmController extends BaseController{
		@InitBinder("crm")
		public void InitBinderGovSealApply(WebDataBinder binder) {
			binder.setFieldDefaultPrefix("crm.");
		}
	
		@RequestMapping(value = "/getcust")
		@ResponseBody
		public ResponseEntity<CrmCust> GetCust (@ModelAttribute("crm") CrmCust cust){
			CrmCust rtn = new CrmCust();
			rtn = CrmDao.GetCust(cust);
			return this.OutReturnBean(rtn);
		}
		
		@RequestMapping(value = "/getlistcust")
		@ResponseBody
		public ResponseEntity<ReturnList> GetListCust (@ModelAttribute("crm") CrmCust cust){
			List<CrmCust> lists = CrmDao.GetLsitCust(cust);
			return this.OutReturnList(lists , cust.getSearch().getTotal());
		}
		
		@RequestMapping(value = "/searchcust")
		@ResponseBody
		public ResponseEntity SearchCust (@ModelAttribute("crm") CrmCust cust){
			String search = "";
			if (!ToolUtils.StringIsEmpty(cust.getCustname())) {
				search += StringUtils.GetAndSearch(search) + " a.custname like '%" + cust.getCustname() + "%' ";
			}
			if (!StringUtils.StringIsEmpty(cust.getProvid())) {
				search += (StringUtils.GetAndSearch(search.toString()) + " a.provid = '" + cust.getProvid() + "' ");
			}
			this.SetSearch(cust.getSearch(), cust.getItem(), new OnlineUser(), search);
			List<CrmCust> lists =  CrmDao.SearchCust(cust);
			if (!cust.getSearch().isHasexport())
				return this.OutReturnList(lists, cust.getSearch().getTotal());
			else {
				try {
					return OutExport(lists, this.GeneratorClomun(SystemDao.SqlColumn(cust.getSearch().getColumnsql())), "客户信息");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return this.OutReturnList(new ArrayList<CrmCust>());
//			return this.OutReturnList(lists, cust.getSearch().getTotal());


		}
		
	
	  @RequestMapping(value = "/savecust", method = RequestMethod.POST)
	  @ResponseBody
	  public ReturnValue SaveCrmCust (@ModelAttribute("crm") CrmCust cust) {

  			ReturnValue rtv = new ReturnValue();
  			if(cust.getDeal().getAction() == 2) {
  				cust.setCustid(new Date().getTime()+"");
  			}
  			CrmService.SaveCrmCust(cust, rtv);
			return rtv;
			
	  }
	  
		@RequestMapping(value = "/updatecust" , method = RequestMethod.POST)
		@ResponseBody
		public ReturnValue UpdateCrmCust(@ModelAttribute("crm") CrmCust cust) {
			ReturnValue rtn = new ReturnValue();
			
			cust.getDeal().setAction(DataAction.Modify.getAction());

			CrmService.SaveCrmCust(cust, rtn);
			
			return rtn;
		}
		
		@RequestMapping(value = "/deletetecust")
		@ResponseBody
		public ReturnValue DeleteCrmCust(@ModelAttribute("crm") CrmCust cust) {
			ReturnValue rtn = new ReturnValue();
			
			cust.getDeal().setAction(DataAction.Delete.getAction());

			CrmService.SaveCrmCust(cust, rtn);
			
			return rtn;
		}

}
