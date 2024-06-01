package com.vicheak.bank.account.controller;

import java.util.List;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vicheak.bank.account.dto.CardResponseDTO;
import com.vicheak.bank.account.dto.CustomerDTO;
import com.vicheak.bank.account.dto.CustomerDetailDTO;
import com.vicheak.bank.account.dto.CustomerMessageDTO;
import com.vicheak.bank.account.dto.LoanResponseDTO;
import com.vicheak.bank.account.entity.Customer;
import com.vicheak.bank.account.mapper.CustomerMapper;
import com.vicheak.bank.account.service.CustomerService;
import com.vicheak.bank.account.service.client.CardFeignClient;
import com.vicheak.bank.account.service.client.LoanFeignClient;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
	
	private final CustomerService customerService;
	private final CustomerMapper customerMapper; 
	private final CardFeignClient cardFeignClient; 
	private final LoanFeignClient loanFeignClient;

	@PostMapping
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO dto){
		Customer customer = customerMapper.toCustomer(dto); 
	    customer = customerService.save(customer);
		return ResponseEntity.ok(customer); 
	}
	
	@GetMapping
	public ResponseEntity<?> getCustomers(){
		return ResponseEntity.ok(customerService.getCustomers()); 
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long customerId){
		return ResponseEntity.ok(customerService.getById(customerId)); 
	}
	
	//@CircuitBreaker(name = "customerDetailSupport", fallbackMethod = "getCustomerDetailDefault")
	@Retry(name = "retryCustomerDetail", fallbackMethod = "getCustomerDetailDefault")
	@GetMapping("/customerDetail/{customerId}")
	public ResponseEntity<CustomerDetailDTO> getCustomerDetail(
			@RequestHeader("vicheakbank-correlation-id") String correlationId,
			@PathVariable Long customerId){
		//System.out.println("================= Account service ================");
		
		//log.debug("Correlation id found : {}", correlationId);
		log.debug("fetchCustomerDetail method start");
		
		CustomerDetailDTO dto = new CustomerDetailDTO(); 
		Customer customer = customerService.getById(customerId);
		if(customer == null) {
			throw new RuntimeException("No customer found with this id"); 
		}
		
		CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
		
		List<LoanResponseDTO> loanInfo = loanFeignClient.getLoanInfo(correlationId, customerId);
		List<CardResponseDTO> cardInfo = cardFeignClient.getCardInfo(correlationId, customerId);
		
		dto.setCustomer(customerDTO); 
		dto.setLoans(loanInfo); 
		dto.setCards(cardInfo); 
		
		log.debug("fetchCustomerDetail method end");
		return ResponseEntity.ok(dto); 
	}
	
	public ResponseEntity<CustomerDetailDTO> getCustomerDetailDefault(
			@RequestHeader("vicheakbank-correlation-id") String correlationId,
			@PathVariable Long customerId, 
			Throwable e){
		//logged exception
		//e.printStackTrace();
		//log.warn("Exception : {}", e.getMessage());
		
		log.debug("Correlation id found : {}", correlationId);
		
		CustomerDetailDTO dto = new CustomerDetailDTO(); 
		Customer customer = customerService.getById(customerId);
		if(customer == null) {
			throw new RuntimeException("No customer found with this id"); 
		}
		
		CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
		
		List<LoanResponseDTO> loanInfo = null;
		List<CardResponseDTO> cardInfo = null; 
		try {
			loanInfo = loanFeignClient.getLoanInfo(correlationId, customerId);
		}catch(Exception ex) {
			//e.printStackTrace(); 
			//log.warn("Exception : {}", e.getMessage());
		}
		
		try {
			cardInfo = cardFeignClient.getCardInfo(correlationId, customerId);
		}catch(Exception ex) {
			//e.printStackTrace();
			//log.warn("Exception : {}", e.getMessage()); 
		}
		
		dto.setCustomer(customerDTO); 
		dto.setLoans(loanInfo); 
		dto.setCards(cardInfo); 
		
		return ResponseEntity.ok(dto); 
	}
	
	@GetMapping("/sayHello")
	@RateLimiter(name = "sayHelloLimiter", fallbackMethod = "sayHi")
	public String sayHello() {
		return "Hello, welcome to Vicheak Bank"; 
	}
	
	public String sayHi(Throwable t) {
		return "Hi, welcome to Vicheak Bank"; 
	}
	
}
