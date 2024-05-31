package com.vicheak.bank.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicheak.bank.account.dto.AccountDTO;
import com.vicheak.bank.account.entity.Account;
import com.vicheak.bank.account.mapper.AccountMapper;
import com.vicheak.bank.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService; 
	private final AccountMapper accountMapper; 

	@PostMapping
	public ResponseEntity<?> saveAccount(@RequestBody AccountDTO dto){
		Account account = accountMapper.toAccount(dto); 
	    account = accountService.save(account);
		return ResponseEntity.ok(account); 
	}
	
	@GetMapping
	public ResponseEntity<?> getAccounts(){
		return ResponseEntity.ok(accountService.getAccounts()); 
	}
	
	@GetMapping("/{accountId}")
	public ResponseEntity<?> getAccountById(@PathVariable Long accountId){
		return ResponseEntity.ok(accountService.getById(accountId)); 
	}
	
}
