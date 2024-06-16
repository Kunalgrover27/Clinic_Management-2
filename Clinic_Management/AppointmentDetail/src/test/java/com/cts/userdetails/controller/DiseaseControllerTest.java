package com.cts.userdetails.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.cts.userdetails.model.Clinic;
import com.cts.userdetails.model.Disease;
import com.cts.userdetails.model.Doctors;
import com.cts.userdetails.model.SuccessResponse;
import com.cts.userdetails.service.DiseaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DiseaseController.class})
@ExtendWith(SpringExtension.class)
class DiseaseControllerTest {
    @Autowired
    private DiseaseController diseaseController;

    @MockBean
    private DiseaseService diseaseService;

    /**
     * Method under test: {@link DiseaseController#addClinic(Clinic)}
     */
    @Test
    void testAddClinic() throws Exception {
        when(diseaseService.createClinic((Clinic) any())).thenReturn(new SuccessResponse());

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        String content = (new ObjectMapper()).writeValueAsString(clinic);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addClinic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":null,\"timestamp\":null}"));
    }

    /**
     * Method under test: {@link DiseaseController#addClinicToDisease(Clinic, long)}
     */
    @Test
    void testAddClinicToDisease() throws Exception {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        String content = (new ObjectMapper()).writeValueAsString(clinic);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addClinicToDisease/*")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link DiseaseController#addDisease(Disease)}
     */
    @Test
    void testAddDisease() throws Exception {
        when(diseaseService.createDisease((Disease) any())).thenReturn(new SuccessResponse());

        Disease disease = new Disease();
        disease.setClinics(new HashSet<>());
        disease.setId(123L);
        disease.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(disease);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createDisease")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":null,\"timestamp\":null}"));
    }

    /**
     * Method under test: {@link DiseaseController#addDoctorToClinic(Doctors, long)}
     */
    @Test
    void testAddDoctorToClinic() throws Exception {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");

        Doctors doctors = new Doctors();
        doctors.setConsulationFee(1);
        doctors.setDisease("Disease");
        doctors.setDoctorId(123L);
        doctors.setDoctorName("Doctor Name");
        doctors.setEngagedClinic(clinic);
        doctors.setExperience(1);
        String content = (new ObjectMapper()).writeValueAsString(doctors);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addDoctor/*")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link DiseaseController#clinicDetail(long)}
     */
    @Test
    void testClinicDetail() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clinicDetailById/*");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link DiseaseController#deleteClinic(long)}
     */
    @Test
    void testDeleteClinic() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteClinic/*");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link DiseaseController#deleteClinicForDisease(long, long)}
     */
    @Test
    void testDeleteClinicForDisease() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteClinicForDisease/*/*");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link DiseaseController#getAllClinics()}
     */
    @Test
    void testGetAllClinics() throws Exception {
        when(diseaseService.getAllClinic()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllClinics");
        MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DiseaseController#getDiseaseClinic(long)}
     */
    @Test
    void testGetDiseaseClinic() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getDisease/*");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link DiseaseController#getDiseases()}
     */
    @Test
    void testGetDiseases() throws Exception {
        when(diseaseService.getAllDiseases()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllDisease");
        MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DiseaseController#updateUser(Clinic)}
     * @throws Throwable 
     */
    @Test
    void testUpdateUser() throws Throwable {
        when(diseaseService.updateClinicDetails((Clinic) any())).thenReturn(new SuccessResponse());

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setClinicId(123L);
        clinic.setClinicName("Clinic Name");
        clinic.setContactUs(1L);
        clinic.setDays("Days");
        clinic.setDoctor(new ArrayList<>());
        clinic.setEndTime("End Time");
        clinic.setStartTime("Start Time");
        String content = (new ObjectMapper()).writeValueAsString(clinic);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/UpdateClinic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }
    @Test
    void testDeleteDoctorForClinic() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteDoctor/*");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(diseaseController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

