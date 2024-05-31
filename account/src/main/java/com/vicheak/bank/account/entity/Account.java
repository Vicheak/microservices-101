package com.vicheak.bank.account.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountNumber;
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	private String accountType;
	private String branchAddress;
	private LocalDate createDate;
	
}
