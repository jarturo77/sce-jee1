package com.company.web;

import java.text.ParseException;

//import java.time.LocalDate;
import java.util.Date; // Importa la clase Date

//import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.company.domain.Employee;

public class DeactivateEmployee {
	static Logger log = LogManager.getLogger();

	public static void main(String[] args) throws ParseException {

		// List<Employee> employees = null;
		// Employee employee = null;
		// String jpql = null;
		// Query q = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		System.out.println("Desactivando empleados....");

		try {
			int idStore = 1; // Reemplaza con el ID de la sucursal específica

			String jpql = ("UPDATE Employee e SET e.active = false WHERE e.store.idStore = :storeId AND e.birthdate <= :birthdateLimit");

			int ageLimit = 60; // Cambia la edad límite según tus necesidades
			Date birthdate1 = new Date(System.currentTimeMillis() - ageLimit * 365 * 24 * 60 * 60 * 1000L);
			//String birthdate1 = LocalDate.now().minusYears(ageLimit).toString();
			
			System.out.println("fecha limite para calcular tercera edad: " + birthdate1);

			int updatedCount = em.createQuery(jpql).setParameter("storeId", idStore).setParameter("birthdateLimit", birthdate1).executeUpdate();
			
			

			System.out.println("Número de empleados desactivados: " + updatedCount);
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.commit();
			}
			em.close();
			emf.close();
		}

	}

}
