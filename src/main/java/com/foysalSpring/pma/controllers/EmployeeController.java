package com.foysalSpring.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foysalSpring.pma.entities.Employee;
import com.foysalSpring.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = employeeService.getAll();
		model.addAttribute("employeesList", employees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		
		employeeService.save(employee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdareForm(@RequestParam("id") Long id, Model model) {
		
		Employee theEmp = employeeService.findByEmployeeId(id);
		
		model.addAttribute("employee", theEmp);
		
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") Long id, Model model) {
		
		Employee theEmp = employeeService.findByEmployeeId(id);
		employeeService.delete(theEmp);
		
		return "redirect:/employees";
		
	}

}