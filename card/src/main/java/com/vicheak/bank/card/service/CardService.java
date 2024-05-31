package com.vicheak.bank.card.service;

import java.util.List;

import com.vicheak.bank.card.entity.Card;

public interface CardService {
	
	Card save(Card card);
	
	List<Card> getList();
	
	List<Card> getByCustomerId(Long customerId); 
	
}
