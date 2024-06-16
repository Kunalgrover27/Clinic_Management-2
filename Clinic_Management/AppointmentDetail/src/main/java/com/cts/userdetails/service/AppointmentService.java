package com.cts.userdetails.service;


import com.cts.userdetails.exception.EmptyDataAccessException;
import com.cts.userdetails.model.Appointment;
import com.cts.userdetails.model.SuccessResponse;
import com.cts.userdetails.repository.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AppointmentService {
    @Autowired
    private AppointmentDao appDao;
    @Autowired
    private SuccessResponse resp;
    public SuccessResponse createAppointment(Appointment app) throws EmptyDataAccessException {
        if(app==null){
            throw new EmptyDataAccessException("Object is null");
        }
        try {
            appDao.save(app);
        }catch (Exception e){resp.setMessage("Appointment is not registered...Try again..");}

        resp.setMessage("Appointment is added successfully.");
        return resp;

    }
    public List<Appointment> appointmentDetailByPatientId(long id) throws EmptyDataAccessException {
      List<Appointment> appointments=appDao.findByPatientId(id);
      if(appointments!=null){
      return appointments;
    }
      else {
      throw new EmptyDataAccessException("There are no appointments for the patient");

      }
    }

    public List<Appointment> appointmentsDetail() throws EmptyDataAccessException {
        List<Appointment> appList=appDao.findAll();
        if(appList!=null){
        return appList;
    }
      else {
        throw new EmptyDataAccessException("There are no appointments");

    }
    }
}
