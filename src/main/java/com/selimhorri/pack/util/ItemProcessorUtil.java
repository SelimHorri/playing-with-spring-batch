package com.selimhorri.pack.util;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.selimhorri.pack.model.entity.Employee;

@Component
public class ItemProcessorUtil implements ItemProcessor<Employee, Employee> {
	
	@Override
	public Employee process(final Employee item) throws Exception {
		return item;
	}
	
	
	
}







