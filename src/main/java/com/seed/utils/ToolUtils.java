package com.seed.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.*;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.seed.entity.log.TranLog;
import com.seed.entity.system.SysSet;
import com.seed.entity.user.OnlineUser;
import com.seed.enums.*;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.*;
import net.sf.json.util.JSONUtils;

public class ToolUtils {

	public static TranLog InitTranLog(OnlineUser ou, String mcode) {
		TranLog log = new TranLog();
		log.setTranuser(ou.getUser().getUserid());
		log.setTrandept(ou.getUser().getDeptid());
		log.setUsername(ou.getUser().getUsername());
		log.setTrancode(mcode);
		
		return log;
	}
	
	public static <T> List<T> Array2List(T[] array){
		List<T> list = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}

	public static String GetSetValue(OnlineUser ou, String key) 
  {
  	for (SysSet set : ou.getSets()) {
			if (set.getSetcode().equals(key))
				return set.getSetvalue();
		}
  	
  	return "";
  }  

  public static int GetDaysBetween(java.util.Date beginDate, java.util.Date endDate) throws ParseException
  {
  	Calendar d1 = new GregorianCalendar();
  	d1.setTime(beginDate);
  	
  	Calendar d2 = new GregorianCalendar();
  	d2.setTime(endDate);

  	int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
  	int y2 = d2.get(Calendar.YEAR);

  	if (d1.get(Calendar.YEAR) != y2) {
  		d1 = (Calendar) d1.clone();
  		do {
  			days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
  			d1.add(Calendar.YEAR, 1);
  		}
  		while (d1.get(Calendar.YEAR) != y2);
  	}
      
  	return days;
  }
  
	public static DataBaseType GetDataBaseType() {
		return DataBaseType.parse(PropertiesUtils.pps.getProperty(Consts.CONFIG_DATABASE_TYPE));
	}

	/*
	 * public static boolean isDebug() { return
	 * Boolean.valueOf(PropertiesUtils.pps.getProperty(Consts.CONFIG_DEBUG_MODE).
	 * toLowerCase()); }
	 */

	public static String ConvertObjectValue(Object in) {
		return ConvertObjectValue(in, "yyyy-MM-dd HH:mm:ss");
	}

	public static String ConvertObjectValue(Object in, String datefmt) {
		String rtn = "";

		if (in == null)
			return rtn;

		if (in instanceof java.util.Date) {
			SimpleDateFormat sp = new SimpleDateFormat(datefmt);
			rtn = sp.format(in);
		} else
			rtn = in.toString();

		return rtn;
	}

	public static <T> String CompareProperty(T source, T target, String[] propertynames) {
		StringBuilder sb = new StringBuilder();

		for (String property : propertynames) {
			String[] propertys = property.split(":");

			try {
				if (propertys.length != 2)
					throw new Exception("对象比较时配置参数出错:" + property);

				Field fld = source.getClass().getDeclaredField(propertys[1]);
				fld.setAccessible(true);

				Object svalue = fld.get(source);
				Object tvalue = fld.get(target);
				if ((svalue != null) && (tvalue != null)) {
					if (!ConvertObjectValue(svalue).equals(ConvertObjectValue(tvalue)))
						sb.append(propertys[0] + "-" + propertys[1] + ":[" + ConvertObjectValue(svalue) + "]-["
								+ ConvertObjectValue(tvalue) + "];" + StringUtils.WriteEnter());
				} else if ((svalue != null) && (tvalue == null)) {
					// source不为空 target为空
					sb.append(propertys[0] + "-" + propertys[1] + ":[" + ConvertObjectValue(svalue) + "]-[空];"
							+ StringUtils.WriteEnter());
				} else if ((svalue == null) && (tvalue != null)) {
					// source不为空 target为空
					sb.append(propertys[0] + "-" + propertys[1] + ":[空]-[" + ConvertObjectValue(tvalue) + "];"
							+ StringUtils.WriteEnter());
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

		return sb.toString();
	}

	public static <T> List<String> ComparePropertyModify(T source, T target, String[] propertynames) throws Exception {
		List<String> rtn = new ArrayList<String>();

		StringBuilder nsb = new StringBuilder();
		StringBuilder msb = new StringBuilder();
		StringBuilder dsb = new StringBuilder();

		for (String property : propertynames) {
			String[] propertys = property.split(":");

			if (propertys.length != 2)
				throw new Exception("对象比较时配置参数出错:" + property);

			Field fld = source.getClass().getDeclaredField(propertys[1]);
			fld.setAccessible(true);

			Object svalue = fld.get(source);
			Object tvalue = fld.get(target);

			if ((svalue != null) && (tvalue != null)) {
				if (!ConvertObjectValue(svalue).equals(ConvertObjectValue(tvalue)))
					msb.append(
							propertys[0] + "由" + "[" + ConvertObjectValue(svalue) + "]调整为[" + ConvertObjectValue(tvalue) + "],");
			} else if ((svalue != null) && (tvalue == null)) {
				// source不为空 target为空
				dsb.append(propertys[0] + "[" + ConvertObjectValue(svalue) + "],");
			} else if ((svalue == null) && (tvalue != null)) {
				// source不为空 target为空
				nsb.append(propertys[0] + "[" + ConvertObjectValue(tvalue) + "],");
			}
		}

		rtn.add(nsb.toString());
		rtn.add(msb.toString());
		rtn.add(dsb.toString());
		return rtn;
	}

	public static <T> String DebugProperty(T source, String[] propertynames) {
		StringBuilder sb = new StringBuilder();

		for (String property : propertynames) {
			String[] propertys = property.split(":");

			if (propertys.length == 2) {
				try {
					Field fld = source.getClass().getDeclaredField(propertys[1]);
					fld.setAccessible(true);

					Object svalue = fld.get(source);

					if ((svalue != null))
						sb.append(propertys[0] + "-" + propertys[1] + ":[" + ConvertObjectValue(svalue) + "];"
								+ StringUtils.WriteEnter());
					else {
						sb.append(propertys[0] + "-" + propertys[1] + ":[];" + StringUtils.WriteEnter());
					}

				} catch (Exception e) {

				}
			}
		}

		return sb.toString();
	}

	public static boolean IsWindows(){
		return System.getProperty("os.name").toLowerCase().startsWith("windows");
	}
	
	public static double MoneyDouble(double in) {
		BigDecimal b = new BigDecimal(in); 
		
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	// region Date Methods

	public static Date GetMinDate() {
		return new java.util.Date(0);
	}

	public static Date GetNowDate() {
		return new java.util.Date();
	}

	public static String GetFmtDate(Date in) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");

		return sp.format(in);
	}

	public static String GetFmtDate(Date in, String fmt) {
		SimpleDateFormat sp = new SimpleDateFormat(fmt);

		return sp.format(in);
	}

	public static java.util.Date GetDateByFmt(String in, String fmt) {
		SimpleDateFormat sp = new SimpleDateFormat(fmt);

		try {
			return sp.parse(in);
		} catch (Exception e) {
			return new java.util.Date();
		}
	}

	public static String GetHMFmtDate(Date in) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return sp.format(in);
	}

	public static String GetDebugDate(Date in) {
		if (in == null)
			return "";

		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sp.format(in);
	}

	public static String GetLongFmtDate(Date in) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sp.format(in);
	}

	public static java.util.Date String2Date(String in) throws ParseException {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sp.parse(in);
	}

	public static int GetWeekOfYear(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	public static int GetSeasonOfYear(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);

		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;

		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;

		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;

		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;

		default:
			break;
		}

		return season;
	}

	public static int GetTenOfMonth(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_MONTH);

		if (day <= 10)
			return 1;
		else if (day <= 20)
			return 2;
		else {
			return 3;
		}
	}

	// endregion Date Methods

	// region Combo Value Methods

	public static boolean MustComboValue(String value) {
		if (StringUtils.StringIsEmpty(value))
			return true;

		if (value.equals(Consts.SELECT_MUST_VALUE))
			return true;

		return false;
	}

	public static boolean CheckComboValue(String value) {
		if (StringUtils.StringIsEmpty(value))
			return true;

		if (value.equals(Consts.SELECT_MUST_VALUE))
			return true;

		if (value.equals(Consts.SELECT_ALL_VALUE))
			return true;

		return false;
	}

	public static boolean AllComboValue(String value) {
		if (StringUtils.StringIsEmpty(value))
			return true;

		if (value.equals(Consts.SELECT_ALL_VALUE))
			return true;

		return false;
	}

	public static boolean NullComboValue(String value) {
		if (StringUtils.StringIsEmpty(value))
			return true;

		if (value.equals(Consts.SELECT_NULL_VALUE))
			return true;

		return false;
	}

	public static boolean ValueComboValue(String value) {
		if (StringUtils.StringIsEmpty(value))
			return true;

		if (value.equals(Consts.SELECT_MUST_VALUE))
			return true;

		if (value.equals(Consts.SELECT_ALL_VALUE))
			return true;

		if (value.equals(Consts.SELECT_NULL_VALUE))
			return true;

		return false;
	}

	// endregion Combo Value Methods

	// region Exception Methods

	public static String GetErrorMessage(Exception e) {
		return GetErrorMessage(e, "");
	}

	public static String GetErrorMessage(Exception e, String prefix) {
		String rtn = "";

		if (e.getCause() instanceof SQLServerException) {

			SQLServerException sex = (SQLServerException) e.getCause();

			int sqlcode = sex.getErrorCode();

			switch (sqlcode) {
			case 2601:
				rtn = prefix + "存在重复数据！";
				break;

			case 18456:
				rtn = prefix + "数据库登录失败！";
				break;

			default:
				rtn = prefix + sex.getMessage();
				break;
			}
		} else if (e.getCause() instanceof java.sql.SQLException) {
			java.sql.SQLException oex = (java.sql.SQLException) e.getCause();

			switch (oex.getErrorCode()) {
			case 17002:
				rtn = prefix + "数据库登录失败！";
				break;

			default:
				rtn = prefix + oex.getMessage();
				break;
			}
		} else
			rtn = prefix + e.getMessage();

		return rtn.replace('"', '\'');
	}

	public static boolean StringIsEmpty(String str) {
		return str == null || str.equals("");
	}

	public static String GetNewLines() {
		return System.getProperty("line.separator");
	}

	// endregion Exception Methods

	// region Json Methods
	
	public static String GetJsonFromBean(Object bean){
		JsonConfig config = new JsonConfig();
		config.registerJsonPropertyNameProcessor(bean.getClass(), new FirstPropertyNameProcessor());
		
		JSONObject JsonObject = JSONObject.fromObject(bean, config);
		
		return JsonObject.toString();
	}
	
	public static String GetJsonFromBean(Object bean, JsonConfig config){
		JSONObject JsonObject = JSONObject.fromObject(bean, config);

		String rtn = JsonObject.toString();
		
		return rtn;
	}
	
	@SuppressWarnings("rawtypes")
	public static String GetJsonFromArray(Object bean, Class classtype){
		JsonConfig config = new JsonConfig();
		config.registerJsonPropertyNameProcessor(classtype, new FirstPropertyNameProcessor());
		
		JSONArray JsonObject = JSONArray.fromObject(bean, config);
		return JsonObject.toString();
	}
	
	public static String GetJsonFromArray(Object bean, JsonConfig config){
		JSONArray JsonObject = JSONArray.fromObject(bean, config);
		return JsonObject.toString();
	}
	
	public static String GetJsonFromArray(Object bean){
		return JSONArray.fromObject(bean).toString();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> GetArrayFromJson(String json, Class<T> classtype) {
		JSONArray JsonObject = JSONArray.fromObject(json);
		
		String[] dateFormats = new String[] { "yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"};
		
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
		
		Collection<T> collections = JSONArray.toCollection(JsonObject, classtype);
		List<T> lists = new ArrayList<T>();
		for (T t : collections) {
			if (classtype.equals(t.getClass()))
				lists.add(t);
		}
		return lists;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> GetArrayFromJson(JSONArray JsonObject, Class<T> classtype) {		
		String[] dateFormats = new String[] { "yyyy-MM-dd'T'HH:mm:ss","yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"};
		
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
		
		Collection<T> collections = JSONArray.toCollection(JsonObject, classtype);
		List<T> lists = new ArrayList<T>();
		for (T t : collections) {
			if (classtype.equals(t.getClass()))
				lists.add(t);
		}
		return lists;
	}
	
	// endregion Json Methods


}
