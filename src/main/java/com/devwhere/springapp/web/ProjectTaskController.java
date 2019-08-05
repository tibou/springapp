package com.devwhere.springapp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devwhere.springapp.domain.ProjectTask;
import com.devwhere.springapp.service.ProjectTaskService;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ProjectTaskController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@PostMapping("")
	public ResponseEntity<?> addPTTOBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result){
		
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			List<FieldError> errors = result.getFieldErrors();
			
			errors.forEach( error -> errorMap.put(error.getField(), error.getDefaultMessage()));
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		ProjectTask projectTaskCreated = projectTaskService.saveOrUpdateProjectTask(projectTask);
		
		return new ResponseEntity<ProjectTask>(projectTaskCreated, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<ProjectTask> getAllPTs(){
		return projectTaskService.findAll();
	}
	
	@GetMapping("/{pt_id}")
	public ResponseEntity<?> getPTById(@PathVariable Long pt_id){
		ProjectTask projectTask = projectTaskService.findbyId(pt_id);
		
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{pt_id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable Long pt_id){
		projectTaskService.deleteById(pt_id);
		
		return new ResponseEntity<String>("Project task is deleted", HttpStatus.OK);
	}

}
