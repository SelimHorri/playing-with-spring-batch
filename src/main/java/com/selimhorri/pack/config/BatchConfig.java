package com.selimhorri.pack.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.selimhorri.pack.model.entity.Employee;

@Configuration
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
	
	
	
}






