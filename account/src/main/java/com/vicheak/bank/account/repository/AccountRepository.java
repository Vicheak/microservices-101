package com.vicheak.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicheak.bank.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	
	
}
