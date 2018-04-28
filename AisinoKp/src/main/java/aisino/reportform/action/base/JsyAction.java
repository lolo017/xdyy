package aisino.reportform.action.base;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.base.Syresource;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.service.base.SyresourceServiceI;
import aisino.reportform.util.base.DbUtil;
@Namespace("/base")
@Action
public class JsyAction extends BaseAction<Syresource>{
	private String id;//订单编号
	private String dm;//物流公司编号
	private String no;//物流编号
	private String status;//状态 0投递中，1投递成功
	private String mx;//明细
	
	
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMx() {
		return mx;
	}
	public void setMx(String mx) {
		this.mx = mx;
	}
	@Autowired
	public void setService(SyresourceServiceI service) {
		this.service = service;
	}
	/**
	 * 保存物流信息
	 */
	public void doNotNeedSecurity_saveLogistics(){
		DbUtil.setDb("3");
		Json json = new Json();
		String sql1="select count(*) from JSPAY_LOGISTICS t where t.id='"+id+"'";
		long num=service.countBySql(sql1);
		if(num>0){
			json.setSuccess(false);
			json.setMsg("物流信息已存在!");
		}else{
			String sql2="insert into JSPAY_LOGISTICS values('"+id+"','"+dm+"','"+no+"','"+status+"',sysdate,'"+mx+"',sysdate)";
			service.executeSql(sql2);
			json.setSuccess(true);
			json.setMsg("物流信息录入成功!");
		}
		writeJson(json);
	}
	/**
	 * 查询物流公司代码
	 */
	public void doNotNeedSecurity_getDm(){
		DbUtil.setDb("3");
		String sql="select t.id as value,t.mc as text from JSPAY_DM_LOGISTICS t";
		List<Map> l=service.findBySql(sql);
		writeJson(l);
		
	}
	/**
	 * 查询订单商品明细
	 */
	public void doNotNeedSecurity_getSpmx(){
		DbUtil.setDb("3");
		String sql="select m.id,m.spdm,s.mc,m.price,m.amount,m.total from jspay_order_mx m left join jspay_sp_mx s on m.spdm=s.id where m.id='"+id+"'";
		List<Map> l=service.findBySql(sql);
		writeJson(l);
	}

}
