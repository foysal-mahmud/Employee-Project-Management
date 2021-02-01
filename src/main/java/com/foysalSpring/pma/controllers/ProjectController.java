package com.foysalSpring.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foysalSpring.pma.dao.EmployeeRepository;
import com.foysalSpring.pma.dao.ProjectRepository;
import com.foysalSpring.pma.entities.Employee;
import com.foysalSpring.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = projectRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project();
		List<Employee> employees = employeeRepo.findAll();
		
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) { 	//@RequestParam List<Long> employees
		
		projectRepo.save(project);
		
//		Iterable<Employee> chosenEmployees = employeeRepo.findAllById(employees);
//		for(Employee emp: chosenEmployees) {
//			emp.setProject(project);
//			employeeRepo.save(emp);
//		}
		
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
	

}
