package com.selimhorri.pack.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.selimhorri.pack.model.entity.Employee;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Bean
	public Job getJob(final JobBuilderFactory jobBuilderFactory, final StepBuilderFactory stepBuilderFactory, final ItemReader<Employee> itemReader, final ItemProcessor<Employee, Employee> itemProcessor, final ItemWriter<Employee> itemWriter) {
		
		final Step step = stepBuilderFactory
							.get("ETL-file-load")
							.<Employee, Employee>chunk(100)
							.reader(itemReader)
							.processor(itemProcessor)
							.writer(itemWriter)
							.build();
		
		return jobBuilderFactory
			.get("ETL-load")
			.incrementer(new RunIdIncrementer())
			.start(step)
			.build();
	}
	
	@Bean
	public FlatFileItemReader<Employee> getFileItemReader(@Value("${input_file}") final Resource resource) {
		
		FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(this.getLineMapper());
		return flatFileItemReader;
	}
	
	private LineMapper<Employee> getLineMapper() {
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String[] {"fname", "lname", "email", "image_url", "hiredate"});
		
		BeanWrapperFieldSetMapper<Employee> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(Employee.class);
		
		DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		
		return defaultLineMapper;
	}
	
	
	
}






