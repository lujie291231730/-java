package com.seed.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.seed.utils.DBUtils;

public class DataBaseInitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		DBUtils.getInstance();
		
		if (event != null){
			event.getServletContext().log("DB inited");
		}
	}

}
