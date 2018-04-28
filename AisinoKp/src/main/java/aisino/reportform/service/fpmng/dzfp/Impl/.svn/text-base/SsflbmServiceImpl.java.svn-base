package aisino.reportform.service.fpmng.dzfp.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.fpmng.Ssflbm;
import aisino.reportform.service.fpmng.dzfp.SsflbmServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;

@Service
public class SsflbmServiceImpl extends BaseServiceImpl<Ssflbm> implements SsflbmServiceI{

	@Override
	public Grid getSsflbmGrid(StringBuffer buff, int page, int rows,
			String sort, String order) {
		Grid grid = new Grid();
		if (sort == null || "".equals(sort)) {
			sort = "CREATETIME";
			order = "desc";
		}
		String sql="select top "+rows+" ID,SPHM,SPMC,GGXH,DW,SLV,SSFLBM,CREATETIME from "
				+ "(select row_number()over(order by "+sort+" "+order+")rownumber,t.* from t_ssflbm t where 1=1 "+buff.toString()+" ) a "
				+ "where rownumber> "+(page-1)*rows+" ";
		String sql_count="select count(1) from t_ssflbm t where 1=1 "+buff.toString();
		
		try {
			List list=findBySql(sql);
			grid.setRows(list);//从t_ssflbm获取grid
			grid.setTotal(countBySql(sql_count));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grid;
	}

}
