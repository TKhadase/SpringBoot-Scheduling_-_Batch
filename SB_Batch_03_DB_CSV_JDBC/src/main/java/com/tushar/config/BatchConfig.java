package com.tushar.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.tushar.Listener.BatchJobLister;
import com.tushar.mapper.StudentsData_mapper;
import com.tushar.model.Students;
import com.tushar.processor.MyProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory  jobFactory;

	@Autowired
	private StepBuilderFactory  stepFactory;
	
	@Autowired
	private DataSource ds;
	
	@Bean
	public ItemProcessor<Students,Students>  createProcessor(){
		return new MyProcessor();
	}
	
	@Bean("myReader")
	public JdbcCursorItemReader< Students> createReader(){
		JdbcCursorItemReader< Students> reader = new JdbcCursorItemReader<>();
		reader.setDataSource(ds);
		reader.setSql("SELECT SID,FNAME, LNAME, AGE, SEX,RANK FROM BATCH_02_CSV_DB_JDBC ORDER BY SID");
		reader.setRowMapper( new StudentsData_mapper());
		return reader;
	}
	//OR
	/*@Bean("myReader")
	public JdbcCursorItemReader< Students> createReader(){
		return new JdbcCursorItemReaderBuilder<Students>()
				.dataSource(ds)
				.sql("SELECT SID,FNAME, LNAME, AGE, SEX,RANK FROM BATCH_02_CSV_DB_JDBC")
				.beanRowMapper(Students.class)
				.build();
	}*/
	
	@Bean("myWriter")
	public FlatFileItemWriter<Students> createWriter(){
		FlatFileItemWriter< Students> writer = new FlatFileItemWriter<Students>();
		writer.setResource(new FileSystemResource("E:\\data.csv"));
		writer.setLineAggregator(new  DelimitedLineAggregator<Students>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<Students>() {
					{
						setNames(new String[] {"sid","fname","lname","age","sex","rank"});
					}
				});
			}
		});
		return writer;
	}
	//OR
	/*@Bean
	public    FlatFileItemWriter<ExamResult> createWriter(){
		   return  new  FlatFileItemWriterBuilder<ExamResult>()
				                     .name("line123")
				                     .resource(new ClassPathResource("data.csv"))
				                     .lineSeparator("\r\n")
				                     .delimited().delimiter(",")
				                     .names("SID","FNAME","LNAME","AGE","SEX","RANK")
				                     .build();
	}
	}*/
	
	@Bean
	public JobExecutionListener createListener(){
		return new BatchJobLister();
	}
	
	@Bean("myStep1")
	public Step createStep() {
		return stepFactory.get("myStep1")
				.<Students,Students>chunk(10)
				.reader(createReader())
				.processor(createProcessor())
				.writer(createWriter())
				.build();
	}
	
	@Bean("myJob1")
	public Job createJob() {
		return jobFactory.get("myJob1")
				.incrementer(new RunIdIncrementer())
				.listener(createListener())
				.start(createStep())
				.build();
	}
	
	
	
}
