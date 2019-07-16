package com.mastek.training.hrapp.entitiese;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class EmployeeLifeCycleListener {
	
	//life cycle methods should be:
	//public, void and <name>(entity e)
	
	@PrePersist
	public void beforeInsert(Employee e) {
		System.out.println("Before Insert: "+e);
	}
	@PostPersist
	public void afterInsert(Employee e) {
		System.out.println("After Insert: "+e);
	}
	
	@PreUpdate
	public void beforeUpdate(Employee e) {
		System.out.println("Before Update: "+e);
	}
	@PostUpdate
	public void afterUpdate(Employee e) {
		System.out.println("After Update: "+e);
	}	
	
	@PreRemove
	public void beforeDelete(Employee e) {
		System.out.println("Before Delete: "+e);
	}
	@PostLoad
	public void afterSelect(Employee e) {
		System.out.println("After Select: "+e);
	}
}
