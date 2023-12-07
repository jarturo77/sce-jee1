package com.company.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
	
  //jpql para ordenar las primeras 3 sucurales que tiene mas mujeres y ordenarlas de manera descendentes
    @NamedQuery(name="OrderStore", query="SELECT s, COUNT(e) AS femaleCount FROM Store s LEFT JOIN s.employeeList e WHERE e.gender = 'Femenino' GROUP BY s.idStore ORDER BY femaleCount DESC"),
    @NamedQuery(name="All.Stores", query="SELECT s FROM Store s")
    
    
    
    
})
@Table(name="store")

public class Store extends AuditableEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idStore")
	
	private int idStore;
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "store")
	private List<Employee> employeeList;
	
	public Store() {
		
	}

	public Store(int idStore, String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public int getIdStore() {
		return idStore;
	}

	public void setIdStore(int idStore) {
		this.idStore = idStore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Store [idStore=" + idStore + ", name=" + name + ", address=" + address + "]";
	}
	
	


		
	
	

}
