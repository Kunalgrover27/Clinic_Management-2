package com.cts.userdetails.controller;

import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Login;
import com.cts.userdetails.model.Patient;
import com.cts.userdetails.model.PatientDto;
import com.cts.userdetails.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @PostMapping(value = "/RegisterPatient")
    public String registerUser(@ModelAttribute PatientDto patient) throws EmptyDataAccessException {

        return patientService.registerPatient(patient);
    }
    @GetMapping(value = "/loadUserByName/{userEmail}")
    public Login loadUserByName(@PathVariable String userEmail){
        return patientService.loadUserByName(userEmail);
    }
    @GetMapping(value = "/getPatientDetails/{email}")
    public Patient loadPatientDetails(@PathVariable String email) throws EmptyDataAccessException {
        return patientService.getPatientDetails(email);
    }

    @PostMapping(value = "/UpdatePatient")
    public String updateUser(@ModelAttribute PatientDto patient) throws EmptyDataAccessException {
        return patientService.updatePatientDetails(patient);
    }
//    @GetMapping(value = "/getImage/{userEmail}")
//    public ResponseEntity<byte[]> loadUserImage(@PathVariable String userEmail){
//        return patientService.loadUserImage(userEmail);
//    }


}
