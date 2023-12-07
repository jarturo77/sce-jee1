package com.company.web;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.company.domain.Employee;
import com.company.service.EmployeeService;


@WebServlet("/employeesOlder")

public class EmployeeOlderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    EmployeeService employeeService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Employee> employees = employeeService.findOldEmployees();
        System.out.println("employees:" + employees);
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/listEmployeesOlder.jsp").forward(request, response);
    }

}
