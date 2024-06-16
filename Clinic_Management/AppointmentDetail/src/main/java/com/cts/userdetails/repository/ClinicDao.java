package com.cts.userdetails.repository;

import com.cts.userdetails.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClinicDao extends JpaRepository<Clinic,Long>{

}
