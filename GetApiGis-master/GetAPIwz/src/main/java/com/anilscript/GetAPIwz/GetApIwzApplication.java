package com.anilscript.GetAPIwz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.anilscript.GetAPIwz.repository")
//@EntityScan(basePackages = "com.anilscript.GetAPIwz.model")

public class GetApIwzApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetApIwzApplication.class, args);
	}

}
