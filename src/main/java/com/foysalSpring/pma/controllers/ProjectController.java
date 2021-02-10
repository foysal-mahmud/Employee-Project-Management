package com.foysalSpring.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foysalSpring.pma.entities.Employee;
import com.foysalSpring.pma.entities.Project;
import com.foysalSpring.pma.services.EmployeeService;
import com.foysalSpring.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = projectService.getAll();
		model.addAttribute("projectsList", projects);
		
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees = employeeService.getAll();
		
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) { 	//@RequestParam List<Long> employees
		
		projectService.save(project);
		
//		Iterable<Employee> chosenEmployees = employeeRepo.findAllById(employees);
//		for(Employee emp: chosenEmployees) {
//			emp.setProject(project);
//			employeeRepo.save(emp);
//		}
		
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
	

}
