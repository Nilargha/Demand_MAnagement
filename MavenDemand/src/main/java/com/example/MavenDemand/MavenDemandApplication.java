package com.example.MavenDemand;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MavenDemandApplication {

	public static void main(String[] args) {
		SpringApplication.run(MavenDemandApplication.class, args);
	}
	
}
