package com.cts.userdetails.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.cts.userdetails.model.Appointment;
import com.cts.userdetails.model.SuccessResponse;
import com.cts.userdetails.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {AppointmentController.class})
@ExtendWith(SpringExtension.class)
class AppointmentControllerTest {
    @Autowired
    private AppointmentController appointmentController;

    @MockBean
    private AppointmentService appointmentService;

    /**
     * Method under test: {@link AppointmentController#addAppointment(Appointment)}
     */
    @Test
    void testAddAppointment() throws Exception {
        when(appointmentService.createAppointment((Appointment) any())).thenReturn(new SuccessResponse());

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(123L);
        appointment.setClinicId(123L);
        appointment.setClinicName("Clinic Name");
        appointment.setDate("2020-03-01");
        appointment.setDiseaseId(123L);
        appointment.setDiseaseName("Disease Name");
        appointment.setDoctorId(123L);
        appointment.setDoctorName("Doctor Name");
        appointment.setPatientId(123L);
        appointment.setTime("Time");
        String content = (new ObjectMapper()).writeValueAsString(appointment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addAppointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":null,\"timestamp\":null}"));
    }

    /**
     * Method under test: {@link AppointmentController#appointmentsDetail()}
     */
    @Test
    void testAppointmentsDetail() throws Exception {
        when(appointmentService.appointmentsDetail()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllAppointments");
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AppointmentController#appointmentDetailByPatientId(long)}
     */
    @Test
    void testAppointmentDetailByPatientId() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllAppointmentsByPatientId/*");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

