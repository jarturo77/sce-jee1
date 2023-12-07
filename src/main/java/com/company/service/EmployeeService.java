package com.company.service;

import java.util.List;

import javax.ejb.Local;

import com.company.domain.Employee;



@Local
public interface EmployeeService {
	public List<Employee> findOldEmployees();
	
	public List<Employee> findAllEmployees();
	
	
	
	 public Employee findEmployeeById(Employee employee);
	 

	 public Employee ChangeStateEmployee(Employee employee);

	 public Employee registerEmployee(Employee employee);

	 public Employee ChangeStoreEmployee(Employee employee);
	
	 public Employee employeeAdultOlder(Employee employee);
	 
	 public void insert(Employee employee);
	 
	 public void update(Employee employee);
	 
	 public void delete(Employee employee);
	 
	

	

}
