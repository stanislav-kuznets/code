package ru.otus.skuznets.hw13.servlet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Template;
import ru.otus.skuznets.hw13.PhoneDataSet;
import ru.otus.skuznets.hw13.AddressDataSet;
import ru.otus.skuznets.hw13.UserDataSet;
import ru.otus.skuznets.hw13.DBServiceImpl;
import ru.otus.skuznets.hw13.DataSet;
import ru.otus.skuznets.hw13.cache.CacheEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheServlet extends HttpServlet {
	
	@Autowired
	private static CacheEngine<Long, DataSet> cache;
	
	@Autowired
	private DBServiceImpl dbServiceImpl;
	
	private TemplateProcessor templateProcessor;
	
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	public CacheServlet() throws IOException {
		this(new TemplateProcessor());
	}
	
	public CacheServlet(TemplateProcessor templateProcessor) {
		this.templateProcessor = templateProcessor;
	}
	
	public void loadData() {
		List<PhoneDataSet> phones = new ArrayList<PhoneDataSet>();
		phones.add(new PhoneDataSet("654"));
		phones.add(new PhoneDataSet("432"));
		phones.add(new PhoneDataSet("413"));
		dbServiceImpl.save(new UserDataSet("Ivan", 22, new AddressDataSet("Komsomolskaya"), phones));
		dbServiceImpl.load(1);
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
