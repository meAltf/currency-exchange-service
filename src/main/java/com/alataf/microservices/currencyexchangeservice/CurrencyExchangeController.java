package com.alataf.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}") //converting USD&INR to from&to for taking it as a path variable
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to){
//		CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to,
//						BigDecimal.valueOf(50));
		
		CurrencyExchange currencyExchange 
					= repository.findByFromAndTo(from, to);
		
		if(currencyExchange == null) {
			throw new RuntimeException
			("Unable to find data for " + from + " to " + to);
		}
		//Now, set the value of the environment
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
