package com.tushar.runner;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component("myRunnerBean")
public class MyRunner implements CommandLineRunner {

	@Autowired
	private JobLauncher launcher;

	@Autowired
	private Job job;
	
	@Override
	public void run(String... args) throws Exception {
		JobParameters jobParams = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		JobExecution  execution = launcher.run(job, jobParams);
		System.out.println("MyRunner JobExecution Status "+execution.getStatus());
		System.out.println("MyRunner JobExecution Exit status: "+execution.getExitStatus());
		
	}

}
