package com.seed.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * @author lazyeraser 手动Json转换工具 使用Google Gson
 */
public class JsonUtils {

	/**
	 * Json对象转Java对象
	 * 
	 * @param json
	 * @param type Example.class
	 * @return
	 */
	public static <T> T GetBeanFromJson(String json, Type type) {
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(Consts.STR_DATE_LONG).create();
		
		return gson.fromJson(json, type);
	}

	/**
	 * Json数组转Java数组（列表）
	 * 
	 * @param json
	 * @param type 数组元素的类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> GetArrayFromJson(String json, Type type) {
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(Consts.STR_DATE_LONG).create();
		
		if (StringUtils.StringIsEmpty(json))
			json = "[]";
		
		List<JsonObject> jsonObjects = gson.fromJson(json, new TypeToken<List<JsonObject>>() { }.getType());
		List<T> lists = new ArrayList<>();
		
		for (JsonObject jsonObject : jsonObjects) {
			lists.add((T) gson.fromJson(jsonObject, type));
		}
		
		return lists;
	}

	/**
	 * Java对象转Json
	 * 
	 * @param bean 对象实例
	 * @return
	 */
	public static <T> String GetJsonFromBean(T bean) {
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(Consts.STR_DATE_LONG).create();
		
		return gson.toJson(bean);
	}
	
	public static <T> String GetHtmlJsonFromBean(T bean) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().setDateFormat(Consts.STR_DATE_LONG).create();
		
		return gson.toJson(bean);
	}

	/**
	 * Java List转Json数组
	 * 
	 * @param list List实例
	 * @return
	 */
	public static <T> String GetJsonFromArray(List<T> list) {
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(Consts.STR_DATE_LONG).create();
		
		return gson.toJson(list);
	}

	public static <T> T LowCopy(T item, Type type) {
		return GetBeanFromJson(GetJsonFromBean(item), type);
	}
}
