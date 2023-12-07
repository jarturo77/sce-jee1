package com.company.web;



import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;


import com.company.domain.Employee;
import com.company.domain.Store;
import com.company.service.EmployeeService;
import com.company.service.StoreService;



@Named("employeeBean")
@RequestScoped
public class EmployeeBean {
	Logger log = LogManager.getRootLogger();

	@Inject
	private EmployeeService employeeService;

	private Employee employeeSelected;

	List<Employee> employees;

	@Inject
	private StoreService storeService;

	List<Store> stores;
	

	
	
	
	

	


	public EmployeeBean() {
		log.debug("Iniciando el objeto EmployeeBean");
	}


	@PostConstruct
	public void init() {
		// Inciamos las variables
		this.employees = employeeService.findAllEmployees();
		log.debug("empleados recuperados en ManagedBean:" + this.employees);
		
		this.employeeSelected = new Employee();
		
		
		
		this.loadStores();
		
		
		

	}

	
	

	
	@Transactional
	public void editListener(RowEditEvent event) {
		try {
		Employee employee = (Employee) event.getObject();
		System.out.println("modificando");
		employeeSelected.setUpdated_by("admin");
		employeeSelected.setUpdated_at(new Date());
		employeeService.update(employee);
		PrimeFaces.current().executeScript("PF('successUpdateDialog').show();");
		} catch (Exception e) {
            e.printStackTrace(); 

            // Mostrar un mensaje de error al usuario
            FacesContext.getCurrentInstance().addMessage("dataTableMessages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - Modificar", "Error al modificar el empleado."));
	 }
	}
	
	@Transactional
	public void insertEmployee() {
		try {
		
		employeeSelected.setCreated_by("admin");
    	employeeSelected.setCreated_at(new Date());
	
		 this.employeeService.insert(employeeSelected);
		 this.employees.add(employeeSelected);
		 PrimeFaces.current().executeScript("PF('successSaveDialog').show();");
		 this.employeeSelected = null;
		} catch (Exception e) {
	            e.printStackTrace(); 

	            // Mostrar un mensaje de error al usuario
	            FacesContext.getCurrentInstance().addMessage("dataTableMessages",
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - Guardar", "Error al registrar el empleado."));
		 }

	       
	}
	
	@Transactional
	public void deleteEmployee() {
		try {
		this.employeeService.delete(employeeSelected);
		this.employees.remove(this.employeeSelected);
		PrimeFaces.current().executeScript("PF('successDeleteDialog').show();");
		this.employeeSelected = null;
		 } catch (Exception e) {
	            e.printStackTrace(); 

	            // Mostrar un mensaje de error al usuario
	            FacesContext.getCurrentInstance().addMessage("dataTableMessages",
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - Eliminar", "Error al eliminar el empleado."));
		 }
	}

	public void restartEmployeeSelected() {
		this.employeeSelected = new Employee();
	}
	
	
	public Employee getEmployeeSelected() {
		return employeeSelected;
	}

	public void setEmployeeSelected(Employee employeeSelected) {
		this.employeeSelected = employeeSelected;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void loadStores() {
		
		stores = storeService.findAllStores();
		
		//log.debug("stores string" + stores);

	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}
	





}
