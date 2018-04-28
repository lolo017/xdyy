package aisino.reportform.action.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.db.DatabaseContextHolder;
import aisino.reportform.model.base.SessionInfo;
import aisino.reportform.model.base.Syorganization;
import aisino.reportform.model.base.Syuser;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.SyuserServiceI;
import aisino.reportform.util.base.BeanUtils;
import aisino.reportform.util.base.ConfigUtil;
import aisino.reportform.util.base.HqlFilter;
import aisino.reportform.util.base.IpUtil;
import aisino.reportform.util.base.MD5Util;
import aisino.reportform.util.base.SendMail;

/**
 * 用户
 * 
 * action访问地址是/base/syuser.sy
 * 
 * @author
 * 
 */
@Namespace("/base")
@Action
public class SyuserAction extends BaseAction<Syuser> {

	private String id;
	private String email;
	private String pwds;//自己的邮箱密码
	private String content;//内容
	private String subject;//标题
	
	
	public String getPwds() {
		return pwds;
	}

	public void setPwds(String pwds) {
		this.pwds = pwds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SyuserServiceI service) {
		this.service = service;
	}

	/**
	 * 注销系统
	 */
	public void doNotNeedSessionAndSecurity_logout() {
		if (getSession() != null) {
			getSession().invalidate();
		}
		Json j = new Json();
		j.setSuccess(true);
		writeJson(j);
	}

	/**
	 * 注册
	 */
	synchronized public void doNotNeedSessionAndSecurity_reg() {
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_1);
		Json json = new Json();
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
		Syuser user = service.getByFilter(hqlFilter);
		if (user != null) {
			json.setMsg("用户名已存在！");
			writeJson(json);
		} else {
			Syuser u = new Syuser();
			u.setLoginname(data.getLoginname());
			u.setPwd(MD5Util.md5(data.getPwd()));
			service.save(u);
			doNotNeedSessionAndSecurity_login();
		}
	}

	/**
	 * 登录
	 */
	public void doNotNeedSessionAndSecurity_login() {
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_1);
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
		hqlFilter.addFilter("QUERY_t#pwd_S_EQ", MD5Util.md5(data.getPwd()));
		Syuser user = service.getByFilter(hqlFilter);
		Json json = new Json();
		if (user != null&&user.getIsLeave().equals("0")) {
			json.setSuccess(true);
			SessionInfo sessionInfo = new SessionInfo();
//			Hibernate.initialize(user.getSyroles());
//			Hibernate.initialize(user.getSyorganizations());
//			for (Syrole role : user.getSyroles()) {
//				Hibernate.initialize(role.getSyresources());
//			}
//			for (Syorganization organization : user.getSyorganizations()) {
//				Hibernate.initialize(organization.getSyresources());
//			}
			if(user.getPwd().equals(MD5Util.md5("123456"))){
				user.setIsPwd(0);
			}else{
				user.setIsPwd(1);
			}
			List list=service.findBySql("select ttt.url from syresource ttt where ttt.id in(select distinct  tt.syresource_id from syrole_syresource tt where tt.syrole_id in(select t.syrole_id  from syuser_syrole t where  t.syuser_id='"+user.getId()+"')) and ttt.url is not null");
			user.setResourceMap(list);
			user.setIp(IpUtil.getIpAddr(getRequest()));
			
			Set s=user.getSyorganizations();
			String org_id = "";
			for(Iterator i = s.iterator(); i.hasNext();){
				Syorganization or = (Syorganization)i.next();
				if(or.getId() != null && !"".equals(or.getId()) && !"09".equals(or.getId()))
				{
					org_id = or.getId();
				}
			}
			sessionInfo.setOrg_id(org_id);
			sessionInfo.setUser(user);
			getSession().setAttribute(ConfigUtil.getSessionInfoName(),
					sessionInfo);
		} else if (user != null&&user.getIsLeave().equals("1")) {
			json.setMsg("该用户已离职！");
		}
		else {
			json.setMsg("用户名或密码错误！");
		}
		writeJson(json);
	}

	/**
	 * 修改自己的密码
	 */
	public void doNotNeedSecurity_updateCurrentPwd() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Json json = new Json();
		Syuser user = service.getById(sessionInfo.getUser().getId());
		user.setPwd(MD5Util.md5(data.getPwd()));
		user.setUpdatedatetime(new Date());
		service.update(user);
		json.setSuccess(true);
		writeJson(json);
	}
	/**
	 * 修改初始密码
	 */
	public void doNotNeedSecurity_firstUpdatePwd(){
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Json json = new Json();
		Syuser user = service.getById(sessionInfo.getUser().getId());
		user.setPwd(MD5Util.md5(data.getPwd()));
		user.setUpdatedatetime(new Date());
		service.update(user);
		sessionInfo.getUser().setPwd(MD5Util.md5(data.getPwd()));
		sessionInfo.getUser().setIsPwd(1);
		getSession().setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 修改用户角色
	 */
	public void grantRole() {
		Json json = new Json();
		((SyuserServiceI) service).grantRole(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 修改用户机构
	 */
	public void grantOrganization() {
		Json json = new Json();
		((SyuserServiceI) service).grantOrganization(id, ids);
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 统计用户注册时间图表
	 */
	public void doNotNeedSecurity_userCreateDatetimeChart() {
		writeJson(((SyuserServiceI) service).userCreateDatetimeChart());
	}

	/**
	 * 新建一个用户
	 */
	@Override
	synchronized public void save() {
		Json json = new Json();
		if (data != null) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
			Syuser user = service.getByFilter(hqlFilter);
			if (user != null) {
				json.setMsg("新建用户失败，用户名已存在！");
			} else {
				data.setPwd(MD5Util.md5("123456"));
				service.save(data);
				json.setMsg("新建用户成功！默认密码：123456");
				json.setSuccess(true);
			}
		}
		writeJson(json);
	}

	/**
	 * 更新一个用户
	 */
	@Override
	synchronized public void update() {
		Json json = new Json();
		json.setMsg("更新失败！");
		if (data != null && !StringUtils.isBlank(data.getId())) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#id_S_NE", data.getId());
			hqlFilter.addFilter("QUERY_t#loginname_S_EQ", data.getLoginname());
			Syuser user = service.getByFilter(hqlFilter);
			if (user != null) {
				json.setMsg("更新用户失败，用户名已存在！");
			} else {
				Syuser t = service.getById(data.getId());
				BeanUtils.copyNotNullProperties(data, t,
						new String[] { "createdatetime" });
				service.update(t);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			}
		}
		writeJson(json);
	}

	/**
	 * 用户登录页面的自动补全
	 */
	public void doNotNeedSessionAndSecurity_loginNameComboBox() {
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_1);
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#loginname_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		hqlFilter.addSort("t.loginname");
		hqlFilter.addOrder("asc");
		writeJsonByIncludesProperties(service.findByFilter(hqlFilter, 1, 10),
				new String[] { "loginname" });
	}

	/**
	 * 用户登录页面的grid自动补全
	 */
	public void doNotNeedSessionAndSecurity_loginNameComboGrid() {
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.DATA_SOURCE_1);
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#loginname_S_LK",
				"%%" + StringUtils.defaultString(q) + "%%");
		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	
	
	
	/**
	 * userTree
	 * */
	
	public void userTreeGird(){
		

		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String pid=sessionInfo.getUser().getEmpId();
//		hqlFilter.addFilter("QUERY_user#id_S_EQ", sessionInfo.getUser().getId());
//		hqlFilter.addFilter("QUERY_t#syresourcetype#id_S_EQ", "0");// 0就是只查菜单
		//service.findBySql(sql, params);
//		List<Syresource> resources = ((SyresourceServiceI) service).getMainMenu(hqlFilter);
//		List<Tree> tree = new ArrayList<Tree>();
//		for (Syresource resource : resources) {
//			Tree node = new Tree();
//			BeanUtils.copyNotNullProperties(resource, node);
//			node.setText(resource.getName());
//			Map<String, String> attributes = new HashMap<String, String>();
//			attributes.put("url", resource.getUrl());
//			attributes.put("target", resource.getTarget());
//			node.setAttributes(attributes);
//			tree.add(node);
//		}
//		writeJson(tree);
	
	}
	
	/**
	 * 以邮件
	 * */
	public void sendEmail(){
		//HqlFilter hqlFilter = new HqlFilter(getRequest());
		//hqlFilter
		List<String> idlist=new ArrayList<String>();
		String[]idss=id.split("\\+");
		String[] to=email.split("\\+");
		for (int i = 0; i < to.length; i++) {
			System.out.println("emailss:"+to[i]);
		}
		for (int i = 0; i < idss.length; i++) {
			System.out.println("idss:"+idss[i]);
		}
		System.out.println("id:"+id);
		System.out.println("email:"+email);
		SendMail sendMail=new SendMail();

		//String to[] = { "723836333@qq.com"};
		String cs[] = null;
		String ms[] = null;
		//String subject = "测试一下111";
		//String content = "这是邮件内容，仅仅是测试，不需要回复1111";
		//String formEmail = "442876771@qq.com";
		String[] arrArchievList = new String[3];
		arrArchievList[0] = "d://Betrayal.jpg";
		arrArchievList[1] = "d://Betrayal.jpg";
		arrArchievList[2] = "d://Betrayal.jpg";
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		String myemail=sessionInfo.getUser().getEmail();
		System.out.println("pwds:"+pwds);
		System.out.println("content:"+content);
		System.out.println("subject:"+subject);
		System.out.println("myemail:"+myemail);
		sendMail.send(to, cs, ms, subject, content, myemail,pwds, arrArchievList);
		
	}
	/**
	 * 重置密码
	 */
	public void resetPwd(){
		Json json=new Json();
		Syuser t = service.getById(id);
		t.setPwd(MD5Util.md5("123456"));
		t.setIsPwd(0);
		service.update(t);
		json.setSuccess(true);
		json.setMsg("重置成功！");
		writeJson(json);
	}
	/**
	 * 离职处理，该用户将不能登录，同时删除syempdata记录，记录移至leave_emp
	 */
	public void leaveUser(){
		Json json=new Json();
		((SyuserServiceI) service).updateLeaveUser(id);
		json.setSuccess(true);
		json.setMsg("离职处理成功！");
		writeJson(json);
	}
	/**
	 * 查询出未离职的员工
	 */
	@Override
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#isLeave_S_EQ", "0");
		hqlFilter.addFilter("QUERY_t#loginname_S_NE", "admin");
		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	/**
	 * 设置主角色（主舱）
	 */
	public void doNotNeedSessionAndSecurity_mainRole(){
		Json json=new Json();
		String sql="update syuser t set t.main_role='"+ids+"' where t.id='"+id+"'";
		service.executeSql(sql);
		json.setSuccess(true);
		json.setMsg("设置成功！");
		writeJson(json);
	}

}
