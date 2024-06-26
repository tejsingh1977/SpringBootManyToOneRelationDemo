package com.wipro.ecms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecms.dto.LoanDTO;
import com.wipro.ecms.exception.InfyBankException;
import com.wipro.ecms.service.CustomerLoanService;

@RestController
@RequestMapping(value = "/indusindbank")
public class CustomerLoanApi {	
	// http://127.0.0.1:9091/indusindbank/loan/2001
	// http://127.0.0.1:9091/indusindbank/loancustomer
	// http://localhost:8080/indusindbank/loan/2001
	// http://localhost:8080/indusindbank/loan/2001
	@Autowired
	CustomerLoanService customerLoanService;
	
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/loan/{loanId}")
	public ResponseEntity<LoanDTO> getLoanDetails(@PathVariable Integer loanId) throws InfyBankException{	
		LoanDTO loanDTO = customerLoanService.getLoanDetails(loanId);
		return new ResponseEntity<>(loanDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/loancustomer")
	public ResponseEntity<String> addLoanAndCustomer(@RequestBody LoanDTO loanDTO) throws InfyBankException{
		Integer loanId = customerLoanService.addLoanAndCustomer(loanDTO);
		String successMessage = environment.getProperty("UserInterface.NEW_LOAN_CUSTOMER_SUCCESS")+loanId;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/loan")
	public ResponseEntity<String> closeLoan(@RequestBody LoanDTO loanDTO) throws InfyBankException{	
		customerLoanService.closeLoan(loanDTO.getLoanId());
		String successMessage = environment.getProperty("UserInterface.LOAN_CLOSE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/loan/{loanId}")
	public ResponseEntity<String> deleteLoan(@PathVariable Integer loanId)throws InfyBankException {
		customerLoanService.deleteLoan(loanId);
		String successMessage = environment.getProperty("UserInterface.LOAN_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

}
