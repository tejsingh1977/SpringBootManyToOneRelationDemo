package com.wipro.ecms.repository;

import org.springframework.data.repository.CrudRepository;

import com.wipro.ecms.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
}
