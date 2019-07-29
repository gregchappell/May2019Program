package com.mastek.training.hrapp.apis;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entitiese.Project;
import com.mastek.training.hrapp.repositories.ProjectRepository;

// indicate to spring to create an object of this class as component
@Component
@Scope("singleton")
@Path("/projects/")
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
			
	public ProjectService() {
		System.out.println("Project Service Created");
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON) // object to be given in json
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Project registerOrUpdateProject(@BeanParam Project pro) {
		pro = projectRepository.save(pro);
		System.out.println("Project Registered" + pro);
		return pro;
	}

	@Path("/find/{prono}")
	@GET //HTTP method used to call the api
	@Produces({//declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in json
		MediaType.APPLICATION_XML // Object to be given in XML
	})
	public Project findByProno(@PathParam("prono")int prono) {
		try {
			return projectRepository.findById(prono).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@GET
	@Path("/fetchByCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> fetchProjectByCustomer(
			@QueryParam("customer")String customer){
			return projectRepository.findByCustomer(customer);
	}
		
	@DELETE //delete http method
	@Path("/delete/{prono}")
	public void deleteByProno(@PathParam("prono")int prono) {
		projectRepository.deleteById(prono);
	}

}
