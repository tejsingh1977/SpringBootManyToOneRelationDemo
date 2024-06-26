package com.wipro.ecms.repository;

import org.springframework.data.repository.CrudRepository;

import com.wipro.ecms.entity.Loan;

public interface LoanRepository extends CrudRepository<Loan, Integer>{
	
}
