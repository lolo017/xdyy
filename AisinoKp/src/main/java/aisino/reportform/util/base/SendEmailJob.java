package aisino.reportform.util.base;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import aisino.reportform.util.base.excel.CreateExcel;

public class SendEmailJob extends QuartzJobBean{
	//Send send=new Send();
	CreateExcel createExcel=new CreateExcel();
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		 try {
			// createExcel.create();
		 } catch (Exception e) {
             e.printStackTrace();
         }
	}

}
