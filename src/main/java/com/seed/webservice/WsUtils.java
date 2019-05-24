package com.seed.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.seed.dao.UserDao;
import com.seed.dao.WsPubDao;
import com.seed.entity.publics.SelectBean;
import com.seed.entity.user.SysUser;
import com.seed.enums.DataAction;
import com.seed.service.WsPubService;
import com.seed.utils.*;
import com.seed.webservice.entity.*;

public class WsUtils {

	public static void CreateMac(WsGet in, String key) {
  	in.setMac(CryptUtils.CryptSHA256(in.getAppid() + in.getItemid() + in.getUserid() + key));
  }
	
	public static void CreateMac(WsApp app, WsGet in) {
  	in.setMac(CryptUtils.CryptSHA256(in.getAppid() + in.getItemid() + in.getUserid() + app.getAppkey()));
  }
	
	public static boolean CheckMac(WsGet in, String key) {
		return in.getMac().equals(CryptUtils.CryptSHA256(in.getAppid() + in.getItemid() + in.getUserid() + key));
	}
	
	public static boolean CheckMac(WsApp app, WsGet in) {
		return in.getMac().equals(CryptUtils.CryptSHA256(in.getAppid() + in.getItemid() + in.getUserid() + app.getAppkey()));
	}
	
	public static String GetServiceIP(WebServiceContext context) {
		
		try {
			javax.xml.ws.handler.MessageContext ctx = context.getMessageContext();
			HttpServletRequest req = (HttpServletRequest)ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
			return req.getRemoteAddr();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "";
	}
	
	public static void LogWsVisit(String appid, String func, String ip, String context) {
		WsVisit visit = new WsVisit();
		
		visit.setAppid(appid);
		visit.setVisitaddress(ip);
		visit.setVisitfunc(func);
		visit.setVisitcontent(context);
		
		WsPubService.SaveWsVisit(visit);
	}
	
	public static boolean CheckMac(String appid, String guid, String in, String mac) {		
		
		try {
			WsApp app = WsPubDao.GetWsApp(appid);
			System.out.println("mac:" + CryptUtils.CryptSHA256(in + app.getAppkey()));
			
			
			WsGuid wg = WsPubDao.GetWsGuid(guid);
			
			if ((wg != null) && !StringUtils.StringIsEmpty(wg.getWsguid()))
				throw new Exception("guid已经被使用！");
			
			WsPubService.SaveWsGuid(guid);
			
			return mac.equals(CryptUtils.CryptSHA256(in + app.getAppkey()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	public static void LogWsVisit(String appid, String func, String userid, String content, WebServiceContext context) {
		try {
			javax.xml.ws.handler.MessageContext ctx = context.getMessageContext();
			HttpServletRequest req = (HttpServletRequest)ctx.get(AbstractHTTPDestination.HTTP_REQUEST);
			
			WsVisit visit = new WsVisit();
			
			visit.setAppid(appid);
			visit.setVisitaddress(req.getRemoteAddr());
			visit.setVisitfunc(func);
			visit.setVisitdev(req.getHeader("user-agent"));
			visit.setVisitcontent(content);
			visit.setVisituser(userid);
			visit.getDeal().setAction(DataAction.Create.getAction());
			WsPubService.SaveWsVisit(visit);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static <T> boolean ValidWebUser(String userid, SelectBean<T> item) {
  	SysUser user = UserDao.GetUser(userid);
		
		if ((user == null) || ToolUtils.StringIsEmpty(user.getUserid())) {
			return false;
		}
		
		item.setUserid(user.getUserid());
		item.setIsadmin(user.getIsadmin());
		
  	return true;
  }
}
