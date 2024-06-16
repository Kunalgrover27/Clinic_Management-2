package com.cts.userdetails.controller;

import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Clinic;
import com.cts.userdetails.model.Disease;
import com.cts.userdetails.model.Doctors;
import com.cts.userdetails.model.SuccessResponse;
import com.cts.userdetails.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class DiseaseController {
	 @Autowired
	    private DiseaseService appointmentService;
	 @PostMapping(value = "/createDisease")
	 public SuccessResponse addDisease(@RequestBody Disease disease) throws EmptyDataAccessException {
	            return appointmentService.createDisease(disease);
	    }
		@PostMapping(value = "/addClinic")
	 public SuccessResponse addClinic(@RequestBody Clinic clinic) throws EmptyDataAccessException {return appointmentService.createClinic(clinic);}
	@GetMapping(value = "/getDisease/{id}")
	public Disease getDiseaseClinic(@PathVariable long id) throws EmptyDataAccessException {
		 return appointmentService.getAllClinics(id);
	}
	@GetMapping(value = "/getAllClinics")
	public List<Clinic> getAllClinics() throws EmptyDataAccessException {
		return appointmentService.getAllClinic();
	}
	@GetMapping(value = "/getAllDisease")
	public List<Disease> getDiseases() throws EmptyDataAccessException {
		return appointmentService.getAllDiseases();
	}
	@GetMapping(value = "/clinicDetailById/{id}")
	public Clinic clinicDetail(@PathVariable long id) throws EmptyDataAccessException {
		return appointmentService.clinicDetailById(id);
	}
	@PostMapping(value = "/addDoctor/{id}")
	public SuccessResponse addDoctorToClinic(@RequestBody Doctors doctor, @PathVariable long id) throws EmptyDataAccessException {return appointmentService.addDoctor(doctor,id);}
	@PostMapping(value = "/addClinicToDisease/{id}")
	public SuccessResponse addClinicToDisease(@RequestBody Clinic clinic, @PathVariable long id) throws EmptyDataAccessException {
		return appointmentService.addClinicToDisease(clinic,id);
	}
	@DeleteMapping(value = "/deleteClinicForDisease/{diseaseId}/{clinicId}")
	public SuccessResponse deleteClinicForDisease(@PathVariable long diseaseId,@PathVariable long clinicId){

		return appointmentService.deleteClinic(diseaseId,clinicId);
	}

	@DeleteMapping(value = "/deleteClinic/{clinicId}")
	public SuccessResponse deleteClinic(@PathVariable long clinicId) throws Exception {

		return appointmentService.deleteClinicById(clinicId);
	}
	@DeleteMapping(value = "/deleteDoctor/{doctorId}")
	public SuccessResponse deleteClinicDoctor(@PathVariable long doctorId) throws Exception {

		return appointmentService.deleteClinicDoctorById(doctorId);
	}

	@PostMapping(value = "/UpdateClinic")
	public SuccessResponse updateUser(@RequestBody Clinic clinic) throws EmptyDataAccessException {
		return appointmentService.updateClinicDetails(clinic);
	}
	@DeleteMapping(value = "/deleteDisease/{diseaseId}")
	public SuccessResponse deleteDisease(@PathVariable long diseaseId) throws Exception {

		return appointmentService.deleteDisease(diseaseId);
	}
}
