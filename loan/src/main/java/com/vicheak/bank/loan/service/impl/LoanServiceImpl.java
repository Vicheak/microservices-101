package com.vicheak.bank.loan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicheak.bank.loan.entity.Loan;
import com.vicheak.bank.loan.repository.LoanRepository;
import com.vicheak.bank.loan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepository; 

	@Override
	public Loan save(Loan loan) {
		return loanRepository.save(loan); 
	}

	@Override
	public List<Loan> getList() {
		return loanRepository.findAll(); 
	}

	@Override
	public List<Loan> getByCustomerId(Long customerId) {
		return loanRepository.findByCustomerId(customerId); 
	}
	
}
