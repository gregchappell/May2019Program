package com.mastek.training.hrapp.entitiese;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // one copy for each test case
@Entity // Declares the class as an entity
@Table(name = "JPA_Department") // names the table created as JPA_EMPLOYEE
//@EntityListeners({DepartmentLifeCycleListener.class})
@NamedQueries({
	@NamedQuery(name="Department.findByLocation",
			query="select e from Department e where e.location = : location")
	
})

public class Department implements Serializable { // manage Serialisation of objects
	private int depno;
	private String depname;
	private String location;
	private Set<Employee> members = new HashSet<>();
	
	//oneTomany used on the get method of set, to configure associations
	//  fetch=lazy, delays the initialisation until the user calls getMembers()
	// fetch=eager, initialises the object on findMethod()
	
	// Cascade ensures all entity operations done on department would be performed on 
	//on each associated employee object
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "currentDepartment")
	public Set<Employee> getMembers() {
		return members;
	}


	public void setMembers(Set<Employee> members) {
		this.members = members;
	}


	public Department(){
		System.out.println("Department Created");
		}
	
	
	@Id // Declare the property as Primary Key
	@Column(name = "department_number") // Declare the name of the column
	@GeneratedValue(strategy = GenerationType.AUTO) // Auto generates the Primary key as incremented
	public int getDepno() {
		return depno;
	}
	public void setDepno(int depno) {
		this.depno = depno;
	}
	
	@Column(name="department_name", nullable=false, length=45) // Column employee_name cannot be null or have a larger length than 45
	public String getDepName() {
		return depname;
	}

	public void setDepName(String depname) {
		this.depname = depname;
	}
	public String getLocation() { //JPA will apply default configurations
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Department [depno=" + depno + ", depname=" + depname + ", location=" + location + "]";
	}


}
