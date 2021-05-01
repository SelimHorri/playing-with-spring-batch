package com.selimhorri.pack.util;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.selimhorri.pack.model.entity.Employee;
import com.selimhorri.pack.service.EmployeeService;

@Component
public class DatasourceWriter implements ItemWriter<Employee> {
	
	private EmployeeService employeeService;
	
	@Autowired
	public DatasourceWriter(final EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@Override
	public void write(final List<? extends Employee> items) throws Exception {
		this.employeeService.saveAll(items);
	}
	
	
	
}







