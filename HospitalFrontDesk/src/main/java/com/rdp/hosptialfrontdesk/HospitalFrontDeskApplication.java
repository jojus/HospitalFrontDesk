package com.rdp.hosptialfrontdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rdp.hosptialfrontdesk.constants.ApplicationProperties;

/*@SpringBootApplication(scanBasePackages = { "com.rdp.hosptialfrontdesk.controller",
		"com.rdp.hosptialfrontdesk.exception", "com.rdp.hosptialfrontdesk.constants", "com.rdp.hosptialfrontdesk.model",
		"com.rdp.hosptialfrontdesk.service" })*/
@SpringBootApplication
public class HospitalFrontDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalFrontDeskApplication.class, args);
	}

	@Bean
	public ApplicationProperties properties() {
		return new ApplicationProperties();
	}

}
