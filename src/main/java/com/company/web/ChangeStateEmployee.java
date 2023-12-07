package com.company.web;

import com.company.domain.Employee;
import com.company.service.EmployeeService;
import com.company.service.EmployeeServiceImpl;

public class ChangeStateEmployee {
	public static void main(String[] args) {
///////////////////////////////cambiar estado del empleado 

		EmployeeService changeState = new EmployeeServiceImpl();
		 Employee employee1 = new Employee();
	     employee1.setIdEmployee(14);
		Employee state = changeState.ChangeStateEmployee(employee1);
		if (state != null) {
			System.out.println(
					"El cambio de estado del empleado se realizó correctamente. Estado es: " + state.isActive());

		} else {
			System.out.println("No se puede cambiar el estado al que pertenece el empleado");
		}

	}

}
