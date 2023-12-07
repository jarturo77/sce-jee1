package com.company.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

@Entity
@Table(name="employee")
@NamedQueries({
	
	//jpql para recuperar todos los objetos tipo empleado de la base de datos 
    @NamedQuery(name = "EmployeeOlder", query = "SELECT e FROM Employee e WHERE FUNCTION('YEAR', CURRENT_DATE)- FUNCTION('YEAR', e.birthdate) > 60 and  e.active = true and (e.store.idStore)= 4 and e.gender ='Femenino'ORDER BY e.birthdate"),
  //jpql para recuperar todos los objetos tipo persona de la base de datos ordenados por idPersona
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e ORDER BY e.idEmployee")
   
    
    
    
    
})


public class Employee extends AuditableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idEmployee")
	
	private  int idEmployee;
	private String name;
	private String lastname;
	private String gender;
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	private boolean active;
	
	
	@JoinColumn(name = "idStore", referencedColumnName = "idStore")
	@ManyToOne 
	private Store store;
	
	public Employee() {
		
	}


	


	public Employee(String name, String lastname,String gender, Date birthdate, boolean active) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.gender= gender;
		this.birthdate = birthdate;
		this.active = active;
	
	}


	public String getGender() {
		return gender;
	}





	public void setGender(String gender) {
		this.gender = gender;
	}





	public int getIdEmployee() {
		return idEmployee;
	}


	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	

	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}





	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Store getStore()
	   {
	      return store;
	   }

	   public void setStore(Store store)
	   {
	      this.store = store;
	   }





	@Override
	public String toString() {
		return "Employee [idEmployee=" + idEmployee + ", name=" + name + ", lastname=" + lastname + ", gender=" + gender
				+ ", birthdate=" + birthdate + ", active=" + active + ", store=" + store + "]";
	}


	
			
	

}
