package com.epam.koryagin.concurrent.customer.factory;

import com.epam.koryagin.concurrent.customer.Customer;

public interface CustomerAbstractFactory {
	public Customer createCustomer();
	public String getName();
}
