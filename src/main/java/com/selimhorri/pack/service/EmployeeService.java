package com.selimhorri.pack.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selimhorri.pack.model.entity.Employee;
import com.selimhorri.pack.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(final EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> findAll() {
		return this.employeeRepository.findAll();
	}
	
	public Employee findById(final Integer id) {
		return this.employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("********* NOT FOUND *********"));
	}
	
	public Employee save(final Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	public Employee update(final Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	public void deleteById(final Integer id) {
		this.employeeRepository.deleteById(id);
	}
	
	
	
}
