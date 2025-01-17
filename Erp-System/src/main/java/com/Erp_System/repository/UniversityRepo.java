package com.Erp_System.repository;

import com.Erp_System.Entity.University;
import com.Erp_System.Entity.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepo extends JpaRepository<University,Integer> {
    Page<University> findByZone(Zone zone, Pageable pageable);
}
