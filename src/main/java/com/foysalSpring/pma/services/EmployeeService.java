package com.foysalSpring.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foysalSpring.pma.dao.EmployeeRepository;
import com.foysalSpring.pma.dto.EmployeeProject;
import com.foysalSpring.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	public Employee save(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects() {
		return employeeRepo.employeeProjects();
	}

}
