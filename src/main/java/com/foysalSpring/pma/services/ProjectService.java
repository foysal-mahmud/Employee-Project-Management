package com.foysalSpring.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foysalSpring.pma.dao.ProjectRepository;
import com.foysalSpring.pma.dto.ChartData;
import com.foysalSpring.pma.dto.TimeChartData;
import com.foysalSpring.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepo;
	
	public Project save(Project project) {
		return projectRepo.save(project);
	}
	
	public List<Project> getAll() {
		return projectRepo.findAll();
	}
	
	public List<ChartData> getProjectStatus() {
		return projectRepo.getProjectStatus();
	}
	
	public List<TimeChartData> getTimeData(){
		return projectRepo.getTimeData();
	}

}