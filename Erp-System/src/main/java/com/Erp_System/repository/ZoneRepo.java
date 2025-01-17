package com.Erp_System.repository;

import com.Erp_System.Entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface ZoneRepo extends JpaRepository<Zone,Integer> {

}
