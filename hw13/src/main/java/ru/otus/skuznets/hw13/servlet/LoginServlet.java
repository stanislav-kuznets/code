package ru.otus.skuznets.hw13.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.http.HttpServlet;

public class LoginServlet  extends HttpServlet {
	
	public static final String LOGIN_PAGE = "/login.html";
	private static String LOGIN = "admin";
	private static String PASSWORD = "demo";
	public static String CACHE_PAGE = "/dataFromCache.html";
	public static String INDEX_PAGE = "/index.html";
	@Autowired
	private TemplateProcessor templateProcessor;
	
	public void init (ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	public LoginServlet (TemplateProcessor templateProcessor) {
		this.templateProcessor = templateProcessor;
	}
	
	public LoginServlet( ) throws IOException {
		this(new TemplateProcessor());
	}
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println(templateProcessor.getPage(LOGIN_PAGE, null));
	}

	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(authorization(request)) {
			response.sendRedirect(CACHE_PAGE);
		} else {
			response.sendRedirect(INDEX_PAGE);
		}
	}
	
	public boolean authorization (HttpServletRequest request) {
		String login = request.getParameter("username");
		String password = request.getParameter("password");
		if (login != null && password != null) {
			if(LOGIN.equals(login) && PASSWORD.equals(password)) {
				return true;
			}
		}
		return false;
	}
	
}
