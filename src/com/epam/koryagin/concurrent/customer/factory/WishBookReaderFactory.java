package com.epam.koryagin.concurrent.customer.factory;

import com.epam.koryagin.concurrent.customer.Customer;
import com.epam.koryagin.concurrent.customer.WishBookReader;
import com.epam.koryagin.concurrent.repository.Repository;
import com.epam.koryagin.concurrent.repository.BookManager;

public class WishBookReaderFactory implements CustomerAbstractFactory{

	Repository repository;
	
	public WishBookReaderFactory(Repository repository){
		this.repository = repository;
	}
	

	
	@Override
	public Customer createCustomer() {
		Customer reader = WishBookReader.create(repository);
		reader.setWishBookList(BookManager.createRandomWishList(repository));
		return reader;
	}

	@Override
	public String getName() {
		return "WishBookReader";
	}

}
