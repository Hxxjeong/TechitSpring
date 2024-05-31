package com.example.springdatajpa.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @Column(name = "job_id")
    private String jobId;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "min_salary")
    private Double minSalary;
    @Column(name = "max_salary")
    private Double maxSalary;

    @OneToMany(mappedBy = "job")
    private List<Employee> employees;

    @OneToMany(mappedBy = "job")
    private List<JobHistory> jobHistories;
}
