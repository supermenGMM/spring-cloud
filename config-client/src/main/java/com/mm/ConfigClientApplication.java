package com.mm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConfigClientApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}//底层使用okhttp进行http请求
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}
