package com.seed.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesUtils {
	
	public static Properties pps;
	
	public interface OnPropertiesLoadedListener{
		void onLoaded();
	}
	
	private static List<OnPropertiesLoadedListener> listeners;
	
	public static void loaded() {
		if (listeners != null) {
			for (OnPropertiesLoadedListener listener : listeners) {
				listener.onLoaded();
			}
		}
	}
	
	public static void addOnPropertiesLoadedListener(OnPropertiesLoadedListener listener){
		if (listeners == null) {
			listeners = new ArrayList<>();
		}
		listeners.add(listener);
	}
	
	public static String GetString(String key) {
		String value = pps.getProperty(key);
		
		if (value == null) {
			// throw new Exception();
			value = "";
		}
		
		return value;
	}
	
	public static int GetInt(String key) {
		try {
			return Integer.parseInt(GetString(key));
		} catch (Exception ex) {
			// throw new PropertiesConfigException(nf.getMessage(),key);
		}
		
		return 0;
	}
	
	public static boolean GetBoolean(String key) {
		try {
			return GetString(key).toLowerCase().equals("true");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
}
