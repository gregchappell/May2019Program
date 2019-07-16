package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entitiese.Department;
import com.mastek.training.hrapp.repositories.DepartmentRepository;



// indicate to spring to create an object of this class as component

@Component
@Scope("singleton")
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
			
	public DepartmentService() {
		System.out.println("Department Service Created");
	}
	
	
	public Department registerOrUpdateDepartment(Department dep) {
		dep = departmentRepository.save(dep);
		System.out.println("Department Registered" + dep);
		return dep;
	}


	public Department findBydepno(int depno) {
		try {
			return departmentRepository.findById(depno).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public List<Department> fetchDepartmentByLocation(String location){
			return departmentRepository.findByLocation(location);
	}
		
	
	public void deleteByDepno(int depno) {
		departmentRepository.deleteById(depno);
	}

}
