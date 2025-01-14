package com.Erp_System.repository;

import com.Erp_System.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
