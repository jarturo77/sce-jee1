package com.company.service;

import java.util.List;

import com.company.domain.Store;

public interface StoreService {
	public List<Object[]> orderStore();
	
	public List<Store> findAllStores();
	public Store ChangeAddressStore(Store store);
	public Store ChangeNameStore(Store store);

	public void insert(Store store);

	public void update(Store store);

	public void delete(Store store);
}
