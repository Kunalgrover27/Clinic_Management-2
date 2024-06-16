package com.cts.userdetails.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.cts.userdetails.model.Login;
import com.cts.userdetails.model.Patient;
import com.cts.userdetails.model.PatientDto;
import com.cts.userdetails.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PatientController.class})
@ExtendWith(SpringExtension.class)
class PatientControllerTest {
    @Autowired
    private PatientController patientController;

    @MockBean
    private PatientService patientService;

    /**
     * Method under test: {@link PatientController#loadPatientDetails(String)}
     */
    @Test
    void testLoadPatientDetails() throws Exception {
        Patient patient = new Patient();
        patient.setConfirmPassword("iloveyou");
        patient.setDob("Dob");
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender("Gender");
        patient.setId(123L);
        patient.setImage("AAAAAAAA".getBytes("UTF-8"));
        patient.setLastName("Doe");
        patient.setPassword("iloveyou");
        patient.setUserName("janedoe");
        when(patientService.getPatientDetails((String) any())).thenReturn(patient);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getPatientDetails/*");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"password\":\"iloveyou\","
                                        + "\"confirmPassword\":\"iloveyou\",\"email\":\"jane.doe@example.org\",\"dob\":\"Dob\",\"gender\":\"Gender\",\"image"
                                        + "\":\"QUFBQUFBQUE=\"}"));
    }

    /**
     * Method under test: {@link PatientController#loadUserByName(String)}
     */
    @Test
    void testLoadUserByName() throws Exception {
        when(patientService.loadUserByName((String) any())).thenReturn(new Login("janedoe", "iloveyou"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/loadUserByName/*");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userName\":\"janedoe\",\"password\":\"iloveyou\"}"));
    }

    /**
     * Method under test: {@link PatientController#registerUser(PatientDto)}
     */
    @Test
    void testRegisterUser() throws Exception {
        when(patientService.registerPatient((PatientDto) any())).thenReturn("Register Patient");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/RegisterPatient");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Register Patient"));
    }

    /**
     * Method under test: {@link PatientController#updateUser(PatientDto)}
     */
    @Test
    void testUpdateUser() throws Exception {
        when(patientService.updatePatientDetails((PatientDto) any())).thenReturn("2020-03-01");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/UpdatePatient");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }
}

