package com.foysalSpring.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.foysalSpring.pma.dto.ChartData;
import com.foysalSpring.pma.dto.EmployeeProject;
import com.foysalSpring.pma.entities.Project;
import com.foysalSpring.pma.services.EmployeeService;
import com.foysalSpring.pma.services.ProjectService;

@Controller
public class HomeController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();
		
		List<Project> projects = projectService.getAll();
		model.addAttribute("projectsList", projects);
		
		List<ChartData> projectData = projectService.getProjectStatus();
		
		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
		
		model.addAttribute("projectStatusCount", jsonString);
		
		List<EmployeeProject> employeesProjectCount = employeeService.employeeProjects();
		model.addAttribute("employeesListProjectCount", employeesProjectCount);
		
		return "main/home";
		
	}
	

}