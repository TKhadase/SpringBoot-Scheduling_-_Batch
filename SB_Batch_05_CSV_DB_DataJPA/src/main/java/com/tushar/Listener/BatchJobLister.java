package com.tushar.Listener;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

//@Component("batchJobListerBean")
public class BatchJobLister implements JobExecutionListener {

	private long startTime, endTime ;
	
	public BatchJobLister() {
		System.out.println("BatchJobLister() Object Ready");
	}
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("BatchJobLister.beforeJob() started: "+new Date());
		startTime  = System.currentTimeMillis();
		System.out.println("BatchJobLister.beforeJob() Status: "+jobExecution.getStatus());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("BatchJobLister.afterJob() started: "+new Date());
		endTime  = System.currentTimeMillis();
		System.out.println("BatchJobLister.afterJob() Status "+jobExecution.getStatus());
		System.out.println("BatchJob Execution Time: "+(endTime-startTime ));
		System.out.println("BatchJob Exit status: "+jobExecution.getExitStatus());
	}

}
