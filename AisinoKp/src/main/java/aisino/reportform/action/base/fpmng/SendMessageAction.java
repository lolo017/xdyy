package aisino.reportform.action.base.fpmng;



import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.service.fpmng.SendMessageServiceI;

/**
 * 
 * @Title: SendMessageAction
 * @Description:发送短信管理
 * Company    JS-YFB LTD
 * @author:张欢
 * @version 1.0   
 * @date 2017-11-1 下午3:05:04
 */
@Namespace("/base/fpmng")
@Action
public class SendMessageAction extends BaseAction<SysOrderHead>{

	private static final long serialVersionUID = 1L;
	@Autowired
	SendMessageServiceI sMessageServiceI;
	Json json = new Json();

	/**
	  * @author:张欢
	  * @Title: SendMessage
	  * @Description: 发送短信
	  * @return: void    
	  * @throws
	  * @dateTime:2017-11-1下午3:30:02
	 */
	public void sendMessage(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//获取传过来的参数
		String ohids = request.getParameter("ohids");
		System.out.println(ohids);
		if(!StringUtils.isBlank(ohids)){
			ohids=ohids.replace("[", "").replace("]", "").replace("\"", "");
		}
		for (String ohid : ohids.split(",")) {
			//调用service中的方法
			int result = sMessageServiceI.sendMessage(ohid);
			if(result == 1){
				json.setMsg("短信发送成功！");
				json.setSuccess(true);
				writeJson(json);
			}else{
				json.setMsg("服务器故障，短信发送失败！");
				json.setSuccess(false);
				writeJson(json);
			}
		}
	}
	
	

}
