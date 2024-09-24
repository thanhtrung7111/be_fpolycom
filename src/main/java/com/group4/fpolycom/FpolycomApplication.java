package com.group4.fpolycom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"entity"})
@EnableJpaRepositories(basePackages = {"dao"})
@ComponentScan(basePackages = {"security","service"})
public class FpolycomApplication {

	public static void main(String[] args) {
		SpringApplication.run(FpolycomApplication.class, args);
	}

}
