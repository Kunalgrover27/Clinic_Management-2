package com.cts.userdetails.service;


import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Login;
import com.cts.userdetails.model.Patient;
import com.cts.userdetails.model.PatientDto;
import com.cts.userdetails.repository.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PatientService {
    @Autowired
    private PatientDao patientDao;


    public String registerPatient(PatientDto patient) throws EmptyDataAccessException {
        if (patient == null) {
            throw new EmptyDataAccessException("Object is null");
        }
        try {
            Patient p = new Patient();
            p.setId(patient.getId());
            p.setFirstName(patient.getFirstName());
            p.setLastName(patient.getLastName());
            p.setUserName(patient.getUserName());
            p.setPassword(patient.getPassword());
            p.setConfirmPassword(patient.getConfirmPassword());
            p.setEmail(patient.getEmail());
            p.setDob(patient.getDob());
            p.setGender(patient.getGender());
            if(patient.getImage()!=null) {
                p.setImage(patient.getImage().getBytes());
            }
            patientDao.save(p);
        } catch (IOException e) {
            return "Patient is not registered successfully";
        }
        return "Patient is registered successfully";
    }

    public Login loadUserByName(String user) {
        Patient patient = patientDao.findByEmail(user);
        return new Login(patient.getEmail(), patient.getPassword());
    }


    public Patient getPatientDetails(String email) throws EmptyDataAccessException {
        Patient patient = patientDao.findByEmail(email);
        if(patient!=null) {
            return patient;
        }
        else{
            throw new EmptyDataAccessException("Patient is not present");
        }

    }

    public String updatePatientDetails(PatientDto patient) throws EmptyDataAccessException {
        if (patient == null) {
            throw new EmptyDataAccessException("Object is null");
        }
        try {
            Patient existingPatient = patientDao.findById(patient.getId()).orElseThrow(new EmptyDataAccessException("User data is not Found"));

            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setUserName(patient.getUserName());
            existingPatient.setPassword(patient.getPassword());
            existingPatient.setConfirmPassword(patient.getConfirmPassword());
            existingPatient.setEmail(patient.getEmail());
            if(patient.getImage()!=null) {
                existingPatient.setImage(patient.getImage().getBytes());
            }
            patientDao.save(existingPatient);
        } catch (IOException e) {
            return "Patient is not updated successfully";
        }
        return "Patient is updated successfully";
    }

//    public ResponseEntity<byte[]> loadUserImage(String userEmail) {
//        Patient patient = patientDao.findByEmail(userEmail);
//        if (patient != null) {
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(patient.getImage());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    }

