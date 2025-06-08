package com.perfulandia.boletaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BoletaserviceApplication {

	public static RestTemplate main(String[] args) {
		SpringApplication.run(BoletaserviceApplication.class, args);

        return null;
    }
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}


}
