package com.eeerrorcode.member_post.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextpathListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("cp", sce.getServletContext().getContextPath() + "/");
	}

}
