package com.mastek.training.hrapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entitiese.Department;

@Component
public interface DepartmentRepository extends CrudRepository<Department, Integer>{
	public List<Department> findByLocation(
			@Param("location") String location);

}
