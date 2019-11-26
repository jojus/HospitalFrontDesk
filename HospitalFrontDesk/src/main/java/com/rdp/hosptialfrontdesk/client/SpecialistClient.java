package com.rdp.hosptialfrontdesk.client;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdp.hosptialfrontdesk.constants.ApplicationProperties;
import com.rdp.hosptialfrontdesk.model.Specialist;

import static com.rdp.hosptialfrontdesk.constants.ApplicationConstants.URI_GET_ALL_SPECIALIST;

@Service
public class SpecialistClient {

	private static MediaType MEDIATYPE_JSON = MediaType.APPLICATION_JSON;
	private static MediaType MEDIATYPE_XML = MediaType.APPLICATION_XML;
	private static String APPLICATION_PORT = "8080";
	private static String APPLICATION_ENVIRONMENT = "http://localhost";

	/**
	 * Requests the response in XML format and prints the objects in console
	 */

	public static void main(String[] args) {

		/*
		 * ApplicationProperties properties = new ApplicationProperties(); String URL =
		 * properties.generateUrl();
		 */

		getSpecialistByType(APPLICATION_PORT, APPLICATION_ENVIRONMENT, MEDIATYPE_JSON, URI_GET_ALL_SPECIALIST, "946",
				"Dentist");
		
		System.out.println("-------------------- -----------------------");
		
		getSpecialistByType(APPLICATION_PORT, APPLICATION_ENVIRONMENT, MEDIATYPE_XML, URI_GET_ALL_SPECIALIST, "946",
				"Dentist");

	}

	private static void getSpecialistByType(String applicationPort, String applicationType, MediaType mediaType,
			String url, String hospitalId, String specialistType) {
		// String url1 = "http://localhost:" + applicationPort + "/" + url;
		HttpHeaders httpHeaders = new HttpHeaders();
		//httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.setAccept(Arrays.asList(new MediaType[] { mediaType }));
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Specialist> entity = new HttpEntity<> (httpHeaders);

		// send request with GET method, and headers

		ResponseEntity<Specialist> response = restTemplate.exchange(url, HttpMethod.GET, entity, Specialist.class, 946,
				"Dentist");

		HttpStatus status = response.getStatusCode();

		if (status == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			Specialist result = response.getBody();
			try {
				System.out.println("===================================== \n"
						+ "====== " + mediaType + " ======= \n"
						+ " ===================================== ");
				System.out.println(mapper.writeValueAsString(result));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		/*
		 * ObjectMapper mapper = new ObjectMapper(); Specialist specialistDetails =
		 * restTemplate.getForObject(url, Specialist.class, hospitalId, specialistType);
		 * try { System.out.println(mapper.writeValueAsString(specialistDetails)); }
		 * catch (JsonProcessingException e) { e.printStackTrace(); }
		 */

	}

}
