package aisino.reportform.listener.base;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ognl.OgnlRuntime;

/**
 * 
 * @author 
 * 
 */
public class OgnlSecurityListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

	}

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		OgnlRuntime.setSecurityManager(null);
	}

}
