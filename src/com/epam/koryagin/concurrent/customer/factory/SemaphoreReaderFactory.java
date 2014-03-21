package com.epam.koryagin.concurrent.customer.factory;

import com.epam.koryagin.concurrent.customer.Customer;
import com.epam.koryagin.concurrent.customer.SemaphoreReader;
import com.epam.koryagin.concurrent.repository.Repository;

public class SemaphoreReaderFactory implements CustomerAbstractFactory{
	Repository repository;
	
	public SemaphoreReaderFactory(Repository repository){
		this.repository = repository;
	}
	
	@Override
	public Customer getInstance() {
		return SemaphoreReader.create(repository);
	}

	@Override
	public String getName() {
		return "SemaphoreReader";
	}
}
