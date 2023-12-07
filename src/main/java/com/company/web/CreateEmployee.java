package com.company.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.company.domain.Employee;
import com.company.domain.User;
import com.company.domain.Store;
import com.company.service.EmployeeService;
import com.company.service.EmployeeServiceImpl;

public class CreateEmployee {
	public static void main(String[] args) throws ParseException {
		
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
	     EntityManager em = emf.createEntityManager();
		//////////////////////////////crear un nuevo empleado
		
		EmployeeService createEmployee = new EmployeeServiceImpl();
		//parametros para crear un nuevo empleado
		
		 //Create Employee1 Entity
		Store store1 = new Store();
		store1.setIdStore(1);
		
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateAsString = "04/12/1960";
		Date date = sourceFormat.parse(dateAsString);
		
		 // Seleccionar un usuario de la tabla de usuarios
        String usernameToFind = "admin"; // Reemplaza con el nombre de usuario que deseas seleccionar
        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", usernameToFind)
                .getResultList();

        if (users.isEmpty()) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        User selectedUser = users.get(0);
        String username = selectedUser.getUsername();
		
	      Employee employee1 = new Employee();
	      employee1.setName("Stanley");
	      employee1.setLastname("Martinez");
	      employee1.setGender("Masculino");
	      employee1.setBirthdate(date);
	      employee1.setActive(true);
	      employee1.setStore(store1);
	      
	   // Establecer los atributos de auditoría
	      employee1.setCreated_by(username);
	      employee1.setUpdated_by(username);
          employee1.setCreated_at(new Date());
          employee1.setUpdated_at(new Date());
	      
		
		Employee newEmployee = createEmployee.registerEmployee(employee1);
		
		if(newEmployee != null){
			System.out.println("Empleado creado con exito. Nombre: " + newEmployee.getName());	
			
		}else{
			System.out.println("No se puede registrar el empleado");
		}
		
	}

}
