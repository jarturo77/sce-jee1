package com.company.service;
import java.util.List;


import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
//import javax.persistence.Query;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.domain.Employee;
import com.company.domain.Store;




@Stateless
public class EmployeeServiceImpl implements EmployeeService{
	List<Employee> employees = null;
	Logger log = LogManager.getRootLogger();
	
	

	 @PersistenceContext(unitName="EmployeePU")
	 EntityManager em;
	 
	 EntityManagerFactory emf;
	
	
	public EmployeeServiceImpl(){
		//EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("EmployeePU");
		
	}
	


	@Override
	public Employee registerEmployee(Employee employee) {
		if(employee.getName() == null || employee.getLastname() == null || employee.getBirthdate() == null){
			System.out.println("No se pueden guardar valores nulos");
			return null;
		}
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
	        transaction.begin();
		try {
			Store store = em.find(Store.class, employee.getStore().getIdStore());
        	if(store == null) {
        		System.out.println("La sucursal con el id especificado no existe");
        		return null;
        	}
        	//em.getTransaction().begin();
        	em.persist(employee); //actualiza empleado de BD
        	transaction.commit();
        	return employee;
			
		}finally {
			em.close();
			emf.close();
		}
		
	}


	

	@Override
	public Employee findEmployeeById(Employee employee) {
		//EntityManager em = emf.createEntityManager();
		
			return em.find(Employee.class, employee.getIdEmployee() );
	   
        
	}


	@Override
	public Employee ChangeStateEmployee(Employee employee) {
		EntityManager em = emf.createEntityManager();
		try{
			Employee employeePersist = em.find(Employee.class, employee.getIdEmployee());
        	if(employeePersist == null) {
        		System.out.println("Empleado no encontado en base de datos");
        		return null;
        	}
        	///cambia el estado del empleado
        	employeePersist.setActive(!employeePersist.isActive());
        	em.getTransaction().begin();
        	em.merge(employeePersist);
        	em.getTransaction().commit();
        	
        	return employeePersist;
        
        	}finally {
        		em.close();
        		
        	}
	
		
	}


	@Override
	public Employee ChangeStoreEmployee(Employee employee) {
		EntityManager em = emf.createEntityManager();
        try{
        	Employee employee1 = em.find(Employee.class, employee.getIdEmployee());
        	if(employee1 == null) {
        		System.out.println("Empleado no encontado en base de datos");
        		return null;
        		
        	}
        	Store newstore = em.find(Store.class, employee.getStore().getIdStore());
        	if(newstore == null) {
        		System.out.println("La sucursal no se encuentra en la base datos");
        		return null;
        		}
        	
        	employee1.setStore(newstore);
        	employee1.setUpdated_at(employee.getUpdated_at());
        	employee1.setUpdated_by(employee.getUpdated_by());
        	em.getTransaction().begin();
        	em.merge(employee1); //actualiza el empleado de la base datos
        	em.getTransaction().commit();
        	
        	return employee1;
        	
        }finally{
        	em.close();
        	
        }
	}



	//@SuppressWarnings("unchecked")
	@Override
	public Employee employeeAdultOlder(Employee employee) {
	
		return null;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findOldEmployees() {
		
		return em.createNamedQuery("EmployeeOlder").getResultList();
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAllEmployees() {
		 return em.createNamedQuery("Employee.findAll").getResultList();
		 
	}



	@Override
	public void insert(Employee employee) {
	//	log.debug("datos a guardar" + employee );
		em.persist(employee);
		
	}



	@Override
	public void update(Employee employee) {
		log.debug("Empleado a modificar" + employee);
		em.merge(employee);
		
	}



	@Override
	public void delete(Employee employee) {
		em.remove(em.merge(employee));
		
	}


	
}
