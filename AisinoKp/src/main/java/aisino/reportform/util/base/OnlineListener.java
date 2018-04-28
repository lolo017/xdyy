package aisino.reportform.util.base;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.Syonline;
import aisino.reportform.service.base.SyonlineServiceI;

/**
 * 监听在线用户上线下线
 * 
 * @author 
 * 
 */
public class OnlineListener implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, HttpSessionBindingListener, ServletRequestListener, ServletRequestAttributeListener {

	private static final Logger logger = Logger.getLogger(OnlineListener.class);

	private static ApplicationContext ctx = null;

	public OnlineListener() {
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	/**
	 * 向session里增加属性时调用(用户成功登陆后会调用)
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent evt) {
		String name = evt.getName();
		logger.debug("向session存入属性：" + name);
		if (ConfigUtil.getSessionInfoName().equals(name)) {// 如果存入的属性是sessionInfo的话
			HttpSession session = evt.getSession();
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(name);
			if (sessionInfo != null) {
				// System.out.println(sessionInfo.getUser().getName() + "登录了");
				SyonlineServiceI syonlineService = (SyonlineServiceI) ctx.getBean("syonlineServiceImpl");
				Syonline online = new Syonline();
				online.setType("1");// 登录
				online.setLoginname(sessionInfo.getUser().getLoginname());
				online.setIp(sessionInfo.getUser().getIp());
				syonlineService.save(online);
			}
		}
	}

	/**
	 * 服务器初始化时调用
	 */
	@Override
	public void contextInitialized(ServletContextEvent evt) {
		logger.debug("服务器启动");
		ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
	}

	/**
	 * session销毁(用户退出系统时会调用)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent evt) {
		HttpSession session = evt.getSession();
		if (session != null) {
			logger.debug("session销毁：" + session.getId());
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			if (sessionInfo != null) {
				// System.out.println(sessionInfo.getUser().getName() + "注销了");
				SyonlineServiceI syonlineService = (SyonlineServiceI) ctx.getBean("syonlineServiceImpl");
				Syonline online = new Syonline();
				online.setType("0");// 注销
				online.setLoginname(sessionInfo.getUser().getLoginname());
				online.setIp(sessionInfo.getUser().getIp());
				syonlineService.save(online);
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent evt) {
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		logger.debug("服务器关闭");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
	}

}
