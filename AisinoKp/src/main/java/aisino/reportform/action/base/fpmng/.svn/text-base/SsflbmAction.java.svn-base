
package aisino.reportform.action.base.fpmng;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import aisino.reportform.action.BaseAction;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.easyui.Json;
import aisino.reportform.model.fpmng.Ssflbm;
import aisino.reportform.service.fpmng.dzfp.SsflbmServiceI;

/**
 * 
* @Title:EInvoiceAction 
* @Description: 电子发票管理
* Company    JS-YFB LTD
* @author 曹梦媛
* @version V1.0    
* @date 2017年11月22日 下午5:25:27
 */
@Namespace("/base/fpmng")
@Action
public class SsflbmAction extends BaseAction<Ssflbm>{
	
	@Autowired
	public SsflbmServiceI bmservice;	//税收分类编码Service注入
	
	/**
	 * 
	 * @Title: getSsflbmGrid
	 * @Description: 税收分类编码列表显示
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void getSsflbmGrid(){
		Grid grid = new Grid();
		try {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			
			//条件筛选:
			String sphm = request.getParameter("sphm");//商品号码
			String spmc = request.getParameter("spmc");//商品名称
			
		  
			StringBuffer buff = new StringBuffer();
			if(sphm != null && !"".equals(sphm)){
				buff.append( " and t.sphm like '%"+sphm+"%' ");
			}
			if(spmc != null && !"".equals(spmc)){
				buff.append( " and t.spmc like '%"+spmc+"%' ");
			}
			
			grid = bmservice.getSsflbmGrid(buff, page, rows, sort, order );
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeJson(grid);
			
		}
	}
		
		
	/**
	 * 
	 * @Title: saveSsflbm
	 * @Description: 新增税收分类编码
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void saveSsflbm(){
		Json json = new Json();
//		data.setId(UUID.randomUUID().toString());
		if (data != null) {
			bmservice.save(data);
			json.setSuccess(true);
			json.setMsg("保存成功！");
		}
		writeJson(json);
	}
	
	/**
	 * 
	 * @Title: updateSsflbm
	 * @Description: 修改税收分类编码
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void updateSsflbm() {
		Json json=new Json();
		if (data!=null) {
			bmservice.update(data);
			json.setSuccess(true);
			json.setMsg("保存成功！");
		}
		writeJson(json);
	}
	
	/**
	 * @Title: deleteSsflbm
	 * @Description: 删除税收分类编码
	 * @param:  
	 * @return: void   
	 * @throws
	 */
	public void deleteSsflbm() {
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			Ssflbm ss=bmservice.getById(id);
			bmservice.delete(ss);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}
	
	public void doNotNeedSecurity_getById() {
		if (!StringUtils.isBlank(id)) {
			writeJson(bmservice.getById(id));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
}



