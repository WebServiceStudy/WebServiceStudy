package com.wss.webservicestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebservicestudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebservicestudyApplication.class, args);
	}

}
