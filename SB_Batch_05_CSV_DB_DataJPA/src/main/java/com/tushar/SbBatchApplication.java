package com.tushar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SbBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbBatchApplication.class, args);
	}

}
