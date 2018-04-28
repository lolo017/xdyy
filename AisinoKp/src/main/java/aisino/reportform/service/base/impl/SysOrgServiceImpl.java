package aisino.reportform.service.base.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import aisino.reportform.model.view.SysOrg;
import aisino.reportform.service.base.SysOrgServiceI;
import aisino.reportform.service.impl.BaseServiceImpl;
@Service
public class SysOrgServiceImpl extends BaseServiceImpl<SysOrg> implements SysOrgServiceI{

	@Override
	@Cacheable(value = "SyresourcetypeServiceCache", key = "'SysOrgList'")
	public List<SysOrg> findSysOrg() {
		// TODO Auto-generated method stub
		return find();
	}

}
