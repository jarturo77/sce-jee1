package com.company.web;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.company.domain.Store;
import com.company.domain.User;
import com.company.service.StoreService;
import com.company.service.StoreServiceImpl;

public class ChangeAddressStore {
	public static void main(String[] args) {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
	     EntityManager em = emf.createEntityManager();
		
		StoreService changeAddres = new StoreServiceImpl();
		
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
		store.setAddress("Bulevar del ejercito,Ilopango");
		store.setUpdated_at(new Date());
		store.setUpdated_by(username);
		
		Store changeaddress = changeAddres.ChangeAddressStore(store);
		
		if(changeaddress != null){
			System.out.println("El cambio de direccion de sucursal se realizó correctamente. La nueva direccion es: " + changeaddress.getAddress());	
			
		}else{
			System.out.println("No se puede cambiar la direccion de la sucursal");
		}
	}

}
