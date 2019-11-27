package com.rdp.hosptialfrontdesk.constants;

public class ApplicationConstants {

	// constants for exceptions
	public static final String INVALID_HOSPITAL_NAME = "Invalid hospital name";
	public static final String NO_SPECIALIST = "No specialist details found for the specified hospital name";
	
	
	// URI 
	public static final String URI_GET_ALL_SPECIALIST = "http://localhost:8080/hospitalfrontdesk/retrievespecialist/{id}/{type}";
	
	public static final String LIST_OF_SPECIALIST = "http://localhost:8080/hospitalfrontdesk/listOfSpecialists";
}
