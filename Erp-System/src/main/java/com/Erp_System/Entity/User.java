package com.Erp_System.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "user_name")
    private String name;

    @Column(unique = true)
    private String employeeId;

    @Column(name = "user_email", unique = true)
    private String email;

    private String gender;

    @Column(length = 12)
    private String phoneNumber;

    @Column(length = 1000)
    private String about;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    @Column(length = 100)
    private String domain;

    @Column(length = 500)
    private String address;

    @Column(name = "user_image_name")
    private String imageName;

    @Column(name = "user_password", length = 500)
    private String password;

}
