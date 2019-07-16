package com.mastek.training.hrapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.entitiese.Department;


// Initilize the JUnit test with Spring Boot application environment 
// Spring Boot test: Used to test Spring application context
// with all test cases identified

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentApplicationTests {

	//scan in memory all the components and provide the best match object in the component
	@Autowired
	DepartmentService DepService;
	
	@Autowired
	Department dep;
	
	
	
	@Test
	public void addDepartmentUsingService() {
		dep.setDepno(0); //setting empno to 0, the default, will add a new employee, setting empno to a current number will update it.
		dep.setDepName("lads");
		dep.setLocation("leeds");
		dep = DepService.registerOrUpdateDepartment(dep);
		assertNotNull(dep);
	}
	
	@Test
	public void findByDepnoUsingService() {
		int depno = 12;
		assertNotNull(DepService.findBydepno(depno));
	}	
	@Test
	public void deleteByDepnoUsingService() {
		int depno = 11;
		DepService.deleteByDepno(depno);
		assertNull(DepService.findBydepno(depno));
	}
	
	@Test
	public void checkFetchByLocation() {
		List<Department> deps = DepService.fetchDepartmentByLocation("leeds");
		for (Department department : deps) {
			System.out.println(department);
			}
		assertEquals(deps.size(),2);}	


}

