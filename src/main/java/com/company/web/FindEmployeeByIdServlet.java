package com.company.web;

import java.io.IOException;

import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.domain.Employee;
import com.company.service.EmployeeService;



@WebServlet("/findEmployee")

public class FindEmployeeByIdServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    EmployeeService employeeService;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Se obtiene el parámetro "idEmpleado" de la solicitud
	    String idEmpleadoStr = request.getParameter("idEmpleado");

	    if (idEmpleadoStr != null) {
	        try {
	            // se convierte el ID del empleado a un Integer
	            int idEmpleado = Integer.parseInt(idEmpleadoStr);
	            //se crea una variable tipo Employee y se le agrega el valor del idEmpleado a buscar
	            Employee employee1 = new Employee();
	            employee1.setIdEmployee(idEmpleado);

	            // Llama al método "findEmployeeById" de "EmployeeService"
	            Employee foundEmployee = employeeService.findEmployeeById(employee1);

	            // Muestra el resultado en la consola
	            if (foundEmployee != null) {
	                System.out.println("Empleado encontrado: " + foundEmployee.getName() + " " + foundEmployee.getLastname()+ " " + foundEmployee.getBirthdate());
	            } else {
	                System.out.println("Empleado no encontrado");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("ID de empleado no válido: " + idEmpleadoStr);
	        }
	    } else {
	        System.out.println("ID de empleado no proporcionado en la solicitud.");
	    }
	}


}
