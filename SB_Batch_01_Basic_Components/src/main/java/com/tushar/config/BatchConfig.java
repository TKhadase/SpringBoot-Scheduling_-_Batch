package com.tushar.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tushar.Listener.BatchJobLister;
import com.tushar.processor.MyProcessor;
import com.tushar.reader.MyReader;
import com.tushar.writer.MyWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory  jobFactory;

	@Autowired
	private StepBuilderFactory  stepFactory;
	
	@Autowired
	private BatchJobLister  batchJobLister;
	
	@Autowired
	private MyReader  myReader;
	
	@Autowired
	private MyProcessor  myProcessor;
	
	@Autowired
	private MyWriter  myWriter;
	
	@Bean("myStep")
	public Step createStep() {
		return stepFactory.get("myStep")
				.<String,String>chunk(1)
				.reader(myReader)
				.processor(myProcessor)
				.writer(myWriter)
				.build();
	}
	
	@Bean("myJob")
	public Job createJob() {
		return jobFactory.get("myJob")
				.incrementer(new RunIdIncrementer())
				.listener(batchJobLister)
				.start(createStep())
				.build();
	}
	
	
	
}
