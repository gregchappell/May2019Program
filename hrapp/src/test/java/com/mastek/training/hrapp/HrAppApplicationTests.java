package com.mastek.training.hrapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.apis.ProjectService;
import com.mastek.training.hrapp.entitiese.Department;
import com.mastek.training.hrapp.entitiese.Employee;
import com.mastek.training.hrapp.entitiese.Project;

// Initilize the JUnit test with Spring Boot application environment 
// Spring Boot test: Used to test Spring application context
// with all test cases identified

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrAppApplicationTests {

	//scan in memory all the components and provide the best match object in the component
	@Autowired
	EmployeeService empService;
	
	@Autowired
	Employee emp;
	
	
	
	@Test
	public void addEmployeeUsingService() {
		emp.setEmpno(6); //setting empno to 0, the default, will add a new employee, setting empno to a current number will update it.
		emp.setName("new guy 6");
		emp.setSalary(5434);
		emp = empService.registerOrUpdateEmployee(emp);
		assertNotNull(emp);
	}
	
	@Test
	public void findByEmpnoUsingService() {
		int empno = 1;
		assertNotNull(empService.findByEmpno(empno));
	}	
	@Test
	public void deleteByEmpnoUsingService() {
		int empno = 5;
		empService.deleteByEmpno(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService.fetchEmployeesBySalaryRange(0, 9000);
		for (Employee employee : emps) {
			System.out.println(employee);
			}
		assertEquals(emps.size(),7);}	
	//employee service @autowired is already assingned at the top

	
	@Autowired
	DepartmentService depService;
	
	@Autowired
	ProjectService proService;
	
	
	
	
	
	@Test
	public void manageAssociations() {
		Department d1 = new Department();
		d1.setDepName("Admin");
		d1.setLocation("Uk");
		
		Employee emp1 = new Employee();
		emp1.setName("Admin emp 1");
		emp1.setSalary(12123);
		
		Employee emp2 = new Employee();
		emp2.setName("Admin emp 2");
		emp2.setSalary(2344);
		
		Project p1 = new Project();
		p1.setProName("Uk project");
		p1.setCustomer("Uk customer");
		
		Project p2 = new Project();
		p2.setProName("Uk project 2");
		p2.setCustomer("cusotmer 2");
		
		// one to many
		d1.getMembers().add(emp1);
		d1.getMembers().add(emp2);
		
		// many to one for each employee to assign the department 
		emp1.setCurrentDepartment(d1);
		emp2.setCurrentDepartment(d1);
		
		//many to many
		emp1.getAssignments().add(p1);
		emp1.getAssignments().add(p2);
		emp2.getAssignments().add(p1);
		
		depService.registerOrUpdateDepartment(d1);
	
	}


}
