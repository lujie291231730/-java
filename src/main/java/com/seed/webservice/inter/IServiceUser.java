package com.seed.webservice.inter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface IServiceUser {

	@WebMethod
	public @WebResult String Login(
			@WebParam(name="appid") String appid, 
			@WebParam(name="guid") String guid,
			@WebParam(name="userid") String userid, 
			@WebParam(name="password") String password, 
			@WebParam(name="mac") String mac);
	
	@WebMethod
	public @WebResult String ChangePwd(@WebParam(name="appid") String appid, 
			@WebParam(name="guid") String guid,
			@WebParam(name="userid") String userid, 
			@WebParam(name="opassword") String opassword,
			@WebParam(name="npassword") String npassword,
			@WebParam(name="rpassword") String rpassword,
			@WebParam(name="mac") String mac);
	
	@WebMethod
	public @WebResult String ChangeTele(
			@WebParam(name="appid") String appid, 
			@WebParam(name="guid") String guid,
			@WebParam(name="userid") String userid, 
			@WebParam(name="usertele") String usertele,
			@WebParam(name="mac") String mac);
	
	@WebMethod
	public @WebResult String ChangeEMail(
			@WebParam(name="appid") String appid, 
			@WebParam(name="guid") String guid,
			@WebParam(name="userid") String userid, 
			@WebParam(name="useremail") String useremail,
			@WebParam(name="mac") String mac);
}
