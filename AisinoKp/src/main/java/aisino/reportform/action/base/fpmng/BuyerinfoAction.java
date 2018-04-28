package aisino.reportform.action.base.fpmng;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.BuyerInfo;
import aisino.reportform.service.fpmng.BuyerInfoServiceI;
import aisino.reportform.util.base.StringUtil;

/**
 * 
* @Title:BuyerinfoAction 
* @Description: 购方信息维护
* Company    JS-YFB LTD
* @author 吕振宇
* @version V1.0    
* @date 2017年12月22日 上午10:42:08
 */
@Namespace("/base/fpmng")
@Action
public class BuyerinfoAction extends BaseAction<BuyerInfo>{
	private String id; //主键id
	private String gfhm; //购方号码
	private String gfmc; //购方名称
	private String gfsh; //购方税号
	private String dzdh; //地址电话
	private String yhzh; //银行账户
	private String mobile; //手机号码
	private String email; //邮箱
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	public String getGfhm() {
		return gfhm;
	}


	public void setGfhm(String gfhm) {
		this.gfhm = gfhm;
	}


	public String getGfmc() {
		return gfmc;
	}


	public void setGfmc(String gfmc) {
		this.gfmc = gfmc;
	}


	public String getGfsh() {
		return gfsh;
	}


	public void setGfsh(String gfsh) {
		this.gfsh = gfsh;
	}


	public String getDzdh() {
		return dzdh;
	}


	public void setDzdh(String dzdh) {
		this.dzdh = dzdh;
	}


	public String getYhzh() {
		return yhzh;
	}


	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Autowired
	private BuyerInfoServiceI buyerService;
	
	
	public void infoGrid(){
		String gfmc=getRequest().getParameter("gfmc");
		String gfsh=getRequest().getParameter("gfsh");
		Grid grid=new Grid();
		
		List<BuyerInfo> list=new ArrayList<>();
		if(!StringUtils.isBlank(gfmc)){
			list=buyerService.find("from BuyerInfo t where t.gfmc like '%"+gfmc+"%'", page, rows);
		}
		else if(!StringUtils.isBlank(gfsh)){
			list=buyerService.find("from BuyerInfo t where t.gfsh like '%"+gfsh+"%'", page, rows);
		}else{
			list=buyerService.find(page, rows);
		}
		
		grid.setRows(list);
		grid.setTotal((long) rows);
		writeJson(grid);
	}
	
	@Override
	public void save(){
		Json json = new Json();
		data.setId(UUID.randomUUID().toString());
		if (data != null) {
			buyerService.save(data);
			json.setSuccess(true);
			json.setMsg("保存成功！");
		}
		writeJson(json);
	}
	@Override
	public void delete(){
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			 BuyerInfo buyer=buyerService.getById(id);
			 buyerService.delete(buyer);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}
	@Override
	public void getById() {
		if (!StringUtils.isBlank(id)) {
			writeJson(buyerService.getById(id));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	@Override
	public void update(){
		Json json=new Json();
		if (data!=null) {
			buyerService.update(data);
			json.setSuccess(true);
			json.setMsg("保存成功！");
		}
		writeJson(json);
	}
}
