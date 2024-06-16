package com.cts.userdetails.repository;

import com.cts.userdetails.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentDao extends JpaRepository<Appointment,Long> {

    @Query("from Appointment where patientId=?1 order by date desc")
    public List<Appointment> findByPatientId(long id);
}
