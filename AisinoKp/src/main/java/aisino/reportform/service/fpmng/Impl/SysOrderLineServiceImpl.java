package aisino.reportform.service.fpmng.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.action.base.SysorgAction;
import aisino.reportform.model.easyui.Grid;
import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.xd.OrderInfo;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;
import aisino.reportform.service.fpmng.xd.OrderInfoServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
import aisino.reportform.util.base.BeanUtils;
import aisino.reportform.util.base.BigdecimalUtil;
import aisino.reportform.util.base.DbUtil;
import aisino.reportform.util.base.xd.OrderNumUtil;

@Service
public class SysOrderLineServiceImpl extends BaseServiceImpl<SysOrderLine>
		implements SysOrderLineServiceI {

	@Autowired
	private SysOrderHeadServiceI ohservice;
	@Autowired
	private OrderDataZServiceI odservice;
	@Autowired
	public OrderInfoServiceI odinfoservice;

	@Override
	public Grid checkOrderDetail(String ohid, int page, int rows) {
		Grid grid = new Grid();
		String sql = "select t.* from t_sysorderline t where t.ohid = '" + ohid
				+ "' order by t.spxh, t.je desc ";
		String sql_count = "select count(1) from t_sysorderline t where t.ohid = '"
				+ ohid + "' ";

		grid.setRows(findBySql(sql, page, rows));// 从系统单据列表获取单据明细grid
		grid.setTotal(countBySql(sql_count));
		return grid;
	}

	@Override
	public String getOlidsByOhid(String ohid) {
		String sql_oh = " select t.olid from t_sysorderline t where t.ohid = '"
				+ ohid + "' ";
		List<HashMap> ols = findBySql(sql_oh);
		String olids = "";
		for (int i = 0; i < ols.size(); i++) {
			olids += "'" + ols.get(i).get("olid").toString() + "',";
		}
		olids = olids.substring(0, olids.lastIndexOf(","));

		// System.out.println(olid);

		return olids;
	}

	@Override
	public int updateKPBZ(String olids) {
		int rs = 0; // 更新t_sysorderline表的记录数
		int rs_d = 0; // 更新t_orderdata表的记录数
		// 更新t_sysorderline表
		if (olids != null && !"".equals(olids)) {
			String sql_ol = "update t_sysorderline set kpbz=1 where olid in ("
					+ olids + ") ";
			rs = executeSql(sql_ol, true);
			// System.out.println(rs);
		}
		// 更新t_orderdata表
		String odids = "";
		odids = getOdidsByOlids(olids);
		if (odids != "") {
			String sql_od = "update t_orderdata set kpbz=1 where odid in ("
					+ odids + ") ";
			rs_d = executeSql(sql_od, true);
		}
		return rs_d;
	}

	@Override
	public String getOdidsByOlids(String olids) {
		String odids = "";
		String sql_oh = " select t.odid from t_order_line t where t.olid in ("
				+ olids + ") ";
		List<HashMap> ols = findBySql(sql_oh);
		for (int i = 0; i < ols.size(); i++) {
			odids += "'" + ols.get(i).get("odid").toString() + "',";
		}
		odids = odids.substring(0, odids.lastIndexOf(","));

		return odids;
	}

	public List<HashMap> getorderlinelist(String ohid) {
		List<HashMap> orderlineList = findBySql("select * from t_sysorderline where ohid='"
				+ ohid + "'");
		return orderlineList;
	}

	@Override
	public void savesplitByTax(String ohid) {
		SysOrderHead org_head = ohservice.getById(ohid);
		List<SysOrderLine> ol_list = find("from SysOrderLine where ohid='"
				+ ohid + "'");
		List<BigDecimal> slv_list = new ArrayList<>();
		for (SysOrderLine ol : ol_list) {
			if (!slv_list.contains(ol.getSlv())) {
				slv_list.add(ol.getSlv());
			}

		}
		// 订单中存在多税率单据时拆分
		if (slv_list.size() > 1) {
			for (BigDecimal slv : slv_list) {
				SysOrderHead oh = new SysOrderHead();
				oh.setTotal(new BigDecimal(0));
				oh.setAmount(new BigDecimal(0));
				BeanUtils.copyNotNullProperties(org_head, oh);
				String uuid = UUID.randomUUID().toString();
				oh.setOhid(uuid);
				List<SysOrderLine> tmp_list = new ArrayList<>();

				BigDecimal new_total = new BigDecimal(0);
				for (SysOrderLine ol : ol_list) {
					if (ol.getSlv().equals(slv)) {
						org_head.getSysOrderLines().remove(ol);
						ol.setSysOrderHead(oh);
						tmp_list.add(ol);
						new_total = BigdecimalUtil.add(new_total, ol.getJe());
					}
				}
				try {
					// 拆分后总金额、税率、商品行数
					oh.setSphs(tmp_list.size());
					oh.setTotal(new_total);
					oh.setSysOrderLines(tmp_list);
					oh.setSlv(slv.toString());
					oh.setMix("0");
					ohservice.save(oh);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// 拆分完成，删除原始订单
			ohservice.delete(org_head);
		}

	}

	@Override
	public void save_recoverOrder(SysOrderHead oh) {
		// executeSql("delete t_sysorderhead where ohid='"+ohid+"'");
		try {

			// 获取所有商品行中的orderdata,存入org_list
			List<SysOrderLine> ollist = oh.getSysOrderLines();
			List<OrderDataZ> org_list = new ArrayList<>();
			for (SysOrderLine ol : ollist) {
				List<OrderDataZ> odlist = ol.getOrderDataZs();
				if (!org_list.contains(odlist)) {
					// 删除ol数据
					delete(ol);
					org_list.addAll(odlist);
				}
			}
			oh.setSysOrderLines(null);
			ohservice.delete(oh);
			// 遍历原始单据
			for (OrderDataZ od : org_list) {

				od.setHqsj(null);
				od.setSysOrderlines(null);
				// 删除od数据
				// odservice.delete(od);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save_returnOrder(SysOrderHead oh) {
		try {

			// 获取所有商品行中的orderdata,存入org_list
			List<SysOrderLine> ollist = oh.getSysOrderLines();
			List<OrderDataZ> org_list = new ArrayList<>();
			for (SysOrderLine ol : ollist) {
				List<OrderDataZ> odlist = ol.getOrderDataZs();
				if (!org_list.contains(odlist)) {
					// 删除ol数据
					delete(ol);
					org_list.addAll(odlist);
				}
			}
			oh.setIs_valid("0");
			oh.setSysOrderLines(null);
			ohservice.delete(oh);
			
			// 遍历原始单据
			for (OrderDataZ od : org_list) {

				od.setHqsj(null);
				od.setSysOrderlines(null);
				// 删除od数据
				odservice.delete(od);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save_mixOrderGoods(SysOrderHead oh, boolean isNega) {
		String ohid = oh.getOhid();
		// 判断是否为负数商品行汇总
		String selectSql = "";
		if (isNega) {
			selectSql = "from SysOrderLine where ohid='" + ohid + "' and je<0";
		} else {
			selectSql = "from SysOrderLine where ohid='" + ohid + "'";
		}

		try {

			List<SysOrderLine> list = find(selectSql);
			outer: while (true) {
				for (int j = 0; j < list.size(); j++) {
					SysOrderLine orderline = list.get(j);
					// SysOrderLine orderline=new SysOrderLine();
					// BeanUtils.copyNotNullProperties(list.get(j), orderline);
					for (int i = 0; i < list.size(); i++) {
						if (i != j) {
							SysOrderLine orderline1 = list.get(i);
							// SysOrderLine orderline1 =new SysOrderLine();
							// BeanUtils.copyNotNullProperties(list.get(i),
							// orderline1);
							// 汇总条件判断相同商品行合并
							if (orderline.compare(orderline1)) {
								// 计算合并后商品数量、金额
								BigDecimal sl = orderline.getSl();
								BigDecimal sl1 = orderline1.getSl();

								BigDecimal je = orderline.getJe();
								BigDecimal je1 = orderline1.getJe();
								if (sl != null) {
									if (sl1 != null) {
										orderline.setSl(BigdecimalUtil.add(sl,
												sl1));

									}
								} else {
									orderline.setSl(sl1);
								}
								if (je != null) {
									if (je1 != null) {
										orderline.setJe(BigdecimalUtil.add(je,
												je1));

									}
								} else {
									orderline.setJe(je1);

								}
								// Map hashmap=new HashMap<>();
								// 更新汇总后list中金额数量
								list.get(j).setJe(orderline.getJe());
								list.get(j).setSl(orderline.getSl());
								list.set(j, list.get(j));
								// update(orderline);
								// 更新orderline与orderdata对应关系
								orderline.getOrderDataZs().addAll(
										orderline1.getOrderDataZs());
								update(orderline);
								// orderline1关联关系
								orderline1.setOrderDataZs(null);
								orderline1.setSysOrderHead(null);
								oh.getSysOrderLines().remove(orderline1);
								ohservice.update(oh);
								// update(orderline1);

								delete(orderline1);
								// 删除orderline1
								// String delsql =
								// "delete from t_SysOrderLine where olid='"
								// + orderline1.getOlid() + "'";
								// executeSql(delsql);
								// 汇总 oderhead商品行数-1
								oh.setSphs(oh.getSphs() - 1);
								list.remove(i);
								continue outer;
							}
						}

					}
				}
				break outer;
			}
			ohservice.update(oh);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save_new_orderline(List<OrderInfo> odlist,String kpr) {
		//生成新单据头、单据
		SysOrderHead oh=new SysOrderHead();
		if(odlist.size()==0){
			return;
		}
		OrderInfo orderinfo=odlist.get(0);
		List<SysOrderLine> orderline_list=new ArrayList<>();
		String fpzl="";
	      
		BigDecimal total=new BigDecimal(0);
		for (OrderInfo od : odlist) {

			SysOrderLine newLine = new SysOrderLine();
			newLine.setOlid(od.getOdid());
			newLine.setCreatetime(od.getCreatetime());
			newLine.setDj(od.getDj());
			newLine.setDw(od.getDw());
			newLine.setEmail(od.getEmail());
			newLine.setFhr("周毅");
			newLine.setFenpiaobiaoshi(od.getFenpiaobiaoshi());
			newLine.setBatchno(od.getBatchno());
			if ("电子发票".equals(od.getFpzl())) {
				fpzl="51";
				newLine.setFpzl("51");
		      } else if ("增值税专用发票".equals(od.getFpzl())) {
		    	  fpzl="0";
		    	  newLine.setFpzl("0");
		      } else if ("增值税普通发票".equals(od.getFpzl())) {
		    	  fpzl="2";
		    	  newLine.setFpzl("2");
		      } else if ("普通发票".equals(od.getFpzl())) {
		    	  fpzl="41";
		    	  newLine.setFpzl("41");
		      }else{
		    	  fpzl="20";
		    	  newLine.setFpzl("41");
		      }
			
			newLine.setGfdzdh(od.getGfdzdh());
			newLine.setGfhm(od.getGfhm());
			newLine.setGfmc(od.getGfmc());
			newLine.setGfsh(od.getGfsh());
			newLine.setGfyhzh(od.getKhh()+" "+od.getGfyhzh());
			newLine.setGgxh(od.getGgxh());
			newLine.setHsbz(od.getHsbz());
			newLine.setJe(od.getJe());
			newLine.setSkr("贺敏");
			newLine.setSl(od.getSl());
			newLine.setSlv(od.getSlv());
			newLine.setSphm(od.getSphm());
			newLine.setSpmc(od.getSpmc());
			newLine.setSpxh(od.getSpxh());
			newLine.setSsflbm(od.getSsflbm());
			newLine.setKpbz("0");
			newLine.setGfyhzh(od.getKhh());
			newLine.setSysOrderHead(oh);
			total=BigdecimalUtil.add(total, od.getJe());
//			odlist.add(od);
			List<SysOrderLine> ollist = new ArrayList<>();
			ollist.add(newLine);
			orderline_list.add(newLine);
			

		}
		oh.setOrigin(orderinfo.getOrigin());
		oh.setYwy(orderinfo.getYwy());
		oh.setEmail(orderinfo.getEmail());
		oh.setOhid(OrderNumUtil.makeOrderNum());
		oh.setCreatetime(new Date());
		oh.setFhr("周毅");
		oh.setFpzl(fpzl);
		oh.setGfdzdh(orderinfo.getGfdzdh());
		oh.setGfhm(orderinfo.getGfhm());
		oh.setGfmc(orderinfo.getGfmc());
		oh.setGfsh(orderinfo.getGfsh());
//		oh.setGfyhzh(orderinfo.getGfyhzh());
		oh.setGfyhzh(orderinfo.getKhh()+" "+orderinfo.getGfyhzh());
		oh.setKpr(kpr);
		if(orderline_list.size()>8){
			oh.setHas_qd("1");
		}else{
			oh.setHas_qd("0");
		}
		saveList(orderline_list);
		oh.setIs_hx("0");
		oh.setIs_qz("0");
		oh.setIs_tsyx("0");
		oh.setIs_valid("1");
		oh.setSkr("贺敏");
		oh.setSphs(orderline_list.size());
		oh.setSlv(orderinfo.getSlv().toString());
		oh.setSysOrderLines(orderline_list);
		oh.setTotal(total);

		ohservice.save(oh);
	}

	@Override
	public String generate_OrderheadID(String djhm) {
		List<HashMap> list = findBySql("select count (*) count from t_sysorderhead where ohid like '"
				+ djhm + "%'");
		int c = (int) list.get(0).get("count");
		if (c >= 1) {
			c++;
			return djhm + "_" + c;
		} else {
			return djhm;
		}
	}

	@Override
	public String discountE(String ohid, BigDecimal rate) {
		List<SysOrderLine> orderlinelist = find("from SysOrderLine where ohid='"
				+ ohid + "'");
		// 计算负数商品总金额是否超过折扣率金额
		List<SysOrderLine> positiveList = new ArrayList<>();
		List<SysOrderLine> negativeList = new ArrayList<>();
		BigDecimal positiveTotal = new BigDecimal(0);
		BigDecimal negativeTotal = new BigDecimal(0);
		for (SysOrderLine orderline : orderlinelist) {
			if (orderline.getJe().compareTo(new BigDecimal(0)) == 1) {
				positiveTotal = BigdecimalUtil.add(positiveTotal,
						orderline.getJe());
				positiveList.add(orderline);
			}
			// 金额为负数商品行存入negativeList
			else if (orderline.getJe().compareTo(new BigDecimal(0)) == -1) {
				negativeTotal = BigdecimalUtil.add(negativeTotal,
						orderline.getJe());
				negativeList.add(orderline);
			}
		}
		if (negativeTotal.abs().compareTo(positiveTotal.multiply(rate)) == -1
				&& positiveTotal.compareTo(new BigDecimal(0)) == 1) {
			String sql = "update t_sysorderline set sphm='',spmc='折扣',ggxh='',dw='',sl=0,dj=0,SSFLBM='' where "
					+ "ohid='" + ohid + "' and je<0";
			executeSql(sql, true);

			return "1";
		}
		// 超过折扣率 返回单据号
		else {
			return ohid;
		}

	}

	@Override
	public int updateZkmc(SysOrderHead oh) {
		List<SysOrderLine> ollist = oh.getSysOrderLines();
		int count = 0;
		for (SysOrderLine ol : ollist) {
			if (!(ol.getJe().compareTo(new BigDecimal(0)) == 1)) {
				ol.setDj(new BigDecimal(0));
				ol.setDw("");
				ol.setSpmc("折扣行");
				ol.setGgxh("");
				ol.setSsflbm("");
				ol.setSphm("");
				update(ol);
				count++;
			}
		}
		return count;
		// 更新折扣行名字
		// String sql =
		// "update t_sysorderline set spmc='折扣行数'+cast((select COUNT(1) from t_sysorderline where ohid = '"
		// + ohid
		// + "')-1 as varchar(8))+'(10.000%)',"
		// + "spxh=(select MAX(spxh)+1 from t_sysorderline where ohid = '"
		// + ohid
		// + "' and spmc not like '折扣%') "
		// + " where ohid='"
		// + ohid
		// + "' and spmc like '折扣%'";
		// executeSql(sql,true);

	}

	@Override
	public void save_NewInvoiceInfo(SysOrderHead oh, int zkh_count) {
		
		//处理多税率订单折扣
		if (oh.getMix().equals("1")) {
			List<SysOrderLine> orderlines = oh.getSysOrderLines();
			Set<BigDecimal> slv_set=new HashSet<>();
			//获取订单税率，存入slv_set,更新序号
			int spxh=1;
			for(SysOrderLine ol:orderlines){
				slv_set.add(ol.getSlv());
				ol.setSpxh(spxh);
				spxh++;
				update(ol);
			}
			Iterator<BigDecimal> it=slv_set.iterator();
			//正数商品行技术，用于计算折扣后商品行数
			int positive_count=0;
			//循环获取不同税率
			while (it.hasNext()) {
				BigDecimal current=it.next();
				//相同税率的商品行放入same_slv_list
				List<SysOrderLine> same_slv_list=new ArrayList<>();
				for(SysOrderLine ol:orderlines){
					if(ol.getSlv().compareTo(current)==0){
						same_slv_list.add(ol);
					}
				}
				
				BigDecimal positiveTotal = new BigDecimal(0); // 每种税率正数总金额
				for(SysOrderLine ol:same_slv_list){
					if (ol.getJe().compareTo(new BigDecimal(0)) == 1) {
						positiveTotal = BigdecimalUtil.add(positiveTotal,
								ol.getJe());
						positive_count++;
					}
					
				}
				//构造折扣行
				SysOrderLine zkh = same_slv_list.get(same_slv_list.size() - 1);
				if (zkh.getJe().compareTo(new BigDecimal("0.00")) == -1) {
					BigDecimal zzkje = zkh.getJe();
					List<OrderDataZ> od_list = new ArrayList<>();
					for (OrderDataZ od : zkh.getOrderDataZs()) {
						od_list.add(od);
					}
					for (int i = 0; i < same_slv_list.size() - 1; i++) {
						SysOrderLine zkh_sp = new SysOrderLine(); // 折扣行商品
						SysOrderLine zjh_sp = same_slv_list.get(i); // 正价行商品
						BeanUtils.copyProperties(zjh_sp, zkh_sp);
						// 均摊后的折扣金额
						BigDecimal zkje = zzkje.multiply(zjh_sp.getJe().divide(
								positiveTotal, 10, BigDecimal.ROUND_HALF_UP));
						BeanUtils.copyNotNullProperties(zjh_sp, zkh_sp);
						zkh_sp.setOlid(UUID.randomUUID().toString());
						zkh_sp.setJe(zkje);
						zkh_sp.setSl(zkje.divide(zjh_sp.getDj(), 10,
								BigDecimal.ROUND_HALF_UP));
						zkh_sp.setOrderDataZs(od_list);
						zkh_sp.setSysOrderHead(oh);
						save(zkh_sp);
					}
					zkh.getOrderDataZs().clear();
					oh.getSysOrderLines().remove(zkh);
					// 计算折扣后订单商品行数
					delete(zkh);
				}
				
			}
			oh.setSphs(positive_count*2);
			ohservice.update(oh);
		} 
		//处理单税率折扣
		else {
			BigDecimal positiveTotal = new BigDecimal(0); // 正数总金额
			List<SysOrderLine> orderlines = oh.getSysOrderLines();
			// orderlines=sortRevOrderLineList(orderlines);
			//计算订单正价总金额
			for (int i = 0; i < orderlines.size(); i++) {
				SysOrderLine ol = orderlines.get(i);
				ol.setSpxh(i + 1);
				update(ol);
				if (ol.getJe().compareTo(new BigDecimal(0)) == 1) {
					positiveTotal = BigdecimalUtil.add(positiveTotal,
							ol.getJe());
				}
			}
			//构造折扣行
			//取折扣汇总后最后一行负数总金额
			SysOrderLine zkh = orderlines.get(orderlines.size() - 1);
			if (zkh.getJe().compareTo(new BigDecimal("0.00")) == -1) {
				BigDecimal zzkje = zkh.getJe();
				List<OrderDataZ> od_list = new ArrayList<>();
				for (OrderDataZ od : zkh.getOrderDataZs()) {
					od_list.add(od);
				}
				BigDecimal res=new BigDecimal(0);
				for (int i = 0; i < orderlines.size() - 1; i++) {
					SysOrderLine zkh_sp = new SysOrderLine(); // 折扣行商品
					SysOrderLine zjh_sp = orderlines.get(i); // 正价行商品
					BeanUtils.copyProperties(zjh_sp, zkh_sp);
					// 均摊后的折扣金额
					BigDecimal zkje =new BigDecimal(0);
					if(!(i==(orderlines.size() - 2))){
						zkje=zzkje.multiply(zjh_sp.getJe().divide(
								positiveTotal, 10, BigDecimal.ROUND_HALF_UP));
						res=BigdecimalUtil.add(res, zkje);
					}else{
						zkje=BigdecimalUtil.add(zzkje, res.abs());
					}
					
					BeanUtils.copyNotNullProperties(zjh_sp, zkh_sp);
					zkh_sp.setOlid(UUID.randomUUID().toString());
					zkh_sp.setJe(zkje);
					// zkh_sp.setDj(new BigDecimal("0"));
//					zkh_sp.setSl(zkje.divide(zjh_sp.getDj(), 10,BigDecimal.ROUND_HALF_UP));
					zkh_sp.setOrderDataZs(od_list);
					// zkh_sp.getOrderDataZs().addAll(zkh.getOrderDataZs());
					zkh_sp.setSysOrderHead(oh);
					save(zkh_sp);
				}
				zkh.getOrderDataZs().clear();
				oh.getSysOrderLines().remove(zkh);
				// 计算折扣后订单商品行数
				oh.setSphs((oh.getSphs() - zkh_count) * 2);
				ohservice.update(oh);
				delete(zkh);

			}
		}

	}

	// 按商品行金额排序 降序
	private static List<SysOrderLine> sortRevOrderLineList(
			List<SysOrderLine> list) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = i; j < list.size(); j++) {
				if (list.get(i).getJe().compareTo(list.get(j).getJe()) == -1) {
					SysOrderLine tmp = new SysOrderLine();
					BeanUtils.copyNotNullProperties(list.get(i), tmp);
					BeanUtils.copyNotNullProperties(list.get(j), list.get(i));
					BeanUtils.copyNotNullProperties(tmp, list.get(j));
				}
			}
		}
		return list;
	}

	@Override
	public void delete_return_Order(SysOrderHead oh) {
		try {
			// 获取所有商品行中的orderdata,存入org_list
			List<SysOrderLine> ollist = oh.getSysOrderLines();
			List<OrderDataZ> org_list = new ArrayList<>();
			for (SysOrderLine ol : ollist) {
				List<OrderDataZ> odlist = ol.getOrderDataZs();
				if (!org_list.contains(odlist)) {
					delete(ol);
					org_list.addAll(odlist);
				}
			}
			ohservice.delete(oh);
			// 遍历原始单据删除
			for (OrderDataZ od : org_list) {
				od.setSysOrderlines(null);
				odservice.delete(od);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String save_splitByLineOver(int max_size, String ohid) {
		SysOrderHead orderhead = ohservice
				.getByHql("from SysOrderHead where ohid='" + ohid + "'");
		List<SysOrderLine> orderlineList = find("from SysOrderLine where ohid='" + ohid + "' order by spxh asc");
		int sphs = orderhead.getSphs();
		// 商品行超过最大行数，进行拆分
		if (sphs > max_size) {
			// 商品行取余数
			int ys = sphs % max_size;

			// 计算拆分后orderhead数量
			int count = 0;
			if (ys != 0) {
				count = sphs / max_size + 1;
			} else {
				count = sphs / max_size;
			}
			for (int i = 0; i < count; i++) {
				SysOrderHead newOrderHead = new SysOrderHead();
				BeanUtils.copyNotNullProperties(orderhead, newOrderHead);
				String newOhid = UUID.randomUUID().toString();
				List<SysOrderLine> sublist = new ArrayList<>();
				if (i == (count - 1)&&ys>0) {
					sublist = orderlineList.subList(i * max_size, i * max_size
							+ ys);
				} else {
					sublist = orderlineList.subList(i * max_size, max_size
							* (i + 1));
				}
				int sphsCount = 0;
				BigDecimal totalSum = new BigDecimal(0);
				// for(SysOrderLine orderline:sublist){
				for (int j = 0; j < sublist.size(); j++) {
					SysOrderLine orderline = sublist.get(j);
					orderline.setSysOrderHead(newOrderHead);
					update(orderline);
					// executeSql("update t_sysorderline set ohid='"+newOhid+"' where olid='"+sublist.get(j).getOlid()+"'");

					totalSum = BigdecimalUtil.add(totalSum, sublist.get(j)
							.getJe());
					sphsCount++;
				}
				newOrderHead.setSysOrderLines(sublist);
				newOrderHead.setOhid(newOhid);
				newOrderHead.setSphs(sphsCount);
				newOrderHead.setTotal(totalSum);
				newOrderHead.setHas_qd("0");
				ohservice.save(newOrderHead);
				// save(newOrderHead);
			}
			executeSql("delete t_sysorderhead where ohid='"
					+ orderhead.getOhid() + "'");
			return "1";
		} else {
			return "0";
		}
	}

	@Override
	public String save_splitOrderByAmount(String ohid, String limitAmount)
			throws Exception {

		SysOrderHead oh = ohservice.getById(ohid);
		BigDecimal oh_total = oh.getTotal();
		// 限额处理 -1
		BigDecimal limit = new BigDecimal(limitAmount).subtract(
				new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
		// BigDecimal limit=new BigDecimal(limitAmount);
		if (oh_total.compareTo(limit) == 1) { // 超限, 进行拆分
			List<SysOrderLine> olines = oh.getSysOrderLines();
			// 订单总金额除以限额 计算该订单拆分单据数量count
			String s_count = oh_total.divide(limit, 0, BigDecimal.ROUND_UP)
					.toString();
			int count = Integer.parseInt(s_count);
			// 拆分后新订单行存入new_ol_lists
			List<List<SysOrderLine>> new_ol_lists = new ArrayList<>();
			// 初始化ollist存放拆分后订单商品行
			for (int i = 0; i < count; i++) {
				List<SysOrderLine> ollist = new ArrayList<>();
				new_ol_lists.add(ollist);
			}

			// index new_ol_lists索引
			int index = 0;
			for (SysOrderLine ol : olines) {
				// 单个商品行金额超限，未超限则直接存入new_ol_lists末位
				if (ol.getJe().compareTo(limit) == 1) {
					// split_ol_to拆分为多个不超限订单，ol_list中最后一位存放余数行
					List<SysOrderLine> ol_list = split_ol_to(limit, ol);
					// 遍历ol_list除最后一位，生成orderhead,保存
					for (int i = 0; i < ol_list.size() - 1; i++) {
						new_ol_lists.get(index).add(ol_list.get(i));
						SysOrderHead new_oh = new SysOrderHead();
						ol_list.get(i).setSysOrderHead(new_oh);
						BeanUtils.copyNotNullProperties(oh, new_oh);
						String uuid = UUID.randomUUID().toString();
						new_oh.setOhid(uuid);
						new_oh.setSysOrderLines(new_ol_lists.get(index));
						new_oh.setTotal(limit);
						new_oh.setSphs(new_ol_lists.get(index).size());
						ohservice.save(new_oh);
						index++;
					}
					new_ol_lists.get(count - 1).add(
							ol_list.get(ol_list.size() - 1));
				} else {
					new_ol_lists.get(count - 1).add(ol);
				}
			}
			// new_ol_lists末位中未超限商品行统一处理拆分
			// 余数订单总价合计拆分
			BigDecimal count_total = new BigDecimal(0);
			List<SysOrderLine> sol_list = new ArrayList<>();
			for (SysOrderLine sol : new_ol_lists.get(count - 1)) {
				sol_list.add(sol);
				// 计算余数订单金额相加，若超限则拆分
				count_total = BigdecimalUtil.add(count_total, sol.getJe());
				if (count_total.compareTo(limit) == 1) {
					// 超限，将当前商品行移出sol_list，并拆分当前商品行
					sol_list.remove(sol_list.size() - 1);
					// 将当前商品行拆分未两个放入list,list.get(0)金额为限额，生成一个订单行
					// list.get(1)为余数
					// 余数部分放入sol_list进入下一次循环
					List<SysOrderLine> list = split_ol(
							count_total.subtract(limit), sol);
					SysOrderHead new_oh = new SysOrderHead();
					list.get(0).setSysOrderHead(new_oh);
					sol_list.add(list.get(0));
					BeanUtils.copyNotNullProperties(oh, new_oh);
					String uuid = UUID.randomUUID().toString();
					new_oh.setOhid(uuid);
					for (SysOrderLine ol : sol_list) {
						oh.getSysOrderLines().remove(ol);
						ol.setSysOrderHead(new_oh);
					}
					new_oh.setSysOrderLines(sol_list);
					new_oh.setTotal(limit);
					int j = sol_list.size();
					new_oh.setSphs(j);
					ohservice.save(new_oh);
					count_total = list.get(1).getJe();
					sol_list.clear();
					sol_list.add(list.get(1));
				}
			}
			// 保存处理最后所剩余数
			SysOrderHead new_oh = new SysOrderHead();

			BeanUtils.copyNotNullProperties(oh, new_oh);
			new_oh.setOhid(UUID.randomUUID().toString());
			new_oh.setSysOrderLines(sol_list);
			new_oh.setTotal(oh.getTotal().divideAndRemainder(limit)[1]);
			new_oh.setSphs(sol_list.size());
			for (SysOrderLine ol : sol_list) {
				oh.getSysOrderLines().remove(ol);
				ol.setSysOrderHead(new_oh);
			}
			ohservice.delete(oh);
			ohservice.save(new_oh);

			return null;
		} else {
			return null;
		}
	}

	private List<SysOrderLine> split_ol_to(BigDecimal limit, SysOrderLine ol) {
		List<SysOrderLine> res_list = new ArrayList<>();
		int count = Integer.parseInt(ol.getJe()
				.divide(limit, 0, BigDecimal.ROUND_UP).toString());
		for (int i = 0; i < count; i++) {
			SysOrderLine ol1 = new SysOrderLine();
			BeanUtils.copyNotNullProperties(ol, ol1);
			if (!ol1.getJe().equals(new BigDecimal(0))) {
				ol1.setSl(limit.divide(ol1.getDj(), 10,
						BigDecimal.ROUND_HALF_UP));
			}
			ol1.setSysOrderHead(null);
			ol1.setJe(limit);
			if (i == count - 1) {
				ol1.setSl(ol.getJe().divideAndRemainder(limit)[1].divide(
						ol1.getDj(), 10, BigDecimal.ROUND_HALF_UP));
				ol1.setJe(ol.getJe().divideAndRemainder(limit)[1]);
			}
			ol1.setOlid(UUID.randomUUID().toString());
			List<OrderDataZ> od_list = new ArrayList<>();
			for (OrderDataZ od : ol.getOrderDataZs()) {
				od_list.add(od);
			}
			ol1.setOrderDataZs(od_list);
			res_list.add(ol1);
		}

		return res_list;
	}

	// 将商品行中超限部分拆分，一个商品上拆分为两个
	private List<SysOrderLine> split_ol(BigDecimal over_limit, SysOrderLine ol) {
		List<SysOrderLine> res_list = new ArrayList<>();
		// 拆分后ol1存入当前订单,ol2存入下一订单
		SysOrderLine ol1 = new SysOrderLine();
		SysOrderLine ol2 = new SysOrderLine();
		BeanUtils.copyNotNullProperties(ol, ol1);
		BeanUtils.copyNotNullProperties(ol, ol2);
		// 计算拆分后商品行金额和数量
		ol1.setJe(ol1.getJe().subtract(over_limit));
		if (!ol1.getJe().equals(new BigDecimal(0))) {
			ol1.setSl(ol1.getJe().divide(ol1.getDj(), 10,
					BigDecimal.ROUND_HALF_UP));
		}
		ol1.setSysOrderHead(null);
		ol1.setOlid(UUID.randomUUID().toString());
		List<OrderDataZ> od_list = new ArrayList<>();
		for (OrderDataZ od : ol.getOrderDataZs()) {
			od_list.add(od);
		}
		ol1.setOrderDataZs(od_list);
		ol2.setJe(over_limit);
		ol2.setSl(over_limit.divide(ol2.getDj(), 10, BigDecimal.ROUND_HALF_UP));
		ol2.setSysOrderHead(null);
		ol2.setOlid(UUID.randomUUID().toString());
		ol2.setOrderDataZs(od_list);
		res_list.add(ol1);
		res_list.add(ol2);
		return res_list;
	}

	@Override
	public String updateSsflbm() {
		// TODO Auto-generated method stub
		String sql_update = "update t_sysorderline set SSFLBM=tmp.ssflbm , SLV=tmp.slv from ("
				+ "select ol.OLID,bm.SSFLBM,bm.SLV from t_sysorderline ol,t_ssflbm bm where ol.sphm=bm.sphm or (ol.SPMC=bm.SPMC and ol.GGXH=bm.GGXH and ol.DW=bm.DW)"
				+ ") tmp where t_sysorderline.OLID=tmp.OLID and (t_sysorderline.SSFLBM is null or t_sysorderline.SSFLBM='') ";
		int rs = executeSql(sql_update);
		if (rs > 0) {
			return "更新" + rs + "条商品行!";
		} else {
			return "无更新数据!";
		}
	}
	@Override
	public int save_xd_Order(List<OrderInfo> odlist, String kpr)
	  {
//	    List<OrderDataZ> orderdataList = new ArrayList();
	    
	    HashSet<String> gfmcSet = new HashSet();
	    HashSet<Integer> markSet = new HashSet();
	    for (OrderInfo od : odlist)
	    {
	      gfmcSet.add(od.getGfmc());
	      markSet.add(od.getFenpiaobiaoshi());
	    }
//	    this.odservice.saveList(orderdataList);
	    Iterator<String> it = gfmcSet.iterator();
	    
	    int count = 0;
	    while (it.hasNext())
	    {
	      count++;
	      List<OrderInfo> fl_list = new ArrayList();
	      String gfmc = (String)it.next();
	      Iterator<Integer> it1=markSet.iterator();
	      while(it1.hasNext()){
		      int mark=it1.next();
		      for (OrderInfo orderdata : odlist) {
		        if (orderdata.getGfmc().equals(gfmc)&&orderdata.getFenpiaobiaoshi()==mark) {
		          fl_list.add(orderdata);
		        }
		      }
	      }
	      save_new_orderline(fl_list, kpr);
	    }
	    return count;
	  }

	@Override
	public void save_disocount(SysOrderHead oh, int zkh_count) {

		//处理多税率订单折扣
		if (oh.getMix().equals("1")) {
			List<SysOrderLine> orderlines = oh.getSysOrderLines();
			Set<BigDecimal> slv_set=new HashSet<>();
			//获取订单税率，存入slv_set,更新序号
			int spxh=1;
			for(SysOrderLine ol:orderlines){
				slv_set.add(ol.getSlv());
				ol.setSpxh(spxh);
				spxh++;
				update(ol);
			}
			Iterator<BigDecimal> it=slv_set.iterator();
			//正数商品行技术，用于计算折扣后商品行数
			int positive_count=0;
			//新产生的部分折扣行数
			int zkhs=0;
			//循环获取不同税率
			int slv_count=0;
			while (it.hasNext()) {
				slv_count++;
				BigDecimal current=it.next();
				//相同税率的商品行放入same_slv_list
				List<SysOrderLine> same_slv_list=new ArrayList<>();
				for(SysOrderLine ol:orderlines){
					if(ol.getSlv().compareTo(current)==0){
						same_slv_list.add(ol);
					}
				}
				
				BigDecimal positiveTotal = new BigDecimal(0); // 每种税率正数总金额
				for(SysOrderLine ol:same_slv_list){
					if (ol.getJe().compareTo(new BigDecimal(0)) == 1) {
						positiveTotal = BigdecimalUtil.add(positiveTotal,
								ol.getJe());
						positive_count++;
					}
					
				}
				//构造折扣行
				SysOrderLine zkh = same_slv_list.get(same_slv_list.size() - 1);
				if (zkh.getJe().compareTo(new BigDecimal("0.00")) == -1) {
					BigDecimal zzkje = zkh.getJe();
					List<OrderDataZ> od_list = new ArrayList<>();
					od_list.addAll(zkh.getOrderDataZs());
//					for (OrderDataZ od : zkh.getOrderDataZs()) {
//						od_list.add(od);
//					}
					List<SysOrderLine> zkh_list=new ArrayList<>();
					for (int i = 0; i < same_slv_list.size() - 1; i++) {
						SysOrderLine zkh_sp = new SysOrderLine(); // 折扣行商品
						SysOrderLine zjh_sp = same_slv_list.get(i); // 正价行商品
						BeanUtils.copyProperties(zjh_sp, zkh_sp);
						// 均摊后的折扣金额
//						zjh_sp.setSpxh(i + 1);
						update(zjh_sp);
						BeanUtils.copyProperties(zjh_sp, zkh_sp);
						//部分折扣后的金额
						BigDecimal zkje =new BigDecimal(0);
						if(zzkje.abs().compareTo(zjh_sp.getJe())==1){

							zkje=zjh_sp.getJe().negate();
							zzkje=BigdecimalUtil.add(zzkje, zjh_sp.getJe());
							zkhs++;
						}else{
							zkje=zzkje;
							zzkje=new BigDecimal(0);
							zkhs++;
						}
						BeanUtils.copyNotNullProperties(zjh_sp, zkh_sp);
						zkh_sp.setOlid(UUID.randomUUID().toString());
						zkh_sp.setJe(zkje);
						zkh_sp.setSl(null);
						zkh_sp.setOrderDataZs(od_list);
						zkh_sp.setSysOrderHead(oh);
						save(zkh_sp);
						if(zzkje.compareTo(new BigDecimal(0))==0){
							break;
						}
						
					}
					saveList(zkh_list);
					zkh.getOrderDataZs().clear();
					oh.getSysOrderLines().remove(zkh);
					// 计算折扣后订单商品行数
					delete(zkh);
				}
				
			}
			oh.setSphs(oh.getSphs()+zkh_count-slv_count);
			ohservice.update(oh);
		} 
		//处理单税率折扣
		else {
			
			int zkhs=0;
			List<SysOrderLine> orderlines = oh.getSysOrderLines();
			// orderlines=sortRevOrderLineList(orderlines);
			int spxh=1;
			for(SysOrderLine ol:orderlines){
				ol.setSpxh(spxh);
				spxh++;
				update(ol);
			}
			//构造折扣行
			//取折扣汇总后最后一行负数总金额
			SysOrderLine zkh = orderlines.get(orderlines.size() - 1);
			if (zkh.getJe().compareTo(new BigDecimal("0.00")) == -1) {
				BigDecimal zzkje = zkh.getJe();
				List<OrderDataZ> od_list = new ArrayList<>();
				od_list.addAll(zkh.getOrderDataZs());
				
				BigDecimal res=new BigDecimal(0);
				for (int i = 0; i < orderlines.size() - 1; i++) {

					SysOrderLine zkh_sp = new SysOrderLine(); // 折扣行商品
					SysOrderLine zjh_sp = orderlines.get(i); // 正价行商品
					zjh_sp.setSpxh(i + 1);
					update(zjh_sp);
					BeanUtils.copyProperties(zjh_sp, zkh_sp);
					// 均摊后的折扣金额
					BigDecimal zkje =new BigDecimal(0);
					if(zzkje.abs().compareTo(zjh_sp.getJe())==1){

						zkje=zjh_sp.getJe().negate();
						zzkje=BigdecimalUtil.add(zzkje, zjh_sp.getJe());
						zkhs++;
					}else{
						zkje=zzkje;
						zzkje=new BigDecimal(0);
						zkhs++;
					}
					BeanUtils.copyNotNullProperties(zjh_sp, zkh_sp);
					zkh_sp.setOlid(UUID.randomUUID().toString());
					zkh_sp.setSl(null);
					zkh_sp.setJe(zkje);
					zkh_sp.setOrderDataZs(od_list);
					zkh_sp.setSysOrderHead(oh);
					save(zkh_sp);
					if(zzkje.compareTo(new BigDecimal(0))==0){
						break;
					}
				}
				zkh.getOrderDataZs().clear();
				oh.getSysOrderLines().remove(zkh);
				// 计算折扣后订单商品行数
				oh.setSphs((oh.getSphs() +zkhs) - 1);
				ohservice.update(oh);
				delete(zkh);

			}
		}

		
	}
}
