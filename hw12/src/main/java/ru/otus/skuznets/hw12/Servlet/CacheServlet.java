package ru.otus.skuznets.hw12.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Template;
import ru.otus.skuznets.hw12.DataSet;
import ru.otus.skuznets.hw12.Cache.CacheEngine;

import java.util.HashMap;
import java.util.Map;

public class CacheServlet extends HttpServlet {
	
	private static CacheEngine<Long, DataSet> cache;
	private TemplateProcessor templateProcessor;
	
	public CacheServlet() throws IOException {
		this(new TemplateProcessor());
	}
	
	public CacheServlet(TemplateProcessor templateProcessor) {
		this.templateProcessor = templateProcessor;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hit", cache.getHitCount());
		map.put("miss", cache.getMissCount());
		map.put("maxElements", cache.getElements());
		String page = templateProcessor.getPage("dataFromCache.html", map);
		response.getWriter().println(page);
		
	}

}
