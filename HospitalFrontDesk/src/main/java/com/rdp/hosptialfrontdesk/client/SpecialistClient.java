package com.rdp.hosptialfrontdesk.client;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static com.rdp.hosptialfrontdesk.constants.ApplicationConstants.URI_GET_ALL_SPECIALIST;

public class SpecialistClient {

	/**
	 * Requests the response in XML format and prints the objects in console
	 */
	private static void getSpecialistDetailsAsXML() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_XML }));
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<SpecialistResponse[]> entity = new HttpEntity<SpecialistResponse[]>(httpHeaders);

		// send request with GET method, and headers
		ResponseEntity<SpecialistResponse[]> response = restTemplate.exchange(URI_GET_ALL_SPECIALIST, HttpMethod.GET, entity,
				SpecialistResponse[].class,"946","Dentist");

		SpecialistResponse[] list = response.getBody();
		if (list != null) {
			System.out.println(" ----------- Get Specialist details as XML ------------ ");
			for (SpecialistResponse doctors : list) {
				System.out.println(" Doctors details " + doctors.getHospitalId() + " - " + doctors.getName());

			}
		}

	}

	/**
	 * Requests the response in JSON format and prints the objects in console
	 */
	private static void getSpecialistDetailsAsJSON() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<SpecialistResponse[]> entity = new HttpEntity<SpecialistResponse[]>(httpHeaders);

		// send request with GET method, and headers
		ResponseEntity<SpecialistResponse[]> response = restTemplate.exchange(URI_GET_ALL_SPECIALIST, HttpMethod.GET, entity,
				SpecialistResponse[].class);

		SpecialistResponse[] list = response.getBody();
		if (list != null) {
			System.out.println(" ----------- Get Specialist details as JSON ------------ ");
			for (SpecialistResponse doctors : list) {
				System.out.println(" Doctors details " + doctors.getHospitalId() + " - " + doctors.getName());

			}
		}
	}

	public static void main(String[] args) {
		getSpecialistDetailsAsXML();
		getSpecialistDetailsAsJSON();
	}

}
