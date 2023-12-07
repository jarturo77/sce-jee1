package com.company.web;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.company.domain.Employee;
import com.company.domain.Store;
import com.company.domain.User;
import com.company.service.EmployeeService;
import com.company.service.EmployeeServiceImpl;


public class ChangeStoreEmployee {
	public static void main(String[] args) {
//////////////////////modificar sucursal a la que pertenece el empleado

		EmployeeService changeStore = new EmployeeServiceImpl();
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
	     EntityManager em = emf.createEntityManager();
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
		
		Store store = new Store();
	     store.setIdStore(1);
	     
		 Employee employee1 = new Employee();
		  
	     employee1.setIdEmployee(22);
	     employee1.setStore(store);
	     
	  // Establecer los atributos de auditoría
	      
	     employee1.setUpdated_by(username);
         
         employee1.setUpdated_at(new Date());
	    
	    
		Employee newStore = changeStore.ChangeStoreEmployee(employee1);
		if (newStore != null) {
			System.out.println(
					"El cambio de sucursal del empleado se realizó correctamente. IDSucursal: " + newStore.getStore());

		} else {
			System.out.println("No se puede cambiar la sucursal a la que pertenece el empleado");
		}

	}

}
