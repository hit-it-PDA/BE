package com.pda.investment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "com.pda")
public class InvestmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentServiceApplication.class, args);
	}

}
