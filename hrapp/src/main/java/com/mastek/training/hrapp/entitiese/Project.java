package com.mastek.training.hrapp.entitiese;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // one copy for each test case
@Entity // Declares the class as an entity
@Table(name = "JPA_Project") // names the table created as JPA_EMPLOYEE
//@EntityListeners({DepartmentLifeCycleListener.class})
@NamedQueries({
	@NamedQuery(name="Project.findByCustomer",
			query="select e from Project e where e.customer = : customer")
	
})

public class Project implements Serializable { // manage Serialisation of objects
	@Value("-1")
	private int prono;
	@Value("lads")
	private String proname;
	@Value("proj")
	private String customer;
	
	private Set<Employee> team = new HashSet<>();
	
	// mappedBy: check the configurations for Many to Many associations 
	// In employee class getAssignments() method
	@ManyToMany(mappedBy = "assignments")
	public Set<Employee> getTeam() {
		return team;
	}


	public void setTeam(Set<Employee> team) {
		this.team = team;
	}


	public Project(){
		System.out.println("Project Created");
		}
	
	
	@Id // Declare the property as Primary Key
	@Column(name = "project_number") // Declare the name of the column
	@GeneratedValue(strategy = GenerationType.AUTO) // Auto generates the Primary key as incremented
	public int getProno() {
		return prono;
	}
	public void setProno(int prono) {
		this.prono = prono;
	}
	
	@Column(name="project_name", nullable=false, length=45) // Column employee_name cannot be null or have a larger length than 45
	public String getProName() {
		return proname;
	}

	public void setProName(String proname) {
		this.proname = proname;
	}
	public String getCustomer() { //JPA will apply default configurations
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Project [prono=" + prono + ", proname=" + proname + ", customer=" + customer + "]";
	}


}
