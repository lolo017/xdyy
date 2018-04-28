package aisino.reportform.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aisino.reportform.dao.base.BaseDaoI;
import aisino.reportform.model.base.ShippingList;
import aisino.reportform.service.base.ShippingListServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;

@Service
public class ShippingListServiceImpl extends BaseServiceImpl<ShippingList> implements ShippingListServiceI {
	@Autowired
	private BaseDaoI<ShippingList> ShippingListDao;

	@Override
	public void insertShippingList(List<ShippingList> list) {
		ShippingListDao.saveList(list);
	}

}
