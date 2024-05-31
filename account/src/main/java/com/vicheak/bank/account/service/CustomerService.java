package com.vicheak.bank.account.service;

import java.util.List;

import com.vicheak.bank.account.entity.Customer;

public interface CustomerService {

	Customer save(Customer customer);
	
	List<Customer> getCustomers();

	Customer getById(Long id); 
	
}
