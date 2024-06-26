package com.vicheak.bank.account.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId; 
	private String name; 
	private String email; 
	private String mobileNumber; 
	private LocalDate createDate;
	private Boolean communicationAlreadySent; 
	
}
