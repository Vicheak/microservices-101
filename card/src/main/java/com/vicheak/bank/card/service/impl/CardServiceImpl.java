package com.vicheak.bank.card.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicheak.bank.card.entity.Card;
import com.vicheak.bank.card.repository.CardRepository;
import com.vicheak.bank.card.service.CardService;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardRepository cardRepository; 

	@Override
	public Card save(Card card) {
		return cardRepository.save(card); 
	}

	@Override
	public List<Card> getList() {
		return cardRepository.findAll(); 
	}

	@Override
	public List<Card> getByCustomerId(Long customerId) {
		return cardRepository.findByCustomerId(customerId); 
	}
	
}
