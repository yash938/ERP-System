package com.Erp_System.Security;

import com.Erp_System.exception.ResourceNotFoundException;
import com.Erp_System.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user name is not found...!!"+email));

    }
}
