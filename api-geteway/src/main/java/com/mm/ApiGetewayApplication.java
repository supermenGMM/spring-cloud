package com.mm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ApiGetewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGetewayApplication.class, args);
	}
}