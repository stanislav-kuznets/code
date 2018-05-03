package ru.otus.skuznets.hw8;

import org.json.simple.*;
import java.lang.reflect.*;
import java.util.Collection;

public class JSONConverter {
	
	public static boolean isWrap (Object obj) {
		if (obj instanceof Integer || obj instanceof Byte || obj instanceof Short || obj instanceof Boolean 
				|| obj instanceof Float || obj instanceof Double || obj instanceof Long) {
			return true;
		}
		return false;
	}
	
	public static JSONArray jCollection (Object value) throws IllegalAccessException {
		JSONArray jsonArray = new JSONArray();
		for (Object element: (Collection) value) {
			if (element instanceof String || isWrap(element)) {
				jsonArray.add(element);
			} else if (element.getClass().isArray()) {
				jsonArray.add(jArray(element));
			} else if (element instanceof Collection) {
				jsonArray.add(jCollection(element));
			} else {
				jsonArray.add(jObject(element));
			}
			
		}
		return jsonArray;
	}
	
	public static JSONArray jArray (Object value) throws IllegalAccessException {
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < Array.getLength(value); i++) {
			Object element = Array.get(value, i);
			if (element instanceof String || isWrap(element)) {
				jsonArray.add(element);
			} else if (element.getClass().isArray()) {
				jsonArray.add(jArray(element));
			} else if (element instanceof Collection) {
				jsonArray.add(jCollection(element));
			} else {
				jsonArray.add(jObject(element));
			}
		}
		return jsonArray;
	}
	
	public static JSONObject jObject (Object obj) throws IllegalAccessException {
		JSONObject jsonObject = new JSONObject();
		for (Field field: obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(obj);
			
			if (value instanceof String || isWrap(value)) {
				jsonObject.put(field.getName(), value);
			} else if (value.getClass().isArray()) {
				jsonObject.put(field.getName(), jArray(value));
			} else if (value instanceof Collection) {
				jsonObject.put(field.getName(), jCollection(value));
			} else {
				jsonObject.put(field.getName(), jObject(value));
			}
		}
		return jsonObject;
	}
	
	public static String convertToJSONString (Object obj) throws IllegalAccessException {
		if (isWrap(obj)) {
			return obj.toString();
		} else if (obj instanceof String) {
			return String.format("\"%s\"" , obj);
		} else if (obj.getClass().isArray()) {
			return jArray(obj).toJSONString();
		} else if(obj instanceof Collection) {
			return jCollection(obj).toJSONString();
		} else {
			return jObject(obj).toJSONString();
		}
	}
}
