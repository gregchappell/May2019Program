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

import com.mastek.training.hrapp.apis.ProjectService;
import com.mastek.training.hrapp.entitiese.Project;



// Initilize the JUnit test with Spring Boot application environment 
// Spring Boot test: Used to test Spring application context
// with all test cases identified

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {

	//scan in memory all the components and provide the best match object in the component
	@Autowired
	ProjectService ProService;
	
	@Autowired
	Project pro;
	
	
	
	@Test
	public void addProjectUsingService() {
		pro.setProno(0); //setting empno to 0, the default, will add a new employee, setting empno to a current number will update it.
		pro.setProName("team");
		pro.setCustomer("mor");
		pro = ProService.registerOrUpdateProject(pro);
		assertNotNull(pro);
	}
	
	@Test
	public void findByPronoUsingService() {
		int prono = 16;
		assertNotNull(ProService.findByProno(prono));
	}	
	@Test
	public void deleteByPronoUsingService() {
		int prono = 14;
		ProService.deleteByProno(prono);
		assertNull(ProService.findByProno(prono));
	}
	
	@Test
	public void checkFetchByCustomer() {
		List<Project> pros = ProService.fetchProjectByCustomer("mor");
		for (Project project : pros) {
			System.out.println(project);
			}
		assertEquals(pros.size(),2);}	
	

}
