package com.mm;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableSwagger2Doc
@EnableDiscoveryClient
public class OrderServiceApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}//底层使用okhttp进行http请求
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
