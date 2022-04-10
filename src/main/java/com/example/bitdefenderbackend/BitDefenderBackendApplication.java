package com.example.bitdefenderbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class BitDefenderBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitDefenderBackendApplication.class, args);
	}

}
