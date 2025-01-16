package com.Erp_System.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Zone {
    @Id
    private String zoneId;

    @Column(nullable = false)
    private String title;

    @Column(length = 10000)
    private String description;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<University> universities = new ArrayList<>();
}
