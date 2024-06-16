package com.cts.userdetails.repository;

import com.cts.userdetails.model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends JpaRepository<Doctors,Long> {
}
