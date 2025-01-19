package com.abcfitness.ignite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ABC Ignite", description = "ABC Ignite is a Software-as-a-Service full solution for workout clubs/gyms (and groups of clubs) which allows business owners to manage their courses, classes, members, memberships, etc."))
public class IgniteApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgniteApplication.class, args);
	}

}
