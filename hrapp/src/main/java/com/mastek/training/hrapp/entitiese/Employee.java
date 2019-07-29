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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component


@Scope("prototype") // one copy for each test case
@Entity // Declares the class as an entity
@Table(name = "JPA_EMPLOYEE") // names the table created as JPA_EMPLOYEE
@EntityListeners({EmployeeLifeCycleListener.class})
@NamedQueries({
	@NamedQuery(name="Employee.findBySalary",
			query="select e from Employee e where e.salary between :min and :max")
	
})
@XmlRootElement
public class Employee implements Serializable { // manage Serialisation of objects
	@FormParam("empno")
	@Value("-1")
	private int empno;
	@Value("Ollie")
	@FormParam("name") // name of parameters passed via html form
	private String name;
	@Value("100.0")
	@FormParam("salary")
	private double salary;

	private Set<Project> assignments = new HashSet<>();
	// manyToMany configuring the association for both entities 
	//joinTable provides all the configuration for the third table
	//name: name of the join table
	//joinColumn: Foregin Key column name for current class
	//inverseJoinColumns: Foregin Key Column for other class
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="JPA_ASSIGNMENT", joinColumns=@JoinColumn(name="FK_EMPNO"),
			inverseJoinColumns = @JoinColumn(name = "FK_PROJECTID"))
	// @XmlTransient // ignore the collections while using api
	public Set<Project> getAssignments() {
		return assignments;
	}
	
	public void setAssignments(Set<Project> assignments) {
		this.assignments = assignments;
	}
	// many to one: each emp to one dep
	private Department currentDepartment;
	
	@ManyToOne
	@JoinColumn(name="FK_DepartmentId")
	public Department getCurrentDepartment() {
		return currentDepartment;
	}
	public void setCurrentDepartment(Department currentDepartment) {
		this.currentDepartment = currentDepartment;
	}
	
	public Employee() {
		System.out.println("Employee Created");
	}
	
	@Id // Declare the property as Primary Key
	@Column(name = "employee_number") // Declare the name of the column
	@GeneratedValue(strategy = GenerationType.AUTO) // Auto generates the Primary key as incremented
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	
	@Column(name="employee_name", nullable=false, length=45) // Column employee_name cannot be null or have a larger length than 45
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() { //JPA will apply default configurations
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", name=" + name + ", salary=" + salary + "]";
	}


}
