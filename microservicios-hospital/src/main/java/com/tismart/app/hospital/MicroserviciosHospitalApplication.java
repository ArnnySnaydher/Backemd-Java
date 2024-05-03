package com.tismart.app.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviciosHospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosHospitalApplication.class, args);
	}

}
