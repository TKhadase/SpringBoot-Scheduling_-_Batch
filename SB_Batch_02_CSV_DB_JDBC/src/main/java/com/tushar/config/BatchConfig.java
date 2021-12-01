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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.tushar.Listener.BatchJobLister;
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
	public ItemReader< Students> createReader(){
		FlatFileItemReader reader = new FlatFileItemReader<Students>();
		reader.setResource(new ClassPathResource("Students.csv"));
		//reader.setResource(new FileSystemResource("E:\\Tushar\\Students.csv"));
		//reader.setResource(new UrlResource("http:abcd.com/files/csv/Students.csv"));
		reader.setLineMapper( new DefaultLineMapper<Students>() {
			{
				setLineTokenizer( new  DelimitedLineTokenizer() {
					{
						setDelimiter(",");
						setNames("sid","fname","lname","age","sex");
					}
				});
				
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Students>() {
					{
						setTargetType(Students.class);
					}
				});
			}
		});
		return reader;
	}
	//OR
	/*@Bean("myReader")
	public ItemReader< Students> createReader(){
		return new FlatFileItemReaderBuilder<Students>()
				.name("file-reader")
				.resource(new ClassPathResource("Students.csv"))
				.delimited().delimiter(",")
				.names("sid","fname","lname","age","sex")
				.targetType(Students.class)
				.build();
	}*/
	
	@Bean("myWriter")
	public ItemWriter<Students> createWriter(){
		JdbcBatchItemWriter< Students> writer = new JdbcBatchItemWriter<Students>();
		writer.setDataSource(ds);
		writer.setSql("INSERT INTO BATCH_02_CSV_DB_JDBC VALUES(:sid,:fname, :lname,:age, :sex, :rank)");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Students>());
		return writer;
	}
	//OR
	/*@Bean("myWriter")
	public ItemWriter<Students> createWriter(){
		return new JdbcBatchItemWriterBuilder<Students>()
				.dataSource(ds)
				.sql("INSERT INTO BATCH_02_CSV_DB_JDBC VALUES(:sid,:fname, :lname,:age, :sex, : rank)")
				.beanMapped()
				.build();
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
