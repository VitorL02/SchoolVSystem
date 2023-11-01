package com.schoolv.schoolvsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchoolvsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolvsystemApplication.class, args);
	}

}
