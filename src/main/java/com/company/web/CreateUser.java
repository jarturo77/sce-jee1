package com.company.web;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.mindrot.jbcrypt.BCrypt;

import com.company.domain.User;

public class CreateUser {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
		EntityManager em = emf.createEntityManager();

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			String username = "admin";
            String plainPassword = "123456";
            
			// Generar un salt aleatorio
            String salt = BCrypt.gensalt();
            
         // Encriptar la contraseña con el salt
            String hashedPassword = BCrypt.hashpw(plainPassword, salt);
            
		    User user = new User();
		    user.setUsername(username);
		    user.setActive(true);
		    user.setPassword(hashedPassword);
		    
		    

		    em.persist(user);
		    transaction.commit();
		    
		 // Mostrar los detalles del usuario creado en la consola
            System.out.println("Usuario creado:");
            System.out.println("ID: " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Activo: " + user.isActive());
		} catch (Exception e) {
		    if (transaction != null && transaction.isActive()) {
		        transaction.rollback();
		    }
		    e.printStackTrace();
		} finally {
		    em.close();
		    emf.close();
		}

	}

}
