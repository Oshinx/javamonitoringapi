package com.servicemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;




@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Service Monitoring API",
				version= "1.0.0",
				description = " monitoring logging "
				
				)
		
		)
public class ServicerequestmonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicerequestmonitorApplication.class, args);
	}

}
