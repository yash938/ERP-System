package com.Erp_System.repository;

import com.Erp_System.Entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {

    Page<Department> findByUniversity(int universityId, Pageable pageable);
}
