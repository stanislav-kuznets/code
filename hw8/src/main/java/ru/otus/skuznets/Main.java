package ru.otus.skuznets.hw8;

import com.google.gson.Gson;


public class Main 
{
    public static void main(String[] args) throws IllegalAccessException {
    
    TestClass test = new TestClass();
    
    System.out.println("convertToJSONString:");
    String jsonStringFromJSONConverter = JSONConverter.convertToJSONString(test);
    System.out.println(jsonStringFromJSONConverter);
    
    
    System.out.println("toJson:");
    Gson gson = new Gson();
    String jsonStringtoJson = gson.toJson(test);
    System.out.println(jsonStringtoJson);
    
    TestClass test1 = gson.fromJson(jsonStringFromJSONConverter, TestClass.class);
    TestClass test2 = gson.fromJson(jsonStringtoJson, TestClass.class);
    	if (test1.equals(test2)) { 
    		System.out.println("Objects are equal");
    	} else {
    		System.out.println("Objects are not equal");
    	}
    }
}
