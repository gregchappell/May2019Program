package com.mastek.training.hrapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entitiese.Project;



@Component
public interface ProjectRepository extends CrudRepository<Project, Integer>{
	public List<Project> findByCustomer(
			@Param("customer") String customer);

}
