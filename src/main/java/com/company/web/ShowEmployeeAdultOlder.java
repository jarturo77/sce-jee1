package com.company.web;

import java.util.List;

import javax.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.company.domain.Employee;




public class ShowEmployeeAdultOlder {
	static Logger log = LogManager.getLogger();
	
	
	public static void main(String[] args) {
		
		
		List<Employee> employee= null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
		EntityManager em = emf.createEntityManager();
		
		log.debug("\n Listado de Empleados");
		//Store store = new Store();
		//int store1 = 4;
		try {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE FUNCTION('YEAR', CURRENT_DATE)- FUNCTION('YEAR', e.birthdate) > 60 and  e.active = true and (e.store.idStore)= 4 and e.gender ='Femenino'ORDER BY e.birthdate",Employee.class);
		query.setMaxResults(3);
		//jpql = ("SELECT e FROM Employee e WHERE FUNCTION('YEAR', CURRENT_DATE)- FUNCTION('YEAR', e.birthdate) > 60 and  e.active = true and (e.store.idStore)= 4 and e.gender ='Femenino'ORDER BY e.birthdate");
		
		//SELECT e FROM Employee e WHERE FUNCTION('YEAR', CURRENT_DATE)- FUNCTION('YEAR', e.birthdate) > 60
		//SELECT e FROM Employee e WHERE e.active = true and (e.store.idStore)= 4 and e.gender ='Femenino' ORDER BY e.idEmployee
		employee = query.getResultList();
		showEmployee(employee);
		} finally {
            em.close();
            emf.close();
        }

		
	}

	private static void showEmployee(List<Employee> employee) {
		for(Employee e: employee) {
			//log.debug(e);
			System.out.println("Nombre : "+e.getName());
			System.out.println("Apellido : "+e.getLastname());
			System.out.println("Estado del empleado  : "+e.isActive());
			System.out.println("Store  : "+e.getStore().getName());
			
			System.out.println();
	        
		}
		
	}
}
