package com.coding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class SellCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellCarApplication.class, args);
	}

}
