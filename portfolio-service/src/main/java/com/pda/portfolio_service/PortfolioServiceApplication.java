package com.pda.portfolio_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableFeignClients(basePackages = {"com.pda.portfolio_service"})
@EnableJpaRepositories(basePackages = {"com.pda.portfolio_service"})
@SpringBootApplication(scanBasePackages = "com.pda")
public class PortfolioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioServiceApplication.class, args);
	}
}
