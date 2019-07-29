package com.mastek.training.hrapp.apis;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.annotations.Polymorphism;
import org.jvnet.hk2.config.GenerateServiceFromMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entitiese.Department;
import com.mastek.training.hrapp.entitiese.Employee;
import com.mastek.training.hrapp.entitiese.Project;
import com.mastek.training.hrapp.repositories.DepartmentRepository;
import com.mastek.training.hrapp.repositories.EmployeeRepository;
import com.mastek.training.hrapp.repositories.ProjectRepository;

// indicate to spring to create an object of this class as component

@Component
@Scope("singleton")
@Path("/employees/") //map the URL pattern with the class as service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ProjectRepository projectService;
			
	public EmployeeService() {
		System.out.println("Employee Service Created");
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON) // object to be given in json
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Transactional
	public Employee registerOrUpdateEmployee(@BeanParam Employee emp) {
		Employee currentEmp = findByEmpno(emp.getEmpno());
		if(currentEmp!= null) {
			currentEmp.setName(emp.getName());
			currentEmp.setSalary(emp.getSalary());
			emp = employeeRepository.save(currentEmp);
			
		}
		else{emp = employeeRepository.save(emp);
		}
		
		System.out.println("Employee Registered" + emp);
		return emp;
	}
	// use the find method using pathparam
	// /find/1122 : 1122 -> empno to pass as param to this method
	
	@Path("/find/{empno}")
	@GET //HTTP method used to call the api
	@Produces({//declare all possible content types of return value
		MediaType.APPLICATION_JSON, // object to be given in json
		MediaType.APPLICATION_XML // Object to be given in XML
	})
	
	@Transactional // help to fetch dependent data
	public Employee findByEmpno(
			// use the path parameter as the argument for the method
			@PathParam("empno")int empno) {
		try {
			Employee emp = employeeRepository.findById(empno).get();
			System.out.println(emp.getAssignments().size() + " Assignments fetched");
			return emp;
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/fetchBySalary")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> fetchEmployeesBySalaryRange(
		@QueryParam("min") double min, 
		@QueryParam("max") double max){
			return employeeRepository.findBySalary(min, max);
	}
		
	@DELETE //delete http method
	@Path("/delete/{empno}")
	public void deleteByEmpno(@PathParam("empno")int empno) {
		employeeRepository.deleteById(empno);
	}
	
	@POST
	@Path("/assign/department")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	@org.springframework.transaction.annotation.Transactional
	public Employee assignDepartment(@FormParam("empno") int empno, 
			@FormParam("depno")int depno) {
		try {
			
			Employee emp = findByEmpno(empno);
			Department dep = departmentRepository.findById(depno).get();
			dep.getMembers().add(emp);
			emp.setCurrentDepartment(dep);
			registerOrUpdateEmployee(emp);
			return emp;
		} catch(Exception e) {
			e.printStackTrace();
		return null;
	}}

	
	@Transactional
	@POST
	@Path("/assign/project")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Project>assignProject(@FormParam("empno") int empno, @FormParam("prono") int projectId) {
		try {
			Employee emp = findByEmpno(empno);
			Project prj = projectService.findById(projectId).get();
			
			emp.getAssignments().add(prj);
			emp = registerOrUpdateEmployee(emp);
			
			return emp.getAssignments();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
