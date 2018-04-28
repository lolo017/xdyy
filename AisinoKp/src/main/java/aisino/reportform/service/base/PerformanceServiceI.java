package aisino.reportform.service.base;

import aisino.reportform.model.base.Performance;
import aisino.reportform.service.BaseServiceI;

public interface  PerformanceServiceI extends BaseServiceI<Performance>{
	/**
	 * 保存绩效，个人指标，部门指标
	 * @param p
	 * @param grIds
	 * @param grfs
	 * @param bmIds
	 * @param bmfs
	 */
	public void savePerformance(Performance p,String zbmc_gr_sy,String zbqz_gr_sy,String zpfz_gr_sy,String zbmc_bm_sy,String zbqz_bm_sy,String zpfz_bm_sy,String zbmc_gr_by,String zbqz_gr_by,String zbmc_bm_by,String zbqz_bm_by,String jxlb_gr_sy,
			String jxlb_gr_by,String jxlb_bm_sy,String jxlb_bm_by);
	/**
	 * 保存绩效，个人指标，部门指标(暂不用)
	 * @param p
	 * @param grIds
	 * @param grfs
	 * @param bmIds
	 * @param bmfs
	 */
	public void savePerformance(Performance p,String grIds,String grfs,String bmIds,String bmfs);

	/**
	 * 更新绩效，个人指标，部门指标（暂不用）
	 * @param p
	 * @param grIds
	 * @param grfs
	 * @param bmIds
	 * @param bmfs
	 */
	public void updatePerformance(Performance p,String grIds,String grfs,String grldfs,String bmIds,String bmfs,String bmldfs);
	/**
	 * 更新绩效，个人指标，部门指标
	 * @param p
	 * @param grIds
	 * @param grfs
	 * @param bmIds
	 * @param bmfs
	 */
	public void updatePerformance(Performance p,String zbmc_gr_sy,String zbqz_gr_sy,String zpfz_gr_sy,String ldpf_gr_sy,String zbmc_bm_sy,String zbqz_bm_sy,String zpfz_bm_sy,String ldpf_bm_sy,String zbmc_gr_by,String zbqz_gr_by,String zbmc_bm_by,String zbqz_bm_by,String jxlb_gr_sy,String jxlb_gr_by,String jxlb_bm_sy,String jxlb_bm_by);
	
	/**
     * 查询该月是不是已经填写过绩效
     * @param date
     * @return
     */
	
	public  Long countPerformance(String date,String userid);
	/**
	 * 删除绩效、个人指标、部门指标
	 * @param id
	 */
	public void deletePerformance(String id);
}
