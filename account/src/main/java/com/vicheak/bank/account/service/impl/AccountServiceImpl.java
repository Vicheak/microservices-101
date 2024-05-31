package com.vicheak.bank.account.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vicheak.bank.account.entity.Account;
import com.vicheak.bank.account.repository.AccountRepository;
import com.vicheak.bank.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository; 

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account getById(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(
						() -> new RuntimeException("Account not found!")
				);
	}

}
