package com.seed.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.seed.utils.Consts;
import com.seed.utils.PropertiesUtils;

public class PropertiesSessionListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		loadConfig(event);
	}
	
	public static void loadConfig(ServletContextEvent event) {
		// TODO Auto-generated method stub
		PropertiesUtils.pps = new Properties();
		
		try {			
			InputStream is = PropertiesSessionListener.class.getClassLoader().getResourceAsStream(Consts.CONFIG_FILE_NAME);
			PropertiesUtils.pps.load(is);
			PropertiesUtils.loaded();
			if (event != null){
				event.getServletContext().log("properties load.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}