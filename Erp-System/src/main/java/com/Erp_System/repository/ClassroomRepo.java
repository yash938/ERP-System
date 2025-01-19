package com.Erp_System.repository;

import com.Erp_System.Entity.Classroom;
import com.Erp_System.Entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ClassroomRepo  extends JpaRepository<Classroom,Integer> {

    Page<Classroom> findByTitleContaining(String title, Pageable pageable);
    Page<Classroom> findByDepartment(Department department,Pageable pageable);
}
