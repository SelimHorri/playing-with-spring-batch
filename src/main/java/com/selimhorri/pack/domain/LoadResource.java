package com.selimhorri.pack.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/app/api/load"})
public class LoadResource {
	
	private final Job job;
	private final JobLauncher jobLauncher;
	
	@Autowired
	public LoadResource(final Job job, final JobLauncher jobLauncher) {
		this.job = job;
		this.jobLauncher = jobLauncher;
	}
	
	@GetMapping
	public ResponseEntity<BatchStatus> load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		final Map<String, JobParameter> map = new HashMap<>();
		map.put("time", new JobParameter(new Date()));
		
		final JobParameters jobParameters = new JobParameters(map);
		final JobExecution jobExecution = this.jobLauncher.run(job, jobParameters);
		
		while (jobExecution.isRunning()) {
			System.out.println("***** STILL RUNNING *****");
		}
		
		return new ResponseEntity<>(jobExecution.getStatus(), HttpStatus.OK);
	}
	
	
	
}









