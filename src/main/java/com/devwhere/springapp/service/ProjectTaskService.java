package com.devwhere.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devwhere.springapp.domain.ProjectTask;
import com.devwhere.springapp.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask) {
		
		if(projectTask.getStatus()==null || projectTask.getStatus()=="") {
			projectTask.setStatus("TO_DO");
		}
		return projectTaskRepository.save(projectTask);
	}
	
	public Iterable<ProjectTask> findAll(){
		return projectTaskRepository.findAll();
	}
	
	public ProjectTask findbyId(Long id) {
		return projectTaskRepository.getById(id);
	}
	
	public void deleteById(Long id) {
		ProjectTask projectTask = findbyId(id);
		
		projectTaskRepository.delete(projectTask);
	}
}
