package com.seed.webservice;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.ibatis.session.SqlSession;

import com.seed.dao.UserDao;
import com.seed.entity.ReturnValue;
import com.seed.entity.log.TranLog;
import com.seed.entity.user.OnlineUser;
import com.seed.entity.user.SysUser;
import com.seed.entity.user.SysUserDept;
import com.seed.service.UserService;
import com.seed.utils.Consts;
import com.seed.utils.CryptUtils;
import com.seed.utils.DBUtils;
import com.seed.utils.JsonUtils;
import com.seed.utils.ToolUtils;
import com.seed.webservice.inter.IServiceUser;

@WebService
public class ServiceUser implements IServiceUser {

	@Resource
	private WebServiceContext context = new org.apache.cxf.jaxws.context.WebServiceContextImpl();

	@WebMethod
	public @WebResult String Login(@WebParam(name="appid") String appid, 
			@WebParam(name="guid") String guid,
			@WebParam(name="userid") String userid, 
			@WebParam(name="password") String password, 
			@WebParam(name="mac") String mac) {
		
		OnlineUser rtv = new OnlineUser();
		
		if (!WsUtils.CheckMac(appid, guid, appid + guid + userid + password, mac)) {
			rtv.getSr().setMsg(Consts.STR_VALID_WEBSERVICE_REQUEST);
			rtv.getSr().setSuccess(false);
			
			return JsonUtils.GetJsonFromBean(rtv);
		}
		
		SqlSession session = DBUtils.getFactory();
		
		try {
			OnlineUser ou = UserDao.GetOnlineUser(session, userid);
			
			if (ToolUtils.StringIsEmpty(ou.getUser().getUserid()) || ToolUtils.StringIsEmpty(ou.getDept().getDeptid())) 
				throw new Exception("无效的用户!");
			
			if (!ou.getUser().getUserstatus().equals(Consts.DEFAULT_VALID_STATUS)) 
				throw new Exception("无效的机构!");
			
			if (!ou.getDept().getDeptstatus().equals(Consts.DEFAULT_VALID_STATUS)) 
				throw new Exception("无效的用户!");
			
			if (ou.getUser().getIslock()) 
				throw new Exception("该用户被锁定，不能登录系统，请联系系统管理员!");
			
			MessageContext ctx = context.getMessageContext();
      HttpServletRequest request = (HttpServletRequest)ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
			
      ou.getUser().getDeal().setIp(request.getRemoteAddr());
      
      if (!ou.getUser().getUserpassword().equals(CryptUtils.GetMD5(password))) {
				String errmsg = "";
				
				switch (ou.getUser().getErrorpassword()) {
					case 2:
						errmsg = "您已经连续输错3次密码，还有2次机会!";
						break;
					
					case 3:
						errmsg = "您已经连续输错4次密码，还有1次机会!";
						break;
						
					default:
						errmsg = "密码不正确!";
						break;
				}
				
				if (ou.getUser().getErrorpassword() >= 4) {
					UserDao.UserLock(session, ou.getUser());
					errmsg = "您的帐号因为多次尝试错误密码而被锁定，请与系统管理员联系！";					
				}
				else
					UserDao.UserError(session, ou.getUser());
				
				session.commit();
				
				if (!ToolUtils.StringIsEmpty(errmsg)) 
					throw new Exception(errmsg);
			}
      
      UserDao.UserLoginLog(session, ou.getUser());
      
      rtv.getUser().setUserid(ou.getUser().getUserid());
      rtv.getUser().setUsername(ou.getUser().getUsername());
      rtv.getUser().setUsertele(ou.getUser().getUsertele());
      rtv.getUser().setUseremail(ou.getUser().getUseremail());
      
      rtv.getDept().setDeptid(ou.getDept().getDeptid());
      rtv.getDept().setDeptname(ou.getDept().getDeptname());
      
      rtv.getMenugroups().clear();
      rtv.getMenugroups().addAll(UserDao.GetMenuGroupByUser(session, ou.getUser()));
      rtv.getMenus().clear();
      rtv.getMenus().addAll(UserDao.GetMenuByUser(session, ou.getUser()));
			
			session.commit();
		} catch (Exception e) {
			session.rollback();
			rtv.getUser().OnInit();
			rtv.getDept().OnInit();
			rtv.getSr().setMsg(e.getMessage());
			rtv.getSr().setSuccess(false);
		}
		
		WsUtils.LogWsVisit(appid, "Login", userid, "政务登录", context);
		
		return JsonUtils.GetJsonFromBean(rtv);
	}
	
	@WebMethod
	public @WebResult String ChangePwd(
			@WebParam(name="appid") String appid, 
			@WebParam(name="guid") String guid,
			@WebParam(name="userid") String userid, 
			@WebParam(name="opassword") String opassword,
			@WebParam(name="npassword") String npassword,
			@WebParam(name="rpassword") String rpassword,
			@WebParam(name="mac") String mac) {
		
		ReturnValue rtv = new ReturnValue();
		TranLog log = new TranLog();
		log.setTranuser(userid);
		
		WsUtils.LogWsVisit(appid, "ChangePwd", userid, "修改密码", context);
		
		if (!WsUtils.CheckMac(appid, guid, appid + guid + userid + opassword + npassword + rpassword, mac)) {
			rtv.setMsg(Consts.STR_VALID_WEBSERVICE_REQUEST);
			rtv.setSuccess(false);
			
			return JsonUtils.GetJsonFromBean(rtv);
		}
		
		SysUser user = new SysUser();
		user.setUserid(userid);
		user.setUserpassword(npassword);
		user.setUsername(opassword);
		user.setRemark(rpassword);
		
		UserService.ChangePwd(user, rtv, log);
		
		return JsonUtils.GetJsonFromBean(rtv);
	}
	
	@WebMethod
	public @WebResult String ChangeTele(
			@WebParam(name="appid") String appid, 
			@WebParam(name="guid") String guid,
			@WebParam(name="userid") String userid, 
			@WebParam(name="usertele") String usertele,
			@WebParam(name="mac") String mac) {
		
		ReturnValue rtv = new ReturnValue();
		TranLog log = new TranLog();
		log.setTranuser(userid);
		
		WsUtils.LogWsVisit(appid, "ChangePwd", userid, "修改手机号码", context);
		
		if (!WsUtils.CheckMac(appid, guid, appid + guid + userid + usertele, mac)) {
			rtv.setMsg(Consts.STR_VALID_WEBSERVICE_REQUEST);
			rtv.setSuccess(false);
			
			return JsonUtils.GetJsonFromBean(rtv);
		}
		
		SysUser user = new SysUser();
		user.setUserid(userid);
		user.setUsertele(usertele);
		
		UserService.ChangeTele(user, rtv, log);
		
		return JsonUtils.GetJsonFromBean(rtv);
	}
	
	@WebMethod
	public @WebResult String ChangeEMail(
			@WebParam(name="appid") String appid, 
			@WebParam(name="guid") String guid,
			@WebParam(name="userid") String userid, 
			@WebParam(name="useremail") String useremail,
			@WebParam(name="mac") String mac) {
		
		ReturnValue rtv = new ReturnValue();
		TranLog log = new TranLog();
		log.setTranuser(userid);
		
		WsUtils.LogWsVisit(appid, "ChangeEMail", userid, "修改邮箱", context);
		
		if (!WsUtils.CheckMac(appid, guid, appid + guid + userid + useremail, mac)) {
			rtv.setMsg(Consts.STR_VALID_WEBSERVICE_REQUEST);
			rtv.setSuccess(false);
			
			return JsonUtils.GetJsonFromBean(rtv);
		}
		
		SysUser user = new SysUser();
		user.setUserid(userid);
		user.setUseremail(useremail);
		
		UserService.ChangeEMail(user, rtv, log);
		
		return JsonUtils.GetJsonFromBean(rtv);
	}
	
	@WebMethod
  public @WebResult String GetSysUserDept(
      @WebParam(name="appid") String appid, 
      @WebParam(name="guid") String guid,
      @WebParam(name="userid") String userid, 
      @WebParam(name="mac") String mac) {
		
		ReturnValue rtv = new ReturnValue();
		
		WsUtils.LogWsVisit(appid, "GetSysUserDept", userid, "可操作部门-查询", context);
		
		if (!WsUtils.CheckMac(appid, guid, appid + guid + userid, mac)) {
			rtv.setMsg(Consts.STR_VALID_WEBSERVICE_REQUEST);
			rtv.setSuccess(false);
			
			return JsonUtils.GetJsonFromBean(rtv);
		}
		
		SysUserDept item = new SysUserDept();
		item.setUserid(userid);
		
		if (!WsUtils.ValidWebUser(userid, item.getItem())) {
			rtv.setMsg(Consts.STR_VALID_WEBSERVICE_USER);
			rtv.setSuccess(false);
			
			return JsonUtils.GetJsonFromBean(rtv);
		}
		
		rtv.setSuccess(true);
		rtv.setData(UserDao.GetSysUserDept(item));
		
		return JsonUtils.GetJsonFromBean(rtv);
	}
}
