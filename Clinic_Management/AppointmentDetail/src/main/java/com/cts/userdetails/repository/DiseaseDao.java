package com.cts.userdetails.repository;

import com.cts.userdetails.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseDao extends JpaRepository<Disease,Long> {


}
