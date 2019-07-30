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
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entitiese.Department;
import com.mastek.training.hrapp.repositories.DepartmentRepository;



// indicate to spring to create an object of this class as component

@Component
@Scope("singleton")
@Path("/departments/")
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
			
	public DepartmentService() {
		System.out.println("Department Service Created");
	}
	
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	public Iterable<Department> listAllDepartments(){
		return departmentRepository.findAll();
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON) // object to be given in json
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Department registerOrUpdateDepartment(@BeanParam Department dep) {
		dep = departmentRepository.save(dep);
		System.out.println("Department Registered" + dep);
		return dep;
	}

	@Path("/find/{depno}")
	@GET //HTTP method used to call the api
	@Produces({//declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in json
		MediaType.APPLICATION_XML // Object to be given in XML
	})
	public Department findBydepno(
			@PathParam("depno")int depno) {
		try {
			return departmentRepository.findById(depno).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/fetchByLocation")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> fetchDepartmentByLocation(String location){
			return departmentRepository.findByLocation(location);
	}
		
	@DELETE //delete http method
	@Path("/delete/{depno}")
	public void deleteByDepno(@PathParam("depno")int depno) {
		departmentRepository.deleteById(depno);
	}

}
