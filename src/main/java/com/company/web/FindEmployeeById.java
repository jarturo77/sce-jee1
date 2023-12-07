package com.company.web;

import com.company.domain.Employee;
import com.company.service.EmployeeService;
import com.company.service.EmployeeServiceImpl;

public class FindEmployeeById {
	public static void main(String[] args) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		
		 Employee employee1 = new Employee();
	     employee1.setIdEmployee(12);
		
		Employee employee = employeeService.findEmployeeById(employee1);
		if(employee != null) {
			System.out.println("Empleado encontrado: " + employee.getName() + " " + employee.getLastname());
		}else {
			System.out.println("Empleado no encontrado");
		}
	}

}
