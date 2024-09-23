package com.group4.fpolycom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"entity"})
public class FpolycomApplication {

	public static void main(String[] args) {
		SpringApplication.run(FpolycomApplication.class, args);
	}

}
