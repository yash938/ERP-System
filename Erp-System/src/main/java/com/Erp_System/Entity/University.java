package com.Erp_System.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class University {
    @Id
    private String universityId;

    @Column(nullable = false)
    private String title;

    @Column(length = 10000)
    private String description;

    @Column(length = 500)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Department> departments = new ArrayList<>();
}
