package com.vicheak.bank.account.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vicheak.bank.account.dto.LoanResponseDTO;

@FeignClient(name = "loan")
public interface LoanFeignClient {

	@GetMapping("/api/loans/{customerId}")
	List<LoanResponseDTO> getLoanInfo(
			@RequestHeader("vicheakbank-correlation-id") String correlationId,
			@PathVariable Long customerId);
	
}
