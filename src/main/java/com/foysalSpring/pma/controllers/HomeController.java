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
import com.foysalSpring.pma.dao.EmployeeRepository;
import com.foysalSpring.pma.dao.ProjectRepository;
import com.foysalSpring.pma.dto.ChartData;
import com.foysalSpring.pma.dto.EmployeeProject;
import com.foysalSpring.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();
		
		List<Project> projects = projectRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		List<ChartData> projectData = projectRepo.getProjectStatus();
		
		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
		
		model.addAttribute("projectStatusCount", jsonString);
		
		List<EmployeeProject> employeesProjectCount = employeeRepo.employeeProjects();
		model.addAttribute("employeesListProjectCount", employeesProjectCount);
		
		return "main/home";
		
	}
	

}
