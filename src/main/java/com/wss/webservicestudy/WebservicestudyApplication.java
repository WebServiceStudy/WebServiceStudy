package com.wss.webservicestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class WebservicestudyApplication {

	@RequestMapping("/")
	public String home() {
		return "hello World";
	}

	public static void main(String[] args) {
		SpringApplication.run(WebservicestudyApplication.class, args);
	}

}
