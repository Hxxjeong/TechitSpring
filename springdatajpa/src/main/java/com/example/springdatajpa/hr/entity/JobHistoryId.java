package com.example.springdatajpa.hr.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JobHistoryId implements Serializable {
    private Integer employeeId;
    private LocalDate startDate;
}
