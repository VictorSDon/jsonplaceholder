package com.donato.jsonplaceholder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JsonplaceholderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonplaceholderApplication.class, args);
	}

}
