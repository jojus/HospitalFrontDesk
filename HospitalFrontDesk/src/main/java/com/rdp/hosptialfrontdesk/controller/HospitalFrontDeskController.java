package com.rdp.hosptialfrontdesk.controller;

import static com.rdp.hosptialfrontdesk.constants.ApplicationConstants.INVALID_HOSPITAL_NAME;
import static com.rdp.hosptialfrontdesk.constants.ApplicationConstants.NO_SPECIALIST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rdp.hosptialfrontdesk.model.Appointment;
import com.rdp.hosptialfrontdesk.model.InvalidHospitalNameException;
import com.rdp.hosptialfrontdesk.model.NoSpecialistFoundException;
import com.rdp.hosptialfrontdesk.model.Specialist;
import com.rdp.hosptialfrontdesk.service.HospitalFrontDeskService;;

@RestController
@RequestMapping(value = { "${hms.parenturl}" })
public class HospitalFrontDeskController {

	@Autowired
	HospitalFrontDeskService hospitalService;

	@GetMapping(value = "${hms.retrievespecialist}",
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
		    consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Specialist> specialistList(@PathVariable("id") String hospitalId,
			@PathVariable("type") String specialistType, Specialist specialist) throws InvalidHospitalNameException {
		// System.out.println("retrieve specialist " + hospitalId + specialistType);
		if (hospitalId != null && specialistType != null) {
			specialist = hospitalService.getSpecialistDetails(hospitalId, specialistType);
			return new ResponseEntity<Specialist>(specialist, HttpStatus.OK);
		} else {
			System.out.println("Hospital name and specialist name should not be empty !!!");
			throw new InvalidHospitalNameException(INVALID_HOSPITAL_NAME);
		}
	}

	@GetMapping(value = "${hms.specialistappointment}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Appointment createAppointment(@PathVariable("id") String hospitalId,
			@PathVariable("name") String specialistName, @PathVariable("patientname") String patientName,
			@PathVariable("appointmentday") String appointmentDay)
			throws InvalidHospitalNameException, NoSpecialistFoundException {
		Appointment appointmentDetails;
		if (hospitalId != null) {
			boolean isSpecialistAvailable = hospitalService.checkSpecialistName(specialistName);
			if (isSpecialistAvailable) {
				appointmentDetails = hospitalService.createAppointment(hospitalId, specialistName, patientName,
						appointmentDay);
			} else {
				throw new NoSpecialistFoundException(NO_SPECIALIST);
			}
		} else {
			throw new InvalidHospitalNameException(INVALID_HOSPITAL_NAME);
		}
		return appointmentDetails;
	}

	@GetMapping(value = "${hms.availablebeds}")
	public String availableBeds(@PathVariable("id") String hospitalId) throws InvalidHospitalNameException {
		int beds = 0;
		if (hospitalId != null) {
			beds = hospitalService.getAvailableBeds(hospitalId);
		} else {
			throw new InvalidHospitalNameException(INVALID_HOSPITAL_NAME);
		}

		return "Number of Beds Available is = " + beds;
	}
	
	@GetMapping(value = "${hms.specialistdetails}",
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
		    consumes = MediaType.ALL_VALUE)
	public List<Specialist> retrieveSpecialistDetails() {		
		return hospitalService.listOfSpecialist();
		
	}
}
