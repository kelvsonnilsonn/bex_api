package com.ecommerce.bex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BexApplication {

	public static void main(String[] args) {
		SpringApplication.run(BexApplication.class, args);
	}

}
