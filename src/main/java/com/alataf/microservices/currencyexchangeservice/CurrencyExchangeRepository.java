package com.alataf.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

//it is able to talk with database
public interface CurrencyExchangeRepository
extends JpaRepository<CurrencyExchange, Long>{
CurrencyExchange findByFromAndTo(String from, String to);

}
 