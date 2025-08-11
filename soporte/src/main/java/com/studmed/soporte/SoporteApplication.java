package com.studmed.soporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.studmed.soporte.ticket.client")
@EnableDiscoveryClient
public class SoporteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoporteApplication.class, args);
	}

}
