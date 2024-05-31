package com.vicheak.bank.card.mapper;

import org.mapstruct.Mapper;

import com.vicheak.bank.card.dto.CardDTO;
import com.vicheak.bank.card.entity.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {

	Card toCard(CardDTO dto); 
	
}
