package com.company.web;

import java.util.List;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.company.domain.Employee;
import com.company.domain.Store;

public class OrderStoreDesc {
static Logger log = LogManager.getLogger();
	
	//@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		String jpql = null;
		//List<Store> store = null;
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
		EntityManager em = emf.createEntityManager();
		
		log.debug("\n Listado de Sucursales");
		//Store store = new Store();
		//int store1 = 4;
		/* TypedQuery<Store> query = em.createQuery("SELECT s.name, COUNT(e.gender) AS num_mujeres FROM Store s JOIN Employee e "
				+ "ON s.idStore = e.store.idStore WHERE e.gender = 'Femenino' "
				+ "GROUP BY s.idStore ORDER BY num_mujeres",Store.class);  */
		try {
		jpql = ("SELECT s, COUNT(e) AS femaleCount FROM Store s LEFT JOIN s.employeeList e WHERE e.gender = 'Femenino' GROUP BY s.idStore ORDER BY femaleCount DESC");
		
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setMaxResults(3);
        List<Object[]> results = query.getResultList();
		//query.setMaxResults(3);
		
		//store = query.getResultList();
		//store = em.createQuery(jpql).getResultList();
        System.out.println("");
        
   
        for (Object[] result : results) {
        	
            Store store = (Store) result[0];
            Long femaleCount = (Long) result[1];
            System.out.println("Store: " + store.getName() + ",   Female Count: " + femaleCount);
        }
		//showStore(store);
		} finally {
            em.close();
            emf.close();
        }

		
	}

		
		
}

	

	
		
	


