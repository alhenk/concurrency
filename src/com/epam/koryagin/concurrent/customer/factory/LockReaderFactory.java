package com.epam.koryagin.concurrent.customer.factory;

import com.epam.koryagin.concurrent.customer.Customer;
import com.epam.koryagin.concurrent.customer.LockReader;
import com.epam.koryagin.concurrent.repository.Repository;

public class LockReaderFactory implements CustomerAbstractFactory {

	Repository repository;
	
	public LockReaderFactory(Repository repository){
		this.repository = repository;
	}
	
	@Override
	public Customer getInstance() {
		return LockReader.create(repository);
	}

	@Override
	public String getName() {
		return "LockReader";
	}

}