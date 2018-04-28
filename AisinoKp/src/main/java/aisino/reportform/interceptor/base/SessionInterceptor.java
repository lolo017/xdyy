package aisino.reportform.interceptor.base;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import aisino.reportform.db.DatabaseContextHolder;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.util.base.ConfigUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * session拦截器
 * 
 * @author 
 * 
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		 //设置db
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_1);

		logger.info("进入session拦截器->访问路径为[" + ServletActionContext.getRequest().getServletPath() + "]");
		if (sessionInfo == null) {
			String errMsg = "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！";
			logger.info(errMsg);
			ServletActionContext.getRequest().setAttribute("msg", errMsg);
			return "noSession";
		}
		return actionInvocation.invoke();
	}

}
