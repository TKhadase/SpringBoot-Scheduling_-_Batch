package com.tushar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SbScheduling01BasicSchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbScheduling01BasicSchedulingApplication.class, args);
	}

}
