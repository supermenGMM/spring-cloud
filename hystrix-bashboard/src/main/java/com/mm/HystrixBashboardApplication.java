package com.mm;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;
@EnableHystrixDashboard
@SpringBootApplication
public class HystrixBashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixBashboardApplication.class, args);
	}


}
