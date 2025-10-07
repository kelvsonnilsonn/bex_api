package com.ecommerce.bex;

import com.ecommerce.bex.model.valueobjects.user.*;
import com.ecommerce.bex.service.ProductService;
import com.ecommerce.bex.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class BexApplication {

	public static void main(String[] args) {
		SpringApplication.run(BexApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(ProductService userService){
        return args -> {
            userService.create();
        };
    }

}
