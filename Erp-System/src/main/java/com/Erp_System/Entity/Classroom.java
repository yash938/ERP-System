package com.Erp_System.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Classroom {

    @Id
    private String classroomId;

    @Column(nullable = false)
    private String title;

    @Column(length = 10000)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private  Department department;
}
