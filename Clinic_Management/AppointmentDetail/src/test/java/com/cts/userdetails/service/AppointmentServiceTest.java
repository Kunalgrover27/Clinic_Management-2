package com.cts.userdetails.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Appointment;
import com.cts.userdetails.model.SuccessResponse;
import com.cts.userdetails.repository.AppointmentDao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {AppointmentService.class})
@ExtendWith(SpringExtension.class)
class AppointmentServiceTest {
    @MockBean
    private AppointmentDao appointmentDao;

    @Autowired
    private AppointmentService appointmentService;

    @MockBean
    private SuccessResponse successResponse;

    /**
     * Method under test: {@link AppointmentService#createAppointment(Appointment)}
     */
    @Test
    void testCreateAppointment() throws EmptyDataAccessException {
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
        when(appointmentDao.save((Appointment) any())).thenReturn(appointment);
        doNothing().when(successResponse).setMessage((String) any());

        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentId(123L);
        appointment1.setClinicId(123L);
        appointment1.setClinicName("Clinic Name");
        appointment1.setDate("2020-03-01");
        appointment1.setDiseaseId(123L);
        appointment1.setDiseaseName("Disease Name");
        appointment1.setDoctorId(123L);
        appointment1.setDoctorName("Doctor Name");
        appointment1.setPatientId(123L);
        appointment1.setTime("Time");
        appointmentService.createAppointment(appointment1);
        verify(appointmentDao).save((Appointment) any());
        verify(successResponse).setMessage((String) any());
    }

    /**
     * Method under test: {@link AppointmentService#appointmentDetailByPatientId(long)}
     */
    @Test
    void testAppointmentDetailByPatientId() throws EmptyDataAccessException {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(appointmentDao.findByPatientId(anyLong())).thenReturn(appointmentList);
        List<Appointment> actualAppointmentDetailByPatientIdResult = appointmentService.appointmentDetailByPatientId(123L);
        assertSame(appointmentList, actualAppointmentDetailByPatientIdResult);
        assertTrue(actualAppointmentDetailByPatientIdResult.isEmpty());
        verify(appointmentDao).findByPatientId(anyLong());
    }

    /**
     * Method under test: {@link AppointmentService#appointmentsDetail()}
     */
    @Test
    void testAppointmentsDetail() throws EmptyDataAccessException {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(appointmentDao.findAll()).thenReturn(appointmentList);
        List<Appointment> actualAppointmentsDetailResult = appointmentService.appointmentsDetail();
        assertSame(appointmentList, actualAppointmentsDetailResult);
        assertTrue(actualAppointmentsDetailResult.isEmpty());
        verify(appointmentDao).findAll();
    }
}

