package aisino.reportform.service.base;

import java.util.List;

import aisino.reportform.model.base.EmpDegree;
import aisino.reportform.model.base.EmpFamily;
import aisino.reportform.model.base.EmpTitle;
import aisino.reportform.model.base.SyEmpData;
import aisino.reportform.service.BaseServiceI;

/**
 * 员工基本信息实现类
 * 
 * @author 廖宸宇
 * 
 */
public interface EmpDataServiceI extends BaseServiceI<SyEmpData>{
	/**
	 * 
	 * 保存员工基本信息，学历，职称，家庭empdata表
	 * 同时保存syuser表
	 */
	public void saveEmpData(List<EmpDegree> empDegreeList,List<EmpFamily> empFamilyList,List<EmpTitle> empTitleList,SyEmpData empData);

	/**
	 * 更新员工基本信息，学历，职称，家庭
	 * 
	 */
	public void updateEmpData(List<EmpDegree> empDegreeList,List<EmpFamily> empFamilyList,List<EmpTitle> empTitleList,SyEmpData empData);
	/**
	 * 填写绩效时，先完善基本信息
	 */
	public void updateComplete(String orgId,String depId,String posId,String leaderId,String empId);

}
