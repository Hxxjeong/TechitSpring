package com.example.springdatajpa.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "department_name")
    private String departmentName;

    // managerid, locationid FK
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToMany
    private List<JobHistory> jobHistories;
}
