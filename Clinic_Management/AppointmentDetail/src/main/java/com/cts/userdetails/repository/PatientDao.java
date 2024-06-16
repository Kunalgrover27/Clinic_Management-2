package com.cts.userdetails.repository;

import com.cts.userdetails.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientDao extends JpaRepository<Patient,Long> {
    @Query("from Patient where email=?1")
    public Patient findByEmail(String email);
}

