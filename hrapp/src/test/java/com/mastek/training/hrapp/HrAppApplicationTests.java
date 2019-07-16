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

import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.entitiese.Employee;

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


}
