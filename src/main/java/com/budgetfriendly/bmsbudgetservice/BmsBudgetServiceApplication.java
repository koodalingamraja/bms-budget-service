package com.budgetfriendly.bmsbudgetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BmsBudgetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsBudgetServiceApplication.class, args);
	}

}
