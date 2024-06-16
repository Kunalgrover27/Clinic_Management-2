package com.cts.userdetails.controller;


import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Appointment;
import com.cts.userdetails.model.SuccessResponse;
import com.cts.userdetails.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")

@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping(value = "/addAppointment")
    public SuccessResponse addAppointment(@RequestBody Appointment app) throws EmptyDataAccessException {

        return appointmentService.createAppointment(app);
    }
    @GetMapping(value = "/getAllAppointments")
    public List<Appointment> appointmentsDetail() throws EmptyDataAccessException {
        return appointmentService.appointmentsDetail();
    }
    @GetMapping(value = "/getAllAppointmentsByPatientId/{id}")
    public List<Appointment> appointmentDetailByPatientId(@PathVariable long id) throws EmptyDataAccessException {
        return appointmentService.appointmentDetailByPatientId(id);
    }
}
