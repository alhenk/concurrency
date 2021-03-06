package com.epam.koryagin.concurrent.customer.factory;

import com.epam.koryagin.concurrent.customer.Customer;
import com.epam.koryagin.concurrent.customer.Reader;
import com.epam.koryagin.concurrent.repository.Repository;

public class ReaderFactory implements CustomerAbstractFactory {

	Repository repository;
	
	public ReaderFactory(Repository repository){
		this.repository = repository;
	}
	
	@Override
	public Customer createCustomer() {
		Customer reader = Reader.create(repository);
		return reader;
	}

	@Override
	public String getName() {
		return "Reader";
	}

}
