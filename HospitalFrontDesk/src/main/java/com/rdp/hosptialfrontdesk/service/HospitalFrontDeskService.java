package com.rdp.hosptialfrontdesk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rdp.hosptialfrontdesk.model.Appointment;
import com.rdp.hosptialfrontdesk.model.HospitalDetails;
import com.rdp.hosptialfrontdesk.model.Specialist;

@Service
public class HospitalFrontDeskService {

	public final static List<Specialist> doctorsAndDetails = populateDoctorsAndDetails();

	public final static List<HospitalDetails> hospitalDetails = populateHospitalDetails();

	// Map<String,Specialist> doctorsDetailsByHospital = new
	// HashMap<String,Specialist>();

	/*
	 * public void loadDoctorsList () {
	 * doctorsDetailsByHospital.put("Apollo Hospital",new
	 * Specialist(1,"","Audiologist",""));
	 * doctorsDetailsByHospital.put(";Apollo Hospital",new
	 * Specialist(2,"","Allergist",""));
	 * doctorsDetailsByHospital.put("Apollo Hospital",new
	 * Specialist(3,"","Anesthesiologist",""));
	 * doctorsDetailsByHospital.put("Apollo Hospital",new
	 * Specialist(4,"","Cardiologist",""));
	 * doctorsDetailsByHospital.put("Apollo Hospital",new
	 * Specialist(5,"","Dentist",""));
	 * doctorsDetailsByHospital.put("MIOT Hospital",new
	 * Specialist(1,"","Audiologist",""));
	 * doctorsDetailsByHospital.put("MIOT Hospital",new
	 * Specialist(2,"","Allergist",""));
	 * doctorsDetailsByHospital.put("MIOT Hospital",new
	 * Specialist(3,"","Anesthesiologist",""));
	 * doctorsDetailsByHospital.put("MIOT Hospital",new
	 * Specialist(4,"","Cardiologist",""));
	 * doctorsDetailsByHospital.put("MIOT Hospital",new
	 * Specialist(5,"","Dentist",""));
	 * doctorsDetailsByHospital.put("Kauvery Hospital",new
	 * Specialist(1,"","Audiologist",""));
	 * doctorsDetailsByHospital.put("Kauvery Hospital",new
	 * Specialist(2,"","Allergist",""));
	 * doctorsDetailsByHospital.put("Kauvery Hospital",new
	 * Specialist(3,"","Anesthesiologist",""));
	 * doctorsDetailsByHospital.put("Kauvery Hospital",new
	 * Specialist(4,"","Cardiologist",""));
	 * doctorsDetailsByHospital.put("Kauvery Hospital",new
	 * Specialist(5,"","Dentist","")); }
	 */

	private static List<Specialist> populateDoctorsAndDetails() {
		List<Specialist> doctorsAndDetails = new ArrayList<>();
		doctorsAndDetails.add(new Specialist("Sandhya", "Dentist", "Monday,Wednesday", "5 to 6", "Y", "946"));
		doctorsAndDetails.add(new Specialist("Saranya", "Dentist", "Monday,Thursday", "6 to 8", "N", "946"));
		doctorsAndDetails.add(new Specialist("Kumar", "Cardiologist", "Monday,Tuesday", "10 to 12", "Y", "956"));
		doctorsAndDetails.add(new Specialist("Rajesh", "Cardiologist", "Monday,Friday", "11 to 12", "Y", "956"));
		doctorsAndDetails.add(new Specialist("Raj", "Dentist", "Tuesday,Wednesday", "2 to 4", "N", "970"));
		return doctorsAndDetails;
	}

	/**
	 * Populate Hospital details
	 * 
	 * @return
	 */

	private static List<HospitalDetails> populateHospitalDetails() {
		List<HospitalDetails> hospitalDetails = new ArrayList<>();
		hospitalDetails.add(new HospitalDetails("946", 8));
		hospitalDetails.add(new HospitalDetails("956", 12));
		hospitalDetails.add(new HospitalDetails("970", 5));
		return hospitalDetails;
	}

	/**
	 * Get specialist details
	 * 
	 * @param hospitalId
	 * @param type
	 * @return
	 */
	public Specialist getSpecialistDetails(String hospitalId, String type) {
		Specialist specialistDetails = null;
		for (Specialist specialist : doctorsAndDetails) {
			if (specialist.getHospitalId().equalsIgnoreCase(hospitalId)
					&& specialist.getType().equalsIgnoreCase(type)) {
				specialistDetails = specialist;
			}
		}
		return specialistDetails;
	}

	public int getAvailableBeds(String hospitalId) {
		int hospitalDetail = 0;
		for (HospitalDetails hospital : hospitalDetails) {
			if (hospital.getHospitalId().equalsIgnoreCase(hospitalId)) {
				hospitalDetail = hospital.getAvailableBeds();
			}
		}
		return hospitalDetail;
	}

	public Appointment createAppointment(String hospitalId, String doctorName, String patientName,
			String availableDay) {
		// List<Appointment> appDetails = new ArrayList<>();
		Appointment appointmentDetails = null;
		for (Specialist doctorsDetails : doctorsAndDetails) {
			if (doctorsDetails.getHospitalId().equalsIgnoreCase(hospitalId)
					&& doctorsDetails.getName().equalsIgnoreCase(doctorName)) {
				// appDetails.add(new Appointment(doctorsDetails.getName(), patientName,
				// doctorsDetails.getAvailableDay(),doctorsDetails.getAvailableTime()));
				appointmentDetails = new Appointment(doctorsDetails.getName(), patientName,
						doctorsDetails.getAvailableDay(), doctorsDetails.getAvailableTime());
			}
		}
		return appointmentDetails;
	}

	/**
	 * check specialist already available or not
	 * 
	 * @param specialistName
	 * @return
	 */
	public boolean checkSpecialistName(String specialistName) {
		boolean flag = false;
		for (Specialist specialist : doctorsAndDetails) {
			if (specialist.getName().equalsIgnoreCase(specialistName)) {
				flag = true;
			}
		}
		return flag;
	}
	
	public List<Specialist> listOfSpecialist() {		
		return doctorsAndDetails;
	}
}
