package com.example.springdatajpa.hr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "job_history")
@IdClass(JobHistoryId.class)
@Getter
@Setter
@NoArgsConstructor
public class JobHistory {
    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;
    @Id
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    // jobid, departmentid FK
    @ManyToOne
    @JoinColumn(name = "Job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
