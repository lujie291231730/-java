package com.seed.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.seed.utils.PropertiesUtils;

public class DruidServletListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		if (event != null){
			ServletContext servletContext = event.getServletContext();
			ServletRegistration druidStatViewServlet = servletContext.addServlet("druidStatViewServlet", StatViewServlet.class);
			druidStatViewServlet.setInitParameter("loginUsername", "adminJh");
			druidStatViewServlet.setInitParameter("loginPassword", PropertiesUtils.pps.getProperty("DB_status_password", "Jh1234"));
			druidStatViewServlet.addMapping("/druid/*");
			event.getServletContext().log("You can visit /druid to view database status");
		}
	}

}
