package lzy;


import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aisino.reportform.model.base.Syorganization;
import aisino.reportform.model.base.Syuser;
import aisino.reportform.model.fpmng.OrderDataZ;
import aisino.reportform.model.fpmng.SysOrderHead;
import aisino.reportform.model.fpmng.SysOrderLine;
import aisino.reportform.model.view.SysUsersView;
import aisino.reportform.service.base.SyuserServiceI;
import aisino.reportform.service.fpmng.OrderDataZServiceI;
import aisino.reportform.service.fpmng.SysOrderHeadServiceI;
import aisino.reportform.service.fpmng.SysOrderLineServiceI;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-hibernate.xml"})
public class test {

	private static final Logger logger = Logger.getLogger(test.class);
	
	@Autowired
	private SyuserServiceI baseService;
	@Autowired
	private OrderDataZServiceI odservice;
	@Autowired
	private SysOrderLineServiceI sysorderlineservice;
	@Autowired
	private SysOrderHeadServiceI ohservice;
	@Autowired
	private SyuserServiceI syservice;
	@Test
	public void test(){
		
		try {
			Set<Syorganization> list= syservice.getById("0").getSyorganizations();
			System.out.println(list.size());
			SysOrderLine ol=sysorderlineservice.getById("ol1");
			System.out.println("size"+ol.getOrderDataZs().size());
			
			SysOrderHead oh=ohservice.getById("oh1");
			logger.info(JSON.toJSONStringWithDateFormat(oh, "yyyy-MM-dd HH:mm:ss"));
//			OrderDataZ od=odservice.getByHql("from OrderDataZ where odid='1'");
//			System.out.println(od.getSysOrderlines());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test1(){
		
		try {
//			List<SysOrderLine> ollist= sysorderlineservice.getSysOrderLineListByOhid("oh1");
//			SysOrderHead oh=ollist.get(0).getSysOrderHead();
//			logger.info(JSON.toJSONStringWithDateFormat(ollist, "yyyy-MM-dd HH:mm:ss"));
			
			int i=ohservice.mixOrder(new String[]{"oh2","oh3"});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
