package com.vicheak.bank.account.service;

import java.util.List;

import com.vicheak.bank.account.entity.Account;

public interface AccountService {

	Account save(Account account);
	
	List<Account> getAccounts();

	Account getById(Long id); 
	
}
