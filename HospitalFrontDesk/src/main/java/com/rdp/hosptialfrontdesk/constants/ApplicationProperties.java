package com.rdp.hosptialfrontdesk.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "com.rdp.hosptialfrontdesk")
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

	@Value("${hms.environment:http://localhost:8080}")
	private String environment;

	@Value("${hms.parenturl:/hospitalfrontdesk}")
	private String parentUrl;
	
	@Value("${hms.retrievespecialist:/retrievespecialist/{id}/{type}}")
	private String retrieveSpecialistUrl;
	
	@Autowired
	Environment environment2;

	/*public ApplicationProperties(@Value("${hms.environment:#{http://localhost:8080}}") String environment, 
			@Value("${hms.parenturl:#{/hospitalfrontdesk}}") String parentUrl,
			@Value("${hms.retrievespecialist:#{/retrievespecialist/{id}/{type}}}") String retrieveSpecialistUrl) {
		super();
		this.environment = environment;
		this.parentUrl = parentUrl;
		this.retrieveSpecialistUrl = retrieveSpecialistUrl;
	}*/
	

	public String generateUrl() {
		//System.out.println("Parent url " + parentUrl);
		
		System.out.println("Property " + environment2.getProperty("hms.environment"));
		return environment + parentUrl + retrieveSpecialistUrl;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyScan() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
