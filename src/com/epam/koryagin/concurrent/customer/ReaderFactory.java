package com.epam.koryagin.concurrent.customer;

import com.epam.koryagin.concurrent.repository.Repository;

public class ReaderFactory implements CustomerAbstractFactory {

	Repository repository;
	
	public ReaderFactory(Repository repository){
		this.repository = repository;
	}
	
	@Override
	public Customer getInstance() {
		return Reader.create(repository);
	}

}
