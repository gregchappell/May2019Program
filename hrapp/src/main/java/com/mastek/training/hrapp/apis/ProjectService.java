package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entitiese.Project;
import com.mastek.training.hrapp.repositories.ProjectRepository;





// indicate to spring to create an object of this class as component

@Component
@Scope("singleton")
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
			
	public ProjectService() {
		System.out.println("Project Service Created");
	}
	
	
	public Project registerOrUpdateProject(Project pro) {
		pro = projectRepository.save(pro);
		System.out.println("Project Registered" + pro);
		return pro;
	}


	public Project findByProno(int prono) {
		try {
			return projectRepository.findById(prono).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public List<Project> fetchProjectByCustomer(String customer){
			return projectRepository.findByCustomer(customer);
	}
		
	
	public void deleteByProno(int prono) {
		projectRepository.deleteById(prono);
	}

}
