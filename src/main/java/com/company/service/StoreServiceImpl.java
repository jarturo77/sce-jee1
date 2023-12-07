package com.company.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;


import com.company.domain.Store;

public class StoreServiceImpl implements StoreService {
	@PersistenceContext(unitName = "EmployeePU")
	 EntityManager em;

	EntityManagerFactory emf;

	public StoreServiceImpl() {
		emf = Persistence.createEntityManagerFactory("EmployeePU");
	}
	/*
	 * @Override public Store ChangeAddressStore(int idStore, String address) {
	 * EntityManager em = emf.createEntityManager();
	 * 
	 * try { Store store = em.find(Store.class, idStore); if(store == null) {
	 * System.out.println("La sucursal con el id especificado no existe"); return
	 * null; }
	 * 
	 * store.setAddress(address); em.getTransaction().begin(); em.merge(store);
	 * //actualiza la direccion de la tienda em.getTransaction().commit();
	 * 
	 * return store; }finally{ em.close(); } }
	 * 
	 * 
	 * @Override public Store ChangeNameStore(int idStore, String name) {
	 * EntityManager em = emf.createEntityManager();
	 * 
	 * try { Store store1 = em.find(Store.class, idStore); if(store1 == null) {
	 * System.out.println("La sucursal con el id especificado no existe"); return
	 * null; }
	 * 
	 * store1.setName(name); em.getTransaction().begin(); em.merge(store1);
	 * //actualiza el nombre de la tienda em.getTransaction().commit();
	 * 
	 * return store1; }finally{ em.close(); } }
	 * 
	 */

	@Override
	public Store ChangeAddressStore(Store store) {
		if(store.getAddress() == null ){
			System.out.println("No se pueden guardar valores nulos");
			return null;
		}
		EntityManager em = emf.createEntityManager();

		try {
			Store storeToChange = em.find(Store.class, store.getIdStore());
			if (storeToChange == null) {
				System.out.println("La sucursal con el id especificado no existe");
				return null;
			}

			storeToChange.setAddress(store.getAddress());
			storeToChange.setUpdated_at(store.getUpdated_at());
			storeToChange.setUpdated_by(store.getUpdated_by());
			em.getTransaction().begin();
			em.merge(storeToChange); // actualiza la direccion de la tienda
			em.getTransaction().commit();

			return store;
		} finally {
			em.close();
		}
	}

	@Override
	public Store ChangeNameStore(Store store) {
		if(store.getName() == null ){
			System.out.println("No se pueden guardar valores nulos");
			return null;
		}
		EntityManager em = emf.createEntityManager();

		try {
			Store storeToChange = em.find(Store.class, store.getIdStore());
			if (storeToChange == null) {
				System.out.println("La sucursal con el id especificado no existe");
				return null;
			}

			storeToChange.setName(store.getName());
			storeToChange.setUpdated_at(store.getUpdated_at());
			storeToChange.setUpdated_by(store.getUpdated_by());
			em.getTransaction().begin();
			em.merge(storeToChange); // actualiza el nombre de la tienda
			em.getTransaction().commit();

			return store;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> orderStore() {
		return em.createNamedQuery("OrderStore").getResultList();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Store> findAllStores() {
		return em.createNamedQuery("All.Stores").getResultList();
	}

	@Override
	public void insert(Store store) {
		em.persist(store);
	}

	@Override
	public void update(Store store) {
		em.merge(store);
		
	}

	@Override
	public void delete(Store store) {
		em.remove(em.merge(store));
		
	}

}
